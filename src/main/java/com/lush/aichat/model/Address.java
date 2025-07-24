package com.lush.aichat.model;


import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/22 10:32
 */
@Description("an address") // you can add an optional description to help an LLM have a better understanding
@Data
public class Address {

    String street;
    Integer streetNumber;
    String city;
}
