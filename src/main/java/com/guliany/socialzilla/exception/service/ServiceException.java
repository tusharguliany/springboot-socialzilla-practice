package com.guliany.socialzilla.exception.service;

import com.guliany.socialzilla.exception.AppException;

public class ServiceException extends AppException {

	private static final long serialVersionUID = 2699522942894858897L;

	public ServiceException() {
	}
	
	public ServiceException(String arg0) {
		super(arg0);
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
