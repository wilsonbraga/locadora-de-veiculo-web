package br.com.sistema.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.dao.FabricanteDAO;
import br.com.sistema.modelo.Fabricante;
import br.com.sistema.service.NegocioException;
import br.com.sistema.util.jsf.FacesMessages;



@Named
@ViewScoped
public class PesquisaFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FabricanteDAO fabricanteDAO;
	
	private List<Fabricante> fabricantes;
	
	private Fabricante fabricanteSelecionado;
	
	@Inject
	private FacesMessages facesMessages;
	
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public void excluir() {
		try {
			fabricanteDAO.excluir(fabricanteSelecionado);
			this.fabricantes.remove(fabricanteSelecionado);
			facesMessages.info("Fabricante " + fabricanteSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
	}

	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}
	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}
	
	public void inicializar() {
		fabricantes = fabricanteDAO.buscarTodos();
	}
}