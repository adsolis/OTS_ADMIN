<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
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
				<h:form>


					<table width="100%">
						<tr>
							<td align="center"><h:outputLabel
									value="Actualización de catálogos" styleClass="tituloPagina"
									id="LabelTituloPaginaActualizacionCatálogos"></h:outputLabel></td>
						</tr>
					</table>



					<h:outputLabel value="#{actualizarCatalogos.mensaje}"
						id="LblMensajeActCatalogos" styleClass="formLabel">
					</h:outputLabel>

					<br />
					<br />

					<h:commandButton id="BtnActualizarCatalogos"
						value="Actualizar Catalogos de OTS" immediate="true"
						action="#{actualizarCatalogos.actualizarCatalogos2}"
						onclick="return confirmarActualizacion()">
					</h:commandButton>
					<h:commandButton id="consultarEstatus"
						action="#{actualizarCatalogos.consultarEstatusActualizacion2}"
						value="Consultar Estatus"></h:commandButton>
					<br />
					<br />

					<rich:panel id="pnlc1">

						<table width="100%" border="0">
							<tr>
								<td align="right" width="47%" valign="top"><rich:dataTable
										value="#{actualizarCatalogos.regsLDC}" var="catalogo"
										rendered="#{actualizarCatalogos.mostrarTabla}"
										id="tablaCatalogosLDC">
										<f:facet name="header">
											<rich:columnGroup>
												<rich:column colspan="3">
													<h:outputText value="Replicación LDC -> EOTS" />
												</rich:column>
												<rich:column breakBefore="true">
													<h:outputText value="Descripción"></h:outputText>
												</rich:column>
												<rich:column>
													<h:outputText value="Estatus"></h:outputText>
												</rich:column>
												<rich:column>
													<h:outputText value="Avance"></h:outputText>
												</rich:column>
											</rich:columnGroup>
										</f:facet>

										<rich:column>
											<h:outputText value="#{catalogo.descripcion}"></h:outputText>
										</rich:column>

										<rich:column>
											<h:outputText value="#{catalogo.estatus}"></h:outputText>
										</rich:column>

										<rich:column>
											<h:outputText value="#{catalogo.avance}"></h:outputText>
										</rich:column>

									</rich:dataTable></td><td width="6%"></td>
								<td align="left" width="47%" valign="top"><rich:dataTable
										value="#{actualizarCatalogos.regsEOTS}" var="catalogo"
										rendered="#{actualizarCatalogos.mostrarTabla}"
										id="tablaCatalogosEOTS">
										<f:facet name="header">
											<rich:columnGroup>
											<rich:column colspan="3">
													<h:outputText value="Replicación EOTS -> LDC" />
												</rich:column>
												<rich:column breakBefore="true">
													<h:outputText value="Descripción"></h:outputText>
												</rich:column>
												<rich:column>
													<h:outputText value="Estatus"></h:outputText>
												</rich:column>
												<rich:column>
													<h:outputText value="Avance"></h:outputText>
												</rich:column>
											</rich:columnGroup>
										</f:facet>

										<rich:column>
											<h:outputText value="#{catalogo.descripcion}"></h:outputText>
										</rich:column>

										<rich:column>
											<h:outputText value="#{catalogo.estatus}"></h:outputText>
										</rich:column>

										<rich:column>
											<h:outputText value="#{catalogo.avance}"></h:outputText>
										</rich:column>

									</rich:dataTable></td>
							</tr>
						</table>
					</rich:panel>
					<br></br>




				</h:form>
			</ui:define>
		</ui:composition>
	</f:view>
</body>
</html>