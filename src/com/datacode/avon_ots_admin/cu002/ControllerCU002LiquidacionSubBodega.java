/**
 * @author jorge.torner
 * @since 20/01/2012
 */
package com.datacode.avon_ots_admin.cu002;

import java.rmi.RemoteException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.component.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Login;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.LiquidacionSubBodegaControllerStub;
import com.datacode.avon_ots_ws.CampaniaControllerStub.Campania;
import com.datacode.avon_ots_ws.PrimeraSegundaLiquidacionControllerStub;
import com.datacode.avon_ots_ws.UtilsStub;
import com.datacode.avon_ots_ws.ZonaControllerStub.Zona;
import com.datacode.avon_ots_ws.LiquidacionSubBodegaControllerStub.ItemLiqSubBodega;
import com.datacode.avon_ots_ws.SubBodegaAlmacenControllerStub.SubBodegaAlmacen;
/**
 * @author jorge.torner
 * @since 20/01/2012
 */
public class ControllerCU002LiquidacionSubBodega {
	//Obtiene el objeto Configuración con los valores cargados al inicio de sesión
	Configuracion config = (Configuracion)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("configuracion");
	
	private UIForm frmFiltros;
	private Integer idCampania;
	private Integer idZona = 0;
	private Integer idSubBodega;
	private List<SelectItem> cmbSubBodegas = new ArrayList<SelectItem>();
	private UIForm frmModificar;
	private UIForm frmGrids;
	private String txtAccount;
	private String txtNombre;
	private String txtCodigo;
	private String txtTipo;
	private String txtDescripcion;
	private String txtCantidad;
	private String txtEntregada;
	private String txtFechaEntregada;
	private String txtRecibida;
	private String txtCausaFaltante;
	private List<ItemLiqSubBodega> grdOrdenes;
	private ItemLiqSubBodega ordenSel;
	private List<ItemLiqSubBodega> grdPremios;
	private ItemLiqSubBodega premioSel;
	private List<ItemLiqSubBodega> grdInventario;
	private UICommand btnAutorizarAlmacen;
	private UICommand btnAutorizarSupervisor;
	private UICommand btnGuardarLiquidacion;
	private UICommand btnConsultar;
	private UICommand btnGuardarItem;
	private UIForm frmAutorizacion;
	private String txtUsuario;
	private String txtContrasenia;
	private String tipoAutorizacion;
	private String lblAutorizacion;
	private String lblMsjAutorizacion;
	private String mensajeError;
	private int idUsuarioAutorizaAlmacen = 0;
	private int idUsuarioAutorizaSupervisor = 0;
	
	
	//********************* METODOS *******************//
	
	/**
	 * Método que baja de sesión el mapa de acciones y establece las
	 *         propias del modulo en la variable mapaAccion
	 * @author jorge.torner
	 * @since 01/02/2012
	 * @return 
	 */
	public HashMap<String, Boolean> getMapaAccion() {
		HashMap<String, Boolean> mapa = new HashMap<String, Boolean>();
		HashMap<Integer, HashMap<String, Boolean>> mapaFull = (HashMap<Integer, HashMap<String, Boolean>>) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("accionesM");
		mapa = mapaFull.get(14); //IdModulo 14 = Liquidación Subbodega

		return mapa;
	}
	
