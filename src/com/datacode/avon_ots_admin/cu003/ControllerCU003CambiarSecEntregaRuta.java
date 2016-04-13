/**
 *
 *  @since 18/01/2012
 *
 */
package com.datacode.avon_ots_admin.cu003;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIData;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.datacode.avon_ots_admin.model.Representante;
import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.RutasControllerStub;
import com.datacode.avon_ots_ws.ZonaControllerStub;
import com.datacode.avon_ots_ws.CampaniaControllerStub.Campania;
import com.datacode.avon_ots_ws.RutasControllerStub.Rutas;

/**
 * @author jessica.leon
 * 
 */
public class ControllerCU003CambiarSecEntregaRuta {

	private UIForm form;
	private UIForm tableForm;
	private UIData dataTable;
	private UIForm formEdicion;
	private UISelectOne selectCampanias;
	private UISelectOne selectRutas;
	private UIInput txtAccountFiltro;
	private String accountFiltro = "";

	private Campania[] campanias;
	private ZonaControllerStub.Zona[] zonas;
	private Rutas[] arrRutas;
	private Map<String, String> tiposRutas;

	private Integer idCampania;
	private Integer idZona;
	private Integer idLDC;
	private Integer idRuta;
	private Integer idUser;
	private String descTipoRuta;
	private String mensaje;
	private boolean disableConsultar;
	private boolean disableCampania;
	private Integer noSecuenciamMaxima;

	private List<Representante> representantes;
	private Representante representante;
	private RepresentanteController repController;

	private Utils utils;
	private Configuracion configuracion;

	public ControllerCU003CambiarSecEntregaRuta() {

		configuracion = (Configuracion) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("configuracion");
		idLDC = configuracion.getIdLDC();
		idZona = 0;
		idCampania = 0;
		idRuta = 0;
		idUser = configuracion.getIdUsuario();
		disableConsultar = true;
		representantes = new ArrayList<Representante>();
		utils = new Utils();
		repController = new RepresentanteController();
		// Número de secuencia máxima permitido. Si quiere modificarse el valor
		// hacerlo aqui, solamente.
		noSecuenciamMaxima = new Integer(10000);
		disableCampania = true;
		getZonas();
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
		mapa = mapaFull.get(13);// el 1 es el id del modulo hay que ver como
								// hacerlo dinamico
		return mapa;
	}

	/**
	 * 
	 * @author jessica.leon
	 * @since 18/01/2012
	 * @return
	 * 
	 */
	
	private List<SelectItem> listCampanias;
	private List<SelectItem> listZonas;
	private List<SelectItem> listRutas;
	
	public List<SelectItem> getListCampanias() {
		return listCampanias;
	}

	public void setListCampanias(List<SelectItem> listCampanias) {
		this.listCampanias = listCampanias;
	}

	public List<SelectItem> getListZonas() {
		return listZonas;
	}

	public void setListZonas(List<SelectItem> listZonas) {
		this.listZonas = listZonas;
	}

	public List<SelectItem> getListRutas() {
		return listRutas;
	}

	public void setListRutas(List<SelectItem> listRutas) {
		this.listRutas = listRutas;
	}

