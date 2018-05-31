/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author john
 */
public class File_Details {

    public static XSSFWorkbook readFileName(String excelFilePath) throws Exception {
        // Spacifying the path to the excel to be read...
        FileInputStream fname = new FileInputStream(new File(excelFilePath));
        // creating the workbook with the file ......
        XSSFWorkbook workbook = new XSSFWorkbook(fname);
        //returning the workbook to the caller
        return workbook;
    }

    public static int getWorksheets(String file) throws Exception {
        Workbook workbook = readFileName(file);
        int sheetCounts = workbook.getNumberOfSheets();
        return sheetCounts;
    }

    public static String getWorksheetName(String file, int sheet) throws Exception {
        Workbook workbook = readFileName(file);
        String sheetName = workbook.getSheetName(sheet);
        return sheetName;
    }

    public static int getIndex(String file, String sheet) throws Exception {
        Workbook workbook = readFileName(file);
        int index = workbook.getSheetIndex(sheet);
        return index;
    }

    public static void deleteSheet(String file, String sheet) throws Exception {
        Workbook workbook = readFileName(file);
        int index = workbook.getSheetIndex(sheet);
        if (index >= 0) {
            workbook.setSheetHidden(index, false);
        }
        System.out.println("Deleted:   " + sheet + "  :  Index: " + index);
    }

    public static List<String> readReposNames(String file) throws Exception {
        int x = 0;
        // array list to store the Repos names
        ArrayList<String> list = new ArrayList<String>();
        //calling the file name.....
        XSSFWorkbook workbook = readFileName(file);
        // setting the sheet number...
        XSSFSheet spreadsheet = workbook.getSheetAt(x);
        String sname = workbook.getSheetName(x);

        Row row;
        Cell cell = null;
        for (int j = 0; j < spreadsheet.getLastRowNum() + 1; ++j) {//To loop thru the rows in a sheet
            row = spreadsheet.getRow(j);
            cell = row.getCell(0); //forks are in the eighth column...
            switch (cell.getCellType()) {
                //Checking for strings values inthe cells..
                case Cell.CELL_TYPE_STRING:
                    if (!cell.getStringCellValue().equals("")) {
                        // adding the call value to the arraylist called forksList 
                        list.add(cell.getStringCellValue());
                    }//end of if statement...
                    break;
                //Checking for numeric values inthe cells..
                case Cell.CELL_TYPE_NUMERIC:
                    list.add(String.valueOf(cell.getNumericCellValue()));
                    break;
                //Checking for bank in the cells..
                case Cell.CELL_TYPE_BLANK:
                    break;
            }//end of switch statement

        }// end of  for loop for the rows.. 
        //returns the arraylist to the main class....
        return list;
    }

    public static String setProjectName(String file, int loop, String point) throws Exception {
        String projectName = "";
        try {
            CellReference pname = new CellReference(point);
            FileInputStream fisi = new FileInputStream(new File(file));
            XSSFWorkbook workbook = new XSSFWorkbook(fisi);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            XSSFSheet sheets = workbook.getSheetAt(loop);
            Row row = sheets.getRow(pname.getRow());

            if (row != null) {
                Cell cell = row.getCell(pname.getCol());
                CellValue cellValue = evaluator.evaluate(cell);
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            projectName = String.valueOf(cell.getNumericCellValue());

                            break;
                        case Cell.CELL_TYPE_STRING:
                            projectName = cell.getStringCellValue();
                            break;
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return projectName;
        }
        /////System.out.println(projectName);  

        return projectName;
    }
}
