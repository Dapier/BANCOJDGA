package com.bancoJDGA.springboot.app.models.dao;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.transaction.annotation.Transactional;

import com.bancoJDGA.springboot.app.models.entity.Banco;


@Repository
public class BancoDaoImpl implements IBancoDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Banco> findAll() {
		//Retorona la lista de Banco
		return em.createQuery("from Banco").getResultList();
	}

	@Override
	@Transactional
	public void save(Banco banco) {
		if(banco.getId() != null && banco.getId() > 0) {
			em.merge(banco);
		}else {
			em.persist(banco);
		}
		

	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Banco findOne(Long id) {
		return em.find(Banco.class, id);
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}
	
	
	

}
