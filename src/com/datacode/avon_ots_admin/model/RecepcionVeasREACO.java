/**
 * Mapeo de las propiedades del Objeto de la BD con PW_RECEPCION_VEAS_REACO
 * @author brenda.estrada
 * @since 29/12/2011
 */
package com.datacode.avon_ots_admin.model;

/**
 * @author brenda.estrada
 * @since 29/12/2011
 */
public class RecepcionVeasREACO {
	
	//Atributos del objeto
	private Integer idRecepcionVeas = 0;
	private String fechaRecep = "";
	private String fechaUpd = "";
	private String userUpd = "";
	//Dependencias
	private Integer idUser = 0;
	private Integer idZona = 0;
	private String descZona = "";
	private Integer idCampania = 0;
	private String descCampania = "";
	
	
	/**
	 * Constructor
	 * @author brenda.estrada
	 * @since 29/12/2011
	 */
	public RecepcionVeasREACO(Integer idRecepcionVeas,
							String fechaRecep,
							String fechaUpd,
							String userUpd,
							Integer idUser,
							Integer idZona,
							String descZona,
							Integer idCampania,
							String descCampania) {
		this.idRecepcionVeas = idRecepcionVeas;
		this.fechaRecep = fechaRecep;
		this.fechaUpd = fechaUpd;
		this.userUpd =  userUpd;
		this.idUser = idUser;
		this.idZona = idZona;
		this.descZona = descZona;
		this.idCampania = idCampania;
		this.descCampania = descCampania;	
	}


	/**
	 * @return the idRecepcionVeas
	 */
	public Integer getIdRecepcionVeas() {
		return idRecepcionVeas;
	}


	/**
	 * @param idRecepcionVeas the idRecepcionVeas to set
	 */
	public void setIdRecepcionVeas(Integer idRecepcionVeas) {
		this.idRecepcionVeas = idRecepcionVeas;
	}


	/**
	 * @return the fechaRecep
	 */
	public String getFechaRecep() {
		return fechaRecep;
	}


	/**
	 * @param fechaRecep the fechaRecep to set
	 */
	public void setFechaRecep(String fechaRecep) {
		this.fechaRecep = fechaRecep;
	}


	/**
	 * @return the fechaUpd
	 */
	public String getFechaUpd() {
		return fechaUpd;
	}


	/**
	 * @param fechaUpd the fechaUpd to set
	 */
	public void setFechaUpd(String fechaUpd) {
		this.fechaUpd = fechaUpd;
	}


	/**
	 * @return the userUpd
	 */
	public String getUserUpd() {
		return userUpd;
	}


	/**
	 * @param userUpd the userUpd to set
	 */
	public void setUserUpd(String userUpd) {
		this.userUpd = userUpd;
	}


	/**
	 * @return the idUser
	 */
	public Integer getIdUser() {
		return idUser;
	}


	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}


	/**
	 * @return the idZona
	 */
	public Integer getIdZona() {
		return idZona;
	}


	/**
	 * @param idZona the idZona to set
	 */
	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}


	/**
	 * @return the descZona
	 */
	public String getDescZona() {
		return descZona;
	}


	/**
	 * @param descZona the descZona to set
	 */
	public void setDescZona(String descZona) {
		this.descZona = descZona;
	}


	/**
	 * @return the idCampania
	 */
	public Integer getIdCampania() {
		return idCampania;
	}


	/**
	 * @param idCampania the idCampania to set
	 */
	public void setIdCampania(Integer idCampania) {
		this.idCampania = idCampania;
	}


	/**
	 * @return the descCampania
	 */
	public String getDescCampania() {
		return descCampania;
	}


	/**
	 * @param descCampania the descCampania to set
	 */
	public void setDescCampania(String descCampania) {
		this.descCampania = descCampania;
	}

}
