package com.nante.commerce.controller.bonDeCommande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.bonCommande.BonDeCommande;
import com.nante.commerce.services.bonCommande.BonCommandeService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("bon-commande")
public class BonDeCommandeController extends GenericController<BonDeCommande> {

}
