package com.endava.doctorsapi.general.exceptions;

import com.endava.doctorsapi.general.base.ServiceBase;
import org.springframework.http.HttpStatus;

public class ServiceException extends CmsException{

	public ServiceException(ServiceBase<?, ?, ?> entity, String message) {
		this(HttpStatus.valueOf(500), entity, message);
	}

	public ServiceException(HttpStatus status, ServiceBase<?,?,?> entity , String message) {
		super(status, "["+entity.getClass().getSimpleName()+"] " + message);
	}
}
