package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelRepMailDejadosCajas;
import com.datacode.avon_ots_admin.reports.model.ModelRepMailDejadosPremios;

public class GeneraReporteMailDejados {
	
	public static List<ModelRepMailDejadosCajas>generaRepMailDejadosCajas()
	{
		List<ModelRepMailDejadosCajas>resultado= new ArrayList<ModelRepMailDejadosCajas>();
		for(int i=1;i<=100;i++)
		{
			ModelRepMailDejadosCajas res= new ModelRepMailDejadosCajas();
			res.setAccount("593453");
			res.setCampania("2");
			res.setCantidad(i);
			res.setCodigo("087859590012");
			res.setDescripcion("licuadora");
			res.setMontoCobrar(154889.23);
			res.setOrden("sabe que sera");
			res.setTipoItem("Caja");
			res.setZona("278");
			res.setCont(i);
			
			
			resultado.add(res);
			
		}
		
		
		return resultado;
	}
	public static List<ModelRepMailDejadosPremios>generaRepMailDejadosPremios()
	{
		List<ModelRepMailDejadosPremios>resultado= new ArrayList<ModelRepMailDejadosPremios>();
		for(int i=1;i<=100;i++)
		{
			ModelRepMailDejadosPremios res= new ModelRepMailDejadosPremios();
			res.setAccount("593453");
			res.setCampania("2");
			res.setCantidad(i);
			res.setCodigo("087859590012");
			res.setDescripcion("licuadora");
			res.setNombre("ANDREA GARCÍA RUÍZ");
			res.setZona("278");
			res.setCont(i);
			resultado.add(res);
			
		}
		
		
		return resultado;
	}
}
