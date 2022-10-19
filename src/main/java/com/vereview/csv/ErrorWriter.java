package com.vereview.csv;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ErrorWriter {
    private static final String[] HEADER = {"documentId", "message"};
    private Map<String, ErrorRow> rows;

    public ErrorWriter(Map<String, ErrorRow> rows) {
        this.rows = rows;
    }

    public void createOpt(Path exportFile) throws Exception{
        CsvPreference csvPreference = new CsvPreference.Builder(CsvPreference.STANDARD_PREFERENCE).build();

        try (Writer w = Files.newBufferedWriter(exportFile); ICsvBeanWriter beanWriter = new CsvBeanWriter(w, csvPreference)){
            beanWriter.writeHeader(HEADER);
            for(ErrorRow row : rows.values()) {
                beanWriter.write(row, HEADER);
            }
        }catch (Exception e){
            throw e;
        }
    }
}
