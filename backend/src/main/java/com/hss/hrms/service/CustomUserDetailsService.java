package com.hss.hrms.service;

import com.hss.hrms.model.UserPrincipal;
import com.hss.hrms.model.Users;
import com.hss.hrms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Fetching the user data !");
        Users user = repo.findByEmail(email);

        if(user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("user not found ");
        }

        return new UserPrincipal(user);
    }
}
