/**
 * Mapeo de las propiedades del Objeto de la BD con Rutas - PW_UNIDAD_REPARTO
 * @author brenda.estrada
 * @since 03/01/2012
 */
package com.datacode.avon_ots_admin.model;

/**
 * @author brenda.estrada
 * @since 03/01/2012
 * @category Map
 */
public class UnidadReparto {
	//Atributos de Mapeo del Objeto
	private Integer idUnidadReparto = 0;
	private String descModelo = "";
	private String anio = "";
	private String noSerie = "";
	private String noEconomico = "";
	private String color ="";
	private String placas = "";
	private String capacidadNoCajas = "";
	private String rendimiento = "";
	private String codigoBarras = "";
	private Boolean imprCodBarras = false;
	//Dependencias
	private Integer idEstatus = 0;
	private String descEstatus = "";
	
	private Integer idMarca = 0;
	private String descMarca = "";
	
	private Integer idLDC = 0;
	private String descLDC = "";
	
	private Integer idPais = 0;
	private String descPais = "";

	private String kilometraje = "";
	private Integer entregando = 0;
	private String entregandoMostrar = "";
	private Boolean entregandoCheck = false; 
	
	/**
	 * @author brenda.estrada
	 * @since 03/01/2012
	 */
	public UnidadReparto(Integer idUnidadReparto, String descModelo,
						 String anio,
						 String noSerie,
						 String noEconomico,
						 String color,
						 String placas,
						 String capacidadNoCajas,
						 String rendimiento,
						 String codigoBarras,
						 Integer idEstatus,
						 String descEstatus,
						 Integer idMarca,
						 String descMarca ,
						 Integer idLDC,
						 String descLDC,
						 Integer idPais,
						 String descPais,
						 String kilometraje,
						 Integer entregando) {
		
		this.idUnidadReparto = idUnidadReparto;
		this.anio = anio;
		this.noSerie = noSerie;
		this.noEconomico = noEconomico;
		this.color = color;
		this.placas = placas;
		this.capacidadNoCajas = capacidadNoCajas;
		this.rendimiento  = rendimiento;
		this.codigoBarras = codigoBarras;
		this.idEstatus = idEstatus;
		this.descEstatus = descEstatus;
		this.idMarca = idMarca;
		this.descMarca = descMarca;
		this.idLDC = idLDC;
		this.descLDC = descLDC;
		this.idPais = idPais;
		this.descPais = descPais;
		this.kilometraje = kilometraje;
		this.setEntregando(entregando);
	}

	public Boolean getEntregandoCheck() {
		return entregandoCheck;
	}

	public void setEntregandoCheck(Boolean entregandoCheck) {
		this.entregandoCheck = entregandoCheck;
	}

	public String getEntregandoMostrar() {
		return entregandoMostrar;
	}

	public void setEntregandoMostrar(String entregandoMostrar) {
		this.entregandoMostrar = entregandoMostrar;
	}

	public Integer getEntregando() {
		return entregando;
	}

	public void setEntregando(Integer entregando) {
		this.entregando = entregando;
		if (entregando == 1) {
			setEntregandoMostrar("S\u00ED");
			setEntregandoCheck(true);
		} else {
			setEntregandoMostrar("No");
			setEntregandoCheck(false);
		}
	}

	public String getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(String kilometraje) {
		this.kilometraje = kilometraje;
	}

	/**
	 * @return the idUnidadReparto
	 */
	public Integer getIdUnidadReparto() {
		return idUnidadReparto;
	}

	/**
	 * @param idUnidadReparto the idUnidadReparto to set
	 */
	public void setIdUnidadReparto(Integer idUnidadReparto) {
		this.idUnidadReparto = idUnidadReparto;
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
	 * @return the noSerie
	 */
	public String getNoSerie() {
		return noSerie;
	}

	/**
	 * @param noSerie the noSerie to set
	 */
	public void setNoSerie(String noSerie) {
		this.noSerie = noSerie;
	}

	/**
	 * @return the noEconomico
	 */
	public String getNoEconomico() {
		return noEconomico;
	}

	/**
	 * @param noEconomico the noEconomico to set
	 */
	public void setNoEconomico(String noEconomico) {
		this.noEconomico = noEconomico;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the placas
	 */
	public String getPlacas() {
		return placas;
	}

	/**
	 * @param placas the placas to set
	 */
	public void setPlacas(String placas) {
		this.placas = placas;
	}

	/**
	 * @return the capacidadNoCajas
	 */
	public String getCapacidadNoCajas() {
		return capacidadNoCajas;
	}

	/**
	 * @param capacidadNoCajas the capacidadNoCajas to set
	 */
	public void setCapacidadNoCajas(String capacidadNoCajas) {
		this.capacidadNoCajas = capacidadNoCajas;
	}

	/**
	 * @return the rendimiento
	 */
	public String getRendimiento() {
		return rendimiento;
	}

	/**
	 * @param rendimiento the rendimiento to set
	 */
	public void setRendimiento(String rendimiento) {
		this.rendimiento = rendimiento;
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
	 * @return the idEstatus
	 */
	public Integer getIdEstatus() {
		return idEstatus;
	}

	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	/**
	 * @return the descEstatus
	 */
	public String getDescEstatus() {
		return descEstatus;
	}

	/**
	 * @param descEstatus the descEstatus to set
	 */
	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
	}

	/**
	 * @return the idMarca
	 */
	public Integer getIdMarca() {
		return idMarca;
	}

	/**
	 * @param idMarca the idMarca to set
	 */
	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	/**
	 * @return the descMarca
	 */
	public String getDescMarca() {
		return descMarca;
	}

	/**
	 * @param descMarca the descMarca to set
	 */
	public void setDescMarca(String descMarca) {
		this.descMarca = descMarca;
	}

	/**
	 * @return the idLDC
	 */
	public Integer getIdLDC() {
		return idLDC;
	}

	/**
	 * @param idLDC the idLDC to set
	 */
	public void setIdLDC(Integer idLDC) {
		this.idLDC = idLDC;
	}

	/**
	 * @return the descLDC
	 */
	public String getDescLDC() {
		return descLDC;
	}

	/**
	 * @param descLDC the descLDC to set
	 */
	public void setDescLDC(String descLDC) {
		this.descLDC = descLDC;
	}

	/**
	 * @return the idPais
	 */
	public Integer getIdPais() {
		return idPais;
	}

	/**
	 * @param idPais the idPais to set
	 */
	public void setIdPais(Integer idPais) {
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
	 * @return the descModelo
	 */
	public String getDescModelo() {
		return descModelo;
	}

	/**
	 * @param descModelo the descModelo to set
	 */
	public void setDescModelo(String descModelo) {
		this.descModelo = descModelo;
	}

	public Boolean getImprCodBarras() {
		return imprCodBarras;
	}

	public void setImprCodBarras(Boolean imprCodBarras) {
		this.imprCodBarras = imprCodBarras;
	}

}
