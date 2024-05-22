package com.fatecdiadema.pesqueiros.pesqueiros.controller;


import com.fatecdiadema.pesqueiros.pesqueiros.model.Cliente;
import com.fatecdiadema.pesqueiros.pesqueiros.model.Embarcacao;
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
// Rota para a embarcação:
// localhost:8080/pesqueiros/embarcacoes
@RequestMapping("/pesqueiros/embarcacoes")
public class APIEmbarcacaoController {

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IEmbarcacaoService embarcacaoService;

    record EmbarcacaoDTO(String proprietario, String nomeEmbarcacao, String arrais, String imagem, Integer capacidade) {
    }

    @GetMapping
    public ResponseEntity<Object> consultaTodos() {
        logger.info(">>>>>> apicontroller consulta todos");
        return ResponseEntity.status(HttpStatus.OK).body(embarcacaoService.consultaTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultaPorId(@PathVariable("id") Long id) {
        logger.info(">>>>>> apicontroller consulta por id");
        Optional<Embarcacao> embarcacao = embarcacaoService.consultaPorId(id);

        if(embarcacao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id de embarcação não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(embarcacao.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarEmbarcacao(@RequestBody EmbarcacaoDTO emb) {
        logger.info(">>>>>> apicontroller cadastrar embarcacao iniciado ");
        try {

            Embarcacao embarcacaoNova = new Embarcacao();
            embarcacaoNova.setProprietario(emb.proprietario());
            embarcacaoNova.setNomeEmbarcacao(emb.nomeEmbarcacao());
            embarcacaoNova.setArrais(emb.arrais());
            embarcacaoNova.setImagem(emb.imagem());
            embarcacaoNova.setCapacidade(emb.capacidade());
            Optional<Embarcacao> embarcacao = embarcacaoService.cadastrar(embarcacaoNova);

            if (embarcacao.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(embarcacao.get());
            } else {
                logger.info(">>>>>> api embarcacao controller cadastrar exception =>");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro não esperado ");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizarEmbarcacao(@PathVariable("id") Long embarcacaoId, @RequestBody Embarcacao embarcacaoAtualizada) {
        logger.info(">>>>>> apicontroller atualizar informacoes de embarcacao iniciado ");
        try {
            Embarcacao embarcacao = embarcacaoService.atualizar(embarcacaoId, embarcacaoAtualizada).get();
            return ResponseEntity.ok(embarcacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> excluirEmbarcacao(@PathVariable Long id) {
        logger.info(">>>>>> apicontroller exluir por id chamado");
        Optional<Embarcacao> embarcacao = embarcacaoService.consultaPorId(id);
        if (embarcacao.isEmpty()) {
            logger.info(">>>>>> apicontroller id not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
        }
        embarcacaoService.excluir(id);
        return ResponseEntity.status(HttpStatus.OK).body("Embarcação excluída com sucesso");
    }
}