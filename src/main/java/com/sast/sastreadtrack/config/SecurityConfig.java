package com.sast.sastreadtrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // ✅ 关闭 Spring Security 的默认登录和认证机制
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 关闭跨站请求伪造
                .authorizeRequests()
                .anyRequest().permitAll() // 所有请求放行（交由 JwtFilter 处理）
                .and()
                .formLogin().disable() // 禁用默认表单登录
                .httpBasic().disable(); // 禁用弹窗式登录
        return http.build();
    }

    // ✅ 提供 BCrypt 密码加密器（给注册、登录时使用）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
