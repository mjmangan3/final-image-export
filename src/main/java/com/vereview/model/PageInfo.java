package com.vereview.model;

import java.nio.file.Path;

/**
 * Created by mjmangan on 9/16/17.
 */
public class PageInfo {
    private Long fileId;
    private String DocumentId;
    private Long pageNumber;
    private Path pageLocation;
    private Long pageCount;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getDocumentId() {
        return DocumentId;
    }

    public void setDocumentId(String documentId) {
        DocumentId = documentId;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Path getPageLocation() {
        return pageLocation;
    }

    public void setPageLocation(Path pageLocation) {
        this.pageLocation = pageLocation;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "fileId=" + fileId +
                ", DocumentId='" + DocumentId + '\'' +
                ", pageNumber=" + pageNumber +
                ", pageLocation=" + pageLocation +
                ", pageCount=" + pageCount +
                '}';
    }
}
