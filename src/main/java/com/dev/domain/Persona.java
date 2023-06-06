package com.dev.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="PERSONA")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PERSONA")
	private Integer idPersona;
	
	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="APELLIDO1")
	private String apellido1;
	
	@Column(name="APELLIDO2")
	private String apellido2;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_GRADO", nullable = true)
	private CatalogosValores grado;
	
	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_GENERO")
	private CatalogosValores genero;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_TIPO_IDENTIFICACION")
	private CatalogosValores tipoIdentificacion;
	
	@Column(name="DNI", unique = true, length = 8)
	private String dni;


	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_INSTITUCION")
	private CatalogosValores tipoInstitucion;
	
	@Column(name="FC_NACIMIENTO")
	private LocalDate fcNacimiento;
	
	@Column(name="TELEFONO", length = 9)
	private String telefono;

	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private  Usuario usuario;



	//@JsonBackReference
	//@JsonBackReference
	//@JsonIgnoreProperties({"persona", "lstDenunciasPersonas"})
	@JsonBackReference
	@OneToMany(mappedBy = "persona", fetch = FetchType.EAGER)
	private Set<DenunciaPersona> lstDenunciasPersonas;

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
		Persona persona = (Persona) o;
		return Objects.equals(idPersona, persona.idPersona);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPersona);
	}
}
