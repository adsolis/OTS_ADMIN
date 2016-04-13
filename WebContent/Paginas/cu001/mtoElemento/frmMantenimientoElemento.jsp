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
	<title>Mantenimiento de Elemento</title>
</head>
<body>
<f:view>
<!-- Se agrego composition para masterPAge -->
<ui:composition template="/Paginas/template.jsp">
	<ui:define name="content">
	<!--  Mensajes de la Vista -->
	<f:loadBundle basename="com.datacode.avon_ots_admin.cu001.messages" var="msg" />
	
	<script type="text/javascript">
			
			function validaCodigoBarras(msg) {
				if (msg == "") {
					document.getElementById("frmGeneral:lnkGenerarCodigosBarras").click();
				}
			}
			
	</script>
	
	
			<!-- Diseno de Pagina -->
			<h:form binding="#{mto.form}" styleClass="todo" id="frmGeneral"  enctype="multipart/form-data">
			<h:outputLabel value="#{msg.titulo}" styleClass="tituloPagina"/>
			<div align="left">
					<h:panelGrid columns="3" id="pnlCargarCatalogo">
						<h:outputLabel value="#{msg.cat}" id="lblCatalogo" styleClass="label"/>
						<h:selectOneMenu    id="cmbxCatalogo" value="#{mto.idCatalogo}">
							<f:selectItems value="#{mto.cargaCatalogos}" />
						</h:selectOneMenu>
						<h:commandButton actionListener="#{mto.cargarCatalogoSeleccionado}" value="Buscar Catalogo" 
							id="btnCargar" accesskey="b" >
						</h:commandButton>
					</h:panelGrid>
					</div>
			<div align="center">
				<h:panelGrid columns="1" id="pnlGeneral" style="text-align:center">
					
									
					<!-- Catalogo 1 - Unidades de Reparto - PW_UNIDAD_REPARTO -->
					<rich:panel id="pnlc1" rendered="#{mto.pnl1 }">
						<h:outputLabel value="#{msg.tituloCat1}" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog1" columns="6" rendered="true">
							<h:outputLabel value="#{msg.cvePais}" id="lblCvePaisC1" styleClass="label"/>
							<h:selectOneMenu  id="cmbxPaisC1"  disabled="true"
								 label="Pais" title="Selecciona una opción" value="#{mto.idPais}">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.cveLDC}" id="lblCveLDCC1" styleClass="label"/>
							<h:selectOneMenu  id="cmbxLDCC1"  disabled="true" value="#{mto.idLDC}" label="LDC" >
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.modelC1}" id="lblMarcaC1" styleClass="label"/>
							<h:selectOneMenu id="cmbxMarcaC1" 
								value="#{mto.idMarca}" label="Marca" title="Selecciona una opción">
								<f:selectItems value="#{mto.listaModelo}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.anio}" id="lblAnioC1" styleClass="label"/>
							<h:inputText id="txtAnioC1" styleClass="caja" label="Año" maxlength="4"  value="#{mto.anio }">
							</h:inputText>
							<h:outputLabel value="#{msg.noSerie}" id="lblNoSerieC1" styleClass="label"/>
							<h:inputText value="#{mto.noSerie }" id="txtNoSerieC1" styleClass="caja" maxlength="99" label="Número de Serie">
								<f:validateLength maximum="100" />
							</h:inputText>
							
							<h:outputLabel value="#{msg.noEco}" id="lblNoEconC1" styleClass="label"/>
							<h:inputText value="#{mto.noEconomico}" id="txtNoEconC1" styleClass="caja" maxlength="99" label="Número Económico">
								<f:validateLength maximum="100" />
							</h:inputText>
							<h:outputLabel value="#{msg.placas}" id="lblPlacasC1" styleClass="label"/>
							<h:inputText value="#{mto.placas}" id="txtPlacasC1" styleClass="caja" label="Placas" maxlength="10"/>
							<h:outputLabel value="#{msg.color}" id="lblColorC1" styleClass="label"/>
							<h:inputText value="#{mto.color}" id="txtColorC1" styleClass="caja"  maxlength="10" label="Color">
								<f:validateLength maximum="50" />
							</h:inputText>
							
							<h:outputLabel value="#{msg.cap}" id="lblCapacidadC1" styleClass="label"/>
							<h:inputText value="#{mto.capacidadNoCajas}" id="txtCapacidadC1" styleClass="caja" label="Capacidad" maxlength="5"/>
							<h:outputLabel value="#{msg.rendimient}" id="lblRen" styleClass="label"/>
							<h:inputText value="#{mto.rendimiento}" id="txtRendimientoC1" styleClass="caja" label="Rendimiento" maxlength="5"/>
							<h:outputLabel value="#{msg.edo}" id="lblEdoC1" styleClass="label"/>
							<h:selectOneMenu id="cmbxEstatusC1" value="#{mto.idEstatus}" title="Selecciona una opción">
								<f:selectItems value="#{mto.listaEstatus}"/>
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.codBarras}" id="lblCodBarrasC1" styleClass="label"/>
							<h:inputText value="#{mto.codigoBarras}" id="txtCodBarrasC1" styleClass="caja" label="Código de Barras" maxlength="16"/>
							
							<h:outputLabel value="#{msg.entregando}" id="lblEntregandoC1" styleClass="label"/>
							<h:selectBooleanCheckbox value="#{mto.entregandoCheck}" id="cbEntregandoC1" onchange="javascript:onchangeEntregandoC1();"></h:selectBooleanCheckbox>
							
							<h:outputLabel value="#{msg.kilometraje}" id="lblKilometrajeC1" styleClass="label"/>
							<h:inputText value="#{mto.kilometraje}" id="txtKilometrajeC1" styleClass="caja" label="Kilometraje" 
								maxlength="6" onkeypress="return validaNumeros(event);" />
							<script>
								onchangeEntregandoC1();
							</script>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC1()" disabled="#{!mto.mapaAccion['ADD_UNIDADREPARTO'] }"/>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"/> 
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_UNIDADREPARTO'] }"/>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT1'] }"/> 
							<h:commandButton action="#{mto.exportarGridPDF}" value="Exportar a PDF" disabled="#{!mto.mapaAccion['EXPORT_CAT1'] }"/> 
							
							<a4j:commandButton action="#{mto.validarCodigosBarras}" 
								value="Imprimir codigo de barras" id="lnkValidarCodigosBarras" 
								disabled="#{!mto.mapaAccion['EXPORT_CAT1'] }"
								oncomplete="validaCodigoBarras('#{mto.msg}')"
								reRender="frmGeneral">
							</a4j:commandButton>
							<h:commandButton action="#{mto.generarCodigosBarras}"  style="display:none;"
								value="Imprimir codigo de barras" id="lnkGenerarCodigosBarras" 
								disabled="#{!mto.mapaAccion['EXPORT_CAT1'] }"
								onclick="this.form.target='_blank'">
							</h:commandButton>
							
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC1Tabla">
								<rich:dataTable value="#{mto.unidades}" var="unidad" id="tblUnidadReparto" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                	<rich:column> <h:outputText value="Imprimir código de barras" />
						                		<h:commandButton action="#{mto.selecTodasUnidadesReparto}" value="Marcar/Desmarcar">
												</h:commandButton>
						                	</rich:column>
						                	<rich:column> <h:outputText value="País" /></rich:column>
						                    <rich:column> <h:outputText value="LDC" /></rich:column>
						                    <rich:column> <h:outputText value="Marca" /></rich:column>
						                    <rich:column> <h:outputText value="Año" /> </rich:column>
						                    <rich:column> <h:outputText value="Número de Serie" /> </rich:column>
						                    <rich:column> <h:outputText value="Número Económico" /></rich:column>
						                    <rich:column> <h:outputText value="Placas" /> </rich:column>
						                    <rich:column> <h:outputText value="Color" /></rich:column>
						                    <rich:column> <h:outputText value="Capacidad de Carga" /></rich:column>
						                    <rich:column> <h:outputText value="Rendimiento Promedio" /> </rich:column>
						                    <rich:column> <h:outputText value="Código de Barras" /> </rich:column>
						                    <rich:column> <h:outputText value="Estatus" /> </rich:column>
						                    <rich:column> <h:outputText value="Entregando" /> </rich:column>
						                    <rich:column> <h:outputText value="Kilometraje" /> </rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						                </rich:columnGroup>
						            </f:facet>
						            <rich:column>
						            	<h:selectBooleanCheckbox value="#{unidad.imprCodBarras}">
						            	</h:selectBooleanCheckbox>
						            </rich:column>
									<rich:column><h:outputText value="#{unidad.descPais}"></h:outputText></rich:column>
									<rich:column><h:outputText value="#{unidad.descLDC}"></h:outputText></rich:column>
									<rich:column> <h:outputText value="#{unidad.descMarca}" /> </rich:column>
						            <rich:column> <h:outputText value="#{unidad.anio}" /> </rich:column>
						            <rich:column> <h:outputText value="#{unidad.noSerie}" /> </rich:column>
						            <rich:column> <h:outputText value="#{unidad.noEconomico}"/> </rich:column>
						            <rich:column> <h:outputText value="#{unidad.placas}"/> </rich:column>
						            <rich:column> <h:outputText value="#{unidad.color}"/> </rich:column>
						            <rich:column> <h:outputText value="#{unidad.capacidadNoCajas}"/> </rich:column>
						            <rich:column> <h:outputText value="#{unidad.rendimiento}"/> </rich:column>
						            <rich:column> <h:outputText value="#{unidad.codigoBarras}"/> </rich:column>
						            <rich:column> <h:outputText value="#{unidad.descEstatus}"/> </rich:column>
						            <rich:column> <h:outputText value="#{unidad.entregandoMostrar}"/> </rich:column>
						            <rich:column> <h:outputText value="#{unidad.kilometraje}"/> </rich:column>
						            <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" accesskey="m" 
						            			disabled="#{!mto.mapaAccion['UPD_UNIDADREPARTO'] || !mto.modificarActivado }">
												<f:setPropertyActionListener  value="#{unidad.color}" target="#{mto.color }"/>
												<f:setPropertyActionListener  value="#{unidad.placas}" target="#{mto.placas }"/>
												<f:setPropertyActionListener  value="#{unidad.capacidadNoCajas}" target="#{mto.capacidadNoCajas }"/>
												<f:setPropertyActionListener  value="#{unidad.rendimiento}" target="#{mto.rendimiento }"/>
												<f:setPropertyActionListener  value="#{unidad.idEstatus}" target="#{mto.idEstatus }"/>
												<f:setPropertyActionListener  value="#{unidad.idUnidadReparto}" target="#{mto.idUnidadReparto }"/>
												<f:setPropertyActionListener  value="#{unidad.idLDC}" target="#{mto.idLDC }"/>
												<f:setPropertyActionListener  value="#{unidad.idMarca}" target="#{mto.idMarca }"/>
												<f:setPropertyActionListener  value="#{unidad.idPais}" target="#{mto.idPais }"/>
												<f:setPropertyActionListener  value="#{unidad.noSerie}" target="#{mto.noSerie }"/>
												<f:setPropertyActionListener  value="#{unidad.noEconomico}" target="#{mto.noEconomico }"/>
												<f:setPropertyActionListener  value="#{unidad.codigoBarras}" target="#{mto.codigoBarras }"/>
												<f:setPropertyActionListener  value="#{unidad.anio}" target="#{mto.anio }"/>
												<f:setPropertyActionListener  value="#{unidad.entregandoCheck}" target="#{mto.entregandoCheck }"/>
												<f:setPropertyActionListener  value="#{unidad.kilometraje}" target="#{mto.kilometraje }"/>
											</h:commandButton>
											<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" accesskey="e" 
												onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_UNIDADREPARTO'] || !mto.eliminarActivado }">
												<f:setPropertyActionListener  value="#{unidad.idUnidadReparto}" target="#{mto.idUnidadReparto }"/>
											</h:commandButton>
						            	</rich:panel>
						            </rich:column>
						             <f:facet name="footer">
						            	<rich:datascroller id="dsC1" for="tblUnidadReparto"></rich:datascroller>
						            </f:facet>
								</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 2 - Modelo de Camioneta - PW_MODELO -->
					<rich:panel id="pnlc2" rendered="#{mto.pnl2 }">
						<h:outputLabel value="#{msg.tituloCat2}" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog2" columns="6">
							<h:outputLabel value="#{msg.descModelo}" id="lblDescC2" styleClass="label"/>
							<h:inputText value="#{mto.descModelo}" id="txtDescC2" styleClass="caja" maxlength="100"/>
							
							<h:outputLabel value="#{msg.cveMarca}" id="lblCveMarcaC2" styleClass="label"/>
							<h:selectOneMenu id="cmbxMarcaC2" value="#{mto.idMarca}">
								<f:selectItems value="#{mto.listaMarcas}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.frecuencia}" id="lblFrecuenciaC2" styleClass="label"/>
							<h:inputText value="#{mto.frecuencia}" id="txtFrecuenciaC2" styleClass="caja" maxlength="100"/>
						</h:panelGrid>
							<h:panelGroup>
								<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar"
									accesskey="g" onclick="return requeridosC2()"
									disabled="#{!mto.mapaAccion['ADD_MODELO'] }"/>
								<h:commandButton action="#{mto.cancelarCatalogo}"
									value="Cancelar" accesskey="c" />
								<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"
									accesskey="n" disabled="#{!mto.mapaAccion['ADD_MODELO'] }" />
								<h:commandButton action="#{mto.exportarGridExcel}"
									value="Exportar a Excel"
									disabled="#{!mto.mapaAccion['EXPORT_CAT2'] }" />
								<h:commandButton action="#{mto.exportarGridPDF}"
									value="Exportar a PDF"
									disabled="#{!mto.mapaAccion['EXPORT_CAT2'] }" />
								<!-- Mensaje de Respuesta del WS -->
								<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"/>
							</h:panelGroup>
							<h:panelGrid id="pnlC2Tabla">
								<rich:dataTable value="#{mto.modelos}" var="modelo" id="tblModelo" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                    <rich:column> <h:outputText value="Descripción"></h:outputText></rich:column>
						                    <rich:column> <h:outputText value="Marca" /></rich:column>
						                    <rich:column> <h:outputText value="Frecuencia de Mantenimiento" /> </rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						            	</rich:columnGroup>
						        	</f:facet>
						        	<rich:column> <h:outputText value="#{modelo.descModelo}"/> </rich:column>
						            <rich:column> <h:outputText value="#{modelo.descMarca}"/> </rich:column>
						            <rich:column> <h:outputText value="#{modelo.frecuencia}"/> </rich:column>
						             <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" accesskey="m" 
						            			disabled="#{!mto.mapaAccion['UPD_MODELO'] || !mto.modificarActivado }">
						            			<f:setPropertyActionListener  value="#{modelo.idModelo}" target="#{mto.idModelo}"/>
												<f:setPropertyActionListener  value="#{modelo.descModelo}" target="#{mto.descModelo }"/>
												<f:setPropertyActionListener  value="#{modelo.idMarca}" target="#{mto.idMarca }"/>
												<f:setPropertyActionListener  value="#{modelo.frecuencia}" target="#{mto.frecuencia }"/>
											</h:commandButton>
											<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" accesskey="e" 
												onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_MODELO'] || !mto.eliminarActivado }">
												<f:setPropertyActionListener  value="#{modelo.idModelo}" target="#{mto.idModelo}"/>
											</h:commandButton>
						            	</rich:panel>
						            </rich:column>
						            <f:facet name="footer">
						            	<rich:datascroller id="dsC2" for="tblModelo"></rich:datascroller>
						        	</f:facet>
						    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 3 - Marca de Camioneta  - PW_MARCA -->
					<rich:panel id="pnlc3" rendered="#{mto.pnl3 }">
						<h:outputLabel value="#{msg.tituloCat3}" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog3" columns="2">
							<h:outputLabel value="#{msg.descObli}" id="lblDescC3" styleClass="label"/>
							<h:inputText value="#{mto.descMarca }" id="txtDescC3" styleClass="caja" maxlength="100"/>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC3();" disabled="#{!mto.mapaAccion['ADD_MARCA'] }"/>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"/>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_MARCA'] }"/>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT3'] }"/>
							<h:commandButton action="#{mto.exportarGridPDF}" value="Exportar a PDF" disabled="#{!mto.mapaAccion['EXPORT_CAT3'] }"/>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"/>
						</h:panelGroup>
						<h:panelGrid id="pnlC3Tabla">
								<rich:dataTable value="#{mto.marcas}" var="marca" id="tblMarca" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                    <rich:column> <h:outputText value="Descripción de Marca" /></rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						            	</rich:columnGroup>
						        	</f:facet>
						            <rich:column> <h:outputText value="#{marca.descMarca}"/> </rich:column>
						             <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" accesskey="m" 
						            			disabled="#{!mto.mapaAccion['UPD_MARCA'] || !mto.modificarActivado }">
						            			<f:setPropertyActionListener  value="#{marca.idMarca}" target="#{mto.idMarca}"/>
												<f:setPropertyActionListener  value="#{marca.descMarca}" target="#{mto.descMarca }"/>
											</h:commandButton>
											<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" accesskey="e" 
												onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_MARCA'] || !mto.eliminarActivado }">
												<f:setPropertyActionListener  value="#{marca.idMarca}" target="#{mto.idMarca}"/>
											</h:commandButton>
						            	</rich:panel>
						            </rich:column>
						            <f:facet name="footer">
						            	<rich:datascroller id="dsC3" for="tblMarca"></rich:datascroller>
						        	</f:facet>
						    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 4 - Asignación de Unidad de Reparto - PW_ASIGNACION_UNIDAD_REPARTO -->
					<rich:panel id="pnlc4" rendered="#{mto.pnl4 }">
						<h:outputLabel value="#{msg.tituloCat4}" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog4" columns="6">
							<h:outputLabel value="#{msg.cvePais}" id="lblCvePaisC4" styleClass="label"/>
							<h:selectOneMenu id="cmbxPaisC4" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.cveLDC}" id="lblCveLDCC4" styleClass="label"/>
							<h:selectOneMenu id="cmbxLDCC4" value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
							
							
							
							<h:outputLabel value="#{msg.empleado}" id="lblEmpleadoC4" styleClass="label"/>
							<h:selectOneMenu id="cmbxEmpleadoC4" value="#{mto.idEmpleado}">
								<f:selectItems value="#{mto.listaEmpleadosChofer}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.uniReparto}" id="lblUniRepartoC4" styleClass="label"/>
							<h:selectOneMenu id="cmbxUniRepartoC4" value="#{mto.idUnidadReparto}">
								<f:selectItems value="#{mto.listaUnidadReparto}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.fAsignada}" id="lblFechaAsignadaC4" styleClass="label"/>
							<h:inputText value="#{mto.fAsignada }" id="txtFechaAsignadaC4" styleClass="tcal"/>
							
							<h:outputLabel value="#{msg.fDenegada}" id="lblFechaDenegadaC4" styleClass="label"/>
							<h:inputText value="#{mto.fDenegada }" id="txtFechaDenegadaC4" styleClass="tcal"/>
							<h:outputLabel value="#{msg.edo}" id="lblEdoC4" styleClass="label"/>
							<h:selectOneMenu id="cmbxEstatusC4" value="#{mto.idEstatus}"  title="Selecciona una opción">
								<f:selectItems value="#{mto.listaEstatus}"/>
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC4();" disabled="#{!mto.mapaAccion['ADD_ASIGUNIDADREPARTO'] }"/>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"/>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_ASIGUNIDADREPARTO'] }"/>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT4'] }"/>
							<h:commandButton action="#{mto.exportarGridPDF}" value="Exportar a PDF" disabled="#{!mto.mapaAccion['EXPORT_CAT4'] }"/>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"/>
						</h:panelGroup>
						<h:panelGrid id="pnlC4Tabla">
								<rich:dataTable value="#{mto.asignaciones}" var="asignacion" id="tblAsignacion" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                    <rich:column> <h:outputText value="Número de Serie" /></rich:column>
						                    <rich:column> <h:outputText value="Fecha Asignada" /></rich:column>
						                    <rich:column> <h:outputText value="Fecha Denegada" ></h:outputText></rich:column>
						                    <rich:column> <h:outputText value="Empleado" /></rich:column>
						                    <rich:column> <h:outputText value="Unidad de Reparto" /></rich:column>
						                    <rich:column> <h:outputText value="LDC" /></rich:column>
						                    <rich:column> <h:outputText value="País" /></rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						            	</rich:columnGroup>
						        	</f:facet>
						        	<!-- Aplica columnas de filtros -->
						        	<rich:column filterMethod="#{mto.filterNoSerie }" filterEvent="onblur">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValueNoSerie}" id="txtFilterNoSerieC4">
						                        <a4j:support event="onkeyup" reRender="tblAsignacion" 
						                            		 ignoreDupResponses="true" requestDelay="1000"/>
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{asignacion.noSerie}" />
						            </rich:column>
						            <rich:column filterMethod="#{mto.filterFeAsignada }" filterEvent="onblur">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValuefAsignada}" id="txtFilterFAsignadaC4">
						                        <a4j:support event="onkeyup" reRender="tblAsignacion"
						                            ignoreDupResponses="true" requestDelay="1000"  />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{asignacion.fAsignada}" />
						            </rich:column>
						            <rich:column filterMethod="#{mto.filterFeDenegada }" filterEvent="onblur">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValuefDenegada}"  id="txtFilterFDenegadaC4">
						                        <a4j:support event="onkeyup" reRender="tblAsignacion" ignoreDupResponses="true"  requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{asignacion.fDenegada}" />
						            </rich:column>
						            <rich:column filterMethod="#{mto.filterDescEmpleado }" filterEvent="onblur">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValueEmpleado}" id="txtFilterEmpleadoC4">
						                        <a4j:support event="onkeyup" reRender="tblAsignacion"
						                            ignoreDupResponses="true" requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{asignacion.descEmpleado}" />
						            </rich:column>
						        	<rich:column> <h:outputText value="#{asignacion.descUnidadReparto}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{asignacion.descLDC}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{asignacion.descPais}"/> </rich:column>
						        	 <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" 
						            			disabled="#{!mto.mapaAccion['UPD_ASIGUNIDADREPARTO'] || !mto.modificarActivado }">
						            			<f:setPropertyActionListener  value="#{asignacion.idAsignacionUnidadReparto}" target="#{mto.idAsignacionUnidadReparto}"/>
												<f:setPropertyActionListener  value="#{asignacion.fAsignada}" target="#{mto.fAsignada }"/>
												<f:setPropertyActionListener  value="#{asignacion.fDenegada}" target="#{mto.fDenegada }"/>
												<f:setPropertyActionListener  value="#{asignacion.idEmpleado}" target="#{mto.idEmpleado }"/>
												<f:setPropertyActionListener  value="#{asignacion.idUnidadReparto}" target="#{mto.idUnidadReparto }"/>
												<f:setPropertyActionListener  value="#{asignacion.idLDC}" target="#{mto.idLDC }"/>
												<f:setPropertyActionListener  value="#{asignacion.idPais}" target="#{mto.idPais }"/>
												<f:setPropertyActionListener  value="#{asignacion.idEstatus}" target="#{mto.idEstatus }"/>
												<f:setPropertyActionListener  value="#{asignacion.noSerie}" target="#{mto.noSerie }"/>
											</h:commandButton>
											<!--  En teoria no elimina porque tiene siempre dependencia -->
						            	</rich:panel>
						            </rich:column>
						            <f:facet name="footer">
						            	<rich:datascroller id="dsC4" for="tblAsignacion"></rich:datascroller>
						        	</f:facet>
						    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 5 - Rutas - PW_RUTAS -->
					<rich:panel id="pnlc5" rendered="#{mto.pnl5 }">
						<h:outputLabel value="#{msg.tituloCat5}" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog5" columns="6">
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
							<h:inputText value="#{mto.tiEfectivo}" id="txtPromEfectivoC5" styleClass="caja" onkeypress="return permite(event,'num2pto')"
							             onchange="return validaTiempo(this.value)" maxlength="8"/>
							<h:outputLabel value="#{msg.tPromTC5}" styleClass="label" id="lblPromTotal"/>
							<h:inputText value="#{mto.tiTotal}" id="txtPromTotalC5" styleClass="caja" onkeypress="return permite(event,'num2pto')"
							             onchange="return validaTiempo(this.value)" maxlength="8"/>
							<h:outputLabel value="#{msg.tipoRiesgoC5}" styleClass="label" id="lblTipoRiesgo5"/>
							<h:selectOneMenu  id="cmbxTipoRiesgC5" value="#{mto.idTipoRiesgo}" >
								<f:selectItems value="#{mto.listaTipoRiesgo}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.diaReparto}" styleClass="label" id="lblDiaReparto"/>
							<h:selectOneMenu id="cmbxDiaReparto" value="#{mto.diaReparto}" >
								<f:selectItems value="#{mto.listaDiaReparto}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.activoReparto}" styleClass="label" id="lblActivoReparto"/>
							<h:selectBooleanCheckbox id="ChkActivoReparto" value="#{mto.activoReparto}"/>             
							<h:outputLabel value="#{msg.sinHH}" rendered="#{mto.lblVisibleSinHH }" styleClass="label" id="lblSinHH"/>
							<h:selectBooleanCheckbox id="ChkHH" rendered="#{mto.chkVisibleSinHH }" value="#{mto.repartoSinHH}"/>
							
							
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC5()"
								id="btnGuardarC5" disabled="#{!mto.mapaAccion['ADD_RUTA'] }"/>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c" disabled="#{!mto.cancelarActivado }"/>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_RUTA'] }"/>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT5'] }"/>
							<h:commandButton action="#{mto.exportarGridPDF}" value="Exportar a PDF" disabled="#{!mto.mapaAccion['EXPORT_CAT5'] }"/>
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
						                    <rich:column> <h:outputText value="Día Reparto" /> </rich:column>
						                    <rich:column> <h:outputText value="Activo Reparto" /> </rich:column>
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
						        	<rich:column> <h:outputText value="#{ruta.diaReparto}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{ruta.gridActivoReparto}"/> </rich:column>
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
												<f:setPropertyActionListener  value="#{ruta.activoReparto}" target="#{mto.activoReparto }"/>
												<f:setPropertyActionListener  value="#{ruta.diaReparto}" target="#{mto.diaReparto }"/>
												<f:setPropertyActionListener  value="#{ruta.idCampaniaReparoSinHH}" target="#{mto.idCampaniaSinHH }"/>
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
					
					
					<!-- Catalogo 6 - Tipo de Ruta - PW_TIPO_RUTA -->
					<rich:panel id="pnlc6" rendered="#{mto.pnl6}">
						<h:outputLabel value="#{msg.tituloCat6}" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog6" columns="4">
							<h:outputLabel value="#{msg.cveObli}" styleClass="label" id="lblCveC6"/>
							<h:inputText id="txtCveC6" value="#{mto.cveTipoRuta }" styleClass="caja" maxlength="50"/>
							<h:outputLabel value="#{msg.descObli}" styleClass="label" id="lblDescC6"/>
							<h:inputText value="#{mto.descTipoRuta }" id="txtDescC6" styleClass="caja" maxlength="100"/>
							
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC6"/>
							<h:selectOneMenu  id="cmbxPaisC6" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.ldc}" styleClass="label" id="lblLDCC6"/>
							<h:selectOneMenu  id="cmbxLDCC6" value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC6()" disabled="#{!mto.mapaAccion['ADD_TIPRUTA'] }"/>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"/>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_TIPRUTA'] }"/>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT6'] }"/>
							<h:commandButton action="#{mto.exportarGridPDF}" value="Exportar a PDF" disabled="#{!mto.mapaAccion['EXPORT_CAT6'] }"/>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"/>
						</h:panelGroup>
						<h:panelGrid id="pnlC6Tabla">
								<rich:dataTable value="#{mto.tipoRutas}" var="tipoRuta" id="tblTipoRuta" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                    <rich:column> <h:outputText value="Clave" /></rich:column>
						                    <rich:column> <h:outputText value="Descripción" /></rich:column>
						                    <rich:column> <h:outputText value="Pais" /></rich:column>
						                    <rich:column> <h:outputText value="LDC" /></rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						            	</rich:columnGroup>
						        	</f:facet>
						            <rich:column> <h:outputText value="#{tipoRuta.clave}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{tipoRuta.descripcion}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{tipoRuta.descPais}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{tipoRuta.descLDC}"/> </rich:column>
						        	 <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_TIPRUTA']  || !mto.modificarActivado }">
						            			<f:setPropertyActionListener  value="#{tipoRuta.idTipoRuta}" target="#{mto.idTipoRuta}"/>
												<f:setPropertyActionListener  value="#{tipoRuta.clave}" target="#{mto.cveTipoRuta }"/>
												<f:setPropertyActionListener  value="#{tipoRuta.descripcion}" target="#{mto.descTipoRuta }"/>
												<f:setPropertyActionListener  value="#{tipoRuta.idPais}" target="#{mto.idPais }"/>
												<f:setPropertyActionListener  value="#{tipoRuta.idLDC}" target="#{mto.idLDC }"/>
											</h:commandButton>
											<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
												onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_TIPRUTA']  || !mto.eliminarActivado }">
												<f:setPropertyActionListener  value="#{tipoRuta.idTipoRuta}" target="#{mto.idTipoRuta}"/>
											</h:commandButton>
						            	</rich:panel>
						            </rich:column>
						            <f:facet name="footer">
						            	<rich:datascroller id="dsC6" for="tblTipoRuta"></rich:datascroller>
						        	</f:facet>
						    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 7 - Representantes por Rutas - PW_REPRESENTANTES_POR_RUTA -->
					<rich:panel id="pnlc7" rendered="#{mto.pnl7}">
						<h:outputLabel value="#{msg.tituloCat7}" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog7" columns="6">
							<h:outputLabel value="#{msg.representante}" styleClass="label" id="lblRepresentanteC7"/>
							<h:selectOneMenu  id="cmbxRepresentanteC7" value="#{mto.idRepresentante}"
											  valueChangeListener="#{mto.getRutasPorZona}" > 
											   <a4j:support event="onchange" reRender="pnlc7,cmbxRutaC7"
						                            ignoreDupResponses="true" requestDelay="1000" />
								<f:selectItems value="#{mto.listaRepresentantesExistentes}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.ruta}" styleClass="label" id="lblRutaC7"/>
							<h:selectOneMenu id="cmbxRutaC7" value="#{mto.idRuta}">
								<f:selectItems value="#{mto.listaRutas}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.fAlta}" styleClass="label" id="lblfAltaC7"/>
							<h:inputText id="txtfAltaC7" value="#{mto.fAlta }" styleClass="tcal"/>
							
							<h:outputLabel value="#{msg.fBaja}" styleClass="label" id="lblfBajaC7"/>
							<h:inputText value="#{mto.fBaja }" id="txtfBajaC7"  styleClass="tcal"/>
							
							<h:outputLabel value="#{msg.sEntregaAnt}" styleClass="label" id="lblSeqEntregaAntC7"/>
							<h:inputText id="txtSeqEntregaAntC7" value="#{mto.seqEntregaAnterior }" maxlength="4" styleClass="caja"/>
							<h:outputLabel value="#{msg.sEntregaRecent}" styleClass="label" id="lblSeqEntregaRecC7"/>
							<h:inputText value="#{mto.seqEntregaReciente }" id="txtSeqEntregaRecC7" maxlength="4"  styleClass="caja"/>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC7()" disabled="#{!mto.mapaAccion['ADD_REPRUTA'] }"/>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"/>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_REPRUTA'] }"/>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT7'] }"/>
							<h:commandButton action="#{mto.exportarGridPDF}" value="Exportar a PDF" disabled="#{!mto.mapaAccion['EXPORT_CAT7'] }"/>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC7Tabla">
							<rich:dataTable value="#{mto.representanteRutas}" var="representanteRuta" id="tblRepresentante" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Ruta" /></rich:column>
					                    <rich:column> <h:outputText value="Representante" /></rich:column>
					                    <rich:column> <h:outputText value="Zona" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha de Alta" /></rich:column>
					                    <rich:column> <h:outputText value="Fecha de Baja" /></rich:column>
					                    <rich:column> <h:outputText value="Secuencia de Entrega Anterior" /></rich:column>
					                    <rich:column> <h:outputText value="Secuencia de Entrega Reciente" /></rich:column>
					                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
					            	</rich:columnGroup>
					        	</f:facet>
					            <rich:column>
					            	<f:facet name="header">
					            		<h:inputText value="#{mto.ruta7}" id="txtFilterNoSerieC7" 
					            					 valueChangeListener="#{mto.getCatalogo7RutaParams }" />
					            	</f:facet>
					            	<h:outputText value="#{representanteRuta.descRuta}" />
					            </rich:column>
					            <rich:column>
					            	<f:facet name="header">
					            		<h:inputText value="#{mto.nombre7}" id="txtFilterNombreC7" 
					            					 valueChangeListener="#{mto.getCatalogo7NombreParams }" />
					            	</f:facet>
					            	<h:outputText value="#{representanteRuta.descRepresentante}" />
					            </rich:column>
					            <rich:column>
					            	<f:facet name="header">
					            		<h:inputText value="#{mto.zona7}" id="txtFilterZonaC7" 
					            					 valueChangeListener="#{mto.getCatalogo7ZonaParams }" />
					            	</f:facet>
					            	<h:outputText value="#{representanteRuta.descZona}" />
					            </rich:column>
					        	<rich:column> <h:outputText value="#{representanteRuta.fAlta}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{representanteRuta.fBaja}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{representanteRuta.seqEntregaAnterior}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{representanteRuta.seqEntregaReciente}"/> </rich:column>
					        	 <rich:column>
					            	<rich:panel>
					            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_REPRUTA']  || !mto.modificarActivado }">
					            			<f:setPropertyActionListener  value="#{representanteRuta.idRepresentanteRuta}" target="#{mto.idRepresentanteRuta}"/>
											<f:setPropertyActionListener  value="#{representanteRuta.idRepresentante}" target="#{mto.idRepresentante }"/>
											<f:setPropertyActionListener  value="#{representanteRuta.idRuta}" target="#{mto.idRuta }"/>
											<f:setPropertyActionListener  value="#{representanteRuta.fAlta}" target="#{mto.fAlta }"/>
											<f:setPropertyActionListener  value="#{representanteRuta.fBaja}" target="#{mto.fBaja }"/>
											<f:setPropertyActionListener  value="#{representanteRuta.seqEntregaAnterior}" target="#{mto.seqEntregaAnterior }"/>
											<f:setPropertyActionListener  value="#{representanteRuta.seqEntregaReciente}" target="#{mto.seqEntregaReciente }"/>
										</h:commandButton>
										<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
											onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_REPRUTA']  || !mto.eliminarActivado }">
											<f:setPropertyActionListener  value="#{representanteRuta.idRepresentanteRuta}" target="#{mto.idRepresentanteRuta}"/>
										</h:commandButton>
					            	</rich:panel>
					            </rich:column>
					            <f:facet name="footer">
					            	<rich:datascroller id="dsC7" for="tblRepresentante"></rich:datascroller>
					        	</f:facet>
					    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 8 - Asignacion de Rutas - PW_ASIGNACION_RUTA_CHOFER -->
					<rich:panel id="pnlc8" rendered="#{mto.pnl8}">
						<h:outputLabel value="#{msg.tituloCat8}" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog8" columns="6">
							
							<h:outputLabel value="#{msg.zona}" styleClass="label" id="lblZonaC8"/>
							<h:selectOneMenu id="cmbxZonaC8" value="#{mto.idZona}"
							onchange="submit()" valueChangeListener="#{mto.getRutasPorZonaAsignacionRuta}">
								<f:selectItems value="#{mto.listaZonas}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.ruta}" styleClass="label" id="lblRutaC8"/>
							<h:selectOneMenu id="cmbxRutaC8" value="#{mto.idRuta}">
								<f:selectItems value="#{mto.rutasPorZona}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC8"/>
							<h:selectOneMenu id="cmbxPaisC8" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
							
							
							
							<h:outputLabel value="#{msg.ldc}" styleClass="label" id="lblLDCC8"/>
							<h:selectOneMenu  id="cmbxLDCC8" value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.empleado}" styleClass="label" id="lblEmpleadoC8"></h:outputLabel>
							<h:selectOneMenu id="cmbxEmpleadoC8" value="#{mto.idEmpleado}">
								<f:selectItems value="#{mto.listaEmpleadosChofer}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.fAsignacion}" styleClass="label" id="lblfAltaC8"/>
							<h:inputText id="txtfAsignadaC8" value="#{mto.fAlta }" styleClass="tcal"/>
							<h:outputLabel value="#{msg.fDenegadaC8}" styleClass="label" id="lblfBajaC8"/>
							<h:inputText value="#{mto.fBaja }" id="txtfDenegadaC8"  styleClass="tcal"/>
							
							<h:outputLabel value="#{msg.tipoAsig}" styleClass="label" id="lblTipoAsigC8"/>
							<h:selectOneMenu id="cmbxTipoAsigC8" value="#{mto.idTipoAsig}">
								<f:selectItems value="#{mto.listaTipoAsignacion}" />
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC8()" disabled="#{!mto.mapaAccion['ADD_ASIGNARUTA'] }"></h:commandButton>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"/>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_ASIGNARUTA'] }"/>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT8'] }"/>
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT8'] }"/>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC8Tabla">
								<rich:dataTable value="#{mto.asignaRutasChofer}" var="asignaRutaChofer" id="tblAsignaRuta" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                    <rich:column> <h:outputText value="Ruta" /></rich:column>
						                    <rich:column> <h:outputText value="Pais" /></rich:column>
						                    <rich:column> <h:outputText value="Zona" /></rich:column>
						                    <rich:column> <h:outputText value="LDC" /></rich:column>
						                    <rich:column> <h:outputText value="Empleado" /></rich:column>
						                    <rich:column> <h:outputText value="Fecha Asignada" /></rich:column>
						                    <rich:column> <h:outputText value="Fecha Denegada" /></rich:column>
						                    <rich:column> <h:outputText value="Tipo Asignación" /></rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						            	</rich:columnGroup>
						        	</f:facet>
						        	<rich:column filterMethod="#{mto.filterClaveRutaAsigna }" filterEvent="onblur">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValueCveRuta}" id="txtFilterRutaC8">
						                        <a4j:support event="onkeyup" reRender="tblAsignaRuta"
						                            ignoreDupResponses="true" requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{asignaRutaChofer.descRuta}" />
						            </rich:column>
						        	<rich:column> <h:outputText value="#{asignaRutaChofer.descPais}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{asignaRutaChofer.descZona}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{asignaRutaChofer.descLDC}"/> </rich:column>
						        	<rich:column filterMethod="#{mto.filterEmpleadoAsigna }" filterEvent="onblur">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValueEmpleado}" id="txtFilterEmpC8">
						                        <a4j:support event="onkeyup" reRender="tblAsignaRuta"
						                            ignoreDupResponses="true" requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{asignaRutaChofer.descEmpleado}" />
						            </rich:column>
						        	<rich:column filterMethod="#{mto.filterFechaAsigAsigna }" filterEvent="onblur">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValuefAsignada}" id="txtFilterAsigC8">
						                        <a4j:support event="onkeyup" reRender="tblAsignaRuta"
						                            ignoreDupResponses="true" requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{asignaRutaChofer.fAsignada}" />
						            </rich:column>
						        	<rich:column filterMethod="#{mto.filterFechaDenAsigna }" filterEvent="onblur">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValuefDenegada}" id="txtFilterDenC8">
						                        <a4j:support event="onkeyup" reRender="tblAsignaRuta"
						                            ignoreDupResponses="true" requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{asignaRutaChofer.fDenegada}" />
						            </rich:column>
						        	<rich:column> <h:outputText value="#{asignaRutaChofer.tipoAsignacion}"/> </rich:column>
						        	 <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_ASIGNAPRUTA'] || !mto.modificarActivado }">
						            			<f:setPropertyActionListener  value="#{asignaRutaChofer.idAsignacionRutaChofer}" target="#{mto.idAsignacionRutaChofer}"/>
												<f:setPropertyActionListener  value="#{asignaRutaChofer.idRuta}" target="#{mto.idRuta }"/>
												<f:setPropertyActionListener  value="#{asignaRutaChofer.idPais}" target="#{mto.idPais }"/>
												<f:setPropertyActionListener  value="#{asignaRutaChofer.idZona}" target="#{mto.idZona }"/>
												<f:setPropertyActionListener  value="#{asignaRutaChofer.idLDC}" target="#{mto.idLDC }"/>
												<f:setPropertyActionListener  value="#{asignaRutaChofer.idEmpleado}" target="#{mto.idEmpleado }"/>
												<f:setPropertyActionListener  value="#{asignaRutaChofer.fAsignada}" target="#{mto.fAlta }"/>
												<f:setPropertyActionListener  value="#{asignaRutaChofer.fDenegada}" target="#{mto.fBaja }"/>
												<f:setPropertyActionListener  value="#{asignaRutaChofer.idTipoAsignacion}" target="#{mto.idTipoAsig }"/>
												<f:setPropertyActionListener  value="#{asignaRutaChofer.tipoAsignacion}" target="#{mto.tipoAsignacion }"/>
											</h:commandButton>
											<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
												onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_ASIGNARUTA'] || !mto.eliminarActivado }">
												<f:setPropertyActionListener  value="#{asignaRutaChofer.idAsignacionRutaChofer}" target="#{mto.idAsignacionRutaChofer}"/>
											</h:commandButton>
						            	</rich:panel>
						            </rich:column>
						           	<f:facet name="footer">
					            		<rich:datascroller id="dsC8" for="tblAsignaRuta"></rich:datascroller>
					        		</f:facet>
						    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 9 - haNDheld - Dispositivo Movil - PW_DISPOSITIVO_MOVIL -->
					<rich:panel id="pnlc9" rendered="#{mto.pnl9}">
						<h:outputLabel value="#{msg.tituloCat9}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog9" columns="6">
							<h:outputLabel value="#{msg.mac}" styleClass="label" id="lblMacC9"></h:outputLabel>
							<h:inputText value="#{mto.macAdress }" id="txtMacC9"  styleClass="caja" maxlength="17">	</h:inputText>
							<h:outputLabel value="#{msg.ip}" styleClass="label" id="lblDirIPC9"></h:outputLabel>
							<h:inputText id="txtDirIPC9" value="#{mto.direccionIP }" styleClass="caja" maxlength="16"> </h:inputText>
							<h:outputLabel value="#{msg.noSerieC9}" styleClass="label" id="lblNoSerieC9"></h:outputLabel>
							<h:inputText id="txtNoSerieC9" value="#{mto.noSerie }" styleClass="caja" maxlength="100"> </h:inputText>
							
							<h:outputLabel value="#{msg.marc}" styleClass="label" id="lblMarcaC9"></h:outputLabel>
							<h:inputText id="txtMarcaC9" value="#{mto.descMarca }" styleClass="caja" maxlength="100"> </h:inputText>
							<h:outputLabel value="#{msg.model}" styleClass="label" id="lblModelC9"></h:outputLabel>
							<h:inputText id="txtModelC9" value="#{mto.descModelo }" styleClass="caja" maxlength="100"> </h:inputText>
							<h:outputLabel value="#{msg.edo}" styleClass="label" id="lblEstatusC9"></h:outputLabel>
							<h:selectOneMenu id="cmbxEstatusC9" value="#{mto.idEstatus}">
								<f:selectItems value="#{mto.listaEstatus}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC9"></h:outputLabel>
							<h:selectOneMenu  id="cmbxPaisC9" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.ldc}" styleClass="label" id="lblLDCC9"/>
							<h:selectOneMenu id="cmbxLDCC9" value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC9()" disabled="#{!mto.mapaAccion['ADD_DISPOSITIVO'] }"></h:commandButton>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_DISPOSITIVO'] }"></h:commandButton>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT9'] }"> </h:commandButton>
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT9'] }"> </h:commandButton>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC9Tabla">
								<rich:dataTable value="#{mto.dispositivosMovil}" var="dispositivoMovil" id="tblHandHeld" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                    <rich:column> <h:outputText value="Dirección Mac" /></rich:column>
						                    <rich:column> <h:outputText value="Dirección IP" /></rich:column>
						                    <rich:column> <h:outputText value="Número de Serie" /></rich:column>
						                    <rich:column> <h:outputText value="Marca" /></rich:column>
						                    <rich:column> <h:outputText value="Modelo" /></rich:column>
						                    <rich:column> <h:outputText value="Estatus" /></rich:column>
						                    <rich:column> <h:outputText value="Pais" /></rich:column>
						                    <rich:column> <h:outputText value="LDC" /></rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						            	</rich:columnGroup>
						        	</f:facet>
						        	<rich:column> <h:outputText value="#{dispositivoMovil.macAdress}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{dispositivoMovil.direccionIP}"/> </rich:column>
									<rich:column filterMethod="#{mto.filterNoSerieDispositivo }">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValueNoSerie}" id="txtFilterDenC9" >
						                        <a4j:support event="onkeyup" reRender="tblHandHeld"
						                            ignoreDupResponses="true" requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{dispositivoMovil.noSerie}" />
						            </rich:column>
									<rich:column filterMethod="#{mto.filterMarcaDispositivo }">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValue}" id="txtFilterMarC9" >
						                        <a4j:support event="onkeyup" reRender="tblHandHeld"
						                            ignoreDupResponses="true" requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{dispositivoMovil.marca}" />
						            </rich:column>
									<rich:column filterMethod="#{mto.filterModeloDispositivo }">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValueZona}" id="txtFilterModC9" >
						                        <a4j:support event="onkeyup" reRender="tblHandHeld"
						                            ignoreDupResponses="true" requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{dispositivoMovil.modelo}" />
						            </rich:column>
									<rich:column> <h:outputText value="#{dispositivoMovil.descEstatus}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{dispositivoMovil.descPais}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{dispositivoMovil.descLDC}"/> </rich:column>
						        	 <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_DISPOSITIVO'] || !mto.modificarActivado }">
						            			<f:setPropertyActionListener  value="#{dispositivoMovil.idDispositivoMovil}" target="#{mto.idDispositivoMovil}"/>
												<f:setPropertyActionListener  value="#{dispositivoMovil.macAdress}" target="#{mto.macAdress }"/>
												<f:setPropertyActionListener  value="#{dispositivoMovil.idPais}" target="#{mto.idPais }"/>
												<f:setPropertyActionListener  value="#{dispositivoMovil.direccionIP}" target="#{mto.direccionIP }"/>
												<f:setPropertyActionListener  value="#{dispositivoMovil.idLDC}" target="#{mto.idLDC }"/>
												<f:setPropertyActionListener  value="#{dispositivoMovil.noSerie}" target="#{mto.noSerie }"/>
												<f:setPropertyActionListener  value="#{dispositivoMovil.marca}" target="#{mto.descMarca }"/>
												<f:setPropertyActionListener  value="#{dispositivoMovil.modelo}" target="#{mto.descModelo }"/>
												<f:setPropertyActionListener  value="#{dispositivoMovil.idEstatus}" target="#{mto.idEstatus }"/>
											</h:commandButton>
											<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
												onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_DISPOSITIVO'] || !mto.eliminarActivado }">
												<f:setPropertyActionListener  value="#{dispositivoMovil.idDispositivoMovil}" target="#{mto.idDispositivoMovil}"/>
											</h:commandButton>
						            	</rich:panel>
						            </rich:column>
						            <f:facet name="footer">
						            	<rich:datascroller id="dsC9" for="tblHandHeld"></rich:datascroller>
						            </f:facet>
						    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 10 - Empleado - PW_EMPLEADO -->
					<rich:panel id="pnlc10" rendered="#{mto.pnl10}">
						<h:outputLabel value="#{msg.tituloCat10}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog10" columns="6">
							<h:outputLabel value="#{msg.nom}" styleClass="label" id="lblNombreC10"></h:outputLabel>
							<h:inputText value="#{mto.nombre }" id="txtNombreC10"  styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.app}" styleClass="label" id="lblApPaternoC10"></h:outputLabel>
							<h:inputText value="#{mto.apPaterno}" id="txtApPaternoC10"  styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.apm}" styleClass="label" id="lblApMaternoC10"></h:outputLabel>
							<h:inputText value="#{mto.apMaterno}" id="txtApMaternoC10"  styleClass="caja" maxlength="100"></h:inputText>
							
							<h:outputLabel value="#{msg.sexo}" styleClass="label" id="lblSexoC9"></h:outputLabel>
							<h:selectOneMenu  id="cmbxSexoC10" value="#{mto.idSexo}">
								<f:selectItems value="#{mto.listaSexo}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.edoCivil}" styleClass="label" id="lblEdoCivilC10"></h:outputLabel>
							<h:selectOneMenu id="cmbxEdoCivilC10" value="#{mto.idEdoCivil}">
								<f:selectItems value="#{mto.listaEdoCivil}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.fNac}" styleClass="label" id="lblfNacimientoC10"></h:outputLabel>
							<h:inputText value="#{mto.feNacimiento}" id="txtfNacimientoC10"  styleClass="tcal"
							size="10" onkeypress="return false;" maxlength="10"></h:inputText>
							
							<h:outputLabel value="#{msg.dom}" styleClass="label" id="lblDomicilioC10"></h:outputLabel>
							<h:inputText value="#{mto.domicilio}" id="txtDomicilioC10"  styleClass="caja"  maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.rfc}" styleClass="label" id="lblRFCC10"></h:outputLabel>
							<h:inputText value="#{mto.rfc}" id="txtRFCC10"  styleClass="caja"  maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.ldc}" styleClass="label" id="lblLDCC10"></h:outputLabel>
							<h:selectOneMenu  id="cmbxLDCC10" value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.puesto}" styleClass="label" id="lblPuestoC10"></h:outputLabel>
							<h:selectOneMenu  id="cmbxPuestoC10" value="#{mto.idPuesto}">
								<f:selectItems value="#{mto.listaPuestos}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.fIng}" styleClass="label" id="lblFIngresoC10"></h:outputLabel>
							<h:inputText value="#{mto.feIngreso}" id="txtFIngresoC10" styleClass="tcal"
							size="10" onkeypress="return false;" maxlength="10"></h:inputText>
							<h:outputLabel value="#{msg.edo}" styleClass="label" id="lblEstatusC10"></h:outputLabel>
							<h:selectOneMenu    id="cmbxEstatusC10" value="#{mto.idEstatus}">
								<f:selectItems value="#{mto.listaEstatus}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.tel}" styleClass="label" id="lblTelefonoC10"></h:outputLabel>
							<h:inputText  value="#{mto.telefono}" id="txtTelefonoC10"  styleClass="caja"  maxlength="13"></h:inputText>
							<h:outputLabel value="#{msg.mail}" styleClass="label" id="lblMailC10"></h:outputLabel>
							<h:inputText value="#{mto.mail}" id="txtMailC10"  styleClass="caja" maxlength="100"></h:inputText>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC10()" disabled="#{!mto.mapaAccion['ADD_EMPLEADO'] }"></h:commandButton>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_EMPLEADO'] }"></h:commandButton>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT10'] }"> </h:commandButton>
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT10'] }"> </h:commandButton>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC10Tabla">
								<rich:dataTable value="#{mto.empleados}" var="empleado" id="tblEmpleado" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                    <rich:column> <h:outputText value="Nombre" /></rich:column>
						                    <rich:column> <h:outputText value="Apellido Paterno" /></rich:column>
						                    <rich:column> <h:outputText value="Apellido Materno" /></rich:column>
						                    <rich:column> <h:outputText value="Sexo" /></rich:column>
						                    <rich:column> <h:outputText value="Estado Civil" /></rich:column>
						                    <rich:column> <h:outputText value="Fecha de Nacimiento" /></rich:column>
						                    <rich:column> <h:outputText value="RFC" /></rich:column>
						                    <rich:column> <h:outputText value="Estatus" /></rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						            	</rich:columnGroup>
						        	</f:facet>
									<rich:column filterMethod="#{mto.filterNombreEmpleado }">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValue}" id="txtFilterEmpC10" >
						                        <a4j:support event="onkeyup" reRender="tblEmpleado"
						                            ignoreDupResponses="true" requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{empleado.nombre}" />
						            </rich:column>
									<rich:column filterMethod="#{mto.filterPaternoEmpleado }">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValueCveRuta}" id="txtFilterPatC10" >
						                        <a4j:support event="onkeyup" reRender="tblEmpleado"
						                            ignoreDupResponses="true" requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{empleado.apPaterno}" />
						            </rich:column>
									<rich:column filterMethod="#{mto.filterMaternoEmpleado }">
						                <f:facet name="header">
						                    <h:inputText value="#{mto.filterValueEmpleado}" id="txtFilterMatC10" >
						                        <a4j:support event="onkeyup" reRender="tblEmpleado"
						                            ignoreDupResponses="true" requestDelay="1000" />
						                    </h:inputText>
						                </f:facet>
						                <h:outputText value="#{empleado.apMaterno}" />
						            </rich:column>
									<rich:column> <h:outputText value="#{empleado.sexo}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{empleado.edoCivil}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{empleado.feNacimiento}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{empleado.rfc}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{empleado.descEstatus}"/> </rich:column>
						        	 <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_EMPLEADO'] || !mto.modificarActivado }">
						            			<f:setPropertyActionListener  value="#{empleado.idEmpleado}" target="#{mto.idEmpleado}"/>
												<f:setPropertyActionListener  value="#{empleado.nombre}" target="#{mto.nombre }"/>
												<f:setPropertyActionListener  value="#{empleado.apPaterno}" target="#{mto.apPaterno }"/>
												<f:setPropertyActionListener  value="#{empleado.apMaterno}" target="#{mto.apMaterno }"/>
												<f:setPropertyActionListener  value="#{empleado.domicilio}" target="#{mto.domicilio }"/>
												<f:setPropertyActionListener  value="#{empleado.feNacimiento}" target="#{mto.feNacimiento }"/>
												<f:setPropertyActionListener  value="#{empleado.mail}" target="#{mto.mail }"/>
												<f:setPropertyActionListener  value="#{empleado.rfc}" target="#{mto.rfc }"/>
												<f:setPropertyActionListener  value="#{empleado.feIngreso}" target="#{mto.feIngreso }"/>
												<f:setPropertyActionListener  value="#{empleado.telefono}" target="#{mto.telefono }"/>
												<f:setPropertyActionListener  value="#{empleado.idSexo}" target="#{mto.idSexo }"/>
												<f:setPropertyActionListener  value="#{empleado.idEdoCivil}" target="#{mto.idEdoCivil }"/>
												<f:setPropertyActionListener  value="#{empleado.idLDC}" target="#{mto.idLDC }"/>
												<f:setPropertyActionListener  value="#{empleado.idPuesto}" target="#{mto.idPuesto }"/>
												<f:setPropertyActionListener  value="#{empleado.idEstatus}" target="#{mto.idEstatus }"/>
											</h:commandButton>
											<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
												onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_EMPLEADO'] || !mto.eliminarActivado }">
												<f:setPropertyActionListener  value="#{empleado.idEmpleado}" target="#{mto.idEmpleado}"/>
											</h:commandButton>
						            	</rich:panel>
						            </rich:column>
						            <f:facet name="footer">
						            	<rich:datascroller id="dsC10" for="tblEmpleado"></rich:datascroller>
						            </f:facet>
						    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 11 - Puesto - PW_PUESTO -->
					<rich:panel id="pnlc11" rendered="#{mto.pnl11}">
						<h:outputLabel value="#{msg.tituloCat11}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog11" columns="4">
							<h:outputLabel value="#{msg.cveObli}" styleClass="label" id="lblCveC11"></h:outputLabel>
							<h:inputText id="txtCveC11" label="Clave" value="#{mto.clave}" styleClass="caja" maxlength="50"></h:inputText>
							<h:outputLabel value="#{msg.descObli}" styleClass="label" id="lblDescC11"></h:outputLabel>
							<h:inputText value="#{mto.descPuesto}" id="txtDescC11" styleClass="caja" maxlength="50"></h:inputText>
							
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC11"></h:outputLabel>
							<h:selectOneMenu  id="cmbxPaisC11" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.ldc}" styleClass="label" id="lblLDCC11"></h:outputLabel>
							<h:selectOneMenu   id="cmbxLDCC11" value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC11()" disabled="#{!mto.mapaAccion['ADD_PUESTO'] }"></h:commandButton>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_PUESTO'] }"></h:commandButton>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT11'] }"> </h:commandButton>
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT11'] }"> </h:commandButton>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC11Tabla">
								<rich:dataTable value="#{mto.puestos}" var="puesto" id="tblPuesto" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                    <rich:column> <h:outputText value="Clave" /></rich:column>
						                    <rich:column> <h:outputText value="Descripción" /></rich:column>
						                    <rich:column> <h:outputText value="Pais" /></rich:column>
						                    <rich:column> <h:outputText value="LDC" /></rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						            	</rich:columnGroup>
						        	</f:facet>
						            <rich:column> <h:outputText value="#{puesto.clave}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{puesto.descripcion}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{puesto.descPais}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{puesto.descLDC}"/> </rich:column>
						        	 <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_PUESTO'] || !mto.modificarActivado }">
						            			<f:setPropertyActionListener  value="#{puesto.idPuesto}" target="#{mto.idPuesto}"/>
												<f:setPropertyActionListener  value="#{puesto.clave}" target="#{mto.clave }"/>
												<f:setPropertyActionListener  value="#{puesto.descripcion}" target="#{mto.descPuesto }"/>
												<f:setPropertyActionListener  value="#{puesto.idPais}" target="#{mto.idPais }"/>
												<f:setPropertyActionListener  value="#{puesto.idLDC}" target="#{mto.idLDC }"/>
											</h:commandButton>
											<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
												onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_PUESTO']  || !mto.eliminarActivado }">
												<f:setPropertyActionListener  value="#{puesto.idPuesto}" target="#{mto.idPuesto}"/>
											</h:commandButton>
						            	</rich:panel>
						            </rich:column>
						            <f:facet name="footer">
					            		<rich:datascroller id="dsC11" for="tblPuesto"></rich:datascroller>
					        		</f:facet>
						    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 12 - Linea Transporte - PW_LINEA_TRANSPORTE -->
					<rich:panel id="pnlc12" rendered="#{mto.pnl12}">
						<h:outputLabel value="#{msg.tituloCat12}" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog12" columns="6">
							<h:outputLabel value="#{msg.cveObli}" styleClass="label" id="lblCveC12"/>
							<h:inputText id="txtCveC12" label="Clave" value="#{mto.clave}"  styleClass="caja" maxlength="50"
										 onkeypress="return permite(event,'num_car')"/>
							<h:outputLabel value="#{msg.descObli}" styleClass="label" id="lblDescC12"/>
							<h:inputText value="#{mto.descripcion}" id="txtDescC12" styleClass="caja" maxlength="100"/>
							<h:outputLabel value="#{msg.tel}" styleClass="label" id="lblTelefonoC12"/>
							<h:inputText value="#{mto.telefono}" id="txtTelefonoC12" styleClass="caja" maxlength="13"/>
							
							<h:outputLabel value="#{msg.dir}" styleClass="label" id="lblDireccionC12"/>
							<h:inputText value="#{mto.domicilio}" id="txtDireccionC12" styleClass="caja" maxlength="250"/>
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC12"/>
							<h:selectOneMenu  id="cmbxPaisC12" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC12()" disabled="#{!mto.mapaAccion['ADD_LINTRANSPORTE'] }"/>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_LINTRANSPORTE'] }"/>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT12'] }"/> 
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT12'] }"/> 
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC12Tabla">
								<rich:dataTable value="#{mto.lineasTransporte}" var="lineaTransporte" id="tblLineaTransporte" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                    <rich:column> <h:outputText value="Clave" /></rich:column>
						                    <rich:column> <h:outputText value="Descripción" /></rich:column>
						                    <rich:column> <h:outputText value="Telefono" /></rich:column>
						                    <rich:column> <h:outputText value="Dirección" /></rich:column>
						                    <rich:column> <h:outputText value="Pais" /></rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						            	</rich:columnGroup>
						            </f:facet>
						            <rich:column> <h:outputText value="#{lineaTransporte.clave}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{lineaTransporte.descripcion}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{lineaTransporte.telefono}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{lineaTransporte.domicilio}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{lineaTransporte.descPais}"/> </rich:column>
						        	 <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_LINTRANSPORTE'] || !mto.modificarActivado }">
						            			<f:setPropertyActionListener  value="#{lineaTransporte.idLineaTransporte}" target="#{mto.idLineaTransporte}"/>
												<f:setPropertyActionListener  value="#{lineaTransporte.clave}" target="#{mto.clave }"/>
												<f:setPropertyActionListener  value="#{lineaTransporte.descripcion}" target="#{mto.descripcion }"/>
												<f:setPropertyActionListener  value="#{lineaTransporte.telefono}" target="#{mto.telefono }"/>
												<f:setPropertyActionListener  value="#{lineaTransporte.domicilio}" target="#{mto.domicilio }"/>
												<f:setPropertyActionListener  value="#{lineaTransporte.idPais}" target="#{mto.idPais }"/>
											</h:commandButton>
											<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
												onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_LINTRANSPORTE'] || !mto.eliminarActivado }">
												<f:setPropertyActionListener  value="#{lineaTransporte.idLineaTransporte}" target="#{mto.idLineaTransporte}"/>
											</h:commandButton>
						            	</rich:panel>
						            </rich:column>
						            <f:facet name="footer">
					            		<rich:datascroller id="dsC12" for="tblLineaTransporte"></rich:datascroller>
					        		</f:facet>
						    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 13 - Informante - PW_INFORMANTE -->
					<rich:panel id="pnlc13" rendered="#{mto.pnl13}">
						<h:outputLabel value="#{msg.tituloCat13}" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog13" columns="6">
							<h:outputLabel value="#{msg.descObli}" styleClass="label" id="lblDescC13"/>
							<h:inputText value="#{mto.descripcion}" id="txtDescC13" styleClass="caja" maxlength="100"/>
							<h:outputLabel value="#{msg.tipoInfor}" styleClass="label" id="lblTipoInformanteC13"/>
							<h:selectOneMenu  id="cmbxTipoInformanteC13"	value="#{mto.idTipoInformante}">
								<f:selectItems value="#{mto.listaTipoInformanteExistentes}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC13"/>
							<h:selectOneMenu  id="cmbxPaisC13"	value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.ldc}" styleClass="label" id="lblLDCC13"/>
							<h:selectOneMenu   id="cmbxLDCC13"	value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC13()" disabled="#{!mto.mapaAccion['ADD_INFORMANTE'] }"></h:commandButton>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_INFORMANTE'] }"/>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT13'] }"/> 
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT13'] }"/>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC13Tabla">
								<rich:dataTable value="#{mto.informantes}" var="informante" id="tblInformante" border="1" rows="50">
									 <f:facet name="header">
						                <rich:columnGroup>
						                    <rich:column> <h:outputText value="Descripción" /></rich:column>
						                    <rich:column> <h:outputText value="Tipo Informante" /></rich:column>
						                    <rich:column> <h:outputText value="Pais" /></rich:column>
						                    <rich:column> <h:outputText value="LDC" /></rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						            	</rich:columnGroup>
						            </f:facet>
						        	<rich:column> <h:outputText value="#{informante.descripcion}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{informante.descTipoInformante}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{informante.descPais}"/> </rich:column>
						        	<rich:column> <h:outputText value="#{informante.descLDC}"/> </rich:column>
						        	 <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_INFORMANTE'] || !mto.modificarActivado }">
						            			<f:setPropertyActionListener  value="#{informante.idInformante}" target="#{mto.idInformante}"/>
												<f:setPropertyActionListener  value="#{informante.descripcion}" target="#{mto.descripcion }"/>
												<f:setPropertyActionListener  value="#{informante.idTipoInformante}" target="#{mto.idTipoInformante }"/>
												<f:setPropertyActionListener  value="#{informante.idLDC}" target="#{mto.idLDC }"/>
												<f:setPropertyActionListener  value="#{informante.idPais}" target="#{mto.idPais }"/>
											</h:commandButton>
											<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
												onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_INFORMANTE'] || !mto.eliminarActivado }">
												<f:setPropertyActionListener  value="#{informante.idInformante}" target="#{mto.idInformante}"/>
											</h:commandButton>
						            	</rich:panel>
						            </rich:column>
						            <f:facet name="footer">
					            		<rich:datascroller id="dsC13" for="tblInformante"></rich:datascroller>
					        		</f:facet>
						    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 14 - SubBodega - PW_SUB_BODEGA_ALMACEN -->
					<rich:panel id="pnlc14" rendered="#{mto.pnl14}">
						<h:outputLabel value="#{msg.tituloCat14}" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog14" columns="6">
							<h:outputLabel value="#{msg.cveObli}" styleClass="label" id="lblCveC14"/>
							<h:inputText id="txtCveC14" value="#{mto.clave}" styleClass="caja" maxlength="50"/>
							<h:outputLabel value="#{msg.descObli}" styleClass="label" id="lblDescC14"/>
							<h:inputText value="#{mto.descripcion}" id="txtDescC14"  styleClass="caja" maxlength="100"/>
							<h:outputLabel value="#{msg.respSubBod}" styleClass="label" id="lblRespSubbodC14"/>
							<h:selectOneMenu  id="cmbxRespSubbodC14" value="#{mto.idUserResponsableSubbodega}">
								<f:selectItems value="#{mto.listaResponsableSubbodegas}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.domNo}" styleClass="label" id="lblDomicilioC14"/>
							<h:inputText value="#{mto.domicilio}" id="txtDomicilioC14" styleClass="caja" maxlength="250"/>
							<h:outputLabel value="#{msg.tel}" styleClass="label" id="lblTelefonoC14"/>
							<h:inputText value="#{mto.telefono}" id="txtTelefonoC14" styleClass="caja" maxlength="13"/>
							<h:outputLabel value="#{msg.ldc}" styleClass="label" id="lblLDCC14"/>
							<h:selectOneMenu   id="cmbxLDCC14" value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.zona}" styleClass="label" id="lblZonaC14"/>
							<h:selectOneMenu  id="cmbxZonaC14" value="#{mto.idZona}">
								<f:selectItems value="#{mto.listaZonas}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC14"/>
							<h:selectOneMenu  id="cmbxPaisC14" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC14()" disabled="#{!mto.mapaAccion['ADD_SUBBODEGA'] }"/>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c" />
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_SUBBODEGA'] }"/>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT14'] }"/> 
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT14'] }"/> 
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC14Tabla">
							<rich:dataTable value="#{mto.subbodegasAlmacen}" var="subBodegaAlmacen" id="tblSubbodegaAlmacen" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Clave" /></rich:column>
					                    <rich:column> <h:outputText value="Descripción" /></rich:column>
					                    <rich:column> <h:outputText value="Domicilio" /></rich:column>
					                    <rich:column> <h:outputText value="Teléfono" /></rich:column>
					                    <rich:column> <h:outputText value="Usuario Responsable" /></rich:column>
					                    <rich:column> <h:outputText value="Pais" /></rich:column>
					                    <rich:column> <h:outputText value="LDC" /></rich:column>
					                    <rich:column> <h:outputText value="Zona" /></rich:column>
					                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
					            	</rich:columnGroup>
					            </f:facet>
					            <rich:column> <h:outputText value="#{subBodegaAlmacen.clave}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{subBodegaAlmacen.descripcion}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{subBodegaAlmacen.domicilio}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{subBodegaAlmacen.telefono}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{subBodegaAlmacen.descUserResponsableSubbodega}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{subBodegaAlmacen.descPais}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{subBodegaAlmacen.descLDC}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{subBodegaAlmacen.descZona}"/> </rich:column>
					        	 <rich:column>
					            	<rich:panel>
					            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_SUBBODEGA'] || !mto.modificarActivado }">
					            			<f:setPropertyActionListener  value="#{subBodegaAlmacen.idSubbodegaAlmacen}" target="#{mto.idSubbodegaAlmacen}"/>
											<f:setPropertyActionListener  value="#{subBodegaAlmacen.clave}" target="#{mto.clave }"/>
											<f:setPropertyActionListener  value="#{subBodegaAlmacen.descripcion}" target="#{mto.descripcion }"/>
											<f:setPropertyActionListener  value="#{subBodegaAlmacen.telefono}" target="#{mto.telefono }"/>
											<f:setPropertyActionListener  value="#{subBodegaAlmacen.domicilio}" target="#{mto.domicilio }"/>
											<f:setPropertyActionListener  value="#{subBodegaAlmacen.idPais}" target="#{mto.idPais }"/>
											<f:setPropertyActionListener  value="#{subBodegaAlmacen.idUserResponsableSubbodega}" target="#{mto.idUserResponsableSubbodega }"/>
											<f:setPropertyActionListener  value="#{subBodegaAlmacen.idLDC}" target="#{mto.idLDC }"/>
											<f:setPropertyActionListener  value="#{subBodegaAlmacen.idZona}" target="#{mto.idZona }"/>
										</h:commandButton>
										<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
											onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_SUBBODEGA'] || !mto.eliminarActivado }">
											<f:setPropertyActionListener  value="#{subBodegaAlmacen.idSubbodegaAlmacen}" target="#{mto.idSubbodegaAlmacen}"/>
										</h:commandButton>
					            	</rich:panel>
					            </rich:column>
					            <f:facet name="footer">
				            		<rich:datascroller id="dsC14" for="tblSubbodegaAlmacen"></rich:datascroller>
				        		</f:facet>
					    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 15 - Destinatario - PW_DESTINATARIO -->
					<rich:panel id="pnlc15" rendered="#{mto.pnl15}">
						<h:outputLabel value="#{msg.tituloCat15}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog15" columns="6">
							<h:outputLabel value="#{msg.nom}" styleClass="label" id="lblNombreC15"></h:outputLabel>
							<h:inputText value="#{mto.nombre}" id="txtNombreC15"  styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.app}" styleClass="label" id="lblApPaternoC15"></h:outputLabel>
							<h:inputText value="#{mto.apPaterno}" id="txtApPaternoC15"  styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.apm}" styleClass="label" id="lblApMaternoC15"></h:outputLabel>
							<h:inputText value="#{mto.apMaterno}" id="txtApMaternoC15"  styleClass="caja" maxlength="100"></h:inputText>
							
							<h:outputLabel value="#{msg.cveObli}" styleClass="label" id="lblCveC15"></h:outputLabel>
							<h:inputText id="txtCveC15" label="Clave" value="#{mto.clave}" styleClass="caja" maxlength="50"></h:inputText>
							<h:outputLabel value="#{msg.mail}" styleClass="label" id="lblMailC15"></h:outputLabel>
							<h:inputText id="txtMailC15" label="Clave" value="#{mto.mail}" styleClass="caja" maxlength="250"></h:inputText>
							<h:outputLabel value="#{msg.tipoDest}" styleClass="label" id="lblTipoDestinC15"></h:outputLabel>
							<h:selectOneMenu  id="cmbxTipoDestinC15" value="#{mto.idTipoDestinatario}">
								<f:selectItems value="#{mto.listaTipoDestinatario}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.ldc}" styleClass="label" id="lblLDCC15"></h:outputLabel>
							<h:selectOneMenu id="cmbxLDCC15"  value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC15"></h:outputLabel>
							<h:selectOneMenu  id="cmbxPaisC15" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC15()" disabled="#{!mto.mapaAccion['ADD_DESTINATARIO'] }"></h:commandButton>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_DESTINATARIO'] }"></h:commandButton>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT15'] }"> </h:commandButton>
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT15'] }"> </h:commandButton>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC15Tabla">
							<rich:dataTable value="#{mto.destinatarios}" var="destinatario" id="tblDestinario" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>		
					                    <rich:column> <h:outputText value="Clave" /></rich:column>
					                    <rich:column> <h:outputText value="Nombre" /></rich:column>
					                    <rich:column> <h:outputText value="Apellido Paterno" /></rich:column>
					                    <rich:column> <h:outputText value="Apellido Materno" /></rich:column>
					                    <rich:column> <h:outputText value="Correo" /></rich:column>
					                    <rich:column> <h:outputText value="Tipo Destinatario" /></rich:column>
					                    <rich:column> <h:outputText value="Pais" /></rich:column>
					                    <rich:column> <h:outputText value="LDC" /></rich:column>
					                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
					            	</rich:columnGroup>
					            </f:facet>
					            <rich:column> <h:outputText value="#{destinatario.clave}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{destinatario.nombre}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{destinatario.apPaterno}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{destinatario.apMaterno}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{destinatario.mail}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{destinatario.descTipoDestinatario}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{destinatario.descPais}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{destinatario.descLDC}"/> </rich:column>
					        	 <rich:column>
					            	<rich:panel>
					            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_DESTINATARIO'] || !mto.modificarActivado }">
					            			<f:setPropertyActionListener  value="#{destinatario.idDestinatario}" target="#{mto.idDestinatario}"/>
											<f:setPropertyActionListener  value="#{destinatario.clave}" target="#{mto.clave }"/>
											<f:setPropertyActionListener  value="#{destinatario.apPaterno}" target="#{mto.apPaterno }"/>
											<f:setPropertyActionListener  value="#{destinatario.apMaterno}" target="#{mto.apMaterno }"/>
											<f:setPropertyActionListener  value="#{destinatario.mail}" target="#{mto.mail }"/>
											<f:setPropertyActionListener  value="#{destinatario.nombre}" target="#{mto.nombre }"/>
											<f:setPropertyActionListener  value="#{destinatario.idPais}" target="#{mto.idPais }"/>
											<f:setPropertyActionListener  value="#{destinatario.idTipoDestinatario}" target="#{mto.idTipoDestinatario }"/>
											<f:setPropertyActionListener  value="#{destinatario.idLDC}" target="#{mto.idLDC }"/>
										</h:commandButton>
										<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
											onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_DESTINATARIO'] || !mto.eliminarActivado }">
											<f:setPropertyActionListener  value="#{destinatario.idDestinatario}" target="#{mto.idDestinatario}"/>
										</h:commandButton>
					            	</rich:panel>
					            </rich:column>
					            <f:facet name="footer">
			            		<rich:datascroller id="dsC15" for="tblDestinario"></rich:datascroller>
			        		</f:facet>
					    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 16 - Tipo Destinatario - PW_TIPO_DESTINATARIO -->
					<rich:panel id="pnlc16" rendered="#{mto.pnl16}">
						<h:outputLabel value="#{msg.tituloCat16}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog16" columns="4">
							<h:outputLabel value="#{msg.cveObli}" styleClass="label" id="lblCveC16"></h:outputLabel>
							<h:inputText id="txtCveC16"  value="#{mto.clave}"  styleClass="caja" maxlength="50"></h:inputText>
							<h:outputLabel value="#{msg.descObli}" styleClass="label" id="lblDescC16"></h:outputLabel>
							<h:inputText id="txtDescC16"  value="#{mto.descTipoDestinatario}" styleClass="caja" maxlength="100"></h:inputText>
							
							<h:outputLabel value="#{msg.ldc}" styleClass="label" id="lblLDCC16"></h:outputLabel>
							<h:selectOneMenu  id="cmbxLDCC16"  value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC16"></h:outputLabel>
							<h:selectOneMenu  id="cmbxPaisC16" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC16()" disabled="#{!mto.mapaAccion['ADD_TIPDESTINATARIO'] }"></h:commandButton>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_TIPDESTINATARIO'] }"></h:commandButton>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT16'] }"> </h:commandButton>
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT16'] }"> </h:commandButton>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC16Tabla">
							<rich:dataTable value="#{mto.tipoDestinatarios}" var="tipoDestinatario" id="tblTipoDestinatario" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Clave" /></rich:column>
					                    <rich:column> <h:outputText value="Descripción" /></rich:column>
					                    <rich:column> <h:outputText value="Pais" /></rich:column>
					                    <rich:column> <h:outputText value="LDC" /></rich:column>
					                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
					            	</rich:columnGroup>
					        	</f:facet>
					            <rich:column> <h:outputText value="#{tipoDestinatario.clave}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{tipoDestinatario.descripcion}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{tipoDestinatario.descPais}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{tipoDestinatario.descLDC}"/> </rich:column>
					        	 <rich:column>
					            	<rich:panel>
					            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_TIPDESTINATARIO'] || !mto.modificarActivado }">
					            			<f:setPropertyActionListener  value="#{tipoDestinatario.idTipoDestinatario}" target="#{mto.idTipoDestinatario}"/>
											<f:setPropertyActionListener  value="#{tipoDestinatario.clave}" target="#{mto.clave }"/>
											<f:setPropertyActionListener  value="#{tipoDestinatario.descripcion}" target="#{mto.descTipoDestinatario }"/>
											<f:setPropertyActionListener  value="#{tipoDestinatario.idPais}" target="#{mto.idPais }"/>
											<f:setPropertyActionListener  value="#{tipoDestinatario.idLDC}" target="#{mto.idLDC }"/>
										</h:commandButton>
										<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
											onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_TIPDESTINATARIO']  || !mto.eliminarActivado }">
											<f:setPropertyActionListener  value="#{tipoDestinatario.idTipoDestinatario}" target="#{mto.idTipoDestinatario}"/>
										</h:commandButton>
					            	</rich:panel>
					            </rich:column>
					            <f:facet name="footer">
				            		<rich:datascroller id="dsC16" for="tblTipoDestinatario"></rich:datascroller>
				        		</f:facet>
					    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 17 - Reporte por Tipo Destinatario - PW_REPORTE_TIPO_DESTINATARIO -->
					<rich:panel id="pnlc17" rendered="#{mto.pnl17}">
						<h:outputLabel value="#{msg.tituloCat17}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog17" columns="4">
							<h:outputLabel value="#{msg.reporte}" styleClass="label" id="lblCveC17"></h:outputLabel>
							
							<h:selectOneMenu   id="cmbxReportes17" value="#{mto.idReporte}">
								<f:selectItems value="#{mto.listaReportes}" />
							</h:selectOneMenu>							
							<h:outputLabel value="#{msg.tipoDest}" styleClass="label" id="lblTipoDestC17"></h:outputLabel>
							<h:selectOneMenu id="cmbxTipoDestC17" value="#{mto.idTipoDestinatario}">
								<f:selectItems value="#{mto.listaTipoDestinatario}" />
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC17()" disabled="#{!mto.mapaAccion['ADD_REPORTETIPDESTINATARIO'] }"></h:commandButton>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_REPORTETIPDESTINATARIO'] }"></h:commandButton>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT17'] }"> </h:commandButton>
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT17'] }"> </h:commandButton>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC17Tabla">
							<rich:dataTable value="#{mto.reportesTipoDestinatario}" var="reporteTipoDestinatario" id="tblReporteTipoDestinatario" border="1" rows="10">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column style="width:220px"> <h:outputText value="Reporte" /></rich:column>
					                    <rich:column style="width:220px"> <h:outputText value="Tipo Destinatario" /></rich:column>
					                    <rich:column style="width:160px"> <h:outputText value="Acciones" /> </rich:column>
					            	</rich:columnGroup>
					        	</f:facet>
					            <rich:column> <h:outputText value="#{reporteTipoDestinatario.clave}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{reporteTipoDestinatario.descTipoDestinatario}"/> </rich:column>
					        	 <rich:column>
					            	<rich:panel>
					            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_REPORTETIPDESTINATARIO'] || !mto.modificarActivado }">
					            			<f:setPropertyActionListener  value="#{reporteTipoDestinatario.idReporteTipoDestinatario}" target="#{mto.idReporteTipoDestinatario}"/>
											<f:setPropertyActionListener  value="#{reporteTipoDestinatario.idReporte}" target="#{mto.idReporte}"/>
											<f:setPropertyActionListener  value="#{reporteTipoDestinatario.idTipoDestinatario}" target="#{mto.idTipoDestinatario }"/>
										</h:commandButton>
										<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
											onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_REPORTETIPDESTINATARIO'] || !mto.eliminarActivado }">
											<f:setPropertyActionListener  value="#{reporteTipoDestinatario.idReporteTipoDestinatario}" target="#{mto.idReporteTipoDestinatario}"/>
										</h:commandButton>
					            	</rich:panel>
					            </rich:column>
					            <f:facet name="footer">
				            		<rich:datascroller id="dsC17" for="tblReporteTipoDestinatario"></rich:datascroller>
				        		</f:facet>
					    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 18 - Reporte - PW_REPORTE -->
					<rich:panel id="pnlc18" rendered="#{mto.pnl18}">
						<h:outputLabel value="#{msg.tituloCat18}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog18" columns="6">
							<h:outputLabel value="#{msg.nom}" styleClass="label" id="lblNombreC18"></h:outputLabel>
							<h:inputText id="txtNombreC18" label="Nombre" value="#{mto.nombre}"  styleClass="caja" maxlength="100" disabled="true"></h:inputText>
							<h:outputLabel value="#{msg.descObli}" styleClass="label" id="lblDescC18"></h:outputLabel>
							<h:inputText id="txtDescC18" label="Descripción" value="#{mto.descripcion}" styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.coment}" styleClass="label" id="lblComentarioC18"></h:outputLabel>
							<h:inputText id="txtComentarioC18" label="Comentarios" value="#{mto.comentario}" styleClass="caja" maxlength="250"></h:inputText>
						
							<h:outputLabel value="#{msg.nomTemplate}" styleClass="label" id="lblNombreTempC18"></h:outputLabel>
							<h:inputText id="txtNombreTempC18" label="Nombre" value="#{mto.nombreTemplate}" styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.rutaTemp}" styleClass="label" id="lblRutaTempC18"></h:outputLabel>
							<h:inputText id="txtRutaTempC18" label="Ruta Template" value="#{mto.rutaTemplate}" styleClass="caja" maxlength="250"></h:inputText>
							
							<h:outputLabel value="#{msg.ldc}" styleClass="label" id="lblLDCC18"></h:outputLabel>
							<h:selectOneMenu   id="cmbxLDCC18" value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC18"></h:outputLabel>
							<h:selectOneMenu  id="cmbxPaisC18" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC18()" disabled="#{!mto.mapaAccion['ADD_REPORTE'] }"></h:commandButton>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT18'] }"> </h:commandButton>
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT18'] }"> </h:commandButton>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC18Tabla">
							<rich:dataTable value="#{mto.reportes}" var="reporte" id="tblReporte" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Nombre" /></rich:column>
					                    <rich:column> <h:outputText value="Descripción" /></rich:column>
					                    <rich:column> <h:outputText value="Nombre Template" /></rich:column>
					                    <rich:column> <h:outputText value="Ruta Template" /></rich:column>
					                    <rich:column> <h:outputText value="LDC" /></rich:column>
					                    <rich:column> <h:outputText value="Pais" /></rich:column>
					                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
					            	</rich:columnGroup>
					        	</f:facet>
					            <rich:column> <h:outputText value="#{reporte.nombre}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{reporte.descripcion}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{reporte.nombreTemplate}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{reporte.rutaTemplate}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{reporte.descLDC}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{reporte.descPais}"/> </rich:column>
					        	 <rich:column>
					            	<rich:panel>
					            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_REPORTE'] || !mto.modificarActivado }">
					            			<f:setPropertyActionListener  value="#{reporte.idReporte}" target="#{mto.idReporte}"/>
											<f:setPropertyActionListener  value="#{reporte.nombre}" target="#{mto.nombre }"/>
											<f:setPropertyActionListener  value="#{reporte.descripcion}" target="#{mto.descripcion }"/>
											<f:setPropertyActionListener  value="#{reporte.nombreTemplate}" target="#{mto.nombreTemplate }"/>
											<f:setPropertyActionListener  value="#{reporte.rutaTemplate}" target="#{mto.rutaTemplate }"/>
											<f:setPropertyActionListener  value="#{reporte.idLDC}" target="#{mto.idLDC }"/>
											<f:setPropertyActionListener  value="#{reporte.idPais}" target="#{mto.idPais }"/>
											<f:setPropertyActionListener  value="#{reporte.comentario}" target="#{mto.comentario }"/>
										</h:commandButton>
									</rich:panel>
					            </rich:column>
					            <f:facet name="footer">
				            		<rich:datascroller id="dsC18" for="tblReporte"></rich:datascroller>
				        		</f:facet>
					    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
			
					<!-- Catalogo 19 - Parametros - CEDIS - PW_LDC -->
					<rich:panel id="pnlc19" rendered="#{mto.pnl19}">
						<h:outputLabel value="#{msg.tituloCat19}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog19" columns="6">
							<h:outputLabel value="#{msg.cveObli}" styleClass="label" id="lblClaveC19"></h:outputLabel>
							<h:inputText id="txtClaveC19" label="Clave" value="#{mto.clave}" styleClass="caja" maxlength="50"></h:inputText>
							<h:outputLabel value="#{msg.descObli}" styleClass="label" id="lblDescC19"></h:outputLabel>
							<h:inputText id="txtDescC19" value="#{mto.descLDC}" styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.raSoc}" styleClass="label" id="lblRazonSocC19"></h:outputLabel>
							<h:inputText id="txtRazonSocC19" label="Razón Social" value="#{mto.razonSocial}" styleClass="caja" maxlength="250"></h:inputText>
							
							<h:outputLabel value="#{msg.mail}" styleClass="label" id="lblMailC19"></h:outputLabel>
							<h:inputText id="txtMailC19" label="Correo Electrónico" value="#{mto.mail}" styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.pass}" styleClass="label" id="lblPasswordC19"></h:outputLabel>
							<h:inputText id="txtPasswordC19" label="Contraseña" value="#{mto.password}" styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.port}" styleClass="label" id="lblPuertoC19"></h:outputLabel>
							<h:inputText id="txtPuertoC19" label="Puerto" value="#{mto.puerto}" styleClass="caja" maxlength="5"></h:inputText>
						
							<h:outputLabel value="#{msg.smpt}" styleClass="label" id="lblServerSMTPC19"></h:outputLabel>
							<h:inputText id="txtServerSMTPC19" value="#{mto.serverSMTP}"  styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.ipserver}" styleClass="label" id="lblIPServerC19"></h:outputLabel>
							<h:inputText id="txtIPServerC19" value="#{mto.ipServerSOS}" styleClass="caja" maxlength="16"></h:inputText>
							<h:outputLabel value="#{msg.tipSeg}" styleClass="label" id="lblTipoSegC19"></h:outputLabel>
							<h:inputText id="txtTipoSegC19" value="#{mto.tipoSeguridad}" styleClass="caja" maxlength="100"></h:inputText>
							
							<h:outputLabel value="#{msg.pobla}" styleClass="label" id="lblPoblacionC19"></h:outputLabel>
							<h:inputText id="txtPoblacionC19" value="#{mto.poblacion}" styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.reg}" styleClass="label" id="lblRegionC19"></h:outputLabel>
							<h:inputText id="txtRegionC19" value="#{mto.region}" styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC19"></h:outputLabel>
							<h:selectOneMenu  id="cmbxPaisC19" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.fMax}" styleClass="label" id="lblFactorMaxC19"></h:outputLabel>
							<h:inputText id="txtFactorMaxC19" value="#{mto.factorMax}"  styleClass="caja" maxlength="9"></h:inputText>
							<h:outputLabel value="#{msg.fMin}" styleClass="label" id="lblFactorMinC19"></h:outputLabel>
							<h:inputText id="txtFactorMinC19" value="#{mto.factorMin}" styleClass="caja" maxlength="9"></h:inputText>
							<h:outputLabel value="#{msg.logoLDC}" styleClass="label" id="lblLogoLdcC19"></h:outputLabel>
							<t:inputFileUpload id="ifuLogoC19" value="#{mto.logLDC }"></t:inputFileUpload>
							
							<h:outputLabel value="#{msg.logoAvon}" styleClass="label" id="lblLogoAvonC19"></h:outputLabel>
							<t:inputFileUpload id="ifuLogoAvonC19" value="#{mto.logAVON }" storage="file" accept="mime-type"></t:inputFileUpload>
							<h:outputLabel value="#{msg.codigEnt}" styleClass="label" id="lblCodigoEntradaC19"></h:outputLabel>
							<h:inputText id="txtCodigoEntradaC19" value="#{mto.codigoBarrasEntrada}"  styleClass="caja" maxlength="14"></h:inputText>
							<h:outputLabel value="#{msg.codigSal}" styleClass="label" id="lblCodigoSalidaC19"></h:outputLabel>
							<h:inputText id="txtCodigoSalidaC19" value="#{mto.codigoBarrasSalida}" styleClass="caja" maxlength="14"></h:inputText>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC19()" id="btnGuardarC19" disabled="#{!mto.mapaAccion['ADD_LDC'] }"></h:commandButton>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_LDC'] }"></h:commandButton>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT19'] }"> </h:commandButton>
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT19'] }"> </h:commandButton>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC19Tabla">
							<rich:dataTable value="#{mto.ldccs}" var="ldc" id="tblLDC" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Clave" /></rich:column>
					                    <rich:column> <h:outputText value="Descripción" /></rich:column>
					                    <rich:column> <h:outputText value="Razón Social" /></rich:column>
					                    <rich:column> <h:outputText value="Pais" /></rich:column>
					                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
					            	</rich:columnGroup>
					            </f:facet>
					        	<rich:column> <h:outputText value="#{ldc.clave}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{ldc.desc}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{ldc.razonSocial}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{ldc.descPais}"/> </rich:column>
					        	 <rich:column>
					            	<rich:panel>
					            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_LDC'] || !mto.modificarActivado }">
					            			<f:setPropertyActionListener  value="#{ldc.idLdc}" target="#{mto.idLDC}"/>
											<f:setPropertyActionListener  value="#{ldc.desc}" target="#{mto.descLDC }"/>
											<f:setPropertyActionListener  value="#{ldc.clave}" target="#{mto.clave }"/>
											<f:setPropertyActionListener  value="#{ldc.razonSocial}" target="#{mto.razonSocial }"/>
											<f:setPropertyActionListener  value="#{ldc.idPais}" target="#{mto.idPais }"/>
											<f:setPropertyActionListener  value="#{ldc.tipoSeguridad}" target="#{mto.tipoSeguridad }"/>
											<f:setPropertyActionListener  value="#{ldc.mail}" target="#{mto.mail }"/>
											<f:setPropertyActionListener  value="#{ldc.password}" target="#{mto.password }"/>
											<f:setPropertyActionListener  value="#{ldc.puerto}" target="#{mto.puerto }"/>
											<f:setPropertyActionListener  value="#{ldc.serverSMTP}" target="#{mto.serverSMTP }"/>
											<f:setPropertyActionListener  value="#{ldc.ipServerSOS}" target="#{mto.ipServerSOS }"/>
											<f:setPropertyActionListener  value="#{ldc.poblacion}" target="#{mto.poblacion }"/>
											<f:setPropertyActionListener  value="#{ldc.region}" target="#{mto.region }"/>
											<f:setPropertyActionListener  value="#{ldc.factorMax}" target="#{mto.factorMax }"/>
											<f:setPropertyActionListener  value="#{ldc.factorMin}" target="#{mto.factorMin }"/>
											<f:setPropertyActionListener  value="#{ldc.logoLDC}" target="#{mto.logoLDC }"/>
											<f:setPropertyActionListener  value="#{ldc.logoAVON}" target="#{mto.logoAVON }"/>
											<f:setPropertyActionListener  value="#{ldc.codigoBarrasEntrada}" target="#{mto.codigoBarrasEntrada }"/>
											<f:setPropertyActionListener  value="#{ldc.codigoBarrasSalida}" target="#{mto.codigoBarrasSalida }"/>
										</h:commandButton>
										<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
											onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_LDC'] || !mto.eliminarActivado }">
											<f:setPropertyActionListener  value="#{ldc.idLdc}" target="#{mto.idLDC}"/>
										</h:commandButton>
					            	</rich:panel>
					            </rich:column>
					            <f:facet name="footer">
				            		<rich:datascroller id="dsC19" for="tblLDC"></rich:datascroller>
				        		</f:facet>
					    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
											
					<!-- Catalogo 20 - Usuario - PW_USUARIO -->
					<rich:panel id="pnlc20" rendered="#{mto.pnl20}">
						<h:outputLabel value="#{msg.tituloCat20}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog20" columns="6">
							<h:outputLabel value="#{msg.user}" styleClass="label" id="lblUserC20"></h:outputLabel>
							<h:inputText id="txtUserC20" label="Usuario" value="#{mto.user}" styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.passObli}" styleClass="label" id="lblPasswordC20"></h:outputLabel>
							<h:inputText id="txtPasswordC20" value="#{mto.password}" styleClass="caja" maxlength="100"></h:inputText>
							<h:outputLabel value="#{msg.empleado}" styleClass="label" id="lblEmpleadoC20"></h:outputLabel>
							<h:selectOneMenu  id="cmbxEmpleadoC20" value="#{mto.idEmpleado}">
								<f:selectItems value="#{mto.listaEmpleadosExistentes}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.tipoUser}" styleClass="label" id="lblTipoUserC20"></h:outputLabel>
							<h:selectOneMenu  id="cmbxTipoUserC20" value="#{mto.idTipoUsuario}">
								<f:selectItems value="#{mto.listaTipoUsuarioExistentes}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.perf}" styleClass="label" id="lblPerfilC20"></h:outputLabel>
							<h:selectOneMenu id="cmbxPerfilC20" value="#{mto.idPerfil}">
								<f:selectItems value="#{mto.listaPerfilesExistentes}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.ldc}" styleClass="label" id="lblLDCC20"></h:outputLabel>
							<h:selectOneMenu id="cmbxLDCC20" value="#{mto.idLDC}" disabled="true">
								<f:selectItems value="#{mto.listaLDC}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.pais}" styleClass="label" id="lblPaisC20"></h:outputLabel>
							<h:selectOneMenu  id="cmbxPaisC20" value="#{mto.idPais}" disabled="true">
								<f:selectItems value="#{mto.listaPaises}" />
							</h:selectOneMenu>
							<h:outputLabel value="#{msg.coment}" styleClass="label" id="lblComentarioC20"></h:outputLabel>
							<h:inputText id="txtComentC20" value="#{mto.comentario}" maxlength="250"></h:inputText>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC20()" disabled="#{!mto.mapaAccion['ADD_USER'] }"></h:commandButton>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_USER'] }"></h:commandButton>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT20'] }"> </h:commandButton>
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT20'] }"> </h:commandButton>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC20Tabla">
							<rich:dataTable value="#{mto.users}" var="usuario" id="tblUsuario" border="1">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Usuario" /></rich:column>
					                    <rich:column> <h:outputText value="Tipo Usuario" /></rich:column>
					                    <rich:column> <h:outputText value="LDC" /></rich:column>
					                    <rich:column> <h:outputText value="Pais" /></rich:column>
					                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
					            	</rich:columnGroup>
					            </f:facet>
					        	<rich:column> <h:outputText value="#{usuario.usuario}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{usuario.descTipoUsuario}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{usuario.descLDC}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{usuario.descPais}"/> </rich:column>
					        	 <rich:column>
					            	<rich:panel>
					            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_USER'] || !mto.modificarActivado }">
					            			<f:setPropertyActionListener  value="#{usuario.idUsuario}" target="#{mto.idUsuario}"/>
											<f:setPropertyActionListener  value="#{usuario.usuario}" target="#{mto.user }"/>
											<f:setPropertyActionListener  value="#{usuario.password}" target="#{mto.password }"/>
											<f:setPropertyActionListener  value="#{usuario.comentario}" target="#{mto.comentario }"/>
											<f:setPropertyActionListener  value="#{usuario.idEmpleado}" target="#{mto.idEmpleado }"/>
											<f:setPropertyActionListener  value="#{usuario.idPerfil}" target="#{mto.idPerfil }"/>
											<f:setPropertyActionListener  value="#{usuario.idTipoUsuario}" target="#{mto.idTipoUsuario }"/>
											<f:setPropertyActionListener  value="#{usuario.idLDC}" target="#{mto.idLDC }"/>
											<f:setPropertyActionListener  value="#{usuario.idPais}" target="#{mto.idPais }"/>
										</h:commandButton>
										<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
											onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_USER'] || !mto.eliminarActivado }">
											<f:setPropertyActionListener  value="#{usuario.idUsuario}" target="#{mto.idUsuario}"/>
										</h:commandButton>
					            	</rich:panel>
					            </rich:column>
					            <f:facet name="footer">
				            		<rich:datascroller id="dsC20" for="tblUsuario"></rich:datascroller>
				        		</f:facet>
					    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 21 - Perfil - PW_PERFIL -->
					<rich:panel id="pnlc21" rendered="#{mto.pnl21}">
						<h:outputLabel value="#{msg.tituloCat21}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
					<center>
					<div style="height:250px;width:100%;overflow-y:scroll;position: static;">
						<h:panelGrid columns="2">
						<div style="position: static;">
							<h:panelGrid id="pnlCatalog21" columns="4">
								<h:outputLabel value="#{msg.cveObli}" styleClass="label" id="lblClaveC21"></h:outputLabel>
								<h:inputText id="txtClaveC21" value="#{mto.clave}" styleClass="caja" maxlength="50"></h:inputText>
								<h:outputLabel value="#{msg.descObli}" styleClass="label" id="lblDescC21"></h:outputLabel>
								<h:inputText id="txtDescC21" value="#{mto.descPerfil}" styleClass="caja" maxlength="100"></h:inputText>
							</h:panelGrid>
						</div>
							<h:panelGrid id="pnlPermisos" rendered="false" binding="#{mto.pnlPermisos }" >
									<rich:dataTable value="#{mto.permisosCheck}" var="permiso" id="tblPermisos" border="1">
										 <f:facet name="header">
							                <rich:columnGroup>
							                    <rich:column colspan="4"> <h:outputText value="Permisos por Perfil" /></rich:column>
							                    <rich:column breakBefore="true"> <h:outputText value="Modulo" /></rich:column>
							                    <rich:column> <h:outputText value="Acción" /></rich:column>
							                    <rich:column> <h:outputText value="Tiene Permiso" /></rich:column>
							                    <rich:column> 
							                    	<h:selectBooleanCheckbox id="selected" 	valueChangeListener="#{mto.seleccionaTodosPermisos }" 
							                    							   onclick="submit()">
							                    			<a4j:support event="onchange" reRender="tblPermisos,frmCatalog21"></a4j:support>
							                    	</h:selectBooleanCheckbox>
							                    	<h:outputText value="Asignar/Denegar Permiso" />
							                    </rich:column>
							            	</rich:columnGroup>
							            </f:facet>
						           		<rich:column> <h:outputText value="#{permiso.descModulo}"/> </rich:column>
							        	<rich:column> <h:outputText value="#{permiso.descModuloAccion}"/> </rich:column>
							        	<rich:column> <h:outputText value="#{permiso.tienePermiso}"/> </rich:column>
							        	 <rich:column>
							            	<rich:panel style="align:center;">
							            		<h:selectBooleanCheckbox id="mtoSel" value="#{permiso.permiso}" 
							            								  disabled="#{!mto.mapaAccion['ADD_PERMISO']}">
							            		</h:selectBooleanCheckbox>
							            	</rich:panel>
							            </rich:column>
							             <f:facet name="footer">
							                <rich:columnGroup>
							                    <rich:column> <h:outputText value="Modulo" /></rich:column>
							                    <rich:column> <h:outputText value="Acción" /></rich:column>
							                    <rich:column> <h:outputText value="Tiene Permiso" /></rich:column>
							                    <rich:column> 
							                    	<h:selectBooleanCheckbox id="selectedf"	valueChangeListener="#{mto.seleccionaTodosPermisos }" 
							                    							   onclick="submit()">
							                    			<a4j:support event="onchange" reRender="tblPermisos,frmCatalog21"></a4j:support>
							                    	</h:selectBooleanCheckbox>
							                    	<h:outputText value="Asignar/Denegar Permiso" />
							                    </rich:column>
							            	</rich:columnGroup>
							            </f:facet>
							    	</rich:dataTable>
							</h:panelGrid>
							</h:panelGrid>
							</div>
						</center>
						<h:panelGroup>
								<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC21()" disabled="#{!mto.mapaAccion['ADD_PERFIL'] }"></h:commandButton>
								<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
								<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_PERFIL'] }"></h:commandButton>
								<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT21'] }"> </h:commandButton>
								<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT21'] }"> </h:commandButton>
								<!-- Mensaje de Respuesta del WS -->
								<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC21Tabla">
							<rich:dataTable value="#{mto.perfiles}" var="perfil" id="tblPerfil" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Clave" /></rich:column>
					                    <rich:column> <h:outputText value="Descripción" /></rich:column>
					                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
					            	</rich:columnGroup>
					            </f:facet>
					        	<rich:column> <h:outputText value="#{perfil.clave}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{perfil.descripcion}"/> </rich:column>
					        	 <rich:column>
					            	<rich:panel>
					            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_PERFIL'] || !mto.modificarActivado }">
					            			<f:setPropertyActionListener  value="#{perfil.idPerfil}" target="#{mto.idPerfil}"/>
											<f:setPropertyActionListener  value="#{perfil.descripcion}" target="#{mto.descPerfil }"/>
											<f:setPropertyActionListener  value="#{perfil.clave}" target="#{mto.clave }"/>
										</h:commandButton>
										<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
											onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_PERFIL'] || !mto.eliminarActivado }">
											<f:setPropertyActionListener  value="#{perfil.idPerfil}" target="#{mto.idPerfil}"/>
										</h:commandButton>
					            	</rich:panel>
					            </rich:column>
					            <f:facet name="footer">
				            		<rich:datascroller id="dsC21" for="tblPerfil"></rich:datascroller>
				        		</f:facet>
					    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 22 - Denominacion - PW_DENOMINACIONES -->
					<rich:panel id="pnlc22" rendered="#{mto.pnl22}">
						<h:outputLabel value="#{msg.tituloCat22}" styleClass="tituloPagina"></h:outputLabel>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog22" columns="4">
							<h:outputLabel value="#{msg.denom}" styleClass="label" id="lblDenominacionC22"></h:outputLabel>
							<h:inputText id="txtDenominacionC22" label="Denominación" value="#{mto.descDenominacion}"  styleClass="caja" maxlength="8"></h:inputText>
							<h:outputLabel value="#{msg.tipo}" styleClass="label" id="lblTipoC22"></h:outputLabel>
							<h:selectOneMenu   id="cmbxTipoC20" value="#{mto.idTipo}">
								<f:selectItems value="#{mto.listaTipoCambio}" />
							</h:selectOneMenu>
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosC22()" disabled="#{!mto.mapaAccion['ADD_DENOMINA'] }"></h:commandButton>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"> </h:commandButton>
							<h:commandButton value="Nuevo" action="#{mto.nuevoCatalogo }"  accesskey="n" disabled="#{!mto.mapaAccion['ADD_DENOMINA'] }"></h:commandButton>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT22'] }"> </h:commandButton>
							<h:commandButton action="#{mto.exportarGridPDF}"   value="Exportar a PDF"   disabled="#{!mto.mapaAccion['EXPORT_CAT22'] }"> </h:commandButton>
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC22Tabla">
							<rich:dataTable value="#{mto.denominaciones}" var="denominacion" id="tblDenominacion" border="1" rows="50">
								 <f:facet name="header">
					                <rich:columnGroup>
					                    <rich:column> <h:outputText value="Denominación" /></rich:column>
					                    <rich:column> <h:outputText value="Tipo" /></rich:column>
					                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
					            	</rich:columnGroup>
					            </f:facet>
					        	<rich:column> <h:outputText value="#{denominacion.denominacion}"/> </rich:column>
					        	<rich:column> <h:outputText value="#{denominacion.tipo}"/> </rich:column>
					        	 <rich:column>
					            	<rich:panel>
					            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" disabled="#{!mto.mapaAccion['UPD_DENOMINA'] || !mto.modificarActivado }">
					            			<f:setPropertyActionListener  value="#{denominacion.idDenominacion}" target="#{mto.idDenominacion}"/>
											<f:setPropertyActionListener  value="#{denominacion.denominacion}" target="#{mto.descDenominacion }"/>
											<f:setPropertyActionListener  value="#{denominacion.idTipo}" target="#{mto.idTipo }"/>
										</h:commandButton>
										<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" 
											onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_DENOMINA'] || !mto.eliminarActivado }">
											<f:setPropertyActionListener  value="#{denominacion.idDenominacion}" target="#{mto.idDenominacion}"/>
										</h:commandButton>
					            	</rich:panel>
					            </rich:column>
					            <f:facet name="footer">
				            		<rich:datascroller id="dsC22" for="tblDenominacion"></rich:datascroller>
				        		</f:facet>
					    	</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Catalogo 23 - Rutas alternas -->
					<rich:panel id="pnlc23" rendered="#{mto.pnl23 }">
						<h:outputLabel value="#{msg.catRutasAlternas_tituloCatalogo}" styleClass="tituloPagina"/>
						<br></br><br></br>
						<h:panelGrid id="pnlCatalog23" columns="4" rendered="true">
						
							<h:outputLabel value="#{msg.catRutasAlternas_zona}" id="lblCatRutaAlternaZona" styleClass="label"/>
							<h:selectOneMenu  id="cmbxCatRutaAlternaZona"  disabled="false"
								 label="Zona" title="Selecciona una opción" value="#{mto.catRutasAlternasZona}">
								<f:selectItems value="#{mto.catRutasAlternasListZonas}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.catRutasAlternas_rutaOTS}" id="lblCatRutaAlternaRutaOTS" styleClass="label"/>
							<h:inputText id="txtCatRutaAlternaRutaOTS" styleClass="caja" label="Ruta OTS" maxlength="3"  value="#{mto.catRutasAlternasRutaOTS }"
							onkeypress="return permite(event, 'num')">
							</h:inputText>
							
							<h:outputLabel value="#{msg.catRutasAlternas_rutaSOS}" id="lblCatRutaAlternaRutaSOS" styleClass="label"/>
							<h:inputText id="txtCatRutaAlternaRutaSOS" styleClass="caja" label="Ruta SOS" maxlength="3"  value="#{mto.catRutasAlternasRutaSOS }"
							onkeypress="return permite(event, 'num')">
							</h:inputText>
							
							<h:outputLabel value="#{msg.catRutasAlternas_orden}" id="lblCatRutaAlternaOrden" styleClass="label"/>
							<h:selectOneMenu  id="cmbxCatRutaAlternaOrden"  disabled="false"
								 label="Orden" title="Selecciona una opción" value="#{mto.catRutasAlternasOrden}">
								<f:selectItem itemLabel="1" itemValue="1" />
								<f:selectItem itemLabel="2" itemValue="2" />
								<f:selectItem itemLabel="3" itemValue="3" />
								<f:selectItem itemLabel="4" itemValue="4" />
								<f:selectItem itemLabel="5" itemValue="5" />
							</h:selectOneMenu>
							
						</h:panelGrid>
						<h:panelGroup>
							<h:commandButton action="#{mto.guardarCatalogo}" value="Guardar" accesskey="g" onclick="return requeridosRutasAlternas()" disabled="#{!mto.mapaAccion['ADD_RUTAALTERNA'] }"/>
							<h:commandButton action="#{mto.cancelarCatalogo}" value="Cancelar" accesskey="c"/> 
							<h:commandButton action="#{mto.nuevoCatalogo }" value="Nuevo" accesskey="n" disabled="#{!mto.mapaAccion['ADD_RUTAALTERNA'] }"/>
							<h:commandButton action="#{mto.exportarGridExcel}" value="Exportar a Excel" disabled="#{!mto.mapaAccion['EXPORT_CAT23'] }"/> 
							<h:commandButton action="#{mto.exportarGridPDF}" value="Exportar a PDF" disabled="#{!mto.mapaAccion['EXPORT_CAT23'] }"/> 
							<!-- Mensaje de Respuesta del WS -->
							<h:outputLabel value="#{mto.msg}" style="color:#FF0000;"></h:outputLabel>
						</h:panelGroup>
						<h:panelGrid id="pnlC23Tabla">
								<rich:dataTable value="#{mto.listRutasAlternas}" var="ruta" id="tblRutasAlternas" border="1" rows="10">
									 <f:facet name="header">
						                <rich:columnGroup>
						                	<rich:column> <h:outputText value="Zona" /></rich:column>
						                    <rich:column> <h:outputText value="Ruta OTS" /></rich:column>
						                    <rich:column> <h:outputText value="Ruta SOS" /></rich:column>
						                    <rich:column> <h:outputText value="Orden" /> </rich:column>
						                    <rich:column> <h:outputText value="Acciones" /> </rich:column>
						                </rich:columnGroup>
						            </f:facet>
									<rich:column filterMethod="#{mto.filterZonaRutaAlterna }" filterEvent="onblur">
										<f:facet name="header">
					            			<h:inputText value="#{mto.catRutasAlternasFiltroZona}" id="txtFilterZona">
						                        <a4j:support event="onkeyup" reRender="tblRutasAlternas" 
						                            		 ignoreDupResponses="true" requestDelay="1000"/>
						                    </h:inputText>
					            		</f:facet>
									 	<h:outputText value="#{ruta.zona}"></h:outputText>
									</rich:column>
									<rich:column> <h:outputText value="#{ruta.rutaOTS}"></h:outputText></rich:column>
									<rich:column> <h:outputText value="#{ruta.rutaSOS}" /> </rich:column>
						            <rich:column> <h:outputText value="#{ruta.orden}" /> </rich:column>
						            <rich:column>
						            	<rich:panel>
						            		<h:commandButton action="#{mto.modificarCatalogo}" value="Modificar" accesskey="m" 
						            			disabled="#{!mto.mapaAccion['UPD_RUTAALTERNA'] || !mto.modificarActivado }">
												<f:setPropertyActionListener  value="#{ruta.idRutaAlterna}" target="#{mto.catRutasAlternasIdRutaAlterna }"/>
												<f:setPropertyActionListener  value="#{ruta.idZona}" target="#{mto.catRutasAlternasZona }"/>
												<f:setPropertyActionListener  value="#{ruta.rutaOTS}" target="#{mto.catRutasAlternasRutaOTS }"/>
												<f:setPropertyActionListener  value="#{ruta.rutaSOS}" target="#{mto.catRutasAlternasRutaSOS }"/>
												<f:setPropertyActionListener  value="#{ruta.orden}" target="#{mto.catRutasAlternasOrden }"/>
											</h:commandButton>
											<h:commandButton action="#{mto.eliminarCatalogo}" value="Eliminar" accesskey="e" 
												onclick="return confirmaEliminar()" immediate="true" disabled="#{!mto.mapaAccion['DEL_RUTAALTERNA'] || !mto.eliminarActivado }">
												<f:setPropertyActionListener  value="#{ruta.idRutaAlterna}" target="#{mto.catRutasAlternasIdRutaAlterna }"/>
											</h:commandButton>
						            	</rich:panel>
						            </rich:column>
						             <f:facet name="footer">
						            	<rich:datascroller id="dsC23" for="tblRutasAlternas"></rich:datascroller>
						            </f:facet>
								</rich:dataTable>
						</h:panelGrid>
					</rich:panel>
					
					<!-- Fin -->
				</h:panelGrid>
				</div>
			</h:form>

	</ui:define>
</ui:composition>
</f:view>
</body>
</html>