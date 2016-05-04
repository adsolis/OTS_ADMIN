package com.datacode.avon_ots_admin.reports.model;

import java.io.Serializable;

/**
 * 
 * @author ironhide
 *Clase de modelo para el detalle de premios para reporte de ordenes dejadas y recolectadas
 */
public class ModelDetallePremios implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8879101564493596846L;

	private String fsc;
	
	private String ean13;
	
	private int cantidad;
	
	private int dejadoPup;
	
	private int recolectadoPup;

	public String getFsc() {
		return fsc;
	}

	public void setFsc(String fsc) {
		this.fsc = fsc;
	}

	public String getEan13() {
		return ean13;
	}

	public void setEan13(String ean13) {
		this.ean13 = ean13;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
