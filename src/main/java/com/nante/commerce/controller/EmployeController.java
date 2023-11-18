package com.nante.commerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.employe.Employe;

/**
 * EmployeController
 */
@RestController
@RequestMapping("employes")
public class EmployeController extends GenericController<Employe> {

}