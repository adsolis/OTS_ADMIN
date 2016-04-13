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
<title>Recepción de Ajustes</title>
</head>
<body>
<f:view>
<ui:composition template="/Paginas/template.jsp">
	<ui:define name="content">
	
		<!--  Mensajes de la Vista -->
		<f:loadBundle basename="com.datacode.avon_ots_admin.cu002.messagesCU002" var="msg" />
		<!-- Diseno de Pagina -->
		<a4j:form binding="#{cu002RecAjustes.frmPrincipal}" styleClass="forma" id="frmPrincipal" >
			<h:outputLabel value="#{msg.titulo_RecAjustes}" styleClass="tituloPagina"></h:outputLabel>
			
			<h:panelGrid columns="6" id="pnlCombos">
				<h:outputLabel value="#{msg.lblZona_RecAjustes}" id="lblZona"
					styleClass="label"></h:outputLabel>
				<h:selectOneMenu validatorMessage="required" required="true" 
					valueChangeListener="#{cu002RecAjustes.getNombreGerente}"
					immediate="true" id="cmbZona" value="#{cu002RecAjustes.idZona}">
					<a4j:support event="onchange" reRender="frmPrincipal" />
					<f:selectItems value="#{cu002RecAjustes.cmbZonas}" />
				</h:selectOneMenu>

				<h:outputLabel value="#{msg.lblCampania_RecAjustes}" id="lblCampania"
					styleClass="label"></h:outputLabel>
				<h:selectOneMenu validatorMessage="required" required="true"
					id="cmbCampania" value="#{cu002RecAjustes.idCampania}">
					<f:selectItems value="#{cu002RecAjustes.cmbCampanias}" />
				</h:selectOneMenu>				
				
				<h:outputLabel value="#{msg.lblGteZona_RecAjustes}" id="lblGteZona"
					styleClass="label"></h:outputLabel>
				<h:outputLabel value="#{cu002RecAjustes.gerenteZona}" id="lblNombreGteZona"
					styleClass="label"></h:outputLabel>
			</h:panelGrid>
			
			<h:outputLabel value="#{cu002RecAjustes.mensajeError}" id="lblError"
					styleClass="error"></h:outputLabel>
					
			<h:panelGrid columns="3" id="pnlBotones">
				<a4j:commandButton action="#{cu002RecAjustes.nuevoAjuste}" value="#{msg.btnNuevo_RecAjustes}" id="btnNuevo" accesskey="n" reRender="frmPrincipal,frmCaptura,frmConsulta"
					disabled="#{not cu002RecAjustes.mapaAccion['NUEVO']}">
					</a4j:commandButton>
				<a4j:commandButton action="#{cu002RecAjustes.consultarAjustes}" value="#{msg.btnConsultar_RecAjustes}" id="btnConsultar" accesskey="o" reRender="frmPrincipal,frmCaptura,frmConsulta"
					disabled="#{not cu002RecAjustes.mapaAccion['CONSULTAR']}">
					</a4j:commandButton>
			</h:panelGrid>
		</a4j:form>
		
		<a4j:form binding="#{cu002RecAjustes.frmCaptura}" styleClass="forma" id="frmCaptura" >
		<rich:panel rendered="#{cu002RecAjustes.mostrarFormCaptura }">
			<rich:panel  id="pnlRecepAjustes" >
				<f:facet name="header">
					<h:outputLabel value="#{msg.lblRecepAjustes_RecAjustes}" id="lblRecepAjustes"
						styleClass="label"></h:outputLabel>
				</f:facet>
				<table align="center">
				<tr>
				<td style="vertical-align: top;">
				<rich:panel  id="pnlBelleza">
					<f:facet name="header">
						<h:outputLabel value="#{msg.lblBelleza_RecAjustes}" id="lblBelleza"
							styleClass="label"></h:outputLabel>
					</f:facet>
					<table><tr class="headerTabla"><td>
						<h:outputLabel value="#{msg.lblNegocioBelleza_RecAjustes}" id="lblNegocioBelleza"
							styleClass="label"></h:outputLabel>
							</td>
							<td>
						<h:outputLabel value="#{msg.lblBuenEstadoBelleza_RecAjustes}" id="lblBuenEstadoBelleza"
							styleClass="label"></h:outputLabel>
							</td>
							<td>
						<h:outputLabel value="#{msg.lblMalEstadoBelleza_RecAjustes}" id="lblMalEstadoBelleza"
							styleClass="label"></h:outputLabel>
							</td>
							</tr>
							<tr>
							<td>
						<h:outputLabel value="#{msg.lblCosmeticoBelleza_RecAjustes}" id="lblCosmeticoBelleza"
							styleClass="label"></h:outputLabel>
							</td>
							<td>
						<h:inputText value="#{cu002RecAjustes.txtCosmeticoBuenEstado}" id="txtCosmeticoBuenEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td>
						<td>
						<h:inputText value="#{cu002RecAjustes.txtCosmeticoMalEstado}" id="txtCosmeticoMalEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td>
						</tr>
						<tr>
						<td>
						<h:outputLabel value="#{msg.lblBienestarBelleza_RecAjustes}" id="lblBienestarBelleza"
							styleClass="label"></h:outputLabel>
							</td>
							<td>
						<h:inputText value="#{cu002RecAjustes.txtBienestarBellezaBuenEstado}" id="txtBienestarBellezaBuenEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td>
						<td>
						<h:inputText value="#{cu002RecAjustes.txtBienestarBellezaMalEstado}" id="txtBienestarBellezaMalEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td>
						</tr>
						<tr>
						<td>
						<h:outputLabel value="#{msg.lblSubtotalBelleza_RecAjustes}" id="lblSubtotalBelleza"
							styleClass="label"></h:outputLabel>
							</td>
							<td>
						<h:outputLabel value="#{cu002RecAjustes.txtSubTotalBellezaBuenEstado}" id="lblSubtotalBellezaBuenEstado"
							styleClass="label"></h:outputLabel>
							</td>
							<td>
						<h:outputLabel value="#{cu002RecAjustes.txtSubTotalBellezaMalEstado}" id="lblSubtotalBellezaMalEstado"
							styleClass="label"></h:outputLabel>
							</td>
					</tr>
					<tr><td colspan="2" align="right">
							<h:outputLabel value="#{msg.lblTotalCajasEnviarBelleza_RecAjustes}" id="lblTotalCajasEnviarBelleza"
								styleClass="label"></h:outputLabel>
								</td>
								<td>
							<h:inputText value="#{cu002RecAjustes.txtTotalCajasEnviarBelleza}" id="txtTotalCajasEnviarBelleza" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3"></h:inputText>
					
					</td></tr></table>
				</rich:panel>
				</td>
				<td>
				<rich:panel  id="pnlCasaModa" >
					<f:facet name="header">
						<h:outputLabel value="#{msg.lblCasaModa_RecAjustes}" id="lblCasaModa"
							styleClass="label"></h:outputLabel>
					</f:facet>
					<table><tr class="headerTabla"><td>
						<h:outputLabel value="#{msg.lblNegocioCasaModa_RecAjustes}" id="lblNegocioCasaModa"
							styleClass="label"></h:outputLabel>
							</td>
							<td>
						<h:outputLabel value="#{msg.lblBuenEstadoCasaModa_RecAjustes}" id="lblBuenEstadoCasaModa"
							styleClass="label"></h:outputLabel>
							</td>
							<td>
						<h:outputLabel value="#{msg.lblMalEstadoCasaModa_RecAjustes}" id="lblMalEstadoCasaModa"
							styleClass="label"></h:outputLabel>
							</td>
							</tr>
							<tr>
							<td>
						<h:outputLabel value="#{msg.lblHogarCasaModa_RecAjustes}" id="lblHogarCasaModa"
							styleClass="label"></h:outputLabel>
							</td>
							<td>
						<h:inputText value="#{cu002RecAjustes.txtHogarBuenEstado}" id="txtHogarBuenEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td>
						<td>
						<h:inputText value="#{cu002RecAjustes.txtHogarMalEstado}" id="txtHogarMalEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td>
						</tr>
						<tr>
						<td>
						<h:outputLabel value="#{msg.lblModaCasaModa_RecAjustes}" id="lblModaCasaModa"
							styleClass="label"></h:outputLabel>
							</td><td>
						<h:inputText value="#{cu002RecAjustes.txtModaBuenEstado}" id="txtModaBuenEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td><td>
						<h:inputText value="#{cu002RecAjustes.txtModaMalEstado}" id="txtModaMalEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td></tr>
						<tr>
						<td>
						<h:outputLabel value="#{msg.lblLenceriaCasaModa_RecAjustes}" id="lblLenceriaCasaModa"
							styleClass="label"></h:outputLabel>
							</td><td>
						<h:inputText value="#{cu002RecAjustes.txtLenceriaBuenEstado}" id="txtLenceriaBuenEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td><td>
						<h:inputText value="#{cu002RecAjustes.txtLenceriaMalEstado}" id="txtLenceriaMalEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td></tr>
						<tr>
						<td>
						<h:outputLabel value="#{msg.lblJoyeriaCasaModa_RecAjustes}" id="lblJoyeriaCasaModa"
							styleClass="label"></h:outputLabel>
							</td><td>
						<h:inputText value="#{cu002RecAjustes.txtJoyeriaBuenEstado}" id="txtJoyeriaBuenEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td><td>
						<h:inputText value="#{cu002RecAjustes.txtJoyeriaMalEstado}" id="txtJoyeriaMalEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td></tr>
						<tr>
						<td>
						<h:outputLabel value="#{msg.lblBienestarCasaModa_RecAjustes}" id="lblBienestarCasaModa"
							styleClass="label"></h:outputLabel>
							</td><td>
						<h:inputText value="#{cu002RecAjustes.txtBienestarCasaModaBuenEstado}" id="txtBienestarCasaModaBuenEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td><td>
						<h:inputText value="#{cu002RecAjustes.txtBienestarCasaModaMalEstado}" id="txtBienestarCasaModaMalEstado" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3" onblur="sumarSubtotales();"></h:inputText>
						</td></tr>
						<tr>
						<td>
						<h:outputLabel value="#{msg.lblSubtotalCasaModa_RecAjustes}" id="lblSubtotalCasaModa"
							styleClass="label"></h:outputLabel>
							</td><td>
						<h:outputLabel value="#{cu002RecAjustes.txtSubTotalCasaModaBuenEstado}" id="lblSubtotalCasaModaBuenEstado"
							styleClass="label"></h:outputLabel>
							</td><td>
						<h:outputLabel value="#{cu002RecAjustes.txtSubTotalCasaModaMalEstado}" id="lblSubtotalCasaModaMalEstado"
							styleClass="label"></h:outputLabel>
							</td></tr>
					<tr>
					<td colspan="2" align="right">
					
							<h:outputLabel value="#{msg.lblTotalCajasEnviarCasaModa_RecAjustes}" id="lblTotalCajasEnviarCasaModa"
								styleClass="label"></h:outputLabel></td>
								<td>
							<h:inputText value="#{cu002RecAjustes.txtTotalCajasEnviarCasaModa}" id="txtTotalCajasEnviarCasaModa" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3"></h:inputText>
						
						</td></tr></table>
					
				</rich:panel>
			</td>
			</tr>
			</table>
			</rich:panel>
			<rich:panel>
			<f:facet name="header">
					<h:outputLabel value="#{msg.lblPremios_RecAjustes}" id="lblPremios"
						styleClass="label"></h:outputLabel>
				</f:facet>
			<h:panelGrid columns="8" id="pnlRecepPremios">
				
				
				<h:outputLabel value="#{msg.lblCantidadPremios_RecAjustes}" id="lblCantidadPremios"
							styleClass="label"></h:outputLabel>
				<h:inputText value="#{cu002RecAjustes.txtCantidadPremios}" id="txtCantidadPremios" styleClass="caja" size="10" onkeypress="return validarNumeros(event);" maxlength="3"></h:inputText>
				<h:outputLabel value="#{msg.lblDescripcionPremios_RecAjustes}" id="lblDescripcionPremios"
							styleClass="label"></h:outputLabel>
				<h:inputText value="#{cu002RecAjustes.txtDescripcionPremios}" id="txtDescripcionPremios" styleClass="caja" maxlength="100" size="30"></h:inputText>
				
				<h:outputLabel value="#{msg.lblPrograma_RecAjustes}" id="lblPrograma"
							styleClass="label"></h:outputLabel>
				<h:inputText value="#{cu002RecAjustes.txtPrograma}" id="txtPrograma" styleClass="caja" maxlength="15" size="10"></h:inputText>
				<h:outputLabel value="#{msg.lblCampaniaPremios_RecAjustes}" id="lblCampaniaPremios"
							styleClass="label"></h:outputLabel>
				<h:selectOneMenu id="cmbCampaniaPremio" value="#{cu002RecAjustes.idCampaniaPremio}">
					<f:selectItems value="#{cu002RecAjustes.cmbCampanias}" />
				</h:selectOneMenu>
			</h:panelGrid>
			
			<h:commandButton action="#{cu002RecAjustes.agregarPremio}" value="#{msg.btnAgregarPremio_RecAjustes}" id="btnAgregarPremio" accesskey="a" 
				styleClass="botonPrincipal" onclick="return validarRequeridosGuardarPremio();">
				</h:commandButton>
			
			<rich:dataTable value="#{cu002RecAjustes.premios}" var="premioSel"
			styleClass="gridDatos" headerClass="gridDatosEncabezado"
			onRowMouseOver="this.style.backgroundColor='#{a4jSkin.additionalBackgroundColor}'"
			onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
				<rich:column>
					<f:facet name="header">
						
							<h:outputText value="#{msg.encabezadoCantidadGridPremios_RecAjustes}"></h:outputText>
						
					</f:facet>
					<h:outputText value="#{premioSel.cantidad}"></h:outputText>
				</rich:column>
				<rich:column>
					<f:facet name="header">
						
							<h:outputText value="#{msg.encabezadoDescripcionGridPremios_RecAjustes}"></h:outputText>
						
					</f:facet>
					<h:outputText value="#{premioSel.descripcion}"></h:outputText>
	
				</rich:column>
				<rich:column>
					<f:facet name="header">
						
							<h:outputText value="#{msg.encabezadoProgramaGridPremios_RecAjustes}"></h:outputText>
						
					</f:facet>
					<h:outputText value="#{premioSel.programa}"></h:outputText>
				</rich:column>
				<rich:column>
					<f:facet name="header">
						
							<h:outputText value="#{msg.encabezadoCampaniaGridPremios_RecAjustes}"></h:outputText>
						
					</f:facet>
					<h:outputText value="#{premioSel.claveCampania}"></h:outputText>
				</rich:column>
	
				<rich:column>
					<f:facet name="header">
						
							<h:outputText value="Acciones"></h:outputText>
						
					</f:facet>
					<h:panelGrid columns="2">
						<h:commandButton action="#{cu002RecAjustes.eliminarPremio}" value="Eliminar" styleClass="botonGrid"
							onclick="return confirmaEliminar()">
							<f:setPropertyActionListener target="#{cu002RecAjustes.premioSel}" value="#{premioSel}" />
						</h:commandButton>
					</h:panelGrid>
				</rich:column>
			</rich:dataTable>
			<h:panelGroup>
				<h:outputLabel value="#{msg.lblTotalCajasEnviarPremios_RecAjustes}" id="lblTotalCajasEnviarPremios"
					styleClass="label"></h:outputLabel>
				<h:inputText value="#{cu002RecAjustes.txtTotalCajasEnviarPremios}" id="txtTotalCajasEnviarPremios" styleClass="caja" onkeypress="return validarNumeros(event);" maxlength="3"></h:inputText>
			</h:panelGroup>
			</rich:panel>
			<br />
			<h:selectOneMenu value="#{cu002RecAjustes.formatoReporte}">
													<f:selectItem itemLabel="PDF" itemValue="PDF" />
													<f:selectItem itemLabel="Excel" itemValue="XLS"  />
													<f:selectItem itemLabel="CSV" itemValue="CSV" />
												</h:selectOneMenu>
			<h:commandLink action="#{cu002RecAjustes.exportarAjuste}" target="_blank"
						>
						<h:commandButton value="Exportar"  />
					</h:commandLink>
			<h:commandButton action="#{cu002RecAjustes.cancelarAjuste}" value="#{msg.btnCancelarAjuste_RecAjustes}" id="btnCancelarAjuste" accesskey="c" styleClass="botonPrincipal">
				</h:commandButton>
			<h:commandButton action="#{cu002RecAjustes.guardarAjuste}" value="#{msg.btnGuardarAjuste_RecAjustes}" id="btnGuardarAjuste" accesskey="g" styleClass="botonPrincipal" 
				binding="#{cu002RecAjustes.btnGuardarAjuste}" onclick="return validarRequeridosGuardarAjuste();"
				disabled="#{not cu002RecAjustes.mapaAccion['GUARDAR']}">
				</h:commandButton>
			<!-- <input type="button" value="#{not cu002RecAjustes.mapaAccion['IMPRIMIR']}" onclick="imprimir()" class="botonPrincipal"/> -->
			<h:commandButton value="Imprimir" onclick="imprimir()" styleClass="botonPrincipal" 
				disabled="#{not cu002RecAjustes.mapaAccion['IMPRIMIR']}" />
			<h:commandLink target="_blank" action="#{cu002RecAjustes.generarCodigosBarras}" value="#{msg.btnGenerarCodigosBarras}" id="lnkGenerarCodigosBarras" styleClass="botonPrincipal"
				binding="#{cu002RecAjustes.lnkGenerarCodigosBarras}" rendered="false" 
				disabled="#{not cu002RecAjustes.mapaAccion['GENERAR CÓDIGOS']}">
				<f:setPropertyActionListener target="#{cu002RecAjustes.idGeneracionCodigos}" value="#{cu002RecAjustes.idGeneracionCodigos}" />
			</h:commandLink>
			<h:inputHidden id="activarGeneracion" value="#{cu002RecAjustes.activarGeneracion}"></h:inputHidden>
			
			<script type="text/javascript">
			//Después de 3 segundos se ejecuta la función para dar click a la liga para generar los códigos de barras
			setTimeout("ejecutarGeneracionCodigos()", 2000);
			</script>
			</rich:panel>
		</a4j:form>
		
		<a4j:form binding="#{cu002RecAjustes.frmConsulta}" styleClass="forma" id="frmConsulta" >
		<rich:panel rendered="#{cu002RecAjustes.mostrarFormConsulta }">
		<f:facet name="header">
					<h:outputText value="#{msg.encabezadoGridAjustes_RecAjustes}"></h:outputText>
				</f:facet>
			<rich:dataTable value="#{cu002RecAjustes.ajustes}" var="ajuste"
				styleClass="gridDatos" headerClass="gridDatosEncabezado" 
				onRowMouseOver="this.style.backgroundColor='#{a4jSkin.additionalBackgroundColor}'"
				onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
				
				<rich:column>
					<f:facet name="header">
						
							<h:outputText value="#{msg.encabezadoZonaGridAjustes_RecAjustes}"></h:outputText>
						
					</f:facet>
					<h:outputText value="#{ajuste.zona}"></h:outputText>
				</rich:column>
				<rich:column>
					<f:facet name="header">
						
							<h:outputText value="#{msg.encabezadoCampaniaGridAjustes_RecAjustes}"></h:outputText>
						
					</f:facet>
					<h:outputText value="#{ajuste.claveCampania}"></h:outputText>
	
				</rich:column>
				<rich:column>
					<f:facet name="header">
						
							<h:outputText value="#{msg.encabezadoFechaGridAjustes_RecAjustes}"></h:outputText>
						
					</f:facet>
					<h:outputText value="#{ajuste.fechaRecepcion}"></h:outputText>
				</rich:column>
				<rich:column>
					<f:facet name="header">
						
							<h:outputText value="#{msg.encabezadoQuienEntregoGridAjustes_RecAjustes}"></h:outputText>
						
					</f:facet>
					<h:outputText value="#{ajuste.gerenteZonal}"></h:outputText>
				</rich:column>
				<rich:column>
					<f:facet name="header">
						
							<h:outputText value="#{msg.encabezadoQuienRecibioGridAjustes_RecAjustes}"></h:outputText>
						
					</f:facet>
					<h:outputText value="#{ajuste.quienRecibio}"></h:outputText>
				</rich:column>
	
				<rich:column>
					<f:facet name="header">
					
							<h:outputText value="Acciones"></h:outputText>
						
					</f:facet>
					<h:panelGrid columns="2">
						<a4j:commandButton action="#{cu002RecAjustes.modificarAjuste}" value="Modificar" styleClass="botonGrid"
							disabled="#{not cu002RecAjustes.mapaAccion['MODIFICAR']}" reRender="frmPrincipal,frmCaptura,frmConsulta">
							<f:setPropertyActionListener target="#{cu002RecAjustes.ajusteSel}" value="#{ajuste}" />
						</a4j:commandButton>
						<a4j:commandButton action="#{cu002RecAjustes.eliminarAjuste}" value="Eliminar" styleClass="botonGrid"
							onclick="confirmaEliminar()"
							disabled="#{not cu002RecAjustes.mapaAccion['ELIMINAR']}" reRender="frmPrincipal,frmCaptura,frmConsulta">
							<f:setPropertyActionListener target="#{cu002RecAjustes.ajusteSel}" value="#{ajuste}" />
						</a4j:commandButton>
					</h:panelGrid>
				</rich:column>
			</rich:dataTable>
			</rich:panel>
		</a4j:form>
		
	</ui:define>
</ui:composition>
</f:view>
</body>
</html>