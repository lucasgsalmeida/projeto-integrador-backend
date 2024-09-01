package me.lucasgsalmeida.gestao10x.service;

import me.lucasgsalmeida.gestao10x.infra.EscritorioStateCache;
import me.lucasgsalmeida.gestao10x.infra.UsuarioStateCache;
import me.lucasgsalmeida.gestao10x.model.domain.escritorio.Escritorio;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.*;
import me.lucasgsalmeida.gestao10x.model.repository.EscritorioRepository;
import me.lucasgsalmeida.gestao10x.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private EscritorioRepository escritorioRepository;

    @Autowired
    UsuarioRepository repository;

    @Autowired
    private EscritorioStateCache escritorioStateCache;

    @Autowired
    private UsuarioStateCache usuarioStateCache;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity createUsuarioMaster(UsuarioRequestDTO data) {

        if(this.repository.findByUsuario(data.usuario()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encPass = new BCryptPasswordEncoder().encode(data.senha());
        Usuario users = new Usuario(data.idEscritorio(), data.nome(), data.usuario(), encPass, data.role());

        this.repository.save(users);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity createUsuario(UsuarioRequestDTO data, UserDetails userDetails) {

        Usuario userCriador = usuarioStateCache.getUserState(userDetails.getUsername());

        if(this.repository.findByUsuario(data.usuario()) != null) {
            return ResponseEntity.badRequest().build();
        }

        if (data.role().equals(UserRole.MASTER)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String encPass = new BCryptPasswordEncoder().encode(data.senha());
        Usuario users = new Usuario(userCriador.getIdEscritorio(), data.idDepartamento(), data.nome(), data.usuario(), encPass, data.role());

        this.repository.save(users);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity login(UsuarioResponseDTO data) {

        var userpw = new UsernamePasswordAuthenticationToken(data.usuario(), data.senha());
        var auth = this.authenticationManager.authenticate(userpw);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        usuarioStateCache.saveUserState(data.usuario(), (Usuario) auth.getPrincipal());

        if (((Usuario) auth.getPrincipal()).getRole() != UserRole.MASTER) {
            Escritorio escritorio = escritorioRepository.getReferenceById(((Usuario) auth.getPrincipal()).getIdEscritorio());
            escritorioStateCache.saveEscritorioState(escritorio.getId(), escritorio);
        }

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
