package com.dev.services;

import java.util.List;

import com.dev.dto.PersonaDTO;


public interface PersonaService {

	/**
	 * registrar
	 * @param personaDTO
	 * @return
	 * @throws Exception
	 */
	PersonaDTO registrar(PersonaDTO personaDTO) throws Exception;

	/**
	 * modificar
	 * @param personaDTO
	 * @return
	 * @throws Exception
	 */
	PersonaDTO modificar(PersonaDTO personaDTO) throws Exception;

	/**
	 * listar
	 * @return
	 * @throws Exception
	 */
	List<PersonaDTO> listarPersonas() throws Exception;

	/**
	 * buscar por id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PersonaDTO buscarPorId(Integer id) throws Exception;

	/**
	 * eliminar
	 * @param id
	 * @throws Exception
	 */
	void eliminar(Integer id) throws Exception;

    PersonaDTO buscarPorDNI(String dni);
}
