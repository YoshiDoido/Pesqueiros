package com.fatecdiadema.pesqueiros.pesqueiros.service;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Proprietario;
import com.fatecdiadema.pesqueiros.pesqueiros.model.ProprietarioDTO;

import java.util.List;
import java.util.Optional;

public interface IProprietarioService {

    public Optional<Proprietario> cadastrar(ProprietarioDTO proprietario);

    public Optional<ProprietarioDTO> consultaPorId(Long id);

    public List<Proprietario> consultaTodos();

    public ProprietarioDTO atualizar(Long id, ProprietarioDTO proprietario);

    public void excluir(Long id);


}
