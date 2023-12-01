package com.nante.commerce.model.proforma;

import com.nante.commerce.crud.model.GenericModel;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * StatistiqueProforma
 */

@Entity
@Table(name = "v_statistique_proforma")
public class StatistiqueProforma extends GenericModel {
    @Transient
    DemandeProforma proforma;
    int reponse;
    int demande;

    public DemandeProforma getProforma() {
        return proforma;
    }

    public void setProforma(DemandeProforma proforma) {
        this.proforma = proforma;
    }

    public int getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }

    public int getDemande() {
        return demande;
    }

    public void setDemande(int demande) {
        this.demande = demande;
    }

}