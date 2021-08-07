package com.bancoJDGA.springboot.app.models.dao;

import java.util.ArrayList;
import java.util.List;

import com.bancoJDGA.springboot.app.models.entity.Cliente;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ClienteDaoImpl implements IClienteDao {

	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Cliente> findAll() {
		
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		if(cliente.getIdUser() != null) {
			em.merge(cliente);
		}else {
			em.persist(cliente);
		}

	}

	
	
	@Override
	@Transactional
	public Cliente findOne(String idUser) {
		return em.find(Cliente.class, idUser);
	}
	
	
	//Evidencia 12. Buscar mediante ID y numero de telefono
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findClient(String numeroTelefonico, String idUser) {
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		for(Cliente cliente : this.findAll()) {
			if(cliente.getNumeroTelefonico().equals(numeroTelefonico) && cliente.getIdUser().equals(idUser) ) {
				clientes.add(cliente);
			}
		}
		return clientes;
	}
	
	
	@Override
	@Transactional
	public void delete(String idUser) {
		em.remove(findOne(idUser));
	}

	
	
	
}
