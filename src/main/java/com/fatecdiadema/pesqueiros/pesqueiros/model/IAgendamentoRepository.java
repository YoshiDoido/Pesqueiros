package com.fatecdiadema.pesqueiros.pesqueiros.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgendamentoRepository extends JpaRepository<Agendamento, Long> {

}
