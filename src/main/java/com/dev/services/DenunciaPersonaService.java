package com.dev.services;

import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;

import java.time.LocalDate;
import java.util.List;

public interface DenunciaPersonaService {


    /**
     * lista los denunciantes
     * @param denuncia
     * @return
     */
    List<DenunciaPersona> obtenerDenunciantes(Denuncia denuncia);

    /**
     * lsta los denunciados
     * @param denuncia
     * @return
     */
    List<DenunciaPersona> obtenerDenunciados(Denuncia denuncia);

    /**
     * exporta las denuncias en excel
     */
    byte[] exportarDenunciasExcel(Integer idEtapaInvestigacion, LocalDate fecha);
}
