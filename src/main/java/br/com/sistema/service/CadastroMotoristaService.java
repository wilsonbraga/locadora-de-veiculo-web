package br.com.sistema.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sistema.dao.MotoristaDAO;
import br.com.sistema.modelo.Motorista;
import br.com.sistema.util.jpa.Transactional;

public class CadastroMotoristaService  implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Inject
	private MotoristaDAO motoristaDAO;
	
	@Transactional
	public void salvar(Motorista motorista) throws NegocioException{
		this.motoristaDAO.salvar(motorista);
	}

}
