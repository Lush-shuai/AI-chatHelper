package com.lush.aichat.demos.chat;


import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/22 09:56
 */
public class GoogleAiGeminiDemo {


    public static void main(String[] args) {
        GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
            .apiKey("AIzaSyDuhI46pFANMzZIF58u3Vb8CGquPPGb1i0")
            .modelName("gemini-2.0-flash")
            .build();
        String response = model.chat("Hello Gemini!");
        System.out.println(response);

    }
}
