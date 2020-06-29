package br.com.aula.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import br.com.aula.domain.Fornecedores;
import br.com.aula.domain.Produtos;
import br.com.aula.factory.ConnectionFactory;

public class ProdutosDAO {

	public void salvar(Produtos produto) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos ");
		sql.append("(descricao, quantidade, preco, codigo_fornecedor) ");
		sql.append("VALUES (?, ?, ?, ?) ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql.toString());
		stmt.setString(1, produto.getDescricao());
		stmt.setInt(2, produto.getQuantidade());
		stmt.setDouble(3, produto.getPreco());
		stmt.setInt(4, produto.getFornecedor().getCodigo());

		stmt.executeUpdate();
	}

	public void excluir(Produtos produto) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql.toString());
		stmt.setInt(1, produto.getCodigo());

		stmt.executeUpdate();
	}

	public void editar(Produtos produto) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append("SET descricao = ?, quantidade = ?, preco = ? ");
		sql.append("WHERE codigo = ? ");
System.out.println(produto);
		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql.toString());
		stmt.setString(1, produto.getDescricao());
		stmt.setInt(2, produto.getQuantidade());
		stmt.setDouble(3, produto.getPreco());
		stmt.setInt(4, produto.getCodigo());

		stmt.executeUpdate();
	}

	public Produtos buscarPorId(Produtos produto) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM produtos ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql.toString());

		ResultSet resultado = stmt.executeQuery();
		Produtos produtoBase = null;

		if (resultado.next()) {

			produtoBase = new Produtos();
			produtoBase.setCodigo(resultado.getInt("codigo"));
			produtoBase.setDescricao(resultado.getString("descricao"));
		}

		return produtoBase;
	}

	public ArrayList<Produtos> listarOrdenado() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM produtos ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql.toString());

		ResultSet resultado = stmt.executeQuery();

		ArrayList<Produtos> produtos = new ArrayList<Produtos>();

		while (resultado.next()) {

			Produtos produto = new Produtos();
			FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
			Fornecedores fornecedor = new Fornecedores();
			produto.setCodigo(resultado.getInt("codigo"));
			produto.setDescricao(resultado.getString("descricao"));
			produto.setPreco(resultado.getDouble("preco"));
			produto.setQuantidade(resultado.getInt("quantidade"));
			fornecedor = fornecedoresDAO.buscarPorId(resultado.getInt("codigo_fornecedor"));
			produto.setFornecedor(fornecedor);

			produtos.add(produto);
		}
		System.out.println(produtos);
		return produtos;
	}

	public ArrayList<Produtos> listarPorDescricao(Produtos produto) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM produtos ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement stmt = (PreparedStatement) conexao.prepareStatement(sql.toString());

		stmt.setString(1, "%" + produto.getDescricao() + "%");

		ResultSet resultado = stmt.executeQuery();

		ArrayList<Produtos> produtos = new ArrayList<Produtos>();

		while (resultado.next()) {

			Produtos produtoBase = new Produtos();
			FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
			Fornecedores fornecedor = new Fornecedores();
			produtoBase.setCodigo(resultado.getInt("codigo"));
			produtoBase.setDescricao(resultado.getString("descricao"));
			produtoBase.setPreco(resultado.getDouble("preco"));
			produtoBase.setQuantidade(resultado.getInt("quantidade"));
			fornecedor = fornecedoresDAO.buscarPorId(resultado.getInt("codigo_fornecedor"));
			produtoBase.setFornecedor(fornecedor);

			produtos.add(produtoBase);
		}

		return produtos;

	}

}