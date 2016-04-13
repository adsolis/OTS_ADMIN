package com.datacode.avon_ots_admin.replicacion;

import java.util.ArrayList;
import java.util.List;

public class ResReplicacionEstatusDTO extends ResReplicacion {
	private Replicacion replicacionDTO;
	private List<ReplicacionDetalle> detalleDTOs;
	
	public ResReplicacionEstatusDTO(String idLDC,
			Replicacion replicacionDTO,
			List<ReplicacionDetalle> detalleDTOs) {
		super(idLDC);
		this.replicacionDTO = replicacionDTO;
		this.detalleDTOs = detalleDTOs;
	}

	public ResReplicacionEstatusDTO() {
		super();
		detalleDTOs = new ArrayList<ReplicacionDetalle>();
		replicacionDTO = new Replicacion();
		
	}

	public ResReplicacionEstatusDTO(String idLDC) {
		super(idLDC);
		
	}

	public Replicacion getReplicacionDTO() {
		return replicacionDTO;
	}

	public void setReplicacionDTO(Replicacion replicacionDTO) {
		this.replicacionDTO = replicacionDTO;
	}

	public List<ReplicacionDetalle> getDetalleDTOs() {
		return detalleDTOs;
	}

	public void setDetalleDTOs(List<ReplicacionDetalle> detalleDTOs) {
		this.detalleDTOs = detalleDTOs;
	}
	
	public ResReplicacionEstatusDTO copy(ResReplicacionEstatusDTO obj) {
		ResReplicacionEstatusDTO res = new ResReplicacionEstatusDTO();
		res.setReplicacionDTO(obj.getReplicacionDTO());
		res.setDetalleDTOs(obj.getDetalleDTOs());
		return res;
	}
}
