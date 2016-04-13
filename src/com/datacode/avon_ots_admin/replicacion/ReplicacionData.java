package com.datacode.avon_ots_admin.replicacion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.datacode.avon_ots_admin.utils.AccesoBD;
import com.datacode.avon_ots_admin.utils.Utils;

public class ReplicacionData {
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Obtiene parametros de configuracion en bd
	 * 
	 * @author carlos.pantoja
	 * @date 17/01/2014
	 * @return Mapa de parametros
	 */
	public Map<String, String> obtieneParametros(Replicacion replicacion) {
		Connection conn = AccesoBD.abrirConexionOTS();
		Map<String, String> res = new HashMap<String, String>();
		CallableStatement cs = null;

		if (conn != null) {
			try {
				cs = conn
						.prepareCall("{call SP_ConfiguracionCentral(?,?,?,?)}");
				cs.setObject("p_tipoAccion", "ObtenerTodos", Types.VARCHAR);
				cs.setObject("p_idConfiguracionCentral", null, Types.SMALLINT);
				cs.setObject("p_idUsuarioCentral", null, Types.INTEGER);
				cs.setObject("p_valor", null, Types.VARCHAR);
				ResultSet rset = AccesoBD.executeRetrieveResultSet(cs);
				while (rset.next()) {
					res.put(rset.getString("NOMBRE"), rset.getString("VALOR"));
				}

			} catch (SQLException e) {
				logger.error(e);
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"ERROR AL OBTENER PARAMETROS INICIALES. TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion(),
						e.getMessage(), 0, "");
			} catch (Exception e) {
				logger.error(e);
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"ERROR AL OBTENER PARAMETROS INICIALES. TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion(),
						e.getMessage(), 0, "");
			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			}
		}

		return res;
	}

	/**
	 * Metodo para guardar replicacion
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 * @param replicacion
	 *            datos replicacion
	 * @return Repllicacion almacenada en BD
	 */
	public Replicacion guardaReplicacion(Replicacion replicacion) {
		// TODO - JORGE TORNER - Validar manejo de excepciones
		Connection conn = AccesoBD.abrirConexionOTS();
		Replicacion res = new Replicacion();
		CallableStatement cs = null;
		logger.info("Entra metodo guardaReplicacion");
		if (conn != null) {
			try {
				logger.info("Ejecuta call SP_REPLICACIONES(?,?,?,?,?,?,?,?,?,?): p_tipoReplicacion: "
						+ replicacion.getTipoReplicacion()
						+ "p_estatus: EN PROCESO");
				cs = conn
						.prepareCall("{call SP_REPLICACIONES(?,?,?,?,?,?,?,?,?,?)}");

				cs.setObject("p_tipoAccion", "InsertarNuevo", Types.VARCHAR);
				cs.setObject("p_idReplicacion", null, Types.INTEGER);
				cs.setObject("p_tipoReplicacion",
						replicacion.getTipoReplicacion(), Types.VARCHAR);
				cs.setObject("p_fechaInicio", null, Types.VARCHAR);
				cs.setObject("p_fechaFin", null, Types.VARCHAR);
				cs.setObject("p_estatus", "EN PROCESO", Types.VARCHAR);
				cs.setObject("p_avance", null, Types.VARCHAR);
				cs.setObject("p_idUsuario", null, Types.INTEGER);
				cs.setObject("p_logEjecucion", null, Types.VARCHAR);
				cs.setObject("p_claveLDC", null, Types.INTEGER);
				ResultSet rset = AccesoBD.executeRetrieveResultSet(cs);
				while (rset.next()) {
					res.setIdReplicacion(rset.getLong("ID_REPLICACION"));
					res.setLdc(rset.getString("LDC"));
				}

			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-3. Error al guardar replicacion.  TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ". SP_REPLICACIONES -> InsertarNuevo",
						e.getMessage(), 0, "");
			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			}
		}
		logger.info("Sale metodo guardaReplicacion");
		return res;
	}

	/**
	 * Metodo para guardar replicacion
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 * @param replicacion
	 *            datos replicacion
	 * @return Repllicacion almacenada en BD
	 */
	public Replicacion obtieneReplicacion(Replicacion replicacion) {
		// TODO - JORGE TORNER - Validar manejo de excepciones
		Connection conn = AccesoBD.abrirConexionOTS();
		Replicacion res = new Replicacion();
		CallableStatement cs = null;
		logger.info("Entra metodo obtieneReplicacion" + replicacion.toString());
		if (conn != null) {
			try {
				logger.info("Ejecuta call SP_REPLICACIONES(?,?,?,?,?,?,?,?,?,?):" +"p_tipoAccion" + "Obtener" +
						"p_idReplicacion" + replicacion.getIdReplicacion() +
						
						"p_tipoReplicacion" +
								replicacion.getTipoReplicacion() +
						"p_fechaInicio" + null +
						"p_fechaFin" + null +
						"p_estatus" + "EN PROCESO" +
						"p_avance" + null +
						"p_idUsuario" + null + 
						"p_logEjecucion" + null +
						"p_claveLDC" + null  );
				
				cs = conn
						.prepareCall("{call SP_REPLICACIONES(?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("p_tipoAccion", "Obtener", Types.VARCHAR);
				cs.setObject("p_idReplicacion", replicacion.getIdReplicacion(),
						Types.INTEGER);
				cs.setObject("p_tipoReplicacion",
						replicacion.getTipoReplicacion(), Types.VARCHAR);
				cs.setObject("p_fechaInicio", null, Types.VARCHAR);
				cs.setObject("p_fechaFin", null, Types.VARCHAR);
				cs.setObject("p_estatus", "EN PROCESO", Types.VARCHAR);
				cs.setObject("p_avance", null, Types.VARCHAR);
				cs.setObject("p_idUsuario", null, Types.INTEGER);
				cs.setObject("p_logEjecucion", null, Types.VARCHAR);
				cs.setObject("p_claveLDC", null, Types.INTEGER);
				ResultSet rset = AccesoBD.executeRetrieveResultSet(cs);
				while (rset.next()) {
					res.setIdReplicacion(rset.getLong("ID_REPLICACION"));
					res.setTipoReplicacion(rset.getString("TIPO_REPLICACION"));
					res.setFechaInicio(rset.getString("FECHA_INICIO"));
					res.setEstatus(rset.getString("ESTATUS"));
					res.setFechaUltimaActualizacion(rset
							.getString("FECHA_ULTIMA_ACTUALIZACION"));
					res.setUsuarioActualiza(rset.getString("USUARIO_ACTUALIZA"));
					res.setLdc(rset.getString("LDC"));

				}

			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-3. Error al obtener replicacion.  TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ". SP_REPLICACIONES -> Obtener",
						e.getMessage(), 0, "");
			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			}
		}
		logger.info("Sale metodo obtieneReplicacion" + replicacion.toString());
		return res;
	}

	/**
	 * Metodo para actualizar replicacion
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 * @param replicacion
	 *            datos replicacion
	 * @return Repllicacion almacenada en BD
	 */
	public void guardaReplicacion(Replicacion replicacion, String operacion) {
		// TODO - JORGE TORNER - Validar manejo de excepciones
		Connection conn = AccesoBD.abrirConexionOTS();
		logger.info("Entra metodo guardaReplicacion");
		CallableStatement cs = null;

		if (conn != null) {
			try {
				logger.info("call SP_REPLICACIONES(?,?,?,?,?,?,?,?,?,?)"
						+ "p_idReplicacion" + replicacion.getIdReplicacion()
						+ "p_tipoReplicacion"
						+ replicacion.getTipoReplicacion() + "p_fechaInicio"
						+ replicacion.getFechaInicio() + "p_fechaFin"
						+ replicacion.getFechaFin() + "p_estatus"
						+ replicacion.getEstatus() + "p_avance" + ""
						+ "p_idUsuario" + 0 + "p_logEjecucion"
						+ replicacion.getLogEjecucion() + "p_claveLDC"
						+ replicacion.getIdLDC());

				cs = conn
						.prepareCall("{call SP_REPLICACIONES(?,?,?,?,?,?,?,?,?,?)}");
				cs.setObject("p_tipoAccion", operacion, Types.VARCHAR);

				cs.setObject("p_idReplicacion", replicacion.getIdReplicacion(),
						Types.INTEGER);
				cs.setObject("p_tipoReplicacion",
						replicacion.getTipoReplicacion(), Types.VARCHAR);
				cs.setObject("p_fechaInicio", replicacion.getFechaInicio(),
						Types.VARCHAR);
				cs.setObject("p_fechaFin", replicacion.getFechaFin(),
						Types.VARCHAR);
				cs.setObject("p_estatus", replicacion.getEstatus(),
						Types.VARCHAR);
				cs.setObject("p_avance", "", Types.VARCHAR);
				cs.setObject("p_idUsuario", 0, Types.INTEGER);
				cs.setObject("p_logEjecucion", replicacion.getLogEjecucion(),
						Types.VARCHAR);
				cs.setObject("p_claveLDC", replicacion.getIdLDC(),
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
						"M-3. Error al guardar replicacion.  TIPO REPLICACION:"
								+ replicacion.getTipoReplicacion()
								+ ". SP_REPLICACIONES -> " + operacion,
						e.getMessage(), 0, "");
			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			}
		}

		logger.info("Sale metodo guardaReplicacion");

	}
}
