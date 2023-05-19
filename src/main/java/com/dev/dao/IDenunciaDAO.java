package com.dev.dao;

import com.dev.domain.CatalogosValores;
import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IDenunciaDAO extends JpaRepository<Denuncia, Integer>{

    Denuncia findByNmDenunciaLikeIgnoreCase(String numDenuncia);

    List<Denuncia> findByEstadoDenunciaIdValorAndFcBajaFilaIsNull(String idValor);

    List<Denuncia> findByEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(String cdCodigo);




}
