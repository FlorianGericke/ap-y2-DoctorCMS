package com.endava.doctorsapi.general.exceptions;

import com.endava.doctorsapi.general.base.BaseService;
import org.springframework.http.HttpStatus;

public class ServiceException extends CmsException {

	public ServiceException(BaseService<?, ?> entity, String message) {
		this(HttpStatus.valueOf(500), entity, message);
	}

	public ServiceException(HttpStatus status, BaseService<?, ?> entity, String message) {
		super(status, "[" + entity.getClass().getSimpleName() + "] " + message);
	}
}
