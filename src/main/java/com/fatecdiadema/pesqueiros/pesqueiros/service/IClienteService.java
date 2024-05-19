package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    public Optional<Cliente> cadastrar(Cliente cliente);

    public Optional<Cliente> consultaPorId(Long id);

    public List<Cliente> consultaTodos();

    public Optional<Cliente> atualizar(Long id, Cliente cliente);

    public void excluir(Long id);
}
