package com.nante.commerce.repositories.utilisateur;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.Utilisateur;

public interface UtilisateurRepo extends GenericRepository<Utilisateur> {
    public Optional<Utilisateur> findByEmail(String email);

    @Query(value = "select * from utilisateur where email = ?1 and password = ?2", nativeQuery = true)
    public Optional<Utilisateur> findByEmailAndPassword(String email, String password);
}
