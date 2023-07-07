package com.dev.controller;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.dev.domain.*;
import com.dev.dto.CatalogosValoresDTO;
import com.dev.dto.DenunciaDTO;

import com.dev.dto.UsuarioDTO;
import com.dev.services.CatalogosValoresService;
import com.dev.services.DenunciaService;
import com.dev.services.PersonaService;
import com.dev.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

	@Autowired
	private CatalogosValoresService catalogosValoresService;

	@Autowired
	private  UsuarioService usuarioService;

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

	@GetMapping("/preliminar")
	public ResponseEntity<List<DenunciaDTO>> listarPreliminar() {
		try {
			List<DenunciaDTO> denuncias = service.lstPreliminar();
			return ResponseEntity.ok(denuncias);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/preparatoria")
	public ResponseEntity<List<DenunciaDTO>> listarPreparatoria() {
		try {
			List<DenunciaDTO> denuncias = service.lstPreparatoria();
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


	@GetMapping("/buscar")
	public ResponseEntity<List<Denuncia>> buscarDenuncias(@RequestParam(value = "fcAltaDenuncia", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fcAltaDenuncia,
			@RequestParam(value = "fcIngresoDocumento", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fcIngresoDocumento,
			@RequestParam(value = "fcHechos", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fcHechos,
			@RequestParam(value = "tipoDelito", required = false) Integer idTipoDelito,
			@RequestParam(value = "investigador", required = false) Integer idInvestigador,
			@RequestParam(value = "nmDenuncia", required = false) String nmDenuncia,
			@RequestParam(value = "estadoDenuncia", required = false) Integer idEstadoDenuncia,
			@RequestParam(value = "tipoDocumento", required = false) Integer idTipoDocumento,
			@RequestParam(value = "nmExpedienteInvPreliminar", required = false) String nmExpedienteInvPreliminar,
			@RequestParam(value = "nmExpedientePreparatoria", required = false) String nmExpedientePreparatoria,
			@RequestParam(value = "nmDocumento", required = false) String nmDocumento
	) throws Exception {
		Denuncia denuncia = new Denuncia();
		denuncia.setFcAltaDenuncia(fcAltaDenuncia);
		denuncia.setFcHechos(fcHechos);
		denuncia.setNmDenuncia(nmDenuncia);
		denuncia.setFcIngresoDocumento(fcIngresoDocumento);
		denuncia.setNmDocumento(nmDocumento);
		denuncia.setNmExpedientePreparatoria(nmExpedientePreparatoria);
		denuncia.setNmExpedienteInvPreliminar(nmExpedienteInvPreliminar);
		if (idTipoDelito != null) {
			CatalogosValores tipoDelito = catalogosValoresService.buscarId(idTipoDelito);
			denuncia.setTipoDelito(tipoDelito);
		}
		if (idInvestigador != null) {
			Usuario investigador = usuarioService.buscarIdUsuario(idInvestigador);
			denuncia.setInvestigador(investigador);
		}
		if (idEstadoDenuncia != null) {
			CatalogosValores estadoDenuncia = catalogosValoresService.buscarId(idEstadoDenuncia);
			denuncia.setEstadoDenuncia(estadoDenuncia);
		}
		if (idTipoDocumento != null) {
			CatalogosValores tipoDocumento = catalogosValoresService.buscarId(idTipoDocumento);
			denuncia.setTipoDocumento(tipoDocumento);
		}
		List<Denuncia> denuncias = service.buscarDenuncias(denuncia);
		return ResponseEntity.ok(denuncias);
	}


	@GetMapping("/historico/{idDenuncia}")
	public ResponseEntity<List<DenunciaHistorico>> obtenerHistoricoDenuncia(@PathVariable Integer idDenuncia) {
		List<DenunciaHistorico> historico = service.lstDenunciaHistorico(idDenuncia);
		if (historico.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(historico);
		}
	}


	/**
	 * REPORTE
	 */

	@GetMapping("/reportes")
	public ResponseEntity<Map<String, Long>> generarInforme() {
		Map<String, Long> informe = new HashMap<>();
		informe.put("totalDenuncias", service.totalDenuncias());
		informe.put("totalEtapaPreliminar", service.totalPreliminar());
		informe.put("totalEtapaPreparatoria", service.totalPreparatoria());
		return ResponseEntity.ok(informe);
	}









//	@GetMapping("/buscar")
//	public ResponseEntity<List<Denuncia>> buscarDenuncias(
//			@RequestParam(value = "fcAltaDenuncia", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fcAltaDenuncia,
//			@RequestParam(value = "fcIngresoDocumento", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fcIngresoDocumento,
//			@RequestParam(value = "fcHechos", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fcHechos,
//			@RequestParam(value = "tipoDelito", required = false)  CatalogosValores tipoDelito,
//			@RequestParam(value = "investigador", required = false) Usuario investigador,
//			@RequestParam(value = "nmDenuncia", required = false) String nmDenuncia,
//			@RequestParam(value = "estadoDenuncia", required = false) CatalogosValores estadoDenuncia,
//			@RequestParam(value = "tipoDocumento", required = false) CatalogosValores tipoDocumento,
//			@RequestParam(value = "nmExpedienteInvPreliminar", required = false) String nmExpedienteInvPreliminar,
//			@RequestParam(value = "nmExpedientePreparatoria", required = false) String nmExpedientePreparatoria,
//			@RequestParam(value = "nmDocumento", required = false) String nmDocumento
//	) throws Exception {
//		Denuncia denuncia = new Denuncia();;
//		denuncia.setFcAltaDenuncia(fcAltaDenuncia);
//		denuncia.setFcHechos(fcHechos);
//		denuncia.setNmDenuncia(nmDenuncia);
//		denuncia.setFcIngresoDocumento(fcIngresoDocumento);
//		denuncia.setNmDocumento(nmDocumento);
//		denuncia.setNmExpedientePreparatoria(nmExpedientePreparatoria);
//		denuncia.setNmExpedienteInvPreliminar(nmExpedienteInvPreliminar);
//
////		denuncia.setTipoDelito(new CatalogosValores(idTipoDelito));
//
////		denuncia.setInvestigador(new Usuario(idInvestigador));
////		denuncia.setEstadoDenuncia(idEstadoDenuncia);
////		denuncia.setTipoDocumento(idTipoDocumento);
//
//		// Configurar los campos de tipo CatalogosValores por nombre
//		if (tipoDelito != null) {
//			CatalogosValores idTipoDelito = catalogosValoresService.buscarId(tipoDelito.getIdValor());
//			denuncia.setTipoDelito(idTipoDelito);
//		}
//		if (investigador != null) {
//			Usuario idInvestigador = usuarioService.buscarIdUsuario(investigador.getIdUsuario());
//			denuncia.setInvestigador(idInvestigador);
//		}
//		if (estadoDenuncia != null) {
//			CatalogosValores idEstadoDenuncia = catalogosValoresService.buscarId(estadoDenuncia.getIdValor());
//			denuncia.setEstadoDenuncia(idEstadoDenuncia);
//		}
//		if (tipoDocumento != null) {
//			CatalogosValores idTipoDocumento = catalogosValoresService.buscarId(tipoDocumento.getIdValor());
//			denuncia.setTipoDocumento(idTipoDocumento);
//		}
//
//		List<Denuncia> denuncias = service.buscarDenuncias(denuncia);
//		return ResponseEntity.ok(denuncias);
//	}






}
