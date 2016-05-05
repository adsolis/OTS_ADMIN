package com.datacode.avon_ots_admin.reports.model;

import java.util.List;

public class ModelOrdenesDejadasRecolectadas {
	
	private long idPup;
	
	private long idSalidaReparto;
	
	private String correo;
	
	private List<ModelDetalleCajas> detalleCajas;
	
	private List<ModelDetallePremios> detallePremios;
	
	private List<ModelDetalleDocumento> detalleDocumentos;
	
	public long getIdPup() {
		return idPup;
	}

	public void setIdPup(long idPup) {
		this.idPup = idPup;
	}

	public long getIdSalidaReparto() {
		return idSalidaReparto;
	}

	public void setIdSalidaReparto(long idSalidaReparto) {
		this.idSalidaReparto = idSalidaReparto;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public List<ModelDetalleCajas> getDetalleCajas() {
		return detalleCajas;
	}

	public void setDetalleCajas(List<ModelDetalleCajas> detalleCajas) {
		this.detalleCajas = detalleCajas;
	}

	public List<ModelDetallePremios> getDetallePremios() {
		return detallePremios;
	}

	public void setDetallePremios(List<ModelDetallePremios> detallePremios) {
		this.detallePremios = detallePremios;
	}

	public List<ModelDetalleDocumento> getDetalleDocumentos() {
		return detalleDocumentos;
	}

	public void setDetalleDocumentos(List<ModelDetalleDocumento> detalleDocumentos) {
		this.detalleDocumentos = detalleDocumentos;
	}
	
	

}
