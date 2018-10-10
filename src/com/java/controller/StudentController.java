package com.java.controller;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.Student;
import com.java.exception.DatabaseException;
import com.java.service.StudentService;

@Controller
public class StudentController  {

	
	private StudentService service;
	
	public StudentController(StudentService service){
		this.service=service;
	}
	
	/*asynchronous support to spring mvc*/
	@RequestMapping(path= {"/addStudent"},  method=RequestMethod.POST)
	public Callable<String> doWork(@ModelAttribute("student") Student student) {
		System.out.println(Thread.currentThread().getName());
			Callable<String> longTask= ()->addStudent(student);
			System.out.println(Thread.currentThread().getName());
			return longTask;
	}

	private String addStudent(Student student) throws DatabaseException{
		student.setId(service.addStudent(student));
		System.out.println(Thread.currentThread().getName());
		return "displayStudent";
	}
		
	/*@ExceptionHandler(value=DatabaseException.class)
	public void handler(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException {
		request.setAttribute("errorMessage", e.getMessage());
		request.getRequestDispatcher("error").forward(request, response);
	}*/
	

	@RequestMapping(path= {"/addStudent"},  method=RequestMethod.GET)
	public ModelAndView navigate(ModelAndView attr) {
		attr.addObject("student", new Student());
		attr.setViewName("addStudent");
		return attr;
	}
	
/*	@ModelAttribute
	public void init(Model model) {
		model.addAttribute("headerMessage", "This is my header!");
	}*/
	
	/*@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(requiredType, field, propertyEditor);
	}*/
}
