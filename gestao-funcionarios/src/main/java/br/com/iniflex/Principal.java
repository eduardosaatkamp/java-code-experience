package br.com.iniflex;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    private static final Locale PT_BR = new Locale("pt", "BR");
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat NF = NumberFormat.getNumberInstance(PT_BR); // 1.234,56
    private static final BigDecimal SAL_MIN = new BigDecimal("1212.00");
    private static final BigDecimal FATOR_AUMENTO = new BigDecimal("1.10");
    private static final Logger LOGGER = Logger.getLogger(Principal.class.getName());

    static {
        NF.setMinimumFractionDigits(2);
        NF.setMaximumFractionDigits(2);
    }

    public static void main(String[] args) {
        FuncionarioService service = new FuncionarioService();
        try {
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
            List<Funcionario> aniversariantes = service.aniversariantesMeses(funcionarios, Month.OCTOBER, Month.DECEMBER);
            if (aniversariantes.isEmpty()) {
                System.out.println("Nenhum funcionário faz aniversário nos meses informados");
            } else {
                aniversariantes.forEach(Principal::imprimirFuncionarioSimples);
            }

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
        } catch (IllegalArgumentException | IllegalStateException e) {
            LOGGER.log(Level.SEVERE, "Erro ao processar funcionários", e);
            System.out.println("Ocorreu um erro ao processar os funcionários. O programa será encerrado.");
        }
    }

    private static void imprimirFuncionario(Funcionario f) {
        String linha = String.format(
                "%-10s | Nasc.: %s | Salário: %s | Função: %s",
                f.getNome(),
                f.getDataNascimento().format(DF),
                NF.format(f.getSalario()),
                f.getFuncao()
        );
        System.out.println(linha);
    }

    private static void imprimirFuncionarioSimples(Funcionario f) {
        System.out.println(
                f.getNome() + " — " + f.getFuncao() +
                        " — Nasc.: " + f.getDataNascimento().format(DF) +
                        " — Salário: " + NF.format(f.getSalario())
        );
    }
}
