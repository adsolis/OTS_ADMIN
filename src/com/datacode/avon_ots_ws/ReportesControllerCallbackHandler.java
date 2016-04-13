
/**
 * ReportesControllerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  ReportesControllerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ReportesControllerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ReportesControllerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ReportesControllerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for obtenerLiquidacionesSalidaCamioneta method
            * override this method for handling normal response from obtenerLiquidacionesSalidaCamioneta operation
            */
           public void receiveResultobtenerLiquidacionesSalidaCamioneta(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerLiquidacionesSalidaCamionetaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerLiquidacionesSalidaCamioneta operation
           */
            public void receiveErrorobtenerLiquidacionesSalidaCamioneta(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtieneHeaderRelPedDejados method
            * override this method for handling normal response from obtieneHeaderRelPedDejados operation
            */
           public void receiveResultobtieneHeaderRelPedDejados(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtieneHeaderRelPedDejadosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtieneHeaderRelPedDejados operation
           */
            public void receiveErrorobtieneHeaderRelPedDejados(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarSegundaLiquidacionAnalisisEfectivo method
            * override this method for handling normal response from consultarSegundaLiquidacionAnalisisEfectivo operation
            */
           public void receiveResultconsultarSegundaLiquidacionAnalisisEfectivo(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ConsultarSegundaLiquidacionAnalisisEfectivoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarSegundaLiquidacionAnalisisEfectivo operation
           */
            public void receiveErrorconsultarSegundaLiquidacionAnalisisEfectivo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerReporteManifiestoRutaEnCampania method
            * override this method for handling normal response from obtenerReporteManifiestoRutaEnCampania operation
            */
           public void receiveResultobtenerReporteManifiestoRutaEnCampania(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerReporteManifiestoRutaEnCampaniaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerReporteManifiestoRutaEnCampania operation
           */
            public void receiveErrorobtenerReporteManifiestoRutaEnCampania(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerReportes method
            * override this method for handling normal response from obtenerReportes operation
            */
           public void receiveResultobtenerReportes(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerReportesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerReportes operation
           */
            public void receiveErrorobtenerReportes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerObservacionesReporteRecepcionCarga method
            * override this method for handling normal response from obtenerObservacionesReporteRecepcionCarga operation
            */
           public void receiveResultobtenerObservacionesReporteRecepcionCarga(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerObservacionesReporteRecepcionCargaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerObservacionesReporteRecepcionCarga operation
           */
            public void receiveErrorobtenerObservacionesReporteRecepcionCarga(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarDatosReportePremios method
            * override this method for handling normal response from consultarDatosReportePremios operation
            */
           public void receiveResultconsultarDatosReportePremios(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ConsultarDatosReportePremiosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarDatosReportePremios operation
           */
            public void receiveErrorconsultarDatosReportePremios(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDetalleNARTotalRED method
            * override this method for handling normal response from getDetalleNARTotalRED operation
            */
           public void receiveResultgetDetalleNARTotalRED(
                    com.datacode.avon_ots_ws.ReportesControllerStub.GetDetalleNARTotalREDResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDetalleNARTotalRED operation
           */
            public void receiveErrorgetDetalleNARTotalRED(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarInfoCodigosFaltantesReporteControlRecepcionCarga method
            * override this method for handling normal response from consultarInfoCodigosFaltantesReporteControlRecepcionCarga operation
            */
           public void receiveResultconsultarInfoCodigosFaltantesReporteControlRecepcionCarga(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ConsultarInfoCodigosFaltantesReporteControlRecepcionCargaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarInfoCodigosFaltantesReporteControlRecepcionCarga operation
           */
            public void receiveErrorconsultarInfoCodigosFaltantesReporteControlRecepcionCarga(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerDestinatariosReportesPorTipoReporte method
            * override this method for handling normal response from obtenerDestinatariosReportesPorTipoReporte operation
            */
           public void receiveResultobtenerDestinatariosReportesPorTipoReporte(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerDestinatariosReportesPorTipoReporteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerDestinatariosReportesPorTipoReporte operation
           */
            public void receiveErrorobtenerDestinatariosReportesPorTipoReporte(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerEncabezadoHistorialPorRepresentante method
            * override this method for handling normal response from obtenerEncabezadoHistorialPorRepresentante operation
            */
           public void receiveResultobtenerEncabezadoHistorialPorRepresentante(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerEncabezadoHistorialPorRepresentanteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerEncabezadoHistorialPorRepresentante operation
           */
            public void receiveErrorobtenerEncabezadoHistorialPorRepresentante(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaItemsDejadosXSubbodega method
            * override this method for handling normal response from obtenerListaItemsDejadosXSubbodega operation
            */
           public void receiveResultobtenerListaItemsDejadosXSubbodega(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerListaItemsDejadosXSubbodegaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaItemsDejadosXSubbodega operation
           */
            public void receiveErrorobtenerListaItemsDejadosXSubbodega(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerRepresentantesReportes method
            * override this method for handling normal response from obtenerRepresentantesReportes operation
            */
           public void receiveResultobtenerRepresentantesReportes(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerRepresentantesReportesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerRepresentantesReportes operation
           */
            public void receiveErrorobtenerRepresentantesReportes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerDatosReporteHistorialPorRepresentante method
            * override this method for handling normal response from obtenerDatosReporteHistorialPorRepresentante operation
            */
           public void receiveResultobtenerDatosReporteHistorialPorRepresentante(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerDatosReporteHistorialPorRepresentanteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerDatosReporteHistorialPorRepresentante operation
           */
            public void receiveErrorobtenerDatosReporteHistorialPorRepresentante(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerReporteManifiestoRutaEnCampania_Detalle method
            * override this method for handling normal response from obtenerReporteManifiestoRutaEnCampania_Detalle operation
            */
           public void receiveResultobtenerReporteManifiestoRutaEnCampania_Detalle(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerReporteManifiestoRutaEnCampania_DetalleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerReporteManifiestoRutaEnCampania_Detalle operation
           */
            public void receiveErrorobtenerReporteManifiestoRutaEnCampania_Detalle(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarCargaRecibidaReporteControlRecepcionCarga method
            * override this method for handling normal response from consultarCargaRecibidaReporteControlRecepcionCarga operation
            */
           public void receiveResultconsultarCargaRecibidaReporteControlRecepcionCarga(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ConsultarCargaRecibidaReporteControlRecepcionCargaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarCargaRecibidaReporteControlRecepcionCarga operation
           */
            public void receiveErrorconsultarCargaRecibidaReporteControlRecepcionCarga(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerDesgloceEfectivo method
            * override this method for handling normal response from obtenerDesgloceEfectivo operation
            */
           public void receiveResultobtenerDesgloceEfectivo(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerDesgloceEfectivoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerDesgloceEfectivo operation
           */
            public void receiveErrorobtenerDesgloceEfectivo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarCajasMaltratadasReporteControlRecepcionCarga method
            * override this method for handling normal response from consultarCajasMaltratadasReporteControlRecepcionCarga operation
            */
           public void receiveResultconsultarCajasMaltratadasReporteControlRecepcionCarga(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ConsultarCajasMaltratadasReporteControlRecepcionCargaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarCajasMaltratadasReporteControlRecepcionCarga operation
           */
            public void receiveErrorconsultarCajasMaltratadasReporteControlRecepcionCarga(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarEncabezadoReporteControlRecepcionCarga method
            * override this method for handling normal response from consultarEncabezadoReporteControlRecepcionCarga operation
            */
           public void receiveResultconsultarEncabezadoReporteControlRecepcionCarga(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ConsultarEncabezadoReporteControlRecepcionCargaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarEncabezadoReporteControlRecepcionCarga operation
           */
            public void receiveErrorconsultarEncabezadoReporteControlRecepcionCarga(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtieneDatosDescargaEnrutado method
            * override this method for handling normal response from obtieneDatosDescargaEnrutado operation
            */
           public void receiveResultobtieneDatosDescargaEnrutado(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtieneDatosDescargaEnrutadoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtieneDatosDescargaEnrutado operation
           */
            public void receiveErrorobtieneDatosDescargaEnrutado(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarDatosReporteResumen method
            * override this method for handling normal response from consultarDatosReporteResumen operation
            */
           public void receiveResultconsultarDatosReporteResumen(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ConsultarDatosReporteResumenResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarDatosReporteResumen operation
           */
            public void receiveErrorconsultarDatosReporteResumen(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaSubbodegasItemsDejados method
            * override this method for handling normal response from obtenerListaSubbodegasItemsDejados operation
            */
           public void receiveResultobtenerListaSubbodegasItemsDejados(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerListaSubbodegasItemsDejadosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaSubbodegasItemsDejados operation
           */
            public void receiveErrorobtenerListaSubbodegasItemsDejados(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizaObservacionesReporteRecepcionCarga method
            * override this method for handling normal response from actualizaObservacionesReporteRecepcionCarga operation
            */
           public void receiveResultactualizaObservacionesReporteRecepcionCarga(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ActualizaObservacionesReporteRecepcionCargaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizaObservacionesReporteRecepcionCarga operation
           */
            public void receiveErroractualizaObservacionesReporteRecepcionCarga(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerKilometrosHorasSalidaCamioneta method
            * override this method for handling normal response from obtenerKilometrosHorasSalidaCamioneta operation
            */
           public void receiveResultobtenerKilometrosHorasSalidaCamioneta(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerKilometrosHorasSalidaCamionetaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerKilometrosHorasSalidaCamioneta operation
           */
            public void receiveErrorobtenerKilometrosHorasSalidaCamioneta(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getObtenerNARTotalExistentes method
            * override this method for handling normal response from getObtenerNARTotalExistentes operation
            */
           public void receiveResultgetObtenerNARTotalExistentes(
                    com.datacode.avon_ots_ws.ReportesControllerStub.GetObtenerNARTotalExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getObtenerNARTotalExistentes operation
           */
            public void receiveErrorgetObtenerNARTotalExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerEncabezadoAnalisisDeEfectivo method
            * override this method for handling normal response from obtenerEncabezadoAnalisisDeEfectivo operation
            */
           public void receiveResultobtenerEncabezadoAnalisisDeEfectivo(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerEncabezadoAnalisisDeEfectivoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerEncabezadoAnalisisDeEfectivo operation
           */
            public void receiveErrorobtenerEncabezadoAnalisisDeEfectivo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDetalleNoAceptacionReparto method
            * override this method for handling normal response from getDetalleNoAceptacionReparto operation
            */
           public void receiveResultgetDetalleNoAceptacionReparto(
                    com.datacode.avon_ots_ws.ReportesControllerStub.GetDetalleNoAceptacionRepartoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDetalleNoAceptacionReparto operation
           */
            public void receiveErrorgetDetalleNoAceptacionReparto(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerPedidosEntregadosGerenteZonal method
            * override this method for handling normal response from obtenerPedidosEntregadosGerenteZonal operation
            */
           public void receiveResultobtenerPedidosEntregadosGerenteZonal(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerPedidosEntregadosGerenteZonalResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerPedidosEntregadosGerenteZonal operation
           */
            public void receiveErrorobtenerPedidosEntregadosGerenteZonal(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for devuelveSubBodegasSalidaReparto method
            * override this method for handling normal response from devuelveSubBodegasSalidaReparto operation
            */
           public void receiveResultdevuelveSubBodegasSalidaReparto(
                    com.datacode.avon_ots_ws.ReportesControllerStub.DevuelveSubBodegasSalidaRepartoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from devuelveSubBodegasSalidaReparto operation
           */
            public void receiveErrordevuelveSubBodegasSalidaReparto(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDetalleNARTotalLibres method
            * override this method for handling normal response from getDetalleNARTotalLibres operation
            */
           public void receiveResultgetDetalleNARTotalLibres(
                    com.datacode.avon_ots_ws.ReportesControllerStub.GetDetalleNARTotalLibresResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDetalleNARTotalLibres operation
           */
            public void receiveErrorgetDetalleNARTotalLibres(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for devuelveOrdenesDejadasSubBodega method
            * override this method for handling normal response from devuelveOrdenesDejadasSubBodega operation
            */
           public void receiveResultdevuelveOrdenesDejadasSubBodega(
                    com.datacode.avon_ots_ws.ReportesControllerStub.DevuelveOrdenesDejadasSubBodegaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from devuelveOrdenesDejadasSubBodega operation
           */
            public void receiveErrordevuelveOrdenesDejadasSubBodega(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerRelPedDejadosDetalle method
            * override this method for handling normal response from obtenerRelPedDejadosDetalle operation
            */
           public void receiveResultobtenerRelPedDejadosDetalle(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerRelPedDejadosDetalleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerRelPedDejadosDetalle operation
           */
            public void receiveErrorobtenerRelPedDejadosDetalle(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerItemsNoEntregadosEnAlmacen method
            * override this method for handling normal response from obtenerItemsNoEntregadosEnAlmacen operation
            */
           public void receiveResultobtenerItemsNoEntregadosEnAlmacen(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerItemsNoEntregadosEnAlmacenResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerItemsNoEntregadosEnAlmacen operation
           */
            public void receiveErrorobtenerItemsNoEntregadosEnAlmacen(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarPrimeraLiquidacionAnalisisEfectivo method
            * override this method for handling normal response from consultarPrimeraLiquidacionAnalisisEfectivo operation
            */
           public void receiveResultconsultarPrimeraLiquidacionAnalisisEfectivo(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ConsultarPrimeraLiquidacionAnalisisEfectivoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarPrimeraLiquidacionAnalisisEfectivo operation
           */
            public void receiveErrorconsultarPrimeraLiquidacionAnalisisEfectivo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerDatosReporteHistoricoRepresentantes method
            * override this method for handling normal response from obtenerDatosReporteHistoricoRepresentantes operation
            */
           public void receiveResultobtenerDatosReporteHistoricoRepresentantes(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerDatosReporteHistoricoRepresentantesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerDatosReporteHistoricoRepresentantes operation
           */
            public void receiveErrorobtenerDatosReporteHistoricoRepresentantes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtieneDatosReparto method
            * override this method for handling normal response from obtieneDatosReparto operation
            */
           public void receiveResultobtieneDatosReparto(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtieneDatosRepartoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtieneDatosReparto operation
           */
            public void receiveErrorobtieneDatosReparto(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerHeadersSalidaCamioneta method
            * override this method for handling normal response from obtenerHeadersSalidaCamioneta operation
            */
           public void receiveResultobtenerHeadersSalidaCamioneta(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerHeadersSalidaCamionetaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerHeadersSalidaCamioneta operation
           */
            public void receiveErrorobtenerHeadersSalidaCamioneta(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getObtenerNoAceptacionRepartoExistentes method
            * override this method for handling normal response from getObtenerNoAceptacionRepartoExistentes operation
            */
           public void receiveResultgetObtenerNoAceptacionRepartoExistentes(
                    com.datacode.avon_ots_ws.ReportesControllerStub.GetObtenerNoAceptacionRepartoExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getObtenerNoAceptacionRepartoExistentes operation
           */
            public void receiveErrorgetObtenerNoAceptacionRepartoExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerEncabezadoPedidosEntregadosGerenteZonal method
            * override this method for handling normal response from obtenerEncabezadoPedidosEntregadosGerenteZonal operation
            */
           public void receiveResultobtenerEncabezadoPedidosEntregadosGerenteZonal(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerEncabezadoPedidosEntregadosGerenteZonalResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerEncabezadoPedidosEntregadosGerenteZonal operation
           */
            public void receiveErrorobtenerEncabezadoPedidosEntregadosGerenteZonal(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerDestinatariosReportes method
            * override this method for handling normal response from obtenerDestinatariosReportes operation
            */
           public void receiveResultobtenerDestinatariosReportes(
                    com.datacode.avon_ots_ws.ReportesControllerStub.ObtenerDestinatariosReportesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerDestinatariosReportes operation
           */
            public void receiveErrorobtenerDestinatariosReportes(java.lang.Exception e) {
            }
                


    }
    