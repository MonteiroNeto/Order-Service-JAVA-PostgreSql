package com.mtech.services.util;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders.ButtonBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BidiOrder;
import com.itextpdf.text.pdf.PdfBorderArray;
import com.itextpdf.text.pdf.PdfDiv.BorderTopStyle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jgoodies.forms.layout.CellConstraints;
import com.mtech.services.model.Client;
import com.mtech.services.model.Os;
import com.mtech.services.values.MyStrings;

import com.itextpdf.*;

public class GenerateReport {
	private Document document;
	private String pdfFile;
	private String title;
	private MyStrings mStrings = new MyStrings();
	private String[] statusDescription = mStrings.LIST_STATUS_OS;
	private String[] serviceTypeDescription = mStrings.LIST_SERVICE_TYPE;

	public GenerateReport(String title) {
		this.title = title;
		document = new Document();
		pdfFile = System.getProperty("user.home") + "/reports/" + title + ".pdf";
	}

	public void generateClientReport(ArrayList<String> columnsTitle, ArrayList<Client> tableData) {
		createFolderToSave();

		try {

			// Criar o docuemento
			PdfWriter.getInstance(document, new FileOutputStream(pdfFile, true));

			// Adcionar tamanho da pagina
			document.setPageSize(PageSize.A4);

			// Adicionar Margin
			document.setMargins(0f, 0f, 0f, 0f);

			// Abri docuemnto
			document.open();

			// criar paragrafo para o titulo/cabeçalho
			Paragraph paragraph = new Paragraph(
					new Phrase(32F, title, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 32F)));
			paragraph.setAlignment(Element.ALIGN_CENTER);

			document.add(paragraph);
			document.add(new Paragraph(" "));

			// Criar tabela
			PdfPTable table = new PdfPTable(new float[] { 50f, 100f, 150f, 150f, 200f });
			// criar titulo das colunas
			table.addCell(columnsTitle.get(0));
			table.addCell(columnsTitle.get(1));
			table.addCell(columnsTitle.get(2));
			table.addCell(columnsTitle.get(3));
			table.addCell(columnsTitle.get(4));

			// Adicionar dados a tabela
			for (int i = 0; i < tableData.size(); i++) {
				Client client = tableData.get(i);
				table.addCell(String.valueOf(client.getIdClient()));
				table.addCell(client.getName());
				table.addCell(client.getAdress());
				table.addCell(client.getPhone());
				table.addCell(client.getEmail());
			}

			// Adicionar o conteudo a tabela
			document.add(table);

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

	public void generateOsReport(ArrayList<String> columnsTitle, ArrayList<Os> tableData) {
		createFolderToSave();

		try {

			// Criar o docuemento
			PdfWriter.getInstance(document, new FileOutputStream(pdfFile, true));

			// Adcionar tamanho da pagina
			document.setPageSize(PageSize.A4);

			// Adicionar Margin
			document.setMargins(0f, 0f, 0f, 0f);

			// Abri docuemnto
			document.open();

			// Adicionar imagem
			Image img = Image.getInstance(getClass().getResource(mStrings.APP_PACKAGE + "icon/ic_main_256.png"), false);
			img.scaleAbsolute(100f, 100f);
			img.setAlignment(Element.ALIGN_LEFT);
			document.add(img);

			// criar paragrafo para o titulo/cabeçalho
			Paragraph paragraph = new Paragraph(
					new Phrase(32F, title, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 32F)));
			paragraph.setAlignment(Element.ALIGN_CENTER);

			document.add(paragraph);

			document.add(new Paragraph(" "));

			// Criar tabela
			PdfPTable table = new PdfPTable(7);

			// definir quanto de espaço oculpara a tabela na oagina A4
			table.setWidthPercentage(95f);

			// definir o quanto em porcentagem cada coluna ira oculpar
			table.setWidths(new float[] { 5f, 10f, 15f, 10f, 10f, 15f, 15f });

			// criar titulo das colunas
			table.addCell(columnsTitle.get(0));
			table.addCell(columnsTitle.get(1));
			table.addCell(columnsTitle.get(2));
			table.addCell(columnsTitle.get(3));
			table.addCell(columnsTitle.get(4));
			table.addCell(columnsTitle.get(5));
			table.addCell(columnsTitle.get(6));

			// Adicionar dados a tabela
			for (int i = 0; i < tableData.size(); i++) {
				Os os = tableData.get(i);
				table.addCell(String.valueOf(os.getId()));
				table.addCell(os.getData());
				table.addCell(os.getEquipment());
				table.addCell(String.valueOf(os.getPrice()));
				table.addCell(os.getStatuDescription());
				table.addCell(os.getNameCli());
				table.addCell(os.getPhoneCli());
			}

			// Adicionar o conteudo a tabela
			document.add(table);

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

	public void generateOsToClientReport(ArrayList<String> columnsTitle, Os os) {
		createFolderToSave();

		try {

			// Criar o docuemento
			PdfWriter.getInstance(document, new FileOutputStream(pdfFile, true));

			// Adcionar tamanho da pagina
			document.setPageSize(PageSize.A4);

			// Adicionar Margin
			document.setMargins(0f, 0f, 0f, 0f);

			// Abri docuemnto
			document.open();

			document.add(new Paragraph(" "));// saltar uma linha em branco

			// *******************************************CRIAR
			// CABEÇALHO***************************************************************
			// Adicionar imagem
			Image img = Image.getInstance(getClass().getResource(mStrings.APP_PACKAGE + "icon/ic_main_256.png"), false);
			img.scaleAbsolute(50f, 50f);
			img.setAlignment(Element.ALIGN_LEFT);
			img.setBorder(0);

			// criar paragrafo para o titulo/cabeçalho
			PdfPCell p1 = new PdfPCell(img);
			p1.setBorder(0);
			// p1.setColspan(3);
			p1.setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPCell p2 = new PdfPCell(new Paragraph(new Phrase(14F,
					"\n" + mStrings.NUMBER_OS + ": " + os.getId() + "\n\n" + mStrings.CLIENT_CODE + os.getIdCli(),
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12F))));
			p2.setBorder(0);
			// p2.setColspan(3);
			p2.setHorizontalAlignment(Element.ALIGN_LEFT);

			PdfPCell p3 = new PdfPCell(
					new Paragraph(new Phrase(
							14F, "\n\n\n" + mStrings.TECHNICIAN + ": " + os.getTechnician() + " \n\n      "
									+ mStrings.PRICE + ": " + os.getPrice(),
							FontFactory.getFont(FontFactory.HELVETICA, 12F))));
			p3.setBorder(0);

			p3.setHorizontalAlignment(Element.ALIGN_RIGHT);

			// criar TABLE para o HEAD
			PdfPTable tableHead = new PdfPTable(3);

			// Definir em porcentagem quanto o TABLE-HEAD oculpara da tabela
			tableHead.setWidthPercentage(98f);

			// definir o quanto cada coluna ocupara em porcentagem
			tableHead.setWidths(new float[] { 20f, 60, 20f });

			tableHead.addCell(p1);
			tableHead.addCell(p2);
			tableHead.addCell(p3);

			// Adicionar o conteudo a tabela
			document.add(tableHead);

			document.add(new Paragraph(" "));// saltar uma linha em branco

			// *************************************************FIM CABEÇALHO

			document.add(new Paragraph(" "));

			// Criar tabela
			PdfPTable table = new PdfPTable(6);

			// definir quanto de espaço oculpara a tabela na oagina A4
			table.setWidthPercentage(95f);

			// definir o quanto em porcentagem cada coluna ira oculpar
			table.setWidths(new float[] { 10f, 10f, 15f, 15f, 20f, 20f });

			// criar titulo das colunas
			table.addCell(columnsTitle.get(0));
			table.addCell(columnsTitle.get(1));
			table.addCell(columnsTitle.get(2));
			table.addCell(columnsTitle.get(3));
			table.addCell(columnsTitle.get(4));
			table.addCell(columnsTitle.get(5));

			// Adicionar dados a tabela
			table.addCell(os.getData());
			table.addCell(serviceTypeDescription[os.getServiceType() - 1]);
			table.addCell(statusDescription[os.getStatus()]);
			table.addCell(os.getEquipment());
			table.addCell(os.getDefect());
			table.addCell(os.getService());

			// Adicionar o conteudo a tabela
			document.add(table);

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
