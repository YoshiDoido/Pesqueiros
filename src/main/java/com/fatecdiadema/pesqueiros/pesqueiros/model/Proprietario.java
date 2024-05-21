package com.fatecdiadema.pesqueiros.pesqueiros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Proprietario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String nrTelefone;

    public Proprietario(Long id, String nome, String cpf, String email, String senha, String nrTelefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.nrTelefone = nrTelefone;
    }

    public Proprietario() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não deve estar em branco");
        } else {
            this.nome = nome;
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("O CPF não deve estar em branco");
        } else {
            this.cpf = cpf;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("O email não deve estar em branco");
        } else {
            this.email = email;
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("A senha não deve estar em branco");
        } else {
            this.senha = senha;
        }
    }

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        if (nrTelefone == null || nrTelefone.isBlank()) {
            throw new IllegalArgumentException("O número de telefone não deve estar em branco");
        } else {
            this.nrTelefone = nrTelefone;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, email, senha, nrTelefone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Proprietario other = (Proprietario) obj;
        return Objects.equals(nome, other.nome) && Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
                && Objects.equals(senha, other.senha) && Objects.equals(nrTelefone, other.nrTelefone);
    }

    @Override
    public String toString() {
        return "Proprietario [cpf=" + cpf + ", email=" + email + ", id=" + id + ", nome=" + nome + ", nrTelefone="
                + nrTelefone + ", senha=" + senha + "]";
    }
}
