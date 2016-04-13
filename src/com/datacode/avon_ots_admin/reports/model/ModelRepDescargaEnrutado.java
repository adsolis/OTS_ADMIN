package com.datacode.avon_ots_admin.reports.model;

import java.util.List;

public class ModelRepDescargaEnrutado {
	
	private String campaniaH;
	private String zonaH;
	private List<ModelTablaDescargaEnrutado> listaDescargaEnrutado;
	private int noEmbarques;
	private String tiempoPromEnCedis;
	private long ordenesCSF;
	private long ordenesREC;
	private long cajasBultosCSF;
	private long cajasBultosREC;
	private String tiempoProDescarga;
	private long productividadDescarga;
	private String tiempoProEnrutado;
	private long productividadEnrutado;
	private long promPersonasDescarga;
	private long promPersonasEnrutado;
	
	
	
	public long getProductividadDescarga() {
		return productividadDescarga;
	}
	public void setProductividadDescarga(long productividadDescarga) {
		this.productividadDescarga = productividadDescarga;
	}
	public long getProductividadEnrutado() {
		return productividadEnrutado;
	}
	public void setProductividadEnrutado(long productividadEnrutado) {
		this.productividadEnrutado = productividadEnrutado;
	}
	public long getPromPersonasDescarga() {
		return promPersonasDescarga;
	}
	public void setPromPersonasDescarga(long promPersonasDescarga) {
		this.promPersonasDescarga = promPersonasDescarga;
	}
	public long getPromPersonasEnrutado() {
		return promPersonasEnrutado;
	}
	public void setPromPersonasEnrutado(long promPersonasEnrutado) {
		this.promPersonasEnrutado = promPersonasEnrutado;
	}
	public String getTiempoPromEnCedis() {
		return tiempoPromEnCedis;
	}
	public void setTiempoPromEnCedis(String tiempoPromEnCedis) {
		this.tiempoPromEnCedis = tiempoPromEnCedis;
	}
	public String getTiempoProDescarga() {
		return tiempoProDescarga;
	}
	public void setTiempoProDescarga(String tiempoProDescarga) {
		this.tiempoProDescarga = tiempoProDescarga;
	}
	public String getTiempoProEnrutado() {
		return tiempoProEnrutado;
	}
	public void setTiempoProEnrutado(String tiempoProEnrutado) {
		this.tiempoProEnrutado = tiempoProEnrutado;
	}
	public String getCampaniaH() {
		return campaniaH;
	}
	public void setCampaniaH(String campaniaH) {
		this.campaniaH = campaniaH;
	}
	public String getZonaH() {
		return zonaH;
	}
	public void setZonaH(String zonaH) {
		this.zonaH = zonaH;
	}
	public List<ModelTablaDescargaEnrutado> getListaDescargaEnrutado() {
		return listaDescargaEnrutado;
	}
	public void setListaDescargaEnrutado(
			List<ModelTablaDescargaEnrutado> listaDescargaEnrutado) {
		this.listaDescargaEnrutado = listaDescargaEnrutado;
	}
	public int getNoEmbarques() {
		return noEmbarques;
	}
	public void setNoEmbarques(int noEmbarques) {
		this.noEmbarques = noEmbarques;
	}
	public long getOrdenesCSF() {
		return ordenesCSF;
	}
	public void setOrdenesCSF(long ordenesCSF) {
		this.ordenesCSF = ordenesCSF;
	}
	public long getOrdenesREC() {
		return ordenesREC;
	}
	public void setOrdenesREC(long ordenesREC) {
		this.ordenesREC = ordenesREC;
	}
	public long getCajasBultosCSF() {
		return cajasBultosCSF;
	}
	public void setCajasBultosCSF(long cajasBultosCSF) {
		this.cajasBultosCSF = cajasBultosCSF;
	}
	public long getCajasBultosREC() {
		return cajasBultosREC;
	}
	public void setCajasBultosREC(long cajasBultosREC) {
		this.cajasBultosREC = cajasBultosREC;
	}
	
}
