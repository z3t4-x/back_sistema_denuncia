package com.dev.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.dev.domain.DenunciaPersona;
import com.dev.domain.Persona;
import com.dev.dto.PersonaDTO;
import com.dev.dto.converters.PersonaToDTO;
import com.dev.exception.ModeloNotFoundException;
import com.dev.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@GetMapping
	public ResponseEntity<List<PersonaDTO>> listarPersonas() throws Exception {
		List<PersonaDTO> lst = this.personaService.listarPersonas();
		return new ResponseEntity<List<PersonaDTO>>(lst, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonaDTO> obtenerPersonaPorId(@PathVariable("id") Integer id) throws Exception {
		PersonaDTO obj = this.personaService.buscarPorId(id);

		if (Objects.isNull(obj)) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		return new ResponseEntity<PersonaDTO>(obj, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PersonaDTO> crearPersona(@Valid @RequestBody PersonaDTO personaDTO) throws Exception {
		PersonaDTO obj = this.personaService.registrar(personaDTO);
		return new ResponseEntity<PersonaDTO>(obj, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PersonaDTO> actualizarPersona(@Valid @RequestBody PersonaDTO personaDTO) throws Exception {
		PersonaDTO obj = new PersonaDTO();

		if (Objects.nonNull(personaDTO)) {
			//personaDTO.setIdPersona(id);
			obj = this.personaService.modificar(personaDTO);
		}
		return new ResponseEntity<PersonaDTO>(obj, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPersona(@PathVariable("id") Integer id) throws Exception {
		PersonaDTO obj = this.personaService.buscarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		this.personaService.eliminar(obj.getIdPersona());
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


	@GetMapping("/buscarPorDNI/{dni}")
	public ResponseEntity<PersonaDTO> buscarPorDNI(@PathVariable String dni) {
		PersonaDTO personaDTO = personaService.buscarPorDNI(dni);

		if (personaDTO != null) {
			return ResponseEntity.ok(personaDTO);
		} else {
			return ResponseEntity.notFound().build();

		}
	}




}