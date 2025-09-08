package br.com.iniflex;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FuncionarioFormatter {
    private static final Locale PT_BR = new Locale("pt", "BR");
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat NF = NumberFormat.getNumberInstance(PT_BR);
    public static final String MSG_DADOS_INDISPONIVEIS = "Dados do funcionário incompletos";

    static {
        NF.setMinimumFractionDigits(2);
        NF.setMaximumFractionDigits(2);
    }

    private static void validar(Funcionario f) {
        if (f == null) {
            throw new IllegalArgumentException("Funcionario não pode ser nulo");
        }
    }

    private static boolean dadosIncompletos(Funcionario f) {
        return f.getNome() == null || f.getFuncao() == null ||
                f.getDataNascimento() == null || f.getSalario() == null;
    }

    public static String formatar(Funcionario f) {
        validar(f);
        if (dadosIncompletos(f)) {
            return MSG_DADOS_INDISPONIVEIS;
        }
        return String.format("%-10s | Nasc.: %s | Salário: %s | Função: %s",
                f.getNome(),
                f.getDataNascimento().format(DF),
                NF.format(f.getSalario()),
                f.getFuncao());
    }

    public static String formatarSimples(Funcionario f) {
        validar(f);
        if (dadosIncompletos(f)) {
            return MSG_DADOS_INDISPONIVEIS;
        }
        return f.getNome() + " — " + f.getFuncao() +
                " — Nasc.: " + f.getDataNascimento().format(DF) +
                " — Salário: " + NF.format(f.getSalario());
    }
}
