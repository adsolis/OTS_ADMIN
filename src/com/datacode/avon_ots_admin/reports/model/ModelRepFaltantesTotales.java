package com.datacode.avon_ots_admin.reports.model;

import java.util.List;

public class ModelRepFaltantesTotales {

	private int cajasFacturadas;
	private int cajasRecibidas;
	private int cajasFaltantes;
	private int unitariosFacturadas;
	private int unitariosRecibidas;
	private int unitariosFaltantes;
	private int premiosFacturadas;
	private int premiosRecibidas;
	private int premiosFaltantes;
	private List<ModelRepFaltantesCajas> detalleCajas;
	private List<ModelRepFaltantesUnitarios> detalleUnitarios;

	public List<ModelRepFaltantesCajas> getDetalleCajas() {
		return detalleCajas;
	}

	public void setDetalleCajas(List<ModelRepFaltantesCajas> detalleCajas) {
		this.detalleCajas = detalleCajas;
	}

	public List<ModelRepFaltantesUnitarios> getDetalleUnitarios() {
		return detalleUnitarios;
	}

	public void setDetalleUnitarios(
			List<ModelRepFaltantesUnitarios> detalleUnitarios) {
		this.detalleUnitarios = detalleUnitarios;
	}

	public int getCajasFacturadas() {
		return cajasFacturadas;
	}

	public void setCajasFacturadas(int cajasFacturadas) {
		this.cajasFacturadas = cajasFacturadas;
	}

	public int getCajasRecibidas() {
		return cajasRecibidas;
	}

	public void setCajasRecibidas(int cajasRecibidas) {
		this.cajasRecibidas = cajasRecibidas;
	}

	public int getCajasFaltantes() {
		return cajasFaltantes;
	}

	public void setCajasFaltantes(int cajasFaltantes) {
		this.cajasFaltantes = cajasFaltantes;
	}

	public int getUnitariosFacturadas() {
		return unitariosFacturadas;
	}

	public void setUnitariosFacturadas(int unitariosFacturadas) {
		this.unitariosFacturadas = unitariosFacturadas;
	}

	public int getUnitariosRecibidas() {
		return unitariosRecibidas;
	}

	public void setUnitariosRecibidas(int unitariosRecibidas) {
		this.unitariosRecibidas = unitariosRecibidas;
	}

	public int getUnitariosFaltantes() {
		return unitariosFaltantes;
	}

	public void setUnitariosFaltantes(int unitariosFaltantes) {
		this.unitariosFaltantes = unitariosFaltantes;
	}

	public int getPremiosFacturadas() {
		return premiosFacturadas;
	}

	public void setPremiosFacturadas(int premiosFacturadas) {
		this.premiosFacturadas = premiosFacturadas;
	}

	public int getPremiosRecibidas() {
		return premiosRecibidas;
	}

	public void setPremiosRecibidas(int premiosRecibidas) {
		this.premiosRecibidas = premiosRecibidas;
	}

	public int getPremiosFaltantes() {
		return premiosFaltantes;
	}

	public void setPremiosFaltantes(int premiosFaltantes) {
		this.premiosFaltantes = premiosFaltantes;
	}
}
