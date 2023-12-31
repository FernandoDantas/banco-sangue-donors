package br.com.myapk.domain.exception;

public class UserNotFoundException extends EntityNotFoundException{
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);		
	}
	
	public UserNotFoundException(Long userId) {
		this(String.format("There is no user registration with code %d", userId));
	}

}
