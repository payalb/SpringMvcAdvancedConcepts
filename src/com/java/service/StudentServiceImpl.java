package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.java.dto.Student;
import com.java.exception.DatabaseException;
import com.java.repository.StudentRepository;
@Service("service")
public class StudentServiceImpl implements StudentService {

	
	private StudentRepository repository;
	
	
	public StudentServiceImpl(@Autowired @Qualifier("rep2") StudentRepository repository){
		this.repository= repository;
	}
	
	@Override
	public int  addStudent(Student student) throws DatabaseException {
		 return repository.addStudent(student);
	}

	@Override
	public void updateStudent(Student student) {
		//fetch
		//update: 
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteStudent(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return null;
	}

}
