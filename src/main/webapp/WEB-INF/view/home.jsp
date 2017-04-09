<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="../fragments/header/head.jsp"%>
<body class="homeBody">

	<%@ include file="../fragments/header/home/userNavHeader.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="row">


					<div class="col-lg-12 text-center">
						<form class="form-inline" method="get" action="search.html">
							<label for="inlineFormInput">Kontakt : </label> <input
								type="text" name="searchString"
								class="form-control mb-2 mr-sm-2 mb-sm-0" id="inlineFormInput"
								placeholder="fujo..">
							<button type="submit" class="btn btn-primary">Trazi</button>
						<a class="btn btn-primary" href="showAll.html" >prikaži sve</a>
						</form>
					</div>
					<div class="col-lg-12">
						<hr>
					</div>

					<c:if test="${searchList == null}">
						<h3 class="text-center">pretraži</h3>
					</c:if>
					<c:if test="${searchList.isEmpty()}">
						<h3 class="text-center">nema rezultata</h3>
					</c:if>

					<%@ include file="../fragments/header/home/addKontaktModal.jsp"%>

					<c:if test="${!searchList.isEmpty()}">
						<c:forEach items="${searchList}" var="item">
							<div class="col-sm-6 col-md-4">
								<div class="thumbnail">
									<img src="http://placehold.it/240x240" alt="slika">
									<div class="caption">
										<h3>${item.name()}</h3>
										<h3>${item.phoneNumber()}</h3>
										<p>${item.description()}</p>
										<form class="form-inline" method="POST" action="delete.html">
											<a href="#" class="btn btn-info" data-toggle="modal"
												data-target=".editButton">Uredi</a> <input type="hidden"
												name="id" value="${item.id()}"> 
												<button type="submit" class="btn btn-danger">Izbriš</button>
										</form>
									</div>
								</div>

								<div class="modal fade editButton" tabindex="-1" role="dialog"
									aria-labelledby="mySmallModalLabel">
									<div class="modal-dialog modal-sm" role="document">
										<div class="modal-content">
											<div class="modal-body">
												<form method="POST" action="update.html">
													<div class="form-group">
														<label for="editKontaktIme">Ime </label> <input
															type="text" class="form-control" name="name"
															id="editKontaktIme" aria-describedby="emailHelp"
															value="${item.name()}">
													</div>
													<div class="form-group">
														<label for="editKontaktPhonenumber">Broj telefona</label>
														<input type="text" class="form-control" name="phonenumber"
															id="editKontaktPhonenumber" value="${item.phoneNumber()}">
													</div>
													<div class="form-group">
														<label for="editKontaktDescription">opis</label>
														<textarea class="form-control" name="description"
															name="description" id="editKontaktDescription">${item.description()}</textarea>
													</div>
													<input type="hidden" name="id" value="${item.id()}">
													<button type="submit" class="btn btn-primary">Submit</button>
												</form>
											</div>
										</div>
									</div>
								</div>

							</div>

						</c:forEach>
					</c:if>



				</div>
			</div>
		</div>
	</div>
	<%@ include file="../fragments/footer/scripts.jsp"%>
</body>

</html>