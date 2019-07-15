package br.com.esdrasferreira.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.esdrasferreira.entity.*;
import br.com.esdrasferreira.factory.jdbc.*;

public class ContaDao {

	private Connection conexao;

	public ContaDao() throws Exception {
		this.conexao = FabricaConexao.getConexao();
	}

	public Conta getConta(int conta) throws Exception {

		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;

		try {

			conn = this.conexao;
			ps = conn.prepareStatement(
					"SELECT contacorrente.conta, contacorrente.saldo FROM bancomvc.cliente, bancomvc.contacorrente where bancomvc.cliente.conta = '"
							+ conta + "' ");

			rs = ps.executeQuery();

			if (rs.next()) {

				return new Conta(rs.getInt(1), rs.getDouble(2));

			}
			return null;

		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			FabricaConexao.fecharPreparedStatement(ps);
			FabricaConexao.fecharResultSet(rs);
			fecharConexao();

		}

	}

	public void update(Conta conta) throws Exception {

		Connection conn = null;
		Statement st = null;

		try {

			conn = this.conexao;
			st = conn.createStatement();

			int idConta = conta.getConta();
			double saldo = conta.getSaldo();

			st.executeUpdate("UPDATE `contacorrente` SET `saldo` = '" + saldo + "' WHERE `contacorrente`.`conta` = '"
					+ idConta + "' ");

		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			FabricaConexao.fecharStatement(st);
			fecharConexao();

		}

	}
	
	public void transferencia(int id, double valor) throws Exception {

		PreparedStatement ps = null;
		Connection conn = null;

		try {
			conn = this.conexao;
			ps = conn.prepareStatement("UPDATE `contacorrente` SET `saldo` = ? WHERE `contacorrente`.`conta` = ?");
			ps.setDouble(1, valor);
			ps.setInt(2, id);

			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception();
		} finally {
			FabricaConexao.fecharPreparedStatement(ps);
			fecharConexao();
		}

	}

//devemos chamar o fechamento da conexao apenas quando não for usar mais
	public void fecharConexao() throws Exception {
		FabricaConexao.fecharConexao(conexao);
	}

}
