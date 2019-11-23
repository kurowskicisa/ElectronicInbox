package com.ctk.services;

public interface ElectronicInboxFilter {

    String getName();

    void setName(String name);

    String getAddress();

    void setAddress(String address);

    String getPlace();

    void setPlace(String place);

    String getPage();

    void setPage(String page);

    Integer getPrevPage();

    void setPrevPage(Integer prevPage);

    Integer getNextPage();

    void setNextPage(Integer nextPage);

    Integer getTotalPages();

    void setTotalPages(Integer totalPages);

    double getTotalFilteredRecords();

    void setTotalFilteredRecords(double totalRecords);

    double getTotalRecords();

    void setTotalRecords(double totalRecords);

}
