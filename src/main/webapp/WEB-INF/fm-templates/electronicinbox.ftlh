<!doctype html>
<html lang="pl">
<#setting number_format="##0">

<head>
    <!-- Required meta tags -->
    <meta content-Type="text/html" ; charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <style>
        .graypage {
            -webkit-filter: grayscale(100%);
            -moz-filter: grayscale(100%);
            -o-filter: grayscale(100%);
            -ms-filter: grayscale(100%);
            filter: grayscale(100%);
        }
    </style>

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
            <a href="/electronicinbox" style="color: rgb(000, 000, 000); text-decoration: none; display: block;"
               ;>
            Elektroniczne Skrzynki Podawcze
            </a>
        </span>
    </nav>

    <div class="container-fluid nopadding" style="padding-left: 0; padding-right: 0;">

        <nav class="navbar navbar-light bg-light">

            <form action="/electronicinbox/eib?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}</#if>&strona=<#if choicePage_??>${choicePage_}</#if>"
                  method="get" style="width: 100%;">

                <div class="form-group row" style="margin-bottom: 0;">

                    <div class="input-group col-lg-4">
                    <input class="form-control"
                           style="margin-bottom: 0;"
                           id="inputName"
                           name="nazwa"
                           type="search"
                           placeholder="Nazwa instytucji (fragment)"
                           aria-label="Search"
                           aria-describedby="btnName"
                            value="<#if choiceName_??>${choiceName_}<#else >""</#if>"
                        >
                        <div class="input-group-append" style="display: inline-block;">
                            <button class="btn btn-outline-secondary"
                                    style="margin-bottom: 0;"
                                    id="btnName"
                                    type="submit">
                                O
                            </button>
                        </div>

                    </div>

                    <div class="input-group col-lg-3">
                    <input class="form-group form-control"
                           style="margin-bottom: 0;"
                           id="inputAddress"
                           name="adres"
                           type="search"
                           placeholder="ulica (fragment)"
                           aria-label="Search"
                            value="<#if choiceAddress_??>${choiceAddress_}<#else >""</#if>"
                        >
                        <div class="input-group-append" style="display: inline-block;">
                            <button class="btn btn-outline-secondary"
                                    style="margin-bottom: 0;"
                                    id="btnAddress"
                                    type="submit">
                                O
                            </button>
                        </div>

                    </div>

                    <div class="input-group col-lg-3">
                    <input class="form-group form-control"
                           style="margin-bottom: 0;"
                           id="inputPlace"
                           name="miejscowosc"
                           type="search"
                           placeholder="miejscowość (fragment)"
                           aria-label="Search"
                            value="<#if choicePlace_??>${choicePlace_}<#else >""</#if>"
                        >
                        <div class="input-group-append" style="display: inline-block;">
                            <button class="btn btn-outline-secondary"
                                    style="margin-bottom: 0;"
                                    id="btnPlace"
                                    type="submit">
                                O
                            </button>
                        </div>

                    </div>

                    <div class="col-lg-2 form-group">
                        <button class="btn btn-outline-success btn-block"
                                style="margin-bottom: 0;"
                                id="btnSearch"
                                type="submit">
                            Znajdź
                        </button>
                    </div>
                </div>

<#if choiceTotalPages_??>

                <input
                        id="inputPage"
                        name="strona"
                        type="hidden"
                        value=<#if choicePage_??>${choicePage_}<#else >1</#if>
                >

                <div class="form-group row" style="margin-bottom: 0;">

                    <div class="col-lg-3">
                        <button class="btn btn-outline-success btn-block"
                                id="btnFirstPage"
                                style="margin-bottom: 0;"
                                type="submit">
                            ||<< 1 <<
                        </button>
                    </div>

                    <div class="col-lg-3">
                        <button class="btn btn-outline-success btn-block"
                                id="btnPrevPage"
                                style="margin-bottom: 0;"
                                type="submit">
                            <<  <#if choicePrevPage_??>${choicePrevPage_}<#else >1</#if> <<
                        </button>
                    </div>

                    <div class="col-lg-3">
                        <button class="btn btn-outline-success btn-block"
                                id="btnNextPage"
                                style="margin-bottom: 0;"
                                type="submit">
                            >>  <#if choiceNextPage_??>${choiceNextPage_}<#else >1</#if> >>
                        </button>
                    </div>

                    <div class="col-lg-3">
                        <button class="btn btn-outline-success btn-block"
                                id="btnLastPage"
                                style="margin-bottom: 0;"
                                type="submit">
                            >>  <#if choiceTotalPages_??>${choiceTotalPages_}<#else >1</#if> >>||
                        </button>
                    </div>
                </div>
