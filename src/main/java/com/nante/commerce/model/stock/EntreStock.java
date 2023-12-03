package com.nante.commerce.model.stock;

import java.time.LocalDate;
import java.util.List;

import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.bonEntre.BonEntre;
import com.nante.commerce.model.bonReception.BonReception;
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
@Table(name = "entre_stock")
public class EntreStock extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate jour;
    @OneToOne
    @JoinColumn(name = "id_bon_reception")
    BonReception bonReception;
    @ManyToOne
    @JoinColumn(name = "id_employe")
    Employe employe;
    @OneToMany(mappedBy = "entreStock", cascade = CascadeType.PERSIST)
    List<EntreStockDetails> details;

    @PrePersist
    public void prePersist() {
        if (getDetails() != null && getDetails().size() > 0) {
            for (EntreStockDetails d : details) {
                d.setEntreStock(this);
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

    public BonReception getBonReception() {
        return bonReception;
    }

    public void setBonReception(BonReception bonReception) {
        this.bonReception = bonReception;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public List<EntreStockDetails> getDetails() {
        return details;
    }

    public void setDetails(List<EntreStockDetails> details) {
        this.details = details;
    }

}
