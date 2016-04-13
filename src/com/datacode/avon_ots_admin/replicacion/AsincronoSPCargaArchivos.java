package com.datacode.avon_ots_admin.replicacion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

import com.datacode.avon_ots_admin.utils.AccesoBD;
import com.datacode.avon_ots_admin.utils.Utils;


public class AsincronoSPCargaArchivos implements Runnable {

	private Replicacion replicacionDTO;
	private Logger logger = Logger.getLogger(this.getClass());
	public AsincronoSPCargaArchivos(Replicacion replicacionDTO) {
		this.replicacionDTO = replicacionDTO;
	}

	@Override
	public void run() {
		Connection conn = AccesoBD.abrirConexionOTS();
		CallableStatement cs = null;
		if (conn != null) {
			try {
				cs = conn.prepareCall("{call SP_REPLICACION_CARGA_ARCHIVOS(?)}");
				cs.setObject("p_idReplicacion", replicacionDTO.getIdReplicacion(),
						Types.INTEGER);
				cs.execute();			
				System.out.println("Termino ejecucion");
							
			} catch (SQLException e) {
				logger.error(e);
				//guardar replicacion error
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.guardarLogReplicacionMensajeParams(
						"UCS022_1: Replicación LDC-OTSe", "M-22 Error al ejecutar SP ASINCRONO",
						e.getMessage(), replicacionDTO
								.getIdReplicacion().intValue(), "");
			} finally {
				AccesoBD.cerrarStatement(cs);	
				AccesoBD.cerrarConexion(conn);
			}
		}
	}

}
