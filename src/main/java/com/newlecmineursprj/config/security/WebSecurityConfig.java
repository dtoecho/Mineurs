package com.newlecmineursprj.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {

    private final OAuth2UserDetailsService  oAuth2UserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("비밀번호 인코더 호출");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("시큐리티 필터체인 호출");
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/member/**").hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers("/admin/**", "/notices/reg").hasRole("ADMIN")
                        .requestMatchers("/myshop/**").hasRole("MEMBER")
                        .anyRequest().permitAll())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(new LoginSuccessHandler())
                        .failureHandler(new LoginAuthenticFailure())
                        .permitAll())
                .oauth2Login(config->config
                                .loginPage("/login")
                                .userInfoEndpoint(userInf->userInf
                                    .userService(oAuth2UserDetailsService))
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/")
                        .permitAll())
                .headers(headers -> headers.frameOptions().sameOrigin());
        return http.build();
    }

}
