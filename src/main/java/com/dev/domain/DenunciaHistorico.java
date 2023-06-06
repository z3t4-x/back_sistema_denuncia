package com.dev.domain;

import com.dev.dto.AuditoriaDTO;
import com.dev.dto.DenunciaDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "DENUNCIA_HISTORICO")
public class DenunciaHistorico  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_DENUNCIA_HIST")
    private  Integer idDenunciaHist;


     @ManyToOne
    @JoinColumn(name="ID_DENUNCIA", nullable = true)
   // @Column(name="id_denuncia")
    private Denuncia denuncia;

    @Column(name="FC_ALTA_DENUNCIA", nullable = false)
    private LocalDateTime fcAltaDenuncia;

    @ManyToOne
    @JoinColumn(name="ID_FISCALIA", nullable = true)
    private CatalogosValores fiscalia;

    @ManyToOne
    @JoinColumn(name="ID_DELITO")
    private CatalogosValores tipoDelito;

    @Column(name="FC_HECHOS")
    private LocalDate fcHechos;

    @ManyToOne
    @JoinColumn(name="ID_AUXILIAR")
    private CatalogosValores auxiliar;

    @Column(name="NUM_DENUNCIA")
    private String numDenuncia;

    @Column(name="FC_PLAZO")
    private LocalDate fcPlazo;

    @ManyToOne
    @JoinColumn(name="ID_ESTADO_EXPEDIENTE")
    private CatalogosValores estadoExpediente;

    @ManyToOne
    @JoinColumn(name="ID_TIPO_DOCUMENTO", nullable = false)
    private CatalogosValores tipoDocumento;

    @Column(name="FC_INGRESO_DOCUMENTO", nullable = false)
    private LocalDate fcIngresoDocumento;

    private String numDocumento;

    private String descripcion;

    private String cdExpedientePreparatoria;

    private String cdExpedientePreliminar;


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
