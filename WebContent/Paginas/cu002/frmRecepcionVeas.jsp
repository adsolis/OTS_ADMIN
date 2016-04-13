<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:t="http://myfaces.apache.org/tomahawk"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type="text/javascript"
	src="#{facesContext.externalContext.requestContextPath}/js/jsCU002.js"></script>
<title>Recepción de Ventas Anticipadas</title>
</head>
<body>
	<f:view>
		<ui:composition template="/Paginas/template.jsp">
			<ui:define name="content">

				<!-- Incluye archivo de properties -->
				<f:loadBundle
					basename="com.datacode.avon_ots_admin.cu002.messagesCU002"
					var="msg" />
				<!-- Diseno de pagina -->
				<h:form binding="#{recepcion.form}" styleClass="principal" id="formCriteriosVeas">

					<h:panelGrid>
						<h:outputLabel value="#{msg.titulo}" styleClass="tituloPagina"
							id="veasLabelTituloPagina"></h:outputLabel>
					</h:panelGrid>

					<br />

					<t:fieldset>
						<h:panelGrid columns="6" cellspacing="5">
						
						<h:outputLabel value="#{msg.zona}" id="veasLabelZona"
								styleClass="formLabel"></h:outputLabel>
							<h:selectOneMenu validatorMessage="required"
								value="#{recepcion.idZona}" id="veasSelectZonas">
								<f:selectItems value="#{recepcion.cmbZonas}" />
								<a4j:support event="onchange" action="#{recepcion.getNombreGerente}" 
								reRender="veasTxtGerente,veasTxtEntrego,labelErrorVeas,veasSelectCampanias,veasSelectOneMenu"/>
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.campania}" id="veasLabelCampania"
								styleClass="formLabel"></h:outputLabel>
							<h:selectOneMenu validatorMessage="required" required="true" id="veasSelectOneMenu"
								value="#{recepcion.idCampania}">
								<f:selectItems value="#{recepcion.campanias}"
									id="veasSelectCampanias" />
									<a4j:support event="onchange" action="#{recepcion.limpiarMensaje}" reRender="labelErrorVeas"  />
							</h:selectOneMenu>

							<h:outputLabel value="#{msg.gerenteZona}" styleClass="formLabel"
								id="veasLabelGerenteZona"></h:outputLabel>
							<h:inputText id="veasTxtGerente" value="#{recepcion.gerenteZona}"
								readonly="true" size="35"></h:inputText>

							<h:outputLabel value="#{msg.entrego}" styleClass="formLabel" id="labelEntregoVeas"></h:outputLabel>
							<h:inputText id="veasTxtEntrego" value="#{recepcion.gerenteZona}"
								readonly="true" size="35"></h:inputText>

							<h:outputLabel value="#{msg.entrega}" styleClass="formLabel" id="labelEntregaVeas"></h:outputLabel>
							<h:inputText id="veasTxtFechaEntrega"
								value="#{recepcion.fEntrega}" readonly="true"></h:inputText>

							<h:outputLabel value="#{msg.recibio}" styleClass="formLabel" id="labelRecibioVeas"></h:outputLabel>
							<h:inputText id="veasTxtRecibio" value="#{recepcion.uRecibio}"
								readonly="true"></h:inputText>
						</h:panelGrid>
					</t:fieldset>
				</h:form>
				<br />

				<t:fieldset legend="#{msg.pnlTituloInfoCap}">
					<h:form binding="#{recepcion.tableForm}" id="veasFormDetalles">
						<h:panelGrid columns="2">
							<h:panelGrid columns="4">

								<h:outputLabel value="#{msg.desc}" styleClass="formLabel" id="labelDescripcionVeas"></h:outputLabel>
								<h:inputText id="veasTxtDescripcion" label="Descripción"
									value="#{recepcion.descDetalle}">
									<f:validateLength maximum="100" />
								</h:inputText>

								<h:outputLabel value="#{msg.cajas}" styleClass="formLabel" id="labelNoCajasVeas"></h:outputLabel>
								<h:inputText value="#{recepcion.noCajas}" id="veasTxtNoCajas"></h:inputText>
								<h:column>&nbsp;</h:column>
								<h:column>&nbsp;</h:column>

								<!--<h:panelGrid>									
									<h:outputLabel value="#{recepcion.msg}" style="color:#FF0000;"></h:outputLabel>
									<h:message for="veasTxtDesc" errorClass="Error"
										infoClass="Info" style="color:red"></h:message>
									<h:message for="veasTxtNoCajas" errorClass="Error"
										infoClass="Info" style="color:red"></h:message>
								</h:panelGrid> -->
							</h:panelGrid>

							<h:panelGroup>
								<h:commandButton id="veasBtnInsertar"
									onclick="return validarCamposVacios()"
									action="#{recepcion.insertarDetalleRecepcionVEAS}"
									value="Insertar" accesskey="s"
									disabled="#{not recepcion.mapaAccion['NUEVO']}">
								</h:commandButton>
								<h:commandButton id="veasBtnNuevo"
									action="#{recepcion.nuevoDetalleRecepcionVEAS}" value="Nuevo"
									accesskey="n">
								</h:commandButton>
							</h:panelGroup>
						</h:panelGrid>

						<h:panelGrid>
							<rich:dataTable value="#{recepcion.detalles}" var="detalle"
								id="tableRecepciones" rendered="#{recepcion.showTable}">
								<f:facet name="header">
									<rich:columnGroup>
										<rich:column>
											<h:outputText value="Descripción"
												id="veasLabelDetalleDescripcion">
											</h:outputText>
										</rich:column>

										<rich:column>
											<h:outputText value="Cajas" id="veasLabelDetalleNoCajas"></h:outputText>
										</rich:column>
										<rich:column>
											<h:outputText value="BC de caja generado"
												id="veasLabelDetalleCodigoBarras"></h:outputText>
										</rich:column>
										<rich:column>
											<h:outputText id="labelAccionesVeas" value="Acciones"></h:outputText>
										</rich:column>
									</rich:columnGroup>
								</f:facet>

								<rich:column>
									<h:outputText value="#{detalle.descDetalle}"
										id="veasTxtDetalleDescripcion"></h:outputText>
								</rich:column>

								<rich:column>
									<h:outputText value="#{detalle.noCajas}"
										id="veasTxtDetalleNoCajas"></h:outputText>
								</rich:column>

								<rich:column>
									<h:outputText id="veasTxtDetalleCodigoBarras"
										value="#{detalle.codigoBarras}"></h:outputText>
								</rich:column>

								<rich:column>
									<h:panelGrid columns="1">
										<h:commandButton id="veasBtnEliminarDetalle"
											disabled="#{not recepcion.mapaAccion['ELIMINAR']}"
											action="#{recepcion.eliminarDetalleRecepcion}"
											value="Eliminar" accesskey="e"
											onclick="return confirmaEliminar()">
											<f:setPropertyActionListener value="#{detalle}"
												target="#{recepcion.detalle}" />
										</h:commandButton>
									</h:panelGrid>
								</rich:column>
							</rich:dataTable>
						</h:panelGrid>

						<h:panelGrid columns="2">
							<h:outputLabel value="#{msg.total}" id="labelTotalCajas" styleClass="formLabel"></h:outputLabel>
							<h:inputText id="veasTxtTotalCajas" value="#{recepcion.totalNoCajas}" readonly="true"></h:inputText>

						</h:panelGrid>

						<h:panelGrid>
							<h:outputLabel value="#{recepcion.msg}" id="labelErrorVeas"
								styleClass="error"></h:outputLabel>
						</h:panelGrid>
					</h:form>

					<h:form id="veasFormBotones">
						<h:panelGrid columns="2">
							<h:commandButton id="veasBtnGuardarRecepciones"
								disabled="#{not recepcion.mapaAccion['GUARDAR']}"
								onclick="return confirmarGuardadoVEAS()"
								action="#{recepcion.guardarRecepcionesVEAS}"
								binding="#{recepcion.btnGuardar }"
								value="Guardar Recepciones VEAS" accesskey="S">
							</h:commandButton>
							<h:panelGroup>
								<h:commandLink target="_blank" 
									action="#{recepcion.generarCodigosBarras}"
									value="#{msg.btnGenerarCodigosBarras}"
									id="lnkGenerarCodigosBarrasVEAS" styleClass="botonPrincipal"
									binding="#{recepcion.lnkGenerarCodigosBarras}" rendered="false"
									disabled="#{not recepcion.mapaAccion['GENERAR CÓDIGOS']}">
									<f:setPropertyActionListener
										target="#{recepcion.idGeneracionCodigos}"
										value="#{recepcion.idGeneracionCodigos}" />
								</h:commandLink>
								<h:inputHidden id="activarGeneracion"
									value="#{recepcion.activarGeneracion}"></h:inputHidden>
							</h:panelGroup>
						</h:panelGrid>
						<script type="text/javascript">
							//Después de 3 segundos se ejecuta la función para dar click a la liga para generar los códigos de barras
							setTimeout("ejecutarGeneracionCodigosVEAS()", 2000);
						</script>
					</h:form>
				</t:fieldset>
			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>