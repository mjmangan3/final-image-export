package com.vereview.export;

import com.vereview.csv.OptRow;
import com.vereview.model.PageInfo;
import com.vereview.utils.ExportUtils;

import java.io.File;

/**
 * Created by mjmangan on 9/30/17.
 */
public class RowDto {
    private static final String IMAGE_FOLDER_NAME = "IMAGE";
    private OptRow optRow;
    private PageInfo pageInfo;
    private Integer foderNumber;

    public RowDto(PageInfo pageInfo, Integer foderNumber) {
        this.optRow = new OptRow();
        this.pageInfo = pageInfo;
        this.foderNumber = foderNumber;
    }

    public RowDto(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public RowDto() {
    }

    public void setOptRow(OptRow optRow) {
        this.optRow = optRow;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Integer getFoderNumber() {
        return foderNumber;
    }

    public void setFoderNumber(Integer foderNumber) {
        this.foderNumber = foderNumber;
    }

    public OptRow getOptRow(){
        optRow.setDocumentId(createDocumentId());
        optRow.setPageCount(createPageCount());
        optRow.setVolumeId("");
        optRow.setFolder("");
        optRow.setBox("");
        optRow.setPage1(pageInfo.getPageNumber() == 1?"Y":"");
        optRow.setRelativePath(createFilePath());
        return optRow;
    }

    public String createDocumentId(){
        if(pageInfo.getPageNumber() == 1){
            return pageInfo.getDocumentId();
        }else {
            return pageInfo.getDocumentId() + "_" + ExportUtils.getPaddedNumber(pageInfo.getPageNumber().intValue());
        }
    }

    protected String createPageCount(){
        if(pageInfo.getPageNumber() == 1){
            return String.valueOf(pageInfo.getPageCount());
        }
        return null;
    }

    protected String createFilePath(){
        return IMAGE_FOLDER_NAME + "\\" + ExportUtils.getPaddedNumber(foderNumber) + "\\" + createExportFileName();

    }

    protected String createExportFileName(){
        String extension = ExportUtils.getExtension(pageInfo.getPageLocation());
        String e = null;
        if(extension.equalsIgnoreCase("png")) {
            e = "jpg";
        }else if(extension.equalsIgnoreCase("tmp")){
            e = "tif";
        }else {
            e = extension;
        }
        return createDocumentId() + "." + e;
    }




}
