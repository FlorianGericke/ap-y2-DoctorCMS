package com.endava.doctorsapi.general.exceptions;

import com.endava.doctorsapi.general.base.BaseController;
import org.springframework.http.HttpStatus;

public class ControllerException extends CmsException {

	public ControllerException(BaseController<?, ?,?,?> entity, String message) {
		this(HttpStatus.valueOf(500), entity, message);
	}

	public ControllerException(HttpStatus status, BaseController<?, ?,?,?> entity, String message) {
		super(status, "[" + entity.getClass().getSimpleName() + "] " + message);
	}
}
