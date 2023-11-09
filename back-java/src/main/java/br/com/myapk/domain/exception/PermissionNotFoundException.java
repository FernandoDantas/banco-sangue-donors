package br.com.myapk.domain.exception;

public class PermissionNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public PermissionNotFoundException(String message) {
		super(message);		
	}
	
	public PermissionNotFoundException(Long permissionId) {
		this(String.format("There is no permission record with code %d", permissionId));
	}

}