<#else >
                <input
                        id="inputPage"
                        name="strona"
                        type="hidden"
                        value=1
                >
</#if>

            </form>

        </nav>
    </div>
</div>

<#if database??>
    <#list database as d>

        <div class="container-fluid">
            <div class="row">
                <div class="col-lg">
                    <div class="card">
                        <div class="card-body mr-sm-3">
                            <h6 class="card-title"><#if d.name??>${d.name}</#if></h6>
                            <h6 class="card-subtitle mb-2 text-muted">
                                <#if d.regon??>Regon: ${d.regon},</#if>
                                <#if d.address??>adres: ${d.address},</#if>
                                <#if d.zip??>${d.zip},</#if>
                                <#if d.place??>${d.place}</#if></h6>
                            <h6 class="card-text">
                                <#if d.uri??>${d.uri}</#if>
                            </h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </#list>
</#if>

<#if database??>

    <div class="container-fluid">

        <#if database?size < 5>
            <div class="row">
                <div class="col-lg">
                    <div class="card">
                        <div class="card-body mr-sm-3">
                            <h6 class="card-title"> &nbsp;</h6>
                            <h6 class="card-subtitle mb-2 text-muted">
                                &nbsp;
                            </h6>
                            <h6 class="card-text">
                                &nbsp;
                            </h6>
                        </div>
                    </div>
                </div>
            </div>
        </#if>

        <#if database?size < 4>
            <div class="row">
                <div class="col-lg">
                    <div class="card">
                        <div class="card-body mr-sm-3">
                            <h6 class="card-title"> &nbsp;</h6>
                            <h6 class="card-subtitle mb-2 text-muted">
                                &nbsp;
                            </h6>
                            <h6 class="card-text">
                                &nbsp;
                            </h6>
                        </div>
                    </div>
                </div>
            </div>
        </#if>

        <#if database?size < 3>
            <div class="row">
                <div class="col-lg">
                    <div class="card">
                        <div class="card-body mr-sm-3">
                            <h6 class="card-title"> &nbsp;</h6>
                            <h6 class="card-subtitle mb-2 text-muted">
                                &nbsp;
                            </h6>
                            <h6 class="card-text">
                                &nbsp;
                            </h6>
                        </div>
                    </div>
                </div>
            </div>
        </#if>

        <#if database?size < 2>
            <div class="row">
                <div class="col-lg">
                    <div class="card">
                        <div class="card-body mr-sm-3">
                            <h6 class="card-title"> &nbsp;</h6>
                            <h6 class="card-subtitle mb-2 text-muted">
                                &nbsp;
                            </h6>
                            <h6 class="card-text">
                                &nbsp;
                            </h6>
                        </div>
                    </div>
                </div>
            </div>
        </#if>

        <#if database?size < 1>
            <div class="row">
                <div class="col-lg">

                    <div class="card">
                        <div class="card-body mr-sm-3">
                            <h6 class="card-title"> &nbsp;</h6>
                            <h6 class="card-subtitle mb-2 text-muted">
                                &nbsp;
                            </h6>
                            <h6 class="card-text">
                                &nbsp;
                            </h6>
                        </div>
                    </div>
                </div>
            </div>
        </#if>
    </div>

