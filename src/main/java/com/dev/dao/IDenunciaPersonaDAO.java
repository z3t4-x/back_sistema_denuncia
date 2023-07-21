package com.dev.dao;

import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;
import com.dev.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface IDenunciaPersonaDAO extends JpaRepository<DenunciaPersona, Integer> {


   // List<DenunciaPersona> findByDenunciaAndTipoPersonaCdCodigoIn(Denuncia denuncia, List<String> cdCodigos);

    List<DenunciaPersona> findByIdDenunciaAndTipoPersonaCdCodigo(Integer idDenuncia, String cdCodigo);

    Boolean existsByIdDenunciaAndPersona(Integer idDenuncia, Persona persona);

    @Query(value = "SELECT dp.* FROM DENUNCIA_PERSONA dp "
            + "INNER JOIN DENUNCIA d ON dp.ID_DENUNCIA = d.ID_DENUNCIA "
            + "INNER JOIN PERSONA p ON dp.ID_PERSONA = p.ID_PERSONA "
            + "WHERE dp.ID_DENUNCIA = :idDenuncia "
            + "AND dp.ID_TIPO_PERSONA = :idTipoPersona", nativeQuery = true)
    List<DenunciaPersona> findByDenunciaAndTipoPersonaIdValor(@Param("idDenuncia") Integer idDenuncia, @Param("idTipoPersona") Integer idTipoPersona);





}
