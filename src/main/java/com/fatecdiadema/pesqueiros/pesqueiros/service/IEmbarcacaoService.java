package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Embarcacao;
import com.fatecdiadema.pesqueiros.pesqueiros.model.EmbarcacaoDTO;

import java.util.List;
import java.util.Optional;

public interface IEmbarcacaoService {

    public Optional<Embarcacao> cadastrar(EmbarcacaoDTO embarcacao);

    public Optional<EmbarcacaoDTO> consultaPorId(Long id);

    public List<Embarcacao> consultaTodos();

    public EmbarcacaoDTO atualizar(Long id, EmbarcacaoDTO embarcacao);

    public void excluir(Long id);
}
