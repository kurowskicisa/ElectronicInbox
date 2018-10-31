package com.ctk.dao;

import com.ctk.model.ElectronicInbox;

import java.util.ArrayList;
import java.util.List;

public class ElectronicInboxDao {

    private final List<ElectronicInbox> list = new ArrayList<>();

    public List<ElectronicInbox> getList() {
        return list;
    }

    private void setList(ElectronicInbox electronicInbox){
        this.list.add(electronicInbox);
    }
}
