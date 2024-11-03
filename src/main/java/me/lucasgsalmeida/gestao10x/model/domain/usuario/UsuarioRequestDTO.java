package me.lucasgsalmeida.gestao10x.model.domain.usuario;

import java.util.List;

public record UsuarioRequestDTO(Long idEscritorio, List<Long> idDepartamentos, String nome, String usuario, String senha, UserRole role) {
}
