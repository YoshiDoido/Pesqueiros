package com.fatecdiadema.pesqueiros.pesqueiros.controller;


import com.fatecdiadema.pesqueiros.pesqueiros.model.Agendamento;
import com.fatecdiadema.pesqueiros.pesqueiros.model.Cliente;
import com.fatecdiadema.pesqueiros.pesqueiros.model.Embarcacao;
import com.fatecdiadema.pesqueiros.pesqueiros.model.Proprietario;
import com.fatecdiadema.pesqueiros.pesqueiros.service.IAgendamentoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
// Rota para o agendamento:
// localhost:8080/pesqueiros/agendamento
@RequestMapping("/pesqueiros/agendamento")
public class APIAgendamentoController {

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IAgendamentoService agendamentoService;

    record AgendamentoDTO(String data, String hora, String local, String descricao, List<Proprietario> proprietario, List<Embarcacao> embarcacao, List<Cliente> cliente) {
    }

    @GetMapping
    public ResponseEntity<Object> consultaTodosAgendamentos() {
        logger.info(">>>>>> apicontroller consulta todos agendamentos");
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.consultaTodosAgendamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultaAgendamentoPorId(@PathVariable("id") Long id) {
        logger.info(">>>>>> apicontroller consulta por id agendamento");
        Optional<Agendamento> agendamento = agendamentoService.consultaAgendamentoPorId(id);

        if (agendamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id de agendamento não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(agendamento.get());
    }

    @PostMapping
    public ResponseEntity<Object> salvarAgendamento(@RequestBody AgendamentoDTO a) {
        logger.info(">>>>>> apicontroller salvar agendamento iniciado ");
        try {

            Agendamento agendamentoNovo = new Agendamento();
            agendamentoNovo.setData(a.data());
            agendamentoNovo.setHora(LocalTime.parse(a.hora()));  // Parse the string to a LocalTime
            agendamentoNovo.setLocal(a.local());
            agendamentoNovo.setDescricao(a.descricao());
            agendamentoNovo.setProprietario(a.proprietario());
            agendamentoNovo.setEmbarcacao(a.embarcacao());
            agendamentoNovo.setCliente(a.cliente());
            Optional<Agendamento> agendamento = agendamentoService.salvarAgendamento(agendamentoNovo);

            if (agendamento.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(agendamento.get());
            } else {
                logger.error(">>>>>> api agendamento controller salvarAgendamento exception =>");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro não esperado ");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirAgendamento(@PathVariable("id") Long id) {
        logger.info(">>>>>> apicontroller excluir agendamento");
        Optional<Agendamento> agendamento = agendamentoService.consultaAgendamentoPorId(id);

        if (agendamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id de agendamento não encontrado");
        } else {
            agendamentoService.excluirAgendamento(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
