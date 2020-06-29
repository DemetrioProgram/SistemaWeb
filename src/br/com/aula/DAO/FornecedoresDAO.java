package br.com.aula.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import br.com.aula.domain.Fornecedores;
import br.com.aula.factory.ConnectionFactory;

public class FornecedoresDAO {

	public void salvar(Fornecedores fornecedor) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?) ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql.toString());
		stmt.setString(1, fornecedor.getDescricao());

		stmt.executeUpdate();
	}

	public void excluir(Fornecedores fornecedor) throws SQLException {
System.out.println(fornecedor);
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql.toString());
		stmt.setInt(1, fornecedor.getCodigo());

		stmt.executeUpdate();
	}

	public void editar(Fornecedores fornecedor) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql.toString());
		stmt.setString(1, fornecedor.getDescricao());
		
		stmt.setInt(2, fornecedor.getCodigo());

		stmt.executeUpdate();
	}

	public Fornecedores buscarPorId(int id) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql.toString());
		
		stmt.setInt(1, id);

		ResultSet resultado = stmt.executeQuery();
		Fornecedores fornecedoresBase = null;

		if (resultado.next()) {

			fornecedoresBase = new Fornecedores();
			fornecedoresBase.setCodigo(resultado.getInt("codigo"));
			fornecedoresBase.setDescricao(resultado.getString("descricao"));
		}

		return fornecedoresBase;
	}

	public ArrayList<Fornecedores> listarOrdenado() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql.toString());

		ResultSet resultado = stmt.executeQuery();

		ArrayList<Fornecedores> fornecedores = new ArrayList<Fornecedores>();

		while (resultado.next()) {

			Fornecedores fornecedor = new Fornecedores();
			fornecedor.setCodigo(resultado.getInt("codigo"));
			fornecedor.setDescricao(resultado.getString("descricao"));

			fornecedores.add(fornecedor);
		}

		return fornecedores;
	}

	public ArrayList<Fornecedores> listarPorDescricao(Fornecedores fornecedor) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql.toString());

		stmt.setString(1, "%" + fornecedor.getDescricao() + "%");

		ResultSet resultado = stmt.executeQuery();

		ArrayList<Fornecedores> fornecedores = new ArrayList<Fornecedores>();

		while (resultado.next()) {

			Fornecedores fornecedorBase = new Fornecedores();
			fornecedorBase.setCodigo(resultado.getInt("codigo"));
			fornecedorBase.setDescricao(resultado.getString("descricao"));

			fornecedores.add(fornecedorBase);
		}

		return fornecedores;

	}
	
	/*
	//Teste
		public static void main(String[] args) {
			
			Fornecedores fornec = new Fornecedores();
			fornec.setDescricao("Fornecedor");
			
			Fornecedores fornec2 = new Fornecedores();
			fornec2.setDescricao("Fornecedor Descrito");
			
			FornecedoresDAO dao = new FornecedoresDAO();
			try {
				dao.salvar(fornec);
				dao.salvar(fornec2);
				System.out.println("Salvo com sucesso!");
			} catch (SQLException e) {
				System.out.println("Erro" + e);
				e.printStackTrace();
			}

		}*/
		
		
		/*
		//Testa Excluir
	public static void main(String[] args) {
			
			Fornecedores fornec = new Fornecedores();
			fornec.setCodigo(2);
			
			FornecedoresDAO dao = new FornecedoresDAO();
			try {
				dao.excluir(fornec);
				System.out.println("Excluido com sucesso!");
			} catch (SQLException e) {
				System.out.println("Erro" + e);
				e.printStackTrace();
			}

			
	}*/
		
		/*
		//Testa Editar
	public static void main(String[] args) {
			
			Fornecedores fornec = new Fornecedores();
			fornec.setCodigo(4);
			fornec.setDescricao("Editado");
			
			FornecedoresDAO dao = new FornecedoresDAO();
			try {
				dao.editar(fornec);
				System.out.println("Editado com sucesso!");
			} catch (SQLException e) {
				System.out.println("Erro" + e);
				e.printStackTrace();
			}

			
	}*/
	
	
	/*
	// Teste ListaPorDescricao
		public static void main(String[] args) {
			
			Fornecedores fornec = new Fornecedores();
			fornec.setDescricao("Tes");

			FornecedoresDAO dao = new FornecedoresDAO();
			try {
				
				ArrayList<Fornecedores>lista = dao.listarPorDescricao(fornec);
				for(Fornecedores fornecs : lista) {
					System.out.println("Resultado: " + fornecs);

				}

			} catch (SQLException e) {
				System.out.println("Erro" + e);
				e.printStackTrace();
			}
			
			
	}*/
	
	/*
	// Teste ListaOrdenado
		public static void main(String[] args) {
			
			Fornecedores fornec = new Fornecedores();

			FornecedoresDAO dao = new FornecedoresDAO();
			try {
				
				ArrayList<Fornecedores> lista = dao.listarOrdenado();
				for(Fornecedores fornecs : lista) {
					System.out.println("Resultado: " + fornecs);

				}

			} catch (SQLException e) {
				System.out.println("Erro" + e);
				e.printStackTrace();
			}
			
			
	}*/

}
