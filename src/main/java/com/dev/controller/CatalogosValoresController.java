package com.dev.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.dev.domain.Catalogos;
import com.dev.domain.CatalogosValores;
import com.dev.dto.CatalogosValoresDTO;
import com.dev.exception.ModeloNotFoundException;
import com.dev.services.CatalogosValoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/catalogosValores")
public class CatalogosValoresController {

	@Autowired
	private CatalogosValoresService service;

	@GetMapping
	public ResponseEntity<List<CatalogosValoresDTO>> listarCatalogosValores() throws Exception {
		List<CatalogosValoresDTO> lista = service.listar();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CatalogosValoresDTO> obtenerCatalogoValoresPorId(@PathVariable Integer id) throws Exception {
		CatalogosValoresDTO catalogoValores = service.lstPorId(id);
		if (catalogoValores != null) {
			return ResponseEntity.ok(catalogoValores);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<CatalogosValoresDTO> registrarCatalogoValores(@Valid @RequestBody CatalogosValoresDTO catalogoValoresDTO) throws Exception {
		CatalogosValoresDTO catalogoValoresRegistrado = service.registrar(catalogoValoresDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(catalogoValoresRegistrado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CatalogosValoresDTO> modificarCatalogoValores(@PathVariable Integer id, @Valid @RequestBody CatalogosValoresDTO catalogoValoresDTO) throws Exception {
		catalogoValoresDTO.setIdValor(id);
		CatalogosValoresDTO catalogoValoresModificado = service.modificar(catalogoValoresDTO);
		if (catalogoValoresModificado != null) {
			return ResponseEntity.ok(catalogoValoresModificado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCatalogoValores(@PathVariable Integer id) throws Exception {
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/catalogo/{id}")
	public ResponseEntity<List<CatalogosValoresDTO>> buscarPorIdCatalogo(@PathVariable Integer id) {
		List<CatalogosValoresDTO> lista = service.buscarPorIdCatalogo(id);
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/nombreCatalogo/{nombre}")
	public ResponseEntity<List<CatalogosValoresDTO>> buscarPorNombreCatalogo(@PathVariable String nombre) {
		List<CatalogosValoresDTO> lista = service.buscarPorNombreCatalogo(nombre);
		return ResponseEntity.ok(lista);
	}


}
