package br.com.esdrasferreira.factory.jdbc;

public class ConexaoException extends Exception {
	
	private static final long serialVersionUID = 1L;


	public ConexaoException() {}
	
	public ConexaoException(String arg) {
		super(arg);
	}
	
	public ConexaoException(Throwable arg) {
		super(arg);
	}
	
	
	public ConexaoException(String arg, Throwable arg1) {
		super(arg, arg1);
	}
	

}
