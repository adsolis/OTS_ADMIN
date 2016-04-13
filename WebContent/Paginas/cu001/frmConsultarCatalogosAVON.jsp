<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" 
	  xmlns:f="http://java.sun.com/jsf/core" 
	  xmlns:h="http://java.sun.com/jsf/html"
	  xmlns:t="http://myfaces.apache.org/tomahawk" 
	  xmlns:ui="http://java.sun.com/jsf/facelets"
	  xmlns:a4j="http://richfaces.org/a4j"
      xmlns:rich="http://richfaces.org/rich"
      xmlns:fn="http://java.sun.com/jsp/jstl/functions"
      xmlns:display="http://displaytag.sf.net"
      xmlns:p="http://primefaces.prime.com.tr/ui">
       
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Consultar Catalogos Provenientes de AVON</title>
</head>
<body>
<f:view>
<!-- Se agrego composition para masterPage -->
<ui:composition template="/Paginas/template.jsp">
	<ui:define name="content">
		<!--  Mensajes de la Vista -->
		<f:loadBundle basename="com.datacode.avon_ots_admin.cu001.messages" var="msg" />
		<h:form binding="#{catAvon.form}" styleClass="forma" id="frmGeneral">
			<h:outputLabel value="#{msg.tituloc4}" styleClass="tituloPagina"></h:outputLabel>
				<h:panelGrid columns="1" id="pnlGeneral">
					<h:panelGrid columns="3" id="pnlCargarCatalogo">
						<h:outputLabel value="#{msg.cat}" id="lblCatalogo" styleClass="label"></h:outputLabel>
						<h:selectOneMenu    id="cmbxCatalogo" value="#{catAvon.idCatalogo}">
							<f:selectItems value="#{catAvon.cargaCatalogos}" />
						</h:selectOneMenu>
						<h:commandButton actionListener="#{catAvon.cargarCatalogoSeleccionado}" value="Buscar Catalogo" 
							id="btnConsultar" accesskey="c" onclick="Richfaces.showModalPanel('mp');" >
						</h:commandButton>
					</h:panelGrid>
					<!-- Catalogo 1 - BANCOS -->
					<rich:panel id="pnlc1" rendered="#{catAvon.pnl1}">
						<h:outputLabel value="#{msg.tituloAvonCat1}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog1">
							<h:panelGroup>
									<h:commandButton action="#{catAvon.exportarGridExcel}"  value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_BANK'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_BANK'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_BANK'] }"> </h:commandButton>
									<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.bancos}" var="banco" id="tblBanco" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                    <rich:column> <h:outputText value="Clave" /></rich:column>
						                    <rich:column> <h:outputText value="Nombre" /></rich:column>
						                    <rich:column> <h:outputText value="Ultima Actualización" /></rich:column>
						                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
						                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
						                </rich:columnGroup>
						            </f:facet>
									<rich:column><h:outputText value="#{banco.clave}"></h:outputText></rich:column>
									<rich:column><h:outputText value="#{banco.nombre}"></h:outputText></rich:column> 
									<rich:column> <h:outputText value="#{banco.lastupd_ts}" /> </rich:column>
						            <rich:column> <h:outputText value="#{banco.fechaActualizado}" /> </rich:column>
						            <rich:column> <h:outputText value="#{banco.usuarioActualiza}" /> </rich:column>
						            <f:facet name="footer">
						            	<rich:datascroller id="dsC1" for="tblBanco"></rich:datascroller>
						            </f:facet>
								</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					<!-- Catalogo 2 Campanias -->
					<rich:panel id="pnlc2" rendered="#{catAvon.pnl2}">
						<h:outputLabel value="#{msg.tituloAvonCat2}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog2">
							<h:panelGroup>
									<h:commandButton action="#{catAvon.exportarGridExcel}" value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_CAMP'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_CAMP'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridCSV}" id="xpcsvC2" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_CAMP'] }"> </h:commandButton>
									<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.campanias}" var="campania" id="tblCampania" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Campaña" /></rich:column>
					                    <rich:column> <h:outputText value="Año de Campaña" /></rich:column>
					                    <rich:column> <h:outputText value="Zona" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha de Inicio" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Fin" /></rich:column>
					                    <rich:column> <h:outputText value="Ultima Actualización" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                </rich:columnGroup>
					            </f:facet>
								<!-- Aplica columnas de filtros -->
								<rich:column>
					               <f:facet name="header">
					            		<h:inputText value="#{catAvon.campania2}" id="txtFilterCampaniaC2" onkeyup="mandarEnter(event);"
					            					 valueChangeListener="#{catAvon.getCatalogo2CampaniaParams}" />
					            	</f:facet>
					                <h:outputText value="#{campania.campania}" />
					            </rich:column>
					        	<rich:column>
					               <f:facet name="header">
					            		<h:inputText value="#{catAvon.anioCampania2}" id="txtFilterAnioC2" onkeyup="mandarEnter(event);"
					            					 valueChangeListener="#{catAvon.getCatalogo2AnioParams }" />
					            	</f:facet>
					                <h:outputText value="#{campania.anioCampania}" />
					            </rich:column>
					            <rich:column>
					               <f:facet name="header">
					            		<h:inputText value="#{catAvon.zona2}" id="txtFilterZonaC2" onkeyup="mandarEnter(event);"
					            					 valueChangeListener="#{catAvon.getCatalogo2ZonaParams}" />
					            	</f:facet>
					                <h:outputText value="#{campania.descZona}" />
					            </rich:column>
								<rich:column><h:outputText value="#{campania.fechaInicio}"/></rich:column>
								<rich:column><h:outputText value="#{campania.fechaFin}"/></rich:column>
								<rich:column> <h:outputText value="#{campania.lastUpdTs}" /> </rich:column>
					            <rich:column> <h:outputText value="#{campania.fechaActualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{campania.usuarioActualiza}" /> </rich:column>
					            <f:facet name="footer">
						        	<rich:datascroller id="dsC2" for="tblCampania"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					<!-- Catalogo  3  Division -->
					<rich:panel id="pnlc3" rendered="#{catAvon.pnl3}">
						<h:outputLabel value="#{msg.tituloAvonCat3}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog3">
							<h:panelGroup>
									<h:commandButton action="#{catAvon.exportarGridExcel}" value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_DIVISION'] }" > </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_DIVISION'] }" > </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_DIVISION'] }"> </h:commandButton>
									<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.divisiones}" var="division" id="tblDivision" border="1" rows="50">
								<f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Nombre" /></rich:column>
					                    <rich:column> <h:outputText value="Región" /></rich:column>
					                    <rich:column> <h:outputText value="Administrador" /></rich:column>
					                    <rich:column> <h:outputText value="Asistente" /></rich:column>
					                    <rich:column> <h:outputText value="Ultima Actualización" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                </rich:columnGroup>
						        </f:facet>
								<rich:column><h:outputText value="#{division.nombre}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{division.areaGeografica}"></h:outputText></rich:column> 
								<rich:column><h:outputText value="#{division.admor}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{division.asistente}"></h:outputText></rich:column>
								<rich:column> <h:outputText value="#{division.lastupd_ts}" /> </rich:column>
					            <rich:column> <h:outputText value="#{division.fechaActualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{division.usuarioActualiza}" /> </rich:column>
					            <f:facet name="footer">
						        	<rich:datascroller id="dsC3" for="tblDivision"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					<!-- Catalogo  4  Zona -->
					<rich:panel id="pnlc4" rendered="#{catAvon.pnl4}">
						<h:outputLabel value="#{msg.tituloAvonCat4}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog4">
							<h:panelGroup>
								<h:commandButton action="#{catAvon.exportarGridExcel}" value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_ZONA'] }"> </h:commandButton>
								<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_ZONA'] }"> </h:commandButton>
								<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_ZONA'] }"> </h:commandButton>
								<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.zonas}" var="zona" id="tblZona" border="1" rows="50">
								<f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Zona" /></rich:column>
					                    <rich:column> <h:outputText value="Tipo de Zona" /></rich:column>
					                    <rich:column> <h:outputText value="Nombre Gerente Zonal" /></rich:column>
					                    <rich:column> <h:outputText value="Año de Campaña Actual" /></rich:column>
					                    <rich:column> <h:outputText value="Campaña" /></rich:column>
					                    <rich:column> <h:outputText value="LDC" /></rich:column>
					                    <rich:column> <h:outputText value="División" /></rich:column>
					                    <rich:column> <h:outputText value="Ultima Actualización" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                </rich:columnGroup>
						        </f:facet>
								<rich:column><h:outputText value="#{zona.zona}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{zona.tipoZona}"></h:outputText></rich:column> 
								<rich:column><h:outputText value="#{zona.nombreGerenteZonal}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{zona.anioCampaniaActual}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{zona.campania}"></h:outputText></rich:column> 
								<rich:column><h:outputText value="#{zona.descLDC}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{zona.descDivision}"></h:outputText></rich:column>
								<rich:column> <h:outputText value="#{zona.lastUpdTs}" /> </rich:column>
					            <rich:column> <h:outputText value="#{zona.fechaActualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{zona.usuarioActualiza}" /> </rich:column>
					            <f:facet name="footer">
						        	<rich:datascroller id="dsC4" for="tblZona"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					
					<!-- Catalogo  5  Representante -->
					<rich:panel id="pnlc5" rendered="#{catAvon.pnl5}">
						<h:outputLabel value="#{msg.tituloAvonCat5}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog5">
						
						<t:fieldset legend="#{msg.representaFiltros}">
							<h:panelGrid id="pnlRepresenFiltros">
								<h:panelGrid id="pnlRepresentanteFiltros" columns="4">
																				
									<h:outputLabel value="Zona" id="lblZonaRep" styleClass="label"></h:outputLabel>
									<h:selectOneMenu id="cmbxZonaRep" value="#{catAvon.zonaFiltro}"  title="Selecciona una opción"
										onchange="submit()" valueChangeListener="#{catAvon.getRutasPorZona}">
										<f:selectItems value="#{catAvon.listZonasFiltro}"/>
									</h:selectOneMenu>
										
									<h:outputLabel value="Ruta" id="lblRutaRep" styleClass="label"></h:outputLabel>
									<h:selectOneMenu id="cmbxRutaRep" value="#{catAvon.rutaFiltro}"  title="Selecciona una opción"
										onchange="submit()" valueChangeListener="#{catAvon.limpiaGridRepresentantes}">
										<f:selectItems value="#{catAvon.listRutasFiltro}"/>
									</h:selectOneMenu>
									
									<h:outputLabel value="Campaña" id="lblCampaniaRep" styleClass="label"></h:outputLabel>
									<h:selectOneMenu id="cmbxCampaniaRep" value="#{catAvon.campaniaFiltro}"  title="Selecciona una opción"
										onchange="submit()" valueChangeListener="#{catAvon.limpiaGridRepresentantes}">
										<f:selectItems value="#{catAvon.listCampaniasFiltro}"/>
									</h:selectOneMenu>
									
									<h:outputLabel value="Registro" id="lblAccountRep" styleClass="label"></h:outputLabel>
									<h:inputText value="#{catAvon.accountFiltro}" id="txtAccountFiltro" onkeypress="return validaEnter(event, 'frmGeneral:btnConsultarRep');" />
										
								</h:panelGrid>
								<h:panelGroup>
									<h:commandButton id="btnConsultarRep" action="#{catAvon.obtenerCatalogo5}" value="Consultar" ></h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridExcel}" value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_REPRESENTANTE'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_REPRESENTANTE'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_REPRESENTANTE'] }"> </h:commandButton>
									<h:commandButton id="btnFiltrarRep" action="#{catAvon.filtraRepresentantes}" value="Consultar"
										style="display:none;" ></h:commandButton>
									<!-- Mensaje de Respuesta del WS -->
									<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
								</h:panelGroup>
							</h:panelGrid>
						</t:fieldset>
						
							<t:fieldset legend="#{msg.representac4}">
								<h:panelGrid id="pnlRepresen"
									binding="#{catAvon.pnlRepresen}">
									<h:panelGrid id="pnlRepresentante" columns="4">
										<h:outputLabel value="Registro : " id="lblRegistro1" styleClass="label"></h:outputLabel>
										<h:outputLabel value="#{catAvon.registro}" id="lblRegistro" styleClass="label"></h:outputLabel>
										<h:outputLabel value="Nombre :" id="lblNombre1" styleClass="label"></h:outputLabel>
										<h:outputLabel value="#{catAvon.nombre}" id="lblNom" styleClass="label"></h:outputLabel>
																				
										<h:outputLabel value="#{msg.siTrabajac4}" id="lblTrabaja" styleClass="label"></h:outputLabel>
										<h:selectOneMenu id="cmbxTrabajaC5" value="#{catAvon.trabaja}"  title="Selecciona una opción">
											<f:selectItems value="#{catAvon.cargaTrabajos}"/>
										</h:selectOneMenu>
										<h:outputLabel value="#{msg.siPagoc4}" id="lblPagoEfectivoC5" styleClass="label"></h:outputLabel>
										<h:selectOneMenu id="cmbxPagoEfectivoC5" value="#{catAvon.pagoEfectivo}"  title="Selecciona una opción">
											<f:selectItems value="#{catAvon.cargaPagoEfectivo}"/>
										</h:selectOneMenu>
										<h:outputLabel value="Domicilio incorrecto" id="lblDomIncorrectoC5" styleClass="label"></h:outputLabel>
										<h:selectBooleanCheckbox value="#{catAvon.domIncorrecto}" disabled="#{!catAvon.domIncorrecto }">
						            	</h:selectBooleanCheckbox>
									</h:panelGrid>
									<h:panelGroup>
										<h:commandButton action="#{catAvon.actualizarCatalogoRepresentante}" value="Guardar" accesskey="x" disabled="#{catAvon.habilitaBtnEditRep}"></h:commandButton>
										<h:commandButton action="#{catAvon.cancelarCatalogo}" value="Cancelar" accesskey="p" > </h:commandButton>
									</h:panelGroup>
								</h:panelGrid>
							</t:fieldset>
							
							<rich:dataTable value="#{catAvon.representantes}" var="representante" id="tblRepresentante" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Registro" /></rich:column>
					                    <rich:column> <h:outputText value="Nombre" /></rich:column>
					                    <rich:column> <h:outputText value="Domicilio 1" /></rich:column>
					                    <rich:column> <h:outputText value="Domicilio 2" /></rich:column>
					                    <rich:column> <h:outputText value="Domicilio 3" /></rich:column>
					                    <rich:column> <h:outputText value="Código Postal" /></rich:column>
					                    <rich:column> <h:outputText value="Monto Actual" /></rich:column>
					                    <rich:column> <h:outputText value="Monto Previo" /></rich:column>
					                    <rich:column> <h:outputText value="Teléfono" /></rich:column>
					                    <rich:column> <h:outputText value="Pago en Efectivo" /></rich:column>
					                    <rich:column> <h:outputText value="Trabaja" /></rich:column>
					                    <rich:column> <h:outputText value="Zona" /></rich:column>
					                    <rich:column> <h:outputText value="Clave de Ruta" /></rich:column>
					                    <rich:column> <h:outputText value="Estatus" /></rich:column>
					                     <rich:column> <h:outputText value="Ultima Actualización" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
					                </rich:columnGroup>
					            </f:facet>
					            <!-- Aplica columnas de filtros -->
								<rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.registro5}" id="txtFilterRegC5" onkeypress="return validaEnter(event, 'frmGeneral:btnFiltrarRep');" />
					                </f:facet>
					                <h:outputText value="#{representante.registro}" />
					            </rich:column>
					            <rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.nombre5}" id="txtFilterAnioC5" size="10" onkeypress="return validaEnter(event, 'frmGeneral:btnFiltrarRep');" />
					                </f:facet>
					                <h:outputText value="#{representante.nombre}" />
					            </rich:column>
					            <rich:column><h:outputText value="#{representante.domicilio}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{representante.estado}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{representante.poblacion}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{representante.coPostal}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{representante.montoActual}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{representante.montoPrevio}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{representante.telefono}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{representante.siPagoEfectivo}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{representante.siTrabaja}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{representante.descZona}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{representante.cveRuta}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{representante.estatus}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{representante.lastUpdTs}"></h:outputText></rich:column>
					            <rich:column> <h:outputText value="#{representante.fechaActualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{representante.usuarioActualiza}" /> </rich:column>
					            <rich:column>
					            	<rich:panel>
					            		<h:commandButton action="#{catAvon.modificarCatalogo}" value="Modificar" >
					            			<f:setPropertyActionListener  value="#{representante.idRepresentante}" target="#{catAvon.idRepresentante}"/>
					            			<f:setPropertyActionListener  value="#{representante.nombre}" target="#{catAvon.nombre}"/>
					            			<f:setPropertyActionListener  value="#{representante.registro}" target="#{catAvon.registro}"/>
											<f:setPropertyActionListener  value="#{representante.trabaja}" target="#{catAvon.trabaja }"/>
											<f:setPropertyActionListener  value="#{representante.pagoEfectivo}" target="#{catAvon.pagoEfectivo }"/>
											<f:setPropertyActionListener  value="#{representante.domIncorrecto}" target="#{catAvon.domIncorrecto }"/>
										</h:commandButton>
					            	</rich:panel>
					            </rich:column>
					            <f:facet name="footer">
						        	<rich:datascroller id="dsC5" for="tblRepresentante"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
							
						</h:panelGrid>
					</rich:panel>
					
					
					<!-- Catalogo  6 - TIPO DE PAGO -->
					<rich:panel id="pnlc6" rendered="#{catAvon.pnl6}">
						<h:outputLabel value="#{msg.tituloAvonCat6}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog6">
							<h:panelGroup>
									<h:commandButton action="#{catAvon.exportarGridExcel}" value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_TIPOPAGO'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_TIPOPAGO'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_TIPOPAGO'] }"> </h:commandButton>
									<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.tiposPago}" var="tipoPago" id="tblTipago" border="1">
								<f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Descripción" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                </rich:columnGroup>
						        </f:facet>
								<rich:column><h:outputText value="#{tipoPago.descripcion}"></h:outputText></rich:column>
					            <rich:column> <h:outputText value="#{tipoPago.fechaActualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{tipoPago.usuarioActualiza}" /> </rich:column>
					             <f:facet name="footer">
						        	<rich:datascroller id="dsC6" for="tblTipago"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					<!-- Catalogo  7  Orden -->
					<rich:panel id="pnlc7" rendered="#{catAvon.pnl7}">
						<h:outputLabel value="#{msg.tituloAvonCat7}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog7">
							<h:panelGroup>
									<h:commandButton action="#{catAvon.exportarGridExcel}" value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_ORDEN'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_ORDEN'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_ORDEN'] }"> </h:commandButton>
									<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.ordenes}" var="orden" id="tblOrden" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Clave de Orden" /></rich:column>
					                    <rich:column> <h:outputText value="Registro Representante" /></rich:column>
					                    <rich:column> <h:outputText value="Año de Campaña" /></rich:column>
					                    <rich:column> <h:outputText value="Campaña" /></rich:column>
					                    <rich:column> <h:outputText value="Clave de Ruta" /></rich:column>
					                    <rich:column> <h:outputText value="Primera Orden" /></rich:column>
					                    <rich:column> <h:outputText value="Cantidad de Cajas" /></rich:column>
					                    <rich:column> <h:outputText value="LDC" /></rich:column>
					                    <rich:column> <h:outputText value="Ultima Actualización" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                </rich:columnGroup>
					            </f:facet>
								<!-- Aplica columnas de filtros -->
								<rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.orden7}" id="txtFilterOrdenC7"
					            					 valueChangeListener="#{catAvon.getCatalogo7OrdenParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{orden.cveOrden}" />
					            </rich:column>
								<rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.registro7}" id="txtFilterRepC7"
					            					 valueChangeListener="#{catAvon.getCatalogo7RegistroParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{orden.descRepresentante}" />
					            </rich:column>
					            <rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.anioCampania7}" id="txtFilterAnioC7"
					            					 valueChangeListener="#{catAvon.getCatalogo7AnioParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{orden.anio}" />
					            </rich:column>
					            <rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.campania7}" id="txtFilterCampC7"
					            					 valueChangeListener="#{catAvon.getCatalogo7CampaniaParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{orden.descCampania}" />
					            </rich:column>
					          	<rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.ruta7}" id="txtFilterRutaC7"
					            					 valueChangeListener="#{catAvon.getCatalogo7RutaParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{orden.cveRuta}" />
					            </rich:column>
								<rich:column> <h:outputText value="#{orden.descPrimeraOrden}" /> </rich:column>
								<rich:column> <h:outputText value="#{orden.noCajas}" /> </rich:column>
								<rich:column> <h:outputText value="#{orden.descLDC}" /> </rich:column>
								<rich:column> <h:outputText value="#{orden.lastupd_ts}" /> </rich:column>
					            <rich:column> <h:outputText value="#{orden.fecha_actualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{orden.usuario_actualiza}" /> </rich:column>
					            <f:facet name="footer">
						        	<rich:datascroller id="dsC7" for="tblOrden"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					<!-- Catalogo  8  RAZON DE DEVOLUCION -->
					<rich:panel id="pnlc8" rendered="#{catAvon.pnl8}">
						<h:outputLabel value="#{msg.tituloAvonCat8}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog8">
							<h:panelGroup>
									<h:commandButton action="#{catAvon.exportarGridExcel}" value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_RDEVOLUCION'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_RDEVOLUCION'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_RDEVOLUCION'] }"> </h:commandButton>
									<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.razonesDeDevolucion}" var="razonDevolucion" id="tblRazonDevolucion" border="1" rows="50">
								<f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Descripción" /></rich:column>
					                    <rich:column> <h:outputText value="Ultima Actualización" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                </rich:columnGroup>
						        </f:facet>
								<rich:column> <h:outputText value="#{razonDevolucion.descripcion}"></h:outputText></rich:column>
								<rich:column> <h:outputText value="#{razonDevolucion.lastupd_ts}" /></rich:column>
					            <rich:column> <h:outputText value="#{razonDevolucion.fechaActualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{razonDevolucion.usuarioActualiza}" /> </rich:column>
					            <f:facet name="footer">
						        	<rich:datascroller id="dsC8" for="tblRazonDevolucion"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					<!-- Catalogo  9 tIPO DE LIQUIDACION -->
					<rich:panel id="pnlc9" rendered="#{catAvon.pnl9}">
						<h:outputLabel value="#{msg.tituloAvonCat9}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog9">
							<h:panelGroup>
									<h:commandButton action="#{catAvon.exportarGridExcel}" value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_TLIQUIDA'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_TLIQUIDA'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_TLIQUIDA'] }"> </h:commandButton>
									<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.tiposLiquidacion}" var="tipoLiquidacion" id="tblTipoLiquidacion" border="1" rows="50">
								<f:facet name="header">
					                <rich:columnGroup>
					                	<rich:column> <h:outputText value="Clave" /></rich:column>
					                    <rich:column> <h:outputText value="Descripción" /></rich:column>
					                    <rich:column> <h:outputText value="Ultima Actualización" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                </rich:columnGroup>
						        </f:facet>
								<rich:column> <h:outputText value="#{tipoLiquidacion.clave}"></h:outputText></rich:column>
								<rich:column> <h:outputText value="#{tipoLiquidacion.descripcion}"></h:outputText></rich:column>
								<rich:column> <h:outputText value="#{tipoLiquidacion.lastupd_ts}" /></rich:column>
					            <rich:column> <h:outputText value="#{tipoLiquidacion.fechaActualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{tipoLiquidacion.usuarioActualiza}" /> </rich:column>
					            <f:facet name="footer">
						        	<rich:datascroller id="dsC9" for="tblTipoLiquidacion"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					<!-- Catalogo  10  ESTADO DE ORDEN -->
					<rich:panel id="pnlc10" rendered="#{catAvon.pnl10}">
						<h:outputLabel value="#{msg.tituloAvonCat10}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog10">
							<h:panelGroup>
									<h:commandButton action="#{catAvon.exportarGridExcel}" value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_EDORDEN'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_EDORDEN'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_EDORDEN'] }"> </h:commandButton>
									<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.edosOrden}" var="edoOrden" id="tblEdoOrden" border="1" rows="50">
								<f:facet name="header">
					                <rich:columnGroup>
					                	<rich:column> <h:outputText value="Tipo" /></rich:column>
					                    <rich:column> <h:outputText value="Descripción" /></rich:column>
					                    <rich:column> <h:outputText value="Ultima Actualización" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                </rich:columnGroup>
						        </f:facet>
								<rich:column> <h:outputText value="#{edoOrden.tipoDlv}"></h:outputText></rich:column>
								<rich:column> <h:outputText value="#{edoOrden.descripcion}"></h:outputText></rich:column>
								<rich:column> <h:outputText value="#{edoOrden.lastupd_ts}" /></rich:column>
					            <rich:column> <h:outputText value="#{edoOrden.fechaActualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{edoOrden.usuarioActualiza}" /> </rich:column>
					            <f:facet name="footer">
						        	<rich:datascroller id="dsC10" for="tblEdoOrden"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					<!-- Catalogo  11  TIPO DE SINIESTRO -->
					<rich:panel id="pnlc11" rendered="#{catAvon.pnl11}">
						<h:outputLabel value="#{msg.tituloAvonCat11}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog11">
							<h:panelGroup>
									<h:commandButton action="#{catAvon.exportarGridExcel}"  value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_TSINIESTRO'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_TSINIESTRO'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_TSINIESTRO'] }"> </h:commandButton>
									<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.tipoSiniestros}" var="tipoSiniestro" id="tblTipoSiniestro" border="1" rows="50">
								<f:facet name="header">
					                <rich:columnGroup>
					                	<rich:column> <h:outputText value="Clave" /></rich:column>
					                    <rich:column> <h:outputText value="Descripción" /></rich:column>
					                    <rich:column> <h:outputText value="Ultima Actualización" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                </rich:columnGroup>
						        </f:facet>
								<rich:column> <h:outputText value="#{tipoSiniestro.clave}"></h:outputText></rich:column>
								<rich:column> <h:outputText value="#{tipoSiniestro.descripcion}"></h:outputText></rich:column>
								<rich:column> <h:outputText value="#{tipoSiniestro.lastupd_ts}" /></rich:column>
					            <rich:column> <h:outputText value="#{tipoSiniestro.fechaActualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{tipoSiniestro.usuarioActualiza}" /> </rich:column>
					            <f:facet name="footer">
						        	<rich:datascroller id="dsC11" for="tblTipoSiniestro"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					<!-- Catalogo  12  uNITARIOS -->
					<rich:panel id="pnlc12" rendered="#{catAvon.pnl12}">
						<h:outputLabel value="#{msg.tituloAvonCat12}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog12">
							<h:panelGroup>
									<h:commandButton action="#{catAvon.exportarGridExcel}"  value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_UNITARIO'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_UNITARIO'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_UNITARIO'] }"> </h:commandButton>
									<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.unitarios}" var="unitario" id="tblUnitario" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Descripción" /></rich:column>
					                    <rich:column> <h:outputText value="Tipo" /></rich:column>
					                    <rich:column> <h:outputText value="Clave de Orden" /></rich:column>
					                    <rich:column> <h:outputText value="Código de Barras" /></rich:column>
					                    <rich:column> <h:outputText value="Campaña" /></rich:column>
					                    <rich:column> <h:outputText value="Año de Campaña" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                </rich:columnGroup>
					            </f:facet>
					            <rich:column><h:outputText value="#{unitario.descripcion}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{unitario.clave}"></h:outputText></rich:column>
								<!-- Aplica columnas de filtros -->
								<rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.orden12}" id="txtFilterOrdenC12"
					            					 valueChangeListener="#{catAvon.getCatalogo12OrdenParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{unitario.orden}" />
					            </rich:column>
								<rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.codigo12}" id="txtFilterCodigoC12"
					            					 valueChangeListener="#{catAvon.getCatalogo12CodigoParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{unitario.codigo}" />
					            </rich:column>
								<rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.campania12}" id="txtFilterCampC12"
					            					 valueChangeListener="#{catAvon.getCatalogo12CampaniaParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{unitario.campania}" />
					            </rich:column>
					            <rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.anioCampania12}" id="txtFilterAnioC12"
					            					 valueChangeListener="#{catAvon.getCatalogo12AnioParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{unitario.anio}" />
					            </rich:column>
					            <rich:column> <h:outputText value="#{unitario.fechaActualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{unitario.usuarioActualiza}" /> </rich:column>
					            <f:facet name="footer">
						        	<rich:datascroller id="dsC12" for="tblUnitario"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					<!-- Catalogo  13 PREMIOS -->
					<rich:panel id="pnlc13" rendered="#{catAvon.pnl13}">
						<h:outputLabel value="#{msg.tituloAvonCat13}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog13">
							<h:panelGroup>
									<h:commandButton action="#{catAvon.exportarGridExcel}" value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_PREMIO'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_PREMIO'] }"> </h:commandButton>
									<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_PREMIO'] }"> </h:commandButton>
									<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.premios}" var="premio" id="tblPremio" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Descripción" /></rich:column>
					                    <rich:column> <h:outputText value="Tipo" /></rich:column>
					                    <rich:column> <h:outputText value="Clave de Orden" /></rich:column>
					                    <rich:column> <h:outputText value="Código de Barras" /></rich:column>
					                    <rich:column> <h:outputText value="Campaña" /></rich:column>
					                    <rich:column> <h:outputText value="Año de Campaña" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                </rich:columnGroup>
					            </f:facet>
					            <rich:column><h:outputText value="#{premio.descripcion}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{premio.registro}"></h:outputText></rich:column>
								<!-- Aplica columnas de filtros -->
								<rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.orden13}" id="txtFilterOrdenC13"
					            					 valueChangeListener="#{catAvon.getCatalogo13OrdenParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{premio.claveOrden}" />
					            </rich:column>
								<rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.codigo13}" id="txtFilterCodigoC13"
					            					 valueChangeListener="#{catAvon.getCatalogo13CodigoParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{premio.code}" />
					            </rich:column>
								<rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.campania13}" id="txtFilterCampC13"
					            					 valueChangeListener="#{catAvon.getCatalogo13CampaniaParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{premio.campania}" />
					            </rich:column>
					            <rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.anioCampania13}" id="txtFilterAnioC13"
					            					 valueChangeListener="#{catAvon.getCatalogo13AnioParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{premio.anio}" />
					            </rich:column>
					            <rich:column> <h:outputText value="#{premio.fechaActualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{premio.usuarioActualiza}" /> </rich:column>
					            <f:facet name="footer">
						        	<rich:datascroller id="dsC13" for="tblPremio"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					<!-- Catalogo  14 PROGRAMACION DE REPARTO -->
					<rich:panel id="pnlc14" rendered="#{catAvon.pnl14}">
						<h:outputLabel value="#{msg.tituloAvonCat14}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog14">
						<h:panelGroup>
								<h:commandButton action="#{catAvon.exportarGridExcel}"  value="Exportar a Excel" disabled="#{!catAvon.mapaAccion['XPORT_PROGRA'] }"> </h:commandButton>
								<h:commandButton action="#{catAvon.exportarGridPDF}" value="Exportar a PDF" disabled="#{!catAvon.mapaAccion['XPORT_PROGRA'] }"> </h:commandButton>
								<h:commandButton action="#{catAvon.exportarGridCSV}" value="Exportar a CSV" disabled="#{!catAvon.mapaAccion['XPORT_PROGRA'] }"> </h:commandButton>
								<h:outputLabel value="#{catAvon.msg}" style="color:#FF0000;"></h:outputLabel>
							</h:panelGroup>
							<rich:dataTable value="#{catAvon.progRepartos}" var="progReparto" id="tblProgramacionReparto" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Campaña" /></rich:column>
					                    <rich:column> <h:outputText value="Año de Campaña" /></rich:column>
					                    <rich:column> <h:outputText value="Zona" /></rich:column>
					                    <rich:column> <h:outputText value="Número de Cuarto Año" /></rich:column>
					                    <rich:column> <h:outputText value="Año" /></rich:column>
					                    <rich:column> <h:outputText value="Correo Electrónico" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha de Facturación" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha de Llegada a LDC" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha de Primer Día de Reparto" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha de Último Día de Reparto" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha de Último Día en Bódega" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha de Cierre" /></rich:column>
					                    <rich:column> <h:outputText value="División" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha Actualizado" /> </rich:column>
					                    <rich:column> <h:outputText value="Usuario Actualiza" /> </rich:column>
					                </rich:columnGroup>
					            </f:facet>
					            <!-- Aplica columnas de filtros -->
					            <rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.campania14}" id="txtFilterCampC14"
					            					 valueChangeListener="#{catAvon.getCatalogo14CampaniaParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{progReparto.descCampania}" />
					            </rich:column>
					            <rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.anioCampania14}" id="txtFilterAnioC14"
					            					 valueChangeListener="#{catAvon.getCatalogo14AnioParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{progReparto.anioCampania}" />
					            </rich:column>
					             <rich:column>
					                <f:facet name="header">
					                    <h:inputText value="#{catAvon.zona14}" id="txtFilterZonaC14"
					            					 valueChangeListener="#{catAvon.getCatalogo14ZonaParams}" onkeyup="mandarEnter(event);" />
					                </f:facet>
					                <h:outputText value="#{progReparto.descZona}" />
					            </rich:column>
								<rich:column><h:outputText value="#{progReparto.noCuartoAnio}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{progReparto.anio}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{progReparto.mail}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{progReparto.feFacturacion}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{progReparto.feLlegadaLDC}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{progReparto.feDiaRepartoPrimero}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{progReparto.feDiaRepartoUltimo}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{progReparto.feUltimoDiaBodega}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{progReparto.feCierre}"></h:outputText></rich:column>
								<rich:column><h:outputText value="#{progReparto.descDivision}"></h:outputText></rich:column>
					            <rich:column> <h:outputText value="#{progReparto.feActualizado}" /> </rich:column>
					            <rich:column> <h:outputText value="#{progReparto.usuarioActualiza}" /> </rich:column>
					            <f:facet name="footer">
						        	<rich:datascroller id="dsC14" for="tblProgramacionReparto"></rich:datascroller>
						        </f:facet>
							</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
				</h:panelGrid>
			</h:form>
	</ui:define>
</ui:composition>
</f:view>
</body>
</html>