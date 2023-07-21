package com.dev.dao;

import com.dev.domain.CatalogosValores;
import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;
import com.dev.domain.Usuario;
import com.dev.dto.DenunciaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.time.LocalDate;
import java.util.List;


public interface IDenunciaDAO extends JpaRepository<Denuncia, Integer>, JpaSpecificationExecutor<Denuncia> {

    Denuncia findByNmDenunciaLikeIgnoreCase(String numDenuncia);

    List<Denuncia> findByEstadoDenunciaIdValorAndFcBajaFilaIsNull(Integer idValor);

    List<Denuncia> findByEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(String cdCodigo);


    List<Denuncia> findByFiscaliaIdValorAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
            Integer fiscalia, String estadoDenunciaCdCodigo);

    List<Denuncia> findByFiscaliaIdValorAndInvestigadorIdUsuarioAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
           Integer fiscalia, Integer investigador, String estadoDenunciaCdCodigo);

    long countByFiscaliaIdValorAndEstadoDenunciaIdValor(Integer idFiscalia, Integer idEstado);


   // long countByFiscaliaIdValorAndEstadoDenunciaIdValor(String idFiscalia, String idEstado);

    long countByEstadoDenunciaIdValor(Integer estadoDenuncia);


    List<Denuncia> findByEstadoDenunciaIdValorAndFcAltaDenunciaBetween(Integer idEstadoInvestigacion, LocalDate fechaInicio, LocalDate fechaFin);


















}
