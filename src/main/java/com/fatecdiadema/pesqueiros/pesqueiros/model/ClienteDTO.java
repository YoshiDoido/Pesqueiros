package com.fatecdiadema.pesqueiros.pesqueiros.model;

public record ClienteDTO (
    String nome,
    String cpf,
    String email,
    String senha,
    String nrTelefone
    ) {

}