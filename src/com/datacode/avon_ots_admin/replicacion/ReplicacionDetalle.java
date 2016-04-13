package com.datacode.avon_ots_admin.replicacion;


public class ReplicacionDetalle {
	private Long idReplicacion;
	private Long idReplicacionTabla;
	private String nombreArchivo;
	private String nombreZIP;
	private String fechaInicio;
	private String fechaFin;
	private String fechaInformacionIni;
	private String fechaInformacionFin;
	private String estatus;
	private String avance;
	private String fechaUltimaActualizacion;
	private String logEjecucion;
	private Long idLDC;
	/**
	 * @param idReplicacion
	 * @param idReplicacionTabla
	 * @param nombreArchivo
	 * @param nombreZIP
	 * @param fechaInicio
	 * @param fechaFin
	 * @param fechaInformacionIni
	 * @param fechaInformacionFin
	 * @param estatus
	 * @param avance
	 * @param fechaUltimaActualizacion
	 * @param logEjecucion
	 */
	public ReplicacionDetalle(Long idReplicacion, Long idReplicacionTabla,
			String nombreArchivo, String nombreZIP, String fechaInicio,
			String fechaFin, String fechaInformacionIni, String fechaInformacionFin,
			String estatus, String avance, String fechaUltimaActualizacion,
			String logEjecucion) {
		super();
		this.idReplicacion = idReplicacion;
		this.idReplicacionTabla = idReplicacionTabla;
		this.nombreArchivo = nombreArchivo;
		this.nombreZIP = nombreZIP;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaInformacionIni = fechaInformacionIni;
		this.fechaInformacionFin = fechaInformacionFin;
		this.estatus = estatus;
		this.avance = avance;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
		this.logEjecucion = logEjecucion;
	}
	
	
	/**
	 * @param idReplicacion
	 * @param idReplicacionTabla
	 * @param nombreArchivo
	 * @param nombreZIP
	 * @param fechaInicio
	 * @param fechaFin
	 * @param fechaInformacionIni
	 * @param fechaInformacionFin
	 * @param estatus
	 * @param avance
	 * @param fechaUltimaActualizacion
	 * @param logEjecucion
	 * @param idLDC
	 */
	public ReplicacionDetalle(Long idReplicacion, Long idReplicacionTabla,
			String nombreArchivo, String nombreZIP, String fechaInicio,
			String fechaFin, String fechaInformacionIni, String fechaInformacionFin,
			String estatus, String avance, String fechaUltimaActualizacion,
			String logEjecucion, Long idLDC) {
		super();
		this.idReplicacion = idReplicacion;
		this.idReplicacionTabla = idReplicacionTabla;
		this.nombreArchivo = nombreArchivo;
		this.nombreZIP = nombreZIP;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaInformacionIni = fechaInformacionIni;
		this.fechaInformacionFin = fechaInformacionFin;
		this.estatus = estatus;
		this.avance = avance;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
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
	 * 
	 */
	public ReplicacionDetalle() {
		super();
	
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
	 * @return the idReplicacionTabla
	 */
	public Long getIdReplicacionTabla() {
		return idReplicacionTabla;
	}
	/**
	 * @param idReplicacionTabla the idReplicacionTabla to set
	 */
	public void setIdReplicacionTabla(Long idReplicacionTabla) {
		this.idReplicacionTabla = idReplicacionTabla;
	}
	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	/**
	 * @return the nombreZIP
	 */
	public String getNombreZIP() {
		return nombreZIP;
	}
	/**
	 * @param nombreZIP the nombreZIP to set
	 */
	public void setNombreZIP(String nombreZIP) {
		this.nombreZIP = nombreZIP;
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
	 * @return the fechaInformacionIni
	 */
	public String getFechaInformacionIni() {
		return fechaInformacionIni;
	}
	/**
	 * @param fechaInformacionIni the fechaInformacionIni to set
	 */
	public void setFechaInformacionIni(String fechaInformacionIni) {
		this.fechaInformacionIni = fechaInformacionIni;
	}
	/**
	 * @return the fechaInformacionFin
	 */
	public String getFechaInformacionFin() {
		return fechaInformacionFin;
	}
	/**
	 * @param fechaInformacionFin the fechaInformacionFin to set
	 */
	public void setFechaInformacionFin(String fechaInformacionFin) {
		this.fechaInformacionFin = fechaInformacionFin;
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
		return "ReplicacionDetalle [idReplicacion=" + idReplicacion
				+ ", idReplicacionTabla=" + idReplicacionTabla
				+ ", nombreArchivo=" + nombreArchivo + ", nombreZIP="
				+ nombreZIP + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + ", fechaInformacionIni=" + fechaInformacionIni
				+ ", fechaInformacionFin=" + fechaInformacionFin + ", estatus="
				+ estatus + ", avance=" + avance
				+ ", fechaUltimaActualizacion=" + fechaUltimaActualizacion
				+ ", logEjecucion=" + logEjecucion + ", idLDC=" + idLDC + "]";
	}
	
	
}
