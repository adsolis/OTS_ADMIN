<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:rich="http://richfaces.org/rich"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:t="http://myfaces.apache.org/tomahawk">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Recepción de devolución de ajustes (Remitos)</title>
</head>
<body>
	<f:view>
		<ui:composition template="/Paginas/template.jsp">
			<ui:define name="content">
				<a4j:form id="formRemitosVentanilla">
				<f:loadBundle basename="com.datacode.avon_ots_admin.cu002.messagesCU002" var="msg" />
				<h:outputLabel value="#{msg.recoleccionRemitos_titulo}" styleClass="tituloPagina"></h:outputLabel>
				<table width="100%">
					<tr>
						<td colspan="2" align="center">
							<h:outputLabel id="lblError" value="#{remitosVentanilla.mensaje}" styleClass="error"/>
						</td>
					</tr>
					<tr>
						<td valign="top" width="50%">
							<t:fieldset style="color: Black; vertical-align: top" id="pnlIzquierdo">
								<t:fieldset legend="#{msg.recoleccionRemitos_consultaRepresentantes}" id="pnlConsultaRepresentantes"
										style="color: Black; max-height: 100%; max-width: 100%; vertical-align: top">
									<table width="100%" border="0" cellspacing="3" cellpadding="0" >
										<tr>
											<td width="10%" align="left">
												<h:outputLabel id="lblCampania" value="#{msg.recoleccionRemitos_campania}" styleClass="label"/>
											</td>
											<td width="40%" align="left">
												<h:selectOneMenu id="cbCampania" value="#{remitosVentanilla.idCampania}" style="width: 100%;" styleClass="inputText"
														disabled="#{ not remitosVentanilla.habilitarFormularioRepresentantes}">
													<f:selectItems value="#{remitosVentanilla.listaCampanias}" />
												</h:selectOneMenu>
											</td>
											<td width="10%" align="left">
											</td>
											<td width="40%" align="left">
											</td>
										</tr>
										<tr>
											<td align="left">
												<h:outputLabel id="lblZona" value="#{msg.recoleccionRemitos_zona}" styleClass="label"/>
											</td>
											<td align="left">
												<h:selectOneMenu id="cbZona" value="#{remitosVentanilla.idZona}" style="width: 100%;" styleClass="inputText"
														disabled="#{ not remitosVentanilla.habilitarFormularioRepresentantes}">
													<f:selectItems value="#{remitosVentanilla.listaZonas}" />
												</h:selectOneMenu>
											</td>
											<td align="left">
											</td>
											<td align="left">
											</td>
										</tr>
										<tr>
											<td colspan="4" align="center">
												<a4j:commandButton value="#{msg.recoleccionRemitos_consultar}" id="btnConsultarRepresentantes"
													action="#{remitosVentanilla.consultarRepresentantes}"
													reRender="formRemitosVentanilla,pnlIzquierdo,pnlConsultaRepresentantes,fsRepresentantes,pnlDerecho,pnlRemitos,cantidadRepresentantes,pnlRepresentantes,comentariosRecoleccion"
													disabled="#{ not remitosVentanilla.habilitarFormularioRepresentantes}">
												</a4j:commandButton>
											</td>
										</tr>
									</table>
										<t:fieldset legend="#{msg.recoleccionRemitos_representantes}" id="fsRepresentantes"
												style="color: Black; max-height: 100%; max-width: 100%; vertical-align: top">
											<table width="100%" border="0" cellspacing="3" cellpadding="0" >
												<tr>
													<td width="100%">
														<table>
															<tr>
																<td width="100%" align="left" colspan="2">
																	<h:outputLabel value="#{msg.recoleccionRemitos_registro}" styleClass="label"/>
																</td>
															</tr>
															<tr>
																<td width="100%" align="left" colspan="2">
																	<h:inputText id="tfRegistro" styleClass="inputText" maxlength="9" style="width: 100%;"
																			onkeypress="return remitosVentanillaKeyPress(event);"
																			disabled="#{remitosVentanilla.habilitarDetalleRemito}">
																		<a4j:support event="onkeypress"
														    				focus="tfRegistro"
																			reRender="formRemitosVentanilla,pnlIzquierdo,pnlConsultaRepresentantes,fsRepresentantes,pnlDerecho,pnlRemitos,cantidadRepresentantes,pnlRepresentantes,comentariosRecoleccion"
																			ignoreDupResponses="true" requestDelay="1000"/>
																	</h:inputText>
																</td>
															</tr>
															<tr>
																<td width="100%" align="left" colspan="2">
																	<a4j:commandButton onclick="validarAccount();" value="Consultar" disabled="#{ not remitosVentanilla.habilitarControlesRepresentantes}"
																		id="btnConsultarRegistroRem" name="btnConsultarRegistroRem">
																	</a4j:commandButton>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td width="100%">
														<h:inputHidden id="cantidadRepresentantes" value="#{remitosVentanilla.cantidadRepresentantes}"></h:inputHidden>
														<rich:scrollableDataTable id="tblRepresentantes"
																value="#{remitosVentanilla.listaRepresentantes}" var="representante"
																styleClass="gridDatosScrollable" headerClass="headerGridDatosScrollable" footerClass="footerGridDatosScrollable"
																rendered="#{remitosVentanilla.habilitarListaRepresentantes}"
																binding="#{remitosVentanilla.tblRemitos}"
																onRowDblClick="javascript:document.getElementById('formRemitosVentanilla:btnDetalleRemito').click();"
																width="100%" height="250px">
															<rich:column width="70px" sortable="false" styleClass="gridDatoIzquierda">
																<f:facet name="header"><h:outputText value="Registro" styleClass="label"/></f:facet>
																<h:outputText value="#{representante.account}" styleClass="label" id="otAccount" name="otAccount"/>
																<h:inputHidden id="ihAccount" value="#{representante.account}"></h:inputHidden>
															</rich:column>
															<rich:column width="240px" sortable="false" styleClass="gridDatoIzquierda">
																<f:facet name="header"><h:outputText value="Nombre" styleClass="label"/></f:facet>
																<h:outputText value="#{representante.nombre}" styleClass="label"/>
															</rich:column>
															<rich:column width="120px" sortable="false" styleClass="gridDatoIzquierda">
																<f:facet name="header"><h:outputText value="Cantidad a Recolectar" styleClass="label"/></f:facet>
																<h:outputText value="#{representante.cantidadRecolectar}" styleClass="label"/>
															</rich:column>
														</rich:scrollableDataTable>
													</td>
												</tr>
												<tr>
													<td align="left">
														<table width="100%">
															<tr>
																<td width="50%" align="center">
																	<a4j:commandButton action="#{remitosVentanilla.mostrarDetalleRemito}"
																		id="btnDetalleRemito"
																		value="Detalle" styleClass="botonGrid"
																		reRender="formRemitosVentanilla,pnlIzquierdo,pnlConsultaRepresentantes,fsRepresentantes,pnlDerecho,pnlRemitos,cantidadRepresentantes,pnlRepresentantes,comentariosRecoleccion"
																		disabled="#{ not remitosVentanilla.habilitarControlesRepresentantes}">
																	</a4j:commandButton>
																</td>
																<td width="50%" align="center">
																	<a4j:commandButton action="#{remitosVentanilla.cancelarRepresentantes}"
																		value="Limpiar" styleClass="botonGrid"
																		reRender="formRemitosVentanilla,pnlIzquierdo,pnlConsultaRepresentantes,fsRepresentantes,pnlDerecho,pnlRemitos,cantidadRepresentantes,pnlRepresentantes,comentariosRecoleccion"
																		disabled="#{ not remitosVentanilla.habilitarControlesRepresentantes}">
																	</a4j:commandButton>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</t:fieldset>
								</t:fieldset>
							</t:fieldset>
						</td>
						<td valign="top" width="50%">
							<t:fieldset style="color: Black; vertical-align: top" id="pnlDerecho" rendered="#{remitosVentanilla.habilitarDetalleRemito}">
								<t:fieldset legend="#{msg.recoleccionRemitos_remito}" id="pnlRemitos"
										style="color: Black; max-height: 100%; max-width: 100%; vertical-align: top" rendered="#{remitosVentanilla.habilitarDetalleRemito}">
									<table>
										<tr>
											<td width="50%" align="left">
											</td>
											<td width="50%" align="left">
											</td>
										</tr>
										<tr>
											<td width="100%" align="center" colspan="2">
												<table width="100%">
													<tr>
														<td width="16%" align="right">
															<h:outputLabel value="#{msg.recoleccionRemitos_registro}" styleClass="label"/>
														</td>
														<td align="left" width="17%">
															<h:outputText value="#{remitosVentanilla.representanteSeleccionado.account}" styleClass="label"/>
														</td>
														<td width="16%" align="right">
															<h:outputLabel value="#{msg.recoleccionRemitos_campania}" styleClass="label"/>
														</td>
														<td align="left" width="17%">
															<h:outputText value="#{remitosVentanilla.representanteSeleccionado.campania}" styleClass="label"/>
														</td>
														<td width="16%" align="right">
															<h:outputLabel value="#{msg.recoleccionRemitos_zona}" styleClass="label"/>
														</td>
														<td align="left" width="18%">
															<h:outputText value="#{remitosVentanilla.representanteSeleccionado.descripcionZona}" styleClass="label"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td width="100%" align="left" colspan="2">
												<br></br>
											</td>
										</tr>
										<tr>
											<td width="50%" align="left">
												<h:outputLabel value="#{msg.recoleccionRemitos_cantidadProductos}" styleClass="label"/>
											</td>
											<td width="50%" align="left">
												<h:outputText value="#{remitosVentanilla.cantidadARecolectar}" styleClass="label"/>
												<h:inputHidden id="ihCantidadARecolectar" value="#{remitosVentanilla.cantidadARecolectar}"></h:inputHidden>
											</td>
										</tr>
										<tr>
											<td width="50%" align="left">
												<h:outputLabel value="#{msg.recoleccionRemitos_cantidadRecolectados}" styleClass="label"/>
											</td>
											<td width="50%" align="left">
												<h:inputText id="cantidadRecolectados" onkeypress="validaCantRecol();" onchange="validarRecoleccion();" value="#{remitosVentanilla.cantidadRecolectada}"></h:inputText>
											</td>
										</tr>
										<tr>
											<td width="100%" align="left" colspan="2">
												<h:outputLabel value="#{msg.recoleccionRemitos_comentarios}" styleClass="label"/>
											</td>
										</tr>
										<tr>
											<td width="100%" align="left" colspan="2">
												<h:inputTextarea id="taCommentsRecol" styleClass="inputText" style="width: 400px; height: 40px; overflow:auto;"
												value="#{remitosVentanilla.comentarios}"
												onkeyup="limitTextArea(this,150);"
												onkeydown="limitTextArea(this,150);" />
											</td>
										</tr>
										<tr>
											<td width="50%" align="left">
												<h:outputLabel value="#{msg.recoleccionRemitos_causaNoRecoleccionAjustes}" styleClass="label"/>
											</td>
											<td width="50%" align="left">
												<h:selectOneMenu onchange="validarRecoleccion();" id="cbCausasNoRecoleccion" value="#{remitosVentanilla.idCausaNoRecoleccion}" style="width: 100%;" styleClass="inputText">
													<f:selectItems value="#{remitosVentanilla.listaCausasNoRecoleccion}" />
												</h:selectOneMenu>
											</td>
										</tr>
										<tr>
											<td width="50%" align="center">
												<a4j:commandButton action="#{remitosVentanilla.recolectarRemito}"
														value="Aceptar" styleClass="botonGrid"
														reRender="formRemitosVentanilla,pnlIzquierdo,pnlConsultaRepresentantes,fsRepresentantes,pnlDerecho,pnlRemitos,cantidadRepresentantes,pnlRepresentantes,comentariosRecoleccion">
												</a4j:commandButton>
											</td>
											<td width="50%" align="center">
												<a4j:commandButton action="#{remitosVentanilla.cancelarRecoleccion}"
														value="Cancelar" styleClass="botonGrid"
														reRender="formRemitosVentanilla,pnlIzquierdo,pnlConsultaRepresentantes,fsRepresentantes,pnlDerecho,pnlRemitos,cantidadRepresentantes,pnlRepresentantes,comentariosRecoleccion">
												</a4j:commandButton>
											</td>
										</tr>
									</table>
								</t:fieldset>
							</t:fieldset>
						</td>
					</tr>
				</table>
				</a4j:form>
			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>