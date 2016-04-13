<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here
</title>
<LINK href="<%=request.getContextPath()%>/css/mainStyleOTS.css"
	rel="stylesheet" type="text/css">
</head>
<body>
<f:view>
	<f:loadBundle basename="com.datacode.avon_ots_admin.cu001.messages" var="msg" />
	<h:form styleClass="todo">
		<h:outputLabel value="#{msg.escribeNombre}"></h:outputLabel>
		<h:inputText value="#{pruebaWS.name}"></h:inputText>
		
		<h:outputLabel value="#{pruebaWS.greeting}"></h:outputLabel>
		<h:commandButton action="#{pruebaWS.Saludar}"></h:commandButton>
		
	</h:form>
	
	<h:form>
		<h:panelGrid styleClass="principal" columns="2">
			<h:graphicImage height="200" width="300" value="http://localhost:8080/rbarcode/BarcodeServlet?BARCODE=123456789012&WIDTH=190&HEIGHT=110&CHECK_CHAR=Y&CODE_TYPE=CODE128&FORMAT=png"></h:graphicImage>
			<h:graphicImage height="200" width="300" value="http://localhost:8080/rbarcode/BarcodeServlet?BARCODE=123456789012&WIDTH=190&HEIGHT=110&CHECK_CHAR=Y&CODE_TYPE=CODE128&FORMAT=png"></h:graphicImage>
		</h:panelGrid>
	</h:form>
</f:view>
</body>
</html>