package com.example.demo.dao;

import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDAO {
    private final JdbcTemplate jdbcTemplate;

    public TicketDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertTicket(Ticket ticket) {
        String query = "INSERT INTO ticket (title, venue, price, availCount, startDate, endDate) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(query, ticket.getTitle(), ticket.getVenue(), ticket.getPrice(), ticket.getAvailCount(), ticket.getStartDate(), ticket.getEndDate());
    }

}
