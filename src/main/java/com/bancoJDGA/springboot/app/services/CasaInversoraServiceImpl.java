package com.bancoJDGA.springboot.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancoJDGA.springboot.app.models.entity.CasaInversionista;

@Service
public class CasaInversoraServiceImpl implements ICasaInversoraService{
	private List<CasaInversionista> listaCasaInversora;
	
	public CasaInversoraServiceImpl() {
		
	}
	
	@Override
	public CasaInversionista getById(Long idOferta, List<CasaInversionista> listaCasaInversora)
	{
		this.listaCasaInversora = listaCasaInversora;
		
		CasaInversionista casaResult = null;
		
		for(CasaInversionista casaInversionista : this.listaCasaInversora) {
			if(idOferta == casaInversionista.getIdOferta()) {
				casaResult = casaInversionista;
				break;
			}
		}
		return casaResult;
	}
}
