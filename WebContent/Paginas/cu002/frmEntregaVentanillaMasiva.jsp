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
<title>Entrega en Ventanilla Masiva</title>
</head>
<body>

	<f:view>
		<ui:composition template="/Paginas/template.jsp">
			<ui:define name="content">
				<!-- Incluye archivo de properties -->
				<f:loadBundle var="msg"
					basename="com.datacode.avon_ots_admin.cu002.messagesCU002" />
				<!-- Diseno de pagina -->
				<h:outputLabel value="#{msg.tituloEM}" styleClass="tituloPagina" />
				<table width="100%">
					<tr>
						<td valign="top"><t:fieldset
								style="color: Black;vertical-align: top" id="pnlIzquierdo">
								<t:fieldset legend="#{msg.pnlCriterioRepresentanteEM}"
									id="pnlCriterioRepresentanteEM"
									style="color: Black; max-height: 100%; max-width: 100%; vertical-align: top">
									<a4j:form id="frmCriterioRepresentante">
										<table width="100%" border="0" cellspacing="3" cellpadding="0">
											<tr>
												<td><h:outputLabel id="lblCampañaEM"
														value="#{msg.lblCampañaEM}" styleClass="label" /></td>
												<td><h:selectOneMenu id="cobCampaña"
														value="#{entregaVentanillaMasiva._idCampania}"
														style="width: 200px;" styleClass="inputText"
														disabled="#{entregaVentanillaMasiva._cobCampania}">
														<f:selectItems
															value="#{entregaVentanillaMasiva.obtenerCampanias}" />

													</h:selectOneMenu></td>
											</tr>
											<tr>
												<td><h:outputLabel id="lblCodigoRepresentanteEM"
														value="#{msg.lblCodigoRepresentanteEM}" styleClass="label" />
												</td>
												<td><h:inputText id="txtCodigoRepresentanteEM"
														styleClass="inputText" maxlength="15"
														style="width: 200px;"
														value="#{entregaVentanillaMasiva._registroRepresentante }"
														onkeypress="return entregaVentanillaMasivaKeyPress(event, 'Consultar');"
														disabled="#{entregaVentanillaMasiva._txtCodigoRepresentante}">
														<a4j:support event="onkeypress"
															focus="txtCodigoRepresentanteEM"
															reRender="frmCriterioRepresentante"
															ignoreDupResponses="true" requestDelay="1000" />
													</h:inputText></td>
											</tr>
											<tr>
												<td><h:outputLabel id="lblNombreRepresentanteEM"
														value="#{msg.lblNombreRepresentanteEM}" styleClass="label" />
												</td>
												<td><h:inputText id="txtNombreRepresentante"
														styleClass="inputText" style="width: 300px"
														value="#{entregaVentanillaMasiva._nombreRepresentante }"
														maxlength="150"
														disabled="#{entregaVentanillaMasiva._txtNombreRepresentante}" />
												</td>
											</tr>
											<tr>
												<td colspan="2" align="right"><a4j:commandButton
														value="#{msg.btnConsultarEM}" id="btnConsultar"
														action="#{entregaVentanillaMasiva.AgregarRepresentante}"
														oncomplete="if (#{entregaVentanillaMasiva._mostrarPanel}) javascript:Richfaces.showModalPanel('panel');
																	document.getElementById('frmCriterioRepresentante:txtCodigoRepresentanteEM').focus();"
														reRender="pnlIzquierdo, frmModal"
														disabled="#{entregaVentanillaMasiva._btnConsultar}">
													</a4j:commandButton></td>
											</tr>
											<tr>
												<td colspan="2" align="center"><h:outputText
														value="#{entregaVentanillaMasiva._mensajeError}"
														id="LblMensaje" styleClass="error" /></td>
											</tr>
										</table>

									</a4j:form>
								</t:fieldset>
								<t:fieldset legend="#{msg.pnlDetalleRepresentateEM}"
									id="pnlDetalleRepresentateEM"
									style="color: Black; max-height: 100%; max-width: 100%;">
									<a4j:form id="frmGridDetalleRepresentante"
										ignoreDupResponses="true">
										<div style="height: 200px; width: 100%; overflow-y: scroll;">
											<h:dataTable id="dtgDetalleRepresentante" sortable="true"
												sortAscending="true" preserveDataModel="true"
												preserveSort="true"
												value="#{entregaVentanillaMasiva._listaRegistrosRepresentantes}"
												var="dtgDetalle" styleClass="gridDatos"
												headerClass="gridDatosEncabezado"
												rendered="#{entregaVentanillaMasiva._tablaRepresentante}">
												<h:column>
													<f:facet name="header">
														<h:outputText value="Zona" styleClass="label" />
													</f:facet>
													<h:outputText value="#{dtgDetalle.zona}" styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:commandLink
															onclick="return SortTable('frmGridDetalleRepresentante:dtgDetalleRepresentante', 1, 'Registro')"
															disabled="#{entregaVentanillaMasiva._btnEliminarRegistro}">
															<h:graphicImage
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/asc.png" />
															<h:outputText value="Registro" styleClass="label"
																id="RepresentanteRegistro" />
															<h:graphicImage
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/desc.png" />
														</h:commandLink>
													</f:facet>
													<h:outputText value="#{dtgDetalle.registro}"
														styleClass="label" />
												</h:column>
												<h:column sortable="true" headeronclick="return false;"
													onclick="return false;">
													<f:facet name="header">
														<h:commandLink
															onclick="return SortTable('frmGridDetalleRepresentante:dtgDetalleRepresentante', 2, 'Nombre')"
															disabled="#{entregaVentanillaMasiva._btnEliminarRegistro}">
															<h:graphicImage
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/asc.png" />
															<h:outputText value="Nombre" styleClass="label"
																id="RepresentanteNombre" />
															<h:graphicImage
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/desc.png" />
														</h:commandLink>
													</f:facet>
													<h:outputText value="#{dtgDetalle.nombre}"
														styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Orden" styleClass="label" />
													</f:facet>
													<h:outputText value="#{dtgDetalle.numeroOrden}"
														styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Monto" styleClass="label" />
													</f:facet>
													<h:outputText value="$ " styleClass="label" />
													<h:outputText value="#{dtgDetalle.montoCobrar}"
														styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Eliminar Registro" styleClass="label" />
													</f:facet>
													<a4j:commandButton
														action="#{entregaVentanillaMasiva.EliminarRegistroTablaRepresentantes}"
														value="Eliminar" styleClass="botonGrid"
														reRender="pnlIzquierdo"
														disabled="#{entregaVentanillaMasiva._btnEliminarRegistro}">
														<f:setPropertyActionListener value="#{dtgDetalle}"
															target="#{entregaVentanillaMasiva._dtDetalle}" />
													</a4j:commandButton>
												</h:column>
											</h:dataTable>
										</div>
									</a4j:form>
								</t:fieldset>
								<a4j:form>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr align="right">
											<td colspan="2"><h:outputLabel id="lblMontoTotalEM"
													value="#{msg.lblMontoTotalEM}" styleClass="label"
													style="font-weight: bold;" /> <h:outputText
													value="$ #{entregaVentanillaMasiva._montoTotal }"
													styleClass="label" style="font-weight: bold;" /></td>
										</tr>
										<tr align="center">
											<td><a4j:commandButton value="#{msg.btnLimpiarPedidoEM}"
													id="btnLimpiarPedidoEM"
													disabled="#{entregaVentanillaMasiva._btnLimpiarPedido}"
													onclick="if(!confirm('¿Desea limpiar los pedidos?'))return false;"
													action="#{entregaVentanillaMasiva.LimpiarPedidos}"
													reRender="pnlIzquierdo" focus="txtCodigoRepresentanteEM">
												</a4j:commandButton></td>
											<td><a4j:commandButton
													value="#{msg.btnMostrarDetalleEM}" id="btnMostrarDetalleEM"
													disabled="#{entregaVentanillaMasiva._btnMostrarDetalle}"
													action="#{entregaVentanillaMasiva.ObtenerDetalles}"
													oncomplete="document.getElementById('frmDetalles:txtCodigoCajas');"
													reRender="pnlIzquierdo, pnlDerecho">
												</a4j:commandButton></td>
										</tr>
									</table>
								</a4j:form>
							</t:fieldset></td>
						<td valign="top"><t:fieldset
								style="color: Black;vertical-align: top" id="pnlDerecho">
								<a4j:form id="frmDetalles">
									<h:commandButton id="btnInaccesible" value="Valida Código"
										style="display: none;visibility: hidden;"
										onclick="return false;" immediate="false" />
									<t:fieldset legend="#{msg.pnlCajasEM}" id="pnlCajasEM"
										style="color: Black; max-height: 100%; max-width: 100%;">
										<table width="100%" border="0" cellspacing="0" cellpadding="5">
											<tr>
												<td style="width: 90px"><h:outputLabel
														id="lblCodigoCajaEM" value="#{msg.lblCodigoCajaEM}"
														styleClass="label" /></td>
												<td style="width: 170px"><h:inputText
														id="txtCodigoCajas" maxlength="20" styleClass="inputText"
														style="width: 150px; "
														onkeypress="return entregaVentanillaMasivaKeyPress(event, 'Cajas');"
														disabled="#{!entregaVentanillaMasiva._txtCodigoCaja}">
													</h:inputText></td>
												<td style="width: 60px"><h:commandButton
														id="btnValidaCajas" value="Valida Código"
														disabled="#{!entregaVentanillaMasiva._btnValidaCaja}"
														onclick="return validaCodigoLeido('Cajas');"
														immediate="false" /></td>
												<td
													style="width: 30px; text-align: center; vertical-align: middle;">
													<h:graphicImage id="imgCajasValido"
														style="width: 23px; height: 23px; visibility: hidden;"
														url="/img/semaforo/palomita.gif" /> <h:graphicImage
														id="imgCajasInvalido"
														style="width: 23px; height: 23px; visibility: hidden;"
														url="/img/semaforo/tache.gif" />
												</td>
												<td style="width: 150px"><h:outputText value=""
														styleClass="error" id="lblErrorCajas"
														style="text-align: center; font-size: 15px; " /></td>
											</tr>
										</table>
										<div
											style="height: 170px; width: 100%; overflow-y: scroll; border: 1px solid #000;">
											<h:dataTable id="dtgCajas"
												value="#{entregaVentanillaMasiva._listaCajas}"
												var="dtgDetalleCajas" styleClass="gridDatos"
												headerClass="gridDatosEncabezado"
												rendered="#{entregaVentanillaMasiva._tablaCaja}">
												<h:column>
													<f:facet name="header">
														<h:commandLink
															onclick="return SortTable('frmDetalles:dtgCajas', 0, 'Registro')">
															<h:graphicImage
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/asc.png" />
															<h:outputText value="Registro" styleClass="label"
																id="RepresentanteRegistro" />
															<h:graphicImage
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/desc.png" />
														</h:commandLink>
													</f:facet>
													<h:outputText value="#{dtgDetalleCajas.registro}"
														styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:commandLink
															onclick="return SortTable('frmDetalles:dtgCajas', 1, 'Nombre')">
															<h:graphicImage
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/asc.png" />
															<h:outputText value="Nombre" styleClass="label"
																id="RepresentanteNombre" />
															<h:graphicImage
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/desc.png" />
														</h:commandLink>
													</f:facet>
													<h:outputText value="#{dtgDetalleCajas.nombre }"
														styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Código de Barra" styleClass="label" />
													</f:facet>
													<h:outputText value="#{dtgDetalleCajas.codigo }"
														id="codigoBarraCajas" styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Entregar" styleClass="label" />
													</f:facet>
													<h:selectBooleanCheckbox
														value="#{dtgDetalleCajas.escaneado}"
														style="text-align: center;" id="checkBoxEscaneado"
														onclick="validarCheckCaja(this);">
													</h:selectBooleanCheckbox>
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Leído" styleClass="label" />
													</f:facet>
													<h:outputText value="0" id="lblLeidoCajas"
														styleClass="label" />
												</h:column>
											</h:dataTable>
										</div>
										<table width="100%" border="0" cellspacing="0" cellpadding="5">
											<tr>
												<td><h:outputLabel id="lblTotalCajasEM"
														value="#{msg.lblTotalCajasEM}" styleClass="label"
														style="font-weight: bold; font-size: 12px;" /> <h:outputText
														value="#{entregaVentanillaMasiva._totalCajas}"
														styleClass="label"
														style="font-weight: bold; font-size: 12px;"
														id="lbltotalCajas" /></td>
												<td><h:outputLabel id="lblEntregadosCajasEM"
														value="#{msg.lblEntregadosEM}" styleClass="label"
														style="font-weight: bold; font-size: 12px;" /> <h:outputText
														value="0" styleClass="label"
														style="font-weight: bold; font-size: 12px;"
														id="lblEntregadosCajas" /></td>
												<td><h:outputLabel id="lblPendientesCajasEM"
														value="#{msg.lblPendientesEM}" styleClass="label"
														style="font-weight: bold; font-size: 12px;" /> <h:outputText
														value="#{entregaVentanillaMasiva._pendientesCajas}"
														styleClass="label"
														style="font-weight: bold; font-size: 12px;"
														id="lblPendientesCajas" /></td>
											</tr>
										</table>
									</t:fieldset>
									<t:fieldset legend="#{msg.pnlUnitariosEM}" id="pnlUnitariosEM"
										style="color: Black; max-height: 100%; max-width: 100%;">
										<table width="100%" border="0" cellspacing="0" cellpadding="5">
											<tr>
												<td style="width: 90px"><h:outputLabel
														id="lblCodigoUnitarioEM"
														value="#{msg.lblCodigoUnitarioEM}" styleClass="label" />
												</td>
												<td style="width: 170px"><h:inputText
														id="txtCodigoUnitarios" maxlength="20"
														styleClass="inputText" style="width: 150px; "
														onkeypress="return entregaVentanillaMasivaKeyPress(event, 'Unitarios');"
														disabled="#{!entregaVentanillaMasiva._txtCodigoUnitario}">
													</h:inputText></td>
												<td style="width: 60px"><h:commandButton
														id="btnValidaUnitarios" value="Valida Código"
														disabled="#{!entregaVentanillaMasiva._btnValidaUnitario}"
														onclick="return validaCodigoLeido('Unitarios');" />
												</td>
												<td
													style="width: 30px; text-align: center; vertical-align: middle;">
													<h:graphicImage id="imgUnitariosValido"
														style="width: 23px; height: 23px; visibility: hidden;"
														url="/img/semaforo/palomita.gif" /> <h:graphicImage
														id="imgUnitariosInvalido"
														style="width: 23px; height: 23px; visibility: hidden;"
														url="/img/semaforo/tache.gif" />
												</td>
												<td style="width: 150px"><h:outputText value="  "
														styleClass="error" id="lblErrorUnitarios"
														style="text-align: center; font-size: 15px; " /></td>
											</tr>
										</table>
										<div
											style="height: 170px; width: 100%; overflow-y: scroll; border: 1px solid #000;">
											<h:dataTable id="dtgUnitarios"
												value="#{entregaVentanillaMasiva._listaUnitarios }"
												var="dtgDetalleUnitarios" styleClass="gridDatos"
												headerClass="gridDatosEncabezado"
												rendered="#{entregaVentanillaMasiva._tablaUnitario}">
												<h:column width="150px">
													<f:facet name="header">
														<h:commandLink
															onclick="return SortTable('frmDetalles:dtgUnitarios', 0, 'Registro')">
															<h:graphicImage
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/asc.png" />
															<h:outputText value="Registro" styleClass="label"
																id="RepresentanteRegistro" />
															<h:graphicImage
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/desc.png" />
														</h:commandLink>
													</f:facet>
													<h:outputText value="#{dtgDetalleUnitarios.registro}"
														styleClass="label" />
												</h:column>
												<h:column width="150px">
													<f:facet name="header">
														<h:commandLink
															onclick="return SortTable('frmDetalles:dtgUnitarios', 1, 'Nombre')">
															<h:graphicImage
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/asc.png" />
															<h:outputText value="Nombre" styleClass="label"
																id="RepresentanteNombre" />
															<h:graphicImage
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/desc.png" />
														</h:commandLink>
													</f:facet>
													<h:outputText value="#{dtgDetalleUnitarios.nombre}"
														styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Código FSC" styleClass="label" />
													</f:facet>
													<h:outputText value="#{dtgDetalleUnitarios.codigoFSC}"
														id="codigoFSCUnitarios" styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Código EAN13" styleClass="label" />
													</f:facet>
													<h:outputText value="#{dtgDetalleUnitarios.codigoEAN13}"
														id="codigoEAN13Unitarios" styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Tipo" styleClass="label" />
													</f:facet>
													<h:outputText value="#{dtgDetalleUnitarios.tipo}" id="tipo"
														styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Descripción" styleClass="label" />
													</f:facet>
													<h:outputText value="#{dtgDetalleUnitarios.descripcion }"
														styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Facturada" styleClass="label" />
													</f:facet>
													<h:outputText value="#{dtgDetalleUnitarios.cantidad }"
														styleClass="label" id="lblCantidadFacturadaUnitarios" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Entregar" styleClass="label" />
													</f:facet>
													<h:inputText id="txtCantidadEntradaUnitarios"
														value="#{dtgDetalleUnitarios.escaneado}"
														styleClass="inputText" style="width: 30px;" maxlength="3"
														onkeypress="return validaNumeros(event);"
														onblur="validarCajaTexto(this, 'Unitarios')">
													</h:inputText>
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Leído" styleClass="label" />
													</f:facet>
													<h:outputText value="0" id="lblLeidoUnitarios"
														styleClass="label" />
												</h:column>
											</h:dataTable>
										</div>
										<table width="100%" border="0" cellspacing="0" cellpadding="5">
											<tr>
												<td><h:outputLabel id="lblTotalUnitariosEM"
														value="#{msg.lblTotalUnitariosEM}" styleClass="label"
														style="font-weight: bold; font-size: 12px;" /> <h:outputText
														value="#{entregaVentanillaMasiva._totalUnitarios}"
														styleClass="label"
														style="font-weight: bold; font-size: 12px;" /></td>
												<td><h:outputLabel id="lblEntregadosUnitarioEM"
														value="#{msg.lblEntregadosEM}" styleClass="label"
														style="font-weight: bold; font-size: 12px;" /> <h:outputText
														value="0" styleClass="label"
														style="font-weight: bold; font-size: 12px;"
														id="lblEntregadosUnitarios" /></td>
												<td><h:outputLabel id="lblPendientesUnitarioEM"
														value="#{msg.lblPendientesEM}" styleClass="label"
														style="font-weight: bold; font-size: 12px;" /> <h:outputText
														value="#{entregaVentanillaMasiva._pendientesUnitarios}"
														styleClass="label"
														style="font-weight: bold; font-size: 12px;"
														id="lblPendientesUnitarios" /></td>
											</tr>
										</table>
									</t:fieldset>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr align="center">
											<td><a4j:commandButton
													value="#{msg.btnCancelarEntregaEM}"
													id="btnCancelarEntregaEM"
													onclick="if(!confirm('¿Desea cancelar la entrega?'))return false;"
													disabled="#{!entregaVentanillaMasiva._btnCancelarEntrega}"
													action="#{entregaVentanillaMasiva.CancelarEntrega}"
													focus="txtCodigoRepresentanteEM"
													reRender="pnlIzquierdo, pnlDerecho">

												</a4j:commandButton></td>
											<td><a4j:commandButton
													value="#{msg.btnEntregarPedidosEM}"
													id="btnEntregarPedidosEM"
													action="#{entregaVentanillaMasiva.EntregarPedidos}"
													onclick="if(!confirm('¿Desea entregar los pedidos?'))return false;"
													reRender="pnlIzquierdo, pnlDerecho"
													focus="txtCodigoRepresentanteEM"
													disabled="#{!entregaVentanillaMasiva._btnEntregarPedidos}">
												</a4j:commandButton></td>
										</tr>
									</table>
								</a4j:form>
							</t:fieldset></td>
					</tr>
				</table>
				<!-- modalpopup representante inicio -->
				<a4j:form styleClass="forma" id="frmModal"
					binding="#{entregaVentanillaMasiva._frmModal }"
					ignoreDupResponses="true">
					<rich:modalPanel id="modalPanel" autosized="true"
						showWhenRendered="#{entregaVentanillaMasiva._mostrarPanel}">
						<f:facet name="header">
							<h:outputText value="#{msg.tituloModalEM }" />
						</f:facet>
						<f:facet name="controls">
							<span style="cursor: pointer"
								onclick="javascript:Richfaces.hideModalPanel('modalPanel')">
								X </span>
						</f:facet>
						<div style="height: 200px; width: 850px; overflow-y: scroll;">

							<h:dataTable
								value="#{entregaVentanillaMasiva.obtenerRepresentantesPopUp}"
								var="dtgmodal" id="dtgPopUp" styleClass="gridDatos"
								headerClass="gridDatosEncabezado"
								rendered="#{entregaVentanillaMasiva._mostrarPanel}"
								style="height: 85px; width: 850px; ">

								<h:column>
									<f:facet name="header">
										<h:outputText value="Account" styleClass="label" />
									</f:facet>
									<h:outputText value="#{dtgmodal.registro}" styleClass="label" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Nombre" styleClass="label" />
									</f:facet>
									<h:outputText value="#{dtgmodal.nombre}" styleClass="label" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Dirección" styleClass="label" />
									</f:facet>
									<h:outputText value="#{dtgmodal.direccion}" styleClass="label" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Zona" styleClass="label" />
									</f:facet>
									<h:outputText value="#{dtgmodal.zona}" styleClass="label" />
								</h:column>
								<h:column rendered="false">
									<f:facet name="header">
										<h:outputText value="Monto a Cobrar" styleClass="label" />
									</f:facet>
									<h:outputText value="$ " styleClass="label" />
									<h:outputText value="#{dtgmodal.montoCobrar}"
										styleClass="label" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Elegir" styleClass="label" />
									</f:facet>
									<a4j:commandButton value="Agregar"
										action="#{entregaVentanillaMasiva.AsignarRepresentanteSeleccionada }"
										oncomplete="javascript:Richfaces.hideModalPanel('panel')"
										reRender="pnlIzquierdo, frmModal">
										<f:setPropertyActionListener value="#{dtgmodal}"
											target="#{entregaVentanillaMasiva._dtRepresentante}" />
									</a4j:commandButton>
								</h:column>
							</h:dataTable>
						</div>
					</rich:modalPanel>
				</a4j:form>
				<!-- modalpopup fin -->
				<script type="text/javascript">
					document.getElementById("frmCriterioRepresentante:txtCodigoRepresentanteEM").focus();
				</script>
			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>