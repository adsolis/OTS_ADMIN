package com.datacode.avon_ots_admin.replicacion;

public class ReqReplicacion {
	private String  idLDC;

	public ReqReplicacion(String LDC) {
		super();
		this.idLDC = LDC;
	}

	public ReqReplicacion() {
		super();
		
	}

	public String getIdLDC() {
		return idLDC;
	}

	public void setIdLDC(String idLDC) {
		this.idLDC = idLDC;
	}

	

	
	
	
}
