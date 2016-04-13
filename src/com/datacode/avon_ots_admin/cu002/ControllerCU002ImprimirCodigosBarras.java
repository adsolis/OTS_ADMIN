package com.datacode.avon_ots_admin.cu002;

/**
 * Clase controlador para las pantallas del CU002
 * @author jorge.torner
 * @since 29/12/2011
 */
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.faces.component.*;
import javax.faces.context.FacesContext;

import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.ImprimirCodigosBarrasControllerStub;

public class ControllerCU002ImprimirCodigosBarras {
	//Obtiene el objeto Configuración con los valores cargados al inicio de sesión
	Configuracion config = (Configuracion)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("configuracion");
	
	//Panel de imágenes de códigos de barras que está vinculado al panel que muestra los códigos a imprimir
	private javax.faces.component.UIPanel panelImagenes;
	//Lista de códigos a imprimir, se debe llenar antes de mandar a llamar a la página ImprimirCodigosBarras
	private ArrayList<String> codigosImprimir;
	//Tipo de los códigos a imprimir, ésta cadena puede ser "Ajustes" o "Veas"
	private String tipoCodigos;
	private String lstIdsGeneracionCodigos;
	private Long idTablaCodigos = new Long(0);
	private String mensajeError;
	

	public UIPanel getPanelImagenes() {
		return panelImagenes;
	}

	/**
	 * Método que además de asignar el panelImagenes, le agrega los controles e imágenes de los códigos a imprimir
	 * @author jorge.torner
	 * @since 30/12/2011
	 * @param panelImagenes El panel que contendrá las imágenes
	 */
	public void setPanelImagenes(javax.faces.component.UIPanel panelImagenes) {
//		//Prueba códigos barras
//		if(tipoCodigos == null)
//			tipoCodigos = "AJUSTES";
//		if(codigosImprimir == null){
//			codigosImprimir = new ArrayList<String>();
//			codigosImprimir.add("ADJ11201120701");
//			codigosImprimir.add("ADJ11201120702");
//			codigosImprimir.add("ADJ11201120703");
//			codigosImprimir.add("ADJ11201120704");
//			codigosImprimir.add("ADJ11201120705");
//			codigosImprimir.add("ADJ11201120706");
//			codigosImprimir.add("ADJ11201120707");
//			codigosImprimir.add("ADJ11201120708");
//			codigosImprimir.add("ADJ11201120709");
//			codigosImprimir.add("ADJ11201120710");
//			codigosImprimir.add("ADJ11201120711");
//			codigosImprimir.add("ADJ11201120712");
//			codigosImprimir.add("ADJ11201120713");
//		}

//		tipoCodigos = "AJUSTES";
//		idTablaCodigos = 1;
		tipoCodigos = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tipoGeneracionCodigos"));
		String id = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idGeneracionCodigos"));
		if(tipoCodigos != null && id != null){
			if (!tipoCodigos.equals("UNIDAD_REPARTO")) {
				idTablaCodigos = Long.parseLong(id);
				lstIdsGeneracionCodigos = "";
				obtenerCodigos();
			} else {
				idTablaCodigos = 0L;
				lstIdsGeneracionCodigos = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("lstIdsGeneracionCodigos"));;
				obtenerCodigos();
			}
		}
		if(getTipoCodigos().equals("AJUSTES"))
			setTipoCodigos("Ajustes");
		else if(getTipoCodigos().equals("VEAS"))
			setTipoCodigos("Veas");
		else if(getTipoCodigos().equals("UNIDAD_REPARTO")) {
			setTipoCodigos("Unidad de reparto");
		}
		
		
		String fecha = Utils.ObtenerFechaActual(Utils.formatoFechaCorta);
		String ipPuertoBarcode = Utils.obtenerPropiedadArchivoConfig("IpPuertoBarcodeServlet");
		
