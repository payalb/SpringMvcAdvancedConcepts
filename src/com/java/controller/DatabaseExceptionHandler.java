package com.java.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.java.exception.DatabaseException;

/*Global Exception handling*/
@ControllerAdvice
public class DatabaseExceptionHandler {

	@ExceptionHandler(DatabaseException.class)
	public String handleDatabaseException(Model model, Exception e) {
		model.addAttribute("errorMessage", e.getMessage());
		return "error";
	}
	
	@ExceptionHandler(value= {RuntimeException.class})
	public void handler(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException {
		request.setAttribute("errorMessage","This is bad: "+ e.getMessage());
		request.getRequestDispatcher("error").forward(request, response);
	}
	
	/*@ModelAttribute
	public void init(Model model) {
		model.addAttribute("headerMessage", "This is my header!");
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(requiredType, field, propertyEditor);
	}*/
	
}
