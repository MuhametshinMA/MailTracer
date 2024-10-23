package com.example.mailtracer.controller;

import com.example.mailtracer.requests.MailItemRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private MailItemRequest mailItemRequest;

    @BeforeEach
    public void setUp() {
        mailItemRequest = new MailItemRequest();
        mailItemRequest.setRecipient(""); // Пустой человек
        mailItemRequest.setType("InvalidType"); // Некорректный тип отправления
        mailItemRequest.setIndex("123"); // Некорректный индекс
        mailItemRequest.setAddress(""); // Пустой адрес
    }

    @Test
    public void testSave_invalidRequest_ReturnsValidationErrors() throws Exception {
        mockMvc.perform(post("/api/v1/mail/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mailItemRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.recipient").value("Получатель не может быть пустым"))
                .andExpect(jsonPath("$.type").value("Тип отправления должен быть: Письмо, Посылка, Бандероль, Открытка"))
                .andExpect(jsonPath("$.index").value("Индекс должен быть в формате 123456"))
                .andExpect(jsonPath("$.address").value("Адрес не может быть пустым"));
    }
}
