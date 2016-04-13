package com.datacode.avon_ots_admin.replicacion;

import java.util.List;

public class ReqActualizaEstatusReplicacion extends ReqReplicacion {

	private Replicacion replicacion;
	private List<ReplicacionDetalle> detalle;

	public ReqActualizaEstatusReplicacion(String LDC, Replicacion replicacion,
			List<ReplicacionDetalle> detalle) {
		super(LDC);
		this.replicacion = replicacion;
		this.detalle = detalle;
	}

	public ReqActualizaEstatusReplicacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReqActualizaEstatusReplicacion(String LDC) {
		super(LDC);
		// TODO Auto-generated constructor stub
	}

	public Replicacion getReplicacion() {
		return replicacion;
	}

	public void setReplicacion(Replicacion replicacion) {
		this.replicacion = replicacion;
	}

	public List<ReplicacionDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<ReplicacionDetalle> detalle) {
		this.detalle = detalle;
	}

}