<#else >

    <div class="container-fluid">

        <div class="row">
            <div class="col-lg">

                <div class="card">
                    <div class="card-body mr-sm-3">
                        <h6 class="card-title"> &nbsp;</h6>
                        <h6 class="card-subtitle mb-2 text-muted">
                            &nbsp;
                        </h6>
                        <h6 class="card-text">
                            &nbsp;
                        </h6>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg">

                <div class="card">
                    <div class="card-body mr-sm-3">
                        <h6 class="card-title"> &nbsp;</h6>
                        <h6 class="card-subtitle mb-2 text-muted">
                            &nbsp;
                        </h6>
                        <h6 class="card-text">
                            &nbsp;
                        </h6>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg">

                <div class="card">
                    <div class="card-body mr-sm-3">
                        <h6 class="card-title"> &nbsp;</h6>
                        <h6 class="card-subtitle mb-2 text-muted">
                            &nbsp;
                        </h6>
                        <h6 class="card-text">
                            &nbsp;
                        </h6>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg">

                <div class="card">
                    <div class="card-body mr-sm-3">
                        <h6 class="card-title"> &nbsp;</h6>
                        <h6 class="card-subtitle mb-2 text-muted">
                            &nbsp;
                        </h6>
                        <h6 class="card-text">
                            &nbsp;
                        </h6>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg">

                <div class="card">
                    <div class="card-body mr-sm-3">
                        <h6 class="card-title"> &nbsp;</h6>
                        <h6 class="card-subtitle mb-2 text-muted">
                            &nbsp;
                        </h6>
                        <h6 class="card-text">
                            &nbsp;
                        </h6>
                    </div>
                </div>
            </div>
        </div>

    </div>

</#if>

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


<script>
    $('#btnName').on('click', function () {
        document.getElementById('inputName').value = "";
        document.getElementById('inputPage').value = 1;
        window.location = '/electronicinbox?nazwa=&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}</#if>&strona=1';
    });
</script>

<script>
    $('#btnAddress').on('click', function () {
        document.getElementById('inputAddress').value = "";
        document.getElementById('inputPage').value = 1;
        window.location = '/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=&miejscowosc=<#if choicePlace_??>${choicePlace_}</#if>&strona=1';
    });
</script>

<script>
    $('#btnPlace').on('click', function () {
        document.getElementById('inputPlace').value = "";
        document.getElementById('inputPage').value = 1;
        window.location = '/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=&strona=1';
    });
</script>


<script>
    $('#btnSearch').on('click', function () {
        document.getElementById('inputPage').value = 1;
        window.location = '/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}<#else >1</#if>&strona=1';
    });
</script>

<script>
    $('#btnFirstPage').on('click', function () {
        document.getElementById('inputPage').value = 1;
        window.location = '/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}<#else >1</#if>&strona=1';
    });
</script>

<script>
    $('#btnPrevPage').on('click', function () {
        document.getElementById('inputPage').value=<#if choicePrevPage_??>${choicePrevPage_}<#else >1</#if>;
        window.location = '/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}</#if>&strona=<#if choicePrevPage_??>${choicePrevPage_}<#else >1</#if>';
    });
</script>

<script>
    $('#btnNextPage').on('click', function () {
        document.getElementById('inputPage').value=<#if choiceNextPage_??>${choiceNextPage_}<#else >1</#if>;
        window.location = '/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}</#if>&strona=<#if choiceNextPage_??>${choiceNextPage_}<#else >1</#if>';
    });
</script>

<script>
    $('#btnLastPage').on('click', function () {
        document.getElementById('inputPage').value=<#if choiceTotalPages_??>${choiceTotalPages_}<#else >1</#if>;
        window.location = '/electronicinbox?nazwa=<#if choiceName_??>${choiceName_}</#if>&adres=<#if choiceAddress_??>${choiceAddress_}</#if>&miejscowosc=<#if choicePlace_??>${choicePlace_}</#if>&strona=<#if choiceTotalPages_??>${choiceTotalPages_}<#else >1</#if>';
    });
</script>

</body>

</html>
