/**
 *
 *  @since 14/02/2012
 *
 */
package com.datacode.avon_ots_admin.reports.model;

/**
 * @author jessica.leon
 *
 */
public class ModelTablaAnalisisEfectivo {

	private String	ruta;
	private double  efectivoRecolectado;
	private String	fechaRecepcionEfectivo;
	private String	bancoDeposito;
	private double depositoGlobal;
	private double totalEfectivo;
	
	/**
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}
	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	/**
	 * @return the fechaRecepcionEfectivo
	 */
	public String getFechaRecepcionEfectivo() {
		return fechaRecepcionEfectivo;
	}
	/**
	 * @param fechaRecepcionEfectivo the fechaRecepcionEfectivo to set
	 */
	public void setFechaRecepcionEfectivo(String fechaRecepcionEfectivo) {
		this.fechaRecepcionEfectivo = fechaRecepcionEfectivo;
	}
	/**
	 * @return the bancoDeposito
	 */
	public String getBancoDeposito() {
		return bancoDeposito;
	}
	/**
	 * @param bancoDeposito the bancoDeposito to set
	 */
	public void setBancoDeposito(String bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}
	/**
	 * @return the depositoGlobal
	 */
	public double getDepositoGlobal() {
		return depositoGlobal;
	}
	/**
	 * @param depositoGlobal the depositoGlobal to set
	 */
	public void setDepositoGlobal(double depositoGlobal) {
		this.depositoGlobal = depositoGlobal;
	}
	/**
	 * @return the totalEfectivo
	 */
	public double getTotalEfectivo() {
		return totalEfectivo;
	}
	/**
	 * @param totalEfectivo the totalEfectivo to set
	 */
	public void setTotalEfectivo(double totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}
	/**
	 * @return the efectivoRecolectado
	 */
	public double getEfectivoRecolectado() {
		return efectivoRecolectado;
	}
	/**
	 * @param efectivoRecolectado the efectivoRecolectado to set
	 */
	public void setEfectivoRecolectado(double efectivoRecolectado) {
		this.efectivoRecolectado = efectivoRecolectado;
	}
	
}
