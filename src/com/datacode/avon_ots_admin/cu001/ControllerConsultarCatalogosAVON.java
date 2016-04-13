/**
 * Contiene las operaciones que obtienes la informacion del Web Service
 * @author brenda.estrada
 * @since 17/01/2012
 */
package com.datacode.avon_ots_admin.cu001;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.component.UIForm;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.datacode.avon_ots_admin.model.Bancos;
import com.datacode.avon_ots_admin.model.Campania;
import com.datacode.avon_ots_admin.model.Division;
import com.datacode.avon_ots_admin.model.EstadoOrden;
import com.datacode.avon_ots_admin.model.Orden;
import com.datacode.avon_ots_admin.model.Premios;
import com.datacode.avon_ots_admin.model.ProgramacionReparto;
import com.datacode.avon_ots_admin.model.RazonesDevolucion;
import com.datacode.avon_ots_admin.model.Representante;
import com.datacode.avon_ots_admin.model.TipoLiquidacion;
import com.datacode.avon_ots_admin.model.TipoPago;
import com.datacode.avon_ots_admin.model.TipoSiniestro;
import com.datacode.avon_ots_admin.model.Unitarios;
import com.datacode.avon_ots_admin.model.Zona;
import com.datacode.avon_ots_admin.reports.JasperGenerator;
import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.CampaniaControllerStub;
import com.datacode.avon_ots_ws.CampaniaControllerStub.GetUltimasCampaniasSinID;
import com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub;
import com.datacode.avon_ots_ws.RutasControllerStub.Rutas;
import com.datacode.avon_ots_ws.ZonaControllerStub;

/**
 * Consultar Catalogos Provenientes de AVON
 * @author brenda.estrada
 * @since 17/01/2012
 */
public class ControllerConsultarCatalogosAVON {
	//Atributos de la Interfaz
	private UIForm form;
	//Atributos Rendered Panel
	private Boolean pnl1 = new Boolean(false);
	private Boolean pnl2 = new Boolean(false);
	private Boolean pnl3 = new Boolean(false);
	private Boolean pnl4 = new Boolean(false);
	private Boolean pnl5 = new Boolean(false);
	private Boolean pnl6 = new Boolean(false);
	private Boolean pnl7 = new Boolean(false);
	private Boolean pnl8 = new Boolean(false);
	private Boolean pnl9 = new Boolean(false);
	private Boolean pnl10 = new Boolean(false);
	private Boolean pnl11 = new Boolean(false);
	private Boolean pnl12 = new Boolean(false);
	private Boolean pnl13 = new Boolean(false);
	private Boolean pnl14 = new Boolean(false);
	private Boolean pnlRepresentante = new Boolean(false);
	
	//Atributos de Interaccion Pantalla
	private Integer idCatalogo = 0;
	private String descCatalogo = "";
	private String msg = "";
	
	//Obtiene el objeto Configuración con los valores cargados al inicio de sesión
	Configuracion config = (Configuracion)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("configuracion");
	
	/* ******************************************* Instancias de Modelo ********************************** */
	private List<Bancos> bancos = null;
	private Bancos banco;
	private List<Campania> campanias = null;
	private Campania campania;
	private List<Division> divisiones = null;
	private Division division;
	private List<Zona> zonas = null;
	private Zona zona;
	private List<Representante> representantes = null;
	private List<Representante> representantesBak = null;
	private Representante representante;
	private List<TipoPago> tiposPago = null;
	private TipoPago tipoPago;
	private List<Orden> ordenes = null;
	private Orden orden;
	private List<RazonesDevolucion> razonesDeDevolucion = null;
	private RazonesDevolucion razonDevolucion;
	private List<TipoLiquidacion> tiposLiquidacion = null;
	private TipoLiquidacion tipoLiquidacion;
	private List<EstadoOrden> edosOrden = null;
	private EstadoOrden edoOrden;
	private List<TipoSiniestro> tipoSiniestros = null;
	private TipoSiniestro tipoSiniestro;
	private List<Unitarios> unitarios = null;
	private Unitarios unitario;
	private List<Premios> premios = null;
	private Premios premio;
	private List<ProgramacionReparto> progRepartos = null;
	private ProgramacionReparto progReparto;
	
	/* ******************************* Atributos Generales de Catalogos ********************************** */
	private String descripcion = "";
	private String clave = "";
	private String nombre="";
	private String lastupd_ts= "";
	private String fechaActualizado = "", usuarioActualiza = "";
	private String cveRuta = ""; //No usa IDRUTA
	private Integer anioCampania = 0;
	private Integer cveCampania = 0;  //campania en BD
	//C5-Representante
	private String registro = "", domicilio = "", estado= "";	
	private double montoActual = 0, montoPrevio = 0;
	private String telefono = "", coPostal = "", poblacion = "";

	/* ***************************** Atributos de Dependencias de los Catalogos ***************************** */
	private Integer idRepresentante = 0;	private String descRepresentante = "";	 //Registro	
	private Integer idPrimeraOrden = 0;		private String descPrimeraOrden = ""; //0-1
	private Integer pagoEfectivo = -1;		private String siPagoEfectivo = "";
	private Integer trabaja = -1;			private String siTrabaja = "";
	
	/* ***************************** Instancias del STUB Web Service ******************************************** */
	ConsultarCatalogosControllerStub ct = null;
	
	/* ***************************** Banderas de Validacion ***************************************************** */
	private Boolean consultarActivado = true;
	
	/* ***************************** Atributos para aplicar Filtros en DataTable ********************************** */
	private String anioCampania2 = ""; //Filtros Catalogo 2 - Campania
	private String campania2 = "";
	private String zona2 = "";
	private String registro5 = ""; //Filtros Catalogo 5- Representante
	private String nombre5 = "";
	private String registro7 = ""; //Filtros Catalogo 7- Ordenes
	private String anioCampania7 = "";
	private String campania7 = "";
	private String ruta7 = "";
	private String orden7 = "";
	private String campania12 = ""; //Filtros Catalogo 12- Unitarios
	private String anioCampania12 = "";
	private String orden12 = "";
	private String codigo12 = "";
	private String campania13 = ""; //Filtros Catalogo 13- Premios
	private String anioCampania13 = "";
	private String orden13 = "";
	private String codigo13 = "";
	private String campania14 = ""; //Filtros Catalogo 14- Programacion de Repartos
	private String anioCampania14 = "";
	private String zona14 = "";
	
	private Utils utils;
	private List<SelectItem> listZonasFiltro;
	private List<SelectItem> listRutasFiltro;
	private List<SelectItem> listCampaniasFiltro;
	private String zonaFiltro = "";
	private String rutaFiltro = "";
	private String campaniaFiltro = "";
	private String accountFiltro = "";
	private Boolean habilitaBtnEditRep = false;
	private UIPanel pnlRepresen;
	private Boolean domIncorrecto;
	private Boolean domIncorrectoAnt;
	
	HashMap<String, Boolean> mapa = new HashMap<String, Boolean>(); 	//Mapa de Permisos por Modulo
	
	List<SelectItem> listCatalogos = null;
	/**
	 * Constructor - SetUp
	 * @author brenda.estrada
	 * @since 17/01/2012
	 */
	public ControllerConsultarCatalogosAVON() {
		//Inicializa componentes
		getMapaAccion();
		utils = new Utils();
	}
	
	/**
	 * @author javier.gallegos
	 * @since 19-01-2012
	 * @return metodo que baja de sesion el mapa de acciones y establece las
	 *         propias del modulo en la variable mapaAccion
	 */
	public HashMap<String, Boolean> getMapaAccion() {
		//HashMap<String, Boolean> mapa = new HashMap<String, Boolean>();
		HashMap<Integer, HashMap<String, Boolean>> mapaFull = (HashMap<Integer, HashMap<String, Boolean>>) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("accionesM");
		mapa = mapaFull.get(3);// el 1 es el id del modulo hay que ver como
								// hacerlo dinamico
		return mapa;
	}
	/* **********************  SELECTITEM  ************************************************************* */
	/**
	 * Lista fija de Catalogos existentes
	 * @author brenda.estrada
	 * @since 18/01/2012
	 * @return Lista catalogos de AVON
	 */
	public List<SelectItem> getCargaCatalogos() {
		listCatalogos = new ArrayList<SelectItem>();
		listCatalogos.add(new SelectItem(0, "Selecciona una opción"));
		listCatalogos.add(new SelectItem(1, "Bancos"));
		listCatalogos.add(new SelectItem(2, "Campañas"));
		listCatalogos.add(new SelectItem(3, "División"));
		listCatalogos.add(new SelectItem(4, "Zonas"));
		listCatalogos.add(new SelectItem(5, "Representante"));
		listCatalogos.add(new SelectItem(6, "Tipo de Pago"));
		listCatalogos.add(new SelectItem(7, "Ordenes"));
		listCatalogos.add(new SelectItem(8, "Razones de Devolución"));
		listCatalogos.add(new SelectItem(9, "Tipos de Liquidación"));
		listCatalogos.add(new SelectItem(10, "Estado de Orden"));
		listCatalogos.add(new SelectItem(11, "Tipo de Siniestro"));
		listCatalogos.add(new SelectItem(12, "Unitarios"));
		listCatalogos.add(new SelectItem(13, "Premios"));
		listCatalogos.add(new SelectItem(14, "Programación de Reparto"));
		/* Valida el Mapa de Permisos - Se agregan elementos de acuerdo a Permiso */
		listCatalogos = validaCatalogosPermitidos();
		return listCatalogos;
	}
	
