package com.example;

public class ContaCorrente extends Conta {

	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato com.example.Conta Corrente ===");
		super.imprimirInfosComuns();
	}
	
}
