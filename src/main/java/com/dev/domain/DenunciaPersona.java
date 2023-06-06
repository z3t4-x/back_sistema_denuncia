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
//@IdClass(DenunciaPersonaPK.class)
public class DenunciaPersona implements Serializable {

	@EmbeddedId
	private DenunciaPersonaPK id;


	//@Id
	//@JsonIgnoreProperties({"lstDenunciantes", "lstDenunciados"})
	@MapsId("idDenuncia")
	@JsonManagedReference
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_denuncia", nullable = false)
	private Denuncia denuncia;

	//@Id
	//@JsonIgnoreProperties("lstDenunciasPersonas")
	@MapsId("idPersona")
	@JsonManagedReference
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_persona", nullable = false)
	private Persona persona;
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PERSONA")
	private CatalogosValores tipoPersona;

	public DenunciaPersona(Denuncia denuncia, Persona persona, CatalogosValores tipoPersona) {
		this.id = new DenunciaPersonaPK(denuncia.getIdDenuncia(), persona.getIdPersona());
		this.denuncia = denuncia;
		this.persona = persona;
		this.tipoPersona = tipoPersona;
	}



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
