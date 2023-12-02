package com.nante.commerce.controller.bonLivraison;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.bonLivraison.BonDeLivraison;

@RestController
@RequestMapping("bon-livraison")
public class BonLivraisonController extends GenericController<BonDeLivraison>{
    
}
