package com.yanolja_final.domain.auth.controller;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanolja_final.domain.auth.controller.request.LoginRequest;
import com.yanolja_final.testutils.ControllerTestFixtureFactory;
import com.yanolja_final.testutils.fixture.UserFixture;
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
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @Autowired
    private ControllerTestFixtureFactory fixture;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
            .apply(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    @DisplayName("POST /v1/users/email/login (로그인)")
    void login() throws Exception {
        // given
        UserFixture userFixture = fixture.signUp();
        LoginRequest request = new LoginRequest(userFixture.getEmail(), userFixture.getPassword());
        String content = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = this.mockMvc
            .perform(post("/v1/users/email/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
            ).andDo(print());

        // then
        result
            .andExpect(status().isOk())
            .andExpect(cookie().exists("accessToken"));

        // restdocs
        result.andDo(document("/v1/users/email/login",
            requestFields(
                fieldWithPath("email").type(STRING)
                    .description("이메일"),
                fieldWithPath("password").type(STRING)
                    .description("비밀번호")
            ),
            responseFields(
                fieldWithPath("code").ignored()
            ),
            responseHeaders(
                headerWithName("Set-Cookie")
                    .description("쿠키에 JWT 액세스 토큰 설정")
            )
        ));
    }
}
