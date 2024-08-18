package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.projeto.responsavel_departamento_projeto.ResponsavelDepartamentoProjeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RDPRepository extends JpaRepository<ResponsavelDepartamentoProjeto, Long> {
}
