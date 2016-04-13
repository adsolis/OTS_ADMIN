/**
 * 
 */
package com.datacode.avon_ots_admin.cu001;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.model.SelectItem;

import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.RutasControllerStub;
import com.datacode.avon_ots_ws.RutasControllerStub.Paises;
import com.datacode.avon_ots_ws.RutasControllerStub.Rutas;


/**
 * @author brenda.estrada
 * @since  20-12-2011
 *
 */
public class ControllerPersona {
	//Propiedades de mapeo bean
	public String name = "";
	private Integer idRuta = 0;
	private String cveRuta = "";
	private String descRuta = "";
	private String fechaUpd = "";
	private String userUpd = "";
	//Dependencias
	private Integer idZona = 0;
	private String descZona = "";
	private Integer idPais = 0;
	private String descPais = "";
	private Integer idTipoRuta = 0;
	private String descTipoRuta = "";
	private Integer idLdc = 0;
	private String descLdc = "";
	
	//Instancia de la clase Modelo por cliente
	private List<com.datacode.avon_ots_admin.model.Rutas> rutas = null;
	private com.datacode.avon_ots_admin.model.Rutas ruta;
	//Instancia Objetos  del Stub
	Rutas[] ruts = null;
	Paises[] paises = null;
	String msg = "";
	//Propiedades interfaz
	private UIForm form;
	private UIForm tableForm;
	private UICommand addCommand;
	
	//Instancias del WS
	RutasControllerStub ct = null;
	RutasControllerStub.GetRutasExistentes req = null;
	RutasControllerStub.GetRutasExistentesResponse res = null;
	
	/**
	 *  Constructor
	 *  Se ejecutan metodos necesarios para cargar datos iniciales de la Vista e inicializar componentes.
	 *   
	 */
	public ControllerPersona(){
		//Inicializa componentes
		cargaDatosIniciales();
	}
	
	
	public void cargaDatosIniciales(){
		rutas = new ArrayList<com.datacode.avon_ots_admin.model.Rutas>();
		
		try {
			//Crea el cliente
			ct = new RutasControllerStub();
			
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			
			//Agrega al request la operacion a ejecutar
			req = new RutasControllerStub.GetRutasExistentes();
			//Añade al response la respuesta de la operacion
			res = ct.getRutasExistentes(req);
			//ruts variable de tipo Ruta heredada del WS
			ruts = res.get_return();
			
			//Ciclo que obtiene los datos del WS y los setea en la clase de mapeo del lado del cliente
			for(int i=0;i<ruts.length;i++){
				//Añade los datos a la lista tipo Ruta de forma local mediante el constructor
//				rutas.add(new com.datacode.avon_ots_admin.model.Rutas(ruts[i].getIdRuta(),
//																	  ruts[i].getCveRuta(),
//																	  ruts[i].getDescRuta(),
//																	  ruts[i].getFechaUpd(),
//																	  ruts[i].getUserUpd(),
//																	  ruts[i].getIdZona(),
//																	  ruts[i].getDescZona(),
//																	  ruts[i].getIdPais(),
//																	  ruts[i].getDescPais(),
//																	  ruts[i].getIdTipoRuta(),
//																	  ruts[i].getDescTipoRuta(),
//																	  ruts[i].getIdLdc(),
//																	  ruts[i].getDescLdc()));
				System.out.println("Clave: " + this.cveRuta);
			}
		} catch (RemoteException excepcionDeInvocacion) {
			System.err.println(excepcionDeInvocacion.toString());
		}finally{
			//
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<SelectItem> getZonas() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(0, "Selecciona una opción"));
		list.add(new SelectItem(1, "207"));
		list.add(new SelectItem(2, "366"));
		list.add(new SelectItem(3, "410"));
		list.add(new SelectItem(5, "1977"));
		return list;
	}
	
	/**
	 * Crea una lista de tipo SelectItem
	 * Los datos se obtienen del WS
	 * @return List tipo Paises
	 */
	public List<SelectItem> getPaises() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		//Invocacion del Stub de datos
		//RutasControllerStub ct = null;
		RutasControllerStub.GetPaises reqPais = null;
		RutasControllerStub.GetPaisesResponse resPais = null;
		
