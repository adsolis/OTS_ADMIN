package com.datacode.avon_ots_admin.reports.model;

import java.util.List;

public class ModelHeaderReparto {

	private List<ModelRepReparto> detalleReparto;
	private long envTotalOrdenesConReparto;
	private long envTotalOrdenesSinReparto;
	private long envTotalPrimerasOrdenes;
	private long envTotalOrdenesTotales;
	private long envTotalCajasTotales;
	private double envTotalCajProm;
	private long envTotalPremios;
	private long kmTotalInicioRuta;
	private long kmTotalPrimeraEntrega;
	private long kmTotalUltimaEntrega;
	private long kmTotalFinRuta;
	private long kmTotalRepartoGlobal;
	private long kmTotalRepartoEfectivo;
	private long kmTotalArrastre;
	private String hrTotalInicioRuta;
	private String hrTotalPrimeraEntrega;
	private String hrTotalUltimaEntrega;
	private String hrTotalFinRuta;
	private String hrTotalRepartoGlobal;
	private String hrTotalRepartoEfectivo;
	private String hrTotalArrastre;
	private String proTotalTiempoVisita;
	private double proTotalOrdHr;
	private double renTotalLitros;
	private double renTotalKmLt;
	private long entTotalPrimeraOrden;
	private long entTotalOrdenesEstablecidas;
	private long entTotalCajasTotales;
	private long entTotalPremios;
	private double acpTotalPremios;
	private double acpTotalPrimeraOrden;
	private double acpTotalTotal;
	private long devTotalOrdenes;
	private long devTotalCajas;
	private long devTotalPremios;
	private double cauPorNoviveahi;
	private double cauPorNoPago;
	private double cauPorNodejoFicha;
	private double cauPorCambiodedomicilio;
	private double cauPorCerradoTotal;
	private double cauPorDiferenciaenCobro;
	private double cauPorFueraZona;
	private double cauPorNometioPedido;
	private double cauPorDomIncompleto;
	private double cauPorNoEsperaReparto;
	private double cauPorExtravioFicha;
	private double cauPorOtro;
	private double cauPorDiferencia;
	private double cauTotNoviveahi;
	private double cauTotNoPago;
	private double cauTotNodejoFicha;
	private double cauTotCambiodedomicilio;
	private double cauTotCerradoTotal;
	private double cauTotDiferenciaenCobro;
	private double cauTotFueraZona;
	private double cauTotNometioPedido;
	private double cauTotDomIncompleto;
	private double cauTotNoEsperaReparto;
	private double cauTotExtravioFicha;
	private double cauTotOtro;
	private double cauTotDiferencia;
	public List<ModelRepReparto> getDetalleReparto() {
		return detalleReparto;
	}
	public void setDetalleReparto(List<ModelRepReparto> detalleReparto) {
		this.detalleReparto = detalleReparto;
	}
	public long getEnvTotalOrdenesConReparto() {
		return envTotalOrdenesConReparto;
	}
	public void setEnvTotalOrdenesConReparto(long envTotalOrdenesConReparto) {
		this.envTotalOrdenesConReparto = envTotalOrdenesConReparto;
	}
	public long getEnvTotalOrdenesSinReparto() {
		return envTotalOrdenesSinReparto;
	}
	public void setEnvTotalOrdenesSinReparto(long envTotalOrdenesSinReparto) {
		this.envTotalOrdenesSinReparto = envTotalOrdenesSinReparto;
	}
	public long getEnvTotalPrimerasOrdenes() {
		return envTotalPrimerasOrdenes;
	}
	public void setEnvTotalPrimerasOrdenes(long envTotalPrimerasOrdenes) {
		this.envTotalPrimerasOrdenes = envTotalPrimerasOrdenes;
	}
	public long getEnvTotalOrdenesTotales() {
		return envTotalOrdenesTotales;
	}
	public void setEnvTotalOrdenesTotales(long envTotalOrdenesTotales) {
		this.envTotalOrdenesTotales = envTotalOrdenesTotales;
	}
	public long getEnvTotalCajasTotales() {
		return envTotalCajasTotales;
	}
	public void setEnvTotalCajasTotales(long envTotalCajasTotales) {
		this.envTotalCajasTotales = envTotalCajasTotales;
	}
	public double getEnvTotalCajProm() {
		return envTotalCajProm;
	}
	public void setEnvTotalCajProm(double envTotalCajProm) {
		this.envTotalCajProm = envTotalCajProm;
	}
	public long getEnvTotalPremios() {
		return envTotalPremios;
	}
	public void setEnvTotalPremios(long envTotalPremios) {
		this.envTotalPremios = envTotalPremios;
	}
	public long getKmTotalInicioRuta() {
		return kmTotalInicioRuta;
	}
	public void setKmTotalInicioRuta(long kmTotalInicioRuta) {
		this.kmTotalInicioRuta = kmTotalInicioRuta;
	}
	public long getKmTotalPrimeraEntrega() {
		return kmTotalPrimeraEntrega;
	}
	public void setKmTotalPrimeraEntrega(long kmTotalPrimeraEntrega) {
		this.kmTotalPrimeraEntrega = kmTotalPrimeraEntrega;
	}
	public long getKmTotalUltimaEntrega() {
		return kmTotalUltimaEntrega;
	}
	public void setKmTotalUltimaEntrega(long kmTotalUltimaEntrega) {
		this.kmTotalUltimaEntrega = kmTotalUltimaEntrega;
	}
	public long getKmTotalFinRuta() {
		return kmTotalFinRuta;
	}
	public void setKmTotalFinRuta(long kmTotalFinRuta) {
		this.kmTotalFinRuta = kmTotalFinRuta;
	}
	public long getKmTotalRepartoGlobal() {
		return kmTotalRepartoGlobal;
	}
	public void setKmTotalRepartoGlobal(long kmTotalRepartoGlobal) {
		this.kmTotalRepartoGlobal = kmTotalRepartoGlobal;
	}
	public long getKmTotalRepartoEfectivo() {
		return kmTotalRepartoEfectivo;
	}
	public void setKmTotalRepartoEfectivo(long kmTotalRepartoEfectivo) {
		this.kmTotalRepartoEfectivo = kmTotalRepartoEfectivo;
	}
	public long getKmTotalArrastre() {
		return kmTotalArrastre;
	}
	public void setKmTotalArrastre(long kmTotalArrastre) {
		this.kmTotalArrastre = kmTotalArrastre;
	}
	public String getHrTotalInicioRuta() {
		return hrTotalInicioRuta;
	}
	public void setHrTotalInicioRuta(String hrTotalInicioRuta) {
		this.hrTotalInicioRuta = hrTotalInicioRuta;
	}
	public String getHrTotalPrimeraEntrega() {
		return hrTotalPrimeraEntrega;
	}
	public void setHrTotalPrimeraEntrega(String hrTotalPrimeraEntrega) {
		this.hrTotalPrimeraEntrega = hrTotalPrimeraEntrega;
	}
	public String getHrTotalUltimaEntrega() {
		return hrTotalUltimaEntrega;
	}
	public void setHrTotalUltimaEntrega(String hrTotalUltimaEntrega) {
		this.hrTotalUltimaEntrega = hrTotalUltimaEntrega;
	}
	public String getHrTotalFinRuta() {
		return hrTotalFinRuta;
	}
	public void setHrTotalFinRuta(String hrTotalFinRuta) {
		this.hrTotalFinRuta = hrTotalFinRuta;
	}
	public String getHrTotalRepartoGlobal() {
		return hrTotalRepartoGlobal;
	}
	public void setHrTotalRepartoGlobal(String hrTotalRepartoGlobal) {
		this.hrTotalRepartoGlobal = hrTotalRepartoGlobal;
	}
	public String getHrTotalRepartoEfectivo() {
		return hrTotalRepartoEfectivo;
	}
	public void setHrTotalRepartoEfectivo(String hrTotalRepartoEfectivo) {
		this.hrTotalRepartoEfectivo = hrTotalRepartoEfectivo;
	}
	public String getHrTotalArrastre() {
		return hrTotalArrastre;
	}
	public void setHrTotalArrastre(String hrTotalArrastre) {
		this.hrTotalArrastre = hrTotalArrastre;
	}
	public String getProTotalTiempoVisita() {
		return proTotalTiempoVisita;
	}
	public void setProTotalTiempoVisita(String proTotalTiempoVisita) {
		this.proTotalTiempoVisita = proTotalTiempoVisita;
	}
	public double getProTotalOrdHr() {
		return proTotalOrdHr;
	}
	public void setProTotalOrdHr(double proTotalOrdHr) {
		this.proTotalOrdHr = proTotalOrdHr;
	}
	public double getRenTotalLitros() {
		return renTotalLitros;
	}
	public void setRenTotalLitros(double renTotalLitros) {
		this.renTotalLitros = renTotalLitros;
	}
	public double getRenTotalKmLt() {
		return renTotalKmLt;
	}
	public void setRenTotalKmLt(double renTotalKmLt) {
		this.renTotalKmLt = renTotalKmLt;
	}
	public long getEntTotalPrimeraOrden() {
		return entTotalPrimeraOrden;
	}
	public void setEntTotalPrimeraOrden(long entTotalPrimeraOrden) {
		this.entTotalPrimeraOrden = entTotalPrimeraOrden;
	}
	public long getEntTotalOrdenesEstablecidas() {
		return entTotalOrdenesEstablecidas;
	}
	public void setEntTotalOrdenesEstablecidas(long entTotalOrdenesEstablecidas) {
		this.entTotalOrdenesEstablecidas = entTotalOrdenesEstablecidas;
	}
	public long getEntTotalCajasTotales() {
		return entTotalCajasTotales;
	}
	public void setEntTotalCajasTotales(long entTotalCajasTotales) {
		this.entTotalCajasTotales = entTotalCajasTotales;
	}
	public long getEntTotalPremios() {
		return entTotalPremios;
	}
	public void setEntTotalPremios(long entTotalPremios) {
		this.entTotalPremios = entTotalPremios;
	}
	public double getAcpTotalPremios() {
		return acpTotalPremios;
	}
	public void setAcpTotalPremios(double acpTotalPremios) {
		this.acpTotalPremios = acpTotalPremios;
	}
	public double getAcpTotalPrimeraOrden() {
		return acpTotalPrimeraOrden;
	}
	public void setAcpTotalPrimeraOrden(double acpTotalPrimeraOrden) {
		this.acpTotalPrimeraOrden = acpTotalPrimeraOrden;
	}
	public double getAcpTotalTotal() {
		return acpTotalTotal;
	}
	public void setAcpTotalTotal(double acpTotalTotal) {
		this.acpTotalTotal = acpTotalTotal;
	}
	public long getDevTotalOrdenes() {
		return devTotalOrdenes;
	}
	public void setDevTotalOrdenes(long devTotalOrdenes) {
		this.devTotalOrdenes = devTotalOrdenes;
	}
	public long getDevTotalCajas() {
		return devTotalCajas;
	}
	public void setDevTotalCajas(long devTotalCajas) {
		this.devTotalCajas = devTotalCajas;
	}
	public long getDevTotalPremios() {
		return devTotalPremios;
	}
	public void setDevTotalPremios(long devTotalPremios) {
		this.devTotalPremios = devTotalPremios;
	}
	public double getCauPorNoviveahi() {
		return cauPorNoviveahi;
	}
	public void setCauPorNoviveahi(double cauPorNoviveahi) {
		this.cauPorNoviveahi = cauPorNoviveahi;
	}
	public double getCauPorNoPago() {
		return cauPorNoPago;
	}
	public void setCauPorNoPago(double cauPorNoPago) {
		this.cauPorNoPago = cauPorNoPago;
	}
	public double getCauPorNodejoFicha() {
		return cauPorNodejoFicha;
	}
	public void setCauPorNodejoFicha(double cauPorNodejoFicha) {
		this.cauPorNodejoFicha = cauPorNodejoFicha;
	}
	public double getCauPorCambiodedomicilio() {
		return cauPorCambiodedomicilio;
	}
	public void setCauPorCambiodedomicilio(double cauPorCambiodedomicilio) {
		this.cauPorCambiodedomicilio = cauPorCambiodedomicilio;
	}
	public double getCauPorCerradoTotal() {
		return cauPorCerradoTotal;
	}
	public void setCauPorCerradoTotal(double cauPorCerradoTotal) {
		this.cauPorCerradoTotal = cauPorCerradoTotal;
	}
	public double getCauPorDiferenciaenCobro() {
		return cauPorDiferenciaenCobro;
	}
	public void setCauPorDiferenciaenCobro(double cauPorDiferenciaenCobro) {
		this.cauPorDiferenciaenCobro = cauPorDiferenciaenCobro;
	}
	public double getCauPorFueraZona() {
		return cauPorFueraZona;
	}
	public void setCauPorFueraZona(double cauPorFueraZona) {
		this.cauPorFueraZona = cauPorFueraZona;
	}
	public double getCauPorNometioPedido() {
		return cauPorNometioPedido;
	}
	public void setCauPorNometioPedido(double cauPorNometioPedido) {
		this.cauPorNometioPedido = cauPorNometioPedido;
	}
	public double getCauPorDomIncompleto() {
		return cauPorDomIncompleto;
	}
	public void setCauPorDomIncompleto(double cauPorDomIncompleto) {
		this.cauPorDomIncompleto = cauPorDomIncompleto;
	}
	public double getCauPorNoEsperaReparto() {
		return cauPorNoEsperaReparto;
	}
	public void setCauPorNoEsperaReparto(double cauPorNoEsperaReparto) {
		this.cauPorNoEsperaReparto = cauPorNoEsperaReparto;
	}
	public double getCauPorExtravioFicha() {
		return cauPorExtravioFicha;
	}
	public void setCauPorExtravioFicha(double cauPorExtravioFicha) {
		this.cauPorExtravioFicha = cauPorExtravioFicha;
	}
	public double getCauPorOtro() {
		return cauPorOtro;
	}
	public void setCauPorOtro(double cauPorOtro) {
		this.cauPorOtro = cauPorOtro;
	}
	public double getCauPorDiferencia() {
		return cauPorDiferencia;
	}
	public void setCauPorDiferencia(double cauPorDiferencia) {
		this.cauPorDiferencia = cauPorDiferencia;
	}
	public double getCauTotNoviveahi() {
		return cauTotNoviveahi;
	}
	public void setCauTotNoviveahi(double cauTotNoviveahi) {
		this.cauTotNoviveahi = cauTotNoviveahi;
	}
	public double getCauTotNoPago() {
		return cauTotNoPago;
	}
	public void setCauTotNoPago(double cauTotNoPago) {
		this.cauTotNoPago = cauTotNoPago;
	}
	public double getCauTotNodejoFicha() {
		return cauTotNodejoFicha;
	}
	public void setCauTotNodejoFicha(double cauTotNodejoFicha) {
		this.cauTotNodejoFicha = cauTotNodejoFicha;
	}
	public double getCauTotCambiodedomicilio() {
		return cauTotCambiodedomicilio;
	}
	public void setCauTotCambiodedomicilio(double cauTotCambiodedomicilio) {
		this.cauTotCambiodedomicilio = cauTotCambiodedomicilio;
	}
	public double getCauTotCerradoTotal() {
		return cauTotCerradoTotal;
	}
	public void setCauTotCerradoTotal(double cauTotCerradoTotal) {
		this.cauTotCerradoTotal = cauTotCerradoTotal;
	}
	public double getCauTotDiferenciaenCobro() {
		return cauTotDiferenciaenCobro;
	}
	public void setCauTotDiferenciaenCobro(double cauTotDiferenciaenCobro) {
		this.cauTotDiferenciaenCobro = cauTotDiferenciaenCobro;
	}
	public double getCauTotFueraZona() {
		return cauTotFueraZona;
	}
	public void setCauTotFueraZona(double cauTotFueraZona) {
		this.cauTotFueraZona = cauTotFueraZona;
	}
	public double getCauTotNometioPedido() {
		return cauTotNometioPedido;
	}
	public void setCauTotNometioPedido(double cauTotNometioPedido) {
		this.cauTotNometioPedido = cauTotNometioPedido;
	}
	public double getCauTotDomIncompleto() {
		return cauTotDomIncompleto;
	}
	public void setCauTotDomIncompleto(double cauTotDomIncompleto) {
		this.cauTotDomIncompleto = cauTotDomIncompleto;
	}
	public double getCauTotNoEsperaReparto() {
		return cauTotNoEsperaReparto;
	}
	public void setCauTotNoEsperaReparto(double cauTotNoEsperaReparto) {
		this.cauTotNoEsperaReparto = cauTotNoEsperaReparto;
	}
	public double getCauTotExtravioFicha() {
		return cauTotExtravioFicha;
	}
	public void setCauTotExtravioFicha(double cauTotExtravioFicha) {
		this.cauTotExtravioFicha = cauTotExtravioFicha;
	}
	public double getCauTotOtro() {
		return cauTotOtro;
	}
	public void setCauTotOtro(double cauTotOtro) {
		this.cauTotOtro = cauTotOtro;
	}
	public double getCauTotDiferencia() {
		return cauTotDiferencia;
	}
	public void setCauTotDiferencia(double cauTotDiferencia) {
		this.cauTotDiferencia = cauTotDiferencia;
	}
}
