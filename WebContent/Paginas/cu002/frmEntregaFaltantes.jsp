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
<title>Registro de Entrega y Devoluci&oacute;n de &Oacute;rdenes</title>
</head>
<body>
	<f:view>
		<ui:composition template="/Paginas/template.jsp">
			<ui:define name="content">
				<a4j:form id="frmEntregaFaltantes">
				<f:loadBundle basename="com.datacode.avon_ots_admin.cu002.messagesCU002" var="msg" />
				<h:outputLabel value="#{msg.entregaFaltantes_titulo}" styleClass="tituloPagina"></h:outputLabel>
				<table width="100%">
					<tr>
						<td colspan="2" align="center">
							<h:outputLabel id="lblError" value="#{entregaFaltantes.mensaje}" styleClass="error"/>
							<h:outputText id="lblErrorCodigos" value="" styleClass="error"/>
						</td>
					</tr>
					<tr>
						<td valign="top" width="50%">
							<t:fieldset style="color: Black; vertical-align: top" id="pnlIzquierdo">
								<t:fieldset legend="#{msg.entregaFaltantes_consultaOrdenes}" id="pnlConsultaOrdenes"
									style="color: Black; max-height: 100%; max-width: 100%; vertical-align: top">
										<table width="100%" border="0" cellspacing="3" cellpadding="0" >
											<tr>
												<td align="left">
													<h:outputLabel id="lblRegistro" value="#{msg.entregaFaltantes_registro}" styleClass="label"/>
												</td>
												<td align="left">
													<h:inputText id="tfRegistro" styleClass="inputText" maxlength="9" style="width: 100%;"
														value="#{entregaFaltantes.registro}"
														onkeypress="return entregaFaltantesKeyPress(event, 'Consultar');"
														onchange="entregaFaltantesOnChangeAccount();"
														disabled="#{ not entregaFaltantes.panelFormularioActivo}">
														<a4j:support event="onkeypress"
														    focus="tfRegistro"
															reRender="frmEntregaFaltantes,pnlDetalle,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlIzquierdo,pnlConsultaOrdenes,outputPanelDerecho,modPanRepLiqRepWeb,pnlCajas,tblCajas,pnlPremiosUnitarios,tblPremiosUnitarios"
															ignoreDupResponses="true" requestDelay="1000"/>
													</h:inputText>
												</td>
												<td align="left">
													&nbsp;&nbsp;<h:outputLabel id="lblNombre" value="#{msg.entregaFaltantes_nombre}" styleClass="label"/>
												</td>
												<td align="left">
													<h:inputText id="tfNombre" styleClass="inputText" style="width: 100%"
														value="#{entregaFaltantes.nombre}" maxlength="150"
														disabled="#{ not entregaFaltantes.panelFormularioActivo}"
														onchange="entregaFaltantesOnChangeNombre();"
														onkeypress="entregaFaltantesOnChangeNombre();"/>
												</td>
											</tr>
											<tr>
												<td colspan="4" align="center">
													<a4j:commandButton value="#{msg.entregaFaltantes_consultar}" id="btnConsultar"
														action="#{entregaFaltantes.consultar}"
														oncomplete="if (#{entregaFaltantes.mostrarGridConsulta}) javascript:Richfaces.showModalPanel('modPanRepLiqRepWeb');
															document.getElementById('frmEntregaFaltantes:tfRegistro').focus();"
														reRender="frmEntregaFaltantes,pnlDetalle,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlIzquierdo,pnlConsultaOrdenes,outputPanelDerecho,modPanRepLiqRepWeb,pnlCajas,tblCajas,pnlPremiosUnitarios,tblPremiosUnitarios"
														disabled="#{ not entregaFaltantes.panelFormularioActivo}">											
													</a4j:commandButton>
												</td>
											</tr>
										</table>									
								</t:fieldset>
								<br></br>
								<t:fieldset legend="#{msg.entregaFaltantes_ordenes}" id="pnlOrdenes"
									style="color: Black; max-height: 100%; max-width: 100%; vertical-align: top">
									<table width="100%" border="0" cellspacing="3" cellpadding="0" >
										<tr>
											<td width="100%">
												<div style="height: 250px; width: 100%; overflow-y: scroll; border: 1px solid #000;">
												<h:dataTable id="tblOrdenes"
													value="#{entregaFaltantes.listaOrdenes}" var="orden"
													styleClass="gridDatos" headerClass="gridDatosEncabezado"
													rendered="#{entregaFaltantes.panelOrdenesActivo}">
													<h:column>
														<f:facet name="header">
															<h:outputText value="Zona" styleClass="label"/>
														</f:facet>
														<h:outputText value="#{orden.descripcionZona}" styleClass="label"/>
													</h:column>
													<h:column>
														<f:facet name="header">
															<h:outputText value="Campaña" styleClass="label"/>
														</f:facet>
														<h:outputText value="#{orden.descripcionCampania}" styleClass="label"/>
													</h:column>
													<h:column>
														<f:facet name="header">
															<h:outputText value="Clave Orden" styleClass="label"/>
														</f:facet>
														<h:outputText value="#{orden.claveOrden}" styleClass="label"/>
													</h:column>
													<h:column>
														<f:facet name="header">
															<h:outputText value="Nombre" styleClass="label"/>
														</f:facet>
														<h:outputText value="#{orden.nombreRepresentante}" styleClass="label"/>
													</h:column>
													<h:column>
														<f:facet name="header">
															<h:outputText value="Detalle" styleClass="label"/>
														</f:facet>
														<a4j:commandButton action="#{entregaFaltantes.mostrarDetalleOrden}"
															value="Entrega" styleClass="botonGrid" 
															reRender="frmEntregaFaltantes,pnlDetalle,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlIzquierdo,pnlConsultaOrdenes,outputPanelDerecho,modPanRepLiqRepWeb,pnlCajas,tblCajas,pnlPremiosUnitarios,tblPremiosUnitarios"
															disabled="#{entregaFaltantes.panelDetalleActivo}"
															oncomplete="borrarPalomitas();">
															<f:setPropertyActionListener value="#{orden}"
																target="#{entregaFaltantes.ordenSeleccionada}" />
														</a4j:commandButton>
													</h:column>
												</h:dataTable>
												</div>
											</td>
											<tr>
												<td width="100%">
													<a4j:commandButton value="#{msg.entregaFaltantes_limpiarOrdenes}" id="btnLimpiar"
														action="#{entregaFaltantes.limpiarPanelOrdenes}"
														reRender="frmEntregaFaltantes,pnlDetalle,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlIzquierdo,pnlConsultaOrdenes,outputPanelDerecho,modPanRepLiqRepWeb,pnlCajas,tblCajas,pnlPremiosUnitarios,tblPremiosUnitarios"
														disabled="#{entregaFaltantes.panelDetalleActivo}"
														oncomplete="borrarPalomitas();">											
													</a4j:commandButton>
												</td>
											</tr>
											<tr>
												<td width="100%">
													&nbsp;&nbsp;
												</td>
											</tr>
										</tr>
									</table>
								</t:fieldset>
							</t:fieldset>
						</td>
						<td valign="top" width="50%">
							<rich:panel rendered="#{entregaFaltantes.panelDetalleActivo}" id="pnlDetalle">
							<h:inputHidden id="cantidadCajas" value="#{entregaFaltantes.cantidadCajas}"></h:inputHidden>
							<h:inputHidden id="cantidadPremios" value="#{entregaFaltantes.cantidadPremios}"></h:inputHidden>
							<a4j:outputPanel id="outputPanelDerecho" ajaxRendered="true">
								<t:fieldset legend="#{msg.entregaFaltantes_cajas}" id="pnlCajas"
									style="color: Black; max-height: 100%; max-width: 100%; vertical-align: top">
									<table>
										<tr>
											<td align="left">
												<h:outputLabel value="#{msg.entregaFaltantes_codigoBarras}" styleClass="label"/>
											</td>
											<td align="left">
												<h:inputText id="tfCodigoBarras" styleClass="inputText" style="width: 100%;"
													onchange="entFalCodigoBarras();">
												</h:inputText>
											</td>
										</tr>
										<tr>
											<td align="left">
												<a4j:commandButton value="#{msg.entregaFaltantes_validaCodigo}" onclick="entFalCodigoBarras();">
												</a4j:commandButton>
											</td>
											<td align="right">
												<h:graphicImage id="codCorrectoCaja" 
													style="width: 23px; height: 23px; visibility: hidden;"
													url="/img/semaforo/palomita.gif" />
												<h:graphicImage id="codIncorrectoCaja" 
													style="width: 23px; height: 23px; visibility: hidden;"
													url="/img/semaforo/tache.gif" />
											</td>
										</tr>
									</table>
									<table width="100%" border="0" cellspacing="3" cellpadding="0" >
										<tr>
											<td width="100%">
												<div style="height: 125px; width: 100%; overflow-y: scroll; border: 1px solid #000;">
												<h:dataTable id="tblCajas"
													value="#{entregaFaltantes.listaCajas}" var="caja"
													styleClass="gridDatos" headerClass="gridDatosEncabezado"
													rendered="#{entregaFaltantes.panelCajasActivo}">
													<h:column>
														<f:facet name="header">
															<h:outputText value="Código de Barras" styleClass="label"/>
														</f:facet>
														<h:outputText value="#{caja.codigoBarras}" styleClass="label"/>
													</h:column>
													<h:column>
														<f:facet name="header">
															<h:outputText value="Descripción" styleClass="label"/>
														</f:facet>
														<h:outputText value="#{caja.descripcionItem}" styleClass="label"/>
													</h:column>
													<h:column>
														<f:facet name="header">
															<h:outputText value="Entregar" styleClass="label"/>
														</f:facet>
														<h:selectBooleanCheckbox id="caja" value="#{caja.seleccionado}">
														</h:selectBooleanCheckbox>
														<h:inputHidden id="codigoBarrasCaja" value="#{caja.codigoBarras}"></h:inputHidden>
													</h:column>
												</h:dataTable>
												</div>
											</td>
										</tr>
									</table>
								</t:fieldset>
								<br></br>
								<t:fieldset legend="#{msg.entregaFaltantes_premiosUnitarios}" id="pnlPremiosUnitarios"
									style="color: Black; max-height: 100%; max-width: 100%; vertical-align: top">
									<table>
										<tr>
											<td align="left">
												<h:outputLabel value="#{msg.entregaFaltantes_codigo}" styleClass="label"/>
											</td>
											<td align="left">
												<h:inputText id="tfCodigo" styleClass="inputText" style="width: 100%;" onchange="entFalCodigo();">
												</h:inputText>
											</td>
										</tr>
										<tr>
											<td align="left">
												<a4j:commandButton id="btnValidarCodigoPremio" value="#{msg.entregaFaltantes_validaCodigo}" onclick="entFalCodigo();">
												</a4j:commandButton>
											</td>
											<td align="right">
												<h:graphicImage id="codCorrectoPremio" 
													style="width: 23px; height: 23px; visibility: hidden;"
													url="/img/semaforo/palomita.gif" />
												<h:graphicImage id="codIncorrectoPremio" 
													style="width: 23px; height: 23px; visibility: hidden;"
													url="/img/semaforo/tache.gif" />
											</td>
										</tr>
									</table>
									<table width="100%" border="0" cellspacing="3" cellpadding="0" >
										<tr>
											<td width="100%">
												<div style="height: 125px; width: 100%; overflow-y: scroll; border: 1px solid #000;">
												<h:dataTable id="tblPremiosUnitarios"
													value="#{entregaFaltantes.listaPremiosUnitarios}" var="premio"
													styleClass="gridDatos" headerClass="gridDatosEncabezado"
													rendered="#{entregaFaltantes.panelPremiosUnitariosActivo}">
													<h:column>
														<f:facet name="header">
															<h:outputText value="FSC" styleClass="label"/>
														</f:facet>
														<h:outputText id="premioFSC" value="#{premio.FSC}" styleClass="label"/>
														<h:inputHidden id="idPremioFSC" value="#{premio.FSC}"></h:inputHidden>
													</h:column>
													<h:column>
														<f:facet name="header">
															<h:outputText value="EAN13" styleClass="label"/>
														</f:facet>
														<h:outputText id="premioEAN13" value="#{premio.EAN13}" styleClass="label"/>
														<h:inputHidden id="idPremioEAN13" value="#{premio.EAN13}"></h:inputHidden>
													</h:column>
													<h:column>
														<f:facet name="header">
															<h:outputText value="Descripción" styleClass="label"/>
														</f:facet>
														<h:outputText value="#{premio.descripcionItem}" styleClass="label"/>
													</h:column>
													<h:column>
														<f:facet name="header">
															<h:outputText value="Facturados" styleClass="label"/>
														</f:facet>
														<h:outputText value="#{premio.facturados}" styleClass="label"/>
													</h:column>
													<h:column>
														<f:facet name="header">
															<h:outputText value="Entrega" styleClass="label"/>
														</f:facet>
														<h:inputText id="premio" value="#{premio.entrega}" onchange="validarEntregasPremios();"></h:inputText>
														<h:inputHidden id="cantidadFacturadosPremio" value="#{premio.facturados}"></h:inputHidden>
													</h:column>
												</h:dataTable>
												</div>
											</td>
										</tr>
									</table>
								</t:fieldset>
								<br></br>
								<table width="100%">
									<tr>
										<td width="50%" align="center">
											<a4j:commandButton value="#{msg.entregaFaltantes_cancelar}" id="btnCancelar"
												action="#{entregaFaltantes.cancelar}"
												reRender="frmEntregaFaltantes,pnlDetalle,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlIzquierdo,pnlConsultaOrdenes,outputPanelDerecho,modPanRepLiqRepWeb,pnlCajas,tblCajas,pnlPremiosUnitarios,tblPremiosUnitarios"
												disabled="#{ not entregaFaltantes.panelDetalleActivo}"
												oncomplete="borrarPalomitas();">											
											</a4j:commandButton>
										</td>
										<td width="50%" align="center">
											<a4j:commandButton value="#{msg.entregaFaltantes_entregar}" id="btnEntregar"
												action="#{entregaFaltantes.entregar}"
												reRender="frmEntregaFaltantes,pnlDetalle,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlIzquierdo,pnlConsultaOrdenes,outputPanelDerecho,modPanRepLiqRepWeb,pnlCajas,tblCajas,pnlPremiosUnitarios,tblPremiosUnitarios"
												disabled="#{ not entregaFaltantes.panelDetalleActivo}"
												oncomplete="borrarPalomitas();">											
											</a4j:commandButton>
										</td>
									</tr>
								</table>
							</a4j:outputPanel>
							</rich:panel>
						</td>
					</tr>
				</table>
			</a4j:form>
			
			<!-- Inicia ventana de representantes -->
			<a4j:form styleClass="forma" id="frmModal"
					binding="#{entregaFaltantes.frmModal }">
					<rich:modalPanel id="modPanRepLiqRepWeb" autosized="true"
						showWhenRendered="#{entregaFaltantes.panelRepresentantesActivo}">
						<f:facet name="header">
							<h:outputText value="#{msg.entregaFaltantes_ventanaRepresentantesTitulo }" />
						</f:facet>
						<f:facet name="controls">
							<span style="cursor: pointer"
								onclick="javascript:Richfaces.hideModalPanel('modPanRepLiqRepWeb')"> X
							</span>
						</f:facet>
							<div style="height: 200px; width: 100%; overflow-y: scroll; border: 1px solid #000;">
							<h:dataTable value="#{entregaFaltantes.listaRepresentantes}"
								var="act" id="tabla" styleClass="gridDatos"
								headerClass="gridDatosEncabezado"
								rendered="#{entregaFaltantes.panelRepresentantesActivo}"
								style="height: 85px; width: 866px; ">

								<h:column>
									<f:facet name="header">
										<h:outputText value="Account" styleClass="label" />
									</f:facet>
									<h:outputText value="#{act.registroRepresentante}" styleClass="label" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="Nombre" styleClass="label" />
									</f:facet>
									<h:outputText value="#{act.nombreRepresentante}" styleClass="label" />
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
										<h:outputText value="Agregar" styleClass="label" />
									</f:facet>
									<a4j:commandButton value="Agregar"
										action="#{entregaFaltantes.asignarRepresentanteSeleccionado }"
										oncomplete="javascript:Richfaces.hideModalPanel('modPanRepLiqRepWeb')"
										reRender="frmEntregaFaltantes,pnlDetalle,tfRegistro,tfNombre,panel,lblError,pnlOrdenes,btnConsultar,btnLimpiarFormulario,btnLimpiar,pnlIzquierdo,pnlConsultaOrdenes,outputPanelDerecho,modPanRepLiqRepWeb,pnlCajas,tblCajas,pnlPremiosUnitarios,tblPremiosUnitarios">
										<f:setPropertyActionListener value="#{act}"
											target="#{entregaFaltantes.representanteSeleccionado}" />
									</a4j:commandButton>
								</h:column>
							</h:dataTable>
							</div>
					</rich:modalPanel>
			</a4j:form>
			<!-- Termina ventana de representantes -->								
			<script type="text/javascript">
				document.getElementById("frmEntregaFaltantes:tfRegistro").focus();
			</script>	
			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>