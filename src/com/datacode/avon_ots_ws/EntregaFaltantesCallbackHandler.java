
/**
 * EntregaFaltantesCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  EntregaFaltantesCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class EntregaFaltantesCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public EntregaFaltantesCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public EntregaFaltantesCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for obtenerOrdenes method
            * override this method for handling normal response from obtenerOrdenes operation
            */
           public void receiveResultobtenerOrdenes(
                    com.datacode.avon_ots_ws.EntregaFaltantesStub.ObtenerOrdenesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerOrdenes operation
           */
            public void receiveErrorobtenerOrdenes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerPremiosUnitarios method
            * override this method for handling normal response from obtenerPremiosUnitarios operation
            */
           public void receiveResultobtenerPremiosUnitarios(
                    com.datacode.avon_ots_ws.EntregaFaltantesStub.ObtenerPremiosUnitariosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerPremiosUnitarios operation
           */
            public void receiveErrorobtenerPremiosUnitarios(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerRepresentantes method
            * override this method for handling normal response from obtenerRepresentantes operation
            */
           public void receiveResultobtenerRepresentantes(
                    com.datacode.avon_ots_ws.EntregaFaltantesStub.ObtenerRepresentantesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerRepresentantes operation
           */
            public void receiveErrorobtenerRepresentantes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerCajas method
            * override this method for handling normal response from obtenerCajas operation
            */
           public void receiveResultobtenerCajas(
                    com.datacode.avon_ots_ws.EntregaFaltantesStub.ObtenerCajasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerCajas operation
           */
            public void receiveErrorobtenerCajas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for entregarItems method
            * override this method for handling normal response from entregarItems operation
            */
           public void receiveResultentregarItems(
                    com.datacode.avon_ots_ws.EntregaFaltantesStub.EntregarItemsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from entregarItems operation
           */
            public void receiveErrorentregarItems(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for buscarRepresentante method
            * override this method for handling normal response from buscarRepresentante operation
            */
           public void receiveResultbuscarRepresentante(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from buscarRepresentante operation
           */
            public void receiveErrorbuscarRepresentante(java.lang.Exception e) {
            }
                


    }
    