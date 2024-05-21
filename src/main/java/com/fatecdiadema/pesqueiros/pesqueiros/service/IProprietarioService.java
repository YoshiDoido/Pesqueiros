package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Proprietario;

import java.util.List;
import java.util.Optional;

public interface IProprietarioService {

    public Optional<Proprietario> cadastrar(Proprietario proprietario);

    public Optional<Proprietario> consultaPorId(Long id);

    public List<Proprietario> consultaTodos();

    public Optional<Proprietario> atualizar(Long id, Proprietario proprietario);

    public void excluir(Long id);


}
