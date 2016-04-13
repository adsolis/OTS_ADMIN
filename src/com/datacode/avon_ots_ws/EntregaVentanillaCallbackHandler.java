
/**
 * EntregaVentanillaCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  EntregaVentanillaCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class EntregaVentanillaCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public EntregaVentanillaCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public EntregaVentanillaCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getDetallePago method
            * override this method for handling normal response from getDetallePago operation
            */
           public void receiveResultgetDetallePago(
                    com.datacode.avon_ots_ws.EntregaVentanillaStub.GetDetallePagoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDetallePago operation
           */
            public void receiveErrorgetDetallePago(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for pagoVentanilla method
            * override this method for handling normal response from pagoVentanilla operation
            */
           public void receiveResultpagoVentanilla(
                    com.datacode.avon_ots_ws.EntregaVentanillaStub.PagoVentanillaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from pagoVentanilla operation
           */
            public void receiveErrorpagoVentanilla(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRepresentantes method
            * override this method for handling normal response from getRepresentantes operation
            */
           public void receiveResultgetRepresentantes(
                    com.datacode.avon_ots_ws.EntregaVentanillaStub.GetRepresentantesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRepresentantes operation
           */
            public void receiveErrorgetRepresentantes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for entregaVentanilla method
            * override this method for handling normal response from entregaVentanilla operation
            */
           public void receiveResultentregaVentanilla(
                    com.datacode.avon_ots_ws.EntregaVentanillaStub.EntregaVentanillaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from entregaVentanilla operation
           */
            public void receiveErrorentregaVentanilla(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getOrdenesRepresentante method
            * override this method for handling normal response from getOrdenesRepresentante operation
            */
           public void receiveResultgetOrdenesRepresentante(
                    com.datacode.avon_ots_ws.EntregaVentanillaStub.GetOrdenesRepresentanteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOrdenesRepresentante operation
           */
            public void receiveErrorgetOrdenesRepresentante(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getBancos method
            * override this method for handling normal response from getBancos operation
            */
           public void receiveResultgetBancos(
                    com.datacode.avon_ots_ws.EntregaVentanillaStub.GetBancosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getBancos operation
           */
            public void receiveErrorgetBancos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPersonaRecibe method
            * override this method for handling normal response from getPersonaRecibe operation
            */
           public void receiveResultgetPersonaRecibe(
                    com.datacode.avon_ots_ws.EntregaVentanillaStub.GetPersonaRecibeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPersonaRecibe operation
           */
            public void receiveErrorgetPersonaRecibe(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTipoPago method
            * override this method for handling normal response from getTipoPago operation
            */
           public void receiveResultgetTipoPago(
                    com.datacode.avon_ots_ws.EntregaVentanillaStub.GetTipoPagoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTipoPago operation
           */
            public void receiveErrorgetTipoPago(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDetalleOrden method
            * override this method for handling normal response from getDetalleOrden operation
            */
           public void receiveResultgetDetalleOrden(
                    com.datacode.avon_ots_ws.EntregaVentanillaStub.GetDetalleOrdenResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDetalleOrden operation
           */
            public void receiveErrorgetDetalleOrden(java.lang.Exception e) {
            }
                


    }
    