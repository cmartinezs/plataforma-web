package cl.smartware.apps.web.platform.service.export;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import cl.smartware.apps.web.platform.repository.jpa.entity.FileEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.service.MultiDatabaseService;
import cl.smartware.apps.web.platform.service.export.model.FileExport;
import cl.smartware.apps.web.platform.utils.ListUtils;

@Service
public class ExportFileServiceImpl implements ExportFileService
{
	@Autowired
	private MultiDatabaseService multiDatabaseService;
	
	@Override
	public ResponseEntity<ByteArrayResource> getDownloadResponse(FileEntity fileEntity)
	{
		String filename = fileEntity.getFileName();
		String contentType = fileEntity.getContentType();
		long contentLength = fileEntity.getSize();
		byte[] byteArray = fileEntity.getFile();
		
		return getDownloadResponse(filename, contentType, contentLength, byteArray);
	}
	
	@Override
	public ResponseEntity<ByteArrayResource> getDownloadResponse(String filename, String contentType, long contentLength, byte[] byteArray) 
	{
		ByteArrayResource resource = new ByteArrayResource(byteArray);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
				.contentType(MediaType.parseMediaType(contentType))
				.contentLength(contentLength)
				.body(resource);
	}

	@Override
	public FileExport generateFileToExport(String module, String database, String table, String ext) throws DocumentException, IOException 
	{
		List<Map<String, Object>> rows = multiDatabaseService.getAll(database, table);
		List<String> header = ListUtils.setToList(rows.get(0).keySet());
		
		ByteArrayOutputStream out = null;
		
		String contentType = null;
		
		if(FileTypes.PDF.getFileExtension().equalsIgnoreCase(ext))
		{
			out = generatePDF(rows, header);
			contentType = MediaType.APPLICATION_PDF_VALUE;
		}
		else
		{
			out = generateExcel(rows, header);
			contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		}
		
		FileExport fileExport = new FileExport();
		fileExport.setFileName(database + "_" + table + "." + ext);
		fileExport.setBytes(out.toByteArray());
		fileExport.setContentLength(out.toByteArray().length);
		fileExport.setContentType(contentType);
		
		return fileExport;
	}

	
	private ByteArrayOutputStream generateExcel(List<Map<String, Object>> dataRows, List<String> header) throws IOException 
	{
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Datos exportados");
		
		int rowNum = 0;
		
		Row row = sheet.createRow(rowNum++);
		int col = 0;
		for(String head: header)
		{
			Cell cell = row.createCell(col++);
			cell.setCellValue(head);
		}
		
		for(Map<String, Object> dataRow: dataRows)
		{
			row = sheet.createRow(rowNum++);
			int colNum = 0;
			
			for(String key: dataRow.keySet())
			{
				Cell cell = row.createCell(colNum++);
				cell.setCellValue(Objects.toString(dataRow.get(key), ""));
			}
		}
		
		workbook.write(byteArrayOutputStream);
        workbook.close();
    
		return byteArrayOutputStream;
	}

	private ByteArrayOutputStream generatePDF(List<Map<String, Object>> rows, List<String> header) throws DocumentException 
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		Document document = new Document();
		
		PdfPTable pdfTable = new PdfPTable(header.size());
		pdfTable.setWidthPercentage(100);
		
		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		
		header.forEach(headColumn -> {
			PdfPCell hcell = new PdfPCell(new Phrase(headColumn, headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfTable.addCell(hcell);
		});
		
		rows.forEach(row -> {
			row.forEach((key, value) -> {
				PdfPCell cell = new PdfPCell(new Phrase(Objects.toString(value, "")));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfTable.addCell(cell);
			});
		});
		
		PdfWriter.getInstance(document, out);
		document.open();
		document.add(pdfTable);
		document.close();
		
		return out;
	}
}
