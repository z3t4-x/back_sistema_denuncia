package com.dev.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;

    @Column(name = "CONTRASENIA")
    private String contrasenia;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_FISCALIA")
    private CatalogosValores fiscalia;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USUARIO_ROL",
            joinColumns = @JoinColumn(name = "ID_USUARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROL")
    )
    private List<Rol> roles;

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

    // constructors, getters and setters
}

