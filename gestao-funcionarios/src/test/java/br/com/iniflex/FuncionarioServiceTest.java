package br.com.iniflex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioServiceTest {

    private FuncionarioService service;
    private List<Funcionario> lista;

    @BeforeEach
    void setUp() {
        service = new FuncionarioService();
        lista = service.seed(); // 10 funcionários
    }

    @Test
    void deveRemoverJoaoDaLista() {
        boolean removido = service.removerPorNome(lista, "João");
        assertTrue(removido);
        assertEquals(9, lista.size());
        assertTrue(lista.stream().noneMatch(f -> f.getNome().equalsIgnoreCase("João")));
    }

    @Test
    void deveRetornarFalseQuandoNomeNaoEncontrado() {
        boolean removido = service.removerPorNome(lista, "Fulano");
        assertFalse(removido);
        assertEquals(10, lista.size());
    }

    @Test
    void deveAplicarAumentoDe10PorCento() {
        service.removerPorNome(lista, "João");
        service.aplicarAumento(lista, new BigDecimal("1.10"));

        // soma esperada (após remoção do João) = 31.978,18
        BigDecimal esperado = new BigDecimal("31978.18");
        assertEquals(0, service.totalSalarios(lista).compareTo(esperado));
    }

    @Test
    void deveAgruparPorFuncao() {
        service.removerPorNome(lista, "João");
        Map<String, List<Funcionario>> grupos = service.agruparPorFuncao(lista);

        assertTrue(grupos.containsKey("Operador"));
        assertTrue(grupos.containsKey("Gerente"));

        // ✅ Gerente = Laura e Helena → 2
        assertEquals(2, grupos.get("Gerente").size());

        // (opcional) mais garantias:
        assertEquals(2, grupos.get("Operador").size()); // Maria, Heitor (João removido)
        assertEquals(1, grupos.get("Coordenador").size()); // Caio
        assertEquals(1, grupos.get("Diretor").size());     // Miguel
        assertEquals(1, grupos.get("Recepcionista").size());// Alice
        assertEquals(1, grupos.get("Contador").size());    // Arthur
        assertEquals(1, grupos.get("Eletricista").size()); // Heloísa
    }

    @Test
    void deveFiltrarAniversariantesDeOutubroEDezembro() {
        service.removerPorNome(lista, "João");
        List<Funcionario> aniversariantes = service.aniversariantesMeses(lista, Month.OCTOBER, Month.DECEMBER);

        // da tabela: Maria (10/18), Miguel (10/14) => 2
        assertEquals(2, aniversariantes.size());
        assertTrue(aniversariantes.stream().anyMatch(f -> f.getNome().equals("Maria")));
        assertTrue(aniversariantes.stream().anyMatch(f -> f.getNome().equals("Miguel")));
    }

    @Test
    void deveEncontrarMaisVelho() {
        service.removerPorNome(lista, "João");
        Funcionario maisVelho = service.maisVelho(lista);
        assertEquals("Caio", maisVelho.getNome());
    }

    @Test
    void deveFalharQuandoListaVazia() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> service.maisVelho(List.of()));
        assertEquals("Lista de funcionários vazia", ex.getMessage());
    }

    @Test
    void deveOrdenarPorNome() {
        service.removerPorNome(lista, "João");
        List<Funcionario> ordenados = service.ordenarPorNome(lista);

        assertEquals("Alice", ordenados.get(0).getNome());
        assertEquals("Miguel", ordenados.get(ordenados.size() - 1).getNome());
    }

    @Test
    void deveSomarSalarios() {
        service.removerPorNome(lista, "João");
        BigDecimal total = service.totalSalarios(lista);
        // antes do aumento (João removido): 29.071,07
        assertEquals(0, total.compareTo(new BigDecimal("29071.07")));
    }

    @Test
    void deveCalcularSalariosMinimos() {
        service.removerPorNome(lista, "João");
        service.aplicarAumento(lista, new BigDecimal("1.10"));

        Map<String, BigDecimal> qtd = service.salariosMinimos(lista, new BigDecimal("1212.00"));
        // alguns asserts pontuais (após aumento):
        // Maria 2.009,44 -> 2.210,38 => 1,82 SM
        assertEquals(0, qtd.get("Maria").compareTo(new BigDecimal("1.82")));
        // Caio 9.834,18 -> 10.817,60 => 8,93 SM
        assertEquals(0, qtd.get("Caio").compareTo(new BigDecimal("8.93")));
        // Arthur 4.071,84 -> 4.479,02 => 3,70 SM
        assertEquals(0, qtd.get("Arthur").compareTo(new BigDecimal("3.70")));
    }

    @Test
    void deveValidarRemoverPorNome() {
        assertThrows(IllegalArgumentException.class, () -> service.removerPorNome(null, "João"));
        assertThrows(IllegalArgumentException.class, () -> service.removerPorNome(List.of(), "João"));
        assertThrows(IllegalArgumentException.class, () -> service.removerPorNome(lista, ""));
    }

    @Test
    void deveValidarAplicarAumento() {
        assertThrows(IllegalArgumentException.class, () -> service.aplicarAumento(null, BigDecimal.ONE));
        assertThrows(IllegalArgumentException.class, () -> service.aplicarAumento(List.of(), BigDecimal.ONE));
        assertThrows(IllegalArgumentException.class, () -> service.aplicarAumento(lista, BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> service.aplicarAumento(lista, new BigDecimal("-1")));
        assertThrows(IllegalArgumentException.class, () -> service.aplicarAumento(lista, null));
    }

    @Test
    void deveValidarAgruparPorFuncao() {
        assertThrows(IllegalArgumentException.class, () -> service.agruparPorFuncao(null));
        assertThrows(IllegalArgumentException.class, () -> service.agruparPorFuncao(List.of()));
    }

    @Test
    void deveValidarAniversariantesMeses() {
        assertThrows(IllegalArgumentException.class, () -> service.aniversariantesMeses(null, Month.JANUARY));
        assertThrows(IllegalArgumentException.class, () -> service.aniversariantesMeses(List.of(), Month.JANUARY));
        assertThrows(IllegalArgumentException.class, () -> service.aniversariantesMeses(lista, (Month[]) null));
        assertThrows(IllegalArgumentException.class, () -> service.aniversariantesMeses(lista, new Month[]{null}));
    }

    @Test
    void deveValidarOrdenarPorNome() {
        assertThrows(IllegalArgumentException.class, () -> service.ordenarPorNome(null));
        assertThrows(IllegalArgumentException.class, () -> service.ordenarPorNome(List.of()));
    }

    @Test
    void deveValidarTotalSalarios() {
        assertThrows(IllegalArgumentException.class, () -> service.totalSalarios(null));
        assertThrows(IllegalArgumentException.class, () -> service.totalSalarios(List.of()));
    }
}

