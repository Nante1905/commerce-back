package com.nante.commerce.model.bonEntre;

import java.time.LocalDate;

import com.nante.commerce.model.bonLivraison.BonDeLivraison;
import com.nante.commerce.model.bonReception.BonReception;
import com.nante.commerce.model.employe.Employe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bon_entre")
public class BonEntre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne
    @JoinColumn(name = "id_bon_reception")
    BonReception bonReception;
    LocalDate jour;
    @ManyToOne
    @JoinColumn(name = "id_employe")
    Employe employe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BonReception getBonReception() {
        return bonReception;
    }

    public void setBonReception(BonReception bonReception) {
        this.bonReception = bonReception;
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
}
