package br.com.sistema.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.sistema.dao.ModeloCarroDAO;
import br.com.sistema.modelo.ModeloCarro;
import br.com.sistema.util.jpa.Transactional;

public class CadastroModeloCarroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ModeloCarroDAO modeloCarroDAO;

	@Transactional
	public void salvar(ModeloCarro modeloCarro) throws NegocioException {
		this.modeloCarroDAO.salvar(modeloCarro);
	}

	public void validar(ModeloCarro modeloCarro) throws NegocioException {
		if (StringUtils.isBlank(modeloCarro.getDescricao())) {
			throw new NegocioException("O nome do modelo é obrigatório.");
		}

		if (modeloCarro.getFabricante() == null) {
			throw new NegocioException("O fabricante e obrigatório");
		}
	}

}