	/**
	 * Crea una lista de tipo SelectItem de Campañas
	 * @author jorge.torner
	 * @since 09/01/2012
	 * @return Lista Campañas
	 */
	public List<SelectItem> getCmbCampanias() {
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		Utils utils = new Utils();

		try {
			// Obtenemos campañas
			Campania[] campanias = utils.getCampanias("CUADMIN002_01_03", config.getIdLDC(), (idZona==null?0:idZona), 0);

			if(campanias != null){
				// Se agregan las opciones al SelectItem de Paises
				list.add(new SelectItem(0, "Selecciona una opción"));
				// Ciclo de datos
				for (int o = 0; o < campanias.length; o++) {
					list.add(new SelectItem(campanias[o].getIdCampania(), campanias[o]
							.getCampania() + "-" + campanias[o].getAnioCampania()));
				}
			}

		} catch (Exception ex) {
			setMensajeError(Utils.ObtenerMensajeBD("General Admin", "M1", true, "No se pudieron cargar las Campañas", ex.getMessage(), config.getIdUsuario())[0]);
		} finally {
			//
		}
		return list;
	}
	/**
	 * Crea una lista de tipo SelectItem de Zonas
	 * @author jorge.torner
	 * @since 09/01/2012
	 * @return Lista tipo Zonas
	 */
	public List<SelectItem> getCmbZonas() {
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		Utils utils = new Utils();

		try {
			// Obtenemos campañas
			Zona[] Zonas = utils.getZonas(config.getIdLDC(), "CUADMIN002_01_03");
			
			if(Zonas != null){
				// Se agregan las opciones al SelectItem de Paises
				list.add(new SelectItem(0, "Selecciona una opción"));
				// Ciclo de datos
				for (int i = 0; i < Zonas.length; i++) {
					list.add(new SelectItem(Zonas[i].getIdZona(), Zonas[i].getZona()));
				}
			}

		} catch (Exception ex) {
			setMensajeError(Utils.ObtenerMensajeBD("General Admin", "M2", true, "No se pudieron cargar las Zonas correctamente", ex.getMessage(), config.getIdUsuario())[0]);
		} finally {
			//
		}
		return list;
	}
	public void llenaSubBodegas(ValueChangeEvent e){
		this.idZona = Integer.parseInt(e.getNewValue().toString());
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		Utils utils = new Utils();

		try {
			// Obtenemos SubBodegas
			SubBodegaAlmacen[] subBodegasArr = utils.getSubBodegaAlmacen(0, idZona, config.getIdPais(), config.getIdLDC());
			
			if(subBodegasArr != null){
				// Se agregan las opciones al SelectItem de Paises
				list.add(new SelectItem(0, "Selecciona una opción"));
				// Ciclo de datos
				for (int i = 0; i < subBodegasArr.length; i++) {
					list.add(new SelectItem(subBodegasArr[i].getIdSubbodegaAlmacen(), subBodegasArr[i].getDescripcion()));
				}
			}
			
//			btnConsultar.getAttributes().put("disabled", true);

		} catch (Exception ex) {
			setMensajeError(Utils.ObtenerMensajeBD("CUADMIN001.02", "M13", true, "Surgió un error al obtener los datos de SubBodega Almacén.", ex.getMessage(), config.getIdUsuario())[0]);
		} finally {
			//
		}
		
		this.setCmbSubBodegas(list);
	}
	
	
//	public List<SelectItem> getCmbSubBodegas() {
//		return 
//	}

	
//	public void selSubBodega(ValueChangeEvent e){
//		this.idSubBodega = Integer.parseInt(e.getNewValue().toString());
//		validarCombos();
//	}
	
