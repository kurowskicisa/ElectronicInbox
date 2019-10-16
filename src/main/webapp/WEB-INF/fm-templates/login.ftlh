<!doctype html>
<html lang="pl">
<#setting number_format="##0">

<head>
    <!-- Required meta tags -->
    <meta content-Type="text/html" ; charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <script type="text/javascript">
        windows.location.href = "/electronicinbox?nazwa=&adres=&miejscowosc=&strona=1"
    </script>

    <#include "include/grayscale.ftlh">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <script type="text/javascript">
        window.history.forward();

        function noBack() {
            window.history.forward();
        }
    </script>

    <title>Elektroniczne Skrzynki Podawcze</title>

</head>

<body onload="noBack();"
      onpageshow="if (event.persisted) noBack();" onunload=""
      style="background-color: white"
      class ="graypage">

<div class="position-sticky sticky-top">
    <nav class="navbar navbar-light bg-light">
        <span class="navbar-brand mb-3 h1">Elektroniczne Skrzynki Podawcze</span>
    </nav>

    <div class="container-fluid nopadding" style="padding-left: 0; padding-right: 0;">

        <nav class="navbar navbar-light bg-light">

        </nav>
    </div>
</div>
<div class="container-fluid">
    <div class="row">

        <div class="col-lg-8">
            <div class="jumbotron">
                <h1 class="display-4">Elektroniczne Skrzynki Podawcze</h1>
                <p class="lead">Zapraszamy Państwa do przeglądania elektronicznych skrzynek podawczych</p>
                <hr class="my-4">
                <h2>Udostępniona baza Elektronicznych Skrzynek Podawczych przez</h2>
                <h2>epuap.gov.pl jest możliwa do pobrania w formatach: csv i xml</h2>
                <h3>&nbsp;</h3>
                <h2>Teraz możesz również skorzystać z przeglądania i wyszukiwania</h2>
                <h3>&nbsp;</h3>
                <h3>&nbsp;</h3>
                <h3>&nbsp;</h3>

                <a class="btn btn-primary btn-lg"
                   style="width: 99%;"
                   href="/electronicinbox/eib?nazwa=&adres=&miejscowosc=&strona=1"
                   role="button">Przeglądaj i szukaj
                </a>
            </div>
        </div>

        <div class="col-lg-4">
            <form method="post" action="/electronicinbox/api/statistics" class="form-signin">

                <h1 class="h3 mb-3 font-weight-normal"></h1>
                <label for="inputUser" class="sr-only">mail</label>
                <input type="text"
                       id="inputUser"
                       name="user"
                       class="form-control"
                       placeholder="użytkownik"
                       required autofocus>
                &nbsp;
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password"
                       id="inputPassword"
                       name="password"
                       class="form-control"
                       placeholder="hasło"
                       required>
                &nbsp;
                <button class="btn btn-lg btn-primary btn-block" type="submit">Zaloguj</button>

            </form>

            <div class=\"container-fluid\">
                <div class=\"row\">
                    &nbsp;
                </div>
            </div>

            <div class="row">
                <div class="col-lg">
                    <div class="card text-white bg-success text-center">
                        <div class="card-body mr-sm-3">
                            <h6 class="card-title">liczba rekordów</h6>
                            <h2 class="card-text">
                                ${dataBaseRecordsCounter_}
                            </h2>
                        </div>
                    </div>

                    <div class="container-fluid">
                        <div class="row">
                            &nbsp;
                        </div>
                    </div>

                    <div class="card text-white bg-primary text-center">
                        <div class="card-body mr-sm-3">
                             <h6 class="card-title">ostatnia aktualizacja bazy</h6>
                             <h2 class="card-text">
                                 ${dataBaseDateUpdate_}
                             </h2>
                         </div>
                     </div>

                    <div class="container-fluid">
                        <div class="row">
                            &nbsp;
                        </div>
                    </div>

                </div>
            </div>

        </div>
    </div>


    <div class="container-fluid" style="background-color: #007bff; padding: 5px; width:95%;">
        <div class="row">
            <div class="col-lg-12">
                <a class="btn btn-primary btn-lg btn-block"
                   style="padding: 0;"
                   href="https://epuap.gov.pl/wps/portal/strefa-urzednika/pomoc_urzednik/"
                   role="button"
                   target="_new">
                    <button type="button" class="btn btn-info col-12">
                        Źródło:
                        epuap.gov.pl
                    </button>
                </a>
            </div>
        </div>

        <div class="row">

            <div class="col-lg-6">
                <a class="btn btn-primary btn-lg btn-block"
                   style="padding: 0;"
                   href="https://epuap.gov.pl/LESP/LESP.csv" role="button" target="_new">
                    <button type="button" class="btn btn-info col-12">Pobierz plik w formacie csv
                    </button>
                </a>
            </div>

            <div class="col-lg-6">
                <a class="btn btn-primary btn-lg btn-block"
                   style="padding: 0;"
                   href="https://epuap.gov.pl/LESP/LESP.xml" role="button" target="_new">
                    <button type="button" class="btn btn-info col-12">Pobierz plik w formacie xml
                    </button>
                </a>
            </div>
        </div>

    </div>

    <!-- Optional Javascript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous">
    </script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous">
    </script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous">
    </script>

</body>

</html>
