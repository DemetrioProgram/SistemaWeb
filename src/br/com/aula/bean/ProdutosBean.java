package br.com.aula.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.aula.DAO.FornecedoresDAO;
import br.com.aula.DAO.ProdutosDAO;
import br.com.aula.domain.Fornecedores;
import br.com.aula.domain.Produtos;
import br.com.aula.util.UtilJSF;

@ManagedBean
@ViewScoped
public class ProdutosBean {

	private Produtos produto;

	private ArrayList<Produtos> produtos;

	private ArrayList<Fornecedores> comboFornecedores;

	private ArrayList<Produtos> produtosFiltrados;

	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}

	public ArrayList<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produtos> produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public ArrayList<Produtos> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(ArrayList<Produtos> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {

			ProdutosDAO dao = new ProdutosDAO();
			produtos = dao.listarOrdenado();
			
//			FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
//			comboFornecedores = fornecedoresDAO.listarOrdenado();

		} catch (Exception e) {
			System.out.println("Erro ao Preparar Pesquisa" + e.getMessage());

		}
	}

	public void prepararNovo() {
		try {

			produto = new Produtos();

			FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
			comboFornecedores = fornecedoresDAO.listarOrdenado();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			ProdutosDAO dao = new ProdutosDAO();
			dao.salvar(produto);

			produtos = dao.listarOrdenado();

			UtilJSF.adicionarMensagemSucesso("Fornecedores Salvo Com Sucessor");

		} catch (SQLException e) {
			System.out.println("Erro ao Preparar Pesquisa" + e.getMessage());
		}
	}

	public void excluir() {
		try {
			ProdutosDAO produtosExcluir = new ProdutosDAO();
			produtosExcluir.excluir(produto);
			produtos = produtosExcluir.listarOrdenado();
			
			UtilJSF.adicionarMensagemSucesso("Produto Excluido Com Sucesso!");
			
		} catch (Exception e) {
			System.out.println("Erro ao Excluir Pesquisa" + e.getMessage());
		}

	}

	public void editar() {
		try {
			ProdutosDAO dao = new ProdutosDAO();
			dao.editar(produto);

			produtos = dao.listarOrdenado();

			UtilJSF.adicionarMensagemSucesso("Produto Editado Com Sucesso!");

		} catch (SQLException e) {
			System.out.println("Erro ao Editar Pesquisa" + e.getMessage());
		}
	}
}
