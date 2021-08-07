package com.bancoJDGA.springboot.app.models.dao;

import java.util.List;

import com.bancoJDGA.springboot.app.models.entity.Empleado;

public interface IEmpleadoDao {
	public List<Empleado> findAll();
	
	public void save(Empleado empleado);
	
	public Empleado findOne(String idEmpleado);
	
	public void delete(String idEmpleado);
	
	//Evidencia 12. Buscar mediante ID y numero de telefono
	public List<Empleado> findEmpleado(String idEmpleado, String puesto);
	
}
