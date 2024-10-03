package com.example.demo.service;

import com.example.demo.dao.TicketDAO;
import com.example.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketDAO ticketDAO;

    @Autowired
    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public void addTicket(Ticket ticket) {
        ticketDAO.insertTicket(ticket);
    }
}
