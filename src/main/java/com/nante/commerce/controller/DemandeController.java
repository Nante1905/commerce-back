package com.nante.commerce.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.demande.Demande;
import com.nante.commerce.model.demande.SelectionDetailsDemande;
import com.nante.commerce.services.demande.DemandeService;
import com.nante.commerce.types.response.Response;

import jakarta.transaction.Transactional;

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

    @GetMapping("/nature")
    // Achat ihany no tokony mahita
    public ResponseEntity<?> findByNature() {
        return ResponseEntity.ok(new Response(demandeService.findAllDemandeOuvertParDetails(), "OK"));
    }

    @GetMapping("/nature/valide")
    // Achat ihany no tokony mahita
    public ResponseEntity<?> findValideByNature() {
        return ResponseEntity.ok(new Response(demandeService.findAllDemandeValideParDetails(), "OK"));
    }

    @GetMapping("/nature/service")
    // Chef ihany no tokony mahita
    public ResponseEntity<?> findByNatureOfDirection() {
        // soloina direction de l'user connecté
        int idDirection = 4;
        return ResponseEntity
                .ok(new Response(demandeService.findAllDemandeOuvertParDetailsParDirection(idDirection), null));
    }

    @PostMapping("validation")
    public ResponseEntity<?> validerDemande(@RequestBody HashMap<String, List<SelectionDetailsDemande>> inputs) {
        List<SelectionDetailsDemande> selected = inputs.get("selected");
        List<SelectionDetailsDemande> rejected = inputs.get("rejected");

        if (inputs.get("selected") == null) {
            return ResponseEntity.ok(new Response("Liste de demande validée obligatoire"));
        }
        if (inputs.get("rejected") == null) {
            return ResponseEntity.ok(new Response("Liste de demande refusée obligatoire"));
        }
        demandeService.validerDemande(selected, rejected);
        return ResponseEntity.ok(new Response(null, "Demande validée"));
    }
}
