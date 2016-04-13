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


public class ReplicacionTablaData {
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * Metodo para obtener un listado de tablas candidatas a respaldar
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 * @return Listado de tablas asociadas al id cliente para respaldas
	 */
	public List<ReplicacionTabla> getReplicacionTabla() {
		// TODO - JORGE TORNER - Validar manejo de excepciones<
		Connection conn = AccesoBD.abrirConexionOTS();
		List<ReplicacionTabla> res = new ArrayList<ReplicacionTabla>();
		CallableStatement cs = null;
		logger.info("Entra metodo getReplicacionTabla");
		if (conn != null) {
			try {
				
				logger.info("Ejecuta call SP_REPLICACIONES_TABLAS(?,?,?,?,?): p_tipoAccion: ObtenerTablasReplicarSubida ");
				cs = conn.prepareCall("{call SP_REPLICACIONES_TABLAS(?,?,?,?,?)}");
				cs.setObject("p_tipoAccion", "ObtenerTablasReplicarSubida", Types.VARCHAR);
				cs.setObject("p_idReplicacionesTabla", null, Types.SMALLINT);
				cs.setObject("p_fechaActualizacionTabla", null, Types.VARCHAR);
				cs.setObject("p_fechaUltimaReplicacion", null, Types.VARCHAR);
				cs.setObject("p_cadena", null, Types.VARCHAR);
				ResultSet rset = AccesoBD.executeRetrieveResultSet(cs);
				while (rset.next()) {
					ReplicacionTabla replicacionTabla = new ReplicacionTabla(
							rset.getLong("ID_REPLICACIONES_TABLA"),
							rset.getString("NOMBRE_ORIGEN"),
							rset.getString("NOMBRE_DESTINO"),
							rset.getString("CAMPOS_LLAVE_PRIMARIA"),
							rset.getString("CAMPOS_DESTINO_IGNORAR"),
							rset.getString("GRUPO"),
							rset.getString("TIPO_REPLICACION"),
							rset.getString("FECHA_ACTUALIZACION_TABLA"),
							rset.getString("FECHA_ULTIMA_REPLICACION"),
							rset.getInt("ACTIVA"), rset.getString("LDC"));
					res.add(replicacionTabla);

				}
				

			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"M-1.Error al obtener tablas replicacion. TIPO REPLICACION:LDC->OTSe. SP_REPLICACIONES_TABLAS -> ObtenerTablasReplicarSubida",
						e.getMessage(),
						0, 
						"");
			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			} 
		}
		logger.info("Sale metodo getReplicacionTabla");
		return res;
	}
	
	/**
	 * Metodo para obtener un listado de tablas candidatas a respaldar para bajar del server
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 * @return Listado de tablas asociadas al id cliente para respaldas
	 */
	public List<ReplicacionTabla> getReplicacionBajadaTabla() {
		// TODO - JORGE TORNER - Validar manejo de excepciones
		Connection conn = AccesoBD.abrirConexionOTS();
		List<ReplicacionTabla> res = new ArrayList<ReplicacionTabla>();
		CallableStatement cs = null;
		logger.info("Entra metodo getReplicacionBajadaTabla");
		if (conn != null) {
			try {
				logger.info("Ejecuta call SP_REPLICACIONES_TABLAS(?,?,?,?,?): p_tipoAccion: ObtenerTablasReplicarBajada ");
				cs = conn.prepareCall("{call SP_REPLICACIONES_TABLAS(?,?,?,?,?)}");
				cs.setObject("p_tipoAccion", "ObtenerTablasReplicarBajada", Types.VARCHAR);
				cs.setObject("p_idReplicacionesTabla", null, Types.SMALLINT);
				cs.setObject("p_fechaActualizacionTabla", null, Types.VARCHAR);
				cs.setObject("p_fechaUltimaReplicacion", null, Types.VARCHAR);
				cs.setObject("p_cadena", null, Types.VARCHAR);
				
				ResultSet rset = AccesoBD.executeRetrieveResultSet(cs);
				while (rset.next()) {
					ReplicacionTabla replicacionTabla = new ReplicacionTabla(
							rset.getLong("ID_REPLICACIONES_TABLA"),
							rset.getString("NOMBRE_ORIGEN"),
							rset.getString("NOMBRE_DESTINO"),
							rset.getString("CAMPOS_LLAVE_PRIMARIA"),
							rset.getString("CAMPOS_DESTINO_IGNORAR"),
							rset.getString("GRUPO"),
							rset.getString("TIPO_REPLICACION"),
							rset.getString("FECHA_ACTUALIZACION_TABLA"),
							rset.getString("FECHA_ULTIMA_REPLICACION"),
							rset.getInt("ACTIVA"), rset.getString("LDC"));
					res.add(replicacionTabla);

				}
				

			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación EOTS->LDC",
						"M-1.Error al obtener tablas replicacion bajada SP_REPLICACIONES_TABLAS. TIPO REPLICACION:OTSe->LDC. SP_REPLICACIONES_TABLAS -> ObtenerTablasReplicarBajada",
						e.getMessage(),
						0, 
						"");
			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			} 
		}
		logger.info("Sale metodo getReplicacionBajadaTabla");
		return res;
	}
	
	
	/**
	 * Actualiza Fechas de actualizacion para tablas recuperadas del servidor
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 * @param resFechasReplicacion listado de tablas a actualizar
	 */
	public void guardaFechasReplicacion(ResFechasReplicacion resFechasReplicacion) {
		// TODO - JORGE TORNER - Validar manejo de excepciones
		Connection conn = AccesoBD.abrirConexionOTS();
		CallableStatement cs = null;

		if (conn != null) {
			try {
				cs = conn.prepareCall("{call SP_REPLICACIONES_TABLAS(?,?,?,?,?)}");
				cs.setObject("p_tipoAccion", "ActualizarFechas", Types.VARCHAR);
				cs.setObject("p_idReplicacionesTabla", null, Types.SMALLINT);
				cs.setObject("p_fechaActualizacionTabla", null, Types.VARCHAR);
				cs.setObject("p_fechaUltimaReplicacion", null, Types.VARCHAR);
				cs.setObject("p_cadena", resFechasReplicacion.getFechasTablasReplicacion(), 
						Types.VARCHAR);
				
				cs.execute();
				
				

			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe",
						"CC22-2",
						e.getMessage(),
						0, 
						"");
			} finally {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
			} 
		}

	
	}
}
