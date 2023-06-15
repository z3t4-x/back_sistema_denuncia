package com.dev.dao;

import com.dev.domain.CatalogosValores;
import com.dev.domain.Persona;
import com.dev.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCdUsuario(String cdUsuario);

    Optional<Usuario> findOneByEmail(String email);

    boolean existsByCdUsuario(String cdUsuario);

    boolean existsByEmail(String email);

    List<Usuario> findByFiscaliaIdValor(Integer fiscalia);

    List<Usuario> findByRolesRolNombreAndFiscaliaIdValor(String rolNombre, Integer idFiscalia);

    List<Usuario> findByFiscaliaIdValorAndIdUsuarioIn(Integer fiscaliaId, List<Integer> usuarioIds);
}
