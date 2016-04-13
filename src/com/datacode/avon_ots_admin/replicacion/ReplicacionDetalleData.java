package com.datacode.avon_ots_admin.replicacion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.datacode.avon_ots_admin.utils.AccesoBD;
import com.datacode.avon_ots_admin.utils.Utils;

public class ReplicacionDetalleData {
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Metodo para guardar replicacion
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 * @param replicacion
	 *            datos replicacion
	 * @return Repllicacion almacenada en BD
	 */
	public List<ReplicacionDetalle> generaArchivosReplicacion(
			ReplicacionTabla replicacionTabla, Replicacion replicacion) {
		// TODO - JORGE TORNER - Validar manejo de excepciones
		Connection conn = AccesoBD.abrirConexionOTS();
		List<ReplicacionDetalle> res = new ArrayList<ReplicacionDetalle>();
		CallableStatement cs = null;
		logger.info("Entra metodo generaArchivosReplicacion");
		if (conn != null) {
			try {
				logger.info("Ejecuta callSP_REPLICACION_GENERA_ARCHIVOS_TABLA(?, ?, ?, ?, ?): "
						+ "p_idReplicacion"
						+ replicacion.getIdReplicacion()
						+ "p_idReplicacionesTabla"
						+ replicacionTabla.getIdReplicacionTabla()
						+ "p_fechaConsulta"
						+ replicacionTabla.getFechaActualizacionTabla()
						+ "p_usbd"
						+ Utils.obtenerPropiedadArchivoConfig("usr_OTS")
						+ "p_clbd"
						+ Utils.obtenerPropiedadArchivoConfig("pwd_OTS"));
				cs = conn
						.prepareCall("{call SP_REPLICACION_GENERA_ARCHIVOS_TABLA(?, ?, ?, ?, ?)}");
				cs.setObject("p_idReplicacion", replicacion.getIdReplicacion(),
						Types.INTEGER);
				cs.setObject("p_idReplicacionesTabla", replicacionTabla
						.getIdReplicacionTabla().intValue(), Types.SMALLINT);
				cs.setObject("p_fechaConsulta",
						replicacionTabla.getFechaActualizacionTabla(),
						Types.VARCHAR);
				// TODO validar cambio
				cs.setObject("p_usbd",
						Utils.obtenerPropiedadArchivoConfig("usr_OTS"),
						Types.VARCHAR);
				cs.setObject("p_clbd",
						Utils.obtenerPropiedadArchivoConfig("pwd_OTS"),
						Types.VARCHAR);
				ResultSet rset = AccesoBD.executeRetrieveResultSet(cs);
				// TODO Cambiar diablito
				/*
				 * PreparedStatement ps = conn .prepareStatement(
				 * "SELECT ID_REPLICACION, ID_REPLICACIONES_TABLA, NOMBRE_ARCHIVO, NOMBRE_ZIP, CONVERT(VARCHAR,FECHA_INICIO,103) + ' ' + CONVERT(VARCHAR,FECHA_INICIO,114) FECHA_INICIO , CONVERT(VARCHAR,FECHA_FIN,103) + ' ' + CONVERT(VARCHAR,FECHA_FIN,114) FECHA_FIN , CONVERT(VARCHAR,FECHA_INFORMACION_INI,103) + ' ' + CONVERT(VARCHAR,FECHA_INFORMACION_INI,114) FECHA_INFORMACION_INI , CONVERT(VARCHAR,FECHA_INFORMACION_FIN,103) + ' ' + CONVERT(VARCHAR,FECHA_INFORMACION_FIN,114) FECHA_INFORMACION_FIN , ESTATUS, AVANCE, CONVERT(VARCHAR,FECHA_ULTIMA_ACTUALIZACION,103) + ' ' + CONVERT(VARCHAR,FECHA_ULTIMA_ACTUALIZACION,114) FECHA_ULTIMA_ACTUALIZACION , LOG_EJECUCION  FROM PW_REPLICACIONES_DETALLE WHERE ID_REPLICACION = ? AND ESTATUS = '2.ENCRIPTANDO'  AND ID_REPLICACIONES_TABLA = ? ORDER BY FECHA_ULTIMA_ACTUALIZACION"
				 * ); ps.setInt(1, replicacion.getIdReplicacion().intValue());
				 * ps.setInt(2, replicacionTabla
				 * .getIdReplicacionTabla().intValue());
				 * 
				 * ResultSet rset = ps.executeQuery();
				 */
				while (rset.next()) {
					try {
						ReplicacionDetalle detalle = new ReplicacionDetalle(
								rset.getLong("ID_REPLICACION"),
								rset.getLong("ID_REPLICACIONES_TABLA"),
								rset.getString("NOMBRE_ARCHIVO"),
								rset.getString("NOMBRE_ZIP"),
								rset.getString("FECHA_INICIO"),
								rset.getString("FECHA_FIN"),
								rset.getString("FECHA_INFORMACION_INI"),
								rset.getString("FECHA_INFORMACION_FIN"),
								rset.getString("ESTATUS"),
								rset.getString("AVANCE"),
								rset.getString("FECHA_ULTIMA_ACTUALIZACION"),
								rset.getString("LOG_EJECUCION"));

						res.add(detalle);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				// ps.close();

			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-4. Error al generar archivos de replicacion. TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ". SP_REPLICACION_GENERA_ARCHIVOS_TABLA", e
								.getMessage(), replicacion.getIdReplicacion()
								.intValue(), "");

			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			}
		}
		logger.info("Sale metodo generaArchivosReplicacion");
		return res;
	}

	/**
	 * Metodo para guardar error en proceso
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 * @param replicacion
	 *            datos replicacion
	 * @return Repllicacion almacenada en BD
	 */
	public void guardaEventoReplicacion(ReplicacionDetalle replicacionDetalle,
			String operacion, String estatus, String mensaje) {
		// TODO - JORGE TORNER - Validar manejo de excepciones
		Connection conn = AccesoBD.abrirConexionOTS();
		CallableStatement cs = null;
		logger.info("Entra metodo guardaEventoReplicacion");
		if (conn != null) {
			try {
				logger.info("call SP_REPLICACIONES_DETALLE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
						+ "p_tipoAccion"
						+ operacion
						+ "p_idReplicacion"
						+ replicacionDetalle.getIdReplicacion()
						+ "p_idReplicacionesTabla"
						+ replicacionDetalle.getIdReplicacionTabla()
						+ "p_nombreArchivo"
						+ replicacionDetalle.getNombreArchivo()
						+ "p_nombreZip"
						+ replicacionDetalle.getNombreZIP()
						+

						"p_fechaInicio"
						+ replicacionDetalle.getFechaInicio()
						+ "p_fechaFin"
						+ replicacionDetalle.getFechaFin()
						+

						"p_fechaInformacionIni"
						+ replicacionDetalle.getFechaInformacionIni()
						+

						"p_fechaInformacionFin"
						+ replicacionDetalle.getFechaInformacionFin()
						+

						"p_estatus"
						+ estatus
						+ "p_avance"
						+ replicacionDetalle.getAvance()
						+

						"p_idUsuario"
						+ 0
						+ "p_logEjecucion"
						+ mensaje
						+ "p_claveLDC" + replicacionDetalle.getIdLDC());

				cs = conn
						.prepareCall("{call SP_REPLICACIONES_DETALLE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("p_tipoAccion", operacion, Types.VARCHAR);
				cs.setObject("p_idReplicacion", replicacionDetalle
						.getIdReplicacion().intValue(), Types.INTEGER);
				cs.setObject("p_idReplicacionesTabla", replicacionDetalle
						.getIdReplicacionTabla().intValue(), Types.SMALLINT);
				cs.setObject("p_nombreArchivo",
						replicacionDetalle.getNombreArchivo(), Types.VARCHAR);
				cs.setObject("p_nombreZip", replicacionDetalle.getNombreZIP(),
						Types.VARCHAR);
				cs.setObject("p_fechaInicio",
						replicacionDetalle.getFechaInicio(), Types.VARCHAR);
				cs.setObject("p_fechaFin", replicacionDetalle.getFechaFin(),
						Types.VARCHAR);
				cs.setObject("p_fechaInformacionIni",
						replicacionDetalle.getFechaInformacionIni(),
						Types.VARCHAR);
				cs.setObject("p_fechaInformacionFin",
						replicacionDetalle.getFechaInformacionFin(),
						Types.VARCHAR);
				cs.setObject("p_estatus", estatus, Types.VARCHAR);
				cs.setObject("p_avance", replicacionDetalle.getAvance(),
						Types.VARCHAR);
				cs.setObject("p_idUsuario", 0, Types.INTEGER);
				cs.setObject("p_logEjecucion", mensaje, Types.VARCHAR);
				cs.setObject("p_claveLDC", replicacionDetalle.getIdLDC(),
						Types.INTEGER);

				cs.execute();

			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-8. Error al guardar evento replicacion" + mensaje
								+ ". SP_REPLICACIONES_DETALLE -> " + operacion,
						e.getMessage(), replicacionDetalle.getIdReplicacion()
								.intValue(), "");

			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			}
		}
		logger.info("Sale metodo guardaEventoReplicacion");
	}

	/**
	 * Metodo para consultar el detalle del avance de la replicacion
	 * 
	 * @author carlos.pantoja
	 * @date 18/02/2014
	 * @param estatus
	 *            datos a consultar
	 * @return Lista de detalles relacionados a la replicacion
	 */
	public List<ReplicacionDetalle> obtieneEventoReplicacion(
			ReplicacionDetalle replicacionDetalle) {
		// TODO - JORGE TORNER - Validar manejo de excepciones
		Connection conn = AccesoBD.abrirConexionOTS();
		CallableStatement cs = null;
		List<ReplicacionDetalle> res = new ArrayList<ReplicacionDetalle>();
		if (conn != null) {
			try {
				cs = conn
						.prepareCall("{call SP_REPLICACIONES_DETALLE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("p_tipoAccion", "Obtener", Types.VARCHAR);
				cs.setObject("p_idReplicacion", replicacionDetalle
						.getIdReplicacion().intValue(), Types.INTEGER);
				cs.setObject("p_idReplicacionesTabla", null, Types.SMALLINT);
				cs.setObject("p_nombreArchivo",
						replicacionDetalle.getNombreArchivo(), Types.VARCHAR);
				cs.setObject("p_nombreZip", replicacionDetalle.getNombreZIP(),
						Types.VARCHAR);
				cs.setObject("p_fechaInicio",
						replicacionDetalle.getFechaInicio(), Types.VARCHAR);
				cs.setObject("p_fechaFin", replicacionDetalle.getFechaFin(),
						Types.VARCHAR);
				cs.setObject("p_fechaInformacionIni",
						replicacionDetalle.getFechaInformacionIni(),
						Types.VARCHAR);
				cs.setObject("p_fechaInformacionFin",
						replicacionDetalle.getFechaInformacionFin(),
						Types.VARCHAR);
				cs.setObject("p_estatus", "", Types.VARCHAR);
				cs.setObject("p_avance", replicacionDetalle.getAvance(),
						Types.VARCHAR);
				cs.setObject("p_idUsuario", 0, Types.INTEGER);
				cs.setObject("p_logEjecucion", "", Types.VARCHAR);
				cs.setObject("p_claveLDC", replicacionDetalle.getIdLDC(),
						Types.INTEGER);

				ResultSet rset = AccesoBD.executeRetrieveResultSet(cs);
				while (rset.next()) {
					try {
						ReplicacionDetalle detalle = new ReplicacionDetalle(
								rset.getLong("ID_REPLICACION"),
								rset.getLong("ID_REPLICACIONES_TABLA"),
								rset.getString("NOMBRE_ARCHIVO"),
								rset.getString("NOMBRE_ZIP"),
								rset.getString("FECHA_INICIO"),
								rset.getString("FECHA_FIN"),
								rset.getString("FECHA_INFORMACION_INI"),
								rset.getString("FECHA_INFORMACION_FIN"),
								rset.getString("ESTATUS"),
								rset.getString("AVANCE"),
								rset.getString("FECHA_ULTIMA_ACTUALIZACION"),
								rset.getString("LOG_EJECUCION"));

						res.add(detalle);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-23. Error al consultar detalle de replicacion.  SP_REPLICACIONES_DETALLE -> Obtener",
						e.getMessage(), replicacionDetalle.getIdReplicacion()
								.intValue(), "");

			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			}
		}
		return res;

	}
}
