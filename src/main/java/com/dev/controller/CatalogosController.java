package com.dev.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.dev.domain.Catalogos;
import com.dev.dto.CatalogosDTO;
import com.dev.exception.ModeloNotFoundException;
import com.dev.services.CatalogosService;
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
@RequestMapping("/catalogos")
public class CatalogosController {
	@Autowired
	private CatalogosService catalogosService;

	@GetMapping
	public ResponseEntity<List<CatalogosDTO>> listarCatalogos() {
		try {
			List<CatalogosDTO> catalogos = catalogosService.listarCatalogos();
			return ResponseEntity.ok(catalogos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CatalogosDTO> obtenerCatalogoPorId(@PathVariable Integer id) {
		try {
			CatalogosDTO catalogo = catalogosService.lstPorId(id);
			if (catalogo != null) {
				return ResponseEntity.ok(catalogo);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 *
	 * @param catalogoDTO
	 * @return
	 */
	@PostMapping
	public ResponseEntity<CatalogosDTO> crearCatalogo(@RequestBody CatalogosDTO catalogoDTO) {
		try {
			CatalogosDTO nuevoCatalogo = catalogosService.registrar(catalogoDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCatalogo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * modificar
	 * @param id
	 * @param catalogoDTO
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CatalogosDTO> modificarCatalogo(@PathVariable Integer id, @RequestBody CatalogosDTO catalogoDTO) {
		try {
			catalogoDTO.setIdCatalogo(id);
			CatalogosDTO catalogoModificado = catalogosService.modificar(catalogoDTO);
			if (catalogoModificado != null) {
				return ResponseEntity.ok(catalogoModificado);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * eliminar
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCatalogo(@PathVariable Integer id) {
		try {
			catalogosService.eliminar(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
