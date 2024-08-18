package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.escritorio.Escritorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscritorioRepository extends JpaRepository<Escritorio, Long> {
}
