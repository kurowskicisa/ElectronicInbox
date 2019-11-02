package com.ctk.api;

import com.ctk.dao.GrayScaleReadFile;
import com.ctk.model.GrayScale;

import javax.inject.Inject;

public class ElectronicInBoxRedirect {

    @Inject
    private GrayScale grayScale;

    @Inject
    private GrayScaleReadFile grayScaleReadFile;

    public String ElectronicInBoxRedirect() {
        String electronicInBoxRedirect;

        String grayScale_ = "000";

        grayScaleReadFile.loadGrayScaleFile();

        if (!(grayScale.getGrayScale() == null)) {
            grayScale_ = grayScale.getGrayScale();
        }

        electronicInBoxRedirect=
                "<!doctype html>\n" +
                        "<html lang=\"pl\">\n" +
                        "\n" +
                        "<head>\n" +
                        "    <!-- Required meta tags -->\n" +
                        "    <meta content-Type=\"text/html\" ; charset=\"utf-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">" +
                        "\n" +
                        "    <meta http-equiv = \"refresh\" content =\"0, /electronicinbox/eib\">\n" +
                        "\n" +
                        "    <script type=\"text/javascript\">\n" +
                        "    windows.location.href = \"/electronicinbox/eib\"\n" +
                        "    </script>\n" +
                        "\n" +
                        "    <style>" +
                        "\n" +
                        "      .graypage {" +
                        "\n" +
                        "        -webkit-filter: grayscale(" +
                        grayScale_ +
                        "%);" +
                        "\n" +
                        "        -moz-filter: grayscale(" +
                        grayScale_ +
                        "%);" +
                        "\n" +
                        "        -o-filter: grayscale(" +
                        grayScale_ +
                        "%);" +
                        "\n" +
                        "        -ms-filter: grayscale(" +
                        grayScale_ +
                        "%);" +
                        "\n" +
                        "        filter: grayscale(" +
                        grayScale_ +
                        "%);" +
                        "\n" +
                        "        }" +
                        "\n" +
                        "    </style>" +
                        "\n" +
                        "<div class=\"position-sticky sticky-top\">" +
                        "<nav class=\"navbar navbar-light bg-light\">" +
                        "<span class=\"navbar-brand mb-3 h1\">" +
                        "<a href=\"/electronicinbox/\"" +
                        "style=\"color: rgb(000, 000, 000); text-decoration: none; display: block;\"" +
                        ">" +
                        "Elektroniczne Skrzynki Podawcze" +
                        "</a>" +
                        "</span>"+
                        "</nav>" +
                        "</div>" +
                        "    <!-- Bootstrap CSS -->\n" +
                        "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\"\n" +
                        "          integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n" +
                        "\n" +
                        "   <script type=\"text/javascript\">\n" +
                        "        window.history.forward();\n" +
                        "        function noBack() { window.history.forward(); }\n" +
                        "    </script>\n" +
                        "\n" +
                        "    <title>Elektroniczne Skrzynki Podawcze</title>\n" +
                        "\n" +
                        "</head>" +
                        "</html>";
        return electronicInBoxRedirect;
    }
}
