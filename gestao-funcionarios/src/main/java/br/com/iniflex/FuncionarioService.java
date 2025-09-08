package br.com.iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {

    public List<Funcionario> seed() {
        return new ArrayList<>(List.of(
                f("Maria",   2000,10,18, "2009.44", "Operador"),
                f("João",    1990, 5,12, "2284.38", "Operador"),
                f("Caio",    1961, 5, 2, "9834.18", "Coordenador"),
                f("Miguel",  1988,10,14, "1913.98", "Diretor"),
                f("Alice",   1995, 1, 5, "2234.68", "Recepcionista"),
                f("Heitor",  1999,11,19, "1582.72", "Operador"),
                f("Arthur",  1993, 3,31, "4071.84", "Contador"),
                f("Laura",   1994, 7, 8, "3017.45", "Gerente"),
                f("Heloísa", 2003, 5,24, "1606.85", "Eletricista"),
                f("Helena",  1996, 9, 2, "2799.93", "Gerente")
        ));
    }

    public void removerPorNome(List<Funcionario> lista, String nome) {
        validarLista(lista);
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("nome inválido");
        }
        lista.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    }

    public void aplicarAumento(List<Funcionario> lista, BigDecimal fator) {
        validarLista(lista);
        if (fator == null || fator.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("fator inválido");
        }
        for (Funcionario f : lista) {
            f.setSalario(f.getSalario().multiply(fator).setScale(2, RoundingMode.HALF_UP));
        }
    }

    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> lista) {
        validarLista(lista);
        return lista.stream().collect(Collectors.groupingBy(Funcionario::getFuncao, LinkedHashMap::new, Collectors.toList()));
    }

    public List<Funcionario> aniversariantesMeses(List<Funcionario> lista, Month... meses) {
        validarLista(lista);
        if (meses == null || meses.length == 0 || Arrays.stream(meses).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("meses inválidos");
        }
        Set<Integer> filtro = Arrays.stream(meses).map(Month::getValue).collect(Collectors.toSet());
        return lista.stream().filter(f -> filtro.contains(f.getDataNascimento().getMonthValue())).toList();
    }

    public Funcionario maisVelho(List<Funcionario> lista) {
        return lista.stream().min(Comparator.comparing(Funcionario::getDataNascimento)).orElseThrow();
    }

    public List<Funcionario> ordenarPorNome(List<Funcionario> lista) {
        validarLista(lista);
        return lista.stream()
                .sorted(Comparator.comparing(Funcionario::getNome, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public BigDecimal totalSalarios(List<Funcionario> lista) {
        validarLista(lista);
        return lista.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, BigDecimal> salariosMinimos(List<Funcionario> lista, BigDecimal salarioMinimo) {
        Map<String, BigDecimal> out = new LinkedHashMap<>();
        for (Funcionario f : lista) {
            out.put(f.getNome(), f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP));
        }
        return out;
    }

    private static <T> void validarLista(List<T> lista) {
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("lista inválida");
        }
    }

    // --- helpers ---
    private static Funcionario f(String n, int y, int m, int d, String s, String func) {
        return new Funcionario(n, LocalDate.of(y, m, d), new BigDecimal(s), func);
    }
}
