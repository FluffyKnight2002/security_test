package org.akz.securitytest.controller;

import lombok.RequiredArgsConstructor;
import org.akz.securitytest.dto.UserDto;
import org.akz.securitytest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/create")
    public String createUser(UserDto userDto) {

        userService.createUser(userDto);
        return "redirect:/login";
    }

}
