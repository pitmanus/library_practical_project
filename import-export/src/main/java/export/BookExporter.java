package export;

import java.io.*;
import java.util.*;

import model.*;
import org.apache.poi.sl.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;


public class BookExporter {
    private static final String[] HEADERS = new String[]{"title", "release", "isbn", "type", "pages", "description"};
    private static final String SHEET_NAME = "books";

    public void export(List<Book> books) throws XlsxParseException{
        try(XSSFWorkbook workbook = new XSSFWorkbook ()) {
            XSSFSheet sheet = workbook.createSheet (SHEET_NAME);
            createSheetHeader (workbook, sheet, HEADERS);

            int rowNumber = 1;
            for (Book book : books){
                Row row = sheet.createRow (rowNumber++);
                setStringValues (book.getTitle (),row, BookXlsxDefinition.TITLE_COLUMN_0 );
                setStringValues (book.getPublicationDate ().toString (), row, BookXlsxDefinition.RELEASE_COLUMN_1);
                setStringValues (book.getIsbn (), row, BookXlsxDefinition.ISBN_COLUMN_2);
                setStringValues (book.getGenresOfBooks ().toString (), row, BookXlsxDefinition.TYPE_COLUMN_4);
                setStringValues (book.getNumberOfPages ().toString (), row, BookXlsxDefinition.PAGES_COLUMN_4);
                setStringValues (book.getShortDescription (), row, BookXlsxDefinition.DESCRIPTION_COLUMN_5);
            }
            FileOutputStream outputStream = new FileOutputStream ("import-export/src/main/java/export/exported_files/new.xlsx");
            workbook.write (outputStream);
            outputStream.flush ();
            outputStream.close ();
            System.out.println ("Success.");
        } catch (IOException e) {
            throw new XlsxParseException ("Can not create xlsx file");
        }
    }

    private void createSheetHeader(XSSFWorkbook workbook, Sheet sheet, String[] header){

        Row headerRow = sheet.createRow (0);
        int lenght = header.length;
        for (int i = 0; i < lenght; ++i){
            sheet.setColumnWidth (i, 5000);
            Cell createCell = headerRow.createCell (i);
            createCell.setCellValue(header[i]);
        }
    }

    private void setStringValues(String value, Row row, int index){
    row.createCell (index).setCellValue(value);
    }
}
