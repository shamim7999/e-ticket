package com.shamim.eticket.controllers;

import com.shamim.eticket.global.constants.AppUrl;
import com.shamim.eticket.models.UserRegistration;
import com.shamim.eticket.services.interfaces.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(AppUrl.AUTH_BASE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private static final Logger logger =
            LoggerFactory.getLogger(AuthController.class);

    @ModelAttribute
    public void commonAttributes(HttpServletRequest request, Model model) {
        logger.info("Inside commonAttributes method");
        model.addAttribute("loginUrl", AppUrl.LOGIN);
        model.addAttribute("registerUrl", AppUrl.REGISTER);
        logger.info("Request is: {}", request.getRequestURI());
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String message, Model model) {
        logger.info("Inside /login..");
        model.addAttribute("message", message);
        logger.info("Ending /login..");
        return "shared/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userRegistration", new UserRegistration());
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
