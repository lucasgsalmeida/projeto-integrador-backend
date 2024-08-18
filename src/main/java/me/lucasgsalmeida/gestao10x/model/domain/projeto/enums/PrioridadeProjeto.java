package me.lucasgsalmeida.gestao10x.model.domain.projeto.enums;

public enum PrioridadeProjeto {

    MUITO_ALTO("1"),
    ALTO("2"),
    MEDIO("3"),
    BAIXO("4"),
    MUITO_BAIXO("5");

    private String prioridade;

    PrioridadeProjeto(String prioridade) {
        this.prioridade = prioridade;
    }
    public String getPrioridade() {
        return this.prioridade;
    }
}
