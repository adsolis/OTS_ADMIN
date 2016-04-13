package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.datacode.avon_ots_ws.ReportesControllerStub.ModelReparto;

public class GeneraReporteReparto {

	public List<ModelReparto> obtenerDatosTest() {

		List<ModelReparto> modelo = new ArrayList<ModelReparto>();
		Random r = new Random();

		for (int i = 0; i <= 5; i++) {
			ModelReparto mo = new ModelReparto();
			mo.setAnio("2012");
			mo.setCampania("asdf");
			mo.setEnvOrdenesCReparto(r.nextInt(100));
			mo.setEnvOrdenesSReparto(r.nextInt(100));
			mo.setEnvPrimerasOrdenes(r.nextInt(100));
			mo.setEnvCajasTotales(r.nextInt(100) + 300);
			mo.setEnvPremios(r.nextInt(100));
			mo.setKmInicioRuta(r.nextInt(100) + 32000);
			mo.setKmPrimeraEntrega(r.nextInt(50) + 32100);
			mo.setKmUltimaEntrega(r.nextInt(50) + 32150);
			mo.setKmFinRuta(r.nextInt(100) + 32200);
			mo.setHrInicioRuta("0" + r.nextInt(4) + ":" + (r.nextInt(45) + 10));
			mo.setHrPrimeraEntrega("0" + (r.nextInt(4) + 5) + ":"
					+ (r.nextInt(45) + 10));
			mo.setHrUltimaEntrega("1" + (r.nextInt(4)) + ":"
					+ (r.nextInt(45) + 10));
			mo.setHrFinRuta("1" + (r.nextInt(4) + 5) + ":"
					+ (r.nextInt(45) + 10));
			mo.setEnvPremios(r.nextInt(50)+100);
			mo.setEntPrimeraOrden(r.nextInt(100));
			mo.setEntOrdenesEstablecidas(r.nextInt(100));
			mo.setEntCajasTotales(r.nextInt(200));
			mo.setEntPremios(r.nextInt(20)+50);
			mo.setCauCambioDom(r.nextInt(15));
			mo.setCauCerradoTotal(r.nextInt(5));
			mo.setCauDifEnCobro(r.nextInt(5));
			mo.setCauDiferencia(r.nextInt(5));
			mo.setCauDomIncompleto(r.nextInt(5));
			mo.setCauExtravioFicha(r.nextInt(15));
			mo.setCauFueraZona(r.nextInt(5));
			mo.setCauNoEsperaReparto(r.nextInt(5));
			mo.setCauNoDejoFicha(r.nextInt(5));
			mo.setCauNoMetioPedido(r.nextInt(5));
			mo.setCauNoPago(r.nextInt(5));
			mo.setCauNoViveAhi(r.nextInt(5));
			mo.setCauOtros(r.nextInt(5));
			modelo.add(mo);
		}

		return modelo;
	}

}
