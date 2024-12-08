package me.lucasgsalmeida.gestao10x.model.domain.chat;

import java.time.LocalDateTime;

public record MensagemRequestDTO(String texto, LocalDateTime dataEnvio, Long idUsuarioRemetente, Long idUsuarioDestinatario) {
}
