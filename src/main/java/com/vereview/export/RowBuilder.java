package com.vereview.export;

import com.vereview.csv.OptRow;
import com.vereview.model.PageInfo;

/**
 * Created by mjmangan on 9/16/17.
 */
public class RowBuilder {
    private OptRow row;
    private PageInfo pageInfo;

    public RowBuilder(PageInfo pageInfo) {
        this.row = new OptRow();
        this.pageInfo = pageInfo;
    }

    public RowBuilder docId(){
        String did = null;
        if(pageInfo.getPageNumber() == 1L){
            did = pageInfo.getDocumentId();
        }else {

        }
        row.setDocumentId(did);
        return this;
    }
}
