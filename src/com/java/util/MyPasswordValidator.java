package com.java.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.java.dto.isValidPassword;

public class MyPasswordValidator implements ConstraintValidator<isValidPassword, String> {

	String role;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if("User".equalsIgnoreCase(role) && "payal".equalsIgnoreCase(value)) {
			return true;
		}
		else if ("admin".equalsIgnoreCase(role) || "admin".equalsIgnoreCase(value)) {
			return true;
		} 
		else {
			return false;
		}
	}

	
	public void initialize(isValidPassword constraintAnnotation) {
		 role=constraintAnnotation.role();
	}

}
