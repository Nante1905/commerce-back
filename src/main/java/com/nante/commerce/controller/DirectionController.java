package com.nante.commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.employe.Direction;
import com.nante.commerce.services.demande.DemandeService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("directions")
public class DirectionController extends GenericController<Direction> {

    @Autowired
    private DemandeService demandeService;

    @GetMapping("/{id}/demandes")
    public ResponseEntity<?> findByDirectionId(@PathVariable("id") int idDirection) {
        try {
            return ResponseEntity.ok(new Response(demandeService.findByDirectionId(idDirection), "Liste des demandes"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }
}
