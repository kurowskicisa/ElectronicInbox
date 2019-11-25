package com.ctk.model;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class Statistic implements com.ctk.services.Statistic, Serializable {

    private Integer nameLengthMin = 0;
    private Integer nameLengthMax = 0;
    private Integer nameCounterEmpty = 0;

    private Integer regonLengthMin = 0;
    private Integer regonLengthMax = 0;
    private Integer regonCounterEmpty = 0;

    private Integer addressLengthMin = 0;
    private Integer addressLengthMax = 0;
    private Integer addressCounterEmpty = 0;

    private Integer zipLengthMin = 0;
    private Integer zipLengthMax = 0;
    private Integer zipCounterEmpty = 0;

    private Integer placeLengthMin = 0;
    private Integer placeLengthMax = 0;
    private Integer placeCounterEmpty = 0;

    private Integer uriLengthMin = 0;
    private Integer uriLengthMax = 0;
    private Integer uriCounterEmpty = 0;

    private Integer totalRecords = 0;

    private Integer dataErrorRegonCounter = 0;
    private Integer dataErrorZipCounter = 0;

    private Integer dataEmptyRegonCounter = 0;
    private Integer dataEmptyZipCounter = 0;

    public Integer getNameLengthMin() {
        return nameLengthMin;
    }

    public void setNameLengthMin(Integer nameLengthMin) {
        this.nameLengthMin = nameLengthMin;
    }

    public Integer getNameLengthMax() {
        return nameLengthMax;
    }

    public void setNameLengthMax(Integer nameLengthMax) {
        this.nameLengthMax = nameLengthMax;
    }

    public Integer getNameCounterEmpty() {
        return nameCounterEmpty;
    }

    public void setNameCounterEmpty(Integer getNameCounterEmpty) {
        this.nameCounterEmpty = getNameCounterEmpty;
    }

    public Integer getRegonLengthMin() {
        return regonLengthMin;
    }

    public void setRegonLengthMin(Integer regonLengthMin) {
        this.regonLengthMin = regonLengthMin;
    }

    public Integer getRegonLengthMax() {
        return regonLengthMax;
    }

    public void setRegonLengthMax(Integer regonLengthMax) {
        this.regonLengthMax = regonLengthMax;
    }

    public Integer getRegonCounterEmpty() {
        return regonCounterEmpty;
    }

    public void setRegonCounterEmpty(Integer regonCounterEmpty) {
        this.regonCounterEmpty = regonCounterEmpty;
    }

    public Integer getAddressLengthMin() {
        return addressLengthMin;
    }

    public void setAddressLengthMin(Integer addressLengthMin) {
        this.addressLengthMin = addressLengthMin;
    }

    public Integer getAddressLengthMax() {
        return addressLengthMax;
    }

    public void setAddressLengthMax(Integer addressLengthMax) {
        this.addressLengthMax = addressLengthMax;
    }

    public Integer getAddressCounterEmpty() {
        return addressCounterEmpty;
    }

    public void setAddressCounterEmpty(Integer addressCounterEmpty) {
        this.addressCounterEmpty = addressCounterEmpty;
    }

    public Integer getZipLengthMin() {
        return zipLengthMin;
    }

    public void setZipLengthMin(Integer zipLengthMin) {
        this.zipLengthMin = zipLengthMin;
    }

    public Integer getZipLengthMax() {
        return zipLengthMax;
    }

    public void setZipLengthMax(Integer zipLengthMax) {
        this.zipLengthMax = zipLengthMax;
    }

    public Integer getZipCounterEmpty() {
        return zipCounterEmpty;
    }

    public void setZipCounterEmpty(Integer zipCounterEmpty) {
        this.zipCounterEmpty = zipCounterEmpty;
    }

    public Integer getPlaceLengthMin() {
        return placeLengthMin;
    }

    public void setPlaceLengthMin(Integer placeLengthMin) {
        this.placeLengthMin = placeLengthMin;
    }

    public Integer getPlaceLengthMax() {
        return placeLengthMax;
    }

    public void setPlaceLengthMax(Integer placeLengthMax) {
        this.placeLengthMax = placeLengthMax;
    }

    public Integer getPlaceCounterEmpty() {
        return placeCounterEmpty;
    }

    public void setPlaceCounterEmpty(Integer placeCounterEmpty) {
        this.placeCounterEmpty = placeCounterEmpty;
    }

    public Integer getUriLengthMin() {
        return uriLengthMin;
    }

    public void setUriLengthMin(Integer uriLengthMin) {
        this.uriLengthMin = uriLengthMin;
    }

    public Integer getUriLengthMax() {
        return uriLengthMax;
    }

    public void setUriLengthMax(Integer uriLengthMax) {
        this.uriLengthMax = uriLengthMax;
    }

    public Integer getUriCounterEmpty() {
        return uriCounterEmpty;
    }

    public void setUriCounterEmpty(Integer uriCounterEmpty) {
        this.uriCounterEmpty = uriCounterEmpty;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getDataErrorRegonCounter() {
        return dataErrorRegonCounter;
    }

    public void setDataErrorRegonCounter(Integer dataErrorRegonCounter) {
        this.dataErrorRegonCounter = dataErrorRegonCounter;
    }

    public Integer getDataErrorZipCounter() {
        return dataErrorZipCounter;
    }

    public void setDataErrorZipCounter(Integer dataErrorZipCounter) {
        this.dataErrorZipCounter = dataErrorZipCounter;
    }

    public Integer getDataEmptyRegonCounter() {
        return dataEmptyRegonCounter;
    }

    public void setDataEmptyRegonCounter(Integer dataEmptyRegonCounter) {
        this.dataEmptyRegonCounter = dataEmptyRegonCounter;
    }

    public Integer getDataEmptyZipCounter() {
        return dataEmptyZipCounter;
    }

    public void setDataEmptyZipCounter(Integer dataEmptyZipCounter) {
        this.dataEmptyZipCounter = dataEmptyZipCounter;
    }

}
