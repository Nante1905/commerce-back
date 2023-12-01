package com.nante.commerce.model.stock;

import java.time.LocalDate;

import com.nante.commerce.model.bonSortie.BonSortie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sortie_stock")
public class SortieStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate jour;
    @OneToOne
    @JoinColumn(name = "id_bon_sortie")
    BonSortie bonSortie;

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

    public BonSortie getBonSortie() {
        return bonSortie;
    }

    public void setBonSortie(BonSortie bonSortie) {
        this.bonSortie = bonSortie;
    }
}
