package me.lucasgsalmeida.gestao10x.model.domain.projeto.enums;

public enum TipoServicoProjeto {

    TRAFEGO("1"),
    SOCIAL_MEDIA("2"),
    TRAFEGO_SOCIAL_MEDIA("3"),
    AUTOMACAO("4"),
    OUTROS("5");

    private String tipoServico;

    TipoServicoProjeto(String tipoServico) {
        this.tipoServico = tipoServico;
    }
    public String getTipoServico() {
        return this.tipoServico;
    }



}
