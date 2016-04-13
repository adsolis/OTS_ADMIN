
/**
 * CierreZonaCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  CierreZonaCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CierreZonaCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CierreZonaCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CierreZonaCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getCierreZona method
            * override this method for handling normal response from getCierreZona operation
            */
           public void receiveResultgetCierreZona(
                    com.datacode.avon_ots_ws.CierreZonaStub.GetCierreZonaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCierreZona operation
           */
            public void receiveErrorgetCierreZona(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizaItem method
            * override this method for handling normal response from actualizaItem operation
            */
           public void receiveResultactualizaItem(
                    com.datacode.avon_ots_ws.CierreZonaStub.ActualizaItemResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizaItem operation
           */
            public void receiveErroractualizaItem(java.lang.Exception e) {
            }
                


    }
    