package com.faculdade.biblioteca.controller;

import com.faculdade.biblioteca.model.Livro;
import com.faculdade.biblioteca.repository.CategoriaRepository;
import com.faculdade.biblioteca.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired private LivroRepository livroRepo;
    @Autowired private CategoriaRepository categoriaRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("livros", livroRepo.findAll());
        model.addAttribute("categorias", categoriaRepo.findAll());
        if (!model.containsAttribute("livro")) {
            model.addAttribute("livro", new Livro());
        }
        return "livros";
    }

    @PostMapping
    public String cadastrar(@Valid @ModelAttribute Livro livro, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("livros", livroRepo.findAll());
            model.addAttribute("categorias", categoriaRepo.findAll());
            return "livros";
        }
        livroRepo.save(livro);
        return "redirect:/livros";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!livroRepo.existsById(id)) return ResponseEntity.notFound().build();
        livroRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
