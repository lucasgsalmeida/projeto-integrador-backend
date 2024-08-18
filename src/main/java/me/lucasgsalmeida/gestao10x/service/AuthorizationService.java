package me.lucasgsalmeida.gestao10x.service;

import me.lucasgsalmeida.gestao10x.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository rep;
    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        return rep.findByUsuario(usuario);
    }
}