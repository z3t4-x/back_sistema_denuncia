package com.dev.security;

/*import com.dev.dao.UsuarioDAO;
import com.dev.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/

import com.dev.dao.UsuarioDAO;
import com.dev.domain.Usuario;
import com.dev.domain.UsuarioPrincipal;
import com.dev.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Lazy
    private UsuarioService usuarioService;


    @Override
    public UserDetails loadUserByUsername(String cdUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.obtenerCdUsuario(cdUsuario).get();
        return UsuarioPrincipal.build(usuario);
    }
}
