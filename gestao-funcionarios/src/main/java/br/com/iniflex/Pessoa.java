package br.com.iniflex;

import java.time.LocalDate;
import java.util.Objects;

public class Pessoa {
    private final String nome;
    private final LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
        this.dataNascimento = Objects.requireNonNull(dataNascimento, "Data de nascimento não pode ser nula");
        if (this.dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento não pode ser no futuro");
        }
    }
    public String getNome() { return nome; }
    public LocalDate getDataNascimento() { return dataNascimento; }
}

