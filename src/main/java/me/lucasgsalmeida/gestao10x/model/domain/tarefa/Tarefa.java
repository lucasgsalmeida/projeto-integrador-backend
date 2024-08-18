package me.lucasgsalmeida.gestao10x.model.domain.tarefa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Tarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "Escritorio", name = "id_escritorio", referencedColumnName = "id")
    private Long idEscritorio;

    @JoinColumn(table = "Projeto", name = "id_projeto", referencedColumnName = "id")
    private Long idProjeto;

    @JoinColumn(table = "TipoTarefa", name = "id_tipoTarefa", referencedColumnName = "id")
    private Long id_tipoTarefa;


}
