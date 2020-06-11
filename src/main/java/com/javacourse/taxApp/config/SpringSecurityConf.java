package com.javacourse.taxApp.config;

import com.javacourse.taxApp.controller.utils.AuthenticationSuccessWithSessionHandler;
import com.javacourse.taxApp.model.entities.Account;
import com.javacourse.taxApp.model.entities.Inspector;
import com.javacourse.taxApp.model.entities.User;
import com.javacourse.taxApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;


@Configuration
@EnableWebSecurity
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("accountService")
    private AccountService<Account> accountService;
    @Autowired
    @Qualifier("userService")
    private AccountService<User> userService;
    @Autowired
    @Qualifier("inspectorService")
    private AccountService<Inspector> inspectorService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/inspector/**").hasAuthority("INSPECTOR")
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/","/css/**","/graphics/**").permitAll()
                .antMatchers("/auth/**").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/sign-in")
                .loginProcessingUrl("/auth/sign-in")
                .defaultSuccessUrl("/", true)
                .successHandler(authenticationSuccessHandler())
                .failureUrl("/auth/sign-in?error=true")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/sign-out"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/auth/sign-in?logout=true")
                .permitAll();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessWithSessionHandler(accountService, inspectorService);
    }
}
