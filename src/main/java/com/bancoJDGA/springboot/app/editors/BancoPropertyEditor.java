package com.bancoJDGA.springboot.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bancoJDGA.springboot.app.models.dao.IBancoDao;
import com.bancoJDGA.springboot.app.services.IBancoService;

@Component
public class BancoPropertyEditor extends PropertyEditorSupport{

	@Autowired
	private IBancoService bancoService;
	
	@Autowired
	private IBancoDao bancoDao;
	
	
	
	@Override
	public void setAsText(String idStr) throws IllegalArgumentException {
		try {
			//Checar
			
			Long id = Long.parseLong(idStr);
			this.setValue(bancoService.getById(id, bancoDao.findAll()));
			
		} catch (Exception e) {
			System.out.println("Error al asignar el banco, error de relacion entre empleado-banco");
		}
	}
	
	
	
	
}
