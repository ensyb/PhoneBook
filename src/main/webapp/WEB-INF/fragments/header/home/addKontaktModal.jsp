<div class="modal fade dodajKontakt" tabindex="-1" role="dialog"
	aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<form method="POST" action="addContact.html">
					<div class="form-group">
						<label for="newKonatkIme">Ime</label> <input type="text"
							class="form-control" name="name" id="newKonatkIme"
							aria-describedby="emailHelp" placeholder="novo ime">
					</div>
					<div class="form-group">
						<label for="newKontaktBroj">Broj telefona</label> <input
							type="text" class="form-control" name="phonenumber"
							id="newKontaktBroj" placeholder="novi broj telefona">
					</div>
					<div class="form-group">
						<label for="newKontaktDescription">opis</label>
						<textarea class="form-control" name="description"
							id="newKontaktDescription" placeholder="kratki opis"></textarea>
					</div>


					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
</div>
