package com.datacode.avon_ots_admin.replicacion;

import java.util.List;

public class ReqReplicacionBajada extends ReqReplicacion {
	private Replicacion replicacion;
	private List<ReplicacionTabla> replicacionTablas;
	
	public ReqReplicacionBajada(String LDC, Replicacion replicacion,
			List<ReplicacionTabla> replicacionTablas) {
		super(LDC);
		this.replicacion = replicacion;
		this.replicacionTablas = replicacionTablas;
	}

	
	
	public ReqReplicacionBajada() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ReqReplicacionBajada(String LDC) {
		super(LDC);
		// TODO Auto-generated constructor stub
	}



	public Replicacion getReplicacion() {
		return replicacion;
	}

	public void setReplicacion(Replicacion replicacion) {
		this.replicacion = replicacion;
	}

	public List<ReplicacionTabla> getReplicacionTablas() {
		return replicacionTablas;
	}

	public void setReplicacionTablas(List<ReplicacionTabla> replicacionTablas) {
		this.replicacionTablas = replicacionTablas;
	}
	
	
	
}
