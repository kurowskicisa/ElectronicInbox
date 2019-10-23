<!doctype html>
<html lang="pl">
<#setting number_format="##0">

<head>
    <!-- Required meta tags -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content-Type="text/html" ; charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
      class ="graypage"
      style="background-color: white">

<div class="position-sticky sticky-top">
    <nav class="navbar navbar-light bg-light">
        <span class="navbar-brand mb-3 h1">
            <a href="/electronicinbox/" style="color: rgb(000, 000, 000); text-decoration: none; display: block;"
               ;>
            Elektroniczne Skrzynki Podawcze
            </a>
        </span>
    </nav>


</div>
</div>

<div class="container-fluid nopadding" style="padding-left: 20px; padding-right: 20px;">
    <div class="row">
        <div class="col-lg">
            <div class="card text-white bg-primary text-center mb-2" style="max-width: 18rem;">
                <div class="card-header">nazwa</div>
                <div class="card-body">
                    <p class="card-text">maksymalna długość:</p>
                    <h2 class="card-text">${nameLengthMax_}</h2>

                    <p class="card-text">minimalna długość: </p>
                    <h4 class="card-text">${nameLengthMin_}</h4>

                    <p class="card-text">pusta nazwa:</p>
                    <h5 class="card-text">${nameCounterEmpty_}</h5>

                </div>
            </div>
        </div>

        <div class="col-lg">
            <div class="card text-white bg-primary text-center mb-2" style="max-width: 18rem;">
                <div class="card-header">Regon</div>
                <div class="card-body">
                    <p class="card-text">maksymalna długość:</p>
                    <h2 class="card-text">${regonLengthMax_}</h2>

                    <p class="card-text">minimalna długość: </p>
                    <h4 class="card-text">${regonLengthMin_}</h4>

                    <p class="card-text">pusty Regon:</p>
                    <h5 class="card-text">${regonCounterEmpty_}</h5>

                </div>
            </div>
        </div>

        <div class="col-lg">
            <div class="card text-white bg-primary text-center mb-2" style="max-width: 18rem;">
                <div class="card-header">adres</div>
                <div class="card-body">
                    <p class="card-text">maksymalna długość:</p>
                    <h2 class="card-text">${addressLengthMax_}</h2>

                    <p class="card-text">minimalna długość:</p>
                    <h4 class="card-text">${addressLengthMin_}</h4>

                    <p class="card-text">pusty adres:</p>
                    <h5 class="card-text">${addressCounterEmpty_}</h5>

                </div>
            </div>
        </div>

        <div class="col-lg">
            <div class="card text-white bg-primary text-center mb-2" style="max-width: 18rem;">
                <div class="card-header">kod pocztowy</div>
                <div class="card-body">
                    <p class="card-text">maksymalna długość:</p>
                    <h2 class="card-text">${zipLengthMax_}</h2>

                    <p class="card-text">minimalna długość:</p>
                    <h4 class="card-text">${zipLengthMin_}</h4>

                    <p class="card-text">pusty kod pocztowy:</p>
                    <h5 class="card-text">${zipCounterEmpty_}</h5>

                </div>
            </div>
        </div>


        <div class="col-lg">
            <div class="card text-white bg-primary text-center mb-2" style="max-width: 18rem;">
                <div class="card-header">miejscowość</div>
                <div class="card-body">
                    <p class="card-text">maksymalna długość:</p>
                    <h2 class="card-text">${placeLengthMax_}</h2>

                    <p class="card-text">minimalna długość: </p>
                    <h4 class="card-text">${placeLengthMin_}</h4>

                    <p class="card-text">pusta miejscowość:</p>
                    <h5 class="card-text">${placeCounterEmpty_}</h5>

                </div>
            </div>
        </div>

        <div class="col-lg">
            <div class="card text-white bg-primary text-center mb-2" style="max-width: 18rem;">
                <div class="card-header">adres ESP</div>
                <div class="card-body">
                    <p class="card-text">maksymalna długość:</p>
                    <h2 class="card-text">${uriLengthMax_}</h2>

                    <p class="card-text">minimalna długość: </p>
                    <h4 class="card-text">${uriLengthMin_}</h4>

                    <p class="card-text">pusty adres ESP:</p>
                    <h5 class="card-text">${uriCounterEmpty_}</h5>

                </div>
            </div>
        </div>

    </div>
</div>


<div class="container-fluid nopadding" style="padding-left: 20px; padding-right: 20px;">
    <div class="row">
        <div class="col-lg-12">
            <div class="card text-white bg-success text-center mb-12" style="max-width: 100%;">
                <div class="card-header">Ilość rekordów</div>
                <div class="card-body">
                    <h1 class="card-text">${totalRecords_}</h1>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid nopadding" style="padding-left: 20px; padding-right: 20px;">
    &nbsp;
</div>

<div class="container-fluid nopadding" style="padding-left: 20px; padding-right: 20px;">

    <div class="row">
        <div class="col-lg-3">
            <div class="card text-white bg-danger text-center mb-2" style="max-width: 100%;">
                <div class="card-header">Regon, brak danych:</div>
                <div class="card-body">
                    <h1 class="card-text">${dataEmptyRegonCounter_}</h1>
                </div>
            </div>
        </div>


        <div class="col-lg-3">
            <div class="card text-white bg-danger text-center mb-2" style="max-width: 100%;">
                <div class="card-header">Regon, będy:</div>
                <div class="card-body">
                    <h1 class="card-text">${dataErrorRegonCounter_}</h1>
                </div>
            </div>
        </div>

        <div class="col-lg-3">
            <div class="card text-white bg-danger text-center mb-2" style="max-width: 100%;">
                <div class="card-header">kod pocztowy, brak danych:</div>
                <div class="card-body">
                    <h1 class="card-text">${dataEmptyZipCounter_}</h1>
                </div>
            </div>
        </div>

        <div class="col-lg-3">
            <div class="card text-white bg-danger text-center mb-2" style="max-width: 100%;">
                <div class="card-header">kod pocztowy, będy:</div>
                <div class="card-body">
                    <h1 class="card-text">${dataErrorZipCounter_}</h1>
                </div>
            </div>
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
