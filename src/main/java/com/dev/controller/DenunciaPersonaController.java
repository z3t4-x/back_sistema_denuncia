package com.dev.controller;

import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;
import com.dev.services.DenunciaPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/denuncias-personas")
public class DenunciaPersonaController {

    @Autowired
    private DenunciaPersonaService denunciaPersonaService;

    @GetMapping("/denunciantes/{idDenuncia}")
    public ResponseEntity<List<DenunciaPersona>> obtenerDenunciantes(@PathVariable Integer idDenuncia) {
        Denuncia denuncia = new Denuncia();
        denuncia.setIdDenuncia(idDenuncia);
        List<DenunciaPersona> denunciantes = denunciaPersonaService.obtenerDenunciantes(denuncia);
        return ResponseEntity.ok(denunciantes);
    }


    @GetMapping("/denunciados/{idDenuncia}")
    public ResponseEntity<List<DenunciaPersona>> obtenerDenunciados(@PathVariable Integer idDenuncia) {
        Denuncia denuncia = new Denuncia();
        denuncia.setIdDenuncia(idDenuncia);
        List<DenunciaPersona> denunciados = denunciaPersonaService.obtenerDenunciados(denuncia);
        return ResponseEntity.ok(denunciados);
    }

}