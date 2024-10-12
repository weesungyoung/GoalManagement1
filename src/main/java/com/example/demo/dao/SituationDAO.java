package com.example.demo.dao;

import com.example.demo.model.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    //클릭 테스트
//    public void insertTicketSit(Integer ticketId) {
//        String query = "insert into ticketsituation (ticketId, status) VALUES ( ?, 'PURCHASED')";
//        jdbcTemplate.update(query, ticketId);
//    }

    public int getCurrentTicketCount(Integer ticketId) {
        String query = "Select count(*) from ticketsituation where ticketId = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{ticketId}, Integer.class);
    }

    public List<Map<String, Object>> getPurchaseStatusToUser(String email) {
        String query = "SELECT DISTINCT * FROM ticket t JOIN ticketsituation ts ON t.ticketId = ts.ticketId WHERE ts.userEmail = ?;";
        return jdbcTemplate.queryForList(query, new Object[]{email});
    }

}
