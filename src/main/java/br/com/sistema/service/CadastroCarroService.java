package br.com.sistema.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sistema.dao.CarroDAO;
import br.com.sistema.modelo.Carro;
import br.com.sistema.util.jpa.Transactional;

public class CadastroCarroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CarroDAO carroDAO;

	@Transactional
	public void salvar(Carro carro) throws NegocioException {

		if (carro.getPlaca() == null || carro.getPlaca().trim().equals("")) {
			throw new NegocioException("A placa é obrigatória");
		}

		this.carroDAO.salvar(carro);
	}

}
