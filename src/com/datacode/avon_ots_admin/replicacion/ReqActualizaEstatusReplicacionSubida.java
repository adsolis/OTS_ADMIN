package com.datacode.avon_ots_admin.replicacion;


public class ReqActualizaEstatusReplicacionSubida extends ReqReplicacion {

	private Replicacion replicacion;
	
	public ReqActualizaEstatusReplicacionSubida() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReqActualizaEstatusReplicacionSubida(String LDC) {
		super(LDC);
		// TODO Auto-generated constructor stub
	}

	public ReqActualizaEstatusReplicacionSubida(String LDC,
			Replicacion replicacion) {
		super(LDC);
		this.replicacion = replicacion;
	}


	public Replicacion getReplicacion() {
		return replicacion;
	}

	public void setReplicacion(Replicacion replicacion) {
		this.replicacion = replicacion;
	}

	
	
}
