package cl.smartware.apps.web.platform.repository.jpa.entity.enums;

public enum FileTypes
{
	PDF("PDF [*.pdf]", "pdf", "application/pdf"), 
	XLS("Libro de Excel 97-2003 [*.xls]", "xls", "application/vnd.ms-excel"), 
	XLSX("Libro de Excel [*.xlsx]", "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	
	private String text;
	private String fileExtension;
	private String mediaType;
	
	FileTypes(String text, String ext, String mediaType)
	{
		this.text = text;
		this.fileExtension = ext;
		this.mediaType = mediaType;
	}

	public String getText() {
		return text;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public String getMediaType() {
		return mediaType;
	}
}
