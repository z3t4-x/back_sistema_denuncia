package com.dev.dao;

import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaHistorico;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DenunciaHistoricoDAO extends JpaRepository<DenunciaHistorico, Integer> {


    List<DenunciaHistorico> findByDenunciaIdDenuncia(Integer idDenuncia, Sort sort);

}
