/**
 * Mapeo de las propiedades del Objeto de la BD con Rutas - PW_RUTAS
 */
package com.datacode.avon_ots_admin.model;

import java.util.Date;

/**
 * @author   brenda.estrada
 * @since    21-12-2011
 * @category Map
 */
public class Rutas {
	
	//Definicion de atributos del objeto
	private String idRuta = "";
	private String cveRuta = "";
	private String descRuta = "";
	private String fechaUpd = "";
	private String userUpd = "";
	//Atributos Nuevos
	private String poblacionColonia = "";
	private Double kmPromedio;
	private int    noPromedioOrdenes = 0;
	private String   tiempoPromEfectivoReparto="HH:MM:SS", tiempoPromTotalReparto="HH:MM:SS";
	private Integer idTipoRiesgo = 0;
	private String tipoRiesgo = "";
	private int diaReparto =1;
	private Boolean activoReparto =true;
	private int idCampaniaReparoSinHH = 0;
	private String gridActivoReparto="";
	
	//Dependencias
	private String idZona = "";
	private String descZona = "";
	private String idPais = "";
	private String descPais = "";
	private String idTipoRuta = "";
	private String descTipoRuta = "";
	private String idLdc = "";
	private String descLdc = "";
	
	public Rutas(String idRuta, String cveRuta,
				 String descRuta, String fechaUpd,
				 String userUpd, String idZona,
				 String descZona, String idPais,
				 String descPais, String idTipoRuta,
				 String descTipoRuta , String idLdc,
				 String descLdc,
				 String poblacionColonia, Double kmPromedio, int noPromedioOrdenes,
				 String tiempoPromEfectivoReparto, String tiempoPromTotalReparto,
				 Integer idTipoRiesgo, String tipoRiesgo, int diaReparto, Boolean activoReparto, int idCampaniaReaprtoSinHH, String gridActivoReparto) {
		this.idRuta = idRuta;
		this.cveRuta = cveRuta;
		this.descRuta = descRuta;
		this.fechaUpd = fechaUpd;
		this.userUpd = userUpd;
		this.idZona = idZona;
		this.descZona = descZona;
		this.idPais = idPais;
		this.descPais =descPais;
		this.idTipoRuta = idTipoRuta;
		this.descTipoRuta = descTipoRuta;
		this.idLdc = idLdc; 	this.descLdc = descLdc;
		this.poblacionColonia = poblacionColonia;
		this.kmPromedio = kmPromedio;
		this.noPromedioOrdenes = noPromedioOrdenes;
		this.tiempoPromEfectivoReparto = tiempoPromEfectivoReparto;
		this.tiempoPromTotalReparto = tiempoPromTotalReparto;
		this.idTipoRiesgo = idTipoRiesgo;	this.tipoRiesgo = tipoRiesgo;
		this.diaReparto = diaReparto;
		this.activoReparto = activoReparto;
		this.idCampaniaReparoSinHH = idCampaniaReaprtoSinHH;
		this.gridActivoReparto = gridActivoReparto;
	}



	/**
	 * @return the idRuta
	 */
	public String getIdRuta() {
		return idRuta;
	}






	/**
	 * @param idRuta the idRuta to set
	 */
	public void setIdRuta(String idRuta) {
		this.idRuta = idRuta;
	}






	/**
	 * @return the cveRuta
	 */
	public String getCveRuta() {
		return cveRuta;
	}






	/**
	 * @param cveRuta the cveRuta to set
	 */
	public void setCveRuta(String cveRuta) {
		this.cveRuta = cveRuta;
	}






	/**
	 * @return the descRuta
	 */
	public String getDescRuta() {
		return descRuta;
	}






	/**
	 * @param descRuta the descRuta to set
	 */
	public void setDescRuta(String descRuta) {
		this.descRuta = descRuta;
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
	 * @return the idZona
	 */
	public String getIdZona() {
		return idZona;
	}






	/**
	 * @param idZona the idZona to set
	 */
	public void setIdZona(String idZona) {
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
	 * @return the idPais
	 */
	public String getIdPais() {
		return idPais;
	}






	/**
	 * @param idPais the idPais to set
	 */
	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}






	/**
	 * @return the descPais
	 */
	public String getDescPais() {
		return descPais;
	}






	/**
	 * @param descPais the descPais to set
	 */
	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}






	/**
	 * @return the idTipoRuta
	 */
	public String getIdTipoRuta() {
		return idTipoRuta;
	}






