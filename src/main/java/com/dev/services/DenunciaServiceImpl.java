package com.dev.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.dev.dao.*;
import com.dev.domain.*;
import com.dev.dto.CatalogosValoresDTO;
import com.dev.dto.DenunciaDTO;
import com.dev.dto.DenunciaPersonaDTO;
import com.dev.dto.converters.*;
import com.dev.utils.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;


@Slf4j
@Service
public class DenunciaServiceImpl implements DenunciaService {


	@Autowired
	private IDenunciaDAO denunciaDAO;

	@Autowired
	private ICatalogosValoresDAO catValoresDAO;

	@Autowired
	private ICatalogoDAO catalogosDAO;

	@Autowired
	private ICatalogosValoresDAO catalogosValoresDAO;

	@Autowired
	private IPersonaDAO personaDAO;

	@Autowired
	private IDenunciaPersonaDAO denunciaPersonaDAO;

	@Autowired
	private DenunciaHistoricoDAO denunciaHistoricoDAO;

	@Autowired
	private  UsuarioDAO usuarioDAO;


	@Autowired
	private UsuarioService usuarioService;


	/**
	 *
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	@Override
	public DenunciaDTO registrarTransaccional(DenunciaDTO denunciaDTO) throws Exception {

		// Guarda la denuncia
		this.prepararDatosDenuncia(denunciaDTO);

		List<DenunciaPersonaDTO> denunciados = null;

		if( !denunciaDTO.getLstDenunciados().isEmpty() ){

			Catalogos catalogo = catalogosDAO.findByDsNombre(Constantes.nombreCatalogos.CATALOGO_TIPO_DENUNCIANTE);
			CatalogosValores catalogoValores =  catalogosValoresDAO.findByCdCodigoAndCatalogo(Constantes.tipoPersona.PERSONA_DENUNCIADA, catalogo);

			for( DenunciaPersonaDTO denunciaPersonaDTO : denunciaDTO.getLstDenunciados() ){
				denunciaPersonaDTO.setTipoPersona(CatalogosValoresToDTO.INSTANCE.apply(catalogoValores));
			}

			denunciados = denunciaDTO.getLstDenunciados().stream().filter(x->x.getItBaja()==null || !x.getItBaja().equals("S") ).collect(Collectors.toList());
		}

		List<DenunciaPersonaDTO> denunciantes = null;

		if( !denunciaDTO.getLstDenunciantes().isEmpty() ){

			Catalogos catalogo = catalogosDAO.findByDsNombre(Constantes.nombreCatalogos.CATALOGO_TIPO_DENUNCIANTE);
			CatalogosValores catalogoValores =  catalogosValoresDAO.findByCdCodigoAndCatalogo(Constantes.tipoPersona.PERSONA_DENUNCIANTE, catalogo);

			for( DenunciaPersonaDTO denunciaPersonaDTO : denunciaDTO.getLstDenunciantes() ){
				denunciaPersonaDTO.setTipoPersona(CatalogosValoresToDTO.INSTANCE.apply(catalogoValores));
			}

			denunciantes = denunciaDTO.getLstDenunciantes().stream().filter(x->x.getItBaja()==null || !x.getItBaja().equals("S") ).collect(Collectors.toList());
		}

		// Quitamos las listas de denuncia
		denunciaDTO.setLstDenunciados(null);
		denunciaDTO.setLstDenunciantes(null);

		//TODO pendiente por el front (USUARIO - investigador)
//		UsuarioDTO usuarioDTO =  new UsuarioDTO();
//		usuarioDTO.setIdUsuario(1);
//		denunciaDTO.setInvestigador(usuarioDTO);

		Denuncia denuncia = DenunciaToEntity.INSTANCE.apply(denunciaDTO);

		denuncia = denunciaDAO.save(denuncia);

		this.crearHistorico(denuncia, denuncia.getCdUsuAlta());

		// Insertamos o actualizamos las lista de denunciantes y denunciados
		for( DenunciaPersonaDTO dto : denunciados ){
			if( dto.getIdDenunciaPersona()==null ) {
				DenunciaPersona dp = DenunciaPersonaToEntity.INSTANCE.apply(dto);
				dp.setIdDenuncia(denuncia.getIdDenuncia());
				denunciaPersonaDAO.save(dp);
			}
		}

		for( DenunciaPersonaDTO dto : denunciantes ){
			if( dto.getIdDenunciaPersona()==null ) {
				DenunciaPersona dp = DenunciaPersonaToEntity.INSTANCE.apply(dto);
				dp.setIdDenuncia(denuncia.getIdDenuncia());
				denunciaPersonaDAO.save(dp);
			}
		}

		return DenunciaToDTO.INSTANCE.apply(denuncia);
	}

	/**
	 *
	 */

	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	@Override
	public DenunciaDTO modificar(DenunciaDTO denunciaDTO, String usuarioOperacion) throws Exception {

		List<DenunciaPersonaDTO> denunciados = null;

		if( !denunciaDTO.getLstDenunciados().isEmpty() ){

			Catalogos catalogo = catalogosDAO.findByDsNombre(Constantes.nombreCatalogos.CATALOGO_TIPO_DENUNCIANTE);
			CatalogosValores catalogoValores =  catalogosValoresDAO.findByCdCodigoAndCatalogo(Constantes.tipoPersona.PERSONA_DENUNCIADA, catalogo);

			// Quitamos los registro de baja
			for( DenunciaPersonaDTO denunciaPersonaDTO : denunciaDTO.getLstDenunciados() ){
				if( denunciaPersonaDTO.getItBaja()!=null && denunciaPersonaDTO.getItBaja().equals("S") ){
					this.denunciaPersonaDAO.deleteById(denunciaPersonaDTO.getIdDenunciaPersona());
				}
				denunciaPersonaDTO.setTipoPersona(CatalogosValoresToDTO.INSTANCE.apply(catalogoValores));
			}
			denunciados = denunciaDTO.getLstDenunciados().stream().filter(x->x.getItBaja()==null || !x.getItBaja().equals("S") ).collect(Collectors.toList());
		}

		List<DenunciaPersonaDTO> denunciantes = null;

		if( !denunciaDTO.getLstDenunciantes().isEmpty() ){

			Catalogos catalogo = catalogosDAO.findByDsNombre(Constantes.nombreCatalogos.CATALOGO_TIPO_DENUNCIANTE);
			CatalogosValores catalogoValores =  catalogosValoresDAO.findByCdCodigoAndCatalogo(Constantes.tipoPersona.PERSONA_DENUNCIANTE, catalogo);

			// Quitamos los registro de baja
			for( DenunciaPersonaDTO denunciaPersonaDTO : denunciaDTO.getLstDenunciantes() ){
				if( denunciaPersonaDTO.getItBaja()!=null && denunciaPersonaDTO.getItBaja().equals("S") ){
					this.denunciaPersonaDAO.deleteById(denunciaPersonaDTO.getIdDenunciaPersona());
				}
				denunciaPersonaDTO.setTipoPersona(CatalogosValoresToDTO.INSTANCE.apply(catalogoValores));
			}
			denunciantes = denunciaDTO.getLstDenunciantes().stream().filter(x->x.getItBaja()==null || !x.getItBaja().equals("S") ).collect(Collectors.toList());
		}

		// Quitamos las listas de denuncia
		denunciaDTO.setLstDenunciados(null);
		denunciaDTO.setLstDenunciantes(null);

		if(denunciaDTO.getEstadoDenuncia().getIdValor().equals(Constantes.estadoInvestigacion.ID_PRELIMINAR)){
			denunciaDTO.setNmExpedienteInvPreliminar(this.generarCodigoDenuncia(denunciaDTO));
		}

		if(denunciaDTO.getEstadoDenuncia().getIdValor().equals(Constantes.estadoInvestigacion.ID_PREPARATORIA)){
			denunciaDTO.setNmExpedientePreparatoria(this.generarCodigoDenuncia(denunciaDTO));
		}

		denunciaDTO.setFcModifFila(LocalDateTime.now());
		denunciaDTO.setCdUsuModif(usuarioOperacion);

		Denuncia denuncia = DenunciaToEntity.INSTANCE.apply(denunciaDTO);

		denuncia = denunciaDAO.save(denuncia);

		// Crear el historial
		this.crearHistorico(denuncia, usuarioOperacion);

		// Insertamos o actualizamos las lista de denunciantes y denunciados
		for( DenunciaPersonaDTO dto : denunciados ){
			if( dto.getIdDenunciaPersona()==null ) {
				DenunciaPersona dp = DenunciaPersonaToEntity.INSTANCE.apply(dto);
				dp.setIdDenuncia(denuncia.getIdDenuncia());
				denunciaPersonaDAO.save(dp);
			}
		}

		for( DenunciaPersonaDTO dto : denunciantes ){
			if( dto.getIdDenunciaPersona()==null ) {
				DenunciaPersona dp = DenunciaPersonaToEntity.INSTANCE.apply(dto);
				dp.setIdDenuncia(denuncia.getIdDenuncia());
				denunciaPersonaDAO.save(dp);
			}
		}

		//denunciaDTO = DenunciaToDTO.INSTANCE.apply(denuncia);

		return denunciaDTO;
	}

