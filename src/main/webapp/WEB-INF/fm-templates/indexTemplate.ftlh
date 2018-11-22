<!doctype html>
<html lang="pl">
<#setting number_format="##0">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv = "refresh" content ="0, /electronicinbox?nazwa=&adres=&miejscowosc=&strona=1"
<script type="text/javascript">
windows.location.href = "/electronicinbox?nazwa=&adres=&miejscowosc=&strona=1"
</script>


    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <title>Elektroniczne Skrzynki Podawcze</title>

</head>

<body style="background-color: white">

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
        &nbsp;
    </div>
</div>


<div class="container-fluid" style="background-color: #007bff; padding: 5px; width:95%;">
    <div class="row">
        <div class="col-lg-12">
            <a class="btn btn-primary btn-lg btn-block"
               style="padding: 0;"
               href="https://epuap.gov.pl/wps/portal/strefa-urzednika/pomoc_urzednik/" role="button" target="_new">
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

<!-- Optional JavaScript -->
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


<script>
    $('#btnName').on('click', function () {
        document.getElementById('inputName').value = "";
        document.getElementById('inputPage').value = 1;
        window.location = 'http://localhost:8080/electronicinbox?nazwa=&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}</#if>&strona=1';
    });
</script>

<script>
    $('#btnAddress').on('click', function () {
        document.getElementById('inputAddress').value = "";
        document.getElementById('inputPage').value = 1;
        window.location = 'http://localhost:8080/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=&miejscowosc=<#if choicePlace_??>${choicePlace_}</#if>&strona=1';
    });
</script>

<script>
    $('#btnPlace').on('click', function () {
        document.getElementById('inputPlace').value = "";
        document.getElementById('inputPage').value = 1;
        window.location = 'http://localhost:8080/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=&strona=1';
    });
</script>


<script>
    $('#btnSearch').on('click', function () {
        document.getElementById('inputPage').value = 1;
        window.location = 'http://localhost:8080/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}<#else >1</#if>&strona=1';
    });
</script>

<script>
    $('#btnFirstPage').on('click', function () {
        document.getElementById('inputPage').value = 1;
        window.location = 'http://localhost:8080/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}<#else >1</#if>&strona=1';
    });
</script>

<script>
    $('#btnPrevPage').on('click', function () {
        document.getElementById('inputPage').value=<#if choicePrevPage_??>${choicePrevPage_}<#else >1</#if> ;
        window.location = 'http://localhost:8080/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}</#if>&strona=<#if choicePrevPage_??>${choicePrevPage_}<#else >1</#if>';
    });
</script>

<script>
    $('#btnNextPage').on('click', function () {
        document.getElementById('inputPage').value=<#if choiceNextPage_??>${choiceNextPage_}<#else >1</#if> ;
        window.location = 'http://localhost:8080/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}</#if>&strona=<#if choiceNextPage_??>${choiceNextPage_}<#else >1</#if>';
    });
</script>

<script>
    $('#btnLastPage').on('click', function () {
        document.getElementById('inputPage').value=<#if choiceTotalPages_??>${choiceTotalPages_}<#else >1</#if> ;
        window.location = 'http://localhost:8080/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}</#if>&strona=<#if choiceTotalPages_??>${choiceTotalPages_}<#else >1</#if>';
    });
</script>

</body>

</html>
