package com.java.exception;

public class CustomTimeoutException extends Exception {

	private static final long serialVersionUID = 357939493004521894L;

	public CustomTimeoutException(String message){
		super(message);
	}
}
