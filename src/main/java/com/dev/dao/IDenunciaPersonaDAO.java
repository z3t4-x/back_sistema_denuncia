package com.dev.dao;

import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;
import com.dev.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IDenunciaPersonaDAO extends JpaRepository<DenunciaPersona, Integer> {


   // List<DenunciaPersona> findByDenunciaAndTipoPersonaCdCodigoIn(Denuncia denuncia, List<String> cdCodigos);

    List<DenunciaPersona> findByIdDenunciaAndTipoPersonaCdCodigo(Integer idDenuncia, String cdCodigo);

    Boolean existsByIdDenunciaAndPersona(Integer idDenuncia, Persona persona);

}
