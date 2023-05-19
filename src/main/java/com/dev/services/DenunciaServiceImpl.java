package com.dev.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.dev.dao.*;
import com.dev.domain.*;
import com.dev.dto.CatalogosValoresDTO;
import com.dev.dto.DenunciaDTO;
import com.dev.dto.DenunciaPersonaDTO;
import com.dev.dto.converters.*;
import com.dev.utils.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Service
public class DenunciaServiceImpl implements DenunciaService {


	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private IDenunciaDAO denunciaDAO;

	@Autowired
	private ICatalogosValoresDAO catValoresDAO;

	@Autowired
	private ICatalogoDAO catalogosDAO;

	@Autowired
	private IPersonaDAO personaDAO;

	@Autowired
	private IDenunciaPersonaDAO denunciaPersonaDAO;

	@Autowired
	private DenunciaHistoricoDAO denunciaHistoricoDAO;

	/**
	 *
	 */
	@Transactional
	@Override
	public DenunciaDTO registrarTransaccional(DenunciaDTO denunciaDTO) throws Exception {

		// Guarda la denuncia
		denunciaDTO = this.guardarDenuncia(denunciaDTO);

		Denuncia denuncia = DenunciaToEntity.INSTANCE.apply(denunciaDTO);

		denuncia = denunciaDAO.save(denuncia);

		this.crearHistorico(denuncia, denuncia.getCdUsuAlta());

		// Agregar denunciantes
		if (denunciaDTO.getLstDenunciados() != null && !denunciaDTO.getLstDenunciantes().isEmpty()) {

			for (DenunciaPersonaDTO denuncianteDTO : denunciaDTO.getLstDenunciantes()) {
				try {
					//Persona persona = PersonaToEntity.INSTANCE.apply(denuncianteDTO.getPersonaDTO());
					Persona persona  = personaDAO.findById(denuncianteDTO.getPersonaDTO().getIdPersona()).orElse(null);

					//DenunciaPersonaPK denunciaPersonaPK =  new DenunciaPersonaPK();
					DenunciaPersona denunciaPersona = new DenunciaPersona();

					//denunciaPersonaPK.setIdDenunciaPK(denuncia.getIdDenuncia());
					//denunciaPersonaPK.setIdPersonaPK(persona.getIdPersona());
					//denunciaPersona.setDenunciaPersonaPK(denunciaPersonaPK);
					denunciaPersona.setDenuncia(denuncia);
					denunciaPersona.setPersona(persona);
					denunciaPersona.setTipoPersona(new CatalogosValores(Constantes.tipoPersonaId.DENUNCIANTE));
					denunciaPersona.setFcAltaDenuncia(LocalDateTime.now());

					//denunciaPersonaDAO.save(denunciaPersona);
					denuncia.getLstDenunciantes().add(denunciaPersona);

				} catch (Exception e) {
					throw e;
				}
			}
		}

		// Agregar denunciados
		if (denunciaDTO.getLstDenunciados() != null && !denunciaDTO.getLstDenunciados().isEmpty()) {

			for (DenunciaPersonaDTO denunciadoDTO : denunciaDTO.getLstDenunciados()) {
				try {

					//Persona persona = PersonaToEntity.INSTANCE.apply(denunciadoDTO.getPersonaDTO());
					Persona persona  = personaDAO.findById(denunciadoDTO.getPersonaDTO().getIdPersona()).orElse(null);

					//DenunciaPersonaPK denunciaPersonaPK =  new DenunciaPersonaPK();
					DenunciaPersona denunciaPersona = new DenunciaPersona();
					//denunciaPersonaPK.setIdDenunciaPK(denuncia.getIdDenuncia());
					//denunciaPersonaPK.setIdPersonaPK(denunciadoDTO.getPersonaDTO().getIdPersona());
					//denunciaPersona.setDenunciaPersonaPK(denunciaPersonaPK);
					denunciaPersona.setDenuncia(denuncia);
					denunciaPersona.setPersona(persona);
					denunciaPersona.setTipoPersona(new CatalogosValores(Constantes.tipoPersonaId.DENUNCIADO));
					denunciaPersona.setFcAltaDenuncia(LocalDateTime.now());
					//denunciaPersonaDAO.save(denunciaPersona);
					denuncia.getLstDenunciados().add(denunciaPersona);
				} catch (Exception e) {

					throw e;
				}
			}
		}
		return DenunciaToDTO.INSTANCE.apply(denuncia);
	}

