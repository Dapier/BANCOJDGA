package com.bancoJDGA.springboot.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bancoJDGA.springboot.app.models.dao.ICuentaDao;
import com.bancoJDGA.springboot.app.services.ICuentaService;

@Component
public class CuentaPropertyEditor extends PropertyEditorSupport {
	
	@Autowired
	private ICuentaService cuentaService;
	
	@Autowired
	private ICuentaDao cuentaDao;

	@Override
	public void setAsText(String text) throws IllegalArgumentException{
		try {
			Long idCuenta = Long.parseLong(text);
			this.setValue(cuentaService.getByIdCuenta(idCuenta, cuentaDao.findAll()));
			
			
		} catch (Exception e) {
			System.out.println("hubo un error al asignar el objeto cuenta a la tarjeta");
		}
	
	}
	
	
	
}
