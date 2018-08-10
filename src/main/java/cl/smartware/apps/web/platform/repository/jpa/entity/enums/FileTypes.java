package cl.smartware.apps.web.platform.repository.jpa.entity.enums;

public enum FileTypes
{
	SELECT("-- Seleccione --", ""), 
	PDF("PDF [*.pdf]", "pdf"), 
	XLS("Libro de Excel 97-2003 [*.xls]", "xls"), 
	XLSX("Libro de Excel [*.xlsx]", "xlsx");
	
	private String text;
	private String ext;
	
	FileTypes(String text, String ext)
	{
		this.text = text;
		this.ext = ext;
	}

	public String getText() {
		return text;
	}

	public String getExt() {
		return ext;
	}
}
