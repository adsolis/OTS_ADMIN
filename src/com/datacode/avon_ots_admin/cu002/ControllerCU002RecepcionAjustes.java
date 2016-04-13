/**
 * @author jorge.torner
 * @since 06/01/2012
 */
package com.datacode.avon_ots_admin.cu002;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.datacode.avon_ots_admin.reports.ConsultaDatosReportes;
import com.datacode.avon_ots_admin.reports.JasperGenerator;
import com.datacode.avon_ots_admin.reports.model.ModelExpRecepcionAjustesPremios;
import com.datacode.avon_ots_admin.reports.model.ModelExpTablaPremios;
import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.avon_ots_ws.CampaniaControllerStub.Campania;
import com.datacode.avon_ots_ws.ZonaControllerStub.Zona;
import com.datacode.avon_ots_ws.RecepcionAjustesReacoControllerStub;
import com.datacode.avon_ots_ws.RecepcionAjustesReacoControllerStub.RecepcionAjustesReaco;
import com.datacode.avon_ots_ws.RecepcionAjustesReacoControllerStub.RecepcionAjustesReacoDetalle;
import com.datacode.avon_ots_ws.RecepcionAjustesReacoControllerStub.RecepcionAjustesReacoPremio;

/**
 * Clase controlador para la pantalla del CU002.01.01-Recepción de Ajustes y
 * Premios
 * 
 * @author jorge.torner
 * @since 06/01/2012
 */
public class ControllerCU002RecepcionAjustes {
	// Obtiene el objeto Configuración con los valores cargados al inicio de
	// sesión
	Configuracion config = (Configuracion) FacesContext.getCurrentInstance()
			.getExternalContext().getSessionMap().get("configuracion");

	private UIForm frmPrincipal;
	private UIForm frmCaptura;
	private UIForm frmConsulta;
	private String mensajeError;
	private UICommand btnGuardarAjuste;
	private UICommand lnkGenerarCodigosBarras;

	private Integer idZona;
	private String gerenteZona;
	private Integer idCampania;
	private Integer idCampaniaPremio;
	private HashMap<String, String> gerentesZona;
	private HashMap<String, String> mapaCampanias;
	private HashMap<String, String> mapaZonas;
	private List<RecepcionAjustesReacoPremio> premios;
	private RecepcionAjustesReacoPremio premioSel;
	private List<RecepcionAjustesReaco> ajustes;
	private RecepcionAjustesReaco ajusteSel;
	private long idGeneracionCodigos;
	private String activarGeneracion = "0";

	private String txtCosmeticoBuenEstado;
	private String txtCosmeticoMalEstado;
	private String txtBienestarBellezaBuenEstado;
	private String txtBienestarBellezaMalEstado;
	private String txtTotalCajasEnviarBelleza;
	private String txtHogarBuenEstado;
	private String txtHogarMalEstado;
	private String txtModaBuenEstado;
	private String txtModaMalEstado;
	private String txtLenceriaBuenEstado;
	private String txtLenceriaMalEstado;
	private String txtJoyeriaBuenEstado;
	private String txtJoyeriaMalEstado;
	private String txtBienestarCasaModaBuenEstado;
	private String txtBienestarCasaModaMalEstado;
	private String txtTotalCajasEnviarCasaModa;
	private String txtCantidadPremios;
	private String txtDescripcionPremios;
	private String txtPrograma;
	private String txtTotalCajasEnviarPremios;
	private String txtSubTotalBellezaBuenEstado;
	private String txtSubTotalBellezaMalEstado;
	private String txtSubTotalCasaModaBuenEstado;
	private String txtSubTotalCasaModaMalEstado;
	private Boolean verBotonGenerarRep = new Boolean(false);
	private Boolean mostrarFormConsulta= new Boolean(false);
	private Boolean mostrarFormCaptura= new Boolean(false);
	private String formatoReporte;

	// ******************* MÉTODOS **************************//

	/**
	 * Método que baja de sesión el mapa de acciones y establece las propias del
	 * modulo en la variable mapaAccion
	 * 
	 * @author jorge.torner
	 * @since 01/02/2012
	 * @return
	 */
	public HashMap<String, Boolean> getMapaAccion() {
		HashMap<String, Boolean> mapa = new HashMap<String, Boolean>();
		HashMap<Integer, HashMap<String, Boolean>> mapaFull = (HashMap<Integer, HashMap<String, Boolean>>) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("accionesM");
		mapa = mapaFull.get(5); // IdModulo 5 = Recepción de Ajustes

		return mapa;
	}

	/**
	 * Crea una lista de tipo SelectItem de Campañas
	 * 
	 * @author jorge.torner
	 * @since 09/01/2012
	 * @return Lista Campañas
	 */
	public List<SelectItem> getCmbCampanias() {

		List<SelectItem> list = new ArrayList<SelectItem>();
		Utils utils = new Utils();
		mapaCampanias = new HashMap<String, String>();

		try {
			// Obtenemos campañas
			Campania[] campanias = utils.getCampanias("CUADMIN002_01_01",
					config.getIdLDC(), (idZona==null?0:idZona), 0);

			// Ciclo de datos
			if(campanias != null){
				// Se agregan las opciones al SelectItem de Paises
				list.add(new SelectItem(0, "Selecciona una opción"));
				for (int o = 0; o < campanias.length; o++) {
					list.add(new SelectItem(campanias[o].getIdCampania(),
							campanias[o].getCampania() + "-"
									+ campanias[o].getAnioCampania()));
					mapaCampanias.put(
							String.valueOf(campanias[o].getIdCampania()),
							campanias[o].getCampania() + "-"
									+ campanias[o].getAnioCampania());
				}
			}

		} catch (Exception ex) {
			mensajeError = Utils.ObtenerMensajeBD("General Admin", "M1", true,
					"No se pudieron cargar las Campañas", ex.getMessage(),
					config.getIdUsuario())[0];
		} finally {
			//
		}
		return list;
	}

