package com.lush.aichat.aiservice;


import com.lush.aichat.model.Person;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/22 10:20
 */
@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = "googleAiGeminiChatModel")
public interface GoogleAiAssistant {

    @SystemMessage("你是一个Java编程助手")
    String chat(String userMessage);

    @UserMessage("Extract information about a person from {{it}}")
    Person extractPersonFrom(String text);

}
