package com.datacode.avon_ots_admin.cu002;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.EntregaVentanillaMasivaStub;
import com.datacode.avon_ots_ws.EntregaVentanillaMasivaStub.EntregaVentanillaMasiva;

public class ControllerEntregaVentanillaMasiva {

	private UIForm _frmModal;
	private Boolean _txtCodigoCaja = false;
	// private Boolean _txtCodigoPremio = false;
	private Boolean _txtCodigoRepresentante = false;
	private Boolean _txtCodigoUnitario = false;
	private Boolean _txtNombreRepresentante = false;
	private Boolean _btnConsultar = false;
	private Boolean _btnCancelarEntrega = false;
	private Boolean _btnEliminarRegistro = false;
	private Boolean _btnEntregarPedidos = false;
	private Boolean _btnLimpiarPedido = false;
	private Boolean _btnMostrarDetalle = false;
	private Boolean _btnValidaCaja = false;
	// private Boolean _btnValidaPremio= false;
	private Boolean _btnValidaUnitario = false;
	private Boolean _cobCampania = false;

	private Boolean _mostrarPanel = false;
	private Boolean _tablaCaja = false;
	// private Boolean _tablaPremio = false;
	private Boolean _tablaRepresentante = false;
	private Boolean _tablaUnitario = false;

	private Boolean _consultaPopUp = true;

	private Integer _idCampania = -1;
	private Integer _totalCajas = 0;
	// private Integer _totalPremios = 0;
	private Integer _totalUnitarios = 0;
	private Integer _pendientesCajas = 0;
	// private Integer _pendientesPremios = 0;
	private Integer _pendientesUnitarios = 0;

	private String _campania = "";
	private String _registroRepresentante = "";
	private String _nombreRepresentante = "";
	private String _codigoBarras = "";
	private String codigoFCS = "";
	private String codigoEAN13 = "";
	private String _mensajeError = " ";
	private String _IdOrden = "";// Variable local para obtener los idOrden de
									// los registros seleccionados
	private double _montoTotal = 0d;

	private EntregaVentanillaMasiva _dtRepresentante;
	private EntregaVentanillaMasiva _dtDetalle;
	private EntregaVentanillaMasiva _dtCaja;
	// private EntregaVentanillaMasiva _dtPremio;
	private EntregaVentanillaMasiva _dtUnitario;

	private String[] _lista = null;

	private List<SelectItem> _listaCampania = null;
	private List<EntregaVentanillaMasiva> _listaRegistrosRepresentantes = null;
	private List<EntregaVentanillaMasiva> _listaRegistrosRepresentantesPopUp = null;
	private List<EntregaVentanillaMasiva> _listaCajas = null;
	private List<EntregaVentanillaMasiva> _listaUnitarios = null;
	// private List<EntregaVentanillaMasiva> _listaPremios = null;

	private DecimalFormat _decimalFormat = null;
	private Configuracion _configuracion = null;

	public ControllerEntregaVentanillaMasiva() {
		_listaRegistrosRepresentantes = new ArrayList<EntregaVentanillaMasivaStub.EntregaVentanillaMasiva>();
		_configuracion = (Configuracion) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("configuracion");
		DecimalFormatSymbols vSymbol = new DecimalFormatSymbols();
		vSymbol.setDecimalSeparator('.');
		_decimalFormat = new DecimalFormat("#0.00", vSymbol);
	}

