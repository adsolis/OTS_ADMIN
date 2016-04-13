/**
 * @author jorge.torner
 * @since 27/01/2012
 */
package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.*;

/**
 * @author jorge.torner
 * @since 27/01/2012
 */
public class GenerarReporteManifiestoRutaEnCampaniaTest {
	public static List<ModelManifiestoRutaEnCampania> generarReporteManifiestoRutaEnCampania(){
		List<ModelManifiestoRutaEnCampania> reporte = new ArrayList<ModelManifiestoRutaEnCampania>();
		
		ModelManifiestoRutaEnCampania rep = new ModelManifiestoRutaEnCampania();
		rep.setFecha("27/01/2012");
		rep.setPoblacionPrincipal("Apaseo el Alto");
		rep.setRuta("Ruta 202");
		rep.setZona("Zona 129");
		rep.setCampania_Anio("03-2012");
		rep.setNombreChofer("Alfredo Vazquez");
		rep.setNombreAyudante("JESSICA LIZETH LEON RUIZ");
		rep.setOrdenesTotales("45");
		rep.setCajasTotales("45");
		rep.setUnitariosTotales("15");
		rep.setPremiosTotales("5");
		
		
		List<ModelTablaManifiestoRutaEnCampania> listaReporte = new ArrayList<ModelTablaManifiestoRutaEnCampania>();
		for(int i = 0; i<50; i++){
			ModelTablaManifiestoRutaEnCampania manif = new ModelTablaManifiestoRutaEnCampania();
			manif.setAccount("Cuenta Maestra");
			manif.setAddress1("Yautepec # " + i);
			manif.setAddress2("Alamos # " + i);
			manif.setAddress3("Hierba # " + i);
			manif.setCajas(String.valueOf(i));
			manif.setEntregado("Si");
			manif.setName("JESSICA LIZETH LEON RUIZ LALALA LALALA LALALA LALALA");
			manif.setPremios("No hay");
			manif.setSecuenciaEntrega(String.valueOf(i));
			manif.setToPay(String.valueOf(i*0.32));
			manif.setUnitarios("25");
			manif.setVisitado((i%2==0) ? "Si" : "No");
			
			listaReporte.add(manif);
		}
		

		rep.setListaManifiesto(listaReporte);
		reporte.add(rep);
		
		return reporte;
	}
}
