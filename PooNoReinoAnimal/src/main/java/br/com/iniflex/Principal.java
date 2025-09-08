package br.com.iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final Locale PT_BR = new Locale("pt", "BR");
    private static final NumberFormat NF = NumberFormat.getNumberInstance(PT_BR); // 1.234,56
    private static final BigDecimal SAL_MIN = new BigDecimal("1212.00");

    static {
        NF.setMinimumFractionDigits(2);
        NF.setMaximumFractionDigits(2);
    }

    public static void main(String[] args) {
        // 3.1 – Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>(List.of(
                new Funcionario("Maria",   LocalDate.of(2000, 10, 18), bd("2009.44"), "Operador"),
                new Funcionario("João",    LocalDate.of(1990, 5, 12),  bd("2284.38"), "Operador"),
                new Funcionario("Caio",    LocalDate.of(1961, 5, 2),   bd("9834.18"), "Coordenador"),
                new Funcionario("Miguel",  LocalDate.of(1988, 10, 14), bd("1913.98"), "Diretor"),
                new Funcionario("Alice",   LocalDate.of(1995, 1, 5),   bd("2234.68"), "Recepcionista"),
                new Funcionario("Heitor",  LocalDate.of(1999, 11, 19), bd("1582.72"), "Operador"),
                new Funcionario("Arthur",  LocalDate.of(1993, 3, 31),  bd("4071.84"), "Contador"),
                new Funcionario("Laura",   LocalDate.of(1994, 7, 8),   bd("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24),  bd("1606.85"), "Eletricista"),
                new Funcionario("Helena",  LocalDate.of(1996, 9, 2),   bd("2799.93"), "Gerente")
        ));

        // 3.2 – Remover “João”
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // 3.3 – Imprimir todos (data dd/MM/aaaa, números 1.234,56)
        System.out.println("==== Funcionários (dados formatados) ====");
        funcionarios.forEach(System.out::println);

        // 3.4 – Aumento de 10%
        funcionarios.forEach(f ->
                f.setSalario(f.getSalario().multiply(bd("1.10")).setScale(2, RoundingMode.HALF_UP))
        );

        System.out.println("\n==== Após aumento de 10% ====");
        funcionarios.forEach(System.out::println);

        // 3.5 – Agrupar por função
        Map<String, List<Funcionario>> porFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao, LinkedHashMap::new, Collectors.toList()));

        // 3.6 – Imprimir agrupados por função
        System.out.println("\n==== Agrupados por função ====");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
            System.out.println();
        });

        // 3.8 – Aniversariantes dos meses 10 e 12
        System.out.println("==== Aniversários em OUT (10) e DEZ (12) ====");
        funcionarios.stream()
                .filter(f -> {
                    int m = f.getDataNascimento().getMonthValue();
                    return (m == Month.OCTOBER.getValue() || m == Month.DECEMBER.getValue());
                })
                .forEach(System.out::println);

        // 3.9 – Funcionário com maior idade (nome e idade)
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento)) // mais antigo = mais velho
                .orElseThrow();
        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("\n==== Mais velho ====");
        System.out.println(maisVelho.getNome() + " - " + idade + " anos");

        // 3.10 – Lista por ordem alfabética
        System.out.println("\n==== Ordenados por nome (A-Z) ====");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome, String.CASE_INSENSITIVE_ORDER))
                .forEach(System.out::println);

        // 3.11 – Total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\n==== Total de salários ====");
        System.out.println(NF.format(totalSalarios));

        // 3.12 – Quantos salários mínimos ganha cada funcionário
        System.out.println("\n==== Salários mínimos por funcionário (min = R$ 1.212,00) ====");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(SAL_MIN, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " — " + NF.format(qtd) + " salários mínimos");
        });
    }

    private static BigDecimal bd(String v) { return new BigDecimal(v); }

    
}
