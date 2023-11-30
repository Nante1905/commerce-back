package com.nante.commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.demande.Demande;
import com.nante.commerce.services.demande.DemandeService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("demandes")
public class DemandeController extends GenericController<Demande> {
    @Autowired
    DemandeService demandeService;

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Demande model) {
        try {
            Demande results = demandeService.save(model);
            return ResponseEntity.ok(new Response(results, "Inseré avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    // @GetMapping("/ouverts")
    // public ResponseEntity<?> findDemandesOuverts() {
    // try {
    // List<Demande> results = demandeService.findOuverts();
    // return ResponseEntity.ok(new Response(results, "Liste des demandes
    // ouverts"));
    // } catch (Exception e) {
    // return ResponseEntity.status(500).body(new Response(e.getMessage()));
    // }
    // }

}
