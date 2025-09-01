package com.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartaoDebitoTest {

    private Conta criarConta() {
        Cliente cliente = new Cliente();
        cliente.setNome("com.example.Cliente");
        return new ContaCorrente(cliente);
    }

    @Test
    public void testImprimirExtrato() {
        Conta conta = criarConta();
        conta.depositar(50.0);
        CartaoDebito cartao = conta.getCartaoDebito();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        cartao.imprimirExtrato();
        System.setOut(original);

        String output = out.toString();
        Assertions.assertTrue(output.contains("=== Extrato do Cartao de Debito ==="));
        Assertions.assertTrue(output.contains("Titular: com.example.Cliente"));
        Assertions.assertTrue(output.contains(String.format("Agencia: %d", conta.getAgencia())));
        Assertions.assertTrue(output.contains(String.format("Numero: %d", conta.getNumero())));
        Assertions.assertTrue(output.contains(String.format("Saldo: %.2f", conta.getSaldo())));
    }
}
