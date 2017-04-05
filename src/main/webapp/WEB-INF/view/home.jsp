<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/header/head.jsp"></c:import>
<body class="homeBody">

<c:import url="fragments/header/userNavHeader.jsp"></c:import>


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
                                    <a href="#" class="btn btn-danger" role="button">IzbriÅ¡i</a></p>
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
                                    <a href="#" class="btn btn-danger" role="button">IzbriÅ¡i</a></p>
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
                                    <a href="#" class="btn btn-danger" role="button">IzbriÅ¡i</a></p>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        </div>
	<c:import url="fragments/footer/scripts.jsp"></c:import>
</body>

</html>