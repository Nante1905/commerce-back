package com.nante.commerce.model.bonLivraison;

import java.time.LocalDate;
import java.util.List;

import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.bonCommande.BonDeCommande;
import com.nante.commerce.model.employe.Employe;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "bon_livraison")
public class BonDeLivraison extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String reference;
    @OneToOne
    @JoinColumn(name = "id_bon_commande")
    BonDeCommande bonDeCommande;
    LocalDate jourSortie;
    LocalDate jourReception;
    @ManyToOne
    @JoinColumn(name = "id_employe")
    Employe employe;
    @OneToMany(mappedBy = "livraison", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<BonDeLivraisonDetails> details;

    @PrePersist
    public void prePersist() {
        if (getDetails() != null && getDetails().size() > 0) {
            for (BonDeLivraisonDetails d : details) {
                d.setLivraison(this);
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

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public LocalDate getJourSortie() {
        return jourSortie;
    }

    public void setJourSortie(LocalDate jourSortie) {
        this.jourSortie = jourSortie;
    }

    public LocalDate getJourReception() {
        return jourReception;
    }

    public void setJourReception(LocalDate jourReception) {
        this.jourReception = jourReception;
    }

    public List<BonDeLivraisonDetails> getDetails() {
        return details;
    }

    public void setDetails(List<BonDeLivraisonDetails> details) {
        this.details = details;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
