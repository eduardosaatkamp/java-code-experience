package br.com.iniflex;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioFormatterTest {

    @Test
    void deveLancarExcecaoQuandoFuncionarioForNulo() {
        assertThrows(IllegalArgumentException.class, () -> FuncionarioFormatter.formatar(null));
        assertThrows(IllegalArgumentException.class, () -> FuncionarioFormatter.formatarSimples(null));
    }

    @Test
    void deveRetornarMensagemPadraoQuandoCampoEssencialForNulo() {
        Funcionario f = new Funcionario(null, LocalDate.now(), new BigDecimal("1000.00"), "Tester");
        String completo = FuncionarioFormatter.formatar(f);
        String simples = FuncionarioFormatter.formatarSimples(f);
        assertEquals(FuncionarioFormatter.MSG_DADOS_INDISPONIVEIS, completo);
        assertEquals(FuncionarioFormatter.MSG_DADOS_INDISPONIVEIS, simples);
    }
}
