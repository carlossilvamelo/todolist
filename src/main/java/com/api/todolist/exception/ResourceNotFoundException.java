package com.api.todolist.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3327183366485380517L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
