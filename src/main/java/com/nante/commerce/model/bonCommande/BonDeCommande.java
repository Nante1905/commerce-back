package com.nante.commerce.model.bonCommande;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.item.Fournisseur;
import com.nante.commerce.model.item.ModePaiement;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "bon_commande")
public class BonDeCommande extends GenericModel {
    String reference;
    LocalDate dateCreation;
    boolean livraisonPartielle;
    LocalDate delaiLivraison;
    int status;
    @ManyToOne
    @JoinColumn(name = "id_mode_paiement")
    ModePaiement paiement;
    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    Fournisseur fournisseur;
    @OneToMany(mappedBy = "bonDeCommande", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    List<BonDeCommandeDetails> details;
    int idDemandeProforma;

    @PrePersist
    public void prePersist() {
        if (getDateCreation() == null) {
            setDateCreation(LocalDate.now());
        }
    }

    public void addDetails(BonDeCommandeDetails details) {
        if (this.details == null) {
            setDetails(new ArrayList<BonDeCommandeDetails>());
        }
        this.details.add(details);
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public boolean isLivraisonPartielle() {
        return livraisonPartielle;
    }

    public void setLivraisonPartielle(boolean livraisonPartielle) {
        this.livraisonPartielle = livraisonPartielle;
    }

    public LocalDate getDelaiLivraison() {
        return delaiLivraison;
    }

    public void setDelaiLivraison(LocalDate delaiLivraison) {
        this.delaiLivraison = delaiLivraison;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ModePaiement getPaiement() {
        return paiement;
    }

    public void setPaiement(ModePaiement paiement) {
        this.paiement = paiement;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<BonDeCommandeDetails> getDetails() {
        return details;
    }

    public void setDetails(List<BonDeCommandeDetails> details) {
        this.details = details;
    }

    public int getIdDemandeProforma() {
        return idDemandeProforma;
    }

    public void setIdDemandeProforma(int idDemandeProforma) {
        this.idDemandeProforma = idDemandeProforma;
    }
}
