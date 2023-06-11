package com.dev.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROL")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROL")
    private Integer idRol;

    @Column(name = "ROL_NOMBRE")
    private String rolNombre;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    // constructors, getters and setters
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
}

