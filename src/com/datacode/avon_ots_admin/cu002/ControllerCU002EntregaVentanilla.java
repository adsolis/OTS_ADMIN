/**
 * @author jose.ponce
 * @since 17/01/2012
 */
package com.datacode.avon_ots_admin.cu002;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.datacode.avon_ots_admin.reports.JasperGenerator;
import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.EntregaVentanillaStub;
import com.datacode.avon_ots_ws.EntregaVentanillaStub.Bancos;
import com.datacode.avon_ots_ws.EntregaVentanillaStub.EntregaVentanilla;
import com.datacode.avon_ots_ws.EntregaVentanillaStub.PersonaRecibe;
import com.datacode.avon_ots_ws.EntregaVentanillaStub.TipoPago;

/**
 * @author jose.ponce
 *
 */
/**
 * @author jessica.leon
 * 
 */
public class ControllerCU002EntregaVentanilla {
	private Integer idTipoPago;
	private String descripcion;
	private Integer idBanco;
	private Integer idPersonaRecibe;
	private String nomBanco;
	private String cveOrden;
	private String nombreRepre = "";
	private String registroRepre = "";
	private String campania;
	private String statusOrden;
	private String domicilio = "";
	private String zona = "";
	private String txtFolioCobranza;
	private String txtOtroRecibe;
	private String txtComentarios;
	private UIForm frmModal;
	private UIForm frmPrincipal;
	private UIForm frmExportar;
	private String MONTO = "$ ";
	//private int idOrden = 0;
	private int idOrden;
	private Double montoAcobrar = 0.0;
	private Double montoPagado = 0.0;
	private Double montoRemanente;
	private String montoRemanenteS;
	private String codigoBarras;
	private String codigoFSC;
	private String codigoEAN13;
	private String rutaImagen;
	private String fechaPago;
	private Double montoDePago;
	private int idZona;
	private int idCampania;
	Boolean bandera = true;
	private Boolean btnConsulta = true;
	private Boolean btnEscan = false;
	private Boolean btnPago = false;
	private Boolean btnEntrega = false;

	private Boolean cmbBanco = false;
	private Boolean txtFolioCob = false;
	private Boolean btnQuitar = false;
	private Boolean btnAgregar = false;
	private Boolean btnExporta = false;
	private Boolean txtOtros = false;
	private Boolean lblOtros = false;
	private Boolean tableRepre = true;
	private Boolean gridOrdDet = true;
	private Boolean gridPagDet = true;
	private Boolean mostrarPanel = false;
	private List<SelectItem> listBanco = new ArrayList<SelectItem>();
	private List<EntregaVentanilla> lstItems = new ArrayList<EntregaVentanilla>();
	private List<EntregaVentanilla> lstPagos = new ArrayList<EntregaVentanilla>();

	private TipoPago[] pag;
	private Bancos[] ban;
	private PersonaRecibe[] perRec;
	private EntregaVentanilla[] detPago = null;
	private String mensajeError;
	private EntregaVentanilla entrega = null;
	private EntregaVentanilla representante;
	private EntregaVentanilla pagoActual;
	private EntregaVentanilla DetOrd;

	Configuracion configuracion;
	Utils utils;

	/**
	 * constructor
	 * 
	 * @author jose.ponce
	 * @since 17/01/2012
	 */
	public ControllerCU002EntregaVentanilla() {
		configuracion = (Configuracion) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("configuracion");
		utils = new Utils();
	}

