package br.com.sistema.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.modelo.Acessorio;
import br.com.sistema.service.CadastroAcessorioService;
import br.com.sistema.service.NegocioException;
import br.com.sistema.util.jsf.FacesMessages;

@Named
@ViewScoped
public class CadastroAcessorioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Acessorio acessorio;

	@Inject
	private CadastroAcessorioService cadastroAcessorioService;

	@Inject
	private FacesMessages facesMessages;

	public void inicializar() {
		if (this.acessorio == null) {
			this.limpar();
		}
	}

	public void salvar() {
		try {
			this.cadastroAcessorioService.salvar(acessorio);
			facesMessages.info("Acessório salvo com sucesso!");
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
	}

	public void limpar() {
		this.acessorio = new Acessorio();
	}

	public Acessorio getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(Acessorio acessorio) {
		this.acessorio = acessorio;
	}

	public boolean isEditando() {
		return this.acessorio.getCodigo() != null;
	}
}
