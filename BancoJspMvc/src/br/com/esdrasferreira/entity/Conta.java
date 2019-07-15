package br.com.esdrasferreira.entity;

public class Conta {
	
	private int conta;
	private Double saldo;
	
	public Conta() {}
	
	
	
	public Conta(int conta, Double saldo) {
		super();
		this.conta = conta;
		this.saldo = saldo;
	}
	public int getConta() {
		return conta;
	}
	public void setConta(int conta) {
		this.conta = conta;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

}
