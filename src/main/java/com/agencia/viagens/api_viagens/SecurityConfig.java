package com.agencia.viagens.api_viagens;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Desabilita CSRF para APIs REST
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/usuarios/**").permitAll() // Permite acesso sem autenticação
                .anyRequest().authenticated() // Requer autenticação para outros endpoints
            )
            .formLogin(form -> form
                .permitAll() // Permite acesso à página de login sem autenticação
            )
            .httpBasic(); // Configuração padrão para autenticação básica

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
