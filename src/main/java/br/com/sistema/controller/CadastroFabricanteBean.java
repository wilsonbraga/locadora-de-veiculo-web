package br.com.sistema.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.modelo.Fabricante;
import br.com.sistema.service.CadastroFabricanteService;
import br.com.sistema.service.NegocioException;
import br.com.sistema.util.jsf.FacesMessages;



@Named
@ViewScoped
public class CadastroFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroFabricanteService cadastroFabricanteService;
	
	private Fabricante fabricante;
	
	@Inject
	private FacesMessages facesMessages;
	
	public void inicializar() {
		if (this.fabricante == null) {
			limpar();
		}
	}
	
	public void salvar() {
		try {
			cadastroFabricanteService.salvar(fabricante);
			facesMessages.info("Fabricante salvo com sucesso!");
			
			limpar();
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
	}
	
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	public boolean isEditando() {
		return this.fabricante.getCodigo() != null;
	}
	
	private void limpar() {
		this.fabricante = new Fabricante();
	}
	
}
