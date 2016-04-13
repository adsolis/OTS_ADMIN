/**
 *  Mapeo de las propiedades del Objeto de la BD - PW_REPRESENTANTE
 *  @since 04/01/2012
 *
 */
package com.datacode.avon_ots_admin.model;

/**
 * 
 * @author jessica.leon
 * @category Map
 */
public class Representante {

	private int idRepresentante = 0;
	private int idZona;
	private String descZona = "";
	private String registro;
	private String nombre;
	private String domicilio;
	private String estado;
	private String estatus;
	private Integer idEstatus = 0;
	private double montoActual;
	private double montoPrevio;
	private String telefono;
	private String coPostal = "";
	private byte pagoEfectivo;
	private byte trabaja;
	private String lastUpdTs;
	private String fechaActualizado;
	private String usuarioActualiza;
	private String poblacion;
	private String siPagoEfectivo = "", siTrabaja = "", cveRuta = "";
	private RepresentantesPorRuta representantePorRuta;
	private String domicilioAlterno;
	private Boolean domIncorrecto;

	public String getDomicilioAlterno() {
		return domicilioAlterno;
	}

	public void setDomicilioAlterno(String domicilioAlterno) {
		this.domicilioAlterno = domicilioAlterno;
	}

	/**
	 * Constructor
	 * 
	 * @author brenda.estrada
	 * @since 18/01/2012
	 */
	public Representante() {
		representantePorRuta = new RepresentantesPorRuta();
	}

	/**
	 * Constructor Parametros
	 * 
	 * @author brenda.estrada
	 * @since 18/01/2012
	 * @param idRepresentante
	 * @param idZona
	 * @param descZona
	 * @param registro
	 * @param nombre
	 * @param domicilio
	 * @param estado
	 * @param estatus
	 * @param idEstatus
	 * @param montoActual
	 * @param montoPrevio
	 * @param telefono
	 * @param coPostal
	 * @param pagoEfectivo
	 * @param trabaja
	 * @param lastUpdTs
	 * @param poblacion
	 * @param fechaActualizado
	 * @param usuarioActualiza
	 * @param siPagoEfectivo
	 * @param siTrabaja
	 * @param cveRuta
	 */
	public Representante(int idRepresentante, int idZona, String descZona,
			String registro, String nombre, String domicilio, String estado,
			String estatus, Integer idEstatus, double montoActual,
			double montoPrevio, String telefono, String coPostal,
			byte pagoEfectivo, byte trabaja, String lastUpdTs,
			String poblacion, String fechaActualizado, String usuarioActualiza,
			String siPagoEfectivo, String siTrabaja, String cveRuta, Boolean domIncorrecto) {
		this.idRepresentante = idRepresentante;
		this.idZona = idZona;
		this.descZona = descZona;
		this.registro = registro;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.estado = estado;
		this.estatus = estatus;
		this.idEstatus = idEstatus;
		this.montoActual = montoActual;
		this.montoPrevio = montoPrevio;
		this.telefono = telefono;
		this.coPostal = coPostal;
		this.pagoEfectivo = pagoEfectivo;
		this.trabaja = trabaja;
		this.lastUpdTs = lastUpdTs;
		this.poblacion = poblacion;
		this.fechaActualizado = fechaActualizado;
		this.usuarioActualiza = usuarioActualiza;
		this.siPagoEfectivo = siPagoEfectivo;
		this.siTrabaja = siTrabaja;
		this.cveRuta = cveRuta;
		this.domIncorrecto = domIncorrecto;
	}

	/**
	 * @return the idRepresentante
	 */
	public int getIdRepresentante() {
		return idRepresentante;
	}

	/**
	 * @param idRepresentante
	 *            the idRepresentante to set
	 */
	public void setIdRepresentante(int idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	/**
	 * @return the idZona
	 */
	public int getIdZona() {
		return idZona;
	}

	/**
	 * @param idZona
	 *            the idZona to set
	 */
	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}

	/**
	 * @return the registro
	 */
	public String getRegistro() {
		return registro;
	}

