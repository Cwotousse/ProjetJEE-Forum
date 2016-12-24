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
				});