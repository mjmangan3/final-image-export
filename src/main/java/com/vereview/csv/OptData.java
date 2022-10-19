package com.vereview.csv;

import java.util.List;

/**
 * Created by mjmangan on 9/24/17.
 */
public class OptData {
    private List<OptRow> rows;

    public List<OptRow> getRows() {
        return rows;
    }

    public void setRows(List<OptRow> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "OptData{" +
                "rows=" + rows +
                '}';
    }
}
