package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    static Workbook workbook;
    static Sheet sheet;

    public static void openExcel(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    //open the sheet in excel file
    public static void getSheet(String sheetName) {
        sheet = workbook.getSheet(sheetName);
    }

    //it will return the total no. of rows available in the worksheet
    public static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    //it will return the total no. of columns in every row
    public static int getColumnsCount(int rowIndex) {
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

    //it will return the data from cell in String format
    public static String getCellData(int rowIndex, int colIndex) {
        return sheet.getRow(rowIndex).getCell(colIndex).toString();

    }

    public static List<Map<String, String>> excelIntoMap(String filePath, String sheetName) {
        openExcel(filePath);
        getSheet(sheetName);

        List<Map<String, String>> ListData = new ArrayList<>();

        //outer loop
        for (int row = 1; row < getRowCount(); row++) {
            //creating a map for every row
            Map<String, String> map = new LinkedHashMap<>();
            for (int col = 0; col < getColumnsCount(row); col++) {
                map.put(getCellData(0, col), getCellData(row, col));
            }
            ListData.add(map);
        }
        return ListData;

    }

}
