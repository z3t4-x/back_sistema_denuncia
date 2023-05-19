package com.dev.services;

import com.dev.dto.PersonaDTO;
import com.dev.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {


    /**
     * registrar
     * @param usuarioDTO
     * @return
     * @throws Exception
     */
    UsuarioDTO registrar(UsuarioDTO usuarioDTO) throws Exception;

    /**
     * modificar
     * @param usuarioDTO
     * @return
     * @throws Exception
     */
    UsuarioDTO modificar(UsuarioDTO usuarioDTO) throws Exception;

    /**
     * listar
     * @return
     * @throws Exception
     */
    List<UsuarioDTO> listarPersonas() throws Exception;

    /**
     * buscar por id
     * @param id
     * @return
     * @throws Exception
     */
    UsuarioDTO buscarPorId(Integer id) throws Exception;

    /**
     * eliminar
     * @param id
     * @throws Exception
     */
    void eliminar(Integer id) throws Exception;
}
