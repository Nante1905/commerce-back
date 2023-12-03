package com.nante.commerce.model.bonSortie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.bonLivraison.BonDeLivraisonDetails;
import com.nante.commerce.model.employe.Direction;
import com.nante.commerce.model.employe.Employe;
import com.nante.commerce.model.stock.SortieStock;
import com.nante.commerce.model.stock.SortieStockDetails;
import com.nante.commerce.model.stock.TypeSortie;

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
@Table(name = "bon_sortie")
public class BonSortie extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String reference;
    LocalDate jour;
    @OneToOne
    @JoinColumn(name = "id_sortie")
    SortieStock sortie;
    @OneToMany(mappedBy = "sortie", cascade = CascadeType.PERSIST)
    List<BonSortieDetails> details;

    @PrePersist
    public void prePersist() {
        if (getDetails() != null && getDetails().size() > 0) {
            for (BonSortieDetails d : details) {
                d.setSortie(this);
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

    public List<BonSortieDetails> getDetails() {
        return details;
    }

    public void setDetails(List<BonSortieDetails> details) {
        this.details = details;
    }

    public void addDetails(BonSortieDetails details) {
        if (getDetails() == null) {
            setDetails(new ArrayList<BonSortieDetails>());
        }
        this.details.add(details);
    }

    public void setDetailsFromStock(List<SortieStockDetails> details) {
        for (SortieStockDetails d : details) {
            this.addDetails(new BonSortieDetails(d));
        }
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public SortieStock getSortie() {
        return sortie;
    }

    public void setSortie(SortieStock sortie) {
        this.sortie = sortie;
    }
}
