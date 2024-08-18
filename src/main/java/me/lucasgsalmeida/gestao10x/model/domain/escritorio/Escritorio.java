package me.lucasgsalmeida.gestao10x.model.domain.escritorio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Escritorio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Escritorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Escritorio(EscritorioRequestDTO dto) {
        this.nome = dto.nome();
    }
}
