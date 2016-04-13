
/**
 * LiquidacionVentanillaMasivaCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  LiquidacionVentanillaMasivaCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class LiquidacionVentanillaMasivaCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public LiquidacionVentanillaMasivaCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public LiquidacionVentanillaMasivaCallbackHandler(){
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
                    com.datacode.avon_ots_ws.LiquidacionVentanillaMasivaStub.ObtenerOrdenesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerOrdenes operation
           */
            public void receiveErrorobtenerOrdenes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerBancos method
            * override this method for handling normal response from obtenerBancos operation
            */
           public void receiveResultobtenerBancos(
                    com.datacode.avon_ots_ws.LiquidacionVentanillaMasivaStub.ObtenerBancosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerBancos operation
           */
            public void receiveErrorobtenerBancos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerTipoPagos method
            * override this method for handling normal response from obtenerTipoPagos operation
            */
           public void receiveResultobtenerTipoPagos(
                    com.datacode.avon_ots_ws.LiquidacionVentanillaMasivaStub.ObtenerTipoPagosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerTipoPagos operation
           */
            public void receiveErrorobtenerTipoPagos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerPagos method
            * override this method for handling normal response from obtenerPagos operation
            */
           public void receiveResultobtenerPagos(
                    com.datacode.avon_ots_ws.LiquidacionVentanillaMasivaStub.ObtenerPagosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerPagos operation
           */
            public void receiveErrorobtenerPagos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerQuienRecibe method
            * override this method for handling normal response from obtenerQuienRecibe operation
            */
           public void receiveResultobtenerQuienRecibe(
                    com.datacode.avon_ots_ws.LiquidacionVentanillaMasivaStub.ObtenerQuienRecibeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerQuienRecibe operation
           */
            public void receiveErrorobtenerQuienRecibe(java.lang.Exception e) {
            }
                


    }
    