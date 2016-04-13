
/**
 * LiquidacionRepartoWebCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  LiquidacionRepartoWebCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class LiquidacionRepartoWebCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public LiquidacionRepartoWebCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public LiquidacionRepartoWebCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for obtenerListaCampanias method
            * override this method for handling normal response from obtenerListaCampanias operation
            */
           public void receiveResultobtenerListaCampanias(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerListaCampaniasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaCampanias operation
           */
            public void receiveErrorobtenerListaCampanias(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaRutas method
            * override this method for handling normal response from obtenerListaRutas operation
            */
           public void receiveResultobtenerListaRutas(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerListaRutasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaRutas operation
           */
            public void receiveErrorobtenerListaRutas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerRepresentantes method
            * override this method for handling normal response from obtenerRepresentantes operation
            */
           public void receiveResultobtenerRepresentantes(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerRepresentantesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerRepresentantes operation
           */
            public void receiveErrorobtenerRepresentantes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerItemsPorOrden method
            * override this method for handling normal response from obtenerItemsPorOrden operation
            */
           public void receiveResultobtenerItemsPorOrden(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerItemsPorOrdenResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerItemsPorOrden operation
           */
            public void receiveErrorobtenerItemsPorOrden(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaOrdenes method
            * override this method for handling normal response from obtenerListaOrdenes operation
            */
           public void receiveResultobtenerListaOrdenes(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerListaOrdenesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaOrdenes operation
           */
            public void receiveErrorobtenerListaOrdenes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarInformacionOrden method
            * override this method for handling normal response from actualizarInformacionOrden operation
            */
           public void receiveResultactualizarInformacionOrden(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ActualizarInformacionOrdenResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarInformacionOrden operation
           */
            public void receiveErroractualizarInformacionOrden(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for enviarLiquidacionesPendientes method
            * override this method for handling normal response from enviarLiquidacionesPendientes operation
            */
           public void receiveResultenviarLiquidacionesPendientes(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.EnviarLiquidacionesPendientesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from enviarLiquidacionesPendientes operation
           */
            public void receiveErrorenviarLiquidacionesPendientes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerTiposLiquidacion method
            * override this method for handling normal response from obtenerTiposLiquidacion operation
            */
           public void receiveResultobtenerTiposLiquidacion(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerTiposLiquidacionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerTiposLiquidacion operation
           */
            public void receiveErrorobtenerTiposLiquidacion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaTipoInformantes method
            * override this method for handling normal response from obtenerListaTipoInformantes operation
            */
           public void receiveResultobtenerListaTipoInformantes(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerListaTipoInformantesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaTipoInformantes operation
           */
            public void receiveErrorobtenerListaTipoInformantes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for devolverOrden method
            * override this method for handling normal response from devolverOrden operation
            */
           public void receiveResultdevolverOrden(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.DevolverOrdenResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from devolverOrden operation
           */
            public void receiveErrordevolverOrden(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaPagosOrden method
            * override this method for handling normal response from obtenerListaPagosOrden operation
            */
           public void receiveResultobtenerListaPagosOrden(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerListaPagosOrdenResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaPagosOrden operation
           */
            public void receiveErrorobtenerListaPagosOrden(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaTiposDePago method
            * override this method for handling normal response from obtenerListaTiposDePago operation
            */
           public void receiveResultobtenerListaTiposDePago(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerListaTiposDePagoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaTiposDePago operation
           */
            public void receiveErrorobtenerListaTiposDePago(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaBancos method
            * override this method for handling normal response from obtenerListaBancos operation
            */
           public void receiveResultobtenerListaBancos(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerListaBancosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaBancos operation
           */
            public void receiveErrorobtenerListaBancos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaRazonesDevolucion method
            * override this method for handling normal response from obtenerListaRazonesDevolucion operation
            */
           public void receiveResultobtenerListaRazonesDevolucion(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerListaRazonesDevolucionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaRazonesDevolucion operation
           */
            public void receiveErrorobtenerListaRazonesDevolucion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for liquidarOrden method
            * override this method for handling normal response from liquidarOrden operation
            */
           public void receiveResultliquidarOrden(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.LiquidarOrdenResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from liquidarOrden operation
           */
            public void receiveErrorliquidarOrden(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaZonas method
            * override this method for handling normal response from obtenerListaZonas operation
            */
           public void receiveResultobtenerListaZonas(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerListaZonasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaZonas operation
           */
            public void receiveErrorobtenerListaZonas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaQuienRecibe method
            * override this method for handling normal response from obtenerListaQuienRecibe operation
            */
           public void receiveResultobtenerListaQuienRecibe(
                    com.datacode.avon_ots_ws.LiquidacionRepartoWebStub.ObtenerListaQuienRecibeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaQuienRecibe operation
           */
            public void receiveErrorobtenerListaQuienRecibe(java.lang.Exception e) {
            }
                


    }
    