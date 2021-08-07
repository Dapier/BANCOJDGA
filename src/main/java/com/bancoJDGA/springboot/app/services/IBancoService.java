package com.bancoJDGA.springboot.app.services;

import java.util.List;

import com.bancoJDGA.springboot.app.models.entity.Banco;

public interface IBancoService {
	public Banco getById(Long id, List<Banco> listaBancos);
}
