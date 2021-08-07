package com.bancoJDGA.springboot.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bancoJDGA.springboot.app.models.dao.ICasaInversionistaDao;
import com.bancoJDGA.springboot.app.services.ICasaInversoraService;

@Component
public class CasaInversoraPropertyEditor extends PropertyEditorSupport {
	@Autowired
	private ICasaInversoraService casaService;
	
	@Autowired
	private ICasaInversionistaDao casaDao;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			Long id = Long.parseLong(text);
			this.setValue(casaService.getById(id, casaDao.findAll()));
		} catch (Exception e) {
			System.out.println("hubo un error al asignar el objeto casa inversora a la cuenta");
		}
	}
	
	
	
	
	
}
