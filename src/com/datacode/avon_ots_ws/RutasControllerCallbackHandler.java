
/**
 * RutasControllerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  RutasControllerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class RutasControllerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public RutasControllerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public RutasControllerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getRutas method
            * override this method for handling normal response from getRutas operation
            */
           public void receiveResultgetRutas(
                    com.datacode.avon_ots_ws.RutasControllerStub.GetRutasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRutas operation
           */
            public void receiveErrorgetRutas(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getRutasExistentes method
            * override this method for handling normal response from getRutasExistentes operation
            */
           public void receiveResultgetRutasExistentes(
                    com.datacode.avon_ots_ws.RutasControllerStub.GetRutasExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRutasExistentes operation
           */
            public void receiveErrorgetRutasExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarRuta method
            * override this method for handling normal response from insertarRuta operation
            */
           public void receiveResultinsertarRuta(
                    com.datacode.avon_ots_ws.RutasControllerStub.InsertarRutaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarRuta operation
           */
            public void receiveErrorinsertarRuta(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRuta method
            * override this method for handling normal response from getRuta operation
            */
           public void receiveResultgetRuta(
                    com.datacode.avon_ots_ws.RutasControllerStub.GetRutaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRuta operation
           */
            public void receiveErrorgetRuta(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarRuta method
            * override this method for handling normal response from actualizarRuta operation
            */
           public void receiveResultactualizarRuta(
                    com.datacode.avon_ots_ws.RutasControllerStub.ActualizarRutaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarRuta operation
           */
            public void receiveErroractualizarRuta(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getRutasPorLDC method
            * override this method for handling normal response from getRutasPorLDC operation
            */
           public void receiveResultgetRutasPorLDC(
                    com.datacode.avon_ots_ws.RutasControllerStub.GetRutasPorLDCResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRutasPorLDC operation
           */
            public void receiveErrorgetRutasPorLDC(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarRuta method
            * override this method for handling normal response from eliminarRuta operation
            */
           public void receiveResulteliminarRuta(
                    com.datacode.avon_ots_ws.RutasControllerStub.EliminarRutaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarRuta operation
           */
            public void receiveErroreliminarRuta(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPaises method
            * override this method for handling normal response from getPaises operation
            */
           public void receiveResultgetPaises(
                    com.datacode.avon_ots_ws.RutasControllerStub.GetPaisesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPaises operation
           */
            public void receiveErrorgetPaises(java.lang.Exception e) {
            }
                


    }
    