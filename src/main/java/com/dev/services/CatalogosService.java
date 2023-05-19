package com.dev.services;

import java.util.List;

import com.dev.domain.Catalogos;
import com.dev.dto.CatalogoDTO;
import com.dev.dto.CatalogosDTO;


public interface CatalogosService {

	/**
	 * registrar
	 * @param catalogoDTO
	 * @return
	 * @throws Exception
	 */
	CatalogosDTO registrar(CatalogosDTO catalogoDTO) throws Exception;

	/**
	 * moddificar
	 * @param catalogoDTO
	 * @return
	 * @throws Exception
	 */
	CatalogosDTO modificar(CatalogosDTO catalogoDTO) throws Exception;

	/**
	 * listar
	 * @return
	 * @throws Exception
	 */
	List<CatalogosDTO> listarCatalogos() throws Exception;

	/**
	 * listar por id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CatalogosDTO lstPorId(Integer id) throws Exception;

	/**
	 * eliminar
	 * @param id
	 * @throws Exception
	 */
	void eliminar(Integer id) throws Exception;
}
