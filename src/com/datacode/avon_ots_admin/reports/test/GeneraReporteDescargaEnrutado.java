package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelRepDescargaEnrutado;
import com.datacode.avon_ots_admin.reports.model.ModelTablaDescargaEnrutado;

public class GeneraReporteDescargaEnrutado {

	public static List<ModelRepDescargaEnrutado> generaReporteDescargaEnrutado(){
		List<ModelRepDescargaEnrutado>reportes= new ArrayList<ModelRepDescargaEnrutado>();
		ModelRepDescargaEnrutado reporte = new ModelRepDescargaEnrutado();
		reporte.setCajasBultosCSF(500);
		reporte.setCajasBultosREC(800);
		reporte.setCampaniaH("2012 - 24");
		reporte.setNoEmbarques(25);
		reporte.setOrdenesCSF(515);
		reporte.setOrdenesREC(280);
		reporte.setProductividadDescarga(128);
		reporte.setProductividadEnrutado(187);
		reporte.setTiempoProDescarga("12:12");
		reporte.setTiempoProEnrutado("12:12");
		reporte.setTiempoPromEnCedis("12:12");
		reporte.setZonaH("1967");
		List<ModelTablaDescargaEnrutado> tabla= new ArrayList<ModelTablaDescargaEnrutado>();
		for(int i=20;i<=500;i++)
		{
			ModelTablaDescargaEnrutado r= new ModelTablaDescargaEnrutado();
			r.setCajOtrZona1REC("1548");
			r.setCajOtrZonaCSF("1548");
			r.setCajZona1REC("1548");
			r.setCajZona2REC("1548");
			r.setCajZonaCSF("1548");
			r.setCampania("2012 - 24");
			r.setComentarios("jajajajaja");
			r.setFechaLleProgramada("12/08/2012");
			r.setFechaLleReal("12/08/2012");
			r.setFechaSalReal("12/08/2012");
			r.setHrFinDescarga("12/08/2012");
			r.setHrFinEnrutado("12/08/2012");
			r.setHrIniDescarga("12/08/2012");
			r.setHrIniEnrutado("12/08/2012");
			r.setHrLleProgramada("12/08/2012");
			r.setHrLleReal("12/08/2012");
			r.setHrSalReal("12/08/2012");
			r.setNoPersonasDescarga("5");
			r.setNoPersonasEnrutado("25");
			r.setOrdZona1REC("15");
			r.setZona("154");
			r.setUniZonaCSF("15");
			r.setTotZonaREC("526");
			r.setTotZonaCSF("65165");
			r.setTipo("cerrado");
			r.setTiempoTotalEnrutado("12/08/2012");
			r.setTiempoTotalDesacarga("12/08/2012");
			r.setTiempoEnCedis("12/08/2012");
			r.setStatusCarga("http://maps.google.com/?q=DataCode+Corporation@20.60722,-100.3828&z=20");
			r.setProductividadEnrutado("6516");
			r.setProductividadDescarga("9849");
			r.setPreZonaCSF("14984");
			r.setPreZona2REC("914984");
			r.setPreZona1REC("984");
			r.setOtrZonaCSF("989");
			r.setOtrZona1REC("98498");
			tabla.add(r);
		}
		reporte.setListaDescargaEnrutado(tabla);
		reportes.add(reporte);
		return reportes;
	}
}
