/**
 *
 *  @since 16/08/2012
 *
 */
package com.datacode.avon_ots_admin.reports.model;

import java.util.List;

/**
 * @author jessica.leon
 *
 */
public class ModelHeaderResumen {

	private String anioCampania;
	private String totalInicioRutaPromedio;
	private String totalPrimerVisitaPromedio;
	private String totalUltimaVisitaPromedio;
	private String totalFinRutaPromedio;
	
	private String totalTiempoPromedioVisita;
	private double totalProductividadOrdenHora;
	
	private String totalCauNoViveAhi;
	private String totalCauNoPago;
	private String totalCauNoDejoFicha;
	private String totalCauCambioDom;
	private String totalCauCerradoTotal;
	private String totalCauDifEnCobro;
	private String totalCauFueraZona;
	private String totalCauNoMetioPedido;
	private String totalCauDomIncompleto;
	private String totalCauNoEsperaReparto;
	private String totalCauExtravioFicha;
	private String totalCauOtro;
	private String totalCauTotal;
	
	private List<ModelReporteResumen> detalles;

	public String getAnioCampania() {
		return anioCampania;
	}

	public void setAnioCampania(String anioCampania) {
		this.anioCampania = anioCampania;
	}

	public String getTotalInicioRutaPromedio() {
		return totalInicioRutaPromedio;
	}

	public void setTotalInicioRutaPromedio(String totalInicioRutaPromedio) {
		this.totalInicioRutaPromedio = totalInicioRutaPromedio;
	}

	public String getTotalPrimerVisitaPromedio() {
		return totalPrimerVisitaPromedio;
	}

	public void setTotalPrimerVisitaPromedio(String totalPrimerVisitaPromedio) {
		this.totalPrimerVisitaPromedio = totalPrimerVisitaPromedio;
	}

	public String getTotalUltimaVisitaPromedio() {
		return totalUltimaVisitaPromedio;
	}

	public void setTotalUltimaVisitaPromedio(String totalUltimaVisitaPromedio) {
		this.totalUltimaVisitaPromedio = totalUltimaVisitaPromedio;
	}

	public String getTotalFinRutaPromedio() {
		return totalFinRutaPromedio;
	}

	public void setTotalFinRutaPromedio(String totalFinRutaPromedio) {
		this.totalFinRutaPromedio = totalFinRutaPromedio;
	}

	public String getTotalTiempoPromedioVisita() {
		return totalTiempoPromedioVisita;
	}

	public void setTotalTiempoPromedioVisita(String totalTiempoPromedioVisita) {
		this.totalTiempoPromedioVisita = totalTiempoPromedioVisita;
	}

	public double getTotalProductividadOrdenHora() {
		return totalProductividadOrdenHora;
	}

	public void setTotalProductividadOrdenHora(double totalProductividadOrdenHora) {
		this.totalProductividadOrdenHora = totalProductividadOrdenHora;
	}

	public String getTotalCauNoViveAhi() {
		return totalCauNoViveAhi;
	}

	public void setTotalCauNoViveAhi(String totalCauNoViveAhi) {
		this.totalCauNoViveAhi = totalCauNoViveAhi;
	}

	public String getTotalCauNoPago() {
		return totalCauNoPago;
	}

	public void setTotalCauNoPago(String totalCauNoPago) {
		this.totalCauNoPago = totalCauNoPago;
	}

	public String getTotalCauNoDejoFicha() {
		return totalCauNoDejoFicha;
	}

	public void setTotalCauNoDejoFicha(String totalCauNoDejoFicha) {
		this.totalCauNoDejoFicha = totalCauNoDejoFicha;
	}

	public String getTotalCauCambioDom() {
		return totalCauCambioDom;
	}

	public void setTotalCauCambioDom(String totalCauCambioDom) {
		this.totalCauCambioDom = totalCauCambioDom;
	}

	public String getTotalCauCerradoTotal() {
		return totalCauCerradoTotal;
	}

	public void setTotalCauCerradoTotal(String totalCauCerradoTotal) {
		this.totalCauCerradoTotal = totalCauCerradoTotal;
	}

	public String getTotalCauDifEnCobro() {
		return totalCauDifEnCobro;
	}

	public void setTotalCauDifEnCobro(String totalCauDifEnCobro) {
		this.totalCauDifEnCobro = totalCauDifEnCobro;
	}

	public String getTotalCauFueraZona() {
		return totalCauFueraZona;
	}

	public void setTotalCauFueraZona(String totalCauFueraZona) {
		this.totalCauFueraZona = totalCauFueraZona;
	}

	public String getTotalCauNoMetioPedido() {
		return totalCauNoMetioPedido;
	}

	public void setTotalCauNoMetioPedido(String totalCauNoMetioPedido) {
		this.totalCauNoMetioPedido = totalCauNoMetioPedido;
	}

	public String getTotalCauDomIncompleto() {
		return totalCauDomIncompleto;
	}

	public void setTotalCauDomIncompleto(String totalCauDomIncompleto) {
		this.totalCauDomIncompleto = totalCauDomIncompleto;
	}

	public String getTotalCauNoEsperaReparto() {
		return totalCauNoEsperaReparto;
	}

	public void setTotalCauNoEsperaReparto(String totalCauNoEsperaReparto) {
		this.totalCauNoEsperaReparto = totalCauNoEsperaReparto;
	}

	public String getTotalCauExtravioFicha() {
		return totalCauExtravioFicha;
	}

	public void setTotalCauExtravioFicha(String totalCauExtravióFicha) {
		this.totalCauExtravioFicha = totalCauExtravióFicha;
	}

	public String getTotalCauOtro() {
		return totalCauOtro;
	}

	public void setTotalCauOtro(String totalCauOtro) {
		this.totalCauOtro = totalCauOtro;
	}

	public List<ModelReporteResumen> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<ModelReporteResumen> detalles) {
		this.detalles = detalles;
	}

	public String getTotalCauTotal() {
		return totalCauTotal;
	}

	public void setTotalCauTotal(String totalCauTotal) {
		this.totalCauTotal = totalCauTotal;
	}
}
