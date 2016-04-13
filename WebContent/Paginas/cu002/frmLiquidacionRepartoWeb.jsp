<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:rich="http://richfaces.org/rich"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:t="http://myfaces.apache.org/tomahawk"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Registro de Entrega y Devoluci&oacute;n de &Oacute;rdenes</title>
</head>
<body>
	<f:view>
		<ui:composition template="/Paginas/template.jsp">
			<ui:define name="content">
				<a4j:form id="frmLiquidacionRepartoWeb">
				<h:inputHidden id="deshabilitarEdicionCampo" value="#{liquidacionRepartoWeb.deshabilitarEdicion}"></h:inputHidden>
				<h:inputHidden id="valorDeOrden" value="#{liquidacionRepartoWeb.valorDeOrden}"></h:inputHidden>
				<h:inputHidden id="tipoDeOrden" value="#{liquidacionRepartoWeb.tipoDeOrden}"></h:inputHidden>
				<h:inputHidden id="permitirAceptarOrden" value="#{liquidacionRepartoWeb.permitirAceptarOrden}"></h:inputHidden>
				<h:inputHidden id="mensajeAceptarDetalleLbl" value="#{liquidacionRepartoWeb.mensajeAceptarDetalle}"></h:inputHidden>
				<h:inputHidden id="deshabilitarDevolucionesSegLiq" value="#{liquidacionRepartoWeb.deshabilitarDevolucionSegundaLiquidacion}"></h:inputHidden>
				<f:loadBundle basename="com.datacode.avon_ots_admin.cu002.messagesCU002" var="msg" />
				<h:outputLabel value="#{msg.regEntDev_titulo}" styleClass="tituloPagina"></h:outputLabel>
				<table width="100%">
					<tr>
						<td align="center">
							<h:outputText styleClass="mensajeErrorReparto" value="#{liquidacionRepartoWeb.mensajeGenerico}" />
						</td>
					</tr>
					<tr>
						<td valign="top" width="100%">
							<t:fieldset legend="#{msg.regEntDev_consultaOrdenes}" id="pnlConsultaOrdenes" style="text-size: 50px; font-size: 50px; font-size-adjust: none;" rendered="#{not liquidacionRepartoWeb.mostrarPanelDetalle}">
									<table>
										<tr>
											<td width="7%" align="left">
												<h:outputLabel id="lblCampania" value="#{msg.regEntDev_campania}" styleClass="label"/>
											</td>
											<td width="17%" align="left">
												<h:selectOneMenu id="cbCampania" value="#{liquidacionRepartoWeb.idCampania}"
													style="width: 100%;" styleClass="inputText"
													onkeypress="javascript:hacerClicBotonLiqRepWeb(event, 'btnConsultar');">
													<f:selectItems value="#{liquidacionRepartoWeb.listaCampanias}" />
												</h:selectOneMenu>
											</td>
											<td width="5%">
												<h:outputLabel id="lblZona" value="#{msg.regEntDev_zona}" styleClass="label"/>
											</td>
											<td width="15%">
												<h:selectOneMenu id="cbZona" value="#{liquidacionRepartoWeb.idZona}"
													style="width: 100%;" styleClass="inputText"
													onkeypress="javascript:hacerClicBotonLiqRepWeb(event, 'btnConsultar');">
													<f:selectItems value="#{liquidacionRepartoWeb.listaZonas}" />
												</h:selectOneMenu>
											</td>
											<td width="7%">
												<h:outputLabel id="lblRegistro" value="#{msg.regEntDev_registro}" styleClass="label"/>
											</td>
											<td width="15%">
												<h:inputText id="tfRegistro" styleClass="inputText" maxlength="9" style="width: 100%;"
													value="#{liquidacionRepartoWeb.registro}"
													onkeypress="return liquidacionRepartoWebKeyPress(event, 'Consultar');"
													onchange="liquidacionRepartoWebCambioRegistroNombre();" >
													<a4j:support event="onkeypress"
													    focus="tfRegistro"
														reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems"
														ignoreDupResponses="true" requestDelay="1000"/>
												</h:inputText>
											</td>
											<td width="12%">
												<h:outputLabel value="#{msg.regEntDev_ordenSegmentada}" styleClass="label"/>
											</td>
											<td width="22%">
												<h:selectOneMenu id="cbOrdenSegmentada" value="#{liquidacionRepartoWeb.idOrdenSegmentada}"
														style="width: 100%;" styleClass="inputText"
														onkeypress="javascript:hacerClicBotonLiqRepWeb(event, 'btnConsultar');">
													<f:selectItem itemValue="-1" itemLabel="Seleccione una opción" />
													<f:selectItem itemValue="0"  itemLabel="0" />
													<f:selectItem itemValue="1"  itemLabel="1" />
													<f:selectItem itemValue="2"  itemLabel="2" />
													<f:selectItem itemValue="-99" itemLabel="N" />
												</h:selectOneMenu>
											</td>
										</tr>
										<tr>
											<td align="left">
												<h:outputLabel id="lblNombre" value="#{msg.regEntDev_nombre}" styleClass="label"/>
											</td>
											<td colspan="3" align="left">
												<h:inputText id="tfNombre" styleClass="inputText" style="width: 100%"
													value="#{liquidacionRepartoWeb.nombre}" maxlength="100"
													onkeypress="javascript:hacerClicBotonLiqRepWeb(event, 'btnConsultar');" />
											</td>
											<td colspan="3" align="center">
												<table width="100%">
													<tr>
														<td width="25%" align="center">
															<h:commandButton value="#{msg.regEntDev_consultar}" id="btnConsultar"
																action="#{liquidacionRepartoWeb.consultarOrdenes}"
																oncomplete="if (#{liquidacionRepartoWeb.mostrarGridConsulta}) javascript:Richfaces.showModalPanel('modPanRepLiqRepWeb');
																	hacerFocoElementoLiqRepWeb('tfRegistro');"
																reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems,btnEditarOrden">											
															</h:commandButton>
														</td>
														<td width="25%" align="center">
															<a4j:commandButton value="#{msg.regEntDev_limpiar}" id="btnLimpiarFormulario"
																action="#{liquidacionRepartoWeb.limpiarFormulario}"
																reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems,btnEditarOrden"
																oncomplete="javascript:f_tcalAddOnload(f_tcalInit());">											
															</a4j:commandButton>
														</td>
														<td width="50%" align="center">
															<a4j:commandButton value="#{msg.regEntDev_enviarLiqPendientes}"
																id="btnEnviarLiqPendientes"
																action="#{liquidacionRepartoWeb.enviarLiquidacionesPendientes}"
																reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems,btnEditarOrden"
																style="visibility: hidden; display: none;">											
																</a4j:commandButton>
															&nbsp;&nbsp;&nbsp;
															<a4j:commandButton value="#{msg.regEntDev_enviarLiqPendientes}"
																onclick="javascript:enviarLiquidacionesPendientes();"
																reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems,btnEditarOrden"
																oncomplete="javascript:f_tcalAddOnload(f_tcalInit());">											
															</a4j:commandButton>
														</td>
													</tr>
												</table>
											</td>
											<td align="right">
											</td>
										</tr>
									</table>									
							</t:fieldset>
						</td>
					</tr>
					<tr>
						<td valign="top" width="100%">
								<t:fieldset legend="#{msg.regEntDev_ordenes}" id="pnlOrdenes" rendered="#{liquidacionRepartoWeb.mostrarPanelOrdenes}">
									<table width="100%" border="0" cellspacing="3" cellpadding="0" >
										<tr>
											<td width="100%" align="center">
												<h:inputHidden id="valorOrdenamientoLiqRepWeb" value="#{liquidacionRepartoWeb.valorOrdenamiento}"></h:inputHidden>
												<h:inputHidden id="valorOrdenamientoAnteriorLiqRepWeb" value="#{liquidacionRepartoWeb.valorOrdenamientoAnterior}"></h:inputHidden>
												<h:inputHidden id="tipoOrdenamientoLiqRepWeb" value="#{liquidacionRepartoWeb.tipoOrdenamiento}"></h:inputHidden>
												<h:inputHidden id="valueEsOrdenamiento" value="#{liquidacionRepartoWeb.esOrdenamiento}"></h:inputHidden>
												<h:inputHidden id="idRowSeleccionado" value="#{liquidacionRepartoWeb.idRowSeleccionado}"></h:inputHidden>
												<h:commandButton
													id="btnOrdenarOrdenes"
													action="#{liquidacionRepartoWeb.ordenarOrdenes}"
													oncomplete="javascript:oncompleteOrdenamientoJavaLiqRepWeb();"
													style="visibility: hidden; display: none;"
													reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems"
													>
												</h:commandButton>
												<rich:scrollableDataTable id="tblOrdenes"
														value="#{liquidacionRepartoWeb.listaOrdenes}" var="orden"
														styleClass="gridDatosScrollable" headerClass="headerGridDatosScrollable" footerClass="footerGridDatosScrollable"
														rendered="#{liquidacionRepartoWeb.mostrarTblOrdenes}" width="100%" height="350px"
														binding="#{liquidacionRepartoWeb.tblOrdenes}"
														onRowDblClick="javascript:document.getElementById('frmLiquidacionRepartoWeb:btnEditarOrden').click();">
													<rich:column width="65px" sortable="false" styleClass="gridDatoDerecha">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("registroNumerico")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="registroNumericoAsc" />
																<h:outputText value="Registro" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="registroNumericoDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<a4j:commandButton value="moverScroll" id="btnMoverScroll" style="visibility: hidden; display: none;">											
														</a4j:commandButton>
														<h:outputText value="#{orden.registro}" styleClass="label" id="labelRegistro"/>
													</rich:column>
													<rich:column width="150px" sortable="false" styleClass="gridDatoIzquierda">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("nombre")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="nombreAsc" />
																<h:outputText value="Nombre" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="nombreDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.nombre}" styleClass="label"/>
													</rich:column>
													<rich:column width="65px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("rutaSecuencia")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="rutaSecuenciaAsc" />
																<h:outputText value="Ruta OTS" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="rutaSecuenciaDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.rutaSecuencia}" styleClass="label"/>
													</rich:column>
													<rich:column width="65px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("rutaSos")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="rutaSosAsc" />
																<h:outputText value="Ruta SOS" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="rutaSosDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.rutaSos}" styleClass="label"/>
													</rich:column>
													<rich:column width="45px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("bloq")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="bloqAsc" />
																<h:outputText value="Bloq" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="bloqDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.bloq}" styleClass="label"/>
													</rich:column>
													<rich:column width="70px" sortable="false" styleClass="gridDatoDerecha">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("montoOrden")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="montoOrdenAsc" />
																<h:outputText value="Valor COD" styleClass="label" style="text-align: right;" />
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="montoOrdenDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.montoOrdenMostrar}" styleClass="label"/>
													</rich:column>
													<rich:column width="70px" sortable="false" styleClass="gridDatoDerecha">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("valorModif")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="valorModifAsc" />
																<h:outputText value="Valor modif" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="valorModifDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.valorModifMostrar}" styleClass="label"/>
													</rich:column>
													<rich:column width="80px" sortable="false" styleClass="gridDatoDerecha">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("digitosModifNumerico")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="digitosModifNumericoAsc" />
																<h:outputText value="Dígitos modif" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="digitosModifNumericoDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.digitosModif}" styleClass="label"/>
													</rich:column>
													<rich:column width="70px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("liquidacion")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="liquidacionAsc" />
																<h:outputText value="No. Liq." styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="liquidacionDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.liquidacion}" styleClass="label"/>
													</rich:column>
													<rich:column width="80px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("tipoDePago")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="tipoDePagoAsc" />
																<h:outputText value="Tipo Pago" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="tipoDePagoDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.tipoDePago}" styleClass="label"/>
													</rich:column>
													<rich:column width="110px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("liqbanco")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="liqbancoAsc" />
																<h:outputText value="Liquidación (Banco)" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="liqbancoDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.liqbanco}" styleClass="label"/>
													</rich:column>
													<rich:column width="70px" sortable="false" styleClass="gridDatoDerecha">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("montoPagado")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="montoPagadoAsc" />
																<h:outputText value="Monto Pago" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="montoPagadoDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.montoPagadoMostrar}" styleClass="label"/>
													</rich:column>
													<rich:column width="70px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("fechaPago")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="fechaPagoAsc" />
																<h:outputText value="Fecha Pago" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="fechaPagoDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.fechaPago}" styleClass="label"/>
													</rich:column>
													<rich:column width="100px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("primeraDevolucion")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="primeraDevolucionAsc" />
																<h:outputText value="Devol Reparto" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="primeraDevolucionDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.primeraDevolucion}" styleClass="label"/>
													</rich:column>
													<rich:column width="100px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("devolucionMostrar")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="devolucionMostrarAsc" />
																<h:outputText value="Devolución final" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="devolucionMostrarDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.devolucionMostrar}" styleClass="label"/>
													</rich:column>
													<rich:column width="140px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("quienRecibe")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="quienRecibeAsc" />
																<h:outputText value="Quién recibe" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="quienRecibeDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.quienRecibe}" styleClass="label"/>
													</rich:column>
													<rich:column width="60px" sortable="false" styleClass="gridDatoDerecha">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("cantidadItems")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="cantidadItemsAsc" />
																<h:outputText value="Bultos" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="cantidadItemsDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="Total: #{liquidacionRepartoWeb.totalItems}" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.cantidadItems}" styleClass="label"/>
													</rich:column>
													<rich:column width="60px" sortable="false" styleClass="gridDatoDerecha">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("cantidadCajas")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="cantidadCajasAsc" />
																<h:outputText value="Cajas" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="cantidadCajasDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="Total: #{liquidacionRepartoWeb.totalCajas}" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.cantidadCajas}" styleClass="label"/>
													</rich:column>
													<rich:column width="60px" sortable="false" styleClass="gridDatoDerecha">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("cantidadPremiosUnitarios")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="cantidadPremiosUnitariosAsc" />
																<h:outputText value="PU" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="cantidadPremiosUnitariosDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="Total: #{liquidacionRepartoWeb.totalPUs}" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.cantidadPremiosUnitarios}" styleClass="label"/>
													</rich:column>
													<rich:column width="50px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("descripcionZona")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="descripcionZonaAsc" />
																<h:outputText value="Zona" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="descripcionZonaDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.descripcionZona}" styleClass="label"/>
													</rich:column>
													<rich:column width="60px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("campania")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="campaniaAsc" />
																<h:outputText value="Campaña" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="campaniaDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.campania}" styleClass="label"/>
													</rich:column>
													<rich:column width="50px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("ordenSegmentada")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="ordenSegmentadaAsc" />
																<h:outputText value="NR" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="ordenSegmentadaDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.ordenSegmentada}" styleClass="label"/>
													</rich:column>
													<rich:column width="70px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("robo")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="roboAsc" />
																<h:outputText value="Robo" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="roboDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.robo}" styleClass="label"/>
													</rich:column>
													<rich:column width="120px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("secuenciaEfectivo")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="secuenciaEfectivoAsc" />
																<h:outputText value="Secuencia efectivo" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="secuenciaEfectivoDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.secuenciaEfectivo}" styleClass="label"/>
													</rich:column>
													<rich:column width="100px" sortable="false" styleClass="gridDatoCentro">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("remitos")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="remitosAsc" />
																<h:outputText value="Remitos" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="remitosDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.remitos}" styleClass="label"/>
													</rich:column>
													<rich:column width="100px" sortable="false" styleClass="gridDatoIzquierda">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("comentario")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="comentarioAsc" />
																<h:outputText value="Comentario" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="comentarioDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.comentario}" styleClass="label"/>
													</rich:column>
													<rich:column width="100px" sortable="false" styleClass="gridDatoIzquierda">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("domicilio1")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="domicilio1Asc" />
																<h:outputText value="Domicilio 1" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="domicilio1Desc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.domicilio1}" styleClass="label"/>
													</rich:column>
													<rich:column width="100px" sortable="false" styleClass="gridDatoIzquierda">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("domicilio2")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="domicilio2Asc" />
																<h:outputText value="Domicilio 2" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="domicilio2Desc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.domicilio2}" styleClass="label"/>
													</rich:column>
													<rich:column width="100px" sortable="false" styleClass="gridDatoIzquierda">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("domicilio3")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="domicilio3Asc" />
																<h:outputText value="Domicilio 3" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="domicilio3Desc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.domicilio3}" styleClass="label"/>
													</rich:column>
													<rich:column width="100px" sortable="false" styleClass="gridDatoIzquierda">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("estado")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="estadoAsc" />
																<h:outputText value="Estado" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="estadoDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.estado}" styleClass="label"/>
													</rich:column>
													<rich:column width="100px" sortable="false" styleClass="gridDatoIzquierda">
														<f:facet name="header">
															<h:commandLink action='#{liquidacionRepartoWeb.ordenarOrdenes("seccion")}'>
														 		<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/asc.png" id="seccionAsc" />
																<h:outputText value="Sección" styleClass="label"/>
																<h:graphicImage style="width: 9px; height: 5px; visibility: hidden;" url="/img/desc.png" id="seccionDesc" />
															</h:commandLink>
														</f:facet>
														<f:facet name="footer">
															<h:outputText value="" styleClass="label" />
														</f:facet>
														<h:outputText value="#{orden.seccion}" styleClass="label"/>
													</rich:column>
												</rich:scrollableDataTable>
											</td>
											<tr>
												<td width="100%">
													<table width="100%">
														<tr>
															<td width="30%" align="left">
																<h:outputLabel value="#{msg.regEntDev_tipoLiquidacion}" styleClass="label"/>
																&nbsp;
																<h:selectOneMenu id="cbTipoLiquidacion" value="#{liquidacionRepartoWeb.tipoLiquidacion}"
																		style="width: 100%;" styleClass="inputText"
																		disabled="#{liquidacionRepartoWeb.disabledBtnLimpiarOrdenes}">
																	<f:selectItems value="#{liquidacionRepartoWeb.listaTiposLiquidacion}" />
																</h:selectOneMenu>
															</td>
															<td width="18%" align="right">
																<h:commandButton value="Editar" id="btnEditarOrden"
																		action="#{liquidacionRepartoWeb.editarOrden}"
																		disabled="#{liquidacionRepartoWeb.disabledBtnLimpiarOrdenes}"
																		reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems,btnEditarOrden"
																		oncomplete="javascript:f_tcalAddOnload(f_tcalInit()); javascript:liqRepWebOnChangeCambiaRazon();">											
																</h:commandButton>
															</td>
															<td width="4%" align="center">
																&nbsp;
															</td>
															<td width="20%" align="left">
																<a4j:commandButton value="#{msg.regEntDev_limpiarOrdenes}" id="btnLimpiar"
																		style="visibility: hidden; display: none;"
																		action="#{liquidacionRepartoWeb.limpiarOrdenes}"
																		reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems,btnEditarOrden"
																		disabled="#{liquidacionRepartoWeb.disabledBtnLimpiarOrdenes}">											
																</a4j:commandButton>
																&nbsp;
																<h:commandButton value="Exportar a Excel" id="btnExportarExcel"
																		action="#{liquidacionRepartoWeb.exportarExcel}"
																		disabled="#{liquidacionRepartoWeb.disabledBtnLimpiarOrdenes}"
																		reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems,btnEditarOrden">											
																</h:commandButton>
															</td>
															<td width="18%" align="right">
																<table width="100%">
																	<tr>
																		<td width="85%" align="right">
																			<h:outputLabel value="#{msg.regEntDev_cantidadRegistrosOrdenes}" styleClass="label"/>
																		</td>
																		<td width="15%" align="right">
																			<h:outputLabel id="lblCantidadRegOrdenes" value="#{liquidacionRepartoWeb.cantidadOrdenes}" styleClass="label"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</tr>
									</table>
								</t:fieldset>
						</td>
					</tr>
					<tr>
						<td valign="top" width="100%">
							<!-- Inicia detalle de Orden -->
							<t:fieldset legend="#{msg.regEntDev_detalleItems}" id="pnlDetalleItems" rendered="#{liquidacionRepartoWeb.mostrarPanelDetalle}">
								<table width="100%" border="0" cellspacing="3" cellpadding="0" >
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td width="5%" align="right">
														<h:outputLabel value="#{msg.regEntDev_registroMostrarDet}" styleClass="label"/>
													</td>
													<td width="5%" align="left">
														<h:outputText value="#{liquidacionRepartoWeb.ordenDetalle.registro}" styleClass="label"/>
													</td>
													<td width="5%" align="right">
														<h:outputLabel value="#{msg.regEntDev_zonaMostrarDet}" styleClass="label"/>
													</td>
													<td width="5%" align="left">
														<h:outputText value="#{liquidacionRepartoWeb.ordenDetalle.descripcionZona}" styleClass="label"/>
													</td>
													<td width="5%" align="right">
														<h:outputLabel value="#{msg.regEntDev_campaniaMostrarDet}" styleClass="label"/>
													</td>
													<td width="6%" align="left">
														<h:outputText value="#{liquidacionRepartoWeb.ordenDetalle.campania}" styleClass="label"/>
													</td>
													<td width="6%" align="right">
														<h:outputLabel value="#{msg.regEntDev_rutaSecuenciaMostrarDet}" styleClass="label"/><h:outputText value=":" styleClass="label"/>
													</td>
													<td width="5%" align="left">
														<h:outputText value="#{liquidacionRepartoWeb.ordenDetalle.rutaSecuencia}" styleClass="label"/>
													</td>
													<td width="5%" align="right">
														<h:outputLabel value="#{msg.regEntDev_nombreMostrarDet}" styleClass="label"/>
													</td>
													<td width="23%" align="left">
														<h:outputText value="#{liquidacionRepartoWeb.ordenDetalle.nombre}" styleClass="label"/>
													</td>
													<td width="8%" align="right">
														<h:outputLabel value="#{msg.regEntDev_totalDeItemsOrden}" styleClass="label"/>
													</td>
													<td width="3%" align="left">
														<h:outputText value="#{liquidacionRepartoWeb.cantidadFilasItems}" styleClass="label"/>
													</td>
													<td width="12%" align="right">
														<h:outputLabel value="#{msg.regEntDev_tipoDeLiquidacion}" styleClass="label"/>
													</td>
													<td width="9%" align="left">
														<h:outputText value="#{liquidacionRepartoWeb.descripcionTipoLiquidacion}" styleClass="label"/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<t:fieldset legend="#{msg.regEntDev_enrutado}">
									<table width="100%" border="0" cellspacing="3" cellpadding="0" >
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td width="7%" align="left">
														<h:outputLabel id="lblEnrutadoRutas" value="#{msg.regEntDev_enrutadoRutas}" styleClass="label"/>
													</td>
													<td width="15%" align="left">
														<h:selectOneMenu id="cbEnrutadoRutas" value="#{liquidacionRepartoWeb.idEnrutadoRuta}" style="width:100%;" styleClass="inputText">
															<f:selectItems value="#{liquidacionRepartoWeb.listaEnrutadoRutas}" />
														</h:selectOneMenu>
													</td>
													<td width="5">
														&nbsp;
													</td>
													<td width="7%" align="left">
														<h:outputLabel id="lblEnrutadoSecuencia" value="#{msg.regEntDev_enrutadoSecuencia}" styleClass="label"/>
													</td>
													<td width="7%" align="left">
														<h:inputText id="tfEnrutadoSecuencia" onkeypress="return enrutadoSoloNumeros(event)" onchange="enrutadoSoloNumerosOnChange(this)" styleClass="inputText" value="#{liquidacionRepartoWeb.enrutadoSecuencia}" maxlength="3" />
													</td>
													<td width="59%">
														&nbsp;
													</td>
												</tr>
											</table>
										</td>
									</tr>
									</table>
								</t:fieldset>
								<t:fieldset legend="#{msg.regEntDev_items}">
									<div style="height: 68px; width: 100%; overflow-y: scroll; border: 1px solid #000;">
									<h:dataTable id="tblDetalleItems"
											value="#{liquidacionRepartoWeb.listaDetalleItems}" var="orden"
											styleClass="gridDatosScrollable" headerClass="headerGridDatosScrollable"
											rendered="#{liquidacionRepartoWeb.mostrarDetalleItems}" width="100%" height="120px"
											columnClasses="detalleItemsCodigoBarras,detalleItemsFSC,detalleItemsEAN13,detalleItemsEstatus,detalleItemsDescripcion,detalleItemsTipo,detalleItemsEntregar">
										<h:column styleClass="gridDatoCentro">
											<f:facet name="header">
												<h:outputText value="Código de barras" styleClass="label"/>
											</f:facet>
											<h:outputText value="#{orden.codigoBarras}" styleClass="label"/>
										</h:column>
										<h:column styleClass="gridDatoCentro">
											<f:facet name="header">
												<h:outputText value="FSC" styleClass="label"/>
											</f:facet>
											<h:outputText value="#{orden.fsc}" styleClass="label"/>
										</h:column>
										<h:column styleClass="gridDatoCentro">
											<f:facet name="header">
												<h:outputText value="EAN13" styleClass="label"/>
											</f:facet>
											<h:outputText value="#{orden.ean13}" styleClass="label"/>
										</h:column>
										<h:column styleClass="gridDatoCentro">
											<f:facet name="header">
												<h:outputText value="Estatus" styleClass="label"/>
											</f:facet>
											<h:outputText value="#{orden.estatusItem}" styleClass="label"/>
										</h:column>
										<h:column styleClass="gridDatoCentro">
											<f:facet name="header">
												<h:outputText value="Descripción" styleClass="label"/>
											</f:facet>
											<h:outputText value="#{orden.descripcionItem}" styleClass="label"/>
										</h:column>
										<h:column styleClass="gridDatoCentro">
											<f:facet name="header">
												<h:outputText value="Tipo" styleClass="label"/>
											</f:facet>
											<h:outputText value="#{orden.tipoItem}" styleClass="label"/>
										</h:column>
										<h:column styleClass="gridDatoCentro">
											<f:facet name="header">
												<h:outputText value="Entregar" styleClass="label"/>
											</f:facet>
											<h:selectBooleanCheckbox id="checkEntregarItem" value="#{orden.checked}" disabled="#{liquidacionRepartoWeb.deshabilitarEdicion}"></h:selectBooleanCheckbox>
										</h:column>
									</h:dataTable>
									</div>
								</t:fieldset>
								<t:fieldset legend="#{msg.regEntDev_liquidacion}">
								<table width="100%" border="0" cellspacing="3" cellpadding="0" >
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td width="9%" align="left">
														<h:outputLabel id="lblTipoDePago" value="#{msg.regEntDev_tipoDePago}" styleClass="label"/>
													</td>
													<td width="19%" align="left">
														<h:selectOneMenu id="cbTipoDePago" value="#{liquidacionRepartoWeb.idTipoDePago}" style="width:100%;" styleClass="inputText"
																disabled="#{liquidacionRepartoWeb.disabledTipoDePago}"
																onchange="liquidacionRepartoWebOnChangeTipoPago();">
															<f:selectItems value="#{liquidacionRepartoWeb.listaTiposDePago}" />
														</h:selectOneMenu>
													</td>
													<td width="1%">
														&nbsp;
													</td>
													<td width="4%" align="left">
														<h:outputLabel id="lblBanco" value="#{msg.regEntDev_banco}" styleClass="label"/>
													</td>
													<td width="15%" align="left">
														<h:selectOneMenu id="cbBanco" value="#{liquidacionRepartoWeb.idBanco}" style="width:100%;" styleClass="inputText"
																disabled="#{liquidacionRepartoWeb.disabledBanco}">
															<f:selectItems value="#{liquidacionRepartoWeb.listaBancos}" />
														</h:selectOneMenu>
													</td>
													<td width="1%">
														&nbsp;
													</td>
													<td width="12%" align="left">
														<h:outputLabel id="lblFechaDePago" value="#{msg.regEntDev_fechaDePago}" styleClass="label"/>
													</td>
													<td width="16%" align="left">
														<a4j:outputPanel id="outputFechaPago" ajaxRendered="true">
															<h:inputText id="txtFechaPago" styleClass="tcal" value="#{liquidacionRepartoWeb.fechaDePago}"
																style="width:100%;"
																disabled="#{liquidacionRepartoWeb.disabledFechaDePago}"/>
														</a4j:outputPanel>
													</td>
													<td width="4%">
														&nbsp;
													</td>
													<td width="17%">
														&nbsp;
													</td>
													<td width="2%">
														&nbsp;
													</td>
												</tr>
												<tr>
													<td align="left">
														<h:outputLabel id="lblFolioCobranza" value="#{msg.regEntDev_folioCobranza}" styleClass="label"/>
													</td>
													<td align="left">
														<h:inputText id="tfFolioCobranza" styleClass="inputText" style="width:98.5%"
																value="#{liquidacionRepartoWeb.folioCobranza}" maxlength="100"
																disabled="#{liquidacionRepartoWeb.disabledFolioCobranza}"/>
													</td>
													<td>
														&nbsp;
													</td>
													<td align="left">
														<h:outputLabel id="lblMonto" value="#{msg.regEntDev_monto}" styleClass="label"/>
													</td>
													<td align="left">
														<h:inputText id="tfMonto" styleClass="inputText" maxlength="8"
																value="#{liquidacionRepartoWeb.monto}"
																onkeypress="return validaMontoLiqRepWeb(event, this, 'btnAgregarPago');"
																onchange="javascript:onchangeMontoLiqRepWeb();"
																disabled="#{liquidacionRepartoWeb.disabledMonto}"
																style="width:98.5%; text-align: right;" />
													</td>
													<td>
														&nbsp;
													</td>
													<td align="left">
														<h:outputLabel id="lblCashSequence" value="#{msg.regEntDev_cashSequence}" styleClass="label"/>
													</td>
													<td align="left">
														<h:inputHidden id="valorNoExistePagoEfectivo" name="valorNoExistePagoEfectivo" value="#{liquidacionRepartoWeb.noExistePagoEfectivo}"></h:inputHidden>
														<h:inputHidden id="valorExistenPagos" name="valorExistenPagos" value="#{liquidacionRepartoWeb.existenPagos}"></h:inputHidden>
														<h:inputText id="tfCashSequence" styleClass="inputText" maxlength="20" style="width:100%;"
															value="#{liquidacionRepartoWeb.cashSequence}"
															disabled="#{liquidacionRepartoWeb.deshabilitarEdicion}">
														</h:inputText>
														<a4j:commandLink id="btnOnChangeHijackedCash"
																action="#{liquidacionRepartoWeb.habilitarCashSequence}"
																oncomplete="javascript:liqRepWebOnChangeCambiaRazon();"
																reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems"
																style="visibility: hidden; display: none;">
														</a4j:commandLink>
													</td>
													<td>
														&nbsp;
													</td>
													<td align="right">
														<a4j:commandButton value="#{msg.regEntDev_agregarPago}" id="btnAgregarPago"
																action="#{liquidacionRepartoWeb.agregarPago}"
																reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems"
																oncomplete="javascript:f_tcalAddOnload(f_tcalInit()); javascript:liqRepWebOnChangeCambiaRazon();"
																disabled="#{liquidacionRepartoWeb.disabledBtnAgregarPago or liquidacionRepartoWeb.deshabilitarEdicion or liquidacionRepartoWeb.disabledEdicionPagos}">											
														</a4j:commandButton>
														&nbsp;&nbsp;
													</td>
													<td align="right">
														&nbsp;
													</td>
												</tr>
											</table>
										</td>
									</tr>									
								</table>
								<t:fieldset legend="#{msg.regEntDev_pagos}" id="pnlPagos">
									<table width="100%" border="0" cellspacing="3" cellpadding="0" >
										<tr>
											<td width="100%">
												<div style="height: 80px; width: 100%; overflow-y: scroll; border: 1px solid #000;">
													<h:dataTable id="tblListaPagosItems"
															value="#{liquidacionRepartoWeb.listaPagosItems}" var="orden"
															styleClass="gridDatos" headerClass="gridDatosEncabezado"
															rendered="#{liquidacionRepartoWeb.mostrarDetalleItems}" width="100%">
														<h:column>
															<f:facet name="header">
																<h:outputText value="Tipo de Pago" styleClass="label"/>
															</f:facet>
															<h:outputText value="#{orden.descripcionTipoDePago}" styleClass="label"/>
														</h:column>
														<h:column>
															<f:facet name="header">
																<h:outputText value="Descripción" styleClass="label"/>
															</f:facet>
															<h:outputText value="#{orden.descripcionItem}" styleClass="label"/>
														</h:column>
														<h:column>
															<f:facet name="header">
																<h:outputText value="Folio" styleClass="label"/>
															</f:facet>
															<h:outputText value="#{orden.folioPago}" styleClass="label"/>
														</h:column>
														<h:column>
															<f:facet name="header">
																<h:outputText value="Fecha" styleClass="label"/>
															</f:facet>
															<h:outputText value="#{orden.fechaPago}" styleClass="label"/>
														</h:column>
														<h:column>
															<f:facet name="header">
																<h:outputText value="Monto" styleClass="label"/>
															</f:facet>
															<h:outputText value="#{orden.montoPago}" styleClass="label"/>
														</h:column>
														<h:column>
															<f:facet name="header">
																<h:outputText value="Acciones" styleClass="label"/>
															</f:facet>
															<table width="100%" align="center">
																<tr>
																	<td width="48%" align="right">
																		<a4j:commandButton value="Eliminar" action="#{liquidacionRepartoWeb.eliminarPago}"
																				disabled="#{liquidacionRepartoWeb.deshabilitarEdicion or liquidacionRepartoWeb.disabledEdicionPagos}"
																				reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems"
																				oncomplete="javascript:f_tcalAddOnload(f_tcalInit()); javascript:liqRepWebOnChangeCambiaRazon();">
																			<f:setPropertyActionListener value="#{orden}" target="#{liquidacionRepartoWeb.pagoEliminacion}" />
																		</a4j:commandButton>
																	</td>
																	<td width="4%">
																	</td>
																	<td width="48%" align="left">
																		<a4j:commandButton value="Editar" action="#{liquidacionRepartoWeb.prepararEdicionPago}"
																				disabled="#{liquidacionRepartoWeb.deshabilitarEdicion or liquidacionRepartoWeb.disabledEdicionPagos}"
																				onclick="javascript:liqRepWebHabilitarTiposPagos();"
																				reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems,tfFolioCobranza,tfMonto,txtFechaPago,cbBanco,cbTipoDePago"
																				oncomplete="javascript:f_tcalAddOnload(f_tcalInit()); javascript:liqRepWebOnChangeCambiaRazon2();">
																			<f:setPropertyActionListener value="#{orden}" target="#{liquidacionRepartoWeb.pagoEdicion}" />
																		</a4j:commandButton>
																	</td>
																</tr>
															</table>
														</h:column>
													</h:dataTable>
												</div>
											</td>
										</tr>
										<tr>
											<td width="100%" align="right">
												<table width="100%" align="right">
													<tr>
														<td width="40%">
														</td>
														<td width="12%" align="right">
															<h:outputLabel value="#{msg.regEntDev_montoCobrar}" styleClass="label"/>
														</td>
														<td width="16%" align="left">
															<h:outputText value="#{liquidacionRepartoWeb.montoTotalMostrar}" styleClass="label"/>
														</td>
														<td width="8%" align="right">
															<h:outputLabel value="#{msg.regEntDev_remanente}" styleClass="label"/>
														</td>
														<td width="8%" align="left">
															<h:outputText value="#{liquidacionRepartoWeb.remanenteMostrar}" styleClass="label"/>
														</td>
														<td width="8%" align="right">
															<h:outputLabel value="#{msg.regEntDev_montoTotal}" styleClass="label"/>
														</td>
														<td width="8%" align="left">
															<h:outputText value="#{liquidacionRepartoWeb.montoTotalPagosMostrar}" styleClass="label"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>	
								</t:fieldset>
								<table width="100%">
									<tr>
										<td width="10%" align="left">
											<h:outputLabel id="lblQuienRecibePago" value="#{msg.regEntDev_quienRecibe}" styleClass="label"/>
										</td>
										<td width="20%" align="left">
											<h:selectOneMenu id="cbQuienRecibe" value="#{liquidacionRepartoWeb.idQuienRecibe}" style="width:90%" styleClass="inputText"
													disabled="#{liquidacionRepartoWeb.disabledQuienRecibe}">
												<f:selectItems value="#{liquidacionRepartoWeb.listaQuienRecibe}" />
											</h:selectOneMenu>
										</td>
										<td width="10%" align="left">
											&nbsp;
										</td>
										<td width="5%" align="left">
											&nbsp;
										</td>
										<td width="15%" align="left">
											&nbsp;
										</td>
										<td width="25%" align="left">
											&nbsp;
										</td>
										<td width="15%">
											<a4j:commandButton value="#{msg.regEntDev_cancelar}" id="btnCancelarPago"
													action="#{liquidacionRepartoWeb.cancelarPago}"
													onclick="javascript:Richfaces.showModalPanel('mp');"
													reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems"
													disabled="false"
													oncomplete="javascript:f_tcalAddOnload(f_tcalInit()); javascript:Richfaces.hideModalPanel('mp');"
													style="visibility: hidden; display: none;">											
											</a4j:commandButton>
											&nbsp;
											<a4j:commandButton value="#{msg.regEntDev_liquidarOrden}" id="btnLiquidarOrdenPago"
													action="#{liquidacionRepartoWeb.liquidarOrdenPago}"
													onclick="javascript:Richfaces.showModalPanel('mp');"
													oncomplete="javascript:Richfaces.hideModalPanel('mp');"
													reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems"
													style="visibility: hidden; display: none;">											
											</a4j:commandButton>
											<input type="button" value="#{msg.regEntDev_liquidarOrden}" onclick="lrwConfLiq()" style="visibility: hidden; display: none;" />
											<h:inputHidden value="#{liquidacionRepartoWeb.remanente}" id="hidRemanente"></h:inputHidden>
										</td>
									</tr>
								</table>
								</t:fieldset>
								<table width="100%">
									<tr>
										<td width="60%">
										<t:fieldset legend="#{msg.regEntDev_devolucionLegend}">
										<table width="100%" border="0" cellspacing="3" cellpadding="0" >
											<tr>
												<td width="18%" align="left">
													<h:outputLabel id="lblRazonDevolucion" value="#{msg.regEntDev_razonDevolucion}" styleClass="label"/>
												</td>
												<td width="17%" align="left">
													<h:selectOneMenu id="cbRazonesDevolucion" value="#{liquidacionRepartoWeb.idRazonDevolucion}" style="width: 30px%;" styleClass="inputText"
															disabled="#{liquidacionRepartoWeb.disabledCbRazonesDevolucion}"
															onchange="cambiaRazonRepartoWeb();">
														<f:selectItems value="#{liquidacionRepartoWeb.listaRazonesDevolucion}" />
													</h:selectOneMenu>
													<a4j:commandButton value="#{msg.regEntDev_consultar}" id="btnValidarRazonesDevolucion"
															action="#{liquidacionRepartoWeb.validarRazonesDevolucion}"
															rendered="true" style="visibility: hidden; display: none;"
															reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems"
															oncomplete="javascript:eliminarTiposDePago(); javascript:f_tcalAddOnload(f_tcalInit());">
													</a4j:commandButton>
												</td>
												<td width="20%" align="right">
													<h:outputLabel id="lblQuienInformaDevolucion" value="#{msg.regEntDev_quienInforma}" styleClass="label" rendered="#{liquidacionRepartoWeb.mostrarPanelQuienInformaDevolucion}" />
												</td>
												<td width="45%" align="left">
													<h:selectOneMenu id="cbQuienInformaDevolucion"
															value="#{liquidacionRepartoWeb.idQuienInformaDevolucion}"
															style="width: 100%;" styleClass="inputText"
															disabled="#{liquidacionRepartoWeb.disabledQuienInformaDevolucion}"
															rendered="#{liquidacionRepartoWeb.mostrarPanelQuienInformaDevolucion}">
														<f:selectItems value="#{liquidacionRepartoWeb.listaQuienInformaDevolucion}" />
													</h:selectOneMenu>
													<a4j:commandButton value="#{msg.regEntDev_cancelar}" id="btnCancelarDevolucion"
															action="#{liquidacionRepartoWeb.cancelarDevolucion}"
															onclick="javascript:Richfaces.showModalPanel('mp');"
															reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems"
															oncomplete="javascript:Richfaces.hideModalPanel('mp');"
															style="visibility: hidden; display: none;">											
													</a4j:commandButton>
													<a4j:commandButton value="#{msg.regEntDev_devolucion}" id="btnDevolverOrden"
															action="#{liquidacionRepartoWeb.devolverOrden}"
															onclick="javascript:Richfaces.showModalPanel('mp');"
															reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems"
															oncomplete="javascript:Richfaces.hideModalPanel('mp');"
															style="visibility: hidden; display: none;">											
													</a4j:commandButton>
												</td>
											</tr>
										</table>
										</t:fieldset>
										</td>
										<td width="40%">
											<t:fieldset legend="#{msg.regEntDev_roboLegend}">
												<table width="100%">
													<tr>
														<td width="35%">
															<h:outputLabel id="lblRoboCajas" value="#{msg.regEntDev_roboCajas}" styleClass="label"/>
														</td>
														<td width="15%">
															<h:selectBooleanCheckbox id="checkRoboCajas"
																value="#{liquidacionRepartoWeb.roboCajas}"
																disabled="#{liquidacionRepartoWeb.deshabilitarEdicion}">
															</h:selectBooleanCheckbox>
														</td>
														<td width="35%">
															<h:outputLabel id="lblHijackedCash" value="#{msg.regEntDev_hijackedCash}" styleClass="label"/>
														</td>
														<td width="15%">
															<h:selectBooleanCheckbox id="checkHijackedCash"
																value="#{liquidacionRepartoWeb.hijackedCash}"
																disabled="#{liquidacionRepartoWeb.deshabilitarEdicion}">
															</h:selectBooleanCheckbox>
														</td>
													</tr>
												</table>
											</t:fieldset>
										</td>
									</tr>
									<tr>
										<td align="left">
											<table width="100%">
												<tr>
													<td width="10%" align="right">
														<h:outputLabel id="lblComentarios" value="#{msg.regEntDev_comentarios}" styleClass="label"/>
													</td>
													<td width="89%" align="left">
														<h:inputTextarea id="tfComentarios" styleClass="inputText" style="width:100%; height:36px; overflow:auto;"
															value="#{liquidacionRepartoWeb.comentarios}"
															disabled="#{liquidacionRepartoWeb.deshabilitarEdicion}">														
														</h:inputTextarea>
													</td>
													<td width="1%">
														&nbsp;
													</td>
												</tr>
											</table>
										</td>
										<td align="right">
											<a4j:commandButton
													value="#{msg.regEntDev_ventanaAceptarTitulo}" id="btnAceptarDetalle"
													action="#{liquidacionRepartoWeb.validarAceptarDetalle}"
													oncomplete="javascript:f_tcalAddOnload(f_tcalInit()); javascript:liqRepWebOnChangeCambiaRazon(); javascript:validarRegresoAceptarDetalle();"
													reRender="mensajeAceptarDetalleLbl,permitirAceptarOrden,idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems,mensajeAceptarDetalleLbl,frmAceptar,modPanRepLiqRepWebAceptar"
													style="margin-right: 20px;">
											</a4j:commandButton>
											<h:commandButton
													value="#{msg.regEntDev_cancelar}" id="btnCancelarDetalle"
													action="#{liquidacionRepartoWeb.cancelarDetalle}"
													onclick="javascript:Richfaces.showModalPanel('mp');"
													oncomplete="javascript:f_tcalAddOnload(f_tcalInit()); javascript:Richfaces.hideModalPanel('mp');"
													reRender="mensajeAceptarDetalleLbl,idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems">
											</h:commandButton>
											<a4j:commandButton
													value="#{msg.regEntDev_aceptar}" id="btnEliminarTodosLosPagos"
													onclick="javascript:Richfaces.showModalPanel('mp');"
													style="visibility: hidden; display: none;"
													oncomplete="javascript:f_tcalAddOnload(f_tcalInit()); javascript:liqRepWebOnChangeCambiaRazon(); javascript:Richfaces.hideModalPanel('mp');">
											</a4j:commandButton>
										</td>
									</tr>
								</table>
							</t:fieldset>
							<!-- Termina detalle de Orden -->
						</td>
					</tr>
				</table>
				
				<h:commandButton value="Aceptar"
						action="#{liquidacionRepartoWeb.aceptarOrden}"
						style="visibility: hidden; display: none;"
						id="btnAceptarCambiosOrden"
						oncomplete="javascript:Richfaces.hideModalPanel('modPanRepLiqRepWebAceptar'); javascript:f_tcalAddOnload(f_tcalInit()); javascript:liqRepWebOnChangeCambiaRazon();">
				</h:commandButton>
				
			</a4j:form>
			
			<a4j:form styleClass="formaAceptar" id="frmAceptarNo"
					binding="#{liquidacionRepartoWeb.frmAceptarNo}">
					<rich:modalPanel id="modPanRepLiqRepWebAceptarNo" autosized="true">
						<f:facet name="header">
							<h:outputText value="#{msg.regEntDev_ventanaAceptarTituloNo}" />
						</f:facet>
						<f:facet name="controls">
							<span style="cursor: pointer"
								onclick="javascript:Richfaces.hideModalPanel('modPanRepLiqRepWebAceptarNo'); javascript:f_tcalAddOnload(f_tcalInit()); javascript:liqRepWebOnChangeCambiaRazon();"> X
							</span>
						</f:facet>
							<div style="height: 110px; width: 300px; border: 1px solid #000;">
								<br></br>
								<h:outputText value="#{msg.regEntDev_ventanaAceptarTituloErrSegLiq}" />
								<br></br>
								<br></br>
								<table width="100%">
									<tr>
										<td width="25%">
											&nbsp;
										</td>
										<td width="50%" align="center">
											<a4j:commandButton value="Aceptar"
												onclick="javascript:Richfaces.hideModalPanel('modPanRepLiqRepWebAceptarNo')"
												oncomplete="javascript:f_tcalAddOnload(f_tcalInit()); javascript:liqRepWebOnChangeCambiaRazon();"
												reRender="mensajeAceptarDetalleLbl,idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems,btnEditarOrden">
											</a4j:commandButton>
										</td>
										<td width="25%">
											&nbsp;
										</td>
									</tr>
								</table>
							</div>
					</rich:modalPanel>
			</a4j:form>
			
			<a4j:form styleClass="formaAceptar" id="frmAceptar"
					binding="#{liquidacionRepartoWeb.frmAceptar}">
					<rich:modalPanel id="modPanRepLiqRepWebAceptar" autosized="true">
						<f:facet name="header">
							<h:outputText value="#{msg.regEntDev_ventanaAceptarTitulo}" />
						</f:facet>
						<f:facet name="controls">
							<span style="cursor: pointer"
								onclick="javascript:Richfaces.hideModalPanel('modPanRepLiqRepWebAceptar'); javascript:f_tcalAddOnload(f_tcalInit()); javascript:liqRepWebOnChangeCambiaRazon();"> X
							</span>
						</f:facet>
							<div style="height: 110px; width: 300px; border: 1px solid #000;">
								<br></br>
								<h:outputText value="#{liquidacionRepartoWeb.mensajeAceptarDetalle}" />
								<br></br>
								<br></br>
								<table width="100%">
									<tr>
										<td width="25%">
											&nbsp;
										</td>
										<td width="25%" align="center">
											<a4j:commandButton value="Aceptar"
												onclick="javascript:document.getElementById('frmLiquidacionRepartoWeb:btnAceptarCambiosOrden').click();"
												oncomplete="javascript:Richfaces.hideModalPanel('modPanRepLiqRepWebAceptar'); javascript:f_tcalAddOnload(f_tcalInit()); javascript:liqRepWebOnChangeCambiaRazon();">
											</a4j:commandButton>
										</td>
										<td width="25%" align="center">
											<a4j:commandButton value="Cancelar"
												onclick="javascript:Richfaces.hideModalPanel('modPanRepLiqRepWebAceptar')"
												oncomplete="javascript:f_tcalAddOnload(f_tcalInit()); javascript:liqRepWebOnChangeCambiaRazon();"
												reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems,btnEditarOrden">
											</a4j:commandButton>
										</td>
										<td width="25%">
											&nbsp;
										</td>
									</tr>
								</table>
							</div>
					</rich:modalPanel>
			</a4j:form>
			
			<!-- Inicia ventana de representantes -->
			<a4j:form styleClass="forma" id="frmModal"
					binding="#{liquidacionRepartoWeb.frmModal }">
					<rich:modalPanel id="modPanRepLiqRepWeb" autosized="true"
						showWhenRendered="#{liquidacionRepartoWeb.mostrarPanel}">
						<f:facet name="header">
							<h:outputText value="#{msg.regEntDev_ventanaRepresentantesTitulo }" />
						</f:facet>
						<f:facet name="controls">
							<span style="cursor: pointer"
								onclick="javascript:Richfaces.hideModalPanel('modPanRepLiqRepWeb')"> X
							</span>
						</f:facet>
							<div style="height: 200px; width: 100%; overflow-y: scroll; border: 1px solid #000;">
							<h:dataTable value="#{liquidacionRepartoWeb.listaRepresentantes}"
								var="act" id="tabla" styleClass="gridDatos"
								headerClass="gridDatosEncabezado"
								rendered="#{liquidacionRepartoWeb.mostrarPanel}"
								style="height: 85px; width: 866px; ">

								<h:column>
									<f:facet name="header">
										<h:outputText value="Registro" styleClass="label" />
									</f:facet>
									<h:outputText value="#{act.registro}" styleClass="label" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Nombre" styleClass="label" />
									</f:facet>
									<h:outputText value="#{act.nombre}" styleClass="label" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Dirección" styleClass="label" />
									</f:facet>
									<h:outputText value="#{act.domicilio}" styleClass="label" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Zona" styleClass="label" />
									</f:facet>
									<h:outputText value="#{act.descripcionZona}" styleClass="label" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Elegir" styleClass="label" />
									</f:facet>
									<a4j:commandButton value="Elegir"
										action="#{liquidacionRepartoWeb.asignarRepresentanteSeleccionado}"
										oncomplete="javascript:Richfaces.hideModalPanel('modPanRepLiqRepWeb')"
										reRender="idRowSeleccionado,valorExistenPagosvalorNoExistePagoEfectivo,frmLiquidacionRepartoWeb,cbRta,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlConsultaOrdenes,outputPanelDerecho,cbQuienInformaDevolucion,lblQuienInformaDevolucion,txtFechaPago,outputFechaPago,modPanRepLiqRepWeb,checkRoboCajas,tfCashSequence,checkHijackedCash,lblCantidadRegOrdenes,pnlDetalleItems">
										<f:setPropertyActionListener value="#{act}"
											target="#{liquidacionRepartoWeb.representanteSeleccionado}" />
									</a4j:commandButton>
								</h:column>
							</h:dataTable>
							</div>
					</rich:modalPanel>
			</a4j:form>
			<!-- Termina ventana de representantes -->								
			<script type="text/javascript">
				liqRepWebOnChangeCambiaRazon();
				onchangeMontoLiqRepWeb();
				oncompleteOrdenamientoJavaLiqRepWeb();
				hacerFocoElementoLiqRepWeb("tfRegistro");
			</script>
			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>