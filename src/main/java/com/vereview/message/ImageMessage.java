package com.vereview.message;

import com.vereview.model.PageInfo;

import java.nio.file.Path;

/**
 * Created by mjmangan on 10/2/17.
 */
public class ImageMessage {
    private PageInfo pageInfo;
    private Path exportFile;
    private String extension;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Path getExportFile() {
        return exportFile;
    }

    public void setExportFile(Path exportFile) {
        this.exportFile = exportFile;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "ImageMessage{" +
                "pageInfo=" + pageInfo +
                ", exportFile=" + exportFile +
                ", extension='" + extension + '\'' +
                '}';
    }
}
