package br.com.esdrasferreira.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import br.com.esdrasferreira.entity.Poupanca;
import br.com.esdrasferreira.factory.jdbc.*;

public class PoupancaDao {
	
	
private Connection conexao;
	
	public PoupancaDao() throws Exception {
		this.conexao = FabricaConexao.getConexao();
	}
	
	public Poupanca saldo(int conta) throws Exception {
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
		
		try {
			
			conn = this.conexao;
			ps = conn.prepareStatement("SELECT * FROM `poupanca` WHERE `contaCorrente_idconta` = ?");
			ps.setInt(1, conta);
			
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				return new Poupanca(rs.getInt(1), rs.getDouble(2), rs.getInt(3));
				
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
	
	public void update(Poupanca poupanca) throws Exception {

		Connection conn = null;
		Statement st = null;

		try {

			conn = this.conexao;
			st = conn.createStatement();

			int idpoupanca = poupanca.getIdpoupanca();
			int idconta = poupanca.getContaCorrente_idconta();
			double valor = poupanca.getSaldo();

			st.executeUpdate("UPDATE `poupanca` SET `saldo` = '"+valor+"' WHERE `poupanca`.`idpoupanca` = '"+idpoupanca+"' AND `poupanca`.`contaCorrente_idconta` = '"+idconta+"'");

		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			FabricaConexao.fecharStatement(st);
			fecharConexao();

		}

	}
	
	
	
	
	
	//devemos chamar o fechamento da conexao apenas quando não for usar mais
		public void fecharConexao() throws Exception {
			FabricaConexao.fecharConexao(conexao);
		}

}
