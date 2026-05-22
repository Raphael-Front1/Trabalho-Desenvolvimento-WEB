package com.faculdade.biblioteca.controller;

import com.faculdade.biblioteca.model.Usuario;
import com.faculdade.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String exibirFormulario() {
        return "registro";
    }

    @PostMapping("/registro")
    public String cadastrar(@RequestParam String nome,
                            @RequestParam String username,
                            @RequestParam String senha,
                            @RequestParam String confirmarSenha,
                            Model model) {

        if (nome == null || nome.isBlank() || username == null || username.isBlank() || senha == null || senha.isBlank()) {
            model.addAttribute("erro", "Todos os campos são obrigatórios.");
            return "registro";
        }

        if (senha.length() < 6) {
            model.addAttribute("erro", "A senha deve ter pelo menos 6 caracteres.");
            return "registro";
        }

        if (!senha.equals(confirmarSenha)) {
            model.addAttribute("erro", "As senhas não conferem.");
            return "registro";
        }

        if (usuarioRepository.findByUsername(username).isPresent()) {
            model.addAttribute("erro", "Este usuário já está em uso. Escolha outro.");
            return "registro";
        }

        Usuario novo = new Usuario();
        novo.setNome(nome.trim());
        novo.setUsername(username.trim());
        novo.setSenhaHash(passwordEncoder.encode(senha));
        usuarioRepository.save(novo);

        return "redirect:/login?cadastrado=true";
    }
}
