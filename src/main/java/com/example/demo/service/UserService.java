package com.example.demo.service;

import com.example.demo.config.JwtUtil;
import com.example.demo.dao.SituationDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final SituationDAO situationDAO;

    @Autowired
    public UserService(UserDAO userDAO, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil, SituationDAO situationDAO) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.situationDAO = situationDAO;
    }

    public void registerUser(String email, String password, String name) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(email, encodedPassword, name);
        userDAO.insertUser(user);
    }

    public String loginUser(String email, String password) {
        User user = userDAO.selectUserByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(user.getEmail(), user.getAdmin());
        } else {
            return null;
        }
    }

    public List<Map<String, Object>> getPurchaseStatus(String email) {
        return situationDAO.getPurchaseStatusToUser(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.selectUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
