package com.java.dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.java.util.MyPasswordValidator;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { MyPasswordValidator.class})
public @interface isValidPassword {

	String role() default "User";
	
	String message() default "Not a valid password"; 

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
