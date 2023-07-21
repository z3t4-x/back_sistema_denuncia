package com.dev.controller;

import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;
import com.dev.services.DenunciaPersonaService;
import com.dev.utils.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
 @Slf4j
@RequestMapping("/denuncias-personas")
public class DenunciaPersonaController {

    @Autowired
    private DenunciaPersonaService denunciaPersonaService;

    /**
     * lista los denunciantes
     * @param idDenuncia
     * @return
     */
    @GetMapping("/denunciantes/{idDenuncia}")
    public ResponseEntity<List<DenunciaPersona>> obtenerDenunciantes(@PathVariable Integer idDenuncia) {
        Denuncia denuncia = new Denuncia();
        denuncia.setIdDenuncia(idDenuncia);
        List<DenunciaPersona> denunciantes = denunciaPersonaService.obtenerDenunciantes(denuncia);
        return ResponseEntity.ok(denunciantes);
    }

    /**
     * lista los denunciados
     * @param idDenuncia
     * @return
     */
    @GetMapping("/denunciados/{idDenuncia}")
    public ResponseEntity<List<DenunciaPersona>> obtenerDenunciados(@PathVariable Integer idDenuncia) {
        Denuncia denuncia = new Denuncia();
        denuncia.setIdDenuncia(idDenuncia);
        List<DenunciaPersona> denunciados = denunciaPersonaService.obtenerDenunciados(denuncia);
        return ResponseEntity.ok(denunciados);
    }


    /**
     * método para exportar en excel
     * @return
     */
    @GetMapping("/exportar-denuncias")
    public ResponseEntity<byte[]> exportarDenunciasExcel(
            @RequestParam(value = "fecha", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
            @RequestParam(value = "estadoInvestigacion", required = false) Integer estadoInvestigacion) {

        try {
            byte[] excelBytes;
            String nombreArchivo;

            if (estadoInvestigacion != null) {
                if (estadoInvestigacion.equals(Constantes.estadoInvestigacion.ID_DENUNCIA.intValue())) {
                    excelBytes = denunciaPersonaService.exportarDenunciasExcel(estadoInvestigacion, fecha);
                    nombreArchivo = "Denuncias.xlsx";
                } else if (estadoInvestigacion.equals(Constantes.estadoInvestigacion.ID_PRELIMINAR.intValue())) {
                    excelBytes = denunciaPersonaService.exportarDenunciasExcel(estadoInvestigacion, fecha);
                    nombreArchivo = "Inv. preliminar.xlsx";
                } else if (estadoInvestigacion.equals(Constantes.estadoInvestigacion.ID_PREPARATORIA.intValue())) {
                    excelBytes = denunciaPersonaService.exportarDenunciasExcel(estadoInvestigacion, fecha);
                    nombreArchivo = "Inv. preparatoria.xlsx";
                } else if (estadoInvestigacion.equals(Constantes.estadoInvestigacion.ID_INTERMEDIA.intValue())) {
                    excelBytes = denunciaPersonaService.exportarDenunciasExcel(estadoInvestigacion, fecha);
                    nombreArchivo = "Inv. intermedia.xlsx";
                } else {
                    // Estado de investigación no válido
                    return ResponseEntity.badRequest().body(null);
                }
            } else {
                // Ambos parámetros son nulos
                excelBytes = denunciaPersonaService.exportarDenunciasExcel(null, fecha);
                nombreArchivo = "Denuncias.xlsx";
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(excelBytes);
        } catch (Exception e) {
           log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }




}