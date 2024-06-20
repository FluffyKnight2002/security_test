package org.akz.securitytest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                                .requestMatchers("/js/**", "/login", "/create").permitAll()
                                .requestMatchers("/admin").hasAuthority("Admin")
                                .requestMatchers("/home").hasAuthority("User")
                                .anyRequest().authenticated()
                )

                .formLogin(
                        login -> login
                                .loginPage("/login")
                                .successHandler(customSuccessHandler())
                                .failureUrl("/login?error=true")
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
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CustomSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler();
    }

}


