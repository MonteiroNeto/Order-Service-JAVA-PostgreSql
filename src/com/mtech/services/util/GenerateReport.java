package com.mtech.services.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerateReport {

	public GenerateReport() {

	}

	public void generateClientReport(String title) {
		createFolderToSave();

		Document document = new Document();

		String pdfFile = System.getProperty("user.home") + "/reports/" + title + ".pdf";

		try {

			// Criar o docuemento
			PdfWriter.getInstance(document, new FileOutputStream(pdfFile, true));

			// Abri docuemnto
			document.open();

			// Adcionar tamanho da pagina
			document.setPageSize(PageSize.A4);

			// criar paragrafo para o titulo/cabeçalho
			Paragraph paragraph = new Paragraph(
					new Phrase(32F, title, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 32F)));
			paragraph.setAlignment(Element.ALIGN_CENTER);

			document.add(paragraph);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			document.close();

			try {
				Desktop.getDesktop().open(new File(pdfFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void createFolderToSave() {
		String folderReports = System.getProperty("user.home") + "/reports";

		File folder = new File(folderReports);
		if (!folder.exists()) {
			folder.mkdir();
		}

	}

}
