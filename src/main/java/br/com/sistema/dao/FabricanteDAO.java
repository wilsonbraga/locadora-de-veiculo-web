package br.com.sistema.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sistema.modelo.Fabricante;
import br.com.sistema.service.NegocioException;
import br.com.sistema.util.jpa.Transactional;



public class FabricanteDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public void salvar(Fabricante fabricante) {
		em.merge(fabricante);
	}

	public List<Fabricante> buscarTodos() {
		return em.createQuery("from Fabricante", Fabricante.class).getResultList();
	}

	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException {
		try {
			Fabricante fabricanteTemp = this.buscarPeloCodigo(fabricante.getCodigo());
		
			em.remove(fabricanteTemp);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Fabricante não pode ser excluído.");
		}
	}

	public Fabricante buscarPeloCodigo(Long codigo) {
		return em.find(Fabricante.class, codigo);
	}
	
}
