/**
 * 
 */
package com.viatt.zhjtpro.common;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1629430133132890692L;

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	} 

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
