package me.lucasgsalmeida.gestao10x.model.domain.projeto.enums;

public enum StatusProjeto {

    ATIVO("1"),
    PAUSADO("2"),
    ENCERRAMENTO("3"),
    ENCERRADO("5");

    private String status;

    StatusProjeto(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }



}
