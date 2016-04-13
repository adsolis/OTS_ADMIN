<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:t="http://myfaces.apache.org/tomahawk"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" /> 
<title>Cierre de Zona</title>
</head>
<body>
	<f:view>
		<!-- Se agrego composition para masterPAge -->
		<ui:composition template="/Paginas/template.jsp">
			<ui:define name="content">
				<!--  Mensajes de la Vista -->
				<f:loadBundle
					basename="com.datacode.avon_ots_admin.cu002.messagesCU002"
					var="msg" />
				<!-- Diseno de pagina -->
				<h:outputLabel value="#{msg.tituloCierre}"
						styleClass="tituloPagina"></h:outputLabel>
				<h:panelGrid columns="2">
					<a4j:form id="frmPrincipal" binding="#{cierreZona.frmPrincipal}">
							<t:fieldset legend="#{msg.encabezado}" style="color: black">
								<h:panelGrid style="text-align: left">
									<h:outputText value="" id="PnlTituloCierre"
										styleClass="tituloPagina" />
									<h:selectOneRadio id="OptTipoItem" required="true"
										styleClass="label" requiredMessage="Seleccione Opcion"
										value="#{cierreZona.tipoItem}">
										<f:selectItem itemLabel="Items(cajas, unitários)"
											itemValue="0" />
										<f:selectItem itemLabel="Premios" itemValue="1" />
									</h:selectOneRadio>

									<h:panelGrid columns="2" style="text-align: left">
										<h:outputLabel value="#{msg.zona}" id="LblZonas"
											styleClass="label" />

										<h:selectOneMenu id="CbxZonas" value="#{cierreZona.idZona}">
											<f:selectItems value="#{cierreZona.cmbZonas}" />
											<a4j:support event="onchange"
												action="#{cierreZona.obtenerCampanias}"
												reRender="CbxCampanias,selectCbxCampanias" />
										</h:selectOneMenu>

										<h:outputLabel value="#{msg.campania}" id="LblCampanias"
											styleClass="label" />

										<h:selectOneMenu id="CbxCampanias"
											value="#{cierreZona.idCampania}">
											<f:selectItems value="#{cierreZona.campanias}"
												id="selectCbxCampanias" />
										</h:selectOneMenu>

										<h:commandButton id="BtnTest" action="#{cierreZona.prueba}"
											value="#{msg.btnItems}" disabled="#{!cierreZona.btnItems}">
											<a4j:support event="action" reRender="frmTabla,GrdLista"
												ignoreDupResponses="true" requestDelay="700" />
										</h:commandButton>
									</h:panelGrid>
								</h:panelGrid>

								<h:panelGrid columns="3">
									<h:outputText value="#{msg.codigo}" id="LblTituloCodigo"
										styleClass="label" />

									<h:inputText id="TxtCodLeido"
										value="#{cierreZona.codigo_barras_leido }"
										maxlength="30"
										onkeypress="return quitarEnter(event);"
												onkeyup="mandarEnter(event);"
										styleClass="inputText">
										<a4j:support event="onkeyup" focus="TxtCodLeido"
											ignoreDupResponses="true" requestDelay="1000"
											reRender="ImgSemaforo,TxtCodLeido,outTextEstatus,outTextEscaneados" />
									</h:inputText>

									<a4j:commandButton id="btnValida" value="valida codigo"
										action="#{cierreZona.Valida}"
										disabled="#{!cierreZona.btnValida}"
										onclick="resaltarRowTabla();"
										reRender="ImgSemaforo,TxtCodLeido,outTextEstatus,outTextEscaneados"
										focus="TxtCodLeido">
									</a4j:commandButton>

									<a4j:commandButton id="BtnSalir" value="#{msg.btnBack}"
										action="#{cierreZona.Cancelar}"
										disabled="#{!cierreZona.btnCancel}"
										reRender="ImgSemaforo,frmPrincipal,frmTabla" />
									<h:commandButton id="BtnGuardar" value="#{msg.btnClose}"
										onclick="return confirmaGuardar();"
										action="#{cierreZona.GuardaItemsCierreZona}"
										disabled="#{!cierreZona.btnCerrar}" />
								</h:panelGrid>
								<h:outputText value="#{cierreZona.mensajeError}" id="LblMensaje"
									styleClass="error" />
							</t:fieldset>
						
					</a4j:form>


					<a4j:form binding="#{cierreZona.frmTabla}" styleClass="todo"
						id="frmTabla" rendered="false">
				
							<h:panelGrid columns="2">
								<h:panelGroup>
									<div id="Div_Prueba"
										style="width: 600px; height: 280px; overflow: scroll">

										<rich:dataTable id="GrdLista" value="#{cierreZona.cerrarZona}"
											var="cierre" border="1">
											<f:facet name="header">
												<rich:columnGroup>
													<rich:column id="hdAccount">
														<h:outputText value="Account" />
													</rich:column>
													<rich:column id="hdOrden" visible="false">
														<h:outputText value="Orden" />
													</rich:column>
													<rich:column>
														<h:outputText id="hdClaveorden" value="Nombre" />
													</rich:column>
													<rich:column visible="false">
														<h:outputText value="Clave Orden" />
													</rich:column>
													<rich:column id="hdCodigoItem" visible="false">
														<h:outputText value="Código Ítem" />
													</rich:column>
													<rich:column>
														<h:outputText id="hdRutaSecuencia" value="Ruta/Secuencia" />
													</rich:column>
													<rich:column>
														<h:outputText id="hdTipoProducto" value="Tipo de Producto" />
													</rich:column>
													<rich:column>
														<h:outputText id="hdCodigoBarras" value="Código de Barras" />
													</rich:column>
													<rich:column>
														<h:outputText id="hdEstatus" value="Estatus" />
													</rich:column>
													<rich:column id="hdCantidad" visible="false">
														<h:outputText value="Cantidad" />
													</rich:column>
													<rich:column id="hdEscaneados" visible="false">
														<h:outputText value="Escaneados" />
													</rich:column>
												</rich:columnGroup>
											</f:facet>

											<rich:columnGroup>
												<rich:column id="columnRegistro">
													<h:outputText id="outTextRegistro"
														value="#{cierre.registro}" />
												</rich:column>
												<rich:column id="columnIdOrden" visible="false">
													<h:outputText id="outTextIdOrden"
														value="#{cierre.id_orden}" />
												</rich:column>
												<rich:column id="columnNombre">
													<h:outputText id="outTextNombre" value="#{cierre.nombre}" />
												</rich:column>
												<rich:column id="columnClave" visible="false">
													<h:outputText id="outTextClave" value="#{cierre.clave}" />
												</rich:column>
												<rich:column id="columnIdItem" visible="false">
													<h:outputText id="outTextIdItem" value="#{cierre.id_item}" />
												</rich:column>
												<rich:column id="columnRutaSecuencia">
													<h:outputText id="outTextRutaSec"
														value="#{cierre.ruta}/#{cierre.secuencia}" />
												</rich:column>
												<rich:column id="columnTipoProducto">
													<h:outputText id="outTextDescripcion"
														value="#{cierre.descripcion}" />
												</rich:column>
												<rich:column id="columnCodigoBarras">
													<h:outputText id="outTextCodigoBarras"
														value="#{cierre.codigo_barras}" />
												</rich:column>
												<rich:column id="columnEstatus">
													<h:outputText id="outTextEstatus" value="#{cierre.estatus}" />
													<f:facet name="footer">
														<h:outputText value="#{msg.total}" id="label" />
													</f:facet>
												</rich:column>
												<rich:column id="columnCantidad" visible="false">
													<h:outputText id="outTextCantidad"
														value="#{cierre.cantidad}" />
													<f:facet name="footer">
														<h:inputText id="TxtCantCodigo"
															value="#{cierreZona.total_items}" disabled="true" />
													</f:facet>
												</rich:column>
												<rich:column id="columnEscaneados" visible="false">
													<h:outputText id="outTextEscaneados"
														value="#{cierre.escaneados}" />
													<f:facet name="footer">
														<h:inputText id="TxtCantEscaneado"
															value="#{cierreZona.total_escaneo}" disabled="true" />
													</f:facet>
												</rich:column>
											</rich:columnGroup>
										</rich:dataTable>
									</div>
								</h:panelGroup>
								<h:graphicImage id="ImgSemaforo"
									value="#{cierreZona.rutaImagen}"></h:graphicImage>
							</h:panelGrid>
					</a4j:form>
				</h:panelGrid>
			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>