package com.nante.commerce.services.employe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.employe.Employe;
import com.nante.commerce.repositories.employe.EmployeRepository;

@Service
public class EmployeService extends GenericService<Employe> {
    @Autowired
    private EmployeRepository employeRepository;

    public Employe findByEmailAndPassword(String email, String password) throws Exception {
        return this.employeRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new Exception("Identifiants incorrects"));
    }

    public Employe findByEmail(String email) {
        return this.employeRepository.findByEmail(email);
    }

    public Employe getAuthenticated() throws Exception {
        Employe authenticated = this.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return authenticated;
    }
}
