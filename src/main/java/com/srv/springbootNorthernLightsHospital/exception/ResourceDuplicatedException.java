package com.srv.springbootNorthernLightsHospital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceDuplicatedException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceDuplicatedException(String string) {
		super(string);
		
	}
}