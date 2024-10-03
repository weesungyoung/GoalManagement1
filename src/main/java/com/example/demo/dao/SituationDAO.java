package com.example.demo.dao;

import com.example.demo.model.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SituationDAO {
    private final JdbcTemplate jdbcTemplate;

    public SituationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertTicketSit(Integer ticketId, String userEmail) {
        String query = "insert into ticketsituation (ticketId, userEmail, status) VALUES (?, ?, 'PURCHASED')";
        jdbcTemplate.update(query, ticketId, userEmail);
    }

    public int getCurrentTicketCount(Integer ticketId) {
        String query = "Select count(*) from ticketsituation where ticketId = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{ticketId}, Integer.class);
    }

}
