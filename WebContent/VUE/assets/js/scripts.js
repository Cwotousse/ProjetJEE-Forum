jQuery(document)
		.ready(
				function() {

					/*
					 * Modals
					 */
					$('.launch-modal').on('click', function(e) {
						e.preventDefault();
						$('#' + $(this).data('modal-id')).modal();
					});

					/*
					 * Login form validation
					 */
					$(
							'.login-form input[type="text"], .login-form input[type="password"], .login-form textarea')
							.on('focus', function() {
								$(this).removeClass('input-error');
							});

					$('.login-form')
							.on(
									'submit',
									function(e) {
										$(this)
												.find(
														'input[type="text"], input[type="password"], textarea')
												.each(
														function() {
															if ($(this).val() == "") {
																e
																		.preventDefault();
																$(this)
																		.addClass(
																				'input-error');
															} else {
																$(this)
																		.removeClass(
																				'input-error');
															}
														});

									});
					/*
					 * Registration form validation
					 */
					$(
							'.registration-form input[type="text"], .registration-form input[type="password"]')
							.on('focus', function() {
								$(this).removeClass('input-error');
							});

					$('.registration-form')
							.on(
									'submit',
									function(e) {
										$(this)
												.find(
														'input[type="text"], input[type="password"]')
												.each(
														function() {
															if ($(this).val() == "") {
																e
																		.preventDefault();
																$(this)
																		.addClass(
																				'input-error');
															} else {
																$(this)
																		.removeClass(
																				'input-error');
															}
														});

									});
					/*
					 * Edit form validation
					 */
					$(
							'.edit-form input[type="text"], .edit-form input[type="password"]')
							.on('focus', function() {
								$(this).removeClass('input-error');
							});

					$('.edit-form')
							.on(
									'submit',
									function(e) {
										$(this)
												.find(
														'input[type="text"], input[type="password"]')
												.each(
														function() {
															if ($(this).val() == "") {
																e
																		.preventDefault();
																$(this)
																		.addClass(
																				'input-error');
															} else {
																$(this)
																		.removeClass(
																				'input-error');
															}
														});

									});
					
					// Cache la partie affichage commentaire (useless)
					if ($('#elem-sub-categorie').length >= 1) {
						// Affich les sous catégories
						$("#body-text").hide();
						$('#subject').hide();
						$('#comments').hide();
						$('#sub-categorie').show();
					} else if ($('#elem-sujet').length >= 1) {
						// Affiche les sujets
						$("#body-text").hide();
						$('#sub-categorie').hide();
						$('#comments').hide();
						$('#subject').show();
					} else if ($('#elem-comment').length >= 1) {
						// Affiche les commentaires
						$("#body-text").hide();
						$('#sub-categorie').hide();
						$('#subject').hide();
						$('#comments').show();
					} else {
						// L'accueil est affiché
						$('#sub-categorie').hide();
						$('#subject').hide();
						$('#comments').hide();
						$("#body-text").show();
					}

					// Si l'utilisateur n'est pas connecté, le nom n'apparait
					// pas dans le bouton pour voir son profil
					// Dans ce cas on affiche la navbar pour se connecter
					if ($('#username-form').text().length == 0) {
						// Il est déconnecté
						$('#deconnect-form').hide();
						$("#profil-form").hide();
						$("#connect-form").show();

						// Je cache les boutons modifier et ajouter pour qu'un
						// invité ne puisse pas faire cela
						$("#reply").hide();
						$("#edit").hide();
					} else {
						// Il est connecté
						$('#deconnect-form').show();
						$("#profil-form").show();
						$("#connect-form").hide();

					}

					// Le modal affichange un nouveau commentaire va indiquer au
					// bon endroit le num du sujet + la date
					$("#reply").click(
							function() {
								var dateSansEspaces = $.trim($(
										"#date-creation-sujet").text());
								$("#nom-sujet-hidden").val(
										$("#nom-sujet").text());
								// On retire les espaces useless
								$("#date-sujet-hidden").val(dateSansEspaces);
								$("#nom-sujet-label").text(
										$("#nom-sujet").text());
								$("#date-sujet-label").text(dateSansEspaces);
							});

					// Je cache l'élement de la navbar "Administration" qui est
					// propre à l'admin
					$('#admin-form').hide();

					var pseudo = $('pseudo').text();
					var nom = $('nom').text();
					var prenom = $('prenom').text();
					var email = $('email').text();
					var dateNaissance = $('date-naissance').text();

					// Je récupère le type de la personne connectée
					var type = $('#type').text();
					if (type == 'Admin') {
						// Si c'est un admin, j'affiche l'élément de la navbar
						// "Administration"
						$('#admin-form').show();
					}
				});
$("[data-toggle=tooltip]").tooltip();