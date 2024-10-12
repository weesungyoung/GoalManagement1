package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

@Repository
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 회원가입
    public void insertUser(User user) {
        String query = "INSERT INTO user (email, password, name) VALUES (?,?,?)";
        jdbcTemplate.update(query, user.getEmail(), user.getPassword(), user.getName());
    }

    // 이메일로 사용자 조회
    public User selectUserByEmail(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{email}, (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setAdmin(rs.getInt("admin"));
                return user;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
