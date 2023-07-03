package br.com.vote.exception;

public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = -4725692488732203450L;

	public BussinessException(String message) {
		super(message);
	}
	
}
