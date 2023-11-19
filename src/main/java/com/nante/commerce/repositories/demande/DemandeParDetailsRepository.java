package com.nante.commerce.repositories.demande;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.commerce.model.demande.DemandeDetailsID;
import com.nante.commerce.model.demande.DemandeParDetails;

public interface DemandeParDetailsRepository extends JpaRepository<DemandeParDetails, DemandeDetailsID> {
    public List<DemandeParDetails> findByEstOuvertAndStatus(boolean estOuvert, int status);

    public List<DemandeParDetails> findByEstOuvertAndStatusAndDirection_id(boolean estOuvert, int status,
            int idDirection);

}
