/**
 *
 *  @since 14/08/2012
 *
 */
package com.datacode.avon_ots_admin.reports.test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelHeaderResumen;
import com.datacode.avon_ots_admin.reports.model.ModelReporteResumen;

/**
 * @author jessica.leon
 *
 */
public class GeneraReporteResumen {

	public static ModelHeaderResumen generarReporte(){
		
		List<ModelReporteResumen> listaDetalles = new ArrayList<ModelReporteResumen>();
	    ModelReporteResumen  detalle = null;
	    ModelHeaderResumen headerResumen = new ModelHeaderResumen();
	    int j = 0;
	    GeneraReporteResumen resumen = new GeneraReporteResumen();
	    
	    
		for(int i=0;i<20;i++){
			detalle = new ModelReporteResumen();
			detalle.setCampania(String.valueOf(i));
			detalle.setProductividadDescarga(i);
			detalle.setProductividadEnrutado(i);
			detalle.setRutasNormales(196);
			detalle.setRutasEspeciales(i*3);
			detalle.setOrdenConRepartoDir(14445);
			detalle.setOrdenSinRepartoDir(0);
			detalle.setPrimerasOrdenes(683);
			detalle.setCajas(i*10);
			detalle.setPremios(i*2);
			detalle.setBultosMatPromocional(i*6);
 			detalle.setRepInicioRutaPromedio("2012-08-15 06:50:47.653");
			detalle.setRepPrimeraVisitaProm("2012-08-15 07:41:47.653");
			detalle.setRepUltimaVisitaPromedio("2012-08-15 13:17:47.653");
			detalle.setRepFinRutaPromedio("2012-08-15 07:50:47.653");
			detalle.setRepKmGlobal(39324*i);
			detalle.setRepKmEfectivo(1678*1);
			detalle.setRepTiempoPromVisita("00:00");
			long tiempoPromedioVisita = (detalle.getOrdenConRepartoDir()+detalle.getOrdenSinRepartoDir()+detalle.getPrimerasOrdenes())/detalle.getRutasNormales();
			
			if(tiempoPromedioVisita > 0){
				long tiempoPromedio = (resumen.obtenerSegundos(detalle.getRepUltimaVisitaPromedio()) - 
											resumen.obtenerSegundos(detalle.getRepPrimeraVisitaProm())) /
												tiempoPromedioVisita;
				detalle.setRepTiempoPromVisita(resumen.formatIntoHHMMSS(tiempoPromedio));
			}
			
			detalle.setRepProductividadOrHr(0);
			
			long productividadOrdenHoraDivisor = (resumen.obtenerSegundosHora(detalle.getRepUltimaVisitaPromedio()) - 
													resumen.obtenerSegundosHora(detalle.getRepPrimeraVisitaProm())) * 24;
			if(productividadOrdenHoraDivisor > 0){
				Long totalProductividadordenHora = ((detalle.getOrdenConRepartoDir() + detalle.getOrdenSinRepartoDir() + detalle.getPrimerasOrdenes())/
														detalle.getRutasNormales())/productividadOrdenHoraDivisor;
				detalle.setRepProductividadOrHr(totalProductividadordenHora.intValue());
			}
			
			detalle.setRepRendimientoProm(i*1.5);
			detalle.setEntPrimeraOrden(i*2);
			detalle.setEntOrdenPtoEntrega(i*i);
			detalle.setEntOrdenEstablecidas(i*3);
			detalle.setEntCajasTotales(i*5);
			detalle.setEntPremios(i);
			detalle.setEntMaterialPromocinal(i*2);
			detalle.setDevOrdenes(i+2);
			detalle.setDevCajas(i+3);
			detalle.setDevPremios(i+1);
			detalle.setDevMatPromocional(i);
			detalle.setCauNoViveAhi(i);
			detalle.setCauNoPago(i);
			detalle.setCauNoDejoFicha(i);
			detalle.setCauCambioDom(i);
			detalle.setCauCerradoTotal(i);
			detalle.setCauDifEnCobro(i);
			detalle.setCauFueraZona(i);
			detalle.setCauNoMetioPedido(i);
			detalle.setCauDomIncompleto(i);
			detalle.setCauNoEsperaReparto(i);
			detalle.setCauExtravioFicha(i);
			detalle.setCauOtro(i);
			detalle.setCauTotal(j+=i);
			listaDetalles.add(detalle);
		}
		headerResumen = resumen.obtenerTotalesReporteResumen(headerResumen,listaDetalles);
		headerResumen.setDetalles(listaDetalles);
		return headerResumen;
	}
	
public static List<ModelReporteResumen> generarReporteResumen(){
		
		List<ModelReporteResumen> listaDetalles = new ArrayList<ModelReporteResumen>();
	    ModelReporteResumen  detalle = null;
	   // ModelHeaderResumen headerResumen = new ModelHeaderResumen();
	    int j = 0;
	    GeneraReporteResumen resumen = new GeneraReporteResumen();
	    
	    
		for(int i=0;i<20;i++){
			detalle = new ModelReporteResumen();
			detalle.setCampania(String.valueOf(i));
			detalle.setProductividadDescarga(i);
			detalle.setProductividadEnrutado(i);
			detalle.setRutasNormales(196);
			detalle.setRutasEspeciales(i*3);
			detalle.setOrdenConRepartoDir(14445);
			detalle.setOrdenSinRepartoDir(0);
			detalle.setPrimerasOrdenes(683);
			detalle.setCajas(i*10);
			detalle.setPremios(i*2);
			detalle.setBultosMatPromocional(i*6);
 			detalle.setRepInicioRutaPromedio("2012-08-15 06:50:47.653");
			detalle.setRepPrimeraVisitaProm("2012-08-15 07:41:47.653");
			detalle.setRepUltimaVisitaPromedio("2012-08-15 13:17:47.653");
			detalle.setRepFinRutaPromedio("2012-08-15 07:50:47.653");
			detalle.setRepKmGlobal(39324*i);
			detalle.setRepKmEfectivo(1678*1);
			detalle.setRepTiempoPromVisita("00:00");
			long tiempoPromedioVisita = (detalle.getOrdenConRepartoDir()+detalle.getOrdenSinRepartoDir()+detalle.getPrimerasOrdenes())/detalle.getRutasNormales();
			
			if(tiempoPromedioVisita > 0){
				long tiempoPromedio = (resumen.obtenerSegundos(detalle.getRepUltimaVisitaPromedio()) - 
											resumen.obtenerSegundos(detalle.getRepPrimeraVisitaProm())) /
												tiempoPromedioVisita;
				detalle.setRepTiempoPromVisita(resumen.formatIntoHHMMSS(tiempoPromedio));
			}
			
			detalle.setRepProductividadOrHr(0);
			
			long productividadOrdenHoraDivisor = (resumen.obtenerSegundosHora(detalle.getRepUltimaVisitaPromedio()) - 
													resumen.obtenerSegundosHora(detalle.getRepPrimeraVisitaProm())) * 24;
			if(productividadOrdenHoraDivisor > 0){
				Long totalProductividadordenHora = ((detalle.getOrdenConRepartoDir() + detalle.getOrdenSinRepartoDir() + detalle.getPrimerasOrdenes())/
														detalle.getRutasNormales())/productividadOrdenHoraDivisor;
				detalle.setRepProductividadOrHr(totalProductividadordenHora.intValue());
			}
			
			detalle.setRepRendimientoProm(i*1.5);
			detalle.setEntPrimeraOrden(i*2);
			detalle.setEntOrdenPtoEntrega(i*i);
			detalle.setEntOrdenEstablecidas(i*3);
			detalle.setEntCajasTotales(i*5);
			detalle.setEntPremios(i);
			detalle.setEntMaterialPromocinal(i*2);
			detalle.setDevOrdenes(i+2);
			detalle.setDevCajas(i+3);
			detalle.setDevPremios(i+1);
			detalle.setDevMatPromocional(i);
			detalle.setCauNoViveAhi(i);
			detalle.setCauNoPago(i);
			detalle.setCauNoDejoFicha(i);
			detalle.setCauCambioDom(i);
			detalle.setCauCerradoTotal(i);
			detalle.setCauDifEnCobro(i);
			detalle.setCauFueraZona(i);
			detalle.setCauNoMetioPedido(i);
			detalle.setCauDomIncompleto(i);
			detalle.setCauNoEsperaReparto(i);
			detalle.setCauExtravioFicha(i);
			detalle.setCauOtro(i);
			detalle.setCauTotal(j+=i);
			listaDetalles.add(detalle);
		}
		//headerResumen = resumen.obtenerTotalesReporteResumen(headerResumen,listaDetalles);
		//headerResumen.setDetalles(listaDetalles);
		return listaDetalles;
	}
	
