package me.lucasgsalmeida.gestao10x.model.domain.tarefa.tipo_tarefa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.responsavel_departamento_projeto.ResponsavelDepartamentoProjeto;

import java.util.List;

@Entity
@Table(name = "TipoTarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoTarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "Escritorio", name = "id_escritorio", referencedColumnName = "id")
    private Long idEscritorio;

    @OneToMany
    private List<ResponsavelDepartamentoProjeto> responsavelDepartamentoProjetos;

    private String descricao;

    public TipoTarefa(TipoTarefaRequestDTO dto){
        this.responsavelDepartamentoProjetos = dto.responsavelDepartamentoProjetos();
        this.descricao = dto.descricao();
    }

}