
/**
 * RecolectarRemitosVentanillaCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  RecolectarRemitosVentanillaCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class RecolectarRemitosVentanillaCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public RecolectarRemitosVentanillaCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public RecolectarRemitosVentanillaCallbackHandler(){
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
                    com.datacode.avon_ots_ws.RecolectarRemitosVentanillaStub.ObtenerListaCampaniasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaCampanias operation
           */
            public void receiveErrorobtenerListaCampanias(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerRepresentantes method
            * override this method for handling normal response from obtenerRepresentantes operation
            */
           public void receiveResultobtenerRepresentantes(
                    com.datacode.avon_ots_ws.RecolectarRemitosVentanillaStub.ObtenerRepresentantesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerRepresentantes operation
           */
            public void receiveErrorobtenerRepresentantes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaZonas method
            * override this method for handling normal response from obtenerListaZonas operation
            */
           public void receiveResultobtenerListaZonas(
                    com.datacode.avon_ots_ws.RecolectarRemitosVentanillaStub.ObtenerListaZonasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaZonas operation
           */
            public void receiveErrorobtenerListaZonas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for verificarCierreDeZona method
            * override this method for handling normal response from verificarCierreDeZona operation
            */
           public void receiveResultverificarCierreDeZona(
                    com.datacode.avon_ots_ws.RecolectarRemitosVentanillaStub.VerificarCierreDeZonaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from verificarCierreDeZona operation
           */
            public void receiveErrorverificarCierreDeZona(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for recolectarRemitos method
            * override this method for handling normal response from recolectarRemitos operation
            */
           public void receiveResultrecolectarRemitos(
                    com.datacode.avon_ots_ws.RecolectarRemitosVentanillaStub.RecolectarRemitosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from recolectarRemitos operation
           */
            public void receiveErrorrecolectarRemitos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerCausasNoRecoleccion method
            * override this method for handling normal response from obtenerCausasNoRecoleccion operation
            */
           public void receiveResultobtenerCausasNoRecoleccion(
                    com.datacode.avon_ots_ws.RecolectarRemitosVentanillaStub.ObtenerCausasNoRecoleccionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerCausasNoRecoleccion operation
           */
            public void receiveErrorobtenerCausasNoRecoleccion(java.lang.Exception e) {
            }
                


    }
    