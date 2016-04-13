package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelRepPremios;
import com.datacode.avon_ots_admin.reports.model.ModelRepTablaPremios;

public class GeneraReportePremios {

	public static List<ModelRepPremios> generaReportePremios() {
		List<ModelRepPremios> reportes = new ArrayList<ModelRepPremios>();
		ModelRepPremios reporte = new ModelRepPremios();
		reporte.setCampaniaH("24- 2012");
		reporte.setZonaH("728");
		List<ModelRepTablaPremios> tabla = new ArrayList<ModelRepTablaPremios>();
		for (int i = 20; i <= 1000; i++) {
			ModelRepTablaPremios p = new ModelRepTablaPremios();
			
			p.setCedi("CLY2");
			p.setZona("114");
			p.setPoblacion("SALVATIERRA");
			p.setCampania("12 - 2012");
			p.setAccount(String.valueOf(i*i+1));
			p.setNombre("Francisco Javier Gallegos Montoya");
			p.setRutaSecuenciaEntrega("86-35");
			p.setLastUpdTs("2012-07-11 17:10:57.737");
			p.setCampaniaEnvio("2012 - 24");
			p.setCode("154878546");
			p.setDescripcionPremio("LICUADORA DE 8 VELOCIDADES");
			p.setFechaEntregaDevolucion(new Date().toString());
			p.setEntregado("EN REPARTO");
			p.setObservaciones("aqui van las observaciones del premio");
			p.setGeoreferenciaDomicilio("192.168,-198.5");
			p.setGeoreferenciaEntrega("19.168,-17.5");
			p.setDevueltoA("Devuelto a sub-bodega");
			p.setCausaDevolucion("Cambio de domicilio");
			tabla.add(p);
		}
		reporte.setListaPremios(tabla);
		reportes.add(reporte);
		return reportes;
	}
}
