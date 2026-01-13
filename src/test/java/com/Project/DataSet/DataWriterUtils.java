package com.Project.DataSet;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataWriterUtils {

	/**
	 * Creates a new Excel file with headers: S.No | Product | Status | Timestamp
	 */
	public static void createExcelFile(String filePath, String sheetName) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetName);

		// Create header row
		Row header = sheet.createRow(0);
		String[] headers = { "S.No", "Product", "ProductTitle", "productLink", "Status", "Timestamp" };

		// Header style (bold)
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		style.setFont(font);

		for (int i = 0; i < headers.length; i++) {
			Cell cell = header.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(style);
		}

		// Auto-size columns
		for (int i = 0; i < headers.length; i++) {
			sheet.autoSizeColumn(i);
		}

		try (FileOutputStream fos = new FileOutputStream(filePath)) {
			workbook.write(fos);
			System.out.println("✅ Excel file created successfully at: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void createExcelFileForNaukri(String filePath, String sheetName, String[] headers) {
		File file = new File(filePath);
		Workbook workbook = null;
		FileOutputStream fos = null;

		try {
			// ✅ Create directories if missing
			file.getParentFile().mkdirs();

			// ✅ Create a new workbook
			workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet(sheetName);

			// ✅ Create header row (index 0)
			Row headerRow = sheet.createRow(0);

			// ✅ Write all headers
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);

				// Optional: make headers bold
				CellStyle style = workbook.createCellStyle();
				Font font = workbook.createFont();
				font.setBold(true);
				style.setFont(font);
				cell.setCellStyle(style);
			}

			// ✅ Auto-size columns for better readability
			for (int i = 0; i < headers.length; i++) {
				sheet.autoSizeColumn(i);
			}

			// ✅ Write workbook to file
			fos = new FileOutputStream(filePath);
			workbook.write(fos);

			System.out.println("📘 Excel file created with headers at: " + filePath);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (workbook != null)
					workbook.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

//	public static void createExcelFileForNaukri(String filePath, String sheetName, String[] headerTitle) {
//		Workbook workbook = new XSSFWorkbook();
//		Sheet sheet = workbook.createSheet(sheetName);
//
//		// Create header row
//		Row header = sheet.createRow(0);
//		String[] headers = new String[headerTitle.length];
//		for (int i = 0; i <= headers.length - 1; i++) {
//			headers[i] = headerTitle[i];
//		}
//
//		// Header style (bold)
//		CellStyle style = workbook.createCellStyle();
//		Font font = workbook.createFont();
//		font.setBold(true);
//		style.setFont(font);
//
//		for (int i = 0; i < headers.length; i++) {
//			Cell cell = header.createCell(i);
//			cell.setCellValue(headers[i]);
//			cell.setCellStyle(style);
//		}
//
//		// Auto-size columns
//		for (int i = 0; i < headers.length; i++) {
//			sheet.autoSizeColumn(i);
//		}
//
//		try (FileOutputStream fos = new FileOutputStream(filePath)) {
//			workbook.write(fos);
//			System.out.println("✅ Excel file created successfully at: " + filePath);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				workbook.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}

//	public static void appendResultNaukri(String filePath, String sheetName, int serialNo, String JobTitle,
//			String JobLink, String jobPostedDate, String statusButton) {
//		File file = new File(filePath);
//		Workbook workbook = null;
//		FileOutputStream fos = null;
//		FileInputStream fis = null;
//
//		try {
//			file.getParentFile().mkdirs();
//
//			if (file.exists()) {
//				fis = new FileInputStream(file);
//				workbook = new XSSFWorkbook(fis);
//			} else {
//				createExcelFile(filePath, sheetName);
//				fis = new FileInputStream(file);
//				workbook = new XSSFWorkbook(fis);
//			}
//
//			Sheet sheet = workbook.getSheet(sheetName);
//			if (sheet == null) {
//				sheet = workbook.createSheet(sheetName);
//			}
//
//			// Determine next empty row
//			int lastRowNum = sheet.getLastRowNum();
//			Row newRow = sheet.createRow(lastRowNum + 1);
//
//			// Normal cells
//			newRow.createCell(0).setCellValue(serialNo);
//			newRow.createCell(1).setCellValue(JobTitle);
//			newRow.createCell(2).setCellValue(JobLink);
//			newRow.createCell(3).setCellValue(jobPostedDate);
//			// ✅ Timestamp
//			String timeStamp = statusButton;
//			newRow.createCell(4).setCellValue(timeStamp);
//
//			fis.close();
//			fos = new FileOutputStream(filePath);
//			workbook.write(fos);
//
//			// System.out.println("📄 Result written: " + productName + " → " + status + "
//			// (" + timeStamp + ")");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fis != null)
//					fis.close();
//				if (fos != null)
//					fos.close();
//				if (workbook != null)
//					workbook.close();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//	}
	
	public static synchronized void appendResultNaukri(
	        String filePath, String sheetName, int serialNo, 
	        String jobTitle, String jobLink, String jobPostedDate, String statusButton) {

	    File file = new File(filePath);
	    XSSFWorkbook workbook;
	    Sheet sheet;

	    try {
	        if (file.exists() && file.length() > 0) {
	            // Read existing workbook
	            try (FileInputStream fis = new FileInputStream(file)) {
	                workbook = new XSSFWorkbook(fis);
	            }
	        } else {
	            // Create new workbook if missing or empty
	            workbook = new XSSFWorkbook();
	        }

	        sheet = workbook.getSheet(sheetName);
	        if (sheet == null) {
	            sheet = workbook.createSheet(sheetName);

	            // Create header row once
	            Row header = sheet.createRow(0);
	            header.createCell(0).setCellValue("Serial No");
	            header.createCell(1).setCellValue("Job Title");
	            header.createCell(2).setCellValue("Job Link");
	            header.createCell(3).setCellValue("Posted Date");
	            header.createCell(4).setCellValue("Status");
	        }

	        // Append new row
	        int newRowNum = sheet.getLastRowNum() + 1;
	        Row row = sheet.createRow(newRowNum);

	        row.createCell(0).setCellValue(serialNo);
	        row.createCell(1).setCellValue(jobTitle);
	        row.createCell(2).setCellValue(jobLink);
	        row.createCell(3).setCellValue(jobPostedDate);
	        row.createCell(4).setCellValue(statusButton);

	        // Write back to file
	        try (FileOutputStream fos = new FileOutputStream(file)) {
	            workbook.write(fos);
	        }

	        workbook.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	/**
	 * Appends a single result row with color-coded Status + Timestamp.
	 */
	public static void appendResult(String filePath, String sheetName, int serialNo, String productName,
			List<String> productLink, Map<String, String> productTitle, String status) {
		File file = new File(filePath);
		Workbook workbook = null;
		FileOutputStream fos = null;
		FileInputStream fis = null;

		try {
			file.getParentFile().mkdirs();

			if (file.exists()) {
				fis = new FileInputStream(file);
				workbook = new XSSFWorkbook(fis);
			} else {
				createExcelFile(filePath, sheetName);
				fis = new FileInputStream(file);
				workbook = new XSSFWorkbook(fis);
			}

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				sheet = workbook.createSheet(sheetName);
			}

			// Determine next empty row
			int lastRowNum = sheet.getLastRowNum();
			Row newRow = sheet.createRow(lastRowNum + 1);

			// Normal cells
			newRow.createCell(0).setCellValue(serialNo);
			newRow.createCell(1).setCellValue(productName);
			newRow.createCell(2).setCellValue(productTitle.toString().replace("[", "").replace("]", ""));
			newRow.createCell(3).setCellValue(productLink.toString().replace("[", "").replace("]", ""));
			// ✅ Status cell with color
			Cell statusCell = newRow.createCell(4);
			statusCell.setCellValue(status);

			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();

			if (status.equalsIgnoreCase("PASS")) {
				font.setColor(IndexedColors.GREEN.getIndex());
			} else {
				font.setColor(IndexedColors.RED.getIndex());
			}
			style.setFont(font);
			statusCell.setCellStyle(style);

			// ✅ Timestamp
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			newRow.createCell(4).setCellValue(timeStamp);

			fis.close();
			fos = new FileOutputStream(filePath);
			workbook.write(fos);

			System.out.println("📄 Result written: " + productName + " → " + status + " (" + timeStamp + ")");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
				if (workbook != null)
					workbook.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
