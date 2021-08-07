package com.bancoJDGA.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bancoJDGA.springboot.app.models.entity.Banco;

@Component
public class BancoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Banco.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Banco banco = (Banco) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sucursal", "NotEmpty.banco.sucursal");
	
		if(!banco.getSucursal().matches("[a-z,A-Z]{1,10}?[ ]?[a-z,A-Z]{1,15}")){
			errors.rejectValue("sucursal", "Format.banco.sucursal");
		}
		
		if(!banco.getUbicacion().matches("[a-z,A-Z]{1,10}?[ ]?[a-z,A-Z]{1,15}")){
			errors.rejectValue("ubicacion", "Format.banco.ubicacion");
		}
		
		
	
	}

}
