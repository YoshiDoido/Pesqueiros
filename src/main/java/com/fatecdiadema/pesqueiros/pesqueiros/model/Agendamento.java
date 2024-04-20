package com.fatecdiadema.pesqueiros.pesqueiros.model;

import jakarta.persistence.*;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Proprietario;
import com.fatecdiadema.pesqueiros.pesqueiros.model.Embarcacao;
import com.fatecdiadema.pesqueiros.pesqueiros.model.Cliente;

import java.util.List;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String data;
    private String hora;
    private String local;
    @ManyToMany
    private List<Proprietario> proprietario;
    @ManyToMany
    private List<Embarcacao> embarcacao;
    @ManyToMany
    private List<Cliente> cliente;

    public Agendamento(Long id, String data, String hora, String local, List<Embarcacao> embarcacao, List<Cliente> cliente, List<Proprietario> proprietario) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.embarcacao = embarcacao;
        this.cliente = cliente;
        this.proprietario = proprietario;
    }

    public Agendamento() {

    }

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("A data não deve estar em branco");
        } else {
            this.data = data;
        }
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        if (hora == null || hora.isBlank()) {
            throw new IllegalArgumentException("A hora não deve estar em branco");
        } else {
            this.hora = hora;
        }
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        if (local == null || local.isBlank()) {
            throw new IllegalArgumentException("O local não deve estar em branco");
        } else {
            this.local = local;
        }
    }

    public List<Proprietario> getProprietario() {
        return proprietario;
    }

    public void setProprietario(List<Proprietario> proprietario) {
        if (proprietario == null || proprietario.isEmpty()) {
            throw new IllegalArgumentException("O proprietário não deve estar em branco");
        } else {
            this.proprietario = proprietario;
        }
    }

    public List<Embarcacao> getEmbarcacao() {
        return embarcacao;
    }

    public void setEmbarcacao(List<Embarcacao> embarcacao) {
        if (embarcacao == null || embarcacao.isEmpty()) {
            throw new IllegalArgumentException("A embarcação não deve estar em branco");
        } else {
            this.embarcacao = embarcacao;
        }
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        if (cliente == null || cliente.isEmpty()) {
            throw new IllegalArgumentException("O cliente não deve estar em branco");
        } else {
            this.cliente = cliente;
        }
    }
}
