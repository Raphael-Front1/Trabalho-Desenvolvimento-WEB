package com.faculdade.biblioteca.config;

import com.faculdade.biblioteca.model.Categoria;
import com.faculdade.biblioteca.model.Usuario;
import com.faculdade.biblioteca.repository.CategoriaRepository;
import com.faculdade.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private CategoriaRepository categoriaRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedUsuarioAdmin();
        seedCategorias();
    }

    private void seedUsuarioAdmin() {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setNome("Administrador");
            admin.setSenhaHash(passwordEncoder.encode("admin123"));
            usuarioRepository.save(admin);
            System.out.println(">>> Usuário 'admin' criado (senha: admin123)");
        }
    }

    private void seedCategorias() {
        if (categoriaRepository.count() == 0) {
            List<String> nomes = List.of(
                    "Ficção",
                    "Técnico",
                    "Biografia",
                    "Romance",
                    "História",
                    "Autoajuda"
            );
            nomes.forEach(nome -> {
                Categoria c = new Categoria();
                c.setNome(nome);
                categoriaRepository.save(c);
            });
            System.out.println(">>> " + nomes.size() + " categorias padrão criadas");
        }
    }
}
