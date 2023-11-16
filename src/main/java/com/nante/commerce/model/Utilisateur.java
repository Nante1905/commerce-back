package com.nante.commerce.model;

import com.nante.commerce.crud.model.GenericModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Utilisateur
 */
@Entity
public class Utilisateur extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nom;
    String email;

    public Utilisateur() {
    }

    // public String generateStringRoles() {
    // String roles = "";
    // for (Role role : this.roles) {
    // roles += " " + role.getNom();
    // }

    // return roles;
    // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
