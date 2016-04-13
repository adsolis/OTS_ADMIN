<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:rich="http://richfaces.org/rich"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:s="http://jboss.com/products/seam/taglib">

<body>

	<ui:composition>
		<h:form>
			<table class="principal">
				<tr>
					<td align="left" width="80%"
						style="padding-left: 20px; padding-right: 20px;"><a
						href="#{facesContext.externalContext.requestContextPath}/faces/Paginas/template.jsp"
						title="Inicio"> <img
							src="#{facesContext.externalContext.requestContextPath}/img/headerAvon.gif"
							alt="Inicio" height="80px" />
					</a></td>
					<td align="left" width="10%" valign="bottom"><h:outputLabel
							value="Usuario: "
							style="font-family: Arial; font-size: 8pt; font-weight: bold;"></h:outputLabel>
						<h:outputLabel value="#{login.name}"
							style="font-family: Arial; font-size: 8pt; font-weight: bold;"></h:outputLabel>
					</td>
				</tr>
			</table>
			<a4j:outputPanel ajaxRendered="true" rendered="true"
				id="a4jPanelSeccion1">
				<table width="80%" align="center">
					<tr>
						<td><rich:toolBar>
								<table>
									<tr>

										<td><rich:dropDownMenu rendered="#{login.menuDinamicS[0].mostrar}">
												<f:facet name="label">
													<h:outputText value="#{login.menuDinamicS[0].nombreMenu}" />
												</f:facet>
												<rich:menuItem
													rendered="#{login.menuDinamicS[0].submenus[0].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[0].submenus[0].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[0].submenus[0].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[0].submenus[1].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[0].submenus[1].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[0].submenus[1].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[0].submenus[2].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[0].submenus[2].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[0].submenus[2].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[0].submenus[3].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[0].submenus[3].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[0].submenus[3].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[0].submenus[4].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[0].submenus[4].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[0].submenus[4].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[0].submenus[5].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[0].submenus[5].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[0].submenus[5].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[0].submenus[6].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[0].submenus[6].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[0].submenus[6].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
											</rich:dropDownMenu>
											<rich:dropDownMenu rendered="#{login.menuDinamicS[1].mostrar}">
												<f:facet name="label">
													<h:outputText value="#{login.menuDinamicS[1].nombreMenu}" />
												</f:facet>
												<rich:menuItem
													rendered="#{login.menuDinamicS[1].submenus[0].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[1].submenus[0].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[1].submenus[0].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[1].submenus[1].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[1].submenus[1].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[1].submenus[1].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[1].submenus[2].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[1].submenus[2].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[1].submenus[2].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[1].submenus[3].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[1].submenus[3].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[1].submenus[3].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[1].submenus[4].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[1].submenus[4].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[1].submenus[4].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[1].submenus[5].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[1].submenus[5].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[1].submenus[5].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[1].submenus[6].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[1].submenus[6].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[1].submenus[6].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
											</rich:dropDownMenu>
											<rich:dropDownMenu rendered="#{login.menuDinamicS[2].mostrar}">
												<f:facet name="label">
													<h:outputText value="#{login.menuDinamicS[2].nombreMenu}" />
												</f:facet>
												<rich:menuItem
													rendered="#{login.menuDinamicS[2].submenus[0].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[2].submenus[0].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[2].submenus[0].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[2].submenus[1].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[2].submenus[1].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[2].submenus[1].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[2].submenus[2].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[2].submenus[2].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[2].submenus[2].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[2].submenus[3].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[2].submenus[3].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[2].submenus[3].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[2].submenus[4].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[2].submenus[4].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[2].submenus[4].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[2].submenus[5].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[2].submenus[5].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[2].submenus[5].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[2].submenus[6].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[2].submenus[6].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[2].submenus[6].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
											</rich:dropDownMenu>
											<rich:dropDownMenu rendered="#{login.menuDinamicS[3].mostrar}">
												<f:facet name="label">
													<h:outputText value="#{login.menuDinamicS[3].nombreMenu}" />
												</f:facet>
												<rich:menuItem
													rendered="#{login.menuDinamicS[3].submenus[0].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[3].submenus[0].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[3].submenus[0].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[3].submenus[1].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[3].submenus[1].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[3].submenus[1].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[3].submenus[2].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[3].submenus[2].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[3].submenus[2].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[3].submenus[3].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[3].submenus[3].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[3].submenus[3].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[3].submenus[4].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[3].submenus[4].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[3].submenus[4].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[3].submenus[5].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[3].submenus[5].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[3].submenus[5].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[3].submenus[6].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[3].submenus[6].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[3].submenus[6].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
											</rich:dropDownMenu>
											<rich:dropDownMenu rendered="#{login.menuDinamicS[4].mostrar}">
												<f:facet name="label">
													<h:outputText value="#{login.menuDinamicS[4].nombreMenu}" />
												</f:facet>
												<rich:menuItem
													rendered="#{login.menuDinamicS[4].submenus[0].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[4].submenus[0].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[4].submenus[0].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[4].submenus[1].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[4].submenus[1].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[4].submenus[1].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[4].submenus[2].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[4].submenus[2].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[4].submenus[2].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[4].submenus[3].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[4].submenus[3].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[4].submenus[3].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[4].submenus[4].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[4].submenus[4].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[4].submenus[4].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[4].submenus[5].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[4].submenus[5].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[4].submenus[5].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[4].submenus[6].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[4].submenus[6].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[4].submenus[6].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
											</rich:dropDownMenu>
											<rich:dropDownMenu rendered="#{login.menuDinamicS[5].mostrar}">
												<f:facet name="label">
													<h:outputText value="#{login.menuDinamicS[5].nombreMenu}" />
												</f:facet>
												<rich:menuItem
													rendered="#{login.menuDinamicS[5].submenus[0].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[5].submenus[0].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[5].submenus[0].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[5].submenus[1].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[5].submenus[1].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[5].submenus[1].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[5].submenus[2].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[5].submenus[2].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[5].submenus[2].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[5].submenus[3].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[5].submenus[3].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[5].submenus[3].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[5].submenus[4].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[5].submenus[4].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[5].submenus[4].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[5].submenus[5].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[5].submenus[5].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[5].submenus[5].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[5].submenus[6].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[5].submenus[6].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[5].submenus[6].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
											</rich:dropDownMenu>
											<rich:dropDownMenu rendered="#{login.menuDinamicS[6].mostrar}">
												<f:facet name="label">
													<h:outputText value="#{login.menuDinamicS[6].nombreMenu}" />
												</f:facet>
												<rich:menuItem
													rendered="#{login.menuDinamicS[6].submenus[0].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[3].submenus[0].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[6].submenus[0].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[6].submenus[1].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[6].submenus[1].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[6].submenus[1].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[6].submenus[2].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[6].submenus[2].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[6].submenus[2].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[6].submenus[3].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[6].submenus[3].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[6].submenus[3].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[6].submenus[4].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[6].submenus[4].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[6].submenus[4].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[6].submenus[5].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[6].submenus[5].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[6].submenus[5].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
												<rich:menuItem
													rendered="#{login.menuDinamicS[6].submenus[6].mostrar}">
													<h:commandButton
														value="#{login.menuDinamicS[6].submenus[6].nombreMenu}"
														action="#{login.destroyBeans }">
														<f:setPropertyActionListener target="#{login.paginaH}"
															value="#{login.menuDinamicS[6].submenus[6].linkMenu}" />
													</h:commandButton>
												</rich:menuItem>
											</rich:dropDownMenu>
											</td>
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamicS[1]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamicS[2]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamicS[3]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamicS[4]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamicS[5]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamicS[6]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamicS[7]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamicS[8]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamicS[9]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamic[10]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamic[11]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamic[12]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamic[13]}"></rich:dropDownMenu></td> -->
										<!-- 										<td><rich:dropDownMenu binding="#{login.menuDinamic[14]}"></rich:dropDownMenu></td> -->
										<td align="right"><rich:dropDownMenu value="SESIÓN">
												<rich:menuItem>
													<h:commandButton action="#{login.TerminarSesion}"
														value="SALIR">
													</h:commandButton>
												</rich:menuItem>
											</rich:dropDownMenu></td>
									</tr>

								</table>
							</rich:toolBar></td>
					</tr>
				</table>
			</a4j:outputPanel>
		</h:form>
	</ui:composition>
</body>
</html>