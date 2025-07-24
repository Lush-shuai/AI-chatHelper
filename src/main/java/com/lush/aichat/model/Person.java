package com.lush.aichat.model;

import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Description:
 * @author: shuaishuai.lu
 * @date: 2025/7/22 10:32
 */
@Data
public class Person {

    @Description("first name of a person") // you can add an optional description to help an LLM have a better understanding
    String firstName;
    String lastName;
    LocalDate birthDate;
    Address address;

}
