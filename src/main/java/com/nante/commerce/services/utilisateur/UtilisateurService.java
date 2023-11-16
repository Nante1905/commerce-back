package com.nante.commerce.services.utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.Utilisateur;
import com.nante.commerce.repositories.utilisateur.UtilisateurRepo;
import com.nante.commerce.services.authentication.JWTManager;

@Service
public class UtilisateurService extends GenericService<Utilisateur> {

    @Autowired
    UtilisateurRepo userRepo;

    @Autowired
    JWTManager jwt;

    public Utilisateur findByEmail(String email) throws Exception {
        return this.userRepo.findByEmail(email)
                .orElseThrow(() -> new Exception("User of email " + email + " not found"));
    }

    // public String login(AuthClass user) throws Exception {
    // Utilisateur utilisateur =
    // this.userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword())
    // .orElseThrow(() -> new Exception("Identifiants invalides"));

    // return jwt.generateToken(utilisateur);
    // }
}
