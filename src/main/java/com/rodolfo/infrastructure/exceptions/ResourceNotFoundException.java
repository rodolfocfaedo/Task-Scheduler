package com.rodolfo.infrastructure.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 3692957763246341649L;

	public ResourceNotFoundException(String message) {
		super(message);

	}

	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
