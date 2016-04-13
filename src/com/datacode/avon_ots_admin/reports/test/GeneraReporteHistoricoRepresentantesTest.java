/**
 *
 *  @since 26/01/2012
 *
 */
package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelHistoricoDeRepresentantes;
import com.datacode.avon_ots_admin.reports.model.ModelTablaHistoricoDeRepresentantes;


/**
 * @author jessica.leon
 *
 */
public class GeneraReporteHistoricoRepresentantesTest {
	
	public static List<ModelHistoricoDeRepresentantes> llenarReporte(){
		
		List<ModelHistoricoDeRepresentantes> listaFinal = new ArrayList<ModelHistoricoDeRepresentantes>();
		ModelHistoricoDeRepresentantes encabezado = new ModelHistoricoDeRepresentantes();
		
		List<ModelTablaHistoricoDeRepresentantes> tabla = new ArrayList<ModelTablaHistoricoDeRepresentantes>();
		ModelTablaHistoricoDeRepresentantes detalle = new ModelTablaHistoricoDeRepresentantes();
		
		encabezado.setZona("366");
		encabezado.setCampania("2011-23");
		
		for(int i = 0;i<30;i++){
			detalle = new ModelTablaHistoricoDeRepresentantes();
			detalle.setCEDI("CEL");
			detalle.setRegistro("95182761");
			detalle.setClaveRuta(String.valueOf(i+1));
			detalle.setNombre("YOLANDA QUIONEZ PEREZ");
			detalle.setDireccion("CALLE ALLENDE Y 6A");
			detalle.setDireccion1("MARSELLA #289A, MISION FUNDADORES");
			detalle.setDireccion2("JACINTO #106, INSURGENTES");
			detalle.setRed("44");
			detalle.setaPagar(String.valueOf(i*i));
			detalle.setModificacion(String.valueOf(i*3.3));
			detalle.setCajas(String.valueOf(i+2));
			detalle.setGeoreferenciaDomicilio("20.6081,-100.401");
			detalle.setTipoDePago("Telecom");
			detalle.setFolios("4875211691");
			detalle.setCantidadPago(String.valueOf(1500*i));
			detalle.setDiferencia(String.valueOf(0.2*i));
			detalle.setFechaDeEntrega("25/02/2010");
			detalle.setFechaDePago("20/01/2012");
			detalle.setEntregado("Ventanilla");
			detalle.setSlipValue(String.valueOf(i*3.14));
			detalle.setGeoreferenciaDeEntrega("20.6081,-100.401");
			detalle.setDevueltoA("Pedido dejado en sub-bodega");
			detalle.setCausaDeDevolucion("02 - No pagó");
			detalle.setHijack("Quien sabe que va aqui");
			detalle.setRepReturn("NINGUNA");
			detalle.setCausaDevolucionFinal("No existe");
			detalle.setReturnValue("????");
			detalle.setFechaDeCierre("11/03/2012");
			detalle.setFechaDevolucionFinal("04/01/2012");
			detalle.setObservaciones("Reporte muy extenso");
			tabla.add(detalle);
		}
		
		encabezado.setDetalleRepresentantes(tabla);
		listaFinal.add(encabezado);
		
		return listaFinal;
	}

}
