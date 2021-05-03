package com.yoonho.bowlog.config;

import com.yoonho.bowlog.user.security.CustomAuthenticationProvider;
import com.yoonho.bowlog.user.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/login", "/sign-up","/profile", "/feed").permitAll()
                .mvcMatchers(HttpMethod.GET, "/profile/*").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login").permitAll();

        http.logout()
                .logoutSuccessUrl("/");

        //tokenRepository는 username, 토큰(랜덤, 매번 바뀜), 시리즈(랜덤, 고정)의 세 가지 값을 데이터베이스에 저장하기 위해 인터페이스의 구현체를 주입해줘야함.
        http.rememberMe()
                .userDetailsService(customUserDetailsService)
                .tokenRepository(tokenRepository());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);

        return jdbcTokenRepository;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 리소스 폴더는 시큐리티 필터를 적용시키지 않음.
        web.ignoring()
                .mvcMatchers("/css/**", "/js/**", "/webfonts/**", "/images/**", "/error", "/assets/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
