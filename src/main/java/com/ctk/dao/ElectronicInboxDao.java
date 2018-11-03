package com.ctk.dao;

import com.ctk.model.ElectronicInbox;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class ElectronicInboxDao {

    private final List<ElectronicInbox> list = new ArrayList<>();

    public List<ElectronicInbox> getList() {
        return list;
    }

    public void setList(ElectronicInbox eInbox) {
        this.list.add(eInbox);
    }
}
