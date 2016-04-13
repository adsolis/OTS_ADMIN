/**
 * Mapeo de las propiedades del Objeto de la BD - PW_BANCOS
 * @author jose.ponce
 * @since 17/01/2012
 */
package com.datacode.avon_ots_admin.model;

/**
 * @author jose.ponce
 * @category Map
 * @version  Se agrego atributo lastupd_ts
 */
public class Bancos {
	
	private Integer IdBanco=0;
	private String Clave="0";
	private String Nombre="";
	private String lastupd_ts = "";
	private String fechaActualizado = "", usuarioActualiza = "";
	
	/**
	 * Constructor
	 * @author brenda.estrada
	 * @since 18/01/2012
	 * @param IdBanco
	 * @param Clave
	 * @param Nombre
	 * @param lastupd_ts
	 * @param fechaActualizado
	 * @param usuarioActualiza
	 */
	public Bancos(Integer IdBanco, String Clave,
		String Nombre, String lastupd_ts, String fechaActualizado, String usuarioActualiza){
		this.IdBanco = IdBanco;		this.Clave = Clave;
		this.Nombre =  Nombre;		this.lastupd_ts = lastupd_ts;
		this.fechaActualizado = fechaActualizado;	this.usuarioActualiza = usuarioActualiza;
	}

	/**
	 * @return the idBanco
	 */
	public Integer getIdBanco() {
		return IdBanco;
	}

	/**
	 * @param idBanco the idBanco to set
	 */
	public void setIdBanco(Integer idBanco) {
		IdBanco = idBanco;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return Clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		Clave = clave;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return Nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	/**
	 * @return the lastupd_ts
	 */
	public String getLastupd_ts() {
		return lastupd_ts;
	}

	/**
	 * @param lastupd_ts the lastupd_ts to set
	 */
	public void setLastupd_ts(String lastupd_ts) {
		this.lastupd_ts = lastupd_ts;
	}

	/**
	 * @return the fechaActualizado
	 */
	public String getFechaActualizado() {
		return fechaActualizado;
	}

	/**
	 * @param fechaActualizado the fechaActualizado to set
	 */
	public void setFechaActualizado(String fechaActualizado) {
		this.fechaActualizado = fechaActualizado;
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
	
	
	
}
