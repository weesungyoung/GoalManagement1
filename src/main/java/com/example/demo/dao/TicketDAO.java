package com.example.demo.dao;

import com.example.demo.model.Ticket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
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

    public List<Ticket> selectTicketsByDate(String date) {
        String query = "SELECT * FROM ticket WHERE endDate >= ?";
        try {
            return jdbcTemplate.query(query, new Object[]{date}, (rs, rowNum) -> {
                Ticket ticket = new Ticket();
                ticket.setTicketId(rs.getInt("ticketId"));
                ticket.setTitle(rs.getString("title"));
                ticket.setVenue(rs.getString("venue"));
                ticket.setPrice(rs.getInt("price"));
                ticket.setAvailCount(rs.getInt("availCount"));
                ticket.setStartDate(rs.getDate("startDate"));
                ticket.setEndDate(rs.getDate("endDate"));
                return ticket;
            });
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }



}
