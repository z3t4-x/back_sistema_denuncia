package com.dev.dao;

import com.dev.domain.Persona;
import com.dev.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {


    Optional<Usuario> findOneByEmail(String email);
}
