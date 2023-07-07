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
public class DenunciaPersona{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DENUNCIA_PERSONA", nullable = false)
	private Integer idDenunciaPersona;

	@Column(name = "ID_DENUNCIA", nullable = false)
	private Integer idDenuncia;

	//@JsonIgnoreProperties("lstDenunciasPersonas")
	//@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA", nullable = false)
	private Persona persona;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PERSONA", nullable = false)
	private CatalogosValores tipoPersona;


}
