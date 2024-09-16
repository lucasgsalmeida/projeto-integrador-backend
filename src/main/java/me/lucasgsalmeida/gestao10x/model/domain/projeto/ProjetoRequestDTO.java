package me.lucasgsalmeida.gestao10x.model.domain.projeto;

import me.lucasgsalmeida.gestao10x.model.domain.projeto.enums.PrioridadeProjeto;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.enums.StatusProjeto;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.enums.TipoServicoProjeto;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.responsavel_departamento_projeto.ResponsavelDepartamentoProjeto;

import java.sql.Date;
import java.util.List;

public record ProjetoRequestDTO(String nome, StatusProjeto status, PrioridadeProjeto prioridade, TipoServicoProjeto tipoServico, List<ResponsavelDepartamentoProjeto> rdp, Date dataInicio, String orcamentoMensal, String observacao) {
}
