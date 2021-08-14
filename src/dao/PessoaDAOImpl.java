package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Conta;
import model.Pessoa;
import model.util.Conexao;

public class PessoaDAOImpl {
	Conexao conexao = new Conexao();

	public void salvar(Pessoa pessoa) {
		Connection conn = conexao.getConnection();
		String sql = "INSERT INTO PESSOA(NOME, IDADE, SEXO, CPF, ID_ENDERECO, NUMERO_CONTA)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setInt(2, pessoa.getIdade());
			ps.setString(3, pessoa.getSexo());
			ps.setString(4, pessoa.getCpf());
//			ps.setEndereco(5, pessoa.getEndereco());
//			ps.setConta(6, pessoa.getConta());
			ps.execute();
			System.out.println("Pessoa inserida com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Pessoa no Banco" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void remover(int cpf) {
		Connection conn = conexao.getConnection();
		String sql = "DETELE FROM PESSOA WHERE CPF = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, cpf);
			ps.execute();
			System.out.println("Conta Deletada com sucesso");

		} catch (Exception e) {
			System.out.println("Erro ao Deletar pessoa no banco" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}
	public void alterar(Pessoa pessoa) {
		Connection conn = conexao.getConnection();
		String sql = "UPDATE PESSOA SET NOME = ?, IDADE = ?, SEXO = ?, CPF = ?, ID_ENDERECO = ?, NUMERO_CONTA = ?, WHERE CPF = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setInt(2, pessoa.getIdade());
			ps.setString(3, pessoa.getSexo());
			ps.setString(4, pessoa.getCpf());
			//ps.setEndereco(5, pessoa.getEndereco());
			//ps.setConta(6, pessoa.getConta());
			ps.execute();
			System.out.println("Pessoa Atualizada com  sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao Atualizar conta no banco" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

}
