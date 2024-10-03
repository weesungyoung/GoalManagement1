package com.example.demo.controller;

import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class adminController {

    private final TicketService ticketService;

    @Autowired
    public adminController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/admin")
    public String showAdminForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "admin";
    }

    @PostMapping("/admin")
    public String addTicketCtrl(@ModelAttribute("ticket") Ticket ticket, Model model) {
        try {
            ticketService.addTicket(ticket);
            model.addAttribute("successMessage", "티켓이 성공적으로 생성되었습니다.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "티켓 생성 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "addSuccess";
    }

}