	private void validarCombos(){
		if(idSubBodega != null && idSubBodega != 0 && idCampania != Integer.valueOf(0) && idZona != Integer.valueOf(0)
				&& getMapaAccion().get("CONSULTAR") != null)
			btnConsultar.getAttributes().put("disabled", false);
		else
			btnConsultar.getAttributes().put("disabled", true);
		
		frmModificar.setRendered(false);
		frmGrids.setRendered(false);
		frmAutorizacion.setRendered(false);
	}
	
	
	public String consultar(){
		//Conectamos con WebService
		try {
			// Crea el cliente
			LiquidacionSubBodegaControllerStub stub = new LiquidacionSubBodegaControllerStub();
			
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			
			// Se obtiene request
			LiquidacionSubBodegaControllerStub.ObtenerGridLiqSubBodega request = new LiquidacionSubBodegaControllerStub.ObtenerGridLiqSubBodega();
			
			//OBTENEMOS GRID ORDENES
			//Se agregan parámetros
			request.setP_idCampania(idCampania);
			request.setP_idSubBodega(idSubBodega);
			request.setP_idUsuario(config.getIdUsuario());
			request.setP_idZona(idZona);
			request.setP_tipo("ORDENES");
			
			// Se invoca servicio
			LiquidacionSubBodegaControllerStub.ObtenerGridLiqSubBodegaResponse response = stub.obtenerGridLiqSubBodega(request);

			// Se obtiene resultado
			ItemLiqSubBodega[] ordenesArr = response.get_return();
			grdOrdenes = new ArrayList<ItemLiqSubBodega>();
			if(ordenesArr != null && ordenesArr.length > 0){
				for(ItemLiqSubBodega item : ordenesArr){
					grdOrdenes.add(item);
				}
			}
			
			//OBTENEMOS GRID PREMIOS
			//Se agregan parámetros
			request.setP_tipo("PREMIOS");
			
			// Se invoca servicio
			response = stub.obtenerGridLiqSubBodega(request);

			// Se obtiene resultado
			ItemLiqSubBodega[] premiosArr = response.get_return();
			grdPremios = new ArrayList<ItemLiqSubBodega>();
			if(premiosArr != null && premiosArr.length > 0){
				for(ItemLiqSubBodega item : premiosArr){
					grdPremios.add(item);
				}
			}
			
			//OBTENEMOS GRID INVENTARIO
			//Se agregan parámetros
			request.setP_tipo("INVENTARIO");
			
			// Se invoca servicio
			response = stub.obtenerGridLiqSubBodega(request);

			// Se obtiene resultado
			ItemLiqSubBodega[] inventarioArr = response.get_return();
			grdInventario = new ArrayList<ItemLiqSubBodega>();
			if(inventarioArr != null && inventarioArr.length > 0){
				for(ItemLiqSubBodega item : inventarioArr){
					grdInventario.add(item);
				}
			}
		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("CUADMIN002.01.03", "M2", "Surgió un error al obtener los items para los grids", ex.getMessage(), config.getIdUsuario());
		}
		
		if(grdOrdenes.size() != 0 || grdPremios.size() != 0 || grdInventario.size() != 0){
			frmModificar.setRendered(true);
			frmGrids.setRendered(true);
			btnAutorizarAlmacen.getAttributes().put("disabled", false);
			btnAutorizarSupervisor.getAttributes().put("disabled", true);
			btnGuardarLiquidacion.getAttributes().put("disabled", true);
			idUsuarioAutorizaAlmacen = 0;
			idUsuarioAutorizaSupervisor = 0;
		}
		else{
			frmModificar.setRendered(false);
			frmGrids.setRendered(false);
			mensajeError = Utils.ObtenerMensajeBD("CUADMIN002.01.03", "M3", true, "No hay información de órdenes, unitarios y premios con estatus de devuelto a sub bodega para los criterios seleccionados", "", config.getIdUsuario())[0];
		}
		
		limpiarCamposCaptura();
		
		return null;
	}
	
	public String modificarItem(){
		mensajeError = "";
		if(idUsuarioAutorizaAlmacen == 0){
			ItemLiqSubBodega itemSel = null;
			if(ordenSel != null)
				itemSel = ordenSel;
			else if(premioSel != null)
				itemSel = premioSel;
			
			if(itemSel != null){
				txtAccount = itemSel.getRegistroRep();
				txtNombre = itemSel.getNombreRep();
				txtCodigo = itemSel.getCodigoBarras();
				txtDescripcion = itemSel.getDescripcionItem();
				txtCantidad = String.valueOf(itemSel.getCantidad());
				txtTipo = itemSel.getTipoItem();
				txtEntregada = String.valueOf(itemSel.getCantEntregada());
				txtRecibida = String.valueOf(itemSel.getCantRecibida());
				
				Format formatter = new SimpleDateFormat(Utils.formatoFechaCorta);
				if(itemSel.getFechaEntrega() != null)
					txtFechaEntregada = formatter.format(itemSel.getFechaEntrega());
				else
					txtFechaEntregada = Utils.ObtenerFechaActual(Utils.formatoFechaCorta);
				if(itemSel.getCausaFaltante() != null)
					txtCausaFaltante = itemSel.getCausaFaltante().trim();
				else
					txtCausaFaltante = "";
					
				btnGuardarItem.getAttributes().put("disabled", false);
				
			}
		}
		else{
			mensajeError = "Ya no puede modificar los items porque ya se dió la Autorización de Almacén";
		}
	return null;
	}
	