		if(panelImagenes.getChildCount() <= 1 && codigosImprimir != null){
		//if(codigosImprimir != null){
			for(int i = 0; i<codigosImprimir.size(); i++){
				int modulo = i % 6;
				//Cuando es cambio de página agregamos un renglón con un espacio grande para saltar de página
				if(i!=0 && modulo ==0){
					UIPanel panelSeparador = new UIPanel();
					panelImagenes.getChildren().add(panelSeparador);

					panelSeparador = new UIPanel();
					panelImagenes.getChildren().add(panelSeparador);
				}
				String codigo = codigosImprimir.get(i);
				
				//Se crean los labels de el tipo de código y fecha
				UIOutput lblTipo = new UIOutput();
				lblTipo.setRendererType("javax.faces.Label");
				lblTipo.setValue(getTipoCodigos());
				UIOutput lblFecha = new UIOutput();
				lblFecha.setRendererType("javax.faces.Label");
				lblFecha.setValue(fecha);
				
				//Se crea el panelGrid que va a contener los labels y los agregamos
				UIPanel pnlGridInt = new UIPanel();
				pnlGridInt.setRendererType("javax.faces.Grid");
				pnlGridInt.getAttributes().put("columns", 2);
				pnlGridInt.getAttributes().put("width", "100%");
				pnlGridInt.getChildren().add(lblTipo);
				pnlGridInt.getChildren().add(lblFecha);
				pnlGridInt.setRendered(true);

				//Se crea la imagen
				UIGraphic img = new UIGraphic();
				img.setValue("http://" + ipPuertoBarcode + "/rbarcode/BarcodeServlet?BARCODE=" + codigo + "&WIDTH=240&HEIGHT=120&CHECK_CHAR=Y&CODE_TYPE=CODE128&FORMAT=png");
//				img.getAttributes().put("width", "200px");//300
//				img.getAttributes().put("height", "140px");//200
				img.setRendered(true);
				
				//Se crea el panelGroup para agregar el panelGrid y la imagen
				UIPanel panelGroup = new UIPanel();
				//panelGroup.setRendererType("javax.faces.Group");
				panelGroup.getChildren().add(pnlGridInt);
				panelGroup.getChildren().add(img);
				panelGroup.setRendered(true);
				
				//Se agrega el panelGroup al panelGrid principal
				panelImagenes.getChildren().add(panelGroup);
			}
		}
		panelImagenes.setRendered(true);

		this.panelImagenes = panelImagenes;
		
	}
	
	private void obtenerCodigos(){
		setMensajeError("");
		try {
			ImprimirCodigosBarrasControllerStub stub = new ImprimirCodigosBarrasControllerStub();
			
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			
			ImprimirCodigosBarrasControllerStub.ObtenerCodigosBarrasAGenerar request = new ImprimirCodigosBarrasControllerStub.ObtenerCodigosBarrasAGenerar();

			// parámetros
			request.setP_tipo(getTipoCodigos());
			request.setP_id(getIdTablaCodigos());
			request.setP_lstIdsGeneracionCodigos(getLstIdsGeneracionCodigos());
			request.setP_idUsuario(config.getIdUsuario());

			// invocamos
			ImprimirCodigosBarrasControllerStub.ObtenerCodigosBarrasAGenerarResponse response = stub.obtenerCodigosBarrasAGenerar(request);

			// obtenemos respuesta
			String[] codigos = response.get_return();
			if(codigos != null){
				codigosImprimir = new ArrayList<String>();
				for(String cod : codigos){
					codigosImprimir.add(cod);
				}
			}

		} catch (RemoteException ex) {
			setMensajeError(Utils.ObtenerMensajeBD("General Admin", "M5", true, "Error al consultar los códigos de barras", ex.getMessage(), config.getIdUsuario())[0]);
		}
		catch (Exception ex) {
			setMensajeError(Utils.ObtenerMensajeBD("General Admin", "M5", true, "Error al consultar los códigos de barras", ex.getMessage(), config.getIdUsuario())[0]);
		}
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getTipoCodigos() {
		return tipoCodigos;
	}

	public void setTipoCodigos(String tipoCodigos) {
		this.tipoCodigos = tipoCodigos;
	}

	public Long getIdTablaCodigos() {
		return idTablaCodigos;
	}

	public void setIdTablaCodigos(Long idTablaCodigos) {
		this.idTablaCodigos = idTablaCodigos;
	}

	public String getLstIdsGeneracionCodigos() {
		return lstIdsGeneracionCodigos;
	}

	public void setLstIdsGeneracionCodigos(String lstIdsGeneracionCodigos) {
		this.lstIdsGeneracionCodigos = lstIdsGeneracionCodigos;
	}
	
	
}
