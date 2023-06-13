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


    @Column(name="ID_DENUNCIA", nullable = false)
    private Integer idDenuncia;

    @Column(name="FC_ALTA_DENUNCIA", nullable = false)
    private LocalDate fcAltaDenuncia;

    @Column(name="ID_FISCALIA", nullable = false)
    private Integer idFiscalia;

    @Column(name="ID_DELITO", nullable = false)
    private Integer idTipoDelito;

    @Column(name="FC_HECHOS")
    private LocalDate fcHechos;

    @Column(name="ID_AUXILIAR")
    private Integer idAuxiliar;

    @Column(name="ID_INVESTIGADOR")
    private Integer idInvestigador;

    @Column(name="NUM_DENUNCIA")
    private String numDenuncia;

    @Column(name="FC_PLAZO")
    private LocalDate fcPlazo;

    @Column(name="ID_ESTADO_EXPEDIENTE")
    private Integer idEstadoExpediente;

    @Column(name="ID_TIPO_DOCUMENTO", nullable = false)
    private Integer idTipoDocumento;

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
