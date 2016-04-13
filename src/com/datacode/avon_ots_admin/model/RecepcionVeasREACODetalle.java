/**
 * Mapeo de las propiedades del Objeto de la BD con PW_RECEPCION_VEAS_REACO_DETALLE
 * @author brenda.estrada
 * @since 29/12/2011
 */
package com.datacode.avon_ots_admin.model;

/**
 * @author brenda.estrada
 * @since 29/12/2011
 * @category Map
 */
public class RecepcionVeasREACODetalle {
	//Atributos del objeto
	//private Integer idRecepDetalleVeas = 0;
	//private Integer idRecepcionVeas = 0;
	private String descDetalle = "";
	private String codigoBarras = "";
	private String uEntrego = "";
	private String fEntrega = "";
	private String uRecibio = "";
	private Integer unidad = 0;
	private byte noCajas = 0;
	//Pendientes de definir  ID_ESTATUS y ID_DEVOLVER_VEAS
	
	/**
	 * Constructor
	 * @author brenda.estrada
	 * @since 29/12/2011
	 */
	public RecepcionVeasREACODetalle(String descDetalle,
									String codigoBarras,
									String uEntrego,
									String fEntrega,
									String uRecibio,
									Integer unidad,
									byte noCajas) {
		//this.idRecepDetalleVeas = idRecepDetalleVeas;
		//this.idRecepcionVeas = idRecepcionVeas;
		this.descDetalle = descDetalle;
		this.codigoBarras = codigoBarras;
		this.uEntrego = uEntrego;
		this.fEntrega = fEntrega;
		this.uRecibio = uRecibio;
		this.unidad = unidad;
		this.noCajas = noCajas;
	}
	
	public RecepcionVeasREACODetalle(){
		
	}


	/**
	 * @return the idRecepDetalleVeas
	 */
	
	/**
	 * @return the descDetalle
	 */
	public String getDescDetalle() {
		return descDetalle;
	}


	/**
	 * @param descDetalle the descDetalle to set
	 */
	public void setDescDetalle(String descDetalle) {
		this.descDetalle = descDetalle;
	}


	/**
	 * @return the codigoBarras
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}


	/**
	 * @param codigoBarras the codigoBarras to set
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}


	/**
	 * @return the uEntrego
	 */
	public String getuEntrego() {
		return uEntrego;
	}


	/**
	 * @param uEntrego the uEntrego to set
	 */
	public void setuEntrego(String uEntrego) {
		this.uEntrego = uEntrego;
	}


	/**
	 * @return the fEntrega
	 */
	public String getfEntrega() {
		return fEntrega;
	}


	/**
	 * @param fEntrega the fEntrega to set
	 */
	public void setfEntrega(String fEntrega) {
		this.fEntrega = fEntrega;
	}


	/**
	 * @return the uRecibio
	 */
	public String getuRecibio() {
		return uRecibio;
	}


	/**
	 * @param uRecibio the uRecibio to set
	 */
	public void setuRecibio(String uRecibio) {
		this.uRecibio = uRecibio;
	}


	/**
	 * @return the unidad
	 */
	public Integer getUnidad() {
		return unidad;
	}


	/**
	 * @param unidad the unidad to set
	 */
	public void setUnidad(Integer unidad) {
		this.unidad = unidad;
	}


	/**
	 * @return the noCajas
	 */
	public byte getNoCajas() {
		return noCajas;
	}


	/**
	 * @param noCajas the noCajas to set
	 */
	public void setNoCajas(byte noCajas) {
		this.noCajas = noCajas;
	}
	
	

}
