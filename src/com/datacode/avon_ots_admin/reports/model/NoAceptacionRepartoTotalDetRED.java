/**
 * 
 */
package com.datacode.avon_ots_admin.reports.model;

/**
 * @author brenda.estrada
 *
 */
public class NoAceptacionRepartoTotalDetRED {
	//Atributos de Detalle de Reporte - NAR
	private String noRed = "",				porcentajeAceptacionRed  = "",
				   ordenDevolucion = "",	sumValorPedidoRed = "",
				   totalOrdenes = "";
	
	/**
	 * 
	 * @param noRed
	 * @param porcentajeAceptacionRed
	 * @param ordenDevolucion
	 * @param sumValorPedidoRed
	 * @param totalOrdenes
	 * @author brenda.estrada
	 * @since 20/02/2012
	 */
	public NoAceptacionRepartoTotalDetRED(String noRed, String porcentajeAceptacionRed, String ordenDevolucion, String sumValorPedidoRed, String totalOrdenes){
		this.noRed = noRed;							this.porcentajeAceptacionRed = porcentajeAceptacionRed;
		this.ordenDevolucion = ordenDevolucion;		this.sumValorPedidoRed = sumValorPedidoRed;		
		this.totalOrdenes = totalOrdenes;	
	}


	public String getNoRed() {
		return noRed;
	}


	public void setNoRed(String noRed) {
		this.noRed = noRed;
	}


	public String getPorcentajeAceptacionRed() {
		return porcentajeAceptacionRed;
	}


	public void setPorcentajeAceptacionRed(String porcentajeAceptacionRed) {
		this.porcentajeAceptacionRed = porcentajeAceptacionRed;
	}


	public String getOrdenDevolucion() {
		return ordenDevolucion;
	}


	public void setOrdenDevolucion(String ordenDevolucion) {
		this.ordenDevolucion = ordenDevolucion;
	}


	public String getSumValorPedidoRed() {
		return sumValorPedidoRed;
	}


	public void setSumValorPedidoRed(String sumValorPedidoRed) {
		this.sumValorPedidoRed = sumValorPedidoRed;
	}


	public String getTotalOrdenes() {
		return totalOrdenes;
	}


	public void setTotalOrdenes(String totalOrdenes) {
		this.totalOrdenes = totalOrdenes;
	}
	
}
