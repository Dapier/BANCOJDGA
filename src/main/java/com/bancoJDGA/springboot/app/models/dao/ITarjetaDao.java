package com.bancoJDGA.springboot.app.models.dao;

import java.util.List;

import com.bancoJDGA.springboot.app.models.entity.Tarjeta;

public interface ITarjetaDao {
	public List<Tarjeta> findAll();
	
	public void save(Tarjeta tarjeta);
	
	public Tarjeta findOne(String idTarjeta);
	
	public void delete(String idTarjeta);
	
	
	//Evidencia 12. Buscar mediante ID y numero de cuenta
	public List<Tarjeta> findTarjeta(String idTarjeta, String numeroDeCuenta);

}
