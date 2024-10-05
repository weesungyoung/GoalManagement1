package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicketController {
    private final TicketService ticketService;
    private final JwtUtil jwtUtil;

    @Autowired
    public TicketController(TicketService ticketService, JwtUtil jwtUtil) {
        this.ticketService = ticketService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/purchase/{ticketId}/{availCount}")
    public String ticketPurchase(@PathVariable("ticketId") Integer ticketId, @PathVariable("availCount") Integer availCount, HttpSession session) {

        String jwtToken = (String) session.getAttribute("jwtToken");

        if (jwtToken != null) {
            try {
                String username = jwtUtil.extractUsername(jwtToken);
                if (ticketService.currentCount(ticketId, availCount)) {
                    ticketService.purchaseTicket(ticketId, username);
                    return "purchaseSuccess";
                } else {
                    return "redirect:/failedTicket";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/error";
            }
        } else {
            return "redirect:/login";
        }
    }

}
