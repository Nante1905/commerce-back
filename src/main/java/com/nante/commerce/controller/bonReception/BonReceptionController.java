package com.nante.commerce.controller.bonReception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.bonLivraison.BonDeLivraison;
import com.nante.commerce.model.bonReception.BonReception;
import com.nante.commerce.services.bonReception.BonReceptionService;
import com.nante.commerce.services.employe.EmployeService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("bon-reception")
public class BonReceptionController extends GenericController<BonReception> {
    @Autowired
    BonReceptionService bonReceptionService;
    @Autowired
    EmployeService employeService;

    @GetMapping("/valides")
    public ResponseEntity<Response> findAll() {
        try {
            return ResponseEntity.ok().body(new Response(bonReceptionService.findAllValide(), null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BonReception model) {
        try {
            model.setEmploye(employeService.getAuthenticated());
            BonReception results = bonReceptionService.save(model);
            return ResponseEntity.ok(new Response(results, "Inserer avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }
}