	public String guardarItem(){
		if(idUsuarioAutorizaAlmacen == 0){
			ItemLiqSubBodega itemSel = null;
			if(ordenSel != null)
				itemSel = ordenSel;
			else if(premioSel != null)
				itemSel = premioSel;
			
			if(itemSel != null){
				itemSel.setRegistroRep(txtAccount);
				itemSel.setNombreRep(txtNombre);
				itemSel.setCodigoBarras(txtCodigo);
				itemSel.setDescripcionItem(txtDescripcion);
				itemSel.setCantidad(Integer.parseInt(txtCantidad));
				itemSel.setTipoItem(txtTipo);
				itemSel.setCantEntregada(Integer.parseInt(txtEntregada));
				itemSel.setCantRecibida(Integer.parseInt(txtRecibida));
	
				Format formatter = new SimpleDateFormat(Utils.formatoFechaCorta);
				Date fecha=null;
				try {
					fecha = (Date)formatter.parseObject(txtFechaEntregada);
				} catch (ParseException e) {
					mensajeError = "Fecha en formato incorrecto";
				}
				itemSel.setFechaEntrega(fecha);
				itemSel.setCausaFaltante(txtCausaFaltante);
				
				//Buscamos código de barras en inventario
				for(ItemLiqSubBodega itemInv : grdInventario){
					if(itemInv.getCodigoBarras().equals(itemSel.getCodigoBarras())){
						itemInv.setCantEntregada(itemSel.getCantEntregada());
						itemInv.setCantRecibida(itemSel.getCantRecibida());
						itemInv.setCausaFaltante(itemSel.getCausaFaltante());
						break;
					}
				}
			}
		
			limpiarCamposCaptura();
			ordenSel = premioSel = null;
		}
		else{
			mensajeError = "Ya no puede modificar los items porque ya se dió la Autorización de Almacén";
		}
		
	return null;
	}
	
	private void limpiarCamposCaptura(){
		txtAccount = "";
		txtNombre = "";
		txtCodigo = "";
		txtDescripcion = "";
		txtCantidad = "";
		txtTipo = "";
		
		txtEntregada = "";
		txtFechaEntregada = "";
		txtRecibida = "";
		txtCausaFaltante = "";
		btnGuardarItem.getAttributes().put("disabled", true);
	}
	
	public String autorizarAlmacen(){
		lblAutorizacion = "Autorización de Almacén";
		frmAutorizacion.setRendered(true);
		frmModificar.setRendered(false);
		frmGrids.setRendered(false);
		frmFiltros.setRendered(false);
		tipoAutorizacion = "ALMACEN";
		return null;
	}
	public String autorizarSupervisor(){
		lblAutorizacion = "Autorización de Supervisor";
		frmAutorizacion.setRendered(true);
		frmModificar.setRendered(false);
		frmGrids.setRendered(false);
		frmFiltros.setRendered(false);
		tipoAutorizacion = "SUPERVISOR";
		return null;
	}
	
	private void limpiaCamposAutorizacion(){
		txtUsuario = "";
		txtContrasenia = "";
		lblMsjAutorizacion = "";
	}

	public String cancelarAutorizacion(){
		frmAutorizacion.setRendered(false);
		frmModificar.setRendered(true);
		frmGrids.setRendered(true);
		frmFiltros.setRendered(true);
		limpiaCamposAutorizacion();
		return null;
	}
	
	public String autorizarUsuario(){
		//Validamos la autorización de acuerdo al tipo
		//CUADMIN002_01_03_ALMACEN
		//CUADMIN002_01_03_SUPERVISOR
		String tipoValidacion = tipoAutorizacion.equals("ALMACEN") ? "CUADMIN002_01_03_ALMACEN" : "CUADMIN002_01_03_SUPERVISOR";
		UtilsStub.Usuario usuario = Login.validarUsuario(tipoValidacion, txtUsuario, txtContrasenia);
		
		if(usuario != null){
			if(tipoAutorizacion.equals("ALMACEN")){
				idUsuarioAutorizaAlmacen = usuario.getIdUsuario();
				btnAutorizarAlmacen.getAttributes().put("disabled", true);
				btnAutorizarSupervisor.getAttributes().put("disabled", false);
			}
			else if(tipoAutorizacion.equals("SUPERVISOR")){
				idUsuarioAutorizaSupervisor = usuario.getIdUsuario();
				btnAutorizarSupervisor.getAttributes().put("disabled", true);
				if(getMapaAccion().get("GUARDAR") != null)
					btnGuardarLiquidacion.getAttributes().put("disabled", false);
				else
					mensajeError = "No tiene los permisos necesarios para Guardar la liquidación";
			}

			limpiaCamposAutorizacion();
			limpiarCamposCaptura();

			frmAutorizacion.setRendered(false);
			frmModificar.setRendered(true);
			frmGrids.setRendered(true);
			frmFiltros.setRendered(true);
		}
		else
			lblMsjAutorizacion = "El usuario o contraseña son incorrectos";
		return null;
	}

