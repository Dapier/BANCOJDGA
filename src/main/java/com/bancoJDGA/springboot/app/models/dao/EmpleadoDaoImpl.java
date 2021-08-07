package com.bancoJDGA.springboot.app.models.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.DataException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bancoJDGA.springboot.app.errors.DataBaseBancoException;
import com.bancoJDGA.springboot.app.models.entity.Empleado;


@Repository
public class EmpleadoDaoImpl implements IEmpleadoDao {

	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Empleado> findAll() {
		
		return em.createQuery("from Empleado").getResultList();
	}

	@Override
	@Transactional
	public void save(Empleado empleado) throws DataBaseBancoException {
		if(empleado.getIdEmpleado() !=null) {
			try {
				em.merge(empleado);
			}catch(DataException e) {
				throw new DataBaseBancoException();
			}
			
		}else {
			try {
				em.persist(empleado);
			}catch(DataException e) {
				throw new DataBaseBancoException();
			}
		}

	}
	
	@Override
	@Transactional(readOnly = true)
	public Empleado findOne(String idEmpleado) {
		return em.find(Empleado.class, idEmpleado);
	}
	
	@Override
	@Transactional
	public void delete(String idEmpleado) {
		em.remove(findOne(idEmpleado));
	}
	
	//Evidencia 12. Buscar mediante ID y puesto
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findEmpleado(String idEmpleado, String puesto)
	{
		
		List<Empleado> empleados = new ArrayList<Empleado>();
		for(Empleado empleado : this.findAll()) {
			if(empleado.getIdEmpleado().equals(idEmpleado) && empleado.getPuesto().equals(puesto)) {
				empleados.add(empleado);
			}
		}
		
		
		return empleados;
	}
	
	
	
	

}
