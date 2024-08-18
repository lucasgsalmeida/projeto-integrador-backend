package me.lucasgsalmeida.gestao10x.model.domain.projeto.responsavel_departamento_projeto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ResponsavelDepartamentoProjeto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsavelDepartamentoProjeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "Escritorio", name = "id_escritorio", referencedColumnName = "id")
    private Long idEscritorio;

    @JoinColumn(table = "Departamento", name = "id_departamento", referencedColumnName = "id")
    private Long idDepartamento;

    @JoinColumn(table = "Usuario", name = "id_usuario", referencedColumnName = "id")
    private Long idUsuario;

    public ResponsavelDepartamentoProjeto(RDPRequestDTO dto) {
        this.idDepartamento = dto.idDepartamento();
        this.idUsuario = dto.idUsuario();
    }
}
