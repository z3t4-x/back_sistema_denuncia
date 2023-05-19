package com.dev.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@ToString
public class AuditoriaDTO implements Serializable {
    /**
     * serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String cdUsuAlta;

    /**
     *
     */
    private LocalDateTime fcAltaFila;

    /**
     *
     */
    private String cdUsuModif;

    /**
     *
     */
    private LocalDateTime fcModifFila;

    /**
     *
     */
    private String cdUsuBaja;

    /**
     *
     */
    private LocalDateTime fcBajaFila;
}
