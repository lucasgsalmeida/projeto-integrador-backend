package me.lucasgsalmeida.gestao10x.model.domain.chat;

import java.time.LocalDateTime;

public record MensagemResponseDTO(Long id, String texto, LocalDateTime dataEnvio, Long idUsuarioRemetente, Long idUsuarioDestinatario) {

    public MensagemResponseDTO(Mensagem msg) {
        this(msg.getId(), msg.getTexto(), msg.getDataEnvio(), msg.getIdUsuarioRemetente(), msg.getIdUsuarioDestinatario());
    }
}
