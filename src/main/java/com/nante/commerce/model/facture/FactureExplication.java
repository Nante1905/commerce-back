package com.nante.commerce.model.facture;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.employe.Employe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "facture_explication")
public class FactureExplication extends GenericModel {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_facture")
    Facture facture;
    @ManyToOne
    @JoinColumn(name = "id_employe")
    Employe employe;
    String explication;
    @Column(insertable = false)
    LocalDateTime jour;

    public FactureExplication() {
    }

    public FactureExplication(int idFacture, String explication, Employe emp) {
        setFacture(new Facture(idFacture));
        setExplication(explication);
        setEmploye(emp);
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }

    public LocalDateTime getJour() {
        return jour;
    }

    public void setJour(LocalDateTime jour) {
        this.jour = jour;
    }
}
