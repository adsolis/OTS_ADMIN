package com.datacode.avon_ots_admin.quartz;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.PropertyConfigurator;

public class ListenerJobs implements javax.servlet.ServletContextListener {

	// public static String path="";
	public static String pathRep = "";
	public static String path = "";

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// path=arg0.getServletContext().getRealPath("/reportes");
		// Inicializar log
		try {
			ServletContext context = arg0.getServletContext();
			String log4jConfigFile = context
					.getInitParameter("log4j-config-location");
			String fullPath = context.getRealPath("") + File.separator
					+ log4jConfigFile;

			PropertyConfigurator.configure(fullPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pathRep = arg0.getServletContext().getRealPath("/reportes");
		path = arg0.getServletContext().getRealPath("/");

		JobMandarMailDejados jobs = new JobMandarMailDejados();
		// JobActualizarCatalogos jobActualizarCatalogos = new
		// JobActualizarCatalogos();
		JobActualizarCatalogosDesdeArchivo jobActualizarDesdeArchivo = new JobActualizarCatalogosDesdeArchivo();
		JobActualizarTablas jobReplicacion = new JobActualizarTablas();
		JobEnviarSmsPendientes jobSmsPendientes = new JobEnviarSmsPendientes();
		JobMandarMailsNoEnviados jobNoEnviados = new JobMandarMailsNoEnviados();
		JobNotificacionesCorreo jobNotificacionesCorreo = new JobNotificacionesCorreo();
		try {
			jobs.iniciarJobs();
			// jobActualizarCatalogos.iniciarJobs();
			jobActualizarDesdeArchivo.iniciarJobs();
			jobReplicacion.iniciarJob();
			jobSmsPendientes.iniciarJob();
			jobNoEnviados.iniciarJobs();
			jobNotificacionesCorreo.iniciarJob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
