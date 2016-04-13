package com.datacode.avon_ots_admin.cu002;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.LiquidacionVentanillaMasivaStub;
import com.datacode.avon_ots_ws.LiquidacionVentanillaMasivaStub.Bancos;
import com.datacode.avon_ots_ws.LiquidacionVentanillaMasivaStub.LiquidarOrdenesVentanillaMasiva;
import com.datacode.avon_ots_ws.LiquidacionVentanillaMasivaStub.PersonaRecibe;
import com.datacode.avon_ots_ws.LiquidacionVentanillaMasivaStub.TipoPago;

public class ControllerLiquidacionVentanillaMasiva {

	private DecimalFormat _decimalFormat = null;
	private Configuracion _configuracion = null;
	
	private Bancos[] _bancos = null;
	private TipoPago[] _tipoPagos = null;
	private PersonaRecibe[] _quienRecibe = null;
	
	private List<SelectItem> _listaBancos = null;
	private List<SelectItem> _listaTipoPagos = null;
	private List<SelectItem> _listaQuienRecibe = null;
	
	private List<LiquidarOrdenesVentanillaMasiva> _listaRegistrosRepresentantes = null;
	private List<LiquidarOrdenesVentanillaMasiva> _listaLiquidar = null;
	
	private LiquidarOrdenesVentanillaMasiva _dtDetalle = null;
	private LiquidarOrdenesVentanillaMasiva _dtDetalleLiquidar = null;
	
	private String _lblCampaña = "";
	private String _lblOrden = "";
	private String _lblRegistro = "";
	private String _lblNombre = "";
	private String _lblMontoCobrar = "";
	private String _lblRemanente = "";
	private String _txtFolioCobranza = "";
	private String _txtMonto = "";
	private String _txtregistro = "";
	private String _txtOtros = "";
	private String _mensajeError1 = "";
	private String _mensajeError2 = "";
	private String _mensajeError3 = "";
	private String _txtFechaPago = "";
	private String _txtComentarios = "";
	private String _campania = "";
	private String _txtregistroBusqueda = "";
	
	private Integer _idBanco = -1;
	private Integer _idTipoPago = -1;
	private Integer _idQuienRecibe = -1;
	private Integer _idLiquidacion = 0;
	
	private double _montoTotal1 = 0;
	private double _montoTotal2 = 0;
	
	private Boolean _dtgLiquidar = false;
	private Boolean _dtgRegistrosRepresentante = false;
	private Boolean _pnlDerecho = false;
	private Boolean _pnlDerechoEliminar = false;
	private Boolean _pnlDerechoCancelar = false;
	private Boolean _pnlDerechoComentarios = false;
	private Boolean _pnlDerechoLiquidar = false;
	public Boolean get_pnlDerechoCancelar() {
		return _pnlDerechoCancelar;
	}

	public void set_pnlDerechoCancelar(Boolean _pnlDerechoCancelar) {
		this._pnlDerechoCancelar = _pnlDerechoCancelar;
	}

	public Boolean get_pnlDerechoComentarios() {
		return _pnlDerechoComentarios;
	}

	public void set_pnlDerechoComentarios(Boolean _pnlDerechoComentarios) {
		this._pnlDerechoComentarios = _pnlDerechoComentarios;
	}

	public Boolean get_pnlDerechoLiquidar() {
		return _pnlDerechoLiquidar;
	}

	public void set_pnlDerechoLiquidar(Boolean _pnlDerechoLiquidar) {
		this._pnlDerechoLiquidar = _pnlDerechoLiquidar;
	}

	public Boolean get_pnlDerechoQuienRecibe() {
		return _pnlDerechoQuienRecibe;
	}

	public void set_pnlDerechoQuienRecibe(Boolean _pnlDerechoQuienRecibe) {
		this._pnlDerechoQuienRecibe = _pnlDerechoQuienRecibe;
	}

	private Boolean _pnlDerechoQuienRecibe = false;
	private Boolean _pnlIzquierdo = false;
	
	private Boolean _activaBanco = true;
	private Boolean _activaQuienRecibe = true;
	private Boolean _activaTipoPago = true;
	
	/*Fin Variables*/
	
	public ControllerLiquidacionVentanillaMasiva(){
		_configuracion =  (Configuracion) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("configuracion");
		DecimalFormatSymbols vSymbol = new DecimalFormatSymbols();
		vSymbol.setDecimalSeparator('.');
		_decimalFormat = new DecimalFormat("#0.00", vSymbol);	
		_dtgRegistrosRepresentante = true;
		_dtgLiquidar = true;
	}
	
