package com.ctk.dao;

import com.ctk.model.ElectronicInbox;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class ElectronicInboxDao extends com.ctk.model.ElectronicInbox implements Serializable {

    private final List<ElectronicInbox> list = new ArrayList<>();

    public List<ElectronicInbox> getList() {
        return list;
    }

    public void setList(ElectronicInbox eInbox) {
        this.list.add(eInbox);
    }

    public void clearList() {
        this.list.clear();
    }
}
