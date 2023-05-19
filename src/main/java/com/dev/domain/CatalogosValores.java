package com.dev.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





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

	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private  Usuario usuario;
	
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

	public Integer getIdValor() {
		return idValor;
	}

	public void setIdValor(Integer idValor) {
		this.idValor = idValor;
	}

	public String getCdCodigo() {
		return cdCodigo;
	}

	public void setCdCodigo(String cdCodigo) {
		this.cdCodigo = cdCodigo;
	}

	public String getDsValor() {
		return dsValor;
	}

	public void setDsValor(String dsValor) {
		this.dsValor = dsValor;
	}

	public String getItMantenible() {
		return itMantenible;
	}

	public void setItMantenible(String itMantenible) {
		this.itMantenible = itMantenible;
	}

	public Catalogos getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogos catalogo) {
		this.catalogo = catalogo;
	}

	public String getCdUsuAlta() {
		return cdUsuAlta;
	}

	public void setCdUsuAlta(String cdUsuAlta) {
		this.cdUsuAlta = cdUsuAlta;
	}

	public String getCdUsuBaja() {
		return cdUsuBaja;
	}

	public void setCdUsuBaja(String cdUsuBaja) {
		this.cdUsuBaja = cdUsuBaja;
	}

	public String getCdUsuModif() {
		return cdUsuModif;
	}

	public void setCdUsuModif(String cdUsuModif) {
		this.cdUsuModif = cdUsuModif;
	}

	public LocalDateTime getFcAltaFila() {
		return fcAltaFila;
	}

	public void setFcAltaFila(LocalDateTime fcAltaFila) {
		this.fcAltaFila = fcAltaFila;
	}

	public LocalDateTime getFcModifFila() {
		return fcModifFila;
	}

	public void setFcModifFila(LocalDateTime fcModifFila) {
		this.fcModifFila = fcModifFila;
	}

	public LocalDateTime getFcBajaFila() {
		return fcBajaFila;
	}

	public void setFcBajaFila(LocalDateTime fcBajaFila) {
		this.fcBajaFila = fcBajaFila;
	}
}
