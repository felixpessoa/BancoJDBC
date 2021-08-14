package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Endereco;
import model.util.Conexao;

public class EnderecoDAOImpl {
	static Conexao conexao = new Conexao();

	public int getSequence() {
		Connection conn = conexao.getConnection();
		Integer retorno = null;

		String sql = "SELECT S_ID_ENDERECO.NEXTVAL AS SEQUENCE FROM DUAL";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				retorno = rs.getInt("SEQUENCE");
			}
		} catch (Exception e) {
			System.out.println("Erro ao sequence" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
		return retorno;
	}

	public void salvar(Endereco endereco) {
		Connection conn = conexao.getConnection();
		String sql = "INSERT INO ENDERECO(ID_ENDERECO, RUA, NUMERO, COMPLEMENTO)" + "VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, getSequence());
			ps.setString(2, endereco.getLogradouro());
			ps.setInt(3, endereco.getNumero());
			ps.setString(4, endereco.getComplemento());
			ps.execute();
			System.out.println("Endereço inserido com sucesso");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Endereço no banco de dados" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void remover(int id) {
		Connection conn = conexao.getConnection();
		String sql = "DELETE FROM CONTA WHERE ID_ENDERECO = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			System.out.println("Endereço Deletada com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao Deletar Endereço no banco" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void alterar(Endereco endereco) {
		Connection conn = conexao.getConnection();
		String sql = "UDATE ENDERECO SET RUA = ?, NUMERO = ?, COMPLEMENTO = ?" + "WHIRE ID_ENDERECO = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, endereco.getLogradouro());
			ps.setInt(2, endereco.getNumero());
			ps.setString(3, endereco.getComplemento());
			ps.setInt(4, endereco.getId());
			ps.executeUpdate();
			System.out.println("Endereço atualizado com sucesso");
		} catch (Exception e) {
			System.out.println("erro ao atualizar endereço no banco" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public static Endereco pesquisar(Integer id) {
		Connection conn = conexao.getConnection();
		Endereco endereco = new Endereco();
		String sql = "SELECT * FROM ENDERECO WHERE ID_ENDERECO = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				endereco.setComplemento(rs.getString("COMPLEMENTO"));
				endereco.setId(rs.getInt("ID_ENDERECO"));
				endereco.setNumero(rs.getInt("NUMERO"));
				endereco.setLogradouro(rs.getString("RUA"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar endereco -" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
		return endereco;
	}

	public List<Endereco> listarTodos() {
		Connection conn = conexao.getConnection();
		List<Endereco> retorno = new ArrayList<Endereco>();
		String sql = "SELECT * FROM ENDERECO";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setComplemento(rs.getString("COMPLEMENTO"));
				endereco.setId(rs.getInt("ID_ENDERECO"));
				endereco.setNumero(rs.getInt("NUMERO"));
				endereco.setLogradouro(rs.getString("RUA"));
				retorno.add(endereco);
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar endereço -" + e.getLocalizedMessage());
		}
		return retorno;
	}
}