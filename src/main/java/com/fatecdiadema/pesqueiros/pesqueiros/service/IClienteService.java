package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Cliente;
import com.fatecdiadema.pesqueiros.pesqueiros.model.ClienteDTO;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    public Optional<Cliente> cadastrar(ClienteDTO cliente);

    public Optional<ClienteDTO> consultaPorId(Long id);

    public List<Cliente> consultaTodos();

    public ClienteDTO atualizar(Long id, ClienteDTO cliente);

    public void excluir(Long id);
}
