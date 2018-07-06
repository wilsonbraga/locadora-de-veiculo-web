package br.com.sistema.service;

import java.io.Serializable;
import java.util.Calendar;

import javax.inject.Inject;

import br.com.sistema.dao.AluguelDAO;
import br.com.sistema.modelo.Aluguel;
import br.com.sistema.util.jpa.Transactional;

public class CadastroAluguelService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AluguelDAO aluguelDAO;

	@Transactional
	public void salvar(Aluguel aluguel) throws NegocioException {

		if (aluguel.getCarro() == null) {
			throw new NegocioException("O carro é obrigatório");
		}

		aluguel.setDataPedido(Calendar.getInstance());

		this.aluguelDAO.salvar(aluguel);
	}
}