	/**
	 * Obtiene una lista con los nombres de los bancos
	 * @author Andres.Alvarez
	 * @date 23/05/2014
	 * @return Regresa una lista
	 */
	public List<SelectItem> getObtenerBancos(){
		try {
			if(_activaBanco){
				LiquidacionVentanillaMasivaStub stub = new LiquidacionVentanillaMasivaStub();
				String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
						.getOptions().getTo().getAddress());
				stub._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
				LiquidacionVentanillaMasivaStub.ObtenerBancos request = new LiquidacionVentanillaMasivaStub.ObtenerBancos();			
				LiquidacionVentanillaMasivaStub.ObtenerBancosResponse response = stub.obtenerBancos(request);
				_bancos = response.get_return();
				_listaBancos = new ArrayList<SelectItem>();
				_listaBancos.add(new SelectItem(0, "Selecciona una opción"));
				for(int i=0;i<_bancos.length;i++){
					_listaBancos.add(new SelectItem(_bancos[i].getIdBanco(), _bancos[i].getNombre()));
				}
				_activaBanco = false;
			}
		
		} catch (RemoteException ex) {
			// Utils.GuardarLogMensajeBD("General Admin", "M6",
			// "No se pudieron cargar representantes en el grid",
			// ex.getMessage(), configuracion.getIdUsuario());
			System.out.println(ex.getMessage());
		} 
		return _listaBancos;
	}
	
	/**
	 * Obtiene una lista con los nombres de los tipos de pago
	 * @author Andres.Alvarez
	 * @date 23/05/2014
	 * @return Regresa una lista
	 */
	public List<SelectItem> getObtenerTipoPagos(){
		try {
			if(_activaTipoPago){
				LiquidacionVentanillaMasivaStub stub = new LiquidacionVentanillaMasivaStub();
				String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
						.getOptions().getTo().getAddress());
				stub._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
				LiquidacionVentanillaMasivaStub.ObtenerTipoPagos request = new LiquidacionVentanillaMasivaStub.ObtenerTipoPagos();			
				LiquidacionVentanillaMasivaStub.ObtenerTipoPagosResponse response = stub.obtenerTipoPagos(request);
				_tipoPagos = response.get_return();
				_listaTipoPagos = new ArrayList<SelectItem>();
				_listaTipoPagos.add(new SelectItem(0, "Selecciona una opción"));
				for(int i=0;i<_tipoPagos.length;i++){
					_listaTipoPagos.add(new SelectItem(_tipoPagos[i].getIdTipoPuesto(), _tipoPagos[i].getDescripcion()));
				}
				_activaTipoPago = false;
			}
		
		} catch (RemoteException ex) {
			// Utils.GuardarLogMensajeBD("General Admin", "M6",
			// "No se pudieron cargar representantes en el grid",
			// ex.getMessage(), configuracion.getIdUsuario());
			System.out.println(ex.getMessage());
		} 
		return _listaTipoPagos;
	}

	/**
	 * Obtiene una lista con los nombres quien recibe el pedido
	 * @author Andres.Alvarez
	 * @date 23/05/2014
	 * @return Regresa una lista
	 */
	public List<SelectItem> getObtenerQuienRecibe(){
		try {
			if(_activaQuienRecibe){
				LiquidacionVentanillaMasivaStub stub = new LiquidacionVentanillaMasivaStub();
				String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
						.getOptions().getTo().getAddress());
				stub._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
				LiquidacionVentanillaMasivaStub.ObtenerQuienRecibe request = new LiquidacionVentanillaMasivaStub.ObtenerQuienRecibe();			
				LiquidacionVentanillaMasivaStub.ObtenerQuienRecibeResponse response = stub.obtenerQuienRecibe(request);
				_quienRecibe = response.get_return();
				_listaQuienRecibe = new ArrayList<SelectItem>();
				_listaQuienRecibe.add(new SelectItem(0, "Selecciona una opción"));
				for(int i=0;i<_quienRecibe.length;i++){
					_listaQuienRecibe.add(new SelectItem(_quienRecibe[i].getIdPersonaRecibe(), _quienRecibe[i].getDescripcion()));
				}
				_activaQuienRecibe = false;
			}
		
		} catch (RemoteException ex) {
			// Utils.GuardarLogMensajeBD("General Admin", "M6",
			// "No se pudieron cargar representantes en el grid",
			// ex.getMessage(), configuracion.getIdUsuario());
			System.out.println(ex.getMessage());
		} 
		return _listaQuienRecibe;
	}

	/**
	 * Agrega el registro a la tabla que envia la informacion a liquidar
	 * @author Andres.Alvarez
	 * @date 23/05/2014
	 */
	public String AgregarRegistro(){
		try{
			
			String vBanco = "";
			if(_idBanco != 0){
				for (SelectItem vLista : _listaBancos) {
					if(vLista.getValue() == _idBanco){
						vBanco = vLista.getLabel();
						break;
					}
				}
			}			
			LiquidarOrdenesVentanillaMasiva vLista = new LiquidarOrdenesVentanillaMasiva();
			if(Double.parseDouble(_lblMontoCobrar.replace("$", "")) <= 0){
				vLista.setIdTipoPago("0");
				vLista.setIdBanco("0");
				vLista.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				vLista.setTipoPago("");
				vLista.setDescripcion("Libre de Cobro");
				vLista.setFolio("");
				vLista.setMontoCobrar("0.0");
					
			}else{
				vLista.setIdTipoPago(String.valueOf(_idTipoPago));
				vLista.setIdBanco(String.valueOf(_idBanco));
				vLista.setFecha(_txtFechaPago);
				vLista.setTipoPago(_listaTipoPagos.get(_idTipoPago).getLabel());
				vLista.setDescripcion(vBanco);
				vLista.setFolio(_txtFolioCobranza);
				vLista.setMontoCobrar(_txtMonto);
				
			}
			vLista.setId(_idLiquidacion++);
			vLista.setIdOrden(_dtDetalle.getIdOrden());
			vLista.setIdOrdenEntVentanilla(_dtDetalle.getIdOrdenEntVentanilla());
			_listaLiquidar.add(vLista);
			_mensajeError2 ="";
			_montoTotal2 =  Double.parseDouble(_decimalFormat.format(_montoTotal2 + Double.parseDouble(_txtMonto)));
			_lblRemanente = "$" + _decimalFormat.format(Double.parseDouble(_lblMontoCobrar.replace("$", "")) - _montoTotal2);
			_txtMonto = _decimalFormat.format(Double.parseDouble(_lblMontoCobrar.replace("$", "")) - _montoTotal2).toString();
			_txtFolioCobranza = "";
			_txtFechaPago = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			_idBanco=0;
			_idTipoPago=0;
		}catch(Exception ex){
			
		}
		return "OK";
	}

	/**
	 * Busca los representantes que se les entrego mercancia
	 * @author Andres.Alvarez
	 * @date 23/05/2014
	 */
	public void BuscarRepresentante(){
		LiquidarOrdenesVentanillaMasiva[] vArregloRepresentante = null;		
		try {
			LiquidacionVentanillaMasivaStub stub = new LiquidacionVentanillaMasivaStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			LiquidacionVentanillaMasivaStub.ObtenerOrdenes request = new LiquidacionVentanillaMasivaStub.ObtenerOrdenes();
			LiquidacionVentanillaMasivaStub.ObtenerOrdenesResponse response = stub.obtenerOrdenes(request);
			vArregloRepresentante = response.get_return();
			if (vArregloRepresentante != null) {
				_listaRegistrosRepresentantes = new ArrayList<LiquidarOrdenesVentanillaMasiva>();
				_montoTotal1 = 0;
				for (int i = 0; i < vArregloRepresentante.length; i++) {
					_listaRegistrosRepresentantes.add(vArregloRepresentante[i]);
					_montoTotal1 = Double.parseDouble(_decimalFormat.format(_montoTotal1 + Double.parseDouble(vArregloRepresentante[i].getMontoCobrar())));
				}
				_dtgRegistrosRepresentante = true;
				_mensajeError1 = " ";
			}else{
				_mensajeError1 = "No existen órdenes para liquidar.";
			}
		}catch(Exception ex){
		
		}
	}
	
	/**
	 * Cancela la liquidacion previamente cargada
	 * @author Andres.Alvarez
	 * @date 23/05/2014
	 */
	public String CancelarLiquidacion(){
		_activaBanco=true;
		_activaQuienRecibe=true;
		_activaTipoPago=true;
		_montoTotal2 = 0;
		_dtDetalleLiquidar = null;
		_listaLiquidar = null;
		_lblCampaña = "";
		_lblOrden = "";
		_lblRegistro = "";
		_lblNombre = "";
		_lblMontoCobrar = "";
		_lblRemanente = "";
		_txtComentarios = "";
		_txtFechaPago = "";
		_txtMonto = "";
		_txtFolioCobranza = "";
		_txtOtros = "";
		_pnlIzquierdo = false;
		_pnlDerecho = false;
		_mensajeError2 = "";
		return "";
		
	}
	
	/**
	 * Muestra los valores del representante a liquidar
	 */
	public String DetalleRepresentante(){
		_listaLiquidar = new ArrayList<LiquidacionVentanillaMasivaStub.LiquidarOrdenesVentanillaMasiva>();
		_lblCampaña = _dtDetalle.getCampania();
		_lblOrden = _dtDetalle.getClaveOrden();
		_lblRegistro = _dtDetalle.getRegistro();
		_lblNombre = _dtDetalle.getNombre();
		_lblMontoCobrar = "$" + _decimalFormat.format(Double.parseDouble(_dtDetalle.getMontoCobrar()));
		_lblRemanente = _lblMontoCobrar;
		_dtgRegistrosRepresentante = true;
		_txtregistroBusqueda = "";
		_pnlIzquierdo = true;//Deshabilita controles
		_mensajeError3 = "";
		_txtMonto = _decimalFormat.format(Double.parseDouble(_dtDetalle.getMontoCobrar())).toString();
		
		if(Double.parseDouble(_dtDetalle.getMontoCobrar()) <= 0){
			_pnlDerecho = false;
			_pnlDerechoCancelar = true;
			_pnlDerechoComentarios = true;
			_pnlDerechoEliminar = false;
			_pnlDerechoLiquidar = true;
			_pnlDerechoQuienRecibe = true;
			AgregarRegistro();
		}else{
			_txtFechaPago = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			_pnlDerecho = true;//Habilita controles
			_pnlDerechoCancelar = true;
			_pnlDerechoComentarios = true;
			_pnlDerechoEliminar = true;
			_pnlDerechoLiquidar = true;
			_pnlDerechoQuienRecibe = true;
		}
		return "OK";
	}
	
	/**
	 * Elimina el registro seleccionado de la tabla que envia la informacion a liquidar
	 * @author Andres.Alvarez
	 * @date 23/05/2014
	 */
	public String EliminarRegistroTablaLiquidar(){
		_mensajeError2 = "";
		//Se realiza un corrimiento a la tabla para eliminar el registro seleccionado
		for(int i=0; i < _listaLiquidar.size();i++){
			//Valida la posicion del registro seleccionado para eliminarlo
			if (_dtDetalleLiquidar.getId() == _listaLiquidar.get(i).getId()){	
				_montoTotal2 = Double.parseDouble(_decimalFormat.format(_montoTotal2 - Double.parseDouble(_listaLiquidar.get(i).getMontoCobrar())));
				_lblRemanente = "$" + _decimalFormat.format(Double.parseDouble(_lblRemanente.replace("$", "")) + Double.parseDouble(_listaLiquidar.get(i).getMontoCobrar()));
				_txtMonto = _lblRemanente.replace("$", "");
				_listaLiquidar.remove(i);
				break;
			}
		}
		return "";
	}

	/**
	 * Limpia la lista de representantes a liquidar y habilita la seleccion de campaña
	 * @author Andres.Alvarez
	 * @date 23/05/2014
	 */
	public void LimpiarBusqueda(){
		_montoTotal1 = 0;
		_dtDetalle = null;
		_listaRegistrosRepresentantes = null;
		_txtregistroBusqueda = "";
		_mensajeError1="";
	}
	
	/**
	 * Realiza la liquidacion del representante seleccionado previamente
	 * @author Andres.Alvarez
	 * @date 23/05/2014
	 */
	public String LiquidarRepresentante(){
		String vRes = "";
		if(_listaLiquidar.size() > 0){
			try{
				LiquidacionVentanillaMasivaStub stub = new LiquidacionVentanillaMasivaStub();
				String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
						.getOptions().getTo().getAddress());
				stub._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
				LiquidacionVentanillaMasivaStub.ObtenerPagos request = new LiquidacionVentanillaMasivaStub.ObtenerPagos();
				request.setPPagos(_listaLiquidar.toArray(new LiquidarOrdenesVentanillaMasiva[_listaLiquidar.size()]));
				request.setPUsuario(String.valueOf(_configuracion.getIdUsuario()));
				request.setPComentarios(_txtComentarios);
				request.setPIdPersonaRecibe(String.valueOf(_idQuienRecibe));
				LiquidacionVentanillaMasivaStub.ObtenerPagosResponse response = stub.obtenerPagos(request);
				vRes = response.get_return();
				if(vRes.equals("OK")){
					// Se envía segunda liquidación
				    Utils utils = new Utils();
				    _mensajeError2 += utils.enviarPrimeraSegundaLiquidacion(2);
				    CancelarLiquidacion();
				    LimpiarBusqueda();
				    BuscarRepresentante();
				    _mensajeError3 = "Orden liquidada.";					    
				}else{
					_mensajeError3 = "Problema al realizar la liquidación.";
				}
			}catch(Exception ex){
				
			}
		}else{
			_mensajeError2 = "Debe ingresar al menos un pago para liquidar.";
		}
		return vRes;
	}
	

	/*
	 * *********************** Encapsulamiento *****************************
	 */
	
	public List<SelectItem> get_listaBancos() {
		return _listaBancos;
	}

	public void set_listaBancos(List<SelectItem> _listaBancos) {
		this._listaBancos = _listaBancos;
	}

	public List<SelectItem> get_listaTipoPagos() {
		return _listaTipoPagos;
	}

	public void set_listaTipoPagos(List<SelectItem> _listaTipoPagos) {
		this._listaTipoPagos = _listaTipoPagos;
	}

	public List<SelectItem> get_listaQuienRecibe() {
		return _listaQuienRecibe;
	}

	public void set_listaQuienRecibe(List<SelectItem> _listaQuienRecibe) {
		this._listaQuienRecibe = _listaQuienRecibe;
	}

	public List<LiquidarOrdenesVentanillaMasiva> get_listaRegistrosRepresentantes() {
		return _listaRegistrosRepresentantes;
	}

	public void set_listaRegistrosRepresentantes(
			List<LiquidarOrdenesVentanillaMasiva> _listaRegistrosRepresentantes) {
		this._listaRegistrosRepresentantes = _listaRegistrosRepresentantes;
	}

	public List<LiquidarOrdenesVentanillaMasiva> get_listaLiquidar() {
		return _listaLiquidar;
	}

	public void set_listaLiquidar(
			List<LiquidarOrdenesVentanillaMasiva> _listaLiquidar) {
		this._listaLiquidar = _listaLiquidar;
	}

	public LiquidarOrdenesVentanillaMasiva get_dtDetalle() {
		return _dtDetalle;
	}

	public void set_dtDetalle(LiquidarOrdenesVentanillaMasiva _dtDetalle) {
		this._dtDetalle = _dtDetalle;
	}

	public LiquidarOrdenesVentanillaMasiva get_dtDetalleLiquidar() {
		return _dtDetalleLiquidar;
	}

	public void set_dtDetalleLiquidar(
			LiquidarOrdenesVentanillaMasiva _dtDetalleLiquidar) {
		this._dtDetalleLiquidar = _dtDetalleLiquidar;
	}

	public String get_lblCampaña() {
		return _lblCampaña;
	}

	public void set_lblCampaña(String _lblCampaña) {
		this._lblCampaña = _lblCampaña;
	}

	public String get_lblOrden() {
		return _lblOrden;
	}

	public void set_lblOrden(String _lblOrden) {
		this._lblOrden = _lblOrden;
	}

	public String get_lblRegistro() {
		return _lblRegistro;
	}

	public void set_lblRegistro(String _lblRegistro) {
		this._lblRegistro = _lblRegistro;
	}

	public String get_lblNombre() {
		return _lblNombre;
	}

	public void set_lblNombre(String _lblNombre) {
		this._lblNombre = _lblNombre;
	}

	public String get_lblMontoCobrar() {
		return _lblMontoCobrar;
	}

	public void set_lblMontoCobrar(String _lblMontoCobrar) {
		this._lblMontoCobrar = _lblMontoCobrar;
	}

	public String get_lblRemanente() {
		return _lblRemanente;
	}

	public void set_lblRemanente(String _lblRemanente) {
		this._lblRemanente = _lblRemanente;
	}

	public String get_txtFolioCobranza() {
		return _txtFolioCobranza;
	}

	public void set_txtFolioCobranza(String _txtFolioCobranza) {
		this._txtFolioCobranza = _txtFolioCobranza;
	}

	public String get_txtMonto() {
		return _txtMonto;
	}

	public void set_txtMonto(String _txtMonto) {
		this._txtMonto = _txtMonto;
	}

	public String get_txtregistro() {
		return _txtregistro;
	}

	public void set_txtregistro(String _txtregistro) {
		this._txtregistro = _txtregistro;
	}

	public String get_mensajeError1() {
		return _mensajeError1;
	}

	public void set_mensajeError1(String _mensajeError1) {
		this._mensajeError1 = _mensajeError1;
	}

	public String get_mensajeError2() {
		return _mensajeError2;
	}

	public void set_mensajeError2(String _mensajeError2) {
		this._mensajeError2 = _mensajeError2;
	}
	
	public String get_mensajeError3() {
		return _mensajeError3;
	}

	public void set_mensajeError3(String _mensajeError3) {
		this._mensajeError3 = _mensajeError3;
	}

	public String get_txtFechaPago() {
		return _txtFechaPago;
	}

	public void set_txtFechaPago(String _txtFechaPago) {
		this._txtFechaPago = _txtFechaPago;
	}

	public String get_txtComentarios() {
		return _txtComentarios;
	}

	public void set_txtComentarios(String _txtComentarios) {
		this._txtComentarios = _txtComentarios;
	}

	public String get_campania() {
		return _campania;
	}

	public void set_campania(String _campania) {
		this._campania = _campania;
	}

	public Integer get_idBanco() {
		return _idBanco;
	}

	public void set_idBanco(Integer _idBanco) {
		this._idBanco = _idBanco;
	}

	public Integer get_idTipoPago() {
		return _idTipoPago;
	}

	public void set_idTipoPago(Integer _idTipoPago) {
		this._idTipoPago = _idTipoPago;
	}

	public Integer get_idQuienRecibe() {
		return _idQuienRecibe;
	}

	public void set_idQuienRecibe(Integer _idQuienRecibe) {
		this._idQuienRecibe = _idQuienRecibe;
	}

	public double get_montoTotal1() {
		return _montoTotal1;
	}

	public void set_montoTotal1(double _montoTotal1) {
		this._montoTotal1 = _montoTotal1;
	}

	public double get_montoTotal2() {
		return _montoTotal2;
	}

	public void set_montoTotal2(double _montoTotal2) {
		this._montoTotal2 = _montoTotal2;
	}

	public Boolean get_dtgRegistrosRepresentante() {
		return _dtgRegistrosRepresentante;
	}

	public void set_dtgRegistrosRepresentante(Boolean _dtgRegistrosRepresentante) {
		this._dtgRegistrosRepresentante = _dtgRegistrosRepresentante;
	}

	public Boolean get_dtgLiquidar() {
		return _dtgLiquidar;
	}

	public void set_dtgLiquidar(Boolean _dtgLiquidar) {
		this._dtgLiquidar = _dtgLiquidar;
	}

	public String get_txtregistroBusqueda() {
		return _txtregistroBusqueda;
	}

	public void set_txtregistroBusqueda(String _txtregistroBusqueda) {
		this._txtregistroBusqueda = _txtregistroBusqueda;
	}

	public String get_txtOtros() {
		return _txtOtros;
	}

	public void set_txtOtros(String _txtOtros) {
		this._txtOtros = _txtOtros;
	}

	public Boolean get_pnlDerecho() {
		return _pnlDerecho;
	}

	public void set_pnlDerecho(Boolean _pnlDerecho) {
		this._pnlDerecho = _pnlDerecho;
	}

	public Boolean get_pnlIzquierdo() {
		return _pnlIzquierdo;
	}

	public void set_pnlIzquierdo(Boolean _pnlIzquierdo) {
		this._pnlIzquierdo = _pnlIzquierdo;
	}

	public Integer get_idLiquidacion() {
		return _idLiquidacion;
	}

	public void set_idLiquidacion(Integer _idLiquidacion) {
		this._idLiquidacion = _idLiquidacion;
	}

	public Boolean get_pnlDerechoEliminar() {
		return _pnlDerechoEliminar;
	}

	public void set_pnlDerechoEliminar(Boolean _pnlDerechoEliminar) {
		this._pnlDerechoEliminar = _pnlDerechoEliminar;
	}

}