	public void AgregarRepresentante() {
		_mensajeError = "";
		try {
			if (_idCampania != 0) {
				if (!(_nombreRepresentante.trim().equals("") && _nombreRepresentante
						.trim().length() == 0)) {
					_frmModal.setRendered(true);
					set_mostrarPanel(true);
					_consultaPopUp = true;
					_registroRepresentante = "";
				} else if (!_registroRepresentante.trim().equals("")) {
					_frmModal.setRendered(true);
					getObtenerRepresentante();
					if (_mensajeError.equals("")) {
						_tablaRepresentante = true;
						_consultaPopUp = false;
						set_mostrarPanel(false);
					}
					set_mostrarPanel(false);
				} else {
					_mensajeError = "Ingresa un registro o representante.";
				}
			} else {
				_mensajeError = "Debe seleccionar una Campaña";
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void AsignarRepresentanteSeleccionada() {
		try {
			_nombreRepresentante = String.valueOf(_dtRepresentante.getNombre());
			_registroRepresentante = String.valueOf(_dtRepresentante
					.getRegistro());
			_tablaRepresentante = true;
			_consultaPopUp = true;
			set_mostrarPanel(false);
			getObtenerRepresentante();
			// _tablaRegistrosRepresentantes.add(_representante);
			// getObtenerTablaRepresentantes();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void getObtenerRepresentante() {
		EntregaVentanillaMasiva[] vArregloRepresentante = null;
		List<EntregaVentanillaMasiva> vLista = null;
		if (_idCampania != 0) {
			try {
				_campania = _listaCampania.get(_idCampania).getLabel();
				EntregaVentanillaMasivaStub stub = new EntregaVentanillaMasivaStub();
				String url = Utils.modificarUrlServicioWeb(stub
						._getServiceClient().getOptions().getTo().getAddress());
				stub._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
				EntregaVentanillaMasivaStub.ObtenerOrdenesRepresentante request = new EntregaVentanillaMasivaStub.ObtenerOrdenesRepresentante();
				request.setPRegistro(_registroRepresentante);
				request.setPCampania(_campania);
				request.setPIdUsuario(_configuracion.getIdUsuario());
				EntregaVentanillaMasivaStub.ObtenerOrdenesRepresentanteResponse response = stub
						.obtenerOrdenesRepresentante(request);
				vArregloRepresentante = response.get_return();

				if (vArregloRepresentante != null) {
					vLista = new ArrayList<EntregaVentanillaMasiva>();
					vLista = Arrays.asList(vArregloRepresentante);
					_dtRepresentante = new EntregaVentanillaMasiva();
					_dtRepresentante = vLista.get(0);
					for (int i = 0; i < vArregloRepresentante.length; i++) {
						Boolean vExiste = false;
						for (EntregaVentanillaMasiva entregaVentanillaMasiva : _listaRegistrosRepresentantes) {
							if (entregaVentanillaMasiva.getRegistro().equals(
									vArregloRepresentante[i].getRegistro())
									&& entregaVentanillaMasiva.getNumeroOrden()
											.equals(vArregloRepresentante[i]
													.getNumeroOrden())) {
								_mensajeError = "Ya se ingreso la representante:"
										+ _registroRepresentante
										+ "con la orden "
										+ entregaVentanillaMasiva
												.getNumeroOrden();
								vExiste = true;
								break;
							}
						}
						if (!vExiste) {
							_listaRegistrosRepresentantes
									.add(vArregloRepresentante[i]);
							_montoTotal = Double
									.parseDouble(_decimalFormat.format(_montoTotal
											+ Double.parseDouble(vArregloRepresentante[i]
													.getMontoCobrar())));
						}
					}
					_nombreRepresentante = "";
					_registroRepresentante = "";
					_cobCampania = true;
				} else {
					_mensajeError = "El COD "
							+ _registroRepresentante
							+ " ingresado no es válido para entregar orden en éste proceso.";
					_nombreRepresentante = "";
					_registroRepresentante = "";
				}
			} catch (RemoteException ex) {
				// Utils.GuardarLogMensajeBD("General Admin", "M6",
				// "No se pudieron cargar representantes en el grid",
				// ex.getMessage(), configuracion.getIdUsuario());
				System.out.println(ex.getMessage());
			} finally {

			}
		} else {
			_mensajeError = "Debe seleccionar una Campaña";
		}
	}

	public List<SelectItem> getObtenerCampanias() {

		try {

			EntregaVentanillaMasivaStub stub = new EntregaVentanillaMasivaStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			EntregaVentanillaMasivaStub.ObtenerCampania request = new EntregaVentanillaMasivaStub.ObtenerCampania();
			EntregaVentanillaMasivaStub.ObtenerCampaniaResponse response = stub
					.obtenerCampania(request);
			_lista = new String[response.get_return().length];
			_lista = response.get_return();
			_listaCampania = new ArrayList<SelectItem>();
			_listaCampania.add(new SelectItem(0, "Selecciona una opción"));
			for (int i = 0; i < _lista.length; i++) {
				_listaCampania.add(new SelectItem(i + 1, _lista[i]));
			}

		} catch (RemoteException ex) {
			// Utils.GuardarLogMensajeBD("General Admin", "M6",
			// "No se pudieron cargar representantes en el grid",
			// ex.getMessage(), configuracion.getIdUsuario());
			System.out.println(ex.getMessage());
		} finally {

		}
		return _listaCampania;
	}

	public List<EntregaVentanillaMasiva> getObtenerRepresentantesPopUp() {
		EntregaVentanillaMasiva[] vArregloRepresentante = null;
		List<EntregaVentanillaMasiva> vLista = null;

		if (_consultaPopUp) {
			try {
				// Se crea el cliente
				EntregaVentanillaMasivaStub stub = new EntregaVentanillaMasivaStub();

				// Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(stub
						._getServiceClient().getOptions().getTo().getAddress());
				stub._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));

				// Se agrega al request para la operacion a ejecutar
				EntregaVentanillaMasivaStub.ObtenerRepresentantes request = new EntregaVentanillaMasivaStub.ObtenerRepresentantes();
				// pasamos parametros
				request.setPRegistro(_registroRepresentante);
				request.setPNombre(_nombreRepresentante);
				request.setPIdUsuario(_configuracion.getIdUsuario());
				// request.setIdUsuario(configuracion.getIdUsuario());
				// Añade al response el request de la operacion
				EntregaVentanillaMasivaStub.ObtenerRepresentantesResponse response = stub
						.obtenerRepresentantes(request);
				// obtenemos valores
				vArregloRepresentante = response.get_return();
				if (vArregloRepresentante == null) {
					vLista = new ArrayList<EntregaVentanillaMasiva>();
				} else {
					vLista = Arrays.asList(vArregloRepresentante);
					_listaRegistrosRepresentantesPopUp = new ArrayList<EntregaVentanillaMasivaStub.EntregaVentanillaMasiva>();
					_listaRegistrosRepresentantesPopUp = vLista;
					// _representante = new EntregaVentanillaMasiva();
					// _representante = vLista.get(0);
				}
			} catch (RemoteException ex) {
				// Utils.GuardarLogMensajeBD("General Admin", "M6",
				// "No se pudieron cargar representantes en el grid",
				// ex.getMessage(), configuracion.getIdUsuario());
				System.out.println(ex.getMessage());
			}
			_consultaPopUp = false;
		} else {
			vLista = _listaRegistrosRepresentantesPopUp;
		}
		return vLista;
	}

	/**
	 * Elimina registro seleccionado
	 * 
	 * @author Andres.Alvarez
	 * @date 12/05/2014
	 */
	public void EliminarRegistroTablaRepresentantes() {
		// Se realiza un corrimiento a la tabla para eliminar el registro
		// seleccionado
		for (int i = 0; i < _listaRegistrosRepresentantes.size(); i++) {
			// Valida la posicion del registro seleccionado para eliminarlo
			if (_dtDetalle.getRegistro() == _listaRegistrosRepresentantes
					.get(i).getRegistro()) {
				// Resta la cantidad del registro seleccionado en el monto total
				_montoTotal = Double
						.parseDouble(_decimalFormat.format(_montoTotal
								- Double.parseDouble(_listaRegistrosRepresentantes
										.get(i).getMontoCobrar())));
				_listaRegistrosRepresentantes.remove(i);
				break;
			}
		}
		// _decimalFormat.format(_montoTotal);
	}

	public void LimpiarPedidos() {
		_totalCajas = 0;
		// _totalPremios = 0;
		_totalUnitarios = 0;
		_pendientesCajas = 0;
		// _pendientesPremios = 0;
		_pendientesUnitarios = 0;
		_montoTotal = 0f;
		_mensajeError = " ";
		_nombreRepresentante = "";
		_registroRepresentante = "";
		_cobCampania = false;
		_consultaPopUp = true;
		_listaRegistrosRepresentantes.clear();
		_dtRepresentante = new EntregaVentanillaMasiva();
	}

	public void CancelarEntrega() {
		_listaCajas = new ArrayList<EntregaVentanillaMasivaStub.EntregaVentanillaMasiva>();
		// _listaPremios = new
		// ArrayList<EntregaVentanillaMasivaStub.EntregaVentanillaMasiva>();
		_listaUnitarios = new ArrayList<EntregaVentanillaMasivaStub.EntregaVentanillaMasiva>();

		_totalCajas = 0;
		// _totalPremios = 0;
		_totalUnitarios = 0;
		_pendientesCajas = 0;
		// _pendientesPremios = 0;
		_pendientesUnitarios = 0;

		_IdOrden = "";

		_txtCodigoCaja = false;
		// _txtCodigoPremio = false;
		_txtCodigoUnitario = false;
		_txtCodigoRepresentante = false;
		_txtNombreRepresentante = false;
		_btnCancelarEntrega = false;
		_btnConsultar = false;
		_btnEliminarRegistro = false;
		_btnEntregarPedidos = false;
		_btnLimpiarPedido = false;
		_btnMostrarDetalle = false;
		_btnValidaCaja = false;
		// _btnValidaPremio = false;
		_btnValidaUnitario = false;
		_tablaCaja = false;
		// _tablaPremio = false;
		_tablaUnitario = false;

	}

	public void ObtenerDetalles() {
		_mensajeError = " ";// Se limpia variable de mensaje
		if (_listaRegistrosRepresentantes.size() > 0) {
			EntregaVentanillaMasiva[] vArregloDetalles = null;// Variable local
																// para obtener
																// los registros
																// correspondientes
																// a los idOrden
			List<EntregaVentanillaMasiva> vLista = null;// Variable local que
														// obtiene una lista de
														// los registros
			// Ciclo que obtiene los idOrden
			for (EntregaVentanillaMasiva vRegistro : _listaRegistrosRepresentantes) {
				_IdOrden += vRegistro.getIdOrden() + ", ";
			}
			try {
				// Se crea el cliente
				EntregaVentanillaMasivaStub stub = new EntregaVentanillaMasivaStub();
				// Obtiene y asigna url de configuración de web services
				String url = Utils.modificarUrlServicioWeb(stub
						._getServiceClient().getOptions().getTo().getAddress());
				stub._getServiceClient()
						.getOptions()
						.setTo(new org.apache.axis2.addressing.EndpointReference(
								url));
				// Se agrega al request para la operacion a ejecutar
				EntregaVentanillaMasivaStub.ObtenerDetalleOrden request = new EntregaVentanillaMasivaStub.ObtenerDetalleOrden();
				// pasamos parametros
				request.setPIdOrden(_IdOrden.substring(0, _IdOrden.length() - 2));
				request.setPIdUsuario(_configuracion.getIdUsuario());
				// Añade al response el request de la operacion
				EntregaVentanillaMasivaStub.ObtenerDetalleOrdenResponse response = stub
						.obtenerDetalleOrden(request);
				// obtenemos valores
				vArregloDetalles = response.get_return();
				// Valida que si obtenga registros
				if (vArregloDetalles == null) {
					vLista = new ArrayList<EntregaVentanillaMasiva>();
				} else {
					// Asigna los registros del arreglo a la lista
					vLista = Arrays.asList(vArregloDetalles);
					// Valida que tenga registros
					if (vLista.size() > 0) {
						// Se instancian las variables globales
						_listaCajas = new ArrayList<EntregaVentanillaMasivaStub.EntregaVentanillaMasiva>();
						// _listaPremios = new
						// ArrayList<EntregaVentanillaMasivaStub.EntregaVentanillaMasiva>();
						_listaUnitarios = new ArrayList<EntregaVentanillaMasivaStub.EntregaVentanillaMasiva>();
						// Ciclo que recorre la lista para ingresar los
						// registros a sus repesctivas listas
						for (EntregaVentanillaMasiva entregaVentanillaMasiva : vLista) {
							// Valida que el tipo se igual a CAJA
							if (!entregaVentanillaMasiva.getCodigo().equals("")) {
								// if(entregaVentanillaMasiva.getTipo().toUpperCase().equals("CAJA")){
								_listaCajas.add(entregaVentanillaMasiva);// Agrega
																			// el
																			// registro
								// Habilita los controles para el panel Cajas
								_tablaCaja = true;
								_txtCodigoCaja = true;
								_btnValidaCaja = true;
								_totalCajas = _totalCajas
										+ Integer
												.parseInt(entregaVentanillaMasiva
														.getCantidad());// Obtiene
																		// el
																		// total
																		// de
																		// registros
																		// a
																		// escanear
								_pendientesCajas = _totalCajas;
							}
							// Valida que el tipo se igual a PREMIO
							/*
							 * else
							 * if(entregaVentanillaMasiva.getTipo().toUpperCase
							 * ().equals("PREMIO")){
							 * _listaPremios.add(entregaVentanillaMasiva
							 * );//Agrega el registro //Habilita los controles
							 * para el panel Premios _tablaPremio = true;
							 * _txtCodigoPremio = true; _btnValidaPremio = true;
							 * _totalPremios = _totalPremios +
							 * Integer.parseInt(entregaVentanillaMasiva
							 * .getCantidad());//Obtiene el total de registros a
							 * escanear _pendientesPremios = _totalPremios; }
							 */
							// Valida que el tipo se igual a UNITARIO
							else // if(entregaVentanillaMasiva.getTipo().toUpperCase().equals("UNITARIO")
									// ||
									// entregaVentanillaMasiva.getTipo().toUpperCase().equals("PREMIO"))
							{
								_listaUnitarios.add(entregaVentanillaMasiva);// Agrega
																				// el
																				// registro
								// Habilita los controles para el panel
								// Unitarios
								_tablaUnitario = true;
								_txtCodigoUnitario = true;
								_btnValidaUnitario = true;
								_totalUnitarios = _totalUnitarios
										+ Integer
												.parseInt(entregaVentanillaMasiva
														.getCantidad());// Obtiene
																		// el
																		// total
																		// de
																		// registros
																		// a
																		// escanear
								_pendientesUnitarios = _totalUnitarios;
							}
						}
						// Deshabilita los controles para no realizar ninguna
						// accion
						_txtCodigoRepresentante = true;
						_txtNombreRepresentante = true;
						_btnCancelarEntrega = true;
						_btnConsultar = true;
						_btnEliminarRegistro = true;
						_btnEntregarPedidos = true;
						_btnLimpiarPedido = true;
						_btnMostrarDetalle = true;

					}
				}
			} catch (RemoteException ex) {
				// Utils.GuardarLogMensajeBD("General Admin", "M6",
				// "No se pudieron cargar representantes en el grid",
				// ex.getMessage(), configuracion.getIdUsuario());
				System.out.println(ex.getMessage());
			}
		} else {
			_mensajeError = "No hay registro para ver detalle.";
		}
	}

	public void EntregarPedidos() {

		try {
			// Se crea el cliente
			EntregaVentanillaMasivaStub stub = new EntregaVentanillaMasivaStub();
			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			// Se agrega al request para la operacion a ejecutar
			EntregaVentanillaMasivaStub.ActualizaEntregaVentanilla request = new EntregaVentanillaMasivaStub.ActualizaEntregaVentanilla();
			// pasamos parametros
			request.setPCajas(_listaCajas
					.toArray(new EntregaVentanillaMasiva[_listaCajas.size()]));
			request.setPUnitarios(_listaUnitarios
					.toArray(new EntregaVentanillaMasiva[_listaUnitarios.size()]));
			// request.setPPremios(_listaPremios.toArray(new
			// EntregaVentanillaMasiva[_listaPremios.size()]));
			request.setPIdOrden(_IdOrden.substring(0, _IdOrden.length() - 2));
			request.setPIdUsuario(String.valueOf(_configuracion.getIdUsuario()));
			// Añade al response el request de la operacion
			@SuppressWarnings("unused")
			EntregaVentanillaMasivaStub.ActualizaEntregaVentanillaResponse response = stub
					.actualizaEntregaVentanilla(request);

			CancelarEntrega();
			LimpiarPedidos();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * *********************** Encapsulamiento *****************************
	 */
	public UIForm get_frmModal() {
		return _frmModal;
	}

	public void set_frmModal(UIForm _frmModal) {
		this._frmModal = _frmModal;
	}

	public Boolean get_txtCodigoCaja() {
		return _txtCodigoCaja;
	}

	public void set_txtCodigoCaja(Boolean _txtCodigoCaja) {
		this._txtCodigoCaja = _txtCodigoCaja;
	}

	/*
	 * public Boolean get_txtCodigoPremio() { return _txtCodigoPremio; }
	 * 
	 * public void set_txtCodigoPremio(Boolean _txtCodigoPremio) {
	 * this._txtCodigoPremio = _txtCodigoPremio; }
	 */

	public Boolean get_txtCodigoRepresentante() {
		return _txtCodigoRepresentante;
	}

	public void set_txtCodigoRepresentante(Boolean _txtCodigoRepresentante) {
		this._txtCodigoRepresentante = _txtCodigoRepresentante;
	}

	public Boolean get_txtCodigoUnitario() {
		return _txtCodigoUnitario;
	}

	public void set_txtCodigoUnitario(Boolean _txtCodigoUnitario) {
		this._txtCodigoUnitario = _txtCodigoUnitario;
	}

	public Boolean get_txtNombreRepresentante() {
		return _txtNombreRepresentante;
	}

	public void set_txtNombreRepresentante(Boolean _txtNombreRepresentante) {
		this._txtNombreRepresentante = _txtNombreRepresentante;
	}

	public Boolean get_btnConsultar() {
		return _btnConsultar;
	}

	public void set_btnConsultar(Boolean _btnConsultar) {
		this._btnConsultar = _btnConsultar;
	}

	public Boolean get_btnCancelarEntrega() {
		return _btnCancelarEntrega;
	}

	public void set_btnCancelarEntrega(Boolean _btnCancelarEntrega) {
		this._btnCancelarEntrega = _btnCancelarEntrega;
	}

	public Boolean get_btnEliminarRegistro() {
		return _btnEliminarRegistro;
	}

	public void set_btnEliminarRegistro(Boolean _btnEliminarRegistro) {
		this._btnEliminarRegistro = _btnEliminarRegistro;
	}

	public Boolean get_btnEntregarPedidos() {
		return _btnEntregarPedidos;
	}

	public void set_btnEntregarPedidos(Boolean _btnEntregarPedidos) {
		this._btnEntregarPedidos = _btnEntregarPedidos;
	}

	public Boolean get_btnLimpiarPedido() {
		return _btnLimpiarPedido;
	}

	public void set_btnLimpiarPedido(Boolean _btnLimpiarPedido) {
		this._btnLimpiarPedido = _btnLimpiarPedido;
	}

	public Boolean get_btnMostrarDetalle() {
		return _btnMostrarDetalle;
	}

	public void set_btnMostrarDetalle(Boolean _btnMostrarDetalle) {
		this._btnMostrarDetalle = _btnMostrarDetalle;
	}

	public Boolean get_btnValidaCaja() {
		return _btnValidaCaja;
	}

	public void set_btnValidaCaja(Boolean _btnValidaCaja) {
		this._btnValidaCaja = _btnValidaCaja;
	}

	/*
	 * public Boolean get_btnValidaPremio() { return _btnValidaPremio; }
	 * 
	 * public void set_btnValidaPremio(Boolean _btnValidaPremio) {
	 * this._btnValidaPremio = _btnValidaPremio; }
	 */

	public Boolean get_btnValidaUnitario() {
		return _btnValidaUnitario;
	}

	public void set_btnValidaUnitario(Boolean _btnValidaUnitario) {
		this._btnValidaUnitario = _btnValidaUnitario;
	}

	public Boolean get_cobCampania() {
		return _cobCampania;
	}

	public void set_cobCampania(Boolean _cobCampania) {
		this._cobCampania = _cobCampania;
	}

	public Boolean get_mostrarPanel() {
		return _mostrarPanel;
	}

	public void set_mostrarPanel(Boolean _mostrarPanel) {
		this._mostrarPanel = _mostrarPanel;
	}

	public Boolean get_tablaCaja() {
		return _tablaCaja;
	}

	public void set_tablaCaja(Boolean _tablaCaja) {
		this._tablaCaja = _tablaCaja;
	}

	/*
	 * public Boolean get_tablaPremio() { return _tablaPremio; }
	 * 
	 * public void set_tablaPremio(Boolean _tablaPremio) { this._tablaPremio =
	 * _tablaPremio; }
	 */

	public Boolean get_tablaRepresentante() {
		return _tablaRepresentante;
	}

	public void set_tablaRepresentante(Boolean _tablaRepresentante) {
		this._tablaRepresentante = _tablaRepresentante;
	}

	public Boolean get_tablaUnitario() {
		return _tablaUnitario;
	}

	public void set_tablaUnitario(Boolean _tablaUnitario) {
		this._tablaUnitario = _tablaUnitario;
	}

	public Boolean get_consultaPopUp() {
		return _consultaPopUp;
	}

	public void set_consultaPopUp(Boolean _consultaPopUp) {
		this._consultaPopUp = _consultaPopUp;
	}

	public Integer get_idCampania() {
		return _idCampania;
	}

	public void set_idCampania(Integer _idCampania) {
		this._idCampania = _idCampania;
	}

	public Integer get_totalCajas() {
		return _totalCajas;
	}

	public void set_totalCajas(Integer _totalCajas) {
		this._totalCajas = _totalCajas;
	}

	/*
	 * public Integer get_totalPremios() { return _totalPremios; }
	 * 
	 * public void set_totalPremios(Integer _totalPremios) { this._totalPremios
	 * = _totalPremios; }
	 */

	public Integer get_totalUnitarios() {
		return _totalUnitarios;
	}

	public void set_totalUnitarios(Integer _totalUnitarios) {
		this._totalUnitarios = _totalUnitarios;
	}

	public Integer get_pendientesCajas() {
		return _pendientesCajas;
	}

	public void set_pendientesCajas(Integer _pendientesCajas) {
		this._pendientesCajas = _pendientesCajas;
	}

	/*
	 * public Integer get_pendientesPremios() { return _pendientesPremios; }
	 * 
	 * public void set_pendientesPremios(Integer _pendientesPremios) {
	 * this._pendientesPremios = _pendientesPremios; }
	 */

	public Integer get_pendientesUnitarios() {
		return _pendientesUnitarios;
	}

	public void set_pendientesUnitarios(Integer _pendientesUnitarios) {
		this._pendientesUnitarios = _pendientesUnitarios;
	}

	public String get_campania() {
		return _campania;
	}

	public void set_campania(String _campania) {
		this._campania = _campania;
	}

	public String get_registroRepresentante() {
		return _registroRepresentante;
	}

	public void set_registroRepresentante(String _registroRepresentante) {
		this._registroRepresentante = _registroRepresentante;
	}

	public String get_nombreRepresentante() {
		return _nombreRepresentante;
	}

	public void set_nombreRepresentante(String _nombreRepresentante) {
		this._nombreRepresentante = _nombreRepresentante;
	}

	public String get_codigoBarras() {
		return _codigoBarras;
	}

	public void set_codigoBarras(String _codigoBarras) {
		this._codigoBarras = _codigoBarras;
	}

	public String get_codigoFSC() {
		return _codigoBarras;
	}

	public void set_codigoFSC(String _codigoBarras) {
		this._codigoBarras = _codigoBarras;
	}

	public String get_codigoEAN13() {
		return _codigoBarras;
	}

	public void set_codigoEAN13(String _codigoBarras) {
		this._codigoBarras = _codigoBarras;
	}

	public String get_mensajeError() {
		return _mensajeError;
	}

	public void set_mensajeError(String _mensajeError) {
		this._mensajeError = _mensajeError;
	}

	public double get_montoTotal() {
		return _montoTotal;
	}

	public void set_montoTotal(double _montoTotal) {
		this._montoTotal = _montoTotal;
	}

	public EntregaVentanillaMasiva get_dtRepresentante() {
		return _dtRepresentante;
	}

	public void set_dtRepresentante(EntregaVentanillaMasiva _dtRepresentante) {
		this._dtRepresentante = _dtRepresentante;
	}

	public EntregaVentanillaMasiva get_dtDetalle() {
		return _dtDetalle;
	}

	public void set_dtDetalle(EntregaVentanillaMasiva _dtDetalle) {
		this._dtDetalle = _dtDetalle;
	}

	public EntregaVentanillaMasiva get_dtCaja() {
		return _dtCaja;
	}

	public void set_dtCaja(EntregaVentanillaMasiva _dtCaja) {
		this._dtCaja = _dtCaja;
	}

	/*
	 * public EntregaVentanillaMasiva get_dtPremio() { return _dtPremio; }
	 * 
	 * public void set_dtPremio(EntregaVentanillaMasiva _dtPremio) {
	 * this._dtPremio = _dtPremio; }
	 */

	public EntregaVentanillaMasiva get_dtUnitario() {
		return _dtUnitario;
	}

	public void set_dtUnitario(EntregaVentanillaMasiva _dtUnitario) {
		this._dtUnitario = _dtUnitario;
	}

	public String[] get_lista() {
		return _lista;
	}

	public void set_lista(String[] _lista) {
		this._lista = _lista;
	}

	public List<SelectItem> get_listaCampania() {
		return _listaCampania;
	}

	public void set_listaCampania(List<SelectItem> _listaCampania) {
		this._listaCampania = _listaCampania;
	}

	/**
	 * Obtiene una lista con los representantes
	 * 
	 * @author Andres.Alvarez
	 * @date 13/05/2014
	 * @return Devuelve una lista con los registros obtenidos
	 */
	public List<EntregaVentanillaMasiva> get_listaRegistrosRepresentantes() {
		return _listaRegistrosRepresentantes;
	}

	public void set_listaRegistrosRepresentantes(
			List<EntregaVentanillaMasiva> _listaRegistrosRepresentantes) {
		this._listaRegistrosRepresentantes = _listaRegistrosRepresentantes;
	}

	public List<EntregaVentanillaMasiva> get_listaRegistrosRepresentantesPopUp() {
		return _listaRegistrosRepresentantesPopUp;
	}

	public void set_listaRegistrosRepresentantesPopUp(
			List<EntregaVentanillaMasiva> _listaRegistrosRepresentantesPopUp) {
		this._listaRegistrosRepresentantesPopUp = _listaRegistrosRepresentantesPopUp;
	}

	/**
	 * Obtiene una lista con las cajas de los registros seleccionados
	 * 
	 * @author Andres.Alvarez
	 * @date 13/05/2014
	 * @return Devuelve una lista con los registros obtenidos
	 */
	public List<EntregaVentanillaMasiva> get_listaCajas() {
		return _listaCajas;
	}

	public void set_listaCajas(List<EntregaVentanillaMasiva> _listaCajas) {
		this._listaCajas = _listaCajas;
	}

	/**
	 * Obtiene una lista con los unitarios de los registros seleccionados
	 * 
	 * @author Andres.Alvarez
	 * @date 13/05/2014
	 * @return Devuelve una lista con los registros obtenidos
	 */
	public List<EntregaVentanillaMasiva> get_listaUnitarios() {
		return _listaUnitarios;
	}

	public void set_listaUnitarios(List<EntregaVentanillaMasiva> _listaUnitarios) {
		this._listaUnitarios = _listaUnitarios;
	}

	/**
	 * Obtiene una lista con los premios de los registros seleccionados
	 * 
	 * @author Andres.Alvarez
	 * @date 13/05/2014
	 * @return Devuelve una lista con los registros obtenidos
	 */
	/*
	 * public List<EntregaVentanillaMasiva> get_listaPremios() { return
	 * _listaPremios; }
	 * 
	 * public void set_listaPremios(List<EntregaVentanillaMasiva> _listaPremios)
	 * { this._listaPremios = _listaPremios; }
	 */

	public DecimalFormat get_decimalFormat() {
		return _decimalFormat;
	}

	public void set_decimalFormat(DecimalFormat _decimalFormat) {
		this._decimalFormat = _decimalFormat;
	}

	public Configuracion get_configuracion() {
		return _configuracion;
	}

	public void set_configuracion(Configuracion _configuracion) {
		this._configuracion = _configuracion;
	}

	public String get_IdOrden() {
		return _IdOrden;
	}

	public void set_IdOrden(String _IdOrden) {
		this._IdOrden = _IdOrden;
	}

}
