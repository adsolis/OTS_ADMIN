package com.datacode.avon_ots_admin.reports.test;

import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_admin.reports.model.ModelDirectorioLideres;
import com.datacode.avon_ots_admin.reports.model.ModelTablaDirectorioLideres;

public class GenerarReporteDirectorioLideresTest {
	
	
	public static List<ModelDirectorioLideres> generaReporteDirectorioLideres(){
		List<ModelDirectorioLideres>modelos= new ArrayList<ModelDirectorioLideres>();
		ModelDirectorioLideres modelo = new ModelDirectorioLideres();
		modelo.setConsejero("Javier");
		modelo.setDe("Ernestina");
		modelo.setPara("sum mama");
		modelo.setDomicilio("priva alamos");
		modelo.setMensaje("waksdjfñ ñkljfaskljf ñklsjdf ñklajdf lñkjaslñfkj asdklñjfñlaksdjf ñkl asd lñkjasdñkl jñaskldjf lkjd lkj");
		modelo.setPlaza("asdfas asdf ");
		modelo.setTitulo("DIRECTORIO DE LIDERES / EMPRESARIAS POR ZONA");
		List<ModelTablaDirectorioLideres> tabla= new ArrayList<ModelTablaDirectorioLideres>();
		for(int i=1;i<=100;i++)
		{
			ModelTablaDirectorioLideres lider= new ModelTablaDirectorioLideres();
			lider.setDireccion("direccion "+i);
			lider.setNombre("javier"+i*i);
			lider.setObservaciones("observaciones de entrada"+i);
			lider.setRed("sabe"+(i/i));
			lider.setTelefono("telefono"+(i*i*i*i*i));
			tabla.add(lider);
			
		}
		modelo.setListaLideres(tabla);
		modelos.add(modelo);
		return modelos;
	}

}
