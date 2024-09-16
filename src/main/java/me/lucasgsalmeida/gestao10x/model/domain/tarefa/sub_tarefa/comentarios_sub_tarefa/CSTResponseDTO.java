package me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.comentarios_sub_tarefa;

public record CSTResponseDTO(Long id, Long idUsuario, String mensagem) {

    public CSTResponseDTO(ComentarioSubTarefa data) {
        this(data.getId(), data.getIdUsuario(), data.getMensagem());
    }
}
