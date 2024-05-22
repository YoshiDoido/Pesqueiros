package com.fatecdiadema.pesqueiros.pesqueiros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;


/* Classe que contém o Cliente da Aplicação */
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String nrTelefone;

    public Cliente(String nome, String cpf, String email, String senha, String nrTelefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.nrTelefone = nrTelefone;
    }

    public Cliente() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        }
        else if (cpf == null || !isCpfValido(cpf)) {
            throw new IllegalArgumentException("O CPF não é válido");

        }
        else {
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

    private boolean isCpfValido(String cpf) {
        // Remove Caracteres Não Numéricos
        cpf = cpf.replaceAll("\\D", "");

        // Verifica se o tamanho do cpf é 11 e se não é uma sequência de números iguais
        if (cpf.length() != 11 || cpf.matches(("(\\d)\\1{10}"))) {
            return false;
        }

        try {

            int[] digits = new int[11];
            for (int i = 0; i < 11; i++) {
                digits[i] = Integer.parseInt(cpf.substring(i, i + 1));
            }

            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < 9; i++) {
                sum1 += digits[i] * (10 - i);
                sum2 += digits[i] * (11 - i);
            }

            sum1 = (sum1 * 10) % 11;
            if (sum1 == 10)
                sum1 = 0;
            sum2 += sum1 * 2;
            sum2 = (sum2 * 10) % 11;
            if (sum2 == 10)
                sum2 = 0;

            return (sum1 == digits[9] && sum2 == digits[10]);

        } catch (NumberFormatException e) {
            return false;
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
        Cliente other = (Cliente) obj;
        return Objects.equals(nome, other.nome) && Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
                && Objects.equals(senha, other.senha) && Objects.equals(nrTelefone, other.nrTelefone);
    }

    @Override
    public String toString() {
        return "Cliente [cpf=" + cpf + ", email=" + email + ", id=" + id + ", nome=" + nome + ", nrTelefone="
                + nrTelefone + "]";
    }
}