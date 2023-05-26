package com.dev.dao;

import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;
import com.dev.domain.DenunciaPersonaPK;
import com.dev.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IDenunciaPersonaDAO extends JpaRepository<DenunciaPersona, DenunciaPersonaPK> {


   // List<DenunciaPersona> findByDenunciaAndTipoPersonaCdCodigoIn(Denuncia denuncia, List<String> cdCodigos);

    List<DenunciaPersona> findByDenunciaAndTipoPersonaCdCodigo(Denuncia denuncia, String cdCodigo);

    Boolean existsByDenunciaAndPersona(Denuncia denuncia, Persona persona);

}
