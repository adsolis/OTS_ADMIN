<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:t="http://myfaces.apache.org/tomahawk"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>ARMADO REPARTOS ESPECIALES</title>


</head>
<body>
	<f:view>
		<ui:composition template="/Paginas/template.jsp">
			<ui:define name="content">

				<f:loadBundle
					basename="com.datacode.avon_ots_admin.armadoRutasEspeciales.messages"
					var="msg" />

				<h:form id="formNormal">

					<h:outputLabel value="#{msg.titulo}" styleClass="tituloPagina"
						id="LblTituloPantalla"></h:outputLabel>

					<t:fieldset legend="#{msg.tituloFiltros}">
						<h:panelGrid columns="7" id="panelFiltros">
							<h:outputLabel value="#{msg.filtroCampania}" id="lblcampania"
								styleClass="formLabel"></h:outputLabel>
							<h:selectOneMenu value="#{armRE.idCampania}" id="cbCampanias">
								<f:selectItems value="#{armRE.campanias}" />

							</h:selectOneMenu>

							<h:outputLabel value="#{msg.rutaEspecial}" id="lblrutaEspecial"
								styleClass="formLabel"></h:outputLabel>
							<h:inputText value="#{armRE.rutaEspecialB}"></h:inputText>
							<h:inputText style="display: none;" id="valorNR"
								value="#{armRE.valorNR}"></h:inputText>



							<h:commandButton id="btnBuscar" action="#{armRE.buscar}"
								value="Buscar">
							</h:commandButton>
							<a4j:commandButton id="btnNueva" action="#{armRE.nueva}"
								value="Nueva" reRender="formNormal">
							</a4j:commandButton>


							<h:outputLabel value="#{armRE.mensajeError}"
								style="color:#FF0000;"></h:outputLabel>

						</h:panelGrid>
					</t:fieldset>

					<t:fieldset rendered="#{armRE.mostrarPanelNueva}" id="fieldNueva"
						legend="#{msg.tituloNueva}">

						<table width="100%" border="0">
							<tr>
								<td colspan="2"><table width="100%">
										<tr>
											<td width="50%"><h:outputLabel value="#{msg.nombreRuta}"
													id="lblnomRuta" styleClass="formLabel"></h:outputLabel> <h:inputText
													value="#{armRE.rutaespecialN}" readonly="true"></h:inputText></td>
											<td width="50%"><h:outputLabel
													value="#{msg.campaniaMax}" id="lblcampaniaMax"
													styleClass="formLabel"></h:outputLabel> <h:inputText
													readonly="true" value="#{armRE.campaniaMaxima}"></h:inputText></td>
										</tr>

									</table></td>
							</tr>
							<tr>
								<td align="center" valign="top"><t:fieldset
										legend="Registros">
										<table width="100%">
											<tr>
												<td>Registro: <h:inputText id="registroRepInput" value="#{armRE.registroRep}"
														disabled="#{armRE.mostrarPanelNuevaDeshabilitado}"
														onkeypress="return validaEnter(event, 'formNormal:btnConsultarRegistro');"></h:inputText>
													<a4j:commandButton action="#{armRE.validaNR}"
														id="btnConsultarRegistro" style="display: none;"
														oncomplete="verificarMensajeNR();" value="Nueva"
														reRender="formNormal">
													</a4j:commandButton> <a4j:commandButton action="#{armRE.consultarRegistro}"
														id="btnConsultarRegistro2" style="display: none;"
														onclick="Richfaces.showModalPanel('mp')"
														oncomplete="Richfaces.hideModalPanel('mp')" value="Nueva"
														reRender="formNormal">
													</a4j:commandButton></td>
											</tr>
											<tr>
												<td><rich:dataTable value="#{armRE.registros}"
														var="reg" id="tablaRegistros" border="1">
														<f:facet name="header">
															<rich:columnGroup>
																<rich:column>
																	<h:outputText value="Registro" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Nombre" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Clave Orden" />
																</rich:column>
																<rich:column>
																	<h:outputText value="NR" />
																</rich:column>

															</rich:columnGroup>
														</f:facet>
														<rich:column>
															<h:outputText value="#{reg.registro}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:outputText value="#{reg.nombre}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:outputText value="#{reg.claveOrden}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:outputText value="#{reg.blocked}"></h:outputText>
														</rich:column>


													</rich:dataTable></td>
											</tr>
										</table>
									</t:fieldset></td>
								<td valign="top"><t:fieldset legend="Cajas">
										<table>
											<tr>
												<td>Código:<h:inputText value="#{armRE.codigoBarras}"
														disabled="#{armRE.mostrarPanelNuevaDeshabilitado}"
														onkeypress="return validaEnter(event, 'formNormal:btnEscanearCaja');"></h:inputText>
													<a4j:commandButton action="#{armRE.escanearCaja}"
														id="btnEscanearCaja" style="display: none;" value="Nueva"
														reRender="formNormal">
													</a4j:commandButton></td>
											</tr>
											<tr>
												<td><rich:dataTable value="#{armRE.cajas}" var="reg"
														id="tablaCajas" border="1">
														<f:facet name="header">
															<rich:columnGroup>
																<rich:column>
																	<h:outputText value="Registro" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Clave Orden" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Código de Barras" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Descripción" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Escaneada" />
																</rich:column>
															</rich:columnGroup>
														</f:facet>
														<rich:column>
															<h:outputText value="#{reg.registro}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:outputText value="#{reg.claveOrden}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:outputText value="#{reg.codigoBarras}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:outputText value="#{reg.descripcion}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:selectBooleanCheckbox value="#{reg.marcado }"></h:selectBooleanCheckbox>

														</rich:column>

													</rich:dataTable></td>
											</tr>
										</table>
									</t:fieldset></td>
								<td valign="top" rowspan="2"><t:fieldset
										legend="Unitarios / Premios">
										<table>
											<tr>
												<td>Código: <h:inputText
														value="#{armRE.codigoUnitario}"
														disabled="#{armRE.mostrarPanelNuevaDeshabilitado}"
														onkeypress="return validaEnter(event, 'formNormal:btnEscanearUnitario');"></h:inputText>
													<a4j:commandButton action="#{armRE.escanearUnitario}"
														id="btnEscanearUnitario" style="display: none;"
														value="Nueva" reRender="formNormal">
													</a4j:commandButton> <a4j:commandButton action="#{armRE.ingresarManual}"
														id="btnIngresarManual" style="display: none;"
														value="Nueva" reRender="formNormal">
													</a4j:commandButton></td>
											</tr>
											<tr>
												<td><rich:dataTable value="#{armRE.unitarios}"
														var="reg" id="tablaUnitarios" border="1">
														<f:facet name="header">
															<rich:columnGroup>
																<rich:column>
																	<h:outputText value="Código FSC" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Código EAN13" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Descripción" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Facturada" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Capturada" />
																</rich:column>
															</rich:columnGroup>
														</f:facet>
														<rich:column>
															<h:outputText value="#{reg.codigoFSC}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:outputText value="#{reg.codigoEAN13}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:outputText value="#{reg.descripcion}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:inputText size="2" disabled="true"
																value="#{reg.cantidadSolicitada }"></h:inputText>
														</rich:column>
														<rich:column>
															<h:inputText size="3" value="#{reg.cantidadIngresada }"
																disabled="#{armRE.mostrarPanelNuevaDeshabilitado}"
																onkeypress="return validaEnter(event, 'formNormal:btnIngresarManual');"
																onblur="validaEnter2('formNormal:btnIngresarManual');"></h:inputText>
														</rich:column>

													</rich:dataTable></td>
											</tr>
										</table>
									</t:fieldset></td>
								<td colspan="2" align="center" valign="top"><t:fieldset
										legend="Remitos" style="overflow: auto; height: 80px">

										<table id="tablaRemitos" width="100%">

											<tr>

												<td>Registro: <h:inputText value="#{armRE.registroRep}" /></td>

											</tr>

											<tr>

												<td><rich:dataTable value="#{armRE.remitos}"
														var="rem" id="tablaRemitos" border="1">

														<f:facet name="header">

															<rich:columnGroup>

																<rich:column>

																	<h:outputText value="Registro" />

																</rich:column>

																<rich:column>

																	<h:outputText value="Nombre" />

																</rich:column>

																<rich:column>

																	<h:outputText value="Cantidad a Recolectar" />

																</rich:column>

																<rich:column>

																	<h:outputText value="Escaneada" />

																</rich:column>



															</rich:columnGroup>

														</f:facet>

														<rich:column max-height="5">
															<h:outputText value="#{rem.registro}"></h:outputText>
														</rich:column>

														<rich:column>
															<h:outputText value="#{rem.nombre}"></h:outputText>
														</rich:column>

														<rich:column>
															<h:outputText value="#{rem.cantidadRecolectar}"></h:outputText>
														</rich:column>

														<rich:column>
														<input type="checkbox" name="checkbox" value="#{rem.idRemito }"></input>
														</rich:column>





													</rich:dataTable></td>

											</tr>

										</table>

									</t:fieldset></td>
							</tr>
							<tr>
								<td colspan="2"><t:fieldset
										legend="Unitarios / Premios Informativa">
										<table>

											<tr>
												<td><rich:dataTable value="#{armRE.unitarios}"
														var="reg" id="tablaUnitarios2" border="1">
														<f:facet name="header">
															<rich:columnGroup>
																<rich:column>
																	<h:outputText value="Registro" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Clave Orden" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Código FSC" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Código EAN13" />
																</rich:column>
																<rich:column>
																	<h:outputText value="Descripción" />
																</rich:column>

															</rich:columnGroup>
														</f:facet>
														<rich:column>
															<h:outputText value="#{reg.registro}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:outputText value="#{reg.claveOrden}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:outputText value="#{reg.codigoFSC}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:outputText value="#{reg.codigoEAN13}"></h:outputText>
														</rich:column>
														<rich:column>
															<h:outputText value="#{reg.descripcion}"></h:outputText>
														</rich:column>
													</rich:dataTable></td>
											</tr>
										</table>
									</t:fieldset></td>
							</tr>
							<tr>
								<td colspan="3" align="center"><a4j:commandButton
										rendered="#{not armRE.mostrarPanelNuevaDeshabilitado}"
										id="btnGuardar" action="#{armRE.guardarRuta}"
										value="Guardar Ruta" reRender="formNormal">
									</a4j:commandButton> <a4j:commandButton id="btnCancelar"
										action="#{login.destroyBeans }" value="Cancelar"
										reRender="formNormal">
									</a4j:commandButton> <a4j:commandButton
										rendered="#{armRE.mostrarPanelNuevaDeshabilitado}"
										id="btnImprimir" onclick="imprimir()" value="Imprimir">
									</a4j:commandButton></td>
							</tr>

						</table>

					</t:fieldset>

					<t:fieldset rendered="#{armRE.mostrarPanelConsulta}"
						id="fieldConsulta" legend="#{msg.tituloConsulta}">
						<rich:dataTable value="#{armRE.rutas}" var="reg" id="tablaRutas"
							border="1">
							<f:facet name="header">
								<rich:columnGroup>
									<rich:column>
										<h:outputText value="Nombre" />
									</rich:column>
									<rich:column>
										<h:outputText value="Campaña Relacionada" />
									</rich:column>
									<rich:column>
										<h:outputText value="Fecha Creación" />
									</rich:column>
									<rich:column>
										<h:outputText value="# Ordenes" />
									</rich:column>
									<rich:column>
										<h:outputText value="Consultar" />
									</rich:column>
									<rich:column>
										<h:outputText value="Eliminar" />
									</rich:column>

								</rich:columnGroup>
							</f:facet>
							<rich:column>
								<h:outputText value="#{reg.claveOrden}"></h:outputText>
							</rich:column>
							<rich:column>
								<h:outputText value="#{reg.campaniaRelacionada}"></h:outputText>
							</rich:column>
							<rich:column>
								<h:outputText value="#{reg.fechaCreacionS}"></h:outputText>
							</rich:column>
							<rich:column>
								<h:outputText value="#{reg.numOrdenes}"></h:outputText>

							</rich:column>
							<rich:column>

								<a4j:commandButton
									action="#{armRE.consultarRuta(reg.idRutaEspecial)}"
									value="Consultar" reRender="formNormal">
								</a4j:commandButton>
							</rich:column>
							<rich:column>

								<h:commandButton onclick="return confirmaEliminarA()"
									immediate="true"
									action="#{armRE.eliminarRutaEspecial(reg.idRutaEspecial)}"
									value="Eliminar" reRender="formNormal">
								</h:commandButton>
							</rich:column>

							<f:facet name="footer">
								<rich:datascroller id="dsC1" for="tablaRutas"></rich:datascroller>
							</f:facet>
						</rich:dataTable>

					</t:fieldset>

				</h:form>
			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>