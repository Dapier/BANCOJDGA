package com.bancoJDGA.springboot.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancoJDGA.springboot.app.models.entity.Banco;


@Service
public class BancoServiceImpl implements IBancoService {

	private List<Banco> listaBancos;
	
	public BancoServiceImpl() {
		
	}
	
	@Override
	public Banco getById(Long id, List<Banco> listaBancos) {
		this.listaBancos = listaBancos;
		Banco bancoResult = null;
		
		for(Banco banco : this.listaBancos) {
			if(id == banco.getId() ) {
				bancoResult = banco;
				break;
			}
		}
		
		return bancoResult;
	}

}
