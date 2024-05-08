package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Proprietario;
import com.fatecdiadema.pesqueiros.pesqueiros.model.ProprietarioDTO;
import com.fatecdiadema.pesqueiros.pesqueiros.model.IProprietarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioService implements IProprietarioService{

    Logger loger = LogManager.getLogger(this.getClass());

    @Autowired
    IProprietarioRepository proprietarioRepository;

    @Override
    public Optional<Proprietario> cadastrar(ProprietarioDTO p) {
        loger.info(">>>>>> servico cadastrar Proprietario iniciado ");
        Proprietario Proprietario = dtoParaProprietario(p);
        return Optional.ofNullable(proprietarioRepository.save(Proprietario));
    }

    @Override
    public Optional<ProprietarioDTO> consultaPorId(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<Proprietario> consultaTodos() {
        // TODO Auto-generated method stub
        return proprietarioRepository.findAll();
    }


    @Override
    public ProprietarioDTO atualizar(Long id, ProprietarioDTO Proprietario) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void excluir(Long id) {
        // TODO Auto-generated method stub
    }

    public Proprietario dtoParaProprietario(ProprietarioDTO p) {
        return new Proprietario(null, p.nome(), p.cpf(), p.email(), p.senha(), p.nrTelefone());
    }
}
