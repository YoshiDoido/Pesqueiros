package com.fatecdiadema.pesqueiros.pesqueiros.model;

public record EmbarcacaoDTO (
    String proprietario,
    String nomeEmbarcacao,
    String arrais,
    String imagem,
    Integer capacidade
    ) {
}
