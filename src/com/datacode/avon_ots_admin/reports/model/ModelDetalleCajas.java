package com.datacode.avon_ots_admin.reports.model;

import java.io.Serializable;

/**
 *
 * @author ironhide
 * Clase de modelo para el detalle de las cajas para los reportes 
 * de Ordenes Recolectadas y Dejadas
 */
public class ModelDetalleCajas implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 983326888106953418L;

	private String zona;
	
	private String campana;
	
	private String registro;
	
	private String nombre;
	
	private long orden;
	
	private int item;
	
	private String codigoBarras;
	
	private int dejadoPup;
	
	private int recolectadoPup;

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getCampana() {
		return campana;
	}

	public void setCampana(String campana) {
		this.campana = campana;
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

	public long getOrden() {
		return orden;
	}

	public void setOrden(long orden) {
		this.orden = orden;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public int getDejadoPup() {
		return dejadoPup;
	}

	public void setDejadoPup(int dejadoPup) {
		this.dejadoPup = dejadoPup;
	}

	public int getRecolectadoPup() {
		return recolectadoPup;
	}

	public void setRecolectadoPup(int recolectadoPup) {
		this.recolectadoPup = recolectadoPup;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
