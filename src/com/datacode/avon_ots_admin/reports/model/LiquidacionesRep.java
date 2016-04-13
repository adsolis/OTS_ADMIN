package com.datacode.avon_ots_admin.reports.model;

public class LiquidacionesRep {

	private String concepto;
	private double cods;
	private int cajas;
	private int premios;
	private int unitarios;
	private String recibio;
	public int getUnitarios() {
		return unitarios;
	}
	public void setUnitarios(int unitarios) {
		this.unitarios = unitarios;
	}
	public String getRecibio() {
		return recibio;
	}
	public void setRecibio(String recibio) {
		this.recibio = recibio;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public double getCods() {
		return cods;
	}
	public void setCods(double cods) {
		this.cods = cods;
	}
	public int getCajas() {
		return cajas;
	}
	public void setCajas(int cajas) {
		this.cajas = cajas;
	}
	public int getPremios() {
		return premios;
	}
	public void setPremios(int premios) {
		this.premios = premios;
	}
}
