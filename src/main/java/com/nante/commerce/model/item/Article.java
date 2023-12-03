package com.nante.commerce.model.item;

import com.nante.commerce.crud.model.GenericModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Article extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String reference;
    String designation;
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    Categorie categorie;

    @Transient
    double qte;
    int gestion;

    public String modeGestion() throws Exception {
        if (gestion == 0) {
            return "LIFO";
        }
        if (gestion == 5) {
            return "FIFO";
        }
        if (gestion == 10) {
            return "CUMP";
        }
        throw new Exception("Mode de gestion inconnu");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

}
