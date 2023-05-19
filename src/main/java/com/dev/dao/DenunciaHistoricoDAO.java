package com.dev.dao;

import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DenunciaHistoricoDAO extends JpaRepository<DenunciaHistorico, Integer> {

}