	/**
	 * Valida los permisos del Perfil para consultar los Catalogos.
	 * @return
	 * @author brenda.estrada
	 * @since 16/02/2012
	 */
	public List<SelectItem> validaCatalogosPermitidos(){
		List<SelectItem> catPermitidos = new ArrayList<SelectItem>();
		catPermitidos = listCatalogos;
		try{
			if(getMapaAccion().get("SEE_BANK") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_CAMP") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_DIVISION") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_ZONA") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_REPRESENTANTE") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_TIPOPAGO") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_ORDEN") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_RDEVOLUCION") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_TLIQUIDA") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_EDORDEN") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_TSINIESTRO") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_UNITARIO") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_PREMIO") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_PROGRA") == null){
				catPermitidos = catalogosPermitidos();
			}
		}catch(NullPointerException nil){  }
		return catPermitidos;
	}
	/**
	 * Maneja List[SelectItem] para remover una posicion en especifico y de acuerdo a permiso.
	 * @return
	 * @author brenda.estrada
	 * @since 16/02/2012
	 */
	public List<SelectItem> catalogosPermitidos(){
		//Manejo interno de Lista
		List<SelectItem> catPermitidos = new ArrayList<SelectItem>();
		catPermitidos = listCatalogos;
		int val = catPermitidos.size();
		for(int i=0;i<val;i++){
			if(catPermitidos.get(i).getLabel().equals("Bancos")){
				if(getMapaAccion().get("SEE_BANK") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Campañas")){
				if(getMapaAccion().get("SEE_CAMP") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("División")){
				if(getMapaAccion().get("SEE_DIVISION") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Zonas")){
				if(getMapaAccion().get("SEE_ZONA") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Representante")){
				if(getMapaAccion().get("SEE_REPRESENTANTE") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Tipo de Pago")){
				if(getMapaAccion().get("SEE_TIPOPAGO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Ordenes")){
				if(getMapaAccion().get("SEE_ORDEN") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Razones de Devolución")){
				if(getMapaAccion().get("SEE_RDEVOLUCION") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Tipos de Liquidación")){
				if(getMapaAccion().get("SEE_TLIQUIDA") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Estado de Orden")){
				if(getMapaAccion().get("SEE_EDORDEN") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Tipo de Siniestro")){
				if(getMapaAccion().get("SEE_TSINIESTRO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Unitarios")){
				if(getMapaAccion().get("SEE_UNITARIO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Premios")){
				if(getMapaAccion().get("SEE_PREMIO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Programación de Reparto")){
				if(getMapaAccion().get("SEE_PROGRA") == null){
					catPermitidos.remove(i);
					break;
				}
			}
		}
		return catPermitidos;
	}
	/**
	 * Inicializa valores de los filtros.
	 * 
	 * @author brenda.estrada
	 * @since 14/02/2012
	 */
	public void inicializarFiltros(){
		this.msg = "";
	}
	
	/**
	 * Lista fija de Trabaja
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Lista catalogos de AVON
	 */
	public List<SelectItem> getCargaTrabajos() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(-1, "Selecciona una opción"));
		list.add(new SelectItem(1, "Si"));
		list.add(new SelectItem(0, "No"));
		return list;
	}
	
	/**
	 * Lista fija de Pago Efectivo
	 * @author brenda.estrada
	 * @since 18/01/2012
	 * @return Lista catalogos de AVON
	 */
	public List<SelectItem> getCargaPagoEfectivo() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(-1, "Selecciona una opción"));
		list.add(new SelectItem(1, "Si"));
		list.add(new SelectItem(0, "No"));
		return list;
	}
	
	/**
	 * Valida la opcion seleccionada del Combo y muestra el form correspondiente
	 * @author brenda.estrada
	 * @since 18/01/2011
	 * @return Form rendered true en interfaz
	 */
	public void cargarCatalogoSeleccionado(ActionEvent event) {
		if(this.idCatalogo!=0){
			reiniciarForms();
			//Validar opcion seleccionada
			if(this.idCatalogo == 1){
				pnl1 = true;
			}else if(this.idCatalogo == 2){
				pnl2 = true;
			}else if(this.idCatalogo == 3){
				pnl3 = true;
			}else if(this.idCatalogo == 4){
				pnl4 = true;
			}else if(this.idCatalogo == 5){
				pnl5 = true;
			}else if(this.idCatalogo == 6){
				pnl6 = true;
			}else if(this.idCatalogo == 7){
				pnl7 = true;
			}else if(this.idCatalogo == 8){
				pnl8 = true;
			}else if(this.idCatalogo == 9){
				pnl9 = true;
			}else if(this.idCatalogo == 10){
				pnl10 = true;
			}else if(this.idCatalogo == 11){
				pnl11 = true;
			}else if(this.idCatalogo == 12){
				pnl12 = true;
			}else if(this.idCatalogo == 13){
				pnl13 = true;
			}else if(this.idCatalogo == 14){
				pnl14 = true;
			}
			try{
				cargaDatosIniciales(); //Cargar datos iniciales
			}catch(NullPointerException ex){
				msg = "Ha ocurrido un error al obtener los datos iniciales.";
			}
		}
	}
	
	/* **********************  OPERACIONES CONSULTA ************************************************************* */
	/* ******************************************** ************************************************************* */
	/* ******************************************** ************************************************************* */
	
	/**
	 * Metodo que ejecuta el WS que corresponda a id catalogo seleccionado. LLena una lista para mostrar en Datatable
	 * @author brenda.estrada
	 * @since 19/01/2012
	 */
	public void cargaDatosIniciales(){
		try{
			inicializarFiltros();
			if(this.idCatalogo == 1){
				ConsultarCatalogosControllerStub.GetBancosExistentes req = null;
				ConsultarCatalogosControllerStub.GetBancosExistentesResponse res = null;
				bancos = new ArrayList<Bancos>();
				com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.Bancos[] arrData = null;
				try {
					ct = new ConsultarCatalogosControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
					
					//Agrega al request la operacion a ejecutar
					req = new ConsultarCatalogosControllerStub.GetBancosExistentes();
					//Añade al response la respuesta de la operacion
					res = ct.getBancosExistentes(req);
					//variable de tipo heredada del WS
					arrData = res.get_return();
					if(arrData == null){
						msg = "No existen datos.";
					}else{
						//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
						for(int i=0;i<arrData.length;i++){
							//Añade los datos a la lista tipo Ruta de forma local mediante el constructor
							bancos.add(new Bancos(arrData[i].getIdBanco(), arrData[i].getClave(), arrData[i].getNombre(), arrData[i].getLastupd_ts(), arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza()));
						}
					}
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.04", "M16", "Error al invocar el web service que consulta los datos de Bancos.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el web service  que consulta los datos de Bancos.";
				}finally{  }
			}
			if(this.idCatalogo == 2){
				obtenerCatalogo2(anioCampania2, campania2, zona2);
			}
			if(this.idCatalogo == 3){
				ConsultarCatalogosControllerStub.GetDivisionesExistentes req = null;
				ConsultarCatalogosControllerStub.GetDivisionesExistentesResponse res = null;
				divisiones = new ArrayList<Division>();
				com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.Division[]  arrData = null;
				try {
					ct = new ConsultarCatalogosControllerStub();
					
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					
					//Agrega al request la operacion a ejecutar
					req = new ConsultarCatalogosControllerStub.GetDivisionesExistentes();
					//Añade al response la respuesta de la operacion
					res = ct.getDivisionesExistentes(req);
					//variable de tipo heredada del WS
					arrData = res.get_return();
					if(arrData == null){
						msg = "No existen datos.";
					}else{
						//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
						for(int i=0;i<arrData.length;i++){
							//Añade los datos a la lista tipo Ruta de forma local mediante el constructor
							divisiones.add(new Division(arrData[i].getIdDivision(), arrData[i].getNombre(), arrData[i].getAreaGeografica(), 
									arrData[i].getAdmor(), arrData[i].getAsistente(), arrData[i].getLastupd_ts(), arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza()));
						}
					}
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.04", "M18", "Error al invocar el web service que consulta los datos de Division.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que consulta los datos de Division.";
				}finally{  }
			}
			if(this.idCatalogo == 4){
				ConsultarCatalogosControllerStub.GetZonasExistentes req = null;
				ConsultarCatalogosControllerStub.GetZonasExistentesResponse res = null;
				zonas = new ArrayList<Zona>();
				com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.Zona[] arrData = null;
				try {
					ct = new ConsultarCatalogosControllerStub();
					
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
					
					//Agrega al request la operacion a ejecutar
					req = new ConsultarCatalogosControllerStub.GetZonasExistentes();
					//Añade al response la respuesta de la operacion
					res = ct.getZonasExistentes(req);
					//variable de tipo heredada del WS
					arrData = res.get_return();
					if(arrData == null){
						msg = "No existen datos.";
					}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista mediante el constructor
						zonas.add(new Zona(arrData[i].getIdZona(), arrData[i].getZona(), arrData[i].getAnioCampaniaActual(), arrData[i].getCampania(),
								arrData[i].getLastUpdTs(), arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza(), arrData[i].getIdLDC(),
								arrData[i].getDescLDC(), arrData[i].getIdDivision(), arrData[i].getDescDivision(), arrData[i].getTipoZona(), arrData[i].getNombreGerenteZonal()));
					}
					}
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.04", "M19", "Error al invocar el web service de consulta los datos de Zonas.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que consulta los datos de Zonas.";
				}finally{  }
			}
			if(this.idCatalogo == 5){
				iniciaCatRepresentante();
			}
			if(this.idCatalogo == 6){
				ConsultarCatalogosControllerStub.GetTiposPagoExistentes req = null;
				ConsultarCatalogosControllerStub.GetTiposPagoExistentesResponse res = null;
				tiposPago = new ArrayList<TipoPago>();
				com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.TipoPago[] arrData = null;
				try {
					ct = new ConsultarCatalogosControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					req = new ConsultarCatalogosControllerStub.GetTiposPagoExistentes();
					//Añade al response la respuesta de la operacion
					res = ct.getTiposPagoExistentes(req);
					//marcs variable de tipo heredada del WS
					arrData = res.get_return();
					if(arrData == null){
						msg = "No existen datos.";
					}else{
						//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
						for(int i=0;i<arrData.length;i++){
							//Añade los datos a la lista mediante el constructor
							tiposPago.add(new TipoPago(arrData[i].getIdTipoPuesto(), arrData[i].getDescripcion(), arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza()));
						}
					}
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.04", "M21", "Error al invocar el web service de consulta los datos de Tipo de Pagos.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que consulta los datos de Tipo de Pagos.";
				}finally{  }
			}
			if(this.idCatalogo == 7){
				obtenerCatalogo7(registro7, anioCampania7, campania7, ruta7, orden7);
			}
			if(this.idCatalogo == 8){
				ConsultarCatalogosControllerStub.GetRazonesDevolucionExistentes req = null;
				ConsultarCatalogosControllerStub.GetRazonesDevolucionExistentesResponse res = null;
				razonesDeDevolucion = new ArrayList<RazonesDevolucion>();
				com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.RazonesDevolucion[] arrData = null;
				try {
					ct = new ConsultarCatalogosControllerStub();
					
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					
					//Agrega al request la operacion a ejecutar
					req = new ConsultarCatalogosControllerStub.GetRazonesDevolucionExistentes();
					//Añade al response la respuesta de la operacion
					res = ct.getRazonesDevolucionExistentes(req);
					// variable de tipo heredada del WS
					arrData = res.get_return();
					if(arrData == null){
						msg = "No existen datos.";
					}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
						for(int i=0;i<arrData.length;i++){
							//Añade los datos a la lista de forma local mediante el constructor
							razonesDeDevolucion.add(new RazonesDevolucion(arrData[i].getIdRazonDevolucion(), arrData[i].getDescripcion(), 
									arrData[i].getLastupd_ts(), arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza()));
						}
					}
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.04", "M23", "Error al invocar el web service de consulta los datos de Razones de Devolución.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que consulta los datos de Razones de Devolución.";
				}finally{  }
			}
			if(this.idCatalogo == 9){
				ConsultarCatalogosControllerStub.GetTiposLiquidacionExistentes req = null;
				ConsultarCatalogosControllerStub.GetTiposLiquidacionExistentesResponse res = null;
				tiposLiquidacion = new ArrayList<TipoLiquidacion>();
				com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.TipoLiquidacion[] arrData = null;
				try {
					ct = new ConsultarCatalogosControllerStub();
					
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					
					//Agrega al request la operacion a ejecutar
					req = new ConsultarCatalogosControllerStub.GetTiposLiquidacionExistentes();
					//Añade al response la respuesta de la operacion
					res = ct.getTiposLiquidacionExistentes(req);
					// variable heredada del WS
					arrData = res.get_return();
					if(arrData == null){
						msg = "No existen datos.";
					}else{
						//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
						for(int i=0;i<arrData.length;i++){
							//Añade los datos a la lista de forma local mediante el constructor
							tiposLiquidacion.add(new TipoLiquidacion(arrData[i].getIdTipoLiquidacion(), arrData[i].getDescripcion(), arrData[i].getClave(),
									arrData[i].getLastupd_ts(), arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza()));
						}
					}
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.04", "M24", "Error al invocar el web service de consulta los datos de Tipo de Liquidación.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que consulta los datos de Tipo de Liquidación.";
				}finally{  }
			}
			if(this.idCatalogo == 10){
				ConsultarCatalogosControllerStub.GetEstadosOrdenExistentes req = null;
				ConsultarCatalogosControllerStub.GetEstadosOrdenExistentesResponse res = null;
				edosOrden = new ArrayList<EstadoOrden>();
				com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.EstadoOrden[] arrData = null;
				try {
					ct = new ConsultarCatalogosControllerStub();
					
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					
					//Agrega al request la operacion a ejecutar
					req = new ConsultarCatalogosControllerStub.GetEstadosOrdenExistentes();
					//Añade al response la respuesta de la operacion
					res = ct.getEstadosOrdenExistentes(req);
					// variable heredada del WS
					arrData = res.get_return();
					if(arrData == null){
						msg = "No existen datos.";
					}else{
						//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
						for(int i=0;i<arrData.length;i++){
							//Añade los datos a la lista de forma local mediante el constructor
							edosOrden.add(new EstadoOrden(arrData[i].getIdEdoOrden(), arrData[i].getTipoDlv(), arrData[i].getDescripcion(),
									arrData[i].getLastupd_ts(), arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza()));
						}
					}
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.04", "M25", "Error al invocar el web service de consulta los datos de Estado de la Orden.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que consulta los datos de Estado de la Orden.";
				}finally{  }
			}
			if(this.idCatalogo == 11){
				ConsultarCatalogosControllerStub.GetTiposSiniestroExistentes req = null;
				ConsultarCatalogosControllerStub.GetTiposSiniestroExistentesResponse res = null;
				tipoSiniestros  = new ArrayList<TipoSiniestro>();
				com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.TipoSiniestro[] arrData = null;
				try {
					ct = new ConsultarCatalogosControllerStub();
					
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					
					//Agrega al request la operacion a ejecutar
					req = new ConsultarCatalogosControllerStub.GetTiposSiniestroExistentes();
					//Añade al response la respuesta de la operacion
					res = ct.getTiposSiniestroExistentes(req);
					// variable heredada del WS
					arrData = res.get_return();
					if(arrData == null){
						msg = "No existen datos.";
					}else{
						//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
						for(int i=0;i<arrData.length;i++){
							//Añade los datos a la lista de forma local mediante el constructor
							tipoSiniestros.add(new TipoSiniestro(arrData[i].getIdTipoSiniestro(), arrData[i].getClave(), arrData[i].getDescripcion(),
									arrData[i].getLastupd_ts(), arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza()));
						}
					}
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.04", "M26", "Error al invocar el web service de consulta los datos de Tipos de Siniestro.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que consulta los datos de Tipos de Siniestro.";
				}finally{  }
			}
			if(this.idCatalogo == 12){
				obtenerCatalogo12(campania12, anioCampania12, orden12, codigo12);
			}
			if(this.idCatalogo == 13){
				obtenerCatalogo13(campania13, anioCampania13, orden13, codigo13);
			}
			if(this.idCatalogo == 14){
				obtenerCatalogo14(campania14, anioCampania14, zona14);
			}
		}catch(NullPointerException nil){  }
	}
	
	/* ***************** Ejecuta el metodo llamado desde el filtro en la vista ************************************************************************************************************** */
	/**
	 * Obtiene el valor de Anio Campania de la vista para filtrar los datos del array[campanias]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo2AnioParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.anioCampania2 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo2(anioCampania2, campania2, zona2);
	}
	
	/**
	 * Obtiene el valor de Campania de la vista para filtrar los datos del array[campanias]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo2CampaniaParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.campania2 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo2(anioCampania2, campania2, zona2);
	}
	
	/**
	 * Obtiene el valor de Campania de la vista para filtrar los datos del array[campanias]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 14/03/2012
	 */
	public void getCatalogo2ZonaParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.zona2 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo2(anioCampania2, campania2, zona2);
	}
	
	/**
	 * Obtiene el valor de Registro de la vista para filtrar los datos del array[representantes]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo5RegistroParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.registro5 = valor.toLowerCase();
		filtraRepresentantes ();
	}
	/**
	 * Obtiene el valor de Nombre de la vista para filtrar los datos del array[representantes]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo5NombreParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.nombre5 = valor.toUpperCase();
		filtraRepresentantes ();
	}
	
	public void filtraRepresentantes () {
		List<Representante> representantesQuitar = new ArrayList<Representante>();
		representantes = new ArrayList<Representante>();
		nombre5 = nombre5.trim().toUpperCase();
		registro5 = registro5.trim();
		for (Representante rep : representantesBak) {
			representantes.add(rep);
		}

		for (Representante rep : representantes) {
			if (!rep.getNombre().contains(nombre5)
					|| !rep.getRegistro().contains(registro5)) {
				representantesQuitar.add(rep);
			}
		}
		
		representantes.removeAll(representantesQuitar);
		
		if (representantes.size() == 1) {
			llenaEdicionRepresentantes();
		} else {
			habilitaBtnEditRep = true;
			pnlRepresen.setRendered(false);
		}
	}
	
	/**
	 * Obtiene el valor de Registro de la vista para filtrar los datos del array[ordenes]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo7RegistroParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.registro7 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo7(registro7, anioCampania7, campania7, ruta7, orden7);
	}
	/**
	 * Obtiene el valor de Anio Campania de la vista para filtrar los datos del array[ordenes]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo7AnioParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.anioCampania7 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo7(registro7, anioCampania7, campania7, ruta7, orden7);
	}
	/**
	 * Obtiene el valor de Campania de la vista para filtrar los datos del array[ordenes]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo7CampaniaParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.campania7 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo7(registro7, anioCampania7, campania7, ruta7, orden7);
	}
	/**
	 * Obtiene el valor de Clave de Ruta de la vista para filtrar los datos del array[ordenes]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo7RutaParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.ruta7 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo7(registro7, anioCampania7, campania7, ruta7, orden7);
	}
	/**
	 * Obtiene el valor de Clave de Orden de la vista para filtrar los datos del array[ordenes]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo7OrdenParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.orden7 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo7(registro7, anioCampania7, campania7, ruta7, orden7);
	}
	/**
	 * Obtiene el valor de Campania de la vista para filtrar los datos del array[unitarios]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo12CampaniaParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.campania12 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo12(campania12, anioCampania12, orden12, codigo12);
	}
	/**
	 * Obtiene el valor de Anio Campania de la vista para filtrar los datos del array[unitarios]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo12AnioParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.anioCampania12 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo12(campania12, anioCampania12, orden12, codigo12);
	}
	/**
	 * Obtiene el valor de Clave de Orden de la vista para filtrar los datos del array[unitarios]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo12OrdenParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.orden12 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo12(campania12, anioCampania12, orden12, codigo12);
	}
	/**
	 * Obtiene el valor de Codigo de Barras de la vista para filtrar los datos del array[unitarios]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo12CodigoParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.codigo12 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo12(campania12, anioCampania12, orden12, codigo12);
	}
	/**
	 * Obtiene el valor de Campania de la vista para filtrar los datos del array[premios]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo13CampaniaParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.campania13 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo13(campania13, anioCampania13, orden13, codigo13);
	}
	/**
	 * Obtiene el valor de Anio Campania de la vista para filtrar los datos del array[premios]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo13AnioParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.anioCampania13 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo13(campania13, anioCampania13, orden13, codigo13);
	}
	/**
	 * Obtiene el valor de Clave de Orden de la vista para filtrar los datos del array[premios]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo13OrdenParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.orden13 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo13(campania13, anioCampania13, orden13, codigo13);
	}
	/**
	 * Obtiene el valor de Codigo de Barras de la vista para filtrar los datos del array[premios]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo13CodigoParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.codigo13 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo13(campania13, anioCampania13, orden13, codigo13);
	}
	/**
	 * Obtiene el valor de Campania de la vista para filtrar los datos del array[progRepartos]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo14CampaniaParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.campania14 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo14(campania14, anioCampania14, zona14);
	}
	/**
	 * Obtiene el valor de Anio Campania de la vista para filtrar los datos del array[progRepartos]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo14AnioParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.anioCampania14 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo14(campania14, anioCampania14, zona14);
	}
	/**
	 * Obtiene el valor de Clave de Zona de la vista para filtrar los datos del array[progRepartos]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo14ZonaParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.zona14 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo14(campania14, anioCampania14, zona14);
	}
	/* ***************** Llama el metodo para consultar los datos con filtros ************************************************************************************************************** */
	/**
	 * Consulta los datos de Campanias, filtrando por parametros
	 * @param anioCampania
	 * @param campania
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void obtenerCatalogo2(String pAnioCampania, String pCampania, String pZona){
		ConsultarCatalogosControllerStub.GetCampaniasExistentes req = null;
		ConsultarCatalogosControllerStub.GetCampaniasExistentesResponse res = null;
		campanias = new ArrayList<Campania>();
		com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.Campania[]   arrData = null;
		try {
			ct = new ConsultarCatalogosControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			
			//Agrega al request la operacion a ejecutar
			req = new ConsultarCatalogosControllerStub.GetCampaniasExistentes();
			req.setPAnio(pCampania);		req.setPAnioCampania(pAnioCampania);
			req.setPZona(pZona);
			//Añade al response la respuesta de la operacion
			res = ct.getCampaniasExistentes(req);
			arrData = res.get_return();
			if(arrData == null){
				msg = "No existen datos.";
			}else{
				//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
				for(int i=0;i<arrData.length;i++){
					//Añade los datos a la lista de forma local mediante el constructor
					campanias.add(new Campania(arrData[i].getIdCampania(), arrData[i].getAnioCampania(), 
							arrData[i].getCampania(), arrData[i].getFechaInicio(), arrData[i].getFechaFin(), 
							arrData[i].getLastUpdTs(), arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza(),
							arrData[i].getIdZona(), arrData[i].getDescZona()));

				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.04", "M17", "Error al invocar el web service que consulta los datos de Campanias.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Campanias.";
		}finally{  }
	}
	
	/**
	 * Inicia el catalogo de representantes
	 * @author moises hernandez
	 * @since 6/11/2014
	 */
	public void iniciaCatRepresentante () {
		
		habilitaBtnEditRep = true;
		listCampaniasFiltro = new ArrayList<SelectItem>();
		listZonasFiltro = new ArrayList<SelectItem>();
		listRutasFiltro = new ArrayList<SelectItem>();
		representantes = new ArrayList<Representante>();
		campaniaFiltro = "0";
		zonaFiltro = "0";
		rutaFiltro = "0";
		accountFiltro = "";
		pnlRepresen.setRendered(false);

		listRutasFiltro.add(new SelectItem("0", "Selecciona una opción"));
		
		ZonaControllerStub.Zona[] zonas = utils.getZonas(config.getIdLDC(), "CUADMIN039_1");
		listZonasFiltro.add(new SelectItem("0", "Selecciona una opción"));
		if (zonas != null && zonas.length > 0) {
			for (int i = 0; i < zonas.length; i++) {
				listZonasFiltro.add(new SelectItem(zonas[i].getIdZona(), zonas[i]
						.getZona()));
			}
		}
		
		try {
			CampaniaControllerStub campaniaStub = new CampaniaControllerStub();
			String url = Utils.modificarUrlServicioWeb(campaniaStub
					._getServiceClient().getOptions().getTo().getAddress());
			campaniaStub
					._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			GetUltimasCampaniasSinID campanias = new GetUltimasCampaniasSinID();
			campanias.setTipoCasoUso("CUADMIN039_1");
			campanias.setIdUsuario(0);
			CampaniaControllerStub.GetUltimasCampaniasSinIDResponse response;
			response = campaniaStub
					.getUltimasCampaniasSinID(campanias);
			CampaniaControllerStub.Campania[] arregloCampanias = response.get_return();
			
			listCampaniasFiltro.add(new SelectItem("0", "Selecciona una opción"));
			if (arregloCampanias != null && arregloCampanias.length > 0) {
				for (int i = 0; i < arregloCampanias.length; i++) {
					listCampaniasFiltro.add(new SelectItem(arregloCampanias[i].getUsuarioActualiza(), 
							arregloCampanias[i].getUsuarioActualiza()));
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getRutasPorZona(ValueChangeEvent e) {
		
		representantes = new ArrayList<Representante>();
		representantesBak = new ArrayList<Representante>();
		cancelarCatalogo();
		setZonaFiltro(e.getNewValue().toString());
		listRutasFiltro = new ArrayList<SelectItem>();
		listRutasFiltro.add(new SelectItem("0", "Selecciona una opción"));
		
		if (!zonaFiltro.equals("0")) {
			Rutas[] arrRutas = utils.getRutas("CUADMIN039_1", config.getIdLDC(),
					Integer.parseInt(zonaFiltro), 0);
			
			try {
				if (arrRutas != null) {
					for (int i = 0; i < arrRutas.length; i++) {
						listRutasFiltro.add(new SelectItem(arrRutas[i].getIdRuta(),
								arrRutas[i].getCveRuta() +" - " + arrRutas[i].getDescTipoRuta()));
					}
				}
				
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void limpiaGridRepresentantes (ValueChangeEvent e) {
		representantes = new ArrayList<Representante>();
		representantesBak = new ArrayList<Representante>();
		cancelarCatalogo();
	}
	
	/**
	 * Consulta los datos de Representantes, filtrando por parametros
	 * @param pRegistro
	 * @param pNombre
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void obtenerCatalogo5(){
		ConsultarCatalogosControllerStub.GetRepresentantesExistentes req = null;
		ConsultarCatalogosControllerStub.GetRepresentantesExistentesResponse res = null;
		representantes = new ArrayList<Representante>();
		representantesBak = new ArrayList<Representante>();
		com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.Representante[] arrData = null;
		nombre5 = "";
		registro5 = "";
		msg = "";
		habilitaBtnEditRep = true;
		int pagina = 1;
		int tamPagina = 1500;
		boolean continuar = true;
		pnlRepresen.setRendered(false);
		cancelarCatalogo();
		
		if ((campaniaFiltro == null || campaniaFiltro.equals("0")) && 
				(zonaFiltro == null || zonaFiltro.equals("0")) && 
				(rutaFiltro == null || rutaFiltro.equals("0")) && 
				(accountFiltro == null || accountFiltro.trim().length() == 0)) {
			msg = "No se han ingresado criterios para la búsqueda de Representantes";
		} else {
			try {
				ct = new ConsultarCatalogosControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				
				//Agrega al request la operacion a ejecutar
				req = new ConsultarCatalogosControllerStub.GetRepresentantesExistentes();
				
				req.setTamPagina(tamPagina);
				
				if (!accountFiltro.isEmpty()) {
					req.setPRegistro(accountFiltro);
				}
				
				if (!rutaFiltro.equals("0")) {
					req.setPRuta(rutaFiltro);
				}
				
				if (!zonaFiltro.equals("0")) {
					req.setPZona(zonaFiltro);
				}
				
				if (!campaniaFiltro.equals("0")) {
					String campania = campaniaFiltro.substring(0, 2);
					String anio = campaniaFiltro.substring(2);
					if (String.valueOf(campania.charAt(0)).equals("0")) {
						campania = campania.substring(1);
					}
					req.setPAnioCampania(anio);
					req.setPCampania(campania);
				} 
				
				while (continuar) {
					req.setPagina(pagina);
					//Añade al response la respuesta de la operacion
					res = ct.getRepresentantesExistentes(req);
					//variable de heredada del WS
					arrData = res.get_return();
					if(arrData != null) {
						
						//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
						for(int i=0;i<arrData.length;i++){
							//Añade los datos a la lista tipo Ruta de forma local mediante el constructor
							representantes.add(new Representante(arrData[i].getIdRepresentante(), arrData[i].getIdZona(), arrData[i].getDescZona(), arrData[i].getRegistro(),
									arrData[i].getNombre(), arrData[i].getDomicilio(), arrData[i].getEstado(), arrData[i].getEstatus(), arrData[i].getIdEstatus(), 
									arrData[i].getMontoActual(), arrData[i].getMontoPrevio(), arrData[i].getTelefono(), arrData[i].getCoPostal(), arrData[i].getPagoEfectivo(),
									arrData[i].getTrabaja(), arrData[i].getLastUpdTs(), arrData[i].getPoblacion(), arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza(),
									arrData[i].getSiPagoEfectivo(), arrData[i].getSiTrabaja(), arrData[i].getCveRuta(), arrData[i].getDomIncorrecto())); 
							
							representantesBak.add(new Representante(arrData[i].getIdRepresentante(), arrData[i].getIdZona(), arrData[i].getDescZona(), arrData[i].getRegistro(),
									arrData[i].getNombre(), arrData[i].getDomicilio(), arrData[i].getEstado(), arrData[i].getEstatus(), arrData[i].getIdEstatus(), 
									arrData[i].getMontoActual(), arrData[i].getMontoPrevio(), arrData[i].getTelefono(), arrData[i].getCoPostal(), arrData[i].getPagoEfectivo(),
									arrData[i].getTrabaja(), arrData[i].getLastUpdTs(), arrData[i].getPoblacion(), arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza(),
									arrData[i].getSiPagoEfectivo(), arrData[i].getSiTrabaja(), arrData[i].getCveRuta(), arrData[i].getDomIncorrecto())); 
						}
						
						pagina++;
						// Se define el tamano de las paginas de 1000, si el tamano del arreglo que regresa es menor quiere decir que no hay mas registros
						if (arrData.length < tamPagina) {
							continuar = false;
						}
						
					} else {
						continuar = false;
					}
				}
				
				if (representantes.size() == 0) {
					msg = "No hay Representantes con los criterios especificados.";
				} else if (representantes.size() == 1) {
					llenaEdicionRepresentantes();
				}
				
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.04", "M20", "Error al invocar el web service de consulta los datos de Representante.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Representante.";
			} 
		}
		
	}
	
	public void llenaEdicionRepresentantes () {
		this.setIdRepresentante(representantes.get(0)
				.getIdRepresentante());
		this.setTrabaja(new Byte(representantes.get(0).getTrabaja())
				.intValue());
		this.setSiTrabaja(representantes.get(0).getSiTrabaja());
		this.setPagoEfectivo(new Byte(representantes.get(0)
				.getPagoEfectivo()).intValue());
		this.setSiPagoEfectivo(representantes.get(0)
				.getSiPagoEfectivo());
		this.setRegistro(representantes.get(0).getRegistro());
		this.setNombre(representantes.get(0).getNombre());
		this.setDomIncorrecto(representantes.get(0).getDomIncorrecto());
		habilitaBtnEditRep = false;
		pnlRepresen.setRendered(true);
	}
	
	/**
	 * Consulta los datos de Ordenes, filtrando por parametros
	 * @param pRegistro5
	 * @param pAnioCampania5
	 * @param pCampania5
	 * @param pRuta5
	 * @param pOrden5
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void obtenerCatalogo7(String pRegistro7, String pAnioCampania7, String pCampania7, String pRuta7, String pOrden7){
		ConsultarCatalogosControllerStub.GetOrdenesExistentes req = null;
		ConsultarCatalogosControllerStub.GetOrdenesExistentesResponse res = null;
		ordenes = new ArrayList<Orden>();
		com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.Orden[] arrData = null;
		try {
			ct = new ConsultarCatalogosControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			req = new ConsultarCatalogosControllerStub.GetOrdenesExistentes();
			req.setPRegistro(pRegistro7);	req.setPAnioCampania(pAnioCampania7);
			req.setPCampania(pCampania7);	req.setPRuta(pRuta7);
			req.setPOrden(pOrden7);
			//Añade al response la respuesta de la operacion
			res = ct.getOrdenesExistentes(req);
			// variable de tipo heredada del WS
			arrData = res.get_return();
			if(arrData == null){
				msg = "No existen datos.";
			}else{
				//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
				for(int i=0;i<arrData.length;i++){
					//Añade los datos a la lista de forma local mediante el constructor
					ordenes.add(new Orden(arrData[i].getIdOrden(), arrData[i].getIdRepresentante(), arrData[i].getIdLDC(), arrData[i].getNoCajas(),
							arrData[i].getLastupd_ts(), arrData[i].getIdPrimeraOrden(), arrData[i].getCveOrden(), arrData[i].getFecha_actualizado(), 
							arrData[i].getUsuario_actualiza(), arrData[i].getIdCampania(), arrData[i].getIdEstatus(), arrData[i].getDescRepresentante(),
							arrData[i].getDescLDC(), arrData[i].getAnio(), arrData[i].getDescCampania(), arrData[i].getCveRuta(), arrData[i].getDescPrimeraOrden()));
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.04", "M22", "Error al invocar el web service de consulta los datos de Orden.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Orden.";
		}finally{  }
	}
	/**
	 * Consulta los datos de Unitarios, filtrando por parametros
	 * @param pCampania12
	 * @param pAnioCampania12
	 * @param pOrden12
	 * @param pCodigo12
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void obtenerCatalogo12(String pCampania12, String pAnioCampania12, String pOrden12, String pCodigo12){
		ConsultarCatalogosControllerStub.GetUnitariosExistentes req = null;
		ConsultarCatalogosControllerStub.GetUnitariosExistentesResponse res = null;
		unitarios  = new ArrayList<Unitarios>();
		com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.Unitarios[] arrData = null;
		try {
			ct = new ConsultarCatalogosControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			
			//Agrega al request la operacion a ejecutar
			req = new ConsultarCatalogosControllerStub.GetUnitariosExistentes();
			req.setPCampania(pCampania12.trim());	req.setPAnioCampania(pAnioCampania12.trim());
			req.setPOrden(pOrden12.trim());		req.setPCodigo(pCodigo12.trim());
			//Añade al response la respuesta de la operacion
			res = ct.getUnitariosExistentes(req);
			// variable heredada del WS
			arrData = res.get_return();
			if(arrData == null){
				msg = "No existen datos.";
			}else{
				//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
				for(int i=0;i<arrData.length;i++){
					//Añade los datos a la lista de forma local mediante el constructor
					unitarios.add(new Unitarios(arrData[i].getIdUnitario(), arrData[i].getClave(), arrData[i].getDescripcion(), arrData[i].getZona(),
							arrData[i].getCodigo(), arrData[i].getCantidad(), arrData[i].getOrden(), arrData[i].getNoCaja(), arrData[i].getRegistro(),
							arrData[i].getNombre(), arrData[i].getDireccion(), arrData[i].getRuta(), arrData[i].getCampania(), arrData[i].getAnio(),
							arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza()));
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.04", "M27", "Error al invocar el web service de consulta los datos de Unitarios.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Unitarios.";
		}finally{  }
	}
	/**
	 * Consultar datos de Premios aplicando filtros
	 * @param pCampania13
	 * @param pAnioCampania13
	 * @param pOrden13
	 * @param pCodigo13
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void obtenerCatalogo13(String pCampania13, String pAnioCampania13, String pOrden13, String pCodigo13){
		ConsultarCatalogosControllerStub.GetPremiosExistentes req = null;
		ConsultarCatalogosControllerStub.GetPremiosExistentesResponse res = null;
		premios  = new ArrayList<Premios>();
		com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.Premios[] arrData = null;
		try {
			ct = new ConsultarCatalogosControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			
			//Agrega al request la operacion a ejecutar
			req = new ConsultarCatalogosControllerStub.GetPremiosExistentes();
			req.setPCampania(pCampania13);		req.setPAnioCampania(pAnioCampania13);
			req.setPOrden(pOrden13);			req.setPCodigo(pCodigo13);
			//Añade al response la respuesta de la operacion
			res = ct.getPremiosExistentes(req);
			// variable heredada del WS
			arrData = res.get_return();
			if(arrData == null){
				msg = "No existen datos.";
			}else{
				//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
				for(int i=0;i<arrData.length;i++){
					//Añade los datos a la lista de forma local mediante el constructor
					premios.add(new Premios(arrData[i].getIdPremio(), arrData[i].getClaveOrden(), arrData[i].getDescripcion(),
							arrData[i].getCedi(), arrData[i].getMail(), arrData[i].getRegistro(), arrData[i].getCode(), arrData[i].getCampania(),
							arrData[i].getAnio(), arrData[i].getObservaciones(), arrData[i].getFechaActualizado(), arrData[i].getUsuarioActualiza())); 
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.04", "M28", "Error al invocar el web service de consulta los datos de Premios.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Premios.";
		}finally{  }
	}
	/**
	 * Consulta los datos de Programacion de Reparto aplicando filtros.
	 * @param pCampania14
	 * @param pAnioCampania14
	 * @param pZona14
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void obtenerCatalogo14(String pCampania14, String pAnioCampania14, String pZona14){
		ConsultarCatalogosControllerStub.GetProgramacionRepartoExistentes req = null;
		ConsultarCatalogosControllerStub.GetProgramacionRepartoExistentesResponse res = null;
		progRepartos  = new ArrayList<ProgramacionReparto>();
		com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.ProgramacionReparto[] arrData = null;
		try {
			ct = new ConsultarCatalogosControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			req = new ConsultarCatalogosControllerStub.GetProgramacionRepartoExistentes();
			req.setPCampania(pCampania14);	req.setPAnio(pAnioCampania14);	req.setPZona(pZona14);
			//Añade al response la respuesta de la operacion
			res = ct.getProgramacionRepartoExistentes(req);
			// variable heredada del WS
			arrData = res.get_return();
			if(arrData == null){
				msg = "No existen datos.";
			}else{
				for(int i=0;i<arrData.length;i++){
					progRepartos.add(new ProgramacionReparto(arrData[i].getIdProgramacionReparto(), arrData[i].getNoCuartoAnio(), arrData[i].getAnio(),
							arrData[i].getMail(), arrData[i].getFeFacturacion(), arrData[i].getFeLlegadaLDC(), arrData[i].getFeDiaRepartoPrimero(),
							arrData[i].getFeDiaRepartoUltimo(), arrData[i].getFeUltimoDiaBodega(), arrData[i].getFeCierre(), arrData[i].getFeActualizado(),
							arrData[i].getIdZona(), arrData[i].getDescZona(), arrData[i].getIdCampania(), arrData[i].getDescCampania(), arrData[i].getIdLDC(),
							arrData[i].getDescLDC(), arrData[i].getIdDivision(), arrData[i].getDescDivision(), arrData[i].getAnioCampania(), arrData[i].getUsuarioActualiza()));
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.04", "M29", "Error al invocar el web service de consulta los datos de Programación de Reparto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Programación de Reparto.";
		}finally{  }
	}
	
	/* **********************  Operacion UPDATE ************************************************************* */
	/**
	 * Actualiza los datos del Catalogo de Representante mediante el WS
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return  String de exito o falla al invocar el WS.
	 */
	public String actualizarCatalogoRepresentante() {
		ConsultarCatalogosControllerStub.ActualizarRepresentante rUpd = null;
		ConsultarCatalogosControllerStub.ActualizarRepresentanteResponse resIns = null;
		try{
			ct = new ConsultarCatalogosControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			
			//Agrega al request la operacion a ejecutar
			rUpd = new ConsultarCatalogosControllerStub.ActualizarRepresentante();
			//Le setea al request los parametros necesarios
			rUpd.setIdRepresentante(idRepresentante);
			rUpd.setIdUser(config.getIdUsuario());
			rUpd.setSiPagoEfectivo(pagoEfectivo);
			rUpd.setSiTrabaja(trabaja);
			
			if (domIncorrectoAnt && !domIncorrecto) {
				rUpd.setActualizarDomIncorrecto(true);
			} else {
				rUpd.setActualizarDomIncorrecto(false);
			}
			
			//Añade al response el request de la operacion
			resIns = ct.actualizarRepresentante(rUpd);
			//Guarda la respuesta en una variable para ser mostrada en la Vista
//			cargaDatosIniciales();
			obtenerCatalogo5 ();
			msg = resIns.get_return();
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.04", "M30", "Error al invocar el web service que actualiza los datos de Representante.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el web service que actualiza los datos de Representante.";
		}finally{  	this.idRepresentante = 0;	this.siPagoEfectivo = "";
					this.pagoEfectivo = -1;		this.siTrabaja = "";	this.trabaja = -1;
					this.registro ="";			this.nombre="";		
		}
		return msg;
	}
	
	/**
	 * Obtiene los valores del registro seleccionado. Los setea al bean para modificarlos
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Atributos del registro
	 */
	public String modificarCatalogo() {
		inicializarFiltros();
		//valida catalogo seleccionado
		if(this.idCatalogo == 5){
			//pnlRepresentante.setRendered(true);
			this.setIdRepresentante(idRepresentante);
			this.setTrabaja(trabaja);				this.setSiTrabaja(siTrabaja);
			this.setPagoEfectivo(pagoEfectivo);		this.setSiPagoEfectivo(siPagoEfectivo);
			this.setRegistro(registro);				this.setNombre(nombre);
			habilitaBtnEditRep = false;
			pnlRepresen.setRendered(true);
			this.setDomIncorrectoAnt(domIncorrecto);
		}
		return null;
	}
	
	/**
	 * Cancela cualquier accion y reinicia el catalogo
	 * @author brenda.estrada
	 * @since 19/01/2012
	 * @return Forma inicial
	 */
	public String cancelarCatalogo() {
		inicializarFiltros();
		//Si consulto un Representante
		if(this.idCatalogo == 5){
			this.idRepresentante = 0;	this.siPagoEfectivo = "";
			this.pagoEfectivo = -1;		this.siTrabaja = "";	this.trabaja = -1;
			this.registro ="";			this.nombre="";
			habilitaBtnEditRep = true;
			pnlRepresen.setRendered(false);
		}
		//Atributos de Form
		//this.idCatalogo = 0;
		this.descCatalogo = "";
		this.consultarActivado = true;
		return ""; 
	}
	
	/**
	 * Inicializa los forms del catalogo
	 * @author brenda.estrada
	 * @since 19/01/2012
	 */
	public void reiniciarForms(){
		pnl1 = false;			pnl2 = false;
		pnl3 = false;			pnl4 = false;
		pnl5 = false;			pnl6 = false;
		pnl7 = false;			pnl8 = false;
		pnl9 = false;			pnl10 = false;
		pnl11 = false;			pnl12 = false;
		pnl13 = false;			pnl14 = false;
	}
	
	 /* **********************  EXPORTAR GRID A EXCEL Y PDF ************************************************************* */
	 /**
	  * Obtiene la lista del Catalogo Seleccionado y envia para Generar Reporte en EXcel
	  * @author brenda.estrada
	  * @since 25/01/2012
	  * @return Output Excel
	  * @throws IOException
	  */
	 public String exportarGridExcel() throws IOException{
		 JasperGenerator genera = new JasperGenerator();
		 if(this.idCatalogo  == 1){
			 genera.generaGridBancos(this.bancos, "XLS");
		 }
		 if(this.idCatalogo  == 2){
			 genera.generaGridCampanias(this.campanias, "XLS");
		 }
		 if(this.idCatalogo  == 3){
			 genera.generaGridDivisiones(this.divisiones, "XLS");
		 }
		 if(this.idCatalogo  == 4){
			 genera.generaGridZonas(this.zonas, "XLS");
		 }
		 if(this.idCatalogo  == 5){
			 genera.generaGridRepresentantes(this.representantes, "XLS");
		 }
		 if(this.idCatalogo  == 6){
			 genera.generaGridTipoPago(this.tiposPago, "XLS");
		 }
		 if(this.idCatalogo  == 7){
			 genera.generaGridOrden(this.ordenes, "XLS");
		 }
		 if(this.idCatalogo  == 8){
			 genera.generaGridRazonesDevolucion(this.razonesDeDevolucion, "XLS");
		 }
		 if(this.idCatalogo  == 9){
			 genera.generaGridTipoLiquidacion(this.tiposLiquidacion, "XLS");
		 }
		 if(this.idCatalogo  == 10){
			 genera.generaGridEstadoOrden(this.edosOrden, "XLS");
		 }
		 if(this.idCatalogo  == 11){
			 genera.generaGridTipoSiniestro(this.tipoSiniestros, "XLS");
		 }
		 if(this.idCatalogo  == 12){
			 genera.generaGridUnitarios(this.unitarios, "XLS");
		 }
		 if(this.idCatalogo  == 13){
			 genera.generaGridPremios(this.premios, "XLS");
		 }
		 if(this.idCatalogo  == 14){
			 genera.generaGridProgramacionReparto(this.progRepartos, "XLS");
		 }
		 return msg = "";
	 }
	 /**
	  * Obtiene la lista del Catalogo Seleccionado y envia para Generar Reporte en PDF
	  * @author brenda.estrada
	  * @since 25/01/2012
	  * @return Output PDF
	  * @throws IOException
	  */
	 public String exportarGridPDF() throws IOException{
		 JasperGenerator genera = new JasperGenerator();
		 if(this.idCatalogo  == 1){
			 genera.generaGridBancos(this.bancos, "PDF");
		 }
		 if(this.idCatalogo  == 2){
			 genera.generaGridCampanias(this.campanias, "PDF");
		 }
		 if(this.idCatalogo  == 3){
			 genera.generaGridDivisiones(this.divisiones, "PDF");
		 }
		 if(this.idCatalogo  == 4){
			 genera.generaGridZonas(this.zonas, "PDF");
		 }
		 if(this.idCatalogo  == 5){
			 genera.generaGridRepresentantes(this.representantes, "PDF");
		 }
		 if(this.idCatalogo  == 6){
			 genera.generaGridTipoPago(this.tiposPago, "PDF");
		 }
		 if(this.idCatalogo  == 7){
			 genera.generaGridOrden(this.ordenes, "PDF");
		 }
		 if(this.idCatalogo  == 8){
			 genera.generaGridRazonesDevolucion(this.razonesDeDevolucion, "PDF");
		 }
		 if(this.idCatalogo  == 9){
			 genera.generaGridTipoLiquidacion(this.tiposLiquidacion, "PDF");
		 }
		 if(this.idCatalogo  == 10){
			 genera.generaGridEstadoOrden(this.edosOrden, "PDF");
		 }
		 if(this.idCatalogo  == 11){
			 genera.generaGridTipoSiniestro(this.tipoSiniestros, "PDF");
		 }
		 if(this.idCatalogo  == 12){
			 genera.generaGridUnitarios(this.unitarios, "PDF");
		 }
		 if(this.idCatalogo  == 13){
			 genera.generaGridPremios(this.premios, "PDF");
		 }
		 if(this.idCatalogo  == 14){
			 genera.generaGridProgramacionReparto(this.progRepartos, "PDF");
		 }
		 return msg = "";
	 }
	 /**
	  * Obtiene la lista del Catalogo Seleccionado y envia para Generar Reporte en CSV
	  * @author brenda.estrada
	  * @since 25/01/2012
	  * @return Output Excel
	  * @throws IOException
	  */
	 public String exportarGridCSV() throws IOException{
		 JasperGenerator genera = new JasperGenerator();
		 if(this.idCatalogo  == 1){
			 genera.generaGridBancos(this.bancos, "CSV");
		 }
		 if(this.idCatalogo  == 2){
			 genera.generaGridCampanias(this.campanias, "CSV");
		 }
		 if(this.idCatalogo  == 3){
			 genera.generaGridDivisiones(this.divisiones, "CSV");
		 }
		 if(this.idCatalogo  == 4){
			 genera.generaGridZonas(this.zonas, "CSV");
		 }
		 if(this.idCatalogo  == 5){
			 genera.generaGridRepresentantes(this.representantes, "CSV");
		 }
		 if(this.idCatalogo  == 6){
			 genera.generaGridTipoPago(this.tiposPago, "CSV");
		 }
		 if(this.idCatalogo  == 7){
			 genera.generaGridOrden(this.ordenes, "CSV");
		 }
		 if(this.idCatalogo  == 8){
			 genera.generaGridRazonesDevolucion(this.razonesDeDevolucion, "CSV");
		 }
		 if(this.idCatalogo  == 9){
			 genera.generaGridTipoLiquidacion(this.tiposLiquidacion, "CSV");
		 }
		 if(this.idCatalogo  == 10){
			 genera.generaGridEstadoOrden(this.edosOrden, "CSV");
		 }
		 if(this.idCatalogo  == 11){
			 genera.generaGridTipoSiniestro(this.tipoSiniestros, "CSV");
		 }
		 if(this.idCatalogo  == 12){
			 genera.generaGridUnitarios(this.unitarios, "CSV");
		 }
		 if(this.idCatalogo  == 13){
			 genera.generaGridPremios(this.premios, "CSV");
		 }
		 if(this.idCatalogo  == 14){
			 genera.generaGridProgramacionReparto(this.progRepartos, "CSV");
		 }
		 return msg = "";
	 }
	/* **********************  Encapsulamiento ************************************************************* */
	
	/**
	 * @return the form
	 */
	public UIForm getForm() {
		return form;
	}
	/**
	 * @param form the form to set
	 */
	public void setForm(UIForm form) {
		this.form = form;
	}


	/**
	 * @return the idCatalogo
	 */
	public Integer getIdCatalogo() {
		return idCatalogo;
	}


	/**
	 * @param idCatalogo the idCatalogo to set
	 */
	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}


	/**
	 * @return the descCatalogo
	 */
	public String getDescCatalogo() {
		return descCatalogo;
	}


	/**
	 * @param descCatalogo the descCatalogo to set
	 */
	public void setDescCatalogo(String descCatalogo) {
		this.descCatalogo = descCatalogo;
	}


	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}


	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}


	/**
	 * @return the config
	 */
	public Configuracion getConfig() {
		return config;
	}


	/**
	 * @param config the config to set
	 */
	public void setConfig(Configuracion config) {
		this.config = config;
	}


	/**
	 * @return the bancos
	 */
	public List<Bancos> getBancos() {
		return bancos;
	}


	/**
	 * @param bancos the bancos to set
	 */
	public void setBancos(List<Bancos> bancos) {
		this.bancos = bancos;
	}


	/**
	 * @return the banco
	 */
	public Bancos getBanco() {
		return banco;
	}


	/**
	 * @param banco the banco to set
	 */
	public void setBanco(Bancos banco) {
		this.banco = banco;
	}


	/**
	 * @return the campanias
	 */
	public List<Campania> getCampanias() {
		return campanias;
	}


	/**
	 * @param campanias the campanias to set
	 */
	public void setCampanias(List<Campania> campanias) {
		this.campanias = campanias;
	}


	/**
	 * @return the campania
	 */
	public Campania getCampania() {
		return campania;
	}


	/**
	 * @param campania the campania to set
	 */
	public void setCampania(Campania campania) {
		this.campania = campania;
	}


	/**
	 * @return the divisiones
	 */
	public List<Division> getDivisiones() {
		return divisiones;
	}


	/**
	 * @param divisiones the divisiones to set
	 */
	public void setDivisiones(List<Division> divisiones) {
		this.divisiones = divisiones;
	}


	/**
	 * @return the division
	 */
	public Division getDivision() {
		return division;
	}


	/**
	 * @param division the division to set
	 */
	public void setDivision(Division division) {
		this.division = division;
	}


	/**
	 * @return the zonas
	 */
	public List<Zona> getZonas() {
		return zonas;
	}


	/**
	 * @param zonas the zonas to set
	 */
	public void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}


	/**
	 * @return the zona
	 */
	public Zona getZona() {
		return zona;
	}


	/**
	 * @param zona the zona to set
	 */
	public void setZona(Zona zona) {
		this.zona = zona;
	}


	/**
	 * @return the representantes
	 */
	public List<Representante> getRepresentantes() {
		return representantes;
	}


	/**
	 * @param representantes the representantes to set
	 */
	public void setRepresentantes(List<Representante> representantes) {
		this.representantes = representantes;
	}
	
	/**
	 * @return the representantes
	 */
	public List<Representante> getRepresentantesBak() {
		return representantesBak;
	}


	/**
	 * @param representantes the representantes to set
	 */
	public void setRepresentantesBak(List<Representante> representantesBak) {
		this.representantesBak = representantesBak;
	}


	/**
	 * @return the representante
	 */
	public Representante getRepresentante() {
		return representante;
	}


	/**
	 * @param representante the representante to set
	 */
	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}


	/**
	 * @return the tiposPago
	 */
	public List<TipoPago> getTiposPago() {
		return tiposPago;
	}


	/**
	 * @param tiposPago the tiposPago to set
	 */
	public void setTiposPago(List<TipoPago> tiposPago) {
		this.tiposPago = tiposPago;
	}


	/**
	 * @return the tipoPago
	 */
	public TipoPago getTipoPago() {
		return tipoPago;
	}


	/**
	 * @param tipoPago the tipoPago to set
	 */
	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}


	/**
	 * @return the ordenes
	 */
	public List<Orden> getOrdenes() {
		return ordenes;
	}


	/**
	 * @param ordenes the ordenes to set
	 */
	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}


	/**
	 * @return the orden
	 */
	public Orden getOrden() {
		return orden;
	}


	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Orden orden) {
		this.orden = orden;
	}


	/**
	 * @return the razonesDeDevolucion
	 */
	public List<RazonesDevolucion> getRazonesDeDevolucion() {
		return razonesDeDevolucion;
	}


	/**
	 * @param razonesDeDevolucion the razonesDeDevolucion to set
	 */
	public void setRazonesDeDevolucion(List<RazonesDevolucion> razonesDeDevolucion) {
		this.razonesDeDevolucion = razonesDeDevolucion;
	}


	/**
	 * @return the razonDevolucion
	 */
	public RazonesDevolucion getRazonDevolucion() {
		return razonDevolucion;
	}


	/**
	 * @param razonDevolucion the razonDevolucion to set
	 */
	public void setRazonDevolucion(RazonesDevolucion razonDevolucion) {
		this.razonDevolucion = razonDevolucion;
	}


	/**
	 * @return the tiposLiquidacion
	 */
	public List<TipoLiquidacion> getTiposLiquidacion() {
		return tiposLiquidacion;
	}


	/**
	 * @param tiposLiquidacion the tiposLiquidacion to set
	 */
	public void setTiposLiquidacion(List<TipoLiquidacion> tiposLiquidacion) {
		this.tiposLiquidacion = tiposLiquidacion;
	}


	/**
	 * @return the tipoLiquidacion
	 */
	public TipoLiquidacion getTipoLiquidacion() {
		return tipoLiquidacion;
	}


	/**
	 * @param tipoLiquidacion the tipoLiquidacion to set
	 */
	public void setTipoLiquidacion(TipoLiquidacion tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
	}


	/**
	 * @return the edosOrden
	 */
	public List<EstadoOrden> getEdosOrden() {
		return edosOrden;
	}


	/**
	 * @param edosOrden the edosOrden to set
	 */
	public void setEdosOrden(List<EstadoOrden> edosOrden) {
		this.edosOrden = edosOrden;
	}


	/**
	 * @return the edoOrden
	 */
	public EstadoOrden getEdoOrden() {
		return edoOrden;
	}


	/**
	 * @param edoOrden the edoOrden to set
	 */
	public void setEdoOrden(EstadoOrden edoOrden) {
		this.edoOrden = edoOrden;
	}


	/**
	 * @return the tipoSiniestros
	 */
	public List<TipoSiniestro> getTipoSiniestros() {
		return tipoSiniestros;
	}


	/**
	 * @param tipoSiniestros the tipoSiniestros to set
	 */
	public void setTipoSiniestros(List<TipoSiniestro> tipoSiniestros) {
		this.tipoSiniestros = tipoSiniestros;
	}


	/**
	 * @return the tipoSiniestro
	 */
	public TipoSiniestro getTipoSiniestro() {
		return tipoSiniestro;
	}


	/**
	 * @param tipoSiniestro the tipoSiniestro to set
	 */
	public void setTipoSiniestro(TipoSiniestro tipoSiniestro) {
		this.tipoSiniestro = tipoSiniestro;
	}


	/**
	 * @return the unitarios
	 */
	public List<Unitarios> getUnitarios() {
		return unitarios;
	}


	/**
	 * @param unitarios the unitarios to set
	 */
	public void setUnitarios(List<Unitarios> unitarios) {
		this.unitarios = unitarios;
	}


	/**
	 * @return the unitario
	 */
	public Unitarios getUnitario() {
		return unitario;
	}


	/**
	 * @param unitario the unitario to set
	 */
	public void setUnitario(Unitarios unitario) {
		this.unitario = unitario;
	}


	/**
	 * @return the premios
	 */
	public List<Premios> getPremios() {
		return premios;
	}


	/**
	 * @param premios the premios to set
	 */
	public void setPremios(List<Premios> premios) {
		this.premios = premios;
	}


	/**
	 * @return the premio
	 */
	public Premios getPremio() {
		return premio;
	}


	/**
	 * @param premio the premio to set
	 */
	public void setPremio(Premios premio) {
		this.premio = premio;
	}


	/**
	 * @return the progRepartos
	 */
	public List<ProgramacionReparto> getProgRepartos() {
		return progRepartos;
	}


	/**
	 * @param progRepartos the progRepartos to set
	 */
	public void setProgRepartos(List<ProgramacionReparto> progRepartos) {
		this.progRepartos = progRepartos;
	}


	/**
	 * @return the progReparto
	 */
	public ProgramacionReparto getProgReparto() {
		return progReparto;
	}


	/**
	 * @param progReparto the progReparto to set
	 */
	public void setProgReparto(ProgramacionReparto progReparto) {
		this.progReparto = progReparto;
	}


	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}


	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the lastupd_ts
	 */
	public String getLastupd_ts() {
		return lastupd_ts;
	}


	/**
	 * @param lastupd_ts the lastupd_ts to set
	 */
	public void setLastupd_ts(String lastupd_ts) {
		this.lastupd_ts = lastupd_ts;
	}


	/**
	 * @return the fechaActualizado
	 */
	public String getFechaActualizado() {
		return fechaActualizado;
	}


	/**
	 * @param fechaActualizado the fechaActualizado to set
	 */
	public void setFechaActualizado(String fechaActualizado) {
		this.fechaActualizado = fechaActualizado;
	}


	/**
	 * @return the usuarioActualiza
	 */
	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}


	/**
	 * @param usuarioActualiza the usuarioActualiza to set
	 */
	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}


	/**
	 * @return the anioCampania
	 */
	public int getAnioCampania() {
		return anioCampania;
	}


	/**
	 * @param anioCampania the anioCampania to set
	 */
	public void setAnioCampania(int anioCampania) {
		this.anioCampania = anioCampania;
	}


	/**
	 * @return the cveCampania
	 */
	public int getCveCampania() {
		return cveCampania;
	}


	/**
	 * @param cveCampania the cveCampania to set
	 */
	public void setCveCampania(int cveCampania) {
		this.cveCampania = cveCampania;
	}


	/**
	 * @return the idRepresentante
	 */
	public int getIdRepresentante() {
		return idRepresentante;
	}


	/**
	 * @param idRepresentante the idRepresentante to set
	 */
	public void setIdRepresentante(int idRepresentante) {
		this.idRepresentante = idRepresentante;
	}


	/**
	 * @return the registro
	 */
	public String getRegistro() {
		return registro;
	}


	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}


	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}


	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}


	/**
	 * @return the montoActual
	 */
	public double getMontoActual() {
		return montoActual;
	}


	/**
	 * @param montoActual the montoActual to set
	 */
	public void setMontoActual(double montoActual) {
		this.montoActual = montoActual;
	}


	/**
	 * @return the montoPrevio
	 */
	public double getMontoPrevio() {
		return montoPrevio;
	}


	/**
	 * @param montoPrevio the montoPrevio to set
	 */
	public void setMontoPrevio(double montoPrevio) {
		this.montoPrevio = montoPrevio;
	}


	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}


	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	/**
	 * @return the coPostal
	 */
	public String getCoPostal() {
		return coPostal;
	}


	/**
	 * @param coPostal the coPostal to set
	 */
	public void setCoPostal(String coPostal) {
		this.coPostal = coPostal;
	}


	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}


	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}


	/**
	 * @return the pagoEfectivo
	 */
	public Integer getPagoEfectivo() {
		return pagoEfectivo;
	}


	/**
	 * @param pagoEfectivo the pagoEfectivo to set
	 */
	public void setPagoEfectivo(Integer pagoEfectivo) {
		this.pagoEfectivo = pagoEfectivo;
	}


	/**
	 * @return the siPagoEfectivo
	 */
	public String getSiPagoEfectivo() {
		return siPagoEfectivo;
	}


	/**
	 * @param siPagoEfectivo the siPagoEfectivo to set
	 */
	public void setSiPagoEfectivo(String siPagoEfectivo) {
		this.siPagoEfectivo = siPagoEfectivo;
	}


	/**
	 * @return the trabaja
	 */
	public Integer getTrabaja() {
		return trabaja;
	}


	/**
	 * @param trabaja the trabaja to set
	 */
	public void setTrabaja(Integer trabaja) {
		this.trabaja = trabaja;
	}


	/**
	 * @return the siTrabaja
	 */
	public String getSiTrabaja() {
		return siTrabaja;
	}


	/**
	 * @param siTrabaja the siTrabaja to set
	 */
	public void setSiTrabaja(String siTrabaja) {
		this.siTrabaja = siTrabaja;
	}


	/**
	 * @return the consultarActivado
	 */
	public Boolean getConsultarActivado() {
		return consultarActivado;
	}


	/**
	 * @param consultarActivado the consultarActivado to set
	 */
	public void setConsultarActivado(Boolean consultarActivado) {
		this.consultarActivado = consultarActivado;
	}

	/**
	 * @return the cveRuta
	 */
	public String getCveRuta() {
		return cveRuta;
	}


	/**
	 * @param cveRuta the cveRuta to set
	 */
	public void setCveRuta(String cveRuta) {
		this.cveRuta = cveRuta;
	}


	/**
	 * @return the descRepresentante
	 */
	public String getDescRepresentante() {
		return descRepresentante;
	}


	/**
	 * @param descRepresentante the descRepresentante to set
	 */
	public void setDescRepresentante(String descRepresentante) {
		this.descRepresentante = descRepresentante;
	}


	/**
	 * @return the idPrimeraOrden
	 */
	public Integer getIdPrimeraOrden() {
		return idPrimeraOrden;
	}


	/**
	 * @param idPrimeraOrden the idPrimeraOrden to set
	 */
	public void setIdPrimeraOrden(Integer idPrimeraOrden) {
		this.idPrimeraOrden = idPrimeraOrden;
	}


	/**
	 * @return the descPrimeraOrden
	 */
	public String getDescPrimeraOrden() {
		return descPrimeraOrden;
	}


	/**
	 * @param descPrimeraOrden the descPrimeraOrden to set
	 */
	public void setDescPrimeraOrden(String descPrimeraOrden) {
		this.descPrimeraOrden = descPrimeraOrden;
	}


	/**
	 * @param idRepresentante the idRepresentante to set
	 */
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	/**
	 * @param anioCampania the anioCampania to set
	 */
	public void setAnioCampania(Integer anioCampania) {
		this.anioCampania = anioCampania;
	}


	/**
	 * @param cveCampania the cveCampania to set
	 */
	public void setCveCampania(Integer cveCampania) {
		this.cveCampania = cveCampania;
	}


	public String getAnioCampania2() {
		return anioCampania2;
	}


	public void setAnioCampania2(String anioCampania2) {
		this.anioCampania2 = anioCampania2;
	}


	public String getCampania2() {
		return campania2;
	}


	public void setCampania2(String campania2) {
		this.campania2 = campania2;
	}


	public String getRegistro5() {
		return registro5;
	}


	public void setRegistro5(String registro5) {
		this.registro5 = registro5;
	}


	public String getNombre5() {
		return nombre5;
	}


	public void setNombre5(String nombre5) {
		this.nombre5 = nombre5;
	}


	public String getRegistro7() {
		return registro7;
	}


	public void setRegistro7(String registro7) {
		this.registro7 = registro7;
	}


	public String getAnioCampania7() {
		return anioCampania7;
	}


	public void setAnioCampania7(String anioCampania7) {
		this.anioCampania7 = anioCampania7;
	}


	public String getCampania7() {
		return campania7;
	}


	public void setCampania7(String campania7) {
		this.campania7 = campania7;
	}


	public String getRuta7() {
		return ruta7;
	}


	public void setRuta7(String ruta7) {
		this.ruta7 = ruta7;
	}


	public String getOrden7() {
		return orden7;
	}


	public void setOrden7(String orden7) {
		this.orden7 = orden7;
	}


	public String getCampania12() {
		return campania12;
	}


	public void setCampania12(String campania12) {
		this.campania12 = campania12;
	}


	public String getAnioCampania12() {
		return anioCampania12;
	}


	public void setAnioCampania12(String anioCampania12) {
		this.anioCampania12 = anioCampania12;
	}


	public String getOrden12() {
		return orden12;
	}


	public void setOrden12(String orden12) {
		this.orden12 = orden12;
	}


	public String getCodigo12() {
		return codigo12;
	}


	public void setCodigo12(String codigo12) {
		this.codigo12 = codigo12;
	}


	public String getCampania13() {
		return campania13;
	}


	public void setCampania13(String campania13) {
		this.campania13 = campania13;
	}


	public String getAnioCampania13() {
		return anioCampania13;
	}


	public void setAnioCampania13(String anioCampania13) {
		this.anioCampania13 = anioCampania13;
	}


	public String getOrden13() {
		return orden13;
	}


	public void setOrden13(String orden13) {
		this.orden13 = orden13;
	}


	public String getCodigo13() {
		return codigo13;
	}


	public void setCodigo13(String codigo13) {
		this.codigo13 = codigo13;
	}


	public String getCampania14() {
		return campania14;
	}


	public void setCampania14(String campania14) {
		this.campania14 = campania14;
	}


	public String getAnioCampania14() {
		return anioCampania14;
	}


	public void setAnioCampania14(String anioCampania14) {
		this.anioCampania14 = anioCampania14;
	}


	public String getZona14() {
		return zona14;
	}


	public void setZona14(String zona14) {
		this.zona14 = zona14;
	}

	/**
	 * @return the pnl1
	 */
	public Boolean getPnl1() {
		return pnl1;
	}

	/**
	 * @param pnl1 the pnl1 to set
	 */
	public void setPnl1(Boolean pnl1) {
		this.pnl1 = pnl1;
	}

	/**
	 * @return the pnl2
	 */
	public Boolean getPnl2() {
		return pnl2;
	}

	/**
	 * @param pnl2 the pnl2 to set
	 */
	public void setPnl2(Boolean pnl2) {
		this.pnl2 = pnl2;
	}

	/**
	 * @return the pnl3
	 */
	public Boolean getPnl3() {
		return pnl3;
	}

	/**
	 * @param pnl3 the pnl3 to set
	 */
	public void setPnl3(Boolean pnl3) {
		this.pnl3 = pnl3;
	}

	/**
	 * @return the pnl4
	 */
	public Boolean getPnl4() {
		return pnl4;
	}

	/**
	 * @param pnl4 the pnl4 to set
	 */
	public void setPnl4(Boolean pnl4) {
		this.pnl4 = pnl4;
	}

	/**
	 * @return the pnl5
	 */
	public Boolean getPnl5() {
		return pnl5;
	}

	/**
	 * @param pnl5 the pnl5 to set
	 */
	public void setPnl5(Boolean pnl5) {
		this.pnl5 = pnl5;
	}

	/**
	 * @return the pnl6
	 */
	public Boolean getPnl6() {
		return pnl6;
	}

	/**
	 * @param pnl6 the pnl6 to set
	 */
	public void setPnl6(Boolean pnl6) {
		this.pnl6 = pnl6;
	}

	/**
	 * @return the pnl7
	 */
	public Boolean getPnl7() {
		return pnl7;
	}

	/**
	 * @param pnl7 the pnl7 to set
	 */
	public void setPnl7(Boolean pnl7) {
		this.pnl7 = pnl7;
	}

	/**
	 * @return the pnl8
	 */
	public Boolean getPnl8() {
		return pnl8;
	}

	/**
	 * @param pnl8 the pnl8 to set
	 */
	public void setPnl8(Boolean pnl8) {
		this.pnl8 = pnl8;
	}

	/**
	 * @return the pnl9
	 */
	public Boolean getPnl9() {
		return pnl9;
	}

	/**
	 * @param pnl9 the pnl9 to set
	 */
	public void setPnl9(Boolean pnl9) {
		this.pnl9 = pnl9;
	}

	/**
	 * @return the pnl10
	 */
	public Boolean getPnl10() {
		return pnl10;
	}

	/**
	 * @param pnl10 the pnl10 to set
	 */
	public void setPnl10(Boolean pnl10) {
		this.pnl10 = pnl10;
	}

	/**
	 * @return the pnl11
	 */
	public Boolean getPnl11() {
		return pnl11;
	}

	/**
	 * @param pnl11 the pnl11 to set
	 */
	public void setPnl11(Boolean pnl11) {
		this.pnl11 = pnl11;
	}

	/**
	 * @return the pnl12
	 */
	public Boolean getPnl12() {
		return pnl12;
	}

	/**
	 * @param pnl12 the pnl12 to set
	 */
	public void setPnl12(Boolean pnl12) {
		this.pnl12 = pnl12;
	}

	/**
	 * @return the pnl13
	 */
	public Boolean getPnl13() {
		return pnl13;
	}

	/**
	 * @param pnl13 the pnl13 to set
	 */
	public void setPnl13(Boolean pnl13) {
		this.pnl13 = pnl13;
	}

	/**
	 * @return the pnl14
	 */
	public Boolean getPnl14() {
		return pnl14;
	}

	/**
	 * @param pnl14 the pnl14 to set
	 */
	public void setPnl14(Boolean pnl14) {
		this.pnl14 = pnl14;
	}

	/**
	 * @return the pnlRepresentante
	 */
	public Boolean getPnlRepresentante() {
		return pnlRepresentante;
	}

	/**
	 * @param pnlRepresentante the pnlRepresentante to set
	 */
	public void setPnlRepresentante(Boolean pnlRepresentante) {
		this.pnlRepresentante = pnlRepresentante;
	}

	/**
	 * @return the zona2
	 */
	public String getZona2() {
		return zona2;
	}

	/**
	 * @param zona2 the zona2 to set
	 */
	public void setZona2(String zona2) {
		this.zona2 = zona2;
	}

	public List<SelectItem> getListZonasFiltro() {
		return listZonasFiltro;
	}

	public void setListZonasFiltro(List<SelectItem> listZonasFiltro) {
		this.listZonasFiltro = listZonasFiltro;
	}

	public List<SelectItem> getListRutasFiltro() {
		return listRutasFiltro;
	}

	public void setListRutasFiltro(List<SelectItem> listRutasFiltro) {
		this.listRutasFiltro = listRutasFiltro;
	}

	public List<SelectItem> getListCampaniasFiltro() {
		return listCampaniasFiltro;
	}

	public void setListCampaniasFiltro(List<SelectItem> listCampaniasFiltro) {
		this.listCampaniasFiltro = listCampaniasFiltro;
	}

	public String getZonaFiltro() {
		return zonaFiltro;
	}

	public void setZonaFiltro(String zonaFiltro) {
		this.zonaFiltro = zonaFiltro;
	}

	public String getRutaFiltro() {
		return rutaFiltro;
	}

	public void setRutaFiltro(String rutaFiltro) {
		this.rutaFiltro = rutaFiltro;
	}

	public String getCampaniaFiltro() {
		return campaniaFiltro;
	}

	public void setCampaniaFiltro(String campaniaFiltro) {
		this.campaniaFiltro = campaniaFiltro;
	}

	public String getAccountFiltro() {
		return accountFiltro;
	}

	public void setAccountFiltro(String accountFiltro) {
		this.accountFiltro = accountFiltro;
	}

	public Boolean getHabilitaBtnEditRep() {
		return habilitaBtnEditRep;
	}

	public void setHabilitaBtnEditRep(Boolean habilitaBtnEditRep) {
		this.habilitaBtnEditRep = habilitaBtnEditRep;
	}

	public UIPanel getPnlRepresen() {
		return pnlRepresen;
	}

	public void setPnlRepresen(UIPanel pnlRepresen) {
		this.pnlRepresen = pnlRepresen;
	}

	public Boolean getDomIncorrecto() {
		return domIncorrecto;
	}

	public void setDomIncorrecto(Boolean domIncorrecto) {
		this.domIncorrecto = domIncorrecto;
	}

	public Boolean getDomIncorrectoAnt() {
		return domIncorrectoAnt;
	}

	public void setDomIncorrectoAnt(Boolean domIncorrectoAnt) {
		this.domIncorrectoAnt = domIncorrectoAnt;
	}
	
}
