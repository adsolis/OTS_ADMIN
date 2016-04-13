<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" 
xmlns:h="http://java.sun.com/jsf/html" xmlns:ui="http://java.sun.com/jsf/facelets"
xmlns:rich="http://richfaces.org/rich">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="#{facesContext.externalContext.requestContextPath}/css/mainStyleOTS.css" />
<title>Inicio de sesión - Avon OTS Admin</title>
</head>
<body>
<f:view>
	<f:loadBundle basename="com.datacode.avon_ots_admin.utils.messages" var="msg" />
	<h:form id="frmLogin">
		<table style="margin-left: auto; margin-right: auto;"> <tr> <td align="center" width="80%" style="padding-left: 20px; padding-right: 20px;">
				<h:graphicImage url="/img/headerAvon.gif" ismap="false"></h:graphicImage>
			</td></tr>
		</table>
		<!-- <h:panelGrid columns="1" style="height: 500px; width: 100%"> -->
		<h:panelGrid columns="1" styleClass="inicioSesion">
				<div style="background: #000000; color: #FFFFFF">Iniciar Sesión</div>
				<h:panelGrid columns="3" styleClass="forma">
					<h:outputLabel value="#{msg.usuario}"></h:outputLabel>
					<h:inputText id="txtUsuario" value="#{login.name}" required="true" requiredMessage="Usuario requerido">
					</h:inputText>
					<h:message for="txtUsuario" errorClass="error"></h:message>
					
					<h:outputLabel value="#{msg.contraseña}"></h:outputLabel>
					<h:inputSecret id="txtContraseña" value="#{login.password}" required="true" requiredMessage="Contraseña requerida">
					</h:inputSecret>
					<h:message for="txtContraseña" errorClass="error"></h:message>
					
					<br />
					<h:commandButton action="#{login.login}" value="#{msg.entrar}"></h:commandButton>
				</h:panelGrid>
		</h:panelGrid>
		<h:outputLabel styleClass="error" value="#{login.mensajeError}"></h:outputLabel>
		<table style="margin-left: auto; margin-right: auto;"> 
			<tr> 
				<td align="center" width="80%">
					<h:outputLabel value="Versión: " style="font-family: Arial; font-size: 8pt; font-weight: bold;"></h:outputLabel>
					<h:outputLabel value="2.5.0.10" style="font-family: Arial; font-size: 8pt; font-weight: bold;"></h:outputLabel>
					<br />
					</td>
			</tr>
		</table>
		<!-- </h:panelGrid> -->
	</h:form>
</f:view>

<script type="text/javascript">
	txtUsuario = document.getElementById("frmLogin:txtUsuario");
	txtUsuario.focus();
</script>
</body>
</html>