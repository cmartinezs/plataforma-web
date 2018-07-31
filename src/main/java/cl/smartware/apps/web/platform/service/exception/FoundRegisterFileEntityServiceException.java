package cl.smartware.apps.web.platform.service.exception;

import java.text.MessageFormat;

public class FoundRegisterFileEntityServiceException extends Exception
{
	private static final long serialVersionUID = 483530515945685579L;

	public FoundRegisterFileEntityServiceException(String name)
	{
		super(MessageFormat.format("El documento con nombre {0} ya existe", name));
	}
}
