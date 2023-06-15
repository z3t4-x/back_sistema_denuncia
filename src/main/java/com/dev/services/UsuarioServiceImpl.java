package com.dev.services;

import com.dev.dao.UsuarioDAO;
import com.dev.dao.UsuarioRolDAO;
import com.dev.domain.Usuario;
import com.dev.domain.UsuarioPrincipal;
import com.dev.dto.UsuarioDTO;
import com.dev.dto.converters.*;
import com.dev.exception.ModeloNotFoundException;
import com.dev.utils.Constantes;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {


     @Autowired
    private UsuarioDAO usuarioDAO;

     @Autowired
     private UsuarioRolDAO usuarioRolDAO;



     @Override
    public Optional<Usuario> obtenerCdUsuario(String nombreUsuario){
        return usuarioDAO.findByCdUsuario(nombreUsuario);
    }

    @Override
    public boolean existeCodigoUsuario(String nombreUsuario){
        return usuarioDAO.existsByCdUsuario(nombreUsuario);
    }

    @Override
    public boolean existeCorreo(String email){
        return usuarioDAO.existsByEmail(email);
    }


    /**
     *
     */
    @Override
    public UsuarioDTO registrar(UsuarioDTO usuarioDTO) throws Exception {

       Usuario usuario = UsuarioToEntity.INSTANCE.apply(usuarioDTO);
//        UsuarioPrincipal usuarioPrincipal = getUsuarioPrincipalAutenticado();
//        String cdUsuario = usuarioPrincipal.getUsername();
//        Optional<Usuario> usuarioOptional = usuarioDAO.findByCdUsuario(cdUsuario);
//       Usuario  usuarioAuth = usuarioOptional.get();
//       usuario.setCdUsuAlta(usuarioAuth.getCdUsuario());
       usuario.setFcAltaFila(LocalDateTime.now());
       usuario =  this.usuarioDAO.save(usuario);
        return UsuarioToDTO.INSTANCE.apply(usuario);
    }

    /**
     *
     */
    @Override
    public UsuarioDTO modificar(UsuarioDTO usuarioDTO) throws Exception {

        Usuario usuario =  UsuarioToEntity.INSTANCE.apply(usuarioDTO);
        UsuarioPrincipal usuarioPrincipal = getUsuarioPrincipalAutenticado();
        String cdUsuario = usuarioPrincipal.getUsername();
        Optional<Usuario> usuarioOptional = usuarioDAO.findByCdUsuario(cdUsuario);
        Usuario  usuarioAuth = usuarioOptional.get();
        usuario.setCdUsuModif(usuarioAuth.getCdUsuario());
        usuario.setFcModifFila(LocalDateTime.now());
        usuario =  usuarioDAO.save(usuario);
        return UsuarioToDTO.INSTANCE.apply(usuario);
    }

    /**
     * lista los usuario de acuerdo a la fiscalía a la que pertenece.
     * @return
     * @throws Exception
     */
    @Override
    public List<UsuarioDTO> listarUsuarios() throws Exception {
        UsuarioPrincipal usuarioPrincipal = getUsuarioPrincipalAutenticado();
        String cdUsuario = usuarioPrincipal.getUsername();

        if (usuarioPrincipal == null) {
            throw new ServiceException("No se encontró el usuario.");
        }

        Usuario usuario = usuarioDAO.findByCdUsuario(cdUsuario).orElse(null);

        if (usuario == null) {
            throw new ServiceException("No se encontró el usuario.");
        }

        if (tieneRol(usuario, Constantes.Roles.ID_ROL_ADMINISTRADOR) || tieneRol(usuario, Constantes.Roles.ID_ROL_MESA_DE_PARTES)) {
            if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_14)) {
                List<Usuario> lstUsuario = usuarioDAO.findByFiscaliaIdValor(usuario.getFiscalia().getIdValor());
                return lstUsuario.stream()
                        .map(UsuarioToDTO.INSTANCE::apply)
                        .collect(Collectors.toList());

            }
            if (usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_13)) {
                List<Usuario> lstUsuario = usuarioDAO.findByFiscaliaIdValor(usuario.getFiscalia().getIdValor());
                return lstUsuario.stream()
                        .map(UsuarioToDTO.INSTANCE::apply)
                        .collect(Collectors.toList());
            }
        }

        throw new ServiceException("No se pudo obtener la lista de usuarios.");
    }


    /**
     * método para listar usuarios con rol de investigador y por fiscalía
     * @return
     */
    @Override
    public List<UsuarioDTO> obtenerUsuariosPorRolYFiscalia() {
        List<Usuario> usuarios = usuarioDAO.findAll();
        List<UsuarioDTO> usuariosFiltrados = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if ((usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_13)
                    || usuario.getFiscalia().getIdValor().equals(Constantes.Fiscalias.ID_FISCALIA_14))
                    && tieneRol(usuario, Constantes.Roles.ID_ROL_INVESTIGADOR)) {
                UsuarioDTO usuarioDTO = UsuarioToDTO.INSTANCE.apply(usuario);
                usuariosFiltrados.add(usuarioDTO);
            }
        }

        return usuariosFiltrados;
    }


    private boolean tieneRol(Usuario usuario, Integer idRol) {
        return usuario.getRoles().stream()
                .anyMatch(rol -> rol.getIdRol().equals(idRol));
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
        UsuarioPrincipal usuarioPrincipal = getUsuarioPrincipalAutenticado();
        String cdUsuario = usuarioPrincipal.getUsername();
        Optional<Usuario> usuarioOptional2 = usuarioDAO.findByCdUsuario(cdUsuario);
        Usuario  usuarioAuth = usuarioOptional2.get();
        usuario.setCdUsuBaja(usuarioAuth.getCdUsuario());
        usuario.setFcBajaFila(LocalDateTime.now());
        usuarioDAO.save(usuario);
    }

//    private boolean tieneRol(Usuario usuario, Integer idRol) {
//        return usuarioRolDAO.existsByUsuarioIdUsuarioAndRolIdRol(usuario.getIdUsuario(), idRol);
//    }


    private UsuarioPrincipal getUsuarioPrincipalAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UsuarioPrincipal) {
            return (UsuarioPrincipal) authentication.getPrincipal();
        } else {
            throw new ServiceException("No se pudo obtener el usuario autenticado.");
        }
    }
}
