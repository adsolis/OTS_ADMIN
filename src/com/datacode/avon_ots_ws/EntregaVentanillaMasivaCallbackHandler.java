
/**
 * EntregaVentanillaMasivaCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  EntregaVentanillaMasivaCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class EntregaVentanillaMasivaCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public EntregaVentanillaMasivaCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public EntregaVentanillaMasivaCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for obtenerRepresentantes method
            * override this method for handling normal response from obtenerRepresentantes operation
            */
           public void receiveResultobtenerRepresentantes(
                    com.datacode.avon_ots_ws.EntregaVentanillaMasivaStub.ObtenerRepresentantesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerRepresentantes operation
           */
            public void receiveErrorobtenerRepresentantes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerCampania method
            * override this method for handling normal response from obtenerCampania operation
            */
           public void receiveResultobtenerCampania(
                    com.datacode.avon_ots_ws.EntregaVentanillaMasivaStub.ObtenerCampaniaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerCampania operation
           */
            public void receiveErrorobtenerCampania(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerDetalleOrden method
            * override this method for handling normal response from obtenerDetalleOrden operation
            */
           public void receiveResultobtenerDetalleOrden(
                    com.datacode.avon_ots_ws.EntregaVentanillaMasivaStub.ObtenerDetalleOrdenResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerDetalleOrden operation
           */
            public void receiveErrorobtenerDetalleOrden(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizaEntregaVentanilla method
            * override this method for handling normal response from actualizaEntregaVentanilla operation
            */
           public void receiveResultactualizaEntregaVentanilla(
                    com.datacode.avon_ots_ws.EntregaVentanillaMasivaStub.ActualizaEntregaVentanillaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizaEntregaVentanilla operation
           */
            public void receiveErroractualizaEntregaVentanilla(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerOrdenesRepresentante method
            * override this method for handling normal response from obtenerOrdenesRepresentante operation
            */
           public void receiveResultobtenerOrdenesRepresentante(
                    com.datacode.avon_ots_ws.EntregaVentanillaMasivaStub.ObtenerOrdenesRepresentanteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerOrdenesRepresentante operation
           */
            public void receiveErrorobtenerOrdenesRepresentante(java.lang.Exception e) {
            }
                


    }
    