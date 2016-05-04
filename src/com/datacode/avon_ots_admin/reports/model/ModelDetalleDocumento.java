package com.datacode.avon_ots_admin.reports.model;

import java.io.Serializable;

/**
 * 
 * @author ironhide
 * Clase de modelo para detalle de documentos para reporte de ordenes dejadas y recolectadas
 */
public class ModelDetalleDocumento implements Serializable{
	
	private long regitro;
	
	private int enviadosCods;
	
	private int recibidosCods;
	
	private int recolectadosCods;
	
	private int enviadosRemitos;
	
	private int recibidosRemitos;
	
	private int recolectadosRemitos;

	public long getRegitro() {
		return regitro;
	}

	public void setRegitro(long regitro) {
		this.regitro = regitro;
	}

	public int getEnviadosCods() {
		return enviadosCods;
	}

	public void setEnviadosCods(int enviadosCods) {
		this.enviadosCods = enviadosCods;
	}

	public int getRecibidosCods() {
		return recibidosCods;
	}

	public void setRecibidosCods(int recibidosCods) {
		this.recibidosCods = recibidosCods;
	}

	public int getRecolectadosCods() {
		return recolectadosCods;
	}

	public void setRecolectadosCods(int recolectadosCods) {
		this.recolectadosCods = recolectadosCods;
	}

	public int getEnviadosRemitos() {
		return enviadosRemitos;
	}

	public void setEnviadosRemitos(int enviadosRemitos) {
		this.enviadosRemitos = enviadosRemitos;
	}

	public int getRecibidosRemitos() {
		return recibidosRemitos;
	}

	public void setRecibidosRemitos(int recibidosRemitos) {
		this.recibidosRemitos = recibidosRemitos;
	}

	public int getRecolectadosRemitos() {
		return recolectadosRemitos;
	}

	public void setRecolectadosRemitos(int recolectadosRemitos) {
		this.recolectadosRemitos = recolectadosRemitos;
	}
	
	
}
