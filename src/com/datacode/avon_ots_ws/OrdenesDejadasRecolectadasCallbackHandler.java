
/**
 * OrdenesDejadasRecolectadasCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:01:29 GMT)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  OrdenesDejadasRecolectadasCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class OrdenesDejadasRecolectadasCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public OrdenesDejadasRecolectadasCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public OrdenesDejadasRecolectadasCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for obtenerPremiosUnitariosOrdenDejadaRecolectada method
            * override this method for handling normal response from obtenerPremiosUnitariosOrdenDejadaRecolectada operation
            */
           public void receiveResultobtenerPremiosUnitariosOrdenDejadaRecolectada(
                    com.datacode.avon_ots_ws.ObtenerPremiosUnitariosOrdenDejadaRecolectadaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerPremiosUnitariosOrdenDejadaRecolectada operation
           */
            public void receiveErrorobtenerPremiosUnitariosOrdenDejadaRecolectada(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerCajasOrdenDejadaRecolectada method
            * override this method for handling normal response from obtenerCajasOrdenDejadaRecolectada operation
            */
           public void receiveResultobtenerCajasOrdenDejadaRecolectada(
                    com.datacode.avon_ots_ws.ObtenerCajasOrdenDejadaRecolectadaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerCajasOrdenDejadaRecolectada operation
           */
            public void receiveErrorobtenerCajasOrdenDejadaRecolectada(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaLiquidacionesMail method
            * override this method for handling normal response from obtenerListaLiquidacionesMail operation
            */
           public void receiveResultobtenerListaLiquidacionesMail(
                    com.datacode.avon_ots_ws.ObtenerListaLiquidacionesMailResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaLiquidacionesMail operation
           */
            public void receiveErrorobtenerListaLiquidacionesMail(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerPUPOrdenesDejadasRecolectadas method
            * override this method for handling normal response from obtenerPUPOrdenesDejadasRecolectadas operation
            */
           public void receiveResultobtenerPUPOrdenesDejadasRecolectadas(
                    com.datacode.avon_ots_ws.ObtenerPUPOrdenesDejadasRecolectadasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerPUPOrdenesDejadasRecolectadas operation
           */
            public void receiveErrorobtenerPUPOrdenesDejadasRecolectadas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarStatusLiquidacionesMail method
            * override this method for handling normal response from actualizarStatusLiquidacionesMail operation
            */
           public void receiveResultactualizarStatusLiquidacionesMail(
                    com.datacode.avon_ots_ws.ActualizarStatusLiquidacionesMailResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarStatusLiquidacionesMail operation
           */
            public void receiveErroractualizarStatusLiquidacionesMail(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerDocumentosOrdenDejadaRecolectada method
            * override this method for handling normal response from obtenerDocumentosOrdenDejadaRecolectada operation
            */
           public void receiveResultobtenerDocumentosOrdenDejadaRecolectada(
                    com.datacode.avon_ots_ws.ObtenerDocumentosOrdenDejadaRecolectadaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerDocumentosOrdenDejadaRecolectada operation
           */
            public void receiveErrorobtenerDocumentosOrdenDejadaRecolectada(java.lang.Exception e) {
            }
                


    }
    