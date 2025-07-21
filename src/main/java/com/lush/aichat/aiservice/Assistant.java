package com.lush.aichat.aiservice;

import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

import java.util.List;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/21 16:31
 */
@AiService
public interface Assistant {

    @SystemMessage("You are a polite assistant")
    String chat(String userMessage);


    @UserMessage("Generate an outline for the article on the following topic: {{it}}")
    Result<List<String>> generateOutlineFor(String topic);
}