	/**
	 *
	 */

//	@Transactional
	@Override
	public DenunciaDTO modificar(DenunciaDTO denunciaDTO) throws Exception {

		DenunciaDTO dto = cambiarEstadoDenuncia(denunciaDTO, new CatalogosValoresDTO(denunciaDTO.getEstadoDenuncia().getIdValor()));

		// evalua si la denuncia está en una etapa y genera el código del numExpediente
		if(denunciaDTO.getEstadoDenuncia().getIdValor().equals(Constantes.estadoInvestigacion.PRELIMINAR)){
			denunciaDTO.setNmExpedienteInvPreliminar(this.generarCodigoDenuncia(denunciaDTO));
		}

		if(denunciaDTO.getEstadoDenuncia().getIdValor().equals(Constantes.estadoInvestigacion.PREPARATORIA)){
			denunciaDTO.setNmExpedientePreparatoria(this.generarCodigoDenuncia(denunciaDTO));
		}
		Denuncia  denuncia=  denunciaDAO.save(DenunciaToEntity.INSTANCE.apply(denunciaDTO));
		this.crearHistorico(denuncia, denuncia.getCdUsuAlta());
		return DenunciaToDTO.INSTANCE.apply(denuncia);
	}

	/**
	 *
	 */
	@Transactional
	@Override
	public List<DenunciaDTO> lstDenuncias() throws Exception {

		List<Denuncia> lstDenuncias = denunciaDAO.findByEstadoDenunciaCdCodigoAndFcBajaFilaIsNull(Constantes.codigoInvestigacion.DENUNCIA);

		//List<Denuncia> lstDenuncias = denunciaDAO.findAll();

		return lstDenuncias.stream()
				.map(DenunciaToDTO.INSTANCE::apply)
				.collect(Collectors.toList());
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
	public DenunciaDTO lstPorId(Integer id) throws Exception {
		return denunciaDAO.findById(id)
				.map(DenunciaToDTO.INSTANCE::apply)
				.orElse(null);
	}


	/**
	 *
	 */
	@Override
	public void eliminar(Integer id) throws Exception {

	}


	/**
	 * método para generar el codigo de la denuncia
	 * @return
	 */
	private String generarCodigoDenuncia(DenunciaDTO denunciaDTO){

		LocalDate fecha = LocalDate.now();
		Integer anio = fecha.getYear();
		String codigoDenuncia = "";
/*
		List<CatalogosValoresDTO> catalogosValoresEstado = catValoresDAO.findByCatalogoDsNombre(Constantes.nombreCatalogos.CATALOGO_ESTADO_DENUNCIA)
				.stream()
				.map(CatalogosValoresToDTO.INSTANCE::apply)
				.collect(Collectors.toList());
		CatalogosValores codigoDen = catValoresDAO.findById(denunciaDTO.getEstadoDenuncia().getIdValor()).
				orElseThrow(() -> new IllegalArgumentException("No se encontró el estado de denuncia con ID: " + denunciaDTO.getEstadoDenuncia().getIdValor()));
*/

	//	if(Objects.nonNull(catalogosValoresEstado)){

		//	for (CatalogosValoresDTO catValorEstado: catalogosValoresEstado) {

				if(Constantes.estadoInvestigacion.DENUNCIA.equals(denunciaDTO.getEstadoDenuncia().getIdValor())){
					codigoDenuncia = "DEN";
				}
				if(Constantes.estadoInvestigacion.PRELIMINAR.equals(denunciaDTO.getEstadoDenuncia().getIdValor())){
					codigoDenuncia = "IP";
				}
				if(Constantes.estadoInvestigacion.PREPARATORIA.equals(denunciaDTO.getEstadoDenuncia().getIdValor())){
					codigoDenuncia = "PRE";
				}
		//	}
	//	}

		String numDenuncia = String.format("%03d", this.denunciaDAO.count() + 1);

		CatalogosValores codigoFiscalia = catValoresDAO.findById(denunciaDTO.getFiscalia().getIdValor())
				.orElseThrow(() -> new IllegalArgumentException("No se encontró la fiscalía con ID: " + denunciaDTO.getFiscalia().getIdValor()));

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(codigoDenuncia);
		stringBuilder.append(numDenuncia);
		stringBuilder.append("-");
		stringBuilder.append(anio);
		stringBuilder.append("-");
		if(denunciaDTO.getEstadoDenuncia().getIdValor().equals(Constantes.estadoInvestigacion.PREPARATORIA)){
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
	private DenunciaDTO guardarDenuncia(DenunciaDTO denunciaDTO) {
		LocalDateTime fechaAltaDenuncia = LocalDateTime.now();
		LocalDateTime fechaPlazoRevision = fechaAltaDenuncia.plusDays(15);

		denunciaDTO.setFcAltaDenuncia(fechaAltaDenuncia);
		denunciaDTO.setFcPlazo(fechaPlazoRevision);

		CatalogosValoresDTO estadoDenunciaDTO = new CatalogosValoresDTO();
		estadoDenunciaDTO.setIdValor(5);
		denunciaDTO.setEstadoDenuncia(estadoDenunciaDTO);
		//TODO
		// que pediente revisar de que forma obternel la fiscalía al momento de iniciar sesión
		//denunciaDTO.setFiscalia(denunciaDTO.getFiscalia());
		denunciaDTO.setFiscalia(new CatalogosValoresDTO(39));
		denunciaDTO.setMesaParte(new CatalogosValoresDTO(41));
		String numDenuncia = this.generarCodigoDenuncia(denunciaDTO);
		denunciaDTO.setNmDenuncia(numDenuncia);

/*		Denuncia denunciaEntity = DenunciaToEntity.INSTANCE.apply(denunciaDTO);
		Denuncia denunciaGuardada = denunciaDAO.save(denunciaEntity);*/
		return denunciaDTO; //DenunciaToDTO.INSTANCE.apply(denunciaGuardada);

	}

	/**
	 * para cambiar de estado
	 * @param denunciaDTO
	 * @param nuevoEstado
	 * @throws Exception
	 */
	private DenunciaDTO cambiarEstadoDenuncia(DenunciaDTO denunciaDTO, CatalogosValoresDTO nuevoEstado) throws Exception {
		CatalogosValoresDTO estadoActual = denunciaDTO.getEstadoDenuncia();
		if (estadoActual.getIdValor().equals(Constantes.estadoInvestigacion.DENUNCIA) &&
				(nuevoEstado.getIdValor().equals(Constantes.estadoInvestigacion.PRELIMINAR) ||
						nuevoEstado.getIdValor().equals(Constantes.estadoInvestigacion.DESESTIMAR))) {

			denunciaDTO.setEstadoDenuncia(nuevoEstado);
			denunciaDTO.setFcModifFila(LocalDateTime.now());
		} else if (estadoActual.getIdValor().equals(Constantes.estadoInvestigacion.PRELIMINAR) &&
				nuevoEstado.getIdValor().equals(Constantes.estadoInvestigacion.PREPARATORIA)) {

			denunciaDTO.setEstadoDenuncia(nuevoEstado);
			denunciaDTO.setFcModifFila(LocalDateTime.now());
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
		denunciaHistorico.setAuxiliar(denuncia.getAuxiliar());
		denunciaHistorico.setNumDenuncia(denuncia.getNmDenuncia());
		denunciaHistorico.setFcPlazo(denuncia.getFcPlazo().toLocalDate());
		denunciaHistorico.setEstadoExpedienteAnterior(denuncia.getEstadoDenuncia());
		denunciaHistorico.setEstadoExpedienteNuevo(new CatalogosValores(denuncia.getEstadoDenuncia().getIdValor()));
		denunciaHistorico.setTipoDocumento(denuncia.getTipoDocumento());
		denunciaHistorico.setFcIngresoDocumento(denuncia.getFcIngresoDocumento());
		denunciaHistorico.setDescripcion(denuncia.getDsDescripcion());
		denunciaHistorico.setCdExpedientePreliminar(denuncia.getNmExpedienteInvPreliminar());
		denunciaHistorico.setCdExpedientePreparatoria(denuncia.getNmExpedientePreparatoria());

		denunciaHistorico.setFcBajaFila(denuncia.getFcAltaFila());
		denunciaHistorico.setFcModifFila(denuncia.getFcModifFila());

		if (Strings.isNotBlank(cdUsuAlta)) {
			denunciaHistorico.setCdUsuAlta(cdUsuAlta);
		}

		if (Strings.isNotBlank(cdUsuAlta)) {
			denunciaHistorico.setCdUsuModif(cdUsuAlta);
		}

		denunciaHistorico = this.denunciaHistoricoDAO.save(denunciaHistorico);

		return denunciaHistorico;

	}






/*		else if (estadoActual.getIdValor().equals(Constantes.estadoInvestigacion.PREPARATORIA) &&
				nuevoEstado.getIdValor().equals(Constantes.estadoInvestigacion.ASIGNADA)) {
			denunciaDTO.setEstadoDenuncia(nuevoEstado);
			denunciaDTO.setFcModifFila(LocalDateTime.now());
		} else if (estadoActual.getIdValor().equals(Constantes.estadoInvestigacion.ASIGNADA) &&
				nuevoEstado.getIdValor().equals(Constantes.estadoInvestigacion.INVESTIGACION)) {
			denunciaDTO.setEstadoDenuncia(nuevoEstado);
			denunciaDTO.setFcModifFila(LocalDateTime.now());
		} else {
			throw new Exception("El cambio de estado no es válido.");
		}*/



}




