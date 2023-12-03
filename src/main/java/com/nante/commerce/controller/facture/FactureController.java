package com.nante.commerce.controller.facture;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.facture.Facture;
import com.nante.commerce.services.employe.EmployeService;
import com.nante.commerce.services.facture.FactureService;
import com.nante.commerce.types.response.Response;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("factures")
public class FactureController extends GenericController<Facture> {
    @Autowired
    FactureService factureService;
    @Autowired
    EmployeService employeService;

    @GetMapping("{id}/valider")
    public ResponseEntity<Response> valider(@PathParam("id") int id) {
        try {
            factureService.valider(id);
            return ResponseEntity.ok(new Response(null, ""));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Response> findAll() {
        try {
            List<Facture> results = factureService.findAll();
            for (Facture facture : results) {
                factureService.findProblemAvecBonCommande(facture);
            }

            return ResponseEntity.ok(new Response(results, ""));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody Facture model) {
        try {
            model.setEmploye(employeService.getAuthenticated());
            Facture results = factureService.save(model);
            return ResponseEntity.ok(new Response(results, "Inserée avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }
}
