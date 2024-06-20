package org.akz.securitytest.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    String url;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        authentication.getAuthorities().forEach(authority -> {
            if (authority.getAuthority().equals("Admin")) {
                url = "/admin";
            } else if (authority.getAuthority().equals("User")) {
                url = "/home";
            }
        });
        response.sendRedirect(url);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        authentication.getAuthorities().forEach(authority -> {
            if (authority.getAuthority().equals("Admin")) {
                url = "/admin";
            } else if (authority.getAuthority().equals("User")) {
                url = "/home";
            }
        });
        response.sendRedirect(url);
    }

}
