package com.dev.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DENUNCIA_PERSONA")
@Entity
@IdClass(DenunciaPersonaPK.class)
public class DenunciaPersona implements Serializable {
	@Id
	//@JsonIgnoreProperties({"lstDenunciantes", "lstDenunciados"})
	@JsonManagedReference
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_denuncia", nullable = false)
	private Denuncia denuncia;
	@Id
	//@JsonIgnoreProperties("lstDenunciasPersonas")
	@JsonManagedReference
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_persona", nullable = false)
	private Persona persona;
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PERSONA")
	private CatalogosValores tipoPersona;


/*	@EmbeddedId
	private DenunciaPersonaPK denunciaPersonaPK;

	@JsonBackReference
	@MapsId("idDenunciaPK")
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_denuncia", nullable = false)
	private Denuncia denuncia;

	@JsonBackReference
	@MapsId("idPersonaPK")
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_persona", nullable = false)
	private Persona persona;*/





}
