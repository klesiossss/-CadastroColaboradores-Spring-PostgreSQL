package br.com.cadastro.exceptions;

public class ElementFoundInBlackListException extends RuntimeException {
	private static final long serialVersionUID = -6030733006897912418L;
	
	public ElementFoundInBlackListException() {
		super("O recurso solicitado encontra-se na black list");
	}
	
	public ElementFoundInBlackListException(String message) {
		super(message);
	}
}
