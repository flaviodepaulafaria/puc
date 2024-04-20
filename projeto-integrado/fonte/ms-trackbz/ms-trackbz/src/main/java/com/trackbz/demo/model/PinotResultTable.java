package com.trackbz.demo.model;

import java.util.List;

public class PinotResultTable {

    private PinotDataSchema dataSchema;
    private List<List<Object>> rows;

    public PinotDataSchema getDataSchema() {
        return dataSchema;
    }

    public void setDataSchema(PinotDataSchema dataSchema) {
        this.dataSchema = dataSchema;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }
}
