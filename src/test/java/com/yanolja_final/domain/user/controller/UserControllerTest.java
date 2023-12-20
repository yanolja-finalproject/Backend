package com.yanolja_final.domain.user.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanolja_final.domain.user.controller.request.SignUpRequest;
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

    @DisplayName("POST /user (회원가입)")
    @Test
    void signUp() throws Exception {
        // given
        String email = "a@a.com";
        SignUpRequest request = new SignUpRequest(email, "password");
        String content = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = this.mockMvc
            .perform(post("/v1/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
            ).andDo(print());

        // then
        result
            .andExpect(status().isOk())
            .andExpect(jsonPath("data.email", is(email)));

        // restdocs
        result.andDo(document("user/signup",
            requestFields(
                fieldWithPath("email").type(STRING)
                    .description("이메일"),
                fieldWithPath("password").type(STRING)
                    .description("비밀번호")
            ),
            responseFields(
                fieldWithPath("code").ignored(),
                fieldWithPath("data.id").type(NUMBER)
                    .description("가입된 회원의 id(고유 번호)"),
                fieldWithPath("data.email").type(STRING)
                    .description("가입된 회원의 email")
            ))
        );
    }
}
