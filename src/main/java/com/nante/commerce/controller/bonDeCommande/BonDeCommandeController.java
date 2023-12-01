package com.nante.commerce.controller.bonDeCommande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.bonCommande.BonDeCommande;
import com.nante.commerce.services.bonCommande.BonCommandeService;
import com.nante.commerce.types.response.Response;

import jakarta.security.auth.message.AuthException;

@RestController
@RequestMapping("bon-commande")
public class BonDeCommandeController extends GenericController<BonDeCommande> {
    @Autowired
    BonCommandeService bonCommandeService;

    @GetMapping("{id}/valider")
    public ResponseEntity<Response> valider(@PathVariable("id") int id) {
        try {
            bonCommandeService.valider(id);
            return ResponseEntity.ok().body(new Response(null, "Bon de commande validé"));
        } catch (AuthException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(e.getMessage()));
        }
    }
}
