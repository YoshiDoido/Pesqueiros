package com.fatecdiadema.pesqueiros.pesqueiros.controller;


import com.fatecdiadema.pesqueiros.pesqueiros.model.Cliente;
import com.fatecdiadema.pesqueiros.pesqueiros.model.ClienteDTO;
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

    @GetMapping
    public ResponseEntity<Object> consultaTodos() {
        logger.info(">>>>>> apicontroller consulta todos");
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.consultaTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteDTO> consultaPorId(@PathVariable("id") Long id) {
        logger.info(">>>>>> apicontroller consulta por id");
        ClienteDTO c = clienteService.consultaPorId(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.consultaPorId(id).get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarCliente(@RequestBody ClienteDTO c) {
        logger.info(">>>>>> apicontroller cadastrar cliente iniciado ");
        try {
            Optional<Cliente> cliente = clienteService.cadastrar(c);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }




}
