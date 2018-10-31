package com.ctk.model;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class ElectronicInbox implements Serializable {

    //   private Long id;
    private String name;
    private String regon;
    private String addres;
    private String zip;
    private String place;
    private String uri;

    public ElectronicInbox() {
    }

    public ElectronicInbox(String name, String regon, String addres, String zip, String place, String uri) {
        this.name = name;
        this.regon = regon;
        this.addres = addres;
        this.zip = zip;
        this.place = place;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElectronicInbox that = (ElectronicInbox) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (regon != null ? !regon.equals(that.regon) : that.regon != null) return false;
        if (addres != null ? !addres.equals(that.addres) : that.addres != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        return uri != null ? uri.equals(that.uri) : that.uri == null;
    }

    @Override
    public int hashCode() {
        int result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (regon != null ? regon.hashCode() : 0);
        result = 31 * result + (addres != null ? addres.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ElectronicInbox{" +
                ", name='" + name + '\'' +
                ", regon='" + regon + '\'' +
                ", addres='" + addres + '\'' +
                ", zip='" + zip + '\'' +
                ", place='" + place + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
