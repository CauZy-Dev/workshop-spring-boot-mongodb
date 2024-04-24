package com.cauzy.workshopmongo.exception;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String id) {
		super("Resouce not found. Id " + id);
	}

}
