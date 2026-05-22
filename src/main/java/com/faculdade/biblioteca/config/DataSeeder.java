package com.faculdade.biblioteca.config;

import com.faculdade.biblioteca.model.Usuario;
import com.faculdade.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setNome("Administrador");
            admin.setSenhaHash(passwordEncoder.encode("admin123"));
            usuarioRepository.save(admin);
            System.out.println(">>> Usuário 'admin' criado (senha: admin123)");
        }
    }
}
