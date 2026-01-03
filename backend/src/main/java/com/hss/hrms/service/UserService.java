package com.hss.hrms.service;
import com.hss.hrms.dto.LoginDTO;
import com.hss.hrms.model.Users;
import com.hss.hrms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Optional<Users> deleteUser(int id) {
        Optional<Users> optionalUser = userRepo.findById(id);
        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }
        userRepo.deleteById(id);
        return optionalUser;
    }


    public List<Users> getUsers(){
        return userRepo.findAll();
    }

    public String verify(LoginDTO user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getEmail());
            }
        } catch (AuthenticationException ex) {
            System.out.println("Authentication failed: " + ex.getMessage());
        }

        return "failed to verify user";
    }


    public Users getUserByEmail(String email){
        return userRepo.findByEmail(email);
    }
}

