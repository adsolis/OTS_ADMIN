/**
 * Mapeo de las propiedades del Objeto de la BD - // VIEW_PREMIOS //
 * @author brenda.estrada
 * @since 18/01/2012
 */
package com.datacode.avon_ots_admin.model;

/**
 * @author brenda.estrada
 * @since 18/01/2012
 */
public class Premios {
	//Atributos del Objeto
	private Integer idPremio = 0;
	private String  claveOrden = "", 		//ClaveOrden
					descripcion = "", 	//Descripcion
					cedi = "",	  	mail = "",
					registro = "",		//EXPR1-Concepto
					code = "",		//CodigoBarras
					campania = "", anio = "", observaciones ="",
					fechaActualizado = "", usuarioActualiza = "";
		
	/**
	 * Constructor
	 * @author brenda.estrada
	 * @since 18/01/2012
	 * @param idPremio
	 * @param claveOrden
	 * @param descripcion
	 * @param cedi
	 * @param mail
	 * @param registro
	 * @param code
	 * @param campania
	 * @param anio
	 * @param observaciones
	 */
	public Premios(Integer idPremio, String  claveOrden, String  descripcion,
			String  cedi,	String  mail, String  registro, 
			String  code,	String  campania, String  anio, String observaciones,
			String fechaActualizado, String usuarioActualiza) {
		this.idPremio = idPremio;		this.claveOrden = claveOrden;		this.descripcion = descripcion;
		this.cedi = cedi; 				this.mail = mail;					this.registro = registro;
		this.code = code;				this.campania = campania;			this.anio = anio;		
		this.observaciones = observaciones;		this.fechaActualizado = fechaActualizado;	this.usuarioActualiza = usuarioActualiza;
	}

	/**
	 * @return the idPremio
	 */
	public Integer getIdPremio() {
		return idPremio;
	}

	/**
	 * @param idPremio the idPremio to set
	 */
	public void setIdPremio(Integer idPremio) {
		this.idPremio = idPremio;
	}

	/**
	 * @return the claveOrden
	 */
	public String getClaveOrden() {
		return claveOrden;
	}

	/**
	 * @param claveOrden the claveOrden to set
	 */
	public void setClaveOrden(String claveOrden) {
		this.claveOrden = claveOrden;
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
	 * @return the cedi
	 */
	public String getCedi() {
		return cedi;
	}

	/**
	 * @param cedi the cedi to set
	 */
	public void setCedi(String cedi) {
		this.cedi = cedi;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the registro
	 */
	public String getRegistro() {
		return registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the campania
	 */
	public String getCampania() {
		return campania;
	}

	/**
	 * @param campania the campania to set
	 */
	public void setCampania(String campania) {
		this.campania = campania;
	}

	/**
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
