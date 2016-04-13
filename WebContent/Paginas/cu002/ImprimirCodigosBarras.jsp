<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

<head>
<title>Order Tracking System</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="icon" type="image/png" href="#{facesContext.externalContext.requestContextPath}/img/favicon.jpg" />
<link rel="stylesheet" type="text/css"
	href="#{facesContext.externalContext.requestContextPath}/css/mainStyleOTS.css" />
<script type="text/javascript"
	src="#{facesContext.externalContext.requestContextPath}/js/jsCatalogos.js"></script>
<script type="text/javascript"
	src="#{facesContext.externalContext.requestContextPath}/js/jsCU001.js"></script>
<script type="text/javascript"
	src="#{facesContext.externalContext.requestContextPath}/js/jsCU003.js"></script>
<script type="text/javascript"
	src="#{facesContext.externalContext.requestContextPath}/js/jsCU002.js"></script>
<script type="text/javascript"
	src="#{facesContext.externalContext.requestContextPath}/js/tcal.js"></script>
<script type="text/javascript"
	src="#{facesContext.externalContext.requestContextPath}/js/jsUtil.js"></script>
<link rel="stylesheet" type="text/css"
	href="#{facesContext.externalContext.requestContextPath}/css/tcal.css" />
</head>


<body style="background-color: #EBEAEE">
	<div class="page">

		<div>
			<h:panelGrid styleClass="principal" columns="1">
				<h:panelGroup>
					<ui:insert name="content">
					
						<f:loadBundle basename="com.datacode.avon_ots_admin.cu002.messagesCU002" var="msg" />
						<!-- <h:form>-->
							<h:panelGrid styleClass="codigosBarras" columns="1">
								<h:panelGrid columns="1" width="100%">
									<h:outputLabel value="#{msg.codigosBarras}" styleClass="tituloPagina"></h:outputLabel>
									<h:outputLabel value="Si algún código de barras no está correctamente generado presione 'F5'"></h:outputLabel>
								</h:panelGrid>
								<h:outputLabel value="#{controllerCU002ImprimirCodigosBarras.mensajeError}" styleClass="error"></h:outputLabel>
								<h:panelGrid columns="2" border="1" width="100%" style="text-align:center" columnClasses="celdaCodigoBarras,celdaCodigoBarras" binding="#{controllerCU002ImprimirCodigosBarras.panelImagenes}">
									<br /><!-- Estos espacios son necesarios, el grid debe tener algo fijo para mostrar los controles dinámicos-->
									<br /><!-- Se ponen dos espacios, uno por cada columna del grid -->
									<f:facet name="footer">
										<h:panelGroup>
											 <input type="button" value="Imprimir" onclick="imprimir()" />
										  <!--<h:commandButton id="imprimir" value="#{msg.botonImprimir}" onmousedown="window.print()" />-->
										</h:panelGroup>
									</f:facet>
								</h:panelGrid>
							</h:panelGrid>
						<!-- </h:form>-->

					</ui:insert>
				</h:panelGroup>
			</h:panelGrid>
		</div>
	</div>
</body>
</html>