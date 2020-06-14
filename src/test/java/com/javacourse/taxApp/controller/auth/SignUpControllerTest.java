package com.javacourse.taxApp.controller.auth;

import com.javacourse.taxApp.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class SignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    SignUpController signUpController;

    @MockBean
    @Qualifier("userService")
    private UserService userService;

    private MultiValueMap<String,String> invalidData;

    @BeforeEach
    void setUp() {
        this.invalidData = new LinkedMultiValueMap<>();
        invalidData.put("username", Collections.singletonList("u45gser"));
        invalidData.put("password", Collections.singletonList("passw45gord"));
        invalidData.put("email", Collections.singletonList("erg34"));
        invalidData.put("full_name", Collections.singletonList("34g"));
        invalidData.put("address", Collections.singletonList("И4g53"));
        invalidData.put("group", Collections.singletonList("first"));
        invalidData.put("passport", Collections.singletonList("34g"));

    }

    @Test
    void notSuccessfulSignUp() throws Exception {
        mockMvc.perform(post("/auth/sign-up").params(invalidData))
                .andDo(print())
                .andExpect(view().name("auth/sign-up"));
    }
}