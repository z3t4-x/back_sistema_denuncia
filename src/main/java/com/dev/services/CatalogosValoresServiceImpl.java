package com.dev.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dev.dao.ICatalogosValoresDAO;
import com.dev.dao.UsuarioDAO;
import com.dev.domain.CatalogosValores;
import com.dev.dto.CatalogosValoresDTO;
import com.dev.dto.converters.*;
import com.dev.exception.ModeloNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class CatalogosValoresServiceImpl implements CatalogosValoresService {

	@Autowired
	private ICatalogosValoresDAO catValoresDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;


	/**
	 *
	 */
	@Override
	public CatalogosValoresDTO registrar(CatalogosValoresDTO catValorDTO) throws Exception {
		CatalogosValores catValor = CatalogosValoresToEntity.INSTANCE.apply(catValorDTO);
		catValor = this.catValoresDAO.save(catValor);
		return CatalogosValoresToDTO.INSTANCE.apply(catValor);
	}

	/**
	 *
	 */

	@Override
	public CatalogosValoresDTO modificar(CatalogosValoresDTO catValorDTO) throws Exception {

			CatalogosValores catValor = CatalogosValoresToEntity.INSTANCE.apply(catValorDTO);
			catValor.setFcAltaFila(LocalDateTime.now());
		//usuarioDAO.findById(catValor.getus)
			//catValor.setCdUsuModif(u);
			catValor = this.catValoresDAO.save(catValor);
			return CatalogosValoresToDTO.INSTANCE.apply(catValor);

	}

	/**
	 *
	 */
	@Override
	public List<CatalogosValoresDTO> listar() throws Exception {
		List<CatalogosValores> catalogosValores = this.catValoresDAO.findAll();
		return catalogosValores.stream().map(CatalogosValoresToDTO.INSTANCE::apply).collect(Collectors.toList());
	}

	/**
	 *
	 */
	@Override
	public CatalogosValoresDTO lstPorId(Integer id) throws Exception {
		Optional<CatalogosValores> catValor = this.catValoresDAO.findById(id);
		return catValor.map(CatalogosValoresToDTO.INSTANCE::apply).orElse(null);
	}

	@Override
	public CatalogosValores buscarId(Integer id) throws Exception {

		return catValoresDAO.findById(id).get();
	}

	/**
	 *
	 */
	@Override
	public void eliminar(Integer id) throws Exception {
		Optional<CatalogosValores> catalogosValoresOptional = catValoresDAO.findById(id);
		CatalogosValoresDTO dto = catalogosValoresOptional.map(CatalogosValoresToDTO.INSTANCE::apply).orElse(null);

		if (Objects.isNull(dto.getIdValor())){
			throw new ModeloNotFoundException("Persona con ID " + id + " no fue encontrado.");
		}

		dto.setFcBajaFila(LocalDateTime.now());
		dto.setCdUsuBaja(dto.getCdUsuBaja());

		CatalogosValores catalogosValores = 	CatalogosValoresToEntity.INSTANCE.apply(dto);
		catValoresDAO.save(catalogosValores);


	}

	/**
	 *
	 */
	@Override
	public List<CatalogosValoresDTO> buscarPorIdCatalogo(Integer idCatalogo) {
		List<CatalogosValores> catalogosValores = this.catValoresDAO.findByCatalogoIdCatalogo(idCatalogo);
		return catalogosValores.stream().map(CatalogosValoresToDTO.INSTANCE::apply).collect(Collectors.toList());
	}

	/**
	 *
	 */
	@Override
	public List<CatalogosValoresDTO> buscarPorNombreCatalogo(String nombreCatalogo) {
		List<CatalogosValores> lstCatValores = this.catValoresDAO.findByCatalogoDsNombre(nombreCatalogo);
		return lstCatValores.stream().map(CatalogosValoresToDTO.INSTANCE::apply).collect(Collectors.toList());
	}


}
