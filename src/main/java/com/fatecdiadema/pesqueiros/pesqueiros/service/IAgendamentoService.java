package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Agendamento;

import java.util.List;
import java.util.Optional;

public interface IAgendamentoService {

    Optional<Agendamento> salvarAgendamento(Agendamento agendamento);

    Optional<Agendamento> consultaAgendamentoPorId(Long id);

    List<Agendamento> consultaTodosAgendamentos();

    void excluirAgendamento(Long id);
}
