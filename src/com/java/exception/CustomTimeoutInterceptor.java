package com.java.exception;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

public class CustomTimeoutInterceptor implements CallableProcessingInterceptor {

	@Override
	public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
		throw new CustomTimeoutException("Timeout occured processing request: "+ task.getClass().getName());
	}
}
