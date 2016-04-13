/**
 * Contiene las operaciones necesarias para la funcionalidad del CU 02-  Recepcion de VEAS
 */
package com.datacode.avon_ots_admin.cu002;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.axis2.AxisFault;

import com.datacode.avon_ots_admin.model.RecepcionVeasREACO;
import com.datacode.avon_ots_admin.model.RecepcionVeasREACODetalle;
import com.datacode.avon_ots_admin.model.Zona;
import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.CampaniaControllerStub;
import com.datacode.avon_ots_ws.CampaniaControllerStub.Campania;
import com.datacode.avon_ots_ws.RecepcionVeasControllerStub;
import com.datacode.avon_ots_ws.ZonaControllerStub;

/**
 * @author brenda.estrada
 * @since 29-12-2011 Description : Clase que interactua con el WS y regresando
 *        valores a la vista
 * 
 */
public class ControllerCU002RecepcionVEAS implements Serializable {

	private Integer idUser;
	private Integer idZona;
	private String gerenteZona;
	private String descZona;
	private Integer idCampania;
	private String descCampania;
	private HashMap<String, String> gerentesZona;
	private String uRecibio;
	private String descDetalle;
	private String fEntrega;
	private byte noCajas;
	private Integer totalNoCajas;

	private UIForm form;
	private UIForm tableForm;
	private UICommand btnGuardar;
	private UICommand lnkGenerarCodigosBarras;

	private String msg;
	private List<Zona> zonas;
	private List<RecepcionVeasREACODetalle> detalles;
	private RecepcionVeasREACODetalle detalle;
	private Campania[] camps;
	private ZonaControllerStub.Zona[] zons;
	private boolean showTable;
	private Configuracion configuracion;
	private Utils utils;
	private long idGeneracionCodigos;
	private String activarGeneracion = "0";

	public ControllerCU002RecepcionVEAS() {

		configuracion = (Configuracion) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("configuracion");
		idUser = configuracion.getIdUsuario();
		uRecibio = configuracion.getUsuario();
		idZona = 0;
		idCampania = 0;
		noCajas = 0;
		totalNoCajas = 0;
		this.detalles = new ArrayList<RecepcionVeasREACODetalle>();
		this.fEntrega = Utils.ObtenerFechaActual(Utils.formatoFechaCorta);
		utils = new Utils();
		showTable = false;

	}

	/**
	 * @author javier.gallegos
	 * @since 19-01-2012
	 * @return metodo que baja de sesion el mapa de acciones y establece las
	 *         propias del modulo en la variable mapaAccion
	 */
	public HashMap<String, Boolean> getMapaAccion() {
		HashMap<String, Boolean> mapa = new HashMap<String, Boolean>();
		HashMap<Integer, HashMap<String, Boolean>> mapaFull = (HashMap<Integer, HashMap<String, Boolean>>) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("accionesM");
		mapa = mapaFull.get(6);// el 1 es el id del modulo hay que ver como
								// hacerlo dinamico
		return mapa;
	}

	/*
	 * ****************************** Metodos que cargan datos a los Combos
	 * ************************************
	 */

