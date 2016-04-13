/**
 * Clase de métodos o propiedades de uso general
 */
package com.datacode.avon_ots_admin.utils;

import java.io.File;

import com.datacode.avon_ots_admin.model.ArchivoCorreo;
import com.datacode.avon_ots_admin.reports.model.ModelRespuestaEncolarCorreo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.faces.context.FacesContext;

import org.apache.axis2.AxisFault;

import com.datacode.avon_ots_ws.EntregaVentanillaStub;
import com.datacode.avon_ots_ws.EntregaVentanillaStub.PersonaRecibe;
import com.datacode.avon_ots_ws.EntregaVentanillaStub.TipoPago;
import com.datacode.avon_ots_ws.EntregaVentanillaStub.Bancos;
import com.datacode.avon_ots_ws.CampaniaControllerStub;
import com.datacode.avon_ots_ws.CorreoControllerStub;
import com.datacode.avon_ots_ws.PrimeraSegundaLiquidacionControllerCallbackHandler;
import com.datacode.avon_ots_ws.PrimeraSegundaLiquidacionControllerStub;
import com.datacode.avon_ots_ws.RutasControllerStub;
import com.datacode.avon_ots_ws.RutasControllerStub.Rutas;
import com.datacode.avon_ots_ws.UtilsStub;
import com.datacode.avon_ots_ws.ZonaControllerStub;
import com.datacode.avon_ots_ws.CampaniaControllerStub.Campania;
import com.datacode.avon_ots_ws.CorreoControllerStub.RegistrarCorreoNoEnviadoResponse;
import com.datacode.avon_ots_ws.ZonaControllerStub.Zona;
import com.datacode.avon_ots_ws.SubBodegaAlmacenControllerStub;
import com.datacode.avon_ots_ws.SubBodegaAlmacenControllerStub.SubBodegaAlmacen;
import com.datacode.avon_ots_ws.UtilsStub.ObtieneParametroResponse;

/**
 * @author jorge.torner
 * @since 20-12-2011
 * 
 */
public class Utils {
	public static String formatoFechaCorta = "dd/MM/yyyy";
	public static String formatoFechaCortaHoraCorta = "dd/MM/yyyy HH:mm";
	public static String formatoFechaCortaHoraLarga = "dd/MM/yyyy HH:mm:ss";

	// Configuracion config =
	// (Configuracion)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("configuracion");

	Configuracion config;

	public Utils() {
		if (FacesContext.getCurrentInstance() != null) {
			config = (Configuracion) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("configuracion");
		}

	}

	/**
	 * Método para generar en una cadena un rango de código de barras, basado en
	 * un prefijo, la campaña, año, zona, consecutivo anterior y cantidad
	 * 
	 * @author jorge.torner
	 * @since 27/12/2011
	 * @param p_prefijo
	 *            -Cadena prefijo de los códigos, puede ser p.ej. "VEA", "ADJ",
	 *            etc.
	 * @param p_campaña
	 *            -Num de campaña
	 * @param p_año
	 *            -Año de campaña
	 * @param p_zona
	 *            -Num de Zona
	 * @param p_cantidad
	 *            -Cantidad de códigos a generar
	 * @param p_consecutivoAnterior
	 *            -Consecutivo del código anterior generado. En caso inicial se
	 *            manda 0.
	 * @return -Cadena concatenando el código inicial y el código final
	 *         indicando el rango de códigos
	 */
	public static String GenerarRangoCodigoBarras(String p_prefijo,
			String p_campaña, String p_año, String p_zona, byte p_cantidad,
			byte p_consecutivoAnterior) {
		String v_rango = "";
		java.text.DecimalFormat v_dFormat = new java.text.DecimalFormat("00");

		String v_prefijoCompleto = obtenerPrefijoCodigosBarras(p_prefijo,
				p_campaña, p_año, p_zona);
		String v_consecutivoInicial = v_dFormat
				.format(p_consecutivoAnterior + 1);
		String v_consecutivoFinal = v_dFormat.format(p_consecutivoAnterior
				+ p_cantidad);

		v_rango = v_prefijoCompleto + v_consecutivoInicial + " - "
				+ v_prefijoCompleto + v_consecutivoFinal;

		return v_rango;
	}

	public static String obtenerPrefijoCodigosBarras(String p_prefijo,
			String p_campaña, String p_año, String p_zona) {
		return p_prefijo + p_campaña + p_año + p_zona;
	}

