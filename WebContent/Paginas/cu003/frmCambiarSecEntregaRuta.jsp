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
<title>CAMBIAR PREENRUTADO DE ENTREGA EN RUTA</title>
</head>
<body>
	<f:view>
		<ui:composition template="/Paginas/template.jsp">
			<ui:define name="content">

				<f:loadBundle
					basename="com.datacode.avon_ots_admin.cu003.messagesCU003"
					var="msg" />

				<h:form binding="#{secuenciaEntregaEnRuta.form}"
					id="formCriteriosConsultaCambiarSecuencia">
					
					<h:outputLabel value="#{msg.tituloCambiarSecuencia}" styleClass="tituloPagina"
						id="LblTituloPantalla"></h:outputLabel>

					<t:fieldset legend="#{msg.PnlTituloCriteriosConsulta}">
						<h:panelGrid columns="4" id="pnlTituloCriteriosConsultaCambiarSecuencia">
							<h:outputLabel value="#{msg.zona}" id="LblZonasCambiarSecuencia"
								styleClass="formLabel"></h:outputLabel>
							<h:selectOneMenu value="#{secuenciaEntregaEnRuta.idZona}"
								id="CbxZonasCambiarSecuencia" onchange="submit()"
								valueChangeListener="#{secuenciaEntregaEnRuta.getRutasPorZona}">
								<f:selectItems value="#{secuenciaEntregaEnRuta.listZonas}" />
							</h:selectOneMenu>

							<h:outputLabel value="#{msg.ruta}" id="LblRutasCambiarSecuencia"
								styleClass="formLabel"></h:outputLabel>
							<h:selectOneMenu id="CbxRutasCambiarSecuencia"
								value="#{secuenciaEntregaEnRuta.idRuta}" onchange="submit()"
								valueChangeListener="#{secuenciaEntregaEnRuta.getDescripcionTipoRuta}"
								binding="#{secuenciaEntregaEnRuta.selectRutas}">
								<f:selectItems value="#{secuenciaEntregaEnRuta.listRutas}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="#{msg.campania}"
								id="LblCampaniasCambiarSecuencia" styleClass="formLabel"></h:outputLabel>
							<h:selectOneMenu validatorMessage="required" required="true" id="cbxCampanias"
								value="#{secuenciaEntregaEnRuta.idCampania}" onchange="submit()"
								valueChangeListener="#{secuenciaEntregaEnRuta.actualizarCampania}"
								binding="#{secuenciaEntregaEnRuta.selectCampanias}">
								<f:selectItems value="#{secuenciaEntregaEnRuta.listCampanias}" />
							</h:selectOneMenu>
							
							<h:outputLabel value="Account" id="lblAccountRep" styleClass="formLabel"></h:outputLabel>
							<h:inputText
								value="#{secuenciaEntregaEnRuta.accountFiltro}"
								readonly="false" id="txtAccountFiltro"
								binding="#{secuenciaEntregaEnRuta.txtAccountFiltro}"></h:inputText>

							<h:inputHidden id="hidden1" value="hidden1">
							</h:inputHidden>

							<h:inputHidden id="hidden2" value="hidden2">
							</h:inputHidden>

							<h:commandButton id="BtnConsultarCambiarSecuencia"
								disabled="#{not secuenciaEntregaEnRuta.mapaAccion['CONSULTAR']}"
								action="#{secuenciaEntregaEnRuta.consultarRepresentantes}"
								value="Consultar" accesskey="c" >
							</h:commandButton>

						</h:panelGrid>
					</t:fieldset>
				</h:form>

				<h:form binding="#{secuenciaEntregaEnRuta.formEdicion}"
					rendered="false" id="formEdicion">
					<t:fieldset legend="#{msg.panelEdicion}">
						<h:panelGrid columns="4" id="grd_Representantes">

							<h:outputText value="#{msg.cuenta}" styleClass="formLabel"></h:outputText>
							<h:inputText
								value="#{secuenciaEntregaEnRuta.representante.registro}"
								readonly="true" styleClass="inputText"></h:inputText>

							<h:outputText value="#{msg.ruta}" styleClass="formLabel"></h:outputText>
							<h:inputText
								value="#{secuenciaEntregaEnRuta.representante.representantePorRuta.descRuta}"
								readonly="true" styleClass="inputText"></h:inputText>

							<h:outputText value="#{msg.nombre}" styleClass="formLabel"></h:outputText>
							<h:inputText
								value="#{secuenciaEntregaEnRuta.representante.nombre}"
								readonly="true" size="70" styleClass="inputText"></h:inputText>

							<h:outputText value="#{msg.secuenciaAnterior}"
								styleClass="formLabel"></h:outputText>
							<h:inputText
								value="#{secuenciaEntregaEnRuta.representante.representantePorRuta.seqEntregaAnterior}"
								readonly="true" id="inputSecuenciaAnterior"
								styleClass="inputText"></h:inputText>

							<h:outputText value="#{msg.direccion}" styleClass="formLabel"></h:outputText>
							<h:inputText
								value="#{secuenciaEntregaEnRuta.representante.domicilio}"
								readonly="true" size="70" styleClass="inputText"></h:inputText>

							<h:outputText value="#{msg.secuenciarReciente}"
								styleClass="formLabel"></h:outputText>
							<h:inputText
								value="#{secuenciaEntregaEnRuta.representante.representantePorRuta.seqEntregaReciente}"
								readonly="false" id="inputNuevaSecuencia"></h:inputText>

						</h:panelGrid>

						<h:panelGrid columns="2">
							<h:commandButton id="btnGuardarCambiarSecuencia"
								action="#{secuenciaEntregaEnRuta.guardarNuevaSecuencia}"
								onclick="return validarNumeroSecuencia()"
								actionListener="#{secuenciaEntregaEnRuta.displayTable}"
								value="Guardar" accesskey="s">
							</h:commandButton>

							<h:commandButton id="btnCancelarCambiarSecuencia"
								action="#{secuenciaEntregaEnRuta.hideTable}" value="Cancelar"
								accesskey="c">
							</h:commandButton>

						</h:panelGrid>
					</t:fieldset>
				</h:form>

				<t:fieldset legend="#{msg.representantes}">
					<h:form binding="#{secuenciaEntregaEnRuta.tableForm}"
						id="formRepresentantesCambiarSecuencia" rendered="false">

						<h:panelGrid columns="1">
							<h:panelGrid>

								<rich:dataTable value="#{secuenciaEntregaEnRuta.representantes}"
									binding="#{secuenciaEntregaEnRuta.dataTable}"
									var="representante" styleClass="codigosBarras" border="1">
									<f:facet name="header">
										<rich:columnGroup>
											<rich:column>
												<h:outputText value="#{msg.cuenta}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="#{msg.nombre}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="#{msg.direccion}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="#{msg.ruta}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="#{msg.secuenciaAnterior}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="#{msg.secuenciarReciente}"></h:outputText>
											</rich:column>
											<rich:column>
												<h:outputText value="Acciones"></h:outputText>
											</rich:column>
										</rich:columnGroup>
									</f:facet>

									<rich:column>
										<h:outputText value="#{representante.registro}"></h:outputText>
									</rich:column>

									<rich:column>
										<h:outputText value="#{representante.nombre}"></h:outputText>
									</rich:column>

									<rich:column>
										<h:outputText value="#{representante.domicilio}"></h:outputText>
									</rich:column>

									<rich:column>
										<h:outputText
											value="#{representante.representantePorRuta.descRuta}"></h:outputText>
									</rich:column>

									<rich:column>
										<h:outputText
											value="#{representante.representantePorRuta.seqEntregaAnterior}">
										</h:outputText>
									</rich:column>

									<rich:column>
										<h:outputText
											value="#{representante.representantePorRuta.seqEntregaReciente}"></h:outputText>
									</rich:column>

									<rich:column>
										<h:commandButton id="btnModificarSecuencia"
											disabled="#{not secuenciaEntregaEnRuta.mapaAccion['MODIFICAR']}"
											action="#{secuenciaEntregaEnRuta.modificarSecuenciaDeRepresentante}"
											actionListener="#{secuenciaEntregaEnRuta.displayTable}"
											value="Modificar" accesskey="m">
											<f:setPropertyActionListener value="#{representante}"
												target="#{secuenciaEntregaEnRuta.representante}" />
										</h:commandButton>
									</rich:column>
								</rich:dataTable>

								<h:inputHidden id="numeroSecuenciaMaxima"
									value="#{secuenciaEntregaEnRuta.noSecuenciaMaxima}">
								</h:inputHidden>

								<h:panelGrid columns="2">
									<h:commandButton id="btnAplicarCambiosSecuencia"
										disabled="#{not secuenciaEntregaEnRuta.mapaAccion['GUARDAR']}"
										onclick="return confirmarRegistrosCambiarSecuencia()"
										action="#{secuenciaEntregaEnRuta.aplicarCambiosEnSecuencia}"
										value="Aplicar cambios en secuencia" accesskey="a">
									</h:commandButton>

									<h:commandButton id="btnOrdenarNuevaSecuencia"
										action="#{secuenciaEntregaEnRuta.ordenarNuevaSecuencia}"
										value="Reordenar secuencia" accesskey="r">
									</h:commandButton>
								</h:panelGrid>

							</h:panelGrid>
						</h:panelGrid>

					</h:form>
					<h:panelGroup>

						<h:outputLabel value="#{secuenciaEntregaEnRuta.mensaje}"
							id="LblMensajeCambiarSecuencia" styleClass="formLabel"></h:outputLabel>
						<h:message for="CbxRutas" errorClass="Error" infoClass="Info"
							style="color:red"></h:message>
					</h:panelGroup>
				</t:fieldset>

			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>