/**
 * @author jorge.torner
 * @since 03/01/2012
 */
package com.datacode.avon_ots_admin.utils;

import java.rmi.RemoteException;
import com.datacode.avon_ots_ws.UtilsStub;

/**
 * @author jorge.torner
 * @since 03/01/2012
 */
public class Configuracion {
	private int idCampania;
	private String anioCampania;
	private String numCampania;
	private int idLDC;
	private String descripcionLDC;
	private String razonSocialLDC;
	private short idPais;
	private int idUsuario;
	private String usuario;
	private String urlServiciosWeb;
	private String claveLDC;
	

	public int getIdCampania() {
		return idCampania;
	}
	public void setIdCampania(int idCampania) {
		this.idCampania = idCampania;
	}
	public String getAnioCampania() {
		return anioCampania;
	}
	public void setAnioCampania(String anioCampania) {
		this.anioCampania = anioCampania;
	}
	public String getNumCampania() {
		return numCampania;
	}
	public void setNumCampania(String numCampania) {
		this.numCampania = numCampania;
	}
	public int getIdLDC() {
		return idLDC;
	}
	public void setIdLDC(int idLDC) {
		this.idLDC = idLDC;
	}
	public String getDescripcionLDC() {
		return descripcionLDC;
	}
	public void setDescripcionLDC(String descripcionLDC) {
		this.descripcionLDC = descripcionLDC;
	}
	public String getRazonSocialLDC() {
		return razonSocialLDC;
	}
	public void setRazonSocialLDC(String razonSocialLDC) {
		this.razonSocialLDC = razonSocialLDC;
	}
	public short getIdPais() {
		return idPais;
	}
	public void setIdPais(short idPais) {
		this.idPais = idPais;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getUrlServiciosWeb() {
		return urlServiciosWeb;
	}
	public void setUrlServiciosWeb(String urlServiciosWeb) {
		this.urlServiciosWeb = urlServiciosWeb;
	}
	
	public String getClaveLDC() {
		return claveLDC;
	}
	public void setClaveLDC(String claveLDC) {
		this.claveLDC = claveLDC;
	}
	/**
	 * Método para cargar en las propiedades de la clase la configuración de sesión
	 * @author jorge.torner
	 * @since 04/01/2012
	 * @return
	 */
	public boolean CargarConfiguracion(){
		boolean res = false;
		UtilsStub.Configuracion config = null;
		try {
			UtilsStub stub = new UtilsStub();
			
			//Obtiene y asigna url de configuración de web services
			String url = Utils.modificarUrlServicioWeb(stub._getServiceClient().getOptions().getTo().getAddress());
			stub._getServiceClient().getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
					url));
			
			UtilsStub.CargarConfiguracion request = new UtilsStub.CargarConfiguracion();

			// invocamos
			UtilsStub.CargarConfiguracionResponse response = stub.cargarConfiguracion(request);

			// obtenemos respuesta
			config = response.get_return();
			
			this.anioCampania = config.getAnioCampania();
			this.descripcionLDC = config.getDescripcionLDC();
			this.idCampania = config.getIdCampania();
			this.idLDC = config.getIdLDC();
			this.idPais = config.getIdPais();
			this.numCampania = config.getNumCampania();
			this.razonSocialLDC = config.getRazonSocialLDC();
			this.claveLDC = config.getClaveLDC();
			this.setUrlServiciosWeb(Utils.obtenerPropiedadArchivoConfig("IpPuertoServiciosWeb"));

		} catch (RemoteException ex) {
			Utils.GuardarLogMensajeBD("General Admin", "M3", "No se pudo cargar la configuración", ex.getMessage(), 0);
		}
		
		return res;
	}

}
