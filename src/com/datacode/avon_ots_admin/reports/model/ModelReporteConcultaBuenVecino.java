package com.datacode.avon_ots_admin.reports.model;

import java.util.Date;

public class ModelReporteConcultaBuenVecino {

	private int idRuta;
	private int idZona;
	private String registro;
	private String nombre;
	private String domicilio;
	private String domicilioAlterno;
	private Date fechaModificacionDomAlterno;
	private String fechaModificacionDomAlternoS;
	private String claveRuta;
	private String campaniaCambio;
	private String entregarEnDomicilioAlterno;

	public String getCampaniaCambio() {
		return campaniaCambio;
	}

	public void setCampaniaCambio(String campaniaCambio) {
		this.campaniaCambio = campaniaCambio;
	}

	public String getEntregarEnDomicilioAlterno() {
		return entregarEnDomicilioAlterno;
	}

	public void setEntregarEnDomicilioAlterno(String entregarEnDomicilioAlterno) {
		this.entregarEnDomicilioAlterno = entregarEnDomicilioAlterno;
	}

	public String getFechaModificacionDomAlternoS() {
		return fechaModificacionDomAlternoS;
	}

	public void setFechaModificacionDomAlternoS(
			String fechaModificacionDomAlternoS) {
		this.fechaModificacionDomAlternoS = fechaModificacionDomAlternoS;
	}

	public int getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public int getIdZona() {
		return idZona;
	}

	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getDomicilioAlterno() {
		return domicilioAlterno;
	}

	public void setDomicilioAlterno(String domicilioAlterno) {
		this.domicilioAlterno = domicilioAlterno;
	}

	public Date getFechaModificacionDomAlterno() {
		return fechaModificacionDomAlterno;
	}

	public void setFechaModificacionDomAlterno(Date fechaModificacionDomAlterno) {
		this.fechaModificacionDomAlterno = fechaModificacionDomAlterno;
	}

	public String getClaveRuta() {
		return claveRuta;
	}

	public void setClaveRuta(String claveRuta) {
		this.claveRuta = claveRuta;
	}
}