	/**
	 * Crea una lista de tipo SelectItem de Zonas
	 * 
	 * @author jorge.torner
	 * @since 09/01/2012
	 * @return Lista tipo Zonas
	 */
	public List<SelectItem> getCmbZonas() {

		List<SelectItem> list = new ArrayList<SelectItem>();
		Utils utils = new Utils();

		try {
			// Obtenemos campañas
			Zona[] Zonas = utils
					.getZonas(config.getIdLDC(), "CUADMIN002_01_01");
			gerentesZona = new HashMap<String, String>();
			mapaZonas = new HashMap<String, String>();

			// Se agregan las opciones al SelectItem de Paises
			list.add(new SelectItem(0, "Selecciona una opción"));
			// Ciclo de datos
			for (int i = 0; i < Zonas.length; i++) {
				list.add(new SelectItem(Zonas[i].getIdZona(), Zonas[i]
						.getZona()));
				gerentesZona.put(Zonas[i].getIdZona(),
						Zonas[i].getNombreGerente());
				mapaZonas.put(Zonas[i].getIdZona(), Zonas[i].getZona());
			}

		} catch (Exception ex) {
			mensajeError = Utils.ObtenerMensajeBD("General Admin", "M2", true,
					"No se pudieron cargar las Zonas correctamente",
					ex.getMessage(), config.getIdUsuario())[0];
		} finally {
			//
		}
		return list;
	}

	public void getNombreGerente(ValueChangeEvent e) {
		String idZona = e.getNewValue().toString();
		this.idZona = Integer.parseInt(idZona); 
		this.gerenteZona = this.gerentesZona.get(idZona);
	}

	public String nuevoAjuste() {
		//this.frmCaptura.setRendered(true);
		//this.frmConsulta.setRendered(false);
		mostrarFormCaptura=true;
		mostrarFormConsulta=false;
		this.lnkGenerarCodigosBarras.setRendered(false);
		limpiarControlesCaptura();
		ajusteSel = new RecepcionAjustesReaco();
		if (getMapaAccion().get("GUARDAR") != null)
			btnGuardarAjuste.getAttributes().put("disabled", false);
		else
			btnGuardarAjuste.getAttributes().put("disabled", true);
		setActivarGeneracion("0");
		return null;
	}

	public String consultarAjustes() {
		//this.frmCaptura.setRendered(false);
		//this.frmConsulta.setRendered(true);
		mostrarFormCaptura=false;
		mostrarFormConsulta=true;
		this.lnkGenerarCodigosBarras.setRendered(false);
		llenarGridAjustes();
		return null;
	}

	private void limpiarControlesCaptura() {
		txtCosmeticoBuenEstado = "";
		txtCosmeticoMalEstado = "";
		txtBienestarBellezaBuenEstado = "";
		txtBienestarBellezaMalEstado = "";
		txtTotalCajasEnviarBelleza = "";
		txtHogarBuenEstado = "";
		txtHogarMalEstado = "";
		txtModaBuenEstado = "";
		txtModaMalEstado = "";
		txtLenceriaBuenEstado = "";
		txtLenceriaMalEstado = "";
		txtJoyeriaBuenEstado = "";
		txtJoyeriaMalEstado = "";
		txtBienestarCasaModaBuenEstado = "";
		txtBienestarCasaModaMalEstado = "";
		txtTotalCajasEnviarCasaModa = "";
		txtCantidadPremios = "";
		txtDescripcionPremios = "";
		txtPrograma = "";
		idCampaniaPremio = 0;
		txtTotalCajasEnviarPremios = "";
		txtSubTotalBellezaBuenEstado = "0";
		txtSubTotalBellezaMalEstado = "0";
		txtSubTotalCasaModaBuenEstado = "0";
		txtSubTotalCasaModaMalEstado = "0";
		premios = null;
		mensajeError = "";
	}

	public String agregarPremio() {
		// Obtenemos el id del último registro para ponerle el id al nuevo
		long nuevoId = 1;
		if (premios != null && premios.size() != 0)
			nuevoId = premios.get(premios.size() - 1)
					.getIdRecepcionAjustesReacoPremio() + 1;
		else
			premios = new ArrayList<RecepcionAjustesReacoPremio>();

		RecepcionAjustesReacoPremio nPremio = new RecepcionAjustesReacoPremio();
		nPremio.setIdRecepcionAjustesReacoPremio(nuevoId);
		nPremio.setCantidad(Integer.parseInt(this.txtCantidadPremios));
		nPremio.setDescripcion(this.txtDescripcionPremios);
		nPremio.setPrograma(this.txtPrograma);
		nPremio.setIdCampania(this.idCampaniaPremio);
		nPremio.setClaveCampania(this.mapaCampanias.get(String
				.valueOf(this.idCampaniaPremio)));
		premios.add(nPremio);
		return "";
	}