	/**
	 * @param registro
	 *            the registro to set
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * @param domicilio
	 *            the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus
	 *            the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the montoActual
	 */
	public double getMontoActual() {
		return montoActual;
	}

	/**
	 * @param montoActual
	 *            the montoActual to set
	 */
	public void setMontoActual(double montoActual) {
		this.montoActual = montoActual;
	}

	/**
	 * @return the montoPrevio
	 */
	public double getMontoPrevio() {
		return montoPrevio;
	}

	/**
	 * @param montoPrevio
	 *            the montoPrevio to set
	 */
	public void setMontoPrevio(double montoPrevio) {
		this.montoPrevio = montoPrevio;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the pagoEfectivo
	 */
	public byte getPagoEfectivo() {
		return pagoEfectivo;
	}

	/**
	 * @param pagoEfectivo
	 *            the pagoEfectivo to set
	 */
	public void setPagoEfectivo(byte pagoEfectivo) {
		this.pagoEfectivo = pagoEfectivo;
	}

	/**
	 * @return the trabaja
	 */
	public byte getTrabaja() {
		return trabaja;
	}

	/**
	 * @param trabaja
	 *            the trabaja to set
	 */
	public void setTrabaja(byte trabaja) {
		this.trabaja = trabaja;
	}

	/**
	 * @return the lastUpdTs
	 */
	public String getLastUpdTs() {
		return lastUpdTs;
	}

	/**
	 * @param lastUpdTs
	 *            the lastUpdTs to set
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
	 * @param fechaActualizado
	 *            the fechaActualizado to set
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
	 * @param usuarioActualiza
	 *            the usuarioActualiza to set
	 */
	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}

	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * @param poblacion
	 *            the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	/**
	 * @return the descZona
	 */
	public String getDescZona() {
		return descZona;
	}

	/**
	 * @param descZona
	 *            the descZona to set
	 */
	public void setDescZona(String descZona) {
		this.descZona = descZona;
	}

	/**
	 * @return the idEstatus
	 */
	public Integer getIdEstatus() {
		return idEstatus;
	}

	/**
	 * @param idEstatus
	 *            the idEstatus to set
	 */
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	/**
	 * @return the coPostal
	 */
	public String getCoPostal() {
		return coPostal;
	}

	/**
	 * @param coPostal
	 *            the coPostal to set
	 */
	public void setCoPostal(String coPostal) {
		this.coPostal = coPostal;
	}

	/**
	 * @return the siPagoEfectivo
	 */
	public String getSiPagoEfectivo() {
		return siPagoEfectivo;
	}

	/**
	 * @param siPagoEfectivo
	 *            the siPagoEfectivo to set
	 */
	public void setSiPagoEfectivo(String siPagoEfectivo) {
		this.siPagoEfectivo = siPagoEfectivo;
	}

	/**
	 * @return the siTrabaja
	 */
	public String getSiTrabaja() {
		return siTrabaja;
	}

	/**
	 * @param siTrabaja
	 *            the siTrabaja to set
	 */
	public void setSiTrabaja(String siTrabaja) {
		this.siTrabaja = siTrabaja;
	}

	/**
	 * @return the cveRuta
	 */
	public String getCveRuta() {
		return cveRuta;
	}

	/**
	 * @param cveRuta
	 *            the cveRuta to set
	 */
	public void setCveRuta(String cveRuta) {
		this.cveRuta = cveRuta;
	}

	/**
	 * @return the representantePorRuta
	 */
	public RepresentantesPorRuta getRepresentantePorRuta() {
		return representantePorRuta;
	}

	/**
	 * @param representantePorRuta
	 *            the representantePorRuta to set
	 */
	public void setRepresentantePorRuta(
			RepresentantesPorRuta representantePorRuta) {
		this.representantePorRuta = representantePorRuta;
	}

	public Boolean getDomIncorrecto() {
		return domIncorrecto;
	}

	public void setDomIncorrecto(Boolean domIncorrecto) {
		this.domIncorrecto = domIncorrecto;
	}
	
}
