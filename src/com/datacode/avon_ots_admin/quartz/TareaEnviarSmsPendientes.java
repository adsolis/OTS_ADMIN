package com.datacode.avon_ots_admin.quartz;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.axis2.AxisFault;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.datacode.avon_ots_admin.utils.AccesoBD;
import com.datacode.avon_ots_admin.utils.Utils;
import com.datacode.otse.controller.InfoPunteoYRepartoControllerServiceStub;
import com.datacode.otse.controller.InfoPunteoYRepartoControllerServiceStub.InformacionRepartoYSMSPendientesDTO;
import com.datacode.otse.controller.InfoPunteoYRepartoControllerServiceStub.RegistrosProcesadosDTO;
import com.datacode.otse.controller.InfoPunteoYRepartoControllerServiceStub.SmsPendientesDTO;


@DisallowConcurrentExecution
public class TareaEnviarSmsPendientes implements Job {
	
	ArrayList<SmsPendientesDTO> arregloSmsPendientes = new ArrayList<SmsPendientesDTO>();
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {	
		
		try
		{			
			InformacionRepartoYSMSPendientesDTO smsPendientes = obtieneSmsPendientes();//Obtenemos los registros a enviar
			RegistrosProcesadosDTO registrosProcesados = enviaSmsPendientes(smsPendientes);//Obtenemos los registros que se enviaron y se insertaron correctamente en el servidor OTSe central
			procesarSmsEnviados(registrosProcesados);//Movemos los registros a la tabla de enviados y los eliminamos de la tabla pendientes
			moverSmsNoEnviados();//Movemos los registros que ya paso su vigencia de envio
			
		}
		catch(Exception e){
			// Guardado a log
			// En caso de que no aplique usuario loggeado, se manda
			// idUsuario = 0
			Utils.GuardarLogMensajeBD("UCS030_8:EnviarSmsPendientes", "M1", "", e.getMessage(), 0);
			
		}
		
	}

	/**
	 * Obtiene los registros SMS Pendientes que no han caducado para enviar al servidor OTSe central
	 * @author Andres.Alvarez
	 * @date 18/02/2014
	 * @return : Regresa los registros de SMS Pendientes que se enviaran al servidor OTSe central
	 * @throws ParseException 
	 */
	private InformacionRepartoYSMSPendientesDTO  obtieneSmsPendientes() {
		
		InfoPunteoYRepartoControllerServiceStub stub = null;
		try {
			stub = new InfoPunteoYRepartoControllerServiceStub();
		} catch (AxisFault e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Obtiene y asigna url de configuración de web services
		String url = Utils.modificarUrlServicioWebeOTS(stub._getServiceClient().getOptions().getTo().getAddress());
		stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
				url));
		
		InformacionRepartoYSMSPendientesDTO datosRuta = new InformacionRepartoYSMSPendientesDTO();
	
		Connection conn = AccesoBD.abrirConexionOTS();
		CallableStatement cs = null;

