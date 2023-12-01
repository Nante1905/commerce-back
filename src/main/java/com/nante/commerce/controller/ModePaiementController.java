package com.nante.commerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.item.ModePaiement;

@RequestMapping("paiement")
@RestController
public class ModePaiementController extends GenericController<ModePaiement> {

}