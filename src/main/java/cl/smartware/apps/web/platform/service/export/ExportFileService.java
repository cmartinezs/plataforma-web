package cl.smartware.apps.web.platform.service.export;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

import com.itextpdf.text.DocumentException;

import cl.smartware.apps.web.platform.repository.jpa.entity.FileEntity;
import cl.smartware.apps.web.platform.service.export.model.FileExport;

public interface ExportFileService 
{
	public ResponseEntity<ByteArrayResource> getDownloadResponse(FileEntity fileEntity);
	
	public ResponseEntity<ByteArrayResource> getDownloadResponse(String filename, String contentType, long contentLength, byte[] byteArray);

	public FileExport generateFileToExport(String module, String database, String table, String ext) throws DocumentException, IOException;
}
