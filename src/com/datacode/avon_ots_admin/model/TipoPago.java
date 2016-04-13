/**
 * Mapeo de las propiedades del Objeto de la BD - PW_TIPO_PAGO
 * @author brenda.estrada
 * @since 18/01/2012
 */
package com.datacode.avon_ots_admin.model;

/**
 * @author brenda.estrada
 * @category Map
 */
public class TipoPago {
	
	private Integer idTipoPago = 0;
	private String descripcion = "", fechaActualizado = "", usuarioActualiza = "";
	
	public TipoPago(Integer idTipoPago, String descripcion, String fechaActualizado, String usuarioActualiza){
		this.idTipoPago = idTipoPago;	this.descripcion =  descripcion;
		this.fechaActualizado = fechaActualizado;	this.usuarioActualiza = usuarioActualiza;
	}

	/**
	 * @return the idTipoPago
	 */
	public Integer getIdTipoPago() {
		return idTipoPago;
	}

	/**
	 * @param idTipoPago the idTipoPago to set
	 */
	public void setIdTipoPago(Integer idTipoPago) {
		this.idTipoPago = idTipoPago;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
