package com.endava.doctorsapi.tables.general.exceptions;

import com.endava.doctorsapi.tables.general.base.ControllerBase;
import org.springframework.http.HttpStatus;

public class ControllerException extends CmsException {

	public ControllerException(ControllerBase<?, ?, ?> entity, String message) {
		this(HttpStatus.valueOf(500), entity, message);
	}

	public ControllerException(HttpStatus status, ControllerBase<?, ?, ?> entity, String message) {
		super(status, "[" + entity.getClass().getSimpleName() + "] " + message);
	}
}
