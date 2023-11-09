package br.com.myapk.domain.exception;

public class GroupingNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

    public GroupingNotFoundException(String message) {
        super(message);
    }
    
    public GroupingNotFoundException(Long groupingId) {
        this(String.format("There is no group registration with code %d", groupingId));
    } 
	
}
