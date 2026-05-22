package com.faculdade.biblioteca.controller;

import com.faculdade.biblioteca.model.Categoria;
import com.faculdade.biblioteca.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired private CategoriaRepository repo;

    @GetMapping
    public List<Categoria> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Categoria criar(@RequestBody Categoria c) {
        return repo.save(c);
    }
}
