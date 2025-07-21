package com.lush.aichat.demos.memory;


import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/21 11:18
 */
public class ChatMemoryExamples {

    public static void main(String[] args) {
        lowLevelChatMemory();

        highLevelChatMemory();
    }


    /**
     * High-level chat memory AI Service
     */

    interface Assistant {

        String chat(String message);
    }

    public static void highLevelChatMemory() {
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        OpenAiChatModel model = OpenAiChatModel.builder()
            .baseUrl("http://langchain4j.dev/demo/openai/v1")
            .apiKey("demo")
            .modelName("gpt-4o-mini")
            .build();

        Assistant assistant = AiServices.builder(Assistant.class)
            .chatMemory(chatMemory)
            .chatModel(model)
            .build();
        String answer = assistant.chat("Hello, my name is Klaus");
        System.out.println(answer);

        String answerWithName = assistant.chat("What is my name?");
        System.out.println(answerWithName);
    }


    /**
     * Low-level chat memory
     */
    public static void lowLevelChatMemory() {
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        OpenAiChatModel model = OpenAiChatModel.builder()
            .baseUrl("http://langchain4j.dev/demo/openai/v1")
            .apiKey("demo")
            .modelName("gpt-4o-mini")
            .build();

        chatMemory.add(UserMessage.from("Hello, my name is Klaus"));
        AiMessage answer = model.chat(chatMemory.messages()).aiMessage();
        System.out.println(answer.text());
        chatMemory.add(answer);


        chatMemory.add(UserMessage.from("What is my name?"));
        AiMessage answerWithName = model.chat(chatMemory.messages()).aiMessage();
        System.out.println(answerWithName.text());
        chatMemory.add(answerWithName);

    }
}
