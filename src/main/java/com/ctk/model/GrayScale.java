package com.ctk.model;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@RequestScoped
public class GrayScale implements Serializable {
    private String grayScale;

    public GrayScale() {
    }

    public GrayScale(String grayScale) {
        this.grayScale = grayScale;
    }

    public String getGrayScale() {
        return grayScale;
    }

    public void setGrayScale(String grayScale) {
        this.grayScale = grayScale;
    }

}
