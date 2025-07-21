package com.lush.aichat.demos.memory;


import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.langchain4j.data.message.ChatMessageDeserializer.messagesFromJson;
import static dev.langchain4j.data.message.ChatMessageSerializer.messagesToJson;

/**
 * @Description:  为每个用户保留持久的聊天记忆
 * @author: shuaishuai.lu
 * @date: 2025/7/21 14:05
 */
public class ServiceWithPersistentMemoryForEachUserExample {

    interface Assistant {
        String chat(@MemoryId String memoryId, @UserMessage String userMessage);
    }

    public static void main(String[] args) {
        PersistentChatMemoryStore store = new PersistentChatMemoryStore();

        ChatMemoryProvider chatMemoryProvider = memoryId -> MessageWindowChatMemory.builder()
            .id(memoryId)
            .maxMessages(10)
            .chatMemoryStore(store)
            .build();

        OpenAiChatModel model = OpenAiChatModel.builder()
            .baseUrl("http://langchain4j.dev/demo/openai/v1")
            .apiKey("demo")
            .modelName("gpt-4o-mini")
            .build();

        Assistant assistant = AiServices.builder(Assistant.class)
            .chatModel(model)
            .chatMemoryProvider(chatMemoryProvider)
            .build();

        System.out.println(assistant.chat("1", "Hello, my name is Klaus"));
        System.out.println(assistant.chat("2", "Hi, my name is Francine"));

    }

    // 创建ChatMemoryStore 实现并随时存储聊天记忆
    static class PersistentChatMemoryStore implements ChatMemoryStore {

        private final Map<String, String> map = new HashMap<>();

        // 每当 chatMemory的用户请求所有消息时，都会调用 getMessages
        @Override
        public List<ChatMessage> getMessages(Object memoryId) {
            System.out.println("Getting messages for " + memoryId);
            String json = map.get((String) memoryId);
            System.out.println("get   Messages: " + json);
            return messagesFromJson(json);
        }

        // 更新聊天记忆。 通常在于LLM的每次交互中发生两次：一次是输入，一次是输出。
        @Override
        public void updateMessages(Object memoryId, List<ChatMessage> messages) {
            System.out.println("Updating messages for " + memoryId);
            String json = messagesToJson(messages);
            System.out.println("update   Messages: " + json);
            map.put((String) memoryId, json);
        }

        @Override
        public void deleteMessages(Object memoryId) {
            System.out.println("Deleting messages for " + memoryId);
            map.remove((String) memoryId);
        }
    }

}
