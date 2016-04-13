<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>GENERA REPORTES</title>
</head>
<body>
	<script type="text/javascript"
		src="#{facesContext.externalContext.requestContextPath}/js/jsCU003.js"></script>
	<f:view>
		<ui:composition template="/Paginas/template.jsp">
			<ui:define name="content">
				<!--  Mensajes de la Vista -->
				<f:loadBundle
					basename="com.datacode.avon_ots_admin.reports.messages" var="msg" />
				<!-- Diseno de Pagina -->

				<a4j:form binding="#{report.form}" id="frmGeneral">
					<h:outputLabel value="#{msg.titulo}" styleClass="tituloPagina"></h:outputLabel>

					<h:commandLink action="#{report.generaReporteMail}" target="_blank"
						rendered="false">
						<h:commandButton value="Generar Reporte" rendered="false" />
					</h:commandLink>
					<table align="center">
					<tr>
							<td><h:outputText value="#{report.mensajeError }"
									style="color: #FF0000;" id="idMensajeError"
									rendered="#{report.mensajeError ne '' }"></h:outputText></td>
						</tr>
					</table>
					<table align="center">
						
						<tr>
							<td width="90%"><rich:panel>
									<f:facet name="header">
										<h:outputText value="Generar Reporte" style="align:center"></h:outputText>
									</f:facet>
									<table>
										<tr>
											<td><h:outputLabel value="#{msg.catLDC}"
													styleClass="label"></h:outputLabel></td>

											<td><h:selectOneMenu id="cbLDC" value="#{report.ldcSel}">
													<f:selectItems value="#{report.listaLDC}" />
												</h:selectOneMenu></td>

										</tr>
										<tr>
											<td><h:outputLabel value="#{msg.catReportes}"
													styleClass="label"></h:outputLabel></td>

											<td><h:selectOneMenu id="cbReportes"
													value="#{report.repSel}"
													valueChangeListener="#{report.cargarFiltrosV}">
													<f:selectItems value="#{report.reportes}" />
													<a4j:support event="onchange"
														reRender="frmGeneral,panelReportePedidosPrestados,panelReporteManifiestoRutaEnCampania,
														panelRepPedidosDejados,panelConsultaRepresentante,panelHistorialPorRepresentante,
														panelHistoricoRepresentantes,panelReporteSalidaCamioneta,panelBoton,panelDestinatario,
														panelReportePremios,panelReporteItemsNoEscaneados" />
												</h:selectOneMenu></td>

										</tr>
									</table>
								</rich:panel></td>

						</tr>

						<tr>
							<td><rich:panel id="panelReporteBuenVecino"
									rendered="#{report.panelReporteBuenVecino }">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte Consulta Buen Vecino"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}"
													onchange="this.form.submit()"
													valueChangeListener="#{report.actualizarCombos}">
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.ruta}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdRuta}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.rutasSeleccione}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="Registro"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:inputText
													value="#{report.filtroRegistro}" maxlength="10"></h:inputText>
											</td>
										</tr>

									</table>
								</rich:panel> <rich:panel id="panelReporteRecoleccionRemitos"
									rendered="#{report.panelReporteRecoleccionDeRemito }">
									<f:facet name="header">
										<h:outputText
											value="Filtros Reporte Recolección y devolución de ajustes (remitos)"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}"
													onchange="this.form.submit()"
													valueChangeListener="#{report.actualizarCombos}">
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.ruta}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdRuta}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.rutasSeleccione}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campanias}" />
												</h:selectOneMenu></td>
										</tr>

									</table>
								</rich:panel> <rich:panel id="panelReporteFaltanteDeCajas"
									rendered="#{report.panelReporteFaltanteDeCajas }">
									<f:facet name="header">
										<h:outputText
											value="Filtros Reporte Faltantes de Cajas, Unitarios y Premios"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}"
													onchange="this.form.submit()"
													valueChangeListener="#{report.actualizarCombos}">
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campanias}" />
												</h:selectOneMenu></td>
										</tr>

									</table>
								</rich:panel> <rich:panel id="panelReporteResumenGeneralZonas"
									rendered="#{report.panelReporteResumenGeneralZonas }">
									<f:facet name="header">
										<h:outputText value="Filtros Resumen General De Zonas"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroCampaniaRep}">
													<f:selectItems value="#{report.campaniasReporte}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.division}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdDivision}">

													<f:selectItems value="#{report.divisiones}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}">
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>


									</table>
								</rich:panel> <rich:panel id="panelReporteItemsNoEntregados"
									rendered="#{report.panelReporteItemsNoEntregados }">
									<f:facet name="header">
										<h:outputText
											value="Filtros Reporte Items No Entregados en Almacén"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}"
													onchange="this.form.submit()"
													valueChangeListener="#{report.actualizarCombos}">
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.ruta}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdRuta}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.rutasSeleccione}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campanias}" />
												</h:selectOneMenu></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelReporteConsultaEstatusOrdenes"
									rendered="#{report.panelReporteConsultaEstatusOrdenes }">
									<f:facet name="header">
										<h:outputText
											value="Filtros Reporte Consulta Estatus Ordenes por Representante"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">


										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroCampaniaRep}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campaniasSinID}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="Registro"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:inputText
													value="#{report.filtroString1 }" maxlength="15">
												</h:inputText></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelReportePedidosPrestados"
									rendered="#{report.panelRepPedidosPrestados }">
									<f:facet name="header">
										<h:outputText
											value="Filtros Reporte Pedidos Entregados a Gerente Zonal"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}"
													id="cmbZonaRepPedidosPrestados"
													onchange="this.form.submit()"
													valueChangeListener="#{report.actualizarCampanias}">
													<f:selectItems value="#{report.zonas}" />
													<a4j:support event="onchange"
														reRender="cmbCampaniasRepPedPrestados" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													id="cmbCampaniasRepPedPrestados"
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campanias}" />
												</h:selectOneMenu></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelReporteManifiestoRutaEnCampania"
									rendered="#{report.panelRepManifiestoRutaEnCampania}">
									<f:facet name="header">
										<h:outputText
											value="Filtros Reporte Manifiesto de Ruta en Campaña"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}"
													onchange="this.form.submit()"
													valueChangeListener="#{report.actualizarCombos}">
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campanias}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.ruta}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdRuta}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.rutas}" />
												</h:selectOneMenu></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelRepPedidosDejados"
									rendered="#{report.panelRepPedidosDejados }">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte Pedidos Dejados"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}"
													onchange="this.form.submit()"
													valueChangeListener="#{report.actualizarCombos}">
													<f:selectItems value="#{report.zonas}" />
													<a4j:support event="onchange" reRender="frmGeneral" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campanias}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.ruta}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdRuta}">
													<f:selectItems value="#{report.rutas}" />
													<a4j:support event="onchange" rendered=""
														reRender="selectSubBodega,selectItemSubBodega" />
												</h:selectOneMenu></td>
										</tr>

										<tr>
											<td align="right"><h:outputLabel
													value="#{msg.subBodega}" styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu id="selectSubBodega"
													value="#{report.filtroSubBodega}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems id="selectItemSubBodega"
														value="#{report.subBodegas}" />
												</h:selectOneMenu></td>
										</tr>
									</table>

								</rich:panel> <rich:panel id="panelConsultaRepresentante"
									rendered="#{report.panelRepConsultaRepresentante }">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte Consulta Representante"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.account}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:inputText
													value="#{report.filtroString1 }" maxlength="15">
												</h:inputText></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelHistorialPorRepresentante"
									rendered="#{report.panelHistorialPorRepresentante}">
									<f:facet name="header">
										<h:outputText
											value="Filtros Reporte Historial Por Representante"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.anio}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroAnio}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.listaAnios}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.listaCampanias}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.account}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:inputText
													value="#{report.filtroString1 }"></h:inputText></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelHistoricoRepresentantes"
									rendered="#{report.panelHistoricoRepresentantes}">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte Historico Representantes"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.anio}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroAnio}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.listaAnios}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.listaCampanias}" />
												</h:selectOneMenu></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelReporteSalidaCamioneta"
									rendered="#{report.panelRepSalidaCamioneta }">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte Salida Camioneta"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}"
													valueChangeListener="#{report.actualizarCombos}">
													<f:selectItems value="#{report.zonas}" />
													<a4j:support event="onchange"
														reRender="frmGeneral,comboRutas,comboCampanias" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu id="comboCampanias"
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campanias}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.ruta}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu id="comboRutas"
													value="#{report.filtroIdRuta}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.rutas}" />
												</h:selectOneMenu></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelRepRecepcionCarga"
									rendered="#{report.panelRepRecepcionCarga }">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte Recepción de Carga"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}">
													<a4j:support event="onchange" reRender="frmGeneral"
														actionListener="#{report.actualizarCampaniasCarga}" />
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.actualizarObservaciones}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campanias}" />
												</h:selectOneMenu></td>
										</tr>

										<tr>
											<td align="right"><h:outputLabel value="Observaciones"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:inputTextarea
													id="txtObservacionesRC"
													value="#{report.observacionesRecepcion}"></h:inputTextarea>
											</td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="pnlListaNARGlobal"
									rendered="#{report.pnlListaNARGlobal }">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte Lista NAR Global"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}"
													onchange="this.form.submit()"
													valueChangeListener="#{report.actualizarCampanias}">
													<a4j:support event="onchange" reRender="frmGeneral" />
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campanias}" />
												</h:selectOneMenu></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelRepDescargaEnrutado"
									rendered="#{report.panelRepDescargaEnrutado }">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte Descarga Enrutado"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.anio}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroAnio}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.listaAnios}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.listaCampanias}" />
												</h:selectOneMenu></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelRepReparto"
									rendered="#{report.panelRepReparto }">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte Reparto"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.anio}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroAnio}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.listaAnios}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.listaCampanias}" />
												</h:selectOneMenu></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="pnlAnalisisDeEfectivo"
									rendered="#{report.panelRepAnalisisEfectivo}">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte Analisis de Efectivo"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}"
													valueChangeListener="#{report.actualizarCombos}">
													<f:selectItems value="#{report.zonas}" />
													<a4j:support event="onchange"
														reRender="cmbCampaniaAnalisisEfectivo" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													id="cmbCampaniaAnalisisEfectivo"
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campanias}" />
												</h:selectOneMenu></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="pnlListaNARTotal"
									rendered="#{report.pnlListaNARTotal }">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte Lista NAR Total"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}"
													onchange="this.form.submit()"
													valueChangeListener="#{report.actualizarCampanias}">
													<a4j:support event="onchange" reRender="frmGeneral" />
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campanias}" />
												</h:selectOneMenu></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelReportePremios"
									rendered="#{report.panelReportePremios}">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte De Premios"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.anio}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroAnio}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.listaAnios}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.listaCampanias}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.account}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:inputText
													value="#{report.filtroString1 }" maxlength="20"></h:inputText></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel
													value="#{msg.descripcionPremio}" styleClass="label"></h:outputLabel></td>
											<td align="left"><h:inputText
													value="#{report.filtroDescripcionPremio }" maxlength="50"></h:inputText></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelReporteResumen"
									rendered="#{report.panelReporteResumen}">
									<f:facet name="header">
										<h:outputText value="Filtros Reporte Resumen"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.anio}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroAnio}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.listaAnios}" />
												</h:selectOneMenu></td>
										</tr>

										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.listaCampanias}" />
												</h:selectOneMenu></td>
										</tr>
									</table>
								</rich:panel> <rich:panel id="panelReporteItemsNoEscaneados"
									rendered="#{report.panelReporteItemsNoEscaneados}">
									<f:facet name="header">
										<h:outputText
											value="Filtros Reporte Items No Escaneados En Reparto"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td align="right"><h:outputLabel value="#{msg.zona}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdZona}"
													onchange="this.form.submit()"
													valueChangeListener="#{report.actualizarCombos}">
													<f:selectItems value="#{report.zonas}" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td align="right"><h:outputLabel value="#{msg.campania}"
													styleClass="label"></h:outputLabel></td>
											<td align="left"><h:selectOneMenu
													value="#{report.filtroIdCampania}">
													<a4j:support event="onchange"
														actionListener="#{report.reseteaValorArchivo}"
														reRender="frmGeneral" />
													<f:selectItems value="#{report.campanias}" />
												</h:selectOneMenu></td>
										</tr>
									</table>
								</rich:panel></td>
						</tr>
						<tr>
							<td><rich:panel id="panelBoton"
									rendered="#{report.panelBotonGenerar }">
									<f:facet name="header">
										<h:outputText value="Formato" style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td><h:selectOneMenu value="#{report.formatoReporte}">
													<f:selectItem itemLabel="PDF" itemValue="PDF" />
													<f:selectItem itemLabel="Excel" itemValue="XLS" />
													<f:selectItem itemLabel="CSV" itemValue="CSV" />
												</h:selectOneMenu></td>
										</tr>
										<tr>
											<td><h:commandLink action="#{report.generarReporte}"
													target="_blank">
													<a4j:commandButton value="Generar Reporte"
														reRender="frmGeneral,idMensajeError,botonEnviar" />
												</h:commandLink> <a4j:commandButton action="#{report.generarReporte}"
													rendered="false" value="Probar Reporte" disabled="false"
													id="botonP">
												</a4j:commandButton></td>
										</tr>
									</table>
								</rich:panel></td>
						</tr>
						<tr>
							<td><rich:panel id="panelDestinatario"
									rendered="#{report.panelDestinatarios }">
									<f:facet name="header">
										<h:outputText value="Destinatarios Reporte"
											style="align:center"></h:outputText>
									</f:facet>
									<table width="100%">
										<tr>
											<td><rich:dataTable align="center" var="destinatario"
													width="90%" cellspacing="0" value="#{report.destinatarios}"
													rendered="true" styleClass="gridDatos"
													onRowMouseOver="this.style.backgroundColor='#{a4jSkin.additionalBackgroundColor}'"
													columnClasses="Centrar"
													onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'"
													id="tablaPerDec">
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Tipo Destinatario" />
														</f:facet>
														<div align="center">
															<h:outputText value="#{destinatario.tipoDestinatario }"></h:outputText>
														</div>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Mail" />
														</f:facet>
														<div align="center">
															<h:outputText value="#{destinatario.mail }"></h:outputText>
														</div>
													</rich:column>
												</rich:dataTable></td>
										</tr>
										<tr>
											<td><a4j:commandButton action="#{report.enviarReporte}"
													reRender="frmGeneral,idMensajeError" value="Enviar Reporte"
													id="botonEnviar">
												</a4j:commandButton></td>
										</tr>
									</table>
								</rich:panel></td>
						</tr>
					</table>
				</a4j:form>
			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>