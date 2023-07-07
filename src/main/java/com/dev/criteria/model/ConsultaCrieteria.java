package com.dev.criteria.model;

import com.dev.domain.CatalogosValores;
import com.dev.domain.DenunciaPersona;
import com.dev.domain.Usuario;
import com.dev.dto.CatalogosValoresDTO;
import com.dev.dto.DenunciaPersonaDTO;
import com.dev.dto.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ConsultaCrieteria {

    private Integer idDenuncia;
    private LocalDate fcAltaDenuncia;
    private CatalogosValoresDTO fiscalia;
    private CatalogosValoresDTO tipoDelito;
    private LocalDate fcHechos;
    private UsuarioDTO investigador;
    private String nmDenuncia;
    private LocalDate fcPlazo;
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
}
