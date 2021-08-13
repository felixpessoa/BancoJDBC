package controle;

import dao.ContaDAOImpl;
import dao.PessoaDAOImpl;
import model.Pessoa;

public class Principal {

	public static void main(String[] args) {

//		Conexao conexao = new Conexao();
//		Connection conn = conexao.getConnection();

//		Conta conta = new Conta();
//		conta.setNumero(1001);
//		conta.setSaldo(0);
//		conta.setLimite(3000d);

		Pessoa p1 = new Pessoa();
		p1.setNome("felix");
		p1.setIdade(23);
		p1.setSexo("M");
		p1.setCpf("70626303494");
		p1.setEndereco(null);
		p1.setConta(null);
		
		PessoaDAOImpl pessoaDAO= new PessoaDAOImpl();
		pessoaDAO.salvar(p1);
	
//		ContaDAOImpl contaDAO = new ContaDAOImpl();
//		contaDAO.alterar(123);

	}

}
