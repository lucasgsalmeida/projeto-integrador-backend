package me.lucasgsalmeida.gestao10x.service;

import me.lucasgsalmeida.gestao10x.infra.UsuarioStateCache;
import me.lucasgsalmeida.gestao10x.model.domain.departamento.Departamento;
import me.lucasgsalmeida.gestao10x.model.domain.departamento.DepartamentoRequestDTO;
import me.lucasgsalmeida.gestao10x.model.domain.departamento.DepartamentoResponseDTO;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.Usuario;
import me.lucasgsalmeida.gestao10x.model.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    @Autowired
    private UsuarioStateCache usuarioStateCache;

    public ResponseEntity createDepartamento(DepartamentoRequestDTO data, UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        if (data == null) {
            return ResponseEntity.badRequest().build();
        }

        Departamento departamento = new Departamento(data);
        departamento.setIdEscritorio(user.getIdEscritorio());

        repository.save(departamento);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity getDepartamentoById(Long id, UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());
        Departamento departamento = repository.findDepartamento(id, user.getIdEscritorio());
        DepartamentoResponseDTO dto = new DepartamentoResponseDTO(departamento);
        return ResponseEntity.ok(dto);

    }

    public ResponseEntity getAllDepartamento(UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());
        List<DepartamentoResponseDTO> departamentoResponseDTOList = repository.findDepartamentoByEscritorio(user.getIdEscritorio());
        return ResponseEntity.ok(departamentoResponseDTOList);
    }
}