package com.datacode.avon_ots_admin.replicacion;
public class ReplicacionTabla {
	private Long idReplicacionTabla;
	private String nombreOrigen;
	private String nombreDestino;
	private String camposLLavePrimaria;
	private String camposDestinoIgorar;
	private String grupo;
	private String tipoReplicacion;
	private String fechaActualizacionTabla;
	private String fechaUltimaReplicacion;
	private Integer activa;
	private Long idLDC;
	private String ldc;
	/**
	 * @param idReplicacionTabla
	 * @param nombreOrigen
	 * @param nombreDestino
	 * @param camposLLavePrimaria
	 * @param camposDestinoIgorar
	 * @param grupo
	 * @param tipoReplicacion
	 * @param fechaActualizacionTabla
	 * @param fechaUltimaReplicacion
	 * @param activa
	 */
	public ReplicacionTabla(Long idReplicacionTabla, String nombreOrigen,
			String nombreDestino, String camposLLavePrimaria,
			String camposDestinoIgorar, String grupo, String tipoReplicacion,
			String fechaActualizacionTabla, String fechaUltimaReplicacion,
			Integer activa) {
		super();
		this.idReplicacionTabla = idReplicacionTabla;
		this.nombreOrigen = nombreOrigen;
		this.nombreDestino = nombreDestino;
		this.camposLLavePrimaria = camposLLavePrimaria;
		this.camposDestinoIgorar = camposDestinoIgorar;
		this.grupo = grupo;
		this.tipoReplicacion = tipoReplicacion;
		this.fechaActualizacionTabla = fechaActualizacionTabla;
		this.fechaUltimaReplicacion = fechaUltimaReplicacion;
		this.activa = activa;
	}
	/**
	 * 
	 */
	public ReplicacionTabla() {
		super();
		
	}
	
	
	/**
	 * @param idReplicacionTabla
	 * @param nombreOrigen
	 * @param nombreDestino
	 * @param camposLLavePrimaria
	 * @param camposDestinoIgorar
	 * @param grupo
	 * @param tipoReplicacion
	 * @param fechaActualizacionTabla
	 * @param fechaUltimaReplicacion
	 * @param activa
	 * @param idLDC
	 */
	public ReplicacionTabla(Long idReplicacionTabla, String nombreOrigen,
			String nombreDestino, String camposLLavePrimaria,
			String camposDestinoIgorar, String grupo, String tipoReplicacion,
			String fechaActualizacionTabla, String fechaUltimaReplicacion,
			Integer activa, Long idLDC) {
		super();
		this.idReplicacionTabla = idReplicacionTabla;
		this.nombreOrigen = nombreOrigen;
		this.nombreDestino = nombreDestino;
		this.camposLLavePrimaria = camposLLavePrimaria;
		this.camposDestinoIgorar = camposDestinoIgorar;
		this.grupo = grupo;
		this.tipoReplicacion = tipoReplicacion;
		this.fechaActualizacionTabla = fechaActualizacionTabla;
		this.fechaUltimaReplicacion = fechaUltimaReplicacion;
		this.activa = activa;
		this.idLDC = idLDC;
	}
	
	
	/**
	 * 
	 * @param idReplicacionTabla
	 * @param nombreOrigen
	 * @param nombreDestino
	 * @param camposLLavePrimaria
	 * @param camposDestinoIgorar
	 * @param grupo
	 * @param tipoReplicacion
	 * @param fechaActualizacionTabla
	 * @param fechaUltimaReplicacion
	 * @param activa
	 * @param ldc
	 */
	public ReplicacionTabla(Long idReplicacionTabla, String nombreOrigen,
			String nombreDestino, String camposLLavePrimaria,
			String camposDestinoIgorar, String grupo, String tipoReplicacion,
			String fechaActualizacionTabla, String fechaUltimaReplicacion,
			Integer activa, String ldc) {
		super();
		this.idReplicacionTabla = idReplicacionTabla;
		this.nombreOrigen = nombreOrigen;
		this.nombreDestino = nombreDestino;
		this.camposLLavePrimaria = camposLLavePrimaria;
		this.camposDestinoIgorar = camposDestinoIgorar;
		this.grupo = grupo;
		this.tipoReplicacion = tipoReplicacion;
		this.fechaActualizacionTabla = fechaActualizacionTabla;
		this.fechaUltimaReplicacion = fechaUltimaReplicacion;
		this.activa = activa;
		this.ldc = ldc;
	}
	public String getLdc() {
		return ldc;
	}
	public void setLdc(String ldc) {
		this.ldc = ldc;
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
	 * @return the nombreOrigen
	 */
	public String getNombreOrigen() {
		return nombreOrigen;
	}
	/**
	 * @param nombreOrigen the nombreOrigen to set
	 */
	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}
	/**
	 * @return the nombreDestino
	 */
	public String getNombreDestino() {
		return nombreDestino;
	}
	/**
	 * @param nombreDestino the nombreDestino to set
	 */
	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}
	/**
	 * @return the camposLLavePrimaria
	 */
	public String getCamposLLavePrimaria() {
		return camposLLavePrimaria;
	}
	/**
	 * @param camposLLavePrimaria the camposLLavePrimaria to set
	 */
	public void setCamposLLavePrimaria(String camposLLavePrimaria) {
		this.camposLLavePrimaria = camposLLavePrimaria;
	}
	/**
	 * @return the camposDestinoIgorar
	 */
	public String getCamposDestinoIgorar() {
		return camposDestinoIgorar;
	}
	/**
	 * @param camposDestinoIgorar the camposDestinoIgorar to set
	 */
	public void setCamposDestinoIgorar(String camposDestinoIgorar) {
		this.camposDestinoIgorar = camposDestinoIgorar;
	}
	/**
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}
	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
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
	 * @return the fechaActualizacionTabla
	 */
	public String getFechaActualizacionTabla() {
		return fechaActualizacionTabla;
	}
	/**
	 * @param fechaActualizacionTabla the fechaActualizacionTabla to set
	 */
	public void setFechaActualizacionTabla(String fechaActualizacionTabla) {
		this.fechaActualizacionTabla = fechaActualizacionTabla;
	}
	/**
	 * @return the fechaUltimaReplicacion
	 */
	public String getFechaUltimaReplicacion() {
		return fechaUltimaReplicacion;
	}
	/**
	 * @param fechaUltimaReplicacion the fechaUltimaReplicacion to set
	 */
	public void setFechaUltimaReplicacion(String fechaUltimaReplicacion) {
		this.fechaUltimaReplicacion = fechaUltimaReplicacion;
	}
	/**
	 * @return the activa
	 */
	public Integer getActiva() {
		return activa;
	}
	/**
	 * @param activa the activa to set
	 */
	public void setActiva(Integer activa) {
		this.activa = activa;
	}
	
	
}