	// public String modificarPremio(){
	// this.txtCantidadPremios = String.valueOf(premioSel.getCantidad());
	// this.txtDescripcionPremios = premioSel.getDescripcion();
	// this.txtPrograma = premioSel.getPrograma();
	// this.idCampaniaPremio = premioSel.getIdCampania();
	// return "";
	// }
	public String eliminarPremio() {
		premios.remove(premioSel);
		// for(RecepcionAjustesReacoPremio prem : premios){
		// if(prem.getIdRecepcionAjustesReacoPremio() ==
		// premioSel.getIdRecepcionAjustesReacoPremio()){
		// premios.remove(prem);
		// break;
		// }
		// }
		return "";
	}

	public String guardarAjuste() {
		ajusteSel.setIdCampania(idCampania);
		ajusteSel.setIdZona(idZona);
		ajusteSel.setTotalCajasBelleza(Short
				.parseShort(txtTotalCajasEnviarBelleza));
		ajusteSel.setTotalCajasCasaModa(Short
				.parseShort(txtTotalCajasEnviarCasaModa));
		ajusteSel.setTotalCajasPremios(Short
				.parseShort(txtTotalCajasEnviarPremios));

		String[] campaniaAnio = mapaCampanias.get(String.valueOf(idCampania))
				.split("-");
		if (campaniaAnio.length == 2) {
			String prefijo = Utils.obtenerPrefijoCodigosBarras("ADJ",
					campaniaAnio[0], campaniaAnio[1],
					mapaZonas.get(String.valueOf(idZona)));
			ajusteSel.setPrefijoCodigoBarras(prefijo);
		}

		// Agregamos los Detalles
		List<RecepcionAjustesReacoDetalle> detalles = new ArrayList<RecepcionAjustesReacoDetalle>();

		// Cosmetico
		RecepcionAjustesReacoDetalle det = new RecepcionAjustesReacoDetalle();
		det.setIdSubcategoriaProducto((short) 1);
		det.setCantidadBuenEstado(Integer.parseInt(txtCosmeticoBuenEstado));
		det.setCantidadMalEstado(Integer.parseInt(txtCosmeticoMalEstado));
		detalles.add(det);

		// Bienestar Belleza
		det = new RecepcionAjustesReacoDetalle();
		det.setIdSubcategoriaProducto((short) 2);
		det.setCantidadBuenEstado(Integer
				.parseInt(txtBienestarBellezaBuenEstado));
		det.setCantidadMalEstado(Integer.parseInt(txtBienestarBellezaMalEstado));
		detalles.add(det);

		// Hogar
		det = new RecepcionAjustesReacoDetalle();
		det.setIdSubcategoriaProducto((short) 3);
		det.setCantidadBuenEstado(Integer.parseInt(txtHogarBuenEstado));
		det.setCantidadMalEstado(Integer.parseInt(txtHogarMalEstado));
		detalles.add(det);

		// Moda
		det = new RecepcionAjustesReacoDetalle();
		det.setIdSubcategoriaProducto((short) 4);
		det.setCantidadBuenEstado(Integer.parseInt(txtModaBuenEstado));
		det.setCantidadMalEstado(Integer.parseInt(txtModaMalEstado));
		detalles.add(det);

		// Lencería
		det = new RecepcionAjustesReacoDetalle();
		det.setIdSubcategoriaProducto((short) 5);
		det.setCantidadBuenEstado(Integer.parseInt(txtLenceriaBuenEstado));
		det.setCantidadMalEstado(Integer.parseInt(txtLenceriaMalEstado));
		detalles.add(det);

		// Joyería
		det = new RecepcionAjustesReacoDetalle();
		det.setIdSubcategoriaProducto((short) 6);
		det.setCantidadBuenEstado(Integer.parseInt(txtJoyeriaBuenEstado));
		det.setCantidadMalEstado(Integer.parseInt(txtJoyeriaMalEstado));
		detalles.add(det);

		// Bienestar CasaModa
		det = new RecepcionAjustesReacoDetalle();
		det.setIdSubcategoriaProducto((short) 7);
		det.setCantidadBuenEstado(Integer
				.parseInt(txtBienestarCasaModaBuenEstado));
		det.setCantidadMalEstado(Integer
				.parseInt(txtBienestarCasaModaMalEstado));
		detalles.add(det);

		ajusteSel.setDetalles(detalles
				.toArray(new RecepcionAjustesReacoDetalle[0]));

		// Agregamos los Premios
		if (premios != null)
			ajusteSel.setPremios(premios
					.toArray(new RecepcionAjustesReacoPremio[0]));
		else
			ajusteSel.setPremios(new RecepcionAjustesReacoPremio[0]);

		// Definimos operación
		String operacion;
		if (ajusteSel.getIdRecepcionAjustesReaco() == 0)
			operacion = "INSERCION";
		else
			operacion = "MODIFICACION";

		// Preparamos para invocar WebService
		try {
			RecepcionAjustesReacoControllerStub stub = new RecepcionAjustesReacoControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			RecepcionAjustesReacoControllerStub.GuardarRecepcionAjuste request = new RecepcionAjustesReacoControllerStub.GuardarRecepcionAjuste();

			// parámetros
			request.setP_tipo(operacion);
			request.setRecepAjuste(ajusteSel);
			request.setP_idUsuario(config.getIdUsuario());

			// invocamos
			RecepcionAjustesReacoControllerStub.GuardarRecepcionAjusteResponse response = stub
					.guardarRecepcionAjuste(request);

			// obtenemos respuesta
			String respuesta = response.get_return();
			if (!Utils.esEntero(respuesta)) {
				mensajeError = respuesta;
			} else {
				setIdGeneracionCodigos(Long.parseLong(respuesta));
				setActivarGeneracion("1");
				mensajeError = "Ajuste Guardado";
				btnGuardarAjuste.getAttributes().put("disabled", true);
				this.lnkGenerarCodigosBarras.setRendered(true);
			}

		} catch (RemoteException ex) {
			mensajeError = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M3",
					true, "No se pudo guardar el Ajuste", ex.getMessage(),
					config.getIdUsuario())[0];
		} catch (Exception ex) {
			mensajeError = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M3",
					true, "No se pudo guardar el Ajuste", ex.getMessage(),
					config.getIdUsuario())[0];
		}

		return "";
	}

	public String cancelarAjuste() {
		//this.frmCaptura.setRendered(false);
		//this.frmConsulta.setRendered(false);
		mostrarFormCaptura=false;
		mostrarFormConsulta=false;
		this.lnkGenerarCodigosBarras.setRendered(false);
		mensajeError = "";
		return "";
	}

	public String modificarAjuste() {
		//this.frmCaptura.setRendered(true);
		//this.frmConsulta.setRendered(false);
		mostrarFormCaptura=true;
		mostrarFormConsulta=false;
		mensajeError = "";
		obtenerAjuste();
		if (getMapaAccion().get("GUARDAR") != null)
			btnGuardarAjuste.getAttributes().put("disabled", false);
		else
			btnGuardarAjuste.getAttributes().put("disabled", true);
		setActivarGeneracion("0");
		return "";
	}

	public String eliminarAjuste() {
		try {
			RecepcionAjustesReacoControllerStub stub = new RecepcionAjustesReacoControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			RecepcionAjustesReacoControllerStub.EliminarRecepcionAjuste request = new RecepcionAjustesReacoControllerStub.EliminarRecepcionAjuste();

			// parámetros
			request.setP_idRecepcionAjustesReaco(ajusteSel
					.getIdRecepcionAjustesReaco());
			request.setP_idUsuario(config.getIdUsuario());

			// invocamos
			RecepcionAjustesReacoControllerStub.EliminarRecepcionAjusteResponse response = stub
					.eliminarRecepcionAjuste(request);

			// obtenemos respuesta
			String respuesta = response.get_return();
			if (!respuesta.equals("")) {
				mensajeError = respuesta;
			} else
				mensajeError = "";

		} catch (RemoteException ex) {
			mensajeError = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M4",
					true, "No se pudo eliminar el Ajuste", ex.getMessage(),
					config.getIdUsuario())[0];
		} catch (Exception ex) {
			mensajeError = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M4",
					true, "No se pudo eliminar el Ajuste", ex.getMessage(),
					config.getIdUsuario())[0];
		}

		ajustes.remove(ajusteSel);
		return "";
	}

	private void llenarGridAjustes() {
		mensajeError = "";
		ajustes = null;
		try {
			RecepcionAjustesReacoControllerStub stub = new RecepcionAjustesReacoControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			RecepcionAjustesReacoControllerStub.ObtenerListaRecepcionAjustes request = new RecepcionAjustesReacoControllerStub.ObtenerListaRecepcionAjustes();

			// parámetros
			request.setP_idCampania(idCampania);
			request.setP_idUsuario(config.getIdUsuario());
			request.setP_idZona(idZona);

			// invocamos
			RecepcionAjustesReacoControllerStub.ObtenerListaRecepcionAjustesResponse response = stub
					.obtenerListaRecepcionAjustes(request);

			// obtenemos respuesta
			RecepcionAjustesReaco[] ajustesArr = response.get_return();
			if (ajustesArr != null && ajustesArr.length > 0) {
				ajustes = new ArrayList<RecepcionAjustesReaco>();
				for (RecepcionAjustesReaco ajuste : ajustesArr) {
					ajustes.add(ajuste);
				}
			}

		} catch (RemoteException ex) {
			mensajeError = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M1",
					true, "No se pudieron consultar los Ajustes",
					ex.getMessage(), config.getIdUsuario())[0];
		} catch (Exception ex) {
			mensajeError = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M1",
					true, "No se pudieron consultar los Ajustes",
					ex.getMessage(), config.getIdUsuario())[0];
		}
	}

	private void obtenerAjuste() {
		try {
			RecepcionAjustesReacoControllerStub stub = new RecepcionAjustesReacoControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			RecepcionAjustesReacoControllerStub.ObtenerRecepcionAjuste request = new RecepcionAjustesReacoControllerStub.ObtenerRecepcionAjuste();

			// parámetros
			request.setP_idRecepcionAjustesReaco(ajusteSel
					.getIdRecepcionAjustesReaco());
			request.setP_idUsuario(config.getIdUsuario());

			// invocamos
			RecepcionAjustesReacoControllerStub.ObtenerRecepcionAjusteResponse response = stub
					.obtenerRecepcionAjuste(request);

			// obtenemos respuesta
			ajusteSel = response.get_return();

			// Asignamos valores para controles de la forma
			setIdGeneracionCodigos(ajusteSel.getIdRecepcionAjustesReaco());
			idCampania = ajusteSel.getIdCampania();
			idZona = ajusteSel.getIdZona();
			this.gerenteZona = this.gerentesZona.get(String.valueOf(idZona));
			txtTotalCajasEnviarBelleza = String.valueOf(ajusteSel
					.getTotalCajasBelleza());
			txtTotalCajasEnviarCasaModa = String.valueOf(ajusteSel
					.getTotalCajasCasaModa());
			txtTotalCajasEnviarPremios = String.valueOf(ajusteSel
					.getTotalCajasPremios());

			// Detalles
			short subTotalBellezaBuenEstado = 0;
			short subTotalBellezaMalEstado = 0;
			short subTotalCasaModaBuenEstado = 0;
			short subTotalCasaModaMalEstado = 0;
			for (RecepcionAjustesReacoDetalle detalle : ajusteSel.getDetalles()) {
				if (detalle.getIdSubcategoriaProducto() == 1) { // Cosmetico
					txtCosmeticoBuenEstado = String.valueOf(detalle
							.getCantidadBuenEstado());
					txtCosmeticoMalEstado = String.valueOf(detalle
							.getCantidadMalEstado());
					subTotalBellezaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalBellezaMalEstado += detalle.getCantidadMalEstado();
				}
				if (detalle.getIdSubcategoriaProducto() == 2) { // Bienestar
																// Belleza
					txtBienestarBellezaBuenEstado = String.valueOf(detalle
							.getCantidadBuenEstado());
					txtBienestarBellezaMalEstado = String.valueOf(detalle
							.getCantidadMalEstado());
					subTotalBellezaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalBellezaMalEstado += detalle.getCantidadMalEstado();
				}
				if (detalle.getIdSubcategoriaProducto() == 3) { // Hogar
					txtHogarBuenEstado = String.valueOf(detalle
							.getCantidadBuenEstado());
					txtHogarMalEstado = String.valueOf(detalle
							.getCantidadMalEstado());
					subTotalCasaModaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalCasaModaMalEstado += detalle.getCantidadMalEstado();
				}
				if (detalle.getIdSubcategoriaProducto() == 4) { // Moda
					txtModaBuenEstado = String.valueOf(detalle
							.getCantidadBuenEstado());
					txtModaMalEstado = String.valueOf(detalle
							.getCantidadMalEstado());
					subTotalCasaModaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalCasaModaMalEstado += detalle.getCantidadMalEstado();
				}
				if (detalle.getIdSubcategoriaProducto() == 5) { // Lencería
					txtLenceriaBuenEstado = String.valueOf(detalle
							.getCantidadBuenEstado());
					txtLenceriaMalEstado = String.valueOf(detalle
							.getCantidadMalEstado());
					subTotalCasaModaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalCasaModaMalEstado += detalle.getCantidadMalEstado();
				}
				if (detalle.getIdSubcategoriaProducto() == 6) { // Joyería
					txtJoyeriaBuenEstado = String.valueOf(detalle
							.getCantidadBuenEstado());
					txtJoyeriaMalEstado = String.valueOf(detalle
							.getCantidadMalEstado());
					subTotalCasaModaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalCasaModaMalEstado += detalle.getCantidadMalEstado();
				}
				if (detalle.getIdSubcategoriaProducto() == 7) { // Bienestar
																// CasaModa
					txtBienestarCasaModaBuenEstado = String.valueOf(detalle
							.getCantidadBuenEstado());
					txtBienestarCasaModaMalEstado = String.valueOf(detalle
							.getCantidadMalEstado());
					subTotalCasaModaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalCasaModaMalEstado += detalle.getCantidadMalEstado();
				}
			}
			txtSubTotalBellezaBuenEstado = String
					.valueOf(subTotalBellezaBuenEstado);
			txtSubTotalBellezaMalEstado = String
					.valueOf(subTotalBellezaMalEstado);
			txtSubTotalCasaModaBuenEstado = String
					.valueOf(subTotalCasaModaBuenEstado);
			txtSubTotalCasaModaMalEstado = String
					.valueOf(subTotalCasaModaMalEstado);

			// Premios
			if (ajusteSel.getPremios() != null) {
				premios = new ArrayList<RecepcionAjustesReacoPremio>();
				RecepcionAjustesReacoPremio[] premiosArr = ajusteSel
						.getPremios().clone();
				for (RecepcionAjustesReacoPremio prem : premiosArr) {
					premios.add(prem);
				}
			}
			// premios = Arrays.asList(premiosArr); //No es recomendable usar el
			// asList si se van a eliminar elementos después

			this.lnkGenerarCodigosBarras.setRendered(true);

		} catch (RemoteException ex) {
			mensajeError = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M2",
					true, "No se pudo consultar el Ajuste", ex.getMessage(),
					config.getIdUsuario())[0];
		} catch (Exception ex) {
			mensajeError = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M2",
					true, "No se pudo consultar el Ajuste", ex.getMessage(),
					config.getIdUsuario())[0];
		}
	}

	public String exportarAjuste() {
		try {
			RecepcionAjustesReacoControllerStub stub = new RecepcionAjustesReacoControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			RecepcionAjustesReacoControllerStub.ObtenerRecepcionAjuste request = new RecepcionAjustesReacoControllerStub.ObtenerRecepcionAjuste();

			// parámetros
			request.setP_idRecepcionAjustesReaco(ajusteSel
					.getIdRecepcionAjustesReaco());
			request.setP_idUsuario(config.getIdUsuario());

			// invocamos
			RecepcionAjustesReacoControllerStub.ObtenerRecepcionAjusteResponse response = stub
					.obtenerRecepcionAjuste(request);

			// obtenemos respuesta
			RecepcionAjustesReaco ajusteExp = response.get_return();

			// llenamos el reporte 
			List<ModelExpRecepcionAjustesPremios>reportes= new ArrayList<ModelExpRecepcionAjustesPremios>();
			ModelExpRecepcionAjustesPremios reporte= new ModelExpRecepcionAjustesPremios();
			reporte.setCampania(ajusteExp.getClaveCampania());
			reporte.setZona(ajusteExp.getZona());
			reporte.setTotalCajasBe(ajusteExp.getTotalCajasBelleza());
			reporte.setTotalCajasCM(ajusteExp.getTotalCajasCasaModa());
			reporte.setTotalCajasPr(ajusteExp.getTotalCajasPremios());
			

			// Detalles
			short subTotalBellezaBuenEstado = 0;
			short subTotalBellezaMalEstado = 0;
			short subTotalCasaModaBuenEstado = 0;
			short subTotalCasaModaMalEstado = 0;
			for (RecepcionAjustesReacoDetalle detalle : ajusteSel.getDetalles()) {
				if (detalle.getIdSubcategoriaProducto() == 1) { // Cosmetico
					reporte.setCosmeticoBEBE(detalle.getCantidadBuenEstado());
					reporte.setCosmeticoBEME(detalle.getCantidadMalEstado());
					subTotalBellezaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalBellezaMalEstado += detalle.getCantidadMalEstado();
				}
				if (detalle.getIdSubcategoriaProducto() == 2) { // Bienestar
																// Belleza
					reporte.setBienestarBEBE(detalle.getCantidadBuenEstado());
					reporte.setBienestarBEME(detalle.getCantidadMalEstado());
					subTotalBellezaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalBellezaMalEstado += detalle.getCantidadMalEstado();
				}
				if (detalle.getIdSubcategoriaProducto() == 3) { // Hogar
					reporte.setHogarCMBE(detalle.getCantidadBuenEstado());
					reporte.setHogarCMME(detalle.getCantidadMalEstado());
					subTotalCasaModaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalCasaModaMalEstado += detalle.getCantidadMalEstado();
				}
				if (detalle.getIdSubcategoriaProducto() == 4) { // Moda
					reporte.setModaCMBE(detalle.getCantidadBuenEstado());
					reporte.setModaCMME(detalle.getCantidadMalEstado());
					subTotalCasaModaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalCasaModaMalEstado += detalle.getCantidadMalEstado();
				}
				if (detalle.getIdSubcategoriaProducto() == 5) { // Lencería
					reporte.setLenceriaCMBE(detalle.getCantidadBuenEstado());
					reporte.setLenceriaCMME(detalle.getCantidadMalEstado());
					subTotalCasaModaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalCasaModaMalEstado += detalle.getCantidadMalEstado();
				}
				if (detalle.getIdSubcategoriaProducto() == 6) { // Joyería
					reporte.setJoyeriaCMBE(detalle.getCantidadBuenEstado());
					reporte.setJoyeriaCMME(detalle.getCantidadMalEstado());
					subTotalCasaModaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalCasaModaMalEstado += detalle.getCantidadMalEstado();
				}
				if (detalle.getIdSubcategoriaProducto() == 7) { // Bienestar
																// CasaModa
					reporte.setBienestarCMBE(detalle.getCantidadBuenEstado());
					reporte.setBienestarCMME(detalle.getCantidadMalEstado());
					subTotalCasaModaBuenEstado += detalle
							.getCantidadBuenEstado();
					subTotalCasaModaMalEstado += detalle.getCantidadMalEstado();
				}
			}
			reporte.setSubtotalBEBE(subTotalBellezaBuenEstado);
			reporte.setSubtotalBEME(subTotalBellezaMalEstado);
			reporte.setSubtotalCMBE(subTotalCasaModaBuenEstado);
			reporte.setSubtotalCMME(subTotalCasaModaMalEstado);

			// Premios
			List<ModelExpTablaPremios> premiosA = new ArrayList<ModelExpTablaPremios>();
			if (ajusteExp.getPremios() != null) {
				
				
				for (RecepcionAjustesReacoPremio prem : ajusteExp.getPremios()) {
					ModelExpTablaPremios p = new ModelExpTablaPremios();
					p.setAcciones("");
					p.setCampania(prem.getClaveCampania());
					p.setCantidad(prem.getCantidad());
					p.setDescripcion(prem.getDescripcion());
					p.setPrograma(prem.getPrograma());
					premiosA.add(p);
				}
			}
			reporte.setListaPremios(premiosA);
			
			reportes.add(reporte);
			
			JasperGenerator generador= new JasperGenerator();
			generador.generaExpRecepcionAjustes(reportes, formatoReporte);

		} catch (RemoteException d) {
			mensajeError = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M2",
					true, "No se pudo consultar el Ajuste", d.getMessage(),
					config.getIdUsuario())[0];
		} catch (Exception d) {
			mensajeError = Utils.ObtenerMensajeBD("CUADMIN002.01.01", "M2",
					true, "No se pudo consultar el Ajuste", d.getMessage(),
					config.getIdUsuario())[0];
		}
		return "";
	}

	public String generarCodigosBarras() {
		// ControllerCU002ImprimirCodigosBarras imprimir = new
		// ControllerCU002ImprimirCodigosBarras();
		// imprimir.setTipoCodigos("AJUSTES");
		// imprimir.setIdTablaCodigos(idGeneracionCodigos);
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().containsKey("tipoGeneracionCodigos"))
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().remove("tipoGeneracionCodigos");
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().containsKey("idGeneracionCodigos"))
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().remove("idGeneracionCodigos");

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("tipoGeneracionCodigos", "AJUSTES");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("idGeneracionCodigos", idGeneracionCodigos);
		return "generar";
	}

	// Seccion de getters y setters

	
	
	public Boolean getVerBotonGenerarRep() {
		return verBotonGenerarRep;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public Boolean getMostrarFormConsulta() {
		return mostrarFormConsulta;
	}

	public void setMostrarFormConsulta(Boolean mostrarFormConsulta) {
		this.mostrarFormConsulta = mostrarFormConsulta;
	}

	public Boolean getMostrarFormCaptura() {
		return mostrarFormCaptura;
	}

	public void setMostrarFormCaptura(Boolean mostrarFormCaptura) {
		this.mostrarFormCaptura = mostrarFormCaptura;
	}

	public UIForm getFrmPrincipal() {
		return frmPrincipal;
	}

	public void setFrmPrincipal(UIForm frmPrincipal) {
		this.frmPrincipal = frmPrincipal;
	}

	public UIForm getFrmCaptura() {
		return frmCaptura;
	}

	public void setFrmCaptura(UIForm frmCaptura) {
		this.frmCaptura = frmCaptura;
	}

	public UIForm getFrmConsulta() {
		return frmConsulta;
	}

	public void setFrmConsulta(UIForm frmConsulta) {
		this.frmConsulta = frmConsulta;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public Integer getIdZona() {
		return idZona;
	}

	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}

	public Integer getIdCampania() {
		return idCampania;
	}

	public void setIdCampania(Integer idCampania) {
		this.idCampania = idCampania;
	}

	public Integer getIdCampaniaPremio() {
		return idCampaniaPremio;
	}

	public void setIdCampaniaPremio(Integer idCampaniaPremio) {
		this.idCampaniaPremio = idCampaniaPremio;
	}

	public List<RecepcionAjustesReacoPremio> getPremios() {
		return premios;
	}

	public void setPremios(List<RecepcionAjustesReacoPremio> premios) {
		this.premios = premios;
	}

	public RecepcionAjustesReacoPremio getPremioSel() {
		return premioSel;
	}

	public void setPremioSel(RecepcionAjustesReacoPremio premioSel) {
		this.premioSel = premioSel;
	}

	public List<RecepcionAjustesReaco> getAjustes() {
		return ajustes;
	}

	public void setAjustes(List<RecepcionAjustesReaco> ajustes) {
		this.ajustes = ajustes;
	}

	public RecepcionAjustesReaco getAjusteSel() {
		return ajusteSel;
	}

	public void setAjusteSel(RecepcionAjustesReaco ajusteSel) {
		this.ajusteSel = ajusteSel;
	}

	public String getGerenteZona() {
		return gerenteZona;
	}

	public void setGerenteZona(String gerenteZona) {
		this.gerenteZona = gerenteZona;
	}

	public String getTxtCosmeticoBuenEstado() {
		return txtCosmeticoBuenEstado;
	}

	public void setTxtCosmeticoBuenEstado(String txtCosmeticoBuenEstado) {
		this.txtCosmeticoBuenEstado = txtCosmeticoBuenEstado;
	}

	public String getTxtCosmeticoMalEstado() {
		return txtCosmeticoMalEstado;
	}

	public void setTxtCosmeticoMalEstado(String txtCosmeticoMalEstado) {
		this.txtCosmeticoMalEstado = txtCosmeticoMalEstado;
	}

	public String getTxtBienestarBellezaBuenEstado() {
		return txtBienestarBellezaBuenEstado;
	}

	public void setTxtBienestarBellezaBuenEstado(
			String txtBienestarBellezaBuenEstado) {
		this.txtBienestarBellezaBuenEstado = txtBienestarBellezaBuenEstado;
	}

	public String getTxtBienestarBellezaMalEstado() {
		return txtBienestarBellezaMalEstado;
	}

	public void setTxtBienestarBellezaMalEstado(
			String txtBienestarBellezaMalEstado) {
		this.txtBienestarBellezaMalEstado = txtBienestarBellezaMalEstado;
	}

	public String getTxtTotalCajasEnviarBelleza() {
		return txtTotalCajasEnviarBelleza;
	}

	public void setTxtTotalCajasEnviarBelleza(String txtTotalCajasEnviarBelleza) {
		this.txtTotalCajasEnviarBelleza = txtTotalCajasEnviarBelleza;
	}

	public String getTxtHogarBuenEstado() {
		return txtHogarBuenEstado;
	}

	public void setTxtHogarBuenEstado(String txtHogarBuenEstado) {
		this.txtHogarBuenEstado = txtHogarBuenEstado;
	}

	public String getTxtHogarMalEstado() {
		return txtHogarMalEstado;
	}

	public void setTxtHogarMalEstado(String txtHogarMalEstado) {
		this.txtHogarMalEstado = txtHogarMalEstado;
	}

	public String getTxtModaBuenEstado() {
		return txtModaBuenEstado;
	}

	public void setTxtModaBuenEstado(String txtModaBuenEstado) {
		this.txtModaBuenEstado = txtModaBuenEstado;
	}

	public String getTxtModaMalEstado() {
		return txtModaMalEstado;
	}

	public void setTxtModaMalEstado(String txtModaMalEstado) {
		this.txtModaMalEstado = txtModaMalEstado;
	}

	public String getTxtLenceriaBuenEstado() {
		return txtLenceriaBuenEstado;
	}

	public void setTxtLenceriaBuenEstado(String txtLenceriaBuenEstado) {
		this.txtLenceriaBuenEstado = txtLenceriaBuenEstado;
	}

	public String getTxtLenceriaMalEstado() {
		return txtLenceriaMalEstado;
	}

	public void setTxtLenceriaMalEstado(String txtLenceriaMalEstado) {
		this.txtLenceriaMalEstado = txtLenceriaMalEstado;
	}

	public String getTxtJoyeriaBuenEstado() {
		return txtJoyeriaBuenEstado;
	}

	public void setTxtJoyeriaBuenEstado(String txtJoyeriaBuenEstado) {
		this.txtJoyeriaBuenEstado = txtJoyeriaBuenEstado;
	}

	public String getTxtJoyeriaMalEstado() {
		return txtJoyeriaMalEstado;
	}

	public void setTxtJoyeriaMalEstado(String txtJoyeriaMalEstado) {
		this.txtJoyeriaMalEstado = txtJoyeriaMalEstado;
	}

	public String getTxtBienestarCasaModaBuenEstado() {
		return txtBienestarCasaModaBuenEstado;
	}

	public void setTxtBienestarCasaModaBuenEstado(
			String txtBienestarCasaModaBuenEstado) {
		this.txtBienestarCasaModaBuenEstado = txtBienestarCasaModaBuenEstado;
	}

	public String getTxtBienestarCasaModaMalEstado() {
		return txtBienestarCasaModaMalEstado;
	}

	public void setTxtBienestarCasaModaMalEstado(
			String txtBienestarCasaModaMalEstado) {
		this.txtBienestarCasaModaMalEstado = txtBienestarCasaModaMalEstado;
	}

	public String getTxtTotalCajasEnviarCasaModa() {
		return txtTotalCajasEnviarCasaModa;
	}

	public void setTxtTotalCajasEnviarCasaModa(
			String txtTotalCajasEnviarCasaModa) {
		this.txtTotalCajasEnviarCasaModa = txtTotalCajasEnviarCasaModa;
	}

	public String getTxtCantidadPremios() {
		return txtCantidadPremios;
	}

	public void setTxtCantidadPremios(String txtCantidadPremios) {
		this.txtCantidadPremios = txtCantidadPremios;
	}

	public String getTxtDescripcionPremios() {
		return txtDescripcionPremios;
	}

	public void setTxtDescripcionPremios(String txtDescripcionPremios) {
		this.txtDescripcionPremios = txtDescripcionPremios;
	}

	public String getTxtPrograma() {
		return txtPrograma;
	}

	public void setTxtPrograma(String txtPrograma) {
		this.txtPrograma = txtPrograma;
	}

	public String getTxtTotalCajasEnviarPremios() {
		return txtTotalCajasEnviarPremios;
	}

	public void setTxtTotalCajasEnviarPremios(String txtTotalCajasEnviarPremios) {
		this.txtTotalCajasEnviarPremios = txtTotalCajasEnviarPremios;
	}

	public String getTxtSubTotalBellezaBuenEstado() {
		return txtSubTotalBellezaBuenEstado;
	}

	public void setTxtSubTotalBellezaBuenEstado(
			String txtSubTotalBellezaBuenEstado) {
		this.txtSubTotalBellezaBuenEstado = txtSubTotalBellezaBuenEstado;
	}

	public String getTxtSubTotalBellezaMalEstado() {
		return txtSubTotalBellezaMalEstado;
	}

	public void setTxtSubTotalBellezaMalEstado(
			String txtSubTotalBellezaMalEstado) {
		this.txtSubTotalBellezaMalEstado = txtSubTotalBellezaMalEstado;
	}

	public String getTxtSubTotalCasaModaBuenEstado() {
		return txtSubTotalCasaModaBuenEstado;
	}

	public void setTxtSubTotalCasaModaBuenEstado(
			String txtSubTotalCasaModaBuenEstado) {
		this.txtSubTotalCasaModaBuenEstado = txtSubTotalCasaModaBuenEstado;
	}

	public String getTxtSubTotalCasaModaMalEstado() {
		return txtSubTotalCasaModaMalEstado;
	}

	public void setTxtSubTotalCasaModaMalEstado(
			String txtSubTotalCasaModaMalEstado) {
		this.txtSubTotalCasaModaMalEstado = txtSubTotalCasaModaMalEstado;
	}

	public UICommand getBtnGuardarAjuste() {
		return btnGuardarAjuste;
	}

	public void setBtnGuardarAjuste(UICommand btnGuardarAjuste) {
		this.btnGuardarAjuste = btnGuardarAjuste;
	}

	public UICommand getLnkGenerarCodigosBarras() {
		return lnkGenerarCodigosBarras;
	}

	public void setLnkGenerarCodigosBarras(UICommand btnGenerarCodigosBarras) {
		this.lnkGenerarCodigosBarras = btnGenerarCodigosBarras;
	}

	public long getIdGeneracionCodigos() {
		return idGeneracionCodigos;
	}

	public void setIdGeneracionCodigos(long idGeneracionCodigos) {
		this.idGeneracionCodigos = idGeneracionCodigos;
	}

	public String getActivarGeneracion() {
		return activarGeneracion;
	}

	public void setActivarGeneracion(String activarGeneracion) {
		this.activarGeneracion = activarGeneracion;
	}

}
