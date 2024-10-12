package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class UserInfoController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserInfoController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/userInfo")
    public String showUserInfo(HttpSession session, Model model) {
        String jwtToken = (String) session.getAttribute("jwtToken");
        List<Map<String, Object>> purchaseHistoryList = userService.getPurchaseStatus(jwtUtil.extractUserEmail(jwtToken));
        model.addAttribute("purchaseHistory", purchaseHistoryList);
        return "userInfo";
    }

}
