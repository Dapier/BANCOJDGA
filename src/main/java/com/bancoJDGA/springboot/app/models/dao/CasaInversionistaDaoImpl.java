package com.bancoJDGA.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bancoJDGA.springboot.app.models.entity.CasaInversionista;


@Repository
public class CasaInversionistaDaoImpl implements ICasaInversionistaDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<CasaInversionista> findAll() {
		return em.createQuery("from CasaInversionista").getResultList();
	}

	
	@Override
	@Transactional
	public void save(CasaInversionista casaInversionista) {
		if(casaInversionista.getIdOferta() !=null) {
			em.merge(casaInversionista);
		}else {
			em.persist(casaInversionista);
		}
	}
	
	
	@Transactional
	public CasaInversionista findOne(Long idOferta) {
		return em.find(CasaInversionista.class, idOferta);
	}
	
	
	@Transactional
	public void delete(Long idOferta) {
		em.remove(findOne(idOferta));
	}


	

}
