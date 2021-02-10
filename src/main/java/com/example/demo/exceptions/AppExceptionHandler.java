package com.example.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.example.demo.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler extends DefaultHandlerExceptionResolver{//  ResponseEntityExceptionHandler {

	//catches all exceptions
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleException(Exception ex, WebRequest req) {
		System.out.println("Inside*******************************************");

		String desc = ex.getLocalizedMessage();
		if(ex.getLocalizedMessage() == null) {
			desc = ex.toString();
		}
		ErrorMessage em = new ErrorMessage(new Date(), desc);
		//way1
		//return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		//way2
		return new ResponseEntity<>(em, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//catches nullpointer
	@ExceptionHandler(value = {NullPointerException.class})
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest req) {
		System.out.println("Inside*******************************************");

		String desc = ex.getLocalizedMessage();
		if(ex.getLocalizedMessage() == null) {
			desc = ex.toString();
		}
		ErrorMessage em = new ErrorMessage(new Date(), desc);
		return new ResponseEntity<>(em, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//catch UserServiceExceptioin and ArithmeticException
	@ExceptionHandler(value = {UserServiceException.class, ArithmeticException.class})
	public ResponseEntity<Object> handleSpecificExceptions(Exception ex, WebRequest req) {
		System.out.println("Inside *******************************************");

		String desc = ex.getLocalizedMessage();
		if(ex.getLocalizedMessage() == null) {
			desc = ex.toString();
		}
		ErrorMessage em = new ErrorMessage(new Date(), desc);
		return new ResponseEntity<>(em, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
