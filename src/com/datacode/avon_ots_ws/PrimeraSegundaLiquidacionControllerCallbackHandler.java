
/**
 * PrimeraSegundaLiquidacionControllerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  PrimeraSegundaLiquidacionControllerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class PrimeraSegundaLiquidacionControllerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public PrimeraSegundaLiquidacionControllerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public PrimeraSegundaLiquidacionControllerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for actualizarRouteSeq method
            * override this method for handling normal response from actualizarRouteSeq operation
            */
           public void receiveResultactualizarRouteSeq(
                    com.datacode.avon_ots_ws.PrimeraSegundaLiquidacionControllerStub.ActualizarRouteSeqResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarRouteSeq operation
           */
            public void receiveErroractualizarRouteSeq(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for ejecutarReversionOrdenes method
            * override this method for handling normal response from ejecutarReversionOrdenes operation
            */
           public void receiveResultejecutarReversionOrdenes(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from ejecutarReversionOrdenes operation
           */
            public void receiveErrorejecutarReversionOrdenes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for enviarPrimeraSegundaLiquidacion method
            * override this method for handling normal response from enviarPrimeraSegundaLiquidacion operation
            */
           public void receiveResultenviarPrimeraSegundaLiquidacion(
                    com.datacode.avon_ots_ws.PrimeraSegundaLiquidacionControllerStub.EnviarPrimeraSegundaLiquidacionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from enviarPrimeraSegundaLiquidacion operation
           */
            public void receiveErrorenviarPrimeraSegundaLiquidacion(java.lang.Exception e) {
            }
                


    }
    