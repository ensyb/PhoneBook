<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
