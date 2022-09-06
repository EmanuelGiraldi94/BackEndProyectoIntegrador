package com.PIF.PIF.service;


import com.PIF.PIF.entities.Usuario;
import com.PIF.PIF.entities.UsuarioRol;
import com.PIF.PIF.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component

public class DataLoader implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String hash1=passwordEncoder.encode("1234");
        Usuario usuario1=new Usuario("Emanuel","Ematechi","ematechi@gmail.com",
                hash1, UsuarioRol.ROLE_ADMIN);
        usuarioRepository.save(usuario1);

        String hash2=passwordEncoder.encode("1234");
        Usuario usuario2=new Usuario("Romina","Romi91","Romi91@gmail.com",
                hash2, UsuarioRol.ROLE_USER);
        usuarioRepository.save(usuario2);

    }

}
