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
<title>Entrega en Ventanilla</title>
</head>
<body >
	<f:view>
		<ui:composition template="/Paginas/template.jsp">
			<ui:define name="content">
				<!-- Incluye archivo de properties -->
				<f:loadBundle
					basename="com.datacode.avon_ots_admin.cu002.messagesCU002"
					var="msg" />
				<!-- Diseno de pagina -->
				<h:panelGrid columns="2" id="pnlCriterios"
					style="vertical-align: top">

					<a4j:form id="frmPrincipal" styleClass="forma"
						binding="#{entregaVentanilla.frmPrincipal}">
						<h:outputLabel value="#{msg.tituloEntrega}"
							styleClass="tituloPagina"></h:outputLabel>

						<rich:panel>
							<t:fieldset legend="#{msg.fiel1}" id="PnlTituloCriteriosCons"
								style="color: Black">
								<h:panelGrid columns="5">
									<h:outputLabel id="LblAccount" value="#{msg.LblRepre}"
										styleClass="label" />

									<h:inputText id="txtAccount" styleClass="inputText" maxlength="30"
										value="#{entregaVentanilla.registroRepre}"
										onkeypress="return quitarEnterEntregaVentanilla(event);"
											onkeyup="mandarEnterEntregaVentanilla(event);">
										<a4j:support event="onkeyup"
										    focus="txtAccount"
											reRender="frmPrincipal,frmOrdenes"
											ignoreDupResponses="true" requestDelay="1000"/>
									</h:inputText>

									<h:outputLabel id="LblNombre" value="#{msg.LblNombre}"
										styleClass="label" />
									<h:inputText id="TxtNombre" styleClass="inputText"
										value="#{entregaVentanilla.nombreRepre}" />

									<a4j:commandButton value="Consultar" id="btnConsultar"
										action="#{entregaVentanilla.mostrarGridRepresentante}"
										oncomplete="if (#{entregaVentanilla.mostrarPanel}) javascript:Richfaces.showModalPanel('panel');"
										reRender="tabla,pnlCriterios,panel,btnExportar,btnConsultar,GrdOrdenes,frmOrdenes"
										disabled="#{!entregaVentanilla.mapaAccion['CONSULTAR'] || !entregaVentanilla.btnConsulta}">
									</a4j:commandButton>
								</h:panelGrid>

								<h:panelGrid columns="2">
									<h:outputLabel id="LblDireccion" value="#{msg.LblDir}"
										styleClass="label" />
									<h:inputText id="TxtDireccion"
										value="#{entregaVentanilla.domicilio}" styleClass="inputText"
										style="width: 446px; " readonly="true" />
								</h:panelGrid>

								<h:panelGrid columns="4">
									<h:outputLabel id="LblZonas" value="#{msg.zona}"
										styleClass="label" />
									<h:inputText id="TxtZonas" styleClass="inputText"
										value="#{entregaVentanilla.zona}" readonly="true" />

									<h:panelGrid columns="2">
										<a4j:commandButton value="Limpiar" id="btnLimpiar"
											focus="txtAccount"
											onclick="posicionarCursor()"
											action="#{entregaVentanilla.limpiar}"
											reRender="frmPrincipal,frmOrdenes,frmCod,frmOrdenPremio,frmPagoCampaniaAnterior">
										</a4j:commandButton>

										<h:outputText value="#{entregaVentanilla.mensajeError}"
											id="LblMensaje" styleClass="error" />
									</h:panelGrid>
								</h:panelGrid>
							</t:fieldset>
						</rich:panel>
					</a4j:form>

					<t:fieldset id="PnlDetalleEntrega" legend="#{msg.fiel3}"
						style="color: black">
						<a4j:form id="frmCod">

							<h:panelGrid columns="4">
								<h:outputLabel id="LblCodigoLeido" value="#{msg.codigo}"
									styleClass="label" />
								<h:inputText id="TxtCodigoLeido"
									value="#{entregaVentanilla.codigoBarras}"
									styleClass="inputText" style="width: 98px; " 
									onkeypress="return quitarEnterCodigoLeido(event);"
												onkeyup="mandarEnterCodigoLeido(event);">
												<a4j:support event="onkeyup" focus="TxtCodigoLeido"
											reRender="frmPrincipal,frmOrdenes,frmCod"
											ignoreDupResponses="true" requestDelay="1000"/>
								</h:inputText>
								<h:commandButton id="btnValida" value="valida codigo"
									action="#{entregaVentanilla.ItemsEscaneados}"
									disabled="#{!entregaVentanilla.btnEscan}" onclick="resaltarRowTabla();" />
								<h:graphicImage id="ImgSemaforo"
									value="#{entregaVentanilla.rutaImagen}" />
							</h:panelGrid>

						</a4j:form>

						<a4j:form id="frmOrdenPremio">
							<t:fieldset id="PnlTituloOrdenOPremio" legend="#{msg.fiel4}"
								style="color: black">
								<h:panelGrid columns="4">
									<h:outputLabel id="LblCampania" value="#{msg.campania}"
										styleClass="label" />
									<h:inputText id="TxtCampania" styleClass="inputText"
										value="#{entregaVentanilla.campania}" readonly="true"
										style="width: 125px; " />

									<h:outputText id="LblOrden" value="#{msg.LblOrden}"
										styleClass="label" />
									<h:inputText id="TxtOrden" styleClass="inputText"
										value="#{entregaVentanilla.cveOrden}" readonly="true"
										style="width: 125px; " />
								</h:panelGrid>
								<h:panelGrid columns="2">
									<h:outputLabel id="LblStatusOrden" value="#{msg.LblEstOrd}"
										styleClass="label" />
									<h:inputText id="TxtStatusOrden" styleClass="inputText"
										value="#{entregaVentanilla.statusOrden}" readonly="true"
										style="width: 285px; " />
								</h:panelGrid>
								<div style="height: 90px; width: 100%; overflow-y: scroll;">
									<h:panelGrid>
										<h:dataTable id="GrdDetalleOrden"
											value="#{entregaVentanilla.llenaDetalleOrden}" var="ordDet"
											styleClass="gridDatos" headerClass="gridDatosEncabezado"
											rendered="#{!entregaVentanilla.gridOrdDet}">
											<h:column>
												<f:facet name="header">
													<h:outputText value="Código" styleClass="label" />
												</f:facet>
												<h:outputText value="#{ordDet.codigo_barras}"
													styleClass="label" />
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:outputText value="Código FSC" styleClass="label" />
												</f:facet>
												<h:outputText value="#{ordDet.codigoFSC}"
													styleClass="label" />
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:outputText value="Código EAN13" styleClass="label" />
												</f:facet>
												<h:outputText value="#{ordDet.codigoEAN13}"
													styleClass="label" />
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:outputText value="Típo" styleClass="label" />
												</f:facet>
												<h:outputText value="#{ordDet.tipo_item}" styleClass="label" />
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:outputText value="Estatus" styleClass="label" />
												</f:facet>
												<h:outputText value="#{ordDet.estatus_item}"
													styleClass="label" />
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:outputText value="Cantidad" styleClass="label" />
												</f:facet>
												<h:outputText value="#{ordDet.total_item}"
													styleClass="label" />
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:outputText value="Escaneado" styleClass="label" />
												</f:facet>
												<h:outputText value="#{ordDet.escaneados}"
													styleClass="label" />
											</h:column>
										</h:dataTable>
									</h:panelGrid>
								</div>
							</t:fieldset>
						</a4j:form>
					</t:fieldset>

					<a4j:form id="frmOrdenes">
						<t:fieldset id="PnlTituloOrdenes" legend="#{msg.fiel2}"
							style="color: black">
							<div style="height: 185px; width: 100%; overflow-y: scroll;">

								<a4j:commandButton value="#{msg.BtnExporta}"
									oncomplete="javascript:Richfaces.showModalPanel('pnlExprt');"
									reRender="tablaExprt"
									disabled="#{!entregaVentanilla.btnExporta}" 
									id="btnExportar"/>

								<h:dataTable id="GrdOrdenes"
									value="#{entregaVentanilla.repreEntrega}" var="ord"
									styleClass="gridDatos" headerClass="gridDatosEncabezado"
									rendered="#{!entregaVentanilla.tableRepre}">
									<h:column>
										<f:facet name="header">
											<h:outputText value="Campaña" styleClass="label" />
										</f:facet>
										<h:outputText value="#{ord.campania}" styleClass="label" />
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Orden" styleClass="label" />
										</f:facet>
										<h:outputText value="#{ord.orden}" styleClass="label" />
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Cajas" styleClass="label" />
										</f:facet>
										<h:outputText value="#{ord.total_cajas}" styleClass="label" />
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Unitarios" styleClass="label" />
										</f:facet>
										<h:outputText value="#{ord.total_unitarios}"
											styleClass="label" />
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Premios" styleClass="label" />
										</f:facet>
										<h:outputText value="#{ord.total_premios}" styleClass="label" />
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Estatus de reparto" styleClass="label" />
										</f:facet>
										<h:outputText value="#{ord.estatus_reparto}"
											styleClass="label" />
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Causa devolución" styleClass="label" />
										</f:facet>
										<h:outputText value="#{ord.causa_devolucion}"
											styleClass="label" />
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Monto" styleClass="label" />
										</f:facet>
										<h:outputText value="#{ord.monto_previo}"
											styleClass="label" />
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Ver" styleClass="label" />
										</f:facet>
										<a4j:commandButton action="#{entregaVentanilla.LlenaValores }"
											value="Ver" styleClass="botonGrid" 
											reRender="frmPrincipal,frmOrdenes,frmCod,frmOrdenPremio,frmPagoCampaniaAnterior">
											<f:setPropertyActionListener value="#{ord}"
												target="#{entregaVentanilla.entrega}" />
										</a4j:commandButton>
									</h:column>
								</h:dataTable>
							</div>
						</t:fieldset>
					</a4j:form>

					<a4j:form id="frmPagoCampaniaAnterior">
						<t:fieldset id="PnlTituloPago" legend="#{msg.fiel5}"
							style="color: black">
							<h:panelGrid columns="4">
								<h:outputLabel id="LblMontoaCobrar" value="#{msg.LblMontoCobr}"
									styleClass="label" />
								<h:inputText id="TxtMontoaCobrar" styleClass="inputText"
									value="#{entregaVentanilla.MONTO }" readonly="true" />

								<h:outputLabel id="LblRemanente" value="#{msg.LblRemanente}"
									styleClass="label" />
								<h:inputText id="TxtRemanente"
									value="#{entregaVentanilla.montoRemanenteS }"
									styleClass="inputText" readonly="true" />
							</h:panelGrid>
							<h:panelGrid columns="2">
								<h:panelGrid columns="2">
									<h:outputLabel id="LblTipoPago" value="#{msg.LblTipo}"
										styleClass="label" />
									<h:selectOneMenu id="CbxTipoPago"
										value="#{entregaVentanilla.idTipoPago}"
										onchange="this.form.submit()"
										valueChangeListener="#{entregaVentanilla.validaTipoPago}"
										immediate="true">
										<f:selectItems value="#{entregaVentanilla.tipoPago}" />
									</h:selectOneMenu>

									<h:outputLabel id="LblBanco" value="#{msg.LblBanco}"
										styleClass="label" />
									<h:selectOneMenu id="CbxBanco"
										value="#{entregaVentanilla.idBanco}"
										onchange="this.form.submit()" immediate="true"
										disabled="#{!entregaVentanilla.cmbBanco}"
										valueChangeListener="#{entregaVentanilla.getNombreBanco }">
										<f:selectItems value="#{entregaVentanilla.catBancos}" />
									</h:selectOneMenu>

									<h:outputLabel id="LblQuienRecibe" value="Quien Recibe"
										styleClass="label" />
									<h:selectOneMenu id="CbxQRecibe"
										value="#{entregaVentanilla.idPersonaRecibe}"
										valueChangeListener="#{entregaVentanilla.validaPersonaRecibe }"
										onchange="this.form.submit()" immediate="true">
										<f:selectItems value="#{entregaVentanilla.catPersona}" />
									</h:selectOneMenu>
									<h:outputLabel id="LblOtros" value="Otros:" styleClass="label"
										rendered="#{entregaVentanilla.lblOtros}" />
									<h:inputText id="TxtOtros" styleClass="inputText"
										rendered="#{entregaVentanilla.txtOtros}"
										value="#{entregaVentanilla.txtOtroRecibe}" />
									<h:outputLabel id="lblComen" value="Comentarios: "
										styleClass="label" />
									<h:inputText id="TxtComen" styleClass="inputText"
										value="#{entregaVentanilla.txtComentarios }" />
								</h:panelGrid>

								<h:panelGrid>
									<t:fieldset legend="#{msg.fiel6}">
										<h:inputText id="TxtFolioCobranza" styleClass="inputText"
											disabled="#{!entregaVentanilla.txtFolioCob}"
											value="#{entregaVentanilla.txtFolioCobranza}" />
									</t:fieldset>
									
									
								</h:panelGrid>

							</h:panelGrid>
							<h:panelGrid columns="4">
								<h:outputLabel id="LblFechaPago" value="#{msg.LblFechaPag}"
									styleClass="label" />
								<h:inputText id="TxtPago" styleClass="tcal"
									value="#{entregaVentanilla.fechaPago }" />
								<h:commandButton id="BtnAgregar" value="#{msg.BtnAcept}"
									action="#{entregaVentanilla.AgregaPagos }"
									disabled="#{!entregaVentanilla.btnPago }" 
									oncomplete="limpiaCampo('frmPagoCampaniaAnterior:TxtMontoPago')"/>
								<a4j:commandButton id="BtnValidaMontos" value="#{msg.BtnGuarda}"
									disabled="#{!entregaVentanilla.btnEntrega }" 
									reRender="frmPrincipal,frmOrdenes,frmCod,frmOrdenPremio,frmPagoCampaniaAnterior"
									onclick="return validaMontoCapturado();"/>
								

								<h:outputLabel id="LblMontoPago" value="#{msg.LblMontoPag}"
									styleClass="label" />
								<h:inputText id="TxtMontoPago" styleClass="inputText"
									value="#{entregaVentanilla.montoDePago }" 
									onkeypress="return numeroDecimal(event)"
									onblur="return limpiaNumeroDecimal('frmPagoCampaniaAnterior:TxtMontoPago')"/>
									
								<a4j:commandButton id="BtnGuardar" value="#{msg.BtnGuarda}"
									action="#{entregaVentanilla.GuardaEntrega }"
									disabled="#{!entregaVentanilla.btnEntrega }" 
									reRender="frmPrincipal,frmOrdenes,frmCod,frmOrdenPremio,frmPagoCampaniaAnterior"
									style="display:none;"/>
									
							</h:panelGrid>
							<div style="height: 90px; width: 100%; overflow-y: scroll;">
								<h:panelGrid columns="1">
									<h:dataTable id="GrdPagos"
										value="#{entregaVentanilla.lstPagos }" var="detPag"
										styleClass="gridDatos" headerClass="gridDatosEncabezado"
										rendered="#{!entregaVentanilla.gridPagDet }">
										<h:column width="100px">
											<f:facet name="header">
												<h:outputText value="Tipo de Pago" styleClass="label" />
											</f:facet>
											<h:outputText value="#{detPag.tipo_pago }" styleClass="label" />
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Descripción" styleClass="label" />
											</f:facet>
											<h:outputText value="#{detPag.banco }" styleClass="label" />
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Folios" styleClass="label" />
											</f:facet>
											<h:outputText value="#{detPag.folios }" styleClass="label" />
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Fecha" styleClass="label" />
											</f:facet>
											<h:outputText value="#{detPag.fecha_pago }"
												styleClass="label" />
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Monto" styleClass="label" />
											</f:facet>
											<h:outputText value="$ " styleClass="label" />
											<h:outputText value="#{detPag.monto_pagado }"
												styleClass="label" />
										</h:column>
										<h:column>
											<f:facet name="header">
												<h:outputText value="Acción" styleClass="label" />
											</f:facet>
											<h:commandButton action="#{entregaVentanilla.eliminarPago}"
												value="#{msg.BtnQuit}" styleClass="botonGrid"
												onclick="return confirmaEliminar();">
												<f:setPropertyActionListener
													target="#{entregaVentanilla.pagoActual}" value="#{detPag}" />
											</h:commandButton>
										</h:column>
									</h:dataTable>

								</h:panelGrid>
							</div>
						</t:fieldset>
					</a4j:form>

				</h:panelGrid>

				<!-- modalpopup representante inicio -->
				<a4j:form styleClass="forma" id="frmModal"
					binding="#{entregaVentanilla.frmModal }">
					<rich:modalPanel id="panel" autosized="true"
						showWhenRendered="#{entregaVentanilla.mostrarPanel}">
						<f:facet name="header">
							<h:outputText value="#{msg.TitleModal }" />
						</f:facet>
						<f:facet name="controls">
							<span style="cursor: pointer"
								onclick="javascript:Richfaces.hideModalPanel('panel')"> X
							</span>
						</f:facet>
						<div style="height: 185px; width: 866px; overflow-y: scroll;">

							<h:dataTable value="#{entregaVentanilla.llenaRepresentantes}"
								var="act" id="tabla" styleClass="gridDatos"
								headerClass="gridDatosEncabezado"
								rendered="#{entregaVentanilla.mostrarPanel}"
								style="height: 85px; width: 866px; ">

								<h:column>
									<f:facet name="header">
										<h:outputText value="Account" styleClass="label" />
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
									<h:outputText value="#{act.zona}" styleClass="label" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Monto a Cobrar" styleClass="label" />
									</f:facet>
									<h:outputText value="$ " styleClass="label" />
									<h:outputText value="#{act.monto_previo}" styleClass="label" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Elegir" styleClass="label" />
									</f:facet>
									<a4j:commandButton value="Ver"
										action="#{entregaVentanilla.AsignaRepreSeleccionada }"
										oncomplete="javascript:Richfaces.hideModalPanel('panel')"
										reRender="pnlCriterios">
										<f:setPropertyActionListener value="#{act}"
											target="#{entregaVentanilla.representante}" />
									</a4j:commandButton>

								</h:column>
							</h:dataTable>
						</div>
					</rich:modalPanel>
				</a4j:form>
				<!-- modalpopup fin -->

				<!-- modalpopup exportar ordenes -->
				<a4j:form id="frmExporta" styleClass="forma"
					binding="#{entregaVentanilla.frmExportar }">
					<rich:modalPanel id="pnlExprt" autosized="true"
						style="height: 485px; width: 930px;">
						<f:facet name="header">
							<h:outputText value="EXPORTAR" />
						</f:facet>
						<f:facet name="controls">
							<span style="cursor: pointer"
								onclick="javascript:Richfaces.hideModalPanel('pnlExprt')">
								X </span>
						</f:facet>

						<h:outputText id="lblExpt" value="Exportar a: " />
						<h:outputText value="    " />
						<h:commandLink value="PDF"
							action="#{entregaVentanilla.ExportaPdf}" target="_blank" />
						<h:outputText value="    " />
						<h:commandLink value="EXCEL"
							action="#{entregaVentanilla.ExportaExcel}" target="_blank" />
						<h:outputText value="    " />
						<h:commandLink value="CSV"
							action="#{entregaVentanilla.ExportaCsv}" target="_blank" />

						<div id="divOrdenes"
							style="height: 200px; width: 910px; overflow-y: scroll;">
							<rich:dataTable value="#{entregaVentanilla.repreEntrega}"
								var="exp" id="tablaExprt" styleClass="gridDatos"
								headerClass="gridDatosEncabezado"
								style="height: 85px; width: 866px; ">
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Campaña" styleClass="label" />
									</f:facet>
									<h:outputText value="#{exp.campania}" styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Orden" styleClass="label" />
									</f:facet>
									<h:outputText value="#{exp.orden}" styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Cajas" styleClass="label" />
									</f:facet>
									<h:outputText value="#{exp.total_cajas}" styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Unitarios" styleClass="label" />
									</f:facet>
									<h:outputText value="#{exp.total_unitarios}" styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Premios" styleClass="label" />
									</f:facet>
									<h:outputText value="#{exp.total_premios}" styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Estatus de reparto" styleClass="label" />
									</f:facet>
									<h:outputText value="#{exp.estatus_reparto}" styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Causa devolución" styleClass="label" />
									</f:facet>
									<h:outputText value="#{exp.causa_devolucion}"
										styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Ver" styleClass="label" />
									</f:facet>
									<a4j:commandButton action="#{entregaVentanilla.LlenaValores }"
										value="Detalle" styleClass="botonGrid" reRender="pnlExprt"
										oncomplete="javascript:Richfaces.showModalPanel('pnlExprt');">
										<f:setPropertyActionListener value="#{exp}"
											target="#{entregaVentanilla.entrega}" />
									</a4j:commandButton>
								</rich:column>
							</rich:dataTable>
						</div>
						<div id="divDetalle"
							style="height: 200px; width: 910px; overflow-y: scroll;">
							<rich:dataTable id="GrdDetalleOrden"
								value="#{entregaVentanilla.llenaDetalleOrden }" var="expDet"
								styleClass="gridDatos" headerClass="gridDatosEncabezado"
								rendered="#{!entregaVentanilla.gridOrdDet }">
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Código" styleClass="label" />
									</f:facet>
									<h:outputText value="#{expDet.codigo_barras}"
										styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Código FSC" styleClass="label" />
									</f:facet>
									<h:outputText value="#{expDet.codigoFSC}"
										styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Código EAN13" styleClass="label" />
									</f:facet>
									<h:outputText value="#{expDet.codigoEAN13}"
										styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Típo" styleClass="label" />
									</f:facet>
									<h:outputText value="#{expDet.tipo_item}" styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Estatus" styleClass="label" />
									</f:facet>
									<h:outputText value="#{expDet.estatus_item}"
										styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Cantidad" styleClass="label" />
									</f:facet>
									<h:outputText value="#{expDet.total_item}" styleClass="label" />
								</rich:column>
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Escaneado" styleClass="label" />
									</f:facet>
									<h:outputText value="#{expDet.escaneados}" styleClass="label" />
								</rich:column>
							</rich:dataTable>
						</div>
					</rich:modalPanel>
				</a4j:form>
				<!-- modalpopup fin -->
				<script type="text/javascript">
					txtCodigo = document
							.getElementById("frmCod:TxtCodigoLeido");
					txtCodigo.focus();
				</script>
			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>