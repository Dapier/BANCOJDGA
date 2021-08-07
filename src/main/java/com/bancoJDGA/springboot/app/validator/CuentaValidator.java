package com.bancoJDGA.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bancoJDGA.springboot.app.models.entity.Cuenta;

@Component
public class CuentaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Cuenta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cuenta cuenta = (Cuenta) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "montoMinimo", "NotEmpty.cuenta.montoMinimo");

		if(cuenta.getPorcentaje() < 0) {
			errors.rejectValue("porcentaje", "MinRequerido.cuenta.porcentaje");
		}
	}

}
