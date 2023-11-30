package com.nante.commerce.model.proforma;

import com.nante.commerce.model.item.Fournisseur;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DemandeProformaFournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    Fournisseur fournisseur;
    String reference;
    @ManyToOne
    @JoinColumn(name = "id_demande_proforma")
    DemandeProforma demandeProforma;
    int etat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public DemandeProforma getDemandeProforma() {
        return demandeProforma;
    }

    public void setDemandeProforma(DemandeProforma demandeProforma) {
        this.demandeProforma = demandeProforma;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

}
