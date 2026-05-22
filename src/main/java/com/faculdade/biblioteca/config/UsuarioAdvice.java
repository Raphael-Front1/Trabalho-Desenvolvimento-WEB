package com.faculdade.biblioteca.config;

import com.faculdade.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UsuarioAdvice {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @ModelAttribute("nomeUsuarioLogado")
    public String nomeUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
            return null;
        }
        return usuarioRepository.findByUsername(auth.getName())
                .map(u -> u.getNome())
                .orElse(auth.getName());
    }
}
