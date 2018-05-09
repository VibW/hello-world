define(
		[ "dojo/_base/declare", "dojo/_base/lang", "ecm/model/Repository",
				"ecm/model/Action", "ecm/model/Desktop", "ecm/model/Request",
				"ecm/model/ResultSet", "dijit/Dialog", "dojo/_base/array",
				"dojo/domReady!"

		],
		function(declare, lang, Repository, Action, Desktop, Request,
				ResultSet, Dialog, array) {
			return declare(
					"fetchHighestSecurityCategoryDojo.FetchHighest",
					[ Action ],
					{
						/**
						 * This action performs task view
						 */
						performAction : function(repository, itemList,
								callback, teamspace, resultSet, parameterMap) {
							console.log("inside fetch highest action");
							//var folderId =itemList[0].id;

							console.log("items---------" + itemList[0]);

							var aggregationId = itemList[0].id.split(",")[2];
							var rmType = itemList[0].attributes.RMEntityType;
							console.log("-------------Record Id-------------"
									+ aggregationId);

							console
									.log("-------------RM Entity Type-------------"
											+ rmType);

							var requestParams = {};

							requestParams.requestParams = {
								aggregationId : aggregationId,
								rmType : rmType
							};
							requestParams.requestCompleteCallback = lang
									.hitch(
											this,
											function(temp) {
												console
														.log("---------------Inside callback-------------------");

												var SecurityCategoryDialog = new Dialog(
														{
															title : "Highesht Security Category",
															content : "<div style = "
																	+ "width:auto;height:auto;"
																	+ ">"
																	+ scArrayObject.scArrayObject
																			.join("</br>")
																	+ "</div>",
															style : "width: auto;height:auto"
														});
												var actionBar = dojo
														.create(
																"div",
																{
																	"class" : "dijitDialogPaneContentArea"
																},
																SecurityCategoryDialog.containerNode);
												new dijit.form.Button(
														{
															"label" : "Close",

															"onClick" : function() {
																console
																		.log("on click CLose..");

																SecurityCategoryDialog
																		.hide();
																SecurityCategoryDialog
																		.destroyRecursive();
															},
															"style" : "float:right;margin-top:10px"
														}).placeAt(actionBar);
												console.log("after service");
												console
														.log("-------------Array is--------------"
																+ temp.temp
																		.join("</br>"));
												SecurityCategoryDialog.show();

											});
							requestParams.requestFailedCallback = function(
									response) {
								console
										.log("---------------Failed callback-------------------");

							};

							Request.invokePluginService(
									"FetchHighestSecurityCategory",
									"FetchHighestSCService", requestParams);

						},

					});
		});