	public  long obtenerSegundosHora(String horaS) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date tTEnRu;
		int hora = 0;
		int minu = 0;
		try {
			tTEnRu = sdf.parse(horaS);
			calendar.setTime(tTEnRu);
			hora = calendar.get(Calendar.HOUR_OF_DAY);
			minu = calendar.get(Calendar.MINUTE);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}

		return (hora * 60 * 60) + (minu * 60);

	}
	
	public  long obtenerSegundos(String horaS) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date fecha;
		long tiempo = 0;
		try {
			fecha = sdf.parse(horaS);
			calendar.setTime(fecha);
			tiempo = calendar.getTimeInMillis();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		tiempo = tiempo/1000;
		return tiempo;
	}

	public String formatIntoHHMMSS(long secsIn) {

		long hours = secsIn / 3600;
		long remainder = secsIn % 3600;
		long minutes = remainder / 60;
		String resultado = ((hours < 10 ? "0" : "") + hours + ":"
				+ (minutes < 10 ? "0" : "") + minutes);
		if (secsIn < 0) {
			resultado = "00:00";
		}
		return resultado;

	}
	
	public String formatearHorasMinutos(long secsIn){
		String resultado = null;
		
		if(secsIn > 0){
			Date fecha = new Date(secsIn * 1000);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fecha);
			int horas = calendar.get(Calendar.HOUR_OF_DAY);
			int minutos = calendar.get(Calendar.MINUTE);
			resultado = ((horas < 10 ? "0" : "") + horas + ":"
				 		+ (minutos < 10 ? "0" : "") + minutos);
		}else{
			resultado = "00:00";
		}
		return resultado;
	}
	
