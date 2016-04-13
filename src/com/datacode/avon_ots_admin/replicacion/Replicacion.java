package com.datacode.avon_ots_admin.replicacion;


public class Replicacion {
	private Long idReplicacion;
	private String tipoReplicacion;
	private String fechaInicio;
	private String fechaFin;
	private String estatus;
	private String avance = "";
	private String fechaUltimaActualizacion;
	private String usuarioActualiza;
	private String logEjecucion;
	private Long idLDC;
	private String ldc;
	/**
	 * @param idReplicacion
	 * @param tipoReplicacion
	 * @param fechaInicio
	 * @param fechaFin
	 * @param estatus
	 * @param avance
	 * @param fechaUltimaActualizacion
	 * @param usuarioActualiza
	 * @param logEjecucion
	 */
	public Replicacion(Long idReplicacion, String tipoReplicacion,
			String fechaInicio, String fechaFin, String estatus, String avance,
			String fechaUltimaActualizacion, String usuarioActualiza,
			String logEjecucion) {
		super();
		this.idReplicacion = idReplicacion;
		this.tipoReplicacion = tipoReplicacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estatus = estatus;
		this.avance = avance;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
		this.usuarioActualiza = usuarioActualiza;
		this.logEjecucion = logEjecucion;
	}
	/**
	 * 
	 */
	public Replicacion() {
		super();
		
	}
	
	
	public String getLdc() {
		return ldc;
	}
	public void setLdc(String ldc) {
		this.ldc = ldc;
	}
	/**
	 * @param idReplicacion
	 * @param tipoReplicacion
	 * @param fechaInicio
	 * @param fechaFin
	 * @param estatus
	 * @param avance
	 * @param fechaUltimaActualizacion
	 * @param usuarioActualiza
	 * @param logEjecucion
	 * @param idLDC
	 */
	public Replicacion(Long idReplicacion, String tipoReplicacion,
			String fechaInicio, String fechaFin, String estatus, String avance,
			String fechaUltimaActualizacion, String usuarioActualiza,
			String logEjecucion, Long idLDC) {
		super();
		this.idReplicacion = idReplicacion;
		this.tipoReplicacion = tipoReplicacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estatus = estatus;
		this.avance = avance;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
		this.usuarioActualiza = usuarioActualiza;
		this.logEjecucion = logEjecucion;
		this.idLDC = idLDC;
	}
	
	
	/**
	 * @return the idLDC
	 */
	public Long getIdLDC() {
		return idLDC;
	}
	/**
	 * @param idLDC the idLDC to set
	 */
	public void setIdLDC(Long idLDC) {
		this.idLDC = idLDC;
	}
	/**
	 * @return the idReplicacion
	 */
	public Long getIdReplicacion() {
		return idReplicacion;
	}
	/**
	 * @param idReplicacion the idReplicacion to set
	 */
	public void setIdReplicacion(Long idReplicacion) {
		this.idReplicacion = idReplicacion;
	}
	/**
	 * @return the tipoReplicacion
	 */
	public String getTipoReplicacion() {
		return tipoReplicacion;
	}
	/**
	 * @param tipoReplicacion the tipoReplicacion to set
	 */
	public void setTipoReplicacion(String tipoReplicacion) {
		this.tipoReplicacion = tipoReplicacion;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return the avance
	 */
	public String getAvance() {
		return avance;
	}
	/**
	 * @param avance the avance to set
	 */
	public void setAvance(String avance) {
		this.avance = avance;
	}
	/**
	 * @return the fechaUltimaActualizacion
	 */
	public String getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	/**
	 * @param fechaUltimaActualizacion the fechaUltimaActualizacion to set
	 */
	public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}
	/**
	 * @return the usuarioActualiza
	 */
	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}
	/**
	 * @param usuarioActualiza the usuarioActualiza to set
	 */
	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}
	/**
	 * @return the logEjecucion
	 */
	public String getLogEjecucion() {
		return logEjecucion;
	}
	/**
	 * @param logEjecucion the logEjecucion to set
	 */
	public void setLogEjecucion(String logEjecucion) {
		this.logEjecucion = logEjecucion;
	}
	@Override
	public String toString() {
		return "Replicacion [idReplicacion=" + idReplicacion
				+ ", tipoReplicacion=" + tipoReplicacion + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", estatus="
				+ estatus + ", avance=" + avance
				+ ", fechaUltimaActualizacion=" + fechaUltimaActualizacion
				+ ", usuarioActualiza=" + usuarioActualiza + ", logEjecucion="
				+ logEjecucion + ", idLDC=" + idLDC + ", ldc=" + ldc + "]";
	}
	
	
	
	
	
	
	
	
}
