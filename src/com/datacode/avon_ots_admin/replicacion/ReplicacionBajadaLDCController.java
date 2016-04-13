package com.datacode.avon_ots_admin.replicacion;

import java.util.List;
import org.apache.log4j.Logger;
import com.datacode.avon_ots_admin.utils.Utils;

public class ReplicacionBajadaLDCController {
	private List<ReplicacionTabla> replicacionTablas;
	private String idLDC;
	private Logger logger = Logger.getLogger(this.getClass());
	private ReplicacionTablaService replicacionTablaService = new ReplicacionTablaService();
	private Replicacion replicacion = new Replicacion();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ReplicacionBajadaLDCController controller = new ReplicacionBajadaLDCController();

		controller.replicacionBajadaLDC();
	}

	/**
	 * Proceso general replicacion Bajada
	 */
	public void replicacionBajadaLDC() {
		logger.info("Entra metodo replicacionBajadaLDC");
		logger.info("Inicializando replicacion: EOTS->LDC");
		replicacion.setTipoReplicacion("EOTS->LDC");
		try {
			replicacionTablaService.inicializaParametros(replicacion);

			if (replicacionTablaService.getReplicacionEOTSLDC().equals("0")) {
				// TODO CJPV guardar fecha Finalizacion
				return;
			}

			obtieneTablasReplicacion();
			guardaReplicacion();
			logger.info("Var Replicacion: " + replicacion.toString());
			if (replicacion != null && replicacion.getIdReplicacion() != null && replicacion.getIdReplicacion() != 0) {
				if (solicitaArchivosTablas() == 0) {
					if (procesaArchivosBajada() == 0) {
						consultaAvanceReplicacion();
					}
				}
			}
		} catch (Exception e) {
			Utils.guardarLogReplicacionMensajeParams(
					"UCS022_1: Replicación OTSe-LDC",
					"replicacionLDC:ERROR EN REPLICACIÓN, MÉTODO replicacionBajadaLDC",
					e.getMessage(), 0, "");
			logger.info("Error en metodo replicacionBajadaLDC: " + e.getMessage());
			logger.error(e);
			e.printStackTrace();
		}
		logger.info("Sale metodo replicacionBajadaLDC");
	}

	/**
	 * Obtiene tablas de replicacion bajada del cliente.
	 * 
	 * @author carlos.pantoja
	 * @date 07/02/2014
	 */
	public void obtieneTablasReplicacion() {
		replicacionTablas = replicacionTablaService.getReplicacionBajadaTabla();
		if (replicacionTablas.size() > 0) {
			// TODO validar tipo de dato
			idLDC = replicacionTablas.get(0).getLdc();
		}

	}

	/**
	 * Gauarda proceso replicacion
	 * 
	 * @author carlos.pantoja
	 * @date 07/02/2014
	 */
	public void guardaReplicacion() {
		logger.info("Entra metodo guardaReplicacion: ");
		if (replicacionTablas != null) {
			if (replicacionTablas.size() > 0) {
				replicacion.setTipoReplicacion(replicacionTablas.get(0)
						.getTipoReplicacion());
			
			//TODO CJPV no guardar si el tamaño es igual a 0
			replicacion = replicacionTablaService
					.guardaReplicacion(replicacion);

			
				replicacion.setTipoReplicacion(replicacionTablas.get(0)
						.getTipoReplicacion());
			}
		}
		logger.info("Sale metodo guardaReplicacion: ");
		replicacion.setTipoReplicacion("EOTS->LDC");
	}

	/**
	 * Solicita creacion archivos replicacion
	 * 
	 * @author carlos.pantoja
	 * @date 07/02/2014
	 */
	public Integer solicitaArchivosTablas() {
		ReqReplicacionBajada bajada = new ReqReplicacionBajada(idLDC,
				replicacion, replicacionTablas);
		return replicacionTablaService.solicitaArchivosTablas(bajada);
	}

	/**
	 * Procesa arhivos de bajada generados en el servidor
	 * 
	 * @return 0 exito 1 error
	 */
	public Integer procesaArchivosBajada() {
		return replicacionTablaService.procesaArchivosBajada(replicacion);
	}

	public void consultaAvanceReplicacion() {
		replicacionTablaService.consultaEstatusReplicacion(replicacion);
	}
}
