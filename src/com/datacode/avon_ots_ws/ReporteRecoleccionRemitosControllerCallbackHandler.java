
/**
 * ReporteRecoleccionRemitosControllerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  ReporteRecoleccionRemitosControllerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ReporteRecoleccionRemitosControllerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ReporteRecoleccionRemitosControllerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ReporteRecoleccionRemitosControllerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for obtenerConsultaRecoleccionRemitos method
            * override this method for handling normal response from obtenerConsultaRecoleccionRemitos operation
            */
           public void receiveResultobtenerConsultaRecoleccionRemitos(
                    com.datacode.avon_ots_ws.ReporteRecoleccionRemitosControllerStub.ObtenerConsultaRecoleccionRemitosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerConsultaRecoleccionRemitos operation
           */
            public void receiveErrorobtenerConsultaRecoleccionRemitos(java.lang.Exception e) {
            }
                


    }
    