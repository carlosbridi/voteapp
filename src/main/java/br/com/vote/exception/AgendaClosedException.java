package br.com.vote.exception;

public class AgendaClosedException extends RuntimeException {

	private static final long serialVersionUID = 1251429525505985407L;

	public AgendaClosedException(final String message) {
		super(message);
	}	
}
