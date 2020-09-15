package export;

import java.io.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import model.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class BookParser {
    public List<Book> parse (String path) throws IOException {
        List<Book> bookList = new ArrayList<> ();
        try (FileInputStream stream = new FileInputStream (path)) {
            XSSFWorkbook workbook = new XSSFWorkbook (stream);
            XSSFSheet sheet = workbook.getSheet ("Books");

            for (Row row : getRows (sheet)) {
                Book book = new Book ();
                book.setTitle (getStringValue (row, BookXlsxDefinition.TITLE_COLUMN_0));
                book.setPublicationDate (getLocalDateValue (row, BookXlsxDefinition.RELEASE_COLUMN_1));
                book.setIsbn (getStringValue (row, BookXlsxDefinition.ISBN_COLUMN_2));
                book.setGenresOfBooks (GenresOfBooks.valueOf (getEnumValue (row, BookXlsxDefinition.TYPE_COLUMN_4)));
                book.setNumberOfPages (getIntegerValue (row, BookXlsxDefinition.PAGES_COLUMN_4));
                book.setShortDescription (getStringValue (row, BookXlsxDefinition.DESCRIPTION_COLUMN_5));
                bookList.add (book);
            }

        } catch (IOException e) {
            System.out.println (e);
        }
        return bookList;
    }

    private String getStringValue (Row row, int index) {
        return row.getCell (index).getStringCellValue ();
    }

    private Integer getIntegerValue (Row row, int index) {
        int integer = (int) row.getCell (index).getNumericCellValue ();
        return integer;
    }

    private LocalDate getLocalDateValue (Row row, int index) {
        Date date = row.getCell (index).getDateCellValue ();
        LocalDate localDate = LocalDate.parse (new SimpleDateFormat ("yyyy-MM-dd").format (date));
        return localDate;
    }

    private String getEnumValue (Row row, int index) {
        return row.getCell (index).getStringCellValue ().toUpperCase ();
    }

    private List<Row> getRows (XSSFSheet sheet) {
        int numOfRows = sheet.getPhysicalNumberOfRows ();
        List<Row> rows = new ArrayList<> ();
        for (int i = 1; i < numOfRows; i++) {
            Row row = sheet.getRow (i);
            rows.add (row);
        }
        return rows;
    }

}

