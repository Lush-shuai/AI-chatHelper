package com.lush.aichat.controller;


import cn.hutool.json.JSONUtil;
import com.lush.aichat.aiservice.GoogleAiAssistant;
import com.lush.aichat.aiservice.GoogleAiStreamAssistant;
import com.lush.aichat.aiservice.OpenAiAssistant;
import dev.langchain4j.model.output.FinishReason;
import dev.langchain4j.model.output.TokenUsage;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.tool.ToolExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/21 16:07
 */
@RestController
public class ChatController {

    @Autowired
    OpenAiAssistant assistant;
    @Autowired
    GoogleAiAssistant googleAiAssistant;
    @Autowired
    GoogleAiStreamAssistant googleAiStreamAssistant;

    @GetMapping("/chat")
    public String model(@RequestParam(value = "message", defaultValue = "Hello") String message) {
        return assistant.chat(message);
    }

    @GetMapping("/chat2")
    public String model2(@RequestParam(value = "message", defaultValue = "Hello") String message) {
        Result<List<String>> result = assistant.generateOutlineFor(message);

        List<String> outline = result.content();
        TokenUsage tokenUsage = result.tokenUsage();
        List<Content> sources = result.sources();
        List<ToolExecution> toolExecutions = result.toolExecutions();
        FinishReason finishReason = result.finishReason();
        System.out.println("tokenUsage: " + tokenUsage);
        System.out.println("sources: " + sources);
        System.out.println("toolExecutions: " + toolExecutions);
        System.out.println("finishReason: " + finishReason);
        return JSONUtil.toJsonStr(outline);
    }


    @GetMapping("/chat3")
    public String chat3(@RequestParam(value = "message", defaultValue = "Hello") String message) {
        return googleAiAssistant.chat(message);
    }

    @GetMapping("/chat4")
    public Flux<String> chat4(@RequestParam(value = "message", defaultValue = "Hello") String message) {
        return googleAiStreamAssistant.fluxChat(message);
    }
}
