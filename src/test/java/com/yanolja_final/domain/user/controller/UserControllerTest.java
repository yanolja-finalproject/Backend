package com.yanolja_final.domain.user.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.BOOLEAN;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanolja_final.domain.user.dto.request.CreateUserRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
            .apply(documentationConfiguration(restDocumentation)).build();
    }

    @DisplayName("POST /v1/user (회원가입)")
    @Test
    void signUp_success() throws Exception {
        // given
        String email = "a@a.com";
        String username = "username1";
        String password = "password1";
        String phoneNumber = "010-1234-5678";
        Boolean isTermsAgreed = true;

        CreateUserRequest request = new CreateUserRequest(email, username, password, phoneNumber,isTermsAgreed);
        String content = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = this.mockMvc
            .perform(post("/v1/users/email")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
            ).andDo(print());

        // then
        result
            .andExpect(status().isOk())
            .andExpect(jsonPath("data.email", is(email)))
            .andExpect(jsonPath("data.username", is(username)))
            .andExpect(jsonPath("data.phoneNumber", is(phoneNumber)));

        // restdocs
        result.andDo(document("v1/users/email",
            requestFields(
                fieldWithPath("email").type(STRING).description("이메일"),
                fieldWithPath("username").type(STRING).description("사용자 이름"),
                fieldWithPath("password").type(STRING).description("비밀번호"),
                fieldWithPath("phoneNumber").type(STRING).description("전화 번호"),
                fieldWithPath("isTermsAgreed").type(BOOLEAN).description("이용약관 동의 여부")
            ),
            responseFields(
                fieldWithPath("code").ignored(),
                fieldWithPath("data.email").type(STRING).description("가입된 회원의 email"),
                fieldWithPath("data.username").type(STRING).description("가입된 회원의 사용자 이름"),
                fieldWithPath("data.phoneNumber").type(STRING).description("가입된 회원의 전화 번호")

            ))
        );
    }
}
