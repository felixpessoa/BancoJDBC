package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Conta;
import model.util.Conexao;

public class ContaDAOImpl {
	Conexao conexao = new Conexao();

	public void salvar(Conta conta) {
		Connection conn = conexao.getConnection();
		String sql = "INSERT INTO CONTA(NUMERO, SALDO, LIMITE) " + "VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, conta.getNumero());
			ps.setDouble(2, conta.getSaldo());
			ps.setDouble(3, conta.getLimite());
			ps.execute();
			System.out.println("Conta inserida com sucesso");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir conta no banco" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void remover(int numero) {
		Connection conn = conexao.getConnection();
		String sql = "DELETE FROM CONTA WHERE NUMERO = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, numero);
			ps.execute();
			System.out.println("Conta Deletada com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao Deletar Conta no banco" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void alterar(Conta conta) {
		Connection conn = conexao.getConnection();
		String sql = "UPDATE CONTA SET SALDO = ?, LIMITE = ?, WHERE NUMERO = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, conta.getSaldo());
			ps.setDouble(2, conta.getLimite());
			ps.setInt(3, conta.getNumero());
			ps.execute();
			System.out.println("Conta Atualizada com  sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao Atualizar conta no banco" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}
}
