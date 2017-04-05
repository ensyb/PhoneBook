<!DOCTYPE html>
<html lang="en">

<head>
    <title>kucni lubimci</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
        crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="libs/fontAwsome/css/font-awesome.min.css">
</head>

<body class="homeBody">

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
                <div class="row">

                    <div class="col-lg-12 text-center">
                        <form class="form-inline">
                            <label for="inlineFormInput">Kontakt : </label>
                            <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="inlineFormInput" placeholder="fujo..">

            

                            <button type="submit" class="btn btn-primary">Trazi</button>
                        </form>
                    </div>
                    <div class="col-lg-12">
                        <hr>
                    </div>

                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img src="http://placehold.it/240x240" alt="slika">
                            <div class="caption">
                                <h3>Ime i Prezime</h3>
                                <h3>012-222-222</h3>
                                <p>Kratki opis ovo ono sit amet, consectetur adipisicing elit. Ab non nisi fugit perferendis
                                    quos, inventore, voluptate, temporibus impedit iste corrupti et sapiente.</p>
                                <p>
                                    <a href="#" class="btn btn-info" data-toggle="modal" data-target=".editButton">Uredi</a>
                                    <a href="#" class="btn btn-danger" role="button">Izbriši</a></p>
                            </div>
                        </div>

                        <div class="modal fade editButton" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                            <div class="modal-dialog modal-sm" role="document">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        <form class="">
                                            <div class="form-group">
                                                <label for="imeprezime">Ime i prezime</label>
                                                <input type="text" class="form-control" id="imeprezime" aria-describedby="emailHelp" placeholder="novo ime i prezime">
                                            </div>
                                            <div class="form-group">
                                                <label for="broj">Broj telefona</label>
                                                <input type="text" class="form-control" id="broj" placeholder="novi broj telefona">
                                            </div>
                                            <div class="form-group">
                                                <label for="opis">opis</label>
                                                <input type="textarea" class="form-control" id="opis" placeholder="kratki opis">
                                            </div>


                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img src="http://placehold.it/240x240" alt="slika">
                            <div class="caption">
                                <h3>Ime i Prezime</h3>
                                <h3>012-222-222</h3>
                                <p>Kratki opis ovo ono sit amet, consectetur adipisicing elit. Ab non nisi fugit perferendis
                                    quos, inventore, voluptate, temporibus impedit iste corrupti et sapiente.</p>
                                <p>
                                    <a href="#" class="btn btn-info" role="button">Uredi</a>
                                    <a href="#" class="btn btn-danger" role="button">Izbriši</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img src="http://placehold.it/240x240" alt="slika">
                            <div class="caption">
                                <h3>Ime i Prezime</h3>
                                <h3>012-222-222</h3>
                                <p>Kratki opis ovo ono sit amet, consectetur adipisicing elit. Ab non nisi fugit perferendis
                                    quos, inventore, voluptate, temporibus impedit iste corrupti et sapiente.</p>
                                <p>
                                    <a href="#" class="btn btn-info" role="button">Uredi</a>
                                    <a href="#" class="btn btn-danger" role="button">Izbriši</a></p>
                            </div>
                        </div>
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
                                            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="ovdje ide vaša email adresa">
                                            <small id="emailHelp" class="form-text text-muted">vaša email adrese neće biti dijeljena </small>
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
                                <div class="modal-header">
                                    <h3 class="text-center">Registruj se</h3>
                                </div>
                                <div class="modal-body">
                                    <form class="">
                                        <div class="form-group">
                                            <label for="email">vaša email adresa</label>
                                            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="ovdje ide vaša email adresa">
                                            <small id="emailHelp" class="form-text text-muted">vaša email adrese neće biti dijeljena </small>
                                        </div>
                                        <div class="form-group">
                                            <label for="lozinka">vaša lozinka</label>
                                            <input type="password" class="form-control" id="lozinka" placeholder="ovdje ide lozinka">
                                        </div>


                                        <button type="submit" class="btn btn-primary">Submit</button>
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
</body>

</html>