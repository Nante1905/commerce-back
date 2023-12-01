package com.nante.commerce.services.employe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.employe.Employe;
import com.nante.commerce.repositories.employe.EmployeRepository;

import jakarta.security.auth.message.AuthException;

@Service
public class EmployeService extends GenericService<Employe> {
    @Autowired
    private EmployeRepository employeRepository;

    public Employe findByEmailAndPassword(String email, String password) throws Exception {
        return this.employeRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new Exception("Identifiants incorrects"));
    }

    public Employe findByEmail(String email) throws AuthException {
        return this.employeRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException("Aucun utilisateur connecté"));
    }

    public Employe getAuthenticated() throws AuthException {
        Employe authenticated = this.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return authenticated;
    }
}
