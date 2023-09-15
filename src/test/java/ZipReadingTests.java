import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipReadingTests {

    @Test
    void readPdfFromZip() throws IOException {
        ZipFile file = new ZipFile(new File("src/test/resources/resources.zip"));

        for (ZipEntry entry : Collections.list(file.entries())) {
            if (entry.getName().endsWith(".pdf")) {
                try (InputStream is = file.getInputStream(entry)) {
                    PDF pdf = new PDF(is);
                    Assertions.assertTrue(pdf.text.contains("Sberbank"));
                }
            }
        }
    }

    @Test
    void readCsvFromZip() throws IOException, CsvException {
        ZipFile file = new ZipFile(new File("src/test/resources/resources.zip"));

        for (ZipEntry entry : Collections.list(file.entries())) {
            if (entry.getName().endsWith(".csv")) {
                try (InputStream is = file.getInputStream(entry); //ZipReadingTest.class.getResourceAsStream(entry.getName());
                     CSVReader reader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

                    List<String[]> content = reader.readAll();
                    Assertions.assertEquals(3, content.size());

                    final String[] firstRow = content.get(0);
                    final String[] secondRow = content.get(1);
                    final String[] thirdRow = content.get(2);

                    Assertions.assertArrayEquals(new String[] {"Новосибирск", "Прогноз погоды", "Микрорайон Тихий Центр" }, firstRow);
                    Assertions.assertArrayEquals(new String[] {"Екатеринбург", "в Екатеринбурге", "Жилой район Центральный" }, secondRow);
                    Assertions.assertArrayEquals(new String[] {"Берлин", "на 10 дней", "Погода в Берлине" }, thirdRow);
                }
            }
        }
    }

    @Test
    void readExcelFromZip() throws IOException {
        ZipFile file = new ZipFile(new File("src/test/resources/resources.zip"));

        for (ZipEntry entry : Collections.list(file.entries())) {
            if (entry.getName().endsWith(".xlsx")) {
                try(InputStream stream = file.getInputStream(entry)) {
                    XLS xls = new XLS(stream);
                    String a1 = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                    List<String> values = new ArrayList<>();

                    for (int i = 0; i < 15; i++) {
                        String x = xls.excel.getSheetAt(0).getRow(4).getCell(i).getStringCellValue();
                        if(x != null){values.add(x); }
                    }

                    Assertions.assertEquals("Список контактов", a1);
                    Assertions.assertEquals(0, values.indexOf("Личные"));
                    Assertions.assertEquals(1, values.indexOf("Smith"));
                    Assertions.assertEquals(2, values.indexOf("Jane"));
                    Assertions.assertEquals(9, values.indexOf("jgirl"));
                }
            }
        }
    }
}