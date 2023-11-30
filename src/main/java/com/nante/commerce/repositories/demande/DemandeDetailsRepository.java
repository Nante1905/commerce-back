package com.nante.commerce.repositories.demande;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nante.commerce.model.demande.DemandeDetails;
import com.nante.commerce.model.demande.DemandeDetailsID;
import com.nante.commerce.model.demande.SelectionDetailsDemande;

public interface DemandeDetailsRepository extends JpaRepository<DemandeDetails, DemandeDetailsID> {

}
