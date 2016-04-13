package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelRelPedidosDejados;
import com.datacode.avon_ots_admin.reports.model.ModelRelPedidosPrestados;
import com.datacode.avon_ots_admin.reports.model.ModelTablaDirectorioLideres;
import com.datacode.avon_ots_admin.reports.model.ModelTablaRelPedidosDejados;
import com.datacode.avon_ots_admin.reports.model.ModelTablaRelPedidosPrestados;

public class GeneraReportePedidosPrestadosTest {
	
	public static List<ModelRelPedidosPrestados>generaReportePedidosDejados()
	{
		List<ModelRelPedidosPrestados>pedidosDejadosG= new ArrayList<ModelRelPedidosPrestados>();
		ModelRelPedidosPrestados pedidoDejadoG= new ModelRelPedidosPrestados();
		pedidoDejadoG.setEntregado("ayudante");
		pedidoDejadoG.setCampania("campania");
		pedidoDejadoG.setTitulo("REPORTE DE PEDIDOS DEJADOS ");
		pedidoDejadoG.setZona("zona");
		pedidoDejadoG.setFecha(new Date());
		List<ModelTablaRelPedidosPrestados>tabla = new ArrayList<ModelTablaRelPedidosPrestados>();
		for(int i=1;i<=100;i++)
		{
			ModelTablaRelPedidosPrestados pedido= new ModelTablaRelPedidosPrestados();
			pedido.setCajas(i);
			pedido.setNombre("javier"+i*i);
			pedido.setCausa("1");
			pedido.setRuta("ruta");
			pedido.setRegistro("registro");
			pedido.setPremio(i*2);
			pedido.setImporte(i*i*i);
			pedido.setCont(i);
			
			tabla.add(pedido);
			
		}
		pedidoDejadoG.setListaPedidos(tabla);
		pedidosDejadosG.add(pedidoDejadoG);
		return pedidosDejadosG;
	}
}
