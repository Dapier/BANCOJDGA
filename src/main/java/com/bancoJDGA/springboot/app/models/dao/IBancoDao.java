package com.bancoJDGA.springboot.app.models.dao;

import java.util.List;

import com.bancoJDGA.springboot.app.models.entity.Banco;


public interface IBancoDao {
	public List<Banco> findAll();
	
	public void save(Banco banco);
	
	public Banco findOne(Long id);
	
	public void delete(Long id);
}
