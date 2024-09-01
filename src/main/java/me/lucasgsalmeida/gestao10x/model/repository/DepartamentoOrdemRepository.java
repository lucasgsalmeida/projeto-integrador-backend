package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa.departamento_ordem.DepartamentoOrdem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoOrdemRepository extends JpaRepository<DepartamentoOrdem, Long> {
}
