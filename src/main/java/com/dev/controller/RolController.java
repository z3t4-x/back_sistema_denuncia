package com.dev.controller;

import com.dev.dto.RolDTO;
import com.dev.dto.UsuarioDTO;
import com.dev.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {


    @Autowired
    private RolService rolService;



    @GetMapping
    public ResponseEntity<List<RolDTO>> listarRol() {
        try {
            List<RolDTO> rolesDTO = rolService.listarRol();
            return ResponseEntity.ok(rolesDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }








}
