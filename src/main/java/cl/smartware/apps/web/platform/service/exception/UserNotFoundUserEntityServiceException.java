package cl.smartware.apps.web.platform.service.exception;

public class UserNotFoundUserEntityServiceException extends Exception 
{
	private static final long serialVersionUID = -4095582092173946134L;

	public UserNotFoundUserEntityServiceException()
	{
		super();
	}

	public UserNotFoundUserEntityServiceException(String message)
	{
		super(message);
	}

	public UserNotFoundUserEntityServiceException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
