package com.datacode.avon_ots_admin.replicacion;

import java.util.List;

import org.apache.log4j.Logger;

import com.datacode.avon_ots_admin.replicacion.Replicacion;
import com.datacode.avon_ots_admin.replicacion.ReplicacionTabla;
import com.datacode.avon_ots_admin.replicacion.ReqFechasReplicacion;
import com.datacode.avon_ots_admin.replicacion.ResFechasReplicacion;
import com.datacode.avon_ots_admin.utils.Utils;

public class ReplicacionLDCController {
	private List<ReplicacionTabla> replicacionTablas;
	private String idLDC;
	private Logger logger = Logger.getLogger(this.getClass());
	private ReplicacionTablaService replicacionTablaService = new ReplicacionTablaService();
	private Replicacion replicacion = new Replicacion();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ReplicacionLDCController controller = new ReplicacionLDCController();

		controller.replicacionLDC();
	}

	/**
	 * Inicia proceso de replicacion LDC
	 */
	public void replicacionLDC() {
		logger.info("Entra metodo replicacionLDC");
		logger.info("Inicializando replicacion: LDC->EOTS");
		replicacion.setTipoReplicacion("LDC->EOTS");
		try {
			replicacionTablaService.inicializaParametros(replicacion);

			if (replicacionTablaService.getReplicacionLDCEOTS().equals("0")) {
				return;
			}

			obtieneTablasReplicacion();
			if (replicacionTablas.size() == 0)
				return;
			actualizaDatosTablasReplicacion();
			guardaReplicacion();

			if (replicacion.getIdReplicacion() != null
					&& replicacion.getIdReplicacion() != 0) {
				if (generaArchivosTablas() == 0) {
					if (subeArchivosTablas() == 0) {
						replicacionTablaService
								.actualizaEstatusReplicacion(replicacion);
					}
				}
			}
		} catch (Exception e) {
			logger.info("Error en metodo replicacionLDC: " + e.getMessage());
			logger.error(e);
			e.printStackTrace();
			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación LDC-OTSe",
					"replicacionLDC:ERROR EN REPLICACIÓN. TIPO REPLICACION:"
							+ replicacion.getTipoReplicacion(),
					e.getMessage(), 0, "");
		}
		logger.info("Sale metodo replicacionLDC");
	}

	/**
	 * Actualiza datos de replicacion con informacion del servidor.
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 */
	public void actualizaDatosTablasReplicacion() {
		logger.info("Entra metodo actualizaDatosTablasReplicacion");
		ReqFechasReplicacion reqFechasReplicacion = new ReqFechasReplicacion(
				idLDC, replicacionTablas);

		ResFechasReplicacion resFechasReplicacion = replicacionTablaService
				.guardaFechasReplicacion(reqFechasReplicacion);
		replicacionTablas = resFechasReplicacion.getReplicacionTablas();

		logger.info("Sale metodo actualizaDatosTablasReplicacion");

	}

	/**
	 * Obtiene tablas de replicacion del cliente.
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 */
	public void obtieneTablasReplicacion() {
		logger.info("Entra metodo obtieneTablasReplicacion: ");
		replicacionTablas = replicacionTablaService.getReplicacionTabla();
		if (replicacionTablas.size() > 0) {
			// TODO validar tipo de dato
			idLDC = replicacionTablas.get(0).getLdc();
		}
		logger.info("Sale metodo obtieneTablasReplicacion: ");
	}

	/**
	 * Gauarda proceso replicacion
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 */
	public void guardaReplicacion() {
		logger.info("Entra metodo guardaReplicacion: ");
		if (replicacionTablas != null) {
			if (replicacionTablas.size() > 0) {
				replicacion.setTipoReplicacion(replicacionTablas.get(0)
						.getTipoReplicacion());
			}
			replicacion = replicacionTablaService
					.guardaReplicacion(replicacion);

			replicacion.setTipoReplicacion("LDC->EOTS");
		}
		logger.info("Sale metodo guardaReplicacion: ");
	}

	/**
	 * Genera archivos replicacion
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 */
	public Integer generaArchivosTablas() {
		return replicacionTablaService.generaArchivosReplicacion(replicacion,
				replicacionTablas);
	}

	/**
	 * Sube archivos replicacion
	 * 
	 * @author carlos.pantoja
	 * @date 11/01/2014
	 */
	public Integer subeArchivosTablas() {
		return replicacionTablaService.uploadFiles(replicacion);
	}
}
