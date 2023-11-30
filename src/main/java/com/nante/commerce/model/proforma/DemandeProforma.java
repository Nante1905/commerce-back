package com.nante.commerce.model.proforma;

import java.time.LocalDate;

import com.nante.commerce.crud.model.GenericModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DemandeProforma extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String reference;
    LocalDate delaiLivraison;
    LocalDate jourDemande;

    public DemandeProforma() {
    }

    public DemandeProforma(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDelaiLivraison() {
        return delaiLivraison;
    }

    public void setDelaiLivraison(LocalDate delaiLivraison) {
        this.delaiLivraison = delaiLivraison;
    }

    public void setDelaiLivraison(String delaiLivraison) {
        this.delaiLivraison = LocalDate.parse(delaiLivraison);
    }

    public LocalDate getJourDemande() {
        return jourDemande;
    }

    public void setJourDemande(LocalDate jourDemande) {
        this.jourDemande = jourDemande;
    }

}
