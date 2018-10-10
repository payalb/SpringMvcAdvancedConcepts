package com.java.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

//java.sql package: jdbc classes
import com.java.dto.Student;
import com.java.exception.DatabaseException;

@Repository("rep2")
@DependsOn("flyway")
public class StudentRepositoryImplOne implements StudentRepository  {


	@Autowired
	BasicDataSource ds;

	@Override
	public int addStudent(Student student) throws DatabaseException {
		
		/*Connection c=DriverManager.getConnection(url, user, password);
		c.setAutoCommit(false)*/;
		int id = -1;
		try (Connection conn = ds.getConnection(); 
			Statement st = conn.createStatement();) {
		
			PreparedStatement ps1=conn.prepareStatement("insert into student (name, password) values ( ?, ? )");
			ps1.setString(1, student.getName());
			ps1.setString(2, student.getPassword());
			ps1.executeUpdate();
			
			PreparedStatement ps2=conn.prepareStatement("select max(id) as id from student where name= ? and password= ? ");
			ps2.setString(1, student.getName());
			ps2.setString(2, student.getPassword());
			ResultSet set = ps2.executeQuery();
			
			while (set.next()) {
				id = set.getInt("id");
				System.out.println(id);
			}
			
			set.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Unable to insert record into db" + e.getMessage());
			
		}
		return id;
	}

	@Override
	public void updateStudent(Student student) {

	}

	@Override
	public void deleteStudent(Student student) {

	}

	@Override
	public Student getStudentById(int id) {
		return null;
	}

	@Override
	public List<Student> getStudents() {
		return null;
	}

	/*
	 * @Override public void setApplicationContext(ApplicationContext
	 * applicationContext) throws BeansException { this.applicationContext=
	 * applicationContext;
	 * 
	 * }
	 */

}
