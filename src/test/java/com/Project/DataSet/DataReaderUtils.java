package com.Project.DataSet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReaderUtils {

	public static FileInputStream openFile(String path) throws FileNotFoundException {
		File files = new File(path);
		FileInputStream file = new FileInputStream(files);
		return file;
	}

	public static Workbook openWorkbook(FileInputStream file) throws IOException {
		Workbook workbook = new XSSFWorkbook(file);
		return workbook;
	}

	public static Sheet getSheet(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);
		return sheet;
	}

	public static void workbookClose() throws FileNotFoundException, IOException {
		openWorkbook(openFile("E:\\Automation\\Automation\\src\\test\\resource\\DataSet.xlsx")).close();
		openFile("E:\\Automation\\Automation\\src\\test\\resource\\DataSet.xlsx").close();
	}

	public static Map<Integer, Map<String, String>> readExcelUtils() throws IOException {
		Map<Integer, Map<String, String>> dataMap = new LinkedHashMap<>(); // maintain order
		// Open workbook once
		String filePath = "E:\\Automation\\Automation\\src\\test\\resource\\DataSet.xlsx";
		try (Workbook workbook = openWorkbook(openFile(filePath))) {
			Sheet sheet = workbook.getSheetAt(0);
			// Loop through rows (skip header)
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row == null)
					continue;
				int sno = (int) row.getCell(0).getNumericCellValue();
				String toRun = row.getCell(1).getStringCellValue();
				String input = row.getCell(2).getStringCellValue();
				// Each row should have its own new map
				Map<String, String> rowData = new LinkedHashMap<>();
				rowData.put(toRun, input);
				dataMap.put(sno, rowData);
			}
		}
		return dataMap;
	}

	public static Map<Map<Integer, String>, Map<String, String>> readExcelUtilsNaukri() throws IOException {
		Map<Map<Integer, String>, Map<String, String>> dataMap = new LinkedHashMap<>(); // maintain order
		// Open workbook once
		String filePath = "E:\\Automation\\Automation\\src\\test\\resource\\Naukri_Set.xlsx";
		try (Workbook workbook = openWorkbook(openFile(filePath))) {
			Sheet sheet = workbook.getSheetAt(0);
			// Loop through rows (skip header)
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row == null)
					continue;
				int sno = (int) row.getCell(0).getNumericCellValue();
				String toRun = row.getCell(1).getStringCellValue();
				String jobTitle = row.getCell(2).getStringCellValue();
				String location = row.getCell(3).getStringCellValue();
				// Each row should have its own new map
				Map<Integer, String> rowData = new LinkedHashMap<>();
				Map<String, String> rowData1 = new LinkedHashMap<>();
				rowData.put(sno, toRun);
				rowData1.put(jobTitle, location);
				dataMap.put(rowData, rowData1);
			}
		}
		return dataMap;
	}

}
