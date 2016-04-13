/**
 *
 *  @since 25/01/2012
 *
 */
package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelHistorialPorRepresentante;
import com.datacode.avon_ots_admin.reports.model.ModelTablaHistorialPorRepresentante;

/**
 * @author jessica.leon
 *
 */
public class GenerarReporteHistorialPorRepresentanteTest {

	public static List<ModelHistorialPorRepresentante> llenarReporte(){
	
		List<ModelHistorialPorRepresentante> listaFinal = new ArrayList<ModelHistorialPorRepresentante>();
		ModelHistorialPorRepresentante encabezado = new ModelHistorialPorRepresentante();
		
		List<ModelTablaHistorialPorRepresentante> tabla = new ArrayList<ModelTablaHistorialPorRepresentante>();
		ModelTablaHistorialPorRepresentante detalle = new ModelTablaHistorialPorRepresentante();
		
		encabezado.setZona("909");
		encabezado.setRegistro("95182761");
		encabezado.setNombre("YOLANDA QUIONEZ PEREZ");
		encabezado.setDireccion("CALLE ALLENDE Y 6A");
		encabezado.setDireccion1("MARSELLA #289A, MISION FUNDADORES");
		encabezado.setDireccion2("JACINTO #106, INSURGENTES");
		encabezado.setRed("44");
		
		for(int i = 0;i<30;i++){
			detalle = new ModelTablaHistorialPorRepresentante();
			detalle.setCampania("2010/0"+i);
			detalle.setaPagar(String.valueOf(i*3.5));
			detalle.setModificacion(String.valueOf(i*4.5));
			detalle.setCajas(String.valueOf(i+2));
			detalle.setGeoreferenciaDomicilio("mi casita");
			detalle.setTipoDePago("Telecom");
			detalle.setFolios("4875211691");
			detalle.setCantidadPago(String.valueOf(1500*i));
			detalle.setDiferencia(String.valueOf(0.2*i));
			detalle.setFechaDeEntrega("25/02/2010");
			detalle.setFechaDePago("20/01/2012");
			detalle.setEntregado("Ventanilla");
			detalle.setSlipValue(String.valueOf(i*3.14));
			detalle.setGeoreferenciaDeEntrega("tu casita");
			detalle.setDevueltoA("Pedido dejado en cub-bodega");
			detalle.setCausaDeDevolucion("02 - No pagó");
			detalle.setHijack("Quien sabe que va aqui");
			detalle.setRepReturn("NINGUNA");
			detalle.setCausaDevolucionFinal("No existe");
			detalle.setReturnValue(String.valueOf(i*2.7));
			detalle.setFechaDeCierre("11/03/2012");
			detalle.setFechaDevolucionFinal("04/01/2012");
			detalle.setObservaciones("Reporte muy extenso");
			tabla.add(detalle);
		}
		
		encabezado.setDetalleRepresentante(tabla);
		listaFinal.add(encabezado);
		
		return listaFinal;
	}
}
