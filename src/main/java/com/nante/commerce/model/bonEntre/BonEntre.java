package com.nante.commerce.model.bonEntre;

import java.time.LocalDate;
import java.util.List;

import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.bonLivraison.BonDeLivraison;
import com.nante.commerce.model.bonLivraison.BonDeLivraisonDetails;
import com.nante.commerce.model.bonReception.BonReception;
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
@Table(name = "bon_entre")
public class BonEntre extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate jour;
    String reference;
    @OneToMany(mappedBy = "bonEntre", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    List<BonEntreDetails> details;

    @PrePersist
    public void prePersist() {
        if (getDetails() != null && getDetails().size() > 0) {
            for (BonEntreDetails d : details) {
                d.setBonEntre(this);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getJour() {
        return jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public List<BonEntreDetails> getDetails() {
        return details;
    }

    public void setDetails(List<BonEntreDetails> details) {
        this.details = details;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

}
