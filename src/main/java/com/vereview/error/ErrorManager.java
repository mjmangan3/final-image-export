package com.vereview.error;

import com.vereview.csv.ErrorRow;

import java.util.concurrent.ConcurrentHashMap;

public class ErrorManager {
    public static final ErrorManager instance = new ErrorManager();

    private ConcurrentHashMap<String, ErrorRow> errorMap;

    private ErrorManager(){
        errorMap = new ConcurrentHashMap<String, ErrorRow>();
    }

    public static ErrorManager getInstance(){
        return instance;
    }

    public ConcurrentHashMap<String, ErrorRow> getErrorMap() {
        return errorMap;
    }
}
