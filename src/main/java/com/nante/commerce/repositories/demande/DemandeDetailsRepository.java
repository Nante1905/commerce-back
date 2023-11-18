package com.nante.commerce.repositories.demande;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.commerce.model.demande.DemandeDetails;
import com.nante.commerce.model.demande.DemandeDetailsID;

public interface DemandeDetailsRepository extends JpaRepository<DemandeDetails, DemandeDetailsID> {

}
