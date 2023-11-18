package com.nante.commerce.repositories.employe;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.employe.Employe;

public interface EmployeRepository extends GenericRepository<Employe> {

    public Employe findByEmail(String email);

    @Query(value = "select * from employe where email=?1 and mot_de_passe=?2", nativeQuery = true)
    public Optional<Employe> findByEmailAndPassword(String email, String password);
}
