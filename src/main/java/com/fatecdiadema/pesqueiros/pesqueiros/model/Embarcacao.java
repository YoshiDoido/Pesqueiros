package com.fatecdiadema.pesqueiros.pesqueiros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Embarcacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String proprietario;
    private String nomeEmbarcacao;
    private String arrais;
    private String imagem;
    private Integer capacidade;

    public Embarcacao(String proprietario, String nomeEmbarcacao, String arrais, String imagem, Integer capacidade) {
        this.proprietario = proprietario;
        this.nomeEmbarcacao = nomeEmbarcacao;
        this.arrais = arrais;
        this.imagem = imagem;
        this.capacidade = capacidade;
    }

    public Embarcacao() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        if (proprietario == null || proprietario.isBlank()) {
            throw new IllegalArgumentException("O proprietário não deve estar em branco");
        } else {
            this.proprietario = proprietario;
        }
    }

    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        if (nomeEmbarcacao == null || nomeEmbarcacao.isBlank()) {
            throw new IllegalArgumentException("O nome da embarcação não deve estar em branco");
        } else {
            this.nomeEmbarcacao = nomeEmbarcacao;
        }
    }

    public String getArrais() {
        return arrais;
    }

    public void setArrais(String arrais) {
        if (arrais == null || arrais.isBlank()) {
            throw new IllegalArgumentException("O número do arrais não deve estar em branco");
        } else {
            this.arrais = arrais;
        }
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        if (imagem == null || imagem.isBlank()) {
            throw new IllegalArgumentException("A imagem não deve estar em branco");
        } else {
            this.imagem = imagem;
        }
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que zero");
        } else {
            this.capacidade = capacidade;
        }
    }

}
