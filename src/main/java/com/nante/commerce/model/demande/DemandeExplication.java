package com.nante.commerce.model.demande;

import java.time.LocalDate;

import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.employe.Employe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "demande_explication")
public class DemandeExplication extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String motif;
    LocalDate jour;
    String texte;
    @ManyToOne
    @JoinColumn(name = "id_employe")
    Employe employe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public LocalDate getJour() {
        return jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String text) {
        this.texte = text;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

}
