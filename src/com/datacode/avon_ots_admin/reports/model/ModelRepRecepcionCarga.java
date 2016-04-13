package com.datacode.avon_ots_admin.reports.model;

import java.util.Date;
import java.util.List;

public class ModelRepRecepcionCarga {

	private String porteador;
	private String poblacion;
	private String cTransportista;
	private String operador;
	private String campania;
	private String zonaH;
	private String campaniaH;
	private String fecha;
	private String horaLlegadaReal;
	private String horaLlegadaProg;
	private String fechaLlegadaReal;
	private String fechaLlegadaProg;
	private String observaciones;
	
	private List<TablaCajasMaltratadas> listaCajasMaltratadas;
	private List<TablaCargaRecibida> listaCajaRecibida;
	private List<TablaInfoCodFaltantes> listaCodFaltantes;
	private List<TablaOrdenesXZona> listaOrdenesFaltantes;
	private List<TablaPapeleo> listaPapeleo;
	
	public String getPorteador() {
		return porteador;
	}
	public void setPorteador(String porteador) {
		this.porteador = porteador;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getcTransportista() {
		return cTransportista;
	}
	public void setcTransportista(String cTransportista) {
		this.cTransportista = cTransportista;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public String getCampania() {
		return campania;
	}
	public void setCampania(String campania) {
		this.campania = campania;
	}
	public String getZonaH() {
		return zonaH;
	}
	public void setZonaH(String zonaH) {
		this.zonaH = zonaH;
	}
	public String getCampaniaH() {
		return campaniaH;
	}
	public void setCampaniaH(String campaniaH) {
		this.campaniaH = campaniaH;
	}
	public List<TablaCajasMaltratadas> getListaCajasMaltratadas() {
		return listaCajasMaltratadas;
	}
	public void setListaCajasMaltratadas(
			List<TablaCajasMaltratadas> listaCajasMaltratadas) {
		this.listaCajasMaltratadas = listaCajasMaltratadas;
	}
	public List<TablaCargaRecibida> getListaCajaRecibida() {
		return listaCajaRecibida;
	}
	public void setListaCajaRecibida(List<TablaCargaRecibida> listaCajaRecibida) {
		this.listaCajaRecibida = listaCajaRecibida;
	}
	public List<TablaInfoCodFaltantes> getListaCodFaltantes() {
		return listaCodFaltantes;
	}
	public void setListaCodFaltantes(List<TablaInfoCodFaltantes> listaCodFaltantes) {
		this.listaCodFaltantes = listaCodFaltantes;
	}
	public List<TablaOrdenesXZona> getListaOrdenesFaltantes() {
		return listaOrdenesFaltantes;
	}
	public void setListaOrdenesFaltantes(
			List<TablaOrdenesXZona> listaOrdenesFaltantes) {
		this.listaOrdenesFaltantes = listaOrdenesFaltantes;
	}
	public List<TablaPapeleo> getListaPapeleo() {
		return listaPapeleo;
	}
	public void setListaPapeleo(List<TablaPapeleo> listaPapeleo) {
		this.listaPapeleo = listaPapeleo;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the horaLlegadaReal
	 */
	public String getHoraLlegadaReal() {
		return horaLlegadaReal;
	}
	/**
	 * @param horaLlegadaReal the horaLlegadaReal to set
	 */
	public void setHoraLlegadaReal(String horaLlegadaReal) {
		this.horaLlegadaReal = horaLlegadaReal;
	}
	/**
	 * @return the horaLlegadaProg
	 */
	public String getHoraLlegadaProg() {
		return horaLlegadaProg;
	}
	/**
	 * @param horaLlegadaProg the horaLlegadaProg to set
	 */
	public void setHoraLlegadaProg(String horaLlegadaProg) {
		this.horaLlegadaProg = horaLlegadaProg;
	}
	/**
	 * @return the fechaLlegadaReal
	 */
	public String getFechaLlegadaReal() {
		return fechaLlegadaReal;
	}
	/**
	 * @param fechaLlegadaReal the fechaLlegadaReal to set
	 */
	public void setFechaLlegadaReal(String fechaLlegadaReal) {
		this.fechaLlegadaReal = fechaLlegadaReal;
	}
	/**
	 * @return the fechaLlegadaProg
	 */
	public String getFechaLlegadaProg() {
		return fechaLlegadaProg;
	}
	/**
	 * @param fechaLlegadaProg the fechaLlegadaProg to set
	 */
	public void setFechaLlegadaProg(String fechaLlegadaProg) {
		this.fechaLlegadaProg = fechaLlegadaProg;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
