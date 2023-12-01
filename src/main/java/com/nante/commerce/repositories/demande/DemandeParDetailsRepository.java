package com.nante.commerce.repositories.demande;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.model.demande.DemandeDetailsID;
import com.nante.commerce.model.demande.DemandeParDetails;

public interface DemandeParDetailsRepository extends JpaRepository<DemandeParDetails, DemandeDetailsID> {
    public List<DemandeParDetails> findByEtatDemandeAndStatus(int etat, int status);

    public List<DemandeParDetails> findByEtatDemandeAndStatusAndDirection_id(int etat, int status,
            int idDirection);

    @Query(nativeQuery = true, value = "select * from v_demande_details where id_demande in (select id_demande from proforma_besoin where id_proforma = ?1) and status = 5")
    public List<DemandeParDetails> findDemandeOfProforma(int idProforma);

}
