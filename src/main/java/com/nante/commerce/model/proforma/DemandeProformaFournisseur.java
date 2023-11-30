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
    @JoinColumn(name = "id_demande_proforma")
    Fournisseur fournisseur;

    int idDemandeProforma;

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

    public int getIdDemandeProforma() {
        return idDemandeProforma;
    }

    public void setIdDemandeProforma(int idDemandeProforma) {
        this.idDemandeProforma = idDemandeProforma;
    }

}