	public String guardarLiquidacion(){
		String respuesta = "";
		try {
			LiquidacionSubBodegaControllerStub stub = new LiquidacionSubBodegaControllerStub();
			
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			
			LiquidacionSubBodegaControllerStub.RegistrarLiquidacionSubBodega request = new LiquidacionSubBodegaControllerStub.RegistrarLiquidacionSubBodega();

			// parámetros
			request.setP_idZona(idZona);
			request.setP_idCampania(idCampania);
			request.setP_idSubBodega(idSubBodega);
			request.setP_idUsuarioSupervisor(idUsuarioAutorizaSupervisor);
			request.setP_idUsuarioAlmacen(idUsuarioAutorizaAlmacen);
			request.setP_idUsuario(config.getIdUsuario());
			request.setP_arrOrdenes(grdOrdenes.toArray(new ItemLiqSubBodega[0]));
			request.setP_arrPremios(grdPremios.toArray(new ItemLiqSubBodega[0]));

			// invocamos
			LiquidacionSubBodegaControllerStub.RegistrarLiquidacionSubBodegaResponse response = stub
					.registrarLiquidacionSubBodega(request);

			// obtenemos respuesta
			respuesta = response.get_return();
			if(!respuesta.equals("")){
				mensajeError = respuesta;
			}
			else{
				btnGuardarLiquidacion.getAttributes().put("disabled", true);
				mensajeError = "Liquidación Guardada";
				Utils utils = new Utils();
				mensajeError += utils.enviarPrimeraSegundaLiquidacion(2);
			}

		} catch (RemoteException ex) {
			mensajeError = Utils.ObtenerMensajeBD("General Admin", "M4", true, "Error al validar el usuario", ex.getMessage(), 0)[0];
		}
		
		return null;
	}
	

