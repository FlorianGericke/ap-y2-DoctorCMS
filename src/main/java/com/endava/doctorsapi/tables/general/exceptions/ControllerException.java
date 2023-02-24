package com.endava.doctorsapi.tables.general.exceptions;

import com.endava.doctorsapi.tables.general.base.ControllerBase;

public class ControllerException extends CmsException {

	public ControllerException(ControllerBase<?, ?, ?> entity, String message) {
		super("[" + entity.getClass().getSimpleName() + "] " + message);
	}
}
