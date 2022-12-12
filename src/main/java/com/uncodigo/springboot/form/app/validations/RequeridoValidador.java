package com.uncodigo.springboot.form.app.validations;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
//		if(value == null || value.isEmpty() || value.isBlank()) {
		if(value == null || !StringUtils.hasText(value)) {
			return false;
		}
				
		return true;
	}

}
