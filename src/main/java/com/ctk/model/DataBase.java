package com.ctk.model;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;

@RequestScoped
public class DataBase implements Serializable {
    private String dataBaseDateUpdate;
    private String dataBaseRecordsCounter;

    public DataBase() {
    }

    public DataBase(String dataBaseDateUpdate, String dataBaseRecordsCounter) {
        this.dataBaseDateUpdate = dataBaseDateUpdate;
        this.dataBaseRecordsCounter = dataBaseRecordsCounter;
    }

    public String getDataBaseDateUpdate() {
        return dataBaseDateUpdate;
    }

    public void setDataBaseDateUpdate(String dataBaseDateUpdate) {
        this.dataBaseDateUpdate = dataBaseDateUpdate;
    }

    public String getDataBaseRecordsCounter() {
        return dataBaseRecordsCounter;
    }

    public void setDataBaseRecordsCounter(String dataBaseRecordsCounter) {
        this.dataBaseRecordsCounter = dataBaseRecordsCounter;
    }
}
