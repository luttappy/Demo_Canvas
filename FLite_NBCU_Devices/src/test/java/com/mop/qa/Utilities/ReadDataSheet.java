package com.mop.qa.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class ReadDataSheet {
	public  WebDriver dr;
	public  HSSFWorkbook wb;
	public HSSFSheet ws;
	public int rowCount;
	public String className;
	public String sheetName;
	public String colName;
	public String value;

	public  int getRownumber(String testCaseName, String colHeader) throws MPException {
		int rownumber = 0;
		rowCount = ws.getLastRowNum();
		
		for (int j = 1; j <= rowCount; j++) {
			HSSFRow row = ws.getRow(j);
			if (row.getCell(0).getStringCellValue()
					.equalsIgnoreCase(testCaseName)) {
				rownumber = j;
				break;
			}

		}
		if (rownumber == 0) {
			throw new MPException("Class Entry missing in DataSheet");
		}

		getColumnNumber(colHeader);
		return rownumber;
	}

	public  int getColumnNumber(String columnHeader) throws MPException {

		HSSFRow row = ws.getRow(0);
		int columnNumber = 0;
		int isValid =0;
		for (int j = ws.getFirstRowNum(); j < row.getPhysicalNumberOfCells(); j++) {
			 //System.out.println("corresponding cell value is "+ row.getCell(j).toString());
			if (row.getCell(j).toString().equalsIgnoreCase(columnHeader)) {
				columnNumber = j;
				isValid =1;
				break;
			}

		}
		if (isValid == 0) {
			throw new MPException("Enter proper column in DataSheet");
		}
	//	System.out.println("column number is " + columnNumber);
		return columnNumber;

	}

	public String getValue(String SheetName, String className,
			String columnHeader) throws MPException {
		
		try {
			FileInputStream file = new FileInputStream(new File("./DataSheet.xls"));
			
			wb = new HSSFWorkbook(file);

			ws = wb.getSheet(SheetName);
			int rownumber = getRownumber(className, columnHeader);
			int columnNumber = getColumnNumber(columnHeader);
			HSSFCell cell = ws.getRow(rownumber).getCell(columnNumber);
			if(cell != null){
				value = cell.toString();
			}
			
//			System.out.println("value is " + value);
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;

	}
	
	public String getAppProperties(String key) throws IOException {
		String value = "";
		try {

			FileInputStream fileInputStream = new FileInputStream(
					"data.properties");
			Properties property = new Properties();
			property.load(fileInputStream);

			value = property.getProperty(key);

			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

}
