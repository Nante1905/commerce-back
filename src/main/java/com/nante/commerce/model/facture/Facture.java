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
import jakarta.persistence.Transient;

@Entity
@Table(name = "facture")
public class Facture extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int formatPrix;
    String reference;
    @OneToOne
    @JoinColumn(name = "id_bon_commande")
    BonDeCommande bonDeCommande;
    LocalDate jour;
    LocalDate jourReception;
    @ManyToOne
    @JoinColumn(name = "id_employe")
    Employe employe;
    int etat;
    LocalDate jourValidation;
    @OneToMany(mappedBy = "facture", cascade = CascadeType.PERSIST)
    List<FactureDetails> details;
    @Transient
    List<String> probleme;

    @PrePersist
    public void prePersist() {
        if (this.getJourReception() == null) {
            setJourReception(LocalDate.now());
        }
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

    public List<FactureDetails> getDetails() {
        return details;
    }

    public void setDetails(List<FactureDetails> details) {
        this.details = details;
    }

    public List<String> getProbleme() {
        return probleme;
    }

    public void setProbleme(List<String> probleme) {
        this.probleme = probleme;
    }

    public int getFormatPrix() {
        return formatPrix;
    }

    public void setFormatPrix(int formatPrix) {
        this.formatPrix = formatPrix;
    }

    public LocalDate getJourReception() {
        return jourReception;
    }

    public void setJourReception(LocalDate jourReception) {
        this.jourReception = jourReception;
    }

    public LocalDate getJourValidation() {
        return jourValidation;
    }

    public void setJourValidation(LocalDate jourValidation) {
        this.jourValidation = jourValidation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
