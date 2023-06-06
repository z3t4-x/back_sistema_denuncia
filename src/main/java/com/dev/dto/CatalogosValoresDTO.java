package com.dev.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CatalogosValoresDTO extends AuditoriaDTO implements Serializable {
    private Integer idValor;
    private String cdCodigo;
    private String dsValor;
    private String itMantenible;
    private CatalogosDTO catalogoDTO;
    private String cdUsuAlta;
    private String cdUsuBaja;
    private String cdUsuModif;
    private LocalDateTime fcAltaFila;
    private LocalDateTime fcModifFila;
    private LocalDateTime fcBajaFila;


     ///////////////////////////////////////////////
     //////////////////////// CONSTRUCTOR
     ///////////////////////////////////////////////

     public CatalogosValoresDTO() {
         super();
         this.catalogoDTO = new CatalogosDTO();
     }

     public CatalogosValoresDTO(Integer idValor) {
         super();
         this.idValor = idValor;
         this.catalogoDTO = new CatalogosDTO();
     }


}
