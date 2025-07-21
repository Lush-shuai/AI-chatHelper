package com.lush.aichat.demos.memory;


import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.langchain4j.data.message.ChatMessageDeserializer.messagesFromJson;
import static dev.langchain4j.data.message.ChatMessageSerializer.messagesToJson;

/**
 * @Description:  持久聊天记忆
 * @author: shuaishuai.lu
 * @date: 2025/7/21 13:38
 */
public class ServiceWithPersistentMemoryExample {


    interface Assistant {
        String chat(String message);
    }


    public static void main(String[] args) {
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.builder()
            .maxMessages(10)
            .chatMemoryStore(new PersistentChatMemoryStore())
            .build();

        OpenAiChatModel model = OpenAiChatModel.builder()
            .baseUrl("http://langchain4j.dev/demo/openai/v1")
            .apiKey("demo")
            .modelName("gpt-4o-mini")
            .build();

        Assistant assistant = AiServices.builder(Assistant.class)
            .chatModel(model)
            .chatMemory(chatMemory)
            .build();

        String answer = assistant.chat("Hello, my name is Klaus");
        System.out.println(answer);
//        String answerWithName = assistant.chat("What is my name?");
//        System.out.println(answerWithName);

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
