package com.datacode.avon_ots_admin.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class InteractuarArchivos {

	private StringBuffer sb = new StringBuffer();
	private String salida;
	private String filename="C:/AVON JAVA/Pasos.txt";
	
	   /**
	  * El metodo leerArchivo lee un archivo de texto y retorna su contenido en
	    * formato de String
	   * @param filename String
	    * @return StringBuffer
	   */
	public String leerArchivo(){
	   try{
	       /**
	      * Aqui creamos un objeto File que representa el archivo de texto que
	       * queremos leer
	       */
	       File file = new File(filename);
	      /**
	       * Variable temporal que usaremos para leer cada una de las lineas del
	       * archivo de texto
	       */
	      String line = null;
	      /**
	       * BufferReader - Es el encargado de leer el archivo de texto.
	      * El constructor recibe como parametro un objeto FileReader, que
	       * a s vez recibe el objeto File creado precedentemente.
	       */
	      BufferedReader br = new BufferedReader(new FileReader(file));
	      /**
	       * A traves de este ciclo el BufferReader lee todo el archivo, y lo va acumulando (sb.append) en un StringBuffer
	       */
	       while ((line = br.readLine()) != null) {
	         sb.append(line);
	       }
	      /**
	      * Al final de la lectura cerramos el objeto
	      */
	      br.close();
	    } catch (FileNotFoundException fnfe){
	       /**
	      * Si damos un nombre de archivo que no existe el sistema genera automaticamente un error. 
	       */
	       System.out.println("No ha sido posible encontrar el archivo "+ filename);
	   }
	   catch (IOException ioe){
	      /** 
	       * Se ha producido un error durante la lectura del archivo 
	      */ 
	      System.out.println("Se ha producido un error durante la lectura del archivo "+ filename);
	    }
	   setSalida(sb.toString());
	   return sb.toString();
	}
	
	public String getSalida() {
		return salida;
	}
	
	public void setSalida(String salida) {
		this.salida = salida;
	}
	
	/**
	 * Guarda a un archivo log de errores, mayormente de conexión
	 * @author jorge.torner
	 * @date 22/01/2014
	 * @param texto a guardar
	 */
	public static void guardaArchivoLog(String texto){
			String ruta = Utils.obtenerPropiedadArchivoConfig("rutaArchivoLog");

			try{
				// Create file 
				FileWriter fstream = new FileWriter(ruta + "\\AvonAdminLog.txt", true);
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(Utils.ObtenerFechaActual(Utils.formatoFechaCortaHoraLarga) + " - " +  texto);
				out.newLine();
				//Close the output stream
				out.close();
			}catch (Exception e){//Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}
		
	}
}
