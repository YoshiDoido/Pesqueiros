package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Agendamento;
import com.fatecdiadema.pesqueiros.pesqueiros.model.IAgendamentoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService implements  IAgendamentoService {

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IAgendamentoRepository agendamentoRepository;

    public Optional<Agendamento> salvarAgendamento(Agendamento agendamento) {
        logger.info(">>>>>> servico salvar agendamento iniciado ");
        try {
            if (agendamento.getData() == null ||
                    agendamento.getHora() == null ||
                    agendamento.getLocal() == null || agendamento.getLocal().isBlank() ||
                    agendamento.getDescricao() == null || agendamento.getDescricao().isBlank() ||
                    agendamento.getProprietario() == null || agendamento.getProprietario().isEmpty() ||
                    agendamento.getEmbarcacao() == null || agendamento.getEmbarcacao().isEmpty() ||
                    agendamento.getCliente() == null || agendamento.getCliente().isEmpty()) {
                throw new IllegalArgumentException("Agendamento não pode ser salvo, campos obrigatórios não preenchidos");
            }
            return Optional.of(agendamentoRepository.save(agendamento));
        } catch (Exception e) {
            logger.error("Erro ao salvar agendamento: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Agendamento> consultaAgendamentoPorId(Long id) {
        logger.info(">>>>>> servico consulta por id chamado");
        return agendamentoRepository.findById(id);
    }

    public List<Agendamento> consultaTodosAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public void excluirAgendamento(Long id) {
        logger.info(">>>>>> servico excluir agendamento chamado");
        agendamentoRepository.deleteById(id);
    }
}