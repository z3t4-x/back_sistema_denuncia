package com.dev.security.controller;

import com.dev.domain.Rol;
import com.dev.domain.Usuario;
import com.dev.dto.RolDTO;
import com.dev.dto.UsuarioDTO;
import com.dev.dto.converters.RolToEntity;
import com.dev.dto.converters.UsuarioToDTO;
import com.dev.dto.converters.UsuarioToEntity;
import com.dev.exception.ModeloNotFoundException;
import com.dev.security.JwtProvider;
import com.dev.security.dto.JwtDTO;
import com.dev.security.dto.LoginUsuario;
import com.dev.services.RolService;
import com.dev.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private  UsuarioService usuarioService;

    @Autowired
    private  RolService rolService;

    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("/nuevoUsuario")
    public ResponseEntity<?> nuevo(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors()){
            log.error("Campos mal puesto");
        }

        if(usuarioService.existeCodigoUsuario(usuarioDTO.getCdUsuario())){

            log.info("Ese usuario ya existe");
        }

        if(usuarioService.existeCorreo(usuarioDTO.getEmail())){

            log.info("Ese correo electrónico ya existe ");
        }

        usuarioDTO.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        List<RolDTO> rolesDTO = new ArrayList<>() ;

        for (RolDTO rolDTO : usuarioDTO.getRolesDTO()) {

            rolesDTO.add(rolService.buscarPorId(rolDTO.getIdRol()));

        }

        usuarioDTO.setRolesDTO(rolesDTO);
        Usuario usuario = UsuarioToEntity.INSTANCE.apply(usuarioDTO);
        usuarioDTO = UsuarioToDTO.INSTANCE.apply(usuario);
         usuarioService.registrar(usuarioDTO);
        return  new ResponseEntity<>(HttpStatus.CREATED);

    }


    @PutMapping
    public ResponseEntity<UsuarioDTO> modificar(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
        UsuarioDTO usuarioModificadoDTO = usuarioService.modificar(usuarioDTO);
        return ResponseEntity.ok(usuarioModificadoDTO);
    }



    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        try {
            List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            // Manejar cualquier excepción y devolver una respuesta de error adecuada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (ModeloNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Integer id) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.buscarPorId(id);
            if (usuarioDTO != null) {
                return ResponseEntity.ok(usuarioDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
           log.error("Campos mal puestos. ");
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getCdUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDTO jwtDto = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
