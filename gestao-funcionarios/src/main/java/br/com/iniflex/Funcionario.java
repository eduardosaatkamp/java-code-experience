package br.com.iniflex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private final String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = validarSalario(salario);
        this.funcao = Objects.requireNonNull(funcao, "Função não pode ser nula");
    }

    public BigDecimal getSalario() { return salario; }

    public void setSalario(BigDecimal salario) {
        this.salario = validarSalario(salario);
    }

    public String getFuncao() { return funcao; }

    private static BigDecimal validarSalario(BigDecimal salario) {
        Objects.requireNonNull(salario, "Salário não pode ser nulo");
        if (salario.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salário não pode ser negativo");
        }
        return salario;
    }
}
