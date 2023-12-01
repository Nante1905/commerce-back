package com.nante.commerce.model.stock;

import java.time.LocalDate;

import com.nante.commerce.model.bonEntre.BonEntre;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "entre_stock")
public class EntreStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate jour;
    @OneToOne
    @JoinColumn(name = "id_bon_entre")
    BonEntre bonEntre;

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

    public BonEntre getBonEntre() {
        return bonEntre;
    }

    public void setBonEntre(BonEntre bonEntre) {
        this.bonEntre = bonEntre;
    }
}
