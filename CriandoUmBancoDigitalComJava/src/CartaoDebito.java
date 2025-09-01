public class CartaoDebito {

	private Conta conta;

	public CartaoDebito(Conta conta) {
		this.conta = conta;
	}

	public void pagar(double valor) {
		conta.sacar(valor);
		System.out.println(String.format("Pagamento de %.2f realizado com sucesso.", valor));
	}

	public void imprimirExtrato() {
		System.out.println("=== Extrato do Cartao de Debito ===");
		System.out.println(String.format("Titular: %s", conta.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", conta.getAgencia()));
		System.out.println(String.format("Numero: %d", conta.getNumero()));
		System.out.println(String.format("Saldo: %.2f", conta.getSaldo()));
	}

}