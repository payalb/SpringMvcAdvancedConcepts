package com.java.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.Student;
import com.java.exception.DatabaseException;
import com.java.service.StudentService;

@Controller
public class StudentController  {

	@Autowired TaskExecutor executor;
	
	private StudentService service;
	
	public StudentController(StudentService service){
		this.service=service;
	}
	
	/*asynchronous support to spring mvc*/
/*	@RequestMapping(path= {"/addStudent"},  method=RequestMethod.POST)
	public Callable<String> doWork(@ModelAttribute("student") Student student, HttpServletRequest request) {
			Callable<String> longTask= ()->addStudent(student);
			System.out.println(Thread.currentThread().getName());
			return longTask;
	}*/

/*	@RequestMapping(path= {"/addStudent"},  method=RequestMethod.POST)
	public DeferredResult<String> doWork(@ModelAttribute("student") Student student, HttpServletRequest request) throws DatabaseException {
		Unlike callable, deferred result needs us to assign a thread for async task
		System.out.println(Thread.currentThread().getName()+"**Thread name");
		DeferredResult<String> longTask= new DeferredResult<>();
		Runnable t=()->{
				longTask.setResult(addStudent1(student));
			
		};
		new Thread(t).start();
		return longTask;
	}*/
	

	/*@RequestMapping(path= {"/addStudent"},  method=RequestMethod.POST)
	public DeferredResult<String> doWork(@ModelAttribute("student") Student student, HttpServletRequest request) throws DatabaseException {
		So generally we create teask executor to manage threads
		System.out.println(Thread.currentThread().getName()+"**Thread name");
		DeferredResult<String> longTask= new DeferredResult<>();
		executor.execute(()->{
			longTask.setResult(addStudent1(student));
		});
		return longTask;
	}*/
	
/*	@RequestMapping(path= {"/addStudent"},  method=RequestMethod.POST)
	public Callable<String> doWork(@ModelAttribute("student") Student student, HttpServletRequest request) throws DatabaseException {
		System.out.println(Thread.currentThread().getName()+"**Thread name"); 
		//Should pull therad from our executor
		Callable<String> longTask= ()->{
			return addStudent1(student);
		};
		return longTask;
	}*/
	
	@RequestMapping(path= {"/addStudent"},  method=RequestMethod.POST)
	public WebAsyncTask<String> doWork(@ModelAttribute("student") Student student) throws DatabaseException {
		System.out.println(Thread.currentThread().getName()+"**Thread name"); 
		//Should pull thread from our executor
		WebAsyncTask<String> longTask= new WebAsyncTask<>(()->{
			return addStudent1(student);
		});
		longTask.onError(()->{ throw new DatabaseException("Exception occured!");});
		return longTask;
	}
/*	
	private String addStudent(Student student) throws DatabaseException{
		student.setId(service.addStudent(student));
		System.out.println(Thread.currentThread().getName()+"*****Thread name");
		return "displayStudent";
	}*/
	private String addStudent1(Student student) {
		try {
			student.setId(service.addStudent(student));
		} catch (DatabaseException e) {
			throw new RuntimeException(e.getMessage());
		}
		System.out.println(Thread.currentThread().getName()+"*****Thread name");
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
