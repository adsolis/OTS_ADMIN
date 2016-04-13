
/**
 * LiquidacionSubBodegaControllerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  LiquidacionSubBodegaControllerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class LiquidacionSubBodegaControllerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public LiquidacionSubBodegaControllerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public LiquidacionSubBodegaControllerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for registrarLiquidacionSubBodega method
            * override this method for handling normal response from registrarLiquidacionSubBodega operation
            */
           public void receiveResultregistrarLiquidacionSubBodega(
                    com.datacode.avon_ots_ws.LiquidacionSubBodegaControllerStub.RegistrarLiquidacionSubBodegaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from registrarLiquidacionSubBodega operation
           */
            public void receiveErrorregistrarLiquidacionSubBodega(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerGridLiqSubBodega method
            * override this method for handling normal response from obtenerGridLiqSubBodega operation
            */
           public void receiveResultobtenerGridLiqSubBodega(
                    com.datacode.avon_ots_ws.LiquidacionSubBodegaControllerStub.ObtenerGridLiqSubBodegaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerGridLiqSubBodega operation
           */
            public void receiveErrorobtenerGridLiqSubBodega(java.lang.Exception e) {
            }
                


    }
    