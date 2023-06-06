package com.dev.security.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class JwtDTO {


    private String token;
    private String bearer = "Bearer";
    private String cdUsuario;
    private Collection<? extends GrantedAuthority> authorities;


    public JwtDTO(String token, String cdUsuario, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.cdUsuario = cdUsuario;
        this.authorities = authorities;
    }

}
