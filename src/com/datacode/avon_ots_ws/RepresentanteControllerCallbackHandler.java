
/**
 * RepresentanteControllerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  RepresentanteControllerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class RepresentanteControllerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public RepresentanteControllerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public RepresentanteControllerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for actualizarSecuenciaRepresentante method
            * override this method for handling normal response from actualizarSecuenciaRepresentante operation
            */
           public void receiveResultactualizarSecuenciaRepresentante(
                    com.datacode.avon_ots_ws.RepresentanteControllerStub.ActualizarSecuenciaRepresentanteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarSecuenciaRepresentante operation
           */
            public void receiveErroractualizarSecuenciaRepresentante(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarPreenrutadoDeRepresentante method
            * override this method for handling normal response from actualizarPreenrutadoDeRepresentante operation
            */
           public void receiveResultactualizarPreenrutadoDeRepresentante(
                    com.datacode.avon_ots_ws.RepresentanteControllerStub.ActualizarPreenrutadoDeRepresentanteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarPreenrutadoDeRepresentante operation
           */
            public void receiveErroractualizarPreenrutadoDeRepresentante(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerRepresentantesSinRutaAsignada method
            * override this method for handling normal response from obtenerRepresentantesSinRutaAsignada operation
            */
           public void receiveResultobtenerRepresentantesSinRutaAsignada(
                    com.datacode.avon_ots_ws.RepresentanteControllerStub.ObtenerRepresentantesSinRutaAsignadaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerRepresentantesSinRutaAsignada operation
           */
            public void receiveErrorobtenerRepresentantesSinRutaAsignada(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for seleccionarRepresentantesConOrden method
            * override this method for handling normal response from seleccionarRepresentantesConOrden operation
            */
           public void receiveResultseleccionarRepresentantesConOrden(
                    com.datacode.avon_ots_ws.RepresentanteControllerStub.SeleccionarRepresentantesConOrdenResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from seleccionarRepresentantesConOrden operation
           */
            public void receiveErrorseleccionarRepresentantesConOrden(java.lang.Exception e) {
            }
                


    }
    