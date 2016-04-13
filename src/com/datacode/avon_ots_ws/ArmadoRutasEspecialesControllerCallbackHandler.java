
/**
 * ArmadoRutasEspecialesControllerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  ArmadoRutasEspecialesControllerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ArmadoRutasEspecialesControllerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ArmadoRutasEspecialesControllerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ArmadoRutasEspecialesControllerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getCampanias method
            * override this method for handling normal response from getCampanias operation
            */
           public void receiveResultgetCampanias(
                    com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.GetCampaniasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCampanias operation
           */
            public void receiveErrorgetCampanias(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtieneItems method
            * override this method for handling normal response from obtieneItems operation
            */
           public void receiveResultobtieneItems(
                    com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.ObtieneItemsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtieneItems operation
           */
            public void receiveErrorobtieneItems(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for guardarRutaUnitarios method
            * override this method for handling normal response from guardarRutaUnitarios operation
            */
           public void receiveResultguardarRutaUnitarios(
                    com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.GuardarRutaUnitariosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from guardarRutaUnitarios operation
           */
            public void receiveErrorguardarRutaUnitarios(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarRutasEspeciales method
            * override this method for handling normal response from consultarRutasEspeciales operation
            */
           public void receiveResultconsultarRutasEspeciales(
                    com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.ConsultarRutasEspecialesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarRutasEspeciales operation
           */
            public void receiveErrorconsultarRutasEspeciales(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarRutasEspecialesDetalleUnitarios method
            * override this method for handling normal response from consultarRutasEspecialesDetalleUnitarios operation
            */
           public void receiveResultconsultarRutasEspecialesDetalleUnitarios(
                    com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.ConsultarRutasEspecialesDetalleUnitariosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarRutasEspecialesDetalleUnitarios operation
           */
            public void receiveErrorconsultarRutasEspecialesDetalleUnitarios(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for guardarRuta method
            * override this method for handling normal response from guardarRuta operation
            */
           public void receiveResultguardarRuta(
                    com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.GuardarRutaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from guardarRuta operation
           */
            public void receiveErrorguardarRuta(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarRutasEspecialesDetalle method
            * override this method for handling normal response from consultarRutasEspecialesDetalle operation
            */
           public void receiveResultconsultarRutasEspecialesDetalle(
                    com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.ConsultarRutasEspecialesDetalleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarRutasEspecialesDetalle operation
           */
            public void receiveErrorconsultarRutasEspecialesDetalle(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarRuta method
            * override this method for handling normal response from eliminarRuta operation
            */
           public void receiveResulteliminarRuta(
                    com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.EliminarRutaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarRuta operation
           */
            public void receiveErroreliminarRuta(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtieneRegistros method
            * override this method for handling normal response from obtieneRegistros operation
            */
           public void receiveResultobtieneRegistros(
                    com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.ObtieneRegistrosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtieneRegistros operation
           */
            public void receiveErrorobtieneRegistros(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for generarFolio method
            * override this method for handling normal response from generarFolio operation
            */
           public void receiveResultgenerarFolio(
                    com.datacode.avon_ots_ws.ArmadoRutasEspecialesControllerStub.GenerarFolioResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from generarFolio operation
           */
            public void receiveErrorgenerarFolio(java.lang.Exception e) {
            }
                


    }
    