package cl.smartware.apps.web.platform.service.exception;

public class FileEntityServiceException extends Exception
{
	private static final long serialVersionUID = -6068154174895124024L;

	/**
	 * 
	 * @param message
	 */
	public FileEntityServiceException(String message)
	{
		super(message);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public FileEntityServiceException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
