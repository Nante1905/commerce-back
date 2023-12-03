package com.nante.commerce.repositories.bonReception;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.bonReception.BonReception;

@Repository
public interface BonReceptionRepo extends GenericRepository<BonReception> {
    @Query(nativeQuery = true, value = "select * from v_bon_reception_valide")
    public List<BonReception> findAllValide();
}
