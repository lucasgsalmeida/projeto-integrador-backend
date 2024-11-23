package me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums;

public enum StatusTarefa {

    CONCLUIDO("1"),
    FAZENDO("2"),
    PARA_FAZER("3"),
    APROVACAO("4");

    private String status;

    StatusTarefa(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }

}
