package com.bancoJDGA.springboot.app.services;

import java.util.List;

import com.bancoJDGA.springboot.app.models.entity.CasaInversionista;

public interface ICasaInversoraService {
	public CasaInversionista getById(Long idOferta, List<CasaInversionista> listaCasaInversora);
}
