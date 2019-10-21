package com.ctk.api;

import com.ctk.dao.GrayScaleReadFile;
import com.ctk.model.GrayScale;

import javax.inject.Inject;

public class StatisticWEB {

    @Inject
    private GrayScale grayScale;

    @Inject
    private GrayScaleReadFile grayScaleReadFile;

    public String StatisticWeb() {
        String statisticWeb;

        String grayScale_ = "000";

        grayScaleReadFile.loadGrayScaleFile();

        if (!(grayScale.getGrayScale() == null)) {
            grayScale_ = grayScale.getGrayScale();
        }

        statisticWeb =
                "<!doctype html>\n" +
                        "<html lang=\"pl\">\n" +
                        "\n" +
                        "<head>\n" +
                        "    <!-- Required meta tags -->\n" +
                        "    <meta content-Type=\"text/html\" ; charset=\"utf-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">" +
                        "\n" +
                        "    <meta http-equiv = \"refresh\" content =\"0, /electronicinbox/statistics\">\n" +
                        "</head>" +
                        "</html>";

        return statisticWeb;
    }

}
