package com.dev.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dev.dao.ICatalogoDAO;
import com.dev.domain.Catalogos;
import com.dev.dto.CatalogosDTO;
import com.dev.dto.CatalogosValoresDTO;
import com.dev.dto.converters.CatalogosToDTO;
import com.dev.dto.converters.CatalogosToEntity;
import com.dev.dto.converters.DenunciaToDTO;
import com.dev.exception.ModeloNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CatalogoServiceImpl implements CatalogosService {


	@Autowired
	private ICatalogoDAO catalogoDAO;

	/**
	 *
	 */
	@Override
	public CatalogosDTO registrar(CatalogosDTO catalogoDTO) throws Exception {
		Catalogos catalogo = CatalogosToEntity.INSTANCE.apply(catalogoDTO);
		return CatalogosToDTO.INSTANCE.apply(this.catalogoDAO.save(catalogo));
	}

	/**
	 *
	 */
	@Override
	public CatalogosDTO modificar(CatalogosDTO catalogoDTO) throws Exception {
		Catalogos catalogo = CatalogosToEntity.INSTANCE.apply(catalogoDTO);
		catalogo.setFcModifFila(LocalDateTime.now());
		return CatalogosToDTO.INSTANCE.apply(this.catalogoDAO.save(catalogo));
	}

	/**
	 *
	 */
	@Override
	public List<CatalogosDTO> listarCatalogos() throws Exception{
		List<Catalogos> catalogos = this.catalogoDAO.findAll();
		return catalogos.stream().map(CatalogosToDTO.INSTANCE::apply).collect(Collectors.toList());
	}

	/**
	 *
	 */
	@Override
	public CatalogosDTO lstPorId(Integer id) throws Exception {
		return this.catalogoDAO.findById(id).map(CatalogosToDTO.INSTANCE::apply).orElse(null);
	}

	/**
	 *
	 */
	@Override
	public void eliminar(Integer id) throws Exception {

		Optional<Catalogos> optionalCatalogo = catalogoDAO.findById(id);
		CatalogosDTO dto = optionalCatalogo.map(CatalogosToDTO.INSTANCE::apply).orElse(null);

		if (Objects.isNull(dto.getIdCatalogo())){
			throw new ModeloNotFoundException("El catálogo con ID " + id + " no fue encontrado.");
		}
		dto.setFcBajaFila(LocalDateTime.now());
		dto.setCdUsuBaja(dto.getCdUsuBaja());

		Catalogos catalogos = 	CatalogosToEntity.INSTANCE.apply(dto);
		catalogoDAO.save(catalogos);

	}
}
