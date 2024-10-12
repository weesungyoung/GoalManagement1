package com.example.demo.controller;

import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class HomeController {

    private final TicketService ticketService;

    @Autowired
    public HomeController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public String showHome(HttpSession session, Model model) {
        String jwtToken = (String) session.getAttribute("jwtToken");
        boolean isLoggedIn = jwtToken != null;
        Integer adminStatus = (Integer) session.getAttribute("isAdmin");
        boolean isAdmin = (adminStatus != null) && (adminStatus == 1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        List<Ticket> tickets = ticketService.getTicketByEndDate(dateFormat.format(currentDate));

        model.addAttribute("ticket", tickets);
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("isAdmin", isAdmin);
        return "index";
    }
}
