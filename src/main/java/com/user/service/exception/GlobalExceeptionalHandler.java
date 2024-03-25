package com.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.service.payload.ApiResponse;
import com.user.service.payload.ApiResponse.ApiResponseBuilder;

@RestControllerAdvice
public class GlobalExceeptionalHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handelResourceNotFoundException(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		

	    ApiResponseBuilder response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND);
		
	   return new ResponseEntity(response, HttpStatus.NOT_FOUND);
		
	}
}
