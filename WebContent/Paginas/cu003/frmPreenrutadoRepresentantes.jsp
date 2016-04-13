<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:t="http://myfaces.apache.org/tomahawk"
	xmlns:rich="http://richfaces.org/rich"
	xmlns:a4j="http://richfaces.org/a4j">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>PRE ENRUTADO DE REPRESENTANTES CON PRIMERAS ÓRDENES</title>
</head>
<body>
	<script type="text/javascript"
		src="#{facesContext.externalContext.requestContextPath}/js/jsCU003.js"></script>
	<f:view>
		<ui:composition template="/Paginas/template.jsp">
			<ui:define name="content">

				<f:loadBundle
					basename="com.datacode.avon_ots_admin.cu003.messagesCU003"
					var="msg" />


				<h:form binding="#{preenrutado.form}" id="formCriteriosConsulta">

					<h:panelGrid>
						<h:outputLabel value="#{msg.tituloEnrutado}"
							styleClass="tituloPagina" id="LabelTituloPaginaPreenrutado"></h:outputLabel>
					</h:panelGrid>

					<t:fieldset legend="#{msg.PnlTituloCriteriosConsulta}">

						<h:panelGrid columns="2" id="pnlTituloCriteriosConsulta">

							<h:outputLabel value="#{msg.tipoEnrutado}" id="LblTipoEnrutado"
								styleClass="formLabel"></h:outputLabel>
							<h:selectOneMenu validatorMessage="required" required="true"
								value="#{preenrutado.tipoEnrutado}" id="CbxTipo"
								onchange="submit()"
								valueChangeListener="#{preenrutado.guardaDatoTipo}">
								<f:selectItems value="#{preenrutado.tiposEnrutado}" />
							</h:selectOneMenu>

							<h:outputLabel value="#{msg.zona}" id="LblZonas"
								styleClass="formLabel"></h:outputLabel>
							<h:selectOneMenu validatorMessage="required" required="true"
								value="#{preenrutado.idZona}" id="CbxZonas" onchange="submit()"
								valueChangeListener="#{preenrutado.getCampaniasPorZona}">
								<f:selectItems value="#{preenrutado.zonas}" />
							</h:selectOneMenu>

							<h:outputLabel value="#{msg.campania}" id="LblCampanias"
								styleClass="formLabel"></h:outputLabel>
							<h:selectOneMenu value="#{preenrutado.idCampania}"
								onchange="submit()"
								valueChangeListener="#{preenrutado.actualizarCampania}">
								<f:selectItems value="#{preenrutado.campanias}"
									id="CbxCampanias" />
							</h:selectOneMenu>

							<h:commandButton id="BtnConsultar"
								action="#{preenrutado.consultarRepresentantes}"
								value="Consultar" accesskey="c" immediate="true"
								disabled="#{not preenrutado.mapaAccion['CONSULTAR']}">
							</h:commandButton>

						</h:panelGrid>
					</t:fieldset>
				</h:form>
				<br />

				<h:form binding="#{preenrutado.tableForm}" id="formRepresentantes">
					<t:fieldset legend="#{msg.PnlTituloGrid}">
						<h:panelGrid columns="3">
							<h:panelGrid>

								<h:panelGroup>
									<h:outputLabel value="Ruta: " id="LblRuta1" styleClass="label"></h:outputLabel>
									<h:selectOneMenu id="CbxRutas" value="#{preenrutado.idRuta}">
										<f:selectItems value="#{preenrutado.rutas}" />
									</h:selectOneMenu>

									<br />
									<br />

									<h:commandButton id="BtnAsignar"
										onclick="return obtenerCheckBoxSeleccionados()"
										action="#{preenrutado.asignarRutaEnRepresentantes}"
										value="Asignar" accesskey="a" immediate="false"
										disabled="#{not preenrutado.mapaAccion['GUARDAR']}">
									</h:commandButton>
								</h:panelGroup>
								<h:panelGroup>
									<h:outputLabel value="#{preenrutado.mensaje}" id="LblMensaje"
										styleClass="formLabel"></h:outputLabel>
								</h:panelGroup>

								<rich:dataTable value="#{preenrutado.representantes}"
									binding="#{preenrutado.dataTable}" var="representante"
									rendered="#{preenrutado.showTable}" id="tblRepresentante">
									<f:facet name="header">
										<rich:columnGroup>
											<rich:column>
												<h:selectBooleanCheckbox
													valueChangeListener="#{preenrutado.seleccionarTodos}"
													onclick="submit()" id="checkSleccionarTodos" />
												<h:outputText value="#{msg.seleccion}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="#{msg.cuenta}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="#{msg.nombre}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="#{msg.direccion1}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="#{msg.direccion2}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="#{msg.direccion3}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="#{msg.ruta}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="#{msg.secuencia}"></h:outputText>
											</rich:column>
											<rich:column rendered="#{preenrutado.tipoEnrutado eq '2'}">
												<h:outputText value="#{msg.domicilioAlterno}"></h:outputText>
											</rich:column>
										</rich:columnGroup>
									</f:facet>

									<rich:column>
										<h:selectBooleanCheckbox
											value="#{preenrutado.checkBoxList[representante.idRepresentante]}"
											valueChangeListener="#{preenrutado.checkBoxSeleccionado}"
											id="sel" />
									</rich:column>

									<rich:column
										filterMethod="#{preenrutado.filterCuentaRepresentante }"
										filterEvent="onblur">
										<f:facet name="header">
											<h:inputText value="#{preenrutado.filterCuenta}"
												id="txtFilterCuenta">
												<a4j:support event="onkeyup" reRender="tblRepresentante"
													ignoreDupResponses="true" requestDelay="1000" />
											</h:inputText>
										</f:facet>
										<h:outputText value="#{representante.registro}" />
									</rich:column>
									<rich:column
										filterMethod="#{preenrutado.filterNombreRepresentante }"
										filterEvent="onblur">
										<f:facet name="header">
											<h:inputText value="#{preenrutado.filterNombre}"
												id="txtFilterNombre">
												<a4j:support event="onkeyup" reRender="tblRepresentante"
													ignoreDupResponses="true" requestDelay="1000" />
											</h:inputText>
										</f:facet>
										<h:outputText value="#{representante.nombre}" />
									</rich:column>
									<rich:column
										filterMethod="#{preenrutado.filterDomicilioRepresentante }"
										filterEvent="onblur">
										<f:facet name="header">
											<h:inputText value="#{preenrutado.filterDireccion}"
												id="txtFilterDireccion">
												<a4j:support event="onkeyup" reRender="tblRepresentante"
													ignoreDupResponses="true" requestDelay="1000" />
											</h:inputText>
										</f:facet>
										<h:outputText value="#{representante.domicilio}" />
									</rich:column>

									<rich:column>
										<h:outputText value="#{representante.estado}"></h:outputText>
									</rich:column>

									<rich:column>
										<h:outputText value="#{representante.poblacion}"></h:outputText>
									</rich:column>

									<rich:column>
										<h:outputText value="#{preenrutado.rutaRepresentante}"></h:outputText>
									</rich:column>

									<rich:column>
										<h:outputText value="#{preenrutado.secuenciaRepresentante}"></h:outputText>
									</rich:column>
									<rich:column rendered="#{preenrutado.tipoEnrutado eq '2'}">
										<h:outputText value="#{representante.domicilioAlterno}"></h:outputText>
									</rich:column>
								</rich:dataTable>
							</h:panelGrid>

						</h:panelGrid>
					</t:fieldset>
				</h:form>

			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>