package com.nante.commerce.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.model.employe.Employe;
import com.nante.commerce.services.employe.EmployeService;

@Service
public class AuthenticationService {
    @Autowired
    private EmployeService employeService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTManager jwtManager;

    public String login(String email, String password) throws Exception {
        try {
            Employe emp = this.employeService.findByEmailAndPassword(email, password);
            return jwtManager.generateToken(emp, customUserDetailsService.getAuthorities(emp));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
