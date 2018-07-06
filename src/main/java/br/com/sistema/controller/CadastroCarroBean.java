package br.com.sistema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.sistema.dao.AcessorioDAO;
import br.com.sistema.dao.ModeloCarroDAO;
import br.com.sistema.modelo.Acessorio;
import br.com.sistema.modelo.Carro;
import br.com.sistema.modelo.ModeloCarro;
import br.com.sistema.service.CadastroCarroService;
import br.com.sistema.service.NegocioException;
import br.com.sistema.util.jsf.FacesMessages;

@Named
@ViewScoped
public class CadastroCarroBean  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Carro carro;

	private List<ModeloCarro> modelosCarros;

	private List<Acessorio> acessorios;
	
	@Inject
	private CadastroCarroService cadastroCarroService;

	@Inject
	private AcessorioDAO acessorioDAO;
	
	@Inject
	private ModeloCarroDAO modeloCarroDAO;

	private UploadedFile fotoCarro;
	
	@Inject
	private FacesMessages facesMessages;
	
	
	public CadastroCarroBean() {
		this.limpar();
	}
	
	public void inicializar(){
		if (this.carro == null) {
			this.limpar();
		}

		this.acessorios = acessorioDAO.buscarTodos();
		this.modelosCarros = this.modeloCarroDAO.buscarTodos();
	}
	
	public void salvar() {
		try {
			
			if (this.fotoCarro != null && this.fotoCarro.getContents() != null 
					&& this.fotoCarro.getContents().length > 0) {
				this.carro.setFoto(this.fotoCarro.getContents());
			}
			
			this.cadastroCarroService.salvar(carro);
			facesMessages.info("Carro salvo com sucesso.");
			
			this.limpar();
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		} 
	}

	private void limpar() {
		this.carro = new Carro();
		this.carro.setAcessorios(new ArrayList<Acessorio>());

	}
	
	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public List<ModeloCarro> getModelosCarros() {
		return modelosCarros;
	}
	
	public UploadedFile getFotoCarro() {
		return fotoCarro;
	}

	public void setFotoCarro(UploadedFile fotoCarro) {
		this.fotoCarro = fotoCarro;
	}
	
	public boolean isEditando() {
		return this.carro.getCodigo() != null;
	}

}
