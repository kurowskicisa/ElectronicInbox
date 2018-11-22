package com.ctk.dao;

import com.ctk.model.ElectronicInboxImpl;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class ElectronicInboxDao implements Serializable {

    private final List<ElectronicInboxImpl> list = new ArrayList<>();

    public List<ElectronicInboxImpl> getList() {
        return list;
    }

    public void setList(ElectronicInboxImpl eInbox) {
        this.list.add(eInbox);
    }

    public void clearList() {
        this.list.clear();
    }
}
