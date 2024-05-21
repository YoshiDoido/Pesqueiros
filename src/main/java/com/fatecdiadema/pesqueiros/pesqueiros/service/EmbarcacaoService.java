package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Embarcacao;
import com.fatecdiadema.pesqueiros.pesqueiros.model.IEmbarcacaoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmbarcacaoService  implements IEmbarcacaoService{

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IEmbarcacaoRepository embarcacaoRepository;

    @Override
    public Optional<Embarcacao> cadastrar(Embarcacao c) {
        logger.info(">>>>>> servico cadastrar Embarcacao iniciado ");
        return Optional.ofNullable(embarcacaoRepository.save(c));
    }

    @Override
    public Optional<Embarcacao> consultaPorId(Long id) {
        logger.info(">>>>>> servico consulta por id chamado");
        return embarcacaoRepository.findById(id);
    }

    @Override
    public List<Embarcacao> consultaTodos() {
        // TODO Auto-generated method stub
        return embarcacaoRepository.findAll();
    }

    @Override
    public Optional<Embarcacao> atualizar(Long id, Embarcacao embarcacao) {
        logger.info(">>>>>> servico atualizar informacoes de Embarcacao chamado");
        Embarcacao embarcacaoAtualizado = embarcacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Embarcacao não cadastrada"));
        embarcacaoAtualizado.setProprietario(embarcacao.getProprietario());
        embarcacaoAtualizado.setNomeEmbarcacao(embarcacao.getNomeEmbarcacao());
        embarcacaoAtualizado.setArrais(embarcacao.getArrais());
        embarcacaoAtualizado.setImagem(embarcacao.getImagem());
        embarcacaoAtualizado.setCapacidade(embarcacao.getCapacidade());

        return Optional.ofNullable(embarcacaoRepository.save(embarcacaoAtualizado));
    }

    @Override
    public void excluir(Long id) {
        embarcacaoRepository.deleteById(id);
    }
}
