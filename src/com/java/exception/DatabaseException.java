package com.java.exception;

public class DatabaseException extends Exception{

	private static final long serialVersionUID = -5579576790131903759L;

	public DatabaseException(String message){
		super(message);
	}
	
	public DatabaseException(){
	}
}
