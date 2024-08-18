package me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums;

public enum PrioridadeTarefa {

    MUITO_ALTO("1"),
    ALTO("2"),
    MEDIO("3"),
    BAIXO("4"),
    MUITO_BAIXO("5");

    private String prioridade;

    PrioridadeTarefa(String prioridade) {
        this.prioridade = prioridade;
    }
    public String getPrioridade() {
        return this.prioridade;
    }

}
