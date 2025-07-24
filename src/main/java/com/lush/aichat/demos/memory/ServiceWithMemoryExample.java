package com.lush.aichat.demos.memory;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/21 11:13
 */
public class ServiceWithMemoryExample {

    public static void main(String[] args) {
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        OpenAiChatModel model = OpenAiChatModel.builder()
            .baseUrl("http://langchain4j.dev/demo/openai/v1")
            .apiKey("demo")
            .modelName("gpt-4o-mini")
            .build();
    }
}
