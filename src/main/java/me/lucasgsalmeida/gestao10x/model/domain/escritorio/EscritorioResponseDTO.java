package me.lucasgsalmeida.gestao10x.model.domain.escritorio;

public record EscritorioResponseDTO(Long id, String nome) {
    public EscritorioResponseDTO(Escritorio escritorio) {
        this(escritorio.getId(), escritorio.getNome());
    }
}
