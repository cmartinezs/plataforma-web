package cl.smartware.apps.web.platform.service.exception;

public class InvalidTypeFileEntityServiceException extends Exception
{
	private static final long serialVersionUID = 483530515945685579L;

	public InvalidTypeFileEntityServiceException()
	{
		super("El tipo de documento seleccionado no es compatible con el tipo de contenido del archivo");
	}
}
