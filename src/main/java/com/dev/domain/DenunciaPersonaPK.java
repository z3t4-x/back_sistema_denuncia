package com.dev.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
@Data
@ToString
public class DenunciaPersonaPK implements Serializable {

	private static final long serialVersionUID = 1L;


	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_denuncia", nullable = false)
	private Denuncia denuncia;

	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_persona", nullable = false)
	private Persona persona;







/*	@Column(name = "id_denuncia")
	private Integer idDenunciaPK;


	@Column(name = "id_persona")
	private Integer idPersonaPK;*/




}
