package com.dev.dao;

import com.dev.domain.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRolDAO extends JpaRepository<UsuarioRol, Integer> {


    boolean existsByUsuarioIdUsuarioAndRolIdRol(Integer usuarioId, Integer rolId);

    List<Integer> findUsuarioIdsByRolIdRol(Integer rolId);


}
