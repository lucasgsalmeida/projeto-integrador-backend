package me.lucasgsalmeida.gestao10x.service;

import me.lucasgsalmeida.gestao10x.infra.EscritorioStateCache;
import me.lucasgsalmeida.gestao10x.infra.UsuarioStateCache;
import me.lucasgsalmeida.gestao10x.model.domain.escritorio.Escritorio;
import me.lucasgsalmeida.gestao10x.model.domain.escritorio.EscritorioResponseDTO;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.UsuarioResponseDTO;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.*;
import me.lucasgsalmeida.gestao10x.model.repository.EscritorioRepository;
import me.lucasgsalmeida.gestao10x.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public ResponseEntity<?> getUsuarioAndCliente(UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());
        Escritorio escritorio = escritorioRepository.getById(user.getIdEscritorio());

        user.setSenha("**");
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(user);
        EscritorioResponseDTO escritorioResponseDTO = new EscritorioResponseDTO(escritorio);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("usuario", responseDTO);
        responseMap.put("cliente", escritorioResponseDTO);

        return ResponseEntity.ok(responseMap);
    }

    public ResponseEntity<String> verifyToken(String authorizationHeader) {
        String token = extractToken(authorizationHeader);

        try {
            if (token != null && tokenService.validateToken(token)) {
                Usuario user = (Usuario) repository.findByUsuario(tokenService.extractUsername(token));
                usuarioStateCache.saveUserState(user.getUsuario(), user);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    public ResponseEntity getAllUsuario(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());
        List<UsuarioAbstractDTO> usuarioResponseDTOList = repository.findUsuarioByEscritorio(user.getIdEscritorio());
        return ResponseEntity.ok(usuarioResponseDTOList);
    }
}
