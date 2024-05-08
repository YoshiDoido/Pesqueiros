package com.fatecdiadema.pesqueiros.pesqueiros.controller;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Proprietario;
import com.fatecdiadema.pesqueiros.pesqueiros.model.ProprietarioDTO;
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
@RequestMapping("/pesqueiros/proprietarios")
public class APIProprietarioController {

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IProprietarioService proprietarioService;

    @GetMapping
    public ResponseEntity<Object> consultaTodos() {
        logger.info(">>>>>> apicontroller consulta todos");
        return ResponseEntity.status(HttpStatus.OK).body(proprietarioService.consultaTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProprietarioDTO> consultaPorId(@PathVariable("id") Long id) {
        logger.info(">>>>>> apicontroller consulta por id");
        ProprietarioDTO c = proprietarioService.consultaPorId(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(proprietarioService.consultaPorId(id).get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarProprietario(@RequestBody ProprietarioDTO c) {
        logger.info(">>>>>> apicontroller cadastrar proprietario iniciado ");
        try {
            Optional<Proprietario> proprietario = proprietarioService.cadastrar(c);
            return ResponseEntity.status(HttpStatus.CREATED).body(proprietario.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
