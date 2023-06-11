package com.dev.services;


import com.dev.dao.IDenunciaPersonaDAO;
import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DenunciaPersonaServiceImpl implements  DenunciaPersonaService{

    @Autowired
    IDenunciaPersonaDAO denunciaPersonaDAO;

    @Override
    public List<DenunciaPersona> obtenerDenunciantes(Denuncia denuncia) {
        String cdCodigo =  "DNCTE";
        return denunciaPersonaDAO.findByIdDenunciaAndTipoPersonaCdCodigo(denuncia.getIdDenuncia(), cdCodigo);
    }

    @Override
    public List<DenunciaPersona> obtenerDenunciados(Denuncia denuncia) {
        String cdCodigo =  "DNCDO";
        return denunciaPersonaDAO.findByIdDenunciaAndTipoPersonaCdCodigo(denuncia.getIdDenuncia(), cdCodigo);
    }
}
