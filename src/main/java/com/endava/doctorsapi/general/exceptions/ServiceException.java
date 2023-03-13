package com.endava.doctorsapi.general.exceptions;

import com.endava.doctorsapi.general.base.ServiceBase;

public class ServiceException extends CmsException{

	public ServiceException(ServiceBase<?,?,?> entity , String message) {
		super("["+entity.getClass().getSimpleName()+"] " + message);
	}
}
