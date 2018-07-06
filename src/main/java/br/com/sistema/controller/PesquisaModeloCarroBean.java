package br.com.sistema.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.dao.ModeloCarroDAO;
import br.com.sistema.modelo.ModeloCarro;
import br.com.sistema.service.NegocioException;
import br.com.sistema.util.jsf.FacesMessages;

@Named
@ViewScoped
public class PesquisaModeloCarroBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private List<ModeloCarro> modelosCarro;
	
	private ModeloCarro modeloCarroSelecionado;
	
	@Inject
	ModeloCarroDAO modeloCarroDAO;
	
	@Inject
	private FacesMessages facesMessages;
	
	
	public List<ModeloCarro> getModelosCarro() {
		return modelosCarro;
	}
	
	public void inicializar(){
		this.modelosCarro = this.modeloCarroDAO.buscarTodos();
	}
	
	public void excluir(){
		try {
			modeloCarroDAO.excluir(modeloCarroSelecionado);
			this.modelosCarro.remove(modeloCarroSelecionado);
			facesMessages.info("Modelo " + modeloCarroSelecionado.getDescricao() + " excluído com sucesso.");
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
	}
	
	public ModeloCarro getModeloCarroSelecionado() {
		return modeloCarroSelecionado;
	}
	
	public void setModeloCarroSelecionado(ModeloCarro modeloCarroSelecionado) {
		this.modeloCarroSelecionado = modeloCarroSelecionado;
	}
	
}
