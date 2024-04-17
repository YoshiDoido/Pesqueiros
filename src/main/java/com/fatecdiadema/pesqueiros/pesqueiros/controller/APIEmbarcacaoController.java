package com.fatecdiadema.pesqueiros.pesqueiros.controller;


import com.fatecdiadema.pesqueiros.pesqueiros.model.Embarcacao;
import com.fatecdiadema.pesqueiros.pesqueiros.model.EmbarcacaoDTO;
import com.fatecdiadema.pesqueiros.pesqueiros.service.IEmbarcacaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/pesqueiros/embarcacoes")
public class APIEmbarcacaoController {

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IEmbarcacaoService embarcacaoService;

    @GetMapping
    public ResponseEntity<Object> consultaTodos() {
        logger.info(">>>>>> apicontroller consulta todos");
        return ResponseEntity.status(HttpStatus.OK).body(embarcacaoService.consultaTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> consultaPorId(Long id) {
        logger.info(">>>>>> apicontroller consulta por id");
        EmbarcacaoDTO e = embarcacaoService.consultaPorId(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(embarcacaoService.consultaPorId(id).get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarEmbarcacao(@RequestBody EmbarcacaoDTO e) {
        logger.info(">>>>>> apicontroller cadastrar embarcacao iniciado ");
        try {
            Optional<Embarcacao> embarcacao = embarcacaoService.cadastrar(e);
            return ResponseEntity.status(HttpStatus.CREATED).body(embarcacao.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}