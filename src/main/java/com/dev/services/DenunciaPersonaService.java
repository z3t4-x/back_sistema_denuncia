package com.dev.services;

import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;

import java.util.List;

public interface DenunciaPersonaService {
    //List<DenunciaPersona> obtenerDenunciantesYDenunciados(Denuncia denuncia);

    List<DenunciaPersona> obtenerDenunciantes(Denuncia denuncia);

    List<DenunciaPersona> obtenerDenunciados(Denuncia denuncia);
}
