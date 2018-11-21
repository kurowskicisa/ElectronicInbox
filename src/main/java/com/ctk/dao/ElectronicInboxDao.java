package com.ctk.dao;

import com.ctk.model.ElectronicInbox;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class ElectronicInboxDao implements Serializable {

    private final List<ElectronicInbox> list = new ArrayList<>();

    public List<ElectronicInbox> getList() {
        return list;
    }

    public void setList(ElectronicInbox eInbox) {
        this.list.add(eInbox);
    }
}
