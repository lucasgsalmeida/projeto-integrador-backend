package me.lucasgsalmeida.gestao10x.model.domain.usuario;

import me.lucasgsalmeida.gestao10x.model.domain.departamento.DepartamentoResponseDTO;

import java.util.List;

public record UsuarioResponseDTO(Long id, Long idEscritorio, List<DepartamentoResponseDTO> departamentos, String nome, String usuario, String senha, UserRole role) {

    public UsuarioResponseDTO(Usuario user, List<DepartamentoResponseDTO> departamentos) {
        this(user.getId(), user.getIdEscritorio(), departamentos, user.getNome(), user.getUsuario(), user.getSenha(), user.getRole());
    }
}
