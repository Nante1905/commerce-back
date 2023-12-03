package com.nante.commerce.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.crud.repository.GenericRepository;

public class GenericService<T extends GenericModel> {
    @Autowired
    GenericRepository<T> repository;

    public List<T> findAll() {
        return repository.findAll();
    }

    public T find(int id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Entité inexistant"));
    }

    public T save(T model) {
        return repository.save(model);
    }

    public T update(T model, int id) {
        model.setId(id);
        return repository.save(model);
    }

    public GenericRepository<T> getRepository() {
        return repository;
    }
}
