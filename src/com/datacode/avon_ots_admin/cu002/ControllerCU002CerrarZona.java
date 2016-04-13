/**
 * @author jose.ponce
 * @since 04/01/2012
 */
package com.datacode.avon_ots_admin.cu002;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.ZonaControllerStub;
import com.datacode.avon_ots_ws.CampaniaControllerStub.Campania;
import com.datacode.avon_ots_ws.CierreZonaStub;
import com.datacode.avon_ots_ws.CierreZonaStub.CierreZona;

/**
 * @author jose.ponce
 * 
 */
public class ControllerCU002CerrarZona {
	// Dependencias
	private Integer idZona;
	private String gerenteZona;
	private String descZona;
	private Integer idCampania;
	private Integer tipoItem;
	private String descCampania;
	private HashMap<String, String> gerentesZona;
	// grid
	private String registro;
	private int id_orden;
	private int id_item;
	private String codigo_barras;
	private String descripcion;
	private String clave;
	private String estatus;
	private int cantidad;
	private String escaneados;
	private int total_items;
	private int total_escaneo;
	private int totalEscaneados;
	private String codigo_barras_leido;
	private String mensajeError;
	private String rutaImagen;
	boolean bandera = false;
	private Boolean btnItems = true;
	private Boolean btnCerrar = false;
	private Boolean btnCancel = false;
	private Boolean btnValida = false;
	private Boolean banderaEscaneo=false;

	private UIForm frmTabla;
	private UIForm frmPrincipal;

	// Instancia Objetos del Stub
	private Campania[] camps;
	private ZonaControllerStub.Zona[] zons;
	private CierreZona[] cierr;
	// instanciamos el ws
	CierreZonaStub czs = null;
	private List<CierreZona> listaCierre;
	Configuracion configuracion;
	Utils utils;

	/**
	 * Constructor
	 * 
	 * @author jose.ponce
	 * @since 11/01/2012
	 */
	public ControllerCU002CerrarZona() {
		configuracion = (Configuracion) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("configuracion");
		idZona = 0;
		idCampania = 0;
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
		mapa = mapaFull.get(17);// hacerlo dinamico
		//mapa.get("CONSULTAR");
		return mapa;
	}

	/**
	 * Método que carga las campañas en el combo
	 * 
	 * @author jose.ponce
	 * @since 04/01/2012
	 * @return List tipo Campanias
	 */
	public List<SelectItem> getCampanias() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		// Tipo CU --se agrega y modifica en stored procedure
		camps = utils.getCampanias("CUADMIN002_02_01", configuracion.getIdLDC(), getIdZona(), 0);
		list.add(new SelectItem(0, "Selecciona una opción"));

