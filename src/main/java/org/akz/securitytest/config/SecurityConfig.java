package org.akz.securitytest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.
//                csrf(AbstractHttpConfigurer::disable)
                csrf(
                csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        )

                .authorizeHttpRequests(
                        authorization -> authorization
                                .requestMatchers("/js/**", "/login").permitAll()
                                .requestMatchers("/admin").hasAuthority("Admin")
                                .requestMatchers("/user").hasAuthority("User")
                                .anyRequest().authenticated()
                )

                .formLogin(
                        login -> login
                                .loginPage("/login")
                                .successForwardUrl("/home")
                                .failureForwardUrl("/login?error=true")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?success=true")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll()
                );
        return http.build();
    }

}