	/**
	 * Crea una lista de tipo SelectItem - Datos obtenidos al invocar WS de
	 * Campanias
	 * 
	 * @author brenda.estrada
	 * @since 29/12/2011
	 * @return List tipo Campanias
	 */
	public List<SelectItem> getCampanias() {

		List<SelectItem> list = new ArrayList<SelectItem>();
		camps = utils.getCampanias("CUADMIN002_01_02",
				configuracion.getIdLDC(), getIdZona(), 0);
		list.add(new SelectItem(0, "Selecciona una opción"));

		try {
			if (camps != null) {
				for (int o = 0; o < camps.length; o++) {
					list.add(new SelectItem(camps[o].getIdCampania(), camps[o]
							.getCampania() + "-" + camps[o].getAnioCampania()));
				}
			}
		} catch (NullPointerException e) {
			msg = Utils.ObtenerMensajeBD("General Admin", "M1", true,
					"No se pudieron cargar las Campañas correctamente",
					e.getMessage(), idUser)[0];
		}
		return list;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 29/12/2011
	 * @return Lista tipo Zonas
	 */
	public List<SelectItem> getCmbZonas() {

		List<SelectItem> listZonas = new ArrayList<SelectItem>();
		gerentesZona = new HashMap<String, String>();

		zons = utils.getZonas(configuracion.getIdLDC(), "CUADMIN002_01_02");
		listZonas.add(new SelectItem(0, "Selecciona una opción"));

		try {
			for (int i = 0; i < zons.length; i++) {
				listZonas.add(new SelectItem(zons[i].getIdZona(), zons[i]
						.getZona()));
				gerentesZona.put(zons[i].getIdZona(),
						zons[i].getNombreGerente());
			}
		} catch (NullPointerException e) {
			msg = Utils.ObtenerMensajeBD("General Admin", "M2", true,
					"No se pudieron cargar las Zonas correctamente",
					e.getMessage(), idUser)[0];
		}
		return listZonas;
	}

	public void getNombreGerente() {

		this.gerenteZona = this.gerentesZona.get(String.valueOf(idZona));
		setActivarGeneracion("0");
		msg = "";
		getCampanias();
	}

	/*
	 * public void getNombreGerente(ValueChangeEvent e) { String idZona =
	 * e.getNewValue().toString(); this.gerenteZona =
	 * this.gerentesZona.get(idZona); setActivarGeneracion("0"); msg = ""; }
	 */

	public void limpiarMensaje() {
		msg = "";
	}

	/**
	 * Se inicializan los componentes de la pantalla
	 * 
	 * @return null en los componentes
	 */
	public String nuevoDetalleRecepcionVEAS() {

		/*
		 * this.descDetalle = null; this.noCajas = 0; this.msg = "";
		 */
		return null;
	}

	/**
	 * Inserta un registro al Table de Detalle de Recepcion de VEAS
	 * 
	 * @author brenda.estrada
	 * @since 29/12/2011
	 * @return Registro al DataTable
	 */
	public String insertarDetalleRecepcionVEAS() {

		String codigoBarras = generarCodigoBarras();
		this.detalle = new RecepcionVeasREACODetalle();
		this.detalle.setDescDetalle(getDescDetalle());
		this.detalle.setCodigoBarras(codigoBarras);
		this.detalle.setuEntrego(getGerenteZona());
		this.detalle.setuRecibio(getGerenteZona());
		this.detalle.setUnidad(1);
		this.detalle.setNoCajas(getNoCajas());
		this.detalles.add(detalle);
		this.detalle = null;
		this.descDetalle = null;
		this.noCajas = 0;
		setTotalNoCajas(obtenerTotalCajas());
		setActivarGeneracion("0");
		setMsg("");
		showTable = true;
		btnGuardar.setRendered(true);
		return null;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 01/01/2012
	 * @return
	 * 
	 */

	public String generarCodigoBarras() {

		String codigoBarras = null;
		String campania = obtenerAnioCampania();
		String zona = obtenerZona();
		byte consecutivoAnterior = obtenerUltimoConsecutivoCodigoBarras();
		codigoBarras = Utils.GenerarRangoCodigoBarras("VEA", campania, "",
				zona, this.getNoCajas(), consecutivoAnterior);
		return codigoBarras;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 01/01/2012
	 * @return
	 * 
	 */
	public byte obtenerUltimoConsecutivoCodigoBarras() {

		byte consecutivoAnterior = 0;
		String codigoBarras = null;

		if (this.getDetalles().size() > 0) {
			codigoBarras = this.detalles.get(this.getDetalles().size() - 1)
					.getCodigoBarras();
			codigoBarras = codigoBarras.substring(codigoBarras.length() - 2);
			consecutivoAnterior = Byte.parseByte(codigoBarras);
		}

		return consecutivoAnterior;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 01/01/2012
	 * @return
	 * 
	 */
	public String obtenerAnioCampania() {
		String anioCampania = null;
		for (int i = 0; i < camps.length; i++) {
			if (camps[i].getIdCampania() == getIdCampania()) {
				anioCampania = String.valueOf(camps[i].getCampania())
						+ String.valueOf(camps[i].getAnioCampania());
			}
		}

		return anioCampania;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 01/01/2012
	 * @return
	 * 
	 */
	public String obtenerZona() {
		String zona = null;
		for (int i = 0; i < zons.length; i++) {
			if (zons[i].getIdZona().equals(String.valueOf(getIdZona()))) {
				zona = String.valueOf(zons[i].getZona());
			}
		}
		return zona;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 01/01/2012
	 * @return
	 * 
	 */
	public int obtenerTotalCajas() {
		int totalCajas = 0;

		if (this.getDetalles().size() > 0) {
			for (RecepcionVeasREACODetalle detalle : this.getDetalles()) {
				totalCajas += detalle.getNoCajas();
			}
		}
		return totalCajas;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 01/01/2012
	 * @return
	 * 
	 */
	public String guardarRecepcionesVEAS() {

		if (detalles != null && detalles.size() > 0) {
			String idRecepcionVeas = guardarRecepcionVEAS();
			guardarRecepcionVeasDetalle(idRecepcionVeas);

			setIdGeneracionCodigos(Long.parseLong(idRecepcionVeas));
			setActivarGeneracion("1");
			this.lnkGenerarCodigosBarras.setRendered(true);

			detalles.clear();
			totalNoCajas = null;
			msg = "Recepciones VEAS guardadas exitosamente";
			// btnGuardar.setRendered(false);

		} else {

			setActivarGeneracion("0");
			msg = "No hay recepciones veas que guardar";
		}
		// this.lnkGenerarCodigosBarras.setRendered(false);
		showTable = false;
		return msg;
	}

	/**
	 * Recibe los parametros y realiza la insercion mediante el WS
	 * 
	 * @author jessica.leon
	 * @since 29/12/2011
	 * @return String de Exito o Falla al invocar el WS
	 */
	public String guardarRecepcionVEAS() {

		String idRecepcionVeas = null;
		RecepcionVeasControllerStub customer = null;
		RecepcionVeasControllerStub.InsertarRecepcionVeas requestRecepcionVeas = null;
		RecepcionVeasControllerStub.InsertarRecepcionVeasResponse responseRecepcionVeas = null;

		try {
			customer = new RecepcionVeasControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(customer
					._getServiceClient().getOptions().getTo().getAddress());
			customer._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			requestRecepcionVeas = new RecepcionVeasControllerStub.InsertarRecepcionVeas();
			requestRecepcionVeas.setIdCampania(getIdCampania());
			requestRecepcionVeas.setIdZona(getIdZona());
			requestRecepcionVeas.setIdUsuario(getIdUser());
			requestRecepcionVeas.setTipoCU("CUADMIN002.01.02");
			responseRecepcionVeas = customer
					.insertarRecepcionVeas(requestRecepcionVeas);
			idRecepcionVeas = responseRecepcionVeas.get_return();

			String respuesta = responseRecepcionVeas.get_return();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idRecepcionVeas;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 01/01/2012
	 * @param idRecepcionVeasReaco
	 * 
	 */
	public void guardarRecepcionVeasDetalle(String idRecepcionVeasReaco) {

		List<RecepcionVeasREACODetalle> recepcionVeasDetalles = new ArrayList<RecepcionVeasREACODetalle>();
		RecepcionVeasControllerStub customer = null;
		RecepcionVeasControllerStub.InsertarRecepcionVeaDetalle requestRecepcionVeas = null;
		RecepcionVeasControllerStub.InsertarRecepcionVeaDetalleResponse responseRecepcionVeas = null;
		String mensaje = null;

		for (RecepcionVeasREACODetalle detalle : this.getDetalles()) {
			generarDetalleRecepcionVea(detalle, recepcionVeasDetalles);
		}

		for (RecepcionVeasREACODetalle detalle : recepcionVeasDetalles) {

			try {
				customer = new RecepcionVeasControllerStub();

				// Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(customer
						._getServiceClient().getOptions().getTo().getAddress());
				customer._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));

				requestRecepcionVeas = new RecepcionVeasControllerStub.InsertarRecepcionVeaDetalle();
				requestRecepcionVeas.setIdRecepcionVeas(idRecepcionVeasReaco);
				requestRecepcionVeas.setDescripcion(detalle.getDescDetalle());
				requestRecepcionVeas.setCodigoBarras(detalle.getCodigoBarras());
				requestRecepcionVeas.setEntrego(detalle.getuEntrego());
				requestRecepcionVeas.setRecibio(detalle.getuRecibio());
				requestRecepcionVeas.setIdEstatus(20);
				requestRecepcionVeas.setIdUsuario(getIdUser());
				requestRecepcionVeas.setTipoCU("CUADMIN002.01.02");

				responseRecepcionVeas = customer
						.insertarRecepcionVeaDetalle(requestRecepcionVeas);
				msg = responseRecepcionVeas.get_return();

			} catch (AxisFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 01/01/2012
	 * @param detalle
	 * @param recepcionVeasDetalles
	 * 
	 */
	public void generarDetalleRecepcionVea(RecepcionVeasREACODetalle detalle,
			List<RecepcionVeasREACODetalle> recepcionVeasDetalles) {

		int noCajas = detalle.getNoCajas();
		RecepcionVeasREACODetalle detalleUnitario = null;
		int ultimoConsecutivo = Integer.parseInt(detalle.getCodigoBarras()
				.substring(detalle.getCodigoBarras().length() - 2));
		int consecutivoInicial = ultimoConsecutivo - (noCajas - 1);

		for (int i = 0; i < noCajas; i++) {
			detalleUnitario = new RecepcionVeasREACODetalle();
			detalleUnitario.setDescDetalle(detalle.getDescDetalle());
			detalleUnitario
					.setCodigoBarras(generarCodigoPorDetalle(consecutivoInicial));
			detalleUnitario.setuEntrego(getGerenteZona());
			detalleUnitario.setuRecibio(getGerenteZona());
			recepcionVeasDetalles.add(detalleUnitario);
			consecutivoInicial++;
		}
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 01/01/2012
	 * @param noCaja
	 * @return
	 * 
	 */
	public String generarCodigoPorDetalle(int noCaja) {
		String codigoBarras = null;
		String campania = obtenerAnioCampania();
		String zona = obtenerZona();
		DecimalFormat formatoNoCaja = new DecimalFormat("00");
		String numeroCaja = formatoNoCaja.format(noCaja);
		codigoBarras = "VEA" + campania + zona + numeroCaja;
		return codigoBarras;
	}

	/**
	 * Elimina el registro seleccionado del Datatable
	 * 
	 * @return String de Exito Falla
	 */
	public String eliminarDetalleRecepcion() {

		setActivarGeneracion("0");
		detalles.remove(detalle);
		setTotalNoCajas(obtenerTotalCajas());
		if (detalles.size() == 0) {
			showTable = false;
			// btnGuardar.setRendered(false);
		}
		return null;
	}

	public String generarCodigosBarras() {

		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().containsKey("tipoGeneracionCodigos"))
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().remove("tipoGeneracionCodigos");
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().containsKey("idGeneracionCodigos"))
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().remove("idGeneracionCodigos");

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("tipoGeneracionCodigos", "VEAS");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("idGeneracionCodigos", idGeneracionCodigos);
		return "generar";
	}

	/**
	 * Inicializa la Vista
	 * 
	 * @return null en los componentes de la vista
	 */
	/*
	 * public String cancelarDetalleRecepcionVEAS() { this.msg = "";
	 * nuevoDetalleRecepcionVEAS(); return null; }
	 */

	/*
	 * public String cancelarRecepcionVEAS() { return null; }
	 */

	/*
	 * ********************************************** Encapsulamiento
	 * *********************************************
	 */

	/**
	 * @return the idUser
	 */
	public Integer getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser
	 *            the idUser to set
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the idZona
	 */
	public Integer getIdZona() {
		return idZona;
	}

	/**
	 * @param idZona
	 *            the idZona to set
	 */
	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}

	/**
	 * @return the descZona
	 */
	public String getDescZona() {
		return descZona;
	}

	/**
	 * @param descZona
	 *            the descZona to set
	 */
	public void setDescZona(String descZona) {
		this.descZona = descZona;
	}

	/**
	 * @return the idCampania
	 */
	public Integer getIdCampania() {
		return idCampania;
	}

	/**
	 * @param idCampania
	 *            the idCampania to set
	 */
	public void setIdCampania(Integer idCampania) {
		this.idCampania = idCampania;
	}

	/**
	 * @return the descCampania
	 */
	public String getDescCampania() {
		return descCampania;
	}

	/**
	 * @param descCampania
	 *            the descCampania to set
	 */
	public void setDescCampania(String descCampania) {
		this.descCampania = descCampania;
	}

	/**
	 * @return the descDetalle
	 */
	public String getDescDetalle() {
		return descDetalle;
	}

	/**
	 * @param descDetalle
	 *            the descDetalle to set
	 */
	public void setDescDetalle(String descDetalle) {
		this.descDetalle = descDetalle;
	}

	/**
	 * @return the noCajas
	 */
	public byte getNoCajas() {
		return noCajas;
	}

	/**
	 * @param noCajas
	 *            the noCajas to set
	 */
	public void setNoCajas(byte noCajas) {
		this.noCajas = noCajas;
	}

	/**
	 * @return the form
	 */
	public UIForm getForm() {
		return form;
	}

	/**
	 * @param form
	 *            the form to set
	 */
	public void setForm(UIForm form) {
		this.form = form;
	}

	/**
	 * @return the tableForm
	 */
	public UIForm getTableForm() {
		return tableForm;
	}

	/**
	 * @param tableForm
	 *            the tableForm to set
	 */
	public void setTableForm(UIForm tableForm) {
		this.tableForm = tableForm;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the gerenteZona
	 */
	public String getGerenteZona() {
		return gerenteZona;
	}

	/**
	 * @param gerenteZona
	 *            the gerenteZona to set
	 */
	public void setGerenteZona(String gerenteZona) {
		this.gerenteZona = gerenteZona;
	}

	/**
	 * @return the fEntrega
	 */
	public String getfEntrega() {
		return fEntrega;
	}

	/**
	 * @param fEntrega
	 *            the fEntrega to set
	 */
	public void setfEntrega(String fEntrega) {
		this.fEntrega = fEntrega;
	}

	/**
	 * @return the zonas
	 */
	public List<Zona> getZonas() {
		return zonas;
	}

	/**
	 * @param zonas
	 *            the zonas to set
	 */
	public void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}

	/**
	 * @return the detalles
	 */
	public List<RecepcionVeasREACODetalle> getDetalles() {
		return detalles;
	}

	/**
	 * @param detalles
	 *            the detalles to set
	 */
	public void setDetalles(List<RecepcionVeasREACODetalle> detalles) {
		this.detalles = detalles;
	}

	/**
	 * @return the detalle
	 */
	public RecepcionVeasREACODetalle getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle
	 *            the detalle to set
	 */
	public void setDetalle(RecepcionVeasREACODetalle detalle) {
		this.detalle = detalle;
	}

	/**
	 * @return the totalNoCajas
	 */
	public Integer getTotalNoCajas() {
		return totalNoCajas;
	}

	/**
	 * @param totalNoCajas
	 *            the totalNoCajas to set
	 */
	public void setTotalNoCajas(Integer totalNoCajas) {
		this.totalNoCajas = totalNoCajas;
	}

	/**
	 * @return the uRecibio
	 */
	public String getuRecibio() {
		return uRecibio;
	}

	/**
	 * @param uRecibio
	 *            the uRecibio to set
	 */
	public void setuRecibio(String uRecibio) {
		this.uRecibio = uRecibio;
	}

	/**
	 * @return the showTable
	 */
	public boolean isShowTable() {
		return showTable;
	}

	/**
	 * @param showTable
	 *            the showTable to set
	 */
	public void setShowTable(boolean showTable) {
		this.showTable = showTable;
	}

	/**
	 * @return the idGeneracionCodigos
	 */
	public long getIdGeneracionCodigos() {
		return idGeneracionCodigos;
	}

	/**
	 * @param idGeneracionCodigos
	 *            the idGeneracionCodigos to set
	 */
	public void setIdGeneracionCodigos(long idGeneracionCodigos) {
		this.idGeneracionCodigos = idGeneracionCodigos;
	}

	/**
	 * @return the activarGeneracion
	 */
	public String getActivarGeneracion() {
		return activarGeneracion;
	}

	/**
	 * @param activarGeneracion
	 *            the activarGeneracion to set
	 */
	public void setActivarGeneracion(String activarGeneracion) {
		this.activarGeneracion = activarGeneracion;
	}

	/**
	 * @return the lnkGenerarCodigosBarras
	 */
	public UICommand getLnkGenerarCodigosBarras() {
		return lnkGenerarCodigosBarras;
	}

	/**
	 * @param lnkGenerarCodigosBarras
	 *            the lnkGenerarCodigosBarras to set
	 */
	public void setLnkGenerarCodigosBarras(UICommand lnkGenerarCodigosBarras) {
		this.lnkGenerarCodigosBarras = lnkGenerarCodigosBarras;
	}

	/**
	 * @return the btnGuardar
	 */
	public UICommand getBtnGuardar() {
		return btnGuardar;
	}

	/**
	 * @param btnGuardar
	 *            the btnGuardar to set
	 */
	public void setBtnGuardar(UICommand btnGuardar) {
		this.btnGuardar = btnGuardar;
	}
}
