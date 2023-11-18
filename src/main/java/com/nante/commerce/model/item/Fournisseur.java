package com.nante.commerce.model.item;

import com.nante.commerce.crud.model.GenericModel;

import jakarta.persistence.Entity;

@Entity
public class Fournisseur extends GenericModel {
    String reference;
    String nom;
    String email;
    String telephone;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
