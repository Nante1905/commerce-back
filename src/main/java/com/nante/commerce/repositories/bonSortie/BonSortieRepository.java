package com.nante.commerce.repositories.bonSortie;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.bonSortie.BonSortie;

@Repository
public interface BonSortieRepository extends GenericRepository<BonSortie> {

    @Query(nativeQuery = true, value = "select id, reference, jour, status, id_sortie from v_dispatch_article where id_direction = ?1 ")
    public List<BonSortie> getBonSortiePourDirection(int id);

    @Modifying
    @Query(nativeQuery = true, value = "update bon_sortie set status = ?1 where id = ?2")
    public void updateStatus(int status, int idSortie);
}
