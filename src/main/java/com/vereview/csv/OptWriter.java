package com.vereview.csv;

import com.vereview.error.ErrorManager;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.PrintStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by mjmangan on 9/24/17.
 */
public class OptWriter {
    private static final String[] HEADER = {"documentId", "volumeId", "relativePath", "page1", "box", "folder", "pageCount"};
    private ErrorManager errorManager = ErrorManager.getInstance();
    private OptData optData;

    public OptWriter(OptData optData) {
        this.optData = optData;
    }

    public void createOpt(Path exportFile) throws Exception{
        CsvPreference csvPreference = new CsvPreference.Builder(CsvPreference.STANDARD_PREFERENCE).useQuoteMode(new OptQuoteMode()).build();

        try (Writer w = Files.newBufferedWriter(exportFile); ICsvBeanWriter beanWriter = new CsvBeanWriter(w, csvPreference)){
            for(OptRow row : optData.getRows()){
                if(errorManager.getErrorMap().containsKey(row.getDocumentId())){
                    continue;
                }
                beanWriter.write(row, HEADER);
            }
        }catch (Exception e){
            throw e;
        }
    }

}
