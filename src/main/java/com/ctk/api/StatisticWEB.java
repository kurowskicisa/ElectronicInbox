package com.ctk.api;

public class StatisticWEB {

    private String web = "<!doctype html>\n" +
            "<html lang=\"pl\">\n" +
            "\n" +
            "<head>\n" +
            "    <!-- Required meta tags -->\n" +
            "    <meta content-Type=\"text/html\" ; charset=\"utf-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">" +
            "\n" +
            "    <meta http-equiv = \"refresh\" content =\"0, /statistics\">\n" +
            "\n" +
            "    <script type=\"text/javascript\">\n" +
            "    windows.location.href = \"/statistics\"\n" +
            "    </script>\n" +
            "\n" +
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

    private String webstart = "<!doctype html>\n" +
            "<html lang=\"pl\">\n" +
            "\n" +
            "<head>\n" +
            "    <!-- Required meta tags -->\n" +
            "    <meta content-Type=\"text/html\" ; charset=\"utf-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">" +
            "\n" +
            "    <meta http-equiv = \"refresh\" content =\"0, /\">\n" +
            "\n" +
            "    <script type=\"text/javascript\">\n" +
            "    windows.location.href = \"/\"\n" +
            "    </script>\n" +
            "\n" +
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

    public String getWeb() {
        return web;
    }

    public String getWebstart() {
        return webstart;
    }
}
