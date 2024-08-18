package me.lucasgsalmeida.gestao10x.model.domain.projeto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.enums.PrioridadeProjeto;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.enums.StatusProjeto;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.enums.TipoServicoProjeto;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.responsavel_departamento_projeto.ResponsavelDepartamentoProjeto;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Projeto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "Escritorio", name = "id_escritorio", referencedColumnName = "id")
    private Long idEscritorio;

    private String nome;

    private StatusProjeto status;
    private PrioridadeProjeto prioridade;
    private TipoServicoProjeto tipoServicoProjeto;

    @OneToMany
    private List<ResponsavelDepartamentoProjeto> responsavelDepartamentoProjetos;

    private Date dataInicio;

    private String orcamentoMensal;

    private String observacao;

    public Projeto(ProjetoRequestDTO dto) {
        this.nome = dto.nome();
        this.status = dto.status();
        this.prioridade = dto.prioridade();
        this.tipoServicoProjeto = dto.tipoServico();
        this.responsavelDepartamentoProjetos = dto.rdp();
        this.dataInicio = dto.dataInicio();
        this.orcamentoMensal = dto.orcamentoMensal();
        this.observacao = dto.observacao();
    }
}
