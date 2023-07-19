package com.dev.services;

import com.dev.domain.Rol;
import com.dev.domain.Usuario;
import com.dev.dto.PersonaDTO;
import com.dev.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {


    Optional<Usuario> obtenerCdUsuario(String nombreUsuario);

    boolean existeCodigoUsuario(String nombreUsuario);

    boolean existeCorreo(String email);

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
    List<UsuarioDTO> listarUsuarios() throws Exception;

    List<Rol> obtenerRolesUsuario();

    List<UsuarioDTO> obtenerUsuariosPorRolYFiscalia();

    /**
     * buscar por id
     * @param id
     * @return
     * @throws Exception
     */
    UsuarioDTO buscarPorId(Integer id) throws Exception;

    Usuario buscarIdUsuario(Integer id) throws Exception;

    /**
     * eliminar
     * @param id
     * @throws Exception
     */
    void eliminar(Integer id) throws Exception;
}
