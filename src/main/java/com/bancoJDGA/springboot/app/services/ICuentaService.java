package com.bancoJDGA.springboot.app.services;

import java.util.List;

import com.bancoJDGA.springboot.app.models.entity.Cuenta;

public interface ICuentaService {
	
	public Cuenta getByIdCuenta(Long idCuenta, List<Cuenta> lista);
}