	/**
	 *
	 */
	@Transactional(readOnly = true)
	@Override
	public List<DenunciaDTO> lstDenuncias() throws Exception {

		UsuarioPrincipal usuarioPrincipal = getUsuarioPrincipalAutenticado();
		String cdUsuario = usuarioPrincipal.getUsername();
		Usuario usuario = usuarioDAO.findByCdUsuario(cdUsuario).get();

		if (usuario == null) {
			throw new ServiceException("No se encontró el usuario.");

		}
		if (tieneRol(usuario, Constantes.Roles.ID_ROL_ADMINISTRADOR) || tieneRol(usuario, Constantes.Roles.ID_ROL_MESA_DE_PARTES)) {

			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_14)) {
				List<Denuncia> lstDenuncias = denunciaDAO.findByFiscaliaIdValorAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
						usuario.getFiscalia().getIdValor(), Constantes.codigoInvestigacion.DENUNCIA);

				return lstDenuncias.stream()
						.map(DenunciaToDTO.INSTANCE::apply)
						.collect(Collectors.toList());
			}

			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_13)) {
				List<Denuncia> lstDenuncias = denunciaDAO.findByFiscaliaIdValorAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
						usuario.getFiscalia().getIdValor(), Constantes.codigoInvestigacion.DENUNCIA);

				return lstDenuncias.stream()
						.map(DenunciaToDTO.INSTANCE::apply)
						.collect(Collectors.toList());
			}
		}

		if (tieneRol(usuario, Constantes.Roles.ID_ROL_INVESTIGADOR)) {
			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_13)) {
				List<Denuncia> lstDenuncias = denunciaDAO.findByFiscaliaIdValorAndInvestigadorIdUsuarioAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
						usuario.getFiscalia().getIdValor(),	usuario.getIdUsuario(), Constantes.codigoInvestigacion.DENUNCIA);

				return lstDenuncias.stream()
						.map(DenunciaToDTO.INSTANCE::apply)
						.collect(Collectors.toList());
			}

			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_14)) {
				List<Denuncia> lstDenuncias = denunciaDAO.findByFiscaliaIdValorAndInvestigadorIdUsuarioAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
						usuario.getFiscalia().getIdValor(),	usuario.getIdUsuario(), Constantes.codigoInvestigacion.DENUNCIA);

				return lstDenuncias.stream()
						.map(DenunciaToDTO.INSTANCE::apply)
						.collect(Collectors.toList());
			}
		}

		throw new ServiceException("No se pudo obtener la lista de denuncias.");

	}

	@Transactional(readOnly = true)
	@Override
	public List<DenunciaDTO> lstPreparatoria() throws Exception {
		UsuarioPrincipal usuarioPrincipal = getUsuarioPrincipalAutenticado();
		String cdUsuario = usuarioPrincipal.getUsername();
		Usuario usuario = usuarioDAO.findByCdUsuario(cdUsuario).orElse(null);

		if (usuario == null) {
			throw new ServiceException("No se encontró el usuario.");
		}

		if (tieneRol(usuario, Constantes.Roles.ID_ROL_ADMINISTRADOR) || tieneRol(usuario, Constantes.Roles.ID_ROL_MESA_DE_PARTES)) {

			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_14)) {

				List<Denuncia> lstDenuncias = denunciaDAO.findByFiscaliaIdValorAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
						usuario.getFiscalia().getIdValor(), Constantes.codigoInvestigacion.PREPARATORIA);

				return lstDenuncias.stream()
						.map(DenunciaToDTO.INSTANCE::apply)
						.collect(Collectors.toList());
			}

			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_13)) {
				List<Denuncia> lstDenuncias = denunciaDAO.findByFiscaliaIdValorAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
						usuario.getFiscalia().getIdValor(), Constantes.codigoInvestigacion.PREPARATORIA);

				return lstDenuncias.stream()
						.map(DenunciaToDTO.INSTANCE::apply)
						.collect(Collectors.toList());
			}

		}

		if (tieneRol(usuario, Constantes.Roles.ID_ROL_INVESTIGADOR.intValue())) {
			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_13)) {
				List<Denuncia> lstDenuncias = denunciaDAO.findByFiscaliaIdValorAndInvestigadorIdUsuarioAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
						usuario.getFiscalia().getIdValor(),	usuario.getIdUsuario(), Constantes.codigoInvestigacion.PREPARATORIA);

				return lstDenuncias.stream()
						.map(DenunciaToDTO.INSTANCE::apply)
						.collect(Collectors.toList());
			}

			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_14)) {
				List<Denuncia> lstDenuncias = denunciaDAO.findByFiscaliaIdValorAndInvestigadorIdUsuarioAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
						usuario.getFiscalia().getIdValor(),	usuario.getIdUsuario(), Constantes.codigoInvestigacion.PREPARATORIA);

				return lstDenuncias.stream()
						.map(DenunciaToDTO.INSTANCE::apply)
						.collect(Collectors.toList());
			}
		}

		throw new ServiceException("No se pudo obtener la lista de denuncias.");
	}


	@Transactional(readOnly = true)
	@Override
	public List<DenunciaDTO> lstPreliminar() throws Exception {
		UsuarioPrincipal usuarioPrincipal = getUsuarioPrincipalAutenticado();
		String cdUsuario = usuarioPrincipal.getUsername();
		Usuario usuario = usuarioDAO.findByCdUsuario(cdUsuario).orElse(null);

		if (usuario == null) {
			throw new ServiceException("No se encontró el usuario.");
		}

		if (tieneRol(usuario, Constantes.Roles.ID_ROL_ADMINISTRADOR) || tieneRol(usuario, Constantes.Roles.ID_ROL_MESA_DE_PARTES)) {

			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_14)) {

				List<Denuncia> lstDenuncias = denunciaDAO.findByFiscaliaIdValorAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
						usuario.getFiscalia().getIdValor(), Constantes.codigoInvestigacion.PRELIMINAR);

				return lstDenuncias.stream()
						.map(DenunciaToDTO.INSTANCE::apply)
						.collect(Collectors.toList());
			}

			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_13)) {
				List<Denuncia> lstDenuncias = denunciaDAO.findByFiscaliaIdValorAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
						usuario.getFiscalia().getIdValor(), Constantes.codigoInvestigacion.PRELIMINAR);

				return lstDenuncias.stream()
						.map(DenunciaToDTO.INSTANCE::apply)
						.collect(Collectors.toList());
			}

		}

		if (tieneRol(usuario, Constantes.Roles.ID_ROL_INVESTIGADOR.intValue())) {
			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_13)) {
				List<Denuncia> lstDenuncias = denunciaDAO.findByFiscaliaIdValorAndInvestigadorIdUsuarioAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
						usuario.getFiscalia().getIdValor(),	usuario.getIdUsuario(), Constantes.codigoInvestigacion.PRELIMINAR);

				return lstDenuncias.stream()
						.map(DenunciaToDTO.INSTANCE::apply)
						.collect(Collectors.toList());
			}

			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_14)) {
				List<Denuncia> lstDenuncias = denunciaDAO.findByFiscaliaIdValorAndInvestigadorIdUsuarioAndEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(
						usuario.getFiscalia().getIdValor(),	usuario.getIdUsuario(), Constantes.codigoInvestigacion.PRELIMINAR);

				return lstDenuncias.stream()
						.map(DenunciaToDTO.INSTANCE::apply)
						.collect(Collectors.toList());
			}
		}

		throw new ServiceException("No se pudo obtener la lista de denuncias.");
	}

	/**
	 *
	 */
	@Override
	public List<DenunciaDTO> lstInvestigacionPreliminar() throws Exception {

		List<Denuncia> lstDenuncias = denunciaDAO.findByEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(Constantes.codigoInvestigacion.PRELIMINAR);

		return lstDenuncias.stream()
				.map(DenunciaToDTO.INSTANCE::apply)
				.collect(Collectors.toList());
	}

	/**
	 *
	 */
	@Override
	public List<DenunciaDTO> lstInvestigacionPreparatoria() throws Exception {

		List<Denuncia> lstDenuncias = denunciaDAO.findByEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(Constantes.codigoInvestigacion.PREPARATORIA);

		return lstDenuncias.stream()
				.map(DenunciaToDTO.INSTANCE::apply)
				.collect(Collectors.toList());
	}

	/**
	 *
	 */
	@Override
		public DenunciaDTO lstPorId(Integer id) throws ServiceException {
			Optional<Denuncia> denunciaOptional = denunciaDAO.findById(id);

			if (!denunciaOptional.isPresent()) {
				throw new ServiceException("No se encontró la denuncia con el ID proporcionado");
			}

			Denuncia denuncia = denunciaOptional.get();
			return DenunciaToDTO.INSTANCE.apply(denuncia);
		}

	/**
	 *
	 */
	@Override
	public void eliminar(Integer id) throws Exception {

	}

	/**
	 * método para buscar campos de la denuncia:
	 * @param denuncia
	 * @return
	 */
	@Override
	public List<Denuncia> buscarDenuncias(Denuncia denuncia) {

		Specification<Denuncia> spec = (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (denuncia.getFcAltaDenuncia() != null) {
				predicates.add(criteriaBuilder.equal(root.get("fcAltaDenuncia"), denuncia.getFcAltaDenuncia()));
			}
			if (denuncia.getTipoDelito() != null) {
				predicates.add(criteriaBuilder.equal(root.get("tipoDelito"), denuncia.getTipoDelito().getIdValor()));
			}
			if (denuncia.getFcHechos() != null) {
				predicates.add(criteriaBuilder.equal(root.get("fcHechos"), denuncia.getFcHechos()));
			}
			if (denuncia.getInvestigador() != null) {
				predicates.add(criteriaBuilder.equal(root.get("investigador"), denuncia.getInvestigador().getIdUsuario()));
			}
			if (denuncia.getNmDenuncia() != null) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("nmDenuncia")), "%" + denuncia.getNmDenuncia().trim().toUpperCase() + "%"));
			}
			if (denuncia.getEstadoDenuncia() != null) {
				predicates.add(criteriaBuilder.equal(root.get("estadoDenuncia"), denuncia.getEstadoDenuncia().getIdValor()));
			}
			if (denuncia.getTipoDocumento() != null) {
				predicates.add(criteriaBuilder.equal(root.get("tipoDocumento"), denuncia.getTipoDocumento().getIdValor()));
			}
			if (denuncia.getFcIngresoDocumento() != null) {
				predicates.add(criteriaBuilder.equal(root.get("fcIngresoDocumento"), denuncia.getFcIngresoDocumento()));
			}
			if (denuncia.getNmDocumento() != null) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("nmDocumento")), "%" + denuncia.getNmDocumento().trim().toUpperCase() + "%"));
			}
			if (denuncia.getNmExpedientePreparatoria() != null) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("nmExpedientePreparatoria")), "%" + denuncia.getNmExpedientePreparatoria().trim().toUpperCase() + "%"));
			}
			if (denuncia.getNmExpedienteInvPreliminar() != null) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("nmExpedienteInvPreliminar")), "%" + denuncia.getNmExpedienteInvPreliminar().trim().toUpperCase() + "%"));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

		};

		return denunciaDAO.findAll(spec);
	}



