package com.lush.aichat.demos.chat;


import dev.langchain4j.model.openai.OpenAiChatModel;

import static java.time.Duration.ofSeconds;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/21 14:28
 */
public class OpenAiChatModelDemo2 {

    // 构建器模式创建可以指定每个参数的值。
    public static void main(String[] args) {
        OpenAiChatModel model = OpenAiChatModel.builder()
            .apiKey(System.getenv("OPENAI_API_KEY"))
            .modelName("gpt-4o-mini")
            .temperature(0.3)
            .timeout(ofSeconds(60))
            .logRequests(true)
            .logResponses(true)
            .build();
        String whoAreYou = model.chat("who are you");
        System.out.println(whoAreYou);
    }
}
