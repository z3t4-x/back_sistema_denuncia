package com.dev.domain;

import com.dev.dto.CatalogosValoresDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Getter
@Setter
@Table(name="CATALOGOS_VALORES")
@Entity
public class CatalogosValores {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_VALOR")
	private Integer idValor;
	

	@Column(name="CD_CODIGO")
	private String cdCodigo;
	
	@Column(name="DS_VALOR")
	private String dsValor;
	
	@Column(name="IT_MANTENIBLE")
	private String itMantenible;
	
	
	@ManyToOne
	@JoinColumn(name="ID_CATALOGO")
	private Catalogos catalogo;

	@Column(name="CD_USU_ALTA")
	private String cdUsuAlta;
	
	@Column(name="CD_USU_BAJA")
	private String cdUsuBaja;
	
	@Column(name="CD_USU_MODIF")
	private String cdUsuModif;
	
	@Column(name="FC_ALTA_FILA")
	private LocalDateTime fcAltaFila;
	
	@Column(name="FC_MODIF_FILA")
	private LocalDateTime fcModifFila;
	
	@Column(name="FC_BAJA_FILA")
	private LocalDateTime fcBajaFila;
	
	
	public CatalogosValores() {

	}
	
	
	
	public CatalogosValores(Integer idValor) {
		super();
		this.idValor = idValor;
	}
	
	public CatalogosValores(String cdCodigo) {
		super();
		this.cdCodigo = cdCodigo;
	}


}
