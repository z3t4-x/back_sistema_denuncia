package com.dev.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;


@Entity
@Table(name="CATALOGOS")
public class Catalogos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_CATALOGO")
	private Integer idCatalogo;
	
	@Column(name=" DS_NOMBRE")
	private String dsNombre;

	@Column(name=" TL_DESCRIPCION")
	private String tlDescripcion;
	
	@Column(name= "IT_MANTENIBLE")
	private String itMantenible;


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

	/**
	 * @return the idCatalogo
	 */
	public Integer getIdCatalogo() {
		return idCatalogo;
	}

	/**
	 * @param idCatalogo the idCatalogo to set
	 */
	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	/**
	 * @return the dsNombre
	 */
	public String getDsNombre() {
		return dsNombre;
	}

	/**
	 * @param dsNombre the dsNombre to set
	 */
	public void setDsNombre(String dsNombre) {
		this.dsNombre = dsNombre;
	}

	/**
	 * @return the itMantenible
	 */
	public String getItMantenible() {
		return itMantenible;
	}

	/**
	 * @param itMantenible the itMantenible to set
	 */
	public void setItMantenible(String itMantenible) {
		this.itMantenible = itMantenible;
	}

	/**
	 * @return the cdUsuAlta
	 */
	public String getCdUsuAlta() {
		return cdUsuAlta;
	}

	/**
	 * @param cdUsuAlta the cdUsuAlta to set
	 */
	public void setCdUsuAlta(String cdUsuAlta) {
		this.cdUsuAlta = cdUsuAlta;
	}

	/**
	 * @return the cdUsuBaja
	 */
	public String getCdUsuBaja() {
		return cdUsuBaja;
	}

	/**
	 * @param cdUsuBaja the cdUsuBaja to set
	 */
	public void setCdUsuBaja(String cdUsuBaja) {
		this.cdUsuBaja = cdUsuBaja;
	}

	/**
	 * @return the cdUsuModif
	 */
	public String getCdUsuModif() {
		return cdUsuModif;
	}

	/**
	 * @param cdUsuModif the cdUsuModif to set
	 */
	public void setCdUsuModif(String cdUsuModif) {
		this.cdUsuModif = cdUsuModif;
	}

	/**
	 * @return the fcAltaFila
	 */
	public LocalDateTime getFcAltaFila() {
		return fcAltaFila;
	}

	/**
	 * @param fcAltaFila the fcAltaFila to set
	 */
	public void setFcAltaFila(LocalDateTime fcAltaFila) {
		this.fcAltaFila = fcAltaFila;
	}

	/**
	 * @return the fcModifFila
	 */
	public LocalDateTime getFcModifFila() {
		return fcModifFila;
	}

	/**
	 * @param fcModifFila the fcModifFila to set
	 */
	public void setFcModifFila(LocalDateTime fcModifFila) {
		this.fcModifFila = fcModifFila;
	}

	/**
	 * @return the fcBajaFila
	 */
	public LocalDateTime getFcBajaFila() {
		return fcBajaFila;
	}

	/**
	 * @param fcBajaFila the fcBajaFila to set
	 */
	public void setFcBajaFila(LocalDateTime fcBajaFila) {
		this.fcBajaFila = fcBajaFila;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cdUsuAlta, cdUsuBaja, cdUsuModif, dsNombre, fcAltaFila, fcBajaFila, fcModifFila, idCatalogo,
				itMantenible);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Catalogos other = (Catalogos) obj;
		return Objects.equals(cdUsuAlta, other.cdUsuAlta) && Objects.equals(cdUsuBaja, other.cdUsuBaja)
				&& Objects.equals(cdUsuModif, other.cdUsuModif) && Objects.equals(dsNombre, other.dsNombre)
				&& Objects.equals(fcAltaFila, other.fcAltaFila) && Objects.equals(fcBajaFila, other.fcBajaFila)
				&& Objects.equals(fcModifFila, other.fcModifFila) && Objects.equals(idCatalogo, other.idCatalogo)
				&& Objects.equals(itMantenible, other.itMantenible);
	}

	@Override
	public String toString() {
		return "Catalogos [idCatalogo=" + idCatalogo + ", dsNombre=" + dsNombre + ", itMantenible=" + itMantenible
				+ ", cdUsuAlta=" + cdUsuAlta + ", cdUsuBaja=" + cdUsuBaja + ", cdUsuModif=" + cdUsuModif
				+ ", fcAltaFila=" + fcAltaFila + ", fcModifFila=" + fcModifFila + ", fcBajaFila=" + fcBajaFila + "]";
	}
	
	

}
