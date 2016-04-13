package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelRepReparto;

public class GeneraReporteRepartoTest {

	public static List<ModelRepReparto> generaReporteReparto() {
		List<ModelRepReparto> modelo = new ArrayList<ModelRepReparto>();
		for (int i = 1000; i <= 1020; i++) {
			ModelRepReparto m = new ModelRepReparto();
			m.setAcpPremios(i);
			m.setAcpPrimeraOrden(i);
			m.setAcpTotal(18.8);
			m.setAnio("1847");
			m.setCampania("1847-26");
			m.setCauCambioDom(i);
			m.setCauCerradoTotal(i);
			m.setCauDifEnCobro(i);
			m.setCauDiferencia(i);
			m.setCauDomIncompleto(i);
			m.setCauExtravioFicha(i);
			m.setCauFueraZona(i);
			m.setCauNoDejoFicha(i);
			m.setCauNoEsperaReparto(i);
			m.setCauNoMetioPedido(i);
			m.setCauNoPago(i);
			m.setCauNoViveAhi(i);
			m.setDevCajas(i);
			m.setDevOrdenes(i);
			m.setDevPremios(i);
			m.setDifCajas(i);
			m.setDifOrdenes(i);
			m.setDifPremios(i);
			m.setEntCajasTotales(i);
			m.setEntOrdenesEstablecidas(i);
			m.setEntPremios(i);
			m.setEntPrimeraOrden(i);
			m.setEnvCajasTotales(i);
			m.setEnvCajOrd(i);
			m.setEnvOrdenesCReparto(i);
			m.setEnvOrdenesSReparto(i);
			m.setEnvOrdenesTotales(i);
			m.setEnvPremios(i);
			m.setEnvPrimerasOrdenes(i);
			m.setFechaReparto("15/18/2012");
			m.setHrFinRuta("15:20");
			m.setHrInicioRuta("2012-06-30 07:05:02.600");
			m.setHrPrimeraEntrega("2012-06-30 08:06:02.600");
			m.setHrUltimaEntrega("2012-06-30 09:07:02.600");
			m.setKmArrastre(i);
			m.setKmFinRuta(i);
			m.setKmInicioRuta(i);
			m.setKmLitros(i);
			m.setKmPrimeraEntrega(i);
			m.setKmRepartoEfectivo(i);
			m.setKmRepartoGlobal(i);
			m.setKmUltimaEntrega(i);
			m.setLitros(185.45);
			m.setNombreAyudante("javier gallegos montoya");
			m.setNombreChofer("esteban gonzalez sarate");
			m.setPoblacion("chichimequillas san jose iturbide");
			m.setProduMinutos("11:00");
			m.setProduOrdHr("1548");
			m.setRuta("176");
			m.setTiempoArrastre("2012-06-30 09:08:02.600");
			m.setTiempoRepartoEfectivo("2012-06-30 10:09:02.600");
			m.setTiempoRepartoGlobal("2012-06-30 11:10:02.600");
			m.setTipoRuta("FORANEA");
			m.setZona("154");
			m.setDiaReparto("UNICO DIA");
			modelo.add(m);

		}
		return modelo;
	}
}
