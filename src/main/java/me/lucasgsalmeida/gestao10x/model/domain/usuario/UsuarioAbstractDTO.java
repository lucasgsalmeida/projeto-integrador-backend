package me.lucasgsalmeida.gestao10x.model.domain.usuario;

import me.lucasgsalmeida.gestao10x.model.domain.departamento.DepartamentoResponseDTO;

import java.util.List;

public record UsuarioAbstractDTO(Long id, Long idEscritorio, List<DepartamentoResponseDTO> departamentos, String nome, String usuario, UserRole role) {

    public UsuarioAbstractDTO(Usuario user, List<DepartamentoResponseDTO> departamentos) {
        this(user.getId(), user.getIdEscritorio(), departamentos, user.getNome(), user.getUsuario(), user.getRole());
    }
}
