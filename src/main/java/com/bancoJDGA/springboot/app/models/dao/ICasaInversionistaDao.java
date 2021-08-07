package com.bancoJDGA.springboot.app.models.dao;

import java.util.List;

import com.bancoJDGA.springboot.app.models.entity.CasaInversionista;

public interface ICasaInversionistaDao {
	public List<CasaInversionista> findAll();
	
	public void save(CasaInversionista casaInversionista);
	
	public CasaInversionista findOne(Long idOferta);
	
	public void delete(Long idOferta);

}
