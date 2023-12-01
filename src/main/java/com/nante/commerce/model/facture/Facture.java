package com.nante.commerce.model.facture;

import java.time.LocalDate;
import java.util.List;

import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.bonCommande.BonDeCommande;
import com.nante.commerce.model.bonLivraison.BonDeLivraisonDetails;
import com.nante.commerce.model.employe.Employe;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "facture")
public class Facture extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int format_prix;
    @OneToOne
    @JoinColumn(name = "id_bon_commande")
    BonDeCommande bonDeCommande;
    LocalDate jour;
    LocalDate jour_reception;
    @ManyToOne
    @JoinColumn(name = "id_employe")
    Employe employe;
    int etat;
    LocalDate jour_validation;
    @OneToMany(mappedBy = "facture", cascade = CascadeType.PERSIST)
    List<FactureDetails> details;

    @PrePersist
    public void prePersist() {
        if (getDetails() != null && getDetails().size() > 0) {
            for (FactureDetails d : details) {
                d.setFacture(this);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormat_prix() {
        return format_prix;
    }

    public void setFormat_prix(int format_prix) {
        this.format_prix = format_prix;
    }

    public BonDeCommande getBonDeCommande() {
        return bonDeCommande;
    }

    public void setBonDeCommande(BonDeCommande bonDeCommande) {
        this.bonDeCommande = bonDeCommande;
    }

    public LocalDate getJour() {
        return jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public LocalDate getJour_reception() {
        return jour_reception;
    }

    public void setJour_reception(LocalDate jour_reception) {
        this.jour_reception = jour_reception;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public LocalDate getJour_validation() {
        return jour_validation;
    }

    public void setJour_validation(LocalDate jour_validation) {
        this.jour_validation = jour_validation;
    }

    public List<FactureDetails> getDetails() {
        return details;
    }

    public void setDetails(List<FactureDetails> details) {
        this.details = details;
    }
}