	/**
	 * @param idTipoRuta the idTipoRuta to set
	 */
	public void setIdTipoRuta(String idTipoRuta) {
		this.idTipoRuta = idTipoRuta;
	}






	/**
	 * @return the descTipoRuta
	 */
	public String getDescTipoRuta() {
		return descTipoRuta;
	}






	/**
	 * @param descTipoRuta the descTipoRuta to set
	 */
	public void setDescTipoRuta(String descTipoRuta) {
		this.descTipoRuta = descTipoRuta;
	}






	/**
	 * @return the idLdc
	 */
	public String getIdLdc() {
		return idLdc;
	}






	/**
	 * @param idLdc the idLdc to set
	 */
	public void setIdLdc(String idLdc) {
		this.idLdc = idLdc;
	}






	/**
	 * @return the descLdc
	 */
	public String getDescLdc() {
		return descLdc;
	}






	/**
	 * @param descLdc the descLdc to set
	 */
	public void setDescLdc(String descLdc) {
		this.descLdc = descLdc;
	}



	/**
	 * @return the poblacionColonia
	 */
	public String getPoblacionColonia() {
		return poblacionColonia;
	}



	/**
	 * @param poblacionColonia the poblacionColonia to set
	 */
	public void setPoblacionColonia(String poblacionColonia) {
		this.poblacionColonia = poblacionColonia;
	}



	/**
	 * @return the kmPromedio
	 */
	public Double getKmPromedio() {
		return kmPromedio;
	}



	/**
	 * @param kmPromedio the kmPromedio to set
	 */
	public void setKmPromedio(Double kmPromedio) {
		this.kmPromedio = kmPromedio;
	}



	/**
	 * @return the noPromedioOrdenes
	 */
	public int getNoPromedioOrdenes() {
		return noPromedioOrdenes;
	}



	/**
	 * @param noPromedioOrdenes the noPromedioOrdenes to set
	 */
	public void setNoPromedioOrdenes(int noPromedioOrdenes) {
		this.noPromedioOrdenes = noPromedioOrdenes;
	}



	/**
	 * @return the idTipoRiesgo
	 */
	public Integer getIdTipoRiesgo() {
		return idTipoRiesgo;
	}



	/**
	 * @param idTipoRiesgo the idTipoRiesgo to set
	 */
	public void setIdTipoRiesgo(Integer idTipoRiesgo) {
		this.idTipoRiesgo = idTipoRiesgo;
	}



	/**
	 * @return the tipoRiesgo
	 */
	public String getTipoRiesgo() {
		return tipoRiesgo;
	}



	/**
	 * @param tipoRiesgo the tipoRiesgo to set
	 */
	public void setTipoRiesgo(String tipoRiesgo) {
		this.tipoRiesgo = tipoRiesgo;
	}



	/**
	 * @return the tiempoPromEfectivoReparto
	 */
	public String getTiempoPromEfectivoReparto() {
		return tiempoPromEfectivoReparto;
	}



	/**
	 * @param tiempoPromEfectivoReparto the tiempoPromEfectivoReparto to set
	 */
	public void setTiempoPromEfectivoReparto(String tiempoPromEfectivoReparto) {
		this.tiempoPromEfectivoReparto = tiempoPromEfectivoReparto;
	}



	/**
	 * @return the tiempoPromTotalReparto
	 */
	public String getTiempoPromTotalReparto() {
		return tiempoPromTotalReparto;
	}



	/**
	 * @param tiempoPromTotalReparto the tiempoPromTotalReparto to set
	 */
	public void setTiempoPromTotalReparto(String tiempoPromTotalReparto) {
		this.tiempoPromTotalReparto = tiempoPromTotalReparto;
	}



	public int getDiaReparto() {
		return diaReparto;
	}



	public void setDiaReparto(int diaReparto) {
		this.diaReparto = diaReparto;
	}



	public Boolean getActivoReparto() {
		return activoReparto;
	}



	public void setActivoReparto(Boolean activoReparto) {
		this.activoReparto = activoReparto;
	}



	public int getIdCampaniaReparoSinHH() {
		return idCampaniaReparoSinHH;
	}



	public void setIdCampaniaReparoSinHH(int idCampaniaReparoSinHH) {
		this.idCampaniaReparoSinHH = idCampaniaReparoSinHH;
	}



	public String getGridActivoReparto() {
		return gridActivoReparto;
	}



	public void setGridActivoReparto(String gridActivoReparto) {
		this.gridActivoReparto = gridActivoReparto;
	}


}
