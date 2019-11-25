package com.ctk.model;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;

@RequestScoped
public class DataBase implements com.ctk.services.DataBase, Serializable {
    private String dataBaseDateUpdate;
    private String dataBaseRecordsCounter;

    public DataBase() {
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
