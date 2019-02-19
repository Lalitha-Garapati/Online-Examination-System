package com.svecw.oes.exception;

public class OESException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public OESException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "oesException [message=" + message + "]";
	}

}
