package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Embarcacao;

import java.util.List;
import java.util.Optional;

public interface IEmbarcacaoService {

    public Optional<Embarcacao> cadastrar(Embarcacao embarcacao);

    public Optional<Embarcacao> consultaPorId(Long id);

    public List<Embarcacao> consultaTodos();

    public Optional<Embarcacao> atualizar(Long id, Embarcacao embarcacao);

    public void excluir(Long id);
}
