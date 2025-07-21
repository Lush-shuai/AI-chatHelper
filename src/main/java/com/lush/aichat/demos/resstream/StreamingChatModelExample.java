package com.lush.aichat.demos.resstream;


import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

import static dev.langchain4j.model.LambdaStreamingResponseHandler.onPartialResponse;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/21 14:33
 */
public class StreamingChatModelExample {


    public static void main(String[] args) throws InterruptedException {
        OpenAiStreamingChatModel model = OpenAiStreamingChatModel.builder()
            .baseUrl("http://langchain4j.dev/demo/openai/v1")
            .apiKey("demo")
            .modelName("gpt-4o-mini")
            .build();

        String userMessage = "Tell me a joke";

        model.chat("Tell me a joke", onPartialResponse(System.out::print));


//        model.chat(userMessage, new StreamingChatResponseHandler() {
//
//            @Override
//            public void onPartialResponse(String partialResponse) {
//                System.out.println("onPartialResponse: " + partialResponse);
//            }
//
//            @Override
//            public void onCompleteResponse(ChatResponse completeResponse) {
//                System.out.println("onCompleteResponse: " + completeResponse);
//            }
//
//            @Override
//            public void onError(Throwable error) {
//                error.printStackTrace();
//            }
//        });

        Thread.sleep(100000L);
    }
}