	/**
	 * Obtiene la fecha del servidor de ésta aplicación
	 * 
	 * @author jorge.torner
	 * @since 26/01/2012
	 * @param p_formatoFecha
	 *            - Formato de la fecha
	 * @return String Cadena con la fecha formateada
	 */
	public static String ObtenerFechaActual(String p_formatoFecha) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(p_formatoFecha);
		return sdf.format(cal.getTime());
	}

	/**
	 * Guarda un registro de log a partir de un mensaje de la BD o un mensaje
	 * personalizado
	 * 
	 * @author jorge.torner
	 * @since 20-12-2011
	 * @param p_cu
	 *            -Caso de uso
	 * @param p_clave
	 *            -Clave del caso de uso
	 * @param p_mensajePersonal
	 *            -Incidente ocurrido personalizado (en caso de no existir en la
	 *            BD el mensaje relacionado a p_cu y p_clave
	 * @param p_mensajeExcepcion
	 *            -Mensaje de excepción interna de código
	 * @param p_idUsuario
	 *            -Id del usuario
	 * @return Lista de String con el mensaje y titulo
	 */
	public static void GuardarLogMensajeBD(String p_cu, String p_clave,
			String p_mensajePersonal, String p_mensajeExcepcion, int p_idUsuario) {
		try {
			UtilsStub stub = new UtilsStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			UtilsStub.GuardarLogMensajeBD request = new UtilsStub.GuardarLogMensajeBD();

			// parámetros
			request.setP_cu(p_cu);
			request.setP_clave(p_clave);
			request.setP_mensajePersonal(p_mensajePersonal);
			request.setP_mensajeExcepcion(p_mensajeExcepcion);
			request.setP_idUsuario(p_idUsuario);

			// invocamos
			stub.guardarLogMensajeBD(request);

			// obtenemos respuesta
			// usuario = response.get_return();

		} catch (RemoteException excepcionDeInvocacion) {
			System.err.println(excepcionDeInvocacion.toString());
		}
	}

	/**
	 * Método para guardar mensaje que conlleve parámetros en el log y lo trae
	 * de regreso paramostrarse en pantalla
	 * 
	 * @author jorge.torner
	 * @since 23/01/2012
	 * @param p_cu
	 *            -Caso de Uso
	 * @param p_clave
	 *            -Clave de mensaje
	 * @param p_mensajeExcepcion
	 *            -Error de excepción si es que hay
	 * @param p_idUsuario
	 *            -Id del usuario
	 * @param p_parametros
	 *            -Cadena de los parámetros a mandar en el mensaje. Cada
	 *            parámetro debe ir separado por el simbolo '&'. Por ejemplo la
	 *            cadena: "102&2011&Celaya" son tres parámetros: "102", "2011" y
	 *            "Celaya"
	 * @return String -Cadena con el mensaje guardado en log
	 */
	public static String GuardarLogMensajeParams(String p_cu, String p_clave,
			String p_mensajeExcepcion, int p_idUsuario, String p_parametros) {
		String mensaje = "";
		try {
			UtilsStub stub = new UtilsStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			UtilsStub.GuardarLogMensajeParams request = new UtilsStub.GuardarLogMensajeParams();

			// parámetros
			request.setP_cu(p_cu);
			request.setP_clave(p_clave);
			request.setP_mensajeExcepcion(p_mensajeExcepcion);
			request.setP_idUsuario(p_idUsuario);
			request.setP_parametros(p_parametros);

			// invocamos
			UtilsStub.GuardarLogMensajeParamsResponse response = stub
					.guardarLogMensajeParams(request);

			// obtenemos respuesta
			mensaje = response.get_return();

		} catch (RemoteException excepcionDeInvocacion) {
			System.err.println(excepcionDeInvocacion.toString());
		}
		return mensaje;
	}

	/**
	 * Método para guardar al log de Replicación un mensaje que conlleve
	 * parámetros y lo trae de regreso usarse
	 * 
	 * @author jorge.torner
	 * @since 15/01/2014
	 * @param p_cu
	 *            -Caso de Uso
	 * @param p_claveOIncidente
	 *            - Clave de mensaje o el mensaje del incidente
	 * @param p_mensajeExcepcion
	 *            -Error de excepción si es que hay
	 * @param p_idReplicacion
	 *            -Id de la replicación si es que hay. En caso de no haber
	 *            mandar 0.
	 * @param p_parametros
	 *            -Cadena de los parámetros a mandar en el mensaje. Cada
	 *            parámetro debe ir separado por el simbolo '&'. Por ejemplo la
	 *            cadena: "102&2011&Celaya" son tres parámetros: "102", "2011" y
	 *            "Celaya"
	 * @return String -Cadena con el mensaje guardado en log
	 */
	public static String guardarLogReplicacionMensajeParams(String p_cu,
			String p_claveOIncidente, String p_mensajeExcepcion,
			int p_idReplicacion, String p_parametros) {
		String mensaje = null;
		Connection con = AccesoBD.abrirConexionOTS();
		CallableStatement cs = null;
		if (con != null) {
			try {
				cs = con.prepareCall("{call SP_LogReplicacionInsertar_MensajeParam(?,?,?,?,?,?)}");
				cs.setObject("p_idReplicacion", p_idReplicacion, Types.INTEGER);
				cs.setObject("p_cu", p_cu, Types.VARCHAR);
				cs.setObject("p_claveOIncidente", p_claveOIncidente,
						Types.VARCHAR);
				cs.setObject("p_parametros", p_parametros, Types.VARCHAR);
				cs.setObject("p_mensajeErrorTecnico", p_mensajeExcepcion,
						Types.VARCHAR);
				cs.registerOutParameter("p_salida", Types.VARCHAR);

				cs.execute();
				mensaje = cs.getString("p_salida");
			} catch (SQLException ex) {
				InteractuarArchivos.guardaArchivoLog(ex.getMessage() + " - "
						+ ex.getStackTrace().toString());
			} finally {
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(con);
			}
		} else {
			InteractuarArchivos.guardaArchivoLog(p_cu + " - "
					+ p_claveOIncidente + " - " + p_mensajeExcepcion);
		}
		return mensaje;
	}

	/**
	 * Obtiene un mensaje de la base de datos para ser mostrado en interfaz
	 * 
	 * @author jorge.torner
	 * @since 13/01/2012
	 * @param p_cu
	 *            -Caso de uso
	 * @param p_clave
	 *            -Clave del mensaje
	 * @param p_guardarLog
	 *            -True para que sea guardado en log
	 * @param p_mensajePersonal
	 *            -Mensaje personalizado default
	 * @param p_mensajeExcepcion
	 *            -Mensaje técnico detallado de la excepción
	 * @param p_idUsuario
	 *            -Id del usuario
	 * @return String[] Arreglo con dos elementos, primer elemento tiene el
	 *         mensaje, el segundo elemento el título si es que se requiere
	 */
	public static String[] ObtenerMensajeBD(String p_cu, String p_clave,
			Boolean p_guardarLog, String p_mensajePersonal,
			String p_mensajeExcepcion, int p_idUsuario) {
		String[] lMensaje = null;
		try {
			UtilsStub stub = new UtilsStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			UtilsStub.ObtenerMensajeBD request = new UtilsStub.ObtenerMensajeBD();

			// parámetros
			request.setP_cu(p_cu);
			request.setP_clave(p_clave);
			request.setP_guardarLog(p_guardarLog);
			request.setP_mensajePersonal(p_mensajePersonal);
			request.setP_mensajeExcepcion(p_mensajeExcepcion);
			request.setP_idUsuario(p_idUsuario);

			// invocamos
			UtilsStub.ObtenerMensajeBDResponse response = stub
					.obtenerMensajeBD(request);

			// obtenemos respuesta
			lMensaje = response.get_return();

		} catch (RemoteException excepcionDeInvocacion) {
			System.err.println(excepcionDeInvocacion.toString());
		}

		return lMensaje;
	}

	/**
	 * Crea una lista de tipo SelectItem - Datos obtenidos al invocar WS de
	 * Campanias
	 * 
	 * @author jorge.torner
	 * @since 09/01/2012
	 * @return Arreglo de Campañas
	 */
	public Campania[] getCampanias(String p_tipoCU, int p_idLDC, int p_idZona,
			int p_idRuta) {

		Campania[] campanias = null;
		// CampaniaControllerStub stub = null;
		// Invocacion del Stub de datos
		// CampaniaControllerStub.GetCampanias reqCam = null;
		// CampaniaControllerStub.GetCampaniasResponse resCam = null;

		try {
			// Crea el cliente
			CampaniaControllerStub stub = new CampaniaControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Se obtiene request
			CampaniaControllerStub.GetCampanias request = new CampaniaControllerStub.GetCampanias();
			// Se agregan parámetros
			request.setIdLDC(p_idLDC);
			request.setTipoCasoUso(p_tipoCU);
			request.setIdZona(p_idZona);
			request.setIdRuta(p_idRuta);
			request.setIdUsuario(config.getIdUsuario());

			// Se invoca servicio
			CampaniaControllerStub.GetCampaniasResponse response = stub
					.getCampanias(request);

			// Se obtiene resultado
			campanias = response.get_return();
		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("General Admin", "M1",
					"No se pudieron cargar las Campañas", ex.getMessage(),
					config.getIdUsuario());
		} finally {
			//
		}
		return campanias;
	}

	/**
	 * Crea una lista de tipo SelectItem - Datos obtenidos al invocar WS de
	 * Zonas
	 * 
	 * @author jorge.torner
	 * @since 09/01/2012
	 * @return Arreglo de Zonas
	 */
	public Zona[] getZonas(int p_idLDC, String p_tipoCU) {

		Zona[] zonas = null;

		try {
			// Crea el cliente
			ZonaControllerStub stub = new ZonaControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Se obtiene request
			ZonaControllerStub.GetZonas request = new ZonaControllerStub.GetZonas();

			// Se agregan parámetros
			request.setIdLDC(p_idLDC);
			request.setTipoCasoUso(p_tipoCU);
			request.setIdUsuario(config.getIdUsuario());

			// Se invoca servicio
			ZonaControllerStub.GetZonasResponse response = stub
					.getZonas(request);

			// Se obtiene resultado
			zonas = response.get_return();
		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("General Admin", "M2",
					"No se pudieron cargar las Zonas", ex.getMessage(),
					config.getIdUsuario());
		} finally {
			//
		}
		return zonas;
	}

	/**
	 * Método para validar si una cadena es entero
	 * 
	 * @author jorge.torner
	 * @since 16/01/2012
	 * @param cadena
	 *            -Cadena a validar
	 * @return boolean -Verdadero si es entero, falso de lo contrario
	 */
	public static boolean esEntero(String cadena) {
		try {
			Integer.parseInt(cadena);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isPostback() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context == null)
			return false;
		return !context.getExternalContext().getRequestParameterMap().isEmpty();
	}

	/**
	 * crea una lista de los tipos de pago
	 * 
	 * @author jose.ponce
	 * @since 17/01/2012
	 * @return Arreglo de Tipo Pago
	 */
	public TipoPago[] getTipoPago() {

		TipoPago[] tipoPagos = null;
		try {
			// Se crea el cliente
			EntregaVentanillaStub stub = new EntregaVentanillaStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Se agrega al request para la operacion a ejecutar
			EntregaVentanillaStub.GetTipoPago request = new EntregaVentanillaStub.GetTipoPago();
			// pasamos parametros
			request.setIdUsuario(config.getIdUsuario());
			// Añade al response el request de la operacion
			EntregaVentanillaStub.GetTipoPagoResponse response = stub
					.getTipoPago(request);
			// obetenemos valores
			tipoPagos = response.get_return();
		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("General Admin", "M6",
					"No se pudieron cargar los tipos de pago", ex.getMessage(),
					config.getIdUsuario());
		} finally {

		}
		return tipoPagos;
	}

	/**
	 * Crea una lista de tipo Persona Recibe
	 * 
	 * @author jose.ponce
	 * @since 04/04/2012
	 */
	public PersonaRecibe[] getPersonaRecibe() {
		PersonaRecibe[] lstPersonaRecibe = null;
		try {
			// Se crea el cliente
			EntregaVentanillaStub stub = new EntregaVentanillaStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Se agrega al request para la operacion a ejecutar
			EntregaVentanillaStub.GetPersonaRecibe request = new EntregaVentanillaStub.GetPersonaRecibe();
			// pasamos parametros
			request.setIdUsuario(config.getIdUsuario());
			// Añade al response el request de la operacion
			EntregaVentanillaStub.GetPersonaRecibeResponse response = stub
					.getPersonaRecibe(request);
			// obetenemos valores
			lstPersonaRecibe = response.get_return();
		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("General Admin", "M6",
					"No se pudieron cargar los tipos de pago", ex.getMessage(),
					config.getIdUsuario());
		} finally {

		}
		return lstPersonaRecibe;
	}

	/**
	 * Crea una lista con el catálogo de bancos
	 * 
	 * @author jose.ponce
	 * @since 17/01/2012
	 * @return Bancos[]
	 */
	public Bancos[] getBancos() {
		Bancos[] catBanco = null;
		try {
			// Se crea el cliente
			EntregaVentanillaStub stub = new EntregaVentanillaStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Se agrega al request para la operacion a ejecutar
			EntregaVentanillaStub.GetBancos request = new EntregaVentanillaStub.GetBancos();
			// pasamos parametros
			request.setIdUsuario(config.getIdUsuario());
			// Añade al response el request de la operacion
			EntregaVentanillaStub.GetBancosResponse response = stub
					.getBancos(request);
			// obetenemos valores
			catBanco = response.get_return();
		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("General Admin", "M7",
					"No se pudieron cargar los bancos", ex.getMessage(),
					config.getIdUsuario());
		} finally {

		}
		return catBanco;
	}

	/**
	 * Obtiene catálogo de Sub bodegas almacén
	 * 
	 * @author jorge.torner
	 * @since 15/02/2012
	 * @param p_idSubBodega
	 *            -Id de la subbodega
	 * @param p_idZona
	 *            -Id de la zona
	 * @param p_idPais
	 *            -Id del país
	 * @param p_idLDC
	 *            -Id del LDC
	 * @return SubBodegaAlmacen[]
	 */
	public SubBodegaAlmacen[] getSubBodegaAlmacen(int p_idSubBodega,
			int p_idZona, int p_idPais, int p_idLDC) {

		SubBodegaAlmacen[] subBodegas = null;

		try {
			// Crea el cliente
			SubBodegaAlmacenControllerStub stub = new SubBodegaAlmacenControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			// Se obtiene request
			SubBodegaAlmacenControllerStub.ObtenerSubBodegaAlmacen request = new SubBodegaAlmacenControllerStub.ObtenerSubBodegaAlmacen();

			// Se agregan parámetros
			request.setP_idLDC(p_idLDC);
			request.setP_idPais(p_idPais);
			request.setP_idUsuario(config.getIdUsuario());
			request.setP_idSubBodega(p_idSubBodega);
			request.setP_idZona(p_idZona);

			// Se invoca servicio
			SubBodegaAlmacenControllerStub.ObtenerSubBodegaAlmacenResponse response = stub
					.obtenerSubBodegaAlmacen(request);

			// Se obtiene resultado
			subBodegas = response.get_return();
		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD(
					"CUADMIN001.02",
					"M13",
					"Surgió un error al obtener los datos de SubBodega Almacen.",
					ex.getMessage(), config.getIdUsuario());
		} finally {
			//
		}
		return subBodegas;
	}

	/**
	 * Obtiene catalogo de rutas
	 * 
	 * @author jorge.torner
	 * @since 15/02/2012
	 * @param p_tipoCU
	 *            - Tipo de caso de uso
	 * @param p_idLDC
	 *            - Id del LDC
	 * @param p_idZona
	 *            - Id de la zona
	 * @param p_idCampania
	 *            - Id de la campaña
	 * @return Rutas[] Arreglo de rutas
	 */
	public Rutas[] getRutas(String p_tipoCU, int p_idLDC, int p_idZona,
			int p_idCampania) {
		Rutas[] rutas = new Rutas[0];

		RutasControllerStub customer = null;
		RutasControllerStub.GetRutasPorLDC req = null;
		RutasControllerStub.GetRutasPorLDCResponse res = null;

		try {
			customer = new RutasControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(customer
					._getServiceClient().getOptions().getTo().getAddress());
			customer._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			req = new RutasControllerStub.GetRutasPorLDC();
			req.setIdLDC(p_idLDC);
			req.setIdZona(p_idZona);
			req.setTipoCasoUso(p_tipoCU);
			req.setCampania(p_idCampania);
			req.setIdUsuario(config.getIdUsuario());

			res = customer.getRutasPorLDC(req);
			rutas = res.get_return();

		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("General Admin", "M8",
					"No se pudieron cargar las rutas correctamente",
					ex.getMessage(), config.getIdUsuario());
		}
		return rutas;
	}

	public UtilsStub.LDC[] getListaLDC(int p_idLdc) {
		UtilsStub.LDC[] listaLdc = new UtilsStub.LDC[0];

		UtilsStub customer = null;
		UtilsStub.ObtenerListaLDC req = null;
		UtilsStub.ObtenerListaLDCResponse res = null;

		try {
			customer = new UtilsStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(customer
					._getServiceClient().getOptions().getTo().getAddress());
			customer._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			req = new UtilsStub.ObtenerListaLDC();
			req.setP_idUsuario(config.getIdUsuario());
			req.setP_idLdc(p_idLdc);

			res = customer.obtenerListaLDC(req);
			listaLdc = res.get_return();

		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("General Admin", "M10",
					"No se pudo cargar la lista de LDC", ex.getMessage(),
					config.getIdUsuario());
		}
		return listaLdc;
	}

	/**
	 * Modifica una url de servicios web de Avon_Ots_WS poniéndole la ip y
	 * puerto de la configuración
	 * 
	 * @author jorge.torner
	 * @since 03/02/2012
	 * @param url
	 *            -Url a modificar
	 * @return String con la url modificada
	 */
	public static String modificarUrlServicioWeb(String url) {
		String urlNueva = "";

		int ind1 = url.indexOf("//");
		int ind2 = url.indexOf("/", ind1 + 2);
		String urlVieja = url.substring(ind1 + 2, ind2);
		urlNueva = url.replace(urlVieja, obtenerIpPuertoServiciosWeb());

		return urlNueva;
	}

	/**
	 * Obtiene del archivo de configuración la ip y puerto de los servicios web
	 * 
	 * @author jorge.torner
	 * @since 03/02/2012
	 * @return String Ip y puerto de configuración
	 */
	public static String obtenerIpPuertoServiciosWeb() {
		String ipPuerto = "";

		Utils utils = new Utils();
		if (utils.config == null
				|| utils.config.getUrlServiciosWeb().equals(""))
			// Obtener del archivo de configuración la Ip y puerto de los
			// webservices
			ipPuerto = obtenerPropiedadArchivoConfig("IpPuertoServiciosWeb");
		else
			ipPuerto = utils.config.getUrlServiciosWeb();

		return ipPuerto;
	}

	/**
	 * Obtiene el valor de una propiedad del archivo de configuración
	 * 
	 * @author jorge.torner
	 * @since 15/02/2012
	 * @param propiedad
	 * @return String Valor de la propiedad
	 */
	public static String obtenerPropiedadArchivoConfig(String propiedad) {
		Properties propiedades = new Properties();
		String archivoPropiedades = "AvonAdminApp.properties";
		String valor = "";

		// ServletContext context = ((ServletContext) FacesContext
		// .getCurrentInstance().getExternalContext().getContext());
		//
		// String rutaApp = context.getRealPath("");
		String rutaApp = com.datacode.avon_ots_admin.quartz.ListenerJobs.path;

		InputStream fis = null;
		try {
			fis = new FileInputStream(rutaApp + "/" + archivoPropiedades);
			propiedades.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Escribir a archivo de log
			return null;
		} catch (IOException e) {
			// TODO Escribir a archivo de log
			return null;
		}

		if (propiedades != null)
			valor = propiedades.getProperty(propiedad);

		return valor;
	}

	/**
	 * Se hace el envío de la segunda liquidación
	 * 
	 * @author jorge.torner
	 * @since 15/02/2012
	 * @return String Cadena con el mensaje de error si es que hubo, o vacía si
	 *         no.
	 */
	public String enviarPrimeraSegundaLiquidacion(int liquidacion) {
		String mensaje = "";
		try {
			PrimeraSegundaLiquidacionControllerStub stub = new PrimeraSegundaLiquidacionControllerStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			PrimeraSegundaLiquidacionControllerStub.EnviarPrimeraSegundaLiquidacion request = new PrimeraSegundaLiquidacionControllerStub.EnviarPrimeraSegundaLiquidacion();

			// parámetros
			request.setP_idLiquidacion((short) liquidacion);
			request.setP_idUsuario(config.getIdUsuario());

			// // Llamada asíncrona ////
			PrimeraSegundaLiquidacionControllerCallbackHandler callbackHand = new PrimeraSegundaLiquidacionControllerCallbackHandler() {
				public void handleResponse(
						PrimeraSegundaLiquidacionControllerStub.EnviarPrimeraSegundaLiquidacionResponse response) {
					String res = "";
					try {
						res = response.get_return();
					} catch (Exception ex) {
						// Guardamos en log si es que hubo algún error
						Utils.GuardarLogMensajeBD(
								"PrimeraSegundaLiquidacionAdmin",
								"M3",
								"No se pudo realizar el envío de la Segunda Liquidación",
								ex.getStackTrace().toString() + ex.getMessage(),
								config.getIdUsuario());
					}
					if (!res.equals("")) {
						// Guardamos en log si es que hubo algún error
						Utils.GuardarLogMensajeBD(
								"PrimeraSegundaLiquidacionAdmin",
								"M3",
								"No se pudo realizar el envío de la Segunda Liquidación",
								res, config.getIdUsuario());
					}
				}
			};

			stub.startenviarPrimeraSegundaLiquidacion(request, callbackHand);
			// // Fin llamada asíncrona ////

			// llamada síncrona
			// PrimeraSegundaLiquidacionControllerStub.EnviarPrimeraSegundaLiquidacionResponse
			// response = stub
			// .enviarPrimeraSegundaLiquidacion(request);
			//
			// // obtenemos respuesta
			// String respuesta = response.get_return();
			//
			// if(!respuesta.equals("")){
			// mensaje += respuesta;
			// }
			// Fin llamada síncrona

		} catch (RemoteException ex) {
			mensaje = Utils.ObtenerMensajeBD("PrimeraSegundaLiquidacionAdmin",
					"M3", true,
					"No se pudo realizar el envío de la Segunda Liquidación",
					ex.getMessage() + ex.getStackTrace().toString(),
					config.getIdUsuario())[0];
		}

		return mensaje;
	}

	public String ModificaCodigoBarras(String CodigoBarras) {
		String nuevocb = "";
		int tamanio;
		tamanio = CodigoBarras.length();
		switch (tamanio) {
		case 5:
			nuevocb = CodigoBarras + "00";
			break;
		case 7:
			StringBuffer s = new StringBuffer(CodigoBarras);
			nuevocb = s.delete(5, 7).toString() + "00";
			break;
		case 8:
			StringBuffer s2 = new StringBuffer(CodigoBarras);
			nuevocb = s2.delete(0, 3).toString() + "00";
			break;
		case 10:
			StringBuffer s3 = new StringBuffer(CodigoBarras);
			s3 = s3.delete(0, 3);
			nuevocb = s3.delete(5, 7).toString() + "00";
			break;
		default:
			nuevocb = CodigoBarras;
			break;
		}
		return nuevocb;
	}

	public String EliminaCerosIzquierda(String account) {
		String cb;
		cb = account.replaceFirst("^0*", "");
		return cb.trim();
	}

	/**
	 * Método para devolver los milisegundos de timeout para las llamadas a los
	 * servicios web
	 * 
	 * @author jorge.torner
	 * @since 02/08/2012
	 * @return int Milisegundos de tiempo de espera
	 */
	public static long ObtenerConfiguracionTiempoEsperaWS() {
		long timeoutWS;
		try {
			timeoutWS = 1000 * Long.parseLong(Utils
					.obtenerPropiedadArchivoConfig("TiempoEsperaInvocacionWS"));
		} catch (Exception ex) {
			timeoutWS = 300000;
		}

		return timeoutWS;
	}

	/**
	 * Modifica una url de servicios web de Avon_Ots_WS poniéndole la ip y
	 * puerto de la configuración
	 * 
	 * @author Andres.Alvarez
	 * @since 17/02/2014
	 * @param url
	 *            -Url a modificar
	 * @return String con la url modificada
	 */
	public static String modificarUrlServicioWebeOTS(String url) {
		String urlNueva = "";
		urlNueva = url.replace(url, obtenerRutaServiciosWebeOTS());

		return urlNueva;
	}

	/**
	 * Obtiene del archivo de configuración la ip y puerto de los servicios web
	 * 
	 * @author Andres.Alvarez
	 * @since 17/02/2014
	 * @return String Ip y puerto de configuración
	 */
	public static String obtenerRutaServiciosWebeOTS() {
		String url = "";

		Utils utils = new Utils();
		if (utils.config == null
				|| utils.config.getUrlServiciosWeb().equals(""))
			// Obtener del archivo de configuración la Ip y puerto de los
			// webservices
			url = obtenerPropiedadArchivoConfig("urlWebeOTS");
		else
			url = utils.config.getUrlServiciosWeb();

		return url;
	}

	/***
	 * @author Javier Gallegos
	 * 
	 *         metodo que guarda los archivos efisicos en la ruta definida por
	 *         el parametro RutaArchivosCorreoNoEnviado
	 * 
	 * @param idUsuario
	 * @param cuenta
	 * @param recipientes
	 * @param asunto
	 * @param texto
	 * @param idReporte
	 * @param archivo
	 */
	public static String encolarCorreos(int idUsuario, String cuenta,
			String recipientes, String asunto, String texto, int idReporte,
			ArchivoCorreo archivo, String nombreReporte) {
		String error = "";
		ModelRespuestaEncolarCorreo res = RegistraCorreoNoEnviado(idUsuario,
				cuenta, recipientes, asunto, texto, idReporte,
				archivo.getNombreArchivo());
		if (res.getIdGenerado() == -1) {
			error = res.getMensaje();
		} else if (res.getIdGenerado() == 0) {
			error = "Ocurrio un error en el SP";
		} else if (res.getIdGenerado() > 0) {
			// se tiene el id y podemos guardar el archivo con ese id
			if (archivo != null) {
				String ruta = obtenerParametroRuta();
				try {
					FileOutputStream fos = new FileOutputStream(new File(ruta
							+ "\\" + archivo.getNombreArchivo()));
					archivo.getBytes().writeTo(fos);
					fos.close();
				} catch (FileNotFoundException e) {
					error = e.getMessage();
				} catch (IOException e) {
					error = e.getMessage();
				} finally {

				}

			}
		}
		System.out.println(error);
		return error;

	}

	/***
	 * @author Javier Gallegos
	 * 
	 *         metodo que permite obtener el valor del parametro
	 *         RutaArchivosCorreoNoEnviado para guardar los archivos de los
	 *         correos no enviados en esa ruta
	 * 
	 * @return devuelve el valor del parametro RutaArchivosCorreoNoEnviado
	 */
	public static String obtenerParametroRuta() {
		String ruta = "";

		UtilsStub stub;
		try {
			stub = new UtilsStub();

			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			UtilsStub.ObtieneParametro p = new UtilsStub.ObtieneParametro();
			p.setParametro("RutaArchivosCorreoNoEnviado");
			ObtieneParametroResponse res = stub.obtieneParametro(p);
			ruta = res.get_return();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ruta;
	}

	/**
	 * @author Javier Gallegos
	 * 
	 *         metodo que permite obtener cualquier valor de los parametros en
	 *         la tabla de PW_CONFIGURACION_CENTRAL
	 * 
	 * @param parametro
	 *            nombre del parametro que se va a buscar
	 * @return devuelve el valor del parametro en formato String
	 */
	public static String obtenerParametroConfiguracionCentral(String parametro) {
		String ruta = "";

		UtilsStub stub;
		try {
			stub = new UtilsStub();

			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			UtilsStub.ObtieneParametro p = new UtilsStub.ObtieneParametro();
			p.setParametro(parametro);
			ObtieneParametroResponse res = stub.obtieneParametro(p);
			ruta = res.get_return();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ruta;
	}

	/**
	 * @author Javier Gallegos
	 * 
	 *         metodo para llamar los WS y registrar los correos no enviados
	 * 
	 * @param idUsuario
	 * @param de
	 * @param para
	 * @param asunto
	 * @param textoCuerpo
	 * @param idReporte
	 * @return devuelve el id generado en la tabla PW_CORREOS_PENDIENTES
	 */
	private static ModelRespuestaEncolarCorreo RegistraCorreoNoEnviado(
			int idUsuario, String de, String para, String asunto,
			String textoCuerpo, int idReporte, String nombreArchivo) {
		ModelRespuestaEncolarCorreo respuesta = new ModelRespuestaEncolarCorreo();
		int nuevo = -1;
		String mensaje = "";
		CorreoControllerStub stub;
		try {
			stub = new CorreoControllerStub();
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient()
					.getOptions().getTo().getAddress());
			stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));
			CorreoControllerStub.RegistrarCorreoNoEnviado r = new CorreoControllerStub.RegistrarCorreoNoEnviado();
			r.setAsunto(asunto);
			r.setDe(de);
			r.setIdReporte(idReporte);
			r.setIdUsuario(idUsuario);
			r.setPara(para);
			r.setTextoCuerpo(textoCuerpo);
			r.setNombreArchivo(nombreArchivo);

			RegistrarCorreoNoEnviadoResponse res = stub
					.registrarCorreoNoEnviado(r);

			nuevo = res.get_return();

		} catch (AxisFault e) {
			mensaje = e.getMessage();
		} catch (RemoteException e) {
			mensaje = e.getMessage();
		}
		respuesta.setIdGenerado(nuevo);
		respuesta.setMensaje(mensaje);
		System.out.println(respuesta.getMensaje());
		return respuesta;
	}

	/**
	 * @autor Javier Gallegos
	 * 
	 *        metodo que permite comprobar la coneion a internetpor medio de un
	 *        socket se utiliza el sitio google ya que este es muy improbable
	 *        que no este activo
	 * 
	 * @return devuelve true si es correcta la conexion a internet, false en
	 *         caso contrario
	 */
	public static Boolean comprobarConexionInternet() {
		Boolean hayConexion = false;
		String dirWeb = "www.google.com";
		int puerto = 80;

		try {
			Socket s = new Socket(dirWeb, puerto);
			if (s.isConnected()) {
				hayConexion = true;

			}
			s.close();
		} catch (Exception e) {
			System.out.println("No hay conexion a internet");
		}
		return hayConexion;
	}

	public static com.datacode.avon_ots_ws.UtilsStub.Configuracion obtenerConfiguracionLDC() {

		com.datacode.avon_ots_ws.UtilsStub.Configuracion arreglo = null;
		try {
			UtilsStub Stub = new UtilsStub();

			// Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(Stub._getServiceClient()
					.getOptions().getTo().getAddress());
			Stub._getServiceClient()
					.getOptions()
					.setTo(new org.apache.axis2.addressing.EndpointReference(
							url));

			UtilsStub.CargarConfiguracion param = new UtilsStub.CargarConfiguracion();
			UtilsStub.CargarConfiguracionResponse response = Stub
					.cargarConfiguracion(param);

			arreglo = response.get_return();
			if (arreglo != null) {
				return arreglo;
			}

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String generarNombreArchivoCorreo(String nombreReporte,
			String claveCedi, String extension) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String nombre = "";
		nombre = nombreReporte + "_" + claveCedi + "_" + df.format(new Date())
				+ "." + extension;

		return nombre;
	}

	public static String obtenerFechaReportes() {
		String fecha;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int anio = cal.get(Calendar.YEAR);
		int mes = cal.get(Calendar.MONTH);
		mes = mes + 1;
		int dia = cal.get(Calendar.DATE);
		String mesS = "";
		switch (mes) {
		case 1:
			mesS = "Enero";
			break;
		case 2:
			mesS = "Febrero";
			break;
		case 3:
			mesS = "Marzo";
			break;
		case 4:
			mesS = "Abril";
			break;
		case 5:
			mesS = "Mayo";
			break;
		case 6:
			mesS = "Junio";
			break;
		case 7:
			mesS = "Julio";
			break;
		case 8:
			mesS = "Agosto";
			break;
		case 9:
			mesS = "Septiembre";
			break;
		case 10:
			mesS = "Octubre";
			break;
		case 11:
			mesS = "Noviembre";
			break;
		case 12:
			mesS = "Diciembre";
			break;
		}

		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		fecha = dia + " de " + mesS + " de " + anio + " "
				+ df.format(new Date())+ " hrs.";
		return fecha;
	}

}
