package com.vereview.csv;

import org.supercsv.prefs.CsvPreference;
import org.supercsv.quote.QuoteMode;
import org.supercsv.util.CsvContext;

/**
 * Created by mjmangan on 9/24/17.
 */
public class OptQuoteMode implements QuoteMode {

    public boolean quotesRequired(String csvColumn, CsvContext context, CsvPreference preference){
        return false;
    }
}
