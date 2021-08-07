package com.bancoJDGA.springboot.app.models.dao;

import java.util.List;

import com.bancoJDGA.springboot.app.models.entity.Cliente;


public interface IClienteDao {
	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	

	
	public Cliente findOne(String idUser);
	
	
	
	public void delete(String idUser);
	
	
	//Evidencia 12. Buscar mediante ID y puesto
	public List<Cliente> findClient(String numeroTelefonico, String idUser);
	
}
