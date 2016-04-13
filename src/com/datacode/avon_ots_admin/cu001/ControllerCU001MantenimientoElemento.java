/**
 * @author brenda.estrada
 * @since 30/12/2011
 */
package com.datacode.avon_ots_admin.cu001;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.datacode.avon_ots_admin.model.AsignacionRutaChofer;
import com.datacode.avon_ots_admin.model.AsignacionUnidadReparto;
import com.datacode.avon_ots_admin.model.Denominaciones;
import com.datacode.avon_ots_admin.model.Destinatario;
import com.datacode.avon_ots_admin.model.DispositivoMovil;
import com.datacode.avon_ots_admin.model.Empleado;
import com.datacode.avon_ots_admin.model.Informante;
import com.datacode.avon_ots_admin.model.Modelo;
import com.datacode.avon_ots_admin.model.ModuloAccion;
import com.datacode.avon_ots_admin.model.Perfil;
import com.datacode.avon_ots_admin.model.Puesto;
import com.datacode.avon_ots_admin.model.Reporte;
import com.datacode.avon_ots_admin.model.ReporteTipoDestinatario;
import com.datacode.avon_ots_admin.model.RepresentantesPorRuta;
import com.datacode.avon_ots_admin.model.RutaAlterna;
import com.datacode.avon_ots_admin.model.SubBodegaAlmacen;
import com.datacode.avon_ots_admin.model.TipoDestinatario;
import com.datacode.avon_ots_admin.model.TipoRuta;
import com.datacode.avon_ots_admin.model.UnidadReparto;
import com.datacode.avon_ots_admin.model.Usuario;
import com.datacode.avon_ots_admin.reports.ControllerGridMtoElemento;
import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.MantenimientoElementoControllerStub;
import com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.LineaTransporte;
import com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Marca;
import com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ParametrosLDC;
import com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.TipoInformante;
import com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.TipoUsuario;
import com.datacode.avon_ots_ws.RutasControllerStub;
import com.datacode.avon_ots_ws.RutasControllerStub.Paises;
import com.datacode.avon_ots_ws.RutasControllerStub.Rutas;
import com.datacode.avon_ots_ws.ZonaControllerStub.Zona;

/**
 * @author brenda.estrada
 * @since 30/12/2011
 */
public class ControllerCU001MantenimientoElemento {
	//Atributos de Interaccion Pantalla
	private Integer idCatalogo = 0;
	private String descCatalogo = "";
	
	//Atributos de la interfaz
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
	private Boolean pnl15 = new Boolean(false);
	private Boolean pnl16 = new Boolean(false);
	private Boolean pnl17 = new Boolean(false);
	private Boolean pnl18 = new Boolean(false);
	private Boolean pnl19 = new Boolean(false);
	private Boolean pnl20 = new Boolean(false);
	private Boolean pnl21 = new Boolean(false);
	private Boolean pnl22 = new Boolean(false);
	private Boolean pnl23 = new Boolean(false);
	
	private Boolean lblVisibleSinHH = new Boolean(false);
	private Boolean chkVisibleSinHH = new Boolean(false);
	private Boolean todasUnidadesReparto = new Boolean (false);
	//
	private UIInput txtAnio;
	private UploadedFile logLDC;
	private UploadedFile logAVON;
	private UIPanel pnlPermisos;		
	//Atributos C1- Unidades de Reparto
	private Integer idUnidadReparto = 0;
	private String anio = "";
	private String noSerie = "";
	private String noEconomico = "";
	private String color ="";
	private String placas = "";
	private String capacidadNoCajas = "";
	private String rendimiento = "";
	private String codigoBarras = "";
	//Atributos C2
	private String frecuencia = "";
	//Atributos C4
	private Integer idAsignacionUnidadReparto = 0;
	private String fAsignada = "";
	private String fDenegada = "";
	private String descUnidadReparto = "";
	//Atributos C5
	private Integer idRuta = 0;
	private String descRuta = "";
	private String cveRuta = "";
	private Double km;
	private int noPromOrdenes = 0;
	private Integer idTipoRiesgo = 0;
	private String tipoRiesgo = "", tiEfectivo = "HH:MM:SS", tiTotal = "HH:MM:SS";
	private Integer diaReparto=0;
	private Boolean repartoSinHH;
	private Integer idCampaniaSinHH=0;
	private Boolean activoReparto;
	//Atributos C6
	private Integer idTipoRuta = 0;
	private String descTipoRuta = "";
	private String cveTipoRuta = "";
	//Atributos C7
	private Integer idRepresentanteRuta;
	private String fAlta = "";
	private String fBaja = "";
	private String seqEntregaAnterior ="";
	private String seqEntregaReciente ="";
	//C8
	private Integer idAsignacionRutaChofer = 0;
	private String tipoAsignacion ="";
	//C9
	private Integer idDispositivoMovil = 0;
	private String macAdress ="";
	private String direccionIP ="";
	//C10
	private String nombre = "", apPaterno = "", apMaterno ="",
				   domicilio ="", feNacimiento ="",	sexo ="",
				   edoCivil ="", rfc ="", feIngreso ="",
				   telefono ="", mail ="";
	private Integer idSexo = 0, idEdoCivil = 0;
	//C11
	private Integer idLineaTransporte = 0;
	//C12
	private Integer idInformante = 0, idTipoInformante = 0;
	private String descTipoInformante = "";
	//C14
	private Integer idSubbodegaAlmacen = 0,  idUserResponsableSubbodega = 0;
	private String descUserResponsableSubbodega = "";
	//C16
	private Integer idDestinatario = 0, idTipoDestinatario = 0;
	private String descTipoDestinatario= "";
	//C17
	private Integer idReporteTipoDestinatario = 0;
	//C18
	private Integer idReporte = 0;
	private String comentario = "", rutaTemplate= "", nombreTemplate = "";
	//C19
	private String razonSocial = "", password = "", 		serverSMTP = "",
				   puerto = "",		 tipoSeguridad = "", 	factorMax = "",
				   factorMin = "", 	codigoBarrasEntrada = "", codigoBarrasSalida = "",
				   poblacion = "", 	region = "", 			ipServerSOS = "",
				   valueLogoLDC = "", valueLogoAVON ="";
	private byte[] logoLDC, 		logoAVON;
	//C20
	private int idUsuario;
	private String user;
	private int idTipoUsuario;
	private Integer idPerfil = 0;
	private String descPerfil = "",	descTipoUsuario ="";
	//C22
	private Integer idDenominacion = 0;
	private String descDenominacion = "", tipo = "";
	/* ******************************* Atributos Generales de Catalogos ********************************** */
	private String descripcion = "";
	private String clave = "";
	private Date fecha1;
	private Date fecha2;
	
	/* ******************************************* Instancias de Modelo ********************************** */
	private List<UnidadReparto> unidades = null;
	private UnidadReparto unidad;
	private List<Modelo> modelos = null;
	private Modelo modelo;
	private List<com.datacode.avon_ots_admin.model.Marca> marcas = null;
	private com.datacode.avon_ots_admin.model.Marca marca;
	private List<AsignacionUnidadReparto> asignaciones = null;
	private AsignacionUnidadReparto asignacion;
	private List<com.datacode.avon_ots_admin.model.Rutas> rutas = null;
	private com.datacode.avon_ots_admin.model.Rutas ruta;
	private List<TipoRuta> tipoRutas = null;
	private TipoRuta tipoRuta;
	private List<Empleado> empleados = null;
	private Empleado empleado;
	private List<RepresentantesPorRuta> representanteRutas = null;
	private RepresentantesPorRuta representanteRuta;
	private List<AsignacionRutaChofer> asignaRutasChofer = null;
	private AsignacionRutaChofer asignaRutaChofer;
	private List<DispositivoMovil> dispositivosMovil = null;
	private DispositivoMovil dispositivoMovil;
	private List<Puesto> puestos = null;
	private Puesto puesto;
	private List<com.datacode.avon_ots_admin.model.LineaTransporte> lineasTransporte = null;
	private com.datacode.avon_ots_admin.model.LineaTransporte lineaTransporte;
	private List<Informante> informantes = null;
	private Informante informante;
	private List<SubBodegaAlmacen> subbodegasAlmacen = null;
	private SubBodegaAlmacen subbodegaAlmacen;
	private List<Destinatario> destinatarios = null;
	private Destinatario destinatario;
	private List<TipoDestinatario> tipoDestinatarios = null;
	private TipoDestinatario tipoDestinatario;
	private List<ReporteTipoDestinatario> reportesTipoDestinatario = null;
	private ReporteTipoDestinatario reporteTipoDestinatario;
	private List<Reporte> reportes = null;
	private Reporte reporte;
	private List<com.datacode.avon_ots_admin.model.ParametrosLDC> ldccs = null;
	private com.datacode.avon_ots_admin.model.ParametrosLDC ldc;
	private List<Usuario> users = null;
	private Usuario usuario;
	private List<Perfil> perfiles = null;
	private Perfil perfil;
	private List<Denominaciones> denominaciones = null;
	private Denominaciones denominacion;
	
	/* ***************************** Atributos de SelectItem de los Catalogos ***************************** */
	private Integer idEstatus = 1;
	private String descEstatus = "";
	private Integer idMarca = 0;
	private String descMarca = "";
	private Integer idLDC = 0;
	private String descLDC = "";
	private Integer idPais = 0;
	private String descPais = "";	
	private Integer idModelo = 0;
	private String descModelo = "";
	private Integer idEmpleado = 0;
	private String descEmpleado = "";
	private Integer idZona = 0;
	private String descZona = "";
	private Integer idRepresentante = 0;
	private String descRepresentante = "";
	private Integer idTipoAsig = 1;
	private String descTipoAsig = "";
	private Integer idPuesto = 0, idTipo = 0;
	private String descPuesto = "";
	private String kilometraje = "";
	private Integer entregando = 0;
	private String entregadoMostrar = "";
	private boolean entregandoCheck = false;
	private boolean disabledKilometraje = true;
	
	//Instancias del WS
	MantenimientoElementoControllerStub ct = null;
	//Rutas existentes
	RutasControllerStub ctRuta = null;
	RutasControllerStub.GetRutasExistentes req = null;
	RutasControllerStub.GetRutasExistentesResponse res = null;
	
	//Instancia Objetos  del Stub
	com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.UnidadReparto[] unis = null;
	Paises[] paises = null;
	Marca[] marcs  = null;
	ParametrosLDC[] ldcs = null;
	com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Modelo[] mods = null;
	Rutas[] ruts = null;
		
	//Atributos para aplicar Filtros en DataTable
	 private String filterValue = ""; //Filtro de Marca
	 private String filterValueAnio = ""; //Filtro de Anios
	 private String filterValueNoSerie = "%"; //Filtro de No serie
	 private String filterValuefAsignada = "%"; //Filtro de Fecha ASignada
	 private String filterValuefDenegada = "%"; //Filtro de Fecha Denegada
	 private String filterValueEmpleado = "%"; //Filtro de Empleado
	 private String filterValueCveRuta = "%"; //Filtro de Clave de Ruta
	 private String filterValueZona = "%"; //Filtro de Desc de Zona
	 
	 private String ruta7 = "";  //Filtro Ruta Representante
	 private String nombre7 = ""; //Filtro Nombre Representante
	 private String zona7 = "";		//Filtro Zona Representante
	 
	 //variable que recibe mensaje de WS
	 private String msg = "";
	 
	 //Banderas
	 private Boolean buscarActivado = true;
	 private Boolean guardarActivado = true;
	 private Boolean modificarActivado = true;
	 private Boolean eliminarActivado = true;
	 private Boolean accionUpd = false;
	 private Boolean soloLectura = true;
	 private Boolean cancelarActivado = true;
	 private Boolean nuevoActivado = true;
	 
	//Obtiene el objeto Configuración con los valores cargados al inicio de sesión
	Configuracion config = (Configuracion)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("configuracion");
	//Mapa de Permisos por Modulo
	HashMap<String, Boolean> mapa = new HashMap<String, Boolean>(); 
	//Atributos para manejo de CheckBox en Permisos
	private Boolean selected = false;
	private Boolean selectedf = false;
	private List<ModuloAccion> permisosCheck = null;
	private ModuloAccion permiso;
	private Map<Long, Boolean> selectedIds = new HashMap<Long, Boolean>();
	private List<ModuloAccion> permisosSelected = null;
	//Lista de catalogos existentes
	List<SelectItem> listCatalog = null;
	List<SelectItem> listLDC = null;
	List<SelectItem> listZonas = null;
	
	//Variables para el catalogo de rutas alternas
	private String catRutasAlternasRutaOTS = "";
	private String catRutasAlternasRutaSOS = "";
	private int catRutasAlternasOrden = 0;
	private int catRutasAlternasZona = 0;
	private int catRutasAlternasIdRutaAlterna = 0;
	private String catRutasAlternasFiltroZona = "%";
	private List<RutaAlterna> listRutasAlternas = null;
	private List<SelectItem> catRutasAlternasListZonas = null;
	com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.RutaAlterna[] arrRutasAlternas;
	
	/**
	 * Constructor
	 * @author brenda.estrada
	 * @since 30/12/2011
	 */
	public ControllerCU001MantenimientoElemento() {
		//Ejecutar Metodos iniciales de Carga de Datos de CAtalogos
		getListaPaises();
		getListaLDC();
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		if (request.getParameter("enrutadoDirecto") != null
				&& request.getParameter("enrutadoDirecto").length() > 0) {
			this.idCatalogo = 7;
			cargarCatalogoSeleccionado(null);
		}
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
		mapa = mapaFull.get(2);// hacerlo dinamico
		return mapa;
	}
	
