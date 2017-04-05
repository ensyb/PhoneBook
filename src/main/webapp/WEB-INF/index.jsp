<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>kucni lubimci</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
        crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
    <link href="includes/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="includes/libs/fontAwsome/css/font-awesome.min.css">

</head>

<body class="indexBody">

    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                    aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
                <a class="navbar-brand" href="#"><span class="fa fa-address-book-o"></span> Imenik</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Pocetna <span class="sr-only">(current)</span></a></li>
                    <li><a href="about.html">O nama</a></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#" data-toggle="modal" data-target=".registracijaModal">Registruj se</a></li>
                    <li><a href="#" data-toggle="modal" data-target=".loginModal"><span class="fa fa-sign-in"></span> Prijavi se</a></li>

                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="inner">
                    <h1>WEB IMENIK</h1>
                    <h2>telefonski imenik na webu, kome to još treba ? </h2>
                    <hr>
                    <button class="btn btn-default btn-lg">istraži</button>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="modal fade loginModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                    <div class="modal-dialog modal-md" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <form class="">
                                    <div class="form-group">
                                        <label for="email">vaša email adresa</label>
                                        <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="ovdje ide vaÅ¡a email adresa">
                                        <small id="emailHelp" class="form-text text-muted">vaša email adrese neće biti dijeljena </small>
                                    </div>
                                    <div class="form-group">
                                        <label for="lozinka">vaša lozinka</label>
                                        <input type="password" class="form-control" id="lozinka" placeholder="ovdje ide lozinka">
                                    </div>


                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </form>
                            </div>
                            <!--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="modal fade registracijaModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                    <div class="modal-dialog modal-md" role="document">
                        <div class="modal-content">
                            <div class="modal-header" id="modalRegisterHeader">
                                <h3 class="text-center" id="register-main-message">Registruj se</h3>
                            </div>
                            <div class="modal-body">
                                <form method="POST" id="register-form">
                                <div id="error">  </div>
                                    <div class="form-group">
                                        <label for="email">vaša email adresa</label>
                                        <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email" placeholder="ovdje ide vaša email adresa">
                                        <small id="emailHelp" class="form-text text-muted">vaša email adrese neće biti dijeljena </small>
                                    </div>
                                    <div class="form-group">
                                        <label for="lozinka">vaša lozinka</label>
                                        <input type="password" class="form-control" id="lozinka" name="password" placeholder="ovdje ide lozinka">
                                    </div>

                                    <span id="loading"></span>
                                    <button type="submit" id="registerDugme" class="btn btn-primary">Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>







    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
    <script src="includes/js/register.js"></script>
    <script src="includes/js/app.js"></script>
</body>

</html>