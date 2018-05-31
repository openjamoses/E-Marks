/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.File_Details;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author john
 */
public class Pick_GeneralNumeric {

    public static ArrayList<Double> pick(String file, int loop, int position) throws Exception {
        int j, x;
        double empty = 0;
        // array list to store the forks
        ArrayList<Double> lists = new ArrayList<>();
        //calling the file name.....
        XSSFWorkbook workbook = File_Details.readFileName(file);
        x = loop;// setting the sheet number...
        XSSFSheet spreadsheet = workbook.getSheetAt(x);
        String sname = workbook.getSheetName(x);

        Row row;
        Cell cell = null;
        for (j = 0; j < spreadsheet.getLastRowNum() + 1; ++j) {//To loop thru the rows in a sheet
            row = spreadsheet.getRow(j);
            cell = row.getCell(position); //forks are in the eighth column...
            if (cell != null) {
                switch (cell.getCellType()) {
                    //Checking for strings values inthe cells..
                    case Cell.CELL_TYPE_STRING:

                        break;
                    //Checking for numeric values inthe cells..
                    case Cell.CELL_TYPE_NUMERIC:
                        lists.add(cell.getNumericCellValue());
                        break;
                    //Checking for bank in the cells..
                    case Cell.CELL_TYPE_BLANK:
                        lists.add(empty);
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        lists.add(empty);
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        lists.add(empty);
                        break;
                }//end of switch statement
            }

        }// end of  for loop for the rows..

        //returns the arraylist to the main class....
        return lists;
    }

    public static ArrayList<Double> pick_3(String file, int loop, int position, int next) throws Exception {
        int j, x;
        double empty = 0;
        // array list to store the forks
        ArrayList<Double> lists = new ArrayList<>();
        //calling the file name.....
        XSSFWorkbook workbook = File_Details.readFileName(file);
        x = loop;// setting the sheet number...
        XSSFSheet spreadsheet = workbook.getSheetAt(x);
        String sname = workbook.getSheetName(x);

        Row row;
        Cell cell = null;
        for (j = next; j < spreadsheet.getLastRowNum() + 1; ++j) {//To loop thru the rows in a sheet
            row = spreadsheet.getRow(j);
            cell = row.getCell(position); //forks are in the eighth column...
            if (cell != null) {
                switch (cell.getCellType()) {
                    //Checking for strings values inthe cells..
                  
                    //Checking for numeric values inthe cells..
                    case Cell.CELL_TYPE_NUMERIC:
                        lists.add(cell.getNumericCellValue());
                        break;
                    //Checking for bank in the cells..
                    case Cell.CELL_TYPE_BLANK:
                        lists.add(empty);
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        lists.add(empty);
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        lists.add(empty);
                        break;
                }//end of switch statement
            }

        }// end of  for loop for the rows..

        //returns the arraylist to the main class....
        return lists;
    }

    
    public static ArrayList<Double> pick2(String file, int loop, int last, int position, int next) throws Exception {
        int j, x;
        double empty = 0;
        // array list to store the forks
        ArrayList<Double> lists = new ArrayList<>();
        //calling the file name.....
        XSSFWorkbook workbook = File_Details.readFileName(file);
        x = loop;// setting the sheet number...
        XSSFSheet spreadsheet = workbook.getSheetAt(x);
        String sname = workbook.getSheetName(x);

        Row row;
        Cell cell = null;
        for (j = next; j < last + 1; ++j) {//To loop thru the rows in a sheet
            row = spreadsheet.getRow(j);
            cell = row.getCell(position); //forks are in the eighth column...
            if (cell != null) {
                switch (cell.getCellType()) {
                    //Checking for strings values inthe cells..
                    case Cell.CELL_TYPE_STRING:

                        break;
                    //Checking for numeric values inthe cells..
                    case Cell.CELL_TYPE_NUMERIC:
                        lists.add(cell.getNumericCellValue());
                        break;
                    //Checking for bank in the cells..
                    case Cell.CELL_TYPE_BLANK:
                        lists.add(empty);
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        lists.add(empty);
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        lists.add(empty);
                        break;
                }//end of switch statement
            } else {
                lists.add(empty);
            }

        }// end of  for loop for the rows..

        //returns the arraylist to the main class....
        return lists;
    }
}
