package com.dev.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DenunciaPersonaPK implements Serializable {

	private static final long serialVersionUID = 1L;


	@Column(name = "ID_DENUNCIA")
	private Integer idDenuncia;

	@Column(name = "ID_PERSONA")
	private Integer idPersona;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DenunciaPersonaPK that = (DenunciaPersonaPK) o;
		return Objects.equals(idDenuncia, that.idDenuncia) && Objects.equals(idPersona, that.idPersona);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDenuncia, idPersona);
	}
//@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "id_denuncia", nullable = false)
//	private Denuncia denuncia;
//
//	//@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "id_persona", nullable = false)
//	private Persona persona;








//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		DenunciaPersonaPK that = (DenunciaPersonaPK) o;
//		return Objects.equals(denuncia, that.denuncia) && Objects.equals(persona, that.persona);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(denuncia, persona);
//	}



/*	@Column(name = "id_denuncia")
	private Integer idDenunciaPK;


	@Column(name = "id_persona")
	private Integer idPersonaPK;*/




}
