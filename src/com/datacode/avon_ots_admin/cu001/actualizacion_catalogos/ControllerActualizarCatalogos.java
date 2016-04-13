/**
 *
 *  @since 02/03/2012
 *
 */
package com.datacode.avon_ots_admin.cu001.actualizacion_catalogos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import com.datacode.avon_ots_admin.model.ActualizacionManual;
import com.datacode.avon_ots_admin.model.Catalogo;
import com.datacode.avon_ots_admin.quartz.TareaActualizarCatalogos;
import com.datacode.avon_ots_admin.utils.Configuracion;
import com.datacode.avon_ots_ws.ActualizacionCatalogosStub.ModelEstatusCatalogo;

/**
 * @author jessica.leon
 * 
 */
public class ControllerActualizarCatalogos {

	private String mensaje;
	private Configuracion configuracion;
	private List<Catalogo> catalogos;
	private Catalogo catalogo;
	private boolean mostrarTabla;
	private List<ActualizacionManual> regsLDC;
	private List<ActualizacionManual> regsEOTS;

	public List<ActualizacionManual> getRegsLDC() {
		return regsLDC;
	}

	public void setRegsLDC(List<ActualizacionManual> regsLDC) {
		this.regsLDC = regsLDC;
	}

	public List<ActualizacionManual> getRegsEOTS() {
		return regsEOTS;
	}

	public void setRegsEOTS(List<ActualizacionManual> regsEOTS) {
		this.regsEOTS = regsEOTS;
	}

	public ControllerActualizarCatalogos() {
		
		configuracion = (Configuracion) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("configuracion");
		catalogos = new ArrayList<Catalogo>();
		setMostrarTabla(false);
	}

	public void actualizarCatalogos2() {
		mensaje="";
		CatalogosController catalogosController = new CatalogosController();
		mensaje = catalogosController.actualizarFechaEjecucion(configuracion
				.getIdUsuario());

	}

	public String actualizarCatalogos() {
		mensaje="";
		int catalogosEjecucion = 1;

		catalogosEjecucion = consultarDisponibilidadServicio();

		if (catalogosEjecucion == 0) {

			setMostrarTabla(true);
			consultarEstatusActualizacion();
			TareaActualizarCatalogos actualizador = new TareaActualizarCatalogos(
					configuracion.getIdUsuario());
			Thread thread = new Thread(actualizador);
			// mensaje = "La actualización de catálogos ha sido iniciada";
			thread.start();
		} else {
			mensaje = "No es posible ejecutar el servicio de actualización de catálogos, es probable que exista una ejecución en progreso."
					+ " Favor de intentarlo más tarde";
		}
		return mensaje;
	}

	public int consultarDisponibilidadServicio() {

		CatalogosController catalogosController = new CatalogosController();
		return catalogosController.consultarCatalogosActivos();
	}

	public String consultarEstatusActualizacion() {
		mensaje="";
		setMostrarTabla(true);
		CatalogosController catalogosController = new CatalogosController();
		catalogos = catalogosController
				.consultarEstatusActualizacionCatalogos();
		return null;
	}

	public void consultarEstatusActualizacion2() {
		mensaje="";
		setMostrarTabla(true);
		CatalogosController catalogosController = new CatalogosController();
		ModelEstatusCatalogo[] regs = catalogosController
				.consultarEstatusActualizacionManual(configuracion
						.getIdUsuario());
		llenarListas(regs);
	}

	private void llenarListas(ModelEstatusCatalogo[] regs) {
		int idLDC = 0;
		int idMaster = 0;
		int contLDC = 1;
		int contMaster = 1;
		regsEOTS = new ArrayList<ActualizacionManual>();
		regsLDC = new ArrayList<ActualizacionManual>();
		for (ModelEstatusCatalogo r : regs) {
			if ("LDC->EOTS".equals(r.getTipo())) {
				if (idLDC == 0) {
					idLDC = r.getIdReplicacionTabla();
					ActualizacionManual a = new ActualizacionManual();
					a.setAvance(r.getAvance());
					a.setDescripcion(r.getNombreOrigen());
					a.setEstatus(r.getEstatus());
					regsLDC.add(a);
				} else {
					if (idLDC == r.getIdReplicacionTabla()) {
						// es porque se tiene que aumentar
						ActualizacionManual a = new ActualizacionManual();
						a.setAvance(r.getAvance());
						a.setDescripcion(r.getNombreOrigen() + " - "
								+ (contLDC + 1));
						a.setEstatus(r.getEstatus());
						regsLDC.add(a);
						contLDC++;
					} else {
						// son diferentes
						idLDC = r.getIdReplicacionTabla();
						ActualizacionManual a = new ActualizacionManual();
						a.setAvance(r.getAvance());
						a.setDescripcion(r.getNombreOrigen());
						a.setEstatus(r.getEstatus());
						regsLDC.add(a);
						contLDC = 1;
					}
				}
			}
			if ("EOTS->LDC".equals(r.getTipo())) {
				if (idMaster == 0) {
					idMaster = r.getIdReplicacionTabla();
					ActualizacionManual a = new ActualizacionManual();
					a.setAvance(r.getAvance());
					a.setDescripcion(r.getNombreOrigen());
					a.setEstatus(r.getEstatus());
					regsEOTS.add(a);
				} else {
					if (idMaster == r.getIdReplicacionTabla()) {
						// es porque se tiene que aumentar
						ActualizacionManual a = new ActualizacionManual();
						a.setAvance(r.getAvance());
						a.setDescripcion(r.getNombreOrigen() + " - "
								+ (contMaster + 1));
						a.setEstatus(r.getEstatus());
						regsEOTS.add(a);
						contMaster++;
					} else {
						// son diferentes
						idMaster = r.getIdReplicacionTabla();
						ActualizacionManual a = new ActualizacionManual();
						a.setAvance(r.getAvance());
						a.setDescripcion(r.getNombreOrigen());
						a.setEstatus(r.getEstatus());
						regsEOTS.add(a);
						contMaster = 1;
					}
				}
			}
		}
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the catalogos
	 */
	public List<Catalogo> getCatalogos() {
		return catalogos;
	}

	/**
	 * @param catalogos
	 *            the catalogos to set
	 */
	public void setCatalogos(List<Catalogo> catalogos) {
		this.catalogos = catalogos;
	}

	/**
	 * @return the catalogo
	 */
	public Catalogo getCatalogo() {
		return catalogo;
	}

	/**
	 * @param catalogo
	 *            the catalogo to set
	 */
	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	/**
	 * @return the mostrarTabla
	 */
	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	/**
	 * @param mostrarTabla
	 *            the mostrarTabla to set
	 */
	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

}
