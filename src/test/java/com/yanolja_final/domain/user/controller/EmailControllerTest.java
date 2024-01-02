package com.yanolja_final.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanolja_final.domain.user.dto.request.EmailRequest;
import com.yanolja_final.domain.user.service.EmailService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class EmailControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    void mailConfirm() throws Exception {
        EmailRequest emailRequest = new EmailRequest("user@example.com");
        given(emailService.sendVerificationEmail("user@example.com")).willReturn("200");

        this.mockMvc.perform(post("/v1/users/email/confirm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(emailRequest)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
            .andDo(document("/v1/users/email/confirm",
                requestFields(
                    fieldWithPath("email").type(JsonFieldType.STRING).description("이메일 주소")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER).description("200")
                )
            ));
    }

    @Test
    void verifyEmail() throws Exception {
        doNothing().when(emailService).verifyEmailCode("user@example.com", "123456");

        this.mockMvc.perform(get("/v1/users/email/verify/123456")
                .param("email", "user@example.com"))
            .andExpect(status().isOk())
            .andDo(document("/v1/users/email/verify",
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER).description("200")
                )
            ));
    }
}
