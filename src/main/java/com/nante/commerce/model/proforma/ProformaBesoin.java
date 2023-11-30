package com.nante.commerce.model.proforma;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProformaBesoin {
    @Id
    int idDemande;
    int idProforma;

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

}
