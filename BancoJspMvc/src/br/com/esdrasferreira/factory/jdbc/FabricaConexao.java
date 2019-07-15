package br.com.esdrasferreira.factory.jdbc;

import java.sql.*;

public class FabricaConexao {
	

	
	public static Connection getConexao() throws Exception {
		String url = "jdbc:mysql://192.175.108.234/bancomvc?useTimezone=true&serverTimezone=UTC";	 //porta tcp 3306	
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			return DriverManager.getConnection( url , "esdrasBanco", "Z,q6&&2+_L^k");
		} catch (SQLException e) {
			
			throw new Exception("Erro na conexão");
		}
	}
	
	public static void fecharConexao(Connection conexao) throws Exception{
		fecharTudo(conexao, null, null, null);
		
		
	}
	
	public static void fecharStatement(Statement stmt) throws Exception{
		fecharTudo(null, stmt, null, null);
		
	}
	
	public static void fecharResultSet(ResultSet rs) throws Exception{
		fecharTudo(null, null, rs, null);
		
	}
	
	public static void fecharPreparedStatement(PreparedStatement ps) throws Exception{
		fecharTudo(null, null, null, ps);
		
	}
	
	public static void fecharStmtRs(Statement stmt, ResultSet rs) throws Exception{
		fecharTudo(null, stmt, rs, null);
		
	}
	
	
	private static void fecharTudo(Connection conn, Statement stmt, ResultSet rs, PreparedStatement ps) throws Exception {
		
		try {
			if( conn != null ) conn.close();
			if( rs != null ) rs.close();
			if( ps != null ) ps.close();
			if( stmt != null ) stmt.close();
	
			
		}catch (Exception e){
			throw new Exception(e.getMessage()) ;
		}
		
	}
	
	
	

}
