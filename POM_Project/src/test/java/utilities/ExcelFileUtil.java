package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {

	Workbook wb;
	public ExcelFileUtil(String Excelpath)throws Throwable
	{
		FileInputStream fi = new FileInputStream(Excelpath);
		wb = WorkbookFactory.create(fi);
	}
	public int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
	}
	public String getCellData(String sheetName,int row,int column)
	{
		String data = " ";
		if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
		{
			int celldata = (int)wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else
		{
			data = wb. getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	
	public void setCellData(String sheetName,int row,int column,String status,String WriteExcel)throws Throwable
	{
		Sheet ws = wb.getSheet(sheetName);
		Row rowNum = ws.getRow(row);
		Cell cell = rowNum.createCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
			
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);

		}
		else if (status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);

		}
		FileOutputStream fo = new FileOutputStream(WriteExcel);
		wb.write(fo);
		
	}
	public static void main(String[] args)throws Throwable {
		  ExcelFileUtil xl = new ExcelFileUtil("D:/Excel Sheets/Emp.xlsx");
		  int rc = xl.rowCount("Emp");
		  System.out.println(rc);
		  for (int i=1;i<=rc;i++)
		  {
			  String fname = xl.getCellData("Emp", i, 0);
			  String lname = xl.getCellData("Emp", i, 1);
			  String eid = xl.getCellData("Emp", i, 2);
			  String sly = xl.getCellData("Emp", i, 3);
			  System.out.println(fname+"  "+lname+"  "+eid+"  "+sly);
			  xl.setCellData("Emp", i, 4, "pass", "D:/Results.xlsx");
			  
			  
		  }
	}
	
	
	
	
}
