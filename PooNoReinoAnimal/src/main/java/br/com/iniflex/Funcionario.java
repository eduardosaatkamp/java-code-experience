package br.com.iniflex;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private final String funcao;

    private static final Locale PT_BR = new Locale("pt", "BR");
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat NF = NumberFormat.getNumberInstance(PT_BR);

    static {
        NF.setMinimumFractionDigits(2);
        NF.setMaximumFractionDigits(2);
    }

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }
    public String getFuncao() { return funcao; }

    @Override
    public String toString() {
        return String.format(
                "%s — %s — Nasc.: %s — Salário: %s",
                getNome(),
                funcao,
                getDataNascimento().format(DF),
                NF.format(salario)
        );
    }
}
