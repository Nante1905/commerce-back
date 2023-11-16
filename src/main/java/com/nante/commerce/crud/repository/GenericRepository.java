package com.nante.commerce.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.commerce.crud.model.GenericModel;

public interface GenericRepository<T extends GenericModel> extends JpaRepository<T, Integer> {

}
