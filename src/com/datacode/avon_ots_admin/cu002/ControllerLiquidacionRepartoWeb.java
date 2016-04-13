package com.datacode.avon_ots_admin.cu002;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.axis2.AxisFault;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.richfaces.component.UIScrollableDataTable;
import org.richfaces.model.selection.SimpleSelection;

import com.datacode.avon_ots_admin.reports.JasperGenerator;
import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.PrimeraSegundaLiquidacionControllerStub;
import com.datacode.avon_ots_ws.LiquidacionRepartoWeb;
import com.datacode.avon_ots_ws.LiquidacionRepartoWebController;

public class ControllerLiquidacionRepartoWeb {

	Configuracion configuracion;

	Utils utils;

	private static final LiquidacionRepartoWebController WS = new LiquidacionRepartoWebController();

	private static final String M1 = "Por favor ingrese o seleccione algún criterio de búsqueda.";

	private static final String M2 = "Debe seleccionar al menos otro filtro.";

	private static final String SIGNO_PESOS = "$";

	private static final String ESPACIO = " ";

	private static final String VACIO = "";

	private static final String COMA = ",";

//	private static final String M3 = "El monto capturado no debe ser mayor al monto a cobrar.";

	private static final int TIMEOUT_MILISEGUNDOS = 360000; //6 minutos

	private Long totalItems = 0L;

	private Long totalCajas = 0L;

	private Long totalPUs = 0L;

	private String idRowSeleccionado = "";

	private Integer cantidadFilasItems = null;

	private String valorOrdenamiento = "";

	private String mensajeAceptarDetalle = "";

	private String valorOrdenamientoAnterior = "";

	private String tipoOrdenamiento = "";

	private Boolean roboCajas = null;

	private Boolean existenPagos = null;

	private Boolean disabledEdicionPagos = null;

	private String tipoDeOrden = "";

	private String valorDeOrden = "";

	private String tipoLiquidacion = "";

	private String campaniaMostrar = null;

	private String ordenMostrar = null;

	private String registroMostrar = null;

	private String nombreMostrar = null;

	private String idCampania = "";

	private Integer estatusReversion = null;

	private Boolean disableCampania = false;

	private Boolean disabledEditarOrden = true;

	private List<SelectItem> listaCampanias = null;

	private List<SelectItem> listaTiposLiquidacion = new ArrayList<SelectItem>();

	private Integer idZona = 0;

	private String idOrdenSegmentada = "";

	private Boolean disableZona = false;

	private List<SelectItem> listaZonas = null;

	private Integer idRuta = -1;

	private Boolean disableRuta = false;

	private String libreCobro = "";

	private Boolean disableOrdenSegmentada = false;

	private List<SelectItem> listaRutas = new ArrayList<SelectItem>();

	private String registro = null;

	private Boolean disableRegistro = false;

	private String nombre = null;

	private Boolean disableNombre = false;

	private Boolean mostrarGridConsulta = false;

	private Boolean disableBtnConsultar = false;

	private Boolean disabledBtnLimpiarFormulario = false;

	private Boolean disabledBtnLimpiarOrdenes = false;

	private LiquidacionRepartoWeb ordenDetalle = null;

	private Boolean deshabilitarEdicion = false;

	private LiquidacionRepartoWeb ordenEdicion = null;

	private LiquidacionRepartoWeb ordenDevolucion = null;

	private List<LiquidacionRepartoWeb> listaOrdenes =new ArrayList<LiquidacionRepartoWeb>();

	private Boolean mostrarTblOrdenes = false;

	private List<LiquidacionRepartoWeb> listaRepresentantes = new ArrayList<LiquidacionRepartoWeb>();

	private LiquidacionRepartoWeb representanteSeleccionado = new LiquidacionRepartoWeb();

	private String domicilio = "";

	private Boolean mostrarPanel = false;

	private UIForm frmModal;

	private UIForm frmAceptar;

	private UIForm frmAceptarNo;

	private UIScrollableDataTable tblOrdenes;

	private Boolean mostrarDetalleItems = false;

	private Boolean mostrarDevolucionItems = false;

	private Boolean mostrarPanelQuienInformaDevolucion = false;

	private Boolean disabledCbRazonesDevolucion = false;

	private List<SelectItem> listaRazonesDevolucion = null;

	private Boolean disabledBtnCancelarDevolucion = false;

	private Boolean disabledBtnDevolverOrden = false;

	private Integer idQuienInformaDevolucion = null;

	private Boolean disabledQuienInformaDevolucion = false;

	private List<SelectItem> listaQuienInformaDevolucion = new ArrayList<SelectItem>();

	private Integer idRazonDevolucion = null;

	private Boolean mostrarTblDetalleItems = false;

	private List<LiquidacionRepartoWeb> listaDetalleItems = new ArrayList<LiquidacionRepartoWeb>();

	private List<LiquidacionRepartoWeb> listaPagosItems = new ArrayList<LiquidacionRepartoWeb>();

	private List<LiquidacionRepartoWeb> listaPagosItemsReales = new ArrayList<LiquidacionRepartoWeb>();

	private Boolean disabledBtnAgregarPago = false;

	private LiquidacionRepartoWeb pagoEliminacion = new LiquidacionRepartoWeb();

	private LiquidacionRepartoWeb pagoEdicion = new LiquidacionRepartoWeb();

	private Boolean disabledBtnEntregaDevolucion = false;

	private Boolean mostrarPanelDerecho = false;

	private Double montoTotal = 0D;

	private Double montoSobrante = 0D;

	private String montoSobranteMostrar = "";

	private Double remanente = 0D;

	private Double montoTotalPagos = 0D;

	private List<SelectItem> listaBancos = null;

	private List<SelectItem> listaTiposDePago = null;

	private List<LiquidacionRepartoWeb> listaTiposDePagoObjeto = new ArrayList<LiquidacionRepartoWeb>();

	private List<LiquidacionRepartoWeb> listaBancosObjeto = new ArrayList<LiquidacionRepartoWeb>();

	private Integer idBanco = null;

	private Integer idTipoDePago = null;

	private Boolean disabledTipoDePago = false;

	private Boolean disabledBanco = false;

	private String folioCobranza = "";

	private Boolean disabledFolioCobranza = false;

	private String fechaDePago = "";

	private Boolean disabledFechaDePago = false;

	private String monto = "";

	private Boolean disabledMonto = false;

	private Integer idQuienRecibe = null;

	private Boolean disabledQuienRecibe = false;

	private List<SelectItem> listaQuienRecibe = null;

	private String comentarios = "";

	private Boolean disabledQuienRecibeComentarios = false;

	private String mensajeGenerico = "";

	private Integer idRepresentante = null;

	private Boolean mostrarPanelOrdenes = null;

	private Boolean mostrarPanelDetalle = null;

	private Boolean hijackedCash = null;

	private String cashSequence = null;

	private Boolean noExistePagoEfectivo = null;

	private Integer cantidadOrdenes = null;

	private Boolean mostrarCashSequence = false;

	private Integer esOrdenamiento = null;

	private Boolean deshabilitarDevolucionSegundaLiquidacion = null;

	private Boolean permitirAceptarOrden = true;

	private String idEnrutadoRuta = "";

	private List<SelectItem> listaEnrutadoRutas = null;

	private String enrutadoSecuencia = "";

	private String enrutadoRutaSecuenciaOriginal = "";

	private Boolean esEntrega = null;

	private Boolean huboCambiosPagos = null;

	private Integer idRazonDevolucionOriginal = null;

