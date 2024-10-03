package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginController(UserService userService, JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpServletRequest request, Model model) {
        String token = userService.loginUser(email, password);
        if (token != null) {
            HttpSession session = request.getSession();
            session.setAttribute("isAdmin", jwtUtil.extractAdmin(token));
            session.setAttribute("jwtToken", token);
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "로그인 실패");
            return "login"; // 로그인 페이지로 돌아가기
        }
    }

}
