<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" 
	  xmlns:f="http://java.sun.com/jsf/core" 
	  xmlns:h="http://java.sun.com/jsf/html"
	  xmlns:t="http://myfaces.apache.org/tomahawk" 
	  xmlns:ui="http://java.sun.com/jsf/facelets"
	  xmlns:a4j="http://richfaces.org/a4j"
      xmlns:rich="http://richfaces.org/rich"
      xmlns:fn="http://java.sun.com/jsp/jstl/functions">
       
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Modificar Catálogo de Rutas</title>
</head>
<body>
<f:view>
<!-- Se agrego composition para masterPAge -->
<ui:composition template="/Paginas/template.jsp">
	<ui:define name="content">
	<!--  Mensajes de la Vista -->
	<f:loadBundle basename="com.datacode.avon_ots_admin.cu001.messages" var="msg" />
			<!-- Diseno de Pagina -->
			<h:form binding="#{mto.form}" styleClass="todo" id="frmGeneral"  enctype="multipart/form-data">
			<h:outputLabel value="Modificar Catálogo de Rutas" styleClass="tituloPagina"/>
				<h:panelGrid columns="1" id="pnlGeneral">
					<h:panelGrid columns="3" id="pnlCargarCatalogo">
						<h:outputLabel value="Modificar Catálogo de Rutas" id="lblCatalogo" styleClass="label"/>
						<h:selectOneMenu    id="cmbxCatalogo" value="#{mto.idCatalogo}">
							<f:selectItems value="#{mto.cargaCatalogos}" />
						</h:selectOneMenu>
						<h:commandButton actionListener="#{mto.cargarCatalogoSeleccionado}" value="Buscar Catalogo" 
							id="btnCargar" accesskey="b" >
						</h:commandButton>
					</h:panelGrid>
					
					<!-- Catalogo 5 - Rutas - PW_RUTAS -->
					<rich:panel id="pnlc5">
						<h:outputLabel value="Modificar Catálogo de Rutas" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog51" columns="6">
							<h:outputLabel value="#{msg.cveRuta}" styleClass="label" id="lblCveRutaC5"/>
							<h:inputText id="txtCveRutaC5" value="#{mto.cveRuta}" styleClass="caja" maxlength="50" onkeypress="return permite(event,'num')" />
							<h:outputLabel value="#{msg.descRuta}" styleClass="label" id="lblDescRutaC5" />
							<h:inputText value="#{mto.descRuta}" id="txtDescRutaC5" styleClass="caja" maxlength="100"/>
							<h:outputLabel value="#{msg.zona}" styleClass="label" id="lblZonaC5"/>
							<h:selectOneMenu id="cmbxZonaC5" value="#{mto.idZona}">
								<f:selectItems value="#{mto.listaZonas}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC5"/>
							<h:selectOneMenu  id="cmbxPaisC5" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.tipoRuta}" styleClass="label" id="lblTipoRutaC5"/>
							<h:selectOneMenu id="cmbxTipoRutaC5" value="#{mto.idTipoRuta}" >
								<f:selectItems value="#{mto.listaTipoRuta}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.ldc}" styleClass="label" id="lblLDCC5"/>
							<h:selectOneMenu  id="cmbxLDCC5" value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.pobC5}" styleClass="label"/>
							<h:inputText value="#{mto.domicilio}" id="txtDomicilioC5" styleClass="caja" maxlength="100" />
							<h:outputLabel value="#{msg.kmC5}" styleClass="label"/>
							<h:inputText value="#{mto.km}" id="txtKmC5" styleClass="caja" maxlength="9" onkeypress="return permite(event,'num_pto')" />
							<h:outputLabel value="#{msg.npromC5}" styleClass="label"/>
							<h:inputText value="#{mto.noPromOrdenes}" id="txtNoPromOrdenesC5" styleClass="caja" maxlength="4" onkeypress="return permite(event,'num')" />
							
							<h:outputLabel value="#{msg.tPromEC5}" styleClass="label" id="lblPromEfectivo"/>
							<h:inputText value="#{mto.tiEfectivo}" id="txtPromEfectivoC5" styleClass="caja" 
							             onchange="return validaTiempo(this.value)" maxlength="8"/>
							
							<h:outputLabel value="#{msg.tPromTC5}" styleClass="label" id="lblPromTotal"/>
							<h:inputText value="#{mto.tiTotal}" id="txtPromTotalC5" styleClass="caja" 
							             onchange="return validaTiempo(this.value)" maxlength="8"/>
							<h:outputLabel value="#{msg.tipoRiesgoC5}" styleClass="label" id="lblTipoRiesgo5"/>
							<h:selectOneMenu  id="cmbxTipoRiesgC5" value="#{mto.idTipoRiesgo}" >
								<f:selectItems value="#{mto.listaTipoRiesgo}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.activoReparto}" styleClass="label" id="lblPromTotal9"/>
							<h:selectBooleanCheckbox id="Chk1" value="true"/>
							<h:outputLabel value="#{msg.diaReparto}" styleClass="label" id="lblPromTota8l"/>
							<h:inputText value="1" id="txtPromTotalC6" styleClass="caja" 
							             onchange="return validaTiempo(this.value)" maxlength="8"/>
							             
							<h:outputLabel value="" styleClass="label" id="lblPromTota7l"/>
							<h:outputLabel value="" styleClass="label" id="lblPromTotal6"/>
							
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Agregar" accesskey="g" onclick="return requeridosC5()"
								id="btnGuardarC5" disabled="#{!mto.mapaAccion['ADD_RUTA'] }"/>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c" disabled="#{!mto.cancelarActivado }"/>
							<h:commandButton value="Aceptar" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_RUTA'] }"/>
							
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						
						<h:panelGrid id="pnlC5Tabla">
								<rich:dataTable value="#{mto.rutas}" var="ruta" id="tblRuta" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                    <rich:column> <h:outputText value="Clave" /></rich:column>
						                    <rich:column> <h:outputText value="Descripción" /></rich:column>
						                    <rich:column> <h:outputText value="Zona" /></rich:column>
						                    <rich:column> <h:outputText value="Pais" /></rich:column>
						                    <rich:column> <h:outputText value="Tipo Ruta" /></rich:column>
						                    <rich:column> <h:outputText value="LDC" /></rich:column>
						                    <rich:column> <h:outputText value="Poblaciones y/o Colonias" /></rich:column>
						                    <rich:column> <h:outputText value="Kilometraje Promedio" /></rich:column>
						                    <rich:column> <h:outputText value="Tipo Riesgo" /> </rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						            	</rich:columnGroup>
						        	</f:facet>
						        	<rich:column filterMethod="#{mto.filterClaveRuta }" filterEvent="onblur">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValueCveRuta}" id="txtFilterCveC5">
						                        <a4j:support event="onkeyup" reRender="tblRuta"
						                            ignoreDupResponses="true" requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{ruta.cveRuta}" />
						            </rich:column>
						        	<rich:column> <h:outputText value="#{ruta.descRuta}"/> </rich:column>
						        	<rich:column filterMethod="#{mto.filterZonas }" filterEvent="onblur">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValueZona}" id="txtFilterZonaC5">
						                        <a4j:support event="onkeyup" reRender="tblRuta"
						                            ignoreDupResponses="true" requestDelay="1000"/>
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{ruta.descZona}" />
						            </rich:column>
						        	<rich:column> <h:outputText value="#{ruta.descPais}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{ruta.descTipoRuta}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{ruta.descLdc}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{ruta.poblacionColonia}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{ruta.kmPromedio}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{ruta.tipoRiesgo}"/> </rich:column>
						        	 <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_RUTA'] || !mto.modificarActivado }">
						            			<f:setPropertyActionListener  value="#{ruta.idRuta}" target="#{mto.idRuta}"/>
												<f:setPropertyActionListener  value="#{ruta.cveRuta}" target="#{mto.cveRuta }"/>
												<f:setPropertyActionListener  value="#{ruta.descRuta}" target="#{mto.descRuta }"/>
												<f:setPropertyActionListener  value="#{ruta.idZona}" target="#{mto.idZona }"/>
												<f:setPropertyActionListener  value="#{ruta.idPais}" target="#{mto.idPais }"/>
												<f:setPropertyActionListener  value="#{ruta.idTipoRuta}" target="#{mto.idTipoRuta }"/>
												<f:setPropertyActionListener  value="#{ruta.idLdc}" target="#{mto.idLDC }"/>
												<f:setPropertyActionListener  value="#{ruta.poblacionColonia}" target="#{mto.domicilio }"/>
												<f:setPropertyActionListener  value="#{ruta.kmPromedio}" target="#{mto.km }"/>
												<f:setPropertyActionListener  value="#{ruta.noPromedioOrdenes}" target="#{mto.noPromOrdenes }"/>
												<f:setPropertyActionListener  value="#{ruta.tiempoPromEfectivoReparto}" target="#{mto.tiEfectivo }"/>
												<f:setPropertyActionListener  value="#{ruta.tiempoPromTotalReparto}" target="#{mto.tiTotal }"/>
												<f:setPropertyActionListener  value="#{ruta.idTipoRiesgo}" target="#{mto.idTipoRiesgo }"/>
											</h:commandButton>
											<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
												onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_RUTA'] || !mto.eliminarActivado }">
												<f:setPropertyActionListener  value="#{ruta.idRuta}" target="#{mto.idRuta}"/>
											</h:commandButton>
						            	</rich:panel>
						            </rich:column>
						            <f:facet name="footer">
						            	<rich:datascroller id="dsC5" for="tblRuta"></rich:datascroller>
						        	</f:facet>
						    	</rich:dataTable>
						</h:panelGrid>
						</rich:panel>
					
					<!-- Fin -->
				</h:panelGrid>
			</h:form>

	</ui:define>
</ui:composition>
</f:view>
</body>
</html>