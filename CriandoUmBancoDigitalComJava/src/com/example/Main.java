package com.example;

public class Main {
public static void main(String[] args) {
        Cliente clienteEduardo = new Cliente();
        clienteEduardo.setNome("Eduardo");

        Conta cc = new ContaCorrente(clienteEduardo);
        Conta poupanca = new ContaPoupanca(clienteEduardo);
    // Valor de um salário mínimo
    cc.depositar(1320);
    cc.imprimirExtrato();
    cc.transferir(132, poupanca);
    // 10% do salário
	cc.imprimirExtrato();
	poupanca.imprimirExtrato();

	CartaoDebito cartaoDebito = cc.getCartaoDebito();
	cartaoDebito.pagar(50);
        cartaoDebito.imprimirExtrato();
}
}
