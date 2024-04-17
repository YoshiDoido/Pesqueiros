package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Embarcacao;
import com.fatecdiadema.pesqueiros.pesqueiros.model.EmbarcacaoDTO;
import com.fatecdiadema.pesqueiros.pesqueiros.model.IEmbarcacaoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmbarcacaoService  implements IEmbarcacaoService{

    Logger loger = LogManager.getLogger(this.getClass());

    @Autowired
    IEmbarcacaoRepository embarcacaoRepository;

    @Override
    public Optional<Embarcacao> cadastrar(EmbarcacaoDTO e) {
        loger.info(">>>>>> servico cadastrar embarcacao iniciado ");
        Embarcacao embarcacao = dtoParaEmbarcacao(e);
        return Optional.ofNullable(embarcacaoRepository.save(embarcacao));
    }

    @Override
    public Optional<EmbarcacaoDTO> consultaPorId(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<Embarcacao> consultaTodos() {
        // TODO Auto-generated method stub
        return embarcacaoRepository.findAll();
    }


    @Override
    public EmbarcacaoDTO atualizar(Long id, EmbarcacaoDTO embarcacao) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void excluir(Long id) {
        // TODO Auto-generated method stub
    }

    public Embarcacao dtoParaEmbarcacao(EmbarcacaoDTO e) {
        return new Embarcacao(e.proprietario(), e.nomeEmbarcacao(), e.arrais(), e.imagem(), e.capacidade());
    }
}
