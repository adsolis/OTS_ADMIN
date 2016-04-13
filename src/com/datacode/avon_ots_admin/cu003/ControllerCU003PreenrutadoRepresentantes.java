/**
 *
 *  @since 04/01/2012
 *
 */
package com.datacode.avon_ots_admin.cu003;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIData;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.datacode.avon_ots_admin.armadoRutasEspeciales.ConsultaDatosRutasEspeciales;
import com.datacode.avon_ots_admin.model.Representante;
import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.CampaniaControllerStub;
import com.datacode.avon_ots_ws.RepresentanteControllerStub;
import com.datacode.avon_ots_ws.RutasControllerStub;
import com.datacode.avon_ots_ws.RutasControllerStub.Rutas;
import com.datacode.avon_ots_ws.ZonaControllerStub;
import com.datacode.avon_ots_ws.CampaniaControllerStub.Campania;

/**
 * @author jessica.leon
 * 
 */
public class ControllerCU003PreenrutadoRepresentantes {

	private UIForm form;
	private UIForm tableForm;
	private UIData dataTable;

	private Campania[] campanias;
	private ZonaControllerStub.Zona[] zonas;
	private Rutas[] arrRutas;
	private RepresentanteControllerStub.Representante[] arrRepresentantes;
	private Map<String, String> tiposRutas;

	private Integer idCampania;
	private Integer idZona;
	private Integer idLDC;
	private Integer idRuta;
	private Integer idUser;
	private String descTipoRuta;
	private Integer rutaRepresentante;
	private Integer secuenciaRepresentante;
	private String mensaje;
	private boolean disableConsultar;
	private boolean disableAsignar;
	private boolean showTable;
	private String tipoEnrutado;
	private List<SelectItem> tiposEnrutado;

	private List<Representante> representantes;
	private Representante representante;
	private Map<Integer, Boolean> checkBoxList;
	private List<Representante> representantesSeleccionadas;
	private Utils utils;

	Configuracion configuracion;
	/* Variables para filtros */
	private String filterCuenta = "";
	private String filterNombre = "";
	private String filterDireccion = "";

	public String getTipoEnrutado() {
		return tipoEnrutado;
	}

	public void setTipoEnrutado(String tipoEnrutado) {
		this.tipoEnrutado = tipoEnrutado;
	}

	public List<SelectItem> getTiposEnrutado() {

		if (tiposEnrutado == null) {

			List<SelectItem> list = new ArrayList<SelectItem>();

			list.add(new SelectItem("1", "Primeras Ordenes"));
			list.add(new SelectItem("2", "Buen Vecino"));

			tiposEnrutado = list;

		}
		return tiposEnrutado;
	}

	public void setTiposEnrutado(List<SelectItem> tiposEnrutado) {
		this.tiposEnrutado = tiposEnrutado;
	}

