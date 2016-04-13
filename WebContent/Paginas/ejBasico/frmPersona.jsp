<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Catalogo de Rutas</title>
	<!-- link href="<%=request.getContextPath()%>/css/mainStyleOTS.css" rel="stylesheet" type="text/css" /-->
	<link href="<%=request.getContextPath()%>/../../css/mainStyleOTS.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../../js/jsCatalogos.js"></script>
	<script type="text/javascript">
		function eliminarRuta(){
			alert("eliminando");
			//document.forms.action = "";
		}
	</script>
</head>
<body>
<f:view>
	<f:loadBundle basename="com.datacode.avon_ots_admin.cu001.messages" var="msg" />
	
	<h:form binding="#{person.form}" styleClass="todo" >
		<h:panelGrid columns="2">
			<h:outputLabel value="#{msg.cveRuta}"></h:outputLabel>
			<h:inputText id="txtClave" label="Clave de Ruta" value="#{person.cveRuta}"
				 requiredMessage="Clave de Ruta es obligatorio" required="true">
				<f:validateLength maximum="50"/>
			</h:inputText>
			
			<h:outputLabel value="#{msg.descRuta}"></h:outputLabel>
			<h:inputText value="#{person.descRuta}" id="txtDesc" required="true" requiredMessage="Descripción de Ruta es obligatorio.">	
			</h:inputText>
			
			<h:outputLabel value="#{msg.zona}"></h:outputLabel>
			<h:selectOneMenu validatorMessage="required" 
				value="#{person.idZona}">
				<f:selectItems value="#{person.zonas}" />
			</h:selectOneMenu>
			
			<h:outputLabel value="#{msg.pais}"></h:outputLabel>
			<h:selectOneMenu validatorMessage="required"
				value="#{person.idPais}">
				<f:selectItems value="#{person.paises}" />
			</h:selectOneMenu>
			
			<h:outputLabel value="#{msg.tipoRuta}"></h:outputLabel>
			<h:selectOneMenu validatorMessage="required"
				value="#{person.idTipoRuta}">
				<f:selectItems value="#{person.tipoRuta}" />
			</h:selectOneMenu>
			
			<h:outputLabel value="#{msg.ldc}"></h:outputLabel>
			<h:selectOneMenu validatorMessage="LDC es obligatorio." required="true" id="cmbLDC"
				value="#{person.idLdc}">
				<f:selectItems value="#{person.LDC}" />
			</h:selectOneMenu>
		</h:panelGrid>
		<!-- Panel que contiene mensajes de validación se ejecuta antes del Action -->
		<h:panelGrid>
			<h:message for="txtClave" errorClass="Error" infoClass="Info" style="color:red"></h:message>
			<h:message for="txtDesc" errorClass="Error" infoClass="Info" style="color:red"></h:message>
			<h:message for="cmbLDC" errorClass="Error" infoClass="Info" style="color:red"></h:message>
		</h:panelGrid>
		<h:panelGroup>
			<h:commandButton action="#{person.guardarRuta}" value="Insertar"
				accesskey="s">
			</h:commandButton>
			<h:commandButton action="#{person.cancelarRuta}" value="Cancelar"
				accesskey="c" immediate="true">
			</h:commandButton>
			<h:commandButton action="#{person.nuevaRuta}" value="Nuevo"
					accesskey="n">
			</h:commandButton>
			<h:commandButton action="#{person.actualizarRuta}" value="Actualizar"
					accesskey="u">
			</h:commandButton>
			<h:outputLabel value="#{person.msg}" style="color:#FF0000;"></h:outputLabel>
		</h:panelGroup>
		
		<h:panelGrid width="100%">
		<h:form binding="#{person.tableForm}" >
			<h:dataTable value="#{person.rutas}" var="ruta"
				styleClass="todo" headerClass="todoheader" columnClasses="first, rest" border="1" style="align:center;">
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputText value="Clave de Ruta"></h:outputText>
						</h:column>
					</f:facet>
					<h:outputText value="#{ruta.cveRuta}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputText value="Descripción"></h:outputText>
						</h:column>
					</f:facet>
					<h:outputText value="#{ruta.descRuta}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputText value="Zona"></h:outputText>
						</h:column>
					</f:facet>
					<h:outputText value="#{ruta.descZona}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputText value="País"></h:outputText>
						</h:column>
					</f:facet>
					<h:outputText value="#{ruta.descPais}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputText value="Tipo de Ruta"></h:outputText>
						</h:column>
					</f:facet>
					<h:outputText value="#{ruta.descTipoRuta}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputText value="LDC"></h:outputText>
						</h:column>
					</f:facet>
					<h:outputText value="#{ruta.descLdc}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:column>
							<h:outputText value="Acciones"></h:outputText>
						</h:column>
					</f:facet>
					<h:panelGrid columns="2">
						<h:commandButton action="#{person.modificarRuta}" value="Modificar" accesskey="m">
							<f:setPropertyActionListener  value="#{ruta.idRuta}" target="#{person.idRuta }"/>
							<f:setPropertyActionListener  value="#{ruta.cveRuta}" target="#{person.cveRuta }"/>
							<f:setPropertyActionListener  value="#{ruta.descRuta}" target="#{person.descRuta }"/>
							<f:setPropertyActionListener  value="#{ruta.idPais}" target="#{person.idPais }"/>
							<f:setPropertyActionListener  value="#{ruta.idZona}" target="#{person.idZona }"/>
							<f:setPropertyActionListener  value="#{ruta.idTipoRuta}" target="#{person.idTipoRuta }"/>
							<f:setPropertyActionListener  value="#{ruta.idLdc}" target="#{person.idLdc }"/>
						</h:commandButton>
						<h:commandButton action="#{person.eliminarRuta}" value="Eliminar" accesskey="e" onclick="return confirmaEliminar()">
							<f:setPropertyActionListener  value="#{ruta.idRuta}" target="#{person.idRuta }"/>
						</h:commandButton>
					</h:panelGrid>
				</h:column>
			</h:dataTable>
		</h:form>
		</h:panelGrid>
	</h:form>
</f:view>
</body>
</html>