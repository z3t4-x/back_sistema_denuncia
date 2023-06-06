package com.dev.dto;

import com.dev.domain.Denuncia;
import com.dev.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DenunciaDTO extends AuditoriaDTO implements Serializable {
    private Integer idDenuncia;
    private LocalDateTime fcAltaDenuncia;
    private CatalogosValoresDTO fiscalia;
    private CatalogosValoresDTO tipoDelito;
    private LocalDate fcHechos;
    private UsuarioDTO investigador;
    private String nmDenuncia;
    private LocalDateTime fcPlazo;
    private CatalogosValoresDTO estadoDenuncia;
    private CatalogosValoresDTO mesaParte;
    private String dsDescripcion;
    private CatalogosValoresDTO tipoDocumento;
    private LocalDate fcIngresoDocumento;
    private String nmDocumento;

    private  String nmExpedientePreparatoria;

    private  String nmExpedienteInvPreliminar;

    private CatalogosValoresDTO auxiliar;

    private List<DenunciaPersonaDTO> lstDenunciantes;
    private List<DenunciaPersonaDTO> lstDenunciados;


    public DenunciaDTO() {
        super();
        this.fiscalia = new CatalogosValoresDTO();
        this.tipoDelito = new CatalogosValoresDTO();
        this.investigador = new UsuarioDTO();
        this.estadoDenuncia = new CatalogosValoresDTO();
        this.mesaParte = new CatalogosValoresDTO();
        this.auxiliar = new CatalogosValoresDTO();
        this.tipoDocumento = new CatalogosValoresDTO();
        this.lstDenunciantes = new ArrayList<>();
        this.lstDenunciados = new ArrayList<>();
    }


}

