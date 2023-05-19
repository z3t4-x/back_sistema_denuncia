package com.dev.services;

import com.dev.dao.UsuarioDAO;
import com.dev.domain.CatalogosValores;
import com.dev.domain.Usuario;
import com.dev.dto.CatalogosValoresDTO;
import com.dev.dto.UsuarioDTO;
import com.dev.dto.converters.*;
import com.dev.exception.ModeloNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioServiceImpl implements UsuarioService {


     @Autowired
    private UsuarioDAO usuarioDAO;

    /**
     *
     */
    @Override
    public UsuarioDTO registrar(UsuarioDTO usuarioDTO) throws Exception {

        Usuario usuario = UsuarioToEntity.INSTANCE.apply(usuarioDTO);

       usuario =  this.usuarioDAO.save(usuario);
        return UsuarioToDTO.INSTANCE.apply(usuario);
    }

    /**
     *
     */
    @Override
    public UsuarioDTO modificar(UsuarioDTO usuarioDTO) throws Exception {

        Usuario usuario =  UsuarioToEntity.INSTANCE.apply(usuarioDTO);
        usuario =  usuarioDAO.save(usuario);
        return UsuarioToDTO.INSTANCE.apply(usuario);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<UsuarioDTO> listarPersonas() throws Exception {

        List<Usuario> lstUsuario =  usuarioDAO.findAll();
        return lstUsuario.stream().map(UsuarioToDTO.INSTANCE::apply).collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public UsuarioDTO buscarPorId(Integer id) throws Exception {

        Optional<Usuario> catValor = this.usuarioDAO.findById(id);
        return catValor.map(UsuarioToDTO.INSTANCE::apply).orElse(null);
    }

    /**
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void eliminar(Integer id) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioDAO.findById(id);
        UsuarioDTO dto = usuarioOptional.map(UsuarioToDTO.INSTANCE::apply).orElse(null);

        if (Objects.isNull(dto.getIdUsuario())){
            throw new ModeloNotFoundException("Persona con ID " + id + " no fue encontrado.");
        }

        dto.setFcBajaFila(LocalDateTime.now());
        dto.setCdUsuBaja(dto.getCdUsuBaja());

        Usuario usuario = 	UsuarioToEntity.INSTANCE.apply(dto);
        usuarioDAO.save(usuario);
    }
}
