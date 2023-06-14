package com.dev.services;

import com.dev.dto.RolDTO;
import com.dev.dto.UsuarioDTO;

import java.util.List;

public interface RolService {

    /**
     * registrar
     * @param rolDTO
     * @return
     * @throws Exception
     */
    RolDTO registrar(RolDTO rolDTO) throws Exception;

    /**
     * modificar
     * @param rolDTO
     * @return
     * @throws Exception
     */
    RolDTO modificar(RolDTO rolDTO) throws Exception;

    /**
     * listar
     * @return
     * @throws Exception
     */
    List<RolDTO> listarRol() throws Exception;

    /**
     * buscar por id
     * @param id
     * @return
     * @throws Exception
     */
    RolDTO buscarPorId(Integer id) throws Exception;

    /**
     * eliminar
     * @param id
     * @throws Exception
     */
    void eliminar(Integer id) throws Exception;
}
