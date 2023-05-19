package com.dev.dto;

import com.dev.domain.CatalogosValores;
import com.dev.domain.Denuncia;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DenunciaHistoricoDTO {

    private Integer idDenunciaHist;
    private String numDenuncia;
    private String cExpediente;
    private CatalogosValoresDTO estadoExpedienteAnterior;
    private CatalogosValoresDTO estadoExpedienteNuevo;
    private Denuncia denunciaDTO;
    private LocalDate fcPlazo;
    private String numDocumento;
    private String descripcion;
    private LocalDate fcIngresoDocumento;

    public DenunciaHistoricoDTO() {
        this.estadoExpedienteAnterior = new CatalogosValoresDTO();
        this.estadoExpedienteNuevo =  new CatalogosValoresDTO();

    }
}