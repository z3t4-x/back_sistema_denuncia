package com.dev.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dev.dao.IPersonaDAO;
import com.dev.domain.Persona;
import com.dev.domain.Usuario;
import com.dev.dto.PersonaDTO;
import com.dev.dto.converters.PersonaToDTO;
import com.dev.dto.converters.PersonaToEntity;
import com.dev.exception.ModeloNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private IPersonaDAO dao;

	/**
	 *
	 * @param personaDTO
	 * @return
	 * @throws Exception
	 */
	@Override
	public PersonaDTO registrar(PersonaDTO personaDTO) throws Exception {
		Persona persona = PersonaToEntity.INSTANCE.apply(personaDTO);
		Persona personaGuardada = dao.save(persona);
		return PersonaToDTO.INSTANCE.apply(personaGuardada);
	}


	/**
	 *
	 */
	@Override
	public PersonaDTO modificar(PersonaDTO personaDTO) throws Exception {
		Persona persona = PersonaToEntity.INSTANCE.apply(personaDTO);
		Persona personaModificada = dao.save(persona);
		return PersonaToDTO.INSTANCE.apply(personaModificada);
	}


	/**
	 *
	 */
	@Override
	public List<PersonaDTO> listarPersonas() throws Exception {
		List<Persona> personas = dao.findAll();
		return personas.stream()
				.map(PersonaToDTO.INSTANCE::apply)
				.collect(Collectors.toList());
	}


	/**
	 *
	 */
	@Override
	public PersonaDTO buscarPorId(Integer id) throws Exception {
		Persona persona = dao.findById(id).orElse(null);
		return PersonaToDTO.INSTANCE.apply(persona);
	}

	/**
	 *
	 */
	@Override
	public void eliminar(Integer id) throws Exception {
		Optional<Persona> optionalPersona = dao.findById(id);
		PersonaDTO dto = optionalPersona.map(PersonaToDTO.INSTANCE::apply).orElse(null);

		if (Objects.isNull(dto.getIdPersona())){
			throw new ModeloNotFoundException("Persona con ID " + id + " no fue encontrado.");
		}


		dto.setFcBajaFila(LocalDateTime.now());
		dto.setCdUsuBaja(dto.getCdUsuBaja());

		Persona persona = 	PersonaToEntity.INSTANCE.apply(dto);
		dao.save(persona);
	}

	@Override
	public PersonaDTO buscarPorDNI(String dni) {
		Persona persona = dao.findOneByDni(dni);
		return PersonaToDTO.INSTANCE.apply(persona);
	}

}
