package com.vereview.csv;

import com.vereview.export.RowDto;
import com.vereview.model.PageInfo;

public class ErrorRow {
    private String documentId;
    private String message;

    public ErrorRow(String documentId, String message) {
        this.documentId = documentId;
        this.message = message;
    }

    public ErrorRow() {
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorRow{" +
                "documentId='" + documentId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


}
