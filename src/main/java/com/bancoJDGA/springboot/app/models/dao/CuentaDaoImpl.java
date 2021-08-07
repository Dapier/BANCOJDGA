package com.bancoJDGA.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bancoJDGA.springboot.app.models.entity.Cuenta;


@Repository
public class CuentaDaoImpl implements ICuentaDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Cuenta> findAll() {
		
		return em.createQuery("from Cuenta").getResultList();
	}

	@Override
	@Transactional
	public void save(Cuenta cuenta) {
		if(cuenta.getIdCuenta() != null && cuenta.getIdCuenta() > 0) {
			em.merge(cuenta);
		}else {
			em.persist(cuenta);
			
		}
		
	}
	
	@Override
	@Transactional
	public Cuenta findOne(Long idCuenta) {
		return em.find(Cuenta.class, idCuenta);
	}
	
	@Override
	@Transactional
	public void delete(Long idCuenta) {
		em.remove(findOne(idCuenta));
	}

}
