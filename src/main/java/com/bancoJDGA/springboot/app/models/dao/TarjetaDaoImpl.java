package com.bancoJDGA.springboot.app.models.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bancoJDGA.springboot.app.models.entity.Tarjeta;

@Repository
public class TarjetaDaoImpl implements ITarjetaDao {

	
	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Tarjeta> findAll() {
		return em.createQuery("from Tarjeta").getResultList();
	}

	@Override
	@Transactional
	public void save(Tarjeta tarjeta) {
		if(tarjeta.getIdTarjeta() != null) {
			em.merge(tarjeta);
		}else {
			em.persist(tarjeta);
		}

	}
	
	@Override
	@Transactional
	public Tarjeta findOne(String idTarjeta) {
		return em.find(Tarjeta.class, idTarjeta);
	}
	
	@Override
	@Transactional
	public void delete(String idTarjeta) {
		em.remove(findOne(idTarjeta));
	}
	
	//Evidencia 12. Buscar mediante ID y numero de cuenta
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Tarjeta> findTarjeta(String idTarjeta, String numeroDeCuenta)
	{
		List<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
		for(Tarjeta tarjeta : this.findAll()) {
			if(tarjeta.getIdTarjeta().equals(idTarjeta) && tarjeta.getNumeroDeCuenta().equals(numeroDeCuenta)) {
				tarjetas.add(tarjeta);
			}
		}
		
		return tarjetas;
	}
	
	
	
	
	

}
