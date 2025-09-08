package br.com.iniflex;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Principal {

    private static final Locale PT_BR = new Locale("pt", "BR");
    private static final NumberFormat NF = NumberFormat.getNumberInstance(PT_BR); // 1.234,56
    private static final BigDecimal SAL_MIN = new BigDecimal("1212.00");
    private static final BigDecimal FATOR_AUMENTO = new BigDecimal("1.10");

    static {
        NF.setMinimumFractionDigits(2);
        NF.setMaximumFractionDigits(2);
    }

    public static void main(String[] args) {
        FuncionarioService service = new FuncionarioService();
        List<Funcionario> funcionarios = service.seed();

        service.removerPorNome(funcionarios, "João");

        System.out.println("==== Funcionários (dados formatados) ====");
        funcionarios.forEach(Principal::imprimirFuncionario);

        service.aplicarAumento(funcionarios, FATOR_AUMENTO);

        System.out.println("\n==== Após aumento de 10% ====");
        funcionarios.forEach(Principal::imprimirFuncionario);

        Map<String, List<Funcionario>> porFuncao = service.agruparPorFuncao(funcionarios);

        System.out.println("\n==== Agrupados por função ====");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(Principal::imprimirFuncionarioSimples);
            System.out.println();
        });

        System.out.println("==== Aniversários em OUT (10) e DEZ (12) ====");
        service.aniversariantesMeses(funcionarios, Month.OCTOBER, Month.DECEMBER)
                .forEach(Principal::imprimirFuncionarioSimples);

        Funcionario maisVelho = service.maisVelho(funcionarios);
        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("\n==== Mais velho ====");
        System.out.println(maisVelho.getNome() + " - " + idade + " anos");

        System.out.println("\n==== Ordenados por nome (A-Z) ====");
        service.ordenarPorNome(funcionarios).forEach(Principal::imprimirFuncionarioSimples);

        BigDecimal totalSalarios = service.totalSalarios(funcionarios);
        System.out.println("\n==== Total de salários ====");
        System.out.println(NF.format(totalSalarios));

        System.out.println("\n==== Salários mínimos por funcionário (min = R$ 1.212,00) ====");
        service.salariosMinimos(funcionarios, SAL_MIN)
                .forEach((nome, qtd) -> System.out.println(nome + " — " + NF.format(qtd) + " salários mínimos"));
    }

    private static void imprimirFuncionario(Funcionario f) {
        System.out.println(FuncionarioFormatter.formatar(f));
    }

    private static void imprimirFuncionarioSimples(Funcionario f) {
        System.out.println(FuncionarioFormatter.formatarSimples(f));
    }
}
