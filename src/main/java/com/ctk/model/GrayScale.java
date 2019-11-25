package com.ctk.model;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;

@RequestScoped
public class GrayScale implements com.ctk.services.GrayScale, Serializable {
    private String grayScale;

    @Override
    public String getGrayScale() {
        return grayScale;
    }

    @Override
    public void setGrayScale(String grayScale) {
        this.grayScale = grayScale;
    }

}
