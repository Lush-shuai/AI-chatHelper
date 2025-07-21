package com.lush.aichat.demos.memory;


import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

/**
 * @Description: 为每个用户提供单独的聊天记录
 * @author: shuaishuai.lu
 * @date: 2025/7/21 11:31
 */
public class ServiceWithMemoryForEachUserExample {

    interface Assistant {
        String chat(@MemoryId int memoryId, @UserMessage String userMessage);
    }

    public static void main(String[] args) {
        OpenAiChatModel model = OpenAiChatModel.builder()
            .baseUrl("http://langchain4j.dev/demo/openai/v1")
            .apiKey("demo")
            .modelName("gpt-4o-mini")
            .build();

        Assistant assistant = AiServices.builder(Assistant.class)
            .chatModel(model)
            // 为每个用户创建一个单独的聊天记录
            .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
            .build();


        System.out.println(assistant.chat(1, "Hello, my name is Klaus"));

        System.out.println(assistant.chat(2, "Hello, my name is Francine"));

        System.out.println(assistant.chat(1, "What is my name?"));

        System.out.println(assistant.chat(2, "What is my name?"));

    }


}
