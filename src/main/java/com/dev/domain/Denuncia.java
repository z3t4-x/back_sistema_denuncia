package com.dev.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="DENUNCIA")
public class Denuncia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_DENUNCIA")
	private Integer idDenuncia;


	@Column(name="FC_ALTA_DENUNCIA", nullable = false)
	private LocalDate fcAltaDenuncia;
	
	@ManyToOne
	@JoinColumn(name="ID_FISCALIA", nullable = true)
	private CatalogosValores fiscalia;

	@ManyToOne
	@JoinColumn(name="ID_DELITO", nullable = false)
	private CatalogosValores tipoDelito;
	
	@Column(name="FC_HECHOS", nullable = false)
	private LocalDate fcHechos;
	
//	@ManyToOne
//	@JoinColumn(name="ID_AUXILIAR", nullable = false)
//	private CatalogosValores auxiliar;
	
	@Column(name="NM_DENUNCIA", nullable = false)
	private String nmDenuncia;
	
	@Column(name="FC_PLAZO", nullable = false)
	private LocalDate fcPlazo;
	
	@ManyToOne // (fetch= FetcType.LAZY)(REPRESENTA LA ETAPA)
	@JoinColumn(name="ID_ESTADO", nullable = false)
	private CatalogosValores estadoDenuncia;
	
	@ManyToOne
	@JoinColumn(name="ID_MESA_PARTE", nullable = true)
	private CatalogosValores mesaParte;
	
	@Column(name="DS_DESCRIPCION")
	private String dsDescripcion;

	@Column(name="NM_EXPEDIENTE_PREPARATORIA")
	private  String nmExpedientePreparatoria;

	@Column(name="NM_EXPEDIENTE_INV_PRELIMINAR")
	private  String nmExpedienteInvPreliminar;

	@ManyToOne
	@JoinColumn(name="ID_INVESTIGADOR")
	private  Usuario investigador;


	
/**indica el tipo de documento de la denuncia**/
	@ManyToOne
	@JoinColumn(name="ID_TIPO_DOCUMENTO", nullable = false)
	private CatalogosValores tipoDocumento;

	
	/***fecha de ingreso de documento de la denuncia***/
	@Column(name="FC_INGRESO_DOCUMENTO", nullable = false)
	private LocalDate fcIngresoDocumento;
	

	
	/**numero del documento del que se ha ejecutado la denuncia**/
	@Column(name="NM_DOCUMENTO", nullable = false)
	private String nmDocumento;


	// ultimos datos agregados
	@Column(name="LINK_FILE")
	private  String linkFile;

	@Column(name="NM_ARCHIVO")
	private String nmArchivo;

	@Column(name="FC_PRORROGA")
	private LocalDate fcProrroga;

	@ManyToOne
	@JoinColumn(name="ID_EXPEDIENTE_ETAPA")
	private CatalogosValores estadoExpedienteEtapa;

	@Column(name = "ID_ANAQUEL")
	private Integer anaquel;

	@Column(name = "ID_BANDA")
	private Integer banda;

	@Column(name = "ID_PAQUETE")
	private Integer paquete;

	@Column(name = "CODIGO_ARCHIVO")
	private String codigoArchivo;

	//@JsonBackReference
	@OneToMany
	@JoinColumn(name = "ID_DENUNCIA", nullable = false, insertable = false, updatable = false)
	private Set<DenunciaPersona> lstDenunciantes = new HashSet<>();

	//@JsonBackReference
	@OneToMany
	@JoinColumn(name = "ID_DENUNCIA", insertable = false, updatable = false)
	private Set<DenunciaPersona> lstDenunciados = new HashSet<>();


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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Denuncia denuncia = (Denuncia) o;
		return Objects.equals(idDenuncia, denuncia.idDenuncia);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDenuncia);
	}
}
