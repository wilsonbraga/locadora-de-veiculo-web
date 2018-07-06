package br.com.sistema.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sistema.modelo.Carro;
import br.com.sistema.service.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class CarroDAO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Carro buscarPeloCodigo(Long codigo){
		return manager.find(Carro.class, codigo);
	}
	
	
	public Carro buscarComModeloPeloCodigo(Long codigo){
		return manager.createQuery("select c from Carro c inner join fetch c.modelo where c.codigo = :codigo", Carro.class)
				.setParameter("codigo", codigo)
				.getSingleResult();		
		
		// o createQuery retorna um getSingleResult
	}
	
	public void salvar(Carro carro){
		manager.merge(carro);
	}
	
	public List<Carro> buscarTodos(){
		return manager.createNamedQuery("Carro.buscarTodos", Carro.class).getResultList();
		//obs createNamedQuery retorna um getResultList
	}
	
	@Transactional
	public void excluir(Carro carro) throws NegocioException{
		
		carro = buscarPeloCodigo(carro.getCodigo());
		
		try {
			manager.remove(carro);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Carro não pode ser excluído.");
		}
	}
	
	public Carro buscarAcessorios(Long codigo){
		return manager.createNamedQuery("Carro.buscarCarroComAcessorios", Carro.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
		//obs createNamedQuery retorna um getResultList
	}
	
	public List<Carro> buscarComPaginacao(int first, int pageSize){
		return manager.createNamedQuery("Carro.buscarTodos", Carro.class)
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
		//obs createNamedQuery retorna um getResultList
	}
	
	
	public Long encontrarQuantidadeDeCarros(){
		return manager.createQuery("select count(c) from Carro c", Long.class).getSingleResult();
		// o createQuery retorna um getSingleResult
	}

}
