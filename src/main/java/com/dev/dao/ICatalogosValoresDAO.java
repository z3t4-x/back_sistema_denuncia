package com.dev.dao;

import com.dev.domain.Catalogos;
import com.dev.domain.CatalogosValores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ICatalogosValoresDAO extends JpaRepository<CatalogosValores, Integer> {


	List<CatalogosValores> findByCatalogoIdCatalogo(Integer idCatalogo);

	List<CatalogosValores> findByCatalogoDsNombre(String nombreCatalogo);

	CatalogosValores findByCdCodigoAndCatalogo(String cdCodigo, Catalogos catalogos);

}