		if (conn != null) {
			try {
				
				cs = conn.prepareCall("{call SP_SmsPendientesObtener()}");				
				ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);			
				while (rs.next()) {
					SmsPendientesDTO smsPendienteDto = new SmsPendientesDTO();
					smsPendienteDto.setIdSmsPendiente(rs.getString("ID_SMS_PENDIENTE"));
					smsPendienteDto.setIdSmsTipo(rs.getString("ID_SMS_TIPO"));
					smsPendienteDto.setRegistro(rs.getString("REGISTRO"));
					smsPendienteDto.setNoCelular(rs.getString("NO_CELULAR"));
					smsPendienteDto.setTextoSms(rs.getString("TEXTO_SMS"));
					smsPendienteDto.setFechaEnvio(rs.getString("FECHA_ENVIO"));
					smsPendienteDto.setFechaHoraRegreso(rs.getString("FECHA_ACTUALIZADO"));
					smsPendienteDto.setUsuarioActualiza(rs.getString("USUARIO_ACTUALIZA"));					
					arregloSmsPendientes.add(smsPendienteDto);
					datosRuta.addInfoSmsPendientes(smsPendienteDto);
				}
				
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);

			} catch (SQLException e) {
				// Cierre de objetos para liberar conexión
				AccesoBD.cerrarStatement(cs);
				AccesoBD.cerrarConexion(conn);
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.GuardarLogMensajeBD("UCS030_8:EnviarSmsPendientes", "M2", "", e.getMessage(),	0);
			}
		}
		return datosRuta;
	}
	
	/**
	 * Envia los SMS Pendientes que no han caducado al servidor OTSe central 
	 * @author Andres.Alvarez
	 * @date 18/02/2014
	 * @param datosRuta : Obtiene los registros que se enviaran al servidor OTSe central
	 * @return : Regresa los registros  que se insertaron de manera correcta en el servidor OTSe central
	 */
	private RegistrosProcesadosDTO  enviaSmsPendientes(InformacionRepartoYSMSPendientesDTO smsPendientes) {		
				
		InfoPunteoYRepartoControllerServiceStub stub = null;
		try {
			stub = new InfoPunteoYRepartoControllerServiceStub();
		} catch (AxisFault e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Obtiene y asigna url de configuración de web services
		String url = Utils.modificarUrlServicioWebeOTS(stub._getServiceClient().getOptions().getTo().getAddress());
		stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
				url));	
		
		RegistrosProcesadosDTO registrosProcesados = new RegistrosProcesadosDTO();
		InfoPunteoYRepartoControllerServiceStub.InfoSmsPendientes mensajesEnviar = new InfoPunteoYRepartoControllerServiceStub.InfoSmsPendientes();
		mensajesEnviar.setArg0(smsPendientes);
		try{
					
			InfoPunteoYRepartoControllerServiceStub.InfoSmsPendientesE request = new InfoPunteoYRepartoControllerServiceStub.InfoSmsPendientesE();
			request.setInfoSmsPendientes(mensajesEnviar);
			InfoPunteoYRepartoControllerServiceStub.InfoSmsPendientesResponseE smsPendientesResponse = stub.infoSmsPendientes(request);
			registrosProcesados = smsPendientesResponse.getInfoSmsPendientesResponse().get_return();
		
		} catch (Exception e) {
			
			// Guardado a log
			// En caso de que no aplique usuario loggeado, se manda
			// idUsuario = 0
			Utils.GuardarLogMensajeBD("UCS030_8:EnviarSmsPendientes", "M3", "", e.getMessage(),	0);
		}
		return registrosProcesados;
	}
	
	/**
	 * Mueve los registros de la tabla PW_SMS_PENDIENTES a la tabla PW_SMS_ENVIADOS, que se insertaron de manera correcta en el servidor OTSe central
	 * @author Andres.Alvarez
	 * @date 18/02/2014
	 * @param p_respuestaWS : Obtiene los registros que se insertaron de manera correcta en el servidor OTSe central
	 */
	private void procesarSmsEnviados(RegistrosProcesadosDTO p_respuestaWS) {
		
		//Se valida que el WS haya regresado registros
        if (p_respuestaWS.getIdSmsPendientes() != null){
        	
        	Connection conn = AccesoBD.abrirConexionOTS();
    		CallableStatement cs = null;    		
    		if (conn != null) {
    			try {
    				for(String item: p_respuestaWS.getIdSmsPendientes()){
    					for(int i = 0; i < arregloSmsPendientes.size(); i++){
    						if(arregloSmsPendientes.get(i).getIdSmsPendiente().contains(item)){
    							cs = conn.prepareCall("{call SP_SmsEnviadosInsertar(?,?,?,?,?,?,?)}");
    							cs.setObject("P_IDSMSPENDIENTE", arregloSmsPendientes.get(i).getIdSmsPendiente(),
    	    						Types.INTEGER);
    							cs.setObject("P_IDSMSTIPO", arregloSmsPendientes.get(i).getIdSmsTipo(),
    	    						Types.INTEGER);
    							cs.setObject("P_REGISTRO", arregloSmsPendientes.get(i).getRegistro(), Types.VARCHAR);
    							cs.setObject("P_NOCELULAR", arregloSmsPendientes.get(i).getNoCelular(), Types.VARCHAR);    	    				
	    	    				cs.setObject("P_TEXTOSMS", arregloSmsPendientes.get(i).getTextoSms(), Types.VARCHAR);
	    	    				cs.setObject("P_FECHAENVIO", arregloSmsPendientes.get(i).getFechaEnvio(), Types.VARCHAR);    	    				
	    	    				cs.setObject("P_USUARIOACTUALIZA", arregloSmsPendientes.get(i).getUsuarioActualiza(), Types.VARCHAR);
	    	    				cs.execute();
	    	    				AccesoBD.cerrarStatement(cs);
	    	    				arregloSmsPendientes.remove(i);
	    	    				break;
    						}
    	        		}    	        			
    	        	}    			
    				// Cierre de objetos para liberar conexión    				
    				AccesoBD.cerrarConexion(conn);

    			} catch (SQLException e) {
    				// Cierre de objetos para liberar conexión
    				AccesoBD.cerrarStatement(cs);
    				AccesoBD.cerrarConexion(conn);
    				// Guardado a log
    				// En caso de que no aplique usuario loggeado, se manda
    				// idUsuario = 0
    				Utils.GuardarLogMensajeBD("UCS030_8:EnviarSmsPendientes", "M4", "", e.getMessage(),	0);
    			}
    		}
        }		
	}
	
	/**
	 * Mueve los registros que ya caducaron en su tiempo de envio al servidor OTSe central
	 * @author Andres.Alvarez
	 * @date 18/02/2014
	 */
	private void moverSmsNoEnviados(){
		
		Connection conn = AccesoBD.abrirConexionOTS();
		CallableStatement cs = null;
		
		if (conn != null) {
			try {
				cs = conn.prepareCall("{call SP_SmsNoEnviadosInsertar()}");
				cs.execute();
			}
			catch(Exception e){
				// Guardado a log
				// En caso de que no aplique usuario loggeado, se manda
				// idUsuario = 0
				Utils.GuardarLogMensajeBD("UCS030_8:EnviarSmsPendientes", "M5", "", e.getMessage(), 0);
			}
		}
	}
	
}
