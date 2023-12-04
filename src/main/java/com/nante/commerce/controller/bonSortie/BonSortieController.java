package com.nante.commerce.controller.bonSortie;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.bonSortie.BonSortie;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("bon-sortie")
public class BonSortieController extends GenericController<BonSortie> {

    @Secured({ "MAG" })
    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<BonSortie> results = this.getService().findAll();
            return ResponseEntity.ok(new Response(results, ""));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    @Secured({ "MAG" })
    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") int id) {
        try {
            BonSortie results = this.getService().find(id);
            return ResponseEntity.ok(new Response(results, ""));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }
}
