package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelExpRecepcionAjustesPremios;
import com.datacode.avon_ots_admin.reports.model.ModelExpTablaPremios;

public class GeneraExpRecepcionAjustes {

	public static List<ModelExpRecepcionAjustesPremios> generaExpRecepcionAjustes() {
		List<ModelExpRecepcionAjustesPremios> reportes = new ArrayList<ModelExpRecepcionAjustesPremios>();
		ModelExpRecepcionAjustesPremios reporte = new ModelExpRecepcionAjustesPremios();
		reporte.setBienestarBEBE(15);
		reporte.setBienestarBEME(20);
		reporte.setBienestarCMBE(23);
		reporte.setBienestarCMME(156);
		reporte.setCampania("campania");
		reporte.setCosmeticoBEBE(458);
		reporte.setCosmeticoBEME(156);
		reporte.setHogarCMBE(24);
		reporte.setHogarCMME(45);
		reporte.setJoyeriaCMBE(78);
		reporte.setJoyeriaCMME(89);
		reporte.setLenceriaCMBE(15);
		reporte.setLenceriaCMME(57);
		reporte.setZona("zona");
		reporte.setTotalCajasCM(548);
		reporte.setTotalCajasBe(59);
		reporte.setSubtotalCMME(265);
		reporte.setSubtotalCMBE(589);
		reporte.setSubtotalBEME(87);
		reporte.setSubtotalBEBE(235);
		reporte.setModaCMME(56);
		reporte.setModaCMBE(1548);
		List<ModelExpTablaPremios> premios = new ArrayList<ModelExpTablaPremios>();
		for (int i = 0; i <= 10; i++) {
			ModelExpTablaPremios p = new ModelExpTablaPremios();
			p.setAcciones("acciones");
			p.setCampania("2014-25");
			p.setCantidad(15);
			p.setDescripcion("licuadora volumetrica matadora");
			p.setPrograma("programa d tv");
			premios.add(p);
		}
		reporte.setListaPremios(premios);
		reportes.add(reporte);
		return reportes;
	}
}