	public ControllerLiquidacionRepartoWeb() {
		setConfiguracion((Configuracion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("configuracion"));
		setUtils(new Utils());
		obtenerListaCampanias();
		obtenerListaZonas();
		obtenerListaRazonesDevolucionItems();
		obtenerListaQuienInformaDevolucionItems();
		obtenerListaBancos();
		obtenerListaTiposDePago();
		obtenerListaQuienRecibe();
		habilitarFormulario(true);
		habilitarPanelOrdenes(false);
		habilitarPanelDetalleItems(false);
		habilitarPanelDevolucion(false);
		setMostrarDetalleItems(false);
		setMostrarPanelOrdenes(true);
		setMostrarPanelDetalle(false);
		setCantidadOrdenes(0);
		obtenerListaTiposLiquidacion();
		setTipoLiquidacion("1");
	}

	public void exportarExcel() {
		try {
			List<LiquidacionRepartoWeb> listaOrdenes = this.listaOrdenes;
			JasperGenerator jasperGenerator = new JasperGenerator();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String fecha = df.format(new Date());
			jasperGenerator.generaReporteLiquidacionRepartoWeb(listaOrdenes, "XLS", "Liquidacion_Enrutado_" + fecha, Utils.obtenerFechaReportes());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void consultarOrdenes() {
		setMensajeGenerico("");
		setListaOrdenes(new ArrayList<LiquidacionRepartoWeb>());
		setCantidadOrdenes(0);
		setMostrarTblOrdenes(false);
		habilitarFormulario(true);
		habilitarPanelOrdenes(false);
		if (getNombre() != null && getNombre().length() > 0 && getNombre().trim().length() > 0) {
			consultarRepresentantes();
		} else {
			obtenerListaOrdenes(true);
		}
		setTipoLiquidacion("1");
	}

	public void validarCamposAgregarPago() {
		setMensajeGenerico("");
		String tipoDePago = "";
		if (getIdTipoDePago() < 1) {
			setMensajeGenerico("Debe seleccionar un tipo de pago.");
		} else {
			for (SelectItem iter : getListaTiposDePago()) {
				if(iter.getValue() == getIdTipoDePago()) {
					tipoDePago = iter.getLabel();
					break;
				}
			}
			if (tipoDePago.indexOf("Banco") > -1) {
				if (getIdBanco() < 1) {
					setMensajeGenerico("Debe seleccionar un banco.");
				}
			} else if (tipoDePago.indexOf("Recibo de cobranza") > -1) {
				if ("".equals(getFolioCobranza().trim())) {
					setMensajeGenerico("Debe seleccionar un folio de cobranza.");
				}
			}
		}
//		if (obtenerMontoDouble() > getRemanente()) {
//			setMensajeGenerico(M3);
//		}
		if (obtenerMontoDouble() <= 0D) {
			setMensajeGenerico("El monto debe ser mayor a cero.");
		}
		if ("".equals(getMensajeGenerico())) {
			if (obtenerMontoDouble() != null) {
				setMensajeGenerico("");
			}
		}
	}

	public void agregarPago() {
		setMensajeGenerico("");
		validarCamposAgregarPago();
		if ("".equals(getMensajeGenerico())) {
			String descripcionTipoDePago = "";
			for (LiquidacionRepartoWeb pago : getListaTiposDePagoObjeto()) {
				if (getIdTipoDePago() == pago.getIdTipoDePago()) {
					descripcionTipoDePago = pago.getDescripcionTipoDePagoCorta();
				}
			}
			String banco = "";
			String descripcionBanco = "";
			for (LiquidacionRepartoWeb bancoIter : getListaBancosObjeto()) {
				if (getIdBanco() == bancoIter.getIdBanco()) {
					descripcionBanco = bancoIter.getDescripcionBancoCorta();
				}
			}
			for (SelectItem iter : getListaBancos()) {
				if(iter.getValue() == getIdBanco()) {
					banco = iter.getLabel();
					break;
				}
			}
			LiquidacionRepartoWeb pagoItem = new LiquidacionRepartoWeb();
			pagoItem.setTipoDePago(getIdTipoDePago().toString());
			pagoItem.setIdTipoDePago(getIdTipoDePago());
			pagoItem.setDescripcionTipoDePago(descripcionTipoDePago);
			pagoItem.setIdBanco(getIdBanco());
			if (getIdTipoDePago() == 1) {
				pagoItem.setDescripcionBanco(descripcionBanco);
				pagoItem.setDescripcionItem(descripcionBanco);
			} else {
				pagoItem.setDescripcionBanco("");
				pagoItem.setDescripcionItem("");
			}
			pagoItem.setDescripcionPago(banco.trim());
			pagoItem.setFolioPago(getFolioCobranza().trim());
			pagoItem.setFechaPago(getFechaDePago());
			pagoItem.setMontoPago(getDecimalFormat().format(obtenerMontoDouble()));
			pagoItem.setMontoPagado(Double.valueOf(obtenerDoubleString(getDecimalFormat().format(obtenerMontoDouble()))));
			pagoItem.setIdPagoEntrega(0);
			for (LiquidacionRepartoWeb iter : getListaPagosItems()) {
				if (iter.getIdPagoEntrega() <= pagoItem.getIdPagoEntrega()) {
					pagoItem.setIdPagoEntrega(iter.getIdPagoEntrega());
				}
			}
			pagoItem.setIdPagoEntrega(pagoItem.getIdPagoEntrega() - 1);
			setListaPagosItems(new ArrayList<LiquidacionRepartoWeb>(getListaPagosItems()));
			getListaPagosItems().add(pagoItem);
			setIdBanco(0);
			setIdTipoDePago(0);
			setFolioCobranza("");
			setFechaDePago(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		}
		recalcularMontos();
		validarExistenciaPagoEfectivo(true);
		setHuboCambiosPagos(true);
	}

	public void eliminarTodosLosPagos() {
		setListaPagosItems(new ArrayList<LiquidacionRepartoWeb>());
		setRemanente(getMontoTotal());
		validarExistenciaPagoEfectivo(true);
	}

	public void eliminarPago() {
		List<LiquidacionRepartoWeb> pagosItems = new ArrayList<LiquidacionRepartoWeb>();
		try {
			for (LiquidacionRepartoWeb iter : getListaPagosItems()) {
				if (iter.getIdPagoEntrega() != getPagoEliminacion().getIdPagoEntrega()) {
					iter.setEliminarPago(false);
					pagosItems.add(iter);
				}
			}
			for (LiquidacionRepartoWeb iter : getListaPagosItemsReales()) {
				if (iter.getIdPagoEntrega() == getPagoEliminacion().getIdPagoEntrega()) {
					iter.setEliminarPago(true);
				}
			}
			setListaPagosItems(pagosItems);
		} catch (NumberFormatException e) {
			setMensajeGenerico("No se pudo eliminar el pago");
		}
		setIdBanco(0);
		setIdTipoDePago(0);
		setFolioCobranza("");
		setFechaDePago(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		recalcularMontos();
		validarExistenciaPagoEfectivo(true);
		setHuboCambiosPagos(true);
	}

	public void habilitarCashSequence() {
		if (!getHijackedCash()) {
		}
		setMostrarCashSequence(getHijackedCash());
	}

	public void asignarRepresentanteSeleccionado() {
		try {
			setNombre(String.valueOf(getRepresentanteSeleccionado().getNombre()));
			setRegistro(String.valueOf(getRepresentanteSeleccionado().getRegistro()));
			setDomicilio(String.valueOf(getRepresentanteSeleccionado().getDomicilio()));
			setIdRepresentante(getRepresentanteSeleccionado().getIdRepresentante());
			setMostrarPanel(false);
			obtenerListaOrdenes(true);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private boolean asignarOrdenDetalle() {
		setMensajeGenerico("");
		if (getTblOrdenes() == null || getTblOrdenes().getSelection() == null || getTblOrdenes().getSelection().getKeys() == null) {
			setMensajeGenerico("Favor de consultar la lista de \u00F3rdenes");
			setOrdenDetalle(null);
			return false;
		}
		Iterator<Object> iterator = getTblOrdenes().getSelection().getKeys();
		try {
			if (getTblOrdenes() == null
					|| getTblOrdenes().getSelection() == null
					|| getTblOrdenes().getSelection().getKeys() == null
					|| getTblOrdenes().getSelection().getKeys().next() == null) {
				setMensajeGenerico("Debe seleccionar al menos una orden");
				setOrdenDetalle(null);
				return false;
			}
			setIdRowSeleccionado(getTblOrdenes().getSelection().getKeys().next().toString());
		} catch (NoSuchElementException e) {
			setMensajeGenerico("Debe seleccionar al menos una orden");
			setOrdenDetalle(null);
			return false;
		} catch (Exception e) {
			setMensajeGenerico("Debe seleccionar al menos una orden");
			setOrdenDetalle(null);
			return false;
		}
		int cantidad = 0;
		Integer indice = -1;
		while (iterator.hasNext()) {
			cantidad++;
			Object key = iterator.next();
			if (key instanceof Integer) {
				indice = (Integer) key;
			}
		}
		if (cantidad < 1) {
			setMensajeGenerico("Debe seleccionar al menos una orden");
			setOrdenDetalle(null);
			return false;
		} else if (cantidad > 1) {
			setMensajeGenerico("Debe seleccionar s\u00F3lamente una orden");
			setOrdenDetalle(null);
			return false;
		} else {
			if (getListaOrdenes() == null || getListaOrdenes().size() < 1 || getListaOrdenes().get(indice) == null) {
				setMensajeGenerico("Favor de consultar la lista de \u00F3rdenes");
				setOrdenDetalle(null);
				return false;
			}
			setOrdenDetalle(getListaOrdenes().get(indice));
			Integer idOrdenSegmentada = null;
			try {
				idOrdenSegmentada = Integer.valueOf(getIdOrdenSegmentada());
			} catch (NumberFormatException e) {
				idOrdenSegmentada = -1;
				System.err.println(e.getMessage());
			}
			List<LiquidacionRepartoWeb> listaOrdenesTemp = WS.obtenerListaOrdenes(
					getIdCampania(),
					getIdZona(),
					0,
					getRegistro(),
					idOrdenSegmentada,
					getNombre(),
					getConfiguracion().getIdUsuario(),
					getOrdenDetalle().getIdOrden());
			if (listaOrdenesTemp == null || listaOrdenesTemp.size() < 1) {
				setMensajeGenerico("Ocurri\u00F3 un error al obtener el detalle de la orden");
				return false;
			}
			setOrdenDetalle(listaOrdenesTemp.get(0));
			getListaOrdenes().set(indice, getOrdenDetalle());
			setDeshabilitarDevolucionSegundaLiquidacion(getTipoLiquidacion() != null && "2".equals(getTipoLiquidacion().trim())); // dona.ugalde cambios a UCW010_1
			if (getOrdenDetalle() != null
					&& ((getOrdenDetalle().getIdRazonDevolucionMostrar() != null && getOrdenDetalle().getIdRazonDevolucionMostrar() > 0)
							|| (getOrdenDetalle().getIdQuienRecibe() != null && getOrdenDetalle().getIdQuienRecibe() > 0))) {
				setEstatusReversion(1);
			} else {
				setEstatusReversion(null);
			}
			return true;
		}
	}	

	public void editarOrden() {
		setEsEntrega(null);
		setHuboCambiosPagos(null);
		setIdRazonDevolucionOriginal(null);
		if (asignarOrdenDetalle()) {
			setEsOrdenamiento(1);
			if (getOrdenDetalle() == null || getDisabledEditarOrden()) {
				return;
			}
			setCashSequence("");
			setHijackedCash(false);
			setMensajeGenerico("");
			setMostrarPanelOrdenes(false);
			setMostrarPanelDetalle(true);
			mostrarInformacionOrden(getOrdenDetalle());
			setMensajeGenerico("");
			habilitarPanelDetalleItems(true);
			habilitarPanelDevolucion(false);
			obtenerListaDetalleItems(getOrdenDetalle());
			obtenerListaPagosOrden();
			setDisabledBtnEntregaDevolucion(true);
			setMostrarPanelDerecho(true);
			setDisabledBtnLimpiarOrdenes(true);
			setearValoresDetalleItems(true);
			setIdBanco(0);
			setIdTipoDePago(0);
			setFolioCobranza("");
			setFechaDePago(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			setMonto(String.valueOf(getRemanente()));
			setIdQuienRecibe(getOrdenDetalle().getIdQuienRecibe());
			if (getOrdenDetalle().getComentariosLiquidacion() != null && !"".equals(getOrdenDetalle().getComentariosLiquidacion().trim())) {
				setComentarios(getOrdenDetalle().getComentariosLiquidacion());
			} else if (getOrdenDetalle().getComentariosEntregaMostrar() != null && !"".equals(getOrdenDetalle().getComentariosEntregaMostrar().trim())) {
				setComentarios(getOrdenDetalle().getComentariosEntregaMostrar());
			} else if (getOrdenDetalle().getComentariosNoEntregaMostrar() != null && !"".equals(getOrdenDetalle().getComentariosNoEntregaMostrar().trim())) {
				setComentarios(getOrdenDetalle().getComentariosNoEntregaMostrar());
			} else {
				setComentarios("");
				if (getOrdenDetalle().getComentario() != null && !"".equals(getOrdenDetalle().getComentario().trim())) {
					setComentarios(getOrdenDetalle().getComentario());
				}
			}
			setDeshabilitarEdicion(getOrdenDetalle().getDeshabilitarEdicion());
			setIdQuienRecibe(getOrdenDetalle().getIdPersonaRecibeMostrar());
			setIdRazonDevolucion(getOrdenDetalle().getIdRazonDevolucionMostrar());
			setRoboCajas(getOrdenDetalle().getRoboCajas());
			setCashSequence(getOrdenDetalle().getCashSequence());
			setHijackedCash(getOrdenDetalle().getHijackedCash());
			if (getOrdenDetalle().getIdRazonDevolucionMostrar() != 0) {
				setIdQuienInformaDevolucion(getOrdenDetalle().getIdInformanteMostrar());
			} else {
				setIdQuienInformaDevolucion(null);
			}
			validarRazonesDevolucion();
			validarExistenciaPagoEfectivo(true);
			setCantidadFilasItems(getListaDetalleItems().size());
			setLibreCobro(getOrdenDetalle().getLibreCobro());
			obtenerListaRutasEnrutado();
			setEnrutadoSecuencia(getOrdenDetalle().getSecuenciaEnrutado());
			
			setIdRazonDevolucionOriginal(getIdRazonDevolucion());
			if (getIdRazonDevolucion() != null && getIdRazonDevolucion() > 0) {
				setEsEntrega(false);
			} else if (getListaPagosItems() != null && getListaPagosItems().size() > 0) {
				setEsEntrega(true);
			} else {
				setEsEntrega(null);
			}
		}
	}

	public void validarAceptarDetalle() {
		if ((getIdRazonDevolucion() != null && getIdRazonDevolucion() != 0)
				&& ("2".equals(getTipoLiquidacion().trim()))) {
			setPermitirAceptarOrden(false);
		} else {
			setPermitirAceptarOrden(true);
			if ((getIdRazonDevolucion() != null && getIdRazonDevolucion() == 0)
					&& (getListaPagosItems() == null || getListaPagosItems().size() < 1)) {
				// Solo capturaron comentarios y/o robo de cajas
				setMensajeAceptarDetalle("¿Desea guardar la informaci\u00F3n de la orden?");
			} else {
				if (getIdRazonDevolucion() != null && getIdRazonDevolucion() != 0) {
					// Es devolucion
					setMensajeAceptarDetalle("¿Desea guardar la informaci\u00F3n de la orden?");
				} else {
					// Es entrega
					if (getRemanente() >= 1) {
						setMensajeAceptarDetalle("Existe un remanente de " + getRemanenteMostrar() + ". ¿Desea guardar la informaci\u00F3n de la orden?");
					} else if (Double.valueOf(obtenerDoubleString(getDecimalFormat().format(getMontoSobrante()))) > 0D) {
						setMensajeAceptarDetalle("Existe un sobrante de " + getMontoSobranteMostrar() + ". ¿Desea guardar la informaci\u00F3n de la orden?");
					} else {
						setMensajeAceptarDetalle("¿Desea guardar la informaci\u00F3n de la orden?");
					}
				}
			}
		}
	}

	public void aceptarOrden() {
		if (getOrdenDetalle().getBlokFlag() != null && getOrdenDetalle().getBlokFlag() == 1) {
			if (obtenerRutaSecuenciaEnrutado() != null) {
				// Solo guardaron el enrutado
				actualizarInformacionOrden(true);
			} else {
				setMensajeGenerico("No se registraron cambios en el enrutado");
			}
		} else {
			if ((getIdRazonDevolucion() == null || getIdRazonDevolucion() == 0)
					&& (getListaPagosItems() == null || getListaPagosItems().size() < 1)) {
				// Solo capturaron comentarios y/o robo de cajas
				actualizarInformacionOrden(false);
			} else {
				if (getIdRazonDevolucion() != null && getIdRazonDevolucion() != 0) {
					// Devolucion
					devolverOrden();
				} else {
					// Entrega
					liquidarOrdenPago();
				}
			}
		}
	}

	public void mostrarDetalleOrden() {
		setMensajeGenerico("");
		setMostrarPanelDetalle(true);
		if (getOrdenDetalle().getBlokFlag() == 1) {
			setMensajeGenerico("La orden est\u00E1 bloqueada, no se puede modificar su liquidaci\u00F3n");
		} else {
			mostrarInformacionOrden(getOrdenDetalle());
			setMensajeGenerico("");
			habilitarPanelDetalleItems(true);
			habilitarPanelDevolucion(false);
			obtenerListaDetalleItems(getOrdenDetalle());
			obtenerListaPagosOrden();
			setDisabledBtnEntregaDevolucion(true);
			setMostrarPanelDerecho(true);
			setDisabledBtnLimpiarOrdenes(true);
			setearValoresDetalleItems(true);
			setIdBanco(0);
			setIdTipoDePago(0);
			setFolioCobranza("");
			setFechaDePago(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			setMonto(String.valueOf(getRemanente()));
			setIdQuienRecibe(getOrdenDetalle().getIdQuienRecibe());
			setComentarios(getOrdenDetalle().getComentariosLiquidacion());
		}
	}

	public void mostrarDevolucionOrden() {
		setMensajeGenerico("");
		setMostrarPanelDetalle(true);
		if (getOrdenDevolucion().getBlokFlag() == 1) {
			setMensajeGenerico("La orden est\u00E1 bloqueada, no se puede modificar su liquidaci\u00F3n");
		} else {
			mostrarInformacionOrden(getOrdenDevolucion());
			habilitarPanelDevolucion(true);
			habilitarPanelDetalleItems(false);
			setIdRazonDevolucion(0);
			setIdQuienInformaDevolucion(0);
			setMostrarPanelQuienInformaDevolucion(false);
			setComentarios("");
			setMostrarDetalleItems(false);
			setDisabledBtnEntregaDevolucion(true);
			setMostrarPanelDerecho(true);
			setDisabledBtnLimpiarOrdenes(true);
			setIdRazonDevolucion(getOrdenDevolucion().getIdRazonDevolucion());
			setIdQuienInformaDevolucion(getOrdenDevolucion().getIdTipoInformante());
			setComentarios(getOrdenDevolucion().getComentariosDevolucion());
			if (getOrdenDevolucion().getIdRazonDevolucion() == 4) {
				setMostrarPanelQuienInformaDevolucion(true);
			} else {
				setMostrarPanelQuienInformaDevolucion(false);
			}
		}
	}

	public void cancelarDetalle() {
		setEsOrdenamiento(1);
		cancelarDevolucion();
		cancelarPago();
		setDeshabilitarEdicion(false);
	}

	public void cancelarDevolucion() {
		setMensajeGenerico("");
		habilitarPanelDevolucion(false);
		habilitarPanelOrdenes(true);
		setDisabledBtnEntregaDevolucion(false);
		setMostrarPanelDerecho(false);
		setDisabledBtnLimpiarOrdenes(false);
		setIdRazonDevolucion(0);
		setMostrarPanelQuienInformaDevolucion(false);
		validarRazonesDevolucion();
		//obtenerListaOrdenes();
		mostrarInformacionOrden(null);
		setIdRazonDevolucion(0);
		setIdQuienInformaDevolucion(0);
		setComentarios("");
		setMostrarPanelOrdenes(true);
		setMostrarPanelDetalle(false);
		setDeshabilitarEdicion(false);
	}

	public void cancelarPago() {
		setMensajeGenerico("");
		habilitarPanelOrdenes(true);
		habilitarPanelDetalleItems(false);
		habilitarPanelDevolucion(false);
		setListaDetalleItems(new ArrayList<LiquidacionRepartoWeb>());
		setListaPagosItems(new ArrayList<LiquidacionRepartoWeb>());
		setMostrarDetalleItems(false);
		setDisabledBtnEntregaDevolucion(false);
		setMostrarPanelDerecho(false);
		setDisabledBtnLimpiarOrdenes(false);
		//obtenerListaOrdenes();
		mostrarInformacionOrden(null);
		setIdQuienRecibe(0);
		setComentarios("");
		setMostrarPanelOrdenes(true);
		setMostrarPanelDetalle(false);
	}

	public void validarRazonesDevolucion() {
		setMensajeGenerico("");
		if (getIdRazonDevolucion() != null && getIdRazonDevolucion() > 0) {
			if (getListaPagosItems().size() > 0) {
				setMensajeGenerico("Elimine todos los pagos capturados antes de seleccionar una raz\u00F3n de devoluci\u00F3n");
				setIdRazonDevolucion(0);
			}
		}
		if (getIdRazonDevolucion() != null && getIdRazonDevolucion() > 0) {
			if (getIdRazonDevolucion() == 4) {
				setMostrarPanelQuienInformaDevolucion(true);
			} else {
				setMostrarPanelQuienInformaDevolucion(false);
			}
			setDisabledEdicionPagos(true);
		} else {
			setMostrarPanelQuienInformaDevolucion(false);
			setDisabledEdicionPagos(false);
		}
		recalcularMontos();
		validarExistenciaPagoEfectivo(true);
	}

	public void limpiarOrdenes() {
		setMostrarPanel(false);
		setMensajeGenerico("");
		setListaOrdenes(new ArrayList<LiquidacionRepartoWeb>());
		habilitarFormulario(true);
		habilitarPanelOrdenes(false);
		setMostrarDetalleItems(false);
		resetearOrdenSeleccionada();
	}

	public void liquidarOrdenPago() {
		if (realizarLiquidacion()) {
			//obtenerListaOrdenes(false);
			setListaDetalleItems(new ArrayList<LiquidacionRepartoWeb>());
			setListaPagosItems(new ArrayList<LiquidacionRepartoWeb>());
			habilitarPanelOrdenes(true);
			habilitarPanelDetalleItems(false);
			habilitarPanelDevolucion(false);
			setListaDetalleItems(new ArrayList<LiquidacionRepartoWeb>());
			setListaPagosItems(new ArrayList<LiquidacionRepartoWeb>());
			setMostrarDetalleItems(false);
			setDisabledBtnEntregaDevolucion(false);
			setMostrarPanelDerecho(false);
			setDisabledBtnLimpiarOrdenes(false);
			mostrarInformacionOrden(null);
			setIdQuienRecibe(0);
			setComentarios("");
			exitoAccion();
		}
	}

	public void devolverOrden() {
		if (realizarDevolucion()) {
			//obtenerListaOrdenes(false);
			setListaDetalleItems(new ArrayList<LiquidacionRepartoWeb>());
			setListaPagosItems(new ArrayList<LiquidacionRepartoWeb>());
			habilitarPanelOrdenes(true);
			habilitarPanelDetalleItems(false);
			habilitarPanelDevolucion(false);
			setMostrarPanelDerecho(false);
			setDisabledBtnEntregaDevolucion(false);
			setDisabledBtnLimpiarOrdenes(false);
			mostrarInformacionOrden(null);
			setIdRazonDevolucion(0);
			setIdQuienInformaDevolucion(0);
			setComentarios("");
			exitoAccion();
		}
	}

	public void actualizarInformacionOrden(boolean soloEnrutado) {
		if (realizarActualizacionInformacionOrden(soloEnrutado)) {
			//obtenerListaOrdenes(false);
			setListaDetalleItems(new ArrayList<LiquidacionRepartoWeb>());
			setListaPagosItems(new ArrayList<LiquidacionRepartoWeb>());
			habilitarPanelOrdenes(true);
			habilitarPanelDetalleItems(false);
			habilitarPanelDevolucion(false);
			setListaDetalleItems(new ArrayList<LiquidacionRepartoWeb>());
			setListaPagosItems(new ArrayList<LiquidacionRepartoWeb>());
			setMostrarPanelDerecho(false);
			setDisabledBtnEntregaDevolucion(false);
			setDisabledBtnLimpiarOrdenes(false);
			mostrarInformacionOrden(null);
			setIdRazonDevolucion(0);
			setIdQuienInformaDevolucion(0);
			setComentarios("");
			exitoAccion();
		}
	}

	public void limpiarFormulario() {
		setMensajeGenerico("");
		setListaOrdenes(new ArrayList<LiquidacionRepartoWeb>());
		setCantidadOrdenes(0);
		setMostrarTblOrdenes(false);
		habilitarFormulario(true);
		habilitarPanelOrdenes(false);
		setMostrarPanel(false);
		setMensajeGenerico("");
		setIdCampania("0");
		setIdZona(0);
		setRegistro("");
		setNombre("");
		setIdOrdenSegmentada("-1");
		setListaOrdenes(new ArrayList<LiquidacionRepartoWeb>());
	}

	public void enviarLiquidacionesPendientes() {
		setMensajeGenerico("");
		setListaOrdenes(new ArrayList<LiquidacionRepartoWeb>());
		setCantidadOrdenes(0);
		setMostrarTblOrdenes(false);
		habilitarFormulario(true);
		habilitarPanelOrdenes(false);
		LiquidacionRepartoWeb respuesta = WS.enviarLiquidacionesPendientes(
				getConfiguracion().getIdUsuario(),
				getConfiguracion().getUsuario());
		actualizarRouteSeqSOS();
		ejecutarReversionOrdenes();
		if (respuesta != null) {
			setMensajeGenerico(respuesta.getMensajeLiqPend());
			// Primeras liquidaciones
			EnviarLiquidacionesSOSHilo1 envioSOS1 = new EnviarLiquidacionesSOSHilo1();
			envioSOS1.idLiq1 = 1;
			envioSOS1.start();
			// Segundas liquidaciones
			EnviarLiquidacionesSOSHilo2 envioSOS2 = new EnviarLiquidacionesSOSHilo2();
			envioSOS2.idLiq2 = 2;
			envioSOS2.start();
		}
	}

	@SuppressWarnings("unchecked")
	public void ordenarOrdenes(String valorOrdenamiento) {
		setValorOrdenamiento(valorOrdenamiento);
		setEsOrdenamiento(1);
		BeanComparator b = new BeanComparator(getValorOrdenamiento(), new NullComparator());
		Collections.sort(getListaOrdenes(), b);
		if (getValorOrdenamientoAnterior().equals(getValorOrdenamiento())) {
			if ("Asc".equals(getTipoOrdenamiento())) {
				setTipoOrdenamiento("Desc");
				Collections.reverse(getListaOrdenes());
			} else {
				setTipoOrdenamiento("Asc");
			}
		} else {
			setTipoOrdenamiento("Asc");
		}
		setValorOrdenamientoAnterior(getValorOrdenamiento());
		resetearOrdenSeleccionada();
	}

	public void prepararEdicionPago() {
		setPagoEliminacion(getPagoEdicion());
		eliminarPago();
		setDisabledFolioCobranza(false);
		setDisabledBanco(false);
		setIdTipoDePago(getPagoEdicion().getIdTipoDePago());
		setIdBanco(getPagoEdicion().getIdBanco());
		setFechaDePago(getPagoEdicion().getFechaPago());
		setFolioCobranza(getPagoEdicion().getFolioPago());
		setMonto(getPagoEdicion().getMontoPago());
	}

	private String obtenerRutaSecuenciaEnrutadoFinal() {
		String ruta = "";
		if (getIdEnrutadoRuta() != null && getListaEnrutadoRutas() != null && getListaEnrutadoRutas().size() > 0) {
			for (SelectItem iter : getListaEnrutadoRutas()) {
				if (getIdEnrutadoRuta().trim().equals(iter.getValue().toString().trim())) {
					ruta = iter.getLabel().trim().toUpperCase();
					break;
				}
			}
		}
		String secuencia = "";
		if (getEnrutadoSecuencia() != null) {
			secuencia = getEnrutadoSecuencia().trim().toUpperCase();
		}
		return ruta + secuencia;
	}

	private void resetearOrdenSeleccionada() {
		if (getTblOrdenes() != null) {
			getTblOrdenes().setActiveRowKey(-1);
			if (getTblOrdenes().getSelection() != null && ((SimpleSelection) (getTblOrdenes().getSelection())) != null) {
				((SimpleSelection) (getTblOrdenes().getSelection())).clear();
				setIdRowSeleccionado("");
			}
		}
		setOrdenDetalle(null);
	}

	private void calcularTotales() {
		setTotalCajas(0L);
		setTotalItems(0L);
		setTotalPUs(0L);
		for(LiquidacionRepartoWeb iter : getListaOrdenes()) {
			setTotalCajas(getTotalCajas() + iter.getCantidadCajas());
			setTotalItems(getTotalItems() + iter.getCantidadItems());
			setTotalPUs(getTotalPUs() + iter.getCantidadPremiosUnitarios());
		}
	}

	private void validarExistenciaPagoEfectivo(boolean cambiarFecha) {
		boolean existeEfectivo = false;
		for (LiquidacionRepartoWeb iter : getListaPagosItems()) {
			if (iter.getDescripcionTipoDePago().indexOf("Efectivo") > -1) {
				existeEfectivo = true;
				break;
			}
		}
		setNoExistePagoEfectivo(!existeEfectivo);
		if (!existeEfectivo) {
			setHijackedCash(false);
			setMostrarCashSequence(false);
		} else {
			setMostrarCashSequence(getHijackedCash());
		}
		if (cambiarFecha) {
			setFechaDePago(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		}
		recalcularMontos();
		habilitarCashSequence();
		if (getListaPagosItems() == null) {
			setExistenPagos(false);
		}
		setExistenPagos(getListaPagosItems().size() > 0);
	}

	private void mostrarInformacionOrden(LiquidacionRepartoWeb orden) {
		if (orden == null) {
			setCampaniaMostrar("");
			setOrdenMostrar("");
			setRegistroMostrar("");
			setNombreMostrar("");
		} else {
			setCampaniaMostrar(orden.getCampania());
			setOrdenMostrar(orden.getClaveOrden());
			setRegistroMostrar(orden.getRegistro());
			setNombreMostrar(orden.getNombre());
		}
	}

	private void obtenerListaQuienRecibe() {
		setListaQuienRecibe(new ArrayList<SelectItem>());
		getListaQuienRecibe().add(new SelectItem(0, "Seleccione una opci\u00F3n"));
		setIdBanco(0);
		List<LiquidacionRepartoWeb> lista = WS.obtenerListaQuienRecibe(getConfiguracion().getIdUsuario());
		if (lista != null) {
			for (LiquidacionRepartoWeb iter : lista) {
				getListaQuienRecibe().add(new SelectItem(iter.getIdQuienRecibe(), iter.getDescripcionQuienRecibe()));
			}
		}
	}

	private void obtenerListaPagosOrden() {
		setListaPagosItems(new ArrayList<LiquidacionRepartoWeb>());
		setListaPagosItemsReales(new ArrayList<LiquidacionRepartoWeb>());
		List<LiquidacionRepartoWeb> listaPagos = WS.obtenerListaPagosOrden(getOrdenDetalle(), getConfiguracion().getIdUsuario());
		if (listaPagos != null) {
			setListaPagosItems(listaPagos);
			setListaPagosItemsReales(listaPagos);
		}
		Double remanente = getOrdenDetalle().getMontoOrden();
		for (LiquidacionRepartoWeb iter : getListaPagosItemsReales()) {
			remanente -= iter.getMontoPagado();
		}
		setRemanente(remanente);
		setMontoTotal(getOrdenDetalle().getMontoOrden());
		setMontoTotalPagos(getOrdenDetalle().getMontoOrden() - getRemanente());
		setMonto(obtenerDoubleString(getRemanenteMostrar()));
		validarExistenciaPagoEfectivo(true);
	}

	private void obtenerListaBancos() {
		setListaBancos(new ArrayList<SelectItem>());
		setListaBancosObjeto(new ArrayList<LiquidacionRepartoWeb>());
		getListaBancos().add(new SelectItem(0, "Seleccione un banco"));
		setIdBanco(0);
		List<LiquidacionRepartoWeb> listaBancos = WS.obtenerListaBancos(getConfiguracion().getIdUsuario());
		if (listaBancos != null) {
			for (LiquidacionRepartoWeb iter : listaBancos) {
				getListaBancos().add(new SelectItem(iter.getIdBanco(), iter.getDescripcionBanco()));
			}
			setListaBancosObjeto(listaBancos);
		}
	}

	private void obtenerListaTiposLiquidacion() {
		setListaTiposLiquidacion(new ArrayList<SelectItem>());
		List<LiquidacionRepartoWeb> listaTiposLiquidacion = WS.obtenerTiposLiquidacion(getConfiguracion().getIdUsuario());
		if (listaTiposLiquidacion != null) {
			for (LiquidacionRepartoWeb iter : listaTiposLiquidacion) {
				getListaTiposLiquidacion().add(new SelectItem(iter.getValor(), iter.getEtiqueta()));
			}
		}
	}

	private void obtenerListaTiposDePago() {
		setListaTiposDePago(new ArrayList<SelectItem>());
		setListaTiposDePagoObjeto(new ArrayList<LiquidacionRepartoWeb>());
		getListaTiposDePago().add(new SelectItem(0, "Seleccione un tipo de pago"));
		setIdTipoDePago(0);
		List<LiquidacionRepartoWeb> listaTiposDePago = WS.obtenerListaTiposDePago(getConfiguracion().getIdUsuario());
		if (listaTiposDePago != null) {
			for (LiquidacionRepartoWeb iter : listaTiposDePago) {
				getListaTiposDePago().add(new SelectItem(iter.getIdTipoDePago(), iter.getDescripcionTipoDePago()));
			}
			setListaTiposDePagoObjeto(listaTiposDePago);
		}
	}

	private void obtenerListaCampanias() {
		setListaCampanias(new ArrayList<SelectItem>());
		getListaCampanias().add(new SelectItem("0", "Seleccione una campa\u00F1a"));
		setIdCampania("0");
		List<String> listaCampanias = WS.obtenerListaCampanias(getConfiguracion().getIdUsuario());
		if (listaCampanias != null) {
			for (String iter : listaCampanias) {
				getListaCampanias().add(new SelectItem(iter, iter));
			}
		}
	}

	private void obtenerListaRutasEnrutado() {
		setListaEnrutadoRutas(new ArrayList<SelectItem>());
		setIdEnrutadoRuta(getOrdenDetalle().getIdRutaEnrutado());
		List<SelectItem> listaRutasEnrutado = WS.obtenerListaRutasEnrutado(getConfiguracion().getIdUsuario(), Integer.valueOf(getOrdenDetalle().getIdRepresentanteEnrutado()));
		if (listaRutasEnrutado != null) {
			for (SelectItem iter : listaRutasEnrutado) {
				getListaEnrutadoRutas().add(iter);
			}
		}
	}

	private void obtenerListaZonas() {
		setListaZonas(new ArrayList<SelectItem>());
		getListaZonas().add(new SelectItem(0, "Seleccione una zona"));
		setIdZona(0);
		List<LiquidacionRepartoWeb> listaZonas = WS.obtenerListaZonas(getConfiguracion().getIdUsuario());
		if (listaZonas != null) {
			for (LiquidacionRepartoWeb iter : listaZonas) {
				getListaZonas().add(new SelectItem(iter.getIdZona(), iter.getDescripcionZona()));
			}
		}
	}

	private Double obtenerMontoDouble() {
		String cantidad = "";
		if (getMonto() != null && getMonto().length() > 0) {
			setMonto(obtenerDoubleString(getMonto()));
			cantidad = getMonto();
		}
		try {
			if (cantidad != null) {
				return Double.parseDouble(cantidad);
			} else {
				setMensajeGenerico("El monto es inv\u00E1lido.");
				return null;
			}
		} catch (NumberFormatException e) {
			setMensajeGenerico("El monto es inv\u00E1lido.");
			return null;
		}
	}

	private void recalcularMontos() {
		Double montoTotalPagos = 0D;
		for (LiquidacionRepartoWeb iter : getListaPagosItems()) {
			montoTotalPagos += Double.parseDouble(obtenerDoubleString(iter.getMontoPago()));
		}
		setMontoTotalPagos(montoTotalPagos);
		setRemanente(getMontoTotal() - getMontoTotalPagos());
		setMonto(getDecimalFormat().format(getRemanente()));
		setMontoSobrante(montoTotalPagos - getMontoTotal());
	}

	private String validarCamposObligatorios() {
		if ("".equals(getRegistro().trim())) {
			int cantidadFiltros = 0;
			try {
				if (getIdCampania() != null && !"0".equals(getIdCampania().trim())) {
					cantidadFiltros++;
				}
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			}
			if (getIdZona() != null && getIdZona() > 0) {
				cantidadFiltros++;
			}
			if (getNombre() != null && getNombre().trim().length() > 0) {
				cantidadFiltros++;
			}
			try {
				if (getIdOrdenSegmentada() != null && Integer.valueOf(getIdOrdenSegmentada().trim()) != -1) {
					cantidadFiltros++;
				}
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			}
			if (cantidadFiltros < 1) {
				return M1;
			} else if (cantidadFiltros < 2) {
				return M2;
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	private void consultarRepresentantes() {
		setListaRepresentantes(new ArrayList<LiquidacionRepartoWeb>());
		if (getNombre() != null && getNombre().length() > 0 && getNombre().trim().length() > 0) {
			setNombre(getNombre().trim());
			setMostrarPanel(true);
		} else if (getRegistro() != null && getRegistro().length() > 0 && getRegistro().trim().length() > 0) {
			setRegistro(getRegistro().trim());
			setMostrarPanel(false);
		}
		String idCampania = null;
		if (getIdCampania() != null && !"0".equals(getIdCampania().trim())) {
			idCampania = getIdCampania();
		} else {
			idCampania = null;
		}
		String idZona = null;
		if (getIdZona() != null && getIdZona() > 0) {
			idZona = String.valueOf(getIdZona());
		} else {
			idZona = null;
		}
		List<LiquidacionRepartoWeb> listaRepresentantes = WS.obtenerRepresentantes(
				getRegistro(),
				getNombre(),
				idCampania,
				idZona,
				getConfiguracion().getIdUsuario());
		if (listaRepresentantes != null) {
			setListaRepresentantes(listaRepresentantes);
		}
		if (getMostrarPanel() == false) {
			if (getListaRepresentantes().size() == 1) {
				setMensajeGenerico("");
				setNombre(getListaRepresentantes().get(0).getNombre());
				setRegistro(getListaRepresentantes().get(0).getRegistro());
				setIdZona(Integer.valueOf(getListaRepresentantes().get(0).getIdZona()));
				setIdRuta(Integer.valueOf(getListaRepresentantes().get(0).getIdRuta()));
			} else {
				setMensajeGenerico("No se encontr\u00F3 al representante.");
				setMostrarPanel(false);
			}
		} else if (listaRepresentantes.size() < 1) {
			setMensajeGenerico("No se encontr\u00F3 ning\u00FAn representante.");
			setMostrarPanel(false);
		} else {
			setMensajeGenerico("");
		}
	}

	private void obtenerListaOrdenes(boolean cambiarMensaje) {
		String mensaje = validarCamposObligatorios();
		if (cambiarMensaje) {
			setMensajeGenerico(mensaje);
		}
		if ("".equals(mensaje)) {
			setListaOrdenes(new ArrayList<LiquidacionRepartoWeb>());
			Integer idOrdenSegmentada = null;
			try {
				idOrdenSegmentada = Integer.valueOf(getIdOrdenSegmentada());
			} catch (NumberFormatException e) {
				idOrdenSegmentada = -1;
				System.err.println(e.getMessage());
			}
			List<LiquidacionRepartoWeb> listaOrdenes = WS.obtenerListaOrdenes(
					getIdCampania(),
					getIdZona(),
					0,
					getRegistro(),
					idOrdenSegmentada, 
					getNombre(),
					getConfiguracion().getIdUsuario(),
					0);
			
			if (listaOrdenes != null) {
				setListaOrdenes(listaOrdenes);
			}
			calcularTotales();
			resetearOrdenSeleccionada();
			if (getListaOrdenes() != null && getListaOrdenes().size() > 0) {
				setMostrarTblOrdenes(true);
				habilitarFormulario(false);
				habilitarPanelOrdenes(true);
				setMensajeGenerico("");
			} else {
				setMostrarTblOrdenes(false);
				habilitarFormulario(true);
				habilitarPanelOrdenes(false);
				setMensajeGenerico("No se encontraron \u00D3rdenes.");
			}
		}
		if (getListaOrdenes() != null) {
			setCantidadOrdenes(getListaOrdenes().size());
		} else {
			setCantidadOrdenes(0);
		}
	}

	private void obtenerListaDetalleItems(LiquidacionRepartoWeb orden) {
		setListaDetalleItems(new ArrayList<LiquidacionRepartoWeb>());
		List<LiquidacionRepartoWeb> listaItemsPorOrden = WS.obtenerItemsPorOrden(orden, getConfiguracion().getIdUsuario());
		if (listaItemsPorOrden != null) {
			setListaDetalleItems(listaItemsPorOrden);
		}
		/**
		Si todos los items de la orden estan en ESPERA, se palomean todos.
		De lo contrario, se palomean solo los que no esten EN ESPERA.
		*/
		boolean todosEnEspera = true;
		final String enEspera = "1";
		for (LiquidacionRepartoWeb item : getListaDetalleItems()) {
			if (item.getIdEstatus() == null || !enEspera.equals(item.getIdEstatus().trim())) {
				todosEnEspera = false;
				break;
			}
		}
		if (todosEnEspera) {
			for (int i = 0; i < getListaDetalleItems().size(); i++) {
				getListaDetalleItems().get(i).setChecked(true);
			}
		} else {
			for (int i = 0; i < getListaDetalleItems().size(); i++) {
				if (getListaDetalleItems().get(i).getIdEstatus() != null && enEspera.equals(getListaDetalleItems().get(i).getIdEstatus().trim())) {
					getListaDetalleItems().get(i).setChecked(false);
				} else {
					getListaDetalleItems().get(i).setChecked(true);
				}
			}
		}
	}

	private void setearValoresDetalleItems(boolean habilitados) {
		setIdBanco(0);
		setIdTipoDePago(0);
		setIdQuienRecibe(0);
		setComentarios("");
		setFolioCobranza("");
		setFechaDePago(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		setDisabledFechaDePago(false);
		setDisabledMonto(false);
		setDisabledQuienRecibe(false);
		setDisabledQuienRecibeComentarios(false);
		setMensajeGenerico("");
	}

	private void habilitarFormulario(boolean habilitar) {
		setDisableCampania(!habilitar);
		setDisableZona(!habilitar);
		setDisableRuta(!habilitar);
		setDisableRegistro(!habilitar);
		setDisableNombre(!habilitar);
		setDisableBtnConsultar(!habilitar);
		setDisabledBtnLimpiarFormulario(!habilitar);
	}

	private void habilitarPanelOrdenes(boolean habilitar) {
		setMostrarTblOrdenes(habilitar);
		setDisabledBtnLimpiarOrdenes(!habilitar);
		setDisabledBtnEntregaDevolucion(!habilitar);
	}

	private void habilitarPanelDevolucion(boolean habilitar) {
		setMostrarDevolucionItems(habilitar);
	}

	private void habilitarPanelDetalleItems(boolean habilitar) {
		setMostrarTblDetalleItems(habilitar);
		setDisabledBtnAgregarPago(!habilitar);
		setMostrarDetalleItems(habilitar);
	}

	private void obtenerListaRazonesDevolucionItems() {
		setListaRazonesDevolucion(new ArrayList<SelectItem>());
		getListaRazonesDevolucion().add(new SelectItem(0, "Seleccione una raz\u00F3n"));
		setIdRazonDevolucion(0);
		List<LiquidacionRepartoWeb> listaRazonesDevolucion = WS.obtenerListaRazonesDevolucion(getConfiguracion().getIdUsuario());
		if (listaRazonesDevolucion != null) {
			for (LiquidacionRepartoWeb iter : listaRazonesDevolucion) {
				getListaRazonesDevolucion().add(new SelectItem(iter.getIdRazonDevolucion(), iter.getDescripcionRazonDevolucion()));
			}
		}
	}

	private void obtenerListaQuienInformaDevolucionItems() {
		setListaQuienInformaDevolucion(new ArrayList<SelectItem>());
		getListaQuienInformaDevolucion().add(new SelectItem(0, "Seleccione qui\u00E9n informa"));
		setIdQuienInformaDevolucion(0);
		List<LiquidacionRepartoWeb> listaInformantes = WS.obtenerListaTipoInformantes(getConfiguracion().getIdUsuario());
		if (listaInformantes != null) {
			for (LiquidacionRepartoWeb iter : listaInformantes) {
				getListaQuienInformaDevolucion().add(new SelectItem(iter.getIdTipoInformante(), iter.getDescripcionTipoInformante()));
			}
		}
	}

	private boolean realizarLiquidacion() {
		setEsOrdenamiento(1);
		validarLiquidacion();
		LiquidacionRepartoWeb ordenModificada = new LiquidacionRepartoWeb();
		if ("".equals(getMensajeGenerico())) {
			getOrdenDetalle().setComentariosLiquidacion(getComentarios());
			getOrdenDetalle().setIdQuienRecibe(getIdQuienRecibe());
			getOrdenDetalle().setMontoCobrar(getMontoTotal());
			getOrdenDetalle().setRemanente(getRemanente());
			getOrdenDetalle().setIdTipoDePago(getIdTipoDePago());
			getOrdenDetalle().setIdBanco(getIdBanco());
			getOrdenDetalle().setFolioPago(getFolioCobranza());
			getOrdenDetalle().setFechaPago(getFechaDePago());
			getOrdenDetalle().setMontoPagado(getMontoTotalPagos());
			getOrdenDetalle().setCashSequence(getCashSequence());
			getOrdenDetalle().setHijackedCash(getHijackedCash());
			getOrdenDetalle().setRutaSecuenciaEnrutado(obtenerRutaSecuenciaEnrutado());
			getOrdenDetalle().setIdRutaEnrutado(getIdEnrutadoRuta());
			getOrdenDetalle().setSecuenciaEnrutado(getEnrutadoSecuencia());
			try {
				getOrdenDetalle().setClaveTipoLiquidacion(Integer.valueOf(getTipoLiquidacion().trim()));
			} catch (NumberFormatException e) {
				getOrdenDetalle().setClaveTipoLiquidacion(0);
			}
			LiquidacionRepartoWeb[] listaPagos = new LiquidacionRepartoWeb[getListaPagosItems().size()];
			int total = 0;
			for (LiquidacionRepartoWeb iter : getListaPagosItems()) {
				listaPagos[total] = iter;
				total++;
			}
			getOrdenDetalle().setHuboCambiosPagos(0);
			if (getEsEntrega() != null && getEsEntrega() && getHuboCambiosPagos() != null && getHuboCambiosPagos()) {
				getOrdenDetalle().setHuboCambiosPagos(1);
			}
			LiquidacionRepartoWeb resultado = WS.liquidarOrden(
					getOrdenDetalle(),
					obtenerIdItemsSeleccionados(),
					listaPagos,
					configuracion.getIdUsuario());
			if (resultado != null) {
				ordenModificada = resultado;
				asignarOrdenModificada(ordenModificada);
				Utils utils = new Utils();
				String error = utils.enviarPrimeraSegundaLiquidacion(1);
				if (error != null && error.trim().length() > 0) {
					setMensajeGenerico("Ocurri\u00F3 un error al realizar la liquidaci\u00F3n de la orden");
					return false;
				} else {
					setMensajeGenerico("Orden liquidada");
					return true;
				}
			} else {
				setMensajeGenerico("Ocurri\u00F3 un error al realizar la liquidaci\u00F3n de la orden");
				return false;
			}
		} else {
			return false;
		}
	}

	private void validarLiquidacion() {
		setMensajeGenerico("");
		if (getMontoTotal() > 0.0D && getMontoTotalPagos() <= 0.0D) {
			setMensajeGenerico("Debe de existir al menos un pago.");
			return;
		}
		if (getIdQuienRecibe() == null || getIdQuienRecibe() == 0) {
			setMensajeGenerico("Debe seleccionar una opci\u00F3n en el campo 'Qui\u00E9n recibe'.");
			return;
		}
		List<LiquidacionRepartoWeb> listaPagos = new ArrayList<LiquidacionRepartoWeb>();
		for (LiquidacionRepartoWeb iter : getListaPagosItems()) {
			listaPagos.add(iter);
		}
		Double montoPagado = 0D;
		for (LiquidacionRepartoWeb v_iter : listaPagos) {
			if (v_iter != null) {
				montoPagado += Double.valueOf(obtenerDoubleString(getDecimalFormat().format(v_iter.getMontoPagado())));
    		}
		}
		montoPagado = Double.valueOf(obtenerDoubleString(getDecimalFormat().format(montoPagado)));
		setMensajeGenerico(validarItemsSeleccionados(true));
	}

	private void exitoAccion() {
		setMostrarDetalleItems(false);
		setMostrarPanelOrdenes(true);
		setMostrarDevolucionItems(false);
		setMostrarPanelDerecho(false);
		setMostrarPanelDetalle(false);
		setMostrarPanelQuienInformaDevolucion(false);
		setMostrarTblDetalleItems(false);
		setMostrarTblOrdenes(true);
	}

	private boolean realizarActualizacionInformacionOrden(boolean soloEnrutado) {
		setMensajeGenerico("");
		LiquidacionRepartoWeb ordenModificar = new LiquidacionRepartoWeb();
		if (getComentarios() == null || "".equals(getComentarios().trim())) {
			setComentarios("");
		}
		ordenModificar.setRutaSecuenciaEnrutado(obtenerRutaSecuenciaEnrutado());
		ordenModificar.setIdRutaEnrutado(getIdEnrutadoRuta());
		ordenModificar.setSecuenciaEnrutado(getEnrutadoSecuencia());
		ordenModificar.setComentario(getComentarios());
		ordenModificar.setRoboCajas(getRoboCajas());
		ordenModificar.setIdOrden(getOrdenDetalle().getIdOrden());
		if ("".equals(getComentarios().trim()) && (getRoboCajas() == null || getRoboCajas() == false)) {
			ordenModificar.setEstatusReversion(getEstatusReversion());
		} else {
			ordenModificar.setEstatusReversion(null);
		}
		LiquidacionRepartoWeb actualizarOrden = WS.actualizarInformacionOrden(ordenModificar, configuracion.getIdUsuario(), soloEnrutado);
		if (actualizarOrden != null) {
			ordenModificar = actualizarOrden;
			asignarOrdenModificada(ordenModificar);
			setMensajeGenerico("Informaci\u00F3n de la orden actualizada");
			return true;
		} else {
			setMensajeGenerico("Ocurri\u00F3 un error al realizar la actualizaci\u00F3n de la informaci\u00F3n de la orden");
			return false;
		}
	}

	private String obtenerRutaSecuenciaEnrutado() {
		if (obtenerRutaSecuenciaEnrutadoFinal() != null && getOrdenDetalle() != null && getOrdenDetalle().getEnrutadoRutaSecuenciaOriginal() != null) {
			if (!getOrdenDetalle().getEnrutadoRutaSecuenciaOriginal().trim().toUpperCase().equals(obtenerRutaSecuenciaEnrutadoFinal().trim().toUpperCase())) {
				return obtenerRutaSecuenciaEnrutadoFinal().trim().toUpperCase();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private boolean realizarDevolucion() {
		setEsOrdenamiento(1);
		validarDevolucion();
		LiquidacionRepartoWeb ordenModificada = new LiquidacionRepartoWeb();
		if ("".equals(getMensajeGenerico())) {
			getOrdenDevolucion().setComentariosDevolucion(getComentarios());
			getOrdenDevolucion().setRoboCajas(getRoboCajas());
			try {
				getOrdenDevolucion().setClaveTipoLiquidacion(Integer.valueOf(getTipoLiquidacion().trim()));
			} catch (NumberFormatException e) {
				getOrdenDevolucion().setClaveTipoLiquidacion(0);
			}
			if (getIdQuienInformaDevolucion() != null) {
				getOrdenDevolucion().setIdTipoInformante(getIdQuienInformaDevolucion());
			} else {
				getOrdenDevolucion().setIdTipoInformante(0);
			}
			getOrdenDevolucion().setIdRazonDevolucion(getIdRazonDevolucion());
			getOrdenDevolucion().setRutaSecuenciaEnrutado(obtenerRutaSecuenciaEnrutado());
			getOrdenDevolucion().setIdRutaEnrutado(getIdEnrutadoRuta());
			getOrdenDevolucion().setSecuenciaEnrutado(getEnrutadoSecuencia());
			
			getOrdenDevolucion().setHuboCambiosRazonDevolucion(0);
			if (getEsEntrega() != null && !getEsEntrega()
					&& getIdRazonDevolucionOriginal() != null && getIdRazonDevolucion() != null
					&& getIdRazonDevolucionOriginal() != getIdRazonDevolucion()) {
				getOrdenDevolucion().setHuboCambiosRazonDevolucion(1);
			}
			
			LiquidacionRepartoWeb devolverOrden = WS.devolverOrden(
					getOrdenDevolucion(),
					obtenerIdItemsSeleccionados(),
					configuracion.getIdUsuario());
			if (devolverOrden != null) {
				ordenModificada = devolverOrden;
				asignarOrdenModificada(ordenModificada);
				setMensajeGenerico("Orden devuelta");
				return true;
			} else {
				setMensajeGenerico("Ocurri\u00F3 un error al realizar la devoluci\u00F3n de la orden");
				return false;
			}
		} else {
			return false;
		}
	}

	private void asignarOrdenModificada(LiquidacionRepartoWeb orden) {
		int indice = 0;
		for (LiquidacionRepartoWeb iter : getListaOrdenes()) {
			long valorIter = 0L;
			long valorOrden = -1L;
			try {
				valorIter = Long.valueOf(String.valueOf(iter.getIdOrden()));
				valorOrden = Long.valueOf(String.valueOf(orden.getIdOrden()));
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			}
			if (valorIter == valorOrden) {
				break;
			}
			indice++;
		}
		getListaOrdenes().set(indice, orden);
		setOrdenDetalle(getListaOrdenes().get(indice));
	}

	private void validarDevolucion() {
		setMensajeGenerico("");
		if (getIdRazonDevolucion() == null || getIdRazonDevolucion() < 1) {
			setMensajeGenerico("Debe seleccionar una raz\u00F3n de devoluci\u00F3n");
			return;
		}
		if (getIdRazonDevolucion() != null && getIdRazonDevolucion() == 4) {
			if (getIdQuienInformaDevolucion() == null || getIdQuienInformaDevolucion() == 0) {
				setMensajeGenerico("Debe seleccionar un tipo de informante");
				return;
			}
		}
		setMensajeGenerico(validarItemsSeleccionados(false));
	}

	private String validarItemsSeleccionados(boolean esLiquidacion) {
		if (obtenerIdItemsSeleccionados().length < 1) {
			if (esLiquidacion) {
				return "Debe seleccionar al menos un ítem de la orden para poderla liquidar";
			} else {
				return "Debe seleccionar al menos un ítem de la orden para poderla devolver";
			}
		} else {
			return "";			
		}
	}

	private void ejecutarReversionOrdenes() {
		try {
			PrimeraSegundaLiquidacionControllerStub stub = new PrimeraSegundaLiquidacionControllerStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(TIMEOUT_MILISEGUNDOS);
			PrimeraSegundaLiquidacionControllerStub.EjecutarReversionOrdenes request = new PrimeraSegundaLiquidacionControllerStub.EjecutarReversionOrdenes();
			request.setP_idUsuario(configuracion.getIdUsuario());
			stub.ejecutarReversionOrdenes(request);
		} catch (AxisFault e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
//		WS.ejecutarReversionOrdenes(configuracion.getIdUsuario());
	}

	private LiquidacionRepartoWeb[] obtenerIdItemsSeleccionados() {
		int i = 0;
		for (LiquidacionRepartoWeb iter : getListaDetalleItems()) {
			if (iter.getChecked()) {
				i++;
			}
		}
		LiquidacionRepartoWeb[] items = new LiquidacionRepartoWeb[i];
		i = 0;
		for (LiquidacionRepartoWeb iter : getListaDetalleItems()) {
			if (iter.getChecked()) {
				items[i] = iter;
				i++;
			}
		}
		return items;
	}

	private Double redondearDosDecimales(Double doble) {
		return Double.valueOf(obtenerDoubleString(doble));
	}

	private boolean actualizarRouteSeqSOS() {
		try {
			PrimeraSegundaLiquidacionControllerStub stub = new PrimeraSegundaLiquidacionControllerStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(TIMEOUT_MILISEGUNDOS);
			PrimeraSegundaLiquidacionControllerStub.ActualizarRouteSeq request =
					new PrimeraSegundaLiquidacionControllerStub.ActualizarRouteSeq();
			request.setP_idUsuario(configuracion.getIdUsuario());
			PrimeraSegundaLiquidacionControllerStub.ActualizarRouteSeqResponse response = stub.actualizarRouteSeq(request);
			return (response != null && response.get_return());
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	class EnviarLiquidacionesSOSHilo1 extends Thread {
	
		public Short idLiq1 = null;

		@Override
		public void run() {
			try {
				PrimeraSegundaLiquidacionControllerStub stub = new PrimeraSegundaLiquidacionControllerStub();
				String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
				stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(TIMEOUT_MILISEGUNDOS);
				PrimeraSegundaLiquidacionControllerStub.EnviarPrimeraSegundaLiquidacion request =
						new PrimeraSegundaLiquidacionControllerStub.EnviarPrimeraSegundaLiquidacion();
				request.setP_idLiquidacion(idLiq1);
				request.setP_idUsuario(configuracion.getIdUsuario());
				@SuppressWarnings("unused")
				PrimeraSegundaLiquidacionControllerStub.EnviarPrimeraSegundaLiquidacionResponse response =
						stub.enviarPrimeraSegundaLiquidacion(request);
			} catch (AxisFault e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			} catch (RemoteException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	class EnviarLiquidacionesSOSHilo2 extends Thread {
	
		public Short idLiq2 = null;

		@Override
		public void run() {
			try {
				PrimeraSegundaLiquidacionControllerStub stub = new PrimeraSegundaLiquidacionControllerStub();
				String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
				stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(url));
				stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(TIMEOUT_MILISEGUNDOS);
				PrimeraSegundaLiquidacionControllerStub.EnviarPrimeraSegundaLiquidacion request =
						new PrimeraSegundaLiquidacionControllerStub.EnviarPrimeraSegundaLiquidacion();
				request.setP_idLiquidacion(idLiq2);
				request.setP_idUsuario(configuracion.getIdUsuario());
				@SuppressWarnings("unused")
				PrimeraSegundaLiquidacionControllerStub.EnviarPrimeraSegundaLiquidacionResponse response =
						stub.enviarPrimeraSegundaLiquidacion(request);
			} catch (AxisFault e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			} catch (RemoteException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	/* COMIENZA SECCION AUTOGENERADA DE GETTERS Y SETTERS */

	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}

	public Utils getUtils() {
		return utils;
	}

	public void setUtils(Utils utils) {
		this.utils = utils;
	}

	public String getIdCampania() {
		return idCampania;
	}

	public void setIdCampania(String idCampania) {
		this.idCampania = idCampania;
	}

	public Boolean getDisableCampania() {
		return disableCampania;
	}

	public void setDisableCampania(Boolean disableCampania) {
		this.disableCampania = disableCampania;
	}

	public List<SelectItem> getListaCampanias() {
		return listaCampanias;
	}

	public void setListaCampanias(List<SelectItem> listaCampanias) {
		this.listaCampanias = listaCampanias;
	}

	public Integer getIdZona() {
		return idZona;
	}

	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}

	public Boolean getDisableZona() {
		return disableZona;
	}

	public void setDisableZona(Boolean disableZona) {
		this.disableZona = disableZona;
	}

	public List<SelectItem> getListaZonas() {
		return listaZonas;
	}

	public void setListaZonas(List<SelectItem> listaZonas) {
		this.listaZonas = listaZonas;
	}

	public Integer getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}

	public Boolean getDisableRuta() {
		return disableRuta;
	}

	public void setDisableRuta(Boolean disableRuta) {
		this.disableRuta = disableRuta;
	}

	public List<SelectItem> getListaRutas() {
		return listaRutas;
	}

	public void setListaRutas(List<SelectItem> listaRutas) {
		this.listaRutas = listaRutas;
	}

	public String getRegistro() {
		if (registro == null) {
			return "";
		} else {
			return registro;
		}
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public Boolean getDisableRegistro() {
		return disableRegistro;
	}

	public void setDisableRegistro(Boolean disableRegistro) {
		this.disableRegistro = disableRegistro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getDisableNombre() {
		return disableNombre;
	}

	public void setDisableNombre(Boolean disableNombre) {
		this.disableNombre = disableNombre;
	}

	public Boolean getMostrarGridConsulta() {
		return mostrarGridConsulta;
	}

	public void setMostrarGridConsulta(Boolean mostrarGridConsulta) {
		this.mostrarGridConsulta = mostrarGridConsulta;
	}

	public Boolean getDisableBtnConsultar() {
		return disableBtnConsultar;
	}

	public void setDisableBtnConsultar(Boolean disableBtnConsultar) {
		this.disableBtnConsultar = disableBtnConsultar;
	}

	public List<LiquidacionRepartoWeb> getListaOrdenes() {
		return listaOrdenes;
	}

	public void setListaOrdenes(List<LiquidacionRepartoWeb> listaOrdenes) {
		this.listaOrdenes = listaOrdenes;
		if (getListaOrdenes() != null) {
			setCantidadOrdenes(getListaOrdenes().size());
		} else {
			setCantidadOrdenes(0);
		}
	}

	public Boolean getMostrarTblOrdenes() {
		return mostrarTblOrdenes;
	}

	public void setMostrarTblOrdenes(Boolean mostrarTblOrdenes) {
		this.mostrarTblOrdenes = mostrarTblOrdenes;
	}

	public LiquidacionRepartoWeb getOrdenDetalle() {
		return ordenDetalle;
	}

	public void setOrdenDetalle(LiquidacionRepartoWeb ordenDetalle) {
		this.ordenDetalle = ordenDetalle;
		this.ordenDevolucion = ordenDetalle;
		if (getOrdenDetalle() != null) {
			setDisabledEditarOrden(false);
		} else {
			setDisabledEditarOrden(true);
		}
	}

	public LiquidacionRepartoWeb getOrdenDevolucion() {
		return ordenDevolucion;
	}

	public void setOrdenDevolucion(LiquidacionRepartoWeb ordenDevolucion) {
		this.ordenDevolucion = ordenDevolucion;
	}

	public Boolean getDisabledBtnLimpiarOrdenes() {
		return disabledBtnLimpiarOrdenes;
	}

	public void setDisabledBtnLimpiarOrdenes(Boolean disabledBtnLimpiarOrdenes) {
		this.disabledBtnLimpiarOrdenes = disabledBtnLimpiarOrdenes;
	}

	public void setListaRepresentantes(
			List<LiquidacionRepartoWeb> listaRepresentantes) {
		this.listaRepresentantes = listaRepresentantes;
	}

	public List<LiquidacionRepartoWeb> getListaRepresentantes() {
		return listaRepresentantes;
	}

	public LiquidacionRepartoWeb getRepresentanteSeleccionado() {
		return representanteSeleccionado;
	}

	public void setRepresentanteSeleccionado(
			LiquidacionRepartoWeb representanteSeleccionado) {
		this.representanteSeleccionado = representanteSeleccionado;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Boolean getMostrarPanel() {
		return mostrarPanel;
	}

	public void setMostrarPanel(Boolean mostrarPanel) {
		this.mostrarPanel = mostrarPanel;
	}

	public UIForm getFrmModal() {
		return frmModal;
	}

	public void setFrmModal(UIForm frmModal) {
		this.frmModal = frmModal;
	}

	public Boolean getDisabledBtnLimpiarFormulario() {
		return disabledBtnLimpiarFormulario;
	}

	public void setDisabledBtnLimpiarFormulario(Boolean disabledBtnLimpiarFormulario) {
		this.disabledBtnLimpiarFormulario = disabledBtnLimpiarFormulario;
	}

	public Boolean getMostrarDetalleItems() {
		return mostrarDetalleItems;
	}

	public void setMostrarDetalleItems(Boolean mostrarDetalleItems) {
		this.mostrarDetalleItems = mostrarDetalleItems;
	}

	public Boolean getMostrarDevolucionItems() {
		return mostrarDevolucionItems;
	}

	public void setMostrarDevolucionItems(Boolean mostrarDevolucionItems) {
		this.mostrarDevolucionItems = mostrarDevolucionItems;
	}

	public Boolean getMostrarPanelQuienInformaDevolucion() {
		return mostrarPanelQuienInformaDevolucion;
	}

	public void setMostrarPanelQuienInformaDevolucion(
			Boolean mostrarPanelQuienInformaDevolucion) {
		this.mostrarPanelQuienInformaDevolucion = mostrarPanelQuienInformaDevolucion;
	}

	public Boolean getDisabledCbRazonesDevolucion() {
		return disabledCbRazonesDevolucion || getDeshabilitarEdicion();
	}

	public void setDisabledCbRazonesDevolucion(Boolean disabledCbRazonesDevolucion) {
		this.disabledCbRazonesDevolucion = disabledCbRazonesDevolucion;
	}

	public List<SelectItem> getListaRazonesDevolucion() {
		return listaRazonesDevolucion;
	}

	public void setListaRazonesDevolucion(List<SelectItem> listaRazonesDevolucion) {
		this.listaRazonesDevolucion = listaRazonesDevolucion;
	}

	public Boolean getDisabledBtnCancelarDevolucion() {
		return disabledBtnCancelarDevolucion;
	}

	public void setDisabledBtnCancelarDevolucion(
			Boolean disabledBtnCancelarDevolucion) {
		this.disabledBtnCancelarDevolucion = disabledBtnCancelarDevolucion;
	}

	public Boolean getDisabledBtnDevolverOrden() {
		return disabledBtnDevolverOrden;
	}

	public void setDisabledBtnDevolverOrden(Boolean disabledBtnDevolverOrden) {
		this.disabledBtnDevolverOrden = disabledBtnDevolverOrden;
	}

	public Integer getIdQuienInformaDevolucion() {
		return idQuienInformaDevolucion;
	}

	public void setIdQuienInformaDevolucion(Integer idQuienInformaDevolucion) {
		this.idQuienInformaDevolucion = idQuienInformaDevolucion;
	}

	public Boolean getDisabledQuienInformaDevolucion() {
		return disabledQuienInformaDevolucion || getDeshabilitarEdicion();
	}

	public void setDisabledQuienInformaDevolucion(
			Boolean disabledQuienInformaDevolucion) {
		this.disabledQuienInformaDevolucion = disabledQuienInformaDevolucion;
	}

	public List<SelectItem> getListaQuienInformaDevolucion() {
		return listaQuienInformaDevolucion;
	}

	public void setListaQuienInformaDevolucion(
			List<SelectItem> listaQuienInformaDevolucion) {
		this.listaQuienInformaDevolucion = listaQuienInformaDevolucion;
	}

	public Integer getIdRazonDevolucion() {
		return idRazonDevolucion;
	}

	public void setIdRazonDevolucion(Integer idRazonDevolucion) {
		this.idRazonDevolucion = idRazonDevolucion;
	}

	public List<LiquidacionRepartoWeb> getListaDetalleItems() {
		return listaDetalleItems;
	}

	public void setListaDetalleItems(List<LiquidacionRepartoWeb> listaDetalleItems) {
		this.listaDetalleItems = listaDetalleItems;
	}

	public Boolean getMostrarTblDetalleItems() {
		return mostrarTblDetalleItems;
	}

	public void setMostrarTblDetalleItems(Boolean mostrarTblDetalleItems) {
		this.mostrarTblDetalleItems = mostrarTblDetalleItems;
	}

	public Boolean getDisabledBtnAgregarPago() {
		return disabledBtnAgregarPago;
	}

	public void setDisabledBtnAgregarPago(Boolean disabledBtnAgregarPago) {
		this.disabledBtnAgregarPago = disabledBtnAgregarPago;
	}

	public List<LiquidacionRepartoWeb> getListaPagosItems() {
		return listaPagosItems;
	}

	public void setListaPagosItems(List<LiquidacionRepartoWeb> listaPagosItems) {
		this.listaPagosItems = listaPagosItems;
	}

	public LiquidacionRepartoWeb getPagoEliminacion() {
		return pagoEliminacion;
	}

	public void setPagoEliminacion(LiquidacionRepartoWeb pagoEliminacion) {
		this.pagoEliminacion = pagoEliminacion;
	}

	public Boolean getDisabledBtnEntregaDevolucion() {
		return disabledBtnEntregaDevolucion;
	}

	public void setDisabledBtnEntregaDevolucion(Boolean disabledBtnEntregaDevolucion) {
		this.disabledBtnEntregaDevolucion = disabledBtnEntregaDevolucion;
	}

	public Boolean getMostrarPanelDerecho() {
		return mostrarPanelDerecho;
	}

	public void setMostrarPanelDerecho(Boolean mostrarPanelDerecho) {
		this.mostrarPanelDerecho = mostrarPanelDerecho;
	}

	public Double getRemanente() {
		if (this.remanente < 0) {
			return 0D;
		}
		return redondearDosDecimales(this.remanente);
	}

	public void setRemanente(Double remanente) {
		if (remanente == null) {
			this.remanente = 0D;
		} else {
			this.remanente = remanente;
		}
	}

	public String getRemanenteMostrar() {
		return getDecimalFormat().format(getRemanente());
	}

	public List<SelectItem> getListaBancos() {
		return listaBancos;
	}

	public void setListaBancos(List<SelectItem> listaBancos) {
		this.listaBancos = listaBancos;
	}

	public List<SelectItem> getListaTiposDePago() {
		return listaTiposDePago;
	}

	public void setListaTiposDePago(List<SelectItem> listaTiposDePago) {
		this.listaTiposDePago = listaTiposDePago;
	}

	public Integer getIdTipoDePago() {
		return idTipoDePago;
	}

	public void setIdTipoDePago(Integer idTipoDePago) {
		this.idTipoDePago = idTipoDePago;
	}

	public Boolean getDisabledTipoDePago() {
		return disabledTipoDePago || getDeshabilitarEdicion();
	}

	public void setDisabledTipoDePago(Boolean disabledTipoDePago) {
		this.disabledTipoDePago = disabledTipoDePago;
	}

	public Boolean getDisabledBanco() {
		return disabledBanco || getDeshabilitarEdicion();
	}

	public void setDisabledBanco(Boolean disabledBanco) {
		this.disabledBanco = disabledBanco;
	}

	public String getFolioCobranza() {
		return folioCobranza;
	}

	public void setFolioCobranza(String folioCobranza) {
		this.folioCobranza = folioCobranza;
	}

	public Boolean getDisabledFolioCobranza() {
		return disabledFolioCobranza || getDeshabilitarEdicion();
	}

	public void setDisabledFolioCobranza(Boolean disabledFolioCobranza) {
		this.disabledFolioCobranza = disabledFolioCobranza;
	}

	public String getFechaDePago() {
		return fechaDePago;
	}

	public void setFechaDePago(String fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	public Boolean getDisabledFechaDePago() {
		return disabledFechaDePago || getDeshabilitarEdicion();
	}

	public void setDisabledFechaDePago(Boolean disabledFechaDePago) {
		this.disabledFechaDePago = disabledFechaDePago;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		if (monto == null) {
			this.monto = "0.00";
		} else {
			monto = obtenerDoubleString(monto);
			try {
				Double.parseDouble(monto);
			} catch (NumberFormatException e) {
				this.monto = "0.00";
			}
			this.monto =  monto;
		}
	}

	public Boolean getDisabledMonto() {
		return disabledMonto || getDeshabilitarEdicion();
	}

	public void setDisabledMonto(Boolean disabledMonto) {
		this.disabledMonto = disabledMonto;
	}

	public Double getMontoTotal() {
		if (this.montoTotal < 0) {
			return 0D;
		}
		return redondearDosDecimales(this.montoTotal);
	}

	public void setMontoTotal(Double montoTotal) {
		if (montoTotal == null) {
			this.montoTotal = 0D;
		} else {
			this.montoTotal = montoTotal;
		}
	}

	public String getMontoTotalMostrar() {
		String mostrar = "";
		mostrar =  getDecimalFormat().format(getMontoTotal());
		if (!"".equals(getLibreCobro().trim())) {
			mostrar += " " + getLibreCobro().trim();
		}
		return mostrar;
	}

	public Double getMontoTotalPagos() {
		if (this.montoTotalPagos < 0) {
			return 0D;
		}
		return redondearDosDecimales(this.montoTotalPagos);
	}

	public String getMontoTotalPagosMostrar() {
		return getDecimalFormat().format(getMontoTotalPagos());
	}

	public void setMontoTotalPagos(Double montoTotalPagos) {
		if (montoTotalPagos == null) {
			this.montoTotalPagos = 0D;
		} else {
			this.montoTotalPagos = montoTotalPagos;
		}
	}

	public Integer getIdQuienRecibe() {
		return idQuienRecibe;
	}

	public void setIdQuienRecibe(Integer idQuienRecibe) {
		this.idQuienRecibe = idQuienRecibe;
	}

	public Boolean getDisabledQuienRecibe() {
		return disabledQuienRecibe || getDeshabilitarEdicion();
	}

	public void setDisabledQuienRecibe(Boolean disabledQuienRecibe) {
		this.disabledQuienRecibe = disabledQuienRecibe;
	}

	public List<SelectItem> getListaQuienRecibe() {
		return listaQuienRecibe;
	}

	public void setListaQuienRecibe(List<SelectItem> listaQuienRecibe) {
		this.listaQuienRecibe = listaQuienRecibe;
	}

	public String getComentarios() {
		if (comentarios != null) {
			if (comentarios.length() > 500) {
				return comentarios.substring(0, 500);
			} else {
				return comentarios;
			}
		} else {
			return "";
		}
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Boolean getDisabledQuienRecibeComentarios() {
		return disabledQuienRecibeComentarios || getDeshabilitarEdicion();
	}

	public void setDisabledQuienRecibeComentarios(
			Boolean disabledQuienRecibeComentarios) {
		this.disabledQuienRecibeComentarios = disabledQuienRecibeComentarios;
	}

	public String getMensajeGenerico() {
		return mensajeGenerico;
	}

	public void setMensajeGenerico(String mensajeGenerico) {
		this.mensajeGenerico = mensajeGenerico;
	}

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public List<LiquidacionRepartoWeb> getListaPagosItemsReales() {
		return listaPagosItemsReales;
	}

	public void setListaPagosItemsReales(
			List<LiquidacionRepartoWeb> listaPagosItemsReales) {
		this.listaPagosItemsReales = listaPagosItemsReales;
	}

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	public String getCampaniaMostrar() {
		return campaniaMostrar;
	}

	public void setCampaniaMostrar(String campaniaMostrar) {
		this.campaniaMostrar = campaniaMostrar;
	}

	public String getOrdenMostrar() {
		return ordenMostrar;
	}

	public void setOrdenMostrar(String ordenMostrar) {
		this.ordenMostrar = ordenMostrar;
	}

	public String getRegistroMostrar() {
		return registroMostrar;
	}

	public void setRegistroMostrar(String registroMostrar) {
		this.registroMostrar = registroMostrar;
	}

	public String getNombreMostrar() {
		return nombreMostrar;
	}

	public void setNombreMostrar(String nombreMostrar) {
		this.nombreMostrar = nombreMostrar;
	}

	public List<LiquidacionRepartoWeb> getListaTiposDePagoObjeto() {
		return listaTiposDePagoObjeto;
	}

	public void setListaTiposDePagoObjeto(
			List<LiquidacionRepartoWeb> listaTiposDePagoObjeto) {
		this.listaTiposDePagoObjeto = listaTiposDePagoObjeto;
	}

	public List<LiquidacionRepartoWeb> getListaBancosObjeto() {
		return listaBancosObjeto;
	}

	public void setListaBancosObjeto(List<LiquidacionRepartoWeb> listaBancosObjeto) {
		this.listaBancosObjeto = listaBancosObjeto;
	}

	public Boolean getDisableOrdenSegmentada() {
		return disableOrdenSegmentada;
	}

	public void setDisableOrdenSegmentada(Boolean disableOrdenSegmentada) {
		this.disableOrdenSegmentada = disableOrdenSegmentada;
	}

	public String getIdOrdenSegmentada() {
		return idOrdenSegmentada;
	}

	public void setIdOrdenSegmentada(String idOrdenSegmentada) {
		this.idOrdenSegmentada = idOrdenSegmentada;
	}

	public LiquidacionRepartoWeb getOrdenEdicion() {
		return ordenEdicion;
	}

	public void setOrdenEdicion(LiquidacionRepartoWeb ordenEdicion) {
		this.ordenEdicion = ordenEdicion;
	}

	public Boolean getMostrarPanelOrdenes() {
		return mostrarPanelOrdenes;
	}

	public void setMostrarPanelOrdenes(Boolean mostrarPanelOrdenes) {
		this.mostrarPanelOrdenes = mostrarPanelOrdenes;
	}

	public Boolean getMostrarPanelDetalle() {
		return mostrarPanelDetalle;
	}

	public void setMostrarPanelDetalle(Boolean mostrarPanelDetalle) {
		this.mostrarPanelDetalle = mostrarPanelDetalle;
	}

	public Boolean getDeshabilitarEdicion() {
		return deshabilitarEdicion;
	}

	public void setDeshabilitarEdicion(Boolean deshabilitarEdicion) {
		this.deshabilitarEdicion = deshabilitarEdicion;
	}

	public String getTipoDeOrden() {
		return tipoDeOrden;
	}

	public void setTipoDeOrden(String tipoDeOrden) {
		this.tipoDeOrden = tipoDeOrden;
	}

	public String getValorDeOrden() {
		return valorDeOrden;
	}

	public void setValorDeOrden(String valorDeOrden) {
		this.valorDeOrden = valorDeOrden;
	}

	public Boolean getRoboCajas() {
		return roboCajas;
	}

	public void setRoboCajas(Boolean roboCajas) {
		this.roboCajas = roboCajas;
	}

	public String getCashSequence() {
		return cashSequence;
	}

	public void setCashSequence(String cashSequence) {
		this.cashSequence = cashSequence;
	}

	public Boolean getHijackedCash() {
		return hijackedCash;
	}

	public void setHijackedCash(Boolean hijackedCash) {
		this.hijackedCash = hijackedCash;
	}

	public Boolean getNoExistePagoEfectivo() {
		return noExistePagoEfectivo;
	}

	public void setNoExistePagoEfectivo(Boolean noExistePagoEfectivo) {
		this.noExistePagoEfectivo = noExistePagoEfectivo;
	}

	public String getValorOrdenamiento() {
		return valorOrdenamiento;
	}

	public void setValorOrdenamiento(String valorOrdenamiento) {
		this.valorOrdenamiento = valorOrdenamiento;
	}

	public String getValorOrdenamientoAnterior() {
		return valorOrdenamientoAnterior;
	}

	public void setValorOrdenamientoAnterior(String valorOrdenamientoAnterior) {
		this.valorOrdenamientoAnterior = valorOrdenamientoAnterior;
	}

	public String getTipoOrdenamiento() {
		return tipoOrdenamiento;
	}

	public void setTipoOrdenamiento(String tipoOrdenamiento) {
		this.tipoOrdenamiento = tipoOrdenamiento;
	}

	public Integer getCantidadOrdenes() {
		return cantidadOrdenes;
	}

	public void setCantidadOrdenes(Integer cantidadOrdenes) {
		this.cantidadOrdenes = cantidadOrdenes;
	}

	public Boolean getMostrarCashSequence() {
		return mostrarCashSequence;
	}

	public void setMostrarCashSequence(Boolean mostrarCashSequence) {
		this.mostrarCashSequence = mostrarCashSequence;
	}

	public UIForm getFrmAceptar() {
		return frmAceptar;
	}

	public void setFrmAceptar(UIForm frmAceptar) {
		this.frmAceptar = frmAceptar;
	}

	public void setEspacio(String espacio) {
	}

	public String getEspacio() {
		return " ";
	}

	public Boolean getDisabledEditarOrden() {
		return disabledEditarOrden;
	}

	public void setDisabledEditarOrden(Boolean disabledEditarOrden) {
		this.disabledEditarOrden = disabledEditarOrden;
	}

	public UIScrollableDataTable getTblOrdenes() {
		return tblOrdenes;
	}

	public void setTblOrdenes(UIScrollableDataTable tblOrdenes) {
		this.tblOrdenes = tblOrdenes;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public Long getTotalCajas() {
		return totalCajas;
	}

	public void setTotalCajas(Long totalCajas) {
		this.totalCajas = totalCajas;
	}

	public Long getTotalPUs() {
		return totalPUs;
	}

	public void setTotalPUs(Long totalPUs) {
		this.totalPUs = totalPUs;
	}

	public Boolean getDisabledEdicionPagos() {
		return disabledEdicionPagos;
	}

	public void setDisabledEdicionPagos(Boolean disabledEdicionPagos) {
		this.disabledEdicionPagos = disabledEdicionPagos;
	}

	public LiquidacionRepartoWeb getPagoEdicion() {
		return pagoEdicion;
	}

	public void setPagoEdicion(LiquidacionRepartoWeb pagoEdicion) {
		this.pagoEdicion = pagoEdicion;
	}

	public Integer getEsOrdenamiento() {
		return esOrdenamiento;
	}

	public void setEsOrdenamiento(Integer esOrdenamiento) {
		this.esOrdenamiento = esOrdenamiento;
	}

	public String getMensajeAceptarDetalle() {
		return mensajeAceptarDetalle;
	}

	public void setMensajeAceptarDetalle(String mensajeAceptarDetalle) {
		this.mensajeAceptarDetalle = mensajeAceptarDetalle;
	}

	public Integer getCantidadFilasItems() {
		return cantidadFilasItems;
	}

	public void setCantidadFilasItems(Integer cantidadFilasItems) {
		this.cantidadFilasItems = cantidadFilasItems;
	}

	public Double getMontoSobrante() {
		return montoSobrante;
	}

	public void setMontoSobrante(Double montoSobrante) {
		this.montoSobrante = montoSobrante;
		if (montoSobrante == null || Double.valueOf(obtenerDoubleString(getDecimalFormat().format(montoSobrante))) <= 0D) {
			setMontoSobranteMostrar("");
		} else {
			setMontoSobranteMostrar(getDecimalFormat().format(montoSobrante));
		}
	}

	public String getMontoSobranteMostrar() {
		return montoSobranteMostrar;
	}

	public void setMontoSobranteMostrar(String montoSobranteMostrar) {
		this.montoSobranteMostrar = montoSobranteMostrar;
	}

	public String getLibreCobro() {
		return libreCobro;
	}

	public void setLibreCobro(String libreCobro) {
		this.libreCobro = libreCobro;
	}

	public static class Comparators {
		public static Comparator<LiquidacionRepartoWeb> REGISTRO_NUMERICO = new Comparator<LiquidacionRepartoWeb>() {
	        @Override
	        public int compare(LiquidacionRepartoWeb o1, LiquidacionRepartoWeb o2) {
	            return o1.getRegistroNumerico() - o2.getRegistroNumerico();
	        }
	    };
	}

	private String obtenerDoubleString(Double numero) {
		if (numero == null) {
			return "0";
		}
		return getDecimalFormat().format(numero).replace(SIGNO_PESOS, VACIO).replace(COMA, VACIO).replace(ESPACIO, VACIO).trim();
	}

	private String obtenerDoubleString(String numero) {
		if (numero == null) {
			return "0";
		}
		return numero.trim().replace(SIGNO_PESOS, VACIO).replace(COMA, VACIO).replace(ESPACIO, VACIO).trim();
	}

	public Boolean getExistenPagos() {
		return existenPagos;
	}

	public void setExistenPagos(Boolean existenPagos) {
		this.existenPagos = existenPagos;
	}

	public String getIdRowSeleccionado() {
		return idRowSeleccionado;
	}

	public void setIdRowSeleccionado(String idRowSeleccionado) {
		this.idRowSeleccionado = idRowSeleccionado;
	}

	public String getTipoLiquidacion() {
		return tipoLiquidacion;
	}

	public void setTipoLiquidacion(String tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
	}

	public List<SelectItem> getListaTiposLiquidacion() {
		return listaTiposLiquidacion;
	}

	public void setListaTiposLiquidacion(List<SelectItem> listaTiposLiquidacion) {
		this.listaTiposLiquidacion = listaTiposLiquidacion;
	}

	public Boolean getDeshabilitarDevolucionSegundaLiquidacion() {
		return deshabilitarDevolucionSegundaLiquidacion;
	}

	public void setDeshabilitarDevolucionSegundaLiquidacion(
			Boolean deshabilitarDevolucionSegundaLiquidacion) {
		this.deshabilitarDevolucionSegundaLiquidacion = deshabilitarDevolucionSegundaLiquidacion;
	}

	public Boolean getPermitirAceptarOrden() {
		return permitirAceptarOrden;
	}

	public void setPermitirAceptarOrden(Boolean permitirAceptarOrden) {
		this.permitirAceptarOrden = permitirAceptarOrden;
	}

	public UIForm getFrmAceptarNo() {
		return frmAceptarNo;
	}

	public void setFrmAceptarNo(UIForm frmAceptarNo) {
		this.frmAceptarNo = frmAceptarNo;
	}

	private DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.US);
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');
		return new DecimalFormat("$ ###,##0.00", simbolos);
	}

	public String getDescripcionTipoLiquidacion() {
		for (SelectItem iter : getListaTiposLiquidacion()) {
			if (getTipoLiquidacion() != null && iter != null && iter.getValue() != null) {
				if (getTipoLiquidacion().trim().equals(iter.getValue().toString().trim())) {
					return iter.getLabel();
				}
			}
		}
		return "";
	}

	public Integer getEstatusReversion() {
		return estatusReversion;
	}

	public void setEstatusReversion(Integer estatusReversion) {
		this.estatusReversion = estatusReversion;
	}

	public String getIdEnrutadoRuta() {
		return idEnrutadoRuta;
	}

	public void setIdEnrutadoRuta(String idEnrutadoRuta) {
		this.idEnrutadoRuta = idEnrutadoRuta;
	}

	public List<SelectItem> getListaEnrutadoRutas() {
		return listaEnrutadoRutas;
	}

	public void setListaEnrutadoRutas(List<SelectItem> listaEnrutadoRutas) {
		this.listaEnrutadoRutas = listaEnrutadoRutas;
	}

	public String getEnrutadoSecuencia() {
		return enrutadoSecuencia;
	}

	public void setEnrutadoSecuencia(String enrutadoSecuencia) {
		this.enrutadoSecuencia = enrutadoSecuencia;
	}

	public String getEnrutadoRutaSecuenciaOriginal() {
		return enrutadoRutaSecuenciaOriginal;
	}

	public void setEnrutadoRutaSecuenciaOriginal(
			String enrutadoRutaSecuenciaOriginal) {
		this.enrutadoRutaSecuenciaOriginal = enrutadoRutaSecuenciaOriginal;
	}

	public Boolean getEsEntrega() {
		return esEntrega;
	}

	public void setEsEntrega(Boolean esEntrega) {
		this.esEntrega = esEntrega;
	}

	public Boolean getHuboCambiosPagos() {
		return huboCambiosPagos;
	}

	public void setHuboCambiosPagos(Boolean huboCambiosPagos) {
		this.huboCambiosPagos = huboCambiosPagos;
	}

	public Integer getIdRazonDevolucionOriginal() {
		return idRazonDevolucionOriginal;
	}

	public void setIdRazonDevolucionOriginal(Integer idRazonDevolucionOriginal) {
		this.idRazonDevolucionOriginal = idRazonDevolucionOriginal;
	}

}
