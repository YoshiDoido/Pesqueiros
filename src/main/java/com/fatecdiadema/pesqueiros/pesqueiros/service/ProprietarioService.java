package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Proprietario;
import com.fatecdiadema.pesqueiros.pesqueiros.model.IProprietarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioService implements IProprietarioService{

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IProprietarioRepository proprietarioRepository;

    @Override
    public Optional<Proprietario> cadastrar(Proprietario p) {
        logger.info(">>>>>> servico cadastrar Proprietario iniciado ");
        return Optional.ofNullable(proprietarioRepository.save(p));
    }

    @Override
    public Optional<Proprietario> consultaPorId(Long id) {
        logger.info(">>>>>> servico consulta por id chamado");
        return proprietarioRepository.findById(id);
    }

    @Override
    public List<Proprietario> consultaTodos() {
        // TODO Auto-generated method stub
        return proprietarioRepository.findAll();
    }


    @Override
    public Optional<Proprietario> atualizar(Long id, Proprietario Proprietario) {
        logger.info(">>>>>> servico atualizar informacoes de Proprietario chamado");
        Proprietario proprietarioAtualizado = proprietarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proprietario não cadastrado"));
        proprietarioAtualizado.setNome(Proprietario.getNome());
        proprietarioAtualizado.setCpf(Proprietario.getCpf());
        proprietarioAtualizado.setEmail(Proprietario.getEmail());
        proprietarioAtualizado.setSenha(Proprietario.getSenha());
        proprietarioAtualizado.setNrTelefone(Proprietario.getNrTelefone());
        return Optional.ofNullable(proprietarioRepository.save(proprietarioAtualizado));
    }

    @Override
    public void excluir(Long id) {
        proprietarioRepository.deleteById(id);
    }

}
