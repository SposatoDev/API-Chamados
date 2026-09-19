package com.faculdade.chamados.repository;

import com.faculdade.chamados.model.chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface chamadoRepository extends JpaRepository<chamado, Integer> {
}