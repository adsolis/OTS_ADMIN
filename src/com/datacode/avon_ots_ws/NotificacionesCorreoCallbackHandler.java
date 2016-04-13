
/**
 * NotificacionesCorreoCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  NotificacionesCorreoCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class NotificacionesCorreoCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public NotificacionesCorreoCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public NotificacionesCorreoCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for obtenerNotificacionesCorreoVencidas method
            * override this method for handling normal response from obtenerNotificacionesCorreoVencidas operation
            */
           public void receiveResultobtenerNotificacionesCorreoVencidas(
                    com.datacode.avon_ots_ws.NotificacionesCorreoStub.ObtenerNotificacionesCorreoVencidasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerNotificacionesCorreoVencidas operation
           */
            public void receiveErrorobtenerNotificacionesCorreoVencidas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerNotificacionesVencidas method
            * override this method for handling normal response from obtenerNotificacionesVencidas operation
            */
           public void receiveResultobtenerNotificacionesVencidas(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerNotificacionesVencidas operation
           */
            public void receiveErrorobtenerNotificacionesVencidas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarFechaUltimaEjecucion method
            * override this method for handling normal response from actualizarFechaUltimaEjecucion operation
            */
           public void receiveResultactualizarFechaUltimaEjecucion(
                    com.datacode.avon_ots_ws.NotificacionesCorreoStub.ActualizarFechaUltimaEjecucionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarFechaUltimaEjecucion operation
           */
            public void receiveErroractualizarFechaUltimaEjecucion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerStringStored method
            * override this method for handling normal response from obtenerStringStored operation
            */
           public void receiveResultobtenerStringStored(
                    com.datacode.avon_ots_ws.NotificacionesCorreoStub.ObtenerStringStoredResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerStringStored operation
           */
            public void receiveErrorobtenerStringStored(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerTablaStore method
            * override this method for handling normal response from obtenerTablaStore operation
            */
           public void receiveResultobtenerTablaStore(
                    com.datacode.avon_ots_ws.NotificacionesCorreoStub.ObtenerTablaStoreResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerTablaStore operation
           */
            public void receiveErrorobtenerTablaStore(java.lang.Exception e) {
            }
                


    }
    