/**
 * REPORTES
 */
	/**
	 * método para obtener el total de etapa denuncia
	 * @return
	 */
	@Override
	public long totalDenuncias() {
		return denunciaDAO.countByEstadoDenunciaIdValor(Constantes.estadoInvestigacion.ID_DENUNCIA);
	}

	/**
	 * método para obtener el total de etapa preliminar
	 * @return
	 */
	@Override
	public long totalPreliminar() {
		return denunciaDAO.countByEstadoDenunciaIdValor(Constantes.estadoInvestigacion.ID_PRELIMINAR);
	}

	/**
	 * método para obtener el total de etapa preparatoria
	 * @return
	 */
	@Override
	public long totalPreparatoria() {
		return denunciaDAO.countByEstadoDenunciaIdValor(Constantes.estadoInvestigacion.ID_PREPARATORIA);
	}


	/**
	 * método para listar el histórico de las denuncias.
	 */
	@Override
	public List<DenunciaHistorico> lstDenunciaHistorico(Integer idDenuncia) {
		UsuarioPrincipal usuarioPrincipal = getUsuarioPrincipalAutenticado();
		String cdUsuario = usuarioPrincipal.getUsername();
		Usuario usuario = usuarioDAO.findByCdUsuario(cdUsuario).orElse(null);

		if (usuario == null) {
			throw new ServiceException("No se encontró el usuario.");
		}

		if (tieneRol(usuario, Constantes.Roles.ID_ROL_ADMINISTRADOR) ||
				tieneRol(usuario, Constantes.Roles.ID_ROL_MESA_DE_PARTES) ||
				tieneRol(usuario, Constantes.Roles.ID_ROL_INVESTIGADOR)) {

			if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_14) ||
					usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_13)) {

				Sort sort = Sort.by(Sort.Direction.DESC, "idDenunciaHist");
				return denunciaHistoricoDAO.findByDenunciaIdDenuncia(idDenuncia, sort);
			}
		}

		// Devolver una lista vacía si no se cumplen las condiciones
		return new ArrayList<>();
	}






	/**
	 * método para generar el codigo de la denuncia
	 * @return
	 */
	private String generarCodigoDenuncia(DenunciaDTO denunciaDTO) {
		LocalDate fecha = LocalDate.now();
		Integer anio = fecha.getYear();
		String codigoDenuncia = "";
		if (Constantes.estadoInvestigacion.ID_DENUNCIA.equals(denunciaDTO.getEstadoDenuncia().getIdValor())) {
			codigoDenuncia = "D";
		} else if (Constantes.estadoInvestigacion.ID_PRELIMINAR.equals(denunciaDTO.getEstadoDenuncia().getIdValor())) {
			codigoDenuncia = "IP";
		} else if (Constantes.estadoInvestigacion.ID_PREPARATORIA.equals(denunciaDTO.getEstadoDenuncia().getIdValor())) {
			codigoDenuncia = "PRE";
		}
		String numDenuncia = "";
		if (denunciaDTO.getFiscalia() != null) {
			numDenuncia = String.format("%03d", denunciaDAO.countByFiscaliaIdValorAndEstadoDenunciaIdValor
					(denunciaDTO.getFiscalia().getIdValor(), denunciaDTO.getEstadoDenuncia().getIdValor()) + 1);
		}
		CatalogosValores codigoFiscalia = catValoresDAO.findById(denunciaDTO.getFiscalia().getIdValor())
				.orElseThrow(() -> new IllegalArgumentException("No se encontró la fiscalía con ID: " + denunciaDTO.getFiscalia().getIdValor()));

		StringBuilder stringBuilder = new StringBuilder();

		if (denunciaDTO.getEstadoDenuncia().getIdValor().equals(Constantes.estadoInvestigacion.ID_DENUNCIA)) {
			stringBuilder.append(codigoDenuncia);
		}
		stringBuilder.append(numDenuncia);
		stringBuilder.append("-");
		stringBuilder.append(anio);
		stringBuilder.append("-");
		if (denunciaDTO.getEstadoDenuncia().getIdValor().equals(Constantes.estadoInvestigacion.ID_PRELIMINAR)) {
			stringBuilder.append(codigoDenuncia);
			stringBuilder.append("-");
		}
		if (denunciaDTO.getEstadoDenuncia().getIdValor().equals(Constantes.estadoInvestigacion.ID_PREPARATORIA)) {
			stringBuilder.append("02");
			stringBuilder.append("-");
		}

		stringBuilder.append(codigoFiscalia.getCdCodigo());
		String codDenuncia = stringBuilder.toString();

		return codDenuncia;
	}





	/**
	 * método para registrar la denuncia
	 * @param denunciaDTO
	 */
	private DenunciaDTO prepararDatosDenuncia(DenunciaDTO denunciaDTO) {

		UsuarioPrincipal usuarioPrincipal = getUsuarioPrincipalAutenticado();
		String cdUsuario = usuarioPrincipal.getUsername();

		Optional<Usuario> usuarioOptional = usuarioDAO.findByCdUsuario(cdUsuario);
		if (usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();

			LocalDate fechaAltaDenuncia = LocalDate.now();
			LocalDate fechaPlazoRevision = fechaAltaDenuncia.plusDays(15);
			denunciaDTO.setFcAltaDenuncia(fechaAltaDenuncia);
			denunciaDTO.setFcPlazo(fechaPlazoRevision);

			denunciaDTO.setEstadoDenuncia(new CatalogosValoresDTO(Constantes.estadoInvestigacion.ID_DENUNCIA));

			if(usuario.getFiscalia()!=null) {
				denunciaDTO.setFiscalia(new CatalogosValoresDTO(usuario.getFiscalia().getIdValor()));
			}
			denunciaDTO.setCdUsuAlta(usuario.getCdUsuario());

			if(usuario.getMesaParte()!=null) {
				denunciaDTO.setMesaParte(new CatalogosValoresDTO(usuario.getMesaParte().getIdValor()));
			}

			String numDenuncia = this.generarCodigoDenuncia(denunciaDTO);
			denunciaDTO.setNmDenuncia(numDenuncia);
		}
		else {
			throw new ServiceException("No se encontró un usuario con el código " + cdUsuario);
		}
		return denunciaDTO;
	}

	/**
	 * para cambiar de estado
	 * @param denunciaDTO
	 * @param nuevoEstado
	 * @throws Exception
	 */
	private DenunciaDTO cambiarEstadoDenuncia(DenunciaDTO denunciaDTO, CatalogosValoresDTO nuevoEstado) throws Exception {
		CatalogosValoresDTO estadoActual = denunciaDTO.getEstadoDenuncia();
		if (estadoActual.getIdValor().equals(Constantes.estadoInvestigacion.ID_DENUNCIA) &&
				(nuevoEstado.getIdValor().equals(Constantes.estadoInvestigacion.ID_PRELIMINAR) ||
						nuevoEstado.getIdValor().equals(Constantes.estadoInvestigacion.ID_DESESTIMAR))) {

			denunciaDTO.setEstadoDenuncia(nuevoEstado);

		} else if (estadoActual.getIdValor().equals(Constantes.estadoInvestigacion.ID_PRELIMINAR) &&
				nuevoEstado.getIdValor().equals(Constantes.estadoInvestigacion.ID_PREPARATORIA)) {

			denunciaDTO.setEstadoDenuncia(nuevoEstado);
		}

		return denunciaDTO;
	}


	/**
	 * método para crear el historico de la denuncia
	 * @param denuncia
	 * @param cdUsuAlta
	 * @return
	 */
	private DenunciaHistorico crearHistorico(Denuncia denuncia, String cdUsuAlta) {

		DenunciaHistorico denunciaHistorico = new DenunciaHistorico();

		denunciaHistorico.setDenuncia(denuncia);
		denunciaHistorico.setFcAltaDenuncia(denuncia.getFcAltaDenuncia());
		denunciaHistorico.setFiscalia(denuncia.getFiscalia());
		denunciaHistorico.setTipoDelito(denuncia.getTipoDelito());
		denunciaHistorico.setFcHechos(denuncia.getFcHechos());
		denunciaHistorico.setInvestigador(denuncia.getInvestigador());
		denunciaHistorico.setNumDenuncia(denuncia.getNmDenuncia());
		denunciaHistorico.setFcPlazo(denuncia.getFcPlazo());
		denunciaHistorico.setEstadoDenuncia(denuncia.getEstadoDenuncia());
		denunciaHistorico.setTipoDocumento(denuncia.getTipoDocumento());
		denunciaHistorico.setFcIngresoDocumento(denuncia.getFcIngresoDocumento());
		denunciaHistorico.setNumDocumento(denuncia.getNmDocumento());
		denunciaHistorico.setDescripcion(denuncia.getDsDescripcion());
		denunciaHistorico.setCdExpedientePreliminar(denuncia.getNmExpedienteInvPreliminar());
		denunciaHistorico.setCdExpedientePreparatoria(denuncia.getNmExpedientePreparatoria());
		denunciaHistorico.setCdUsuAlta(cdUsuAlta);
		denunciaHistorico.setFcAltaFila(LocalDateTime.now());
		denunciaHistorico.setCdUsuModif(denuncia.getCdUsuModif());
		denunciaHistorico.setFcModifFila(denuncia.getFcModifFila());

		denunciaHistorico = this.denunciaHistoricoDAO.save(denunciaHistorico);

		return denunciaHistorico;

	}



	private UsuarioPrincipal getUsuarioPrincipalAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UsuarioPrincipal) {
			return (UsuarioPrincipal) authentication.getPrincipal();
		} else {
			throw new ServiceException("No se pudo obtener el usuario autenticado.");
		}
	}


	private boolean tieneRol(Usuario usuario, Integer idRol) {
		return usuario.getRoles().stream()
				.anyMatch(rol -> rol.getIdRol().equals(idRol));
	}


}




