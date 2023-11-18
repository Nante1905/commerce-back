package com.nante.commerce.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.item.Fournisseur;
import com.nante.commerce.services.item.FournisseurService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("fournisseurs")
public class FournisseurController extends GenericController<Fournisseur> {
    @Autowired
    FournisseurService fournisseurService;

    @PostMapping("/articles")
    public ResponseEntity<?> findForArticles(@RequestBody HashMap<String, Object> inputs) {
        try {
            List<Integer> articles = ((List<Integer>) inputs.get("articles"));
            return ResponseEntity.ok().body(new Response(fournisseurService.findForArticles(articles), null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(new Response(e.getMessage()));

        }
    }

}
