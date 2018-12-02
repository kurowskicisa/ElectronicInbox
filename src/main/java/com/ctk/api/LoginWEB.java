package com.ctk.api;

public class LoginWEB {

    private String loginWEB = "<!doctype html>\n" +
            "<html lang=\"pl\">\n" +
            "\n" +
            "<head>\n" +
            "    <!-- Required meta tags -->\n" +
            "    <meta content-Type=\"text/html\" ; charset=\"utf-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">" +
            "\n" +
            "    <script type=\"text/javascript\">\n" +
            "    windows.location.href = \"/electronicinbox?nazwa=&adres=&miejscowosc=&strona=1\"\n" +
            "    </script>\n" +
            "\n" +
            "    <!-- Bootstrap CSS -->\n" +
            "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\"\n" +
            "    integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n" +
            "\n" +
            "\n" +
            "   <script type=\"text/javascript\">\n" +
            "        window.history.forward();\n" +
            "        function noBack() { window.history.forward(); }\n" +
            "    </script>\n" +
            "\n" +
            "    <title>Elektroniczne Skrzynki Podawcze</title>\n" +
            "</head>\n" +
            "\n" +
            "<body onload=\"noBack();\"\n" +
            "      onpageshow=\"if (event.persisted) noBack();\" onunload=\"\"\n" +
            "      style=\"background-color: white\">\n" +
            "\n" +
            "\n" +
            "<div class=\"position-sticky sticky-top\">\n" +
            "    <nav class=\"navbar navbar-light bg-light\">\n" +
            "        <span class=\"navbar-brand mb-3 h1\">Elektroniczne Skrzynki Podawcze</span>\n" +
            "    </nav>\n" +
            "\n" +
            "    <div class=\"container-fluid nopadding\" style=\"padding-left: 0; padding-right: 0;\">\n" +
            "\n" +
            "        <nav class=\"navbar navbar-light bg-light\">\n" +
            "\n" +
            "        </nav>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div class=\"container-fluid\">\n" +
            "    <div class=\"row\">\n" +
            "\n" +
            "        <div class=\"col-lg-8\">\n" +
            "            <div class=\"jumbotron\">\n" +
            "                <h1 class=\"display-4\">Elektroniczne Skrzynki Podawcze</h1>\n" +
            "                <p class=\"lead\">Zapraszamy Państwa do przeglądania elektronicznych skrzynek podawczych</p>\n" +
            "                <hr class=\"my-4\">\n" +
            "    <h2>Udostępniona baza Elektronicznych Skrzynek Podawczych przez</h2>\n" +
            "    <h2>epuap.gov.pl jest możliwa do pobrania w formatach: csv i xml</h2>\n" +
            "                <h3>&nbsp;</h3>\n" +
            "    <h2>Teraz możesz również skorzystać z przeglądania i wyszukiwania</h2>\n" +
            "                <h3>&nbsp;</h3>\n" +
            "                <h3>&nbsp;</h3>\n" +
            "                <h3>&nbsp;</h3>\n" +
            "\n" +
            "                <a class=\"btn btn-primary btn-lg\"\n" +
            "    style=\"width: 99%;\"\n" +
            "    href=\"/electronicinbox?nazwa=&adres=&miejscowosc=&strona=1\"\n" +
            "    role=\"button\">Pzeglądaj\n" +
            "            </a>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"col-lg-4\">\n" +
            "            <form  method=\"post\" action=\"http://localhost:8080/api/statistics\" class=\"form-signin\">\n" +
            "\n" +
            "                <h1 class=\"h3 mb-3 font-weight-normal\"></h1>\n" +
            "                <label for=\"inputUser\" class=\"sr-only\">mail</label>\n" +
            "                <input type=\"text\"\n" +
            "    id=\"inputUser\"\n" +
            "    name=\"user\"\n" +
            "    class=\"form-control\"\n" +
            "    placeholder=\"użytkownik\"\n" +
            "    required autofocus>\n" +
            "\n" +
            "            &nbsp;\n" +
            "\n" +
            "                <label for=\"inputPassword\" class=\"sr-only\">Password</label>\n" +
            "                <input type=\"password\"\n" +
            "    id=\"inputPassword\"\n" +
            "    name=\"password\"\n" +
            "    class=\"form-control\"\n" +
            "    placeholder=\"hasło\"\n" +
            "    required>\n" +
            "\n" +
            "            &nbsp;\n" +
            "\n" +
            "                <button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Zaloguj</button>\n" +
            "\n" +
            "            </form>\n" +
            "\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"container-fluid\">\n" +
            "    <div class=\"row\">\n" +
            "            &nbsp;\n" +
            "    </div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "<div class=\"container-fluid\" style=\"background-color: #007bff; padding: 5px; width:95%;\">\n" +
            "    <div class=\"row\">\n" +
            "        <div class=\"col-lg-12\">\n" +
            "            <a class=\"btn btn-primary btn-lg btn-block\"\n" +
            "    style=\"padding: 0;\"\n" +
            "    href=\"https://epuap.gov.pl/wps/portal/strefa-urzednika/pomoc_urzednik/\" role=\"button\"\n" +
            "    target=\"_new\">\n" +
            "                <button type=\"button\" class=\"btn btn-info col-12\">\n" +
            "    Źródło:\n" +
            "    epuap.gov.pl\n" +
            "            </button>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"row\">\n" +
            "\n" +
            "        <div class=\"col-lg-6\">\n" +
            "            <a class=\"btn btn-primary btn-lg btn-block\"\n" +
            "    style=\"padding: 0;\"\n" +
            "    href=\"https://epuap.gov.pl/LESP/LESP.csv\" role=\"button\" target=\"_new\">\n" +
            "                <button type=\"button\" class=\"btn btn-info col-12\">Pobierz plik w formacie csv\n" +
            "            </button>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"col-lg-6\">\n" +
            "            <a class=\"btn btn-primary btn-lg btn-block\"\n" +
            "    style=\"padding: 0;\"\n" +
            "    href=\"https://epuap.gov.pl/LESP/LESP.xml\" role=\"button\" target=\"_new\">\n" +
            "                <button type=\"button\" class=\"btn btn-info col-12\">Pobierz plik w formacie xml\n" +
            "            </button>\n" +
            "            </a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<!-- Optional Javascript -->\n" +
            "<!-- jQuery first, then Popper.js, then Bootstrap JS -->\n" +
            "<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"\n" +
            "    integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\"\n" +
            "    crossorigin=\"anonymous\">\n" +
            "</script>\n" +
            "\n" +
            "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\"\n" +
            "    integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\"\n" +
            "    crossorigin=\"anonymous\">\n" +
            "</script>\n" +
            "\n" +
            "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\"\n" +
            "    integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\"\n" +
            "    crossorigin=\"anonymous\">\n" +
            "</script>\n" +
            "\n" +
            "\n" +
            "</body>\n" +
            "\n" +
            "</html>";

    public String LoginWEB() {
        return loginWEB;
    }
}
