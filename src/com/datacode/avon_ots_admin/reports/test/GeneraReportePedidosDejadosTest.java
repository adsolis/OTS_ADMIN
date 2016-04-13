package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelRelPedidosDejados;
import com.datacode.avon_ots_admin.reports.model.ModelTablaDirectorioLideres;
import com.datacode.avon_ots_admin.reports.model.ModelTablaRelPedidosDejados;

public class GeneraReportePedidosDejadosTest {
	 
	public static List<ModelRelPedidosDejados>generaReportePedidosDejados()
	{
		List<ModelRelPedidosDejados>pedidosDejadosG= new ArrayList<ModelRelPedidosDejados>();
		ModelRelPedidosDejados pedidoDejadoG= new ModelRelPedidosDejados();
		pedidoDejadoG.setAyudante("ayudante");
		pedidoDejadoG.setCampania("campania");
		pedidoDejadoG.setChofer("chofer");
		pedidoDejadoG.setClave("clave");
		pedidoDejadoG.setEncargado("encargado");
		pedidoDejadoG.setPoblacionP("poblacionP");
		pedidoDejadoG.setRepartidor("repartidor");
		pedidoDejadoG.setRuta("ruta");
		pedidoDejadoG.setTitulo("REPORTE DE PEDIDOS DEJADOS ");
		pedidoDejadoG.setZona("zona");
		List<ModelTablaRelPedidosDejados>tabla = new ArrayList<ModelTablaRelPedidosDejados>();
		for(int i=1;i<=100;i++)
		{
			ModelTablaRelPedidosDejados pedido= new ModelTablaRelPedidosDejados();
			pedido.setCajas(i);
			pedido.setNombre("javier"+i*i);
			pedido.setCausa1("1");
			pedido.setRuta("ruta");
			pedido.setRegistro("registro");
			pedido.setPremio("premio");
			pedido.setImporte(i*i*i);
			pedido.setCont(i);
			
			tabla.add(pedido);
			
		}
		pedidoDejadoG.setPedidosDejados(tabla);
		pedidosDejadosG.add(pedidoDejadoG);
		return pedidosDejadosG;
	}
}
