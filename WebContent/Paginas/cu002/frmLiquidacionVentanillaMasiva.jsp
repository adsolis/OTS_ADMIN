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
<title>Liquidación en Ventanilla Masiva</title>
</head>
<body>
	<f:view>
		<ui:composition template="/Paginas/template.jsp">
			<ui:define name="content">
				<!-- Incluye archivo de properties -->
				<f:loadBundle var="msg" basename="com.datacode.avon_ots_admin.cu002.messagesCU002" />
				<!-- Diseno de pagina -->
				<h:outputLabel value="#{msg.tituloLM}" styleClass="tituloPagina"/>
					<table width="100%">
						<tr>
							<td valign="top" style="width: 60%;">
								<t:fieldset style="color: Black;vertical-align: top" id="pnlIzquierdo">						
									<a4j:form id="frmBuscarRepresentante" >
										<table width="100%" border="0" cellspacing="3" cellpadding="0" >
											<tr>
												<td colspan="3" align="right">
													<a4j:commandButton value="#{msg.btnBuscarLM}" id="btnBuscar"
														action="#{liquidacionVentanillaMasiva.BuscarRepresentante}"
														oncomplete="document.getElementById('frmBuscarRepresentante:txtRegistro').focus();"
														reRender="pnlIzquierdo"
														disabled="#{liquidacionVentanillaMasiva._pnlIzquierdo}">											
													<h:outputText value="#{liquidacionVentanillaMasiva._mensajeError1}" id="lblMensajeError1" styleClass="error" /></a4j:commandButton>	
												</td>
											</tr>
											<tr>
												<td>
													<h:outputLabel value="#{msg.lblRegistroLM}"
														styleClass="label"/>
												</td>
												<td colspan="2">
													<h:inputText id="txtregistroBusqueda" styleClass="inputText" maxlength="15" style="width: 150px;"
														value="#{liquidacionVentanillaMasiva._txtregistroBusqueda}"
														onkeypress="return liquidacionVentanillaMasivaKeyPress(event, 'Buscar');"														
														disabled="#{liquidacionVentanillaMasiva._pnlIzquierdo}" >
														<a4j:support event="onkeypress"
														    focus="txtregistroBusqueda"
															reRender="frmBuscarRepresentante"
															ignoreDupResponses="true" requestDelay="1000"/>
													</h:inputText>
												</td>
											</tr>
											<tr>
												<td colspan="3" align="center">
														
												</td>
											</tr>
										</table>
										<div style="height: 200px; width: 100%; overflow-y: scroll;">
											<h:dataTable id="dtgDetalleRepresentante"
												value="#{liquidacionVentanillaMasiva._listaRegistrosRepresentantes}" var="dtgDetalle"
												styleClass="gridDatos" headerClass="gridDatosEncabezado"
												rendered="#{liquidacionVentanillaMasiva._dtgRegistrosRepresentante}">
	
												<h:inputHidden>													
													<h:outputText value="#{dtgDetalle.idOrden}" id="lblIdOrden"
														 styleClass="label" style="visibility: hidden; width: 0px;"/>
												</h:inputHidden>
												<h:inputHidden>
													<h:outputText value="#{dtgDetalle.idOrdenEntVentanilla}" id="lblIdOrdenEntVentanilla"
														 styleClass="label" style="visibility: hidden; width: 0px;"/>
												</h:inputHidden>
												<h:column style="width: 10%;">
													<f:facet name="header">
														<h:outputText value="Campaña" styleClass="label"/>
													</f:facet>
													<h:outputText value="#{dtgDetalle.campania}"  id="lblCampaña" styleClass="label" />
												</h:column>
												<h:column style="width: 10%;">
													<f:facet name="header">
														<h:outputText value="Zona" styleClass="label"/>
													</f:facet>
													<h:outputText value="#{dtgDetalle.zona}"  id="lblZona" styleClass="label" />
												</h:column>
												<h:column style="width: 10%;">
													<f:facet name="header">
														<h:outputText value="Orden" styleClass="label"/>
													</f:facet>
													<h:outputText value="#{dtgDetalle.claveOrden}"  id="lblOrden" styleClass="label"/>
												</h:column>
												<h:column style="width: 10%;">
													<f:facet name="header">
														<h:commandLink  onclick="return SortTable('frmBuscarRepresentante:dtgDetalleRepresentante', 3, 'Registro')" 
															disabled="#{liquidacionVentanillaMasiva._pnlIzquierdo}">
														 	<h:graphicImage 
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/asc.png" />
																<h:outputText value="Registro" styleClass="label" id="RepresentanteRegistro"/>
															<h:graphicImage  
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/desc.png" />
														</h:commandLink>
													</f:facet>
													<h:outputText value="#{dtgDetalle.registro}"  id="lblRegistro" styleClass="label"/>
												</h:column>
												<h:column style="width: 40%;">
													<f:facet name="header">
														<h:commandLink  onclick="return SortTable('frmBuscarRepresentante:dtgDetalleRepresentante', 4, 'Nombre')"
															disabled="#{liquidacionVentanillaMasiva._pnlIzquierdo}">
															<h:graphicImage 
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/asc.png" />
																<h:outputText value="Nombre" styleClass="label" id="RepresentanteNombre"/>
															<h:graphicImage  
																style="width: 9px; height: 5px; visibility: hidden;"
																url="/img/desc.png" />
														</h:commandLink>
													</f:facet>
													<h:outputText value="#{dtgDetalle.nombre}"  id="lblNombre" styleClass="label"/>
												</h:column>
												<h:column style="width: 10%;">
													<f:facet name="header">
														<h:outputText value="Monto" styleClass="label"/>
													</f:facet>
													<h:outputText value="$ " styleClass="label" />
													<h:outputText value="#{dtgDetalle.montoCobrar}"   id="lblMonto" styleClass="label"/>
												</h:column>
												<h:column style="width: 10%;">
													<f:facet name="header">
														<h:outputText value="" styleClass="label"/>
													</f:facet>
													<h:commandButton id="btnDetalle" 														
														action="#{liquidacionVentanillaMasiva.DetalleRepresentante}"
														value="Detalle" styleClass="botonGrid" 											
														disabled="#{liquidacionVentanillaMasiva._pnlIzquierdo}"
														onclick="return validaRegistro();"
														>
														<f:setPropertyActionListener value="#{dtgDetalle}" 
															target="#{liquidacionVentanillaMasiva._dtDetalle}" />
																										
													</h:commandButton>
												</h:column>
											</h:dataTable>	
										</div>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr align="right">
												<td>											
													<h:outputLabel value="#{msg.lblMontoTotalEM}"
														styleClass="label" style="font-weight: bold;" />
														<h:outputText value="$ #{liquidacionVentanillaMasiva._montoTotal1}" styleClass="label" style="font-weight: bold;" />
												</td>
											</tr>
											<tr align="left">
												<td >											
													<a4j:commandButton
														action="#{liquidacionVentanillaMasiva.LimpiarBusqueda}"
														value="#{msg.btnLimpiarLM}" styleClass="botonGrid" 
														reRender="pnlIzquierdo"
														disabled="#{liquidacionVentanillaMasiva._pnlIzquierdo}">														
													</a4j:commandButton>
												</td>
											</tr>
										</table>
									</a4j:form>
							</t:fieldset>
						</td>
						<td valign="top">
							<t:fieldset style="color: Black;vertical-align: top" id="pnlDerecho">
								<a4j:form id="frmDetalles">							
									<table width="100%" border="0" cellspacing="0" cellpadding="5">
										<tr align="left" style="text-align: left;">											
											<td>
												<h:outputLabel value="#{msg.lblCampañaLM}"
													styleClass="label"/>
											</td>
											<td>
												<h:outputLabel id="lblCampaña" value="#{liquidacionVentanillaMasiva._lblCampaña}"
													styleClass="label" />
											</td>
											<td>
												<h:outputLabel value="#{msg.lblOrdenLM}"
													styleClass="label"/>
											</td>
											<td>
												<h:outputLabel id="lblOrden" value="#{liquidacionVentanillaMasiva._lblOrden}"
													styleClass="label" />
											</td>
										</tr>
										<tr align="left" style="text-align: left;">											
											<td>
												<h:outputLabel value="#{msg.lblRegistroLM}"
													styleClass="label"/>
											</td>
											<td>
												<h:outputLabel id="lblRegistro" value="#{liquidacionVentanillaMasiva._lblRegistro}"
													styleClass="label" />
											</td>
											<td>
												<h:outputLabel value="#{msg.lblNombreLM}"
													styleClass="label"/>
											</td>
											<td>
												<h:outputLabel id="lblNombre" value="#{liquidacionVentanillaMasiva._lblNombre}"
													styleClass="label" />
											</td>
										</tr>
										<tr align="left" style="text-align: left;">											
											<td>
												<h:outputLabel  value="#{msg.lblMontoCobrarLM}"
													styleClass="label"/>
											</td>
											<td>
												<h:outputLabel id="lblMontoCobrar" value="#{liquidacionVentanillaMasiva._lblMontoCobrar}" 
													styleClass="label" />
											</td>
											<td>
												<h:outputLabel value="#{msg.lblRemanenteLM}"
													styleClass="label"/>
											</td>
											<td>
												<h:outputLabel id="LblRemanente" value="#{liquidacionVentanillaMasiva._lblRemanente}"
													styleClass="label" />
											</td>
										</tr>
										<tr align="left" style="text-align: left;">											
											<td>
												<h:outputLabel value="#{msg.lblTipoPagoLM}"
													styleClass="label"/>
											</td>
											<td>
												<h:selectOneMenu id="cobTipoPago" value="#{liquidacionVentanillaMasiva._idTipoPago}" style="width: 150px;" styleClass="inputText" 
														disabled="#{!liquidacionVentanillaMasiva._pnlDerecho}" onchange="validarCombo('TipoPago')">
													<f:selectItems value="#{liquidacionVentanillaMasiva.obtenerTipoPagos}" />
												</h:selectOneMenu>
											</td>
											<td>
												<h:outputLabel value="#{msg.lblBancoLM}"
													styleClass="label"/>
											</td>
											<td>
												<h:selectOneMenu id="cobBanco" value="#{liquidacionVentanillaMasiva._idBanco}" style="width: 150px;" styleClass="inputText" 
														disabled="#{!liquidacionVentanillaMasiva._pnlDerecho}">
													<f:selectItems value="#{liquidacionVentanillaMasiva.obtenerBancos}" />
												</h:selectOneMenu>
											</td>
										</tr>
										<tr align="left" style="text-align: left;">											
											<td>
												<h:outputLabel value="#{msg.lblFolioCobranzaLM}"
													styleClass="label"/>
											</td>
											<td>
												<h:inputText id="txtFolioCobranza" styleClass="inputText" maxlength="100" style="width: 150px;"
													value="#{liquidacionVentanillaMasiva._txtFolioCobranza}"
													onkeypress="return validaLetrasYNumeros(event);"														
													disabled="#{!liquidacionVentanillaMasiva._pnlDerecho}" />
											</td>
											<td>
												<h:outputLabel id="lblFechaPagoLM" value="#{msg.lblFechaPagoLM}"
													styleClass="label"/>
											</td>
											<td>
												<h:inputText id="txtFechaPago" styleClass="tcal" style="width: 150px;"
													value="#{liquidacionVentanillaMasiva._txtFechaPago}" maxlength="10"
														onkeypress="return validaFormatoFecha(event);"
													disabled="#{!liquidacionVentanillaMasiva._pnlDerecho}"
													/>
											</td>
										</tr>
										<tr align="right" style="text-align: left;">											
											<td colspan="1">
												<h:outputLabel id="lblMontoLM" value="#{msg.lblMontoLM}"
													styleClass="label"/>
											</td>
											<td>
												<h:inputText id="txtMonto" styleClass="inputText" maxlength="8" style="width: 150px;"
													value="#{liquidacionVentanillaMasiva._txtMonto}"
													onkeypress="return validaMonto(event, this);"														
													disabled="#{!liquidacionVentanillaMasiva._pnlDerecho}" />
											</td>
										</tr>
										<tr>
											<td>
												<h:commandButton id="btnAgregarLiquidacion"
													action="#{liquidacionVentanillaMasiva.AgregarRegistro}"
													value="#{msg.btnAgregarLM}"
													disabled="#{!liquidacionVentanillaMasiva._pnlDerecho}"
													onclick="return validarLiquidacion();">
												</h:commandButton>
											</td>
											<td colspan="3">
												<h:outputText value="#{liquidacionVentanillaMasiva._mensajeError2}"
													id="lblMensajeError2" styleClass="error"/>
											</td>
										</tr>
									</table>
										<div style="height: 170px; width: 100%; overflow-y: scroll; border: 1px solid #000;">
											<h:dataTable id="dtgLiquidar"
												value="#{liquidacionVentanillaMasiva._listaLiquidar}" var="dtgDetalleLiquidar"
												styleClass="gridDatos" headerClass="gridDatosEncabezado"
												rendered="#{liquidacionVentanillaMasiva._dtgLiquidar}">	
												<h:inputHidden>
													<h:outputText value="#{dtgDetalleLiquidar.id}" id="lblIdLiquidar"
														 styleClass="label" style="visibility: hidden; width: 0px;"/>
												</h:inputHidden>											
												<h:inputHidden>
													<h:outputText value="#{dtgDetalleLiquidar.idOrden}" id="lblIdOrden"
														 styleClass="label" style="visibility: hidden; width: 0px;"/>
												</h:inputHidden>
												<h:inputHidden>
													<h:outputText value="#{dtgDetalleLiquidar.idOrdenEntVentanilla}" id="lblIdOrdenVentanilla"
														 styleClass="label" style="visibility: hidden; width: 0px;"/>
												</h:inputHidden>
												<h:inputHidden>
													<h:outputText value="#{dtgDetalleLiquidar.idTipoPago}" id="lblIdTipoPago"
														 styleClass="label" style="visibility: hidden; width: 0px;"/>
												</h:inputHidden>
												<h:inputHidden>
													<h:outputText value="#{dtgDetalleLiquidar.idBanco}" id="lblIdBanco"
														 styleClass="label" style="visibility: hidden; width: 0px;"/>
												</h:inputHidden>												
												<h:column>
													<f:facet name="header">
														<h:outputText value="Tipo de Pago" styleClass="label"/>
													</f:facet>
													<h:outputText value="#{dtgDetalleLiquidar.tipoPago}" styleClass="label"/>
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Descripción" styleClass="label"/>
													</f:facet>
													<h:outputText value="#{dtgDetalleLiquidar.descripcion }" styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Folio" styleClass="label"/>
													</f:facet>													
													<h:outputText value="#{dtgDetalleLiquidar.folio }" styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Fecha" styleClass="label"/>
													</f:facet>
													<h:outputText value="#{dtgDetalleLiquidar.fecha}" id="lblFecha"
														 styleClass="label"/>
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="Monto" styleClass="label"/>
													</f:facet>													
													<h:outputText value="#{dtgDetalleLiquidar.montoCobrar }" styleClass="label" />
												</h:column>
												<h:column>
													<f:facet name="header">
														<h:outputText value="" styleClass="label"/>
													</f:facet>
													<h:commandButton id="btnEliminarFila"
														action="#{liquidacionVentanillaMasiva.EliminarRegistroTablaLiquidar}"
														value="Eliminar" styleClass="botonGrid"
														disabled="#{!liquidacionVentanillaMasiva._pnlDerechoEliminar}" 
														>
														<f:setPropertyActionListener value="#{dtgDetalleLiquidar}"
															target="#{liquidacionVentanillaMasiva._dtDetalleLiquidar}" />
													</h:commandButton>
												</h:column>									
											</h:dataTable>
										</div>	
										<table width="100%" border="0" cellspacing="0" cellpadding="5">									
										<tr align="right">
												<td colspan="2">											
													<h:outputLabel value="#{msg.lblMontoTotalEM}"
														styleClass="label" style="font-weight: bold;" />
														<h:outputText value="$ #{liquidacionVentanillaMasiva._montoTotal2}" id="lblMontoTotal" styleClass="label" style="font-weight: bold;" />
												</td>
										</tr>
										<tr>
										<td>
												<h:outputLabel value="#{msg.lblQuieRecibeLM}"
													styleClass="label"/>
											</td>
											<td>
												<h:selectOneMenu id="cobQuienRecibe" value="#{liquidacionVentanillaMasiva._idQuienRecibe}" style="width: 150px;" styleClass="inputText" 
													onchange="validarCombo('QuienRecibe')"
													disabled="#{!liquidacionVentanillaMasiva._pnlDerechoQuienRecibe}">
													<f:selectItems value="#{liquidacionVentanillaMasiva.obtenerQuienRecibe}" />
												</h:selectOneMenu>
												<h:inputText id="txtOtros" styleClass="inputText" maxlength="30" style="width: 150px; visibility: hidden;"
													value="#{liquidacionVentanillaMasiva._txtOtros}"
													onkeypress="return validaLetrasYNumeros(event);"/>
											</td>
										</tr>
										<tr align="center">
												<td>
													<h:outputLabel value="#{msg.lblComentariosLM}"
														styleClass="label"/>
												</td>
												<td>
													<h:inputTextarea id="lblComentarios" styleClass="inputText" style="width: 400px; height: 40px; overflow:auto;"
														value="#{liquidacionVentanillaMasiva._txtComentarios}"  																												
														disabled="#{!liquidacionVentanillaMasiva._pnlDerechoComentarios}">														
													</h:inputTextarea>
												</td>
										</tr>
										<tr align="center" style="width: 100%">
											<td>
												<h:commandButton value="#{msg.btnCancelarLM}" id="btnCancelar"	
													onclick="return validarConfirm('Cancelar');"
													action="#{liquidacionVentanillaMasiva.CancelarLiquidacion}"
													disabled="#{!liquidacionVentanillaMasiva._pnlDerechoCancelar}">
												</h:commandButton>
											</td>
											<td>
												<h:commandButton value="#{msg.btnLiquidarOrdenLM}" id="btnLiquidarOrden"
													action="#{liquidacionVentanillaMasiva.LiquidarRepresentante}"
													onclick="return validarConfirm('Liquidar');"
													disabled="#{!liquidacionVentanillaMasiva._pnlDerechoLiquidar}">												
												</h:commandButton>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<h:outputText value="#{liquidacionVentanillaMasiva._mensajeError3}"
													id="lblMensajeError3" styleClass="error"/>
											</td>
										</tr>
									</table>
									</a4j:form>
								</t:fieldset>
							</td>
						</tr>
					</table>
				<script type="text/javascript">
					document.getElementById("frmBuscarRepresentante:txtregistroBusqueda").focus();
				</script>			
			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>