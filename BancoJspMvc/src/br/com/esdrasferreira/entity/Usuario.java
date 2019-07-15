package br.com.esdrasferreira.entity;

public class Usuario {

	private int conta;
	private String nome;
	private String cpf;
	private String end;
	private String senha;
	private int idContaCorrente;

	public Usuario() {
	}

	public Usuario(int conta, String nome, String cpf, String end, String senha, int idContaCorrente) {
		super();
		this.conta = conta;
		this.nome = nome;
		this.cpf = cpf;
		this.end = end;
		this.senha = senha;
		this.idContaCorrente = idContaCorrente;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getIdContaCorrente() {
		return idContaCorrente;
	}

	public void setIdContaCorrente(int idContaCorrente) {
		this.idContaCorrente = idContaCorrente;
	}

	

}