		try {
			if (camps != null) {
				for (int o = 0; o < camps.length; o++) {
					list.add(new SelectItem(camps[o].getIdCampania(), camps[o]
							.getCampania() + "-" + camps[o].getAnioCampania()));
				}
			}
		} catch (NullPointerException e) {
			mensajeError = Utils.ObtenerMensajeBD("General Admin", "M1", true,
					"No se pudieron cargar las Campañas", e.getMessage(),
					configuracion.getIdUsuario())[0];
		}
		return list;
	}

	/**
	 * Método para cargar combo de zonas
	 * 
	 * @author jose.ponce
	 * @since 04/01/2012
	 * @return List con las zonas del cedis
	 */
	public List<SelectItem> getCmbZonas() {
		List<SelectItem> listZonas = new ArrayList<SelectItem>();
		gerentesZona = new HashMap<String, String>();

		zons = utils.getZonas(configuracion.getIdLDC(), "CUADMIN002_02_01");
		listZonas.add(new SelectItem(0, "Selecciona una opción"));

		try {
			if (zons != null) {
				for (int i = 0; i < zons.length; i++) {
					listZonas.add(new SelectItem(zons[i].getIdZona(), zons[i]
							.getZona()));
					gerentesZona.put(zons[i].getIdZona(),
							zons[i].getNombreGerente());
				}
			}
		} catch (NullPointerException e) {
			mensajeError = Utils.ObtenerMensajeBD("General Admin", "M1", true,
					"No se pudieron cargar las Zonas", e.getMessage(),configuracion.getIdUsuario())[0];
		}
		return listZonas;
	}

	/**
	 * Método que valida la seleccion de zona, campaña y el tipo de item.
	 * 
	 * @author jose.ponce
	 * @since 05/01/2012
	 */
	public String prueba() {
		try {
			if (idCampania == 0 || idZona == 0 || tipoItem == null) {
				frmTabla.setRendered(false);
			} else {
				// Se limpia la lista y se reinicia el total de escaneos a 0
				btnItems = false;
				setTotalEscaneados(0);
				total_escaneo = 0;
				bandera = true;
				btnCerrar = true;
				btnValida = true;
				btnCancel = true;
				listaCierre = new ArrayList<CierreZona>();
				frmTabla.setRendered(true);
				total_items = getListaCierre().size();
				getCerrarZona();
				// setTotal_items(getListaCierre().size());
			}
		} catch (NullPointerException ex) {
			System.out.println(ex.toString());
		}
		return "";
	}

	/**
	 * Método que llena el datatable para el cierre de zona
	 * 
	 * @author jose.ponce
	 * @since 06/01/2012
	 */
	@SuppressWarnings("static-access")
	public List<CierreZona> getCerrarZona() {

		CierreZonaStub.GetCierreZona reqcierre = null;
		CierreZonaStub.GetCierreZonaResponse resCierre = null;
		// listaCierre=new ArrayList<CierreZona>();
		try {
			if (idCampania != 0 && idZona != 0 && tipoItem != null) {
				//validacion 05/04/2012
				if(banderaEscaneo == false){
					
				// if(bandera == true){
				czs = new CierreZonaStub();

				// Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(czs
						._getServiceClient().getOptions().getTo().getAddress());
				czs._getServiceClient().getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(url));
				reqcierre = new CierreZonaStub.GetCierreZona();
				// aqui pasamos los parametros al ws
				reqcierre.setId_Usuario(configuracion.getIdUsuario());
				reqcierre.setId_Campania(idCampania);
				reqcierre.setId_Zona(idZona);
				reqcierre.setTipo_Item(tipoItem);
				resCierre = czs.getCierreZona(reqcierre);
				cierr = resCierre.get_return();
				// int totalEscaneados=0;
				// for (int i = 0; i < cierr.length; i++) {
				// if(cierr[i].getEscaneados() == "Si"){
				// totalEscaneados=i;
				// }
				// }
				// setTotal_escaneo(totalEscaneados);
				// En caso de que el ws nos devuelva un nulo
				if (cierr == null) {
					total_items = 0;
					listaCierre = new ArrayList<CierreZona>();
					setListaCierre(listaCierre);
					mensajeError="No hay Valores";
				} else {
					mensajeError=" ";
					int f = 0;
					f = cierr.length;
					total_items = f;
					if (getListaCierre().size() == 0) {
						if (bandera == true) {
							setListaCierre(Arrays.asList(cierr));
						}
					}
				}
				banderaEscaneo=true;
				}///termina if
			}
		} catch (RemoteException excepcionDeInvocacion) {
			utils.GuardarLogMensajeBD(
					"CUADMIN002.02.01",
					"M5",
					"Error al invocar el web service que llena tabla de escaneo.",
					excepcionDeInvocacion.toString(),
					configuracion.getIdUsuario());
		} finally {
			//
		}
		return getListaCierre();		
	}

	/**
	 * Método que actualiza en el datatble los items escaneados y encontrados
	 * 
	 * @author jose.ponce
	 * @since 11/01/2012
	 * 
	 */
	/*public void ActualizaEscaneo(ValueChangeEvent e) {
		try {
			for (int i = 0; i < getListaCierre().size(); i++) {
				if (getListaCierre().get(i).getCodigo_barras().equals(e.getNewValue().toString())
						&& getListaCierre().get(i).getEscaneados().equals("No")) {
					getListaCierre().get(i).setEscaneados("Si");
					getListaCierre().get(i).setEstatus("No Disponible");
					setTotalEscaneados(getTotalEscaneados() + 1);
					setRutaImagen("/img/semaforo/palomita.gif");
					bandera = false;
					break;
				} else {
					setRutaImagen("/img/semaforo/tache.gif");
				}
			}
			setTotal_escaneo(getTotalEscaneados());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}*/

	/**
	 * Método para actualizar los items escaneados
	 * 
	 * @author jose.ponce
	 * @since 11/01/2012
	 */
	@SuppressWarnings({ "static-access", "unused" })
	public String GuardaItemsCierreZona() {
		CierreZonaStub.ActualizaItem actualizarItem = null;
		CierreZonaStub.ActualizaItemResponse actItemResp = null;

		try {
			// Se crea el cliente
			czs = new CierreZonaStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(czs._getServiceClient().getOptions().getTo().getAddress());
			czs._getServiceClient().getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(url));
			// validamos que haya items para actualizar
			if (getListaCierre().size() != 0) {
				// Se agrega al request para la operacion a ejecutar
				actualizarItem = new CierreZonaStub.ActualizaItem();
				for (int i = 0; i < getListaCierre().size(); i++) {
					// VALIDAMOS QUE EL ITEM ESTE ESCANEADO
					if (getListaCierre().get(i).getEscaneados() == "Si") {
						actualizarItem.setId_Usuario(configuracion.getIdUsuario());
						actualizarItem.setUsuario(configuracion.getUsuario());
						actualizarItem.setClave_Orden(getListaCierre().get(i).getClave());
						actualizarItem.setId_Orden(getListaCierre().get(i).getId_orden());
						actualizarItem.setId_Item(getListaCierre().get(i).getId_item());
						actualizarItem.setId_Estatus(i);// id_estatus******
						actualizarItem.setId_Zona(idZona);
						actualizarItem.setId_Campania(idCampania);
						// Añade al response el request de la operacion
						actItemResp = czs.actualizaItem(actualizarItem);
					}
				}
				//02/05/2012
				btnCerrar = false;
				btnValida = false;
				Utils utils = new Utils();
				mensajeError = "Cierre de Zona Exitoso.";
				mensajeError += " " + utils.enviarPrimeraSegundaLiquidacion(2);
				return mensajeError;
			} else {
				return "";
			}

		} catch (RemoteException excepcionDeInvocacion) {
			utils.GuardarLogMensajeBD(
					"CUADMIN002.02.01",
					"M6",
					"Error al invocar el web service que actualiza items escaneados.",
					excepcionDeInvocacion.toString(),
					configuracion.getIdUsuario());
			return mensajeError = "Error al invocar el web service que actualiza items escaneados.";
		} finally {
			//
		}
	}

	public void obtenerCampanias() {
		//setIdZona(Integer.parseInt(e.getNewValue().toString()));
		if (getIdZona() != 0) {
			getCampanias();
			//Cancelar();
		}
	}

	/**
	 * Método para limpiar la pantalla
	 * 
	 * @author jose.ponce
	 * @since 20/02/2012
	 */
	public String Cancelar() {
		// frmTabla.setRendered(true);
		listaCierre = new ArrayList<CierreZona>();
		setRutaImagen("");
		btnItems = true;
		btnCancel = false;
		btnCerrar = false;
		btnValida = false;
		codigo_barras_leido = "";
		total_escaneo = 0;
		total_items = 0;
		idZona = 0;
		idCampania = 0;
		tipoItem = -1;
		mensajeError=" ";
		banderaEscaneo=false;
		bandera=true;
		return "";
	}

	
	public void validaDesdeInput(){
		Valida();
	}
	
	
	/**
	 * Método para validar el escaneo de código de barras.
	 * 
	 * @author jose.ponce
	 * @since 20/02/2012
	 */
	public String Valida() {
		try {
			codigo_barras_leido =utils.ModificaCodigoBarras(codigo_barras_leido);
			for (int i = 0; i < getListaCierre().size(); i++) {
				if (getListaCierre().get(i).getCodigo_barras().equals(codigo_barras_leido)
						&& getListaCierre().get(i).getEscaneados().equals("No")) {
					getListaCierre().get(i).setEscaneados("Si");
					getListaCierre().get(i).setEstatus("No Disponible");
					setTotalEscaneados(getTotalEscaneados() + 1);
					setRutaImagen("/img/semaforo/palomita.gif");
					bandera = false;
					break;
				} else {
					setRutaImagen("/img/semaforo/tache.gif");
				}
			}
			setTotal_escaneo(getTotalEscaneados());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		codigo_barras_leido = "";
		return "";
	}

	/*
	 * *********************** Encapsulamiento *****************************
	 */
	public Integer getIdCampania() {
		return idCampania;
	}

	public void setIdCampania(Integer idCampania) {
		this.idCampania = idCampania;
	}

	public String getDescCampania() {
		return descCampania;
	}

	public void setDescCampania(String descCampania) {
		this.descCampania = descCampania;
	}

	public Integer getIdZona() {
		return idZona;
	}

	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}

	public String getGerenteZona() {
		return gerenteZona;
	}

	public void setGerenteZona(String gerenteZona) {
		this.gerenteZona = gerenteZona;
	}

	public String getDescZona() {
		return descZona;
	}

	public void setDescZona(String descZona) {
		this.descZona = descZona;
	}

	public HashMap<String, String> getGerentesZona() {
		return gerentesZona;
	}

	public void setGerentesZona(HashMap<String, String> gerentesZona) {
		this.gerentesZona = gerentesZona;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public int getId_orden() {
		return id_orden;
	}

	public void setId_orden(int id_orden) {
		this.id_orden = id_orden;
	}

	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

	public String getCodigo_barras() {
		return codigo_barras;
	}

	public void setCodigo_barras(String codigo_barras) {
		this.codigo_barras = codigo_barras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getTotal_items() {
		return total_items;
	}

	public void setTotal_items(int total_items) {
		this.total_items = total_items;
	}

	public int getTotal_escaneo() {
		return total_escaneo;
	}

	public void setTotal_escaneo(int total_escaneo) {
		this.total_escaneo = total_escaneo;
	}

	public String getEscaneados() {
		return escaneados;
	}

	public void setEscaneados(String escaneados) {
		this.escaneados = escaneados;
	}

	public String getCodigo_barras_leido() {
		return codigo_barras_leido;
	}

	public void setCodigo_barras_leido(String codigo_barras_leido) {
		this.codigo_barras_leido = codigo_barras_leido;
	}

	public UIForm getFrmTabla() {
		return frmTabla;
	}

	public void setFrmTabla(UIForm frmTabla) {
		this.frmTabla = frmTabla;
	}

	public int getTotalEscaneados() {
		return totalEscaneados;
	}

	public void setTotalEscaneados(int totalEscaneados) {
		this.totalEscaneados = totalEscaneados;
	}

	public List<CierreZona> getListaCierre() {
		return listaCierre;
	}

	public void setListaCierre(List<CierreZona> listaCierre) {
		this.listaCierre = listaCierre;
	}

	public Integer getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(Integer tipoItem) {
		this.tipoItem = tipoItem;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public Boolean getBtnItems() {
		return btnItems;
	}

	public void setBtnItems(Boolean btnItems) {
		this.btnItems = btnItems;
	}

	public Boolean getBtnCerrar() {
		return btnCerrar;
	}

	public void setBtnCerrar(Boolean btnCerrar) {
		this.btnCerrar = btnCerrar;
	}

	public UIForm getFrmPrincipal() {
		return frmPrincipal;
	}

	public void setFrmPrincipal(UIForm frmPrincipal) {
		this.frmPrincipal = frmPrincipal;
	}

	public Boolean getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Boolean btnCancel) {
		this.btnCancel = btnCancel;
	}

	public Boolean getBtnValida() {
		return btnValida;
	}

	public void setBtnValida(Boolean btnValida) {
		this.btnValida = btnValida;
	}

	public Boolean getBanderaEscaneo() {
		return banderaEscaneo;
	}

	public void setBanderaEscaneo(Boolean banderaEscaneo) {
		this.banderaEscaneo = banderaEscaneo;
	}

}
