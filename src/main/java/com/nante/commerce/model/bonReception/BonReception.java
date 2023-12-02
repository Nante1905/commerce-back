package com.nante.commerce.model.bonReception;

import java.time.LocalDate;
import java.util.List;

import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.bonLivraison.BonDeLivraison;
import com.nante.commerce.model.bonLivraison.BonDeLivraisonDetails;
import com.nante.commerce.model.employe.Employe;

import jakarta.annotation.Generated;
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
@Table(name = "bon_reception")
public class BonReception extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne
    @JoinColumn(name = "id_bon_livraison")
    BonDeLivraison bonDeLivraison;
    LocalDate jour;
    @ManyToOne
    @JoinColumn(name = "id_employe")
    Employe employe;
    @OneToMany(mappedBy = "bonReception", cascade = CascadeType.PERSIST)
    List<BonReceptionDetails> details;

    @PrePersist
    public void prePersist() {
        if (getDetails() != null && getDetails().size() > 0) {
            for (BonReceptionDetails d : details) {
                d.setBonReception(this);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BonDeLivraison getBonDeLivraison() {
        return bonDeLivraison;
    }

    public void setBonDeLivraison(BonDeLivraison bonDeLivraison) {
        this.bonDeLivraison = bonDeLivraison;
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

    public List<BonReceptionDetails> getDetails() {
        return details;
    }

    public void setDetails(List<BonReceptionDetails> details) {
        this.details = details;
    }
}
