
/**
 * RecepcionAjustesReacoControllerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  RecepcionAjustesReacoControllerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class RecepcionAjustesReacoControllerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public RecepcionAjustesReacoControllerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public RecepcionAjustesReacoControllerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for eliminarRecepcionAjuste method
            * override this method for handling normal response from eliminarRecepcionAjuste operation
            */
           public void receiveResulteliminarRecepcionAjuste(
                    com.datacode.avon_ots_ws.RecepcionAjustesReacoControllerStub.EliminarRecepcionAjusteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarRecepcionAjuste operation
           */
            public void receiveErroreliminarRecepcionAjuste(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerRecepcionAjuste method
            * override this method for handling normal response from obtenerRecepcionAjuste operation
            */
           public void receiveResultobtenerRecepcionAjuste(
                    com.datacode.avon_ots_ws.RecepcionAjustesReacoControllerStub.ObtenerRecepcionAjusteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerRecepcionAjuste operation
           */
            public void receiveErrorobtenerRecepcionAjuste(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for guardarRecepcionAjuste method
            * override this method for handling normal response from guardarRecepcionAjuste operation
            */
           public void receiveResultguardarRecepcionAjuste(
                    com.datacode.avon_ots_ws.RecepcionAjustesReacoControllerStub.GuardarRecepcionAjusteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from guardarRecepcionAjuste operation
           */
            public void receiveErrorguardarRecepcionAjuste(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaRecepcionAjustes method
            * override this method for handling normal response from obtenerListaRecepcionAjustes operation
            */
           public void receiveResultobtenerListaRecepcionAjustes(
                    com.datacode.avon_ots_ws.RecepcionAjustesReacoControllerStub.ObtenerListaRecepcionAjustesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaRecepcionAjustes operation
           */
            public void receiveErrorobtenerListaRecepcionAjustes(java.lang.Exception e) {
            }
                


    }
    