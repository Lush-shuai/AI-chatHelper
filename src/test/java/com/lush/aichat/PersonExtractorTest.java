package com.lush.aichat;


import cn.hutool.json.JSONUtil;
import com.lush.aichat.aiservice.GoogleAiAssistant;
import com.lush.aichat.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/22 10:34
 */
@SpringBootTest(classes = AiChatApplication.class)
public class PersonExtractorTest {

    @Autowired
    private GoogleAiAssistant googleAiAssistant;

    @Test
    public void test() {
        String text = """
            In 1968, amidst the fading echoes of Independence Day,
            a child named John arrived under the calm evening sky.
            This newborn, bearing the surname Doe, marked the start of a new journey.
            He was welcomed into the world at 345 Whispering Pines Avenue
            a quaint street nestled in the heart of Springfield
            an abode that echoed with the gentle hum of suburban dreams and aspirations.
            """;

        Person person = googleAiAssistant.extractPersonFrom(text);
        System.out.println(JSONUtil.toJsonStr(person));
    }
}
