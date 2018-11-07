package com.ctk.model;

import javax.enterprise.context.SessionScoped;

import java.io.Serializable;



@SessionScoped
public class ElectronicInboxFilterFile implements Serializable {

    private String name;
    private String address;
    private String place;
    private String page;
    private Integer totalPages;
    private double totalFilteredRecords;
    private double totalRecords;

    public ElectronicInboxFilterFile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public double getTotalFilteredRecords() {
        return totalFilteredRecords;
    }

    public void setTotalFilteredRecords(double totalRecords) {
        this.totalFilteredRecords = totalRecords;
    }

    public double getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(double totalRecords) {
        this.totalRecords = totalRecords;
    }
}
