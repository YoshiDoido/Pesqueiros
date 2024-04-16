package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Cliente;
import com.fatecdiadema.pesqueiros.pesqueiros.model.ClienteDTO;
import com.fatecdiadema.pesqueiros.pesqueiros.model.IClienteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> cadastrar(ClienteDTO c) {
        logger.info(">>>>>> servico cadastrar cliente iniciado ");
        Cliente cliente = dtoParaCliente(c);
        return Optional.ofNullable(clienteRepository.save(cliente));
    }

    @Override
    public Optional<ClienteDTO> consultaPorId(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<Cliente> consultaTodos() {
        // TODO Auto-generated method stub
        return clienteRepository.findAll();
    }

    @Override
    public ClienteDTO atualizar(Long id, ClienteDTO cliente) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void excluir(Long id) {
        // TODO Auto-generated method stub

    }

    public Cliente dtoParaCliente(ClienteDTO c) {
        return new Cliente(c.nome(), c.cpf(),c.email(), c.senha(),c.nrTelefone());
    }
}
