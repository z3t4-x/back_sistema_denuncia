package com.dev.domain;



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
    @JoinColumn(name = "ID_DENUNCIA", nullable = false)
    private Denuncia denuncia;

    @Column(name="FC_ALTA_DENUNCIA", nullable = false)
    private LocalDate fcAltaDenuncia;

    @ManyToOne
    @JoinColumn(name="ID_FISCALIA")
    private CatalogosValores fiscalia;

    @ManyToOne
    @JoinColumn(name="ID_DELITO")
    private CatalogosValores tipoDelito;

    @Column(name="FC_HECHOS")
    private LocalDate fcHechos;

    @ManyToOne
    @JoinColumn(name="ID_INVESTIGADOR")
    private Usuario investigador;

    @Column(name="NUM_DENUNCIA")
    private String numDenuncia;

    @Column(name="FC_PLAZO")
    private LocalDate fcPlazo;

    @ManyToOne
    @JoinColumn(name="ID_ESTADO")
    private CatalogosValores estadoDenuncia;

    @ManyToOne
    @JoinColumn(name="ID_TIPO_DOCUMENTO", nullable = false)
    private CatalogosValores tipoDocumento;

    @Column(name="FC_INGRESO_DOCUMENTO", nullable = false)
    private LocalDate fcIngresoDocumento;

    private String numDocumento;

    private String descripcion;

    private String cdExpedientePreparatoria;

    private String cdExpedientePreliminar;

    // ultimos datos agregados
    @Column(name="LINK_FILE")
    private  String linkFile;
    @Column(name="NM_ARCHIVO")
    private String nmArchivo;

    @Column(name="FC_PRORROGA")
    private LocalDate fcProrroga;

    @ManyToOne
    @JoinColumn(name="ID_EXPEDIENTE_ETAPA")
    private CatalogosValores estadoExpedienteEtapa;


    @Column(name = "ID_ANAQUEL")
    private Integer anaquel;

    @Column(name = "ID_BANDA")
    private Integer banda;

    @Column(name = "ID_PAQUETE")
    private Integer paquete;

    @Column(name = "CODIGO_ARCHIVO")
    private String codigoArchivo;


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
