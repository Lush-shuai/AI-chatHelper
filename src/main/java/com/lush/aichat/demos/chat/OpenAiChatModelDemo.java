package com.lush.aichat.demos.chat;


import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/21 10:06
 */
public class OpenAiChatModelDemo {

    public static void main(String[] args) {
        /**
         * 聊天快速入门
         */
        chat("What is your name?");

        /**
         * 聊天多轮对话
         */
        multipleChat();
    }


    public static void multipleChat() {
        OpenAiChatModel model = OpenAiChatModel.builder()
            .baseUrl("http://langchain4j.dev/demo/openai/v1")
            .apiKey("demo")
            .modelName("gpt-4o-mini")
            .build();

        /**
         * 多轮对话,可以以chatMemory跟高级的聊天记忆进行对话  {@link  com.lush.aichat.demos.memory.ChatMemoryExamples}
         */
        UserMessage firstUserMessage = UserMessage.from("Hello, my name is Klaus");
        AiMessage firstAiMessage = model.chat(firstUserMessage).aiMessage();
        System.out.println(firstAiMessage.text());
        UserMessage secondUserMessage = UserMessage.from("What is my name?");
        AiMessage secondAiMessage = model.chat(firstUserMessage, firstAiMessage, secondUserMessage).aiMessage();
        System.out.println(secondAiMessage.text());
    }

    public static void chat(String question) {
        OpenAiChatModel model = OpenAiChatModel.builder()
            .baseUrl("http://langchain4j.dev/demo/openai/v1")
            .apiKey("demo")
            .modelName("gpt-4o-mini")
            .build();

        String answer = model.chat(question);
        System.out.println(answer);
    }



}
