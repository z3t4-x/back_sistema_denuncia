package com.dev.controller;


import java.util.List;
import java.util.Objects;

import com.dev.domain.DenunciaPersona;
import com.dev.dto.DenunciaDTO;

import com.dev.dto.UsuarioDTO;
import com.dev.services.DenunciaService;
import com.dev.services.PersonaService;
import com.dev.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

	@Autowired
	private DenunciaService service;

	/**
	 * listar todo
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<DenunciaDTO>> listarDenuncias() {
		try {
			List<DenunciaDTO> denuncias = service.lstDenuncias();
			return ResponseEntity.ok(denuncias);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * listar por id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DenunciaDTO> obtenerDenunciaPorId(@PathVariable Integer id) {
		try {
			DenunciaDTO denuncia = service.lstPorId(id);
			return denuncia != null ? ResponseEntity.ok(denuncia) : ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	/**
	 * registrar
	 * @param denunciaDTO
	 * @return
	 */
	@PostMapping
	public ResponseEntity<DenunciaDTO> registrarDenuncia(@RequestBody DenunciaDTO denunciaDTO) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body( service.registrarTransaccional(denunciaDTO));
		} catch (Exception e) {
			log.error( e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	/**
	 * modificar
	 * @param denunciaDTO
	 * @return
	 */
	@PutMapping
	public ResponseEntity<DenunciaDTO> modificarDenuncia(@RequestBody DenunciaDTO denunciaDTO) {
		try {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			if( Objects.isNull(userDetails) ){
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}

			DenunciaDTO denunciaModificada = service.modificar(denunciaDTO, userDetails.getUsername());
			return ResponseEntity.ok(denunciaModificada);

		} catch (Exception e) {
			log.info("==========> MODIFICACIÓN ==> {}" , e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	/**
	 * eliminar
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarDenuncia(@PathVariable Integer id) {
		try {
			service.eliminar(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