	//GETTERS Y SETTERS
	public UIForm getFrmFiltros() {
		return frmFiltros;
	}
	public void setFrmFiltros(UIForm frmFiltros) {
		this.frmFiltros = frmFiltros;
	}
	public Integer getIdCampania() {
		return idCampania;
	}
	public void setIdCampania(Integer idCampania) {
		this.idCampania = idCampania;
		validarCombos();
	}
	public Integer getIdZona() {
		return idZona;
	}
	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
		validarCombos();
	}
	public Integer getIdSubBodega() {
		return idSubBodega;
	}
	public void setIdSubBodega(Integer idSubBodega) {
		this.idSubBodega = idSubBodega;
		validarCombos();
	}
	public UIForm getFrmModificar() {
		return frmModificar;
	}
	public void setFrmModificar(UIForm frmModificar) {
		this.frmModificar = frmModificar;
	}
	public String getTxtAccount() {
		return txtAccount;
	}
	public void setTxtAccount(String txtAccount) {
		this.txtAccount = txtAccount;
	}
	public String getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(String txtNombre) {
		this.txtNombre = txtNombre;
	}
	public String getTxtCodigo() {
		return txtCodigo;
	}
	public void setTxtCodigo(String txtCodigo) {
		this.txtCodigo = txtCodigo;
	}
	public String getTxtTipo() {
		return txtTipo;
	}
	public void setTxtTipo(String txtTipo) {
		this.txtTipo = txtTipo;
	}
	public String getTxtDescripcion() {
		return txtDescripcion;
	}
	public void setTxtDescripcion(String txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}
	public String getTxtCantidad() {
		return txtCantidad;
	}
	public void setTxtCantidad(String txtCantidad) {
		this.txtCantidad = txtCantidad;
	}
	public String getTxtEntregada() {
		return txtEntregada;
	}
	public void setTxtEntregada(String txtEntregada) {
		this.txtEntregada = txtEntregada;
	}
	public String getTxtFechaEntregada() {
		return txtFechaEntregada;
	}
	public void setTxtFechaEntregada(String txtFechaEntregada) {
		this.txtFechaEntregada = txtFechaEntregada;
	}
	public String getTxtRecibida() {
		return txtRecibida;
	}
	public void setTxtRecibida(String txtRecibida) {
		this.txtRecibida = txtRecibida;
	}
	public String getTxtCausaFaltante() {
		return txtCausaFaltante;
	}
	public void setTxtCausaFaltante(String txtCausaFaltante) {
		this.txtCausaFaltante = txtCausaFaltante;
	}
	public List<ItemLiqSubBodega> getGrdOrdenes() {
		return grdOrdenes;
	}
	public void setGrdOrdenes(List<ItemLiqSubBodega> grdOrdenes) {
		this.grdOrdenes = grdOrdenes;
	}
	public ItemLiqSubBodega getOrdenSel() {
		return ordenSel;
	}
	public void setOrdenSel(ItemLiqSubBodega ordenSel) {
		this.ordenSel = ordenSel;
	}
	public List<ItemLiqSubBodega> getGrdPremios() {
		return grdPremios;
	}
	public void setGrdPremios(List<ItemLiqSubBodega> grdPremios) {
		this.grdPremios = grdPremios;
	}
	public ItemLiqSubBodega getPremioSel() {
		return premioSel;
	}
	public void setPremioSel(ItemLiqSubBodega premioSel) {
		this.premioSel = premioSel;
	}
	public List<ItemLiqSubBodega> getGrdInventario() {
		return grdInventario;
	}
	public void setGrdInventario(List<ItemLiqSubBodega> grdInventario) {
		this.grdInventario = grdInventario;
	}
	public UICommand getBtnAutorizarAlmacen() {
		return btnAutorizarAlmacen;
	}
	public void setBtnAutorizarAlmacen(UICommand btnAutorizarAlmacen) {
		this.btnAutorizarAlmacen = btnAutorizarAlmacen;
	}
	public UICommand getBtnAutorizarSupervisor() {
		return btnAutorizarSupervisor;
	}
	public void setBtnAutorizarSupervisor(UICommand btnAutorizarSupervisor) {
		this.btnAutorizarSupervisor = btnAutorizarSupervisor;
	}
	public UICommand getBtnGuardarLiquidacion() {
		return btnGuardarLiquidacion;
	}
	public void setBtnGuardarLiquidacion(UICommand btnGuardarLiquidacion) {
		this.btnGuardarLiquidacion = btnGuardarLiquidacion;
	}
	public UIForm getFrmAutorizacion() {
		return frmAutorizacion;
	}
	public void setFrmAutorizacion(UIForm frmAutorizacion) {
		this.frmAutorizacion = frmAutorizacion;
	}
	public String getTxtUsuario() {
		return txtUsuario;
	}
	public void setTxtUsuario(String txtUsuario) {
		this.txtUsuario = txtUsuario;
	}
	public String getTxtContrasenia() {
		return txtContrasenia;
	}
	public void setTxtContrasenia(String txtContrasenia) {
		this.txtContrasenia = txtContrasenia;
	}
	public String getTipoAutorizacion() {
		return tipoAutorizacion;
	}
	public void setTipoAutorizacion(String tipoAutorizacion) {
		this.tipoAutorizacion = tipoAutorizacion;
	}
	public String getLblAutorizacion() {
		return lblAutorizacion;
	}
	public void setLblAutorizacion(String lblAutorizacion) {
		this.lblAutorizacion = lblAutorizacion;
	}
	public String getLblMsjAutorizacion() {
		return lblMsjAutorizacion;
	}
	public void setLblMsjAutorizacion(String lblMsjAutorizacion) {
		this.lblMsjAutorizacion = lblMsjAutorizacion;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	public UICommand getBtnConsultar() {
		return btnConsultar;
	}
	public void setBtnConsultar(UICommand btnConsultar) {
		this.btnConsultar = btnConsultar;
	}
	public int getIdUsuarioAutorizaAlmacen() {
		return idUsuarioAutorizaAlmacen;
	}
	public void setIdUsuarioAutorizaAlmacen(int idUsuarioAutorizaAlmacen) {
		this.idUsuarioAutorizaAlmacen = idUsuarioAutorizaAlmacen;
	}
	public int getIdUsuarioAutorizaSupervisor() {
		return idUsuarioAutorizaSupervisor;
	}
	public void setIdUsuarioAutorizaSupervisor(int idUsuarioAutorizaSupervisor) {
		this.idUsuarioAutorizaSupervisor = idUsuarioAutorizaSupervisor;
	}
	public UIForm getFrmGrids() {
		return frmGrids;
	}
	public void setFrmGrids(UIForm frmGrids) {
		this.frmGrids = frmGrids;
	}
	public List<SelectItem> getCmbSubBodegas() {
		if(cmbSubBodegas == null)
			cmbSubBodegas = new ArrayList<SelectItem>();
		return cmbSubBodegas;
	}
	public void setCmbSubBodegas(List<SelectItem> cmbSubBodegas) {
		this.cmbSubBodegas = cmbSubBodegas;
	}
	public UICommand getBtnGuardarItem() {
		return btnGuardarItem;
	}
	public void setBtnGuardarItem(UICommand btnGuardarItem) {
		this.btnGuardarItem = btnGuardarItem;
	}

}
