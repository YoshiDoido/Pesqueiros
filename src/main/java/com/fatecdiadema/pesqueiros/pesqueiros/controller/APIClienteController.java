package com.fatecdiadema.pesqueiros.pesqueiros.controller;


import com.fatecdiadema.pesqueiros.pesqueiros.model.Cliente;
import com.fatecdiadema.pesqueiros.pesqueiros.service.IClienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/pesqueiros/clientes")
public class APIClienteController {

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IClienteService clienteService;

    record ClienteDTO(String nome, String cpf, String email, String senha, String nrTelefone) {
    }

    @GetMapping
    public ResponseEntity<Object> consultaTodos() {
        logger.info(">>>>>> apicontroller consulta todos");
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.consultaTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultaPorId(@PathVariable("id") Long id) {
        logger.info(">>>>>> apicontroller consulta por id");
        Optional<Cliente> cliente = clienteService.consultaPorId(id);

        if(cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id de cliente não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarCliente(@RequestBody ClienteDTO c) {
        logger.info(">>>>>> apicontroller cadastrar cliente iniciado ");
        try {

            Cliente clienteNovo = new Cliente();
            clienteNovo.setNome(c.nome());
            clienteNovo.setCpf(c.cpf());
            clienteNovo.setEmail(c.email());
            clienteNovo.setSenha(c.senha());
            clienteNovo.setNrTelefone(c.nrTelefone());
            Optional<Cliente> cliente = clienteService.cadastrar(clienteNovo);

            if (cliente.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(cliente.get());
            } else {
                logger.info(">>>>>> api cliente controller cadastrar exception =>");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro não esperado ");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCliente(@PathVariable("id") Long clienteId, @RequestBody Cliente clienteAtualizado) {
        logger.info(">>>>>> apicontroller atualizar informacoes de cliente iniciado ");
        try {
            Cliente cliente = clienteService.atualizar(clienteId, clienteAtualizado).get();
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> excluirProduto(@PathVariable Long id) {
        logger.info(">>>>>> apicontroller excluir por id chamado");
        Optional<Cliente> cliente = clienteService.consultaPorId(id);
        if (cliente.isEmpty()) {
            logger.info(">>>>>> apicontroller id not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
        } else {
            clienteService.excluir(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }



}
