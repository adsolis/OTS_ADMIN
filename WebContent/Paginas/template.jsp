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
		<div class="header">
			<ui:insert name="header">
				<ui:include src="/Paginas/head.jsp" />
			</ui:insert>
		</div>

		<div>
			<h:panelGrid styleClass="principal" columns="1">
				<h:panelGroup>
					<ui:insert name="content">

					</ui:insert>
				</h:panelGroup>
			</h:panelGrid>
		</div>
	</div>
</body>
<rich:modalPanel id="mp" minHeight="50" minWidth="50" height="150"
	width="150" zindex="500">
	<f:facet name="header">
		<h:outputText value="Cargando Datos..." />
	</f:facet>
	<table align="center"><tr style="height: 140px;"><td style="vertical-align: middle;">
	<h:graphicImage value="/img/loading.gif" />
	</td></tr></table>
</rich:modalPanel>
<a4j:form id="formmodal">
	<a4j:status id="stado" onstart="Richfaces.showModalPanel('mp')"
		onstop="Richfaces.hideModalPanel('mp')">
	</a4j:status>
</a4j:form>
</html>