package website.generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static XSSFWorkbook wb;
	
	public static XSSFSheet sh;
	
	
	
	public static String getCellValue(String filepath, int sheetIndex, int row, int coloumn) {
		String str = "";
		try {
			FileInputStream fis = new FileInputStream(filepath);
			
			wb = new XSSFWorkbook(fis);
			
			str = wb.getSheetAt(sheetIndex).getRow(row).getCell(coloumn).getStringCellValue();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static Object[][] getData(String filepat, int sheetindex){
		
		
		Object rowcell[][] = null;
		
		try {
			FileInputStream fis = new FileInputStream(filepat);
			wb = new XSSFWorkbook(fis);
			int rowcount = wb.getSheetAt(sheetindex).getLastRowNum();
			
			int cellcount = wb.getSheetAt(sheetindex).getRow(0).getLastCellNum();
			
			System.out.println("Cell Count ::  " + cellcount);
			System.out.println("Row count :: " + rowcount);
			
			rowcell = new Object [rowcount] [cellcount];
			
			for(int i = 0; i<rowcount; i++) {
				
				for(int j=0; j<cellcount; j++) {
					
					rowcell [i] [j] =  getCellValue(filepat, sheetindex, i+1, j);
					
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowcell;
		
	}
	
	
	
	public static void main(String[] args) {
		
		String filePath = System.getProperty("user.dir")+"/src/main/java/demo/config/Budget.xlsx";
		Object[][] getvalue = getData(filePath, 0);
		
		System.out.println(getvalue.length);
		
		for(int i = 0; i<getvalue.length; i++) {
			for(int j = 0; j<getvalue[0].length; j++) {
				
				System.out.println(getvalue[i][j]);
			}
		}
	
	}

}
