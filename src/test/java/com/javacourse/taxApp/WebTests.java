package com.javacourse.taxApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource("/test_application.properties")
@Sql("/test_schema.sql")
@Sql("/test_db.sql")
@AutoConfigureMockMvc
public class WebTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldShowDefaultLogoFromHeader() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TAX REPORTING")));
    }

    @Test
    public void shouldAskUserForAuth() throws Exception{
        this.mockMvc.perform(get("/user/profile"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/auth/sign-in"));
    }

    @Test
    public void correctAuth() throws Exception{
        this.mockMvc.perform(formLogin().loginProcessingUrl("/auth/sign-in").user("neo").password("root"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void badCredentials() throws Exception{
        this.mockMvc.perform(formLogin().loginProcessingUrl("/auth/sign-in").user("root").password("root1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/sign-in?error=true"));
    }

}
