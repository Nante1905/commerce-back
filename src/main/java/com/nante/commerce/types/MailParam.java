package com.nante.commerce.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nante.commerce.model.item.Article;
import com.nante.commerce.model.item.Fournisseur;

public class MailParam {
    String delaiLivraison;
    List<String> fournisseurs = new ArrayList<String>();
    List<Article> articles;

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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
