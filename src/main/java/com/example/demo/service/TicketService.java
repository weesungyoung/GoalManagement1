package com.example.demo.service;

import com.example.demo.dao.SituationDAO;
import com.example.demo.dao.TicketDAO;
import com.example.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    private final TicketDAO ticketDAO;
    private final SituationDAO situationDAO;

    @Autowired
    public TicketService(TicketDAO ticketDAO, SituationDAO situationDAO)
    {
        this.situationDAO = situationDAO;
        this.ticketDAO = ticketDAO;
    }

    public void addTicket(Ticket ticket) {
        ticketDAO.insertTicket(ticket);
    }

    public List<Ticket> getTicketByEndDate(String date) {
        return ticketDAO.selectTicketsByDate(date);
    }

    public int currentCount(Integer ticketId) {
        return situationDAO.getCurrentTicketCount(ticketId);
    }

    public void purchaseTicket(Integer ticketID, String email) {
        situationDAO.insertTicketSit(ticketID, email);
    }
}
