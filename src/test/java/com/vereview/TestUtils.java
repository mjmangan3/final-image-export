package com.vereview;

import com.vereview.csv.OptData;
import com.vereview.csv.OptRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjmangan on 9/24/17.
 */
public class TestUtils {

    public static OptData createTestOptData(){
        OptData data = new OptData();

        List<OptRow> rows = new ArrayList<>();

        OptRow row1 = new OptRow();
        row1.setDocumentId("VE00001");
        row1.setRelativePath("IMAGES\\0001\\VE00001.tif");
        row1.setPage1("Y");
        row1.setPageCount("3");
        rows.add(row1);

        OptRow row2 = new OptRow();
        row2.setDocumentId("VE00002");
        row2.setRelativePath("IMAGES\\0001\\VE00002.tif");
        row2.setPage1("");
        row2.setPageCount("");
        rows.add(row2);

        OptRow row3 = new OptRow();
        row3.setDocumentId("VE00003");
        row3.setRelativePath("IMAGES\\0001\\VE00003.tif");
        row3.setPage1("");
        row3.setPageCount("");
        rows.add(row3);

        OptRow row4 = new OptRow();
        row4.setDocumentId("VE00004");
        row4.setRelativePath("IMAGES\\0001\\VE00004.tif");
        row4.setPage1("Y");
        row4.setPageCount("2");
        rows.add(row4);

        OptRow row5 = new OptRow();
        row5.setDocumentId("VE00005");
        row5.setRelativePath("IMAGES\\0001\\VE00005.tif");
        row5.setPage1("");
        row5.setPageCount("");
        rows.add(row5);

        data.setRows(rows);

        return data;
    }



}
