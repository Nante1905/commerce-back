package com.nante.commerce.controller.facture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.facture.Facture;
import com.nante.commerce.model.facture.FactureExplication;
import com.nante.commerce.services.employe.EmployeService;
import com.nante.commerce.services.facture.FactureExplicationService;
import com.nante.commerce.services.facture.FactureService;
import com.nante.commerce.types.response.Response;

import jakarta.security.auth.message.AuthException;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("factures")
public class FactureController extends GenericController<Facture> {
    @Autowired
    FactureService factureService;
    @Autowired
    EmployeService employeService;
    @Autowired
    FactureExplicationService explicationService;

    @Secured({ "FIN CHEF", "DG" })
    @GetMapping("/{id}/valider")
    public ResponseEntity<Response> valider(@PathVariable("id") int id) {
        try {
            factureService.valider(id);
            return ResponseEntity.ok(new Response(null, "Facture validée"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    @Secured({ "MAG RECEP", "MAG", "FIN" })
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

    @Secured({ "MAG", "FIN" })
    @GetMapping("/{id}")
    public ResponseEntity<Response> find(@PathVariable("id") int id) {
        Facture results;
        try {
            results = factureService.find(id);
            factureService.findProblemAvecBonCommande(results);
            return ResponseEntity.ok(new Response(results, ""));
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(new Response("Facture inexistante"));

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

    @Secured({ "MAG", "FIN" })
    @PostMapping("/{id}/explications")
    public ResponseEntity<Response> saveExplication(@PathVariable("id") int id,
            @RequestBody HashMap<String, String> inputs) {
        try {
            String text = inputs.get("text");
            FactureExplication res = explicationService.save(id, text);
            return ResponseEntity.ok(new Response(res, "Inserée avec succes"));
        } catch (Exception e) {
            if (e.getClass().equals(AuthException.class)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(e.getMessage()));
            }
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }
}