	/**
	 * Metodo que ejecuta el WS que corresponda a id catalogo seleccionado. LLena una lista para mostrar en Datatable
	 * @author brenda.estrada
	 * @since 26/12/2011
	 */
	public void cargaDatosIniciales() throws IOException{
		buscarActivado = false;
		if(this.idCatalogo == 1){
			MantenimientoElementoControllerStub.GetUnidadesRepartoExistentes req = null;
			MantenimientoElementoControllerStub.GetUnidadesRepartoExistentesResponse res = null;
			unidades = new ArrayList<UnidadReparto>();
			try {
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetUnidadesRepartoExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getUnidadesRepartoExistentes(req);
				//ruts variable de tipo Ruta heredada del WS
				unis = res.get_return();
				if(unis == null){
					msg = "No existen datos.";
				}else{
				//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
				for(int i=0;i<unis.length;i++){
					//Añade los datos a la lista tipo Ruta de forma local mediante el constructor
					unidades.add(new UnidadReparto(unis[i].getIdUnidadReparto(), unis[i].getDescModelo(), unis[i].getAnio(), 
												   unis[i].getNoSerie(), unis[i].getNoEconomico(), unis[i].getColor(), unis[i].getPlacas(), 
												   unis[i].getCapacidadNoCajas(), unis[i].getRendimiento(), unis[i].getCodigoBarras(), 
												   unis[i].getIdEstatus(), unis[i].getDescEstatus(), 
												   unis[i].getIdMarca(), unis[i].getDescMarca(), 
												   unis[i].getIdLDC(), unis[i].getDescLDC(), 
												   unis[i].getIdPais(), unis[i].getDescPais(), ""+unis[i].getKilometraje(), unis[i].getEntregando()));
				}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M121", "Error al invocar el web service de llenar datos de Unidad de Reparto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el web service de llenar datos de Unidad de Reparto.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 2){
			MantenimientoElementoControllerStub.GetModelosExistentes req = null;
			MantenimientoElementoControllerStub.GetModelosExistentesResponse res = null;
			modelos = new ArrayList<Modelo>();
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetModelosExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getModelosExistentes(req);
				//ruts variable de tipo Ruta heredada del WS
				mods = res.get_return();
				if(mods == null){
					msg = "No existen datos.";
				}else{
				//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
				for(int i=0;i<mods.length;i++){
					//Añade los datos a la lista tipo Ruta de forma local mediante el constructor
					modelos.add(new Modelo(mods[i].getIdModelo(), mods[i].getDescModelo(), mods[i].getFrecuencia(), mods[i].getIdMarca(), mods[i].getDescMarca()));
				}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M122", "Error al invocar el web service de llenar datos de Modelo.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el web service de llenar datos de Modelo.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 3){
			MantenimientoElementoControllerStub.GetMarcasExistentes req = null;
			MantenimientoElementoControllerStub.GetMarcasExistentesResponse res = null;
			marcas = new ArrayList<com.datacode.avon_ots_admin.model.Marca>();
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetMarcasExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getMarcasExistentes(req);
				//marcs variable de tipo Marca heredada del WS
				marcs = res.get_return();
				if(marcs == null){
					msg = "No existen datos.";
				}else{
				//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
				for(int i=0;i<marcs.length;i++){
					//Añade los datos a la lista tipo Ruta de forma local mediante el constructor
					marcas.add(new com.datacode.avon_ots_admin.model.Marca(marcs[i].getIdMarca(), marcs[i].getDescMarca()));
				}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M123", "Error al invocar el web service de consulta los datos de Marcas.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Marcas.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 4){
			//Ejecuta el metodo que consulta los datos
			obtenerCatalogo4("", "", "");
		}
		///////////////////////////////////////////catálogo de RUTAS////////////////////////////////////////////////////////////
		if(this.idCatalogo == 5){
			MantenimientoElementoControllerStub.GetObtenerRutasExistentes req = null;
			MantenimientoElementoControllerStub.GetObtenerRutasExistentesResponse res = null;
			rutas = new ArrayList<com.datacode.avon_ots_admin.model.Rutas>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Rutas[] data = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetObtenerRutasExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getObtenerRutasExistentes(req);
				data = res.get_return();
				if(data == null){
					msg = "No existen datos.";
				}else{
					String gridActivoRep ="";
					for(int i=0;i<data.length;i++){
						if(data[i].getIdCampaniaReparoSinHH() == 0){
							 gridActivoRep = "No";
						}else{
							 gridActivoRep = "Sí";
						}
						rutas.add(new com.datacode.avon_ots_admin.model.Rutas(data[i].getIdRuta(),	data[i].getCveRuta(),
								  data[i].getDescRuta(), 	data[i].getFechaUpd(),
								  data[i].getUserUpd(), 
								  data[i].getIdZona(),		data[i].getDescZona(), 
								  data[i].getIdPais(), 		data[i].getDescPais(),
								  data[i].getIdTipoRuta(), 	data[i].getDescTipoRuta(),
								  data[i].getIdLdc(), 		data[i].getDescLdc(),
								  data[i].getPoblacionColonia(),
								  data[i].getKmPromedio(),
								  data[i].getNoPromedioOrdenes(),
								  data[i].getTiempoPromEfectivoReparto(),
								  data[i].getTiempoPromTotalReparto(),
								  data[i].getIdTipoRiesgo(), data[i].getTipoRiesgo(),
								  data[i].getDiaReparto(),
								  data[i].getActivoReparto(),
								  data[i].getIdCampaniaReparoSinHH(), gridActivoRep
						));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M126", "Error al invocar el web service de consulta los datos de Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Ruta.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 6){
			MantenimientoElementoControllerStub.GetTiposRutaExistentes req = null;
			MantenimientoElementoControllerStub.GetTiposRutaExistentesResponse res = null;
			tipoRutas = new ArrayList<TipoRuta>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.TipoRuta[] tips = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetTiposRutaExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getTiposRutaExistentes(req);
				//marcs variable de tipo Marca heredada del WS
				tips = res.get_return();
				if(tips == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<tips.length;i++){
						//Añade los datos a la lista mediante el constructor
						tipoRutas.add(new TipoRuta(tips[i].getIdTipoRuta(), tips[i].getClave(), tips[i].getDescripcion(),
								tips[i].getIdLDC(), tips[i].getDescLDC(), tips[i].getIdPais(), tips[i].getDescPais()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M126", "Error al invocar el web service de consulta los datos de Tipo de Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Tipo de Ruta.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 7){
			//Ejecuta el metodo que consulta los datos
			obtenerCatalogo7(ruta7, nombre7, zona7);
		}
		if(this.idCatalogo == 8){
			MantenimientoElementoControllerStub.GetAsignacionesRutaChoferExistentes req = null;
			MantenimientoElementoControllerStub.GetAsignacionesRutaChoferExistentesResponse res = null;
			asignaRutasChofer = new ArrayList<AsignacionRutaChofer>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.AsignacionRutaChofer[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetAsignacionesRutaChoferExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getAsignacionesRutaChoferExistentes(req);
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						asignaRutasChofer.add(new AsignacionRutaChofer(arrData[i].getIdAsignacionRutaChofer(), arrData[i].getFAsignada(), arrData[i].getFDenegada(),
								arrData[i].getTipoAsignacion(), arrData[i].getIdTipoAsignacion(), arrData[i].getIdRuta(), arrData[i].getDescRuta(), arrData[i].getIdPais(), arrData[i].getDescPais(), 
								arrData[i].getIdZona(), arrData[i].getDescZona(), arrData[i].getIdLDC(), arrData[i].getDescLDC(), arrData[i].getIdEmpleado(), arrData[i].getDescEmpleado()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M128", "Error al invocar el web service de consulta los datos de Asignación Ruta Chofer.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Asignación Ruta Chofer.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 9){
			MantenimientoElementoControllerStub.GetDispositivosMovilesExistentes req = null;
			MantenimientoElementoControllerStub.GetDispositivosMovilesExistentesResponse res = null;
			dispositivosMovil = new ArrayList<DispositivoMovil>();
			 com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.DispositivoMovil[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetDispositivosMovilesExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getDispositivosMovilesExistentes(req);
				// variable heredada del WS
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						dispositivosMovil.add(new DispositivoMovil(arrData[i].getIdDispositivoMovil(), arrData[i].getNoSerie(), arrData[i].getMarca(), arrData[i].getModelo(), 
								arrData[i].getMacAdress(), arrData[i].getDireccionIP(), arrData[i].getIdLDC(), arrData[i].getDescLDC(), arrData[i].getIdPais(), 
								arrData[i].getDescPais(), arrData[i].getIdEstatus(), arrData[i].getDescEstatus()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M129", "Error al invocar el web service de consulta los datos de Dispositivo Movil.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Dispositivo Movil.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 10){
			MantenimientoElementoControllerStub.GetEmpleadosExistentes req = null;
			MantenimientoElementoControllerStub.GetEmpleadosExistentesResponse res = null;
			empleados = new ArrayList<Empleado>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Empleado[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetEmpleadosExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getEmpleadosExistentes(req);
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						empleados.add(new Empleado(arrData[i].getIdEmpleado(), arrData[i].getNombre(), arrData[i].getApPaterno(), arrData[i].getApMaterno(), 
								arrData[i].getDomicilio(), arrData[i].getFeNacimiento(), arrData[i].getIdSexo(), arrData[i].getSexo(), arrData[i].getIdEdoCivil(), arrData[i].getEdoCivil(), 
								arrData[i].getRfc(), arrData[i].getFeIngreso(), arrData[i].getTelefono(), arrData[i].getMail(), arrData[i].getIdEstatus(), arrData[i].getDescEstatus(), 
								arrData[i].getIdPuesto(), arrData[i].getDescPuesto(), arrData[i].getIdLDC(), arrData[i].getDescLDC()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M130", "Error al invocar el web service de consulta los datos de Empleado.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Empleado.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 11){
			MantenimientoElementoControllerStub.GetPuestosExistentes req = null;
			MantenimientoElementoControllerStub.GetPuestosExistentesResponse res = null;
			puestos  = new ArrayList<Puesto>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Puesto[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetPuestosExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getPuestosExistentes(req);
				// variable heredada del WS
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						puestos.add(new Puesto(arrData[i].getIdPuesto(), arrData[i].getClave(), arrData[i].getDescripcion(), arrData[i].getIdLDC(), arrData[i].getDescLDC(),
								arrData[i].getIdPais(), arrData[i].getDescPais()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M131", "Error al invocar el web service de consulta los datos de Puesto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Puesto.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 12){
			MantenimientoElementoControllerStub.GetLineasTransporteExistentes req = null;
			MantenimientoElementoControllerStub.GetLineasTransporteExistentesResponse res = null;
			lineasTransporte  = new ArrayList<com.datacode.avon_ots_admin.model.LineaTransporte>();
			LineaTransporte[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetLineasTransporteExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getLineasTransporteExistentes(req);
				// variable heredada del WS
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						lineasTransporte.add(new com.datacode.avon_ots_admin.model.LineaTransporte(arrData[i].getIdLineaTransporte(), arrData[i].getClave(), 
								arrData[i].getDescripcion(), arrData[i].getTelefono(), arrData[i].getDomicilio(), arrData[i].getIdPais(), arrData[i].getDescPais()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M132", "Error al invocar el web service de consulta los datos de Linea de Transporte.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Linea de Transporte.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 13){
			MantenimientoElementoControllerStub.GetInformantesExistentes req = null;
			MantenimientoElementoControllerStub.GetInformantesExistentesResponse res = null;
			informantes  = new ArrayList<Informante>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Informante[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetInformantesExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getInformantesExistentes(req);
				// variable heredada del WS
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						informantes.add(new  Informante(arrData[i].getIdInformante(), arrData[i].getDescripcion(), arrData[i].getIdTipoInformante(), 
								arrData[i].getDescTipoInformante(), arrData[i].getIdLDC(), arrData[i].getDescLDC(),  arrData[i].getIdPais(), arrData[i].getDescPais()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M133", "Error al invocar el web service de consulta los datos de Informante.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Informante.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 14){
			MantenimientoElementoControllerStub.GetSubBodegasDeAlmacenExistentes req = null;
			MantenimientoElementoControllerStub.GetSubBodegasDeAlmacenExistentesResponse res = null;
			subbodegasAlmacen  = new ArrayList<SubBodegaAlmacen>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.SubBodegaAlmacen[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetSubBodegasDeAlmacenExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getSubBodegasDeAlmacenExistentes(req);
				// variable heredada del WS
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						subbodegasAlmacen.add(new SubBodegaAlmacen(arrData[i].getIdSubbodegaAlmacen(), arrData[i].getClave(), arrData[i].getDescripcion(), arrData[i].getTelefono(), arrData[i].getDomicilio(), 
								arrData[i].getIdPais(), arrData[i].getDescPais(), arrData[i].getIdLDC(), arrData[i].getDescLDC(), arrData[i].getIdZona(), arrData[i].getDescZona(), 
								arrData[i].getIdUserResponsableSubbodega(), arrData[i].getDescUserResponsableSubbodega()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M134", "Error al invocar el web service de consulta los datos de Sub-Bodega de Almacen.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Sub-Bodega de Almacen.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 15){
			MantenimientoElementoControllerStub.GetDestinatariosExistentes req = null;
			MantenimientoElementoControllerStub.GetDestinatariosExistentesResponse res = null;
			destinatarios  = new ArrayList<Destinatario>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Destinatario[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetDestinatariosExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getDestinatariosExistentes(req);
				// variable heredada del WS
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						destinatarios.add(new Destinatario(arrData[i].getIdDestinatario(), arrData[i].getClave(), arrData[i].getNombre(), arrData[i].getApPaterno(), arrData[i].getApMaterno(), arrData[i].getMail(), 
								arrData[i].getIdPais(), arrData[i].getDescPais(), arrData[i].getIdLDC(), arrData[i].getDescLDC(), arrData[i].getIdTipoDestinatario(), arrData[i].getDescTipoDestinatario()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M135", "Error al invocar el web service de consulta los datos de Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Destinatario.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 16){
			MantenimientoElementoControllerStub.GetTipoDestinatarioExistentes req = null;
			MantenimientoElementoControllerStub.GetTipoDestinatarioExistentesResponse res = null;
			tipoDestinatarios  = new ArrayList<TipoDestinatario>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.TipoDestinatario[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetTipoDestinatarioExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getTipoDestinatarioExistentes(req);
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						tipoDestinatarios.add(new TipoDestinatario(arrData[i].getIdTipoDestinatario(), arrData[i].getClave(), arrData[i].getDescripcion(),
								arrData[i].getIdPais(), arrData[i].getDescPais(), arrData[i].getIdLDC(), arrData[i].getDescLDC()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M137", "Error al invocar el web service de consulta los datos de Tipo Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Tipo Destinatario.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 17){
			MantenimientoElementoControllerStub.GetReportesTipoDestinatarioExistentes req = null;
			MantenimientoElementoControllerStub.GetReportesTipoDestinatarioExistentesResponse res = null;
			reportesTipoDestinatario  = new ArrayList<ReporteTipoDestinatario>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ReporteTipoDestinatario[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetReportesTipoDestinatarioExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getReportesTipoDestinatarioExistentes(req);
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						reportesTipoDestinatario.add(new ReporteTipoDestinatario(arrData[i].getIdReporteTipoDestinatario(), 
								arrData[i].getClave(), arrData[i].getIdTipoDestinatario(), arrData[i].getDescTipoDestinatario(),arrData[i].getIdReporte()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M138", "Error al invocar el web service de consulta los datos de Reporte Tipo Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Reporte Tipo Destinatario.";
			}finally{ nuevoCatalogo();
			this.idReporte = 0;
			}
		}
		if(this.idCatalogo == 18){
			MantenimientoElementoControllerStub.GetReportesExistentes req = null;
			MantenimientoElementoControllerStub.GetReportesExistentesResponse res = null;
			reportes  = new ArrayList<Reporte>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Reporte[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetReportesExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getReportesExistentes(req);
				// variable heredada del WS
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						reportes.add(new Reporte(arrData[i].getIdReporte(), arrData[i].getNombre(), arrData[i].getDescripcion(), arrData[i].getComentario(), arrData[i].getRutaTemplate(), 
								arrData[i].getNombreTemplate(), arrData[i].getIdPais(), arrData[i].getDescPais(), arrData[i].getIdLDC(), arrData[i].getDescLDC()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M139", "Error al invocar el web service de consulta los datos de Reporte.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Reporte.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 19){
			MantenimientoElementoControllerStub.GetParamsLDCExistentes req = null;
			MantenimientoElementoControllerStub.GetParamsLDCExistentesResponse res = null;
			ldccs  = new ArrayList<com.datacode.avon_ots_admin.model.ParametrosLDC>();
			ParametrosLDC[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				req = new MantenimientoElementoControllerStub.GetParamsLDCExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getParamsLDCExistentes(req);
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						ldccs.add(new com.datacode.avon_ots_admin.model.ParametrosLDC(arrData[i].getIdLdc(), arrData[i].getDesc(), arrData[i].getRazonSocial(), arrData[i].getMail(), 
								arrData[i].getClave(), arrData[i].getPassword(), arrData[i].getServerSMTP(), arrData[i].getPuerto(), arrData[i].getTipoSeguridad(), arrData[i].getFactorMax(), arrData[i].getFactorMin(), 
								arrData[i].getCodigoBarrasEntrada(), arrData[i].getCodigoBarrasSalida(), arrData[i].getPoblacion(), arrData[i].getRegion(), 
								arrData[i].getIpServerSOS(), arrData[i].getIdPais())); 
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M140", "Error al invocar el web service de consulta los datos de LDC.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de LDC.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 20){
			MantenimientoElementoControllerStub.GetUsuariosExistentes req = null;
			MantenimientoElementoControllerStub.GetUsuariosExistentesResponse res = null;
			users  = new ArrayList<Usuario>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Usuario[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetUsuariosExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getUsuariosExistentes(req);
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						users.add(new Usuario(arrData[i].getIdUsuario(), arrData[i].getUsuario(), arrData[i].getIdTipoUsuario(), arrData[i].getDescTipoUsuario(), 
								arrData[i].getIdPerfil(), arrData[i].getDescPerfil(), arrData[i].getIdPais(), arrData[i].getDescPais(), arrData[i].getIdLDC(), arrData[i].getDescLDC(),
								arrData[i].getIdEmpleado(), arrData[i].getDescEmpleado(), arrData[i].getPassword(), arrData[i].getComentario()));
						
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M141", "Error al invocar el web service de consulta los datos de Usuario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Usuario.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 21){
			MantenimientoElementoControllerStub.GetPerfilesExistentes req = null;
			MantenimientoElementoControllerStub.GetPerfilesExistentesResponse res = null;
			perfiles  = new ArrayList<Perfil>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Perfil[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetPerfilesExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getPerfilesExistentes(req);
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						perfiles.add(new Perfil(arrData[i].getIdPerfil(), arrData[i].getClave(), arrData[i].getDescripcion()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M142", "Error al invocar el web service de consulta los datos de Perfil.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Perfil.";
			}finally{ nuevoCatalogo(); }
		}
		if(this.idCatalogo == 22){
			MantenimientoElementoControllerStub.GetDenominacionesExistentes req = null;
			MantenimientoElementoControllerStub.GetDenominacionesExistentesResponse res = null;
			denominaciones  = new ArrayList<Denominaciones>();
			com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Denominaciones[] arrData = null;
			try {
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				//Agrega al request la operacion a ejecutar
				req = new MantenimientoElementoControllerStub.GetDenominacionesExistentes();
				//Añade al response la respuesta de la operacion
				res = ct.getDenominacionesExistentes(req);
				// variable heredada del WS
				arrData = res.get_return();
				if(arrData == null){
					msg = "No existen datos.";
				}else{
					for(int i=0;i<arrData.length;i++){
						//Añade los datos a la lista de forma local mediante el constructor
						denominaciones.add(new Denominaciones(arrData[i].getIdDenominacion(), arrData[i].getDenominacion(), arrData[i].getTipo(), arrData[i].getIdTipo()));
					}
				}
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M143", "Error al invocar el web service de consulta los datos de Denominaciones.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que consulta los datos de Denominaciones.";
			}finally{ nuevoCatalogo(); }
		}
		
		if(this.idCatalogo == 23){
			consultaRutasAlternas();
		}
	}
	
	/* ***************** Ejecuta el metodo llamado desde el filtro en la vista ************************************************************************************************************** */
	
	public void consultaRutasAlternas () {
		MantenimientoElementoControllerStub.GetRutasAlternasExistentes req = null;
		MantenimientoElementoControllerStub.GetRutasAlternasExistentesResponse res = null;
		listRutasAlternas = new ArrayList<RutaAlterna>();
		try {
			
			//Crea el cliente
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			
			//Agrega al request la operacion a ejecutar
			req = new MantenimientoElementoControllerStub.GetRutasAlternasExistentes();
			//Añade al response la respuesta de la operacion
			res = ct.getRutasAlternasExistentes(req);
			//ruts variable de tipo Ruta heredada del WS
			arrRutasAlternas = res.get_return();
			if(arrRutasAlternas == null){
				msg = "No existen datos.";
			} else {
				// Ciclo que obtiene los datos del WS y los setea en la
				// clase de mapeo del lado del cliente
				for (int i = 0; i < arrRutasAlternas.length; i++) {
					listRutasAlternas.add(new RutaAlterna(
							arrRutasAlternas[i].getIdRutaAlterna(),
							arrRutasAlternas[i].getRutaOTS(),
							arrRutasAlternas[i].getRutaSOS(),
							arrRutasAlternas[i].getIdZona(),
							arrRutasAlternas[i].getZona(),
							arrRutasAlternas[i].getOrden()));
				}
			}
			
			catRutasAlternasListZonas = getListaZonas();
			
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("UCW049_1", "M121", "Error al invocar el web service de llenar datos de Rutas Alternas.", 
					excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el web service de llenar datos de Rutas Alternas.";
		}finally{ nuevoCatalogo(); }
	}
	
	
	public void getCatalogoNSerie(ValueChangeEvent e) {
		obtenerCatalogo4(filterValueNoSerie, "", "");
	}
	/**
	 * Obtiene el valor de Ruta de la vista para filtrar los datos del array[representanteRutas]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo7RutaParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.ruta7 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo7(ruta7, nombre7, zona7);
		//Actualiza la lista de Representantes
		getListaRepresentantesExistentes();
	}
	/**
	 * Obtiene el valor de Nombre de la vista para filtrar los datos del array[representanteRutas]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void getCatalogo7NombreParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.nombre7 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo7(ruta7, nombre7, zona7);
		//Actualiza la lista de Representantes
		getListaRepresentantesExistentes();
	}
	/**
	 * Obtiene el valor de Zona de la vista para filtrar los datos del array[representanteRutas]
	 * @param e - ValueChangeEvent
	 * @author brenda.estrada
	 * @since 16/02/2012
	 */
	public void getCatalogo7ZonaParams(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		this.zona7 = valor.toLowerCase();
		//Consulta los datos del Grid
		obtenerCatalogo7(ruta7, nombre7, zona7);
		//Actualiza la lista de Representantes
		getListaRepresentantesExistentes();
	}
	/* ***************** Llama el metodo para consultar los datos con filtros ************************************************************************************************************** */
	/**
	 * Consulta los datos de Asignacionde Unidad de Reparto, filtrando por parametros
	 * @param pRuta
	 * @param nombreRep
	 * @author brenda.estrada
	 * @since 15/02/2012
	 */
	public void obtenerCatalogo7(String pRuta, String nombreRep, String pZona){
		MantenimientoElementoControllerStub.GetRepresentantesPorRutaExistentes req = null;
		MantenimientoElementoControllerStub.GetRepresentantesPorRutaExistentesResponse res = null;
		representanteRutas = new ArrayList<RepresentantesPorRuta>();
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.RepresentantesPorRuta[] arrRepRuts = null;
		try {
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			req = new MantenimientoElementoControllerStub.GetRepresentantesPorRutaExistentes();
			req.setPRuta(pRuta);		req.setPRepresentante(nombreRep);
			req.setPZona(pZona);
			//Añade al response la respuesta de la operacion
			res = ct.getRepresentantesPorRutaExistentes(req);
			// variable de tipo Marca heredada del WS
			arrRepRuts = res.get_return();
			if(arrRepRuts == null){
				msg = "No existen datos.";
			}else{
				//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
				for(int i=0;i<arrRepRuts.length;i++){
					//Añade los datos a la lista de forma local mediante el constructor
					representanteRutas.add(new RepresentantesPorRuta(arrRepRuts[i].getIdRepresentanteRuta(), arrRepRuts[i].getFAlta(),
							arrRepRuts[i].getFBaja(), arrRepRuts[i].getSeqEntregaAnterior(), arrRepRuts[i].getSeqEntregaReciente(),
							arrRepRuts[i].getIdRepresentante(), arrRepRuts[i].getDescRepresentante(), arrRepRuts[i].getIdRuta(), arrRepRuts[i].getDescRuta(),
							arrRepRuts[i].getIdZona(), arrRepRuts[i].getDescZona()));
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M127", "Error al invocar el web service de consulta los datos de Representante por Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Representante por Ruta.";
		}finally{ nuevoCatalogo(); }
	}
	
	/**
	 * Consulta los datos de Asignacionde Unidad de Reparto, filtrando por parametros
	 * @param param1
	 * @param param2
	 * @param param3
	 * @author brenda.estrada
	 * @since 14/02/2012
	 */
	public void obtenerCatalogo4(String param1, String param2, String param3){
		MantenimientoElementoControllerStub.GetAsignacionesUnidadRepartoExistentes req = null;
		MantenimientoElementoControllerStub.GetAsignacionesUnidadRepartoExistentesResponse res = null;
		asignaciones = new ArrayList<AsignacionUnidadReparto>();
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.AsignacionUnidadReparto[] asigns = null;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));

			//Agrega al request la operacion a ejecutar
			req = new MantenimientoElementoControllerStub.GetAsignacionesUnidadRepartoExistentes();
			req.setNoSerie(param1);		req.setAsignada(param2);		req.setDenegada(param3);
			//Añade al response la respuesta de la operacion
			res = ct.getAsignacionesUnidadRepartoExistentes(req);
			//marcs variable de tipo Marca heredada del WS
			asigns = res.get_return();
			if(asigns == null){
				msg = "No existen registros.";
			}else{
				//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
				for(int i=0;i<asigns.length;i++){
					//Añade los datos a la lista mediante el constructor
					asignaciones.add(new AsignacionUnidadReparto(asigns[i].getIdAsignacionUnidadReparto(), asigns[i].getNoSerie(), 
							asigns[i].getFAsignada(), asigns[i].getFDenegada(), asigns[i].getIdEmpleado(), asigns[i].getDescEmpleado(),
							asigns[i].getIdUnidadReparto(), asigns[i].getDescUnidadReparto(), asigns[i].getIdLDC(), asigns[i].getDescLDC(), 
							asigns[i].getIdPais(), asigns[i].getDescPais(), asigns[i].getIdEstatus(), asigns[i].getDescEstatus()));
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M124", "Error al invocar el web service de consulta los datos de Asignación por Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Asignación por Ruta.";
		}finally{ 
			nuevoCatalogo(); filterValueNoSerie = "";
		}	
	}
	
	/* ******************************************************************************************************************************* */
	/**
	 * Valida la opcion seleccionada del Combo y muestra el form correspondiente
	 * @author brenda.estrada
	 * @since 30/12/2011
	 * @return Form rendered true en interfaz
	 */
	public void cargarCatalogoSeleccionado(ActionEvent event) {
		this.msg = "";
		if(this.idCatalogo != 0){
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
				lblVisibleSinHH =false;
				chkVisibleSinHH =false;
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
			}else if(this.idCatalogo == 15){
				pnl15 = true;
			}else if(this.idCatalogo == 16){
				pnl16 = true;
			}else if(this.idCatalogo == 17){
				pnl17 = true;
			}else if(this.idCatalogo == 18){
				pnl18 = true;
			}else if(this.idCatalogo == 19){
				pnl19 = true;
			}else if(this.idCatalogo == 20){
				pnl20 = true;
			}else if(this.idCatalogo == 21){
				pnl21 = true;
			}else if(this.idCatalogo == 22){
				pnl22 = true;
			} else if (this.idCatalogo == 23) {
				pnl23 = true;
			}
			try{
				cargaDatosIniciales(); //Cargar datos iniciales
			}catch(NullPointerException ex){
				msg = "Ha ocurrido un error al obtener los datos iniciales.";
			} catch (IOException e) {
				msg = "Ha ocurrido un error al obtener los datos iniciales.";
			}
		}
	}
	
	/**
	 * Inicializa los forms del catalogo
	 * @author brenda.estrada
	 * @since 06/01/2012
	 */
	public void reiniciarForms(){
		pnl1 = false;			pnl2 = false;
		pnl3 = false;			pnl4 = false;
		pnl5 = false;			pnl6 = false;
		lblVisibleSinHH =false; chkVisibleSinHH=false;
		pnl7 = false;			pnl8 = false;
		pnl9 = false;			pnl10 = false;
		pnl11 = false;			pnl12 = false;
		pnl13 = false;			pnl14 = false;
		pnl15 = false;			pnl16 = false;
		pnl17 = false;			pnl18 = false;
		pnl19 = false;			pnl20 = false;
		pnl21 = false;			pnl22 = false;
		pnl23 = false;
	}
	
	/**
	 * Lista fija de Catalogos existentes
	 * @author brenda.estrada
	 * @since 30/12/2011
	 * @return Lista catalogos
	 */
	public List<SelectItem> getCargaCatalogos() {
		listCatalog = new ArrayList<SelectItem>();
		
		listCatalog.add(new SelectItem(0, "Selecciona una opción"));
		listCatalog.add(new SelectItem(1, "Unidades de Reparto"));
		listCatalog.add(new SelectItem(2, "Modelo de Camioneta"));
		listCatalog.add(new SelectItem(3, "Marca de Camioneta"));
		listCatalog.add(new SelectItem(4, "Asignación de Unidad de Reparto"));
		listCatalog.add(new SelectItem(5, "Ruta"));
		listCatalog.add(new SelectItem(6, "Tipo de Ruta"));
		listCatalog.add(new SelectItem(7, "Representante por Ruta"));
		listCatalog.add(new SelectItem(8, "Asignación de Rutas"));
		listCatalog.add(new SelectItem(9, "HandHeld - Dispositivo Movil"));
		listCatalog.add(new SelectItem(10, "Empleado"));
		listCatalog.add(new SelectItem(11, "Puesto"));
		listCatalog.add(new SelectItem(12, "Linea de Transporte"));
		listCatalog.add(new SelectItem(13, "Informante"));
		listCatalog.add(new SelectItem(14, "SubBodega"));
		listCatalog.add(new SelectItem(15, "Destinatario"));
		listCatalog.add(new SelectItem(16, "Tipo Destinatario"));
		listCatalog.add(new SelectItem(17, "Reporte por Tipo Destinatario"));
		listCatalog.add(new SelectItem(18, "Reporte"));
		listCatalog.add(new SelectItem(19, "LDC - Parametros"));
		listCatalog.add(new SelectItem(20, "Usuario"));
		listCatalog.add(new SelectItem(21, "Perfil"));
		listCatalog.add(new SelectItem(22, "Denominaciones"));
		listCatalog.add(new SelectItem(23, "Rutas Alternas"));
		/* Valida el Mapa de Permisos - Se agregan elementos de acuerdo a Permiso */
		listCatalog = validaCatalogosPermitidos();
		return listCatalog;
	}
	
	/**
	 * Valida los permisos del Perfil para consultar los Catalogos.
	 * @return
	 * @author brenda.estrada
	 * @since 16/02/2012
	 */
	public List<SelectItem> validaCatalogosPermitidos(){
		List<SelectItem> catPermitidos = new ArrayList<SelectItem>();
		catPermitidos = listCatalog;
		try{
			if(getMapaAccion().get("SEE_UREPARTO") == null){ //
				catPermitidos = catalogosPermitidos(); 
			}
			if(getMapaAccion().get("SEE_MODELO") == null){ //
				catPermitidos = catalogosPermitidos(); 
			}
			if(getMapaAccion().get("SEE_MARCA") == null){  //
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_AUNIDADREPARTO") == null){  
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_RUTA") == null){ 	//
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_TIPRUTA") == null){	// 
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_REPRUTA") == null){  //
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_ASIGNARUTA") == null){	// 
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_DISPOSITIVO") == null){// 
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_EMPLEADO") == null){ // 
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_PUESTO") == null){	// 
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_LINTRANSPORTE") == null){	// 
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_INFORMANTE") == null){	// 
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_SUBBODEGA") == null){  //
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_DESTINATARIO") == null){// 
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_TIPDESTINATARIO") == null){// 
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_REPORTETIPDESTINATARIO") == null){  //
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_REPORTE") == null){ 
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_LDC") == null){ 
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_USER") == null){
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_PERFIL") == null){ 
				catPermitidos = catalogosPermitidos();
			}
			if(getMapaAccion().get("SEE_DENOMINA") == null){ 
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
		catPermitidos = listCatalog;
		int val = catPermitidos.size();
		for(int i=0;i<val;i++){
			if(catPermitidos.get(i).getLabel().equals("Unidades de Reparto")){
				if(getMapaAccion().get("SEE_UREPARTO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Modelo de Camioneta")){
				if(getMapaAccion().get("SEE_MODELO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Marca de Camioneta")){
				if(getMapaAccion().get("SEE_MARCA") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Asignación de Unidad de Reparto")){
				if(getMapaAccion().get("SEE_AUNIDADREPARTO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Ruta")){
				if(getMapaAccion().get("SEE_RUTA") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Tipo de Ruta")){
				if(getMapaAccion().get("SEE_TIPRUTA") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Representante por Ruta")){
				if(getMapaAccion().get("SEE_REPRUTA") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Asignación de Rutas")){
				if(getMapaAccion().get("SEE_ASIGNARUTA") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("HandHeld - Dispositivo Movil")){
				if(getMapaAccion().get("SEE_DISPOSITIVO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Empleado")){
				if(getMapaAccion().get("SEE_EMPLEADO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Puesto")){
				if(getMapaAccion().get("SEE_PUESTO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Linea de Transporte")){
				if(getMapaAccion().get("SEE_LINTRANSPORTE") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Informante")){
				if(getMapaAccion().get("SEE_INFORMANTE") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("SubBodega")){
				if(getMapaAccion().get("SEE_SUBBODEGA") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Destinatario")){
				if(getMapaAccion().get("SEE_DESTINATARIO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Tipo Destinatario")){
				if(getMapaAccion().get("SEE_TIPDESTINATARIO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Reporte por Tipo Destinatario")){
				if(getMapaAccion().get("SEE_REPORTETIPDESTINATARIO") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Reporte")){
				if(getMapaAccion().get("SEE_REPORTE") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("LDC - Parametros")){
				if(getMapaAccion().get("SEE_LDC") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Usuario")){
				if(getMapaAccion().get("SEE_USER") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Perfil")){
				if(getMapaAccion().get("SEE_PERFIL") == null){
					catPermitidos.remove(i);
					break;
				}
			}
			if(catPermitidos.get(i).getLabel().equals("Denominaciones")){
				if(getMapaAccion().get("SEE_DENOMINA") == null){
					catPermitidos.remove(i);
					break;
				}
			}
		}
		return catPermitidos;
	}
	
	/**
	 * Valida los elemetos[dataItem] de la List[permisosCheck] que tengan permiso.
	 * Si es igual el tamanio checa el CheckAll de Permisos
	 * @author brenda.estrada
	 * @since 31/01/2012
	 * @return String null
	 */
	public String validarPermisos(){
		List<ModuloAccion> siPermisos =  new ArrayList<ModuloAccion>();
		int  original = permisosCheck.size();
		int  validados = siPermisos.size();
	 	for (ModuloAccion dataItem : permisosCheck) {
			try{
				if(dataItem.getPermiso())
				{
					siPermisos.add(dataItem);
				}
			}catch(NullPointerException nil){
				nil.printStackTrace();
			}
		}
	 	validados = siPermisos.size();
	 	if(original == validados){
			selected = true;
			selectedf = true;
		}
		return  "";
	}
	
	/**
	 * Agrega a una list[ModuloAccion] los permisos seleccionados del DataTable
	 * @author brenda.estrada
	 * @since 31/01/2012
	 * @return List[PermisosSelected]
	 */
	public String guardarPermisosSelected(){
		permisosSelected =  new ArrayList<ModuloAccion>();
		for (ModuloAccion dataItem : permisosCheck) {
			//Guarda los Id's que son nuevos permisos
			try{
				if(dataItem.getPermiso())
				{
					permisosSelected.add(dataItem);
				}
			}catch(NullPointerException nil){
				nil.printStackTrace();
			}
		}
		return  "";
	}
	
	/**
	 * Selecciona todos los registros de Permisos mediante el checkbox
	 * @author brenda.estrada
	 * @since 07/02/2012
	 * @param event
	 */
	public void seleccionaTodosPermisos(ValueChangeEvent event){
		Boolean selecciona = (Boolean) event.getNewValue();
		if(permisosCheck.size() > 0){
			if(selecciona){
				if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
					event.setPhaseId(PhaseId.INVOKE_APPLICATION);
					event.queue();
				} else {
					for (ModuloAccion si : permisosCheck) {
						si.setPermiso(true);
					}
				}
				//selectedf = true;
			}
			if(!selecciona){
				if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
					event.setPhaseId(PhaseId.INVOKE_APPLICATION);
					event.queue();
				} else {
					ArrayList<ModuloAccion> nuevos = new ArrayList<ModuloAccion>();
					for (ModuloAccion si : permisosCheck) {
						si.setPermiso(false);
						nuevos.add(si);
					}
					permisosCheck = nuevos;
					//selectedf = false;
				}
			}
		}
	}
	
	/**
	 * Lista fija de Estatus existentes
	 * @author brenda.estrada
	 * @since 30/12/2011
	 * @return Lista Estatus
	 */
	public List<SelectItem> getListaEstatus() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(-1, "Selecciona una opción"));
		list.add(new SelectItem(1, "Activo"));
		list.add(new SelectItem(0, "Inactivo"));
		return list;
	}
	/**
	 * Lista fija de Tipo Asignacion
	 * @author brenda.estrada
	 * @since 30/12/2011
	 * @return Lista Estatus
	 */
	public List<SelectItem> getListaTipoAsignacion() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(0, "Selecciona una opción"));
		list.add(new SelectItem(1, "Principal"));
		list.add(new SelectItem(2, "Alterno"));
		return list;
	}
	/**
	 * Lista fija de Sexo
	 * @author brenda.estrada
	 * @since 30/12/2011
	 * @return Lista Estatus
	 */
	public List<SelectItem> getListaSexo() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(0, "Selecciona una opción"));
		list.add(new SelectItem(1, "Mujer"));
		list.add(new SelectItem(2, "Hombre"));
		return list;
	}
	/**
	 * Lista fija de Estado Civil
	 * @author brenda.estrada
	 * @since 30/12/2011
	 * @return Lista Estatus
	 */
	public List<SelectItem> getListaEdoCivil() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(0, "Selecciona una opción"));
		list.add(new SelectItem(1, "Soltero"));
		list.add(new SelectItem(2, "Casado"));
		return list;
	}
	/**
	 * Lista fija de Tipo Cambio
	 * @author brenda.estrada
	 * @since 13/01/2012
	 * @return Lista Estatus
	 */
	public List<SelectItem> getListaTipoCambio() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(0, "Selecciona una opción"));
		list.add(new SelectItem(1, "Billete"));
		list.add(new SelectItem(2, "Moneda"));
		return list;
	}
	/**
	 * Lista fija de Sexo
	 * @author brenda.estrada
	 * @since 01/03/2012
	 * @return Lista Estatus
	 */
	public List<SelectItem> getListaTipoRiesgo() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(0, "Selecciona una opción"));
		list.add(new SelectItem(1, "Alto"));
		list.add(new SelectItem(2, "Bajo"));
		list.add(new SelectItem(3, "Medio"));
		return list;
	}
	
	//MENSAJES desde 143 hasta 165
	List<SelectItem> listPaises = new ArrayList<SelectItem>();
	/**
	 * Crea una lista de tipo SelectItem. Lista de Paises existentes carga datos desde WS
	 * @author brenda.estrada
	 * @since 03/01/2012
	 * @return Lista Paises
	 */
	public List<SelectItem> getListaPaises() throws NullPointerException {
		listPaises = new ArrayList<SelectItem>();
		RutasControllerStub ct = null;
		RutasControllerStub.GetPaises reqPais = null;
		RutasControllerStub.GetPaisesResponse resPais = null;
		try{
			ct = new RutasControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			reqPais = new RutasControllerStub.GetPaises();
			//Añade al response la respuesta de la operacion
			resPais = ct.getPaises(reqPais);
			paises = resPais.get_return();
			listPaises.add(new SelectItem(0, "Selecciona una opción"));
			if(paises != null){
				for(int o=0;o<paises.length;o++){
					if(!paises[o].getIdPais().isEmpty() && !paises[o].getDescPais().isEmpty()){
						idPais = Integer.parseInt(paises[o].getIdPais());
						listPaises.add(new SelectItem(paises[o].getIdPais(), paises[o].getDescPais()));
					}
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M143", "Surgió un error al invocar el web service que obtiene los datos de Paises.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Surgió un error al invocar el web service que obtiene los datos de Paises";
		}finally{  }
		return listPaises;
	}
	
	public List<SelectItem> getListaReportes(){
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		MantenimientoElementoControllerStub.GetReportesExistentes req = null;
		MantenimientoElementoControllerStub.GetReportesExistentesResponse res = null;
		//reportes  = new ArrayList<Reporte>();
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Reporte[] arrData = null;
		try {
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			
			//Agrega al request la operacion a ejecutar
			req = new MantenimientoElementoControllerStub.GetReportesExistentes();
			//Añade al response la respuesta de la operacion
			res = ct.getReportesExistentes(req);
			// variable heredada del WS
			arrData = res.get_return();
			
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData == null){
				msg = "No existen datos.";
			}else{
				//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
				for(int i=0;i<arrData.length;i++){
					//Añade los datos a la lista de forma local mediante el constructor
					list.add(new SelectItem(arrData[i].getIdReporte(), arrData[i].getNombre()));
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M139", "Error al invocar el web service de consulta los datos de Reporte.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Reporte.";
		}
		return list;
	}
	
	/**
	 * Lista de Marcas existentes carga datos desde WS
	 * @author brenda.estrada
	 * @since 03/01/2012
	 * @return Lista Estatus
	 */
	public List<SelectItem> getListaMarcas() throws NullPointerException {
		List<SelectItem> list = new ArrayList<SelectItem>();
		MantenimientoElementoControllerStub.GetMarcasExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetMarcasExistentesResponse resMar = null;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetMarcasExistentes();
			//Añade al response la respuesta de la operacion
			resMar = ct.getMarcasExistentes(reqMar);
			marcs = resMar.get_return();
			//Sea gregan las opciones al SelectItem
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(marcs != null){
				for(int o=0;o<marcs.length;o++){
					if(!marcs[o].getIdMarca().isEmpty() && !marcs[o].getDescMarca().isEmpty()){
						list.add(new SelectItem(marcs[o].getIdMarca(), marcs[o].getDescMarca()));
					}
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M144", "Surgió un error al invocar el web service que obtiene los datos de Marcas.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Surgió un error al invocar el web service que obtiene los datos de Marcas.";
		}finally{  }
		return list;
	}
	
	/**
	 * Lista de LDC existentes carga datos desde WS
	 * @author brenda.estrada
	 * @since 03/01/2012
	 * @return Lista Estatus
	 */
	public List<SelectItem> getListaLDC() {
		listLDC = new ArrayList<SelectItem>();
		//Invocacion del Stub de datos
		MantenimientoElementoControllerStub.GetParamsLDCExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetParamsLDCExistentesResponse resMar = null;
		ParametrosLDC[] arrData = null;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetParamsLDCExistentes();
			//Añade al response la respuesta de la operacion
			resMar = ct.getParamsLDCExistentes(reqMar);
			arrData = resMar.get_return();
			//Sea gregan las opciones al SelectItem
			listLDC.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData != null){
				for(int o=0;o<arrData.length;o++){
					this.idLDC = arrData[o].getIdLdc();
					if(!arrData[o].getDesc().isEmpty()){
						listLDC.add(new SelectItem(arrData[o].getIdLdc(), arrData[o].getDesc()));
					}
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M145", "Surgió un error al invocar el web service que obtiene los datos de LDC.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Surgió un error al invocar el web service que obtiene los datos de LDC";
		}finally{  }
		return listLDC;
	}
	
	/**
	 * Lista de Unidad de Reparto existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 10/01/2012
	 * @return Lista Unidades de Reparto
	 */
	public List<SelectItem> getListaUnidadReparto() throws NullPointerException {
		List<SelectItem> list = new ArrayList<SelectItem>();
		//Invocacion del Stub de datos
		MantenimientoElementoControllerStub.GetUnidadesRepartoExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetUnidadesRepartoExistentesResponse resMar = null;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetUnidadesRepartoExistentes();
			//Añade al response la respuesta de la operacion
			resMar = ct.getUnidadesRepartoExistentes(reqMar);
			unis = resMar.get_return();
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(unis != null){
				for(int o=0;o<unis.length;o++){
					if(!unis[o].getPlacas().isEmpty()){
						list.add(new SelectItem(unis[o].getIdUnidadReparto(), unis[o].getPlacas())); //validar que valor mostrar
					}
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M146", "Error al invocar el web service que obtiene los datos de Unidad de Reparto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Unidad de Reparto.";
		}finally{  }
		return list;
	}
	
	/**
	 * Lista de Empleados solo Chofer existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 10/01/2012
	 * @return Lista Empleados
	 */
	public List<SelectItem> getListaEmpleadosChofer() throws NullPointerException {
		List<SelectItem> list = new ArrayList<SelectItem>();
		//Invocacion del Stub de datos
		MantenimientoElementoControllerStub.GetUsuariosChoferExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetUsuariosChoferExistentesResponse resMar = null;
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Usuario[] arrData = null;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetUsuariosChoferExistentes();
			resMar = ct.getUsuariosChoferExistentes(reqMar);
			arrData = resMar.get_return();
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData != null){
				//Ciclo de datos
				for(int o=0;o<arrData.length;o++){
					if(!arrData[o].getDescEmpleado().isEmpty())
						list.add(new SelectItem(arrData[o].getIdEmpleado(), arrData[o].getDescEmpleado()));
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M147", "Error al invocar el web service que obtiene los datos de Usuario Chofer.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Usuario Chofer.";
		}finally{  }
		return list;
	}

	/**
	 * Crea una lista de tipo SelectItem de Zonas
	 * @author brenda.estrada
	 * @since 09/01/2012
	 * @return Lista tipo Zonas
	 */
	public List<SelectItem> getListaZonas() throws NullPointerException {
		List<SelectItem> list = new ArrayList<SelectItem>();
		Utils utils = new Utils();
		try {
			Zona[] zonas = utils.getZonas(config.getIdLDC(), "UCW049_1");
			// Se agregan las opciones al SelectItem de Paises
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(zonas != null){
				// Ciclo de datos
				for (int o = 0; o < zonas.length; o++) {
					if(!zonas[o].getZona().isEmpty())
						list.add(new SelectItem(zonas[o].getIdZona(), zonas[o].getZona()));
				}
			}
		} catch (Exception ex) {
			msg = Utils.ObtenerMensajeBD("General Admin", "M2", true, "No se pudieron cargar las Zonas", ex.getMessage(), config.getIdUsuario())[0];
		} finally {  }
		return list;
	}
	/**
	 * Lista de Tipo Ruta existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 10/01/2012
	 * @return Lista TipoRuta
	 */
	public List<SelectItem> getListaTipoRuta() throws NullPointerException {
		List<SelectItem> list = new ArrayList<SelectItem>();
		MantenimientoElementoControllerStub.GetTiposRutaExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetTiposRutaExistentesResponse resMar = null;
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.TipoRuta[] tips = null;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetTiposRutaExistentes();
			//Añade al response la respuesta de la operacion
			resMar = ct.getTiposRutaExistentes(reqMar);
			tips = resMar.get_return();
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(tips != null){
				//Ciclo de datos
				for(int o=0;o<tips.length;o++){
					if(!tips[o].getDescripcion().isEmpty())
						list.add(new SelectItem(tips[o].getIdTipoRuta(), tips[o].getDescripcion())); 
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M148", "Error al invocar el web service que obtiene los datos de Tipo de Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Tipo de Ruta.";
		}finally{  }
		return list;
	}
	
	public List<SelectItem> getListaDiaReparto(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(0, "Selecciona una opción"));
		list.add(new SelectItem(1, "1"));
		list.add(new SelectItem(2, "2"));
		list.add(new SelectItem(3, "3"));
		list.add(new SelectItem(4, "4"));
		list.add(new SelectItem(5, "5"));
		return list;
	}
	
	/**
	 * Lista de Rutas existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 10/01/2012
	 * @return Lista Rutas
	 */
	public List<SelectItem> getListaRutas() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		Rutas[] arrru = null;
		try {
			ctRuta = new RutasControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ctRuta._getServiceClient().getOptions().getTo().getAddress());
			ctRuta._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			req = new RutasControllerStub.GetRutasExistentes();
			
//			if(idCatalogo == 7){
//				//if(idRepresentante != null && idRepresentante != 0) {
			req.setPIdRepresentante(idRepresentante.toString());
//				//}else{ req.setPIdRepresentante(idRepresentante.toString());}
//			}else{
//				req.setPIdRepresentante("A");
//			}
			//Añade al response la respuesta de la operacion
			res = ctRuta.getRutasExistentes(req);
			arrru = res.get_return();
			//Sea gregan las opciones al SelectItem
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrru != null){
				//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
				for(int i=0;i<arrru.length;i++){
					if(!arrru[i].getCveRuta().isEmpty())
						list.add(new SelectItem(arrru[i].getIdRuta(), arrru[i].getCveRuta() + " - " + arrru[i].getDescTipoRuta())); 
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M149", "Error al invocar el web service que obtiene los datos de Rutas.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Rutas.";
		}finally{  }
		return list;
	}
	
	/**
	 * Lista de Rutas existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 10/01/2012
	 * @return Lista Rutas
	 */
	public List<SelectItem> getListaRutasTodo() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		MantenimientoElementoControllerStub.GetObtenerRutasExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetObtenerRutasExistentesResponse resMar = null;
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Rutas[] arrData = null;
		try {
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetObtenerRutasExistentes();
			
			//Añade al response la respuesta de la operacion
			resMar = ct.getObtenerRutasExistentes(reqMar);
			arrData = resMar.get_return();
			//Sea gregan las opciones al SelectItem
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData != null){
				//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
				for(int i=0;i<arrData.length;i++){
					if(!arrData[i].getCveRuta().isEmpty())
						list.add(new SelectItem(arrData[i].getIdRuta(), arrData[i].getCveRuta() + " - " + arrData[i].getDescTipoRuta())); 
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M149", "Error al invocar el web service que obtiene los datos de Rutas.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Rutas.";
		}finally{  }
		return list;
	}
	/**
	 * 
	 * @param e
	 * @author brenda.estrada
	 * @since 01/03/2012
	 */
	public void getRutasPorZona(ValueChangeEvent e) {
		getListaRutas();
	}

	/**
	 * Lista de Representantes por Ruta existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 10/01/2012
	 * @return Lista RepresentantesRuta
	 */
	public List<SelectItem> getListaRepresentantesRuta() {
		List<SelectItem> list = new ArrayList<SelectItem>(); //Aun no se define donde se muestra esta lista
		MantenimientoElementoControllerStub.GetRepresentantesPorRutaExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetRepresentantesPorRutaExistentesResponse resMar = null;
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.RepresentantesPorRuta[] arrData;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetRepresentantesPorRutaExistentes();
			//Añade al response la respuesta de la operacion
			resMar = ct.getRepresentantesPorRutaExistentes(reqMar);
			arrData = resMar.get_return();
			//Sea gregan las opciones al SelectItem de Paises
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData != null){
				//Ciclo de datos
				for(int o=0;o<arrData.length;o++){
					if(!arrData[o].getSeqEntregaAnterior().isEmpty())
						list.add(new SelectItem(arrData[o].getIdRepresentanteRuta(), arrData[o].getSeqEntregaAnterior())); //Validar que dato mostrar
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M150", "Error al invocar el web service que obtiene los datos de Representantes por Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Representantes por Ruta.";
		}finally{  }
		return list;
	}
	
	/**
	 * Lista de Representantes existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 10/01/2012
	 * @return Lista Representantes
	 */
	public List<SelectItem> getListaRepresentantesExistentes() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		MantenimientoElementoControllerStub.GetRepresentantesExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetRepresentantesExistentesResponse resMar = null;
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Representante[] arrData;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetRepresentantesExistentes();
			reqMar.setPRuta(ruta7);   reqMar.setPRepresentante(nombre7);  reqMar.setPZona(zona7);
			//Añade al response la respuesta de la operacion
			resMar = ct.getRepresentantesExistentes(reqMar);
			arrData = resMar.get_return();
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData != null){
				//Ciclo de datos
				for(int o=0;o<arrData.length;o++){
					if(!arrData[o].getNombre().isEmpty())
						list.add(new SelectItem(arrData[o].getIdRepresentante(), arrData[o].getNombre())); 
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M150", "Error al invocar el web service que obtiene los datos de Representantes por Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Representantes por Ruta.";
		}finally{  }
		return list;
	}
	/**
	 * Lista de Tipo Informante existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 10/01/2012
	 * @return Lista Representantes
	 */
	public List<SelectItem> getListaTipoInformanteExistentes() throws NullPointerException {
		List<SelectItem> list = new ArrayList<SelectItem>();
		//Invocacion del Stub de datos
		MantenimientoElementoControllerStub.GetTipoInformantesExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetTipoInformantesExistentesResponse resMar = null;
		TipoInformante[] arrData;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetTipoInformantesExistentes();
			//Añade al response la respuesta de la operacion
			resMar = ct.getTipoInformantesExistentes(reqMar);
			//unis variable del WS
			arrData = resMar.get_return();
			//Sea gregan las opciones al SelectItem de Paises
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData != null){
				//Ciclo de datos
				for(int o=0;o<arrData.length;o++){
					if(!arrData[o].getDescTipoInformante().isEmpty())
						list.add(new SelectItem(arrData[o].getIdTipoInformante(), arrData[o].getDescTipoInformante())); 
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M151", "Error al invocar el web service que obtiene los datos de Tipo Informante.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Tipo Informante.";
		}finally{  }
		return list;
	}
	/**
	 * Lista de Tipo Perfil existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 13/01/2012
	 * @return Lista Perfiles
	 */
	public List<SelectItem> getListaPerfilesExistentes() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		MantenimientoElementoControllerStub.GetPerfilesExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetPerfilesExistentesResponse resMar = null;
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Perfil[] arrData;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetPerfilesExistentes();
			//Añade al response la respuesta de la operacion
			resMar = ct.getPerfilesExistentes(reqMar);
			//unis variable del WS
			arrData = resMar.get_return();
			//Sea gregan las opciones al SelectItem de Paises
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData != null){
				//Ciclo de datos
				for(int o=0;o<arrData.length;o++){
					if(!arrData[o].getDescripcion().isEmpty())
						list.add(new SelectItem(arrData[o].getIdPerfil(), arrData[o].getDescripcion())); 
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M152", "Error al invocar el web service que obtiene los datos de Perfiles.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Perfiles.";
		}finally{  }
		return list;
	}
	/**
	 * Lista de Tipo Usuario existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 13/01/2012
	 * @return Lista Perfiles
	 */
	public List<SelectItem> getListaTipoUsuarioExistentes() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		MantenimientoElementoControllerStub.GetTipoUsuariosExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetTipoUsuariosExistentesResponse resMar = null;
		TipoUsuario[] arrData;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetTipoUsuariosExistentes();
			//Añade al response la respuesta de la operacion
			resMar = ct.getTipoUsuariosExistentes(reqMar);
			//unis variable del WS
			arrData = resMar.get_return();
			//Sea gregan las opciones al SelectItem de Paises
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData != null){
				//Ciclo de datos
				for(int o=0;o<arrData.length;o++){
					if(!arrData[o].getCveTipoUsuario().isEmpty())
						list.add(new SelectItem(arrData[o].getIdTipoUsuario(), arrData[o].getCveTipoUsuario())); 
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M153", "Error al invocar el web service que obtiene los datos de Tipo Usuario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Tipo Usuario.";
		}finally{  }
		return list;
	}
	/**
	 * Lista de Empleados existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 13/01/2012
	 * @return Lista Empleados
	 */
	public List<SelectItem> getListaEmpleadosExistentes() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		MantenimientoElementoControllerStub.GetEmpleadosExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetEmpleadosExistentesResponse resMar = null;
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Empleado[] arrData;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetEmpleadosExistentes();
			//Añade al response la respuesta de la operacion
			resMar = ct.getEmpleadosExistentes(reqMar);
			//unis variable del WS
			arrData = resMar.get_return();
			//Sea gregan las opciones al SelectItem de Paises
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData != null){
				for(int o=0;o<arrData.length;o++){
					if(! arrData[o].getNombre().isEmpty())
						list.add(new SelectItem(arrData[o].getIdEmpleado(), arrData[o].getNombre() +" "+arrData[o].getApPaterno()+" "+arrData[o].getApMaterno())); 
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M154", "Error al invocar el web service que obtiene los datos de Empleados.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Empleados.";
		}finally{  }
		return list;
	}
	/**
	 * Lista de Puestos existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 13/01/2012
	 * @return Lista Puestos
	 */
	public List<SelectItem> getListaPuestos() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		MantenimientoElementoControllerStub.GetPuestosExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetPuestosExistentesResponse resMar = null;
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Puesto[] arrData;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetPuestosExistentes();
			//Añade al response la respuesta de la operacion
			resMar = ct.getPuestosExistentes(reqMar);
			//unis variable del WS
			arrData = resMar.get_return();
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData != null){
				for(int o=0;o<arrData.length;o++){
					if(! arrData[o].getDescripcion().isEmpty())
						list.add(new SelectItem(arrData[o].getIdPuesto(), arrData[o].getDescripcion())); 
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M155", "Error al invocar el web service que obtiene los datos de Puestos.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Puestos.";
		}finally{  }
		return list;
	}
	/**
	 * Lista de SubBodega Almacen existentes.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 13/01/2012
	 * @return Lista SubBodegaAlmacen
	 */
	public List<SelectItem> getListaResponsableSubbodegas() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		MantenimientoElementoControllerStub.GetUsuariosResponsableSubBodega reqMar = null;
		MantenimientoElementoControllerStub.GetUsuariosResponsableSubBodegaResponse resMar = null;
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.Usuario[] arrData;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetUsuariosResponsableSubBodega();
			//Añade al response la respuesta de la operacion
			resMar = ct.getUsuariosResponsableSubBodega(reqMar);
			arrData = resMar.get_return();
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData != null){
				for(int o=0;o<arrData.length;o++){
					if(arrData[o].getDescEmpleado() != null && arrData[o].getDescEmpleado().length() > 0)
			//		if(!arrData[o].getDescEmpleado().isEmpty())
						list.add(new SelectItem(arrData[o].getIdUsuario(), arrData[o].getDescEmpleado())); 
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M156", "Error al invocar el web service que obtiene los datos de Usuario Responsable de Sub-Bodega.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Usuario Responsable de Sub-Bodega.";
		}finally{  }
		return list;
	}
	/**
	 * Lista de TipoDestinatario.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 13/01/2012
	 * @return Lista TipoDestinatario
	 */
	public List<SelectItem> getListaTipoDestinatario() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		//Invocacion del Stub de datos
		MantenimientoElementoControllerStub.GetTipoDestinatarioExistentes reqMar = null;
		MantenimientoElementoControllerStub.GetTipoDestinatarioExistentesResponse resMar = null;
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.TipoDestinatario[] arrData;
		try{
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			reqMar = new MantenimientoElementoControllerStub.GetTipoDestinatarioExistentes();
			//Añade al response la respuesta de la operacion
			resMar = ct.getTipoDestinatarioExistentes(reqMar);
			arrData = resMar.get_return();
			//Sea gregan las opciones al SelectItem de Paises
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(arrData != null){
				for(int o=0;o<arrData.length;o++){
					if(!arrData[o].getDescripcion().isEmpty())
						list.add(new SelectItem(arrData[o].getIdTipoDestinatario(), arrData[o].getDescripcion())); 
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M157", "Error al invocar el web service que obtiene los datos de Tipo Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Tipo Destinatario."; 
		}finally{  }
		return list;
	}
	/**
	 * Lista de Tipo Modelo.Carga datos desde WS
	 * @author brenda.estrada
	 * @since 08/02/2012
	 * @return List[SelectItem] Modelo
	 */
	public List<SelectItem> getListaModelo() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		MantenimientoElementoControllerStub.GetModelosExistentes req = null;
		MantenimientoElementoControllerStub.GetModelosExistentesResponse res = null;
		try {
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			//Agrega al request la operacion a ejecutar
			req = new MantenimientoElementoControllerStub.GetModelosExistentes();
			res = ct.getModelosExistentes(req);
			mods = res.get_return();
			list.add(new SelectItem(0, "Selecciona una opción"));
			if(mods != null){
				for(int i=0;i<mods.length;i++){
					if(!mods[i].getDescModelo().isEmpty())
						list.add(new SelectItem(mods[i].getIdModelo(), mods[i].getDescModelo()));
				}
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M157", "Error al invocar el web service que obtiene los datos de Tipo Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Tipo Destinatario."; 
		}finally{  }
		return list;
	}
	
	/* ***************************** Metodo Generales de los Catalogos ************************************* */
	//MENSAJES del 166 al 190
	/**
	 * Guarda las datos del Catalogo seleccionando mediante el WS
	 * @author brenda.estrada
	 * @since 09/01/2012
	 * @return  String de exito o falla al invocar el WS.
	 * @throws IOException 
	 */
	public String guardarCatalogo() throws IOException {
		this.msg = "";
		//Validar CAtalogo seleccionado
		if(this.idCatalogo == 1){
			//Validar accion
			String validacionKilometraje = "";
			Integer insertarKilometraje = 0;
			Integer entregando = 0;
			if (this.isEntregandoCheck()) {
				insertarKilometraje = 0;
				entregando = 1;
				this.setKilometraje("0");
			} else {
				entregando = 0;
				insertarKilometraje = 1;
				validacionKilometraje = validarKilometrajeUnidadReparto(this.getKilometraje());
			}
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog1 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog1Response resIns = null;
				//Valida Campos requeridos
				if(idPais == 0 || idLDC == 0 || idMarca==0 || anio == "" || noSerie == "" ||
						noEconomico =="" || placas == "" || color =="" || capacidadNoCajas == "" || rendimiento =="" || idEstatus==-1){
					return msg = "Todos los campos marcados con * son requeridos.";
				}else if (!"".equals(validacionKilometraje)) {
					return msg = validacionKilometraje;
				} else {
					try{
						ct = new MantenimientoElementoControllerStub();
						//Obtiene y asigna url de configuración de web services
						String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
						ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
						//Agrega al request la operacion a ejecutar
						rIns = new MantenimientoElementoControllerStub.InsertarCatalog1();
						//Le setea al request los parametros necesarios
						rIns.setP_Activo(this.idEstatus.toString());
						rIns.setP_Anio(this.anio);
						rIns.setP_Capacidad(this.capacidadNoCajas);
						rIns.setP_CodBarras(this.codigoBarras);
						rIns.setP_Color(this.color);
						rIns.setP_Idldc(this.idLDC.toString());
						rIns.setP_IdMarca(this.idMarca.toString());
						rIns.setP_IdPais(this.idPais.toString());
						rIns.setP_IdUser(config.getIdUsuario());
						rIns.setP_NoEconomico(this.noEconomico);
						rIns.setP_NoSerie(this.noSerie);
						rIns.setP_Placas(this.placas);
						rIns.setP_Rendimiento(this.rendimiento);
						rIns.setP_kilometraje(Integer.valueOf(this.getKilometraje()));
						rIns.setP_insertarKilometraje(insertarKilometraje);
						rIns.setP_entregando(entregando);
						//Añade al response el request de la operacion
						resIns = ct.insertarCatalog1(rIns);
						cargaDatosIniciales();
						return msg = resIns.get_return();
					} catch (RemoteException excepcionDeInvocacion) {
						Utils.GuardarLogMensajeBD("CUADMIN001.02", "M166", "Error al invocar el web service que guarda los datos de Unidad de Reparto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
						msg = "Error al invocar el web service que guarda los datos de Unidad de Reparto.";
					}finally{ nuevoCatalogo(); }
				}
			}else{
				if(idPais == 0 || idLDC == 0 || idMarca==0 || anio == "" || noSerie == "" ||
						noEconomico =="" || placas == "" || color =="" || capacidadNoCajas == "" || rendimiento =="" || idEstatus==-1){
					return msg = "Todos los campos marcados con * son requeridos.";
				}else if (!"".equals(validacionKilometraje)) {
					return msg = validacionKilometraje;
				} else {
					MantenimientoElementoControllerStub.ActualizarCatalog1 rUpd = null;
					MantenimientoElementoControllerStub.ActualizarCatalog1Response resUpd = null;
					try{
						ct = new MantenimientoElementoControllerStub();
						//Obtiene y asigna url de configuración de web services
						String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
						ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
						
						//Agrega al request la operacion a ejecutar
						rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog1();
						//Le setea al request los parametros necesarios
						rUpd.setP_IdUnidadReparto(this.idUnidadReparto.toString());
						rUpd.setP_Activo(this.idEstatus.toString());
						rUpd.setP_Anio(this.anio);
						rUpd.setP_Capacidad(this.capacidadNoCajas);
						rUpd.setP_CodBarras(this.codigoBarras);
						rUpd.setP_Color(this.color);
						rUpd.setP_Idldc(this.idLDC.toString());
						rUpd.setP_IdMarca(this.idMarca.toString());
						rUpd.setP_IdPais(this.idPais.toString());
						rUpd.setP_IdUser(config.getIdUsuario()); 
						rUpd.setP_NoEconomico(this.noEconomico);
						rUpd.setP_NoSerie(this.noSerie);
						rUpd.setP_Placas(this.placas);
						rUpd.setP_Rendimiento(this.rendimiento);
						rUpd.setP_kilometraje(Integer.valueOf(this.getKilometraje()));
						rUpd.setP_insertarKilometraje(insertarKilometraje);
						rUpd.setP_entregando(entregando);
						//Añade al response el request de la operacion
						resUpd = ct.actualizarCatalog1(rUpd);
						cargaDatosIniciales();
						//Inicializar acciones
						this.accionUpd = false;
						this.eliminarActivado = true;
						this.soloLectura = true;
						return msg = resUpd.get_return();
					} catch (RemoteException excepcionDeInvocacion) {
						Utils.GuardarLogMensajeBD("CUADMIN001.02", "M167", "Error al invocar el web service que guarda los cambios de Unidad de Reparto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
						msg = "Error al invocar el servicio que guarda los cambios de Unidad de Reparto.";
					}finally{ nuevoCatalogo(); }
				}
			}
		}
		if(this.idCatalogo == 2){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog2 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog2Response resIns = null;
				//Valida Campos requeridos
				if(idMarca==0 || descModelo == ""){
					return msg = "Todos los campos marcados con * son requeridos.";
				}else{
					try{
						ct = new MantenimientoElementoControllerStub();
						//Obtiene y asigna url de configuración de web services
						String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
						ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
						//Agrega al request la operacion a ejecutar
						rIns = new MantenimientoElementoControllerStub.InsertarCatalog2();
						//Le setea al request los parametros necesarios
						rIns.setP_desc(this.descModelo);
						rIns.setP_frecuencia(this.frecuencia);
						rIns.setP_IdMarca(this.idMarca.toString());
						rIns.setP_IdUser(config.getIdUsuario()); 
						//Añade al response el request de la operacion
						resIns = ct.insertarCatalog2(rIns);
						cargaDatosIniciales();
						return msg = resIns.get_return();
					} catch (RemoteException excepcionDeInvocacion) {
						Utils.GuardarLogMensajeBD("CUADMIN001.02", "M168", "Error al invocar el web service que guarda los datos de Modelo.", excepcionDeInvocacion.toString(), config.getIdUsuario());
						msg = "Error al invocar el web service que guarda los datos de Modelo.";
					}finally{ nuevoCatalogo(); }
				}
			}else{
				if(idMarca==0 || descModelo == ""){
					return msg = "Todos los campos marcados con * son requeridos.";
				}else{
					MantenimientoElementoControllerStub.ActualizarCatalog2 rUpd = null;
					MantenimientoElementoControllerStub.ActualizarCatalog2Response resUpd = null;
					try{
						ct = new MantenimientoElementoControllerStub();
						//Obtiene y asigna url de configuración de web services
						String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
						ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
						//Agrega al request la operacion a ejecutar
						rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog2();
						//Le setea al request los parametros necesarios
						rUpd.setP_IdModelo(this.idModelo.toString());
						rUpd.setP_desc(this.descModelo);
						rUpd.setP_frecuencia(this.frecuencia);
						rUpd.setP_IdMarca(this.idMarca.toString());
						rUpd.setP_IdUser(config.getIdUsuario()); 
						//Añade al response el request de la operacion
						resUpd = ct.actualizarCatalog2(rUpd);
						cargaDatosIniciales();
						//Inicializar acciones
						this.accionUpd = false;
						this.eliminarActivado = true;
						this.soloLectura = true;
						return msg = resUpd.get_return();
					} catch (RemoteException excepcionDeInvocacion) {
						Utils.GuardarLogMensajeBD("CUADMIN001.02", "M169", "Error al invocar el web service que guarda los datos de Modelo.", excepcionDeInvocacion.toString(), config.getIdUsuario());
						msg = "Error al invocar el web service que guarda los datos de Modelo.";
					}finally{ nuevoCatalogo(); }
				}
			}
		}
		if(this.idCatalogo==3){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog3 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog3Response resIns = null;
				if(descMarca==""){
					return msg = "Todos los campos marcados con * son requeridos.";
				}else{
					try{
						ct = new MantenimientoElementoControllerStub();
						//Obtiene y asigna url de configuración de web services
						String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
						ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
						//Agrega al request la operacion a ejecutar
						rIns = new MantenimientoElementoControllerStub.InsertarCatalog3();
						//Le setea al request los parametros necesarios
						rIns.setP_DescMarca(this.descMarca);
						rIns.setP_IdUser(config.getIdUsuario()); 
						//Añade al response el request de la operacion
						resIns = ct.insertarCatalog3(rIns);
						cargaDatosIniciales();
						return msg = resIns.get_return();
					} catch (RemoteException excepcionDeInvocacion) {
						Utils.GuardarLogMensajeBD("CUADMIN001.02", "M170", "Error al invocar el web service que guarda los datos de Marca.", excepcionDeInvocacion.toString(), config.getIdUsuario());
						msg = "Error al invocar el servicio que guarda los datos de Marca.";
					}finally{ nuevoCatalogo(); }
				}
			}else{
				if(descMarca == ""){
					return msg = "Todos los campos marcados con * son requeridos.";
				}else{
					MantenimientoElementoControllerStub.ActualizarCatalog3 rUpd = null;
					MantenimientoElementoControllerStub.ActualizarCatalog3Response resUpd = null;
					try{
						ct = new MantenimientoElementoControllerStub();
						//Obtiene y asigna url de configuración de web services
						String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
						ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
						//Agrega al request la operacion a ejecutar
						rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog3();
						//Le setea al request los parametros necesarios
						rUpd.setP_IdMarca(this.idMarca.toString());
						rUpd.setP_DescMarca(this.descMarca);
						rUpd.setP_IdUser(config.getIdUsuario()); 
						//Añade al response el request de la operacion
						resUpd = ct.actualizarCatalog3(rUpd);
						cargaDatosIniciales();
						//Inicializar acciones
						this.accionUpd = false;
						this.eliminarActivado = true;
						this.soloLectura = true;
						return msg = resUpd.get_return();
					} catch (RemoteException excepcionDeInvocacion) {
						Utils.GuardarLogMensajeBD("CUADMIN001.02", "M171", "Error al invocar el web service que guarda los datos de Marca.", excepcionDeInvocacion.toString(), config.getIdUsuario());
						msg = "Error al invocar el servicio que guarda los datos de Marca.";
					}finally{ nuevoCatalogo(); }
				}
			}
		}
		if(this.idCatalogo == 4){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog4 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog4Response resIns = null;
				if(idPais  == 0 || idEmpleado == 0 ){ return ""; }else{
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog4();
					//Le setea al request los parametros necesarios
					rIns.setP_IdAsignacion(idAsignacionUnidadReparto.toString());
					rIns.setP_IdUser(config.getIdUsuario());
					rIns.setIdEmpleado(idEmpleado.toString());			rIns.setIdUnidadReparto(idUnidadReparto.toString());
					rIns.setP_idPais(idPais.toString());				rIns.setP_idLDC(idLDC.toString());
					rIns.setNoSerie(null);							rIns.setFAsignada(fAsignada);
					rIns.setFDenegada(fDenegada);						rIns.setIdEstatus(idEstatus.toString()); 
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog4(rIns);
					//Guarda la respuesta en una variable para ser mostrada en la Vista
					cargaDatosIniciales();
					msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M172", "Error al invocar el web service que guarda los datos de Asignacion de Unidad de Reparto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Asignacion de Unidad de Reparto.";
				}finally{ nuevoCatalogo(); }}
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog4 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog4Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog4();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdAsignacion(idAsignacionUnidadReparto.toString());
					rUpd.setP_IdUser(config.getIdUsuario());
					rUpd.setIdEmpleado(idEmpleado.toString());			rUpd.setIdUnidadReparto(idUnidadReparto.toString());
					rUpd.setP_idPais(idPais.toString());				rUpd.setP_idLDC(idLDC.toString());
					rUpd.setNoSerie(null);							rUpd.setFAsignada(fAsignada);
					rUpd.setFDenegada(fDenegada);						rUpd.setIdEstatus(idEstatus.toString()); 
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog4(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M173", "Error al invocar el web service que guarda los datos de Asignacion de Unidad de Reparto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Asignacion de Unidad de Reparto.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==5){
			//Valida Tipo Riesgo
			if(this.idTipoRiesgo == 1){
				this.setTipoRiesgo("Alto");
			}else if(this.idTipoRiesgo == 2){
				this.setTipoRiesgo("Bajo");
			}else{
				this.setTipoRiesgo("Medio");
			}
			if(!this.accionUpd){
				RutasControllerStub.InsertarRuta rIns = null;
				RutasControllerStub.InsertarRutaResponse resIns = null;
				try{
					ctRuta = new RutasControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ctRuta._getServiceClient().getOptions().getTo().getAddress());
					ctRuta._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
					rIns = new RutasControllerStub.InsertarRuta();
					rIns.setP_IdUser(config.getIdUsuario());		rIns.setP_Desc(descRuta);
					rIns.setP_Cve(cveRuta);
					rIns.setP_IdPais(idPais.toString());		rIns.setP_IdTipoRuta(idTipoRuta.toString());
					rIns.setP_IdZona(idZona.toString());		rIns.setP_Idldc(idLDC.toString());
					rIns.setPoblacion(domicilio);
					rIns.setKm(km);
					rIns.setNoPromOrdenes(noPromOrdenes);
					rIns.setTiPromEfectivo(tiEfectivo);
					rIns.setTiPromTotal(tiTotal);
					rIns.setTipoRiesgo(tipoRiesgo);
					rIns.setP_DiaReparto(diaReparto);
					rIns.setP_ActivoReparto(activoReparto);
					resIns = ctRuta.insertarRuta(rIns);
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M174", "Error al invocar el web service que guarda los datos de Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Ruta.";
				}finally{ nuevoCatalogo(); }
			}else{
				RutasControllerStub.ActualizarRuta rUpd = null;
				RutasControllerStub.ActualizarRutaResponse resUpd = null;
				try{
					ctRuta = new RutasControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ctRuta._getServiceClient().getOptions().getTo().getAddress());
					ctRuta._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
					rUpd = new RutasControllerStub.ActualizarRuta();
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setP_Desc(descRuta);
					rUpd.setP_Cve(cveRuta);						rUpd.setP_IdRuta(idRuta);
					rUpd.setP_IdPais(idPais.toString());		rUpd.setP_IdTipoRuta(idTipoRuta.toString());
					rUpd.setP_IdZona(idZona.toString());		rUpd.setP_Idldc(idLDC.toString());
					rUpd.setPoblacion(domicilio);
					rUpd.setKm(km);
					rUpd.setNoPromOrdenes(noPromOrdenes);
					rUpd.setTiPromEfectivo(tiEfectivo);
					rUpd.setTiPromTotal(tiTotal);
					rUpd.setTipoRiesgo(tipoRiesgo);
					rUpd.setP_DiaReparto(diaReparto);
					rUpd.setP_ActivoReparto(activoReparto);
					rUpd.setP_RepartoSinHH(repartoSinHH);
					resUpd = ctRuta.actualizarRuta(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M175", "Error al invocar el web service que guarda los datos de Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Ruta.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==6){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog6 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog6Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog6();
					//setea al request los parametros necesarios
					  rIns.setIdTipoRuta(idTipoRuta.toString());		rIns.setP_IdUser(config.getIdUsuario());
					  rIns.setCve(cveTipoRuta);							rIns.setDesc(descTipoRuta); 
					  rIns.setIdPais(idPais.toString());				rIns.setIdLDC(idLDC.toString());
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog6(rIns);
					//Guarda la respuesta en una variable para ser mostrada en la Vista
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M176", "Error al invocar el web service que guarda los datos de Tipo Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Tipo de Ruta.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog6 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog6Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog6();
					//Setea al request los parametros necesarios
					rUpd.setIdTipoRuta(idTipoRuta.toString());		rUpd.setP_IdUser(config.getIdUsuario());
					rUpd.setCve(cveTipoRuta);						rUpd.setDesc(descTipoRuta); 
					rUpd.setIdPais(idPais.toString());				rUpd.setIdLDC(idLDC.toString());
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog6(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M177", "Error al invocar el web service que guarda los datos de Tipo de Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Tipo de Ruta.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==7){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog7 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog7Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog7();
					//Le setea al request los parametros necesarios--p_IdUser, idRepresentaRuta, idRuta, idRepresentante, fAlta, fBaja, sAnterior, sReciente
					rIns.setIdRepresentaRuta("0"); 	rIns.setP_IdUser(config.getIdUsuario());		
					rIns.setIdRepresentante(idRepresentante.toString());		rIns.setIdRuta(idRuta.toString());	
					rIns.setFAlta(fAlta); 		rIns.setFBaja(fBaja);	
					rIns.setSAnterior(seqEntregaAnterior);	rIns.setSReciente(seqEntregaReciente);
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog7(rIns);
					cargaDatosIniciales();
					msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M178", "Error al invocar el web service que guarda los datos de Representante por Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Representante por Ruta.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog7 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog7Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog7();
					//setea al request los parametros necesarios
					rUpd.setIdRepresentaRuta(idRepresentanteRuta.toString());	rUpd.setP_IdUser(config.getIdUsuario());
					rUpd.setIdRepresentante(idRepresentante.toString());	rUpd.setIdRuta(idRuta.toString());	rUpd.setFAlta(fAlta);
					rUpd.setFBaja(fBaja);	rUpd.setSAnterior(seqEntregaAnterior);	rUpd.setSReciente(seqEntregaReciente);
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog7(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M179", "Error al invocar el web service que guarda los datos de Representante por Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Representante por de Ruta.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==8){ // Catálogo de Asignación de Ruta
			//Valida Tipo Asignacion
			if(idTipoAsig == 1){ 
				tipoAsignacion = "Principal";
			}else if(idTipoAsig == 2){
				tipoAsignacion = "Alterno";
			}
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog8 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog8Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog8();
					//setea al request los parametros necesarios
					rIns.setIdAsignaRutaChofer(idAsignacionRutaChofer.toString());		rIns.setP_IdUser(config.getIdUsuario());
					rIns.setIdRuta(idRuta.toString());	rIns.setIdPais(idPais.toString()); 	rIns.setIdZona(null);
					rIns.setIdLDC(idLDC.toString());	rIns.setIdEmpleado(idEmpleado.toString());  rIns.setFAsignada(fAlta);
					rIns.setFDenegada(fBaja);		rIns.setTipoAsignacion(tipoAsignacion);
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog8(rIns);
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M180", "Error al invocar el web service que guarda los datos de Asignacion de Ruta a Chofer.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Asignacion de Ruta a Chofer.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog8 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog8Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog8();
					//Le setea al request los parametros necesarios
					rUpd.setIdAsignaRutaChofer(idAsignacionRutaChofer.toString());			rUpd.setP_IdUser(config.getIdUsuario());
					rUpd.setIdRuta(idRuta.toString());	rUpd.setIdPais(idPais.toString()); 	rUpd.setIdZona(null);
					rUpd.setIdLDC(idLDC.toString());	rUpd.setIdEmpleado(idEmpleado.toString());  rUpd.setFAsignada(fAlta);
					rUpd.setFDenegada(fBaja);			rUpd.setTipoAsignacion(tipoAsignacion);
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog8(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M181", "Error al invocar el web service que guarda los datos de Asignacion de Ruta a Chofer.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Asignacion de Ruta a Chofer.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  == 9){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog9 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog9Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog9();
					//Le setea al request los parametros necesarios
					rIns.setIdDispositivo(idDispositivoMovil.toString());	rIns.setP_IdUser(config.getIdUsuario());
					rIns.setIdLDC(idLDC.toString());						rIns.setIdPais(idPais.toString());
					rIns.setNoSerie(noSerie);		rIns.setMarca(descMarca);	rIns.setModelo(descModelo);
					rIns.setMac(macAdress);			rIns.setIp(direccionIP); 	rIns.setIdEstatus(idEstatus.toString());
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog9(rIns);
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M182", "Error al invocar el web service que guarda los datos de HandHeld.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de HandHeld.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog9 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog9Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog9();
					//Le setea al request los parametros necesarios
					rUpd.setIdDispositivo(idDispositivoMovil.toString());	rUpd.setP_IdUser(config.getIdUsuario());
					rUpd.setIdLDC(idLDC.toString());						rUpd.setIdPais(idPais.toString());
					rUpd.setNoSerie(noSerie);		rUpd.setMarca(descMarca);	rUpd.setModelo(descModelo);
					rUpd.setMac(macAdress);			rUpd.setIp(direccionIP); 	rUpd.setIdEstatus(idEstatus.toString());
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog9(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M183", "Error al invocar el web service que guarda los datos de HandHeld.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de HandHeld.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo ==10){
			//Valida datos seleccionados
			if(idSexo == 1){
				this.setSexo("M"); 
			}else if(idSexo == 2){
				this.setSexo("H");
			}
			if(idEdoCivil == 1){
				this.setEdoCivil("S"); 
			}else if(idEdoCivil == 2){
				this.setEdoCivil("C"); 
			}	
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog10 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog10Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog10();
					//setea al request los parametros necesarios
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdEmpleado(idEmpleado.toString());	
					rIns.setIdPuesto(idPuesto.toString());		rIns.setNombre(nombre);
					rIns.setIdPais(idPais.toString());			rIns.setIdLDC(idLDC.toString());
					rIns.setDom(domicilio);						rIns.setApp(apPaterno);			rIns.setApm(apMaterno);
					rIns.setNac(feNacimiento);					
					rIns.setSexo(this.sexo);					rIns.setEdoCivil(this.edoCivil); 
					rIns.setRfc(rfc);							rIns.setTel(telefono);			rIns.setMail(mail);
					rIns.setFIngreso(feIngreso);				rIns.setIdEstatus(idEstatus.toString());
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog10(rIns);
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M184", "Error al invocar el web service que guarda los datos de Empleado.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Empleado.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog10 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog10Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog10();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdEmpleado(idEmpleado.toString());	
					rUpd.setIdPuesto(idPuesto.toString());		rUpd.setNombre(nombre);
					rUpd.setIdPais(idPais.toString());			rUpd.setIdLDC(idLDC.toString());
					rUpd.setDom(domicilio);						rUpd.setApp(apPaterno);			rUpd.setApm(apMaterno);
					rUpd.setNac(feNacimiento);					
					rUpd.setSexo(this.sexo);					rUpd.setEdoCivil(this.edoCivil);
					rUpd.setRfc(rfc);							rUpd.setTel(telefono);			rUpd.setMail(mail);
					rUpd.setFIngreso(feIngreso);				rUpd.setIdEstatus(idEstatus.toString());
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog10(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M185", "Error al invocar el web service que guarda los datos de Empleado.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Empleado.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==11){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog11 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog11Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog11();
					//Le setea al request los parametros necesarios
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdPuesto(idPuesto.toString());	
					rIns.setIdPais(idPais.toString());			rIns.setIdLDC(idLDC.toString());						
					rIns.setCve(clave);							rIns.setDesc(descPuesto);
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog11(rIns);
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M186", "Error al invocar el web service que guarda los datos de Puesto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Puesto.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog11 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog11Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog11();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdPuesto(idPuesto.toString());	
					rUpd.setIdPais(idPais.toString());			rUpd.setIdLDC(idLDC.toString());						
					rUpd.setCve(clave);							rUpd.setDesc(descPuesto);
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog11(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M187", "Error al invocar el web service que guarda los datos de Puesto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Puesto.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==12){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog12 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog12Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog12();
					//Le setea al request los parametros necesarios p_IdUser, idLineaTransporte, idPais, tel, cve, desc, dom
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdLineaTransporte(idLineaTransporte.toString());	
					rIns.setIdPais(idPais.toString());			rIns.setTel(telefono);							
					rIns.setCve(clave);							rIns.setDesc(descripcion);	rIns.setDom(domicilio);	
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog12(rIns);
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M188", "Error al invocar el web service que guarda los datos de Linea de Transporte.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Linea de Transporte.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog12 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog12Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog12();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdLineaTransporte(idLineaTransporte.toString());	
					rUpd.setIdPais(idPais.toString());			rUpd.setTel(telefono);							
					rUpd.setCve(clave);							rUpd.setDesc(descripcion);	rUpd.setDom(domicilio);	
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog12(rUpd);
					//Guarda la respuesta en una variable para ser mostrada en la Vista
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M189", "Error al invocar el web service que guarda los datos de Linea de Transporte.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Linea de Transporte.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==13){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog13 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog13Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog13();
					//Le setea al request los parametros necesarios--p_IdUser, idInformante, idPais, idTipoInformante, idLDC, desc
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdInformante(idInformante.toString());	
					rIns.setIdPais(idPais.toString());			rIns.setIdTipoInformante(idTipoInformante.toString());
					rIns.setIdLDC(idLDC.toString());			rIns.setDesc(descripcion);	
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog13(rIns);
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M190", "Error al invocar el web service que guarda los datos de Informante.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Informante.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog13 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog13Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog13();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdInformante(idInformante.toString());	
					rUpd.setIdPais(idPais.toString());			rUpd.setIdTipoInformante(idTipoInformante.toString());
					rUpd.setIdLDC(idLDC.toString());			rUpd.setDesc(descripcion);	
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog13(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M191", "Error al invocar el web service que guarda los datos de Informante.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Informante.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==14){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog14 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog14Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog14();
					//Le setea al request los parametros necesarios -- p_IdUser, idSubbodega, idZona, idPais, idLDC, cve, tel, dom, desc, idUserResponsableSub
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdSubbodega(idSubbodegaAlmacen.toString());	
					rIns.setIdZona(idZona.toString());			rIns.setIdPais(idPais.toString());			rIns.setIdLDC(idLDC.toString());							
					rIns.setCve(clave);							rIns.setTel(telefono);						rIns.setDom(domicilio);
					rIns.setDesc(descripcion);					rIns.setIdUserResponsableSub(idUserResponsableSubbodega.toString());
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog14(rIns);
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M192", "Error al invocar el web service que guarda los datos de Sub-Bodega.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Sub-Bodega.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog14 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog14Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog14();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdSubbodega(idSubbodegaAlmacen.toString());	
					rUpd.setIdZona(idZona.toString());			rUpd.setIdPais(idPais.toString());			rUpd.setIdLDC(idLDC.toString());							
					rUpd.setCve(clave);							rUpd.setTel(telefono);						rUpd.setDom(domicilio);
					rUpd.setDesc(descripcion);					rUpd.setIdUserResponsableSub(idUserResponsableSubbodega.toString());
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog14(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M193", "Error al invocar el web service que guarda los datos de Sub-Bodega.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Sub-Bodega.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==15){
			if(!this.accionUpd){
				//Se hace la instancia del metodo
				MantenimientoElementoControllerStub.InsertarCatalog15 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog15Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog15();
					//Le setea al request los parametros necesarios --p_IdUser, idDestinatario, idPais, idLDC, cve, nom, app, apm, mail, idTipoDestinatario
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdDestinatario(idDestinatario.toString());	
					rIns.setIdPais(idPais.toString());			rIns.setIdLDC(idLDC.toString());							
					rIns.setCve(clave);							rIns.setNom(nombre);	rIns.setApp(apPaterno);	
					rIns.setApm(apMaterno);			rIns.setMail(mail);			rIns.setIdTipoDestinatario(idTipoDestinatario.toString());
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog15(rIns);
					//Guarda la respuesta en una variable para ser mostrada en la Vista
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M194", "Error al invocar el web service que guarda los datos de Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Destinatario.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog15 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog15Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog15();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdDestinatario(idDestinatario.toString());	
					rUpd.setIdPais(idPais.toString());			rUpd.setIdLDC(idLDC.toString());							
					rUpd.setCve(clave);							rUpd.setNom(nombre);			rUpd.setApp(apPaterno);	
					rUpd.setApm(apMaterno);						rUpd.setMail(mail);				rUpd.setIdTipoDestinatario(idTipoDestinatario.toString());
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog15(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M195", "Error al invocar el web service que guarda los datos de Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Destinatario.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==16){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog16 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog16Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog16();
					//Le setea al request los parametros necesarios -- p_IdUser, idTipoDestinatario, idDestinatario, idPais, idLDC, cve, desc
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdTipoDestinatario(idTipoDestinatario.toString());
					rIns.setIdPais(idPais.toString());			rIns.setIdLDC(idLDC.toString());							
					rIns.setCve(clave);							rIns.setDesc(descTipoDestinatario);			
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog16(rIns);
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M196", "Error al invocar el web service que guarda los datos de Tipo de Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Tipo de Destinatario.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog16 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog16Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog16();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdTipoDestinatario(idTipoDestinatario.toString());
					rUpd.setIdDestinatario(idDestinatario.toString());
					rUpd.setIdPais(idPais.toString());			rUpd.setIdLDC(idLDC.toString());							
					rUpd.setCve(clave);							rUpd.setDesc(descTipoDestinatario);		
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog16(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M197", "Error al invocar el web service que guarda los datos de Tipo de Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Tipo de Destinatario.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==17){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog17 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog17Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog17();
					//Le setea al request los parametros necesarios --p_IdUser, idReporteTipoDestinatario, idTipoDestinatario, cve
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdReporteTipoDestinatario(idReporteTipoDestinatario.toString());
					rIns.setIdTipoDestinatario(idTipoDestinatario.toString());						
					rIns.setCve(idReporte.toString());
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog17(rIns);
					//Guarda la respuesta en una variable para ser mostrada en la Vista
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M198", "Error al invocar el web service que guarda los datos de Reporte por Tipo de Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos Reporte por Tipo de Destinatario.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog17 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog17Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog17();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdReporteTipoDestinatario(idReporteTipoDestinatario.toString());
					rUpd.setIdTipoDestinatario(idTipoDestinatario.toString());						
					rUpd.setCve(idReporte.toString());	
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog17(rUpd);
					//Guarda la respuesta en una variable para ser mostrada en la Vista
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M199", "Error al invocar el web service que guarda los datos de Reporte por Tipo Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Reporte por Tipo Destinatario.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==18){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog18 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog18Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog18();
					//Le setea al request los parametros necesarios -- p_IdUser, idReporte, idPais, idLDC, nom, desc, comm, rutaTemplate, nomTemplate
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdReporte(idReporte.toString());
					rIns.setIdPais(idPais.toString());			rIns.setIdLDC(idLDC.toString());							
					rIns.setNom(nombre);						rIns.setDesc(descripcion);			rIns.setComm(comentario);	
					rIns.setRutaTemplate(rutaTemplate)	;		rIns.setNomTemplate(nombreTemplate);
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog18(rIns);
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M200", "Error al invocar el web service que guarda los datos de Reporte.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Reporte.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog18 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog18Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog18();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdReporte(idReporte.toString());
					rUpd.setIdPais(idPais.toString());			rUpd.setIdLDC(idLDC.toString());							
					rUpd.setNom(nombre);						rUpd.setDesc(descripcion);			rUpd.setComm(comentario);	
					rUpd.setRutaTemplate(rutaTemplate)	;		rUpd.setNomTemplate(nombreTemplate);
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog18(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M201", "Error al invocar el web service que guarda los datos de L.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Reporte.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo ==19){
			//Valida imagen
			ByteArrayDataSource rawDataLDC= null;
	        DataHandler dataLDC = null;
	        ByteArrayDataSource rawDataAVON= null;
	        DataHandler dataAVON = null;
			if(logLDC != null){
				try {
					rawDataLDC= new ByteArrayDataSource(logLDC.getBytes(),logLDC.getName());
			        dataLDC = new DataHandler(rawDataLDC);
				} catch (IOException e) {
					msg = "No se cargo la imagen correctamente.";
				}
			}
			if(logAVON != null){
				try {
					 rawDataAVON= new ByteArrayDataSource(logAVON.getBytes(),logAVON.getName());
				     dataAVON = new DataHandler(rawDataAVON);
				} catch (IOException e) {
					msg = "No se cargo la imagen correctamente.";
					e.printStackTrace();
				}
			}else{ msg = "No se cargo la imagen correctamente."; }
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog19 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog19Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog19();
					//Le setea al request los parametros necesarios 
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdLDC(idLDC.toString());	rIns.setDesc(descLDC);
					rIns.setRazonSocial(razonSocial);			rIns.setMail(mail);					rIns.setCve(clave);
					rIns.setPass(password);						rIns.setServerSMTP(serverSMTP);		rIns.setPuerto(puerto);
					rIns.setTipoSeguridad(tipoSeguridad);		rIns.setFactorMax(factorMax);		rIns.setFactorMin(factorMin);
					rIns.setCodBarrasSal(codigoBarrasSalida);	rIns.setCodBarrasEnt(codigoBarrasEntrada);			
					rIns.setPoblacion(poblacion);				rIns.setRegion(region);
					rIns.setIdPais(idPais.toString());	
					rIns.setLogLDC(dataLDC);					rIns.setLogAVON(dataAVON);
					rIns.setServerSOS(ipServerSOS);				
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog19(rIns);
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M202", "Error al invocar el web service que guarda los datos de LDC.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de LDC.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog19 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog19Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog19();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdLDC(idLDC.toString());	rUpd.setDesc(descLDC);
					rUpd.setRazonSocial(razonSocial);			rUpd.setMail(mail);					rUpd.setCve(clave);
					rUpd.setPass(password);						rUpd.setServerSMTP(serverSMTP);		rUpd.setPuerto(puerto);
					rUpd.setTipoSeguridad(tipoSeguridad);		rUpd.setFactorMax(factorMax);		rUpd.setFactorMin(factorMin);
					rUpd.setCodBarrasSal(codigoBarrasSalida);	rUpd.setCodBarrasEnt(codigoBarrasEntrada);			
					rUpd.setPoblacion(poblacion);	rUpd.setRegion(region);
					rUpd.setIdPais(idPais.toString());			
					rUpd.setLogLDC(dataLDC);					rUpd.setLogAvon(dataAVON);
					rUpd.setServerSOS(ipServerSOS);			
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog19(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M203", "Error al invocar el web service que guarda los datos de LDC.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de LDC.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==20){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog20 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog20Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog20();
					//Le setea al request los parametros necesarios--p_IdUser, idUserCatalogo, idPais, idPerfil, idTipoUsuario, idLDC, user, pass, comm, idEmpleado
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdUserCatalogo(String.valueOf(idUsuario));	
					rIns.setIdPais(idPais.toString());			rIns.setIdPerfil(idPerfil.toString());		rIns.setIdTipoUsuario(String.valueOf(idTipoUsuario));							
					rIns.setIdLDC(idLDC.toString());  			rIns.setUser(user);						rIns.setPass(password);						
					rIns.setComm(comentario);					rIns.setIdEmpleado(idEmpleado.toString());	
					//Añade al response el request de la operacion
					resIns = ct.insertarCatalog20(rIns);
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M204", "Error al invocar el web service que guarda los datos de Usuario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					return msg = "Error al invocar el servicio que guarda los datos de Usuario.";
				}finally{ nuevoCatalogo(); }
			}else{
				//Se hace la instancia del metodo
				MantenimientoElementoControllerStub.ActualizarCatalog20 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog20Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog20();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdUserCatalogo(String.valueOf(idUsuario));	
					rUpd.setIdPais(idPais.toString());			rUpd.setIdPerfil(idPerfil.toString());		rUpd.setIdTipoUsuario(String.valueOf(idTipoUsuario));							
					rUpd.setIdLDC(idLDC.toString());  			rUpd.setUser(user);						rUpd.setPass(password);						
					rUpd.setComm(comentario);					rUpd.setIdEmpleado(idEmpleado.toString());
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog20(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M205", "Error al invocar el web service que guarda los datos de Usuario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Usuario.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==21){
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog21 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog21Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog21();
					//setea al request los parametros necesarios -- p_IdUser, idPerfil, cve, desc
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdPerfil(String.valueOf(idPerfil));							
					rIns.setCve(clave);							rIns.setDesc(descPerfil.toString());
					resIns = ct.insertarCatalog21(rIns);
					cargaDatosIniciales();
					permisosSelected = null;
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M206", "Error al invocar el web service que guarda los datos de Perfil.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Perfil.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog21 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog21Response resUpd = null;
				//Elimina todo
				insertarPermisosPorPerfil();
				guardarPermisosSelected();
				if(permisosSelected.size()>0){
					if(insertarPermisosPorPerfil()){ msg = "Permisos insertando ...."; }
				}else{
					insertarPermisosPorPerfil();
				}
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
					//Agrega al request la operacion a ejecutar
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog21();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdPerfil(String.valueOf(idPerfil));							
					rUpd.setCve(clave);							rUpd.setDesc(descPerfil.toString());
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog21(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					permisosSelected = null;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M207", "Error al invocar el web service que guarda los datos de Perfil.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Perfil.";
				}finally{ nuevoCatalogo(); }
			}
		}
		if(this.idCatalogo  ==22){
			//Valida tipo
			if(idTipo == 1){ tipo = "B"; }else{ tipo = "M"; }
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarCatalog22 rIns = null;
				MantenimientoElementoControllerStub.InsertarCatalog22Response resIns = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
					//Agrega al request la operacion a ejecutar
					rIns = new MantenimientoElementoControllerStub.InsertarCatalog22();
					//Le setea al request los parametros necesarios --p_IdUser, idDenominacion, denominacion, tipo
					rIns.setP_IdUser(config.getIdUsuario());	rIns.setIdDenominacion(idDenominacion.toString());							
					rIns.setDenominacion(descDenominacion);
					rIns.setTipo(tipo);
					resIns = ct.insertarCatalog22(rIns);
					//Guarda la respuesta en una variable para ser mostrada en la Vista
					cargaDatosIniciales();
					return msg = resIns.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M208", "Error al invocar el web service que guarda los datos de Denominaciones.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Denominaciones.";
				}finally{ nuevoCatalogo(); }
			}else{
				MantenimientoElementoControllerStub.ActualizarCatalog22 rUpd = null;
				MantenimientoElementoControllerStub.ActualizarCatalog22Response resUpd = null;
				try{
					ct = new MantenimientoElementoControllerStub();
					//Obtiene y asigna url de configuración de web services
					String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
					ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
					rUpd = new MantenimientoElementoControllerStub.ActualizarCatalog22();
					//Le setea al request los parametros necesarios
					rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdDenominacion(idDenominacion.toString());							
					rUpd.setDenominacion(descDenominacion);		rUpd.setTipo(tipo);
					//Añade al response el request de la operacion
					resUpd = ct.actualizarCatalog22(rUpd);
					cargaDatosIniciales();
					//Inicializar acciones
					this.accionUpd = false;
					this.eliminarActivado = true;
					this.soloLectura = true;
					return msg = resUpd.get_return();
				} catch (RemoteException excepcionDeInvocacion) {
					Utils.GuardarLogMensajeBD("CUADMIN001.02", "M209", "Error al invocar el web service que guarda los datos de Denominaciones.", excepcionDeInvocacion.toString(), config.getIdUsuario());
					msg = "Error al invocar el servicio que guarda los datos de Denominaciones.";
				}finally{ nuevoCatalogo(); }
			}
		}
		
		if(this.idCatalogo == 23){
			//Validar accion
			if(!this.accionUpd){
				MantenimientoElementoControllerStub.InsertarRutaAlterna rIns = null;
				MantenimientoElementoControllerStub.InsertarRutaAlternaResponse resIns = null;
				//Valida Campos requeridos
				if(catRutasAlternasZona == 0 || catRutasAlternasOrden == 0 
						|| catRutasAlternasRutaOTS.trim().isEmpty() || catRutasAlternasRutaSOS.trim().isEmpty()){
					return msg = "Todos los campos marcados con * son requeridos.";
				} else {
					try{
						ct = new MantenimientoElementoControllerStub();
						//Obtiene y asigna url de configuración de web services
						String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
						ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
						//Agrega al request la operacion a ejecutar
						rIns = new MantenimientoElementoControllerStub.InsertarRutaAlterna();
						//Le setea al request los parametros necesarios
						rIns.setIdZona(this.catRutasAlternasZona);
						rIns.setOrden(this.catRutasAlternasOrden);
						rIns.setRutaOTS(this.catRutasAlternasRutaOTS);
						rIns.setRutaSOS(this.catRutasAlternasRutaSOS);
						rIns.setIdUsuario(config.getIdUsuario());
						//Añade al response el request de la operacion
						resIns = ct.insertarRutaAlterna(rIns);
						cargaDatosIniciales();
						return msg = resIns.get_return();
					} catch (RemoteException excepcionDeInvocacion) {
						Utils.GuardarLogMensajeBD("UCW049_1", "", "Error al invocar el web service que guarda los datos de Rutas Alternas.", excepcionDeInvocacion.toString(), config.getIdUsuario());
						msg = "Error al invocar el web service que guarda los datos de Unidad de Reparto.";
					}finally{ nuevoCatalogo(); }
				}
			}else{
				if(catRutasAlternasZona == 0 || catRutasAlternasOrden == 0 
						|| catRutasAlternasRutaOTS.trim().isEmpty() || catRutasAlternasRutaSOS.trim().isEmpty()){
					return msg = "Todos los campos marcados con * son requeridos.";
				} else {
					MantenimientoElementoControllerStub.ActualizarRutaAlterna rUpd = null;
					MantenimientoElementoControllerStub.ActualizarRutaAlternaResponse resUpd = null;
					try{
						ct = new MantenimientoElementoControllerStub();
						//Obtiene y asigna url de configuración de web services
						String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
						ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
						//Agrega al request la operacion a ejecutar
						rUpd = new MantenimientoElementoControllerStub.ActualizarRutaAlterna();
						//Le setea al request los parametros necesarios
						rUpd.setIdRuta(this.catRutasAlternasIdRutaAlterna);
						rUpd.setIdZona(this.catRutasAlternasZona);
						rUpd.setOrden(this.catRutasAlternasOrden);
						rUpd.setRutaOTS(this.catRutasAlternasRutaOTS);
						rUpd.setRutaSOS(this.catRutasAlternasRutaSOS);
						rUpd.setIdUsuario(config.getIdUsuario());
						//Añade al response el request de la operacion
						resUpd = ct.actualizarRutaAlterna(rUpd);
						cargaDatosIniciales();
						return msg = resUpd.get_return();
					} catch (RemoteException excepcionDeInvocacion) {
						Utils.GuardarLogMensajeBD("CUADMIN001.02", "M167", "Error al invocar el web service que guarda los cambios de Rutas Alternas.", excepcionDeInvocacion.toString(), config.getIdUsuario());
						msg = "Error al invocar el servicio que guarda los cambios de Rutas Alternas.";
					}finally{ nuevoCatalogo(); }
				}
			}
		}
		
		return msg; 
	}
	
	/**
	 * Realiza la consulta de Permisos por Perfil.
	 * @author brenda.estrada
	 * @since 31/01/2012
	 * @throws NullPointerException
	 */
	public void cargarPermisosPorPerfil(Integer pIdPerfil) throws NullPointerException{
		MantenimientoElementoControllerStub.GetPermisosPorModulo req = null;
		MantenimientoElementoControllerStub.GetPermisosPorModuloResponse res = null;
		permisosCheck = new ArrayList<ModuloAccion>();
		com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ModuloAccion[] arrData = null;
		try {
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			req = new MantenimientoElementoControllerStub.GetPermisosPorModulo();
			req.setIdPerfil(pIdPerfil);
			res = ct.getPermisosPorModulo(req);
			arrData = res.get_return();
			//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
			for(int i=0;i<arrData.length;i++){
				//Añade los datos a la lista de forma local mediante el constructor
				permisosCheck.add(new ModuloAccion(arrData[i].getIdModuloAccion(), arrData[i].getIdModulo(), arrData[i].getDescModulo(),
						arrData[i].getIdAccion(), arrData[i].getDescAccion(), "", "", arrData[i].getTienePermiso(), arrData[i].getPermiso(), 0, arrData[i].getDescModuloAccion()));
			}
			// Valida CheckAll ASHE
			//validarPermisos();
		} catch (Exception excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M215", "Error al invocar el web service de consulta los datos de Permisos.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que consulta los datos de Permisos.";
		}finally{  }
	}
	/**
	 * Reliza insercion de permisos mediante ws
	 * @author brenda.estrada
	 * @since 31/01/2012
	 * @return [Boolean]
	 */
	public Boolean insertarPermisosPorPerfil(){
		try{
			MantenimientoElementoControllerStub.InsertarPermisos rIns = null;
			MantenimientoElementoControllerStub.InsertarPermisosResponse resIns = null;
			ct = new MantenimientoElementoControllerStub();
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			if(permisosSelected.size() >0){
				for(ModuloAccion acciones : permisosSelected){
					//Agrega parametros al REQUEST de Operacion
					rIns = new MantenimientoElementoControllerStub.InsertarPermisos();
					//Le setea al request los parametros necesarios
					rIns.setIdUser(config.getIdUsuario());		rIns.setIdAccion(acciones.getIdAccion());							
					rIns.setIdModulo(acciones.getIdModulo());	rIns.setIdPerfil(idPerfil);
					//Añade al response el request de la operacion
					resIns = ct.insertarPermisos(rIns);
					msg += " - " + resIns.get_return();
				}
			}else{
				rIns = new MantenimientoElementoControllerStub.InsertarPermisos();
				//Le setea al request los parametros necesario
				rIns.setIdUser(config.getIdUsuario());		rIns.setIdPerfil(idPerfil);
				//Añade al response el request de la operacion
				resIns = ct.insertarPermisos(rIns);
				msg += " - " + resIns.get_return();
			}
		} catch (RemoteException excepcionDeInvocacion) {
			Utils.GuardarLogMensajeBD("CUADMIN001.02", "M210", "Error al invocar el web service que guarda los datos de Permisos.", excepcionDeInvocacion.toString(), config.getIdUsuario());
			msg = "Error al invocar el servicio que guarda los datos de Permisos.";
			return false;
		}finally{  }
		return true;
	}

	/**
	 * Cancela cualquier accion y reinicia el catalogo
	 * @author brenda.estrada
	 * @since 06/01/2012
	 * @return Forma inicial
	 */
	public String cancelarCatalogo() {
		this.msg = "";
		//Inicializa los campos del bean
		nuevoCatalogo();
		//Formas en false
		//reiniciarForms();
		//Atributos de Form
		//this.idCatalogo = 0;
		//this.descCatalogo = "";
		//this.buscarActivado = true;
		this.eliminarActivado = true;
		return ""; 
		
	}

	/**
	 * Inicializa los atributos de acuerdo al Form Seleccionado
	 * @author brenda.estrada
	 * @since 06/01/2012
	 * @return Inicializa atributos en null
	 */
	public String nuevoCatalogo() {
		//this.msg = "";
		this.accionUpd = false;
		this.eliminarActivado = true;
		//Inicializar var locales del bean correspondientes al form seleccionado
		if(this.idCatalogo == 1){
			this.anio = ""; this.descModelo = "";
			this.capacidadNoCajas = ""; this.color = "";
			this.idUnidadReparto = 0; this.noSerie = "";
			this.noEconomico = ""; this.placas = "";
			this.capacidadNoCajas = ""; this.rendimiento = "";
			this.idEstatus = 1; //Combos
			this.descEstatus = "";
			this.codigoBarras = "";
			this.kilometraje = "";
			this.entregando = 0;
			this.entregadoMostrar = "";
			this.setEntregandoCheck(false);
		}
		if(this.idCatalogo == 2){
			this.descModelo = ""; this.frecuencia = "";
			this.idMarca = 0;
		}
		if(this.idCatalogo == 3){
			this.idMarca = 0;
			this.descMarca = "";
		}
		if(this.idCatalogo == 4){
			this.idAsignacionUnidadReparto = 0; this.noSerie = "";
			this.fAsignada = ""; this.fDenegada = "";
			this.idEmpleado = 0; this.idUnidadReparto = 0;
		}
		if(this.idCatalogo == 5){
			this.idRuta = 0;	this.cveRuta = "";	this.descRuta = "";
			this.idZona = 0;	this.idTipoRuta =0;
			this.domicilio = "";	this.km = 0.0;
			this.noPromOrdenes = 0;	this.tiEfectivo = "HH:MM:SS";
			this.tiTotal = "HH:MM:SS";		this.idTipoRiesgo = 0;
			this.tipoRiesgo = "";
			this.diaReparto = 0;
			this.activoReparto = false;
			this.repartoSinHH = false;
			this.lblVisibleSinHH = false;
			this.chkVisibleSinHH = false;
		}
		if(this.idCatalogo == 6){
			this.idTipoRuta =0;		this.cveTipoRuta = "";	this.descTipoRuta = "";
		}
		if(this.idCatalogo == 7){
			this.idRepresentanteRuta = 0;	this.idRuta = 0;	this.idRepresentante = 0;
			this.fAlta = "";	this.fBaja = "";
			this.seqEntregaAnterior = "";	this.seqEntregaReciente = "";
		}
		if(this.idCatalogo == 8){
			this.idAsignacionRutaChofer = 0;	this.idRuta = 0;	this.idZona = 0;
			this.idEmpleado = 0;
			this.fAlta = "";	this.fBaja ="";	this.idTipoAsig = 0;	this.tipoAsignacion="";
			//listPaises.get(1);		listLDC.get(1);
		}
		if(this.idCatalogo == 9){
			this.macAdress = "";	this.direccionIP = "";	this.noSerie = "";
			this.descMarca = "";	this.descModelo ="";	this.idEstatus = 1;
			this.idDispositivoMovil = 0;
			//listPaises.get(1);		listLDC.get(1);
		}
		if(this.idCatalogo == 10){
			this.nombre = ""; 		this.apPaterno = "";	this.apMaterno ="";
			this.domicilio =""; 	this.feNacimiento ="";	this.mail ="";
			this.rfc ="";			this.feIngreso ="";		this.telefono ="";
			this.idSexo = 0;		this.idEdoCivil =0;		
			this.idPuesto =0;		this.idEstatus = 1;		this.idEmpleado = 0;
			this.sexo = "";			this.edoCivil = "";
			//listLDC.get(1);
		}
		if(this.idCatalogo == 11){
			this.idPuesto =0;	
			//listPaises.get(1);		listLDC.get(1);					
			this.clave = "";	this.descPuesto = "";
		}
		if(this.idCatalogo == 12){
			this.idLineaTransporte = 0;	
			this.telefono = "";							
			this.clave = "";	this.descripcion = "";	this.domicilio = "";
			//listLDC.get(1);
		}
		if(this.idCatalogo == 13){
			this.idInformante = 0;	
			this.idTipoInformante =0; this.descripcion = "";
			//listPaises.get(1);		listLDC.get(1);
		}
		if(this.idCatalogo == 14){
			//listPaises.get(1);		listLDC.get(1);
			this.telefono = "";							
			this.clave = "";	this.domicilio = ""; this.descripcion = "";
			this.idSubbodegaAlmacen = 0;	this.idZona = 0; this.idUserResponsableSubbodega = 0;
		}
		if(this.idCatalogo == 15){
			this.idDestinatario = 0;	
			this.nombre = ""; 			this.apPaterno = "";	this.apMaterno ="";
			this.mail ="";				this.clave = "";		this.idTipoDestinatario = 0;
			//listPaises.get(1);		listLDC.get(1);
		}
		if(this.idCatalogo == 16){
			this.idTipoDestinatario = 0;	this.idDestinatario = 0;
			this.idLDC = 1;				this.idLDC = 1;								
			this.clave = "";				this.descTipoDestinatario = "";		
		}
		if(this.idCatalogo == 17){
			this.idReporteTipoDestinatario = 0;
			this.idTipoDestinatario = 0;						
			this.clave = "";
			this.idReporte = 0;
		}
		if(this.idCatalogo == 18){
			this.idReporte = 0;
			//listPaises.get(1);		listLDC.get(1);							
			this.nombre = "";		this.descripcion = "";		this.comentario = "";	
			this.rutaTemplate = ""; this.nombreTemplate = "";
		}
		if(this.idCatalogo == 19){
			//listPaises.get(1);		listLDC.get(1);
			this.razonSocial="";		this.mail="";				this.clave="";
			this.password="";			this.serverSMTP="";			this.puerto="";
			this.tipoSeguridad="";		this.factorMax="";			this.factorMin="";
			this.codigoBarrasSalida="";	this.codigoBarrasEntrada="";	this.poblacion="";	this.region="";
			//this.logoAVON;			this.logoLDC;
			this.ipServerSOS="";			
		}
		if(this.idCatalogo == 20){
			this.idUsuario = 0;	
			this.idPerfil = 0;		this.idTipoUsuario=0;							
			this.user = "";		this.password="";						
			this.comentario ="";		this.idEmpleado = 0;
			//listPaises.get(1);		listLDC.get(1);
		}
		if(this.idCatalogo == 21){
			this.idPerfil = 0;	this.clave="";		this.descPerfil ="";   
			pnlPermisos.setRendered(false); 		permisosSelected = new ArrayList<ModuloAccion>(); 	selectedIds = new HashMap<Long, Boolean>();
		}
		if(this.idCatalogo == 22){
			this.idDenominacion = 0;							
			this.descDenominacion = "";		this.tipo =""; this.idTipo = 0;
		}
		
		if(this.idCatalogo == 23) {
			this.catRutasAlternasIdRutaAlterna = 0;
			this.catRutasAlternasOrden = 0;
			this.catRutasAlternasRutaOTS = "";
			this.catRutasAlternasRutaSOS = "";
			this.catRutasAlternasZona = 0;
//			this.catRutasAlternasFiltroZona = null;
		}
		
		return ""; 
	}
	
	/**
	 * Obtiene los valores del registro seleccionado. Los setea al bean para modificarlos
	 * @author brenda.estrada
	 * @since 06/01/2012
	 * @return Atributos del registro
	 */
	public String modificarCatalogo() {
		//Activa Actualizar
		this.accionUpd = true;
		this.eliminarActivado = false;
		this.soloLectura = false;
		this.msg = "";
		//valida catalogo seleccionado
		if(this.idCatalogo == 1){
			this.setAnio(anio);
			this.setCapacidadNoCajas(capacidadNoCajas);
			this.setCodigoBarras(codigoBarras);
			this.setColor(color);
			this.setIdUnidadReparto(idUnidadReparto);
			this.setNoSerie(noSerie);
			this.setNoEconomico(noEconomico);
			this.setPlacas(placas);
			this.setCapacidadNoCajas(capacidadNoCajas);
			this.setRendimiento(rendimiento);
			this.setIdEstatus(idEstatus); //Combos
			this.setIdLDC(idLDC);
			this.setIdPais(idPais);
			this.setIdMarca(idMarca);
			this.setKilometraje(kilometraje);
			this.setEntregandoCheck(entregandoCheck);
		}
		if(this.idCatalogo == 2){
			this.setIdModelo(idModelo);
			this.setDescModelo(descModelo);
			this.setFrecuencia(frecuencia);
			this.setIdMarca(idMarca);
		}
		if(this.idCatalogo == 3){
			this.setIdMarca(idMarca);
			this.setDescMarca(descMarca);
		}
		if(this.idCatalogo == 4){
			this.setIdAsignacionUnidadReparto(idAsignacionUnidadReparto);
			this.setNoSerie(noSerie); this.setfAsignada(fAsignada);
			this.setfDenegada(fDenegada);	this.setIdEstatus(idEstatus);
			this.setIdEmpleado(idEmpleado); this.setIdUnidadReparto(idUnidadReparto);
			this.setIdLDC(idLDC); this.setIdPais(idPais);
			
		}
		if(this.idCatalogo == 5){
			this.setIdRuta(idRuta);		
			this.setIdPais(idPais);		this.setIdTipoRuta(idTipoRuta);
			this.setIdZona(idZona);		this.setIdLDC(idLDC);
			this.setCveRuta(cveRuta);	this.setDescRuta(descRuta);
			this.setDomicilio(domicilio);
			this.setKm(km);
			this.setNoPromOrdenes(noPromOrdenes);
			this.setTiEfectivo(tiEfectivo);
			this.setTiTotal(tiTotal);
			if(this.idTipoRiesgo == 1){
				this.setTipoRiesgo("Alto");
			}else if(this.idTipoRiesgo == 2){
				this.setTipoRiesgo("Bajo");
			}else{
				this.setTipoRiesgo("Medio");
			}
			this.setActivoReparto(activoReparto);
			this.setDiaReparto(diaReparto);
			if(this.idCampaniaSinHH == 0){
				this.setRepartoSinHH(false);
			}else{
				this.setRepartoSinHH(true);
			}
			lblVisibleSinHH = true;
			chkVisibleSinHH = true;
		}
		if(this.idCatalogo == 6){
			this.setIdTipoRuta(idTipoRuta);	
			this.setCveTipoRuta(cveTipoRuta);	this.setDescTipoRuta(descTipoRuta); 
			this.setIdPais(idPais);				this.setIdLDC(idLDC);
		}
		if(this.idCatalogo == 7){
			this.setIdRepresentanteRuta(idRepresentanteRuta);	this.setIdRuta(idRuta);
			this.setIdRepresentante(idRepresentante); 			
			this.setFecha1(fecha1);		this.setFecha2(fecha2);		//this.setfAlta(fAlta); this.setfBaja(fBaja);	
			this.setSeqEntregaAnterior(seqEntregaAnterior); this.setSeqEntregaReciente(seqEntregaReciente);
		}
		if(this.idCatalogo == 8){
			this.setIdAsignacionRutaChofer(idAsignacionRutaChofer);	
			this.setIdRuta(idRuta);		this.setIdPais(idPais);			 this.setIdZona(idZona);
			this.setIdLDC(idLDC);		this.setIdEmpleado(idEmpleado);  this.setfAsignada(fAlta);
			this.setfDenegada(fBaja);	
			this.setIdTipoAsig(idTipoAsig);		this.setTipoAsignacion(tipoAsignacion);
		}
		if(this.idCatalogo == 9){
			this.setMacAdress(macAdress);	this.setDireccionIP(direccionIP);	this.setNoSerie(noSerie);
			this.setDescMarca(descMarca);	this.setDescModelo(descModelo);		this.setIdEstatus(idEstatus);
			this.setIdPais(idPais);			this.setIdLDC(idLDC);				this.setIdDispositivoMovil(idDispositivoMovil);
		} 
		if(this.idCatalogo == 10){
			this.setNombre(nombre);			this.setApPaterno(apPaterno);			this.setApMaterno(apMaterno);
			this.setDomicilio(domicilio);	this.setFeNacimiento(feNacimiento);		this.setMail(mail);
			this.setRfc(rfc);				this.setFeIngreso(feIngreso);			this.setTelefono(telefono);
			this.setIdSexo(idSexo);			this.setIdEdoCivil(idEdoCivil);			this.setIdLDC(idLDC);
			this.setIdPuesto(idPuesto);		this.setIdEstatus(idEstatus);			this.setIdEmpleado(idEmpleado);
			if(idSexo == 1){ setSexo("M");  }else{ setSexo("H"); }
			if(idEdoCivil == 1){ setEdoCivil("S"); }else{ setEdoCivil("C"); }
		}
		if(this.idCatalogo == 11){
			this.setIdPuesto(idPuesto);
			this.setIdPais(idPais);		this.setIdLDC(idLDC);					
			this.setClave(clave);		this.setDescPuesto(descPuesto);
		}
		if(this.idCatalogo == 12){
			this.setIdLineaTransporte(idLineaTransporte);	
			this.setIdPais(idPais);		this.setTelefono(telefono);							
			this.setClave(clave);		this.setDescripcion(descripcion);	this.setDomicilio(domicilio);
		}
		if(this.idCatalogo == 13){
			this.setIdInformante(idInformante);	this.setDescripcion(descripcion);	
			this.setIdPais(idPais);				this.setIdLDC(idLDC);	this.setIdTipoInformante(idTipoInformante);	
		}
		if(this.idCatalogo == 14){
			this.setIdPais(idPais);		this.setIdLDC(idLDC);			this.setTelefono(telefono);							
			this.setClave(clave);		this.setDomicilio(domicilio); 	this.setDescripcion(descripcion);
			this.setIdSubbodegaAlmacen(idSubbodegaAlmacen);				this.setIdZona(idZona);			this.setIdUserResponsableSubbodega(idUserResponsableSubbodega);
		}
		if(this.idCatalogo == 15){
			this.setIdDestinatario(idDestinatario);		this.setIdPais(idPais);			this.setIdLDC(idLDC);	
			this.setNombre(nombre);						this.setApPaterno(apPaterno);	this.setApMaterno(apMaterno);
			this.setMail(mail);							this.setClave(clave);			this.setIdTipoDestinatario(idTipoDestinatario);
		}
		if(this.idCatalogo == 16){
			this.setIdTipoDestinatario(idTipoDestinatario);		
			this.setIdPais(idPais);								this.setIdLDC(idLDC);								
			this.setClave(clave);								this.setDescTipoDestinatario(descTipoDestinatario);
		}
		if(this.idCatalogo == 17){
			this.setIdReporteTipoDestinatario(idReporteTipoDestinatario);
			this.setIdTipoDestinatario(idTipoDestinatario);							
			this.setClave(idReporte.toString());	
		}
		if(this.idCatalogo == 18){
			this.setIdReporte(idReporte);
			this.setIdPais(idPais);		this.setIdLDC(idLDC);							
			this.setNombre(nombre);		this.setDescripcion(descripcion);		this.setComentario(comentario);	
			this.setRutaTemplate(rutaTemplate);		this.setNombreTemplate(nombreTemplate);
		}
		if(this.idCatalogo == 19){
			this.setIdLDC(idLDC);				this.setDescLDC(descLDC);
			this.setRazonSocial(razonSocial);	this.setMail(mail);					this.setClave(clave);
			this.setPassword(password);			this.setServerSMTP(serverSMTP);		this.setPuerto(puerto);
			this.setTipoSeguridad(tipoSeguridad);	this.setFactorMax(factorMax);	this.setFactorMin(factorMin);
			this.setCodigoBarrasSalida(codigoBarrasSalida);	this.setCodigoBarrasEntrada(codigoBarrasEntrada);	this.setPoblacion(poblacion);	this.setRegion(region);
			this.setIdPais(idPais);		this.setLogoAVON(logoAVON);	this.setLogoLDC(logoLDC);
			this.setIpServerSOS(ipServerSOS);
		}
		if(this.idCatalogo == 20){
			this.setIdUsuario(idUsuario);	
			this.setIdPais(idPais);		this.setIdLDC(idLDC);		this.setIdPerfil(idPerfil);			this.setIdTipoUsuario(idTipoUsuario);							
			this.setUser(user);			this.setPassword(password);	this.setComentario(comentario);		this.setIdEmpleado(idEmpleado);
		}
		if(this.idCatalogo == 21){
			this.setIdPerfil(idPerfil);		this.setClave(clave); 	this.setDescPerfil(descPerfil);
			pnlPermisos.setRendered(true);
			//Obtiene los permisos por perfil
			cargarPermisosPorPerfil(idPerfil);
		}
		if(this.idCatalogo == 22){
			this.setIdDenominacion(idDenominacion);							
			this.setDescDenominacion(descDenominacion);		this.setTipo(tipo); 	this.setIdTipo(idTipo);
		}
		return ""; 
	}
	
	//MENSAJES de 210 al 233
	/**
	 * Elimina el registro seleccionado de la Bd, mediante el WS
	 * @author brenda.estrada
	 * @since 09/01/2012
	 * @return String de Exito o Falla al invocar el WS
	 * @throws IOException 
	 */
	public String eliminarCatalogo() throws IOException { 
		this.msg = "";
		//Valida el Catalogo seleccionado
		if(this.idCatalogo == 1){
			//Se hace la instancia del metodo
			MantenimientoElementoControllerStub.EliminarCatalog1 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog1Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog1();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUnidadReparto(this.idUnidadReparto);
				rUpd.setP_IdUser(config.getIdUsuario()); 
				resUpd = ct.eliminarCatalog1(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M210", "Error al invocar el web service que elimina el registro de Unidad de Reparto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Unidad de Reparto.";
			}finally{  }
		}
		if(this.idCatalogo == 2){
			//Se hace la instancia del metodo
			MantenimientoElementoControllerStub.EliminarCatalog2 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog2Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog2();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdModelo(this.idModelo);
				rUpd.setP_IdUser(config.getIdUsuario()); 
				resUpd = ct.eliminarCatalog2(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M211", "Error al invocar el web service que elimina el registro de Modelo.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Modelo.";
			}finally{  }
		}
		if(this.idCatalogo == 3){
			MantenimientoElementoControllerStub.EliminarCatalog3 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog3Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog3();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdMarca(this.idMarca);
				rUpd.setP_IdUser(config.getIdUsuario()); 
				resUpd = ct.eliminarCatalog3(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M212", "Error al invocar el web service que elimina el registro de Marca.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Marca.";
			}finally{  }
		}
		if(this.idCatalogo == 4){
			MantenimientoElementoControllerStub.EliminarCatalog4 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog4Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog4();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdAsignacion(this.idAsignacionUnidadReparto.toString());
				rUpd.setP_IdUser(config.getIdUsuario()); 
				resUpd = ct.eliminarCatalog4(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M213", "Error al invocar el web service que elimina el registro de Asignación de Unidad de Reparto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Asignación de Unidad de Reparto.";
			}finally{  }
		}
		if(this.idCatalogo == 5){
			MantenimientoElementoControllerStub.EliminarCatalog5 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog5Response resUpd = null;
			try{
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog5();
				//Le setea al request los parametros necesarios -- idRuta, p_IdUser
				rUpd.setIdRuta(idRuta);	rUpd.setP_IdUser(config.getIdUsuario());
				resUpd = ct.eliminarCatalog5(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M214", "Error al invocar el web service que elimina el registro de Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Ruta.";
			}finally{  }
		}
		if(this.idCatalogo == 6){
			MantenimientoElementoControllerStub.EliminarCatalog6 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog6Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog6();
				//Le setea al request los parametros necesarios--p_IdUser, idTipoRuta
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdTipoRuta(idTipoRuta.toString());
				resUpd = ct.eliminarCatalog6(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M215", "Error al invocar el web service que elimina el registro de Tipo Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Tipo Ruta.";
			}finally{  }
		}
		if(this.idCatalogo == 7){
			MantenimientoElementoControllerStub.EliminarCatalog7 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog7Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog7();
				//Le setea al request los parametros necesarios--p_IdUser, idRepresentaRuta
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdRepresentaRuta(this.idRepresentanteRuta.toString());
				resUpd = ct.eliminarCatalog7(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M216", "Error al invocar el web service que elimina el registro de Representante por Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Representante por Ruta.";
			}finally{  }
		}
		if(this.idCatalogo == 8){
			MantenimientoElementoControllerStub.EliminarCatalog8 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog8Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog8();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdAsignaRutaChofer(idAsignacionRutaChofer.toString());
				resUpd = ct.eliminarCatalog8(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M217", "Error al invocar el web service que elimina el registro de Asignacion de Ruta.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Asignacion de Ruta.";
			}finally{  }
		}
		if(this.idCatalogo == 9){
			MantenimientoElementoControllerStub.EliminarCatalog9 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog9Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog9();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdDispositivo(idDispositivoMovil.toString());
				resUpd = ct.eliminarCatalog9(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M218", "Error al invocar el web service que elimina el registro de HandHeld.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de HandHeld.";
			}finally{  }
		}
		if(this.idCatalogo == 10){
			MantenimientoElementoControllerStub.EliminarCatalog10 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog10Response resUpd = null;
			try{
				ct = new MantenimientoElementoControllerStub();
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog10();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdEmpleado(idEmpleado.toString());
				resUpd = ct.eliminarCatalog10(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M219", "Error al invocar el web service que elimina el registro de Empleado.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Empleado.";
			}finally{  }
		}
		if(this.idCatalogo == 11){
			MantenimientoElementoControllerStub.EliminarCatalog11 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog11Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog11();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdPuesto(idPuesto.toString());
				resUpd = ct.eliminarCatalog11(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M220", "Error al invocar el web service que elimina el registro de Puesto.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Puesto.";
			}finally{  }
		}
		if(this.idCatalogo == 12){
			MantenimientoElementoControllerStub.EliminarCatalog12 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog12Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog12();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdLineaTransporte(idLineaTransporte.toString());
				resUpd = ct.eliminarCatalog12(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M221", "Error al invocar el web service que elimina el registro de Linea de Transporte.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Linea de Transporte.";
			}finally{  }
		}
		if(this.idCatalogo == 13){
			MantenimientoElementoControllerStub.EliminarCatalog13 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog13Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog13();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdInformante(idInformante.toString());
				resUpd = ct.eliminarCatalog13(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M222", "Error al invocar el web service que elimina el registro de Informante.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Informante.";
			}finally{  }
		}
		if(this.idCatalogo == 14){
			MantenimientoElementoControllerStub.EliminarCatalog14 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog14Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog14();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdSubbodega(idSubbodegaAlmacen.toString());
				resUpd = ct.eliminarCatalog14(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M223", "Error al invocar el web service que elimina el registro de Sub-Bodega.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Sub-Bodega.";
			}finally{  }
		}
		if(this.idCatalogo == 15){
			MantenimientoElementoControllerStub.EliminarCatalog15 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog15Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog15();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdDestinatario(idDestinatario.toString());
				resUpd = ct.eliminarCatalog15(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M224", "Error al invocar el web service que elimina el registro de Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Destinatario.";
			}finally{  }
		}
		if(this.idCatalogo == 16){
			MantenimientoElementoControllerStub.EliminarCatalog16 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog16Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog16();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdTipoDestinatario(idTipoDestinatario.toString());
				resUpd = ct.eliminarCatalog16(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M225", "Error al invocar el web service que elimina el registro de Tipo Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Tipo Destinatario.";
			}finally{  }
		}
		if(this.idCatalogo == 17){
			MantenimientoElementoControllerStub.EliminarCatalog17 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog17Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog17();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdReporteTipoDestinatario(idReporteTipoDestinatario.toString());
				resUpd = ct.eliminarCatalog17(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M226", "Error al invocar el web service que elimina el registro de Reporte por Tipo Destinatario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Reporte por Tipo Destinatario.";
			}finally{  }
		}
		if(this.idCatalogo == 18){
			MantenimientoElementoControllerStub.EliminarCatalog18 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog18Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog18();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdReporte(idReporte.toString());
				resUpd = ct.eliminarCatalog18(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M227", "Error al invocar el web service que elimina el registro de Reporte.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Reporte.";
			}finally{  }
		}
		if(this.idCatalogo == 19){
			MantenimientoElementoControllerStub.EliminarCatalog19 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog19Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog19();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdLDC(idLDC.toString());
				resUpd = ct.eliminarCatalog19(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M228", "Error al invocar el web service que elimina el registro de LDC.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de LDC.";
			}finally{  }
		}
		if(this.idCatalogo == 20){
			MantenimientoElementoControllerStub.EliminarCatalog20 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog20Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog20();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdUserCatalogo(String.valueOf(idUsuario));
				resUpd = ct.eliminarCatalog20(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M229", "Error al invocar el web service que elimina el registro de Usuario.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Usuario.";
			}finally{  }
		}
		if(this.idCatalogo == 21){
			MantenimientoElementoControllerStub.EliminarCatalog21 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog21Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog21();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdPerfil(idPerfil.toString());
				resUpd = ct.eliminarCatalog21(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M230", "Error al invocar el web service que elimina el registro de Perfil.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Perfil.";
			}finally{  }
		}
		if(this.idCatalogo == 22){
			MantenimientoElementoControllerStub.EliminarCatalog22 rUpd = null;
			MantenimientoElementoControllerStub.EliminarCatalog22Response resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarCatalog22();
				//Le setea al request los parametros necesarios
				rUpd.setP_IdUser(config.getIdUsuario());	rUpd.setIdDenominacion(idDenominacion.toString());
				resUpd = ct.eliminarCatalog22(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("CUADMIN001.02", "M231", "Error al invocar el web service que elimina el registro de Denominaciones.", excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Denominaciones.";
			}finally{  }
		}
		
		if(this.idCatalogo == 23) {
			//Se hace la instancia del metodo
			MantenimientoElementoControllerStub.EliminarRutaAlterna rUpd = null;
			MantenimientoElementoControllerStub.EliminarRutaAlternaResponse resUpd = null;
			try{
				//Crea el cliente
				ct = new MantenimientoElementoControllerStub();
				
				//Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
				ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
						url));
				
				//Agrega al request la operacion a ejecutar
				rUpd = new MantenimientoElementoControllerStub.EliminarRutaAlterna();
				//Le setea al request los parametros necesarios
				rUpd.setIdRuta(this.catRutasAlternasIdRutaAlterna);
				rUpd.setIdUsuario(config.getIdUsuario());
				config.getIdUsuario();
				resUpd = ct.eliminarRutaAlterna(rUpd);
				//Guarda la respuesta en una variable para ser mostrada en la Vista
				cargaDatosIniciales();
				return msg = resUpd.get_return();
			} catch (RemoteException excepcionDeInvocacion) {
				Utils.GuardarLogMensajeBD("UCW049_1", "M210", 
						"Error al invocar el web service que elimina el registro de Rutas Alternas.", 
						excepcionDeInvocacion.toString(), config.getIdUsuario());
				msg = "Error al invocar el servicio que elimina el registro de Rutas Alternas.";
			} finally {
				
			}
		}
		
		//Retorno msg del WS
		return msg; 
	}
	
	
	/* ***************************** Metodo Boolean para aplicar Filtros ************************************* */
	/**
	 * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.
	 * @param  current  - Tipo Objeto se recibe desde interfaz
	 * @author brenda.estrada
	 * @since 05/01/2012
	 * @return boolean true si encuentra el valor
	 * @return boolean false Objecto nulo
	 */
	public Boolean filterDescMarcas(Object current) {
		try{
			UnidadReparto currentMarca = (UnidadReparto)current;
			if (filterValue.length()==0  || filterValue.equals("%")) {
				return true;
			}
			if (currentMarca.getDescMarca().toLowerCase().contains(filterValue.toLowerCase())) {
				return true;
			}else {
				return false; 
			}
		}catch(NullPointerException nil){return false;}
	}
	
	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.
	  * @param  anio  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	  * @since 05/01/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterAnios(Object anio) {
		 try{ 
			 UnidadReparto currentAnio = (UnidadReparto)anio;
			 if (currentAnio.getAnio().contains(filterValueAnio) || filterValueAnio.equals("%")) {
				 return true;
			 }else {
				 return false; 
			 }
		 }catch(NullPointerException nil){return false;}
	 }
	 
	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro. C4
	  * @param  noSerie  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	  * @since 10/01/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterNoSerie(Object noSerie) {
		 try{
			 AsignacionUnidadReparto currentSerie = (AsignacionUnidadReparto)noSerie;
			 if (filterValueNoSerie.length()==0 || filterValueNoSerie.equals("%")) {
				 return true;
			 }
			 if (currentSerie.getNoSerie().toLowerCase().contains(filterValueNoSerie.toLowerCase())) {
				 
				 return true;
			 }else {
				 return false; 
			 }
		 }catch(NullPointerException nil){return false;}
	 }
	 
	 
	 public Boolean filterZonaRutaAlterna(Object zona) {
		 try{
			 RutaAlterna currentZona = (RutaAlterna) zona;
			 if (catRutasAlternasFiltroZona.length()==0 || catRutasAlternasFiltroZona.equals("%")) {
				 return true;
			 }
			 if (currentZona.getZona().toLowerCase().contains(catRutasAlternasFiltroZona.toLowerCase())) {
				 
				 return true;
			 }else {
				 return false; 
			 }
		 }catch(NullPointerException nil){return false;}
	 }
	 
	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro. C4
	  * @param  fAsig  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	  * @since 10/01/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterFeAsignada(Object fAsig) {
		 try{
			 AsignacionUnidadReparto currentAsig = (AsignacionUnidadReparto)fAsig;
			 if (filterValuefAsignada.length()==0 || filterValuefAsignada.equals("%")) {
				 return true;
			 }
			 if (currentAsig.getfAsignada().contains(filterValuefAsignada)) {
				 return true;
			 }else {
				 return false; 
			 }
		 }catch(NullPointerException nil){return false;}
	 }

	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la
	  * condición del filtro. C4
	  * @param fDen - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	  * @since 10/01/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterFeDenegada(Object fDen) {
		 try {
			 AsignacionUnidadReparto currentDen = (AsignacionUnidadReparto) fDen;
			 if (filterValuefDenegada.length() == 0 || filterValuefDenegada.equals("%")) {
				 return true;
			 }
			 if (currentDen.getfDenegada().contains(filterValuefDenegada)) {
				 return true;
			 } else {
				 return false;
			 }
		 }catch(NullPointerException nil){return false;}
	 }
	/**
	 * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.
	 * @param  emp  - Tipo Objeto se recibe desde interfaz
	 * @author brenda.estrada
	 * @since 10/01/2012
	 * @return boolean true si encuentra el valor
	 * @return boolean false Objecto nulo
	 */
	public Boolean filterDescEmpleado(Object emp) {
		try{
			AsignacionUnidadReparto currentEmp = (AsignacionUnidadReparto)emp;
			if (filterValueEmpleado.length()==0 || filterValueEmpleado.equals("%")) {
				return true;
			}
			if (currentEmp.getDescEmpleado().toLowerCase().contains(filterValueEmpleado.toLowerCase())) {
				return true;
			}else {
				return false; 
			}
		}catch(NullPointerException nil){return false;}
	}
	/**
	 * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.C5
	 * @param  ruta  - Tipo Objeto se recibe desde interfaz
	 * @author brenda.estrada
	 * @since 10/01/2012
	 * @return boolean true si encuentra el valor
	 * @return boolean false Objecto nulo
	 */
	public Boolean filterClaveRuta(Object ruta) {
		try{
			com.datacode.avon_ots_admin.model.Rutas currentRuta = (com.datacode.avon_ots_admin.model.Rutas)ruta;
			if (filterValueCveRuta.length()==0 || filterValueCveRuta.equals("%")) {
				return true;
			}
			if (currentRuta.getCveRuta().toLowerCase().contains(filterValueCveRuta.toLowerCase())) {
				return true;
			}else {  return false;  }
		}catch(NullPointerException nil){return false;}
	}
	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.C5
	  * @param  zona  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	  * @since 09/02/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterZonas(Object zona) {
		 try{
			 com.datacode.avon_ots_admin.model.Rutas currentZona = (com.datacode.avon_ots_admin.model.Rutas)zona;
			 if (filterValueZona.length()==0 || filterValueZona.equals("%")) {
				 return true;
			 }
			 if (currentZona.getDescZona().toLowerCase().contains(filterValueZona.toLowerCase())) {
				 return true;
			 }else {
				 return false; 
			 }
		 }catch(NullPointerException nil){return false;}
	 }

	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.C7
	  * @param  cve  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	   * @since 09/02/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterClaveRutaRepresentante(Object cve) {
		 try{
			 RepresentantesPorRuta currentRepRuta = (RepresentantesPorRuta)cve;
			 if (filterValueCveRuta.length()==0 || filterValueCveRuta.equals("%")) {
				 return true;
			 }
			 if (currentRepRuta.getDescRuta().toLowerCase().contains(filterValueCveRuta.toLowerCase())) {
				 return true;
			 }else {
				 return false; 
			 }
		 }catch(NullPointerException nil){return false;}
	 }
	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.C8
	  * @param  cve  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	   * @since 09/02/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterClaveRutaAsigna(Object cve) {
		 try{
			 AsignacionRutaChofer currentRepRuta = (AsignacionRutaChofer)cve;
			 if (filterValueCveRuta.length()==0  || filterValueCveRuta.equals("%")) {
				 return true;
			 }
			 if (currentRepRuta.getDescRuta().toLowerCase().contains(filterValueCveRuta.toLowerCase())) {
				 return true;
			 }else {
				 return false; 
			 }
		 }catch(NullPointerException nil){return false;}
	 }
	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.C8
	  * @param  emp  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	  * @since 09/02/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterEmpleadoAsigna(Object emp) {
		 try{
			 AsignacionRutaChofer currentEmp = (AsignacionRutaChofer)emp;
			 if(currentEmp != null){
				 if (filterValueEmpleado.length()==0 || filterValueEmpleado.equals("%")) {
					 return true;
				 }
				 if (currentEmp.getDescEmpleado().toLowerCase().contains(filterValueEmpleado.toLowerCase())) {
					 return true;
				 }else {
					 return false; 
				 }
			 }
		 }catch(NullPointerException nil){return false;}
		 return false;
	 }
	 
	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.C8
	  * @param  emp  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	  * @since 09/02/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterFechaAsigAsigna(Object emp) {
		 try{
			 AsignacionRutaChofer currentEmp = (AsignacionRutaChofer)emp;
			 if(currentEmp != null){
				 if (filterValuefAsignada.length()==0 || filterValuefAsignada.equals("%")) {
					 return true;
				 }
				 if (currentEmp.getfAsignada().contains(filterValuefAsignada.toLowerCase())) {
					 return true;
				 }else {
					 return false; 
				 }
			 }
		 }catch(NullPointerException nil){return false;}
		 return false;
	 }
	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.C8
	  * @param  emp  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	  * @since 09/02/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterFechaDenAsigna(Object emp) {
		 try{
			 AsignacionRutaChofer currentEmp = (AsignacionRutaChofer)emp;
			 if(currentEmp != null){
				 if (filterValuefDenegada.length()==0 || filterValuefDenegada.equals("%")) {
					 return true;
				 }
				 if (currentEmp.getfDenegada().contains(filterValuefDenegada)) {
					 return true;
				 }else {
					 return false; 
				 }
			 }
		 }catch(NullPointerException nil){return false;}
		 return false;
	 }
	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.C9
	  * @param  cve  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	   * @since 09/02/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterNoSerieDispositivo(Object cve) {
		 try{
			 DispositivoMovil currentRepRuta = (DispositivoMovil)cve;
			 if (filterValueNoSerie.length()==0 || filterValueNoSerie.equals("%")) {
				 return true;
			 }
			 if (currentRepRuta.getNoSerie().toLowerCase().contains(filterValueNoSerie.toLowerCase())) {
				 return true;
			 }else {
				 return false; 
			 }
		 }catch(NullPointerException nil){return false;}
	 }
	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.C9
	  * @param  cve  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	   * @since 09/02/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterMarcaDispositivo(Object cve) {
		 try{
			 DispositivoMovil currentRepRuta = (DispositivoMovil)cve;
			 if (filterValue.length()==0 || filterValue.equals("%")) {
				 return true;
			 }
			 if (currentRepRuta.getMarca().toLowerCase().contains(filterValue.toLowerCase())) {
				 return true;
			 }else {
				 return false; 
			 }
		 }catch(NullPointerException nil){return false;}
	 }
	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.C9
	  * @param  cve  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	   * @since 09/02/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterModeloDispositivo(Object cve) {
		 try{
			 DispositivoMovil currentRepRuta = (DispositivoMovil)cve;
			 if (filterValueZona.length()==0 || filterValueZona.equals("%")) {
				 return true;
			 }
			 if (currentRepRuta.getModelo().toLowerCase().contains(filterValueZona.toLowerCase())) {
				 return true;
			 }else {
				 return false; 
			 }
		 }catch(NullPointerException nil){return false;}
	 }
	 
	 
	 /**
		 * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.
		 * @param  current  - Tipo Objeto se recibe desde interfaz
		 * @author brenda.estrada
		 * @since 05/01/2012
		 * @return boolean true si encuentra el valor
		 * @return boolean false Objecto nulo
		 */
		public Boolean filterNombreEmpleado(Object current) {
			try{
				Empleado currentMarca = (Empleado)current;
				if (filterValue.length()==0  || filterValue.equals("%")) {
					return true;
				}
				if (currentMarca.getNombre().toLowerCase().contains(filterValue.toLowerCase())) {
					return true;
				}else {
					return false; 
				}
			}catch(NullPointerException nil){return false;}
		}
		
		 /**
		 * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.
		 * @param  current  - Tipo Objeto se recibe desde interfaz
		 * @author brenda.estrada
		 * @since 05/01/2012
		 * @return boolean true si encuentra el valor
		 * @return boolean false Objecto nulo
		 */
		public Boolean filterPaternoEmpleado(Object current) {
			try{
				Empleado currentMarca = (Empleado)current;
				if (filterValueCveRuta.length()==0  || filterValueCveRuta.equals("%")) {
					return true;
				}
				if (currentMarca.getApPaterno().toLowerCase().contains(filterValueCveRuta.toLowerCase())) {
					return true;
				}else {
					return false; 
				}
			}catch(NullPointerException nil){return false;}
		}
		
		 /**
		 * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.
		 * @param  current  - Tipo Objeto se recibe desde interfaz
		 * @author brenda.estrada
		 * @since 05/01/2012
		 * @return boolean true si encuentra el valor
		 * @return boolean false Objecto nulo
		 */
		public Boolean filterMaternoEmpleado(Object current) {
			try{
				Empleado currentMarca = (Empleado)current;
				if (filterValueEmpleado.length()==0  || filterValueEmpleado.equals("%")) {
					return true;
				}
				if (currentMarca.getApMaterno().toLowerCase().contains(filterValueEmpleado.toLowerCase())) {
					return true;
				}else {
					return false; 
				}
			}catch(NullPointerException nil){return false;}
		}
		
	 /**
	  * Metodo utilizado para validar si el Object recibido satisface la condición del filtro.
	  * @param  current  - Tipo Objeto se recibe desde interfaz
	  * @author brenda.estrada
	  * @since 01/02/2012
	  * @return boolean true si encuentra el valor
	  * @return boolean false Objecto nulo
	  */
	 public Boolean filterModulo(Object current) {
		 try{
			 ModuloAccion modulo = (ModuloAccion)current;
			 if (filterValue.length()==0 || filterValue.equals("%")) {
				 return true;
			 }
			 if (modulo.getDescModulo().toLowerCase().contains(filterValue.toLowerCase())) {
				 return true;
			 }else {
				 return false; 
			 }
		 }catch(NullPointerException nil){return false;}
	 }
	 
	 /***
	  *@author jessica.leon
	  *@since 08/05/2013
	  *Método que obtiene las rutas filtradas por la zona
	  *para el catálogo de Asignación de Ruta
	  */
	 public void getRutasPorZonaAsignacionRuta(ValueChangeEvent e) {
			setIdZona(Integer.parseInt(e.getNewValue().toString()));
			descTipoRuta = null;
			getRutasPorZona();
		}
	 
	 /***
	  * @author jessica.leon
	  * @since 08/05/2013
	  * Método que consulta las rutas que pertencen a una ruta especificada.
	  * 
	  */
	 public List<SelectItem> getRutasPorZona() {

		    Rutas[] arrRutas = new Rutas[0];
		    Utils utils = new Utils();
		 	List<SelectItem> listRutas = new ArrayList<SelectItem>();
			arrRutas = utils.getRutas("CUADMIN003_02",getIdLDC(),getIdZona(),0);
			listRutas.add(new SelectItem(-1, "Selecciona una opción"));
			//tiposRutas = new HashMap<String,String>();
			
			try {
				if (arrRutas != null) {
					for (int i = 0; i < arrRutas.length; i++) {
						listRutas.add(new SelectItem(arrRutas[i].getIdRuta(),
								arrRutas[i].getCveRuta() +" - " + arrRutas[i].getDescTipoRuta()));
					}
				}
			} catch (NullPointerException e) {
				msg = "Error al obtener los datos de Rutas.";
			}
			return listRutas;
		}

	 
	 
	 /* **********************  EXPORTAR GRID A EXCEL Y PDF ************************************************************* */
	 /**
	  * Obtiene la lista del Catalogo Seleccionado y envia para Generar Reporte en EXcel
	  * @author brenda.estrada
	  * @since 25/01/2012
	  * @return Output ContentType[Excel]
	  * @throws IOException
	  */
	 public String exportarGridExcel() {
		ControllerGridMtoElemento grid = new ControllerGridMtoElemento();
		if(this.idCatalogo  == 1){
			 grid.exportC1(unidades, "XLS");
		}
		if(this.idCatalogo  == 2){
			grid.exportC2(modelos, "XLS");
		}
		if(this.idCatalogo  == 3){
			 grid.exportC3(marcas, "XLS");
		}
		if(this.idCatalogo  == 4){
			 grid.exportC4(asignaciones, "XLS");
		}
		if(this.idCatalogo  == 5){
			grid.exportC5(rutas, "XLS");
		}
		if(this.idCatalogo  == 6){
			 grid.exportC6(tipoRutas, "XLS");
		}
		if(this.idCatalogo  == 7){
			 grid.exportC7(representanteRutas, "XLS");
		}
		if(this.idCatalogo  == 8){
			 grid.exportC8(asignaRutasChofer, "XLS");
		}
		if(this.idCatalogo  == 9){
			 grid.exportC9(dispositivosMovil, "XLS");
		}
		if(this.idCatalogo  == 10){
			 grid.exportC10(empleados, "XLS");
		}
		if(this.idCatalogo  == 11){
			 grid.exportC11(puestos, "XLS");
		}
		if(this.idCatalogo  == 12){
			 grid.exportC12(lineasTransporte, "XLS");
		}
		if(this.idCatalogo  == 13){
			 grid.exportC13(informantes, "XLS");
		}
		if(this.idCatalogo  == 14){
			 grid.exportC14(subbodegasAlmacen, "XLS");
		}
		if(this.idCatalogo  == 15){
			 grid.exportC15(destinatarios, "XLS");
		}
		if(this.idCatalogo  == 16){
			 grid.exportC16(tipoDestinatarios, "XLS");
		}
		if(this.idCatalogo  == 17){
			 grid.exportC17(reportesTipoDestinatario, "XLS");
		}
		if(this.idCatalogo  == 18){
			 grid.exportC18(reportes, "XLS");
		}
		if(this.idCatalogo  == 19){
			 grid.exportC19(ldccs, "XLS");
		}
		if(this.idCatalogo  == 20){
			 grid.exportC20(users, "XLS");
		}
		if(this.idCatalogo  == 21){
			 grid.exportC21(perfiles, "XLS");
		}
		if(this.idCatalogo  == 22){
			 grid.exportC22(denominaciones, "XLS");
		}
		if(this.idCatalogo == 23) {
			grid.exportC23(listRutasAlternas, "XLS");
		}
		 return "";
	 }
	 /**
	  * Obtiene la lista del Catalogo Seleccionado y envia para Generar Reporte en PDF
	  * @author brenda.estrada
	  * @since 25/01/2012
	  * @return Output ContentType[PDF]
	  * @throws IOException
	  */
	 public String exportarGridPDF() {
		 ControllerGridMtoElemento grid = new ControllerGridMtoElemento();
			if(this.idCatalogo  == 1){
				 grid.exportC1(unidades, "PDF");
			}
			if(this.idCatalogo  == 2){
				grid.exportC2(modelos, "PDF");
			}
			if(this.idCatalogo  == 3){
				 grid.exportC3(marcas, "PDF");
			}
			if(this.idCatalogo  == 4){
				 grid.exportC4(asignaciones, "PDF");
			}
			if(this.idCatalogo  == 5){
				grid.exportC5(rutas, "PDF");
			}
			if(this.idCatalogo  == 6){
				 grid.exportC6(tipoRutas, "PDF");
			}
			if(this.idCatalogo  == 7){
				 grid.exportC7(representanteRutas, "PDF");
			}
			if(this.idCatalogo  == 8){
				 grid.exportC8(asignaRutasChofer, "PDF");
			}
			if(this.idCatalogo  == 9){
				 grid.exportC9(dispositivosMovil, "PDF");
			}
			if(this.idCatalogo  == 10){
				 grid.exportC10(empleados, "PDF");
			}
			if(this.idCatalogo  == 11){
				 grid.exportC11(puestos, "PDF");
			}
			if(this.idCatalogo  == 12){
				 grid.exportC12(lineasTransporte, "PDF");
			}
			if(this.idCatalogo  == 13){
				 grid.exportC13(informantes, "PDF");
			}
			if(this.idCatalogo  == 14){
				 grid.exportC14(subbodegasAlmacen, "PDF");
			}
			if(this.idCatalogo  == 15){
				 grid.exportC15(destinatarios, "PDF");
			}
			if(this.idCatalogo  == 16){
				 grid.exportC16(tipoDestinatarios, "PDF");
			}
			if(this.idCatalogo  == 17){
				 grid.exportC17(reportesTipoDestinatario, "PDF");
			}
			if(this.idCatalogo  == 18){
				 grid.exportC18(reportes, "PDF");
			}
			if(this.idCatalogo  == 19){
				 grid.exportC19(ldccs, "PDF");
			}
			if(this.idCatalogo  == 20){
				 grid.exportC20(users, "PDF");
			}
			if(this.idCatalogo  == 21){
				 grid.exportC21(perfiles, "PDF");
			}
			if(this.idCatalogo  == 22){
				 grid.exportC22(denominaciones, "PDF");
			}
			if(this.idCatalogo == 23) {
				grid.exportC23(listRutasAlternas, "PDF");
			}
		 return "";
	 }

	 private String validarKilometrajeUnidadReparto(final String p_kilometraje) {
		 try {
			 if (p_kilometraje == null) {
				 return "El kilometraje ingresado no es v\u00E1lido";
			 }
			 int v_kilometraje = Integer.valueOf(p_kilometraje);
			 if (v_kilometraje > 999999) {
				 return "El kilometraje ingresado excede los 6 d\u00EDgitos";
			 } else if (v_kilometraje < 0) {
				 return "El kilometraje ingresado no puede ser menor a cero";
			 }
		 } catch (final NumberFormatException e) {
			 return "El kilometraje ingresado no es v\u00E1lido";
		 }
		 return "";
	 }
	 
	public String generarCodigosBarras() {
		msg = "";
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().containsKey("tipoGeneracionCodigos")) {
			FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().remove("tipoGeneracionCodigos");
		}
		if (FacesContext.getCurrentInstance().getExternalContext() 
				.getSessionMap().containsKey("idGeneracionCodigos")) {
			FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().remove("idGeneracionCodigos");
		}
		if (FacesContext.getCurrentInstance().getExternalContext() 
				.getSessionMap().containsKey("lstIdsGeneracionCodigos")) {
			FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().remove("lstIdsGeneracionCodigos");
		}
		
		StringBuffer lstIdsGeneracionCodigos = new StringBuffer();
		int cont = 0;
		for (UnidadReparto u : unidades) {
			if (u.getImprCodBarras()) {
				if (cont == 0) {
					lstIdsGeneracionCodigos.append(u.getIdUnidadReparto());
					cont++;
				} else {
					lstIdsGeneracionCodigos.append(",");
					lstIdsGeneracionCodigos.append(u.getIdUnidadReparto());
				}
			}
		}

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
			.put("tipoGeneracionCodigos", "UNIDAD_REPARTO");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
			.put("idGeneracionCodigos", 0);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
			.put("lstIdsGeneracionCodigos", lstIdsGeneracionCodigos);
		
		if (lstIdsGeneracionCodigos.length() == 0) {
			msg = "Por favor seleccione una unidad de reparto.";
			return "";
		} else {
			return "generar";
		}
	}
	
	public void validarCodigosBarras() {
		msg = "";
		StringBuffer lstIdsGeneracionCodigos = new StringBuffer();
		int cont = 0;
		for (UnidadReparto u : unidades) {
			if (u.getImprCodBarras()) {
				if (cont == 0) {
					lstIdsGeneracionCodigos.append(u.getIdUnidadReparto());
					cont++;
				} else {
					lstIdsGeneracionCodigos.append(",");
					lstIdsGeneracionCodigos.append(u.getIdUnidadReparto());
				}
			}
		}
		if (lstIdsGeneracionCodigos.length() == 0) {
			msg = "Por favor seleccione una unidad de reparto.";
		}
	}
	
	public void selecTodasUnidadesReparto () {
//		todasUnidadesReparto = (Boolean) e.getNewValue();
		todasUnidadesReparto = !todasUnidadesReparto;
		for (UnidadReparto u : unidades) {
			u.setImprCodBarras(todasUnidadesReparto);
		}
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
	 * @return the idEstatus
	 */
	public Integer getIdEstatus() {
		return idEstatus;
	}

	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	/**
	 * @return the descEstatus
	 */
	public String getDescEstatus() {
		return descEstatus;
	}

	/**
	 * @param descEstatus the descEstatus to set
	 */
	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
	}

	/**
	 * @return the idMarca
	 */
	public Integer getIdMarca() {
		return idMarca;
	}

	/**
	 * @param idMarca the idMarca to set
	 */
	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	/**
	 * @return the descMarca
	 */
	public String getDescMarca() {
		return descMarca;
	}

	/**
	 * @param descMarca the descMarca to set
	 */
	public void setDescMarca(String descMarca) {
		this.descMarca = descMarca;
	}

	/**
	 * @return the idLDC
	 */
	public Integer getIdLDC() {
		return idLDC;
	}

	/**
	 * @param idLDC the idLDC to set
	 */
	public void setIdLDC(Integer idLDC) {
		this.idLDC = idLDC;
	}

	/**
	 * @return the descLDC
	 */
	public String getDescLDC() {
		return descLDC;
	}

	/**
	 * @param descLDC the descLDC to set
	 */
	public void setDescLDC(String descLDC) {
		this.descLDC = descLDC;
	}

	/**
	 * @return the idPais
	 */
	public Integer getIdPais() {
		return idPais;
	}

	/**
	 * @param idPais the idPais to set
	 */
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	/**
	 * @return the descPais
	 */
	public String getDescPais() {
		return descPais;
	}

	/**
	 * @param descPais the descPais to set
	 */
	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}

	/**
	 * @return the idUnidadReparto
	 */
	public Integer getIdUnidadReparto() {
		return idUnidadReparto;
	}

	/**
	 * @param idUnidadReparto the idUnidadReparto to set
	 */
	public void setIdUnidadReparto(Integer idUnidadReparto) {
		this.idUnidadReparto = idUnidadReparto;
	}

	/**
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * @return the noSerie
	 */
	public String getNoSerie() {
		return noSerie;
	}

	/**
	 * @param noSerie the noSerie to set
	 */
	public void setNoSerie(String noSerie) {
		this.noSerie = noSerie;
	}

	/**
	 * @return the noEconomico
	 */
	public String getNoEconomico() {
		return noEconomico;
	}

	/**
	 * @param noEconomico the noEconomico to set
	 */
	public void setNoEconomico(String noEconomico) {
		this.noEconomico = noEconomico;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the placas
	 */
	public String getPlacas() {
		return placas;
	}

	/**
	 * @param placas the placas to set
	 */
	public void setPlacas(String placas) {
		this.placas = placas;
	}

	/**
	 * @return the capacidadNoCajas
	 */
	public String getCapacidadNoCajas() {
		return capacidadNoCajas;
	}

	/**
	 * @param capacidadNoCajas the capacidadNoCajas to set
	 */
	public void setCapacidadNoCajas(String capacidadNoCajas) {
		this.capacidadNoCajas = capacidadNoCajas;
	}

	/**
	 * @return the rendimiento
	 */
	public String getRendimiento() {
		return rendimiento;
	}

	/**
	 * @param rendimiento the rendimiento to set
	 */
	public void setRendimiento(String rendimiento) {
		this.rendimiento = rendimiento;
	}

	/**
	 * @return the codigoBarras
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * @param codigoBarras the codigoBarras to set
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	/**
	 * @return the unidades
	 */
	public List<UnidadReparto> getUnidades() {
		return unidades;
	}

	/**
	 * @param unidades the unidades to set
	 */
	public void setUnidades(List<UnidadReparto> unidades) {
		this.unidades = unidades;
	}

	/**
	 * @return the unidad
	 */
	public UnidadReparto getUnidad() {
		return unidad;
	}

	/**
	 * @param unidad the unidad to set
	 */
	public void setUnidad(UnidadReparto unidad) {
		this.unidad = unidad;
	}

	/**
	 * @return the descModelo
	 */
	public String getDescModelo() {
		return descModelo;
	}

	/**
	 * @param descModelo the descModelo to set
	 */
	public void setDescModelo(String descModelo) {
		this.descModelo = descModelo;
	}

	/**
	 * @return the filterValue
	 */
	public String getFilterValue() {
		return filterValue;
	}


	/**
	 * @param filterValue the filterValue to set
	 */
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}


	/**
	 * @return the filterValueAnio
	 */
	public String getFilterValueAnio() {
		return filterValueAnio;
	}

	/**
	 * @param filterValueAnio the filterValueAnio to set
	 */
	public void setFilterValueAnio(String filterValueAnio) {
		this.filterValueAnio = filterValueAnio;
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
	 * @return the buscarActivado
	 */
	public Boolean getBuscarActivado() {
		return buscarActivado;
	}

	/**
	 * @param buscarActivado the buscarActivado to set
	 */
	public void setBuscarActivado(Boolean buscarActivado) {
		this.buscarActivado = buscarActivado;
	}

	/**
	 * @return the guardarActivado
	 */
	public Boolean getGuardarActivado() {
		return guardarActivado;
	}

	/**
	 * @param guardarActivado the guardarActivado to set
	 */
	public void setGuardarActivado(Boolean guardarActivado) {
		this.guardarActivado = guardarActivado;
	}

	/**
	 * @return the modificarActivado
	 */
	public Boolean getModificarActivado() {
		return modificarActivado;
	}

	/**
	 * @param modificarActivado the modificarActivado to set
	 */
	public void setModificarActivado(Boolean modificarActivado) {
		this.modificarActivado = modificarActivado;
	}

	/**
	 * @return the eliminarActivado
	 */
	public Boolean getEliminarActivado() {
		return eliminarActivado;
	}

	/**
	 * @param eliminarActivado the eliminarActivado to set
	 */
	public void setEliminarActivado(Boolean eliminarActivado) {
		this.eliminarActivado = eliminarActivado;
	}

	/**
	 * @return the accionUpd
	 */
	public Boolean getAccionUpd() {
		return accionUpd;
	}

	/**
	 * @param accionUpd the accionUpd to set
	 */
	public void setAccionUpd(Boolean accionUpd) {
		this.accionUpd = accionUpd;
	}

	/**
	 * @return the txtAnio
	 */
	public UIInput getTxtAnio() {
		return txtAnio;
	}

	/**
	 * @param txtAnio the txtAnio to set
	 */
	public void setTxtAnio(UIInput txtAnio) {
		this.txtAnio = txtAnio;
	}

	/**
	 * @return the soloLectura
	 */
	public Boolean getSoloLectura() {
		return soloLectura;
	}

	/**
	 * @param soloLectura the soloLectura to set
	 */
	public void setSoloLectura(Boolean soloLectura) {
		this.soloLectura = soloLectura;
	}

	/**
	 * @return the frecuencia
	 */
	public String getFrecuencia() {
		return frecuencia;
	}

	/**
	 * @param frecuencia the frecuencia to set
	 */
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
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
	 * @return the modelos
	 */
	public List<Modelo> getModelos() {
		return modelos;
	}

	/**
	 * @param modelos the modelos to set
	 */
	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	/**
	 * @return the modelo
	 */
	public Modelo getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the idModelo
	 */
	public Integer getIdModelo() {
		return idModelo;
	}

	/**
	 * @param idModelo the idModelo to set
	 */
	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}
	/**
	 * @return the marcas
	 */
	public List<com.datacode.avon_ots_admin.model.Marca> getMarcas() {
		return marcas;
	}
	/**
	 * @param marcas the marcas to set
	 */
	public void setMarcas(List<com.datacode.avon_ots_admin.model.Marca> marcas) {
		this.marcas = marcas;
	}
	/**
	 * @return the marca
	 */
	public com.datacode.avon_ots_admin.model.Marca getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(com.datacode.avon_ots_admin.model.Marca marca) {
		this.marca = marca;
	}
	/**
	 * @return the idEmpleado
	 */
	public Integer getIdEmpleado() {
		return idEmpleado;
	}
	/**
	 * @param idEmpleado the idEmpleado to set
	 */
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	/**
	 * @return the descEmpleado
	 */
	public String getDescEmpleado() {
		return descEmpleado;
	}
	/**
	 * @param descEmpleado the descEmpleado to set
	 */
	public void setDescEmpleado(String descEmpleado) {
		this.descEmpleado = descEmpleado;
	}
	/**
	 * @return the fAsignada
	 */
	public String getfAsignada() {
		return fAsignada;
	}
	/**
	 * @param fAsignada the fAsignada to set
	 */
	public void setfAsignada(String fAsignada) {
		this.fAsignada = fAsignada;
	}
	/**
	 * @return the fDenegada
	 */
	public String getfDenegada() {
		return fDenegada;
	}
	/**
	 * @param fDenegada the fDenegada to set
	 */
	public void setfDenegada(String fDenegada) {
		this.fDenegada = fDenegada;
	}
	/**
	 * @return the asignaciones
	 */
	public List<AsignacionUnidadReparto> getAsignaciones() {
		return asignaciones;
	}
	/**
	 * @param asignaciones the asignaciones to set
	 */
	public void setAsignaciones(List<AsignacionUnidadReparto> asignaciones) {
		this.asignaciones = asignaciones;
	}
	/**
	 * @return the asignacion
	 */
	public AsignacionUnidadReparto getAsignacion() {
		return asignacion;
	}
	/**
	 * @param asignacion the asignacion to set
	 */
	public void setAsignacion(AsignacionUnidadReparto asignacion) {
		this.asignacion = asignacion;
	}
	/**
	 * @return the idAsignacionUnidadReparto
	 */
	public Integer getIdAsignacionUnidadReparto() {
		return idAsignacionUnidadReparto;
	}
	/**
	 * @param idAsignacionUnidadReparto the idAsignacionUnidadReparto to set
	 */
	public void setIdAsignacionUnidadReparto(Integer idAsignacionUnidadReparto) {
		this.idAsignacionUnidadReparto = idAsignacionUnidadReparto;
	}
	/**
	 * @return the empleados
	 */
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	/**
	 * @param empleados the empleados to set
	 */
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	/**
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}
	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	/**
	 * @return the idTipoRuta
	 */
	public Integer getIdTipoRuta() {
		return idTipoRuta;
	}
	/**
	 * @param idTipoRuta the idTipoRuta to set
	 */
	public void setIdTipoRuta(Integer idTipoRuta) {
		this.idTipoRuta = idTipoRuta;
	}
	/**
	 * @return the tipoRutas
	 */
	public List<TipoRuta> getTipoRutas() {
		return tipoRutas;
	}
	/**
	 * @param tipoRutas the tipoRutas to set
	 */
	public void setTipoRutas(List<TipoRuta> tipoRutas) {
		this.tipoRutas = tipoRutas;
	}
	/**
	 * @return the tipoRuta
	 */
	public TipoRuta getTipoRuta() {
		return tipoRuta;
	}
	/**
	 * @param tipoRuta the tipoRuta to set
	 */
	public void setTipoRuta(TipoRuta tipoRuta) {
		this.tipoRuta = tipoRuta;
	}
	/**
	 * @return the idRuta
	 */
	public Integer getIdRuta() {
		return idRuta;
	}
	/**
	 * @param idRuta the idRuta to set
	 */
	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}
	/**
	 * @return the descRuta
	 */
	public String getDescRuta() {
		return descRuta;
	}
	/**
	 * @param descRuta the descRuta to set
	 */
	public void setDescRuta(String descRuta) {
		this.descRuta = descRuta;
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
	 * @return the descTipoRuta
	 */
	public String getDescTipoRuta() {
		return descTipoRuta;
	}
	/**
	 * @param descTipoRuta the descTipoRuta to set
	 */
	public void setDescTipoRuta(String descTipoRuta) {
		this.descTipoRuta = descTipoRuta;
	}
	/**
	 * @return the cveTipoRuta
	 */
	public String getCveTipoRuta() {
		return cveTipoRuta;
	}
	/**
	 * @param cveTipoRuta the cveTipoRuta to set
	 */
	public void setCveTipoRuta(String cveTipoRuta) {
		this.cveTipoRuta = cveTipoRuta;
	}
	/**
	 * @return the rutas
	 */
	public List<com.datacode.avon_ots_admin.model.Rutas> getRutas() {
		return rutas;
	}
	/**
	 * @param rutas the rutas to set
	 */
	public void setRutas(List<com.datacode.avon_ots_admin.model.Rutas> rutas) {
		this.rutas = rutas;
	}
	/**
	 * @return the ruta
	 */
	public com.datacode.avon_ots_admin.model.Rutas getRuta() {
		return ruta;
	}
	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(com.datacode.avon_ots_admin.model.Rutas ruta) {
		this.ruta = ruta;
	}
	/**
	 * @return the idZona
	 */
	public Integer getIdZona() {
		return idZona;
	}
	/**
	 * @param idZona the idZona to set
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
	 * @param descZona the descZona to set
	 */
	public void setDescZona(String descZona) {
		this.descZona = descZona;
	}
	/**
	 * @return the filterValueNoSerie
	 */
	public String getFilterValueNoSerie() {
		return filterValueNoSerie;
	}
	/**
	 * @param filterValueNoSerie the filterValueNoSerie to set
	 */
	public void setFilterValueNoSerie(String filterValueNoSerie) {
		this.filterValueNoSerie = filterValueNoSerie;
	}
	/**
	 * @return the filterValuefAsignada
	 */
	public String getFilterValuefAsignada() {
		return filterValuefAsignada;
	}
	/**
	 * @param filterValuefAsignada the filterValuefAsignada to set
	 */
	public void setFilterValuefAsignada(String filterValuefAsignada) {
		this.filterValuefAsignada = filterValuefAsignada;
	}
	/**
	 * @return the filterValuefDenegada
	 */
	public String getFilterValuefDenegada() {
		return filterValuefDenegada;
	}
	/**
	 * @param filterValuefDenegada the filterValuefDenegada to set
	 */
	public void setFilterValuefDenegada(String filterValuefDenegada) {
		this.filterValuefDenegada = filterValuefDenegada;
	}
	/**
	 * @return the filterValueEmpleado
	 */
	public String getFilterValueEmpleado() {
		return filterValueEmpleado;
	}
	/**
	 * @param filterValueEmpleado the filterValueEmpleado to set
	 */
	public void setFilterValueEmpleado(String filterValueEmpleado) {
		this.filterValueEmpleado = filterValueEmpleado;
	}
	/**
	 * @return the filterValueCveRuta
	 */
	public String getFilterValueCveRuta() {
		return filterValueCveRuta;
	}
	/**
	 * @param filterValueCveRuta the filterValueCveRuta to set
	 */
	public void setFilterValueCveRuta(String filterValueCveRuta) {
		this.filterValueCveRuta = filterValueCveRuta;
	}
	/**
	 * @return the filterValueZona
	 */
	public String getFilterValueZona() {
		return filterValueZona;
	}
	/**
	 * @param filterValueZona the filterValueZona to set
	 */
	public void setFilterValueZona(String filterValueZona) {
		this.filterValueZona = filterValueZona;
	}
	/**
	 * @return the fAlta
	 */
	public String getfAlta() {
		return fAlta;
	}
	/**
	 * @param fAlta the fAlta to set
	 */
	public void setfAlta(String fAlta) {
		this.fAlta = fAlta;
	}
	/**
	 * @return the fBaja
	 */
	public String getfBaja() {
		return fBaja;
	}
	/**
	 * @param fBaja the fBaja to set
	 */
	public void setfBaja(String fBaja) {
		this.fBaja = fBaja;
	}
	/**
	 * @return the seqEntregaAnterior
	 */
	public String getSeqEntregaAnterior() {
		return seqEntregaAnterior;
	}
	/**
	 * @param seqEntregaAnterior the seqEntregaAnterior to set
	 */
	public void setSeqEntregaAnterior(String seqEntregaAnterior) {
		this.seqEntregaAnterior = seqEntregaAnterior;
	}
	/**
	 * @return the seqEntregaReciente
	 */
	public String getSeqEntregaReciente() {
		return seqEntregaReciente;
	}
	/**
	 * @param seqEntregaReciente the seqEntregaReciente to set
	 */
	public void setSeqEntregaReciente(String seqEntregaReciente) {
		this.seqEntregaReciente = seqEntregaReciente;
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
	 * @return the idRepresentanteRuta
	 */
	public Integer getIdRepresentanteRuta() {
		return idRepresentanteRuta;
	}
	/**
	 * @param idRepresentanteRuta the idRepresentanteRuta to set
	 */
	public void setIdRepresentanteRuta(Integer idRepresentanteRuta) {
		this.idRepresentanteRuta = idRepresentanteRuta;
	}
	/**
	 * @return the idRepresentante
	 */
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	/**
	 * @param idRepresentante the idRepresentante to set
	 */
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	/**
	 * @return the representanteRutas
	 */
	public List<RepresentantesPorRuta> getRepresentanteRutas() {
		return representanteRutas;
	}
	/**
	 * @param representanteRutas the representanteRutas to set
	 */
	public void setRepresentanteRutas(List<RepresentantesPorRuta> representanteRutas) {
		this.representanteRutas = representanteRutas;
	}
	/**
	 * @return the representanteRuta
	 */
	public RepresentantesPorRuta getRepresentanteRuta() {
		return representanteRuta;
	}
	/**
	 * @param representanteRuta the representanteRuta to set
	 */
	public void setRepresentanteRuta(RepresentantesPorRuta representanteRuta) {
		this.representanteRuta = representanteRuta;
	}
	/**
	 * @return the descUnidadReparto
	 */
	public String getDescUnidadReparto() {
		return descUnidadReparto;
	}
	/**
	 * @param descUnidadReparto the descUnidadReparto to set
	 */
	public void setDescUnidadReparto(String descUnidadReparto) {
		this.descUnidadReparto = descUnidadReparto;
	}
	/**
	 * @return the cancelarActivado
	 */
	public Boolean getCancelarActivado() {
		return cancelarActivado;
	}
	/**
	 * @param cancelarActivado the cancelarActivado to set
	 */
	public void setCancelarActivado(Boolean cancelarActivado) {
		this.cancelarActivado = cancelarActivado;
	}
	/**
	 * @return the nuevoActivado
	 */
	public Boolean getNuevoActivado() {
		return nuevoActivado;
	}
	/**
	 * @param nuevoActivado the nuevoActivado to set
	 */
	public void setNuevoActivado(Boolean nuevoActivado) {
		this.nuevoActivado = nuevoActivado;
	}
	/**
	 * @return the idAsignacionRutaChofer
	 */
	public Integer getIdAsignacionRutaChofer() {
		return idAsignacionRutaChofer;
	}
	/**
	 * @param idAsignacionRutaChofer the idAsignacionRutaChofer to set
	 */
	public void setIdAsignacionRutaChofer(Integer idAsignacionRutaChofer) {
		this.idAsignacionRutaChofer = idAsignacionRutaChofer;
	}
	/**
	 * @return the tipoAsignacion
	 */
	public String getTipoAsignacion() {
		return tipoAsignacion;
	}
	/**
	 * @param tipoAsignacion the tipoAsignacion to set
	 */
	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}
	/**
	 * @return the asignaRutasChofer
	 */
	public List<AsignacionRutaChofer> getAsignaRutasChofer() {
		return asignaRutasChofer;
	}
	/**
	 * @param asignaRutasChofer the asignaRutasChofer to set
	 */
	public void setAsignaRutasChofer(List<AsignacionRutaChofer> asignaRutasChofer) {
		this.asignaRutasChofer = asignaRutasChofer;
	}
	/**
	 * @return the asignaRutaChofer
	 */
	public AsignacionRutaChofer getAsignaRutaChofer() {
		return asignaRutaChofer;
	}
	/**
	 * @param asignaRutaChofer the asignaRutaChofer to set
	 */
	public void setAsignaRutaChofer(AsignacionRutaChofer asignaRutaChofer) {
		this.asignaRutaChofer = asignaRutaChofer;
	}
	/**
	 * @return the idTipoAsig
	 */
	public Integer getIdTipoAsig() {
		return idTipoAsig;
	}
	/**
	 * @param idTipoAsig the idTipoAsig to set
	 */
	public void setIdTipoAsig(Integer idTipoAsig) {
		this.idTipoAsig = idTipoAsig;
	}
	/**
	 * @return the descTipoAsig
	 */
	public String getDescTipoAsig() {
		return descTipoAsig;
	}
	/**
	 * @param descTipoAsig the descTipoAsig to set
	 */
	public void setDescTipoAsig(String descTipoAsig) {
		this.descTipoAsig = descTipoAsig;
	}
	/**
	 * @return the idDispositivoMovil
	 */
	public Integer getIdDispositivoMovil() {
		return idDispositivoMovil;
	}
	/**
	 * @param idDispositivoMovil the idDispositivoMovil to set
	 */
	public void setIdDispositivoMovil(Integer idDispositivoMovil) {
		this.idDispositivoMovil = idDispositivoMovil;
	}
	/**
	 * @return the macAdress
	 */
	public String getMacAdress() {
		return macAdress;
	}
	/**
	 * @param macAdress the macAdress to set
	 */
	public void setMacAdress(String macAdress) {
		this.macAdress = macAdress;
	}
	/**
	 * @return the direccionIP
	 */
	public String getDireccionIP() {
		return direccionIP;
	}
	/**
	 * @param direccionIP the direccionIP to set
	 */
	public void setDireccionIP(String direccionIP) {
		this.direccionIP = direccionIP;
	}
	/**
	 * @return the dispositivosMovil
	 */
	public List<DispositivoMovil> getDispositivosMovil() {
		return dispositivosMovil;
	}
	/**
	 * @param dispositivosMovil the dispositivosMovil to set
	 */
	public void setDispositivosMovil(List<DispositivoMovil> dispositivosMovil) {
		this.dispositivosMovil = dispositivosMovil;
	}
	/**
	 * @return the dispositivoMovil
	 */
	public DispositivoMovil getDispositivoMovil() {
		return dispositivoMovil;
	}
	/**
	 * @param dispositivoMovil the dispositivoMovil to set
	 */
	public void setDispositivoMovil(DispositivoMovil dispositivoMovil) {
		this.dispositivoMovil = dispositivoMovil;
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
	 * @return the apPaterno
	 */
	public String getApPaterno() {
		return apPaterno;
	}
	/**
	 * @param apPaterno the apPaterno to set
	 */
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	/**
	 * @return the apMaterno
	 */
	public String getApMaterno() {
		return apMaterno;
	}
	/**
	 * @param apMaterno the apMaterno to set
	 */
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
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
	 * @return the feNacimiento
	 */
	public String getFeNacimiento() {
		return feNacimiento;
	}
	/**
	 * @param feNacimiento the feNacimiento to set
	 */
	public void setFeNacimiento(String feNacimiento) {
		this.feNacimiento = feNacimiento;
	}
	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}
	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 * @return the edoCivil
	 */
	public String getEdoCivil() {
		return edoCivil;
	}
	/**
	 * @param edoCivil the edoCivil to set
	 */
	public void setEdoCivil(String edoCivil) {
		this.edoCivil = edoCivil;
	}
	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}
	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	/**
	 * @return the feIngreso
	 */
	public String getFeIngreso() {
		return feIngreso;
	}
	/**
	 * @param feIngreso the feIngreso to set
	 */
	public void setFeIngreso(String feIngreso) {
		this.feIngreso = feIngreso;
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
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the idSexo
	 */
	public Integer getIdSexo() {
		return idSexo;
	}
	/**
	 * @param idSexo the idSexo to set
	 */
	public void setIdSexo(Integer idSexo) {
		this.idSexo = idSexo;
	}
	/**
	 * @return the idEdoCivil
	 */
	public Integer getIdEdoCivil() {
		return idEdoCivil;
	}
	/**
	 * @param idEdoCivil the idEdoCivil to set
	 */
	public void setIdEdoCivil(Integer idEdoCivil) {
		this.idEdoCivil = idEdoCivil;
	}
	/**
	 * @return the idPuesto
	 */
	public Integer getIdPuesto() {
		return idPuesto;
	}
	/**
	 * @param idPuesto the idPuesto to set
	 */
	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}
	/**
	 * @return the descPuesto
	 */
	public String getDescPuesto() {
		return descPuesto;
	}
	/**
	 * @param descPuesto the descPuesto to set
	 */
	public void setDescPuesto(String descPuesto) {
		this.descPuesto = descPuesto;
	}
	/**
	 * @return the puestos
	 */
	public List<Puesto> getPuestos() {
		return puestos;
	}
	/**
	 * @param puestos the puestos to set
	 */
	public void setPuestos(List<Puesto> puestos) {
		this.puestos = puestos;
	}
	/**
	 * @return the puesto
	 */
	public Puesto getPuesto() {
		return puesto;
	}
	/**
	 * @param puesto the puesto to set
	 */
	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}
	/**
	 * @return the lineasTransporte
	 */
	public List<com.datacode.avon_ots_admin.model.LineaTransporte> getLineasTransporte() {
		return lineasTransporte;
	}
	/**
	 * @param lineasTransporte the lineasTransporte to set
	 */
	public void setLineasTransporte(
			List<com.datacode.avon_ots_admin.model.LineaTransporte> lineasTransporte) {
		this.lineasTransporte = lineasTransporte;
	}
	/**
	 * @return the lineaTransporte
	 */
	public com.datacode.avon_ots_admin.model.LineaTransporte getLineaTransporte() {
		return lineaTransporte;
	}
	/**
	 * @param lineaTransporte the lineaTransporte to set
	 */
	public void setLineaTransporte(
			com.datacode.avon_ots_admin.model.LineaTransporte lineaTransporte) {
		this.lineaTransporte = lineaTransporte;
	}
	/**
	 * @return the idLineaTransporte
	 */
	public Integer getIdLineaTransporte() {
		return idLineaTransporte;
	}
	/**
	 * @param idLineaTransporte the idLineaTransporte to set
	 */
	public void setIdLineaTransporte(Integer idLineaTransporte) {
		this.idLineaTransporte = idLineaTransporte;
	}
	/**
	 * @return the idInformante
	 */
	public Integer getIdInformante() {
		return idInformante;
	}
	/**
	 * @param idInformante the idInformante to set
	 */
	public void setIdInformante(Integer idInformante) {
		this.idInformante = idInformante;
	}
	/**
	 * @return the idTipoInformante
	 */
	public Integer getIdTipoInformante() {
		return idTipoInformante;
	}
	/**
	 * @param idTipoInformante the idTipoInformante to set
	 */
	public void setIdTipoInformante(Integer idTipoInformante) {
		this.idTipoInformante = idTipoInformante;
	}
	/**
	 * @return the descTipoInformante
	 */
	public String getDescTipoInformante() {
		return descTipoInformante;
	}
	/**
	 * @param descTipoInformante the descTipoInformante to set
	 */
	public void setDescTipoInformante(String descTipoInformante) {
		this.descTipoInformante = descTipoInformante;
	}
	/**
	 * @return the informantes
	 */
	public List<Informante> getInformantes() {
		return informantes;
	}
	/**
	 * @param informantes the informantes to set
	 */
	public void setInformantes(List<Informante> informantes) {
		this.informantes = informantes;
	}
	/**
	 * @return the informante
	 */
	public Informante getInformante() {
		return informante;
	}
	/**
	 * @param informante the informante to set
	 */
	public void setInformante(Informante informante) {
		this.informante = informante;
	}
	/**
	 * @return the idSubbodegaAlmacen
	 */
	public Integer getIdSubbodegaAlmacen() {
		return idSubbodegaAlmacen;
	}
	/**
	 * @param idSubbodegaAlmacen the idSubbodegaAlmacen to set
	 */
	public void setIdSubbodegaAlmacen(Integer idSubbodegaAlmacen) {
		this.idSubbodegaAlmacen = idSubbodegaAlmacen;
	}
	/**
	 * @return the idUserResponsableSubbodega
	 */
	public Integer getIdUserResponsableSubbodega() {
		return idUserResponsableSubbodega;
	}
	/**
	 * @param idUserResponsableSubbodega the idUserResponsableSubbodega to set
	 */
	public void setIdUserResponsableSubbodega(Integer idUserResponsableSubbodega) {
		this.idUserResponsableSubbodega = idUserResponsableSubbodega;
	}
	/**
	 * @return the descUserResponsableSubbodega
	 */
	public String getDescUserResponsableSubbodega() {
		return descUserResponsableSubbodega;
	}
	/**
	 * @param descUserResponsableSubbodega the descUserResponsableSubbodega to set
	 */
	public void setDescUserResponsableSubbodega(String descUserResponsableSubbodega) {
		this.descUserResponsableSubbodega = descUserResponsableSubbodega;
	}
	/**
	 * @return the subbodegasAlmacen
	 */
	public List<SubBodegaAlmacen> getSubbodegasAlmacen() {
		return subbodegasAlmacen;
	}
	/**
	 * @param subbodegasAlmacen the subbodegasAlmacen to set
	 */
	public void setSubbodegasAlmacen(List<SubBodegaAlmacen> subbodegasAlmacen) {
		this.subbodegasAlmacen = subbodegasAlmacen;
	}
	/**
	 * @return the subbodegaAlmacen
	 */
	public SubBodegaAlmacen getSubbodegaAlmacen() {
		return subbodegaAlmacen;
	}
	/**
	 * @param subbodegaAlmacen the subbodegaAlmacen to set
	 */
	public void setSubbodegaAlmacen(SubBodegaAlmacen subbodegaAlmacen) {
		this.subbodegaAlmacen = subbodegaAlmacen;
	}
	/**
	 * @return the idDestinatario
	 */
	public Integer getIdDestinatario() {
		return idDestinatario;
	}
	/**
	 * @param idDestinatario the idDestinatario to set
	 */
	public void setIdDestinatario(Integer idDestinatario) {
		this.idDestinatario = idDestinatario;
	}
	/**
	 * @return the idTipoDestinatario
	 */
	public Integer getIdTipoDestinatario() {
		return idTipoDestinatario;
	}
	/**
	 * @param idTipoDestinatario the idTipoDestinatario to set
	 */
	public void setIdTipoDestinatario(Integer idTipoDestinatario) {
		this.idTipoDestinatario = idTipoDestinatario;
	}
	/**
	 * @return the descTipoDestinatario
	 */
	public String getDescTipoDestinatario() {
		return descTipoDestinatario;
	}
	/**
	 * @param descTipoDestinatario the descTipoDestinatario to set
	 */
	public void setDescTipoDestinatario(String descTipoDestinatario) {
		this.descTipoDestinatario = descTipoDestinatario;
	}
	/**
	 * @return the destinatarios
	 */
	public List<Destinatario> getDestinatarios() {
		return destinatarios;
	}
	/**
	 * @param destinatarios the destinatarios to set
	 */
	public void setDestinatarios(List<Destinatario> destinatarios) {
		this.destinatarios = destinatarios;
	}
	/**
	 * @return the destinatario
	 */
	public Destinatario getDestinatario() {
		return destinatario;
	}
	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}
	/**
	 * @return the tipoDestinatarios
	 */
	public List<TipoDestinatario> getTipoDestinatarios() {
		return tipoDestinatarios;
	}
	/**
	 * @param tipoDestinatarios the tipoDestinatarios to set
	 */
	public void setTipoDestinatarios(List<TipoDestinatario> tipoDestinatarios) {
		this.tipoDestinatarios = tipoDestinatarios;
	}
	/**
	 * @return the tipoDestinatario
	 */
	public TipoDestinatario getTipoDestinatario() {
		return tipoDestinatario;
	}
	/**
	 * @param tipoDestinatario the tipoDestinatario to set
	 */
	public void setTipoDestinatario(TipoDestinatario tipoDestinatario) {
		this.tipoDestinatario = tipoDestinatario;
	}
	/**
	 * @return the idReporteTipoDestinatario
	 */
	public Integer getIdReporteTipoDestinatario() {
		return idReporteTipoDestinatario;
	}
	/**
	 * @param idReporteTipoDestinatario the idReporteTipoDestinatario to set
	 */
	public void setIdReporteTipoDestinatario(Integer idReporteTipoDestinatario) {
		this.idReporteTipoDestinatario = idReporteTipoDestinatario;
	}
	/**
	 * @return the reportesTipoDestinatario
	 */
	public List<ReporteTipoDestinatario> getReportesTipoDestinatario() {
		return reportesTipoDestinatario;
	}
	/**
	 * @param reportesTipoDestinatario the reportesTipoDestinatario to set
	 */
	public void setReportesTipoDestinatario(
			List<ReporteTipoDestinatario> reportesTipoDestinatario) {
		this.reportesTipoDestinatario = reportesTipoDestinatario;
	}
	/**
	 * @return the reporteTipoDestinatario
	 */
	public ReporteTipoDestinatario getReporteTipoDestinatario() {
		return reporteTipoDestinatario;
	}
	/**
	 * @param reporteTipoDestinatario the reporteTipoDestinatario to set
	 */
	public void setReporteTipoDestinatario(
			ReporteTipoDestinatario reporteTipoDestinatario) {
		this.reporteTipoDestinatario = reporteTipoDestinatario;
	}
	/**
	 * @return the idReporte
	 */
	public Integer getIdReporte() {
		return idReporte;
	}
	/**
	 * @param idReporte the idReporte to set
	 */
	public void setIdReporte(Integer idReporte) {
		this.idReporte = idReporte;
	}
	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}
	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	/**
	 * @return the rutaTemplate
	 */
	public String getRutaTemplate() {
		return rutaTemplate;
	}
	/**
	 * @param rutaTemplate the rutaTemplate to set
	 */
	public void setRutaTemplate(String rutaTemplate) {
		this.rutaTemplate = rutaTemplate;
	}
	/**
	 * @return the nombreTemplate
	 */
	public String getNombreTemplate() {
		return nombreTemplate;
	}
	/**
	 * @param nombreTemplate the nombreTemplate to set
	 */
	public void setNombreTemplate(String nombreTemplate) {
		this.nombreTemplate = nombreTemplate;
	}
	/**
	 * @return the reportes
	 */
	public List<Reporte> getReportes() {
		return reportes;
	}
	/**
	 * @param reportes the reportes to set
	 */
	public void setReportes(List<Reporte> reportes) {
		this.reportes = reportes;
	}
	/**
	 * @return the reporte
	 */
	public Reporte getReporte() {
		return reporte;
	}
	/**
	 * @param reporte the reporte to set
	 */
	public void setReporte(Reporte reporte) {
		this.reporte = reporte;
	}
	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the serverSMTP
	 */
	public String getServerSMTP() {
		return serverSMTP;
	}
	/**
	 * @param serverSMTP the serverSMTP to set
	 */
	public void setServerSMTP(String serverSMTP) {
		this.serverSMTP = serverSMTP;
	}
	/**
	 * @return the puerto
	 */
	public String getPuerto() {
		return puerto;
	}
	/**
	 * @param puerto the puerto to set
	 */
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}
	/**
	 * @return the tipoSeguridad
	 */
	public String getTipoSeguridad() {
		return tipoSeguridad;
	}
	/**
	 * @param tipoSeguridad the tipoSeguridad to set
	 */
	public void setTipoSeguridad(String tipoSeguridad) {
		this.tipoSeguridad = tipoSeguridad;
	}
	/**
	 * @return the factorMax
	 */
	public String getFactorMax() {
		return factorMax;
	}
	/**
	 * @param factorMax the factorMax to set
	 */
	public void setFactorMax(String factorMax) {
		this.factorMax = factorMax;
	}
	/**
	 * @return the factorMin
	 */
	public String getFactorMin() {
		return factorMin;
	}
	/**
	 * @param factorMin the factorMin to set
	 */
	public void setFactorMin(String factorMin) {
		this.factorMin = factorMin;
	}
	/**
	 * @return the codigoBarrasEntrada
	 */
	public String getCodigoBarrasEntrada() {
		return codigoBarrasEntrada;
	}
	/**
	 * @param codigoBarrasEntrada the codigoBarrasEntrada to set
	 */
	public void setCodigoBarrasEntrada(String codigoBarrasEntrada) {
		this.codigoBarrasEntrada = codigoBarrasEntrada;
	}
	/**
	 * @return the codigoBarrasSalida
	 */
	public String getCodigoBarrasSalida() {
		return codigoBarrasSalida;
	}
	/**
	 * @param codigoBarrasSalida the codigoBarrasSalida to set
	 */
	public void setCodigoBarrasSalida(String codigoBarrasSalida) {
		this.codigoBarrasSalida = codigoBarrasSalida;
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
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the ipServerSOS
	 */
	public String getIpServerSOS() {
		return ipServerSOS;
	}
	/**
	 * @param ipServerSOS the ipServerSOS to set
	 */
	public void setIpServerSOS(String ipServerSOS) {
		this.ipServerSOS = ipServerSOS;
	}
	/**
	 * @return the logoLDC
	 */
	public byte[] getLogoLDC() {
		return logoLDC;
	}
	/**
	 * @param logoLDC the logoLDC to set
	 */
	public void setLogoLDC(byte[] logoLDC) {
		this.logoLDC = logoLDC;
	}
	/**
	 * @return the logoAVON
	 */
	public byte[] getLogoAVON() {
		return logoAVON;
	}
	/**
	 * @param logoAVON the logoAVON to set
	 */
	public void setLogoAVON(byte[] logoAVON) {
		this.logoAVON = logoAVON;
	}
	/**
	 * @return the ldccs
	 */
	public List<com.datacode.avon_ots_admin.model.ParametrosLDC> getLdccs() {
		return ldccs;
	}
	/**
	 * @param ldccs the ldccs to set
	 */
	public void setLdccs(List<com.datacode.avon_ots_admin.model.ParametrosLDC> ldccs) {
		this.ldccs = ldccs;
	}
	/**
	 * @return the ldc
	 */
	public com.datacode.avon_ots_admin.model.ParametrosLDC getLdc() {
		return ldc;
	}
	/**
	 * @param ldc the ldc to set
	 */
	public void setLdc(com.datacode.avon_ots_admin.model.ParametrosLDC ldc) {
		this.ldc = ldc;
	}
	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * @return the idTipoUsuario
	 */
	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}
	/**
	 * @param idTipoUsuario the idTipoUsuario to set
	 */
	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	/**
	 * @return the idPerfil
	 */
	public Integer getIdPerfil() {
		return idPerfil;
	}
	/**
	 * @param idPerfil the idPerfil to set
	 */
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	/**
	 * @return the descPerfil
	 */
	public String getDescPerfil() {
		return descPerfil;
	}
	/**
	 * @param descPerfil the descPerfil to set
	 */
	public void setDescPerfil(String descPerfil) {
		this.descPerfil = descPerfil;
	}
	/**
	 * @return the descTipoUsuario
	 */
	public String getDescTipoUsuario() {
		return descTipoUsuario;
	}
	/**
	 * @param descTipoUsuario the descTipoUsuario to set
	 */
	public void setDescTipoUsuario(String descTipoUsuario) {
		this.descTipoUsuario = descTipoUsuario;
	}
	/**
	 * @return the users
	 */
	public List<Usuario> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(List<Usuario> users) {
		this.users = users;
	}
	
	/**
	 * @return the idDenominacion
	 */
	public Integer getIdDenominacion() {
		return idDenominacion;
	}
	/**
	 * @param idDenominacion the idDenominacion to set
	 */
	public void setIdDenominacion(Integer idDenominacion) {
		this.idDenominacion = idDenominacion;
	}
	/**
	 * @return the descDenominacion
	 */
	public String getDescDenominacion() {
		return descDenominacion;
	}
	/**
	 * @param descDenominacion the descDenominacion to set
	 */
	public void setDescDenominacion(String descDenominacion) {
		this.descDenominacion = descDenominacion;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the perfiles
	 */
	public List<Perfil> getPerfiles() {
		return perfiles;
	}
	/**
	 * @param perfiles the perfiles to set
	 */
	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	/**
	 * @return the perfil
	 */
	public Perfil getPerfil() {
		return perfil;
	}
	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	/**
	 * @return the denominaciones
	 */
	public List<Denominaciones> getDenominaciones() {
		return denominaciones;
	}
	/**
	 * @param denominaciones the denominaciones to set
	 */
	public void setDenominaciones(List<Denominaciones> denominaciones) {
		this.denominaciones = denominaciones;
	}
	/**
	 * @return the denominacion
	 */
	public Denominaciones getDenominacion() {
		return denominacion;
	}
	/**
	 * @param denominacion the denominacion to set
	 */
	public void setDenominacion(Denominaciones denominacion) {
		this.denominacion = denominacion;
	}
	/**
	 * @return the idTipo
	 */
	public Integer getIdTipo() {
		return idTipo;
	}
	/**
	 * @param idTipo the idTipo to set
	 */
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * @return the valueLogoLDC
	 */
	public String getValueLogoLDC() {
		return valueLogoLDC;
	}
	/**
	 * @param valueLogoLDC the valueLogoLDC to set
	 */
	public void setValueLogoLDC(String valueLogoLDC) {
		this.valueLogoLDC = valueLogoLDC;
	}
	/**
	 * @return the valueLogoAVON
	 */
	public String getValueLogoAVON() {
		return valueLogoAVON;
	}
	/**
	 * @param valueLogoAVON the valueLogoAVON to set
	 */
	public void setValueLogoAVON(String valueLogoAVON) {
		this.valueLogoAVON = valueLogoAVON;
	}
	
	/**
	 * @return the logLDC
	 */
	public UploadedFile getLogLDC() {
		return logLDC;
	}
	/**
	 * @param logLDC the logLDC to set
	 */
	public void setLogLDC(UploadedFile logLDC) {
		this.logLDC = logLDC;
	}
	/**
	 * @return the logAVON
	 */
	public UploadedFile getLogAVON() {
		return logAVON;
	}
	/**
	 * @param logAVON the logAVON to set
	 */
	public void setLogAVON(UploadedFile logAVON) {
		this.logAVON = logAVON;
	}

	/**
	 * @return the selected
	 */
	public Boolean getSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the permisos
	 */
	public List<ModuloAccion> getPermisosCheck() {
		return permisosCheck;
	}

	/**
	 * @param permisos the permisos to set
	 */
	public void setPermisosCheck(List<ModuloAccion> permisos) {
		this.permisosCheck = permisos;
	}

	/**
	 * @return the permiso
	 */
	public ModuloAccion getPermiso() {
		return permiso;
	}

	/**
	 * @param permiso the permiso to set
	 */
	public void setPermiso(ModuloAccion permiso) {
		this.permiso = permiso;
	}

	/**
	 * @return the selectedIds
	 */
	public Map<Long, Boolean> getSelectedIds() {
		return selectedIds;
	}

	/**
	 * @param selectedIds the selectedIds to set
	 */
	public void setSelectedIds(Map<Long, Boolean> selectedIds) {
		this.selectedIds = selectedIds;
	}

	/**
	 * @return the pnlPermisos
	 */
	public UIPanel getPnlPermisos() {
		return pnlPermisos;
	}

	/**
	 * @param pnlPermisos the pnlPermisos to set
	 */
	public void setPnlPermisos(UIPanel pnlPermisos) {
		this.pnlPermisos = pnlPermisos;
	}

	/**
	 * @return the selectedf
	 */
	public Boolean getSelectedf() {
		return selectedf;
	}

	/**
	 * @param selectedf the selectedf to set
	 */
	public void setSelectedf(Boolean selectedf) {
		this.selectedf = selectedf;
	}

	public String getRuta7() {
		return ruta7;
	}

	public void setRuta7(String ruta7) {
		this.ruta7 = ruta7;
	}

	public String getNombre7() {
		return nombre7;
	}

	public void setNombre7(String nombre7) {
		this.nombre7 = nombre7;
	}

	public String getZona7() {
		return zona7;
	}

	public void setZona7(String zona7) {
		this.zona7 = zona7;
	}

	public List<SelectItem> getListPaises() {
		return listPaises;
	}

	public void setListPaises(List<SelectItem> listPaises) {
		this.listPaises = listPaises;
	}

	public Boolean getPnl5() {
		return pnl5;
	}

	public void setPnl5(Boolean pnl5) {
		this.pnl5 = pnl5;
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
	 * @return the pnl15
	 */
	public Boolean getPnl15() {
		return pnl15;
	}

	/**
	 * @param pnl15 the pnl15 to set
	 */
	public void setPnl15(Boolean pnl15) {
		this.pnl15 = pnl15;
	}

	/**
	 * @return the pnl16
	 */
	public Boolean getPnl16() {
		return pnl16;
	}

	/**
	 * @param pnl16 the pnl16 to set
	 */
	public void setPnl16(Boolean pnl16) {
		this.pnl16 = pnl16;
	}

	/**
	 * @return the pnl17
	 */
	public Boolean getPnl17() {
		return pnl17;
	}

	/**
	 * @param pnl17 the pnl17 to set
	 */
	public void setPnl17(Boolean pnl17) {
		this.pnl17 = pnl17;
	}

	/**
	 * @return the pnl18
	 */
	public Boolean getPnl18() {
		return pnl18;
	}

	/**
	 * @param pnl18 the pnl18 to set
	 */
	public void setPnl18(Boolean pnl18) {
		this.pnl18 = pnl18;
	}

	/**
	 * @return the pnl19
	 */
	public Boolean getPnl19() {
		return pnl19;
	}

	/**
	 * @param pnl19 the pnl19 to set
	 */
	public void setPnl19(Boolean pnl19) {
		this.pnl19 = pnl19;
	}

	/**
	 * @return the pnl20
	 */
	public Boolean getPnl20() {
		return pnl20;
	}

	/**
	 * @param pnl20 the pnl20 to set
	 */
	public void setPnl20(Boolean pnl20) {
		this.pnl20 = pnl20;
	}

	/**
	 * @return the pnl21
	 */
	public Boolean getPnl21() {
		return pnl21;
	}

	/**
	 * @param pnl21 the pnl21 to set
	 */
	public void setPnl21(Boolean pnl21) {
		this.pnl21 = pnl21;
	}

	/**
	 * @return the pnl22
	 */
	public Boolean getPnl22() {
		return pnl22;
	}

	/**
	 * @param pnl22 the pnl22 to set
	 */
	public void setPnl22(Boolean pnl22) {
		this.pnl22 = pnl22;
	}
	
	/**
	 * @return the pnl23
	 */
	public Boolean getPnl23() {
		return pnl23;
	}

	/**
	 * @param pnl22 the pnl23 to set
	 */
	public void setPnl23(Boolean pnl23) {
		this.pnl23 = pnl23;
	}

	/**
	 * @return the fecha1
	 */
	public Date getFecha1() {
		return fecha1;
	}

	/**
	 * @param fecha1 the fecha1 to set
	 */
	public void setFecha1(Date fecha1) {
		this.fecha1 = fecha1;
	}

	/**
	 * @return the fecha2
	 */
	public Date getFecha2() {
		return fecha2;
	}

	/**
	 * @param fecha2 the fecha2 to set
	 */
	public void setFecha2(Date fecha2) {
		this.fecha2 = fecha2;
	}

	/**
	 * @return the km
	 */
	public Double getKm() {
		return km;
	}

	/**
	 * @param km the km to set
	 */
	public void setKm(Double km) {
		this.km = km;
	}

	/**
	 * @return the noPromOrdenes
	 */
	public int getNoPromOrdenes() {
		return noPromOrdenes;
	}

	/**
	 * @param noPromOrdenes the noPromOrdenes to set
	 */
	public void setNoPromOrdenes(int noPromOrdenes) {
		this.noPromOrdenes = noPromOrdenes;
	}

	/**
	 * @return the idTipoRiesgo
	 */
	public Integer getIdTipoRiesgo() {
		return idTipoRiesgo;
	}

	/**
	 * @param idTipoRiesgo the idTipoRiesgo to set
	 */
	public void setIdTipoRiesgo(Integer idTipoRiesgo) {
		this.idTipoRiesgo = idTipoRiesgo;
	}

	/**
	 * @return the tipoRiesgo
	 */
	public String getTipoRiesgo() {
		return tipoRiesgo;
	}

	/**
	 * @param tipoRiesgo the tipoRiesgo to set
	 */
	public void setTipoRiesgo(String tipoRiesgo) {
		this.tipoRiesgo = tipoRiesgo;
	}

	/**
	 * @return the tiEfectivo
	 */
	public String getTiEfectivo() {
		return tiEfectivo;
	}

	/**
	 * @param tiEfectivo the tiEfectivo to set
	 */
	public void setTiEfectivo(String tiEfectivo) {
		this.tiEfectivo = tiEfectivo;
	}

	/**
	 * @return the tiTotal
	 */
	public String getTiTotal() {
		return tiTotal;
	}

	/**
	 * @param tiTotal the tiTotal to set
	 */
	public void setTiTotal(String tiTotal) {
		this.tiTotal = tiTotal;
	}

	public Boolean getActivoReparto() {
		return activoReparto;
	}

	public void setActivoReparto(Boolean activoReparto) {
		this.activoReparto = activoReparto;
	}

	public Boolean getRepartoSinHH() {
		return repartoSinHH;
	}

	public void setRepartoSinHH(Boolean repartoSinHH) {
		this.repartoSinHH = repartoSinHH;
	}

	public Integer getDiaReparto() {
		return diaReparto;
	}

	public void setDiaReparto(Integer diaReparto) {
		this.diaReparto = diaReparto;
	}

	public Integer getIdCampaniaSinHH() {
		return idCampaniaSinHH;
	}

	public void setIdCampaniaSinHH(Integer idCampaniaSinHH) {
		this.idCampaniaSinHH = idCampaniaSinHH;
	}

	public Boolean getLblVisibleSinHH() {
		return lblVisibleSinHH;
	}

	public void setLblVisibleSinHH(Boolean lblVisibleSinHH) {
		this.lblVisibleSinHH = lblVisibleSinHH;
	}

	public Boolean getChkVisibleSinHH() {
		return chkVisibleSinHH;
	}

	public void setChkVisibleSinHH(Boolean chkVisibleSinHH) {
		this.chkVisibleSinHH = chkVisibleSinHH;
	}

	public Integer getEntregando() {
		return entregando;
	}

	public void setEntregando(Integer entregando) {
		this.entregando = entregando;
	}

	public boolean isEntregandoCheck() {
		return entregandoCheck;
	}

	public void setEntregandoCheck(boolean entregandoCheck) {
		this.entregandoCheck = entregandoCheck;
		setDisabledKilometraje(isEntregandoCheck());
	}

	public String getEntregadoMostrar() {
		return entregadoMostrar;
	}

	public void setEntregadoMostrar(String entregadoMostrar) {
		this.entregadoMostrar = entregadoMostrar;
	}

	public String getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(String kilometraje) {
		this.kilometraje = kilometraje;
	}

	public boolean isDisabledKilometraje() {
		return disabledKilometraje;
	}

	public void setDisabledKilometraje(boolean disabledKilometraje) {
		this.disabledKilometraje = disabledKilometraje;
	}
	
	//Getters y setters catalogo rutas alternas
	public String getCatRutasAlternasRutaOTS() {
		return catRutasAlternasRutaOTS;
	}

	public void setCatRutasAlternasRutaOTS(String catRutasAlternasRutaOTS) {
		this.catRutasAlternasRutaOTS = catRutasAlternasRutaOTS;
	}

	public String getCatRutasAlternasRutaSOS() {
		return catRutasAlternasRutaSOS;
	}

	public void setCatRutasAlternasRutaSOS(String catRutasAlternasRutaSOS) {
		this.catRutasAlternasRutaSOS = catRutasAlternasRutaSOS;
	}

	public int getCatRutasAlternasOrden() {
		return catRutasAlternasOrden;
	}

	public void setCatRutasAlternasOrden(int catRutasAlternasOrden) {
		this.catRutasAlternasOrden = catRutasAlternasOrden;
	}

	public List<RutaAlterna> getListRutasAlternas() {
		return listRutasAlternas;
	}

	public void setListRutasAlternas(List<RutaAlterna> listRutasAlternas) {
		this.listRutasAlternas = listRutasAlternas;
	}

	public int getCatRutasAlternasIdRutaAlterna() {
		return catRutasAlternasIdRutaAlterna;
	}

	public void setCatRutasAlternasIdRutaAlterna(int catRutasAlternasIdRutaAlterna) {
		this.catRutasAlternasIdRutaAlterna = catRutasAlternasIdRutaAlterna;
	}

	public List<SelectItem> getCatRutasAlternasListZonas() {
		return catRutasAlternasListZonas;
	}

	public void setCatRutasAlternasListZonas(
			List<SelectItem> catRutasAlternasListZonas) {
		this.catRutasAlternasListZonas = catRutasAlternasListZonas;
	}

	public int getCatRutasAlternasZona() {
		return catRutasAlternasZona;
	}

	public void setCatRutasAlternasZona(int catRutasAlternasZona) {
		this.catRutasAlternasZona = catRutasAlternasZona;
	}

	public String getCatRutasAlternasFiltroZona() {
		return catRutasAlternasFiltroZona;
	}

	public void setCatRutasAlternasFiltroZona(String catRutasAlternasFiltroZona) {
		this.catRutasAlternasFiltroZona = catRutasAlternasFiltroZona;
	}

	public Boolean getTodasUnidadesReparto() {
		return todasUnidadesReparto;
	}

	public void setTodasUnidadesReparto(Boolean todasUnidadesReparto) {
		this.todasUnidadesReparto = todasUnidadesReparto;
	}
	
}