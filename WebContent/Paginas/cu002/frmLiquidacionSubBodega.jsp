<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:t="http://myfaces.apache.org/tomahawk">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Liquidación de SubBodega</title>
</head>
<body>
<f:view>
<ui:composition template="/Paginas/template.jsp">
	<ui:define name="content">
		<!--  Mensajes de la Vista -->
		<f:loadBundle basename="com.datacode.avon_ots_admin.cu002.messagesCU002" var="msg" />
		<!-- Diseno de Pagina -->
		
		<h:form binding="#{cu002LiqSubBodega.frmFiltros}" styleClass="forma" id="frmFiltros">
		<h:outputLabel value="#{msg.titulo_LiqSubBodega}" styleClass="tituloPagina"></h:outputLabel>
			<t:fieldset legend="#{msg.pnlTituloCriteriosConsulta_LiqSubBodega}">
				<h:panelGrid columns="7" id="pnlCombos">
					<h:outputLabel value="#{msg.lblZona_LiqSubBodega}" id="lblZona"
						styleClass="label"></h:outputLabel>
					<h:selectOneMenu validatorMessage="required" required="true" 
						onchange="this.form.submit()" valueChangeListener="#{cu002LiqSubBodega.llenaSubBodegas}"
						immediate="true" id="cmbZona" value="#{cu002LiqSubBodega.idZona}">
						<f:selectItems value="#{cu002LiqSubBodega.cmbZonas}" />
					</h:selectOneMenu>
					
					<h:outputLabel value="#{msg.lblCampania_LiqSubBodega}" id="lblCampania"
						styleClass="label"></h:outputLabel>
					<h:selectOneMenu validatorMessage="required" required="true"
						id="cmbCampania" onchange="this.form.submit()" value="#{cu002LiqSubBodega.idCampania}">
						<f:selectItems value="#{cu002LiqSubBodega.cmbCampanias}" />
					</h:selectOneMenu>
					
					<h:outputLabel value="#{msg.lblSubBodega_LiqSubBodega}" id="lblSubBodega"
						styleClass="label"></h:outputLabel>
					<h:selectOneMenu validatorMessage="required" required="true" 
						onchange="this.form.submit()"
						immediate="true" id="cmbSubBodega" value="#{cu002LiqSubBodega.idSubBodega}">
						<f:selectItems value="#{cu002LiqSubBodega.cmbSubBodegas}" />
					</h:selectOneMenu>
					
					<h:commandButton action="#{cu002LiqSubBodega.consultar}" value="#{msg.btnConsultar_LiqSubBodega}" 
						disabled="true" id="btnConsultar" accesskey="o" styleClass="botonPrincipal" binding="#{cu002LiqSubBodega.btnConsultar}">
					</h:commandButton>
				</h:panelGrid>
				
				<h:outputLabel value="#{cu002LiqSubBodega.mensajeError}" id="lblMensajeError"
						styleClass="error"></h:outputLabel>
			</t:fieldset>
		</h:form>
		
		<h:form binding="#{cu002LiqSubBodega.frmModificar}" styleClass="forma" id="frmModificar" rendered="false">
			<t:fieldset legend="#{msg.pnlTituloModificarItem_LiqSubBodega}">
				<h:panelGrid columns="1" id="pnlModificar0">
					<h:panelGrid columns="6" id="pnlModificar1">
						<h:outputLabel value="#{msg.lblAccount_RecAjustes}" id="lblAccount"
							styleClass="label"></h:outputLabel>
						<h:inputText value="#{cu002LiqSubBodega.txtAccount}" id="txtAccount" styleClass="caja" 
							disabled="true"></h:inputText>
						
						<h:outputLabel value="#{msg.lblNombre_RecAjustes}" id="lblNombre"
							styleClass="label"></h:outputLabel>
						<h:inputText value="#{cu002LiqSubBodega.txtNombre}" id="txtNombre" styleClass="caja" 
							size="40" disabled="true"></h:inputText>
											
						<h:outputLabel value="#{msg.lblCodigo_RecAjustes}" id="lblCodigo"
							styleClass="label"></h:outputLabel>
						<h:inputText value="#{cu002LiqSubBodega.txtCodigo}" id="txtCodigo" styleClass="caja" 
							disabled="true"></h:inputText>
											
						<h:outputLabel value="#{msg.lblTipo_RecAjustes}" id="lblTipo"
							styleClass="label"></h:outputLabel>
						<h:inputText value="#{cu002LiqSubBodega.txtTipo}" id="txtTipo" styleClass="caja" 
							disabled="true"></h:inputText>
											
						<h:outputLabel value="#{msg.lblDescripcion_RecAjustes}" id="lblDescripcion"
							styleClass="label"></h:outputLabel>
						<h:inputText value="#{cu002LiqSubBodega.txtDescripcion}" id="txtDescripcion" styleClass="caja" 
							size="40" disabled="true"></h:inputText>
											
						<h:outputLabel value="#{msg.lblCantidad_RecAjustes}" id="lblCantidad"
							styleClass="label"></h:outputLabel>
						<h:inputText value="#{cu002LiqSubBodega.txtCantidad}" id="txtCantidad" styleClass="caja" 
							disabled="true"></h:inputText>
							
							
						<h:outputLabel value="#{msg.lblEntregada_RecAjustes}" id="lblEntregada"
							styleClass="label"></h:outputLabel>
						<h:inputText value="#{cu002LiqSubBodega.txtEntregada}" id="txtEntregada" styleClass="caja"
							onkeypress="return validarNumeros(event);" onblur="return validarCampoCantidad_LiqSubBodega(this);" 
							maxlength="4"></h:inputText>
						
						<h:outputLabel value="#{msg.lblFechaEntregada_RecAjustes}" id="lblFechaEntregada"
							styleClass="label"></h:outputLabel>
						<h:inputText value="#{cu002LiqSubBodega.txtFechaEntregada}" id="txtFechaEntregada" styleClass="tcal"
							size="10" onkeypress="return false;" maxlength="10"></h:inputText>
							
						<h:outputLabel value="#{msg.lblRecibida_RecAjustes}" id="lblRecibida"
							styleClass="label"></h:outputLabel>
						<h:inputText value="#{cu002LiqSubBodega.txtRecibida}" id="txtRecibida" styleClass="caja"
							onkeypress="return validarNumeros(event);" onblur="return validarCampoCantidad_LiqSubBodega(this);" 
							maxlength="4"></h:inputText>
					</h:panelGrid>
							
					<h:panelGrid columns="2" id="pnlModificar2">			
						<h:outputLabel value="#{msg.lblCausaFaltante_RecAjustes}" id="lblCausaFaltante"
							styleClass="label"></h:outputLabel>
						<h:inputText value="#{cu002LiqSubBodega.txtCausaFaltante}" id="txtCausaFaltante" styleClass="caja"
							size="80" maxlength="100"></h:inputText>
					</h:panelGrid>
				</h:panelGrid>
				<h:commandButton action="#{cu002LiqSubBodega.guardarItem}" value="#{msg.btnGuardarItem_LiqSubBodega}" 
					id="btnGuardarItem" styleClass="botonPrincipal" binding="#{cu002LiqSubBodega.btnGuardarItem}"
					onclick="return validarRequeridosGuardarItem_LiqSubBodega();">
				</h:commandButton>
			</t:fieldset>
		</h:form>
		
		<h:form binding="#{cu002LiqSubBodega.frmGrids}" styleClass="forma" id="frmGrids" rendered="false">
		<!-- ====================================GRID DE ORDENES================================= -->
			<t:fieldset legend="#{msg.pnlTituloGridOrdenes_LiqSubBodega}">
				<h:dataTable value="#{cu002LiqSubBodega.grdOrdenes}" var="orden"
				styleClass="gridDatos" headerClass="gridDatosEncabezado">
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoAccountGridOrdenes_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{orden.registroRep}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoNombreGridOrdenes_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{orden.nombreRep}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoOrdenGridOrdenes_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{orden.claveOrden}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoCodigoGridOrdenes_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{orden.codigoBarras}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoDescripcionGridOrdenes_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{orden.descripcionItem}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoTipoGridOrdenes_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{orden.tipoItem}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoCantidadGridOrdenes_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{orden.cantidad}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoEntregadaGridOrdenes_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{orden.cantEntregada}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoFechaEntregaGridOrdenes_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{orden.fechaEntrega}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoRecibidaGridOrdenes_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{orden.cantRecibida}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoCausaFaltanteGridOrdenes_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{orden.causaFaltante}"></h:outputText>
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="Acciones"></h:outputText>
							</h:column>
						</f:facet>
						<h:panelGrid columns="2">
							<h:commandButton action="#{cu002LiqSubBodega.modificarItem}" value="Modificar" styleClass="botonGrid">
								<f:setPropertyActionListener target="#{cu002LiqSubBodega.ordenSel}" value="#{orden}" />
							</h:commandButton>
						</h:panelGrid>
					</h:column>
				</h:dataTable>
			</t:fieldset>
			
			<!-- ====================================GRID DE PREMIOS================================= -->
			<t:fieldset legend="#{msg.pnlTituloGridPremios_LiqSubBodega}">
				<h:dataTable value="#{cu002LiqSubBodega.grdPremios}" var="premio"
				styleClass="gridDatos" headerClass="gridDatosEncabezado">
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoAccountGridPremios_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{premio.registroRep}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoNombreGridPremios_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{premio.nombreRep}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoCodigoGridPremios_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{premio.codigoBarras}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoDescripcionGridPremios_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{premio.descripcionItem}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoCantidadGridPremios_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{premio.cantidad}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoEntregadaGridPremios_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{premio.cantEntregada}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoFechaEntregaGridPremios_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{premio.fechaEntrega}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoRecibidaGridPremios_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{premio.cantRecibida}"></h:outputText>
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="Acciones"></h:outputText>
							</h:column>
						</f:facet>
						<h:panelGrid columns="2">
							<h:commandButton action="#{cu002LiqSubBodega.modificarItem}" value="Modificar" styleClass="botonGrid">
								<f:setPropertyActionListener target="#{cu002LiqSubBodega.premioSel}" value="#{premio}" />
							</h:commandButton>
						</h:panelGrid>
					</h:column>
				</h:dataTable>
			</t:fieldset>
			
			<!-- ====================================GRID DE INVENTARIO================================= -->
			<t:fieldset legend="#{msg.pnlTituloGridInventario_LiqSubBodega}">
				<h:dataTable value="#{cu002LiqSubBodega.grdInventario}" var="inv"
				styleClass="gridDatos" headerClass="gridDatosEncabezado">
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoCodigoGridInventario_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{inv.codigoBarras}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoDescripcionGridInventario_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{inv.descripcionItem}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoTipoGridInventario_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{inv.tipoItem}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoCantidadGridInventario_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{inv.cantidad}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoEntregadaGridInventario_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{inv.cantEntregada}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoRecibidaGridInventario_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{inv.cantRecibida}"></h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:column>
								<h:outputText value="#{msg.encabezadoCausaFaltanteGridInventario_LiqSubBodega}"></h:outputText>
							</h:column>
						</f:facet>
						<h:outputText value="#{inv.causaFaltante}"></h:outputText>
					</h:column>
				</h:dataTable>
			</t:fieldset>
			
			<h:commandButton action="#{cu002LiqSubBodega.autorizarAlmacen}" value="#{msg.btnAutorizaAlmacen_LiqSubBodega}" 
				binding="#{cu002LiqSubBodega.btnAutorizarAlmacen}" id="btnAutorizarAlmacen" styleClass="botonPrincipal">
			</h:commandButton>
			<h:commandButton action="#{cu002LiqSubBodega.autorizarSupervisor}" value="#{msg.btnAutorizaSupervisor_LiqSubBodega}" 
				binding="#{cu002LiqSubBodega.btnAutorizarSupervisor}" id="btnAutorizarSupervisor" styleClass="botonPrincipal">
			</h:commandButton>
			<h:commandButton action="#{cu002LiqSubBodega.guardarLiquidacion}" value="#{msg.btnGuardarLiquidacion_LiqSubBodega}" 
				onclick="return confirmarGuardado_LiqSubBodega();" binding="#{cu002LiqSubBodega.btnGuardarLiquidacion}" id="btnGuardarLiquidacion" styleClass="botonPrincipal">
			</h:commandButton>
		</h:form>
		
		<h:form binding="#{cu002LiqSubBodega.frmAutorizacion}" styleClass="forma" id="frmAutorizacion" rendered="false">
			<t:fieldset legend="#{cu002LiqSubBodega.lblAutorizacion}">
				<h:panelGrid columns="2" id="pnlAutorizacion">
					<h:outputLabel value="#{msg.lblUsuario_LiqSubBodega}" id="lblUsuario"
						styleClass="label"></h:outputLabel>
					<h:inputText value="#{cu002LiqSubBodega.txtUsuario}" id="txtUsuario" styleClass="caja"
						maxlength="15"></h:inputText>
					
					<h:outputLabel value="#{msg.lblContrasenia_LiqSubBodega}" id="lblContrasenia"
						styleClass="label"></h:outputLabel>
					<h:inputSecret value="#{cu002LiqSubBodega.txtContrasenia}" id="txtContrasenia" styleClass="caja"
						maxlength="15"></h:inputSecret>
				</h:panelGrid>
				
				<h:commandButton action="#{cu002LiqSubBodega.autorizarUsuario}" value="#{msg.btnAutorizarUsuario_LiqSubBodega}" 
					id="btnAutorizarUsuario" styleClass="botonPrincipal">
				</h:commandButton>
				<h:commandButton action="#{cu002LiqSubBodega.cancelarAutorizacion}" value="#{msg.btnCancelarAutorizacion_LiqSubBodega}" 
					id="btnCancelarAutorizacion" styleClass="botonPrincipal">
				</h:commandButton>
				<h:inputHidden value="#{cu002LiqSubBodega.tipoAutorizacion}"></h:inputHidden>
				<h:inputHidden value="#{cu002LiqSubBodega.idUsuarioAutorizaAlmacen}"></h:inputHidden>
				<h:inputHidden value="#{cu002LiqSubBodega.idUsuarioAutorizaSupervisor}"></h:inputHidden>
				<br />
				<h:outputLabel value="#{cu002LiqSubBodega.lblMsjAutorizacion}" id="lblMsjAutorizacion"
						styleClass="error"></h:outputLabel>
			</t:fieldset>
		</h:form>
	</ui:define>
</ui:composition>
</f:view>
</body>
</html>