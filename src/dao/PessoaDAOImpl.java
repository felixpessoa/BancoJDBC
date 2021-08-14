package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Pessoa;
import model.util.Conexao;

public class PessoaDAOImpl {
	Conexao conexao = new Conexao();
	EnderecoDAOImpl enderecoDAO = new EnderecoDAOImpl();
	ContaDAOImpl contaDAO = new ContaDAOImpl();

	public void salvar(Pessoa pessoa) {
		Connection conn = conexao.getConnection();
		String sql = "INSERT INTO PESSOA(NOME, IDADE, SEXO, CPF, ID_ENDERECO, NUMERO_CONTA)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			this.contaDAO.salvar(pessoa.getConta());
			pessoa.getEndereco().setId(this.enderecoDAO.getSequence());
			this.enderecoDAO.salvar(pessoa.getEndereco());
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setInt(2, pessoa.getIdade());
			ps.setString(3, pessoa.getSexo());
			ps.setString(4, pessoa.getCpf());
			ps.setInt(5, pessoa.getEndereco().getId());
			ps.setInt(6, pessoa.getConta().getNumero());
			ps.execute();
			System.out.println("Pessoa inserida com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Pessoa no Banco de dados" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void remover(String cpf) {
		Connection conn = conexao.getConnection();
		String sql = "DETELE FROM PESSOA WHERE CPF = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
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
			ps.setInt(5, pessoa.getEndereco().getId());
			ps.setInt(6, pessoa.getConta().getNumero());
			ps.execute();
			System.out.println("Pessoa Atualizada com  sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao Atualizar conta no banco" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

}
