package com.shamim.eticket.controllers;

import com.shamim.eticket.global.constants.AppUrl;
import com.shamim.eticket.models.UserRegistration;
import com.shamim.eticket.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(AppUrl.AUTH_BASE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("loginUrl", AppUrl.LOGIN);
        model.addAttribute("registerUrl", AppUrl.REGISTER);
        return "shared/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userRegistration", new UserRegistration());
        model.addAttribute("loginUrl", AppUrl.LOGIN);
        model.addAttribute("registerUrl", AppUrl.REGISTER);
        return "shared/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegistration userRegistration, Model model) {
        try {
            authService.register(userRegistration);
            return "redirect:" + AppUrl.LOGIN + "?message=Registration successful!";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "shared/register";
        }
    }
}