	/**
	 * 
	 */
	public ControllerCU003PreenrutadoRepresentantes() {

		configuracion = (Configuracion) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("configuracion");
		idLDC = configuracion.getIdLDC();
		idZona = 0;
		idCampania = 0;
		idRuta = 0;
		tipoEnrutado = "1";
		idUser = configuracion.getIdUsuario();
		rutaRepresentante = 0;
		secuenciaRepresentante = 0;
		disableAsignar = true;
		disableConsultar = true;
		checkBoxList = new HashMap<Integer, Boolean>();
		representantes = new ArrayList<Representante>();
		showTable = false;
		utils = new Utils();

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
		mapa = mapaFull.get(8);// el 1 es el id del modulo hay que ver como
								// hacerlo dinamico
		return mapa;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 10/01/2012
	 * @return
	 * 
	 */
	public List<SelectItem> getCampanias() {

		List<SelectItem> list = new ArrayList<SelectItem>();
		campanias = utils.getCampanias("CUADMIN002_01_02",
				configuracion.getIdLDC(), getIdZona(), 0);
		list.add(new SelectItem(0, "Selecciona una opción"));

		try {
			if (campanias != null) {
				for (int o = 0; o < campanias.length; o++) {
					list.add(new SelectItem(campanias[o].getIdCampania(),
							campanias[o].getCampania() + "-"
									+ campanias[o].getAnioCampania()));
				}
			}
		} catch (NullPointerException e) {
			mensaje = Utils.ObtenerMensajeBD("General Admin", "M1", true,
					"No se pudieron cargar las Campañas en el Controller",
					e.getMessage(), idUser)[0];
			mensaje = e.getMessage();
		}
		return list;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 10/01/2012
	 * @return
	 * 
	 */
	public List<SelectItem> getZonas() {

		List<SelectItem> listZonas = new ArrayList<SelectItem>();
		zonas = utils.getZonas(configuracion.getIdLDC(), "CUADMIN003_02");
		listZonas.add(new SelectItem(0, "Selecciona una opción"));

		try {
			for (int i = 0; i < zonas.length; i++) {
				listZonas.add(new SelectItem(zonas[i].getIdZona(), zonas[i]
						.getZona()));
			}
		} catch (NullPointerException e) {
			mensaje = Utils.ObtenerMensajeBD("General Admin", "M1", true,
					"No se pudieron cargar las Zonas", e.getMessage(), idUser)[0];
		}
		return listZonas;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 10/01/2012
	 * @return
	 * 
	 */
	public List<SelectItem> getRutas() {

		List<SelectItem> listRutas = new ArrayList<SelectItem>();
		arrRutas = utils.getRutas("CUADMIN003_02", getIdLDC(), getIdZona(),
				getIdCampania());
		listRutas.add(new SelectItem(-1, "Selecciona una opción"));
		tiposRutas = new HashMap<String, String>();

		try {
			if (arrRutas != null) {
				for (int i = 0; i < arrRutas.length; i++) {
					listRutas.add(new SelectItem(arrRutas[i].getIdRuta(),
							arrRutas[i].getCveRuta() + " - "
									+ arrRutas[i].getDescTipoRuta()));
					this.tiposRutas.put(arrRutas[i].getIdRuta(),
							arrRutas[i].getDescTipoRuta());
				}
			}
		} catch (NullPointerException e) {
			mensaje = "Error al obtener los datos de Rutas.";
		}
		return listRutas;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 10/01/2012
	 * @param e
	 * 
	 */
	public void getCampaniasPorZona(ValueChangeEvent e) {

		setIdZona(Integer.parseInt(e.getNewValue().toString()));

		if (getIdZona() != 0) {
			descTipoRuta = null;
			representantes.clear();
			checkBoxList.clear();
			showTable = false;
			getCampanias();
			getRutas();
			disableAsignar = true;
			disableConsultar = false;
			mensaje = null;
		} else {
			mensaje = "Debe seleccionar una zona";
		}

	}

	public void guardaDatoTipo(ValueChangeEvent e) {

		System.out.println(tipoEnrutado);

	}

	public void actualizarCampania(ValueChangeEvent e) {

		setIdCampania(Integer.parseInt(e.getNewValue().toString()));
		// getRutas();
		descTipoRuta = null;
		representantes.clear();
		checkBoxList.clear();
		showTable = false;
		mensaje = null;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 10/01/2012
	 * @param e
	 * 
	 */
	public void getDescripcionTipoRuta(ValueChangeEvent e) {

		String idRuta = e.getNewValue().toString();
		// this.descTipoRuta = this.tiposRutas.get(idRuta);
		disableAsignar = false;

	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 10/01/2012
	 * 
	 */
	public void obtenerListaRepresentantes() {

		RepresentanteControllerStub customer = null;
		RepresentanteControllerStub.ObtenerRepresentantesSinRutaAsignada request = null;
		RepresentanteControllerStub.ObtenerRepresentantesSinRutaAsignadaResponse response = null;

		try {
			customer = new RepresentanteControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(customer
					._getServiceClient().getOptions().getTo().getAddress());
			customer._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			request = new RepresentanteControllerStub.ObtenerRepresentantesSinRutaAsignada();
			request.setIdZona(getIdZona());
			request.setIdCampania(getIdCampania());
			// aqui es donde hay que hacer el if
			if (tipoEnrutado.equals("1")) {
				request.setTipo("PRINCIPAL");
			} else if (tipoEnrutado.equals("2")) {
				request.setTipo("BUENVECINO");
			}

			request.setIdUsuario(configuracion.getIdUsuario());
			request.setTipoCU("CUADMIN003_02");

			response = customer.obtenerRepresentantesSinRutaAsignada(request);
			arrRepresentantes = response.get_return();

			if (arrRepresentantes != null) {
				for (int i = 0; i < arrRepresentantes.length; i++) {
					representante = new Representante();
					representante.setIdRepresentante(arrRepresentantes[i]
							.getIdRepresentante());
					representante.setNombre(arrRepresentantes[i].getNombre());
					representante.setDomicilio(arrRepresentantes[i]
							.getDomicilio());
					representante.setRegistro(arrRepresentantes[i]
							.getRegistro());
					representante.setEstado(arrRepresentantes[i].getEstado());
					representante.setPoblacion(arrRepresentantes[i]
							.getPoblacion());
					representante.setDomicilioAlterno(arrRepresentantes[i]
							.getDomicilioAlterno());
					representantes.add(representante);
					// selectedIds.put(arrRepresentantes[i].getIdRepresentante(),Boolean.FALSE);
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 10/01/2012
	 * @return
	 * 
	 */
	public String consultarRepresentantes() {
		mensaje = null;
		representantes.clear();

		if (getIdZona() != 0 && getIdCampania() != 0) {
			obtenerListaRepresentantes();
			if (representantes.size() > 0) {
				showTable = true;
			} else {
				mensaje = "No existen representantes con los criterios seleccionados";
			}
		} else {
			mensaje = "Por favor, seleccione zona y campaña";
		}
		return null;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 10/01/2012
	 * @return
	 * 
	 */
	public String asignarRutaEnRepresentantes() {
		getRepresentantesSeleccionadas();
		if (!idRuta.equals("-1")) {
			if (representantesSeleccionadas.size() > 0) {
				actualizarPreenrutadoRepresentantes();
				showTable = false;
				descTipoRuta = null;
				representantes.clear();
				checkBoxList.clear();
				representantesSeleccionadas.clear();
				String claveRuta = obtenerClaveRuta();
				mensaje = "Representantes enrutadas";
			} else {
				mensaje = "Por favor, seleccione representante(s) y/o ruta";
			}
		} else {
			mensaje = "Por favor, seleccione representante(s) y/o ruta";
		}

		return null;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 10/01/2012
	 * @return
	 * 
	 */
	public String obtenerClaveRuta() {
		String claveRuta = null;

		for (int i = 0; i < arrRutas.length; i++) {
			if (arrRutas[i].getIdRuta().equals(String.valueOf(getIdRuta()))) {
				claveRuta = arrRutas[i].getCveRuta();
			}
		}
		return claveRuta;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 10/01/2012
	 * 
	 */
	public void getRepresentantesSeleccionadas() {

		representantesSeleccionadas = new ArrayList<Representante>();

		for (Representante representante : representantes) {
			if (checkBoxList.get(representante.getIdRepresentante())
					.booleanValue()) {
				representantesSeleccionadas.add(representante);
				checkBoxList.remove(representante.getIdRepresentante());
			}
		}
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 10/01/2012
	 * @param event
	 * 
	 */
	public void checkBoxSeleccionado(ValueChangeEvent event) {

		Representante representante = (Representante) dataTable.getRowData();
		checkBoxList.put(representante.getIdRepresentante(),
				(Boolean) event.getNewValue());
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 16/02/2012
	 * @param event
	 * 
	 */
	public void seleccionarTodos(ValueChangeEvent event) {

		Boolean seleccionado = (Boolean) event.getNewValue();

		if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			event.queue();
		} else {
			if (seleccionado) {
				actualizarListaCheckBox(checkBoxList, seleccionado);
			} else {
				actualizarListaCheckBox(checkBoxList, seleccionado);
			}
		}
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 17/02/2012
	 * @param selectedComponentMap
	 * @param blnValue
	 * 
	 */
	public void actualizarListaCheckBox(
			Map<Integer, Boolean> selectedComponentMap, Boolean blnValue) {
		if (selectedComponentMap != null) {
			Iterator<Integer> itr = selectedComponentMap.keySet().iterator();
			while (itr.hasNext()) {
				selectedComponentMap.put(itr.next(), blnValue);
			}
			setCheckBoxList(selectedComponentMap);
		}
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 10/01/2012
	 * 
	 */
	public void actualizarPreenrutadoRepresentantes() {

		RepresentanteControllerStub customer = null;
		RepresentanteControllerStub.ActualizarPreenrutadoDeRepresentante request = null;
		RepresentanteControllerStub.ActualizarPreenrutadoDeRepresentanteResponse response = null;

		for (Representante representante : representantesSeleccionadas) {

			try {
				customer = new RepresentanteControllerStub();

				// Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(customer
						._getServiceClient().getOptions().getTo().getAddress());
				customer._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));

				request = new RepresentanteControllerStub.ActualizarPreenrutadoDeRepresentante();
				request.setIdLDC(getIdLDC());
				request.setIdRepresentante(representante.getIdRepresentante());
				request.setIdZona(getIdZona());
				request.setIdRuta(getIdRuta());
				request.setSecuenciaActual(getSecuenciaRepresentante());
				request.setSecuenciaAnterior(getSecuenciaRepresentante());
				request.setIdUsuario(configuracion.getIdUsuario());
				if (tipoEnrutado.equals("2")) {
					request.setTipoEnrutado("BUENVECINO");
				} else {
					request.setTipoEnrutado("NORMAL");
				}
				request.setTipoCU("CUADMIN003_02");
				response = customer
						.actualizarPreenrutadoDeRepresentante(request);
				mensaje = response.get_return();

			} catch (RemoteException e) {

				Utils.GuardarLogMensajeBD(
						"CUADMIN003_02",
						"M7",
						"Surgió un error al actualizar la ruta de la representante",
						e.getMessage(), idUser);
				e.printStackTrace();
			}
		}
	}

	/*
	 * ****************************** FILTROS GRID REPRESENTANTE
	 * **************************************************************
	 */
	/**
	 * Metodo utilizado para validar si el Object recibido satisface la
	 * condición del filtro. Cuenta de Representante
	 * 
	 * @param current
	 *            - Tipo Objeto se recibe desde interfaz
	 * @param current
	 * @return boolean true si encuentra el valor
	 * @return boolean false Objecto nulo
	 * @author brenda.estrada
	 * @since 28/02/2012
	 */
	public Boolean filterCuentaRepresentante(Object current) {
		try {
			Representante objCuenta = (Representante) current;
			if (filterCuenta.length() == 0 || filterCuenta.equals("%")) {
				return true;
			}
			if (objCuenta.getRegistro().toLowerCase()
					.contains(filterCuenta.toLowerCase())) {
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException nil) {
			return false;
		}
	}

	/**
	 * Metodo utilizado para validar si el Object recibido satisface la
	 * condición del filtro. Nombre de Representante
	 * 
	 * @param current
	 *            - Tipo Objeto se recibe desde interfaz
	 * @param current
	 * @return boolean true si encuentra el valor
	 * @return boolean false Objecto nulo
	 * @author brenda.estrada
	 * @since 28/02/2012
	 */
	public Boolean filterNombreRepresentante(Object current) {
		try {
			Representante objCuenta = (Representante) current;
			if (filterNombre.length() == 0) {
				return true;
			}
			if (objCuenta.getNombre().toLowerCase()
					.contains(filterNombre.toLowerCase())) {
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException nil) {
			return false;
		}
	}

	/**
	 * Metodo utilizado para validar si el Object recibido satisface la
	 * condición del filtro. Domicilio de Representante
	 * 
	 * @param current
	 *            - Tipo Objeto se recibe desde interfaz
	 * @param current
	 * @return boolean true si encuentra el valor
	 * @return boolean false Objecto nulo
	 * @author brenda.estrada
	 * @since 28/02/2012
	 */
	public Boolean filterDomicilioRepresentante(Object current) {
		try {
			Representante objCuenta = (Representante) current;
			if (filterDireccion.length() == 0) {
				return true;
			}
			if (objCuenta.getDomicilio().toLowerCase()
					.contains(filterDireccion.toLowerCase())) {
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException nil) {
			return false;
		}
	}

	/*
	 * *************************** Inicia Encapsulamiento
	 * ****************************************
	 */
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
	 * @return the idLDC
	 */
	public Integer getIdLDC() {
		return idLDC;
	}

	/**
	 * @param idLDC
	 *            the idLDC to set
	 */
	public void setIdLDC(Integer idLDC) {
		this.idLDC = idLDC;
	}

	/**
	 * @return the idRuta
	 */
	public Integer getIdRuta() {
		return idRuta;
	}

	/**
	 * @param idRuta
	 *            the idRuta to set
	 */
	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}

	/**
	 * @return the descTipoRuta
	 */
	public String getDescTipoRuta() {
		return descTipoRuta;
	}

	/**
	 * @param descTipoRuta
	 *            the descTipoRuta to set
	 */
	public void setDescTipoRuta(String descTipoRuta) {
		this.descTipoRuta = descTipoRuta;
	}

	/**
	 * @return the representantes
	 */
	public List<Representante> getRepresentantes() {
		return representantes;
	}

	/**
	 * @param representantes
	 *            the representantes to set
	 */
	public void setRepresentantes(List<Representante> representantes) {
		this.representantes = representantes;
	}

	/**
	 * @return the representante
	 */
	public Representante getRepresentante() {
		return representante;
	}

	/**
	 * @param representante
	 *            the representante to set
	 */
	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	/**
	 * @return the rutaRepresentante
	 */
	public Integer getRutaRepresentante() {
		return rutaRepresentante;
	}

	/**
	 * @param rutaRepresentante
	 *            the rutaRepresentante to set
	 */
	public void setRutaRepresentante(Integer rutaRepresentante) {
		this.rutaRepresentante = rutaRepresentante;
	}

	/**
	 * @return the secuenciaRepresentante
	 */
	public Integer getSecuenciaRepresentante() {
		return secuenciaRepresentante;
	}

	/**
	 * @param secuenciaRepresentante
	 *            the secuenciaRepresentante to set
	 */
	public void setSecuenciaRepresentante(Integer secuenciaRepresentante) {
		this.secuenciaRepresentante = secuenciaRepresentante;
	}

	/**
	 * @return the checkBoxList
	 */
	public Map<Integer, Boolean> getCheckBoxList() {
		return checkBoxList;
	}

	/**
	 * @param checkBoxList
	 *            the checkBoxList to set
	 */
	public void setCheckBoxList(Map<Integer, Boolean> checkBoxList) {
		this.checkBoxList = checkBoxList;
	}

	/**
	 * @return the dataTable
	 */
	public UIData getDataTable() {
		return dataTable;
	}

	/**
	 * @param dataTable
	 *            the dataTable to set
	 */
	public void setDataTable(UIData dataTable) {
		this.dataTable = dataTable;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the disableConsultar
	 */
	public boolean isDisableConsultar() {
		return disableConsultar;
	}

	/**
	 * @param disableConsultar
	 *            the disableConsultar to set
	 */
	public void setDisableConsultar(boolean disableConsultar) {
		this.disableConsultar = disableConsultar;
	}

	/**
	 * @return the disableAsignar
	 */
	public boolean isDisableAsignar() {
		return disableAsignar;
	}

	/**
	 * @param disableAsignar
	 *            the disableAsignar to set
	 */
	public void setDisableAsignar(boolean disableAsignar) {
		this.disableAsignar = disableAsignar;
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
	 * @return the filterCuenta
	 */
	public String getFilterCuenta() {
		return filterCuenta;
	}

	/**
	 * @param filterCuenta
	 *            the filterCuenta to set
	 */
	public void setFilterCuenta(String filterCuenta) {
		this.filterCuenta = filterCuenta;
	}

	/**
	 * @return the filterNombre
	 */
	public String getFilterNombre() {
		return filterNombre;
	}

	/**
	 * @param filterNombre
	 *            the filterNombre to set
	 */
	public void setFilterNombre(String filterNombre) {
		this.filterNombre = filterNombre;
	}

	/**
	 * @return the filterDireccion
	 */
	public String getFilterDireccion() {
		return filterDireccion;
	}

	/**
	 * @param filterDireccion
	 *            the filterDireccion to set
	 */
	public void setFilterDireccion(String filterDireccion) {
		this.filterDireccion = filterDireccion;
	}
}
