package me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa.departamento_ordem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DepartamentoOrdem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoOrdem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "Escritorio", name = "id_escritorio", referencedColumnName = "id")
    private Long idEscritorio;

    @JoinColumn(table = "Departamento", name = "id_departamento", referencedColumnName = "id")
    private Long idDepartamento;

    private int ordem;

    private int qtdDias;

    public DepartamentoOrdem(DORequestDTO data) {
        this.idDepartamento = data.idDepartamento();
        this.ordem = data.ordem();
        this.qtdDias = data.qtdDias();
    }


}

