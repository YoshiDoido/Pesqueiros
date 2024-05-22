package com.fatecdiadema.pesqueiros.pesqueiros.controller;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Cliente;
import com.fatecdiadema.pesqueiros.pesqueiros.model.Proprietario;
import com.fatecdiadema.pesqueiros.pesqueiros.service.IProprietarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
// Rota para o proprietario:
// localhost:8080/pesqueiros/proprietarios
@RequestMapping("/pesqueiros/proprietarios")
public class APIProprietarioController {

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IProprietarioService proprietarioService;

    record ProprietarioDTO(String nome, String cpf, String email, String senha, String nrTelefone) {
    }

    @GetMapping
    public ResponseEntity<Object> consultaTodos() {
        logger.info(">>>>>> apicontroller consulta todos");
        return ResponseEntity.status(HttpStatus.OK).body(proprietarioService.consultaTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultaPorId(@PathVariable("id") Long id) {
        logger.info(">>>>>> apicontroller consulta por id");
        Optional<Proprietario> proprietario = proprietarioService.consultaPorId(id);

        if(proprietario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id de cliente não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(proprietario.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarProprietario(@RequestBody APIClienteController.ClienteDTO p) {
        logger.info(">>>>>> apicontroller cadastrar cliente iniciado ");
        try {

            Proprietario proprietarioNovo = new Proprietario();
            proprietarioNovo.setNome(p.nome());
            proprietarioNovo.setCpf(p.cpf());
            proprietarioNovo.setEmail(p.email());
            proprietarioNovo.setSenha(p.senha());
            proprietarioNovo.setNrTelefone(p.nrTelefone());
            Optional<Proprietario> proprietario = proprietarioService.cadastrar(proprietarioNovo);

            if (proprietario.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(proprietario.get());
            } else {
                logger.info(">>>>>> api proprietario controller cadastrar exception =>");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro não esperado ");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProprietario(@PathVariable("id") Long proprietarioId, @RequestBody Proprietario proprietarioAtualizado) {
        logger.info(">>>>>> apicontroller atualizar informacoes de cliente iniciado ");
        try {
            Proprietario proprietario = proprietarioService.atualizar(proprietarioId, proprietarioAtualizado).get();
            return ResponseEntity.ok(proprietario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> excluirProprietario(@PathVariable Long id) {
        logger.info(">>>>>> apicontroller excluir por id chamado");
        Optional<Proprietario> proprietario = proprietarioService.consultaPorId(id);
        if (proprietario.isEmpty()) {
            logger.info(">>>>>> apicontroller id not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
        } else {
            proprietarioService.excluir(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
