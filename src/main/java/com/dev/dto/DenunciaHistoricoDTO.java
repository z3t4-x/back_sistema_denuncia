package com.dev.dto;

import com.dev.domain.CatalogosValores;
import com.dev.domain.Denuncia;
import com.dev.domain.Usuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DenunciaHistoricoDTO {

    private Integer idDenunciaHist;
    private Denuncia denunciaDTO;
    private LocalDate fcAltaDenuncia;
    private CatalogosValoresDTO fiscalia;
    private CatalogosValoresDTO tipoDelito;
    private LocalDate fcHechos;
    private Usuario investigador;
    private String numDenuncia;
    private LocalDate fcPlazo;
    private CatalogosValoresDTO estadoDenuncia;
    private CatalogosValoresDTO tipoDocumento;
    private LocalDate fcIngresoDocumento;
    private String numDocumento;
    private String cExpediente;
    private String descripcion;
    private String cdExpedientePreparatoria;
    private String cdExpedientePreliminar;
    // ultimos datos agregados
    private  String linkFile;
    private String nmArchivo;
    private LocalDate fcProrroga;
    private CatalogosValoresDTO estadoExpedienteEtapa;
    private Integer anaquel;
    private Integer banda;
    private Integer paquete;
    private String codArchivo;



    public DenunciaHistoricoDTO() {
        this.estadoDenuncia = new CatalogosValoresDTO();


    }
}