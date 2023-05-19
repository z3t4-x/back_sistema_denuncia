package com.dev.services;

import java.util.List;

import com.dev.domain.Catalogos;
import com.dev.domain.CatalogosValores;
import com.dev.dto.CatalogosValoresDTO;


public interface CatalogosValoresService {

	/**
	 * registrar
	 * @param catValorDTO
	 * @return
	 * @throws Exception
	 */
	CatalogosValoresDTO registrar(CatalogosValoresDTO catValorDTO) throws Exception;

	/**
	 * modificar
	 * @param catValorDTO
	 * @return
	 * @throws Exception
	 */
	CatalogosValoresDTO modificar(CatalogosValoresDTO catValorDTO) throws Exception;

	/**
	 * lista todos los catalogos valores
	 * @return
	 * @throws Exception
	 */
	List<CatalogosValoresDTO> listar() throws Exception;

	/**
	 * lista por id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CatalogosValoresDTO lstPorId(Integer id) throws Exception;

	/**
	 * eliminará de forma lógica
	 * @param id
	 * @throws Exception
	 */
	void eliminar(Integer id) throws Exception;


	/**
	 * busca por codigo de catalogo
	 * @param idCatalogo
	 * @return
	 */
	List<CatalogosValoresDTO> buscarPorIdCatalogo(Integer idCatalogo);

	/**
	 * metodo que busca por codigo de catalogo
	 * @param nombreCatalogo
	 * @return
	 */
	List<CatalogosValoresDTO> buscarPorNombreCatalogo(String nombreCatalogo);
}
