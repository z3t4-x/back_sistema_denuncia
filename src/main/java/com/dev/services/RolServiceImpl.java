package com.dev.services;

import com.dev.dao.RolDAO;
import com.dev.domain.Rol;
import com.dev.domain.Usuario;
import com.dev.dto.RolDTO;
import com.dev.dto.UsuarioDTO;
import com.dev.dto.converters.RolToDTO;
import com.dev.dto.converters.RolToEntity;
import com.dev.dto.converters.UsuarioToDTO;
import com.dev.dto.converters.UsuarioToEntity;
import com.dev.exception.ModeloNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class RolServiceImpl implements RolService {

    @Autowired
    private RolDAO rolDAO;


    @Override
    public RolDTO registrar(RolDTO rolDTO) throws Exception {
        return null;
    }

    @Override
    public RolDTO modificar(RolDTO rolDTO) throws Exception {
        Rol rol =  RolToEntity.INSTANCE.apply(rolDTO);
        rol =  rolDAO.save(rol);
        return RolToDTO.INSTANCE.apply(rol);
    }

    @Override
    public List<RolDTO> listarPersonas() throws Exception {
        List<Rol> lstRol =  rolDAO.findAll();
        return lstRol.stream().map(RolToDTO.INSTANCE::apply).collect(Collectors.toList());
    }

    @Override
    public RolDTO buscarPorId(Integer id) throws Exception {
        Optional<Rol> rol = this.rolDAO.findById(id);
        return rol.map(RolToDTO.INSTANCE::apply).orElse(null);
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        Optional<Rol> rolOptional = rolDAO.findById(id);
        RolDTO dto = rolOptional.map(RolToDTO.INSTANCE::apply).orElse(null);

        if (Objects.isNull(dto.getIdRol())){
            throw new ModeloNotFoundException("Persona con ID " + id + " no fue encontrado.");
        }

        dto.setFcBajaFila(LocalDateTime.now());
        dto.setCdUsuBaja(dto.getCdUsuBaja());

        Rol rol = 	RolToEntity.INSTANCE.apply(dto);
        rolDAO.save(rol);
    }
}
