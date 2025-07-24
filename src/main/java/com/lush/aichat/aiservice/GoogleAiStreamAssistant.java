package com.lush.aichat.aiservice;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/22 10:43
 */
@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, streamingChatModel = "googleAiGeminiStreamingChatModel")
public interface GoogleAiStreamAssistant {

    @SystemMessage("你是一个Java编程助手，使用中文解答问题")
    Flux<String> fluxChat(String message);
}
