package com.endava.doctorsapi.tables.general.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CmsException extends ResponseStatusException {

	public CmsException(String message) {
		this(HttpStatus.valueOf(500), message);
	}

	public CmsException(HttpStatus status, String message) {
		super(status, message);
	}

}
