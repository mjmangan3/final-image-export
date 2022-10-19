package com.vereview.csv;

/**
 * Created by mjmangan on 9/24/17.
 */
public class OptRow {
    private String documentId;
    private String volumeId = "";
    private String relativePath;
    private String page1;
    private String box = "";
    private String folder = "";
    private String pageCount;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getPage1() {
        return page1;
    }

    public void setPage1(String page1) {
        this.page1 = page1;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "OptRow{" +
                "documentId='" + documentId + '\'' +
                ", volumeId='" + volumeId + '\'' +
                ", relativePath='" + relativePath + '\'' +
                ", page1='" + page1 + '\'' +
                ", box='" + box + '\'' +
                ", folder='" + folder + '\'' +
                ", pageCount='" + pageCount + '\'' +
                '}';
    }
}
