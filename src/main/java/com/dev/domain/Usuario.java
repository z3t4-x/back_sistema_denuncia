package com.dev.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "CD_USUARIO", unique = true)
    private  String cdUsuario;

    @Column(name = "PASSWORD")
    private String password;

    @Email
    @Column(name = "EMAIL", unique = true)
    private  String email;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_FISCALIA")
    private CatalogosValores fiscalia;


    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_MESA_PARTE")
    private CatalogosValores mesaParte;

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

    public Usuario() {
    }

    public Usuario(@NotNull String nombre, @NotNull String cdUsuario, @NotNull String email, @NotNull String password) {
        this.nombre = nombre;
        this.cdUsuario = cdUsuario;
        this.email = email;
        this.password = password;
    }

    // constructors, getters and setters
}

