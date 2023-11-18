package com.nante.commerce.services.authentication;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nante.commerce.model.employe.Employe;
import com.nante.commerce.services.employe.EmployeService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeService employeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employe employe;
        try {
            employe = this.employeService.findByEmail(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(e.getMessage());
        }
        return new User(employe.getEmail(), "", new ArrayList<>());
    }

}
