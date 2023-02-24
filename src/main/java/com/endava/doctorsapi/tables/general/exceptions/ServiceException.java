package com.endava.doctorsapi.tables.general.exceptions;

import com.endava.doctorsapi.tables.general.base.ServiceBase;

public class ServiceException extends CmsException{

	public ServiceException(ServiceBase<?,?,?> entity , String message) {
		super("["+entity.getClass().getSimpleName()+"] " + message);
	}
}
