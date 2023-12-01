package com.nante.commerce.model.bonSortie;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import com.nante.commerce.model.employe.Direction;
import com.nante.commerce.model.employe.Employe;
import com.nante.commerce.model.stock.TypeSortie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bon_sortie")
public class BonSortie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    TypeSortie type;
    @ManyToOne
    @JoinColumn(name = "id_direction")
    Direction direction;
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
