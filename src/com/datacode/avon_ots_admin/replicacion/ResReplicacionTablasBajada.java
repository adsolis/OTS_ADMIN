package com.datacode.avon_ots_admin.replicacion;

import java.util.List;

public class ResReplicacionTablasBajada extends ResReplicacion {
	private List<ReqReplicacionUploadZip> archivosDescarga;

	public ResReplicacionTablasBajada(String idLDC,
			List<ReqReplicacionUploadZip> archivosDescarga) {
		super(idLDC);
		this.archivosDescarga = archivosDescarga;
	}

	
	public ResReplicacionTablasBajada() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<ReqReplicacionUploadZip> getArchivosDescarga() {
		return archivosDescarga;
	}

	public void setArchivosDescarga(List<ReqReplicacionUploadZip> archivosDescarga) {
		this.archivosDescarga = archivosDescarga;
	}
	
	
}
