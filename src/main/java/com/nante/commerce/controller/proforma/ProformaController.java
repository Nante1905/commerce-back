package com.nante.commerce.controller.proforma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.proforma.DemandeProformaFournisseur;
import com.nante.commerce.model.proforma.resultat.ResultatProforma;
import com.nante.commerce.services.bonCommande.BonCommandeService;
import com.nante.commerce.services.proforma.ProformaService;
import com.nante.commerce.services.proforma.ResultatProformaService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("proforma")
public class ProformaController {
    @Autowired
    ProformaService proformaService;

    @Autowired
    ResultatProformaService resultatProformaService;
    @Autowired
    BonCommandeService bonCommandeService;

    @GetMapping("/sans-reponse")
    public ResponseEntity<?> findProformaSansReponse() {
        try {
            return ResponseEntity.ok().body(new Response(proformaService.findDemandesSansReponse(), null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/reponse")
    public ResponseEntity<?> findResultatProforma() {
        try {
            return ResponseEntity.ok().body(new Response(resultatProformaService.findAll(), null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    @PostMapping("{id}/reponse")
    public ResponseEntity<?> saveResultatProforma(@PathVariable("id") int id, @RequestBody ResultatProforma resultat) {
        try {
            resultat.setProforma(new DemandeProformaFournisseur(id));
            this.resultatProformaService.save(resultat);
            return ResponseEntity.ok().body(new Response(null, "Résultat insérée"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("{id}/bon-commande")
    public ResponseEntity<?> generer(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok().body(new Response(bonCommandeService.genererBonDeCommande(id), null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("avec-reponse")
    public ResponseEntity<?> findAllAvecReponse() {
        try {
            return ResponseEntity.ok().body(new Response(proformaService.findAllAvecReponse(), null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }
}
