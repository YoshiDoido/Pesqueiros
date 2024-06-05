package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.exception.ClienteExceptionHandler;
import com.fatecdiadema.pesqueiros.pesqueiros.model.Cliente;
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
    public Optional<Cliente> cadastrar(Cliente c) {
        logger.info(">>>>>> servico cadastrar cliente iniciado ");
        if (c.getNome() == null || c.getNome().isBlank() ||
        c.getCpf() == null || c.getCpf().isBlank() ||
        c.getEmail() == null || c.getEmail().isBlank() ||
        c.getSenha() == null || c.getSenha().isBlank() ||
        c.getNrTelefone() == null || c.getNrTelefone().isBlank()) {
            throw new ClienteExceptionHandler("Campo digitado é inválido");
        }
        Cliente clienteCriado = clienteRepository.save(c);
        return Optional.ofNullable(clienteCriado);
    }

    @Override
    public Optional<Cliente> consultaPorId(Long id) {
        logger.info(">>>>>> servico consulta por id chamado");
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> consultaTodos() {
        // TODO Auto-generated method stub
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> atualizar(Long id, Cliente cliente) {
        logger.info(">>>>>> servico atualizar informacoes de cliente chamado");
        Cliente clienteAtualizado = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não cadastrado"));
        clienteAtualizado.setNome(cliente.getNome());
        clienteAtualizado.setCpf(cliente.getCpf());
        clienteAtualizado.setEmail(cliente.getEmail());
        clienteAtualizado.setSenha(cliente.getSenha());
        clienteAtualizado.setNrTelefone(cliente.getNrTelefone());
        return Optional.ofNullable(clienteRepository.save(clienteAtualizado));
    }

    @Override
    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }

    // Registro do cliente
    public Optional<Cliente> registrar(Cliente cliente) {
        logger.info(">>>>>> servico registrar cliente iniciado ");
        return Optional.ofNullable(clienteRepository.save(cliente));
    }


    // Login do Cliente - somente email e senha para validar
    public Optional<Cliente> login(String email, String senha) {
        logger.info(">>>>>> servico login cliente iniciado ");
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if (cliente.isPresent() && cliente.get().getSenha().equals(senha)) {
            return cliente;
        }
        return Optional.empty();

    }
}
