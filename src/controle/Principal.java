package controle;

import dao.EnderecoDAOImpl;
import model.Endereco;

public class Principal {

	public static void main(String[] args) {

//		Conexao conexao = new Conexao();
//		Connection conn = conexao.getConnection();

//		Conta conta = new Conta();
//		conta.setNumero(1001);
//		conta.setSaldo(0);
//		conta.setLimite(3000d);

//		Pessoa p1 = new Pessoa();
//		p1.setNome("Fernando");
//		p1.setIdade(23);
//		p1.setSexo("M");
//		p1.setCpf("12345678919");
//		p1.getEndereco().setId(42);
//		p1.getConta().setNumero(127);
		
		
//		PessoaDAOImpl pessoaDAO= new PessoaDAOImpl();
//		pessoaDAO.salvar(p1);
		
		EnderecoDAOImpl endereco = new EnderecoDAOImpl();
		Endereco endereco1 = EnderecoDAOImpl.pesquisar(43);
		System.out.println(endereco1.toString());
	
//		ContaDAOImpl contaDAO = new ContaDAOImpl();
//		contaDAO.alterar(123);

	}

}