	/**
	 * @author javier.gallegos
	 * @since 19-01-2012
	 * @return metodo que baja de sesion el mapa de acciones y establece las
	 *         propias del modulo en la variable mapaAccion
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Boolean> getMapaAccion() {
		HashMap<String, Boolean> mapa = new HashMap<String, Boolean>();
		
		HashMap<Integer, HashMap<String, Boolean>> mapaFull = (HashMap<Integer, HashMap<String, Boolean>>) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("accionesM");
		mapa = mapaFull.get(17);// hacerlo dinamico
		// mapa.get("CONSULTAR");
		return mapa;
	}

	/**
	 * Método que nos devuelve la lista con el catálogo de tipos de pago
	 * 
	 * @author jose.ponce
	 * @since 17/01/2012
	 */
	public List<SelectItem> getTipoPago() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		pag = utils.getTipoPago();
		list.add(new SelectItem(0, "Selecciona una opción"));
		try {
			for (int i = 0; i < pag.length; i++) {
				list.add(new SelectItem(pag[i].getIdTipoPuesto(), pag[i]
						.getDescripcion()));
			}
		} catch (NullPointerException ex) {
			setMensajeError(Utils.ObtenerMensajeBD("General Admin", "M6", true,
					"No se pudieron cargar los tipos de pago", ex.getMessage(),
					configuracion.getIdUsuario())[0]);
		}
		return list;
	}

	/**
	 * Método que nos devuelve el catálogo de bancos
	 * 
	 * @author jose.ponce
	 * @since 17/01/2012
	 */
	public List<SelectItem> getCatBancos() {

		ban = utils.getBancos();
		if (getListBanco().size() == 0) {
			getListBanco().add(new SelectItem(0, "Selecciona una opción"));
			try {
				for (int i = 0; i < ban.length; i++) {
					getListBanco().add(
							new SelectItem(ban[i].getIdBanco(), ban[i]
									.getNombre()));
				}
			} catch (NullPointerException ex) {
				setMensajeError(Utils.ObtenerMensajeBD("General Admin", "M7",
						true, "No se pudieron cargar los bancos",
						ex.getMessage(), configuracion.getIdUsuario())[0]);
			}
		}
		return getListBanco();
	}

	/**
	 * Método que nos devuelve el catálogo de personas recibe
	 * 
	 * @author jose.ponce
	 * @since 04/04/2012
	 */
	public List<SelectItem> getCatPersona() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		perRec = utils.getPersonaRecibe();
		list.add(new SelectItem(0, "Selecciona una opción"));
		try {
			for (int i = 0; i < perRec.length; i++) {
				list.add(new SelectItem(perRec[i].getIdPersonaRecibe(),
						perRec[i].getDescripcion()));
			}
		} catch (NullPointerException ex) {
			setMensajeError(Utils.ObtenerMensajeBD("General Admin", "M6", true,
					"No se pudieron cargar los tipos persona recibe",
					ex.getMessage(), configuracion.getIdUsuario())[0]);
		}
		return list;
	}

	/**
	 * Método que obtiene el nombre del banco seleccionado
	 * 
	 * @author jose.ponce
	 * @since 01/02/2012
	 */
	public void getNombreBanco(ValueChangeEvent e) {
		try {
			int idBanco = Integer.valueOf(e.getNewValue().toString());
			// idBanco=idBanco-1;
			ban = utils.getBancos();
			if (idBanco != -1) {
				// nomBanco = ban[idBanco].getNombre();
				for (int i = 0; i < ban.length; i++) {
					if (ban[i].getIdBanco() == idBanco) {
						nomBanco = ban[i].getNombre();
					}
				}
				// nomBanco= getListBanco().get(idBanco).getValue().toString();
			} else {
				nomBanco = "";
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Método para validar el tipo de pago seleccionado y habilitar o
	 * deshabilitar controles
	 * 
	 * @author jose.ponce
	 * @since 21/01/2012
	 */
	public void validaTipoPago(ValueChangeEvent e) {
		try {
			String idTipoPago = e.getNewValue().toString();
			if (idTipoPago.equals("1")) {// Banco
				cmbBanco = true;
				txtFolioCob = false;
				txtFolioCobranza = "";
				idBanco = 0;
			} else if (idTipoPago.equals("2")) {// Folio de Cobranza
				txtFolioCob = true;
				cmbBanco = false;
				txtFolioCobranza = "";
				idBanco = 0;
			} else {
				txtFolioCob = false;
				cmbBanco = false;
				txtFolioCobranza = "";
				idBanco = 0;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void validaPersonaRecibe(ValueChangeEvent e) {
		try {
			String idPersona = e.getNewValue().toString();
			if (idPersona.equals("3")) {
				lblOtros = true;
				txtOtros = true;
				txtOtroRecibe = "";
			} else {
				lblOtros = false;
				txtOtros = false;
				txtOtroRecibe = "";
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Método que llena el grid de ordes a entregar deacuerdo a la representante
	 * 
	 * @author jose.ponce
	 * @since 25/01/2012
	 */
	public List<EntregaVentanilla> getRepreEntrega() {
		EntregaVentanilla[] arrOrd = null;
		List<EntregaVentanilla> lstRepre = null;
		try {
			// Se crea el cliente
			EntregaVentanillaStub stub = new EntregaVentanillaStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Se agrega al request para la operacion a ejecutar
			EntregaVentanillaStub.GetOrdenesRepresentante request = new EntregaVentanillaStub.GetOrdenesRepresentante();
			// pasamos parametros
			request.setRegistro(registroRepre);
			request.setNombre(nombreRepre);
			request.setIdUsuario(configuracion.getIdUsuario());
			// Añade al response el request de la operacion
			EntregaVentanillaStub.GetOrdenesRepresentanteResponse response = stub
					.getOrdenesRepresentante(request);
			// obtenemos valores
			arrOrd = response.get_return();
			if (arrOrd == null) {
				lstRepre = new ArrayList<EntregaVentanilla>();
				mensajeError = "No es Posilble Entregar Orden";
				//btnExporta = false;
			} else {
				//btnExporta = true;
				lstRepre = Arrays.asList(arrOrd);
				if(lstRepre.size() != 0){
					DetOrd =new EntregaVentanilla();
					DetOrd= lstRepre.get(0);
				}
				mensajeError = " ";
			}
		} catch (RemoteException ex) {
			// Utils.GuardarLogMensajeBD("General Admin", "M6",
			// "No se pudieron cargar representantes en el grid",
			// ex.getMessage(), configuracion.getIdUsuario());
			System.out.println(ex.getMessage());
		} finally {

		}
		return lstRepre;
	}

	/**
	 * Método que llena el grid de representantes a seleccionar
	 * 
	 * @author jose.ponce
	 * @since 25/01/2012
	 */
	public List<EntregaVentanilla> getLlenaRepresentantes() {
		EntregaVentanilla[] arrRepre = null;
		List<EntregaVentanilla> lstRepres = null;
		try {
			// Se crea el cliente
			EntregaVentanillaStub stub = new EntregaVentanillaStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Se agrega al request para la operacion a ejecutar
			EntregaVentanillaStub.GetRepresentantes request = new EntregaVentanillaStub.GetRepresentantes();
			// pasamos parametros
			request.setRegistro(registroRepre);
			request.setNombre(nombreRepre);
			request.setIdUsuario(configuracion.getIdUsuario());
			// request.setIdUsuario(configuracion.getIdUsuario());
			// Añade al response el request de la operacion
			EntregaVentanillaStub.GetRepresentantesResponse response = stub
					.getRepresentantes(request);
			// obtenemos valores
			arrRepre = response.get_return();
			if (arrRepre == null) {
				lstRepres = new ArrayList<EntregaVentanilla>();
			} else {
				lstRepres = Arrays.asList(arrRepre);
			}
		} catch (RemoteException ex) {
			// Utils.GuardarLogMensajeBD("General Admin", "M6",
			// "No se pudieron cargar representantes en el grid",
			// ex.getMessage(), configuracion.getIdUsuario());
			System.out.println(ex.getMessage());
		} finally {

		}
		return lstRepres;
	}
	
	public void obtenerRepresentantePorAccount(){
		
		EntregaVentanilla[] arrRepre = null;
		List<EntregaVentanilla> lstRepres = null;
		
		try {
			
			EntregaVentanillaStub stub = new EntregaVentanillaStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			EntregaVentanillaStub.GetRepresentantes request = new EntregaVentanillaStub.GetRepresentantes();
			request.setRegistro(registroRepre);
			request.setNombre(nombreRepre);
			request.setIdUsuario(configuracion.getIdUsuario());
			EntregaVentanillaStub.GetRepresentantesResponse response = stub.getRepresentantes(request);
			arrRepre = response.get_return();
			
			if (arrRepre != null) {
				lstRepres = new ArrayList<EntregaVentanilla>();
				lstRepres = Arrays.asList(arrRepre);
				if(lstRepres.size() == 1){
					representante = new EntregaVentanilla();
					representante = lstRepres.get(0);
				}
			}
			else{
				mensajeError= "No se encontró representante";
			}
		} catch (RemoteException ex) {
			// Utils.GuardarLogMensajeBD("General Admin", "M6",
			// "No se pudieron cargar representantes en el grid",
			// ex.getMessage(), configuracion.getIdUsuario());
			System.out.println(ex.getMessage());
		} finally {

		}
	}

	/**
	 * Método que llena el grid del detalle de la orden
	 * 
	 * @author jose.ponce
	 * @since 30/01/2012
	 */
	public List<EntregaVentanilla> getLlenaDetalleOrden() {
		EntregaVentanilla[] detOrden = null;
		// lstItems=null;
		try {
			// Se crea el cliente
			EntregaVentanillaStub stub = new EntregaVentanillaStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Se agrega al request para la operacion a ejecutar
			EntregaVentanillaStub.GetDetalleOrden request = new EntregaVentanillaStub.GetDetalleOrden();
			// pasamos parametros
			request.setIdOrden(idOrden);
			request.setIdUsuario(configuracion.getIdUsuario());
			// Añade al response el request de la operacion
			EntregaVentanillaStub.GetDetalleOrdenResponse response = stub
					.getDetalleOrden(request);
			// obtenemos valores
			detOrden = response.get_return();
			if (detOrden == null) {
				lstItems = new ArrayList<EntregaVentanilla>();
				// mensajeError="No es Posilble Entregar Orden";
			} else {
				if (bandera == true) {
					setLstItems(Arrays.asList(detOrden));
					// mensajeError=" ";
					if(lstItems.size() != 0){
						DetOrd =new EntregaVentanilla();
						DetOrd= lstItems.get(0);
					}
				}
			}
			// setLstItems(Arrays.asList(detOrden));
		} catch (RemoteException ex) {
			// Utils.GuardarLogMensajeBD("General Admin", "M6",
			// "No se pudieron cargar representantes en el grid",
			// ex.getMessage(), configuracion.getIdUsuario());
			System.out.println(ex.getMessage());
		} finally {

		}
		return getLstItems();
	}

	/**
	 * Método que llena el grid con los detalles de pago de la orden
	 * 
	 * @author jose.ponce
	 * @since 31/01/2012
	 */
	public List<EntregaVentanilla> getLlenaDetallePago() {

		try {
			if (detPago == null) {
				// Se crea el cliente
				EntregaVentanillaStub stub = new EntregaVentanillaStub();

				// Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(stub
						._getServiceClient().getOptions().getTo().getAddress());
				stub._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));

				// Se agrega al request para la operacion a ejecutar
				EntregaVentanillaStub.GetDetallePago request = new EntregaVentanillaStub.GetDetallePago();
				// pasamos parametros
				request.setIdOrden(idOrden);
				request.setIdUsuario(configuracion.getIdUsuario());
				// request.setIdUsuario(configuracion.getIdUsuario());
				// Añade al response el request de la operacion
				EntregaVentanillaStub.GetDetallePagoResponse response = stub
						.getDetallePago(request);
				// obtenemos valores
				detPago = response.get_return();
				setLstPagos(Arrays.asList(detPago));
				calculaPagos();
			}
		} catch (RemoteException ex) {
			// Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M17",
			// "No se pudieron cargar detalles de pago en el grid",
			// ex.getMessage(), configuracion.getIdUsuario());
			// gridPagDet=true;
			System.out.println(ex.getMessage());
		} finally {

		}
		return Arrays.asList(detPago);
	}

	/**
	 * Método que muestra el grid de representantes deacuerdo a los criterios de
	 * selección
	 * 
	 * @author jose.ponce
	 * @since 27/01/2012
	 */
	public String mostrarGridRepresentante() {

		String vacio = "";
		mensajeError = "";
		
		try {
			if(!(nombreRepre.trim().equals(vacio) && nombreRepre.trim().length() == 0)){
				frmModal.setRendered(true);
				setMostrarPanel(true);
				registroRepre = "";
			}
			else if (!registroRepre.trim().equals(vacio)) {
				frmModal.setRendered(true);
				obtenerRepresentantePorAccount();
				AsignaRepreSeleccionada();
				setMostrarPanel(false);
			} 
			
			//setTableRepre(false);
			/*
			 * if(nombreRepre.equals("") && registroRepre.equals("")){ //linea
			 * opcional *** prueba //this.frmModal.setRendered(false); }else{
			 * //getLlenaRepresentantes(); frmModal.setRendered(true); }
			 */
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 09/04/2012
	 * @param e
	 * 
	 */
	public void getRepresentantePorRegistro() {
		//UIInput account = FacesContext.getCurrentInstance().;
		//registroRepre = (String) account.getValue();
		//registroRepre = valor;
		mostrarGridRepresentante();
	}

	/**
	 * Método que asigna valores de la fila seleccionada del datable
	 * 
	 * @author jose.ponce
	 * @since 25/01/2012
	 */
	public String LlenaValores() {
		try {
			cveOrden = String.valueOf(entrega.getOrden());
			campania = String.valueOf(entrega.getCampania());
			statusOrden = String.valueOf(entrega.getEstatus_reparto());
			idOrden = Integer.valueOf(entrega.getId_orden());
			setIdCampania(Integer.valueOf(entrega.getId_campania()));
			//montoAcobrar = Double.valueOf(representante.getMonto_previo());
			//montoAcobrar = Double.valueOf(DetOrd.getMonto_previo());
			montoAcobrar = Double.valueOf(entrega.getMonto_previo());
			//getLlenaDetalleOrden();
			gridOrdDet = false;
			gridPagDet = false;
			//MONTO = "$ " + String.valueOf(representante.getMonto_previo());
			//MONTO = "$ " + String.valueOf(DetOrd.getMonto_previo());
			MONTO = "$ " + String.valueOf(entrega.getMonto_previo());
			//montoDePago = representante.getMonto_previo();
			//montoDePago = DetOrd.getMonto_previo();
			montoDePago = entrega.getMonto_previo();
			btnEscan = true;
			btnPago = true;
			btnEntrega = true;
			btnConsulta = false;
			mostrarPanel=false;
			// CalculaPagos();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}

	/**
	 * Método que calcula el remanente deacuerdo a los pagos
	 * 
	 * @author jose.ponce
	 * @since 01/02/2012
	 */
	public void calculaPagos() {
		 
		double montoPagadoLocal = 0.0;
		
		try {
			for (int i = 0; i < getLstPagos().size(); i++) {
				montoPagadoLocal +=  getLstPagos().get(i).getMonto_pagado();
			}
			
			montoPagado = montoPagadoLocal;
			if (montoPagado <= montoAcobrar) {
				setMontoRemanente(montoAcobrar - montoPagado);
				montoRemanenteS = new DecimalFormat("#.##").format(montoRemanente);
				montoDePago = 0.0;
			} else {
				mensajeError = "El monto a pagar excede el monto a cobrar";
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Método que agrega los pagos ingresados por el usuario
	 * 
	 * @author jose.ponce
	 * @since 01/02/2012
	 */
	public String AgregaPagos() {
		try {

			mensajeError = "";
			
			if (montoDePago <= montoAcobrar) {
				if(montoDePago > 0 && fechaPago !=""){
				EntregaVentanilla nuevoPago = null;
				
				if (idTipoPago == 1) {// Banco
					if (idBanco != -1 && idBanco !=0) {
						nuevoPago = new EntregaVentanilla();
						nuevoPago.setTipo_pago("Banco");
						nuevoPago.setId_banco(idBanco);
						nuevoPago.setBanco(nomBanco);
						nuevoPago.setFolios("");
						nuevoPago.setFecha_pago(fechaPago);
						nuevoPago.setMonto_pagado(Double.valueOf(montoDePago));
						nuevoPago.setId_tipo_pago(idTipoPago);
					}else{
						mensajeError="Seleccione Banco";
					}
				}
				if (idTipoPago == 2) {// Recibo de Cobranza
					if(txtFolioCobranza.trim() != ""){
						nuevoPago = new EntregaVentanilla();
						nuevoPago.setTipo_pago("Recibo de Cobranza");
						nuevoPago.setId_banco(0);
						nuevoPago.setBanco("");
						nuevoPago.setFolios(txtFolioCobranza);
						nuevoPago.setFecha_pago(fechaPago);
						nuevoPago.setMonto_pagado(Double.valueOf(montoDePago));
						nuevoPago.setId_tipo_pago(idTipoPago);
						//getLstPagos().add(nuevoPago);
					}else{
						mensajeError="Ingrese Folio de Cobranza";
					}
				}
				if (idTipoPago == 3) {// Pago en Efectivo
					nuevoPago = new EntregaVentanilla();
					nuevoPago.setTipo_pago("Pago en efectivo");
					nuevoPago.setId_banco(0);
					nuevoPago.setBanco("");
					nuevoPago.setFolios("");
					nuevoPago.setFecha_pago(fechaPago);
					nuevoPago.setMonto_pagado(Double.valueOf(montoDePago));
					nuevoPago.setId_tipo_pago(idTipoPago);
					//getLstPagos().add(nuevoPago);
				}
				
				if(nuevoPago!=null){
					//if(getLstPagos().size() >= 1){
						getLstPagos().add(nuevoPago);
						calculaPagos();
						if(montoPagado > montoAcobrar){
							getLstPagos().remove(getLstPagos().size()-1);
							calculaPagos(); 
						}	
					//}
					
					/*else{
						getLstPagos().add(nuevoPago);
					}*/	
				}
			}else{
				mensajeError = "Ingrese datos completos";
			}
			}
			else {
				mensajeError = "El monto a pagar excede el monto a cobrar";
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}

	/**
	 * Método que asigna valores a las respectivas cajas de texto deacuerdo a la
	 * representante selecciona del modal popup.
	 * 
	 * @author jose.ponce
	 * @since 30/01/2012
	 */
	public String AsignaRepreSeleccionada() {
		try {
			nombreRepre = String.valueOf(representante.getNombre());
			registroRepre = String.valueOf(representante.getRegistro());
			domicilio = String.valueOf(representante.getDomicilio());
			zona = String.valueOf(representante.getZona());
			idZona = Integer.valueOf(representante.getId_zona());
			tableRepre = false;
			btnExporta= true;
			setMostrarPanel(false);
			getRepreEntrega();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}

	/**
	 * Método que elimina el pago seleccionado
	 * 
	 * @author jose.ponce
	 * @since 01/02/2012
	 */
	public String eliminarPago() {
		try {
			getLstPagos().remove(pagoActual);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		calculaPagos();
		return "";
	}

	/**
	 * Método para validar items escaneados
	 * 
	 * @author jose.ponce
	 * @since 31/01/2012
	 */
	public String ItemsEscaneados() 
	{
		try {
			codigoBarras = utils.ModificaCodigoBarras(codigoBarras);
			boolean codigoCoincide = false;
			for (int i = 0; i < getLstItems().size(); i++) 
			{
				//Si el registro es diferente de 1 (caja) y la longitud es de 13
				if (codigoBarras.length() == 13 && !getLstItems().get(i).getTipo_item().equals("CAJA"))
				{
					//Validación contra EAN13
					if (getLstItems().get(i).getCodigoEAN13().equals(codigoBarras)
							&& getLstItems().get(i).getEscaneados().equals("No"))
					{
						codigoCoincide = true;
					}
				}
				//Validación contra CODIGO_BARRAS
				else if (getLstItems().get(i).getCodigo_barras().equals(codigoBarras) 
						&& getLstItems().get(i).getEscaneados().equals("No")) 
				{
					codigoCoincide = true;
				}
				//Validación contra FSC
				else if (getLstItems().get(i).getCodigoFSC().equals(codigoBarras)
						&& getLstItems().get(i).getEscaneados().equals("No")
						&& !getLstItems().get(i).getTipo_item().equals("CAJA"))
				{
					codigoCoincide = true;
				}
				//Si no hay coincidencías.
				else 
				{
					setRutaImagen("/img/semaforo/tache.gif");
					codigoCoincide = false;
				}
				
				//Si existió alguna coincidencia
				if (codigoCoincide)
				{
					getLstItems().get(i).setEscaneados("Si");
					getLstItems().get(i).setTotal_item(1);
					getLstItems().get(i).setEstatus_item(
							"Entrega en Ventanilla");
					setRutaImagen("/img/semaforo/palomita.gif");
					bandera = false;
					//Sale del método.
					break;
				}
			}
		} 
		catch (Exception ex)
		{
			System.out.println(ex.toString());
		}
		codigoBarras = "";
		return "";
	}

	/**
	 * Método que guarda la orden entregada en ventanilla
	 * 
	 * @author jose.ponce
	 * @since 03/02/2012
	 */
	public String GuardaEntrega() {
		try {
			if((idPersonaRecibe !=0) && (idTipoPago !=0 && getLstPagos().size() !=0 || montoAcobrar ==0)){
				ActualizaItem();
				InsertaPago();
				limpiar();
				// Se envía segunda liquidación
				Utils utils = new Utils();
				mensajeError += utils.enviarPrimeraSegundaLiquidacion(2);
				mensajeError = "Orden Entregada Exitosamente";
				return null;
			}else{
				mensajeError ="Ingrese datos para poder entregar orden";
				return null;
			}
		} catch (Exception ex) {
			 Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M17",
			 "No se pudo guardar entrega en ventanilla",
			 ex.getMessage(), configuracion.getIdUsuario());
			return null;
		}
	}

	/**
	 * Método que limpia los controles de la página
	 * 
	 * @author jose.ponce
	 * @since 21/02/2012
	 */
	public String limpiar() {
		//lstItems = new ArrayList<EntregaVentanilla>();
		lstPagos = new ArrayList<EntregaVentanilla>();
		cveOrden = "";
		nombreRepre = "";
		registroRepre = "";
		domicilio = "";
		zona = "";
		idZona = 0;
		campania = "";
		statusOrden = "";
		//idOrden = 0;
		montoAcobrar = 0.0;
		montoRemanente = 0.0;
		montoRemanenteS = "0.0";
		MONTO = "$ ";
		setRutaImagen("");
		idBanco = 0;
		montoDePago = 0.0;
		idTipoPago = 0;
		fechaPago = "";
		txtFolioCobranza = "";
		txtOtroRecibe = "";
		txtComentarios = "";
		idPersonaRecibe = 0;
		btnConsulta = true;
		btnExporta = false;
		tableRepre = true;
		representante = null;
		mensajeError = "";
		btnEscan = false;
		btnPago = false;
		btnEntrega = false;
		btnExporta = false;
		btnConsulta = true;
		gridOrdDet = true;
		bandera=true;
		return null;
	}

	/**
	 * Método que ejecuta WS de actualización de items.
	 * 
	 * @author jose.ponce
	 * @since 02/02/2012
	 */
	public void ActualizaItem() {
		try {
			// Se crea el cliente
			EntregaVentanillaStub stub = new EntregaVentanillaStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Se agrega al request para la operacion a ejecutar
			EntregaVentanillaStub.EntregaVentanillaE request = new EntregaVentanillaStub.EntregaVentanillaE();
			// pasamos parametros
			for (int i = 0; i < getLstItems().size(); i++) {
				if (getLstItems().get(i).getEscaneados() == "Si") {
					request.setId_Orden(idOrden);
					request.setId_Item(getLstItems().get(i).getId_item());
					request.setUsuario(configuracion.getUsuario());
					request.setId_Campania(getIdCampania());
					request.setId_Zona(getIdZona());
					request.setId_Usuario(configuracion.getIdUsuario());
					request.setId_Persona_Recibe(idPersonaRecibe);
					request.setPersona_Recibe(txtOtroRecibe);
					request.setComentarios(txtComentarios);
					// Añade al response el request de la operacion
					@SuppressWarnings("unused")
					EntregaVentanillaStub.EntregaVentanillaResponse response = stub
							.entregaVentanilla(request);
				}
			}

		} catch (RemoteException ex) {
			// Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M17",
			// "No se pudieron cargar detalles de pago en el grid",
			// ex.getMessage(), configuracion.getIdUsuario());
			System.out.println(ex.getMessage());
		} finally {

		}
	}

	/**
	 * Método que ejecuta WS para insertar pagos correspondientes a orden
	 * 
	 * @author jose.ponce
	 * @since 02/02/2012
	 */
	public void InsertaPago() {
		try {
			
			// Se crea el cliente
			EntregaVentanillaStub stub = new EntregaVentanillaStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Se agrega al request para la operacion a ejecutar
			EntregaVentanillaStub.PagoVentanilla request = new EntregaVentanillaStub.PagoVentanilla();
				// pasamos parametros
			for (int i = 0; i < getLstPagos().size(); i++) {
				request.setId_Orden(idOrden);
				request.setId_Campania(getIdCampania());
				request.setId_Zona(getIdZona());
				//request.setId_Banco(idBanco);
				request.setId_Banco(Integer.valueOf(getLstPagos().get(i).getId_banco()));
				//request.setId_Tipo_Pago(idTipoPago);
				request.setId_Tipo_Pago(Integer.valueOf(getLstPagos().get(i).getId_tipo_pago()));
				//request.setMonto(String.valueOf(montoDePago));
				request.setMonto(String.valueOf(getLstPagos().get(i).getMonto_pagado()));
				//request.setFolios(txtFolioCobranza);
				request.setFolios(String.valueOf(getLstPagos().get(i).getFolios()));
				//request.setFecha(fechaPago);
				request.setFecha(String.valueOf(getLstPagos().get(i).getFecha_pago()));
				request.setUsuario(configuracion.getUsuario());
				request.setId_Usuario(configuracion.getIdUsuario());
				// Añade al response el request de la operacion
				@SuppressWarnings("unused")
				EntregaVentanillaStub.PagoVentanillaResponse response = stub
						.pagoVentanilla(request);
			}

		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M18", "Excepción Insertar pagos.", ex.getMessage(), configuracion.getIdUsuario());
			//System.out.println(ex.getMessage());
		} finally {

		}
	}

	/**
	 * Método para exportar grids a formato de excel
	 * 
	 * @author jose.ponce
	 * @since 01/03/2012
	 */
	public String ExportaExcel() throws IOException {
		try {
			List<EntregaVentanilla> lstTest = null;
			lstTest = getRepreEntrega();
			for (int i = 0; i < lstTest.size(); i++) {
				lstTest.get(i).setNombre(nombreRepre);
				lstTest.get(i).setRegistro(registroRepre);
				lstTest.get(i).setZona(zona);
				lstTest.get(i).setDomicilio(domicilio);
			}
			JasperGenerator genera = new JasperGenerator();
			genera.generaRptEntVentanilla(lstTest, "XLS");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}

	/**
	 * Método para exportar grids a formato pdf
	 * 
	 * @author jose.ponce
	 * @since 01/03/2012
	 */
	public String ExportaPdf() throws IOException {
		try {
			List<EntregaVentanilla> lstTest = null;
			lstTest = getRepreEntrega();
			for (int i = 0; i < lstTest.size(); i++) {
				lstTest.get(i).setNombre(nombreRepre);
				lstTest.get(i).setRegistro(registroRepre);
				lstTest.get(i).setZona(zona);
				lstTest.get(i).setDomicilio(domicilio);
			}
			JasperGenerator genera = new JasperGenerator();
			genera.generaRptEntVentanilla(lstTest, "PDF");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}

	/**
	 * Método para exportar grids a formato csv
	 * 
	 * @author jose.ponce
	 * @since 01/03/2012
	 */
	public String ExportaCsv() throws IOException {
		try {
			List<EntregaVentanilla> lstTest = null;
			lstTest = getRepreEntrega();
			for (int i = 0; i < lstTest.size(); i++) {
				lstTest.get(i).setNombre(nombreRepre);
				lstTest.get(i).setRegistro(registroRepre);
				lstTest.get(i).setZona(zona);
				lstTest.get(i).setDomicilio(domicilio);
			}
			JasperGenerator genera = new JasperGenerator();
			genera.generaRptEntVentanilla(lstTest, "CSV");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}
	
	private String eliminaCero(String account){
		String cb;
		cb= account.replaceFirst("^0*", "");
		return cb.trim();
	}

	/*
	 * *********************** Encapsulamiento *****************************
	 */
	public Integer getIdTipoPago() {
		return idTipoPago;
	}

	public void setIdTipoPago(Integer idTipoPago) {
		this.idTipoPago = idTipoPago;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public String getNomBanco() {
		return nomBanco;
	}

	public void setNomBanco(String nomBanco) {
		this.nomBanco = nomBanco;
	}

	public Boolean getCmbBanco() {
		return cmbBanco;
	}

	public void setCmbBanco(Boolean cmbBanco) {
		this.cmbBanco = cmbBanco;
	}

	public Boolean getTxtFolioCob() {
		return txtFolioCob;
	}

	public void setTxtFolioCob(Boolean txtFolioCob) {
		this.txtFolioCob = txtFolioCob;
	}

	public EntregaVentanilla getEntrega() {
		return entrega;
	}

	public void setEntrega(EntregaVentanilla entrega) {
		this.entrega = entrega;
	}

	public String getCveOrden() {
		return cveOrden;
	}

	public void setCveOrden(String cveOrden) {
		this.cveOrden = cveOrden;
	}

	public String getNombreRepre() {
		return nombreRepre;
	}

	public void setNombreRepre(String nombreRepre) {
		this.nombreRepre = nombreRepre;
	}

	public String getRegistroRepre() {
		return registroRepre;
	}

	public void setRegistroRepre(String registroRepre) {
		this.registroRepre = eliminaCero(registroRepre);
	}

	public String getCampania() {
		return campania;
	}

	public void setCampania(String campania) {
		this.campania = campania;
	}

	public String getStatusOrden() {
		return statusOrden;
	}

	public void setStatusOrden(String statusOrden) {
		this.statusOrden = statusOrden;
	}

	public UIForm getFrmModal() {
		return frmModal;
	}

	public void setFrmModal(UIForm frmModal) {
		this.frmModal = frmModal;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public EntregaVentanilla getRepresentante() {
		return representante;
	}

	public void setRepresentante(EntregaVentanilla representante) {
		this.representante = representante;
	}

	public UIForm getFrmPrincipal() {
		return frmPrincipal;
	}

	public void setFrmPrincipal(UIForm frmPrincipal) {
		this.frmPrincipal = frmPrincipal;
	}

	public Boolean getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(Boolean btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public Boolean getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(Boolean btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public Boolean getTableRepre() {
		return tableRepre;
	}

	public void setTableRepre(Boolean tableRepre) {
		this.tableRepre = tableRepre;
	}

	public String getTxtFolioCobranza() {
		return txtFolioCobranza;
	}

	public void setTxtFolioCobranza(String txtFolioCobranza) {
		this.txtFolioCobranza = txtFolioCobranza;
	}

	public int getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}

	public Boolean getGridOrdDet() {
		return gridOrdDet;
	}

	public void setGridOrdDet(Boolean gridOrdDet) {
		this.gridOrdDet = gridOrdDet;
	}

	public Double getMontoAcobrar() {
		return montoAcobrar;
	}

	public void setMontoAcobrar(Double montoAcobrar) {
		this.montoAcobrar = montoAcobrar;
	}

	public String getMONTO() {
		return MONTO;
	}

	public void setMONTO(String mONTO) {
		MONTO = mONTO;
	}

	public Boolean getGridPagDet() {
		return gridPagDet;
	}

	public void setGridPagDet(Boolean gridPagDet) {
		this.gridPagDet = gridPagDet;
	}

	public List<EntregaVentanilla> getLstItems() {
		return lstItems;
	}

	public void setLstItems(List<EntregaVentanilla> lstItems) {
		this.lstItems = lstItems;
	}

	public List<EntregaVentanilla> getLstPagos() {
		return lstPagos;
	}

	public void setLstPagos(List<EntregaVentanilla> lstPagos) {
		this.lstPagos = lstPagos;
	}

	public Double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public Double getMontoRemanente() {
		return montoRemanente;
	}

	public void setMontoRemanente(Double montoRemanente) {
		this.montoRemanente = montoRemanente;
	}

	public String getMontoRemanenteS() {
		return montoRemanenteS;
	}

	public void setMontoRemanenteS(String montoRemanenteS) {
		this.montoRemanenteS = montoRemanenteS;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
	//Inicia Nuevos Codigos 
	public String getCodigoFSC() {
		return codigoFSC;
	}

	public void setCodigoFSC(String codigoBarras) {
		this.codigoFSC = codigoBarras;
	}
	
	public String getCodigoEAN13() {
		return codigoEAN13;
	}

	public void setCodigoEAN13(String codigoBarras) {
		this.codigoEAN13 = codigoBarras;
	}
	//Finaliza Nuevos Codigos

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public EntregaVentanilla getPagoActual() {
		return pagoActual;
	}

	public void setPagoActual(EntregaVentanilla pagoActual) {
		this.pagoActual = pagoActual;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Double getMontoDePago() {
		return montoDePago;
	}

	public void setMontoDePago(Double montoDePago) {
		this.montoDePago = montoDePago;
	}

	public int getIdZona() {
		return idZona;
	}

	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}

	public int getIdCampania() {
		return idCampania;
	}

	public void setIdCampania(int idCampania) {
		this.idCampania = idCampania;
	}

	public List<SelectItem> getListBanco() {
		return listBanco;
	}

	public void setListBanco(List<SelectItem> listBanco) {
		this.listBanco = listBanco;
	}

	public UIForm getFrmExportar() {
		return frmExportar;
	}

	public void setFrmExportar(UIForm frmExportar) {
		this.frmExportar = frmExportar;
	}

	public Boolean getBtnConsulta() {
		return btnConsulta;
	}

	public void setBtnConsulta(Boolean btnConsulta) {
		this.btnConsulta = btnConsulta;
	}

	public Boolean getBtnEscan() {
		return btnEscan;
	}

	public void setBtnEscan(Boolean btnEscan) {
		this.btnEscan = btnEscan;
	}

	public Boolean getBtnPago() {
		return btnPago;
	}

	public void setBtnPago(Boolean btnPago) {
		this.btnPago = btnPago;
	}

	public Boolean getBtnEntrega() {
		return btnEntrega;
	}

	public void setBtnEntrega(Boolean btnEntrega) {
		this.btnEntrega = btnEntrega;
	}

	public Boolean getBtnExporta() {
		return btnExporta;
	}

	public void setBtnExporta(Boolean btnExporta) {
		this.btnExporta = btnExporta;
	}

	public PersonaRecibe[] getPerRec() {
		return perRec;
	}

	public void setPerRec(PersonaRecibe[] perRec) {
		this.perRec = perRec;
	}

	public Integer getIdPersonaRecibe() {
		return idPersonaRecibe;
	}

	public void setIdPersonaRecibe(Integer idPersonaRecibe) {
		this.idPersonaRecibe = idPersonaRecibe;
	}

	public Boolean getTxtOtros() {
		return txtOtros;
	}

	public void setTxtOtros(Boolean txtOtros) {
		this.txtOtros = txtOtros;
	}

	public Boolean getLblOtros() {
		return lblOtros;
	}

	public void setLblOtros(Boolean lblOtros) {
		this.lblOtros = lblOtros;
	}

	public String getTxtOtroRecibe() {
		return txtOtroRecibe;
	}

	public void setTxtOtroRecibe(String txtOtroRecibe) {
		this.txtOtroRecibe = txtOtroRecibe;
	}

	public String getTxtComentarios() {
		return txtComentarios;
	}

	public void setTxtComentarios(String txtComentarios) {
		this.txtComentarios = txtComentarios;
	}

	/**
	 * @return the mostrarpanel
	 */
	public Boolean getMostrarPanel() {
		return mostrarPanel;
	}

	/**
	 * @param mostrarpanel
	 *            the mostrarpanel to set
	 */
	public void setMostrarPanel(Boolean mostrarPanel) {
		this.mostrarPanel = mostrarPanel;
	}

	public EntregaVentanilla getDetOrd() {
		return DetOrd;
	}

	public void setDetOrd(EntregaVentanilla detOrd) {
		DetOrd = detOrd;
	}

}
