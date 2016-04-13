package com.datacode.avon_ots_admin.replicacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

import com.datacode.avon_ots_admin.replicacion.Replicacion;
import com.datacode.avon_ots_admin.replicacion.ReplicacionData;
import com.datacode.avon_ots_admin.replicacion.ReplicacionDetalle;
import com.datacode.avon_ots_admin.replicacion.ReplicacionDetalleData;
import com.datacode.avon_ots_admin.replicacion.ReplicacionTabla;
import com.datacode.avon_ots_admin.replicacion.ReplicacionTablaData;
import com.datacode.avon_ots_admin.replicacion.ReqFechasReplicacion;
import com.datacode.avon_ots_admin.replicacion.ReqReplicacionUploadZip;
import com.datacode.avon_ots_admin.replicacion.ResFechasReplicacion;
import com.datacode.avon_ots_admin.replicacion.ResReplicacion;
import com.datacode.avon_ots_admin.replicacion.ResReplicacionEstatusDTO;
import com.datacode.avon_ots_admin.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReplicacionTablaService {
	/**
	 * Acceso a BD
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	private ReplicacionTablaData data;
	private ReplicacionData replicacionData;
	private ReplicacionDetalleData detalleData;
	private String URL_REPLICACION;
	private String PUERTO_SEGURO_REPLICACION;
	private List<ReplicacionDetalle> replicacionDetalles;
	private String rutaReplicacion;
	private Integer tamanoArchivos;
	private Integer numeroReintentos;
	private Integer reintentosEstatus;
	private Double porcentajeCompresion;
	private String SUB_DIRECTORIO;
	private String SUB_DIRECTORIO_RECIBIR;
	private List<String> archivosZip;
	private String Replicacion_RutaLDC_ArchivosBD;
	private byte[] secretKey;
	private List<ReqReplicacionUploadZip> generadosServer = new ArrayList<ReqReplicacionUploadZip>();
	private String replicacionEOTSLDC;
	private String replicacionLDCEOTS;

	private final String USER_AGENT = "Mozilla/5.0";

	public String getReplicacionEOTSLDC() {
		return replicacionEOTSLDC;
	}

	public void setReplicacionEOTSLDC(String replicacionEOTSLDC) {
		this.replicacionEOTSLDC = replicacionEOTSLDC;
	}

	public String getReplicacionLDCEOTS() {
		return replicacionLDCEOTS;
	}

	public void setReplicacionLDCEOTS(String replicacionLDCEOTS) {
		this.replicacionLDCEOTS = replicacionLDCEOTS;
	}

	public ReplicacionTablaService() {
		data = new ReplicacionTablaData();
		replicacionData = new ReplicacionData();
		detalleData = new ReplicacionDetalleData();
	}

	public void inicializaParametros(Replicacion replicacion) {
		try {
			logger.info("Entrando a metodo inicializaParametros:"
					+ replicacion.toString());
			SUB_DIRECTORIO = Utils
					.obtenerPropiedadArchivoConfig("SUB_DIRECTORIO");
			SUB_DIRECTORIO_RECIBIR = Utils
					.obtenerPropiedadArchivoConfig("SUB_DIRECTORIO_RECIBIR");

			porcentajeCompresion = Double.valueOf(Utils
					.obtenerPropiedadArchivoConfig("porcentajeCompresion"));
			URL_REPLICACION = Utils
					.obtenerPropiedadArchivoConfig("URL_REPLICACION");

			// PUERTO_SEGURO_REPLICACION = Utils
			// .obtenerPropiedadArchivoConfig("PUERTO_SEGURO_REPLICACION");
			reintentosEstatus = Integer.valueOf(Utils
					.obtenerPropiedadArchivoConfig("reintentosEstatus"));

			Map<String, String> parametros = replicacionData
					.obtieneParametros(replicacion);
			logger.info(parametros.toString());
			rutaReplicacion = parametros.get("Replicacion_RutaLDC_Archivos");
			tamanoArchivos = Integer.valueOf(parametros
					.get("Replicacion_TamañoArchivos"));
			numeroReintentos = Integer.valueOf(parametros
					.get("Replicacion_ReintentosEnvioArchivos"));
			Replicacion_RutaLDC_ArchivosBD = parametros
					.get("Replicacion_RutaLDC_ArchivosBD");

			replicacionEOTSLDC = parametros.get("Replicacion_EOTS->LDC_Activa");
			replicacionLDCEOTS = parametros.get("Replicacion_LDC->EOTS_Activa");
			logger.info("Sale metodo inicializaParametros:"
					+ replicacion.toString());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			Utils.guardarLogReplicacionMensajeParams("UCS022_1: Replicación",
					"inicializaParametros:ERROR AL OBTENER PARAMETROS INICIALES. TIPO REPLICACION:"
							+ replicacion.getTipoReplicacion(), e.getMessage(),
					0, "");
		}
	}

	/**
	 * Metodo para obtener un listado de tablas candidatas a respaldar
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 * @return Listado de tablas asociadas al id cliente para respaldas
	 */
	public List<ReplicacionTabla> getReplicacionTabla() {
		return data.getReplicacionTabla();
	}

	/**
	 * Actualiza Fechas de actualizacion para tablas recuperadas del servidor
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 * @param resFechasReplicacion
	 *            listado de tablas a actualizar
	 */
	public ResFechasReplicacion guardaFechasReplicacion(
			ReqFechasReplicacion reqFechasReplicacion) {
		ObjectMapper mapper = new ObjectMapper();
		HttpClient cliente = getCliente();
		logger.info("Entra metodo guardaFechasReplicacion");
		HttpPost target = new HttpPost(URL_REPLICACION
				+ "ReplicacionTablaConsultaService");

		// target.setHeader("User-Agent", USER_AGENT);
		StringEntity input = null;
		ResFechasReplicacion resFechasReplicacion = new ResFechasReplicacion();
		try {
			input = new StringEntity(
					mapper.writeValueAsString(reqFechasReplicacion));
			logger.info("PETICION:" + URL_REPLICACION
					+ "ReplicacionTablaConsultaService: "
					+ mapper.writeValueAsString(reqFechasReplicacion));

			input.setContentType("application/json");
		} catch (JsonProcessingException je) {
			logger.error(je);
			je.printStackTrace();
			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación LDC-OTSe",
					"M-5  Error al solicitar fechas de replicacion desde servidor. TIPO REPLICACION:LDC-OTSe.",
					je.getMessage(), 0, "");
		} catch (UnsupportedEncodingException ue) {
			logger.error(ue);
			ue.printStackTrace();
			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación LDC-OTSe",
					"M-5  Error al solicitar fechas de replicacion desde servidor. TIPO REPLICACION:LDC-OTSe.",
					ue.getMessage(), 0, "");
		}
		target.setEntity(input);

		try {
			HttpResponse response = cliente.execute(target);
			resFechasReplicacion = (ResFechasReplicacion) UtilRest
					.procesaMapRespuesta(response, ResFechasReplicacion.class);

			if (!resFechasReplicacion.getClave().equals("OK")) {
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-5. Error al obtener fechas de replicacion desde servidor. TIPO REPLICACION:LDC-OTSe.",
						resFechasReplicacion.getMensaje(), 0, "");
				return resFechasReplicacion;
			}

			data.guardaFechasReplicacion(resFechasReplicacion);
		} catch (IOException ie) {
			logger.error(ie);
			ie.printStackTrace();
			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación LDC-OTSe",
					"M-5. Error al obtener fechas de replicacion desde servidor. TIPO REPLICACION:LDC-OTSe.",
					ie.getMessage(), 0, "");
		}

		logger.info("Sale metodo guardaFechasReplicacion");
		return resFechasReplicacion;

	}

	/**
	 * Guarda proceso replicacion en BD
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 * @param replicacion
	 *            replicacion a almacenar
	 * @return {@link Replicacion} guardada en bd
	 */
	public Replicacion guardaReplicacion(Replicacion replicacion) {
		return replicacionData.guardaReplicacion(replicacion);
	}

	/**
	 * Guarda proceso replicacion en BD
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 * @param replicacion
	 *            replicacion a almacenar
	 * @param replicacionTablas
	 *            lista de tablas a generar archivos
	 * @return 0 exito - 1 error
	 */
	public Integer generaArchivosReplicacion(Replicacion replicacion,
			List<ReplicacionTabla> replicacionTablas) {
		logger.info("Entra metodo generaArchivosReplicacion:"
				+ replicacion.getIdReplicacion());
		byte[] buffer = new byte[1024];
		replicacionDetalles = new ArrayList<ReplicacionDetalle>();

		// Genera archivos en BD

		logger.info("generaArchivosReplicacion:"
				+ replicacion.getIdReplicacion()
				+ "  : Iniciando generacion de archivos");
		for (ReplicacionTabla replicacionTabla : replicacionTablas) {
			List<ReplicacionDetalle> tmp = detalleData
					.generaArchivosReplicacion(replicacionTabla, replicacion);

			replicacionDetalles.addAll(tmp);
		}
		logger.info("generaArchivosReplicacion:"
				+ replicacion.getIdReplicacion()
				+ "  : Finalizando generacion de archivos");

		if (replicacionDetalles.size() == 0) {
			replicacion.setFechaFin(Utils
					.ObtenerFechaActual(Utils.formatoFechaCortaHoraLarga));
			replicacion.setEstatus("3.FINALIZADA");
			replicacionData.guardaReplicacion(replicacion, "Actualizar");
			return 1;
		}

		// Copiar archvivos desde servidor de base de datos
		logger.info("generaArchivosReplicacion:"
				+ replicacion.getIdReplicacion()
				+ "  : Inicia Copiando archivos desde BD");
		String ruta = rutaReplicacion + SUB_DIRECTORIO;

		String rutaServer = Replicacion_RutaLDC_ArchivosBD + SUB_DIRECTORIO;

		File destinoVal = new File(ruta);
		if (!destinoVal.exists()) {
			replicacion.setEstatus("9.FINALIZADA CON ERROR");
			replicacion.setFechaFin(Utils
					.ObtenerFechaActual(Utils.formatoFechaCortaHoraLarga));
			replicacionData
					.guardaReplicacion(replicacion, "Actualizar");

			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación LDC-OTSe",
					"M-15. No existe ruta destino en servidor para generacion de archivos."
							+ replicacion.getTipoReplicacion()
							+ ".REPLICACION ID:"
							+ replicacion.getIdReplicacion(), "", replicacion
							.getIdReplicacion().intValue(), "");
			return 1;
		}
		if (!Replicacion_RutaLDC_ArchivosBD.equals("")) {
			InputStream inStream = null;
			OutputStream outStream = null;
			File origenVal = new File(rutaServer);
			if (!origenVal.exists()) {
				replicacion.setEstatus("9.FINALIZADA CON ERROR");
				replicacion.setFechaFin(Utils
						.ObtenerFechaActual(Utils.formatoFechaCortaHoraLarga));
				replicacionData
						.guardaReplicacion(replicacion, "Actualizar");

				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-15. No existe ruta origen en servidor BD para generacion de archivos."
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(),"", replicacion
								.getIdReplicacion().intValue(), "");
				return 1;
			}
			for (ReplicacionDetalle replicacionDetalle : replicacionDetalles) {
				try {
					File origen = new File(rutaServer
							+ replicacionDetalle.getNombreArchivo());
					File destino = new File(ruta
							+ replicacionDetalle.getNombreArchivo());
					inStream = new FileInputStream(origen);
					outStream = new FileOutputStream(destino);
					int length;
					// copy the file content in bytes
					while ((length = inStream.read(buffer)) > 0) {
						outStream.write(buffer, 0, length);
					}
					inStream.close();
					outStream.close();

					File borrar = new File(rutaServer
							+ replicacionDetalle.getNombreArchivo());
					borrar.delete();

				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();

					detalleData.guardaEventoReplicacion(
							replicacionDetalle,
							"ActualizarError",
							"9.FINALIZADA CON ERROR",
							"Error al copiar archivo desde servidor de "
									+ replicacionDetalle
											.getIdReplicacionTabla()
									+ ".TIPO REPLICACION:"
									+ replicacion.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ replicacion.getIdReplicacion());
					// TODO validar con jorge torner
					replicacion.setEstatus("9.FINALIZADA CON ERROR");
					replicacion.setFechaFin(Utils
							.ObtenerFechaActual(Utils.formatoFechaCortaHoraLarga));
					replicacionData
							.guardaReplicacion(replicacion, "Actualizar");

					Utils.guardarLogReplicacionMensajeParams(
							"UCS022_1: Replicación LDC-OTSe",
							"M-15. Error al copiar archivos desde servidor de base de datos. TIPO REPLICACION:"
									+ replicacion.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ replicacion.getIdReplicacion(), e
									.getMessage(), replicacion
									.getIdReplicacion().intValue(), "");
					return 1;
				}
			}
		}
		logger.info("generaArchivosReplicacion:"
				+ replicacion.getIdReplicacion()
				+ "  : Finaliza Copiando archivos desde BD");
		try {
			SecretKey key = KeyGenerator.getInstance("DES").generateKey();
			EncriptadorDES encrypter = new EncriptadorDES(key);
			secretKey = key.getEncoded();
			// Inicia encriptacion de archivos

			for (ReplicacionDetalle replicacionDetalle : replicacionDetalles) {
				try {

					encrypter.encrypt(new FileInputStream(ruta
							+ replicacionDetalle.getNombreArchivo()),
							new FileOutputStream(ruta
									+ replicacionDetalle.getNombreArchivo()
											.replaceAll("txt", "enc")));
					detalleData.guardaEventoReplicacion(
							replicacionDetalle,
							"Actualizar",
							"3.EMPAQUETANDO",
							"EMPAQUETANDO"
									+ ".TIPO REPLICACION:"
									+ replicacion.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ replicacion.getIdReplicacion()
									+ ".REPLICACION DETALLE:"
									+ replicacionDetalle
											.getIdReplicacionTabla());
					// mandar a actualizar estatus
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
					replicacion.setEstatus("9.FINALIZADA CON ERROR");
					replicacionData
							.guardaReplicacion(replicacion, "Actualizar");
					detalleData.guardaEventoReplicacion(
							replicacionDetalle,
							"ActualizarError",
							"9.FINALIZADA CON ERROR",
							"Error al encriptar archivo de "
									+ replicacionDetalle
											.getIdReplicacionTabla()
									+ ".TIPO REPLICACION:"
									+ replicacion.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ replicacion.getIdReplicacion());
					Utils.guardarLogReplicacionMensajeParams(
							"UCS022_1: Replicación LDC-OTSe",
							"M-6. Error al encriptar archivo de "
									+ replicacionDetalle
											.getIdReplicacionTabla()
									+ ".TIPO REPLICACION:"
									+ replicacion.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ replicacion.getIdReplicacion(), e
									.getMessage(), replicacion
									.getIdReplicacion().intValue(), "");
					return 1;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
			e.printStackTrace();
			// Replicaicon y log general
			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación LDC-OTSe",
					"M-6. Error de algoritmo al encriptar archivo  "
							+ ".TIPO REPLICACION:"
							+ replicacion.getTipoReplicacion()
							+ ".REPLICACION ID:"
							+ replicacion.getIdReplicacion(), e.getMessage(),
					replicacion.getIdReplicacion().intValue(), "");
			return 1;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación LDC-OTSe",
					"M-6. Error general al encriptar archivo  "
							+ ".TIPO REPLICACION:"
							+ replicacion.getTipoReplicacion()
							+ ".REPLICACION ID:"
							+ replicacion.getIdReplicacion(), e.getMessage(),
					replicacion.getIdReplicacion().intValue(), "");
			return 1;
		}
		// Comprimiendo archivos
		int contArchivosZip = 0;
		int bandera = 0;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		long banderaMax = 0;
		archivosZip = new ArrayList<String>();
		logger.info("generaArchivosReplicacion:"
				+ replicacion.getIdReplicacion()
				+ "  : Inicia comprimiendo archivos");
		for (ReplicacionDetalle replicacionDetalle : replicacionDetalles) {

			try {
				// Generar zip
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				if (bandera == 0) {
					contArchivosZip++;

					fos = new FileOutputStream(ruta
							+ replicacion.getIdReplicacion().toString()
							+ String.valueOf(contArchivosZip)
							+ df.format(new Date()) + ".zip");
					archivosZip.add(replicacion.getIdReplicacion().toString()
							+ String.valueOf(contArchivosZip)
							+ df.format(new Date()) + ".zip");
					zos = new ZipOutputStream(fos);
					bandera = 1;
				}
				replicacionDetalle.setNombreZIP(replicacion.getIdReplicacion()
						.toString()
						+ String.valueOf(contArchivosZip)
						+ df.format(new Date()) + ".zip");

				// Agregando entradas (entrada por archivo) al zip
				ZipEntry ze = new ZipEntry(replicacionDetalle
						.getNombreArchivo().replaceAll("txt", "enc"));
				zos.putNextEntry(ze);
				FileInputStream in = new FileInputStream(ruta
						+ replicacionDetalle.getNombreArchivo().replaceAll(
								"txt", "enc"));

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
					banderaMax++;
				}

				in.close();
				detalleData.guardaEventoReplicacion(
						replicacionDetalle,
						"Actualizar",
						"4.ENVIANDO",
						"ENVIANDO" + ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion()
								+ ".REPLICACION DETALLE:"
								+ replicacionDetalle.getIdReplicacionTabla());
				// banderaMax indica el numero de k agregados al zip. ya que la
				// lectura es cada 1024 bytes
				// 1.60 tamaño de compresion hay que validar
				if ((banderaMax / 1024) > tamanoArchivos * porcentajeCompresion) {
					banderaMax = 0;
					bandera = 0;
					zos.closeEntry();
					zos.close();
				}

				// borrar archivos txt y enc

				File borrar = new File(ruta
						+ replicacionDetalle.getNombreArchivo());
				borrar.delete();
				borrar = new File(ruta
						+ replicacionDetalle.getNombreArchivo().replaceAll(
								".txt", ".enc"));
				borrar.delete();
			} catch (FileNotFoundException fne) {
				logger.error(fne);
				fne.printStackTrace();
				detalleData.guardaEventoReplicacion(
						replicacionDetalle,
						"ActualizarError",
						"9.FINALIZADA CON ERROR",
						"Error al comprimir archivo(s) de "
								+ replicacionDetalle.getIdReplicacionTabla()
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion());
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-7 Error al comprimir archivo(s) de "
								+ replicacionDetalle.getIdReplicacionTabla()
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), fne
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
				return 1;
			} catch (IOException ioe) {
				logger.error(ioe);
				ioe.printStackTrace();
				detalleData.guardaEventoReplicacion(
						replicacionDetalle,
						"ActualizarError",
						"9.FINALIZADA CON ERROR",
						"Error al comprimir archivo(s) de "
								+ replicacionDetalle.getIdReplicacionTabla()
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion());
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-7 Error al comprimir archivo(s) de "
								+ replicacionDetalle.getIdReplicacionTabla()
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), ioe
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
				return 1;
			}
		}
		if (zos != null) {
			try {
				zos.closeEntry();
				zos.close();
			} catch (IOException ioe) {
				logger.error(ioe);
				ioe.printStackTrace();
				// Cierre de stream en caso de que al agregar no se haya
				// superado el limite
			}
		}
		logger.info("generaArchivosReplicacion:"
				+ replicacion.getIdReplicacion()
				+ "  : Finaliza comprimiendo archivos");
		logger.info("Sale metodo generaArchivosReplicacion:"
				+ replicacion.getIdReplicacion());
		return 0;
	}

	/**
	 * Sube archivos a servidor y procesa respuesta de servicio rest
	 * 
	 * @author carlos.pantoja
	 * @date 18/01/2014
	 * 
	 * @param replicacion
	 *            replicacion a subir
	 * @return 0 exito - 1 error
	 */
	public Integer uploadFiles(Replicacion replicacion) {

		int intentos;
		int exito;
		String ruta = rutaReplicacion + SUB_DIRECTORIO;
		logger.info("Entra metodo uploadFiles:" + replicacion.toString());

		for (String zips : archivosZip) {
			List<ReplicacionDetalle> listaCarga = new ArrayList<ReplicacionDetalle>();

			for (ReplicacionDetalle replicacionDetalle : replicacionDetalles) {
				if (replicacionDetalle.getNombreZIP().equals(zips)) {
					listaCarga.add(replicacionDetalle);
				}
			}
			long checksumL = 0;
			final File file = new File(ruta + zips);
			logger.info("Entra metodo uploadFiles:" + replicacion.toString()
					+ " Inicia generando checksum.");
			try {
				checksumL = FileUtils.checksumCRC32(file);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				replicacion.setEstatus("9.FINALIZADA CON ERROR");
				replicacionData.guardaReplicacion(replicacion, "Actualizar");
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-9 Error al generar checksum" + ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), e
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
				return 1;
			}
			logger.info("Entra metodo uploadFiles:" + replicacion.toString()
					+ " Finaliza generando checksum.");
			Replicacion paraReplicacion = replicacionData
					.obtieneReplicacion(replicacion);

			ReqReplicacionUploadZip replicacionUploadZip = new ReqReplicacionUploadZip(
					replicacion.getLdc(), checksumL, zips, paraReplicacion,
					listaCarga);
			replicacionUploadZip.setSecretKey(secretKey);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();

			FileBody fb = new FileBody(file);
			builder.addPart("content", fb);
			builder.addTextBody("datos", UtilRest.toJson(replicacionUploadZip));
			HttpClient cliente = getCliente();
			HttpPost target = new HttpPost(URL_REPLICACION
					+ "ReplicacionUploadFiles");
			final HttpEntity yourEntity = builder.build();
			target.setEntity(yourEntity);
			intentos = 1;
			exito = 1;
			logger.info("Entra metodo uploadFiles:" + replicacion.toString()
					+ " Inicia Envio archivos.");
			logger.info("Entra metodo uploadFiles:" + URL_REPLICACION
					+ "ReplicacionUploadFiles "
					+ UtilRest.toJson(replicacionUploadZip));

			do {
				try {

					HttpResponse response = cliente.execute(target);
					ResReplicacion resReplicacion = (ResReplicacion) UtilRest
							.procesaMapRespuesta(response, ResReplicacion.class);
					if (!resReplicacion.getClave().equals("OK")) {
						intentos++;
					} else {
						exito = 0;
					}

					if (intentos > numeroReintentos) {
						for (ReplicacionDetalle replicacionDetalle : replicacionUploadZip
								.getReplicacionDetalles()) {
							detalleData.guardaEventoReplicacion(
									replicacionDetalle,
									"ActualizarError",
									"9.FINALIZADA CON ERROR",
									"Error al enviar archivo " + zips
											+ resReplicacion.getMensaje()
											+ ".TIPO REPLICACION:"
											+ replicacion.getTipoReplicacion()
											+ ".REPLICACION ID:"
											+ replicacion.getIdReplicacion());

						}
						return 1;
					}

				} catch (IOException io) {
					logger.error(io);
					io.printStackTrace();
					exito = 0;
					Utils.guardarLogReplicacionMensajeParams(
							"UCS022_1: Replicación LDC-OTSe",
							"M-10 Error al comunicarse con servidor envio archivos"
									+ ".TIPO REPLICACION:"
									+ replicacion.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ replicacion.getIdReplicacion(), io
									.getMessage(), replicacion
									.getIdReplicacion().intValue(), "");
					return 1;
				}

			} while (exito == 1);
			// Borrar archivo
			File borrar = new File(ruta + zips);
			borrar.delete();
			logger.info("Entra metodo uploadFiles:" + replicacion.toString()
					+ " Finaliza Envio archivos.");
			// Actualiza estatus replicacion subida.

			replicacion.setEstatus("2.CARGANDO");
			HttpClient clienteReplicacion = getCliente();
			HttpPost targetReplicacion = new HttpPost(URL_REPLICACION
					+ "ReplicacionEstatusSubidaService");
			logger.info("Entra metodo uploadFiles:" + replicacion.toString()
					+ " Inicializa Actualizacion Estatus replicacion.");
			StringEntity input = null;
			ObjectMapper mapper = new ObjectMapper();
			try {

				ReqActualizaEstatusReplicacionSubida actualizaEstatusReplicacionSubida = new ReqActualizaEstatusReplicacionSubida();
				actualizaEstatusReplicacionSubida.setReplicacion(replicacion);
				logger.info("Entra metodo uploadFiles:" + URL_REPLICACION
						+ "ReplicacionEstatusSubidaService : "
						+ UtilRest.toJson(actualizaEstatusReplicacionSubida));

				input = new StringEntity(
						mapper.writeValueAsString(actualizaEstatusReplicacionSubida));
				input.setContentType("application/json");
			} catch (JsonProcessingException je) {
				logger.error(je);
				je.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-5 Error al solicitar actualizacion de la replicacion a el servidor. "
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), je
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
			} catch (UnsupportedEncodingException ue) {
				logger.error(ue);
				ue.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-5 Error al solicitar actualizacion de la replicacion a el servidor. "
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), ue
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
			}

			HttpResponse responseSubida;
			try {
				targetReplicacion.setEntity(input);
				responseSubida = clienteReplicacion.execute(targetReplicacion);
				ResReplicacion resReplicacionSubida = (ResReplicacion) UtilRest
						.procesaMapRespuesta(responseSubida,
								ResReplicacion.class);

				if (!resReplicacionSubida.getClave().equals("OK")) {
					Utils.guardarLogReplicacionMensajeParams(
							"UCS022_1: Replicación LDC-OTSe",
							"M-5 Error al actualizar la replicacion a el servidor. "
									+ ".TIPO REPLICACION:"
									+ replicacion.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ replicacion.getIdReplicacion(), "",
							replicacion.getIdReplicacion().intValue(), "");
				}

			} catch (ClientProtocolException e) {
				logger.error(e);
				e.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-5 Error al actualizar la replicacion a el servidor. "
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), e
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
			} catch (IOException e) {
				logger.error(e);
				e.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-5 Error al actualizar la replicacion a el servidor. "
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), e
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
			}

			for (ReplicacionDetalle replicacionDetalle : replicacionUploadZip
					.getReplicacionDetalles()) {
				detalleData.guardaEventoReplicacion(
						replicacionDetalle,
						"Actualizar",
						"7.ESPERANDO CARGA",
						"ESPERANDO CARGA"
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion()
								+ ".REPLICACION DETALLE:"
								+ replicacionDetalle.getIdReplicacionTabla());
			}

		}
		logger.info("Sale metodo uploadFiles:" + replicacion.toString());
		return 0;
	}

	/**
	 * Consulta estatus replicacion servidor y actualiza en bd
	 * 
	 * @author carlos.pantoja
	 * @date 18/01/2014
	 * @param replicacion
	 *            replicacion a cosultar
	 */
	public void actualizaEstatusReplicacion(Replicacion replicacion) {
		ObjectMapper mapper = new ObjectMapper();
		String resultado = "";

		logger.info("Entra metodo actualizaEstatusReplicacion:"
				+ replicacion.toString());

		ResReplicacionEstatusDTO res = new ResReplicacionEstatusDTO();
		ResReplicacionEstatusDTO resant = new ResReplicacionEstatusDTO();

		int contadorCambios = 0;
		while (!resultado.equals("3.FINALIZADA")
				&& !resultado.equals("4.FINALIZADA CON ERROR")) {
			try {

				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					logger.error(e);
					e.printStackTrace();
				}

				HttpPost target = new HttpPost(URL_REPLICACION
						+ "ReplicacionEstatusConsultaService");
				StringEntity input = null;
				try {
					input = new StringEntity(
							mapper.writeValueAsString(replicacion));

					logger.info("Entra metodo uploadFiles:" + URL_REPLICACION
							+ "ReplicacionEstatusConsultaService : "
							+ UtilRest.toJson(replicacion));

					input.setContentType("application/json");
				} catch (JsonProcessingException je) {
					logger.error(je);
					je.printStackTrace();
					Utils.guardarLogReplicacionMensajeParams(
							"UCS022_1: Replicación LDC-OTSe",
							"M-5 Error al solicitar actualizacion de replicacion desde el servidor. "
									+ ".TIPO REPLICACION:"
									+ replicacion.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ replicacion.getIdReplicacion(), je
									.getMessage(), replicacion
									.getIdReplicacion().intValue(), "");
				} catch (UnsupportedEncodingException ue) {
					logger.error(ue);
					ue.printStackTrace();
					Utils.guardarLogReplicacionMensajeParams(
							"UCS022_1: Replicación LDC-OTSe",
							"M-5 Error al solicitar actualizacion de replicacion desde el servidor. "
									+ ".TIPO REPLICACION:"
									+ replicacion.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ replicacion.getIdReplicacion(), ue
									.getMessage(), replicacion
									.getIdReplicacion().intValue(), "");
				}
				target.setEntity(input);
				HttpClient cliente = getCliente();

				HttpResponse response = cliente.execute(target);
				resant = res.copy(res);
				res = (ResReplicacionEstatusDTO) UtilRest.procesaMapRespuesta(
						response, ResReplicacionEstatusDTO.class);
				replicacion.setAvance(res.getReplicacionDTO().getAvance());
				replicacion.setEstatus(res.getReplicacionDTO().getEstatus());
				replicacion.setFechaFin(res.getReplicacionDTO().getFechaFin());
				replicacion.setLogEjecucion(res.getReplicacionDTO()
						.getLogEjecucion());
				replicacionData.guardaReplicacion(replicacion, "Actualizar");
				resultado = res.getReplicacionDTO().getEstatus();
				if (resultado == null) {
					resultado = "";
				}
				for (ReplicacionDetalle replicacionDetalle : res
						.getDetalleDTOs()) {
					detalleData.guardaEventoReplicacion(replicacionDetalle,
							"Actualizar", replicacionDetalle.getEstatus(),
							replicacionDetalle.getLogEjecucion());
				}
				if (!resultado.equals("3.FINALIZADA")
						&& !resultado.equals("4.FINALIZADA CON ERROR")) {
					if (resant.getReplicacionDTO().getAvance()
							.equals(res.getReplicacionDTO().getAvance())) {
						contadorCambios++;
					} else {
						contadorCambios = 0;
					}
				}

				if (contadorCambios >= reintentosEstatus) {
					replicacion.setEstatus("4.DETENIDA CON ERROR");
					replicacion
							.setLogEjecucion("Error en replicacion: No se obtuvo avance de carga de archivos en servidor central. Revisar log en servidor central."
									+ ".TIPO REPLICACION:"
									+ replicacion.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ replicacion.getIdReplicacion());
					replicacionData.guardaReplicacion(replicacion,
							"ActualizarError");
					resultado = "3.FINALIZADA";
				}

			} catch (IOException ie) {
				logger.error(ie);
				ie.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-5 Error al obtener actualizacion de replicacion desde el servidor. "
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), ie
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
			}
		}

		logger.info("Finaliza metodo actualizaEstatusReplicacion:"
				+ replicacion.toString());

	}

	/**
	 * Incian metodos para la replicacion de bajada
	 */

	/**
	 * Metodo para obtener un listado de tablas candidatas a respaldar de bajada
	 * 
	 * @author carlos.pantoja
	 * @date 07/02/2014
	 * @return Listado de tablas asociadas al id cliente para respaldar desde el
	 *         server
	 */
	public List<ReplicacionTabla> getReplicacionBajadaTabla() {
		return data.getReplicacionBajadaTabla();
	}

	/**
	 * Envia la solicitud para la generacion de archivos en el servidor y
	 * recupera una lista de archivos generados.
	 * 
	 * @return 0 exito 1 error
	 */
	public Integer solicitaArchivosTablas(ReqReplicacionBajada bajada) {
		ObjectMapper mapper = new ObjectMapper();
		HttpClient cliente = getCliente();
		HttpPost target = new HttpPost(URL_REPLICACION
				+ "ReplicacionTablasBajada");
		StringEntity input = null;
		logger.info("Entra metodo solicitaArchivosTablas");
		ResReplicacionTablasBajada resReplicacionTablasBajada = new ResReplicacionTablasBajada();
		try {

			logger.info("Entra metodo solicitaArchivosTablas:"
					+ URL_REPLICACION + "ReplicacionTablasBajada : "
					+ mapper.writeValueAsString(bajada));

			input = new StringEntity(mapper.writeValueAsString(bajada));
			input.setContentType("application/json");
			logger.info("Metodo solicitaArchivosTablas: Después de input");
		} catch (JsonProcessingException je) {
			logger.info("Metodo solicitaArchivosTablas: Entra a error 1");
			logger.error(je);
			je.printStackTrace();
			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación EOTS->LDC",
					"M-5 Error al obtener actualizacion de replicacion desde el servidor. "
							+ ".TIPO REPLICACION:"
							+ bajada.getReplicacion().getTipoReplicacion()
							+ ".REPLICACION ID:"
							+ bajada.getReplicacion().getIdReplicacion(),
					je.getMessage(), bajada.getReplicacion().getIdReplicacion()
							.intValue(), "");
			return 1;
		} catch (UnsupportedEncodingException ue) {
			logger.info("Metodo solicitaArchivosTablas: Entra a error 2");
			logger.error(ue);
			ue.printStackTrace();
			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación EOTS->LDC",
					"M-5 Error al obtener actualizacion de replicacion desde el servidor. "
							+ ".TIPO REPLICACION:"
							+ bajada.getReplicacion().getTipoReplicacion()
							+ ".REPLICACION ID:"
							+ bajada.getReplicacion().getIdReplicacion(),
					ue.getMessage(), bajada.getReplicacion().getIdReplicacion()
							.intValue(), "");
			return 1;
		}

		target.setEntity(input);
		logger.info("Metodo solicitaArchivosTablas: Después de target");

		try {
			HttpResponse response = cliente.execute(target);
			logger.info("Metodo solicitaArchivosTablas: Después de execute del cliente");
			resReplicacionTablasBajada = (ResReplicacionTablasBajada) UtilRest
					.procesaMapRespuesta(response,
							ResReplicacionTablasBajada.class);
			logger.info("Metodo solicitaArchivosTablas: Después de obtener el response: "
					+ resReplicacionTablasBajada.toString());
			if (resReplicacionTablasBajada != null
					&& resReplicacionTablasBajada.getArchivosDescarga() != null) {
				generadosServer = resReplicacionTablasBajada
						.getArchivosDescarga();
			} else {
				return 1;
			}

		} catch (IOException ie) {
			logger.info("Metodo solicitaArchivosTablas: Entra a error 3");
			logger.error(ie);
			ie.printStackTrace();
			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación EOTS->LDC",
					"M-5 Error al obtener actualizacion de replicacion desde el servidor. "
							+ ".TIPO REPLICACION:"
							+ bajada.getReplicacion().getTipoReplicacion()
							+ ".REPLICACION ID:"
							+ bajada.getReplicacion().getIdReplicacion(),
					ie.getMessage(), 0, "");
			return 1;
		}
		logger.info("Sale metodo solicitaArchivosTablas");
		return 0;

	}

	public Integer procesaArchivosBajada(Replicacion replicacion) {
		byte[] bytes = new byte[1024];
		List<ReplicacionDetalle> detallesServer = new ArrayList<ReplicacionDetalle>();
		logger.info("Entra metodo procesaArchivosBajada");
		if (generadosServer.size() == 0) {
			logger.info("Finalizada sin archivos:" + replicacion.toString());
			replicacion.setEstatus("3.FINALIZADA");
			replicacion.setFechaFin(Utils
					.ObtenerFechaActual(Utils.formatoFechaCortaHoraLarga));
			replicacionData.guardaReplicacion(replicacion, "Actualizar");

			HttpClient clienteReplicacion = getCliente();
			HttpPost targetReplicacion = new HttpPost(URL_REPLICACION
					+ "ReplicacionEstatusSubidaService");

			StringEntity input = null;
			ObjectMapper mapper = new ObjectMapper();
			try {
				ReqActualizaEstatusReplicacionSubida actualizaEstatusReplicacionSubida = new ReqActualizaEstatusReplicacionSubida();
				actualizaEstatusReplicacionSubida.setReplicacion(replicacion);
				input = new StringEntity(
						mapper.writeValueAsString(actualizaEstatusReplicacionSubida));

				logger.info("Entra metodo solicitaArchivosTablas:"
						+ URL_REPLICACION
						+ "ReplicacionEstatusSubidaService : "
						+ mapper.writeValueAsString(actualizaEstatusReplicacionSubida));

				input.setContentType("application/json");
			} catch (JsonProcessingException je) {
				logger.error(je);
				je.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación EOTS->LDC",
						"M-5 Error al actualizar la replicacion a el servidor. "
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), je
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
			} catch (UnsupportedEncodingException ue) {
				logger.error(ue);
				ue.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación EOTS->LDC",
						"M-5 Error al actualizar la replicacion a el servidor. "
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), ue
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
			}

			HttpResponse responseSubida;
			try {
				targetReplicacion.setEntity(input);
				responseSubida = clienteReplicacion.execute(targetReplicacion);
				ResReplicacion resReplicacionSubida = (ResReplicacion) UtilRest
						.procesaMapRespuesta(responseSubida,
								ResReplicacion.class);

				if (!resReplicacionSubida.getClave().equals("OK")) {
					Utils.guardarLogReplicacionMensajeParams(
							"UCS022_1: Replicación EOTS->LDC",
							"M-5 Error al actualizar la replicacion a el servidor. "
									+ ".TIPO REPLICACION:"
									+ replicacion.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ replicacion.getIdReplicacion(), "",
							replicacion.getIdReplicacion().intValue(), "");
				}

			} catch (ClientProtocolException e) {
				logger.error(e);
				e.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación EOTS->LDC",
						"M-5 Error al actualizar la replicacion a el servidor. "
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), e
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
			} catch (IOException e) {
				logger.error(e);
				e.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación EOTS->LDC",
						"M-5 Error al actualizar la replicacion a el servidor. "
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), e
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
			}

			return 1;
		}

		for (ReqReplicacionUploadZip uploadZip : generadosServer) {
			detallesServer.addAll(uploadZip.getReplicacionDetalles());
		}

		for (ReplicacionDetalle detalle : detallesServer) {
			detalleData.guardaEventoReplicacion(
					detalle,
					"InsertarReplicado",
					"5.DESEMPAQUETANDO",
					"Error al guardar evento replicacion. DESEMPAQUETANDO"
							+ ".TIPO REPLICACION: EOTS->LDC"

							+ ".REPLICACION ID:" + detalle.getIdReplicacion()
							+ ".REPLICACION DETALLE:"
							+ detalle.getIdReplicacionTabla());
		}
		logger.info("Entra metodo solicitaArchivosTablas:"
				+ "Solicita archivos bajada: " + replicacion.toString());
		for (ReqReplicacionUploadZip uploadZip : generadosServer) {
			int intentos = 1;
			int exito = 1;
			do {
				try {
					ObjectMapper mapper = new ObjectMapper();
					HttpClient cliente = getCliente();
					HttpPost target = new HttpPost(URL_REPLICACION
							+ "ReplicacionTablasObtieneArchivo");
					StringEntity input = null;
					logger.info("Entra metodo solicitaArchivosTablas:"
							+ URL_REPLICACION
							+ "ReplicacionTablasObtieneArchivo : "
							+ mapper.writeValueAsString(uploadZip));
					input = new StringEntity(
							mapper.writeValueAsString(uploadZip));
					input.setContentType("application/json");

					target.setEntity(input);
					// Obtener archivo

					HttpResponse response = cliente.execute(target);

					OutputStream archivoSalida = new FileOutputStream(
							rutaReplicacion + SUB_DIRECTORIO_RECIBIR
									+ uploadZip.getNombreArchivo());
					int read = 0;
					while ((read = response.getEntity().getContent()
							.read(bytes)) != -1) {
						archivoSalida.write(bytes, 0, read);
					}
					archivoSalida.close();

					// Validar Checksum

					File archivo = new File(rutaReplicacion
							+ SUB_DIRECTORIO_RECIBIR
							+ uploadZip.getNombreArchivo());

					long checksumL = 0;
					checksumL = FileUtils.checksumCRC32(archivo);
					if (checksumL != uploadZip.getCheckSum()) {
						Utils.guardarLogReplicacionMensajeParams(
								"UCS022_1: Replicación EOTS->LDC",
								"M-21 Error checksum diferente al servidor."
										+ ".TIPO REPLICACION: EOTS->LDC"

										+ ".REPLICACION ID:"
										+ uploadZip.getReplicacion()
												.getIdReplicacion()
										+ ".Archivo:" + rutaReplicacion
										+ SUB_DIRECTORIO_RECIBIR
										+ uploadZip.getNombreArchivo(), "",
								uploadZip.getReplicacion().getIdReplicacion()
										.intValue(), "");
						intentos++;
					} else {
						ZipInputStream zis = new ZipInputStream(
								new FileInputStream(rutaReplicacion
										+ SUB_DIRECTORIO_RECIBIR
										+ uploadZip.getNombreArchivo()));

						ZipEntry ze = zis.getNextEntry();
						while (ze != null) {
							String fileName = ze.getName();
							File newFile = new File(rutaReplicacion
									+ SUB_DIRECTORIO_RECIBIR + File.separator
									+ fileName);
							System.out.println("file unzip : "
									+ newFile.getAbsoluteFile());

							FileOutputStream fos = new FileOutputStream(newFile);
							int len;
							while ((len = zis.read(bytes)) > 0) {
								fos.write(bytes, 0, len);
							}

							fos.close();
							ze = zis.getNextEntry();
						}
						zis.closeEntry();
						zis.close();

						exito = 0;
					}
					if (intentos > numeroReintentos) {
						exito = 0;
					}

				} catch (JsonProcessingException je) {
					logger.error(je);
					je.printStackTrace();
					Utils.guardarLogReplicacionMensajeParams(
							"UCS022_1: Replicación EOTS->LDC",
							"M-5 Error al recuperar archivo desde el servidor. "
									+ ".TIPO REPLICACION:"
									+ uploadZip.getReplicacion()
											.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ uploadZip.getReplicacion()
											.getIdReplicacion() + "ARchivo"
									+ uploadZip.getNombreArchivo(), je
									.getMessage(), uploadZip.getReplicacion()
									.getIdReplicacion().intValue(), "");
					intentos++;
				} catch (UnsupportedEncodingException ue) {
					logger.error(ue);
					ue.printStackTrace();
					Utils.guardarLogReplicacionMensajeParams(
							"UCS022_1: Replicación EOTS->LDC",
							"M-5 Error al recuperar archivo desde el servidor. "
									+ ".TIPO REPLICACION:"
									+ uploadZip.getReplicacion()
											.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ uploadZip.getReplicacion()
											.getIdReplicacion() + "ARchivo"
									+ uploadZip.getNombreArchivo(), ue
									.getMessage(), uploadZip.getReplicacion()
									.getIdReplicacion().intValue(), "");
					intentos++;
				} catch (IOException ie) {
					logger.error(ie);
					ie.printStackTrace();
					Utils.guardarLogReplicacionMensajeParams(
							"UCS022_1: Replicación EOTS->LDC",
							"M-5 Error al recuperar archivo desde el servidor. "
									+ ".TIPO REPLICACION:"
									+ uploadZip.getReplicacion()
											.getTipoReplicacion()
									+ ".REPLICACION ID:"
									+ uploadZip.getReplicacion()
											.getIdReplicacion() + "ARchivo"
									+ uploadZip.getNombreArchivo(), ie
									.getMessage(), uploadZip.getReplicacion()
									.getIdReplicacion().intValue(), "");
					intentos++;
				}

			} while (exito == 1);
			if (intentos > numeroReintentos) {
				for (ReplicacionDetalle detalle : detallesServer) {
					detalleData.guardaEventoReplicacion(detalle,
							"ActualizarError", "4.DETENIDA CON ERROR",
							"Numero de intentos superado. Error al obtener archivos para replicación"
									+ uploadZip.getReplicacion()
											.getIdReplicacion()
									+ ".TIPO REPLICACION:"
									+ uploadZip.getReplicacion()
											.getTipoReplicacion());
				}
				return 1;
			}
			logger.info("Entra metodo solicitaArchivosTablas:"
					+ "Finaliza archivos bajada: " + replicacion.toString());
			// Desencriptando
			logger.info("Entra metodo solicitaArchivosTablas:"
					+ "Inicializa desencriptado archivos: "
					+ replicacion.toString());
			for (ReplicacionDetalle detalle : uploadZip
					.getReplicacionDetalles()) {
				detalleData.guardaEventoReplicacion(
						detalle,
						"Actualizar",
						"6.DESENCRIPTANDO",
						"Error al guardar evento replicacion. DESENCRIPTANDO"
								+ ".TIPO REPLICACION:"
								+ uploadZip.getReplicacion()
										.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ uploadZip.getReplicacion().getIdReplicacion()
								+ ".REPLICACION DETALLE:"
								+ detalle.getIdReplicacionTabla());
			}

			String files;
			File folder = new File(rutaReplicacion + SUB_DIRECTORIO_RECIBIR);
			File[] listOfFiles = folder.listFiles();

			try {
				SecretKeySpec key = new SecretKeySpec(uploadZip.getSecretKey(),
						"DES");
				EncriptadorDES encrypter = new EncriptadorDES(key);
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						files = listOfFiles[i].getName();
						if (files.endsWith(".enc") || files.endsWith(".ENC")) {
							encrypter.decrypt(new FileInputStream(
									rutaReplicacion + SUB_DIRECTORIO_RECIBIR
											+ files), new FileOutputStream(
									rutaReplicacion + SUB_DIRECTORIO_RECIBIR
											+ files.replaceAll("enc", "txt")));

							// Borrar archivo enc
							File fileDelete = new File(rutaReplicacion
									+ SUB_DIRECTORIO_RECIBIR + files);
							fileDelete.delete();
						}
					}
				}
			} catch (NoSuchAlgorithmException e) {
				logger.error(e);
				e.printStackTrace();
				// Replicaicon y log general
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación EOTS->LDC",
						"M-6. Error de algoritmo al encriptar archivo  "
								+ ".TIPO REPLICACION:"
								+ uploadZip.getReplicacion()
										.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ uploadZip.getReplicacion().getIdReplicacion()
								+ "ARchivo" + uploadZip.getNombreArchivo(), e
								.getMessage(), uploadZip.getReplicacion()
								.getIdReplicacion().intValue(), "");
				uploadZip.getReplicacion().setEstatus("3.DETENIDA CON ERROR");
				uploadZip
						.getReplicacion()
						.setLogEjecucion(
								"Error en replicación: Error al desencriptar archivos.");
				replicacionData.guardaReplicacion(uploadZip.getReplicacion(),
						"ActualizarError");
				return 1;
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación EOTS->LDC",
						"M-6. Error al desencriptar archivo  "
								+ ".TIPO REPLICACION:"
								+ uploadZip.getReplicacion()
										.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ uploadZip.getReplicacion().getIdReplicacion()
								+ "ARchivo" + uploadZip.getNombreArchivo(), e
								.getMessage(), uploadZip.getReplicacion()
								.getIdReplicacion().intValue(), "");
				uploadZip.getReplicacion().setEstatus("3.DETENIDA CON ERROR");
				uploadZip
						.getReplicacion()
						.setLogEjecucion(
								"Error en replicación: Error al desencriptar archivos.");
				replicacionData.guardaReplicacion(uploadZip.getReplicacion(),
						"ActualizarError");
				return 1;
			}
			logger.info("Entra metodo solicitaArchivosTablas:"
					+ "Finaliza desencriptado archivos: "
					+ replicacion.toString());
			// Copiar archvivos desde servidor de base de datos
			String ruta = rutaReplicacion + SUB_DIRECTORIO_RECIBIR;
			String rutaServer = Replicacion_RutaLDC_ArchivosBD
					+ SUB_DIRECTORIO_RECIBIR;
			logger.info("Entra metodo solicitaArchivosTablas:"
					+ "Incializa copiado archivos a servidor BD: "
					+ replicacion.toString());
			if (!Replicacion_RutaLDC_ArchivosBD.equals("")) {
				InputStream inStream = null;
				OutputStream outStream = null;
				for (ReplicacionDetalle replicacionDetalle : uploadZip
						.getReplicacionDetalles()) {
					try {
						File origen = new File(ruta
								+ replicacionDetalle.getNombreArchivo());
						File destino = new File(rutaServer
								+ replicacionDetalle.getNombreArchivo());
						inStream = new FileInputStream(origen);
						outStream = new FileOutputStream(destino);
						int length;
						// copy the file content in bytes
						while ((length = inStream.read(bytes)) > 0) {
							outStream.write(bytes, 0, length);
						}
						inStream.close();
						outStream.close();

						origen.delete();

						File origenenc = new File(ruta
								+ replicacionDetalle.getNombreArchivo()
										.replaceAll("txt", "enc"));
						origenenc.delete();
					} catch (Exception e) {
						logger.error(e);
						e.printStackTrace();
						detalleData.guardaEventoReplicacion(
								replicacionDetalle,
								"ActualizarError",
								"9.FINALIZADA CON ERROR",
								"Error al copiar archivo a el servidor BD de "
										+ replicacionDetalle
												.getIdReplicacionTabla()
										+ ".TIPO REPLICACION:"
										+ uploadZip.getReplicacion()
												.getTipoReplicacion()
										+ ".REPLICACION ID:"
										+ uploadZip.getReplicacion()
												.getIdReplicacion() + "ARchivo"
										+ uploadZip.getNombreArchivo());
						Utils.guardarLogReplicacionMensajeParams(
								"UCS022_1: Replicación LDC-OTSe",
								"M-15 Error al copiar archivo a el servidor BD de "
										+ replicacionDetalle
												.getIdReplicacionTabla()
										+ ".TIPO REPLICACION:"
										+ uploadZip.getReplicacion()
												.getTipoReplicacion()
										+ ".REPLICACION ID:"
										+ uploadZip.getReplicacion()
												.getIdReplicacion() + "ARchivo"
										+ uploadZip.getNombreArchivo(), e
										.getMessage(), replicacionDetalle
										.getIdReplicacion().intValue(), "");
						return 1;
					}
				}
			}
			logger.info("Entra metodo solicitaArchivosTablas:"
					+ "Finaliza copiado archivos a servidor BD: "
					+ replicacion.toString());
			for (ReplicacionDetalle detalle : uploadZip
					.getReplicacionDetalles()) {
				detalleData.guardaEventoReplicacion(
						detalle,
						"Actualizar",
						"7.ESPERANDO CARGA",
						"Error al guardar evento replicacion. ESPERANDO CARGA"
								+ ".TIPO REPLICACION:"
								+ uploadZip.getReplicacion()
										.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ uploadZip.getReplicacion().getIdReplicacion()
								+ ".REPLICACION DETALLE:"
								+ detalle.getIdReplicacionTabla());
			}

			ejecutaSPAsincrono(uploadZip.getReplicacion());
		}

		replicacion.setEstatus("2.CARGANDO");
		replicacionData.guardaReplicacion(replicacion, "Actualizar");
		logger.info("Sale  metodo procesaArchivosBajada");
		return 0;
	}

	/**
	 * Ejecuta SP de manera asincrona para carga de archivos
	 * 
	 * @author carlos.pantoja
	 * @date 18/01/2014
	 * @param replicacionDTO
	 *            replicacion que se ejecuta en este hilo
	 * @return exito replicacion hilo
	 */
	public Integer ejecutaSPAsincrono(Replicacion replicacionDTO) {
		AsincronoSPCargaArchivos archivos = new AsincronoSPCargaArchivos(
				replicacionDTO);
		logger.info("Entra  metodo ejecutaSPAsincrono : "
				+ replicacionDTO.toString());
		try {
			new Thread(archivos).start();
		} catch (IllegalThreadStateException e) {
			logger.error(e);
			e.printStackTrace();
			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación LDC-OTSe",
					"M-10 Error al ejecutar SP asincrono."
							+ ".TIPO REPLICACION:"
							+ replicacionDTO.getTipoReplicacion()
							+ ".REPLICACION ID:"
							+ replicacionDTO.getIdReplicacion(),
					e.getMessage(), replicacionDTO.getIdReplicacion()
							.intValue(), "");

			return 1;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación LDC-OTSe",
					"M-10 Error al ejecutar SP asincrono."
							+ ".TIPO REPLICACION:"
							+ replicacionDTO.getTipoReplicacion()
							+ ".REPLICACION ID:"
							+ replicacionDTO.getIdReplicacion(),
					e.getMessage(), replicacionDTO.getIdReplicacion()
							.intValue(), "");

			return 1;
		}
		logger.info("Sale  metodo ejecutaSPAsincrono : "
				+ replicacionDTO.toString());
		return 0;
	}

	/**
	 * Consulta estatus replicacion y lo actualiza en servidor central
	 * 
	 * @author carlos.pantoja
	 * @date 18/02/2014
	 * @param replicacion
	 *            Replicacion a consultar
	 */
	public void consultaEstatusReplicacion(Replicacion replicacion) {
		ObjectMapper mapper = new ObjectMapper();

		int contadorCambios = 0;
		String resultado = "";
		Replicacion rep = new Replicacion();
		logger.info("Entra  metodo consultaEstatusReplicacion : "
				+ replicacion.toString());

		Replicacion repAnt = replicacionData.obtieneReplicacion(replicacion);
		while (!resultado.equals("3.FINALIZADA")
				&& !resultado.equals("4.DETENIDA CON ERROR")) {

			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				logger.error(e);
				e.printStackTrace();
			}

			rep = replicacionData.obtieneReplicacion(replicacion);
			ReplicacionDetalle detalle = new ReplicacionDetalle();
			detalle.setIdReplicacion(replicacion.getIdReplicacion());
			List<ReplicacionDetalle> detalles = detalleData
					.obtieneEventoReplicacion(detalle);

			resultado = rep.getEstatus();

			if (!resultado.equals("3.FINALIZADA")
					&& !resultado.equals("4.FINALIZADA CON ERROR")) {
				if (repAnt.getAvance().equals(rep.getAvance())) {
					contadorCambios++;
				} else {
					contadorCambios = 0;
				}
			}
			if (contadorCambios >= reintentosEstatus) {
				replicacion.setEstatus("3.FINALIZADA");
				replicacion
						.setLogEjecucion("Error en replicacion: No se obtuvo avance de carga de archivos en servidor central. Revisar log en servidor central.");
				// replicacionData.guardaReplicacion(replicacion,
				// "ActualizarError");
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-24 Error al escribir informacion en el servidor actualizacion Replicacion."
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), "",
						replicacion.getIdReplicacion().intValue(), "");
				resultado = "3.FINALIZADA";
			}
			repAnt = rep;
			// Actualizar en server
			ReqActualizaEstatusReplicacion actualizaEstatusReplicacion = new ReqActualizaEstatusReplicacion(
					replicacion.getLdc(), rep, detalles);
			HttpPost target = new HttpPost(URL_REPLICACION
					+ "ReplicacionEstatusActualizaService");
			StringEntity input = null;
			try {
				input = new StringEntity(
						mapper.writeValueAsString(actualizaEstatusReplicacion));

				logger.info("Entra metodo solicitaArchivosTablas:"
						+ URL_REPLICACION
						+ "ReplicacionEstatusActualizaService : "
						+ mapper.writeValueAsString(actualizaEstatusReplicacion));

				input.setContentType("application/json");
			} catch (JsonProcessingException je) {
				logger.error(je);
				je.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-24 Error al escribir informacion en el servidor actualizacion Replicacion."
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), je
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
			} catch (UnsupportedEncodingException ue) {
				logger.error(ue);
				ue.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-24 Error al escribir informacion en el servidor actualizacion Replicacion."
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), ue
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
			}
			target.setEntity(input);
			try {
				HttpClient cliente = getCliente();
				cliente.execute(target);
			} catch (IOException e) {
				logger.error(e);
				e.printStackTrace();
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-24 Error al escribir informacion en el servidor actualizacion Replicacion."
								+ ".TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ".REPLICACION ID:"
								+ replicacion.getIdReplicacion(), e
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");
			}
		}
		logger.info("Sale metodo consultaEstatusReplicacion : "
				+ replicacion.toString());

	}

	/**
	 * Clase para obtener la instancia de cliente para la llamada al ws rest
	 * 
	 * @author carlos.pantoja
	 * @date 24/10/2014 (antes)
	 * @return instancia de cliente rest
	 * @history 24/10/2014 ja.torner. Se agrega timeout para las llamadas
	 */
	private HttpClient getCliente() {
		if (URL_REPLICACION.indexOf("https") != -1) {
			try {
				RequestConfig requestConfig = RequestConfig.custom()
						.setConnectTimeout(3600 * 1000)
						.setSocketTimeout(3600 * 1000)
						.setConnectionRequestTimeout(3600 * 1000).build();

				return HttpClientBuilder.create()
						.setDefaultRequestConfig(requestConfig).build();
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				RequestConfig requestConfig = RequestConfig.custom()
						.setConnectTimeout(3600 * 1000)
						.setSocketTimeout(3600 * 1000)
						.setConnectionRequestTimeout(3600 * 1000).build();
				return HttpClientBuilder.create()
						.setDefaultRequestConfig(requestConfig).build();
			}
		} else {
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(3600 * 1000)
					.setSocketTimeout(3600 * 1000)
					.setConnectionRequestTimeout(3600 * 1000).build();
			return HttpClientBuilder.create()
					.setDefaultRequestConfig(requestConfig).build();
		}
	}

	/*
	 * @SuppressWarnings("deprecation") private HttpClient
	 * httpClientTrustingAllSSLCerts(){ try { SchemeRegistry registry = new
	 * SchemeRegistry();
	 * 
	 * SSLSocketFactory socketFactory = new SSLSocketFactory(new TrustStrategy()
	 * {
	 * 
	 * @Override public boolean isTrusted(X509Certificate[] arg0, String arg1)
	 * throws CertificateException {
	 * 
	 * return true; } } ,
	 * org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	 * 
	 * registry.register(new Scheme("https",
	 * Integer.valueOf(PUERTO_SEGURO_REPLICACION), socketFactory));
	 * ThreadSafeClientConnManager mgr = new
	 * ThreadSafeClientConnManager(registry); HttpClient client = new
	 * DefaultHttpClient(mgr, new DefaultHttpClient().getParams()); return
	 * client; } catch (GeneralSecurityException e) { throw new
	 * RuntimeException(e); }
	 * 
	 * }
	 */

}
