package com.endava.doctorsapi.general.exceptions;

import com.endava.doctorsapi.general.base.ControllerBase;

public class ControllerException extends CmsException {

	public ControllerException(ControllerBase<?, ?, ?> entity, String message) {
		super("[" + entity.getClass().getSimpleName() + "] " + message);
	}
}
