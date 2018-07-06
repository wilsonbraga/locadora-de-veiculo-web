package br.com.sistema.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.dao.FabricanteDAO;
import br.com.sistema.modelo.Categoria;
import br.com.sistema.modelo.Fabricante;
import br.com.sistema.modelo.ModeloCarro;
import br.com.sistema.service.CadastroModeloCarroService;
import br.com.sistema.service.NegocioException;
import br.com.sistema.util.jsf.FacesMessages;
@Named
@ViewScoped
public class CadastroModeloCarroBean  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private ModeloCarro modeloCarro;
	
	private List<Fabricante> fabricantes;
	
	@Inject
	private CadastroModeloCarroService cadastroModeloCarroService;
	
	@Inject
	private FabricanteDAO fabricanteDAO;
	
	private List<Categoria> categorias;
	
	@Inject
	private FacesMessages facesMessages;
	
	public void salvar(){
		try {
			this.cadastroModeloCarroService.salvar(modeloCarro);
			facesMessages.info("Modelo carro salvo com sucesso!");
			this.limpar();
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
	}
	
	public void inicializar(){
		if(this.modeloCarro == null){
			this.limpar();
		}
		this.fabricantes = fabricanteDAO.buscarTodos();
		this.categorias = Arrays.asList(Categoria.values());
	}
	
	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public boolean isEditando() {
		return this.modeloCarro.getCodigo() != null;
	}
	
	private void limpar() {
		this.modeloCarro = new ModeloCarro();
	}

}
