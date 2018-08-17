package cl.smartware.apps.web.platform.service.exception;

public class RoleNotFoundUserEntityServiceException extends Exception {

	private static final long serialVersionUID = 7387449584953727946L;
	
	public RoleNotFoundUserEntityServiceException()
	{
		super();
	}
	
	public RoleNotFoundUserEntityServiceException(String message)
	{
		super(message);
	}

	public RoleNotFoundUserEntityServiceException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
