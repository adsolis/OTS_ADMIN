/**
 *
 *  @since 14/02/2012
 *
 */
package com.datacode.avon_ots_admin.reports.model;

import java.util.List;

/**
 * @author jessica.leon
 *
 */
public class ModelAnalisisEfectivo {

	private String porteador;
	private String plaza;
	private String zona;
	private String campania;
	private List<ModelTablaAnalisisEfectivo> listaAnalisisEfectivo;
	private List<ModelTablaAnalisisEfectivoLiquidacion> listaAnalisisEfectivoLiquidacion;
	/**
	 * @return the porteador
	 */
	public String getPorteador() {
		return porteador;
	}
	/**
	 * @param porteador the porteador to set
	 */
	public void setPorteador(String porteador) {
		this.porteador = porteador;
	}
	/**
	 * @return the plaza
	 */
	public String getPlaza() {
		return plaza;
	}
	/**
	 * @param plaza the plaza to set
	 */
	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}
	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	/**
	 * @return the campania
	 */
	public String getCampania() {
		return campania;
	}
	/**
	 * @param campania the campania to set
	 */
	public void setCampania(String campania) {
		this.campania = campania;
	}
	/**
	 * @return the detalle1
	 */
	/**
	 * @return the listaAnalisisEfectivo
	 */
	public List<ModelTablaAnalisisEfectivo> getListaAnalisisEfectivo() {
		return listaAnalisisEfectivo;
	}
	/**
	 * @param listaAnalisisEfectivo the listaAnalisisEfectivo to set
	 */
	public void setListaAnalisisEfectivo(
			List<ModelTablaAnalisisEfectivo> listaAnalisisEfectivo) {
		this.listaAnalisisEfectivo = listaAnalisisEfectivo;
	}
	/**
	 * @return the listaAnalisisEfectivoLiquidacion
	 */
	public List<ModelTablaAnalisisEfectivoLiquidacion> getListaAnalisisEfectivoLiquidacion() {
		return listaAnalisisEfectivoLiquidacion;
	}
	/**
	 * @param listaAnalisisEfectivoLiquidacion the listaAnalisisEfectivoLiquidacion to set
	 */
	public void setListaAnalisisEfectivoLiquidacion(
			List<ModelTablaAnalisisEfectivoLiquidacion> listaAnalisisEfectivoLiquidacion) {
		this.listaAnalisisEfectivoLiquidacion = listaAnalisisEfectivoLiquidacion;
	}
}
