package br.com.esdrasferreira.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.esdrasferreira.entity.Usuario;
import br.com.esdrasferreira.factory.jdbc.*;

public class UsuarioDao {
	
	
	private Connection conexao;
	
	public UsuarioDao() throws Exception {
		this.conexao = FabricaConexao.getConexao();
	}
	
	
	
	
	public Usuario login(String nome, String senha) throws Exception {
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
		
		try {
			
			conn = this.conexao;
			ps = conn.prepareStatement("SELECT * FROM cliente WHERE nome=? AND senha =?");
			ps.setString(1, nome);
			ps.setString(2, senha);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				return new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
				
			}
			return null;
			
			
			
		}catch(SQLException sqle) {
			throw new Exception(sqle);
		}finally {
			FabricaConexao.fecharPreparedStatement(ps);
			FabricaConexao.fecharResultSet(rs);
			fecharConexao();

		}
		
		
		
	}
	
public Usuario getUser(int conta) throws Exception {
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
		
		try {
			
			conn = this.conexao;
			ps = conn.prepareStatement("SELECT * FROM `cliente` WHERE `conta` = '"+conta+"'");
			 			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				return new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
				
			}
			return null;
			
			
			
			
		}catch(SQLException sqle) {
			throw new Exception(sqle);
		}finally {
			FabricaConexao.fecharPreparedStatement(ps);
			FabricaConexao.fecharResultSet(rs);
			fecharConexao();

		}
		
		
		
	}	
	
	

	

	//devemos chamar o fechamento da conexao apenas quando não for usar mais
	public void fecharConexao() throws Exception {
		FabricaConexao.fecharConexao(conexao);
	}


}