	public void getCampanias() {

		listCampanias = new ArrayList<SelectItem>();
		campanias = utils.getCampanias("CUADMIN002_01_02",
				configuracion.getIdLDC(), getIdZona(), 0);
		listCampanias.add(new SelectItem(0, "Selecciona una opción"));
		setIdCampania(0);

		try {
			if (campanias != null) {
				for (int o = 0; o < campanias.length; o++) {
					listCampanias.add(new SelectItem(campanias[o].getIdCampania(),
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
	}

	public void getZonas() {

		listZonas = new ArrayList<SelectItem>();
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
		
		listRutas = new ArrayList<SelectItem>();
		listRutas.add(new SelectItem(0, "Selecciona una opción"));
		listCampanias = new ArrayList<SelectItem>();
		listCampanias.add(new SelectItem(0, "Selecciona una opción"));
		
	}

	public void getRutas() {

		listRutas = new ArrayList<SelectItem>();
		arrRutas = utils.getRutas("CUADMIN003_02",getIdLDC(),getIdZona(),getIdCampania());
		listRutas.add(new SelectItem(0, "Selecciona una opción"));
		setIdRuta(0);
		tiposRutas = new HashMap<String,String>();
		
		try {
			if (arrRutas != null) {
				for (int i = 0; i < arrRutas.length; i++) {
					listRutas.add(new SelectItem(arrRutas[i].getIdRuta(),
							arrRutas[i].getCveRuta() +" - " + arrRutas[i].getDescTipoRuta()));
					this.tiposRutas.put(arrRutas[i].getIdRuta(),
							arrRutas[i].getDescTipoRuta());
				}
			}
		} catch (NullPointerException e) {
			mensaje = "Error al obtener los datos de Rutas.";
		}
		
	}

	public void getRutasPorZona(ValueChangeEvent e) {
		setIdZona(Integer.parseInt(e.getNewValue().toString()));
		descTipoRuta = null;
		this.tableForm.setRendered(false);
		formEdicion.setRendered(false);
		representantes.clear();
		selectCampanias.setValue(0);
		selectRutas.setValue(0);
		txtAccountFiltro.setValue("");
		accountFiltro = "";
		getRutas();
		getCampanias();
		
	}

	public void actualizarCampania(ValueChangeEvent e) {
//		setIdCampania(Integer.parseInt(e.getNewValue().toString()));
		this.tableForm.setRendered(false);
		formEdicion.setRendered(false);
		representantes.clear();
		disableConsultar = false;
	}

	public void getDescripcionTipoRuta(ValueChangeEvent e) {

		this.tableForm.setRendered(false);
		formEdicion.setRendered(false);
		representantes.clear();
		disableCampania = false;
//		String idRuta = e.getNewValue().toString();
		// BEV - this.descTipoRuta = this.tiposRutas.get(idRuta);
		// disableConsultar = false;
	}

	public String consultarRepresentantes() {

		mensaje = null;
		representantes.clear();
		this.tableForm.setRendered(false);
		formEdicion.setRendered(false);
		representante = new Representante();
		
		if (idZona == 0 && idRuta == 0 && idCampania == 0 && accountFiltro.isEmpty()) {
			mensaje = "No se han ingresado criterios para la búsqueda de Representantes";
		} else {
			obtenerListaRepresentantes();
			if (representantes.size() > 0) {
				this.tableForm.setRendered(true);
				if (representantes.size() == 1) {
					representante = representantes.get(0);
					formEdicion.setRendered(true);
				} 
			} else {
				mensaje = "No hay representantes con los criterios especificados";
			}
		}
		return null;
	}

	public void obtenerListaRepresentantes() {
		repController.obtenerListaRepresentantes(getIdCampania(), getIdZona(),
				getIdRuta(), idUser, accountFiltro, representantes);
		// obtenerSecuenciaMaxima();
	}

	public String modificarSecuenciaDeRepresentante() {
		// this.formEdicion.setRendered(false);
		return null;
	}

	public String guardarNuevaSecuencia() {
		// this.formEdicion.setRendered(true);
		return null;
	}

	public String hideTable() {
		formEdicion.setRendered(false);
		return null;
	}

	public void displayTable(ActionEvent event) {
		if (event.getComponent().getId()
				.equalsIgnoreCase("btnGuardarCambiarSecuencia")) {
			formEdicion.setRendered(false);
		} else {
			formEdicion.setRendered(true);
		}
	}

	public void obtenerSecuenciaMaxima() {
		int sizeList = representantes.size();
		this.noSecuenciamMaxima = Integer.parseInt(representantes
				.get(sizeList - 1).getRepresentantePorRuta()
				.getSeqEntregaAnterior());
	}

	public String aplicarCambiosEnSecuencia() {
		generarNuevaLista();
		repController.actualizarSecuenciaRepresentante(representantes, idUser);
		mensaje = "Secuencia Actualizada";
		representantes.clear();
		obtenerListaRepresentantes();
		return null;
	}

	public String ordenarNuevaSecuencia() {
		Collections.sort(representantes, new OrdenarSecuencia());
		return null;
	}

	public void generarNuevaLista() {

		String secuenciaAnterior = null;
		String secuenciaNueva = null;
		for (Representante representante : representantes) {
			secuenciaAnterior = representante.getRepresentantePorRuta()
					.getSeqEntregaAnterior();
			secuenciaNueva = representante.getRepresentantePorRuta()
					.getSeqEntregaReciente();
			if (Integer.parseInt(secuenciaNueva) > 0) {
				if (!secuenciaAnterior.equals(secuenciaNueva)) {
					representante.getRepresentantePorRuta()
							.setSeqEntregaAnterior(secuenciaNueva);
				}
			}
		}
	}

	/* GETTERS Y SETTERS */

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
	 * @return the formEdicion
	 */
	public UIForm getFormEdicion() {
		return formEdicion;
	}

	/**
	 * @param formEdicion
	 *            the formEdicion to set
	 */
	public void setFormEdicion(UIForm formEdicion) {
		this.formEdicion = formEdicion;
	}

	/**
	 * @return the noSecuenciamMxima
	 */
	public Integer getNoSecuenciaMaxima() {
		return noSecuenciamMaxima;
	}

	/**
	 * @param noSecuenciamMxima
	 *            the noSecuenciamMxima to set
	 */
	public void setNoSecuenciaMaxima(Integer noSecuenciamMaxima) {
		this.noSecuenciamMaxima = noSecuenciamMaxima;
	}

	/**
	 * @return the disableCampania
	 */
	public boolean isDisableZonas() {
		return disableCampania;
	}

	/**
	 * @param disableCampania
	 *            the disableCampania to set
	 */
	public void setDisableZonas(boolean disableCampania) {
		this.disableCampania = disableCampania;
	}

	public UISelectOne getSelectCampanias() {
		return selectCampanias;
	}

	public void setSelectCampanias(UISelectOne selectCampanias) {
		this.selectCampanias = selectCampanias;
	}

	public UISelectOne getSelectRutas() {
		return selectRutas;
	}

	public void setSelectRutas(UISelectOne selectRutas) {
		this.selectRutas = selectRutas;
	}

	public String getAccountFiltro() {
		return accountFiltro;
	}

	public void setAccountFiltro(String accountFiltro) {
		this.accountFiltro = accountFiltro;
	}

	public UIInput getTxtAccountFiltro() {
		return txtAccountFiltro;
	}

	public void setTxtAccountFiltro(UIInput txtAccountFiltro) {
		this.txtAccountFiltro = txtAccountFiltro;
	}

}
