package com.datacode.avon_ots_admin.model;
/**
 * Mapeo de las propiedades del Objeto de la BD con Rutas - PW_CAMPANIA
 */


/**
 * @author jessica.leon
 * @since 27/12/2011
 * @category Map
 * @author brenda.estrada - Se cambio tipo de Date a String
 */
public class Campania {
	//Atributos del Objeto
	private int idCampania = 0;
	private int anioCampania = 0;
	private int campania = 0;
	private String fechaInicio  ="";
	private String fechaFin = "";
	private String lastUpdTs = "";
	private String fechaActualizado  = "";
	private String usuarioActualiza  = "";
	//b.estrada: se agrego Zona
	private int idZona;
	private String descZona;

	/* Constructor */
	public Campania(){
		
		idCampania = 0;
		anioCampania = 0;
		campania = 0;
		fechaInicio = null;
		fechaFin = null;
		lastUpdTs = null;
		fechaActualizado = null;
		usuarioActualiza = null;
		idZona = 0;
		descZona = null;
	}
	
	/**
	 * Constructor Parametros
	 * @author brenda.estrada
	 * @since 18/01/2012
	 * @param idCampania
	 * @param anioCampania
	 * @param campania
	 * @param fechaInicio
	 * @param fechaFin
	 * @param lastUpdTs
	 * @param fechaActualizado
	 * @param usuarioActualiza
	 */
	public Campania(int idCampania, int anioCampania, int campania,
			String fechaInicio, String fechaFin, String lastUpdTs, String fechaActualizado, 
			String usuarioActualiza, int idZona, String descZona){
		this.idCampania = idCampania;	this.anioCampania = anioCampania;
		this.campania = campania;		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;		this.lastUpdTs = lastUpdTs;
		this.fechaActualizado = fechaActualizado;	this.usuarioActualiza = usuarioActualiza;
		this.idZona = idZona;			this.descZona = descZona;
	}
	
	

	/**
	 * @author jessica.leon
	 * @since 27/12/2011
	 * @return the idCampania
	 */
	public int getIdCampania() {
		return idCampania;
	}

	/**
	 * @author jessica.leon
	 * @since 27/12/2011
	 * @param idCampania
	 *            the idCampania to set
	 */
	public void setIdCampania(int idCampania) {
		this.idCampania = idCampania;
	}

	/**
	 * @author jessica.leon
	 * @since 27/12/2011
	 * @return the anioCampania
	 */
	public int getAnioCampania() {
		return anioCampania;
	}

	/**
	 * @author jessica.leon
	 * @since 27/12/2011
	 * @param anioCampania
	 *            the anioCampania to set
	 */
	public void setAnioCampania(int anioCampania) {
		this.anioCampania = anioCampania;
	}

	/**
	 * @author jessica.leon
	 * @since 27/12/2011
	 * @return the campania
	 */
	public int getCampania() {
		return campania;
	}

	/**
	 * @author jessica.leon
	 * @since 27/12/2011
	 * @param campania
	 *            the campania to set
	 */
	public void setCampania(int campania) {
		this.campania = campania;
	}

	
	/**
	 * @author jessica.leon
	 * @since 27/12/2011
	 * @return the usuarioActualiza
	 */
	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}

	/**
	 * @author jessica.leon
	 * @since 27/12/2011
	 * @param usuarioActualiza
	 *            the usuarioActualiza to set
	 */
	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
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
	 * @return the lastUpdTs
	 */
	public String getLastUpdTs() {
		return lastUpdTs;
	}

	/**
	 * @param lastUpdTs the lastUpdTs to set
	 */
	public void setLastUpdTs(String lastUpdTs) {
		this.lastUpdTs = lastUpdTs;
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
	 * @return the idZona
	 */
	public int getIdZona() {
		return idZona;
	}

	/**
	 * @param idZona the idZona to set
	 */
	public void setIdZona(int idZona) {
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

}
