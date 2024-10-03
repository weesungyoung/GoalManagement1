package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome(HttpSession session, Model model) {
        String jwtToken = (String) session.getAttribute("jwtToken");
        boolean isLoggedIn = jwtToken != null;
        Integer adminStatus = (Integer) session.getAttribute("isAdmin");
        boolean isAdmin = (adminStatus != null) && (adminStatus == 1);


        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("isAdmin", isAdmin);
        return "index";
    }
}
