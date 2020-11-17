package com.guliany.socialzilla.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.guliany.socialzilla.dto.ErrorDTO;
import com.guliany.socialzilla.exception.AppException;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDTO dto = new ErrorDTO("Server Error", ex.getMessage());
		return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AppException.class)
	public final ResponseEntity<ErrorDTO> handleAppExceptions(Exception ex, WebRequest request) {
		ErrorDTO dto = new ErrorDTO("App Exception", ex.getMessage());
		return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDTO dto = new ErrorDTO("Invalid Item", ex.getMessage());
		return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
	}

}
