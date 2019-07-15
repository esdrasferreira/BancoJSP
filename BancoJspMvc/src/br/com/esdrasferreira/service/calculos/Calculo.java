package br.com.esdrasferreira.service.calculos;

import br.com.esdrasferreira.entity.Conta;
import br.com.esdrasferreira.entity.Poupanca;

public class Calculo {

	Conta conta = null;

	public void depositarCC(double valor, Conta conta) {

		double saldofinal = valor + conta.getSaldo();
		conta.setSaldo(saldofinal);

	}
	
	

	public void depositarPP(double valor, Poupanca poupanca) {

		double saldofinal = valor + poupanca.getSaldo();
		poupanca.setSaldo(saldofinal);

	}

	public void sacarCC(double valor, Conta conta) {

		double saldofinal = conta.getSaldo() - valor;
		conta.setSaldo(saldofinal);

	}

	public void sacarPP(double valor, Poupanca poupanca) {

		double saldofinal = poupanca.getSaldo() - valor;
		poupanca.setSaldo(saldofinal);

	}

	public void paraPoupanca(double valor, Conta conta, Poupanca poupanca) {

		this.sacarCC(valor, conta);
		this.depositarPP(valor, poupanca);

	}
	
	public void paraConta(double valor, Conta conta, Poupanca poupanca) {

		this.sacarPP(valor, poupanca);
		this.depositarCC(valor, conta);

	}

}
