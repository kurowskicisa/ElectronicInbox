package com.ctk.model;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.Objects;

@RequestScoped
public class ElectronicInbox implements com.ctk.services.ElectronicInbox, Serializable {

    private String name;
    private String regon;
    private String address;
    private String zip;
    private String place;
    private String uri;

    public ElectronicInbox() {
    }

    public ElectronicInbox(String name, String regon, String address, String zip, String place, String uri) {
        this.name = name;
        this.regon = regon;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(regon, that.regon)) return false;
        if (!Objects.equals(address, that.address)) return false;
        if (!Objects.equals(zip, that.zip)) return false;
        if (!Objects.equals(place, that.place)) return false;
        return Objects.equals(uri, that.uri);
    }

    @Override
    public int hashCode() {
        int result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (regon != null ? regon.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ElectronicInbox{" + '\n' +
                "name='" + name + '\'' +
                ", regon='" + regon + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", place='" + place + '\'' +
                ", uri='" + uri + '\'' + '\n' +
                '}';
    }
}
