package com.bancoJDGA.springboot.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancoJDGA.springboot.app.models.entity.Cuenta;

@Service
public class CuentaServiceImpl implements ICuentaService {
	
	private List<Cuenta> lista;
	
	public CuentaServiceImpl() {
		
	}
	
	public Cuenta getByIdCuenta(Long idCuenta, List<Cuenta> lista) {
		this.lista = lista;
		Cuenta cuentaResult = null;
		
		for(Cuenta cuenta : this.lista) {
			if(idCuenta == cuenta.getIdCuenta()) {
				cuentaResult = cuenta;
				break;
			}
		}
		return cuentaResult;
	}
	
}
