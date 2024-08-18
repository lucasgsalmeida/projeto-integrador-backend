package me.lucasgsalmeida.gestao10x.model.domain.departamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Departamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "Escritorio", name = "id_escritorio", referencedColumnName = "id")
    private Long idEscritorio;

    @JoinColumn(table = "Usuario", name = "id_usuario", referencedColumnName = "id")
    private Long idUsuario;

    private String nome;

    public Departamento(DepartamentoRequestDTO dto) {
        this.idUsuario = dto.idUsuario();
        this.nome = dto.nome();
    }


}
