package br.com.sistema.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.modelo.Funcionario;
import br.com.sistema.modelo.Sexo;
import br.com.sistema.service.CadastroFuncionarioService;
import br.com.sistema.service.NegocioException;
import br.com.sistema.util.jsf.FacesMessages;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	
	@Inject
	private CadastroFuncionarioService cadastroFuncionarioService;
	
	private List<Sexo> sexos;
	
	@Inject
	private FacesMessages facesMessages;
	
	public void inicializar() {
		if (this.funcionario == null) {
			this.limpar();
		}
		
		this.sexos = Arrays.asList(Sexo.values());
	}
	
	public void salvar() {
		try {
			this.cadastroFuncionarioService.salvar(funcionario);
			facesMessages.info("Funcionário salvo com sucesso!");
			this.limpar();
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
	}
	
	public void limpar() {
		this.funcionario = new Funcionario();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public List<Sexo> getSexos() {
		return sexos;
	}
	
	public boolean isEditando() {
		return this.funcionario.getCodigo() != null;
	}

}