		try{
			//Crea el cliente
			ct = new RutasControllerStub();
			
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			
			//Agrega al request la operacion a ejecutar
			reqPais = new RutasControllerStub.GetPaises();
			//Añade al response la respuesta de la operacion
			resPais = ct.getPaises(reqPais);
			
			//ruts variable de tipo Ruta heredada del WS
			paises = resPais.get_return();
			
			//Sea gregan las opciones al SelectItem de Paises
			list.add(new SelectItem(0, "Selecciona una opción"));
			//Ciclo de datos
			for(int o=0;o<paises.length;o++){
				list.add(new SelectItem(paises[o].getIdPais(), paises[o].getDescPais()));
			}
		
		} catch (RemoteException excepcionDeInvocacion) {
			System.err.println(excepcionDeInvocacion.toString());
		}finally{
			//
		}
		return list;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<SelectItem> getTipoRuta() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(0, "Selecciona una opción"));
		list.add(new SelectItem(1, "Foranea"));
		list.add(new SelectItem(2, "Mixta"));
		list.add(new SelectItem(3, "Local"));
		return list;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<SelectItem> getLDC() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(0, "Selecciona una opción"));
		list.add(new SelectItem(1, "CEL"));
		list.add(new SelectItem(2, "DPX"));
		list.add(new SelectItem(3, "GDA"));
		list.add(new SelectItem(4, "MTY1"));
		list.add(new SelectItem(5, "LDC_CD"));
		return list;
	}
	
	/**
	 * Se inicializan los componentes de la pantalla
	 * @return null en los componentes
	 */
	public String nuevaRuta(){
		//Inicializa bean
		this.cveRuta = "";
		this.descRuta = "";
		this.idPais = 0;
		this.idZona = 0;
		this.idTipoRuta = 0;
		this.idLdc = 0;
		
		//Incializa variable
		this.msg = "";
		form.setRendered(true);
		//addCommand.setRendered(true);
		return null;
	}
	
	/**
	 * Manda llamar metodo para realizar la inserción mediante el WS
	 * @return Mensaje de exito o fallo del WS 
	 */
	public String guardarRuta(){
		//Se ejecuta el metodo de inserción guardando el return en var msg
		msg = guardarRutas();
		//Recarga los datos del grid principal
		cargaDatosIniciales();
		//Regresa a la vista el msg del WS
		return msg;
	}
	/**
	 * Recibe los parametros y realiza la insercion mediante el WS
	 * @return String de Exito
	 */
	public String guardarRutas(){
		//Se hace la instancia del metodo
		RutasControllerStub.InsertarRuta reqinsRuta = null;
		RutasControllerStub.InsertarRutaResponse resInserta = null;
		
		try{
			//Crea el cliente
			ct = new RutasControllerStub();
			
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			
			//Agrega al request la operacion a ejecutar
			reqinsRuta = new RutasControllerStub.InsertarRuta();
			//Le setea al request los parametros necesarios
			reqinsRuta.setP_IdUser(5); //pendiente de sacar dee session
			reqinsRuta.setP_Desc(this.descRuta);
			reqinsRuta.setP_Cve(this.cveRuta);
			reqinsRuta.setP_IdPais(this.idPais.toString());
			reqinsRuta.setP_IdZona(this.idZona.toString());
			reqinsRuta.setP_IdTipoRuta(this.idTipoRuta.toString());
			reqinsRuta.setP_Idldc(this.idLdc.toString());
			//Añade al response el request de la operacion
			resInserta = ct.insertarRuta(reqinsRuta);
			//Guarda la respuesta en una variable para ser mostrada en la Vista
			return msg = resInserta.get_return();
		
		} catch (RemoteException excepcionDeInvocacion) {
			System.err.println(excepcionDeInvocacion.toString());
			msg = "Surgió un error al invocar el web service.";
		}finally{
			//
		}
		return msg;
	}
	
	/**
	 * Obtiene los datos del registro seleccionado para actualizar
	 * 
	 * @return Muestra los datos en los componentes en la vista
	 */
	public String modificarRuta(){
		//Setear al Bean los valores que estan en el request
		this.setCveRuta(cveRuta);
		this.setDescRuta(descRuta);
		this.setIdPais(idPais);
		this.setIdZona(idZona);
		this.setIdTipoRuta(idTipoRuta);
		this.setIdLdc(idLdc);
		return null;
	}
	
	/**
	 * Llama el metodo que actualiza el registro seleccionado
	 * @return Mensaje de Exito -Falla o Error al invocar el WS
	 */
	public String actualizarRuta(){
		//Se hace la instancia del metodo
		RutasControllerStub.ActualizarRuta reqUpdRuta = null;
		RutasControllerStub.ActualizarRutaResponse resActualiza = null;
		
		try{
			//Crea el cliente
			ct = new RutasControllerStub();
			
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			
			//Agrega al request la operacion a ejecutar
			reqUpdRuta = new RutasControllerStub.ActualizarRuta();
			//Le setea al request los parametros necesarios
			reqUpdRuta.setP_IdUser(5); //pendiente de sacar dee session
			reqUpdRuta.setP_IdRuta(this.idRuta);
			reqUpdRuta.setP_Desc(this.descRuta);
			reqUpdRuta.setP_Cve(this.cveRuta);
			reqUpdRuta.setP_IdPais(this.idPais.toString());
			reqUpdRuta.setP_IdZona(this.idZona.toString());
			reqUpdRuta.setP_IdTipoRuta(this.idTipoRuta.toString());
			reqUpdRuta.setP_Idldc(this.idLdc.toString());
			//Añade al response el request de la operacion
			resActualiza = ct.actualizarRuta(reqUpdRuta);
			//Recarga el grid para ver cambios
			cargaDatosIniciales();
			//Guarda la respuesta en una variable para ser mostrada en la Vista
			return msg = resActualiza.get_return();
		
		} catch (RemoteException excepcionDeInvocacion) {
			System.err.println(excepcionDeInvocacion.toString());
			msg = "Surgió un error al invocar el web service.";
		}finally{
			//
		}
		return msg;
	}
	
	/**
	 * Obtiene el Id Ruta seleccionada de la Vista mediante el atributo del bean
	 * @return String de Exito Falla o si surgió error al invocar el WS
	 */
	public String eliminarRuta(){
		//Se hace la instancia del metodo
		RutasControllerStub.EliminarRuta reqdelRuta = null;
		RutasControllerStub.EliminarRutaResponse resElimina = null;
		
		try{
			//Crea el cliente
			ct = new RutasControllerStub();
			
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(ct._getServiceClient().getOptions().getTo().getAddress());
			ct._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			
			//Agrega al request la operacion a ejecutar
			reqdelRuta = new RutasControllerStub.EliminarRuta();
			//Le setea al request los parametros que recibe el WS
			reqdelRuta.setP_IdUser(5); //pendiente de sacar dee session
			reqdelRuta.setP_IdRuta(this.idRuta);
			
			//Añade al response el request de la operacion
			resElimina = ct.eliminarRuta(reqdelRuta);

			//Se recarga grid
			cargaDatosIniciales();
			//Guarda la respuesta en una variable para ser mostrada en la Vista
			return msg = resElimina.get_return();
		
		} catch (RemoteException excepcionDeInvocacion) {
			System.err.println(excepcionDeInvocacion.toString());
			msg = "Surgió un error al invocar el web service.";
		}finally{
			//
		}
		return msg;
	}
	
	/**
	 * Inicializa la Vista
	 * @return null en los componentes de la vista
	 */
	public String cancelarRuta(){
		this.msg = "";
		nuevaRuta();
		return null;
	}
	
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
	 * @return the tableForm
	 */
	public UIForm getTableForm() {
		return tableForm;
	}

	/**
	 * @param tableForm the tableForm to set
	 */
	public void setTableForm(UIForm tableForm) {
		this.tableForm = tableForm;
	}

	/**
	 * @return the addCommand
	 */
	public UICommand getAddCommand() {
		return addCommand;
	}

	/**
	 * @param addCommand the addCommand to set
	 */
	public void setAddCommand(UICommand addCommand) {
		this.addCommand = addCommand;
	}

	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	
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
	 * @return the fechaUpd
	 */
	public String getFechaUpd() {
		return fechaUpd;
	}

	/**
	 * @param fechaUpd the fechaUpd to set
	 */
	public void setFechaUpd(String fechaUpd) {
		this.fechaUpd = fechaUpd;
	}

	/**
	 * @return the userUpd
	 */
	public String getUserUpd() {
		return userUpd;
	}

	/**
	 * @param userUpd the userUpd to set
	 */
	public void setUserUpd(String userUpd) {
		this.userUpd = userUpd;
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
	 * @return the descLdc
	 */
	public String getDescLdc() {
		return descLdc;
	}

	/**
	 * @param descLdc the descLdc to set
	 */
	public void setDescLdc(String descLdc) {
		this.descLdc = descLdc;
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
	 * @return the idLdc
	 */
	public Integer getIdLdc() {
		return idLdc;
	}

	/**
	 * @param idLdc the idLdc to set
	 */
	public void setIdLdc(Integer idLdc) {
		this.idLdc = idLdc;
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
	 * @return the ruts
	 */
	public Rutas[] getRuts() {
		return ruts;
	}

	/**
	 * @param ruts the ruts to set
	 */
	public void setRuts(Rutas[] ruts) {
		this.ruts = ruts;
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

	
}
