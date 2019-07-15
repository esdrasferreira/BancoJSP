package br.com.esdrasferreira.entity;

public class Poupanca {
	
	private int idpoupanca;
	private Double saldo;
	private int contaCorrente_idconta;
	
	public Poupanca() {}
	
	public Poupanca(int idpoupanca, Double saldo, int contaCorrente_idconta) {
		super();
		this.idpoupanca = idpoupanca;
		this.saldo = saldo;
		this.contaCorrente_idconta = contaCorrente_idconta;
	}
	public int getIdpoupanca() {
		return idpoupanca;
	}
	public void setIdpoupanca(int idpoupanca) {
		this.idpoupanca = idpoupanca;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public int getContaCorrente_idconta() {
		return contaCorrente_idconta;
	}
	public void setContaCorrente_idconta(int contaCorrente_idconta) {
		this.contaCorrente_idconta = contaCorrente_idconta;
	}
	
	

}
