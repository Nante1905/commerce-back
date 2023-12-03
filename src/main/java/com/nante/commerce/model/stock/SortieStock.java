package com.nante.commerce.model.stock;

import java.time.LocalDate;
import java.util.List;

import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.bonSortie.BonSortie;
import com.nante.commerce.model.bonSortie.BonSortieDetails;
import com.nante.commerce.model.employe.Direction;
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
@Table(name = "sortie_stock")
public class SortieStock extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate jour;
    @ManyToOne
    @JoinColumn(name = "id_type")
    TypeSortie type;
    @ManyToOne
    @JoinColumn(name = "id_direction")
    Direction direction;
    @ManyToOne
    @JoinColumn(name = "id_employe")
    Employe employe;
    @OneToMany(mappedBy = "sortieStock", cascade = CascadeType.PERSIST)
    List<SortieStockDetails> details;

    @PrePersist
    public void prePersist() {
        if (getDetails() != null && getDetails().size() > 0) {
            for (SortieStockDetails d : details) {
                d.setSortieStock(this);
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

    public TypeSortie getType() {
        return type;
    }

    public void setType(TypeSortie type) {
        this.type = type;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public List<SortieStockDetails> getDetails() {
        return details;
    }

    public void setDetails(List<SortieStockDetails> details) {
        this.details = details;
    }

}