private ModelHeaderResumen obtenerTotalesReporteResumen(ModelHeaderResumen headerResumen,List<ModelReporteResumen> arrayRepResumen){
		
		int ultimoElemento = arrayRepResumen.size()-1;
		
		headerResumen.setTotalInicioRutaPromedio(formatIntoHHMMSS(obtenerSegundosHora(arrayRepResumen.get(ultimoElemento).getRepInicioRutaPromedio())));
		headerResumen.setTotalPrimerVisitaPromedio(formatIntoHHMMSS(obtenerSegundosHora(arrayRepResumen.get(ultimoElemento).getRepPrimeraVisitaProm())));
		headerResumen.setTotalUltimaVisitaPromedio(formatIntoHHMMSS(obtenerSegundosHora(arrayRepResumen.get(ultimoElemento).getRepUltimaVisitaPromedio())));
		headerResumen.setTotalFinRutaPromedio(formatIntoHHMMSS(obtenerSegundosHora(arrayRepResumen.get(ultimoElemento).getRepFinRutaPromedio())));
		
		headerResumen.setTotalCauNoViveAhi(String.valueOf(arrayRepResumen.get(ultimoElemento).getCauNoViveAhi()));
		headerResumen.setTotalCauNoPago(String.valueOf(arrayRepResumen.get(ultimoElemento).getCauNoPago()));
		headerResumen.setTotalCauNoDejoFicha(String.valueOf(arrayRepResumen.get(ultimoElemento).getCauNoDejoFicha()));
		headerResumen.setTotalCauCambioDom(String.valueOf(arrayRepResumen.get(ultimoElemento).getCauCambioDom()));
		headerResumen.setTotalCauCerradoTotal(String.valueOf(arrayRepResumen.get(ultimoElemento).getCauCerradoTotal()));
		headerResumen.setTotalCauDifEnCobro(String.valueOf(arrayRepResumen.get(ultimoElemento).getCauDifEnCobro()));
		headerResumen.setTotalCauFueraZona(String.valueOf(arrayRepResumen.get(ultimoElemento).getCauFueraZona()));
		headerResumen.setTotalCauNoMetioPedido(String.valueOf(arrayRepResumen.get(ultimoElemento).getCauNoMetioPedido()));
		headerResumen.setTotalCauDomIncompleto(String.valueOf(arrayRepResumen.get(ultimoElemento).getCauDomIncompleto()));
		headerResumen.setTotalCauNoEsperaReparto(String.valueOf(arrayRepResumen.get(ultimoElemento).getCauNoEsperaReparto()));
		headerResumen.setTotalCauExtravioFicha(String.valueOf(arrayRepResumen.get(ultimoElemento).getCauExtravioFicha()));
		headerResumen.setTotalCauOtro(String.valueOf(arrayRepResumen.get(ultimoElemento).getCauOtro()));
		
		headerResumen.setTotalTiempoPromedioVisita(calcularTiempoPromedioVisitaRepResumen(arrayRepResumen));
		headerResumen.setTotalProductividadOrdenHora(calcularProductividadOrdenHoraRepResumen(arrayRepResumen));
		headerResumen.setTotalCauTotal(calcularCausasNoAceptacionTotal(arrayRepResumen));
	return headerResumen;	
	}
	
	public String calcularTiempoPromedioVisitaRepResumen(List<ModelReporteResumen> listaDetalles){
		String tiempoPromedioVisita = "00:00";
		long sumUltimaVisitaPromedio = 0;
		long sumPrimerVisitaPromedio = 0;
		int  sumOrdenRepDirecto = 0;
		int  sumOrdenSinRepDirecto = 0;
		int  sumPrimerasOrdenes = 0;
		int  sumRutasNormales = 0;
		int totalDivisor = 0;
		int ultimoElemento = 0;
		long resultado = 0;
		//List<com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen> listaDetalles = Arrays.asList(arrayRepResumen);
		
		for(ModelReporteResumen detalle: listaDetalles){
			sumOrdenRepDirecto += detalle.getOrdenConRepartoDir();
			sumOrdenSinRepDirecto += detalle.getOrdenSinRepartoDir();
			sumPrimerasOrdenes += detalle.getPrimerasOrdenes();
			sumRutasNormales += detalle.getRutasNormales();
		}
		
		if(sumRutasNormales > 0){
			totalDivisor = (sumOrdenRepDirecto + sumOrdenSinRepDirecto + sumPrimerasOrdenes)/sumRutasNormales;
			if(totalDivisor>0){
				ultimoElemento = listaDetalles.size() - 1;
				sumUltimaVisitaPromedio = obtenerSegundosHora(listaDetalles.get(ultimoElemento).getRepUltimaVisitaPromedio());
				sumPrimerVisitaPromedio = obtenerSegundosHora(listaDetalles.get(ultimoElemento).getRepPrimeraVisitaProm());
				resultado = (sumUltimaVisitaPromedio - sumPrimerVisitaPromedio)/totalDivisor;
				tiempoPromedioVisita = formatIntoHHMMSS(resultado);
			}
		}	
		return tiempoPromedioVisita;
	}
	
	public int calcularProductividadOrdenHoraRepResumen(List<ModelReporteResumen> listaDetalles){
		
		int productividadOrdenHora = 0;
		long sumUltimaVisitaPromedio = 0;
		long sumPrimerVisitaPromedio = 0;
		int  sumOrdenRepDirecto = 0;
		int  sumOrdenSinRepDirecto = 0;
		int  sumPrimerasOrdenes = 0;
		int  sumRutasNormales = 0;
		long totalDivisor = 0;
		int ultimoElemento = 0;
		Long resultado = null;
		
		//List<com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen> listaDetalles = Arrays.asList(arrayRepResumen);
		ultimoElemento = listaDetalles.size() - 1;
		sumUltimaVisitaPromedio = obtenerSegundosHora(listaDetalles.get(ultimoElemento).getRepUltimaVisitaPromedio());
		sumPrimerVisitaPromedio = obtenerSegundosHora(listaDetalles.get(ultimoElemento).getRepPrimeraVisitaProm());
		
		for(ModelReporteResumen detalle: listaDetalles){
			sumOrdenRepDirecto += detalle.getOrdenConRepartoDir();
			sumOrdenSinRepDirecto += detalle.getOrdenSinRepartoDir();
			sumPrimerasOrdenes += detalle.getPrimerasOrdenes();
			sumRutasNormales += detalle.getRutasNormales();
		}
		
		totalDivisor = (sumUltimaVisitaPromedio - sumPrimerVisitaPromedio) * 24;
		
		if(totalDivisor>0 && sumRutasNormales >0){
			resultado = ((sumOrdenRepDirecto + sumOrdenSinRepDirecto + sumPrimerasOrdenes)/sumRutasNormales)/totalDivisor;
		}
		productividadOrdenHora = resultado.intValue();
		return productividadOrdenHora;
	}
	
	private String calcularCausasNoAceptacionTotal(List<ModelReporteResumen> arrayRepResumen){
		String causaTotal = null;
		int ultimoElemento = arrayRepResumen.size() - 1;
		double resultadoTotal = 0;
		
		//List<com.datacode.avon_ots_ws.ReportesControllerStub.ModelReporteResumen> arrayRepResumen = Arrays.asList(arrayReporteResumen); // Cambiar el nombre en el param que llega.
		
		double causaNoViveAhi = arrayRepResumen.get(ultimoElemento).getCauNoViveAhi() ;
		double causaNoPago =    arrayRepResumen.get(ultimoElemento).getCauNoPago();
		double causaNoDejoFicha = arrayRepResumen.get(ultimoElemento).getCauNoDejoFicha();
		double causaCambioDom = arrayRepResumen.get(ultimoElemento).getCauCambioDom();
		double causaCerradoTotal = arrayRepResumen.get(ultimoElemento).getCauCerradoTotal();
		double causaDifEnCobro = arrayRepResumen.get(ultimoElemento).getCauDifEnCobro() ;
		double causaFueraZona = arrayRepResumen.get(ultimoElemento).getCauFueraZona();
		double causaNoMetioPedido = arrayRepResumen.get(ultimoElemento).getCauNoMetioPedido();
		double causaDomIncompleto = arrayRepResumen.get(ultimoElemento).getCauDomIncompleto();
		double causaNoEsperaReparto = arrayRepResumen.get(ultimoElemento).getCauNoEsperaReparto() ;
		double causaExtravioFicha = arrayRepResumen.get(ultimoElemento).getCauExtravioFicha();
		double causaOtro = arrayRepResumen.get(ultimoElemento).getCauOtro();
		
		resultadoTotal = causaNoViveAhi + causaNoPago + causaNoDejoFicha + causaCambioDom + causaCerradoTotal +
							causaDifEnCobro + causaFueraZona + causaNoMetioPedido + causaDomIncompleto +
								causaNoEsperaReparto + causaExtravioFicha+causaOtro;
		resultadoTotal = formatearDosDigitos(resultadoTotal);
		causaTotal = String.valueOf(resultadoTotal);
		return causaTotal;
	}
	
	private double formatearDosDigitos(double d) {
		DecimalFormat df = new DecimalFormat("#########0.00");
		return Double.parseDouble(df.format(d));
	}
}
