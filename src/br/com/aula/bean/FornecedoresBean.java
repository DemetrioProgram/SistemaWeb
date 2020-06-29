package br.com.aula.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.aula.DAO.FornecedoresDAO;
import br.com.aula.domain.Fornecedores;
import br.com.aula.util.UtilJSF;

@ManagedBean
@ViewScoped
public class FornecedoresBean {

	private Fornecedores fornecedores;

	private ArrayList<Fornecedores>itens; //Trocar DataModel por Arraylist pois o Primefaces 4 não trabalha bem com DataModel.
	
	private ArrayList<Fornecedores>itensFiltrados; //Variavel para trabalhar com itens Filtrados.

	//Getters e Setters
	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	
	
	public ArrayList<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}

	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	

	@PostConstruct
	public void preparaPesquisa() {
		try {
			FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
			itens = fornecedoresDAO.listarOrdenado();
			//itens = new ArrayList<Fornecedores>(list);
		} catch (Exception e) {
			System.out.println(" Ocorreu um erro ao listar. Erro: " + e.getMessage());
		}
	}

	public void prepararNovo() {
		fornecedores = new Fornecedores();
	}

	public void novo() {

		try {
			FornecedoresDAO dao = new FornecedoresDAO();
			dao.salvar(fornecedores);

			itens = dao.listarOrdenado();
			//itens = new ListDataModel<Fornecedores>(lista);

			UtilJSF.adicionarMensagemSucesso("Fornecedor Salvo Com Sucesso!");

		} catch (SQLException e) {
			UtilJSF.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	/*public void prepararExcluir() {
		fornecedores = itens.getRowData();
		System.out.println(fornecedores);
	}*/

	public void excluir() {

		try {
			FornecedoresDAO dao = new FornecedoresDAO();
			dao.excluir(fornecedores);

			itens = dao.listarOrdenado();
			//itens = new ListDataModel<Fornecedores>(lista);

			UtilJSF.adicionarMensagemSucesso("Fornecedor Excluido Com Sucesso!");

		} catch (SQLException e) {
			UtilJSF.adicionarMensagemErro("Não é possivel excluir um fornecedor com produto associado a ele");
			e.printStackTrace();
		}

	}
	
	public void editar() {

		try {
			FornecedoresDAO dao = new FornecedoresDAO();
			dao.editar(fornecedores);

			itens = dao.listarOrdenado();
			//itens = new ListDataModel<Fornecedores>(lista);

			UtilJSF.adicionarMensagemSucesso("Fornecedor Editado Com Sucesso!");

		} catch (SQLException e) {
			UtilJSF.adicionarMensagemErro("Não é possivel editado um fornecedor com produto associado a ele");
			e.printStackTrace();
		}

	}

}
