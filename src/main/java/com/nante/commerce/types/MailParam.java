package com.nante.commerce.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nante.commerce.model.item.Fournisseur;

public class MailParam {
    String delaiLivraison;
    List<String> fournisseurs = new ArrayList<String>();
    HashMap<String, Object> articles;

    public String getDelaiLivraison() {
        return delaiLivraison;
    }

    public void setDelaiLivraison(String delaiLivraison) {
        this.delaiLivraison = delaiLivraison;
    }

    public List<String> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        fournisseurs.stream().forEach((fournisseur) -> {
            this.fournisseurs.add(fournisseur.getEmail());
        });
    }

    public HashMap<String, Object> getArticles() {
        return articles;
    }

    public void setArticles(HashMap<String, Object> articles) {
        this.articles = articles;
    }

}
