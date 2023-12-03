package com.nante.commerce.controller.bonDeCommande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.model.item.ModePaiement;
import com.nante.commerce.services.bonCommande.BonCommandeService;
import com.nante.commerce.types.response.Response;

import jakarta.security.auth.message.AuthException;

@RestController
@RequestMapping("bon-commande")
public class BonDeCommandeController extends GenericController<BonDeCommande> {
    @Autowired
    BonCommandeService bonCommandeService;

    @GetMapping("")
    public ResponseEntity<Response> findAll() {
        try {
            return ResponseEntity.ok().body(new Response(bonCommandeService.findAll(), null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/valides")
    public ResponseEntity<Response> findAllValide() {
        try {
            return ResponseEntity.ok().body(new Response(bonCommandeService.findBonDeCommandesValides(), null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok().body(new Response(bonCommandeService.find(id), "Bon de commande validé"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(e.getMessage()));
        }
    }

    @Secured({ "FIN CHEF", "DG" })
    @PostMapping("{id}/valider")
    public ResponseEntity<Response> valider(@PathVariable("id") int id, @RequestBody ModePaiement paiement) {
        try {
            bonCommandeService.valider(id, paiement);
            return ResponseEntity.ok().body(new Response(null, "Bon de commande validé"));
        } catch (AuthException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(e.getMessage()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.ok().body(new Response(null, e.getMessage()));
        }
    }
}
